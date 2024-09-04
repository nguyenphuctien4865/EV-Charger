package com.evcharger.architecture.facade;

import com.evcharger.architecture.exception.common.InvalidParamException;
import com.evcharger.architecture.exception.core.ArchitectureException;
import com.evcharger.architecture.entity.User;
import com.evcharger.architecture.exception.user.UserAlreadyExistException;
import com.evcharger.architecture.exception.user.UserNotFoundException;
import com.evcharger.architecture.model.user.UserModel;
import com.evcharger.architecture.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    //region
    private final UserService userService;
    //endregion

    /**
     * Get user by id
     *
     * @param userId as String
     * @return User
     * @throws ArchitectureException
     */
    public Object getUser(String userId) throws ArchitectureException {
        try {
            //Checking params
            if (userId.isEmpty())
                throw new InvalidParamException();
            //Checking user
            User user = userService.getUserById(userId);
            if (user == null)
                throw new UserNotFoundException();
            return user;
        } catch (ArchitectureException exception) {
            throw exception;
        }
    }

    /**
     * Save user
     *
     * @param model as UserModel
     * @return User
     * @throws ArchitectureException
     */
    public Object saveUser(UserModel model) throws ArchitectureException {
        try {
            //Checking params
            if (model == null || model.getUserId().isEmpty() ||
                    model.getName().isEmpty() || model.getUsername().isEmpty())
                throw new InvalidParamException();
            //Checking if user is already exist
            User existUser = userService.getUserById(model.getUserId());
            if (existUser != null)
                throw new UserAlreadyExistException();
            //Mapping model UserModel -> User
            User user = new ModelMapper().map(model, User.class);
            return userService.saveUser(user);
        } catch (ArchitectureException exception) {
            throw exception;
        }
    }
}
