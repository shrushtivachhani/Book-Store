import java.sql.*;
import java.util.Scanner;

public class books {
    public static void main(String[] args) {
            bookMenu();
    }

    public static void bookMenu(){
        Scanner sc = new Scanner(System.in);
        int ch;

        do{
            System.out.println("***********Menu**********\n");
            System.out.println("1. All Books");
            System.out.println("2. Update Books");
            System.out.println("3. Add new Book");
            System.out.println("4. Exit\n");
            System.out.println("*************************");

            System.out.print("Enter Choice: ");
            ch = sc.nextInt();

            switch(ch){
                case 1:
                    getBookInfo();
                    break;

                case 2:
                    System.out.println("\nEnter Book Id: ");
                    int id = sc.nextInt();
                    updateBookInfo(id);
                    break;

                case 3:
                    addBook();
                    break;

//                default:
//                    System.out.println("Please Enter Valide option...");
            }
        }
        while(ch != 4);
        if(ch == 4){
            home.menu();
        }
    }

    private static void addBook() {
        System.out.println();
        System.out.println("---------------------------------");
        System.out.println("           Book Details          ");
        System.out.println("---------------------------------");
        System.out.println();

        String bookName, bookAuthor;
        int price, stock;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Book Name: ");
        bookName = sc.nextLine();

        System.out.print("Enter Book Author Name: ");
        bookAuthor = sc.nextLine();

        System.out.print("Enter Book Price: ");
        price = sc.nextInt();

        System.out.print("Enter Book Stock: ");
        stock = sc.nextInt();

        //Inserting data into the database
        String url = "jdbc:mysql://localhost:3306/book_store";
        String user = "root";
        String pass ="";

        String sql = "insert into books(bname, bauthor, price, stock)"+ "values(?,?,?,?)";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, user, pass);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,bookName);
            ps.setString(2, bookAuthor);
            ps.setInt(3, price);
            ps.setInt(4, stock);


            int st = ps.executeUpdate();

            if(st == 1){
                System.out.println("Book Detail Inserted....");
            }else {
                System.out.println("Error!");
            }


            con.close();

        } catch(Exception e){
            System.out.println(e);
        }

    }

    protected static void getBookInfo() {
        String url = "jdbc:mysql://localhost:3306/book_store";
        String user = "root";
        String pass ="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, user, pass);


            System.out.println("--------------------------------------------------------------------------------------------------------");
            System.out.println("ID\t\t\tBook Name\t\t\tAuthor Name\t\t\tPrice\t\t\tStock\t\t\tStatus");
            System.out.println("--------------------------------------------------------------------------------------------------------");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from books");
            while(rs.next()){
                int id = rs.getInt("id");
                String bname = rs.getString("bname");
                String bauthor = rs.getString("bauthor");
                int price = rs.getInt("price");
                int stock = rs.getInt("stock");
                String status="";
                if(rs.getInt("status") == 1){
                    status = "Available";
                }else{
                    status = "Inavailable";
                }
                System.out.println(id + "\t\t\t" + bname
                        + "\t\t\t" + bauthor+ "\t\t\t\t" + price+ "\t\t\t\t" + stock+ "\t\t\t\t" +status);
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }

    private static void updateBookInfo(int id) {
        System.out.println("\n-----------------------------------");
        System.out.println("         Enter New Details         ");
        System.out.println("-----------------------------------\n");

        String bookName, bookAuthor;
        int price, stock;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Book Name: ");
        bookName = sc.nextLine();

        System.out.print("Enter Book Author Name: ");
        bookAuthor = sc.nextLine();

        System.out.print("Enter Book Price: ");
        price = sc.nextInt();

        System.out.print("Enter Book Stock: ");
        stock = sc.nextInt();

        System.out.print("Enter Book Status (0/1): ");
        int status = sc.nextInt();


        String url = "jdbc:mysql://localhost:3306/book_store";
        String user = "root";
        String pass ="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, user, pass);

//            String sql = "";
            Statement stmt = con.createStatement();

            String sql = "update books set bname='"+bookName+"' ,bauthor='"+bookAuthor+"' ,price='"+price+"' ,stock='"+stock+"',status='"+status+"' where id="+id;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            System.out.println("\nUser Details Updated......");



        }catch (Exception e){
            System.out.println(e);
        }

    }
}
