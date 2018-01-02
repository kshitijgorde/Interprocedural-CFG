// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient;

import java.io.IOException;
import java.util.Locale;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Dump
{
    static PrintWriter Out;
    static boolean Terminal;
    
    public static void open(final String s) {
        try {
            (Dump.Out = new PrintWriter(new FileOutputStream(s), true)).println("Locale: " + Locale.getDefault() + "\n");
        }
        catch (IOException ex) {
            Dump.Out = null;
        }
    }
    
    public static void println(final String s) {
        if (Dump.Out != null) {
            Dump.Out.println(s);
        }
        if (Dump.Terminal) {
            System.out.println(s);
        }
    }
    
    public static void print(final String s) {
        if (Dump.Out != null) {
            Dump.Out.print(s);
        }
        if (Dump.Terminal) {
            System.out.print(s);
        }
    }
    
    public static void close() {
        if (Dump.Out != null) {
            Dump.Out.close();
        }
    }
    
    public static void terminal(final boolean terminal) {
        Dump.Terminal = terminal;
    }
    
    static {
        Dump.Out = null;
    }
}
