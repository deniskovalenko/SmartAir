package com.smartair.service.implementation;

import com.smartair.dao.UserRepository;
import com.smartair.model.DeviceCreateModel;
import com.smartair.model.UserException;
import com.smartair.model.entity.DeviceModel;
import com.smartair.model.entity.user.Role;
import com.smartair.model.entity.user.RoleType;
import com.smartair.model.entity.user.User;
import com.smartair.service.DeviceService;
import com.smartair.service.UserService;
import com.smartair.service.mail.MailService;
import com.smartair.utils.MailMessageBuilder;
import org.apache.commons.lang.RandomStringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

/**
 * Created by amira on 02.04.15.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private MailService mailService;
    @Autowired
    private MailMessageBuilder mailMessageBuilder;

    private final static int timePasswordChangeAllowed = 10;

    @Override
    public List<DeviceModel> findDevicesByUser(String userId) {
        return userRepository.findDevices(userId);
    }

    @Override
    public void add(User user) throws UserException {
        user.setUserId(UUID.randomUUID().toString());
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(RoleType.ROLE_USER));
        user.setAuthorities(roles);
        user.setPasswordSetTime(new Date());
        userRepository.create(user);

        final SimpleMailMessage msg = mailMessageBuilder.buildRegistrationMail(user);
        mailService.sendMail(msg);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public User find(String userId) {
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

    @Override
    public void setNewPassword(String userId, String password) throws UserException {
        User oldUser = userRepository.findOne(userId);

        //checking, whether password change is allowed
        if (!oldUser.getPassword().equals(password)) {
            DateTime currenTime = new DateTime();

            //if enough time spent after previous password change
            if (new DateTime(oldUser.getPasswordSetTime()).plusMinutes(timePasswordChangeAllowed).isBefore(currenTime)) {
                oldUser.setPassword(password);
                oldUser.setPasswordSetTime(currenTime.toDate());
                update(oldUser);
            } else {
                throw  new UserException("Password has been changed less than " + timePasswordChangeAllowed + " minutes ago" );
            }
        }
    }

    @Override
    public void setLastFailureLoginTime(String userId, Date date) {
        User oldUser = userRepository.findOne(userId);
        oldUser.setLastFailureLoginTime(date);
    }

    @Override
    public void setFailedLoginCount(User user, int count) {
        user.setFailedLoginAttempt(count);
        userRepository.save(user);
    }

    @Override
    public void addSubscriber(String email) {
        String generatedPassword = RandomStringUtils.random(10, 0, 0, true, true, null, new SecureRandom());
        userRepository.create(new User(email, email, email, generatedPassword));
        final SimpleMailMessage msg = mailMessageBuilder.buildSubscribeMail(email);
        mailService.sendMail(msg);
    }
}