package ru.package01;

import ru.package01.db.DbService;
import ru.package01.db.DbServiceImpl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class MessageServer extends UnicastRemoteObject implements EchoInterface {

    private static final int SERVER_PORT = 8091;
    private static final int REGISTRY_PORT = 1100;

    private static final int SERVER_PORT_FOR_DBSERVER_002 = 8093;
    private static final int REGISTRY_PORT_FOR_DBSERVER_002 = 1102;


    private MessageServer(int port) throws RemoteException {
        super(port);
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(REGISTRY_PORT);
            Naming.rebind("//localhost/MessageServer", new MessageServer(SERVER_PORT));
            System.out.println("waiting for Frontend connection...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            LocateRegistry.createRegistry(SERVER_PORT_FOR_DBSERVER_002);
            Naming.rebind("//localhost/MessageServer002", new MessageServer(REGISTRY_PORT_FOR_DBSERVER_002));
            System.out.println("waiting for Frontend connection 002...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String echo(String data) throws RemoteException, MalformedURLException, NotBoundException, InterruptedException {

        EchoInterface echoInterface = (EchoInterface) Naming.lookup("//localhost/DbServer");
        var dataFromServer = echoInterface.echo(data);
        System.out.println(String.format("response from the DbServer: %s", dataFromServer));  //  -- this info going from DataBase (all users)

        MessageAppl messageAppl = new MessageAppl(dataFromServer);
        DbService dbService = new DbServiceImpl(dataFromServer);

        EchoInterface echoInterface002 = (EchoInterface) Naming.lookup("//localhost/DbServer002");
        var dataFromServer002 = echoInterface002.echo(data);
        System.out.println(String.format("response from the DbServer 002: %s", dataFromServer));  //  -- this info going from DataBase (all users)

        MessageAppl messageAppl002 = new MessageAppl(dataFromServer002);
        DbService dbService002 = new DbServiceImpl(dataFromServer002);

        System.out.println(String.format("data from Frontend (client) (1): %s", data));
        return  /*"echo from MessageServer :" +*/ dataFromServer; // -- this info is going to controller
    }
}
