
import java.util.ArrayList;
import java.time.LocalDateTime;

import data.DBInterface;
import data.Calculation;

public class TestDBInterface {
   
    public static void main(String args[]){

        DBInterface dao = DBInterface.getDAO();

        try{
            System.out.println(
                "dao.usernameExists('jack'): " +
                String.valueOf(
                    dao.usernameExists("jack")
                )
            );

            dao.insertUser("jack", "jack");
            System.out.println("insert user 'jack':'jack'");
            
            System.out.println(
                "dao.usernameExists('jack'): " +
                String.valueOf(
                    dao.usernameExists("jack")
                )
            );

            System.out.println(
                "dao.authorizeUser('jack', 'jack'): " +
                String.valueOf(
                    dao.authorizeUser("jack", "jack")
                )
            );

            System.out.println(
                "dao.authorizeUser('jack', 'jackL'): " +
                String.valueOf(
                    dao.authorizeUser("jack", "jackL")
                )
            );

            System.out.println(
                "dao.getUsernameById(1): " +
                String.valueOf(
                    dao.getUsernameById(1)
                )
            );

            System.out.println(
                "dao.getIdByUsername('jack'): " +
                String.valueOf(
                    dao.getIdByUsername("jack")
                )
            );

            dao.insertCalculation(
                new Calculation(
                    1, 10, "+", 10, 20, LocalDateTime.now()
                )
            );

            dao.insertCalculation(
                new Calculation(
                    1, 12, "+", 12, 24, "2020-01-01 01:02:03"
                )
            );

            ArrayList< Calculation > calcs = dao.getLast10Calculations();

            System.out.println("Printing " + calcs.size() + " calcs");

            for( Calculation calc : calcs ){
                System.out.println( calc.toJSON() );
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
