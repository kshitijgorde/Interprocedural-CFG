// 
// Decompiled by Procyon v0.5.30
// 

public class a6 extends x
{
    public a6() {
    }
    
    public a6(final a6 a6) {
        super(a6);
    }
    
    public a6(final String s) {
        super(s);
        if (s == null) {
            return;
        }
        this.a();
    }
    
    public void d(final String b) {
        super.b = b;
        this.a();
        super.e = null;
    }
    
    private void a() {
        if (super.b == null || super.b.length() == 0) {
            return;
        }
        if (!super.b.startsWith("/")) {
            super.b = "/" + super.b;
        }
        if (!super.b.endsWith(".csv")) {
            super.b += ".csv";
        }
    }
    
    public void e(final String c) {
        super.c = c;
        if (super.c.endsWith("/")) {
            super.c = super.c.substring(0, super.c.length() - 1);
        }
        super.e = null;
    }
    
    public void f(String s) {
        if (s == null) {
            return;
        }
        if (!s.startsWith("/")) {
            s = "/" + s;
        }
        if (s.endsWith("/")) {
            s = s.substring(0, s.length() - 1);
        }
        super.b = s + super.b;
        super.e = null;
    }
}
