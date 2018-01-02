import java.applet.Applet;
import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

public class C12
{
    static final long h;
    static final long j;
    
    static {
        j = new Date(99, 5, 20).getTime();
        h = new Date(99, 5, 15).getTime();
        if (C12.h > C12.j) {
            System.out.println("Warning #40044");
        }
    }
    
    public static boolean a() {
        return false;
    }
    
    public static boolean c() {
        return false;
    }
    
    public static boolean f() {
        return false;
    }
    
    public static boolean g(final String s, final Applet applet) {
        System.out.println("HOST: " + s);
        final String parameter = applet.getParameter("key");
        if (parameter == null || parameter.equals("")) {
            System.out.println("NO KEY");
            return false;
        }
        final String parameter2 = applet.getParameter("act_expires");
        final String parameter3 = applet.getParameter("host");
        final String string = parameter2 + "|" + parameter3.toLowerCase();
        if (!new C21().a(string, parameter)) {
            System.out.println("Err $3444 " + string);
            return false;
        }
        try {
            final long long1 = Long.parseLong(parameter2);
            System.out.println("EXPIRES: " + new Date(long1));
            if (long1 < System.currentTimeMillis()) {
                System.out.println("Err expire ");
                return false;
            }
            if (s.toLowerCase().equals(parameter3) || s.toLowerCase().indexOf(parameter3.toLowerCase()) != -1) {
                return true;
            }
            System.out.println("Error, Host Not Valid (" + s + "!=" + parameter3 + ")");
        }
        catch (Throwable t) {
            System.out.println("Err $34343 " + t);
            return false;
        }
        return false;
    }
}
