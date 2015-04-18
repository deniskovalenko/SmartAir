package com.breathe.service;

import com.breathe.model.DeviceModel;
import com.breathe.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by denis on 03.04.15.
 */
public interface UserService {

    List<DeviceModel> findDevicesByUser(String userId);

    void addUser(UserModel user);

    UserModel validateLogin(String username, String password);

    UserModel getUserById(String userId);
}


