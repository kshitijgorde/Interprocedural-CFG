// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.io.DataInputStream;

public final class d implements db
{
    private aj a;
    private eh a;
    
    public d() {
    }
    
    public static String a(final long n) {
        final int n2 = (int)n / 1000;
        return n2 / 60 + ":" + b(n2 % 60, 2);
    }
    
    public static String a(final int n, int n2) {
        n2 = (int)(n * 100L / 1000 % 100L);
        return n / 1000 + "." + ((n2 < 10) ? "0" : "") + n2;
    }
    
    private static String b(int n, int i) {
        for (int j = 1; j < 2; ++j) {
            i = 10;
        }
        final StringBuffer sb = new StringBuffer();
        while (i > 0) {
            sb.append((char)(48 + n / i));
            n %= i;
            i /= 10;
        }
        return new String(sb);
    }
    
    public d(final aj a) {
        this.a = a;
    }
    
    public final void a() {
    }
    
    public final void b() {
        this.a = new eh(this.a);
        this.a.a.a(this.a.b(), 1, 1, 0, 0, true);
        this.a.a = this.a.a();
        this.a.pack();
        this.a.show();
    }
    
    public final void c() {
        this.a.hide();
    }
    
    private void b(final String s) {
        this.a.setTitle(this.a.a().a(1716519088) + this.a.a().c() + this.a.a().a(1716519053) + this.a.b() + this.a.a().a(1716519086) + this.a.c() + ((s == null) ? "" : (this.a.a().a(1716519078) + s)));
    }
    
    public final void a(final eu eu) {
        if (eu.a.equals(this.a.a())) {
            this.b(null);
        }
    }
    
    public final void d() {
        try {
            Thread.sleep(100L);
        }
        catch (InterruptedException ex) {}
        this.a.hide();
        try {
            Thread.sleep(100L);
        }
        catch (InterruptedException ex2) {}
        this.a.dispose();
    }
    
    public final void a(final String s) {
        this.b(this.a.a(s).b);
    }
    
    public final void e() {
    }
    
    public final void a(final int n) {
    }
    
    public final boolean a(final byte b, final DataInputStream dataInputStream) {
        return false;
    }
    
    public final void f() {
    }
    
    public final void g() {
    }
    
    public final boolean a() {
        return true;
    }
    
    public final boolean a(final boolean b) {
        return true;
    }
    
    public final void b(final int n) {
    }
}
