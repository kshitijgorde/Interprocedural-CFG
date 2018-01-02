import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class EyedMarqueeEyesUpdater extends Thread
{
    EyedMarqueeImageTracker m_imageTracker;
    Color m_eyeColor;
    int m_stareX;
    int m_stareY;
    int m_centerX;
    int m_centerY;
    int m_eyeSize;
    boolean m_bigEyeBall;
    
    EyedMarqueeEyesUpdater(final EyedMarqueeImageTracker imageTracker, final int eyeSize, final Color eyeColor, final int centerX, final int centerY) {
        this.m_imageTracker = imageTracker;
        this.m_imageTracker.m_eyesUpdater = this;
        this.m_eyeColor = eyeColor;
        this.m_eyeSize = eyeSize;
        this.m_centerX = centerX;
        this.m_centerY = centerY;
    }
    
    public int getStareX() {
        return this.m_stareX;
    }
    
    public int getStareY() {
        return this.m_stareY;
    }
    
    public void run() {
        while (true) {
            final EyedMarqueeImageTracker imageTracker = this.m_imageTracker;
            if (!imageTracker.m_staringAtPointer) {
                imageTracker.updateStare();
            }
            imageTracker.m_imageSink.repaint();
            int n;
            if (this.m_bigEyeBall) {
                n = 100;
            }
            else {
                n = 5000 + (int)Math.random() * 10000;
            }
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
            this.m_bigEyeBall = !this.m_bigEyeBall;
        }
    }
    
    final void updateStare(final int stareX, final int stareY) {
        if (stareX != this.m_stareX || stareY != this.m_stareY) {
            this.m_stareX = stareX;
            this.m_stareY = stareY;
            final EyedMarqueeImageTracker imageTracker = this.m_imageTracker;
            if (!imageTracker.m_staringAtPointer) {
                imageTracker.updateStare();
            }
            imageTracker.m_imageSink.repaint();
        }
    }
    
    final void draw(final Graphics graphics) {
        final int centerX = this.m_centerX;
        final int centerY = this.m_centerY;
        final int n = centerX - this.m_eyeSize / 2;
        this.drawEye(graphics, n, centerY);
        this.drawEye(graphics, n + this.m_eyeSize, centerY);
    }
    
    final void drawEye(final Graphics graphics, final int n, final int n2) {
        final int eyeSize = this.m_eyeSize;
        final int eyeSize2 = this.m_eyeSize;
        final int x = this.m_imageTracker.m_bounds.x;
        final int y = this.m_imageTracker.m_bounds.y;
        final int n3 = n - eyeSize / 2;
        final int n4 = n2 - eyeSize2 / 2;
        graphics.setColor(this.m_eyeColor);
        graphics.fillOval(n3, n4, eyeSize, eyeSize2);
        graphics.setColor(Color.black);
        graphics.drawOval(n3, n4, eyeSize, eyeSize2);
        double n5;
        if (this.m_bigEyeBall) {
            n5 = eyeSize / 2 - 6;
        }
        else {
            n5 = eyeSize / 2 - 5;
        }
        final double n6 = this.m_stareX - x - n;
        double n7 = this.m_stareY - y - n2;
        double n8 = 1.0;
        double n9;
        double n10;
        if (n7 != 0.0) {
            if (n7 < 0.0) {
                n7 = -n7;
                n8 = -1.0;
            }
            final double atan = Math.atan(n6 / n7);
            n9 = n5 * Math.sin(atan);
            n10 = n5 * Math.cos(atan);
        }
        else {
            if (n6 < 0.0) {
                n9 = -n5;
            }
            else {
                n9 = n5;
            }
            n10 = 0.0;
        }
        if (Math.abs(n6) < Math.abs(n9)) {
            n9 = n6;
        }
        if (Math.abs(n7) < Math.abs(n10)) {
            n10 = n7;
        }
        final double n11 = n8 * n10;
        final int n12 = (int)Math.round(n + n9);
        final int n13 = (int)Math.round(n2 + n11);
        graphics.setColor(Color.black);
        if (this.m_bigEyeBall) {
            graphics.fillRect(x + n12 - 2, y + n13 - 2, 5, 5);
            return;
        }
        graphics.fillRect(x + n12 - 1, y + n13 - 1, 3, 3);
    }
}
