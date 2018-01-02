// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.j2ee;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface ManagementHome extends EJBHome
{
    Management create() throws CreateException, RemoteException;
}
