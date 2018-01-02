import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class PolyJava1
{
    int[] I;
    int[][][] Z;
    
    final void I(final Graphics graphics) {
        for (int i = 0; i < this.I.length; ++i) {
            graphics.drawPolygon(this.Z[i][0], this.Z[i][1], this.I[i]);
            graphics.fillPolygon(this.Z[i][0], this.Z[i][1], this.I[i]);
        }
    }
}
