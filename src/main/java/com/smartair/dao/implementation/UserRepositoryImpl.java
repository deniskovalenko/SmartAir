package com.smartair.dao.implementation;

import com.smartair.dao.CustomUserRepository;
import com.smartair.model.entity.DeviceModel;
import com.smartair.model.entity.UserModel;
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


//    private MongoCollection<UserModel> usersCollection;
//    private MongoClient mongoClient;
//    private MongoDatabase co2Database;


    private Random random = new SecureRandom();
    private EmailValidator emailValidator = new EmailValidator();

    public UserRepositoryImpl() {
//        MongoClientURI uri = new MongoClientURI("mongodb://smartair:xnndxdfkoavg@ds053894.mongolab.com:53894/co2");
//        mongoClient = new MongoClient(uri);
//        co2Database = mongoClient.getDatabase("co2");
//        usersCollection = co2Database.getCollection("new_users", UserModel.class);
    }

    public List<DeviceModel> findDevices(String userId) {
       UserModel user =  mongoOperations.findOne(Query.query(Criteria.where("_id").is(userId)), UserModel.class);
        if (user != null) {
            return user.getDevices();
        } else {
            return null;
        }
//        List<DeviceModel> result = new ArrayList<>();
//        UserModel user =  usersCollection.find(new BasicDBObject("_id", userId)).first();
//        return user.getDevices();
    }



    // validates that username is unique and insert into db
    public void create(UserModel user) {
//        if (usersCollection.count(new Document("username", Pattern.compile(user.getUsername(), Pattern.CASE_INSENSITIVE))) > 0) {
//            System.out.println("User with this username already exists: " + user.getUsername());
//            //TODO move to service level
//        }
        if (mongoOperations.count(Query.query(Criteria.where("username").
                is(Pattern.compile(user.getUsername(), Pattern.CASE_INSENSITIVE))), UserModel.class) > 0) {
            System.out.println("User with this username already exists: " + user.getUsername());
            //TODO return some error
        }
//        if (usersCollection.count(new Document("email", user.getEmail())) > 0) {
//            System.out.println("User with this email already exists: " + user.getEmail());
//            //TODO move to service level
//        }
        if (mongoOperations.count(Query.query(Criteria.where("email").
                is(user.getEmail())), UserModel.class) > 0) {
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


//        try {
//            usersCollection.insertOne(user);
//        } catch (DuplicateKeyException e) {
//            System.out.println("Username already in use: " + user.getUsername());
//        }
    }

//    public UserModel find(String userId) {
//        return mongoOperations.findOne(Query.query(Criteria.where("_id").is(userId)), UserModel.class);
//      //  return usersCollection.find(new Document("_id", userId)).first();
//    }


    public void addDevice(String userId, DeviceModel device) {
//        if (usersCollection.count(new BasicDBObject("devices", new BasicDBObject("deviceId", device.getDeviceId()))) > 0) {
//            System.out.println("Device with this device_id already exists: " + device.getDeviceId());
//        }
//        Query query = new Query();
//        query.addCriteria(Criteria.where("devices").)
//        mongoOperations.count()
//        if (usersCollection.count(new BasicDBObject("_id", userId)) == 0) {
//            System.out.println("User with this _id doesn't exist: " + userId);
//        //TODO remove all of this to service level
//        }
//
//        BasicDBObject find = new BasicDBObject("_id", userId);
//        DBObject push = new BasicDBObject("devices", new BasicDBObject("deviceId", device.getDeviceId())
//                .append("deviceName", device.getDeviceName())
//                .append("delay", device.getDelay())
//                .append("co2Min", device.getCo2MaxLevel()));
//        try {
//            usersCollection.updateOne(find, new BasicDBObject("$addToSet", push));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        mongoOperations.updateFirst(Query.query(Criteria.where("_id").is(userId)), new Update().addToSet("devices", device), UserModel.class);
    }

    public UserModel validateLogin(String username, String password) {
        UserModel user = mongoOperations.findOne(Query.query(Criteria.where("username").is(Pattern.compile(username, Pattern.CASE_INSENSITIVE))), UserModel.class);
       // UserModel user = usersCollection.find(new Document("username", Pattern.compile(username, Pattern.CASE_INSENSITIVE))).first();

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


//    private DeviceModel convertDeviceDbObject(Document deviceDbObject) {
//        try {
//            DeviceModel device = new DeviceModel();
//            device.setDeviceId((String) deviceDbObject.get("deviceId"));
//            device.setDeviceName((String) deviceDbObject.get("deviceName"));
//            return  device;
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//            return null;
//        }
//    }

}
