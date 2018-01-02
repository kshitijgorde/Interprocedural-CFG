import java.awt.geom.Point2D;

// 
// Decompiled by Procyon v0.5.30
// 

class Intersection
{
    Point2D.Double p;
    CircleLayout c1;
    CircleLayout c2;
    
    Intersection(final Point2D.Double p, final CircleLayout c1, final CircleLayout c2) {
        this.p = p;
        this.c1 = c1;
        this.c2 = c2;
    }
}
