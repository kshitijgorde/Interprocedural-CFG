// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.c;

public class d
{
    public static String a;
    private static String b;
    private static Class c;
    
    public String toString() {
        return "Version " + d.b + "\n" + d.b + "\n";
    }
    
    private static Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        d.a = ((d.c == null) ? (d.c = a("com.bullionvault.chart.c.d")) : d.c).getPackage().getImplementationVersion();
        d.b = "$Id: Version.java 198 2009-04-08 16:45:14Z thomas $";
    }
}
