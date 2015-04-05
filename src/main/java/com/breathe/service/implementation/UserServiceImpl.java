package com.breathe.service.implementation;

import com.breathe.utils.mappers.StatisticMapper;
import com.breathe.dao.UserDAO;
import com.breathe.model.DeviceModel;
import com.breathe.model.UserModel;
import com.breathe.service.UserService;
import com.mongodb.DB;
import com.mongodb.DBObject;

import java.util.List;

/**
 * Created by amira on 02.04.15.
 */
//@Component
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

//    public UserServiceImpl() {
//    }

    public UserServiceImpl(final DB co2Database) {
        userDAO = new UserDAO(co2Database);
    }

    public List<DeviceModel> findDevicesByUser(String userId) {
        List<DBObject> devices = userDAO.findDevicesByUser(userId);
        return  StatisticMapper.convertDevicesList(devices);
    }

    public Boolean addUser(UserModel user) {
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();
        String name = user.getName();
        String surname = user.getSurname();
        String country = user.getCountry();
        String city = user.getCity();
        List<DeviceModel> devices = user.getDevices();
        //TODO add generation of _id like new RandomUUID
        return userDAO.addUser(username, email, password, name, surname,
                country, city, devices);
    }
}
