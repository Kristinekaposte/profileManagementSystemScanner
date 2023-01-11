public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private int age;
    private int phone;
    private String password;
    private String username;

    public  User(){

    }
    public User(int id , String firstName, String lastName,
                String gender,int age,String email,
                int phone,String password) {
        this.id = id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.gender=gender;
        this.age=age;
        this.email=email;
        this.phone=phone;
        this.password=password;
    }
    public User(String firstName, String lastName,
                String gender,int age,String email,
                int phone,String password) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.phone=phone;
        this.gender=gender;
        this.age=age;
        this.password=password;
    }
    public User(int id ) {
        this.id = id;
    }
    public User(String username, String password) {
        this.username=username;
        this.password=password;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
