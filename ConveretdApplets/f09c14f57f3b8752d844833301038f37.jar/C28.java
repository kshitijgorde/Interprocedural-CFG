import java.awt.Rectangle;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class C28 extends C35
{
    C25 q;
    C17 r;
    public static final int[] s;
    public static final int[] t;
    public static final int[] u;
    public static final int[] v;
    public static final int[] w;
    public static final int[] x;
    int z;
    int[] A;
    public int B;
    boolean C;
    int D;
    public static final int[] E;
    public static final int[] F;
    public static final int[] G;
    public int H;
    public static final int[] I;
    public int J;
    public static final int[] K;
    public static final int[] L;
    public static final int[] M;
    public static final int[] N;
    public static final int[] O;
    public int P;
    Color Q;
    public static final int[] R;
    public static final int[] S;
    
    public void f(final int n, final int n2) {
        this.P += n;
        this.B += n2;
    }
    
    public C25 e() {
        return this.q;
    }
    
    public void f(final int z) {
        this.z = z;
    }
    
    public C28() {
        this.z = 0;
        this.D = 0;
        this.A = C28.S;
        this.C = true;
    }
    
    public int g() {
        return this.z;
    }
    
    public void h(final boolean c) {
        this.C = c;
    }
    
    public void j(final int[] a) {
        this.A = a;
    }
    
    public boolean k() {
        return this.C;
    }
    
    static {
        S = new int[] { 0 };
        R = new int[] { 25, 10 };
        E = new int[] { 1, 10 };
        u = new int[] { 25, 10, 1, 10 };
        w = new int[] { 15, 10 };
        s = new int[] { 25, 10 };
        x = new int[] { 40, 10 };
        F = new int[] { 30, 10 };
        t = new int[] { 50, 10 };
        M = new int[] { 80, 10 };
        v = new int[] { 25, 10, 40, 10 };
        G = new int[] { 25, 10, 15, 10, 15, 10 };
        N = new int[] { 40, 10, 15, 10 };
        L = new int[] { 40, 10, 1, 10, 1, 10 };
        I = new int[] { 40, 10, 1, 10 };
        K = new int[] { 25, 10, 1, 10, 15, 10, 1, 10 };
        O = new int[] { 1, 20 };
    }
    
    public boolean a(final Rectangle rectangle) {
        return rectangle.x + rectangle.width > this.P && rectangle.y + rectangle.height > this.B && rectangle.x < this.P + this.H && rectangle.y < this.B + this.J;
    }
    
    public void l(final Color q) {
        this.Q = q;
    }
    
    public void m(final int d) {
        this.D = d;
    }
    
    public Color n() {
        return this.Q;
    }
    
    public void o(final C25 q) {
        this.q = q;
    }
    
    public C17 p() {
        if (this.r != null) {
            return this.r;
        }
        final int n = this.H * 2;
        final int n2 = this.J * 2;
        return this.r = new C17(this.P - n / 2, this.B - n2 / 2, this.P + n, this.B + n2, this + "");
    }
}
