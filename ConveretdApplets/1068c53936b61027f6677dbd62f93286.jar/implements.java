import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class implements
{
    public static final int O = 0;
    public static final int P = 1;
    private int Q;
    private int R;
    private int S;
    private int T;
    private int U;
    private int x;
    private int y;
    private int V;
    private String W;
    private Rectangle X;
    private boolean Y;
    
    public implements(final String w, final int r, final int x, final int y, final int v) {
        this.W = w;
        this.S = this.W.length();
        this.R = r;
        this.x = x;
        this.y = y;
        this.V = v;
        if (this.R == 0) {
            this.X = new Rectangle(x, y, this.S * v, v);
            return;
        }
        this.X = new Rectangle(x, y, v, this.S * v);
    }
    
    public void _(final boolean y) {
        this.Y = y;
    }
    
    public void _(final int t) {
        this.T = t;
    }
    
    public void a(final int u) {
        this.U = u;
    }
    
    public int b() {
        return this.T;
    }
    
    public int _() {
        return this.U;
    }
    
    public void b(final int n) {
        this.Q += n;
        this._();
    }
    
    private void _() {
        if (this.Y) {
            if (this.Q < 0) {
                this.Q = this.S - 1;
                return;
            }
            if (this.Q >= this.S) {
                this.Q = 0;
            }
        }
    }
    
    public void l(final int q) {
        this.Q = q;
    }
    
    public int g() {
        return this.Q;
    }
    
    public int h() {
        if (this.R == 0) {
            return this.U + this.Q;
        }
        return this.T + this.Q;
    }
    
    public int i() {
        return this.R;
    }
    
    public boolean l() {
        return this.Q >= this.S;
    }
    
    public boolean m() {
        return this.Q == 0;
    }
    
    public int length() {
        return this.S;
    }
    
    public int j() {
        if (this.R == 0) {
            return this.U;
        }
        return this.T;
    }
    
    public String e() {
        return this.W;
    }
    
    public Rectangle bounds() {
        return this.X;
    }
    
    public boolean contains(final int n, final int n2) {
        return this.X.contains(n, n2);
    }
    
    public boolean a(final int n, final int n2) {
        if (this.R == 0) {
            return n == this.T && n2 >= this.U && n2 <= this.U + this.S;
        }
        return n2 == this.U && n >= this.T && n <= this.T + this.S;
    }
    
    public Rectangle b() {
        Rectangle rectangle;
        if (this.R == 0) {
            rectangle = new Rectangle(this.x + this.Q * this.V, this.y, this.V, this.V);
        }
        else {
            rectangle = new Rectangle(this.x, this.y + this.Q * this.V, this.V, this.V);
        }
        return rectangle;
    }
    
    public Rectangle b(final boolean[] array, final boolean b) {
        if (b) {
            for (int i = this.Q; i < this.S; ++i) {
                if (!array[i]) {
                    break;
                }
                ++this.Q;
                this._();
            }
        }
        else {
            for (int q = this.Q; q >= 0 && array[q]; --q) {
                --this.Q;
                this._();
            }
        }
        return this.b();
    }
    
    public Rectangle b(final char[] array, final char c, final boolean b) {
        if (b) {
            for (int i = this.Q; i < this.S; ++i) {
                if (array[i] == c) {
                    break;
                }
                ++this.Q;
                this._();
            }
        }
        else {
            for (int q = this.Q; q >= 0 && array[q] != c; --q) {
                --this.Q;
                this._();
            }
        }
        return this.b();
    }
    
    public boolean m(final String s) {
        return s.equals(this.W);
    }
}
