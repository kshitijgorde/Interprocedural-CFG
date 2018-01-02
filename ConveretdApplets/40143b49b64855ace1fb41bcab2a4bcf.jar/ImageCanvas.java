import java.awt.Event;
import java.awt.Window;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Container;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class ImageCanvas extends Canvas
{
    Container pappy;
    Image image;
    private Image startImage;
    private Graphics startGraphics;
    boolean trueSizeKnown;
    boolean start;
    Dimension minSize;
    int w;
    int h;
    int x;
    int y;
    int imageWidth;
    int imageHeight;
    
    public ImageCanvas(final Container pappy, final int w, final int h) {
        this.image = null;
        this.startImage = null;
        this.trueSizeKnown = false;
        this.start = false;
        this.pappy = pappy;
        this.w = w;
        this.h = h;
        this.minSize = new Dimension(this.w, this.h);
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public synchronized Dimension minimumSize() {
        return this.minSize;
    }
    
    public void update(final Graphics graphics) {
        if (this.image != null) {
            if (!(this.pappy instanceof Applet)) {
                graphics.drawImage(this.image, this.x + 10, this.y + 10, this);
                graphics.drawRect(this.x + 5, this.y + 5, this.imageWidth + 10, this.imageHeight + 10);
                return;
            }
            if (this.startImage == null) {
                this.start();
            }
            graphics.drawImage(this.image, 0, 0, this);
            if (this.start) {
                graphics.drawImage(this.startImage, this.imageWidth / 2 - 25, this.imageHeight / 2 - 10, this);
            }
        }
    }
    
    private void start() {
        this.startImage = this.createImage(51, 21);
        (this.startGraphics = this.startImage.getGraphics()).setColor(new Color(255, 255, 128));
        this.startGraphics.fillRect(0, 0, 50, 20);
        this.startGraphics.setColor(Color.black);
        this.startGraphics.drawRect(0, 0, 50, 20);
        this.startGraphics.setFont(new Font("Helvetica", 1, 12));
        this.startGraphics.drawString("Start", 10, 15);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public void setImage(final Image image) {
        this.image = image;
        this.x = 0;
        this.y = 0;
        int n = this.w;
        int n2 = this.h;
        this.imageWidth = image.getWidth(this);
        this.imageHeight = image.getHeight(this);
        if (!(this.pappy instanceof Applet)) {
            if (this.imageWidth > this.w) {
                n = this.imageWidth;
            }
            if (this.imageHeight > this.h) {
                n2 = this.imageHeight;
            }
            this.x = (n - this.imageWidth) / 2;
            this.y = (n2 - this.imageHeight) / 2;
            this.resize(this.minSize = new Dimension(n + 20, n2 + 20));
            ((Window)this.pappy).pack();
        }
        else {
            this.resize(this.minSize = new Dimension(this.imageWidth, this.imageHeight));
        }
        this.pappy.validate();
        this.repaint();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 504) {
            this.start = true;
            this.repaint();
        }
        if (event.id == 505) {
            this.start = false;
            this.repaint();
        }
        return super.handleEvent(event);
    }
}
