// 
// Decompiled by Procyon v0.5.30
// 

public final class Debug
{
    private static boolean _debug;
    
    public Debug() {
        if (!Debug._debug) {
            final String strDebug = System.getProperty("debug", "true");
            Debug._debug = strDebug.equalsIgnoreCase("true");
        }
        out("Debugging is " + (Debug._debug ? "on." : "off."));
    }
    
    public Debug(final boolean debug) {
        Debug._debug = debug;
        out("Debugging is " + (Debug._debug ? "on." : "off."));
    }
    
    public Debug(final String x) {
        final boolean debug = Debug._debug;
        Debug._debug = true;
        out(x);
        Debug._debug = debug;
    }
    
    public static boolean debug() {
        return Debug._debug;
    }
    
    public static void out(final String x) {
        if (!Debug._debug) {
            return;
        }
        System.out.println(x.trim());
    }
    
    public static void err(final String x) {
        System.err.println(x.trim());
    }
    
    static {
        Debug._debug = false;
    }
}
