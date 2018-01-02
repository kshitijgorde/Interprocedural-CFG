import java.rmi.RemoteException;
import java.rmi.Remote;

// 
// Decompiled by Procyon v0.5.30
// 

public interface AddressBookServer extends Remote
{
    void add(final String p0, final String p1, final String p2, final String p3) throws RemoteException;
    
    void remove(final String p0, final String p1, final String p2, final String p3) throws RemoteException;
    
    String[] getAddresses(final String p0) throws RemoteException;
}
