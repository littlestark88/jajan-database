package com.littlestark.jajan.service.auth;

import com.littlestark.jajan.controller.error.ForbiddenException;
import com.littlestark.jajan.controller.user.Role;
import com.littlestark.jajan.model.entity.TokenEntity;
import com.littlestark.jajan.model.entity.UserEntity;
import com.littlestark.jajan.model.request.user.CreateUserRequest;
import com.littlestark.jajan.model.request.user.LoginUserRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.repository.IAuthenticationRepository;
import com.littlestark.jajan.repository.ITokenRepository;
import com.littlestark.jajan.service.email.IEmail;
import com.littlestark.jajan.service.jwt.JwtService;
import com.littlestark.jajan.service.validation.ValidationUtils;
import com.littlestark.jajan.utils.ResourceValue;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private IAuthenticationRepository userRepository;
    @Autowired
    private ITokenRepository tokenRepository;
    private final LocalDateTime localDateTime = LocalDateTime.now();
    @Autowired
    private ValidationUtils validationUtils;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager authenticationManager;
    private final IEmail emailSender;
    private final ResourceValue resourceValue;

    @Override
    public BaseResponse<Object> registerUser(CreateUserRequest createUserRequest) {
        validationUtils.validate(createUserRequest);
        var email = userRepository.findByEmail(createUserRequest.getEmail());
        var jwtToken = resourceValue.getEmptyString();
        var message = resourceValue.getEmptyString();
        var isSuccess = false;
        if (email.isEmpty()) {
            var userEntity = UserEntity.builder()
                    .email(createUserRequest.getEmail())
                    .password(passwordEncoder.encode(createUserRequest.getPassword()))
                    .dateRegister(localDateTime)
                    .role(Role.USER)
                    .isVerificationUser(false)
                    .phoneNumber(createUserRequest.getPhoneNumber())
                    .build();
            var user = userRepository.save(userEntity);
            jwtToken = jwtService.generateToken(userEntity);
            message = resourceValue.getSuccessRegisterEmail();
            saveUserToken(user, jwtToken);
            var link = "tes" + jwtToken;
            emailSender.sendEmail(
                    createUserRequest.getEmail(),
                    buildEmail(createUserRequest.getEmail(), link)
            );
            isSuccess = true;
        } else {
            message = resourceValue.getRegisteredEmail();
        }

        return BaseResponse.builder()
                .message(message)
                .isSuccess(isSuccess)
                .build();
    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

    private void saveUserToken(UserEntity userEntity, String jwtToken) {
        var token = TokenEntity.builder()
                .token(jwtToken)
                .expiredAt(false)
                .createdAt(localDateTime)
                .expiredAt(false)
                .userEntity(userEntity)
                .build();
        tokenRepository.save(token);
    }

    @Override
    @Transactional
    public BaseResponse<Object> authenticationLogin(LoginUserRequest loginUserRequest) {

        validationUtils.validate(loginUserRequest);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserRequest.getEmail(),
                        loginUserRequest.getPassword()
                )
        );
        UserEntity userEntity = userRepository.findByEmail(loginUserRequest.getEmail()).orElseThrow();
        var token = resourceValue.getEmptyString();
        var message = resourceValue.getEmptyString();
        var isSuccess = false;
        if(passwordEncoder.matches(loginUserRequest.getPassword(), userEntity.getPassword())) {
            token = jwtService.generateToken(userEntity);
            message = resourceValue.getSuccessLogin();
            isSuccess = true;

        }

        return BaseResponse.builder()
                .token(token)
                .message(message)
                .data(userEntity.getId())
                .isSuccess(isSuccess)
                .build();
    }
}
