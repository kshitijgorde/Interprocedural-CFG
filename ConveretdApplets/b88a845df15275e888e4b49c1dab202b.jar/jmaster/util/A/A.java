// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.A;

import java.applet.Applet;
import netscape.javascript.JSObject;
import javax.swing.JApplet;

public class A extends JApplet
{
    private static final long B = -1228348390281469532L;
    private static final String A = "javaInfo";
    
    public void init() {
        super.init();
        try {
            JSObject.getWindow((Applet)this).call("javaInfo", new Object[] { System.getProperty("java.version"), System.getProperty("java.vendor") });
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
