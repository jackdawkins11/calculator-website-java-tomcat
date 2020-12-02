package util.userValidation;

import data.DBInterface;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class UserValidator {
    
    public static ValidationResult validate(String username, String password) throws Exception {
        if( username.length() < 5 ){
            return new ValidationResult(false, "Username needs at least 5 characters");
        }
        DBInterface dao = DBInterface.getDAO();
        if( dao.usernameExists(username) ){
            return new ValidationResult(false, "Username exists.");
        }
        if( !Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$")
            .matcher(password).find() ){
                return new ValidationResult(false, "Password does not meet requirements");
        }
        return new ValidationResult(true, "");
    }
}
