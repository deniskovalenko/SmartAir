package com.breathe.service.implementation;

import com.breathe.utils.mappers.StatisticMapper;
import com.breathe.dao.UserDAO;
import com.breathe.model.DeviceModel;
import com.breathe.model.UserModel;
import com.breathe.service.UserService;
import com.breathe.utils.mappers.UserMapper;
import com.mongodb.DB;
import com.mongodb.DBObject;

import java.util.List;

/**
 * Created by amira on 02.04.15.
 */
//@Component
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    public UserServiceImpl() {
    }

    public UserServiceImpl(final DB co2Database) {
        userDAO = new UserDAO(co2Database);
    }

    public List<DeviceModel> findDevicesByUser(String userId) {
        List<DBObject> devices = userDAO.findDevicesByUser(userId);
        return  StatisticMapper.convertDevicesList(devices);
    }

    public boolean addUser(UserModel user) {
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();
        List<DeviceModel> devices = user.getDevices();
        //TODO add generation of _id like new RandomUUID
        return userDAO.addUser(username, email, password, devices);
    }

    public UserModel validateLogin(String username, String password) {
        return UserMapper.convertUserDbObject(userDAO.validateLogin(username, password));
    }
}
