package com.evcharger.architecture.controller;

import com.evcharger.architecture.entity.User;
import com.evcharger.architecture.exception.core.ArchitectureException;
import com.evcharger.architecture.facade.UserFacade;
import com.evcharger.architecture.model.common.ResponseHandler;
import com.evcharger.architecture.model.user.UserModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.evcharger.architecture.util.Constants.UserPaths.USER_PATH;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER_PATH)
@Api(value = USER_PATH, tags = "User APIs")
public class UserController {

    //region
    private final UserFacade facade;
    //endregion

    /**
     * Save user
     *
     * @param model as UserModel
     * @return User
     * @throws ArchitectureException
     */
    @PostMapping
    @ApiOperation(value = "Save User", response = User.class)
    public ResponseEntity<Object> saveUser(@RequestBody UserModel model) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK, facade.saveUser(model), true);
    }

    /**
     * Get user by id
     *
     * @param userId as String
     * @return User
     * @throws ArchitectureException
     */
    @GetMapping
    @ApiOperation(value = "Get User", response = User.class)
    public ResponseEntity<Object> getUser(@RequestHeader String userId) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK, facade.getUser(userId), true);
    }
}
