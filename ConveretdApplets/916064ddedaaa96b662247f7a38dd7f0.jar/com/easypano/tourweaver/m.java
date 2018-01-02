// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver;

import com.easypano.tourweaver.a.e;
import com.easypano.tourweaver.e.a;
import com.easypano.tourweaver.e.b;
import com.easypano.tourweaver.b.ab;
import java.util.Vector;
import com.easypano.tourweaver.b.q;

public class m implements Runnable
{
    n a;
    q b;
    Vector c;
    Vector d;
    Thread e;
    double[] f;
    int g;
    h h;
    ab i;
    boolean j;
    
    public m() {
        this.a = null;
        this.b = null;
        this.c = new Vector();
        this.d = new Vector();
        this.f = new double[100];
        this.g = -1;
        this.j = true;
    }
    
    public void a() {
        this.e.interrupt();
        this.e = null;
        this.c.removeAllElements();
        this.d.removeAllElements();
    }
    
    public m(final n a) {
        this.a = null;
        this.b = null;
        this.c = new Vector();
        this.d = new Vector();
        this.f = new double[100];
        this.g = -1;
        this.j = true;
        this.a = a;
        (this.e = new Thread(this)).setPriority(6);
        this.e.start();
    }
    
    public void a(final double n) {
        ++this.g;
        m m = this;
        if (!com.easypano.tourweaver.g.v) {
            if (this.g >= 100) {
                this.g = 99;
            }
            m = this;
        }
        m.f[this.g] = n;
    }
    
    private double b() {
        m m = this;
        if (!com.easypano.tourweaver.g.v) {
            if (this.g == -1) {
                return -1.0;
            }
            m = this;
        }
        final double n = m.f[this.g];
        this.g = -1;
        return n;
    }
    
    public void a(final q b) {
        this.b = b;
    }
    
    public void a(final h h) {
        this.h = h;
    }
    
    public void a(final boolean j) {
        this.j = j;
    }
    
    public void a(final b b) {
        if (b instanceof a) {
            this.d.addElement(b);
            if (!com.easypano.tourweaver.g.v) {
                return;
            }
        }
        this.c.addElement(b);
    }
    
    private void c() {
        final boolean v = com.easypano.tourweaver.g.v;
        final boolean empty = this.d.isEmpty();
        while (true) {
            Label_0058: {
                if (v) {
                    break Label_0058;
                }
                if (!empty) {
                    this.d.elementAt(this.d.size() - 1).a();
                    this.d.removeAllElements();
                }
                this.c.isEmpty();
            }
            if (!empty) {
                final b b = this.c.elementAt(0);
                this.c.removeElementAt(0);
                b.a();
                if (!v) {
                    continue;
                }
            }
            break;
        }
    }
    
    public void run() {
        final boolean v = com.easypano.tourweaver.g.v;
        long n = 0L;
        while (this.e != null && !Thread.interrupted()) {
            final long currentTimeMillis = System.currentTimeMillis();
            this.c();
            this.a.play();
            m m = this;
            if (!v) {
                if (this.h != null) {
                    this.h.repaint();
                }
                n = System.currentTimeMillis() - currentTimeMillis;
                m = this;
            }
            final ab i = m.i;
            m j = null;
            Label_0129: {
                if (!v) {
                    m k = null;
                    Label_0106: {
                        if (i == null) {
                            j = this;
                            k = this;
                            if (v) {
                                break Label_0106;
                            }
                            if (this.h != null) {
                                this.i = this.h.getMovieController();
                            }
                        }
                        j = this;
                        k = this;
                    }
                    if (v) {
                        break Label_0129;
                    }
                    final ab l = k.i;
                }
                if (i != null) {
                    this.i.a(this.b());
                }
                j = this;
            }
            final q b = j.b;
            Label_0147: {
                if (!v) {
                    if (b == null) {
                        break Label_0147;
                    }
                    final q b2 = this.b;
                }
                b.play();
            }
            final long n2 = n;
            if (!v && n2 < 10L) {
                com.easypano.tourweaver.a.e.a(10L - n);
                if (v) {
                    goto Label_0175;
                }
                continue;
            }
            else {
                com.easypano.tourweaver.a.e.a(n2);
                if (v) {
                    break;
                }
                continue;
            }
        }
    }
}
