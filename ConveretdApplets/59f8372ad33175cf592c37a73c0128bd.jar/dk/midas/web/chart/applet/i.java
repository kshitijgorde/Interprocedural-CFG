// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Graphics;
import java.util.Vector;

public class i
{
    static final int if = 10;
    Vector a;
    
    i() {
        this.a = new Vector(10, 5);
    }
    
    int a(final aj aj) {
        int n = 0;
        if (this.a.size() >= 10) {
            this.a.removeElementAt(0);
            n = -1;
        }
        this.a.addElement(aj);
        return n;
    }
    
    void a(final int n) {
        this.a.removeElementAt(n);
    }
    
    void a() {
        this.a.removeAllElements();
    }
    
    void do(final int n) {
        for (int i = this.a.size() - 1; i >= 0; --i) {
            if (((aj)this.a.elementAt(i)).c() == n) {
                this.a.removeElementAt(i);
            }
        }
    }
    
    int a(final int n, final int n2) {
        int n3 = -1;
        float n4 = 1000000.0f;
        for (int i = 0; i < this.a.size(); ++i) {
            final float a = this.if(i).a(n, n2);
            if (n4 > a) {
                n3 = i;
                n4 = a;
            }
        }
        return n3;
    }
    
    aj if(final int n) {
        return this.a.elementAt(n);
    }
    
    void a(final be be) {
        for (int i = 0; i < this.a.size(); ++i) {
            this.if(i).a(be);
        }
    }
    
    void a(final Graphics graphics) {
        for (int i = 0; i < this.a.size(); ++i) {
            this.if(i).a(graphics, 0, 0);
        }
    }
}
