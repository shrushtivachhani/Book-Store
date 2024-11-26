
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class soldBooks {
    public static void main(String[] args) {
        sold();
    }

    protected static void sold(){
        String url = "jdbc:mysql://localhost:3306/book_store";
        String user = "root";
        String pass ="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, user, pass);


            System.out.println("--------------------------------------------------------------------------------------------------------");
            System.out.println("ID\t\t\tBook Name\t\t\tBuyer Name\t\t\t\t\tPrice\t\t\tQauntity\t\tTotal Amount");
            System.out.println("--------------------------------------------------------------------------------------------------------");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from sold_books");
            while(rs.next()){
                int id = rs.getInt("id");
                String bname = rs.getString("bname");
                String bauthor = rs.getString("buyername");
                int price = rs.getInt("price");
                int qty = rs.getInt("qty");
                int total = rs.getInt("totalbill");

                System.out.println(id + "\t\t\t" + bname
                        + "\t\t\t\t" + bauthor+ "\t\t\t\t" + price+ "\t\t\t\t" + qty+ "\t\t\t\t" +total);
            }


        }catch (Exception e){
            System.out.println(e);
        }
    }
}
