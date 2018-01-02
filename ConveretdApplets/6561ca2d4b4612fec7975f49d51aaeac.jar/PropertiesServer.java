import java.rmi.RemoteException;
import java.rmi.Remote;

// 
// Decompiled by Procyon v0.5.30
// 

public interface PropertiesServer extends Remote
{
    void saveProps(final String p0, final String[] p1) throws RemoteException;
    
    void saveProps(final String p0, final String[] p1, final String p2) throws RemoteException;
    
    JMUserData getUserProps(final String p0) throws RemoteException;
}
