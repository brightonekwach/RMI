package comp440;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface jdbcInterface extends Remote {

//    public String Insert(String name, int id, String gender, int age, String department, String year) throws RemoteException;
//    public String Update(String name, int id, String gender, int age, String department, String year) throws RemoteException;
//    public  String Delete(int id) throws RemoteException;
//    public ArrayList Search(int id) throws RemoteException;
    
    
    public String Create(int id,String accountNumber,  String username,  String password, double balance) throws RemoteException;
    public String deposit(String accountNumber, double amount) throws RemoteException;
    public String Withdrawal(String accountNumber, double amount) throws RemoteException;
    public ArrayList CheckAccount(String accountNumber) throws RemoteException;


    
}
