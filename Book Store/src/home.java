import java.util.Scanner;

public class home {

    public static void main(String[] args) {
        menu();
    }

    protected static void menu(){
        Scanner sc = new Scanner(System.in);
        int ch;

        do{
            System.out.println("***********Menu**********\n");
            System.out.println("1. View Users");
            System.out.println("2. View Books");
            System.out.println("3. Sold Books");
            System.out.println("4. Exit\n");
            System.out.println("*************************");

            System.out.print("Enter Choice: ");
            ch = sc.nextInt();

            switch(ch){
                case 1:
                    user.getUsers();
                    break;

                case 2:
                    books.bookMenu();
                    break;

                case 3:
                    soldBooks.sold();
                    break;

//                case 4:
//                    auth.sign();
//                    break;

//                case 5:
//                    auth.signup();
//                    break;

                default:
                   System.out.println("Please Enter Valid option...");
            }
        }
        while(ch != 3);
        if(ch == 3){
            new home();
        }
    }
}



