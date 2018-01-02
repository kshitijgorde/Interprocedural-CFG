// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.Vector;

public final class H
{
    private Vector a;
    private int b;
    private int c;
    private boolean d;
    
    public H() {
        this.a = new Vector(200);
        this.b = -1;
        this.c = 0;
        this.d = false;
    }
    
    public final synchronized void a(final Object o) {
        this.a.addElement(o);
        this.notifyAll();
    }
    
    public final synchronized Vector a() {
        while (this.c >= this.a.size() && !this.d) {
            this.wait();
        }
        if (this.d && this.c >= this.a.size()) {
            return null;
        }
        Vector a;
        if (this.b == -1) {
            a = this.a;
            this.a = new Vector(200);
        }
        else {
            a = new Vector<Object>(this.a.size() - this.c);
            for (int i = this.c; i < this.a.size(); ++i) {
                a.addElement(this.a.elementAt(i));
            }
            this.c += a.size();
        }
        return a;
    }
    
    public final synchronized void b() {
        if (this.b == -1) {
            this.b = 0;
            return;
        }
        for (int i = 0; i < this.c; ++i) {
            this.a.removeElementAt(0);
        }
        this.c = 0;
        this.b = 0;
    }
    
    public final synchronized void c() {
        this.c = 0;
        this.notifyAll();
    }
    
    public final synchronized void d() {
        this.d = true;
        this.notifyAll();
    }
}
