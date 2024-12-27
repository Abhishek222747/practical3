package com.abhishek.practical.security;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.security.authentication.*;
import io.micronaut.security.authentication.provider.HttpRequestAuthenticationProvider;
import io.micronaut.security.utils.SecurityService;
import jakarta.inject.Singleton;

import static java.util.Collections.singletonList;

@Singleton
public class CustomAuthenticationProvider implements HttpRequestAuthenticationProvider {

    private final SecurityService securityService;

    public CustomAuthenticationProvider(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public AuthenticationResponse authenticate(Object requestContext, @NonNull AuthenticationRequest authRequest) {
        String username = authRequest.getIdentity().toString();
        String password = authRequest.getSecret().toString();

        // Example: Mock validation logic
        if ("admin".equals(username) && "admin123".equals(password)) {
            return AuthenticationResponse.success(username, singletonList("ROLE_ADMIN"));
        } else if ("user".equals(username) && "user123".equals(password)) {
            return AuthenticationResponse.success(username, singletonList("ROLE_USER"));
        } else {
            return new AuthenticationFailed(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH);
        }
    }
}
