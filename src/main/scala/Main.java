import com.mysql.cj.jdbc.MysqlDataSource;
import net.liftweb.http.S;

import javax.sql.DataSource;
import java.sql.*;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        Connection conn=null;
        try {
          conn= DriverManager.getConnection
                  ("jdbc:mysql://localhost:3306/java_class_db",
                          "root","admin");


          //  getProducer("apple");
         //   createTable(conn);
          //  String[] randomNames  = {"id INTEGER PRIMARY KEY AUTO_INCREMENT", ", title2 VARCHAR (30) UNIQUE", ", title3 INTEGER NOT NULL"};
          //  createTableTwo(conn,"random_table_two",randomNames);

         //   insertIntoTable(conn);
         //   getProducer2(conn,"apple");

          //  getAllMovies(conn,"mary");
          //  getProducerCall(conn,"windows");
          //  getAppearanceCount(conn, "mary", "john");
            getAppearanceCount2(conn, "mary", "john");

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection
               ("jdbc:mysql://localhost:3306/java_class_db",
                       "root","admin");
    }

    public static Connection getSourceConnection () throws SQLException{
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/java_class_db");
        dataSource.setUser("root");
        dataSource.setPassword("admin");

        return dataSource.getConnection();
    }

    public static void getProducer (String searchName){
        String getProducerByName = "SELECT * FROM Producer WHERE reg_name LIKE" + "'" + searchName + "'";
        try( Connection conn =  getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(getProducerByName);
            DecimalFormat df = new DecimalFormat("#0.0");
            while (rs.next()){
                System.out.println("id " +rs.getInt(1));
                     System.out.println("reg_name " +rs.getString(2));
                    System.out.println("revenue " + df.format(rs.getDouble(3)));

            }
/*
  create a method to fetch producer by name  |  getProducer("apple")
 */
            st.close();
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static  void createTable (Connection conn) throws SQLException{
        Statement stat = conn.createStatement();
     //   boolean isResultSetReturned = stat.execute
              stat.execute  ("CREATE TABLE IF NOT EXISTS random_table " +
                        "(id INTEGER PRIMARY KEY AUTO_INCREMENT, title VARCHAR (30) UNIQUE)");


    }

    public static  void createTableTwo (Connection conn, String tableName,String [] randomNames  ) throws SQLException{
      Statement stat = conn.createStatement();
      String temp="";
       for (int i=0; i<randomNames.length; i++){
     // temp +=  randomNames[i];
       temp= temp + randomNames[i];

       }
        stat.execute  ("CREATE TABLE IF NOT EXISTS " +tableName + "("+temp+")");
        System.out.println(temp);
    }

    public static  void insertIntoTable (Connection conn) throws SQLException{
        Statement stat = conn.createStatement();
        //   boolean isResultSetReturned = stat.execute
        stat.executeUpdate  ("INSERT INTO random_table(title) VALUES ('NEW TITLE')");


    }

    public static void getProducer2 (Connection conn, String regName)throws SQLException{
        PreparedStatement pStatement = conn.prepareStatement(
                "SELECT * FROM Producer WHERE reg_name = ?");
        pStatement.setString(1,regName);

        ResultSet resultSet = pStatement.executeQuery();
        while (resultSet.next()){
            System.out.println("producer name is " +resultSet.getString(2)+ " and their revenue is " + resultSet.getDouble(3));
        }
    }

    public static void getAllMovies (Connection conn, String actorName)throws SQLException{
        PreparedStatement pStatement = conn.prepareStatement(
                "SELECT actor.full_name, movie.title FROM Movie_actor\n" +
                        "JOIN Actor ON\n" +
                        "Movie_actor.actor_id = Actor.id\n" +
                        "JOIN  Movie ON\n" +
                        "Movie_actor.movie_id = movie.id \n" +
                        "WHERE Actor.full_name LIKE CONCAT ('%', ? , '%')");
        pStatement.setString(1,actorName);
        ResultSet resultSet = pStatement.executeQuery();

        while (resultSet.next()){
            System.out.println("actor name is: " +resultSet.getString(1)+
                    " movie name is: " + resultSet.getString(2));
        }
    }

    public static void getProducerCall (Connection conn, String name)throws SQLException {
        CallableStatement callableStatement= conn.prepareCall("{Call getProducers(?)}");
        callableStatement.setString(1,name);

        ResultSet resultSet = callableStatement.executeQuery();
        while (resultSet.next()){
            System.out.println("Producers revenue is "+ resultSet.getDouble(3));
        }
    }
    public static void getAppearanceCount(Connection conn, String actorName, String directorName) throws SQLException {
        PreparedStatement pStatement = conn.prepareStatement("{CALL getAppearanceCount (?,?)}");

        pStatement.setString(1,actorName);
        pStatement.setString(2,directorName);
        ResultSet resultSet = pStatement.executeQuery();

        while (resultSet.next()){
            System.out.println("Actor appears in movies by provided director: " +resultSet.getInt(1)+" times");
        }
    }
    public static void getAppearanceCount2(Connection conn, String actorName, String directorName) throws SQLException {
        CallableStatement callableStatement= conn.prepareCall("{CALL getAppearanceCount (?,?)}");
        callableStatement.setString(1,actorName);
        callableStatement.setString(2,directorName);

        ResultSet resultSet = callableStatement.executeQuery();

        while (resultSet.next()){
            System.out.println("Actor appears in movies by provided director: " +resultSet.getInt(1)+" times");
        }
    }
}


