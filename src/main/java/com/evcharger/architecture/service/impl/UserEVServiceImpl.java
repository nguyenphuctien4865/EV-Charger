package com.evcharger.architecture.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.evcharger.architecture.entity.UserEV;
import com.evcharger.architecture.exception.common.ResourceNotFoundException;
import com.evcharger.architecture.model.ApiResponse;
import com.evcharger.architecture.model.UserEVDTO;
import com.evcharger.architecture.repository.UserEVRepository;
import com.evcharger.architecture.service.UserEVService;
import com.evcharger.architecture.specification.UserEVSpecification;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserEVServiceImpl implements UserEVService {

    @Autowired
    private UserEVRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserEVDTO createUser(UserEVDTO user) {

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UnsupportedOperationException("Username is already taken!");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UnsupportedOperationException("Email is already taken!");
        }

        return mapToDto(userRepository.save(mapToEntity(user)));
    }

    @Override
    public UserEVDTO getUserById(String id) {

        return mapToDto(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id)));
    }

    @Override
    public UserEVDTO updateUser(String id, UserEVDTO user) {

        UserEV userEV = userRepository.findByUserId(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        userEV.setUsername(user.getUsername());
        userEV.setEmail(user.getEmail());
        userEV.setPassword(user.getPassword());
        userEV.setFavorites(user.getFavorites());

        return mapToDto(userRepository.save(userEV));

    }

    @Override
    public void deleteUser(String id) {

        UserEV userEV = userRepository.findByUserId(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        userRepository.delete(userEV);
    }

    @Override
    public ApiResponse<UserEVDTO> listUsers(int page, int limit, String sortDir, String sortBy, String username,
            String email) {
        // TODO Auto-generated method stub
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, limit, sort);

        Specification<UserEV> spec = Specification.where(null);

        if (username != null && !username.isEmpty()) {
            spec = spec.and(UserEVSpecification.hasUsername(username));
        }
        if (email != null && !email.isEmpty()) {
            spec = spec.and(UserEVSpecification.hasEmail(email));
        }

        Page<UserEV> pageResult = userRepository.findAll(spec, pageable);

        List<UserEVDTO> userEVDTOs = pageResult.getContent()
                .stream().map(this::mapToDto).collect(Collectors.toList());

        ApiResponse<UserEVDTO> pageResponse = new ApiResponse<>();
        pageResponse.setContent(userEVDTOs);
        pageResponse.setPageNumber(pageResult.getNumber());
        pageResponse.setPageSize(pageResult.getSize());
        pageResponse.setTotalElements(pageResult.getTotalElements());
        pageResponse.setTotalPages(pageResult.getTotalPages());
        pageResponse.setLast(pageResult.isLast());
        pageResponse.setFirst(pageResult.isFirst());
        pageResponse.setEmpty(pageResult.isEmpty());

        log.info("Fetched {}  Users:", userEVDTOs.size());

        return pageResponse;
    }

    public UserEV mapToEntity(UserEVDTO userEVDTO) {
        return mapper.map(userEVDTO, UserEV.class);
    }

    public UserEVDTO mapToDto(UserEV userEV) {
        return mapper.map(userEV, UserEVDTO.class);
    }

}
