package com.littlestark.jajan.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class ResourceValue {

    @Value("${app.registered.email}")
    private String registeredEmail;
    @Value("${app.success.register.email}")
    private String successRegisterEmail;
    @Value("${app.success.login}")
    private String successLogin;

    @Value("${app.success.change.password}")
    private String successChangePassword;

    @Value("${app.success.change.phone.number}")
    private String successChangePhoneNumber;

    @Value("${app.user.not.found}")
    private String userNotFound;
    @Value("${app.success.create.store}")
    private String successCreateStore;
    @Value("${app.created.store}")
    private String createdStore;
    @Value("${app.success.update.store}")
    private String successUpdateStore;
    @Value("${app.success.verification.store}")
    private String successVerificationStore;

    @Value("${app.success.create.product}")
    private String successCreateProduct;

    @Value("")
    private String emptyString;

    public String getChangePasswordFailed() {
        return changePasswordFailed;
    }

    @Value("${app.change.password.failed}")
    private String changePasswordFailed;

    public String getSuccessCreateProduct() {
        return successCreateProduct;
    }

    public String getSuccessUpdateProduct() {
        return successUpdateProduct;
    }

    public String getSuccessDeleteProduct() {
        return successDeleteProduct;
    }

    @Value("${app.success.update.product}")
    private String successUpdateProduct;

    @Value("${app.success.delete.product}")
    private String successDeleteProduct;

    public String getSuccessCreateStore() {
        return successCreateStore;
    }

    public String getCreatedStore() {
        return createdStore;
    }

    public String getSuccessUpdateStore() {
        return successUpdateStore;
    }

    public String getSuccessVerificationStore() {
        return successVerificationStore;
    }

    public String getSuccessChangePassword() {
        return successChangePassword;
    }

    public String getSuccessChangePhoneNumber() {
        return successChangePhoneNumber;
    }

    public String getSuccessVerificationUser() {
        return successVerificationUser;
    }

    @Value("${app.success.verification.user}")
    private String successVerificationUser;

    public String getUserVerify() {
        return userVerify;
    }

    @Value("${app.user.verify}")
    private String userVerify;
    @Value("${app.phone.number.not.same}")
    private String phoneNumberNotSame;
    @Value("${app.image.max.size}")
    private String imageMaxSize;

    public String getPhoneNumberNotSame() {
        return phoneNumberNotSame;
    }

    public String getRegisteredEmail() {
        return registeredEmail;
    }

    public String getSuccessRegisterEmail() {
        return successRegisterEmail;
    }

    public String getSuccessLogin() {
        return successLogin;
    }

    public String getUserNotFound() {
        return userNotFound;
    }


    public String getEmptyString() {
        return emptyString;
    }

    public String getImageMaxSize() {
        return imageMaxSize;
    }
}
