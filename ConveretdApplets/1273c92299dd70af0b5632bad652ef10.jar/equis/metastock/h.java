// 
// Decompiled by Procyon v0.5.30
// 

package equis.metastock;

import java.awt.Color;

public class h
{
    public int a;
    public int b;
    public double[] c;
    public double d;
    public double e;
    public int f;
    public int g;
    public Color h;
    
    h(final int g) {
        this.c = new double[g];
        this.g = g;
        this.a();
    }
    
    public void a() {
        for (int i = 0; i < this.g; ++i) {
            this.c[i] = 0.0;
        }
        this.a = -1;
        this.b = -1;
        this.d = -1.0;
        this.e = 1.0;
        this.f = 0;
        this.h = MS4Java.w;
    }
    
    public int b() {
        return this.b - this.a + 1;
    }
    
    public void a(final int n, final int n2) {
        if (this.a < 0) {
            this.e = 0.0;
            this.d = 0.0;
            return;
        }
        this.d = 1.0E9;
        this.e = -1.0E9;
        for (int i = Math.max(this.a, n); i <= n2; ++i) {
            if (this.c[i] > this.e) {
                this.e = this.c[i];
            }
            if (this.c[i] < this.d) {
                this.d = this.c[i];
            }
        }
        double e = this.e - this.d;
        if (e == 0.0) {
            e = this.e;
        }
        this.e += e * 0.05;
        this.d -= e * 0.05;
    }
}
