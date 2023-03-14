package com.littlestark.jajan.service.user;

import com.littlestark.jajan.controller.error.NotFoundException;
import com.littlestark.jajan.controller.user.Role;
import com.littlestark.jajan.model.entity.UserEntity;
import com.littlestark.jajan.model.request.user.ChangePasswordRequest;
import com.littlestark.jajan.model.request.user.CreateUserRequest;
import com.littlestark.jajan.model.request.user.LoginUserRequest;
import com.littlestark.jajan.model.response.UserResponse;
import com.littlestark.jajan.repository.IUserRepository;
import com.littlestark.jajan.service.jwt.JwtService;
import com.littlestark.jajan.utils.validation.ValidationUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private IUserRepository userRepository;
    private final LocalDateTime localDateTime = LocalDateTime.now();
    private ValidationUtils<CreateUserRequest> validationCreateUserRequest;
    private ValidationUtils<LoginUserRequest> validationLoginUserRequest;
    private ValidationUtils<ChangePasswordRequest> validationChangePasswordRequest;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserResponse createUser(CreateUserRequest createUserRequest) {
        validationCreateUserRequest.validate(createUserRequest);
        var userEntity = new UserEntity();
        userEntity.setEmail(createUserRequest.getEmail());
        userEntity.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        userEntity.setDateRegister(localDateTime);
        userEntity.setRole(Role.USER);
        userRepository.save(userEntity);
        var jwtToken = jwtService.generateToken(userEntity);
        return UserResponse.builder()
                .token(jwtToken)
                .build();

    }

    @Override
    public UserResponse findUsername(LoginUserRequest loginUserRequest) throws NotFoundException {
//        validationLoginUserRequest.validate(loginUserRequest);
//        UserEntity userEntity = userRepository.findById(loginUserRequest.getEmail()).orElseThrow(NotFoundException::new);
//        UserResponse userResponse = new UserResponse();
//        userResponse.setPassword(userEntity.getPassword());
        return null;
    }

    @Override
    public UserResponse authenticationLogin(LoginUserRequest loginUserRequest) {
        validationLoginUserRequest.validate(loginUserRequest);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserRequest.getEmail(),
                        loginUserRequest.getPassword()
                )
        );
        var userEntity = userRepository.findByEmail(loginUserRequest.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(userEntity);

        return UserResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public void putChangePassword(ChangePasswordRequest changePasswordRequest) throws Exception {
//        validationChangePasswordRequest.validate(changePasswordRequest);
//        UserEntity userEntity = userRepository.findById(changePasswordRequest.getEmail()).orElseThrow(Exception::new);
//        userEntity.setPassword(changePasswordRequest.getPassword());
//        userRepository.save(userEntity);
    }
}
