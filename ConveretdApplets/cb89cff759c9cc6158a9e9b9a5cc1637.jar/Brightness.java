import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class Brightness extends Canvas implements MouseMotionListener, MouseListener
{
    private final int HEIGHT = 256;
    private final int WIDTH = 30;
    private int[] colormap;
    private Image col_img;
    private Image buf;
    private Graphics gbuf;
    private HexColorTool hct;
    private int mouseY;
    private int prevY;
    
    public Brightness(final HexColorTool hct) {
        this.colormap = new int[7680];
        this.col_img = null;
        this.mouseY = 0;
        this.prevY = 0;
        this.hct = hct;
        this.setBackground(Color.lightGray);
        for (int i = 0; i < 30; ++i) {
            for (int j = 1; j <= 256; ++j) {
                this.colormap[i + (256 - j) * 30] = Color.HSBtoRGB(0.0f, 0.0f, j / 256.0f);
            }
        }
        this.col_img = this.createImage(new MemoryImageSource(30, 256, this.colormap, 0, 30));
        this.mouseY = 128;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public void refresh(final Color color) {
        for (int i = 0; i < 30; ++i) {
            for (int j = 1; j <= 128; ++j) {
                this.colormap[i + j * 30] = (0xFF000000 | j * 2 * (color.getRed() - 255) / 256 + 255 << 16 | j * 2 * (color.getGreen() - 255) / 256 + 255 << 8 | j * 2 * (color.getBlue() - 255) / 256 + 255);
            }
        }
        for (int k = 0; k < 30; ++k) {
            for (int l = 128; l < 256; ++l) {
                this.colormap[k + l * 30] = (0xFF000000 | color.getRed() * 2 * (256 - l) / 256 << 16 | color.getGreen() * 2 * (256 - l) / 256 << 8 | color.getBlue() * 2 * (256 - l) / 256);
            }
        }
        this.col_img = this.createImage(new MemoryImageSource(30, 256, this.colormap, 0, 30));
        this.mouseY = 128;
        this.repaint();
    }
    
    public Color getColorAt(final int n, final int n2) {
        return new Color(this.colormap[n + n2 * 30]);
    }
    
    public Color getColor() {
        return new Color(this.colormap[this.mouseY * 30]);
    }
    
    public void setCursor(final int mouseY) {
        this.mouseY = mouseY;
        this.repaint();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.hct.updateColor(this.getColorAt(x, y), false, false);
        this.setCursor(y);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.hct.updateColor(this.getColorAt(mouseEvent.getX(), mouseEvent.getY()), false, true);
        this.setCursor(mouseEvent.getY());
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        if (this.buf == null) {
            this.buf = this.createImage(width, height);
            this.gbuf = this.buf.getGraphics();
        }
        this.gbuf.setColor(Color.lightGray);
        this.gbuf.fillRect(0, 0, width, height);
        final int[] array = { 0, 5, 0 };
        final int[] array2 = { this.mouseY - 5, this.mouseY, this.mouseY + 5 };
        this.gbuf.setColor(Color.black);
        this.gbuf.fillPolygon(array, array2, 3);
        this.gbuf.drawImage(this.col_img, 5, 0, this);
        this.gbuf.setColor(Color.lightGray);
        this.gbuf.draw3DRect(0, 0, width - 1, height - 1, false);
        graphics.drawImage(this.buf, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
