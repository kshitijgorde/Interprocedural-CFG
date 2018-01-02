// 
// Decompiled by Procyon v0.5.30
// 

package ABLwidgets;

import java.util.Hashtable;
import java.util.Vector;

public class abljem
{
    public static char a;
    public static char b;
    public static String c;
    private static Vector d;
    private static boolean e;
    private static Hashtable f;
    
    public static void a(String replace) {
        replace = replace.replace('\0', ' ');
        System.out.print(replace);
    }
    
    public static void b(final String s) {
        a(String.valueOf(s) + "\n");
    }
    
    static {
        abljem.a = '+';
        abljem.b = '-';
        abljem.c = "runid=";
        abljem.d = new Vector();
        abljem.e = true;
        abljem.f = new Hashtable();
    }
}
