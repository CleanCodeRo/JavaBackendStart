package com.utcb.javaBackendStart.shared.helpers;

import com.utcb.javaBackendStart.auth.models.UserModel;
import jakarta.servlet.http.HttpServletRequest;

public class EmailComposer {

    public static String createResetPasswordEmail(HttpServletRequest request, UserModel user, String verificationCode) {
        String origin = request.getHeader("Origin") == null ? "http://localhost:3000" : request.getHeader("Origin") ;
        String url = origin + "/auth/passwordReset?email=" + user.getEmail();
        String content = "<!DOCTYPE html>"
                + "<html lang='en'>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                + "<title>Reset Your Password</title>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }"
                + ".container { max-width: 600px; margin: 20px auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }"
                + ".header { font-size: 24px; font-weight: bold; color: #333333; margin-bottom: 20px; }"
                + ".content { font-size: 16px; color: #666666; line-height: 1.6; }"
                + ".button { display: inline-block; padding: 10px 20px; margin-top: 20px; background-color: #007bff; color: #ffffff; text-decoration: none; border-radius: 4px; }"
                + ".footer { margin-top: 30px; font-size: 14px; color: #999999; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='container'>"
                + "<div class='header'>Reset Your Password</div>"
                + "<div class='content'>"
                + "Dear [[name]],<br><br>"
                + "You requested to change your password. Please click the button below to reset your password:<br><br>"
                + "<a href='[[URL]]' class='button'>Reset Password</a><br><br>"
                + "If you are unable to click the button, use the following code:<br>"
                + "Your verification code is: <h3>[[CODE]]</h3><br><br>"
                + "Thank you,<br>"
                + "Our team"
                + "</div>"
                + "<div class='footer'>If you did not request a password reset, please ignore this email or contact support if you have concerns.</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        content = content.replace("[[name]]", user.getName());
        content = content.replace("[[URL]]", url);
        content = content.replace("[[CODE]]", verificationCode);

        return content;
    }

    public static String createSetPasswordEmail(HttpServletRequest request, UserModel user, String verificationCode) {
        String origin = request.getHeader("Origin") == null ? "http://localhost:3000" : request.getHeader("Origin") ;
        String url = origin + "/auth/passwordReset?email=" + user.getEmail();
        String content = "<!DOCTYPE html>"
                + "<html lang='en'>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                + "<title>Reset Your Password</title>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }"
                + ".container { max-width: 600px; margin: 20px auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }"
                + ".header { font-size: 24px; font-weight: bold; color: #333333; margin-bottom: 20px; }"
                + ".content { font-size: 16px; color: #666666; line-height: 1.6; }"
                + ".button { display: inline-block; padding: 10px 20px; margin-top: 20px; background-color: #007bff; color: #ffffff; text-decoration: none; border-radius: 4px; }"
                + ".footer { margin-top: 30px; font-size: 14px; color: #999999; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='container'>"
                + "<div class='header'>Set Your Password</div>"
                + "<div class='content'>"
                + "Dear [[name]],<br><br>"
                + "You requested to change your password. Please click the button below to reset your password:<br><br>"
                + "<a href='[[URL]]' class='button'>Set Password</a><br><br>"
                + "If you are unable to click the button, use the following code:<br>"
                + "Your verification code is: <h3>[[CODE]]</h3><br><br>"
                + "Thank you,<br>"
                + "Our team"
                + "</div>"
                + "<div class='footer'>If you did not request a password reset, please ignore this email or contact support if you have concerns.</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        content = content.replace("[[name]]", user.getName());
        content = content.replace("[[URL]]", url);
        content = content.replace("[[CODE]]", verificationCode);

        return content;
    }
}
