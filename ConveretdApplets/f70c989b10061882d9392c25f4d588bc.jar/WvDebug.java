import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class WvDebug
{
    private static boolean flag;
    
    public static void println(final Exception exception) {
        if (WvDebug.flag) {
            System.out.println(String.valueOf(getTimeString()) + " " + exception.toString());
        }
    }
    
    public static void set(final boolean flag1) {
        WvDebug.flag = flag1;
    }
    
    public static void set(final Applet applet) {
        final String s = applet.getParameter("debug");
        if (s != null && s.equalsIgnoreCase("on")) {
            WvDebug.flag = true;
            return;
        }
        WvDebug.flag = false;
    }
    
    private static synchronized String getTimeString() {
        String s = null;
        try {
            s = new Date().toString().substring(4, 20);
        }
        catch (Exception _ex) {
            s = "??? ?? ??:??:?? ";
        }
        return s;
    }
    
    public static void println(final String s) {
        if (WvDebug.flag) {
            System.out.println(String.valueOf(getTimeString()) + " " + s);
        }
    }
}
