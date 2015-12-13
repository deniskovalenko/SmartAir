package com.smartair.dao.implementation;

import com.smartair.dao.CustomUserRepository;
import com.smartair.model.entity.DeviceModel;
import com.smartair.model.entity.user.User;
import com.smartair.utils.EmailValidator;
import com.smartair.utils.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by amira on 02.04.15.
 */
@Repository
public class UserRepositoryImpl implements CustomUserRepository {
    @Autowired
    private MongoOperations mongoOperations;

    private Random random = new SecureRandom();
    private EmailValidator emailValidator = new EmailValidator();

    public UserRepositoryImpl() {
    }

    public List<DeviceModel> findDevices(String userId) {
       User user =  mongoOperations.findOne(Query.query(Criteria.where("_id").is(userId)), User.class);
        if (user != null) {
            return user.getDevices();
        } else {
            return null;
        }
    }

//    @Override
//    public User findByUsername(String username) {
//        return mongoOperations.findOne(Query.query(Criteria.where("username").is(username)), User.class);
//    }

    // validates that username is unique and insert into db
    public void create(User user) {
//        if (usersCollection.count(new Document("username", Pattern.compile(user.getUsername(), Pattern.CASE_INSENSITIVE))) > 0) {
//            System.out.println("User with this username already exists: " + user.getUsername());
//            //TODO move to service level
//        }
        if (mongoOperations.count(Query.query(Criteria.where("username").
                is(Pattern.compile(user.getUsername(), Pattern.CASE_INSENSITIVE))), User.class) > 0) {
            System.out.println("User with this username already exists: " + user.getUsername());
            //TODO return some error
        }
//        if (usersCollection.count(new Document("email", user.getEmail())) > 0) {
//            System.out.println("User with this email already exists: " + user.getEmail());
//            //TODO move to service level
//        }
        if (mongoOperations.count(Query.query(Criteria.where("email").
                is(user.getEmail())), User.class) > 0) {
            System.out.println("User with this emauk already exists: " + user.getEmail());
            //TODO return some error
        }
//        TODO hash also to service level
//        String passwordHash = makePasswordHash(password, Integer.toString(random.nextInt()));

//        Document userObject = new Document();
//        userObject.append("username", user.getUsername()).append("password", user.getPassword());
//           // .append("password", passwordHash)
//        userObject.append("_id", user.getUserId());
//        if (user.getEmail() != null && !user.getEmail().equals("") && emailValidator.validate(user.getEmail())) {
//            userObject.append("email", user.getEmail());
//        }

//        ArrayList<String> devicesArray = new ArrayList<String>();
//        if ((user.getDevices() != null) && (!user.getDevices().isEmpty())) {
//            for (DeviceModel device : user.getDevices()) {
//                devicesArray.add(device.getDeviceId());
//            }
//        }
//        userObject.append("devices", devicesArray);


        mongoOperations.insert(user);
    }

    public void addDevice(String userId, DeviceModel device) {
        mongoOperations.updateFirst(Query.query(Criteria.where("_id").is(userId)), new Update().addToSet("devices", device), User.class);
    }

    public User validateLogin(String username, String password) {
        User user = mongoOperations.findOne(Query.query(Criteria.where("username").is(Pattern.compile(username, Pattern.CASE_INSENSITIVE))), User.class);

        if (user == null) {
            System.out.println("User not in database");
            return null;

        }
        Boolean valid = false;
        try {
            valid = PasswordHash.validatePassword(password, PasswordHash.createHash(user.getPassword()));
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
}
