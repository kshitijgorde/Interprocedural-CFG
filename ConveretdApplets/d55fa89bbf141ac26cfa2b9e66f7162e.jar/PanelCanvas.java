import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class PanelCanvas extends Canvas
{
    private int width;
    private int height;
    Image bufferImage;
    Image time;
    Image food;
    Graphics buffer;
    
    PanelCanvas(final int width, final int height) {
        this.width = width;
        this.height = height;
    }
    
    public void init() {
        if (this.buffer == null) {
            this.bufferImage = this.createImage(this.width, this.height);
            this.buffer = this.bufferImage.getGraphics();
        }
        this.buffer.setColor(Color.black);
        this.buffer.fillRect(0, 0, this.width, this.height);
    }
    
    public void drawStaticImage(final Image time, final Image food) {
        this.time = time;
        this.food = food;
        if (this.buffer == null) {
            this.init();
        }
        this.buffer.drawImage(food, 0, 20, this);
        this.buffer.drawImage(time, 0, 50, this);
        this.repaint();
    }
    
    public synchronized void drawFoodBar(final int n) {
        final int n2 = 75;
        final int n3 = 24;
        final int n4 = 20;
        final int n5 = 57;
        this.buffer.setColor(Color.white);
        this.buffer.draw3DRect(n4 + 10, n5, n2 + n4 + 10, n3, false);
        this.buffer.setColor(Color.black);
        this.buffer.fillRect(n4 + 10 + 2, n5 + 2, n2 + n4 + 10 - 2, n3 - 2);
        this.buffer.setColor(Color.red);
        this.buffer.fillRect(n4 + 10 + 2, n5 + 2, (n2 + n4 + 10 - 2) * n / 100, n3 - 2);
        this.repaint();
    }
    
    public synchronized void drawTimeBar(final int n) {
        final int n2 = 75;
        final int n3 = 24;
        final int n4 = 20;
        final int n5 = 20;
        this.buffer.setColor(Color.white);
        this.buffer.draw3DRect(n4 + 10, n5, n2 + n4 + 10, n3, false);
        this.buffer.setColor(Color.black);
        this.buffer.fillRect(n4 + 10 + 2, n5 + 2, n2 + n4 + 10 - 2, n3 - 2);
        this.buffer.setColor(Color.red);
        this.buffer.fillRect(n4 + 10 + 2, n5 + 2, (n2 + n4 + 10 - 2) * n / 100, n3 - 2);
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        if (this.bufferImage != null) {
            graphics.drawImage(this.bufferImage, 0, 0, this);
        }
    }
}
