package com.breathe.service;

import com.breathe.model.DeviceModel;
import com.breathe.model.UserModel;

import java.util.List;

/**
 * Created by denis on 03.04.15.
 */
public interface UserService {

    List<DeviceModel> findDevicesByUser(String userId);

    Boolean addUser(UserModel user);
}


