package com.breathe.dao.implementation;

import com.breathe.dao.UserDAO;
import com.breathe.model.DeviceModel;
import com.breathe.model.UserModel;
import com.breathe.utils.EmailValidator;
import com.breathe.utils.PasswordHash;
import com.mongodb.*;
import org.springframework.stereotype.Repository;

import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by amira on 02.04.15.
 */

@Repository
public class UserDAOImpl implements UserDAO {
    private DBCollection usersCollection;
    private MongoClient mongoClient;
    private DB co2Database;


    private Random random = new SecureRandom();
    private EmailValidator emailValidator = new EmailValidator();

    public UserDAOImpl() throws UnknownHostException {
         mongoClient= new MongoClient(new MongoClientURI("mongodb://localhost"));
         co2Database = mongoClient.getDB("co2");
         usersCollection = co2Database.getCollection("users");
    }

    public List<DBObject> findDevices(String userId) {
        List<DBObject> result = new ArrayList<>();
        DBObject user = usersCollection.findOne(new BasicDBObject("_id", userId));
        BasicDBList devices = (BasicDBList) user.get("devices");
        for (Object device : devices ) {
            result.add((DBObject) device);
        }
        return  result;
    }

    // validates that username is unique and insert into db
    public void addUser(String userId, String username, String email, String password, List<DeviceModel> devices) {
        if (usersCollection.find(new BasicDBObject("username", Pattern.compile(username, Pattern.CASE_INSENSITIVE))).count() > 0) {
            System.out.println("User with this username already exists: " + username);
            //TODO throw exception
        }
        if (usersCollection.find(new BasicDBObject("email", email)).count() > 0) {
            System.out.println("User with this email already exists: " + email);
            //TODO throw exception
        }

       // String passwordHash = makePasswordHash(password, Integer.toString(random.nextInt()));

        BasicDBObject user = new BasicDBObject();
        user.append("username", username).append("password", password);
           // .append("password", passwordHash)
        user.append("_id", userId);
        if (email != null && !email.equals("") && emailValidator.validate(email)) {
            user.append("email", email);
        }

        ArrayList<String> devicesArray = new ArrayList<String>();
        if ((devices != null) && (!devices.isEmpty())) {
            for (DeviceModel device : devices) {
                devicesArray.add(device.getDeviceId());
            }
        }
        user.append("devices", devicesArray);

        try {
            usersCollection.insert(user);
        } catch (MongoException.DuplicateKey e) {
            System.out.println("Username already in use: " + username);
        }
    }

    public DBObject validateLogin(String username, String password) {
        DBObject user = usersCollection.findOne(new BasicDBObject("username", Pattern.compile(username, Pattern.CASE_INSENSITIVE)));

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

        return user;
    }

    public DBObject getUserById(String userId) {
        DBObject user = usersCollection.findOne(new BasicDBObject("_id", userId));

        if (user == null) {
            System.out.println("User not in database");
            return null;
        }
        else {
            return user;
        }
    }
}
