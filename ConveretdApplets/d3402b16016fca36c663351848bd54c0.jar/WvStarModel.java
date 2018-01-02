import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class WvStarModel
{
    private static final int STARS = 100;
    private int xsize;
    private int ysize;
    private int xcenter;
    private int ycenter;
    private int[] starX;
    private int[] starY;
    private int[] starZ;
    private Color[] grayScale;
    public int[] xpos;
    public int[] ypos;
    public Color color;
    
    public Color getColor(final int n) {
        return this.grayScale[this.starZ[n] >> 4];
    }
    
    public void tick() {
        int n = 0;
        do {
            final int[] starZ = this.starZ;
            final int n2 = n;
            starZ[n2] -= 50;
            if (this.starZ[n] < 512) {
                this.starZ[n] = 4095;
            }
            final int n3 = this.starZ[n];
            this.xpos[n] = (this.starX[n] << 9) / n3 + this.xcenter;
            this.ypos[n] = (this.starY[n] << 9) / n3 + this.ycenter;
        } while (++n < 100);
    }
    
    public WvStarModel(final int xsize, final int ysize) {
        this.xsize = xsize;
        this.ysize = ysize;
        this.xcenter = xsize / 2;
        this.ycenter = ysize / 2;
        this.starX = new int[100];
        this.starY = new int[100];
        this.starZ = new int[100];
        this.xpos = new int[100];
        this.ypos = new int[100];
        int n = 0;
        do {
            this.starX[n] = (int)(Math.random() * xsize * 4.0 - xsize * 2);
            this.starY[n] = (int)(Math.random() * xsize * 4.0 - ysize * 2);
            this.starZ[n] = (int)(Math.random() * 4095.0);
        } while (++n < 100);
        this.grayScale = new Color[256];
        int n2 = 0;
        do {
            this.grayScale[255 - n2] = new Color(n2, n2, n2);
        } while (++n2 < 256);
    }
}
