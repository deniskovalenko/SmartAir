package com.breathe.service;

import com.breathe.model.DeviceModel;
import com.breathe.model.UserModel;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * Created by denis on 03.04.15.
 */
public interface UserService {

    List<DeviceModel> findDevicesByUser(String userId);

    void addUser(UserModel user);

    void addDevice(String userId, DeviceModel device);

    UserModel validateLogin(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException;

    UserModel getUserById(String userId);
}


