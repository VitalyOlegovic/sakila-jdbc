
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EsempiJDBC {

    public static void main(String[] args) throws SQLException {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String host = "localhost";
            String databaseName = "sakila";
            String userName = "sakila";
            String password = "sakila";
            
            String url = "jdbc:mysql://" + host + ":3306/" + databaseName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            
            String query = "select * from actor";
            Connection conn = DriverManager.getConnection( url, userName, password);
            PreparedStatement ps = conn.prepareStatement( query );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EsempiJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
