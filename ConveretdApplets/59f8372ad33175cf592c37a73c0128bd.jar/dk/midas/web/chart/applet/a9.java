// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.util.Vector;

public class a9
{
    private Vector if;
    private DataSource a;
    
    public a9(final DataSource a) {
        this.if = new Vector();
        this.a = a;
    }
    
    public synchronized void a(final aq aq) {
        if (this.if.indexOf(aq) < 0) {
            this.if.addElement(aq);
        }
    }
    
    public synchronized void if(final aq aq) {
        if (this.if.indexOf(aq) >= 0) {
            this.if.removeElement(aq);
        }
    }
    
    public void a(final int n) {
        this.a(new a2(this.a, n));
    }
    
    public void a(final int n, final boolean b, final boolean b2) {
        this.a(new a2(this.a, n, b, b2));
    }
    
    public void a(final String s, final Object o, final Object o2) {
        this.a(new a2(this.a, s, o, o2));
    }
    
    public void a(final a2 a2) {
        final aq[] a3 = this.a();
        for (int i = 0; i < a3.length; ++i) {
            a3[i].dataSourceChanged(a2);
        }
    }
    
    protected synchronized aq[] a() {
        final aq[] array = new aq[this.if.size()];
        this.if.copyInto(array);
        return array;
    }
}
