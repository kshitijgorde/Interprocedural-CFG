import java.applet.Applet;
import netscape.javascript.JSObject;
import sexy.gui.SexyApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class o implements Runnable
{
    public SexyApplet a;
    public String b;
    public Object[] c;
    
    public o(final SexyApplet a, final String b, final Object[] c) {
        this.a = a;
        this.b = b;
        this.c = c;
        new Thread(this).start();
    }
    
    public void run() {
        try {
            if (this.a.u == null) {
                this.a.u = JSObject.getWindow((Applet)this.a);
            }
            ((JSObject)this.a.u).call(this.b, this.c);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
