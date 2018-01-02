import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class ShootUpdater extends Thread
{
    EyedMarqueeImageTracker m_imageTracker;
    Image m_stareImage;
    int m_minX;
    int m_minY;
    int m_maxX;
    int m_maxY;
    float m_x;
    float m_y;
    float m_deltX;
    float m_deltY;
    
    ShootUpdater(final EyedMarqueeImageTracker imageTracker, final Image stareImage, final int minX, final int minY, final int maxX, final int maxY) {
        this.m_imageTracker = imageTracker;
        this.m_stareImage = stareImage;
        this.m_minX = minX;
        this.m_minY = minY;
        this.m_maxX = maxX;
        this.m_maxY = maxY;
        this.m_x = this.m_maxX;
        this.m_y = this.m_maxY;
        this.m_imageTracker.m_shootUpdater = this;
    }
    
    public int getX() {
        return (int)(this.m_x + 0.5);
    }
    
    public int getY() {
        return (int)(this.m_y + 0.5);
    }
    
    final void draw(final Graphics graphics) {
        final int x = this.getX();
        final int y = this.getY();
        if (this.m_stareImage != null) {
            graphics.drawImage(this.m_stareImage, x - this.m_stareImage.getWidth(null) / 2, y - this.m_stareImage.getHeight(null) / 2, null);
            return;
        }
        graphics.setColor(Color.black);
        graphics.fillRect(x, y, 1, 1);
    }
    
    public void run() {
        while (true) {
            this.randomize();
            this.m_x += this.m_deltX;
            this.m_y += this.m_deltY;
            final EyedMarqueeImageTracker imageTracker = this.m_imageTracker;
            if (!imageTracker.m_staringAtPointer) {
                imageTracker.updateStare();
            }
            imageTracker.m_imageSink.repaint();
            try {
                Thread.sleep(200L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    void randomize() {
        if (this.m_x <= this.m_minX || this.m_x >= this.m_maxX || this.m_y <= this.m_minY || this.m_y >= this.m_maxY) {
            this.m_deltX = (80 + (int)Math.random() * 250) / 10;
            this.m_deltY = (15 + (int)Math.random() * 45) / 10;
            if (this.m_x > this.m_minX) {
                if (this.m_x < this.m_maxX) {
                    this.m_deltX *= ((Math.random() < 0.5) ? -1 : 1);
                }
                else {
                    this.m_deltX = -this.m_deltX;
                }
            }
            if (this.m_y > this.m_minY) {
                if (this.m_y < this.m_maxY) {
                    this.m_deltY *= ((Math.random() < 0.5) ? -1 : 1);
                    return;
                }
                this.m_deltY = -this.m_deltY;
            }
        }
    }
}
