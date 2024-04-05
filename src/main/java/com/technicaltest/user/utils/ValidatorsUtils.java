package com.technicaltest.user.utils;

public class ValidatorsUtils {
    
    public static boolean isBlankOrNullString(String text){
        return text == null || text.isBlank();
    }
    public static boolean isEmailValid(String email){
        
        String regex = "[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}";
        return (!isBlankOrNullString(email)) && email.matches(regex);
    }
    public static boolean isPasswordValid(String password){
        int minLength = 8;
        int maxLength = 16;
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{" + minLength + "," + maxLength + "}$";
        return (!isBlankOrNullString(password)) && password.matches(regex);
    }
}
