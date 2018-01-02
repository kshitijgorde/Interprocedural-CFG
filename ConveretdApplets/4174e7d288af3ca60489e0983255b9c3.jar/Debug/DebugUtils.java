// 
// Decompiled by Procyon v0.5.30
// 

package Debug;

import java.io.PrintStream;

public class DebugUtils
{
    public static boolean DEBUG_ON;
    private static PrintStream _out;
    
    public static void setPrintStream(final PrintStream newOutStream) {
        DebugUtils._out = newOutStream;
    }
    
    public static void print(final String message) {
        if (DebugUtils.DEBUG_ON) {
            DebugUtils._out.print(message);
        }
    }
    
    public static void println(final String message) {
        if (DebugUtils.DEBUG_ON) {
            DebugUtils._out.println(message);
        }
    }
    
    static {
        DebugUtils.DEBUG_ON = true;
        DebugUtils._out = System.out;
    }
}
