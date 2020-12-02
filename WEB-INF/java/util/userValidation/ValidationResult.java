package util.userValidation;

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

    public boolean getMessage(){
        return message;
    }
}
