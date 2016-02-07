package com.smartair.utils;

import com.smartair.model.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that creates register notification message
 */
@Component
public class MailMessageBuilder {

    private static final String LINK = "http://smartair.tech";
    private static final String SENDER_MAIL = "smartair.team@gmail.com";
    private static final String REGISTRATION_SUBJECT = "Registration confirmation";
    private static final String SUBSCRIBE_SUBJECT = "SmartAir.tech subscribe";

    private static final String REGISTRATION_TEMPLATE_PATH = "templates/registration_mail.ftl";
    private static final String SUBSCRIBE_TEMPLATE_PATH = "templates/subscribe_mail.ftl";


    @Autowired
    private TemplateStringBuilder templateStringBuilder;

    /**
     * Build mail register notification based on User object
     * @param user user that needs register notification
     * @return built SimpleMailMessage, cannot be null
     */
    @Nonnull
    public SimpleMailMessage buildRegistrationMail(User user) {
        return buildRegistrationMail(user.getUsername(), user.getEmail());
    }

    /**
     * Create mail register notification based on user name and email
     * @param name name Rof the receiver user
     * @param mail email address of the receiver
     * @return built SimpleMailMessage, cannot be null
     */
    @Nonnull
    public SimpleMailMessage buildRegistrationMail(String name, String mail) {

        final Map<String, Object> templateParam = new HashMap<>();
        templateParam.put("userName", name);
        templateParam.put("link", LINK);


        return buildMailFromTemplateWithParams(REGISTRATION_TEMPLATE_PATH, templateParam, REGISTRATION_SUBJECT, mail);
    }

    /**
     * Build mail register notification based on User object
     * @param user user that needs register notification
     * @return built SimpleMailMessage, cannot be null
     */
    @Nonnull
    public SimpleMailMessage buildSubscribeMail(User user) {
        return buildRegistrationMail(user.getUsername(), user.getEmail());
    }

    /**
     * Create mail register notification based on user name and email
     * @param mail email address of the receiver
     * @return built SimpleMailMessage, cannot be null
     */
    @Nonnull
    public SimpleMailMessage buildSubscribeMail(String mail) {

        final Map<String, Object> templateParam = new HashMap<>();
        templateParam.put("link", LINK);
        //TODO provide unsubscribe link

        return buildMailFromTemplateWithParams(SUBSCRIBE_TEMPLATE_PATH, templateParam, SUBSCRIBE_SUBJECT, mail);
    }

    private SimpleMailMessage buildMailFromTemplateWithParams(String templatePath, Map<String, Object> params, String subject, String mail) {
        String text = templateStringBuilder.build(templatePath, params);
        if (text.equals(TemplateStringBuilder.UNKNOWN_VALUE)) {
            text = "Welcome," + params.get("userName") + "! \n\nBest regards from SmartAir team";
        }

        final SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(SENDER_MAIL);
        message.setTo(mail);
        message.setSubject(subject);
        message.setText(text);

        return message;
    }

}
