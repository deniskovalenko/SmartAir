package com.smartair.service.implementation;

import com.smartair.dao.UserRepository;
import com.smartair.model.DeviceCreateModel;
import com.smartair.model.entity.DeviceModel;
import com.smartair.model.entity.user.Role;
import com.smartair.model.entity.user.RoleType;
import com.smartair.model.entity.user.User;
import com.smartair.service.DeviceService;
import com.smartair.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Override
    public List<DeviceModel> findDevicesByUser(String userId) {
        return userRepository.findDevices(userId);
    }

    @Override
    public void addUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(RoleType.ROLE_USER));
        user.setAuthorities(roles);
        userRepository.create(user);
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }

        return user;
    }

    @Override
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
        User user = userRepository.findOne(userId);
        if (user == null) {
            return;
        }
        user.setGcmToken(token);
        userRepository.save(user);
    }

    @Override
    public User validateLogin(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return userRepository.validateLogin(username, password);
    }
}
