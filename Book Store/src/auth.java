import com.sun.source.tree.CaseTree;

import java.sql.*;
import java.util.Scanner;

public class auth {

    public static void main(String[] args) {
//        signup();
        sign();
    }
    public static void sign() {
        System.out.println();
        System.out.println("---------------------------");
        System.out.println("           Login           ");
        System.out.println("---------------------------");
        System.out.println();

        Scanner sc = new Scanner(System.in);
        String username, password;

        System.out.print("Enter User Name: ");
        username = sc.nextLine();

        System.out.print("Enter Password: ");
        password = sc.nextLine();
            String url = "jdbc:mysql://localhost:3306/book_store";
            String user = "root";
            String pass = "";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection con = DriverManager.getConnection(url, user, pass);

//            String sql = "";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select username,password,userType from users where username='"+ String.valueOf(username) +"' and password='"+String.valueOf(password)+"'");
//            boolean count = rs.next();

                if(rs.next()) {
                    int a=rs.getInt("userType");
                    if (a == 1) {
                        books.bookMenu();
                    } else {
//                        userHome uh = new userHome();
                        userHome.userMenu();
                    }
                }


            } catch (Exception e) {
                System.out.println(e);
            }
        }



    public static void signup() {
        System.out.println();
        System.out.println("---------------------------");
        System.out.println("           SignUp          ");
        System.out.println("---------------------------");
        System.out.println();

        String username, password, email;
        Long phone;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Full Name: ");
        username = sc.nextLine();

        phone = askPhone();

        email = askEmail();

        password = askPass();

        //Inserting data into the database
        String url = "jdbc:mysql://localhost:3306/book_store";
        String user = "root";
        String pass ="";

        String sql = "insert into users(username, password, phone, email)"+ "values(?,?,?,?)";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, user, pass);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2, password);
            ps.setLong(3, phone);
            ps.setString(4, email);


            int st = ps.executeUpdate();

            if(st == 1){
                System.out.println("Inserted");
            }else {
                System.out.println("Error!");
            }


            con.close();

        } catch(Exception e){
            System.out.println(e);
        }
    }
    static Long askPhone(){
        System.out.print("Enter Phone Number: ");
        Scanner sc = new Scanner(System.in);
        Long phone = sc.nextLong();

        if (String.valueOf(phone).length() < 10){
            System.out.println("Please enter 10 digits in mobile number");
            return askPhone();
        }
        return phone;
    }
    static String askEmail(){
        System.out.print("Enter Email: ");
        Scanner sc = new Scanner(System.in);
        String email = sc.nextLine();

        if (!email.contains("@") || (!email.contains(".com") && !email.contains(".in"))){
            System.out.println("Please enter a valid email");
            return askEmail();
        }
        return email;
    }
    static String askPass(){
        System.out.print("Enter Password: ");
        Scanner sc = new Scanner(System.in);
        String pass = sc.nextLine();

        if (pass.length() < 8){
            System.out.println("Please enter 8 characters in password");
            return askPass();
        }
        return pass;
    }
}
