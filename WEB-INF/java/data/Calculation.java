package data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import data.DBInterface;

public class Calculation {
    
    private int userId;
    private float x, y, val;
    private String op;
    private LocalDateTime date;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //Create a new calculation
    public Calculation(int userId, float x, String op, float y, float val, LocalDateTime date){
        this.userId = userId;
        this.x = x;
        this.op = op;
        this.y = y;
        this.val = val;
        this.date = date;
    }

    //Create a new calculation with a timestring
    public Calculation(int userId, float x, String op, float y, float val, String timestring){
        this.userId = userId;
        this.x = x;
        this.op = op;
        this.y = y;
        this.val = val;
        this.date = LocalDateTime.parse(timestring, formatter);
    }

    //Read its values
    public int getUserId(){
        return userId;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float getVal(){
        return val;
    }

    public String getOp(){
        return op;
    }

    public LocalDateTime getDate(){
        return date;
    }

    public String getDateString(){
        return date.format(formatter);
    }

    //Get the JSON needed for the UI
    public String toJSON() throws Exception {
        DBInterface dao = DBInterface.getDAO();
        String username = dao.getUsernameById(userId);
        return String.format(
            "{ \"Username\": \"%s\","
            + " \"X\": %f,"
            + " \"Op\": \"%s\","
            + " \"Y\": %f,"
            + " \"Val\": %f,"
            + " \"Date\": \"%s\" }",
            username, x, op, y, val, getDateString());
    }
}
