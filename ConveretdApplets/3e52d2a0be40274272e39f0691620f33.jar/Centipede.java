import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class Centipede extends Point
{
    int hort_dir;
    int vert_dir;
    
    Centipede(final int n, final int n2, final int hort_dir, final int vert_dir) {
        super(n, n2);
        this.hort_dir = hort_dir;
        this.vert_dir = vert_dir;
    }
}