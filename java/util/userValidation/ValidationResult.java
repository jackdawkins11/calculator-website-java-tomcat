package util.userValidation;

/*
    Simple class that is used to return data
    from UserValidator.Validate()
*/
public class ValidationResult {

    private boolean valid;
    private String message;

    public ValidationResult(boolean valid, String message){
        this.valid = valid;
        this.message = message;
    }

    public boolean getValid(){
        return valid;
    }

    public String getMessage(){
        return message;
    }
}
