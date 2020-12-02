package data;

import java.util.ArrayList;
import java.util.Date;

public interface DBInterface {
    
    //Checks whether the given username
    //is already in use
    boolean usernameExists(String username) throws Exception;

    //Inserts the specified username and password
    //into the DB
    void insertUser(String username, String password) throws Exception;

    //Checks if there is a user with the given username
    //and password
    boolean authorizeUser(String username, String password) throws Exception;

    //Gets the id of the user with the given username
    int getIdByUsername(String username) throws Exception;

    //Gets the username of the user with the given id
    String getUsernameById(int id) throws Exception;

    //Inserts the specified calculation into the database
    void insertCalculation(Calculation calculation) throws Exception;

    //Gets up to the last 10 calculations in the database
    ArrayList< Calculation > getLast10Calculations() throws Exception;

    //Returns an implementation of this interface
    public static DBInterface getDAO(){
        return new SQLDAO();
    }
}
