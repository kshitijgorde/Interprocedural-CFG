import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Color;
import java.awt.Point;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class CanvasHS extends Canvas
{
    static final int HEIGHT = 255;
    static final int WIDTH = 255;
    private int[] colormap;
    private Image col;
    private Point cur_point;
    
    CanvasHS() {
        this.colormap = new int[65025];
        this.col = null;
        this.cur_point = new Point();
        super.resize(255, 255);
        int n = 0;
        do {
            int n2 = 1;
            do {
                this.colormap[n + (255 - n2) * 255] = Color.HSBtoRGB(n / 255.0f, n2 / 255.0f, 0.75f);
            } while (++n2 <= 255);
        } while (++n < 255);
        this.col = this.createImage(new MemoryImageSource(255, 255, this.colormap, 0, 255));
    }
    
    public Color getColorAt(final int n, final int n2) {
        return new Color(this.colormap[n + n2 * 255]);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.col, 0, 0, this);
    }
}
