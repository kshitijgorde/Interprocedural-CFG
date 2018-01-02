import netscape.javascript.JSObject;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DK
{
    public static void js(final Applet applet, final String s) {
        JSObject.getWindow(applet).eval(s);
    }
}
