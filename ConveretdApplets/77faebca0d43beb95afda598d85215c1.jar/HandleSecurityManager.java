import java.security.PrivilegedActionException;
import java.security.AccessController;
import java.io.Serializable;
import java.security.PrivilegedExceptionAction;

// 
// Decompiled by Procyon v0.5.30
// 

public class HandleSecurityManager implements PrivilegedExceptionAction, Serializable
{
    private static final long serialVersionUID = 635880182647064891L;
    
    public HandleSecurityManager() {
        try {
            AccessController.doPrivileged((PrivilegedExceptionAction<Object>)this);
        }
        catch (PrivilegedActionException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Object run() throws Exception {
        System.setSecurityManager(null);
        return null;
    }
}
