package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UpdateRequest;
import com.group.libraryapp.dto.user.request.UserRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceV1 {

    private final UserJdbcRepository userJdbcRepository;

    public UserServiceV1(UserJdbcRepository userJdbcRepository) {
        this.userJdbcRepository = userJdbcRepository;
    }

    public void saveUser(UserRequest userRequest){
        userJdbcRepository.saveUser(userRequest.getName(), userRequest.getAge());
    }

    public List<UserResponse> getUsers(){
        return userJdbcRepository.getUsers();
    }

    public void updateUser(UpdateRequest updateRequest) {
        if (updateRequest.getName() == "" || userJdbcRepository.isUserExist(updateRequest.getId())) {
            throw new IllegalArgumentException();
        }
        userJdbcRepository.updateUserName(updateRequest.getName(), updateRequest.getId());
    }
    public void deleteUser(String name){
        if (userJdbcRepository.isUserExist(name)) {
            throw new IllegalArgumentException();
        }
        userJdbcRepository.deleteUser(name);
    }
}
