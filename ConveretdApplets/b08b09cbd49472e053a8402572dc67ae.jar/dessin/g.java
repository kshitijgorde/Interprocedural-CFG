// 
// Decompiled by Procyon v0.5.30
// 

package dessin;

import java.awt.Graphics;
import java.util.Vector;
import java.awt.Point;
import java.awt.Color;

public class g extends q
{
    Color M;
    float O;
    boolean N;
    
    public g(final int n, final int n2, final int n3, final int n4, final Color m, final float o, final boolean n5) {
        this.M = Color.black;
        this.O = 1.0f;
        super.K = new Point(n, n2);
        super.L = new Point(n + n3, n2 + n4);
        this.M = m;
        this.O = o;
        this.N = n5;
        super.if.a(super.K, super.L);
    }
    
    public g(final Point k, final Point l, final Color m, final float o, final boolean n) {
        this.M = Color.black;
        this.O = 1.0f;
        this.M = m;
        this.O = o;
        this.N = n;
        super.K = k;
        super.L = l;
        super.if.a(super.K, super.L);
    }
    
    public g(final int x, final int y, final int x2, final int y2) {
        this.M = Color.black;
        this.O = 1.0f;
        super.K = new Point(0, 0);
        super.L = new Point(0, 0);
        super.K.x = x;
        super.K.y = y;
        super.L.x = x2;
        super.L.y = y2;
        super.if.a(super.K, super.L);
    }
    
    public g(final int x, final int y, final Color m, final float o, final boolean n) {
        this.M = Color.black;
        this.O = 1.0f;
        this.M = m;
        this.O = o;
        this.N = n;
        super.K = new Point(0, 0);
        super.L = new Point(0, 0);
        super.K.x = x;
        super.K.y = y;
        super.if.a(super.K, super.L);
    }
    
    m do(final int n, final int n2) {
        if (n > super.K.x - 3 && n < super.L.x + 3 && n2 > super.K.y - 3 && n2 < super.L.y + 3) {
            return this;
        }
        return null;
    }
    
    void a(final Vector vector) {
        final int intValue = vector.elementAt(0);
        final int intValue2 = vector.elementAt(1);
        final int intValue3 = vector.elementAt(2);
        final int intValue4 = vector.elementAt(3);
        if (intValue < super.K.x && intValue2 < super.K.y && intValue3 > super.L.x && intValue4 > super.L.y) {
            super.a = true;
        }
    }
    
    void if(final Graphics graphics, final int n, final int n2) {
    }
    
    public void a(final Graphics graphics, final boolean b) {
        if (!b) {
            graphics.setColor(this.M);
        }
        else if (!super.a) {
            return;
        }
        if (!this.N) {
            graphics.drawOval(super.if.if, super.if.a, super.if.a(), super.if.if());
        }
        if (this.N) {
            graphics.fillOval(super.if.if, super.if.a, super.if.a(), super.if.if());
        }
    }
    
    void a(final Graphics graphics, final int n, final int n2) {
    }
    
    void a() {
        final Point k = super.K;
        final Point l = super.L;
        if (super.K.x <= super.L.x && super.K.y <= super.L.y) {
            return;
        }
        if (super.K.x > super.L.x && super.K.y < super.L.y) {
            final int x = l.x;
            final int y = k.y;
            final int x2 = k.x;
            final int y2 = l.y;
            super.K = new Point(x, y);
            super.L = new Point(x2, y2);
        }
        else if (super.K.x > super.L.x && super.K.y > super.L.y) {
            final int x3 = l.x;
            final int y3 = l.y;
            final int x4 = k.x;
            final int y4 = k.y;
            super.K = new Point(x3, y3);
            super.L = new Point(x4, y4);
        }
        else if (super.K.x < super.L.x && super.K.y > super.L.y) {
            final int x5 = k.x;
            final int y5 = l.y;
            final int x6 = l.x;
            final int y6 = k.y;
            super.K = new Point(x5, y5);
            super.L = new Point(x6, y6);
        }
    }
    
    Color do() {
        return Color.gray;
    }
    
    public String if() {
        final String string = "" + "oval;" + super.if.if + ";" + super.if.a + ";" + super.if.a() + ";" + super.if.if() + ";" + this.M.getRGB() + ";" + (int)this.O + ";";
        String s;
        if (this.N) {
            s = string + "0;";
        }
        else {
            s = string + "1;";
        }
        return s + "0;" + "0;";
    }
    
    void if(final Point point) {
    }
    
    void a(final Point point) {
    }
    
    void a(final int n, final int n2, final int n3) {
        if (n3 == 4) {
            super.K.y = n2;
        }
        if (n3 == 5) {
            super.L.x = n;
            super.K.y = n2;
        }
        if (n3 == 6) {
            super.L.x = n;
        }
        if (n3 == 7) {
            super.L.x = n;
            super.L.y = n2;
        }
        if (n3 == 8) {
            super.L.y = n2;
        }
        if (n3 == 9) {
            super.K.x = n;
            super.L.y = n2;
        }
        if (n3 == 10) {
            super.K.x = n;
        }
        if (n3 == 11) {
            super.K.x = n;
            super.K.y = n2;
        }
        super.if.a(super.K, super.L);
        this.a();
    }
    
    int a(final int n, final int n2) {
        if (this.do(n, n2) == null) {
            return -1;
        }
        if (n > super.K.x && n < super.L.x && n2 > super.K.y - 3 && n2 < super.K.y + 3) {
            return 4;
        }
        if (n > super.L.x - 3 && n < super.L.x + 3 && n2 > super.K.y - 3 && n2 < super.K.y + 3) {
            return 5;
        }
        if (n > super.L.x - 3 && n < super.L.x + 3 && n2 > super.K.y && n2 < super.L.y) {
            return 6;
        }
        if (n > super.L.x - 3 && n < super.L.x + 3 && n2 > super.L.y - 3 && n2 < super.L.y + 3) {
            return 7;
        }
        if (n > super.K.x && n < super.L.x && n2 > super.L.y - 3 && n2 < super.L.y + 3) {
            return 8;
        }
        if (n > super.K.x - 3 && n < super.K.x + 3 && n2 > super.L.y - 3 && n2 < super.L.y + 3) {
            return 9;
        }
        if (n > super.K.x - 3 && n < super.K.x + 3 && n2 > super.K.y && n2 < super.L.y) {
            return 10;
        }
        if (n > super.K.x - 3 && n < super.K.x + 3 && n2 > super.K.y - 3 && n2 < super.K.y + 3) {
            return 11;
        }
        return 1;
    }
}
