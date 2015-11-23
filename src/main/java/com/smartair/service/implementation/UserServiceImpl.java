package com.smartair.service.implementation;

import com.smartair.dao.UserRepository;
import com.smartair.model.DeviceCreateModel;
import com.smartair.model.entity.DeviceModel;
import com.smartair.model.entity.UserModel;
import com.smartair.service.DeviceService;
import com.smartair.service.UserService;
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
    private UserRepository userRepository;
    @Autowired
    private DeviceService deviceService;

    public List<DeviceModel> findDevicesByUser(String userId) {
        return userRepository.findDevices(userId);
    }

    public void addUser(UserModel user) {
        user.setUserId(UUID.randomUUID().toString());

        userRepository.create(user);
    }

    public void addDevice(String userId, DeviceModel device) {
        //TODO - change to addDevice(DeviceModel)
        //TODO and generate unique device ID
       // if (ifDeviceExists(device.getDeviceId())) return false;
        device.setUserId(userId);
        userRepository.addDevice(userId, device);
        deviceService.addDevice(device);
    }

    @Override
    public void addDevice(String userId, DeviceCreateModel device) {
        addDevice(userId, mapToDeviceModel(device));
    }

    private DeviceModel mapToDeviceModel(DeviceCreateModel device) {
        DeviceModel entity = new DeviceModel();
        entity.setDeviceId(device.getDeviceId());
        entity.setCo2MaxLevel(device.getCo2MaxLevel());
        entity.setDelay(device.getDelay());
        entity.setDeviceName(device.getDeviceName());

        return entity;
    }

    @Override
    public void setGcmToken(String userId, String token) {
        //TODO implement setting token for user
        UserModel user = userRepository.findOne(userId);
        if (user == null) {
            return;
        }
        user.setGcmToken(token);
        userRepository.save(user);
    }

    public UserModel validateLogin(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return userRepository.validateLogin(username, password);
    }

    public UserModel getUserById(String userId) {
        UserModel user = userRepository.findOne(userId);

        if (user != null) {
            return user;
        }
        else {
            return null;
        }
    }
}
