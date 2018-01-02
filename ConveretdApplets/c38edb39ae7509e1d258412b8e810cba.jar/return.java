import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

class return
{
    private throw[] z;
    private Font A;
    private Font B;
    private Color C;
    private Color D;
    private Color E;
    private Rectangle F;
    private Rectangle G;
    private Rectangle H;
    private Rectangle I;
    private Rectangle J;
    private int K;
    private int L;
    private int M;
    private int N;
    private int O;
    private int P;
    private String Q;
    private String R;
    private static String ya = "\ue4e8\ue4db\ue4c0\ue4c8\ue4c5";
    private static String za = "\ue4dd\ue4c6\ue4dd\ue4da\ue4ca\ue4c6\ue4db\ue4f6\ue4c4\ue4da\ue4ce";
    private static String Aa = "\ue4c1\ue4c0\ue4da\ue4ca\ue4c6\ue4db\ue4cc\ue4f6\ue4c4\ue4da\ue4ce";
    private static String Ba = "\ue489\ue489\ue489";
    private static String Ca = "\ue489\ue489\ue489\ue489\ue489\ue489\ue489\ue489\ue489";
    private static String Da = "\ue482";
    private static String Ea = "\ue481";
    private static String Ta = "\ue480";
    
    return(final super super1) {
        this.A = new Font(return.ya, 0, 11);
        this.B = new Font(return.ya, 1, 12);
        this.C = Color.white;
        this.D = new Color(16384);
        this.E = new Color(16763904);
        this.F = new Rectangle(136, 245, 34, 19);
        this.G = new Rectangle(136, 265, 34, 19);
        this.H = new Rectangle(136, 284, 34, 19);
        this.I = new Rectangle(296, 284, 34, 19);
        this.J = new Rectangle(10, 306, 318, 23);
        this.z = new throw[14];
        final Rectangle rectangle = new Rectangle(136, 132, 34, 19);
        for (int i = 0; i < this.z.length; ++i) {
            this.z[i] = new throw(i, rectangle);
            final Rectangle rectangle2 = rectangle;
            rectangle2.y += rectangle.height;
            if (i == 5) {
                rectangle.x = 296;
                rectangle.y = 132;
            }
        }
        this.Q = super1.m(return.za);
        this.R = super1.m(return.Aa);
        this.K = 0;
    }
    
    void reset() {
        for (int i = 0; i < this.z.length; ++i) {
            this.z[i].reset();
        }
        this.K = 0;
    }
    
    boolean h() {
        for (int i = 0; i < this.z.length; ++i) {
            if (this.z[i]._() == -1) {
                return false;
            }
        }
        return true;
    }
    
    int _(final int n, final int n2, final boolean b) {
        int n3 = -1;
        int i = 0;
        while (i < this.z.length) {
            if (this.z[i].contains(n, n2)) {
                if (this.z[i]._() == -1) {
                    if (b) {
                        n3 = this.z[i].b();
                    }
                    else {
                        n3 = this.z[i].a();
                    }
                    this.z[i].b(String.valueOf(n3));
                    break;
                }
                break;
            }
            else {
                ++i;
            }
        }
        return n3;
    }
    
    void _() {
        for (int i = 0; i < this.z.length; ++i) {
            if (this.z[i]._() == -1) {
                this.z[i].b(null);
            }
        }
    }
    
    void b(final Graphics graphics) {
        graphics.setColor(this.C);
        graphics.setFont(this.A);
        for (int i = 0; i < this.z.length; ++i) {
            final String b = this.z[i].b();
            if (b != null) {
                graphics.drawString(b, switch.b(b, true, this.z[i].oa, graphics), switch.b(b, false, this.z[i].oa, graphics));
            }
        }
        if (this.K == 0 && this.h()) {
            this.n();
        }
        if (this.K != 0) {
            graphics.setColor(this.D);
            final String value = String.valueOf(this.O);
            graphics.drawString(value, switch.b(value, true, this.F, graphics), switch.b(value, false, this.F, graphics));
            final String value2 = String.valueOf(this.L);
            graphics.drawString(value2, switch.b(value2, true, this.G, graphics), switch.b(value2, false, this.G, graphics));
            final String value3 = String.valueOf(this.O + this.L);
            graphics.drawString(value3, switch.b(value3, true, this.H, graphics), switch.b(value3, false, this.H, graphics));
            final String value4 = String.valueOf(this.N);
            graphics.drawString(value4, switch.b(value4, true, this.I, graphics), switch.b(value4, false, this.I, graphics));
            graphics.setFont(this.B);
            graphics.setColor(this.E);
            if (this.K > this.P) {
                this.P = this.K;
            }
            final StringBuffer sb = new StringBuffer();
            sb.append(this.Q).append(return.Ba).append(this.K);
            if (this.P > 0) {
                sb.append(return.Ca).append(this.R).append(return.Ba).append(this.P);
            }
            final String string = sb.toString();
            graphics.drawString(string, switch.b(string, true, this.J, graphics), switch.b(string, false, this.J, graphics));
            return;
        }
        graphics.setColor(this.D);
        int n = 0;
        for (int j = 0; j < 6; ++j) {
            final int _;
            if ((_ = this.z[j]._()) != -1) {
                n += _ - (j + 1) * 3;
            }
        }
        String s = String.valueOf(n);
        if (n > 0) {
            s = return.Da + s;
        }
        final String string2 = return.Ea + s + return.Ta;
        graphics.drawString(string2, switch.b(string2, true, this.G, graphics), switch.b(string2, false, this.G, graphics));
    }
    
    private void n() {
        this.O = 0;
        for (int i = 0; i < 6; ++i) {
            this.O += this.z[i]._();
        }
        this.L = 0;
        if (this.O >= 63) {
            this.L = 30;
        }
        this.N = 0;
        for (int j = 6; j < this.z.length; ++j) {
            this.N += this.z[j]._();
        }
        this.K = this.O + this.L + this.N;
    }
    
    boolean contains(final int n, final int n2) {
        for (int i = 0; i < this.z.length; ++i) {
            if (this.z[i].contains(n, n2)) {
                return true;
            }
        }
        return false;
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\ue4a9');
        }
        return new String(array);
    }
    
    static {
        return.ya = _(return.ya);
        return.za = _(return.za);
        return.Aa = _(return.Aa);
        return.Ba = _(return.Ba);
        return.Ca = _(return.Ca);
        return.Da = _(return.Da);
        return.Ea = _(return.Ea);
        return.Ta = _(return.Ta);
    }
}
