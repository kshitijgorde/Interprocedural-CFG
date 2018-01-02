// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet.b;

import dk.midas.web.chart.applet.d.c;
import java.util.Enumeration;
import java.util.Vector;

public class a implements b
{
    private int for;
    private long int;
    private String do;
    private static Vector if;
    
    private static synchronized Vector for() {
        return a.if;
    }
    
    public static synchronized a a(final long n) {
        final Enumeration<a> elements = for().elements();
        while (elements.hasMoreElements()) {
            final a a = elements.nextElement();
            if (n == a.int()) {
                return a;
            }
        }
        final a a2 = new a(n);
        a.if.addElement(a2);
        return a2;
    }
    
    public static synchronized a a(final int n) {
        final Enumeration<a> elements = for().elements();
        while (elements.hasMoreElements()) {
            final a a = elements.nextElement();
            if (n == a.a()) {
                return a;
            }
        }
        return null;
    }
    
    public static synchronized void a(final int n, final long n2) {
        a a = a(n2);
        if (a == null) {
            a = new a(n2);
            dk.midas.web.chart.applet.b.a.if.addElement(a);
        }
        a.if(n);
    }
    
    public static synchronized void a(final int n, final long n2, final String do1) {
        a a = a(n2);
        if (a == null) {
            a = new a(n2, do1);
            dk.midas.web.chart.applet.b.a.if.addElement(a);
        }
        else {
            a.do = do1;
        }
        a.if(n);
    }
    
    public static a a(final c c) {
        return a((long)c.a("TimeScale", 60));
    }
    
    private a(final long n) {
        this(n, if(n));
    }
    
    private a(final long int1, final String do1) {
        this.for = -1;
        this.int = int1;
        this.do = do1;
    }
    
    public long int() {
        return this.int;
    }
    
    public long if() {
        return this.int * 60L;
    }
    
    public int a() {
        return this.for;
    }
    
    public void if(final int for1) {
        this.for = for1;
    }
    
    public String toString() {
        return this.do;
    }
    
    public boolean equals(final Object o) {
        return o instanceof a && this.int() == ((a)o).int();
    }
    
    public int hashCode() {
        return this.do.hashCode();
    }
    
    public boolean do() {
        return this.int == 0L;
    }
    
    static String if(final long n) {
        String s = "" + n + " Min ";
        final long[] array = { 60L, 1440L, 10080L };
        final String[] array2 = { "Hours", "Days", "Weeks" };
        final String[] array3 = { "Hour", "Daily", "Weekly" };
        for (int i = array.length - 1; i >= 0; --i) {
            if (n > array[i] && n % array[i] == 0L) {
                s = "" + n / array[i] + " " + array2[i] + " ";
                break;
            }
            if (n == array[i]) {
                s = (array3[i].endsWith("ly") ? (array3[i] + " ") : ("1 " + array3[i] + " "));
                break;
            }
        }
        return s;
    }
    
    static {
        (a.if = new Vector(16, 4)).addElement(new a(0L, "Ticks"));
        a.if.addElement(new a(1L, "1 Min "));
        a.if.addElement(new a(5L, "5 Min "));
        a.if.addElement(new a(10L, "10 Min "));
        a.if.addElement(new a(15L, "15 Min "));
        a.if.addElement(new a(30L, "30 Min "));
        a.if.addElement(new a(60L, "1 Hour "));
        a.if.addElement(new a(120L, "2 Hours "));
        a.if.addElement(new a(240L, "4 Hours "));
        a.if.addElement(new a(360L, "6 Hours "));
        a.if.addElement(new a(480L, "8 Hours "));
        a.if.addElement(new a(1440L, "Daily "));
        a.if.addElement(new a(10080L, "Weekly "));
        a.if.addElement(new a(43200L, "Monthly "));
    }
}
