// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Vector;
import java.util.Random;

class g
{
    private j[] a;
    private Random c;
    private Vector b;
    
    g() {
        this.c = new Random();
        this.b = new Vector();
        this.a = new j[] { new s(), new u(), new t() };
    }
    
    public void a(final Graphics graphics, final int n, final int n2, final ImageObserver imageObserver) {
        graphics.clearRect(0, 0, n, n2);
        if ((this.b.size() == 0 || this.b.lastElement().b > 20) && this.c.nextInt(100) < 5) {
            final c c = new c();
            final j a = this.a();
            final int a2 = this.a(0 - a.a + 30, n2 + a.a - 30);
            final int b = 0 - a.b + 1;
            c.c = a2;
            c.b = b;
            c.a = a;
            this.b.addElement(c);
        }
        for (int i = 0; i < this.b.size(); ++i) {
            final c c3;
            final c c2 = c3 = this.b.elementAt(i);
            ++c3.b;
            graphics.drawImage(c2.a.a(), c2.c, c2.b, imageObserver);
        }
        if (this.b.size() > 0 && this.b.firstElement().b > n2) {
            this.b.remove(0);
        }
    }
    
    private j a() {
        return this.a[this.c.nextInt(this.a.length)];
    }
    
    private int a(final int n, final int n2) {
        return this.c.nextInt(n2 - n) + n;
    }
}
