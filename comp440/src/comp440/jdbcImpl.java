package comp440;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;

public class jdbcImpl extends UnicastRemoteObject implements jdbcInterface {
    String user = "root";
    String pass = "";
    Connection con;
    public jdbcImpl () throws RemoteException, SQLException, ClassNotFoundException {
        super();//
        String path = "com.mysql.cj.jdbc.Driver";
        Class.forName(path);
               con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "");
        
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        //    con = DriverManager.getConnection("jdbc:mysql:/localhost:3306/Schooldb", user, pass);
//        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Schooldb", "root", "");
    }
//    public String Insert(String name, int id, String gender, int age, String department, String year) throws RemoteException {
//
//       try{
//
//
//           Statement st = con.createStatement();
//           String sql = "insert into student values('" +id+"','" +name+"','"+gender+"','"+department+"','"+age+"','"+year+"')";
//           st.executeUpdate(sql);
//           return "Record inserted successfully";
//       }catch(Exception e){
//           return (e.toString());
//       }
//
//
//    }
//
//    public String Update(String name, int id, String gender, int age, String department, String year) throws RemoteException {
//        try {
//
//            Statement st = con.createStatement();
//            String sql = "update student set name ='" +name+"', gender = '"+gender+"' department = ,'"+department+"', age = '"+age+"',year= '"+year+"' ";
//            st.executeUpdate(sql);
//            return  "Record updated succesfully";
//        }
//        catch (Exception e){
//            return (e.toString());
//
//        }
//    }
//
//    public String Delete(int id) throws RemoteException {
//        try {
//
//            Statement st = con.createStatement();
//            String sql = "delete from student where id='" +id+"'";
//            st.executeUpdate(sql);
//             return "Record deleted successfully";
//        } catch (Exception e) {
//            return (e.toString());
//        }
//    }
//
//    public ArrayList Search(int id) throws RemoteException {
//        ArrayList student = new ArrayList();
//        try{
//            Statement st = con.createStatement();
//            String sql = "select * from student where id = '"+id+"' ";
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()){
//                student.add("Name :" + rs.getString("name"));
//                student.add("Gender :" + rs.getString("gender"));
//                student.add("Department :" + rs.getString("department"));
//                student.add("Age :" + rs.getString("age"));
//                student.add("Year :" + rs.getString("year"));
//            }
//        }
//        catch (Exception e){
//        }
//        return student;
//    }

    @Override
    public String Create( int id,String accountNumber, String username, String password, double balance) throws RemoteException {
          try{


           Statement st = con.createStatement();
           String sql = "insert into accounts values('" +id+"','" +accountNumber+"','" +password+"','"+username+"','"+balance+"')";
           st.executeUpdate(sql);
           return "Account created successfully";
       }catch(Exception e){
           return (e.toString());
       }    
    }

    @Override
    public String deposit(String accountNumber, double amount) throws RemoteException {
                try {

            Statement st = con.createStatement();
            String sql1 = "UPDATE accounts SET balance = balance + ? WHERE accountnumber = ?"; 
            PreparedStatement preparedStatement = con.prepareStatement(sql1);
            preparedStatement.setDouble(1, amount);
            preparedStatement.setString(2, accountNumber);
            
            int rowsAffected = preparedStatement.executeUpdate();
            
            if (rowsAffected > 0){
                return "Accont balance updated succesfully";
            } else{
                return "failed to update account balance";
            }
        }
        catch (Exception e){
            return (e.toString());

        }
    }

    @Override
    public String Withdrawal(String accountNumber, double amount) throws RemoteException {
            try {

            Statement st = con.createStatement();
            String sql1 = "UPDATE accounts SET balance = balance - ? WHERE accountnumber = ?"; 
            PreparedStatement preparedStatement = con.prepareStatement(sql1);
            preparedStatement.setDouble(1, amount);
            preparedStatement.setString(2, accountNumber);
            
            int rowsAffected = preparedStatement.executeUpdate();
            
            if (rowsAffected > 0){
                return "Amount Withdrawn  succesfully";
            } else{
                return "Withdrawal failed";
            }
        }
        catch (Exception e){
            return (e.toString());

        }
    }

    @Override
    public ArrayList CheckAccount(String accountNumber) throws RemoteException {
                ArrayList Client = new ArrayList();
        try{
            Statement st = con.createStatement();
            String sql = "select * from accounts where accountnumber = '"+accountNumber+"' ";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
           
                Client.add("Account number :" + rs.getString("accountnumber"));
                Client.add("Username :" + rs.getString("username"));
                Client.add("Balance :" + rs.getDouble("balance"));
                
            }
        }
        catch (Exception e){
        }
        return Client;
    
    }
}
