import java.awt.Point;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class c extends Rectangle
{
    Point a;
    Point b;
    Point c;
    Point d;
    Point e;
    Point f;
    Point g;
    Point h;
    
    c(final d d) {
        super(d.x, d.y, d.width, d.height);
        this.a = d.D;
        this.b = d.A;
        this.c = d.H;
        this.d = d.L;
        this.e = d.E;
        this.f = d.B;
        this.g = d.I;
        this.h = d.M;
        if (!d.R) {
            super.y += d.O;
            super.height = d.P - d.O;
            return;
        }
        super.x += d.O;
        super.width = d.P - d.O;
    }
}
