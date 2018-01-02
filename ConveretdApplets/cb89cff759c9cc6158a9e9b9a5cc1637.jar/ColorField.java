import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class ColorField extends Canvas implements MouseListener, MouseMotionListener
{
    private Image buf;
    private int x;
    private int y;
    private Graphics gbuf;
    private int[] colormap;
    private Image col;
    private HexColorTool hct;
    
    public ColorField(final HexColorTool hct) {
        this.colormap = new int[65025];
        this.col = null;
        this.hct = hct;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        for (int i = 0; i < 255; ++i) {
            for (int j = 1; j <= 255; ++j) {
                this.colormap[i + (255 - j) * 255] = Color.HSBtoRGB(i / 255.0f, j / 255.0f, 0.75f);
            }
        }
        this.col = this.createImage(new MemoryImageSource(255, 255, this.colormap, 0, 255));
    }
    
    public Color getColorAt(final int x, final int y) {
        this.x = x;
        this.y = y;
        this.repaint();
        return new Color(this.colormap[x + y * 255]);
    }
    
    public void paint(final Graphics graphics) {
        if (this.buf == null) {
            this.buf = this.createImage(this.getSize().width, this.getSize().height);
            this.gbuf = this.buf.getGraphics();
        }
        this.gbuf.drawImage(this.col, 0, 0, this);
        this.gbuf.setColor(Color.lightGray);
        this.gbuf.draw3DRect(0, 0, this.getSize().width - 1, this.getSize().height - 1, false);
        this.gbuf.setColor(Color.black);
        this.gbuf.drawLine(this.x - 5, this.y, this.x + 5, this.y);
        this.gbuf.drawLine(this.x, this.y - 5, this.x, this.y + 5);
        graphics.drawImage(this.buf, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.hct.updateColor(this.getColorAt(mouseEvent.getX(), mouseEvent.getY()), true, true);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.hct.updateColor(this.getColorAt(mouseEvent.getX(), mouseEvent.getY()), true, true);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
}
