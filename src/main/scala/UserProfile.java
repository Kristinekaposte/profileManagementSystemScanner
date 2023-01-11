import java.util.Scanner;

public class UserProfile {
    public static void main(String[] args) {
        DatabaseMethods databaseMethods =new DatabaseMethods();
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        System.out.println(ANSI_RED + "                 WELCOME TO OUR Profile Management System!"+ ANSI_RESET);
        try (Scanner scanner = new Scanner(System.in)) {
            boolean  isRunning=true;
            while (isRunning) {
                System.out.println('\n'+ ANSI_GREEN+"TO CONTINUE ENTER CHOICE BY NUMBER"+ ANSI_RESET);
                System.out.println("|------------Profile Management System-------|");
                System.out.println("| 1.SIGN UP USER                             |");
                System.out.println("| 2.UPDATE USER                              |");
                System.out.println("| 3.DELETE USER                              |");
                System.out.println("| 4.LOGIN                                    |");
                System.out.println("| 5.SHOW TABLE                               |");
                System.out.println("| 0.EXIT                                     |");
                System.out.println("| ***!!Fields with * can't be null!!***      |");
                System.out.println("|--------------------------------------------|");
                int choise = Integer.parseInt(scanner.nextLine());

                switch (choise) {
                    case 1:
                        System.out.println("Enter user firstName*,lastName*,gender,age*,email*,phone*,password*");
                        User user = new User(
                               scanner.nextLine(), scanner.nextLine(), scanner.nextLine(),
                               Integer.parseInt(scanner.nextLine()), scanner.nextLine(),
                               Integer.parseInt(scanner.nextLine()),  scanner.nextLine());
                        databaseMethods.signUpUser(user);
                        System.out.println("You created new user: firstName:" +user.getFirstName()
                                + ", lastName: " +user.getLastName()
                                + ", gender: " +user.getGender()
                                + ", age: " +user.getAge()
                                + ", email: " +user.getEmail()
                                + ", phone: " +user.getPhone()
                                + ", password: " +user.getPassword());
                        break;

                    case 2:
                        System.out.println("Provide id to set new parameters for user: firstName*,lastName*,gender,age*,email*,phone*,password*");
                        User user2 = new User(Integer.parseInt(scanner.nextLine()),                           //id
                                scanner.nextLine(), scanner.nextLine(),scanner.nextLine(),                    //firstname,lastname,gender
                                Integer.parseInt(scanner.nextLine()),                                         //age
                                scanner.nextLine(), Integer.parseInt(scanner.nextLine()),scanner.nextLine()   //email,phone,password

                        );

                        databaseMethods.updateUser(user2);
                        System.out.println("Updated id: "+ user2.getId()
                                + ", firstName: " +user2.getFirstName()
                                + ", lastName: " +user2.getLastName()
                                + ", gender: " +user2.getGender()
                                + ", age: " +user2.getAge()
                                + ", email: " +user2.getEmail()
                                + ", phone: " +user2.getPhone()
                                + ", password: " +user2.getPassword());
                        break;

                    case 3:
                        System.out.println("Provide id to delete user");
                        User user3 = new User(Integer.parseInt(scanner.nextLine()));
                        databaseMethods.deleteUser(user3);
                        System.out.println("deleted id: "+ user3.getId());
                        break;
                    case 4:
                        System.out.println("Provide username and password to login");
                        User user4 = new User(scanner.nextLine(),scanner.nextLine());
                        databaseMethods.loginUser(user4);
                        System.out.println("provided: username: "+ user4.getUsername()+" password: "+ user4.getPassword());
                        break;



                    case 5:
                        System.out.println("HERE IS TABLE:");
                        User user5 = new User();
                        databaseMethods.showTable(user5);
                      //  System.out.println("username: "+ user5.getUsername()+"password: "+ user5.getPassword());
                        break;

                    default:
                        System.out.println("Enter valid choice:");
                        break;
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    }



/*
  Create a user profile management system
  User (firstname, lastname, email, phone, address, gender, age, password, USERNAME(firstname + lastname + last 2 digits of phone))
  SIGNUP, LOGIN(username, password), UPDATE(id), DELETE(id) [CRUD]
     */