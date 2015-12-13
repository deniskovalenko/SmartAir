package com.smartair.service;

import com.smartair.model.DeviceCreateModel;
import com.smartair.model.entity.DeviceModel;
import com.smartair.model.entity.user.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * Created by denis on 03.04.15.
 */
public interface UserService {

    User getUserById(String userId);

    User getUserByUsername(String userName);

    List<DeviceModel> findDevicesByUser(String userId);

    void addUser(User user);

    void addDevice(String userId, DeviceModel device);

    void addDevice(String userId, DeviceCreateModel device);

    User validateLogin(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException;



    /**
     * this method should set or update Google Cloud Messaging token, which would be used to send notifications
     */
    void setGcmToken(String userId, String token);
}


