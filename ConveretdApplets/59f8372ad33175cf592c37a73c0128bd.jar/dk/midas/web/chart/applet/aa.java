// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.text.NumberFormat;

public class aa
{
    public int for;
    public int do;
    private NumberFormat if;
    private int a;
    private int int;
    
    public aa() {
        this.for = 4;
        (this.if = NumberFormat.getInstance()).setMinimumFractionDigits(this.for);
        this.if.setMaximumFractionDigits(this.for);
        final int for1 = this.for;
        this.int = for1;
        this.a = for1;
    }
    
    public String a(final float n) {
        return this.if.format(n);
    }
    
    public void a(final int a) {
        if (Math.abs(a) > 10) {
            return;
        }
        if ((this.do = a) == 0) {}
        this.if.setMinimumFractionDigits(Math.abs(a));
        this.if.setMaximumFractionDigits(Math.abs(a));
        this.int = a;
        this.a = a;
    }
    
    public void a(final int n, final int int1) {
        if (Math.abs(n) > 10) {
            return;
        }
        if ((this.do = n) == 0) {}
        this.if.setMinimumFractionDigits(Math.abs(n));
        this.if.setMaximumFractionDigits(Math.abs(int1));
        this.a = n;
        this.int = int1;
    }
    
    public void a() {
        this.if.setMinimumFractionDigits(this.for);
        this.if.setMaximumFractionDigits(this.for);
    }
    
    public int do() {
        return this.a;
    }
    
    public int if() {
        return this.int;
    }
}
