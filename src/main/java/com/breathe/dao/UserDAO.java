package com.breathe.dao;

import com.breathe.model.DeviceModel;
import com.breathe.model.UserModel;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * Created by denis on 14.04.15.
 */
public interface UserDAO {
    List<DeviceModel> findDevices(String userId);

    //TODO remove devices for creating
    void addUser(UserModel user);

    void addDevice(String userId, DeviceModel device);

    //TODO change to bool
    UserModel validateLogin(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException;

    UserModel getUserById(String userId);
}
