package com.littlestark.jajan.service.user;

import com.littlestark.jajan.model.entity.UserEntity;
import com.littlestark.jajan.model.entity.VerificationUserEntity;
import com.littlestark.jajan.model.request.user.ChangePasswordRequest;
import com.littlestark.jajan.model.request.user.ChangePhoneNumberRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.model.response.user.UserResponse;
import com.littlestark.jajan.repository.IAuthenticationRepository;
import com.littlestark.jajan.repository.IVerificationUserRepository;
import com.littlestark.jajan.service.email.IEmail;
import com.littlestark.jajan.utils.ResourceValue;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private IAuthenticationRepository authenticationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final ResourceValue resourceValue;
    private final IEmail emailSender;

    @Override
    public BaseResponse<Object> changePassword(String userId, ChangePasswordRequest changePasswordRequest){

        var user = authenticationRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, resourceValue.getUserNotFound()));
        var message = resourceValue.getEmptyString();
        var isSuccess = false;
        if(passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
            authenticationRepository.save(user);
            message = resourceValue.getSuccessChangePassword();
            isSuccess = true;
        } else {
            message = resourceValue.getChangePasswordFailed();
        }
        return BaseResponse.builder()
                .message(message)
                .isSuccess(isSuccess)
                .build();
    }

    @Override
    public BaseResponse<Object> changePhoneNumber(String userId, ChangePhoneNumberRequest changePhoneNumberRequest) {
        var user = authenticationRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, resourceValue.getUserNotFound()));

        var message = resourceValue.getEmptyString();
        var isSuccess = false;
        if(user.getPhoneNumber().equals(changePhoneNumberRequest.getOldPhoneNumber())) {
            user.setPhoneNumber(changePhoneNumberRequest.getNewPhoneNumber());
            authenticationRepository.save(user);
            message = resourceValue.getSuccessChangePhoneNumber();
            isSuccess = true;
        } else {
            message = resourceValue.getPhoneNumberNotSame();
        }
        return BaseResponse.builder()
                .message(message)
                .isSuccess(isSuccess)
                .build();
    }

    @Override
    public BaseResponse<Object> verificationUser(String userId, Boolean isVerificationUser) {
        var user = authenticationRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, resourceValue.getUserNotFound()));
        var message = resourceValue.getEmptyString();
        var isSuccess = false;
        if(!user.getIsVerificationUser()) {
            user.setIsVerificationUser(isVerificationUser);
            authenticationRepository.save(user);
            message = resourceValue.getSuccessVerificationUser();
            isSuccess = true;
        } else {
            message = resourceValue.getUserVerify();
        }

        return BaseResponse.builder()
                .message(message)
                .isSuccess(isSuccess)
                .build();
    }

    @Override
    public BaseResponse<Object> resendEmailVerification(String email, String token) {
        var link = "tes" + token;
        var message = resourceValue.getEmptyString();
        var isSuccess = false;
        if(!email.isEmpty()) {
            emailSender.sendEmail(
                    email,
                    buildEmail(email, link)
            );
            message = "Email verification link sent to your email";
            isSuccess = true;
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
}
