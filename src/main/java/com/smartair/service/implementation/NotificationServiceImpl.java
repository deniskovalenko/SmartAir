package com.smartair.service.implementation;

import com.smartair.model.entity.user.User;
import com.smartair.service.NotificationService;
import com.smartair.service.UserService;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by denis on 22.11.15.
 */
@Service
public class NotificationServiceImpl implements NotificationService {
    public static final String API_KEY = "AIzaSyATXRF6z8JHFa2YTq59MBrvYBVSi58k56o";
    @Autowired
    UserService userService;

    @Override
    public void notifyUser(String userId, String message) {
        User user = userService.find(userId);
        if (user != null) {
            String token = user.getGcmToken();
            if (token != null && !token.equals("")) {
                sendGcmNotification(token, message);
            }
        }
    }

    private void sendGcmNotification(String token, String message) {
        try {
            // Prepare JSON containing the GCM message content. What to send and where to send.
            JSONObject jGcmData = new JSONObject();
            JSONObject jData = new JSONObject();
            jData.put("message", message);

            // Where to send GCM message.
            jGcmData.put("to", token);
//// What to send in GCM message.
            jGcmData.put("data", jData);

            // Create connection to send GCM Message request.
            URL url = new URL("https://android.googleapis.com/gcm/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "key=" + API_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // Send GCM message content.
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(jGcmData.toString().getBytes());

            // Read GCM response.
            InputStream inputStream = conn.getInputStream();
            String resp = IOUtils.toString(inputStream);
            System.out.println(resp);
            System.out.println("Check your device/emulator for notification or logcat for " +
                    "confirmation of the receipt of the GCM message.");
        } catch (IOException e) {
            System.out.println("Unable to send GCM message.");
            System.out.println("Please ensure that API_KEY has been replaced by the server " +
                    "API key, and that the device's registration token is correct (if specified).");
            e.printStackTrace();
        }
    }
}
