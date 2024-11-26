import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ch;

        do{
            System.out.println("***********Menu**********\n");
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit\n");
            System.out.println("*************************");

            System.out.print("Enter Choice: ");
            ch = sc.nextInt();

            switch(ch){
                case 1:
                    auth.sign();
                    System.out.println("You need to add Username and Password...!!");
                    auth.sign();
                    break;

                case 2:
                    auth.signup();
                    break;

                default:
                    System.out.println("You are Logged out...!!");
            }
        }
        while(ch != 3);
    }
}
