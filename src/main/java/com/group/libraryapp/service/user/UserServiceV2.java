package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UpdateRequest;
import com.group.libraryapp.dto.user.request.UserRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceV2 {
    private final UserRepository userRepository;


    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 아래 있는 함수가 시작될 때 start transaction;
    // 에러 없이 잘 끝났다면 commit;
    // 문제가 있다면 rollback;
    @Transactional
    public void saveUser(UserRequest userRequest){
        userRepository.save(new User(userRequest.getName(), userRequest.getAge()));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers(){
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }


    @Transactional //영속성 컨텍스트도 같이 시작
    public void updateUser(UpdateRequest updateRequest){
        User user = userRepository.findById(updateRequest.getId())
                .orElseThrow(IllegalArgumentException::new);

        user.updateName(updateRequest.getName()); //Entity 객체의 변경을 감지 (자동으로 저장)
//        userRepository.save(user);

    }

    @Transactional
    public void deleteUser(String name){
        User user = userRepository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);

        userRepository.delete(user);
    }
}
