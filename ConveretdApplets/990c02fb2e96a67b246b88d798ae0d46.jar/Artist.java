import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class Artist
{
    public int maxwidth;
    public int maxheight;
    int noPoints;
    int[][] s1;
    
    public void compose(final Graphics graphics) {
        graphics.setColor(Color.red);
        this.crisscross(graphics);
    }
    
    public void crisscross(final Graphics graphics) {
        graphics.drawLine(0, 0, this.maxwidth, this.maxheight);
        graphics.drawLine(0, this.maxheight, this.maxwidth, 0);
    }
    
    public void drawLines(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.drawLine(n, n2, n3, n4);
        graphics.drawLine(n + 1, n2, n3 + 1, n4);
    }
    
    public void viewcoord(final double[][] array, final int[][] array2) {
        final double n = 0.2;
        final double[][] array3 = new double[this.noPoints][2];
        for (int i = 0; i < this.noPoints; ++i) {
            array3[i][0] = array[i][0] / (n * array[i][2] + 1.3);
            array3[i][1] = array[i][1] / (n * array[i][2] + 1.3);
            array2[i][0] = (int)(Math.round(array3[i][0] * 50.0) + this.maxwidth / 2.0);
            array2[i][1] = (int)(Math.round(-array3[i][1] * 50.0) + this.maxheight / 2.0);
        }
    }
}
