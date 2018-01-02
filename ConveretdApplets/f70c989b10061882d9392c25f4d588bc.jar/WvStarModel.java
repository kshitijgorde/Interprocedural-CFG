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
    
    public Color getColor(final int i) {
        final int j = this.starZ[i] >> 4;
        return this.grayScale[j];
    }
    
    public void tick() {
        int i = 0;
        do {
            final int[] starZ = this.starZ;
            final int n = i;
            starZ[n] -= 50;
            if (this.starZ[i] < 512) {
                this.starZ[i] = 4095;
            }
            final int j = this.starZ[i];
            this.xpos[i] = (this.starX[i] << 9) / j + this.xcenter;
            this.ypos[i] = (this.starY[i] << 9) / j + this.ycenter;
        } while (++i < 100);
    }
    
    public WvStarModel(final int i, final int j) {
        this.xsize = i;
        this.ysize = j;
        this.xcenter = i / 2;
        this.ycenter = j / 2;
        this.starX = new int[100];
        this.starY = new int[100];
        this.starZ = new int[100];
        this.xpos = new int[100];
        this.ypos = new int[100];
        int k = 0;
        do {
            this.starX[k] = (int)(Math.random() * i * 4.0 - i * 2);
            this.starY[k] = (int)(Math.random() * i * 4.0 - j * 2);
            this.starZ[k] = (int)(Math.random() * 4095.0);
        } while (++k < 100);
        this.grayScale = new Color[256];
        k = 0;
        do {
            this.grayScale[255 - k] = new Color(k, k, k);
        } while (++k < 256);
    }
}
