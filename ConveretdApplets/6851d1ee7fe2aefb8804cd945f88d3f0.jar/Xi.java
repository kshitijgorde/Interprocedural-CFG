import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class Xi extends implements
{
    private int v;
    private int w;
    private int x;
    
    public Xi(final String s, final int[] array, final class class1) {
        super(s, 1, null, null, class1);
        this.v = 1;
        this.w = 15;
        this.x = -5;
        this.W();
    }
    
    protected void W() {
        super.r[0] = this.w + this.x;
    }
    
    protected void X() {
        final double[] _ = super.s._();
        if (_ == null || super.r[0] > _.length - 1) {
            super.t = null;
            return;
        }
        super.t = new double[super.u][_.length];
        if (this.v != 0) {
            do.calculateSimpleAvg(_, super.t[0], 0, this.w);
        }
        else {
            do.calculateExpAvg(_, super.t[0], 0, this.w);
        }
        if (this.x > 0) {
            for (int i = super.t[0].length - 1; i >= 0; --i) {
                super.t[0][i] = super.t[0][Math.max(0, i - this.x)];
            }
            do._(super.t[0], super.r[0], super.t[0][super.t[0].length - 1]);
        }
        else if (this.x < 0) {
            for (int j = 0; j < super.t[0].length; ++j) {
                super.t[0][j] = super.t[0][Math.min(super.t[0].length - 1, j - this.x)];
            }
            do._(super.t[0], super.r[0], super.t[0][super.t[0].length - 1]);
        }
    }
    
    public boolean b(final Frame frame, final o o) {
        final cj cj = new cj(this, frame, o);
        final while while1 = new while(frame, super.name, o, cj);
        while1.show();
        if (while1.a()) {
            if (cj.g.getState()) {
                this.v = 1;
            }
            else {
                this.v = 0;
            }
            try {
                this.w = Integer.parseInt(cj.k.getText());
            }
            catch (NumberFormatException ex) {}
            try {
                this.x = Integer.parseInt(cj.l.getText());
                if (cj.j.getState()) {
                    this.x = -this.x;
                }
            }
            catch (NumberFormatException ex2) {}
            super.y = true;
            this.W();
        }
        return while1.a();
    }
    
    public boolean i() {
        return true;
    }
    
    public void k(final String s) {
        final u u = new u(",");
        u.m(s);
        if (u.a() != 3) {
            return;
        }
        final String b = u.b(0);
        if (b != null) {
            try {
                this.v = Integer.parseInt(b);
            }
            catch (NumberFormatException ex) {}
        }
        final String b2 = u.b(1);
        if (b2 != null) {
            try {
                this.w = Integer.parseInt(b2);
            }
            catch (NumberFormatException ex2) {}
        }
        if (this.w < 2 || this.w > 999) {
            this.w = 15;
        }
        final String b3 = u.b(2);
        if (b3 != null) {
            try {
                this.x = Integer.parseInt(b3);
            }
            catch (NumberFormatException ex3) {}
        }
        if (Math.abs(this.x) > 999) {
            this.x = 15;
        }
        this.W();
        super.y = true;
    }
    
    public String k() {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.v);
        sb.append(",");
        sb.append(this.w);
        sb.append(",");
        sb.append(this.x);
        return sb.toString();
    }
    
    static int b(final Xi xi) {
        return xi.v;
    }
    
    static int _(final Xi xi) {
        return xi.w;
    }
    
    static int a(final Xi xi) {
        return xi.x;
    }
}
