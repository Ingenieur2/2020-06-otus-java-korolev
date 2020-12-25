package ru.package01;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EchoInterface extends Remote {

    String echo(String data) throws RemoteException, MalformedURLException, NotBoundException, InterruptedException;
}
