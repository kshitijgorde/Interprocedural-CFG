import java.awt.Point;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class b extends Rectangle
{
    Point a;
    Point b;
    Point c;
    Point d;
    
    b(final d d, final boolean b) {
        super(d.x, d.y, d.width, d.height);
        if (b) {
            this.a = d.p;
            this.b = d.z;
            this.c = d.G;
            this.d = d.K;
            if (!d.R) {
                super.height = d.O;
                return;
            }
            super.width = d.O;
        }
        else {
            this.a = d.F;
            this.b = d.C;
            this.c = d.J;
            this.d = d.N;
            if (!d.R) {
                super.y += d.P;
                super.height -= d.P;
                return;
            }
            super.x += d.P;
            super.width -= d.P;
        }
    }
}
