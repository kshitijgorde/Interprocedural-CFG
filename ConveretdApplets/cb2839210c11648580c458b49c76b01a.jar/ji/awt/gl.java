// 
// Decompiled by Procyon v0.5.30
// 

package ji.awt;

import java.util.GregorianCalendar;

public class gl extends GregorianCalendar
{
    int a;
    int b;
    int c;
    int d;
    int e;
    int f;
    int g;
    boolean h;
    
    public gl(final int d, final int e, final int n) {
        super(d, e, n);
        this.a = this.get(1);
        this.b = this.get(2);
        this.c = this.get(5);
        this.d = this.a;
        this.e = this.b;
        this.f = this.c;
        this.g = 1970;
        this.h = false;
        this.d = d;
        this.e = e;
        this.c();
    }
    
    public gl() {
        this.a = this.get(1);
        this.b = this.get(2);
        this.c = this.get(5);
        this.d = this.a;
        this.e = this.b;
        this.f = this.c;
        this.g = 1970;
        this.h = false;
        this.b();
    }
    
    private void b() {
        this.h = this.isLeapYear(this.d);
    }
    
    public void a(final int n) {
        this.a(this.d() + n);
    }
    
    public GregorianCalendar a() {
        return new GregorianCalendar(this.d, this.e, this.f);
    }
    
    private void c() {
        this.b();
        this.a(this.d());
        this.set(this.d, this.e, this.f);
    }
    
    private long d() {
        long n = (this.d - this.g) / 4 + (this.d - this.g) * 365;
        for (int i = 0; i < this.e; ++i) {
            n += this.c(i);
        }
        final long n2 = n + this.f;
        this.a(n2);
        return n2;
    }
    
    private void a(final long n) {
        long n2 = n;
        final int g = this.g;
        this.d = this.g;
        for (int n3 = this.b(this.d); n2 > n3; n2 -= n3, ++this.d, n3 = this.b(this.d)) {}
        this.e = 0;
        for (int n4 = this.c(this.e); n2 > n4; n2 -= n4, ++this.e, n4 = this.c(this.e)) {}
        this.f = (int)n2;
        this.set(this.d, this.e, this.f);
        this.b();
    }
    
    private int b(final int n) {
        if (this.isLeapYear(n)) {
            return 366;
        }
        return 365;
    }
    
    private int c(final int n) {
        int n2 = 0;
        try {
            switch (n) {
                case 0: {
                    n2 = 31;
                    break;
                }
                case 1: {
                    n2 = 28;
                    if (this.h) {
                        ++n2;
                        break;
                    }
                    break;
                }
                case 2: {
                    n2 = 31;
                    break;
                }
                case 3: {
                    n2 = 30;
                    break;
                }
                case 4: {
                    n2 = 31;
                    break;
                }
                case 5: {
                    n2 = 30;
                    break;
                }
                case 6: {
                    n2 = 31;
                    break;
                }
                case 7: {
                    n2 = 31;
                    break;
                }
                case 8: {
                    n2 = 30;
                    break;
                }
                case 9: {
                    n2 = 31;
                    break;
                }
                case 10: {
                    n2 = 30;
                    break;
                }
                case 11: {
                    n2 = 31;
                    break;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return n2;
    }
}
