// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet.a;

import java.io.PrintStream;
import java.util.Vector;

public class b
{
    private String if;
    private Vector a;
    
    public b(final String if1) {
        this.a = new Vector();
        this.if = if1;
        this.a("start");
    }
    
    public void a(final String s) {
        this.a.addElement(new a(s));
    }
    
    public void a() {
        this.a(System.out);
    }
    
    public void a(final PrintStream printStream) {
        this.a("end");
        a a2;
        final a a = a2 = this.a.elementAt(0);
        for (int i = 1; i < this.a.size(); ++i) {
            final a a3 = this.a.elementAt(i);
            printStream.println(this.if + ": " + a2.a + " -> " + a3.a + " = " + (a3.if - a2.if));
            a2 = a3;
        }
        if (this.a.size() > 2) {
            printStream.println(this.if + ": total = " + (a2.if - a.if));
        }
    }
    
    private static class a
    {
        public String a;
        public long if;
        
        public a(final String a) {
            this.a = a;
            this.if = System.currentTimeMillis();
        }
    }
}
