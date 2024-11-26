import java.sql.*;
import java.util.Scanner;

public class user {
    public static void main(String[] args) {
        getUsers();
    }
    protected static void getUsers(){
        System.out.println();
        System.out.println("----------------------------------");
        System.out.println("           Users Details          ");
        System.out.println("----------------------------------");
        System.out.println();

        String url = "jdbc:mysql://localhost:3306/book_store";
        String user = "root";
        String pass ="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, user, pass);

//            String sql = "";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");

            System.out.println("--------------------------------------------------------------------------------------------------------");
            System.out.println("ID\t\t\tUsername\t\t\t\t\tphone\t\t\t\t\temail\t\t\t\t\t\tuser_type");
            System.out.println("--------------------------------------------------------------------------------------------------------");


                while (rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("username");
                    int phone = rs.getInt("phone");
                    String email = rs.getString("email");
                    String utype="";
                    if(rs.getInt("userType") == 1){
                        utype = "Admin";
                    }else{
                        utype = "User";
                    }
                    System.out.println(id + "\t\t\t" + name
                            + "\t\t\t\t" + phone+ "\t\t\t" + email+ "\t\t\t" + utype);
                }

            System.out.println("\n");
                userMenu();

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void userMenu(){
        Scanner sc = new Scanner(System.in);
        int ch;

        do{
            System.out.println("***********Menu**********\n");
            System.out.println("1. Update User");
            System.out.println("2. View User");
            System.out.println("3. Exit\n");
            System.out.println("*************************");

            System.out.print("Enter Choice: ");
            ch = sc.nextInt();

            switch(ch){
                case 1:
                    System.out.println("\nEnter User Id: ");
                    int id = sc.nextInt();
                    updateUserInfo(id);
                    break;

                case 2:
                    System.out.println("\nEnter User Id: ");
                    id = sc.nextInt();
                    getUserInfo(id);
                    break;

//                default:
//                    System.out.println("Please Enter Valid option...");
            }
        }
        while(ch != 3);
        if(ch == 3){
            home.menu();
        }
    }

    private static void getUserInfo(int id){
        String url = "jdbc:mysql://localhost:3306/book_store";
        String user = "root";
        String pass ="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, user, pass);


            System.out.println("--------------------------------------------------------------------------------------------------------");
            System.out.println("ID\t\t\tUsername\t\t\t\t\tphone\t\t\t\t\temail\t\t\t\t\t\tuser_type");
            System.out.println("--------------------------------------------------------------------------------------------------------");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users where id='" + id +"'");
            if(rs.next()){
//                int id = rs.getInt("id");
                String name = rs.getString("username");
                long phone = rs.getLong("phone");
                String email = rs.getString("email");
                String utype="";
                if(rs.getInt("userType") == 1){
                    utype = "Admin";
                }else{
                    utype = "User";
                }
                System.out.println(id + "\t\t\t" + name
                        + "\t\t\t\t" + phone+ "\t\t\t" + email+ "\t\t\t" + utype);
            }else{
                System.out.println("Please Enter Valide Username and Password");
            }


        }catch (Exception e){
            System.out.println(e);
        }
    }

    private static void updateUserInfo(int id){
        getUserInfo(id);

        System.out.println("\n-----------------------------------");
        System.out.println("         Enter New Details         ");
        System.out.println("-----------------------------------\n");

        String username, email;
        int phone;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Full Name: ");
        username = sc.nextLine();

        System.out.print("Enter Phone Number: ");
        phone = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Email: ");
        email = sc.nextLine();

        System.out.print("Enter Admin Rights press (0/1): ");
        int status = sc.nextInt();

        String url = "jdbc:mysql://localhost:3306/book_store";
        String user = "root";
        String pass ="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, user, pass);

//            String sql = "";
            Statement stmt = con.createStatement();

                String sql = "update users set username='"+username+"' ,email='"+email+"' ,phone='"+phone+"', userType='"+status+"' where id="+id;
                PreparedStatement ps = con.prepareStatement(sql);
                ps.execute();
                System.out.println("\nUser Details Updated......");



        }catch (Exception e){
            System.out.println(e);
        }


    }
}
