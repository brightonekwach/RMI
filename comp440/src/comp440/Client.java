package comp440;



import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
        String accountnumber, username,password,result;
        int  choice ,id, option;
        double balance, amount;
        option = 1;

        jdbcInterface jdbci = (jdbcInterface) Naming.lookup("rmi://192.168.90.80:101/db");
        

        Scanner scan = new Scanner(System.in);
        do{
            System.out.println("Enter one of the choices");
        System.out.println("1 - create account");
        System.out.println("2 - Withdraw ");
        System.out.println("3 - deposit");
        System.out.println("4 - Check account info");
        System.out.println("5 - Exit");
        choice = scan.nextInt();
        switch (choice){
            case 1:
                balance = 0.0;
                    System.out.println("Enter id");
                    id = scan.nextInt();
                    System.out.println("Enter Username");
                    username = scan.next();
                    System.out.println("Account number");
                    accountnumber = scan.next();
                    System.out.println("Enter password");
                     password = scan.next();
                  
                    result = jdbci.Create( id ,accountnumber,username,password,balance );
                    System.out.println(result);

                break;
            case 2:
                
                  System.out.println("Enter Account number");
                  accountnumber = scan.next();
                  System.out.println("Enter Amount to withdraw");
                   amount = scan.nextDouble();
                
                 result = jdbci.Withdrawal(accountnumber,amount);
                 System.out.println(result);

                break;
            case  3:
                System.out.println("Enter Account number");
                accountnumber = scan.next();
                System.out.println("Enter Amount to deposit");
                amount = scan.nextDouble();
                
                 result = jdbci.deposit(accountnumber,amount);
                System.out.println(result);
        
               


                break;
            case 4:
                System.out.println("Enter Account number");
                accountnumber = scan.next();
                ArrayList record = jdbci.CheckAccount(accountnumber);
                System.out.println(record);

                break;
            case 5:
                option = 0;
                break;

        }
        }while(option == 1);

        
        
    }
}
