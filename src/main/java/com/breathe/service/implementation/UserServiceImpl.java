package com.breathe.service.implementation;

import com.breathe.dao.UserDAO;
import com.breathe.utils.mappers.StatisticMapper;
import com.breathe.model.DeviceModel;
import com.breathe.model.UserModel;
import com.breathe.service.UserService;
import com.breathe.utils.mappers.UserMapper;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.UUID;

/**
 * Created by amira on 02.04.15.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    public List<DeviceModel> findDevicesByUser(String userId) {
        List<DBObject> devices = userDAO.findDevices(userId);
        return  StatisticMapper.convertDevicesList(devices);
    }

    public void addUser(UserModel user) {
        String username = user.getUsername();
        String userId = UUID.randomUUID().toString();
        String email = user.getEmail();
        String password = user.getPassword();
        List<DeviceModel> devices = user.getDevices();
        //TODO add generation of _id like new RandomUUID
        userDAO.addUser(userId, username, email, password, devices);
    }

    public UserModel validateLogin(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return UserMapper.convertUserDbObject(userDAO.validateLogin(username, password));
    }

    public UserModel getUserById(String userId) {
        DBObject user = userDAO.getUserById(userId);
        if (user != null) {
            return UserMapper.convertUserDbObject(user);
        }
        else {
            return null;
        }
    }
}
