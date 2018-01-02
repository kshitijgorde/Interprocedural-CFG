// 
// Decompiled by Procyon v0.5.30
// 

package imaging;

import imaging.math3D.Int2D;

public class Quadre
{
    public Int2D tl;
    public Int2D tr;
    public Int2D bl;
    public Int2D br;
    
    public Quadre() {
        this.tl = new Int2D();
        this.tr = new Int2D();
        this.bl = new Int2D();
        this.br = new Int2D();
    }
    
    public int hitsPoint(final int x, final int y) {
        if (this.distance(this.tl.x, this.tl.y, x, y) <= 5.0) {
            return 0;
        }
        if (this.distance(this.tr.x, this.tr.y, x, y) <= 5.0) {
            return 1;
        }
        if (this.distance(this.br.x, this.br.y, x, y) <= 5.0) {
            return 2;
        }
        if (this.distance(this.bl.x, this.bl.y, x, y) <= 5.0) {
            return 3;
        }
        return -1;
    }
    
    private double distance(final double x1, final double y1, final double x2, final double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
}
