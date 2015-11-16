package com.breathe.dao.implementation;

import com.breathe.dao.UserDAO;
import com.breathe.model.DeviceModel;
import com.breathe.model.UserModel;
import com.breathe.utils.EmailValidator;
import com.breathe.utils.PasswordHash;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by amira on 02.04.15.
 */

@Repository
public class UserDAOImpl implements UserDAO {
    private MongoCollection usersCollection;
    private MongoClient mongoClient;
    private MongoDatabase co2Database;


    private Random random = new SecureRandom();
    private EmailValidator emailValidator = new EmailValidator();

    public UserDAOImpl() throws UnknownHostException {
        MongoClientURI uri = new MongoClientURI("mongodb://smartair:xnndxdfkoavg@ds053894.mongolab.com:53894/co2"); 
        mongoClient = new MongoClient(uri);
        co2Database = mongoClient.getDatabase("co2");
        usersCollection = co2Database.getCollection("users");
    }

    public List<DeviceModel> findDevices(String userId) {
        List<DBObject> result = new ArrayList<>();
        DBObject user = (DBObject) usersCollection.find(new BasicDBObject("_id", userId)).limit(1);
        BasicDBList devices = (BasicDBList) user.get("devices");
        for (Object device : devices ) {
            result.add((DBObject) device);
        }
        return  convertDevicesList(result);
    }

    // validates that username is unique and insert into db
    public void addUser(UserModel user) {
        if (usersCollection.count(new BasicDBObject("username", Pattern.compile(user.getUsername(), Pattern.CASE_INSENSITIVE))) > 0) {
            System.out.println("User with this username already exists: " + user.getUsername());
            //TODO move to service level
        }
        if (usersCollection.count(new BasicDBObject("email", user.getEmail())) > 0) {
            System.out.println("User with this email already exists: " + user.getEmail());
            //TODO move to service level
        }
        //TODO hash also to service level
       // String passwordHash = makePasswordHash(password, Integer.toString(random.nextInt()));

        Document userObject = new Document();
        userObject.append("username", user.getUsername()).append("password", user.getPassword());
           // .append("password", passwordHash)
        userObject.append("_id", user.getUserId());
        if (user.getEmail() != null && !user.getEmail().equals("") && emailValidator.validate(user.getEmail())) {
            userObject.append("email", user.getEmail());
        }

        ArrayList<String> devicesArray = new ArrayList<String>();
        if ((user.getDevices() != null) && (!user.getDevices().isEmpty())) {
            for (DeviceModel device : user.getDevices()) {
                devicesArray.add(device.getDeviceId());
            }
        }
        userObject.append("devices", devicesArray);

        try {
            usersCollection.insertOne(userObject);
        } catch (DuplicateKeyException e) {
            System.out.println("Username already in use: " + user.getUsername());
        }
    }

    public void addDevice(String userId, DeviceModel device) {
        if (usersCollection.count(new BasicDBObject("devices", new BasicDBObject("deviceId", device.getDeviceId()))) > 0) {
            System.out.println("Device with this device_id already exists: " + device.getDeviceId());
        }
        if (usersCollection.count(new BasicDBObject("_id", userId)) == 0) {
            System.out.println("User with this _id doesn't exist: " + userId);
        //TODO remove all of this to service level
        }

        BasicDBObject find = new BasicDBObject("_id", userId);
        DBObject push = new BasicDBObject("devices", new BasicDBObject("deviceId", device.getDeviceId())
                .append("deviceName", device.getDeviceName())
                .append("delay", device.getDelay())
                .append("co2Min", device.getCo2MinLevel()));
        try {
            usersCollection.updateOne(find, new BasicDBObject("$addToSet", push));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserModel validateLogin(String username, String password) {
        Document user = (Document) usersCollection.find(new BasicDBObject("username", Pattern.compile(username, Pattern.CASE_INSENSITIVE))).first();

        if (user == null) {
            System.out.println("User not in database");
            return null;

        }

        Boolean valid = false;
        try {
            valid = PasswordHash.validatePassword(password, PasswordHash.createHash(user.get("password").toString()));
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
            return null;
        }
        if (!valid) {
            System.out.println("Wrong password");
            return null;
        }

        return UserMapper.convertUserDbObject(user);
    }

    public UserModel getUserById(String userId) {
        Document user = ((Document) usersCollection.find(new BasicDBObject("_id", userId)).first());

        if (user == null) {
            System.out.println("User not in database");
            return null;
        }
        else {
            return UserMapper.convertUserDbObject(user);
        }
    }
    private DeviceModel convertDeviceDbObject(DBObject deviceDbObject) {
        try {
            DeviceModel device = new DeviceModel();
            device.setDeviceId((String) deviceDbObject.get("deviceId"));
            device.setDeviceName((String) deviceDbObject.get("deviceName"));
            return  device;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    private List<DeviceModel> convertDevicesList(List<DBObject> devicesList) {
        List<DeviceModel> result = new ArrayList<>();
        if (!devicesList.isEmpty()) {
            for (DBObject device : devicesList) {
                result.add(convertDeviceDbObject(device));
            }
        }
        return result;
    }

    /**
     * Created by amira on 10.04.15.
     */
    private static class UserMapper {
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
        static public UserModel convertUserDbObject(Document UserDbObject) {
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
}
