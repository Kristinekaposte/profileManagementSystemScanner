import java.io.IOException;
import java.sql.*;

public class DatabaseMethods {

    DatabaseMethods() {
    } // CONSTRUCTOR

    ConnectionClass connectionClass = new ConnectionClass(); //OBJECT TO CREATE CONNECTION
    PreparedStatement pStatement;


    public void signUpUser(User user) {
        String createSignUp = "INSERT INTO UserData (firstName,lastName,gender,age,email,phone,password ) VALUES \n" +
                "(?,?,?,?,?,?,?)";
        try (Connection connection = connectionClass.getConnection()) {
            pStatement = connection.prepareStatement(createSignUp);


            pStatement.setString(1, user.getFirstName());
            pStatement.setString(2, user.getLastName());
            pStatement.setString(3, user.getGender());
            pStatement.setInt(4, user.getAge());
            pStatement.setString(5, user.getEmail());
            pStatement.setInt(6, user.getPhone());
            pStatement.setString(7, user.getPassword());

            pStatement.executeUpdate();
            pStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateUser(User user) throws IOException {

        String updateUserData = "UPDATE  userData SET firstName=?,lastName=?,gender=?,age=?,email=?,phone=?,password=?  WHERE id =? ";
        try (Connection connection = connectionClass.getConnection()) {
            pStatement = connection.prepareStatement(updateUserData);

            pStatement.setString(1, user.getFirstName());
            pStatement.setString(2, user.getLastName());
            pStatement.setString(3, user.getGender());
            pStatement.setInt(4, user.getAge());
            pStatement.setString(5, user.getEmail());
            pStatement.setInt(6, user.getPhone());
            pStatement.setString(7, user.getPassword());
            pStatement.setInt(8, user.getId());

            pStatement.executeUpdate();
            pStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(User user) {

        String deleteUserData = "DELETE FROM userData where id=?";
        try (Connection connection = connectionClass.getConnection()) {
            pStatement = connection.prepareStatement( deleteUserData);

            pStatement.setInt(1,user.getId());

            pStatement.executeUpdate();
            pStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loginUser(User user) {
        String loginUser = "SELECT * FROM userdata WHERE  username LIKE"+ "'" +  user.getUsername() + "'"+"AND password= "+ "'" + user.getPassword() + "'";
        try (Connection connection = connectionClass.getConnection()) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(loginUser);
            if (rs.next()){
               System.out.println("You have logged in successfully!");

            }else {
                // if username or password is not correct show
                System.out.println("Please enter valid username or password");
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showTable(User user){
        String allUserData = "SELECT * FROM userdata";
        try (Connection connection = connectionClass.getConnection()) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(allUserData);

                while (rs.next()){
                    System.out.println("_______________________________________________________________________________________________________________________");
                    System.out.println(" " +rs.getInt(1)
                            + "   " + rs.getString(2)
                            + "   " + rs.getString(3)
                            + "   " + rs.getString(4)
                            + "   " + rs.getInt(5)
                            + "   " + rs.getString(6)
                            + "   " + rs.getInt(7)
                            + "   " + rs.getString(8)
                            + "   " + rs.getString(9)
                    );

//                    System.out.println("id :" +rs.getInt(1)
//                            + " first name is: " + rs.getString(2)
//                            + " last name is: " + rs.getString(3)
//                            + " gender is: " + rs.getString(4)
//                            + " age is: " + rs.getInt(5)
//                            + " email is: " + rs.getString(6)
//                            + " phone is: " + rs.getInt(7)
//                            + " password is: " + rs.getString(8)
//                            + " username is: " + rs.getString(9)
//                    );
                }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
