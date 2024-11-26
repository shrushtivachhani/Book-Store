
import java.sql.*;
import java.util.Scanner;

public class userHome {
    public static void main(String[] args) {
        userMenu();
    }

    protected static void userMenu() {
        Scanner sc = new Scanner(System.in);
        int ch;

        do{
            System.out.println("***********Menu**********\n");
            System.out.println("1. View Books");
            System.out.println("2. Exit\n");
            System.out.println("*************************");

            System.out.print("Enter Choice: ");
            ch = sc.nextInt();

            switch(ch){
                case 1:
                    viewBooks();
                    break;
            }
        }
        while(ch != 2);
        if(ch == 2){
          new home();
        }
    }

    private static void viewBooks() {
        books.getBookInfo();
        int ch;
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("\nPress 1 to buy any books..:1");
        ch = sc.nextInt();
        System.out.println();
        do {
            if (ch == 1) {
                System.out.println("Enter Book id: ");
                int id = sc.nextInt();
                buyBook(id);
            } else {
                System.out.println("Opps!....Please Enter 1 to buy a book: ");
                ch = sc.nextInt();
            }
        }while(ch!=1);

    }


    private static void buyBook(int id){
        String url = "jdbc:mysql://localhost:3306/book_store";
        String user = "root";
        String pass ="";
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, user, pass);



            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from books where id='"+id+"'");
            if(rs.next()){

                System.out.println("How many books you want to buy: ");
                int qty = sc.nextInt();
                sc.nextLine();

                if(rs.getInt("stock") > 0 && qty <= rs.getInt("stock")) {
//                int id = rs.getInt("id");
                    System.out.println("Enter Your User Name");
                    String name = sc.nextLine();

                    String bname = rs.getString("bname");
                    int price = rs.getInt("price");

                    int totalBill = 0;
                    totalBill += price * qty;
                    int stock = rs.getInt("stock") - qty;

                    String sql = "insert into sold_books(bname, buyername, price, qty, totalbill)"+ "values(?,?,?,?,?)";
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");

//                        Connection con = DriverManager.getConnection(url, user, pass);

                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1,bname);
                        ps.setString(2, name);
                        ps.setInt(3, price);
                        ps.setInt(4, qty);
                        ps.setInt(5, totalBill);


                        int st = ps.executeUpdate();
                        String sql1 = "update books set stock="+stock+" where id="+id;
                        ps = con.prepareStatement(sql1);
                        ps.execute();

                        if(st == 1){
                            soldBooks sb = new soldBooks();
                            System.out.println("Book Buyed SuccessFully.....\nYour Bill is : " + totalBill);
                        }else {
                            System.out.println("Error!");
                        }


                        con.close();

                    } catch(Exception e){
                        System.out.println(e);
                    }

                }else{
                    System.out.println("Book is not available...!");
                }
            }else{
                System.out.println("Please Enter Book Id");
            }


        }catch (Exception e){
            System.out.println(e);
        }




    }

}
