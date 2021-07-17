package com.example.digico_labs.repository.model.user;

public enum UserStatus {
    INITIAL("Please confirm your email address to continue."),
    INACTIVE("Please contact administrator to activate your account."),
    ACTIVE("Active account."),
    BLOCKED("Your account is blocked. Please contact administrator."),
    DELETED("Deleted account.");

    public final String message;

    UserStatus(String message) {
        this.message = message;
    }
}
