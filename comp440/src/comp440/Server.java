package comp440;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class Server {
    public Server() throws RemoteException{
        super();
    }
    public static void main(String [] args) throws RemoteException, SQLException, ClassNotFoundException {
        Registry reg = LocateRegistry.createRegistry(101);
        jdbcImpl ji = new jdbcImpl();
        reg.rebind("db",(Remote) ji);
        System.out.println("Server is running _________________________________________________________");
    }
}
