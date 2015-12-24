package com.smartair.service.security;

import com.smartair.model.entity.user.User;
import com.smartair.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Date;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    /**
     * Performs authentication
     * @param authentication - the authentication request object.
     * @return a fully authenticated object including credentials, cannot be null
     */
    @Override
    @Nonnull
    public Authentication authenticate(final Authentication authentication) {
        final String username = authentication.getName();
        final String password = (String) authentication.getCredentials();

        if (username == null || username.trim().isEmpty()) {
            brutForseWait();
            throw new BadCredentialsException("Email cannot be empty");
        }

        if (password == null || password.trim().isEmpty()) {
            brutForseWait();
            throw new BadCredentialsException("Password cannot be empty");
        }

        final User user = userService.loadUserByUsername(username);

        if (user == null) {
            brutForseWait();
            throw new BadCredentialsException("Username not found.");
        }

        if (!user.isEnabled()) {
            brutForseWait(user);
            throw new BadCredentialsException("Contact the administrator "
                    + "denis.v.kovalenko@gmail.com");
        }

        if (!password.equals(user.getPassword())) {
            brutForseWait(user);
            throw new BadCredentialsException("Wrong password.");
        }

        //after successful login make failed count 0
        userService.setFailedLoginCount(user, 0);
        final Collection<? extends GrantedAuthority> authorities
                = user.getAuthorities();

        return new UsernamePasswordAuthenticationToken(user, password,
                authorities);
    }

    private void brutForseWait() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void brutForseWait(User user) {
        Date currentTime = new Date();
        userService.setLastFailureLoginTime(user.getUserId(), currentTime);
        int failedAttemts = user.getFailedLoginAttempt();
        failedAttemts++;
        userService.setFailedLoginCount(user, failedAttemts);
        try {
            if (failedAttemts > 1) {
                Thread.sleep(300000);
            } else {
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if AuthenticationProvider supports the indicated Authentication object.
     * @param aClass - the authentication object.
     * @return true if supporting
     */
    @Override
    public boolean supports(final Class<?> aClass) {
        return true;
    }
}
