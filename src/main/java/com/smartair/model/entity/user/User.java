package com.smartair.model.entity.user;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.smartair.model.entity.DeviceModel;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by amira on 02.04.15.
 */
@Document(collection = User.COLLECTION_NAME)
public class User implements UserDetails {
    public static final String COLLECTION_NAME = "users";

    @Id
    private String id;
    private String username;
    private String name;
    private String surname;
    private String email;
    @NotNull
    @Size(min = 6, max = 32)
    private String password;
    private List<DeviceModel> devices;
    private String gcmToken;
    private List<Role> authorities;
    private Date passwordSetTime;
    private Date lastFailureLoginTime;
    private int failedLoginAttempt;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    public User() {}

    public User(String username, String name,  String email, String password) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public boolean hasRole(final String role) {
        boolean hasRole = false;
        for (final GrantedAuthority authority : this.getAuthorities()) {
            if (authority.getAuthority().equals(role)) {
                hasRole = true;
                break;
            }
        }
        return hasRole;
    }

    // UserDetails implementation
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    //End of UserDetails implementation


    public String getUserId() {
        return id;
    }

    public void setUserId(String userId) {
        this.id = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<DeviceModel> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceModel> devices) {
        this.devices = devices;
    }

    public String getGcmToken() {
        return gcmToken;
    }

    public void setGcmToken(String gcmToken) {
        this.gcmToken = gcmToken;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(final List<Role> authorities) {
        this.authorities = authorities;
    }

    public Date getPasswordSetTime() {
        return passwordSetTime;
    }

    public void setPasswordSetTime(Date passwordSetTime) {
        this.passwordSetTime = passwordSetTime;
    }

    public Date getLastFailureLoginTime() {
        return lastFailureLoginTime;
    }

    public void setLastFailureLoginTime(Date lastFailureLoginTime) {
        this.lastFailureLoginTime = lastFailureLoginTime;
    }

    public int getFailedLoginAttempt() {
        return failedLoginAttempt;
    }

    public void setFailedLoginAttempt(int failedLoginAttempt) {
        this.failedLoginAttempt = failedLoginAttempt;
    }
}
