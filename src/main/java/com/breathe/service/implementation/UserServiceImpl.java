package com.breathe.service.implementation;

import com.breathe.dao.UserDAO;
import com.breathe.model.DeviceModel;
import com.breathe.model.UserModel;
import com.breathe.service.UserService;
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
        return userDAO.findDevices(userId);
    }

    public void addUser(UserModel user) {
        user.setUserId(UUID.randomUUID().toString());

        userDAO.addUser(user);
    }

    public UserModel validateLogin(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return userDAO.validateLogin(username, password);
    }

    public UserModel getUserById(String userId) {
        UserModel user = userDAO.getUserById(userId);
        if (user != null) {
            return user;
        }
        else {
            return null;
        }
    }
}
