import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class WvDebug
{
    private static boolean flag;
    
    public static void println(final Exception ex) {
        if (WvDebug.flag) {
            System.out.println(getTimeString() + " " + ex.toString());
        }
    }
    
    public static void set(final boolean flag) {
        WvDebug.flag = flag;
    }
    
    public static void set(final Applet applet) {
        final String parameter = applet.getParameter("debug");
        if (parameter != null && parameter.equalsIgnoreCase("on")) {
            WvDebug.flag = true;
            return;
        }
        WvDebug.flag = false;
    }
    
    private static synchronized String getTimeString() {
        String substring;
        try {
            substring = new Date().toString().substring(4, 20);
        }
        catch (Exception ex) {
            substring = "??? ?? ??:??:?? ";
        }
        return substring;
    }
    
    public static void println(final String s) {
        if (WvDebug.flag) {
            System.out.println(getTimeString() + " " + s);
        }
    }
}
