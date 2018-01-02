// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.util.Vector;

public class u
{
    private DataSource new;
    private int if;
    private int int;
    private float a;
    private int do;
    private Vector for;
    
    public u(final DataSource new1, final int if1, final int int1, final float a, final int do1) {
        this.new = new1;
        this.if = if1;
        this.int = int1;
        this.a = a;
        this.do = do1;
        this.for = new Vector(int1 - if1 + 1);
    }
    
    public synchronized void try() {
        if (this.new.bX.case() == 0) {
            return;
        }
        this.for.removeAllElements();
        r r = new r(this);
        this.for.addElement(r);
        for (int i = this.a() + 1; i <= this.int(); ++i) {
            final r a = r.a(this, i);
            if (a != r) {
                this.for.addElement(a);
                r = a;
            }
        }
    }
    
    public float new() {
        return this.a;
    }
    
    public void a(final float a) {
        this.a = a;
        if (this.for.size() > 0) {
            this.try();
        }
    }
    
    public Vector if() {
        return this.for;
    }
    
    public int int() {
        return this.int;
    }
    
    public int do() {
        return this.do;
    }
    
    public void a(final int do1) {
        this.do = do1;
        if (this.for.size() > 0) {
            this.try();
        }
    }
    
    public DataSource for() {
        return this.new;
    }
    
    public int a() {
        return this.if;
    }
}
