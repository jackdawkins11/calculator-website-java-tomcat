package data;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.Statement;

public class DBUtilities {

    final static String url = "jdbc:mysql://localhost:3306/";
    final static String database = "calculatorjava";
    final static String user = "jack";
    final static String pass = "jack";
    private static boolean registeredDriver = false;
    
    /*
    Creates the database.
    */
    static void createDatabase(){

        String sql[] = {
            "drop database if exists " + database,
            "create database " + database
        };

        try{
        
            Connection conn = DriverManager.getConnection(url, user, pass);

            for (String cSql : sql){
                
                Statement stmt = conn.createStatement();

                boolean ret = stmt.execute(cSql);

                System.out.println( "Executed: " + cSql );
            }

        }catch(Exception err){
            err.printStackTrace();
        }

    }

    /*
        This function accesses static variables (registeredDriver) and 
        could be called from multiple threads at once so it must be synchronized.
    */
    public static synchronized Connection getConnection() throws Exception {
        if( !registeredDriver ){
            Class.forName("com.mysql.jdbc.Driver");
            registeredDriver = true;
        }
        return DriverManager.getConnection(url + database, user, pass);
    }

    /*
    Closes the given resources if possible.
    */
    public static void closeResources(Connection conn, PreparedStatement statement ){
        try{
            conn.close();
        }catch(Exception e){
            System.out.println("Couldn't close connection: " + e.getLocalizedMessage());
        }
        try{
            statement.close();
        }catch(Exception e){
            System.out.println("Couldn't close statement: " + e.getLocalizedMessage());
        }
    }

    /*
    Creates the tables for the application.
    */
    public static void createTables(){
        
        String sql[] = {
            "create table users (id int not null auto_increment primary key,"
            + " username varchar(100) not null, password varchar(100) not null)",
            "create table calculations (user_id int not null, x float(53), op char(1), y float(53),"
            + " val float(53), time datetime)"
        };

        try{
            Connection conn = getConnection();

            for(String cSql : sql){

                Statement stmt = conn.createStatement();

                boolean ret = stmt.execute(cSql);

                System.out.println("Executed: " + cSql );

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
        Initializes the database for the application. Run this before you deploy the app.
    */
    public static void main(String args[]){
        DBUtilities.createDatabase();
        DBUtilities.createTables();
    }
}
