import java.rmi.RemoteException;
import java.rmi.Remote;

// 
// Decompiled by Procyon v0.5.30
// 

public interface BrokerServer extends Remote
{
    String authenticatePop(final String p0, final String p1) throws RemoteException;
    
    String authenticatePop(final String p0, final String p1, final String p2) throws RemoteException;
    
    String authenticateImap(final String p0, final String p1) throws RemoteException;
    
    String authenticateImap(final String p0, final String p1, final String p2) throws RemoteException;
    
    String getPopServer(final String p0, final String p1, final String p2) throws RemoteException;
    
    String getPopServer(final String p0, final String p1) throws RemoteException;
    
    String getImapServer(final String p0, final String p1, final String p2) throws RemoteException;
    
    String getImapServer(final String p0, final String p1) throws RemoteException;
    
    void removeServer(final String p0) throws RemoteException;
    
    int getNumberOfMessagesPop(final String p0, final String p1) throws RemoteException;
    
    int getNumberOfMessagesPop(final String p0, final String p1, final String p2) throws RemoteException;
    
    int getNumberOfMessagesImap(final String p0, final String p1) throws RemoteException;
    
    int getNumberOfMessagesImap(final String p0, final String p1, final String p2) throws RemoteException;
    
    int getNumberOfNewMessages(final String p0, final String p1) throws RemoteException;
    
    boolean confirm() throws RemoteException;
}
