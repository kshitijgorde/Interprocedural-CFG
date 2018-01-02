import java.awt.Color;
import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

public class j extends o
{
    private Date a;
    private int b;
    private o c;
    
    public j(final int b) {
        this.b = b;
        this.a = new Date(0L);
        super.s = new a(new Color(255, 255, 255));
        this.F();
        super.n = this.c.n;
        super.o = this.c.o;
    }
    
    public void O(final int l, final int m) {
        super.l = l;
        super.m = m;
        this.c.O(l, m);
    }
    
    public void J() {
        this.c.J();
    }
    
    public l N() {
        return this.c.N();
    }
    
    public boolean P() {
        return this.c.P();
    }
    
    public void K() {
        this.F();
    }
    
    public void S() {
        this.F();
    }
    
    public void Q(final a s) {
        super.s = s;
        this.c.Q(super.s);
    }
    
    public o M(final int n, final int n2) {
        if (this.c.M(n, n2) != null) {
            return this;
        }
        return null;
    }
    
    private static String G(final int n) {
        if (n < 10) {
            return "0" + n;
        }
        return "" + n;
    }
    
    private void F() {
        if (new Date().getSeconds() != this.a.getSeconds()) {
            this.a = new Date();
            if (this.b == 0) {
                this.H();
            }
            else {
                this.E();
            }
            this.c.O(super.l, super.m);
            this.c.Q(super.s);
            super.j = this.c.j;
        }
    }
    
    private void H() {
        String s = " ";
        if (this.a.getSeconds() % 2 == 0) {
            s = ":";
        }
        this.c = new f(G(this.a.getHours()) + s + G(this.a.getMinutes()));
    }
    
    private void E() {
        this.c = new f(G(this.a.getDate()) + "-" + G(this.a.getMonth() + 1) + "-" + (this.a.getYear() + 1900));
    }
}
