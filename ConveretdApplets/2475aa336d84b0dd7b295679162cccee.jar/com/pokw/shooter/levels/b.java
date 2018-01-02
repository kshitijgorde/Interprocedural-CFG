// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.levels;

import java.util.Vector;

public class b
{
    private G[] c;
    private int[] b;
    private int f;
    private int a;
    private boolean e;
    private int d;
    
    public b() {
        this.f = 0;
        this.a = 0;
        this.e = false;
        this.d = 0;
        this.c = new G[90];
        this.b = new int[90];
        this.a();
    }
    
    public void a() {
        this.a(new t(), 100);
        this.a(new a(), 50);
        this.a(new p(), 100);
        this.a(new B(), 100);
        this.a(new A(), 50);
        this.a(new n(), 100);
        this.a(new h(), 20);
        this.a(new q(), 100);
        this.a(new q(), 100);
        this.a(new q(), 100);
        this.a(new o(), 20);
        this.a(new o(), 20);
        this.a(new u(), 20);
        this.a(new o(), 20);
        this.a(new o(), 50);
        this.a(new E(), 50);
        this.a(new f(), 80);
        this.a(new f(), 80);
        this.a(new y(), 80);
        this.a(new y(), 80);
        this.a(new e(), 40);
        this.a(new l(), 40);
        this.a(new B(), 80);
        this.a(new x(), 30);
        this.a(new x(), 30);
        this.a(new s(), 30);
        this.a(new r(), 0);
        this.a(new s(), 30);
        this.a(new d(), 30);
        this.a(new x(), 150);
        this.a(new I(), 85);
        this.a(new I(), 85);
        this.a(new q(), 60);
        this.a(new v(), 150);
        this.a(new z(), 150);
        this.a(new w(), 30);
        this.a(new m(), 45);
        this.a(new m(), 75);
        this.a(new q(), 50);
        this.a(new C(), 60);
        this.a(new k(), 85);
        this.a(new x(), 20);
        this.a(new s(), 20);
        this.a(new x(), 20);
        this.a(new s(), 95);
        this.a(new i(), 130);
        this.a(new F(), 180);
        this.a(new x(), 60);
        this.a(new x(), 60);
        this.a(new g(), 80);
        this.a(new f(), 50);
        this.a(new y(), 50);
        this.a(new f(), 50);
        this.a(new y(), 50);
        this.a(new f(), 200);
        this.a(new c(), 40);
        this.a(new x(), 10);
        this.a(new x(), 60);
        this.a(new s(), 10);
        this.a(new x(), 60);
        this.a(new m(), 35);
        this.a(new m(), 35);
        this.a(new I(), 120);
        this.a(new F(), 100);
        this.a(new y(), 70);
        this.a(new f(), 60);
        this.a(new C(), 60);
        this.a(new B(), 60);
        this.a(new s(), 20);
        this.a(new m(), 60);
        this.a(new m(), 20);
        this.a(new D(), 20);
        this.a(new s(), 20);
        this.a(new x(), 10);
        this.a(new x(), 10);
        this.a(new x(), 40);
        this.a(new g(), 60);
        this.a(new x(), 10);
        this.a(new x(), 10);
        this.a(new x(), 40);
        this.a(new k(), 60);
        this.a(new B(), 55);
        this.a(new l(), 55);
        this.a(new q(), 55);
        this.a(new C(), 80);
        this.a(new I(), 45);
        this.a(new m(), 20);
        this.a(new x(), 100);
        this.a(new q(), 40);
        this.a(new q(), 80);
        this.d = 0;
        this.f = 0;
    }
    
    private void a(final G g, final int n) {
        this.c[this.d] = g;
        this.b[this.d] = n;
        ++this.d;
    }
    
    public void a(final Vector vector) {
        if (this.e) {
            ++this.a;
            if (this.a >= this.b[this.f]) {
                this.e = false;
                this.a = 0;
                ++this.f;
                if (this.f >= this.c.length) {
                    this.a();
                }
            }
            return;
        }
        if (this.f < this.c.length && !this.c[this.f].a(vector)) {
            this.e = true;
        }
    }
}
