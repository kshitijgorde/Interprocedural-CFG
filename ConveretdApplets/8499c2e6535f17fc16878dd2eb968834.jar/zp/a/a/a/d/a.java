// 
// Decompiled by Procyon v0.5.30
// 

package zp.a.a.a.d;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.awt.Toolkit;

public final class a
{
    private static a char;
    public static final Toolkit for;
    public static final boolean int;
    public static final boolean case;
    public static final int try = 20;
    public static final int a = 5;
    public static final boolean if;
    public static final boolean new;
    public static final boolean byte;
    public static final boolean do;
    
    public static InputStream a(final String s) {
        return zp.a.a.a.d.a.char.getClass().getResourceAsStream(s);
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
    
    static {
        zp.a.a.a.d.a.char = new a();
        for = Toolkit.getDefaultToolkit();
        int = System.getProperty("java.version").startsWith("1.1.5");
        case = System.getProperty("java.version").startsWith("1.0");
        if = System.getProperty("os.name").startsWith("Mac");
        new = System.getProperty("java.vendor").startsWith("Apple");
        byte = System.getProperty("java.vendor").startsWith("Microsoft");
        do = (zp.a.a.a.d.a.byte && !zp.a.a.a.d.a.case);
    }
}
