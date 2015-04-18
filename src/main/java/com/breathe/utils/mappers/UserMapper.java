package com.breathe.utils.mappers;

import com.breathe.model.DeviceModel;
import com.breathe.model.UserModel;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by amira on 10.04.15.
 */
public class UserMapper {
    static public UserModel convertUserDbObject(DBObject UserDbObject) {
        try {
            UserModel user = new UserModel();
            user.setPassword((String) UserDbObject.get("password"));
            user.setDevices((List<DeviceModel>) UserDbObject.get("devices"));
            user.setEmail((String) UserDbObject.get("email"));
            user.setUsername((String) UserDbObject.get("username"));
            user.setUserId((String) UserDbObject.get("_id"));
            return  user;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    static public List<UserModel> convertUserList(List<DBObject> UserList) {
        List<UserModel> result = new ArrayList<UserModel>();
        if (!UserList.isEmpty()) {
            for (DBObject row : UserList) {
                result.add(UserMapper.convertUserDbObject(row));
            }
        }
        return result;
    }
}
