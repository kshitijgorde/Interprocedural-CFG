// 
// Decompiled by Procyon v0.5.30
// 

package pa.a.a.a.c;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.awt.Toolkit;

public final class a
{
    private static a if;
    public static final Toolkit int;
    public static final boolean byte;
    public static final boolean a;
    public static final int new = 20;
    public static final int do = 5;
    public static final boolean case;
    public static final boolean char;
    public static final boolean for;
    public static final boolean try;
    
    static {
        pa.a.a.a.c.a.if = new a();
        int = Toolkit.getDefaultToolkit();
        byte = System.getProperty("java.version").startsWith("1.1.5");
        a = System.getProperty("java.version").startsWith("1.0");
        case = System.getProperty("os.name").startsWith("Mac");
        char = System.getProperty("java.vendor").startsWith("Apple");
        for = System.getProperty("java.vendor").startsWith("Microsoft");
        try = (pa.a.a.a.c.a.for && !pa.a.a.a.c.a.a);
    }
    
    public static String a(final InputStream inputStream) {
        String line = " OO ";
        try {
            line = new BufferedReader(new InputStreamReader(inputStream)).readLine();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
        return line.substring(9);
    }
    
    public static InputStream a(final String s) {
        return pa.a.a.a.c.a.if.getClass().getResourceAsStream(s);
    }
}
