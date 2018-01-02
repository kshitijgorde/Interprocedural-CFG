// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;

public class q
{
    public double a;
    public double b;
    public boolean c;
    public Color d;
    public String e;
    public boolean f;
    public String g;
    public boolean h;
    public double i;
    public double j;
    
    public q(final double a, final double b, final boolean c, final Color d, final String e) {
        this.c = false;
        this.f = false;
        this.g = "";
        this.h = false;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public q(final String g, final Color d, final String e) {
        this.c = false;
        this.f = false;
        this.g = "";
        this.h = false;
        this.g = g;
        this.f = true;
        this.c = true;
        this.d = d;
        this.e = e;
    }
    
    public double a() {
        return this.a;
    }
    
    public double b() {
        return this.b;
    }
    
    public boolean a(final double n, final double n2) {
        return this.c && this.a >= n && this.a <= n2;
    }
    
    public boolean b(final double n, final double n2) {
        return !this.c && ((this.a >= n && this.a <= n2) || (this.b >= n && this.b <= n2));
    }
    
    public double[] c(final double n, final double n2) {
        final double[] array = { this.a, this.b };
        if (this.a >= n && this.a <= n2 && (this.b < n || this.b > n2)) {
            if (this.b < n) {
                array[1] = n;
            }
            else {
                array[1] = n2;
            }
        }
        else if ((this.a < n || this.a > n2) && this.b >= n && this.b <= n2) {
            if (this.a < n) {
                array[0] = n;
            }
            else {
                array[0] = n2;
            }
        }
        return array;
    }
}
