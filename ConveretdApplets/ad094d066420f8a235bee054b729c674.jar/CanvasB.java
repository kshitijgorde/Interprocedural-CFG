import java.awt.Event;
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

public class CanvasB extends Canvas
{
    static final int HEIGHT = 256;
    static final int WIDTH = 30;
    private int[] colormap;
    private Image col_img;
    private Point cur_point;
    int mouseY;
    int prevY;
    
    CanvasB() {
        this.colormap = new int[7680];
        this.col_img = null;
        this.cur_point = new Point();
        this.mouseY = 0;
        this.prevY = 0;
        super.setBackground(Color.white);
        super.resize(40, 256);
        int n = 0;
        do {
            int n2 = 1;
            do {
                this.colormap[n + (256 - n2) * 30] = Color.HSBtoRGB(0.0f, 0.0f, n2 / 256.0f);
            } while (++n2 <= 256);
        } while (++n < 30);
        this.col_img = this.createImage(new MemoryImageSource(30, 256, this.colormap, 0, 30));
        this.mouseY = 128;
    }
    
    public Color getColorAt(final int n, final int n2) {
        return new Color(this.colormap[n + n2 * 30]);
    }
    
    public Color getColor() {
        return new Color(this.colormap[this.mouseY * 30]);
    }
    
    public void paint(final Graphics graphics) {
        final int[] array = { 0, 5, 0 };
        final int[] array2 = { this.mouseY - 5, this.mouseY, this.mouseY + 5 };
        graphics.setColor(Color.black);
        graphics.fillPolygon(array, array2, 3);
        array[0] = this.size().width - array[0];
        array[1] = this.size().width - array[1];
        array[2] = this.size().width - array[2];
        graphics.fillPolygon(array, array2, 3);
        graphics.drawImage(this.col_img, 5, 0, this);
    }
    
    public void update(final Graphics graphics) {
        graphics.clearRect(0, 0, 5, this.size().height);
        graphics.clearRect(this.size().width - 5, 0, this.size().width, this.size().height);
        this.paint(graphics);
    }
    
    public void refresh(final Color color) {
        int n = 0;
        do {
            int n2 = 1;
            do {
                this.colormap[n + n2 * 30] = (0xFF000000 | n2 * 2 * (color.getRed() - 255) / 256 + 255 << 16 | n2 * 2 * (color.getGreen() - 255) / 256 + 255 << 8 | n2 * 2 * (color.getBlue() - 255) / 256 + 255);
            } while (++n2 <= 128);
        } while (++n < 30);
        int n3 = 0;
        do {
            int n4 = 128;
            do {
                this.colormap[n3 + n4 * 30] = (0xFF000000 | color.getRed() * 2 * (256 - n4) / 256 << 16 | color.getGreen() * 2 * (256 - n4) / 256 << 8 | color.getBlue() * 2 * (256 - n4) / 256);
            } while (++n4 < 256);
        } while (++n3 < 30);
        this.col_img = this.createImage(new MemoryImageSource(30, 256, this.colormap, 0, 30));
        this.repaint();
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        return true;
    }
    
    public void setCursor(final int mouseY) {
        this.prevY = this.mouseY;
        this.mouseY = mouseY;
    }
}
