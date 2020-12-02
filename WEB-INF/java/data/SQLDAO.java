package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import data.DBInterface;

public class SQLDAO implements DBInterface {

    //Checks if the username is taken
    public boolean usernameExists(String username) throws Exception{

        String sql = "select count(*) > 0 from users u where u.username = ?";
        
        Connection conn = DBUtilities.getConnection();

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, username);

        ResultSet rs = stmt.executeQuery();

        if( !rs.next() ){
            throw new Exception("No results in resultset in SQLDAO.usernameExists");
        }

        boolean ret = rs.getBoolean(1);

        DBUtilities.closeResources(conn, stmt);

        System.out.println("Executed: " + sql + " Result: " + String.valueOf(ret));

        return ret;
    }

    //Create a user with the given username and password
    public void insertUser(String username, String password) throws Exception {

        String sql = "insert into users (username, password) values (?, ?)";

        Connection conn = DBUtilities.getConnection();

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, username);
        stmt.setString(2, password);

        int rowcnt = stmt.executeUpdate();

        DBUtilities.closeResources(conn, stmt);

        System.out.println("Executed: " + sql + " Result: " + String.valueOf(rowcnt));

    }

    //Checks if there is an account with the given username and password
    public boolean authorizeUser(String username, String password ) throws Exception {

        String sql = "select count(*) > 0 from users u where u.username = ? and u.password = ?";

        Connection conn = DBUtilities.getConnection();

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();

        if( !rs.next() ){
            throw new Exception("SQLDAO.authorizeUser: No rows in result set");
        }

        boolean ret = rs.getBoolean(1);

        DBUtilities.closeResources(conn, stmt);

        System.out.println("Executed: " + sql + " Result: " + String.valueOf(ret));

        return ret;
    }

    //Returns the id associated with the given username
    public int getIdByUsername(String username) throws Exception{

        String sql = "select id from users u where u.username = ?";

        Connection conn = DBUtilities.getConnection();

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, username);

        ResultSet rs = stmt.executeQuery();

        if( !rs.next() ){
            throw new Exception("No results in resultset in SQLDAO.getIdByUsername");
        }

        int ret = rs.getInt(1);

        DBUtilities.closeResources(conn, stmt);

        System.out.println("Executed: " + sql + " Result: " + String.valueOf(ret));

        return ret;
    }

    //Returns the username of the user with the given id
    public String getUsernameById(int id) throws Exception{

        String sql = "select username from users u where u.id = ?";

        Connection conn = DBUtilities.getConnection();

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        if( !rs.next() ){
            throw new Exception("No results in resultset in SQLDAO.getUsernameById");
        }

        String ret = rs.getString(1);

        DBUtilities.closeResources(conn, stmt);

        System.out.println("Executed: " + sql + " Result: " + String.valueOf(ret));

        return ret;
    }

    //Inserts the given calculation into the database
    public void insertCalculation(Calculation calculation) throws Exception{

        String sql = "insert into calculations values (?, ?, ?, ?, ?, ?)";

        Connection conn = DBUtilities.getConnection();

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, calculation.getUserId() );
        stmt.setFloat(2, calculation.getX());
        stmt.setString(3, calculation.getOp());
        stmt.setFloat(4, calculation.getY());
        stmt.setFloat(5, calculation.getVal());
        stmt.setString(6, calculation.getDateString() );

        int rowcnt = stmt.executeUpdate();

        DBUtilities.closeResources(conn, stmt);

        System.out.println("Executed: " + sql + " Result: " + String.valueOf(rowcnt));
    }

    //Gets up to the last 10 calculations from the db
    public ArrayList< Calculation > getLast10Calculations() throws Exception{

        String sql = "select * from calculations c order by c.time desc limit 10";
        
        Connection conn = DBUtilities.getConnection();

        PreparedStatement stmt = conn.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();

        ArrayList< Calculation > ret = new ArrayList<Calculation>();

        while( rs.next() ){

            ret.add(
                new Calculation(
                    rs.getInt(1),
                    rs.getFloat(2),
                    rs.getString(3),
                    rs.getFloat(4),
                    rs.getFloat(5),
                    rs.getTimestamp(6).toLocalDateTime()
                )
            );
        }

        DBUtilities.closeResources(conn, stmt);

        System.out.println("Executed: " + sql + " Result cnt: " + String.valueOf(ret.size()));

        return ret;

    }

}
