import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class e
{
    public static String a(final Applet applet, final String s, final String s2) {
        final String parameter = applet.getParameter(s);
        if (parameter == null) {
            return s2;
        }
        return parameter;
    }
    
    public static int a(final Applet applet, final String s, final int n) {
        final String parameter = applet.getParameter(s);
        int int1;
        try {
            int1 = Integer.parseInt(parameter, 10);
        }
        catch (NumberFormatException ex) {
            int1 = n;
        }
        return int1;
    }
    
    public static long a(final Applet applet, final String s, final long n) {
        final String parameter = applet.getParameter(s);
        long long1;
        try {
            long1 = Long.parseLong(parameter, 10);
        }
        catch (NumberFormatException ex) {
            long1 = n;
        }
        return long1;
    }
    
    public static double a(final Applet applet, final String s, final double n) {
        final String parameter = applet.getParameter(s);
        double doubleValue;
        try {
            doubleValue = Double.valueOf(parameter);
        }
        catch (Exception ex) {
            doubleValue = n;
        }
        return doubleValue;
    }
    
    public static boolean a(final Applet applet, final String s, final boolean b) {
        final String parameter = applet.getParameter(s);
        int int1;
        try {
            int1 = Integer.parseInt(parameter, 10);
        }
        catch (Exception ex) {
            int1 = (b ? 1 : 0);
        }
        return int1 != 0;
    }
}
