package com.smartair.dao;

import com.smartair.model.entity.DeviceModel;
import com.smartair.model.entity.user.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * Created by denis on 14.04.15.
 */

public interface CustomUserRepository {
    List<DeviceModel> findDevices(String userId);

    //TODO remove devices for creating
    void create(User user);

    void addDevice(String userId, DeviceModel device);

    //TODO change to bool
    User validateLogin(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException;

   // User findByUsername(String userName);

    // User find(String userId);

}
