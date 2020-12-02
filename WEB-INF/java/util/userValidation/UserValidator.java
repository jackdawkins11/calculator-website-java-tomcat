package util.userValidation;

import util.data.DBInterface;

public class UserValidator {
    
    public static ValidationResult validate(String username, String password){
        if( username.length() < 5 ){
            return new ValidationResult(false, "Username needs at least 5 characters");
        }
        DBInterface dao = DBInterface.getDAO();
        if( dao.usernameExists(username) ){
            return new ValidationResult(false, "Username exists.")
        }
        return new ValidationResult(true, "");
    }
}
