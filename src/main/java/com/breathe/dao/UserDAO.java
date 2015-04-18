package com.breathe.dao;

import com.breathe.model.DeviceModel;
import com.mongodb.DBObject;

import java.util.List;

/**
 * Created by denis on 14.04.15.
 */
public interface UserDAO {
    List<DBObject> findDevices(String userId);


    //TODO remove devices for creating
    void addUser(String userId, String username, String email, String password, List<DeviceModel> devices);

    //TODO change to bool
    DBObject validateLogin(String username, String password);

    DBObject getUserById(String userId);
}
