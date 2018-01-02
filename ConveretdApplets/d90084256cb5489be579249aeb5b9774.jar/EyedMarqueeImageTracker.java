import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

class EyedMarqueeImageTracker implements ImageObserver
{
    Rectangle m_bounds;
    EyedMarqueeImageSink m_imageSink;
    HotSpotTracker m_hotSpotTracker;
    EyedMarqueeTextUpdater m_textUpdater;
    EyedMarqueeEyesUpdater m_eyesUpdater;
    ShootUpdater m_shootUpdater;
    boolean m_staringAtPointer;
    Image m_backgroundImage;
    Color m_backgroundColor;
    int m_imageX;
    int m_imageY;
    
    EyedMarqueeImageTracker(final EyedMarqueeImageSink imageSink, final Rectangle bounds) {
        this.m_imageSink = imageSink;
        this.m_bounds = bounds;
        this.m_backgroundColor = Color.yellow;
    }
    
    final void setHotSpotTracker(final HotSpotTracker hotSpotTracker) {
        this.m_hotSpotTracker = hotSpotTracker;
    }
    
    final void setTextUpdater(final EyedMarqueeTextUpdater textUpdater) {
        this.m_textUpdater = textUpdater;
    }
    
    final void setEyesUpdater(final EyedMarqueeEyesUpdater eyesUpdater) {
        this.m_eyesUpdater = eyesUpdater;
    }
    
    final void setShootUpdater(final ShootUpdater shootUpdater) {
        this.m_shootUpdater = shootUpdater;
    }
    
    final void setBackgroundImage(final int imageX, final int imageY, final Image backgroundImage) {
        this.m_imageX = imageX;
        this.m_imageY = imageY;
        this.m_backgroundImage = backgroundImage;
    }
    
    final void setBackgroundColor(final Color backgroundColor) {
        this.m_backgroundColor = backgroundColor;
    }
    
    final void mouseEnter(final int n, final int n2) {
        this.m_staringAtPointer = true;
        if (this.m_eyesUpdater != null) {
            this.m_eyesUpdater.updateStare(n, n2);
        }
        if (this.m_hotSpotTracker != null) {
            final HotSpotTracker hotSpotTracker = this.m_hotSpotTracker;
            hotSpotTracker.overHotSpot(hotSpotTracker.mapHotSpot(n, n2));
        }
    }
    
    final void mouseExit(final int n, final int n2) {
        this.updateStare();
        if (this.m_hotSpotTracker != null) {
            this.m_hotSpotTracker.overHotSpot(null);
        }
    }
    
    final void mouseMove(final int n, final int n2) {
        this.m_staringAtPointer = true;
        if (this.m_eyesUpdater != null) {
            this.m_eyesUpdater.updateStare(n, n2);
        }
        if (this.m_hotSpotTracker != null) {
            final HotSpotTracker hotSpotTracker = this.m_hotSpotTracker;
            hotSpotTracker.overHotSpot(hotSpotTracker.mapHotSpot(n, n2));
        }
    }
    
    final void mouseDrag(final int n, final int n2) {
        this.m_staringAtPointer = true;
        if (this.m_eyesUpdater != null) {
            this.m_eyesUpdater.updateStare(n, n2);
        }
        if (this.m_hotSpotTracker != null) {
            final HotSpotTracker hotSpotTracker = this.m_hotSpotTracker;
            hotSpotTracker.overHotSpot(hotSpotTracker.mapHotSpot(n, n2));
        }
    }
    
    final void mouseDown(final int n, final int n2) {
        if (this.m_hotSpotTracker != null) {
            final HotSpotTracker hotSpotTracker = this.m_hotSpotTracker;
            hotSpotTracker.m_mouseDown = true;
            hotSpotTracker.m_hotSpotSink.repaint();
        }
    }
    
    final void mouseUp(final int n, final int n2) {
        if (this.m_hotSpotTracker != null) {
            this.m_hotSpotTracker.mouseUp(n, n2);
        }
    }
    
    final void drawImage(final Image image) {
        final Graphics graphics = image.getGraphics();
        graphics.setColor(this.m_backgroundColor);
        graphics.fillRect(this.m_bounds.x, this.m_bounds.y, this.m_bounds.width, this.m_bounds.height);
        if (this.m_backgroundImage != null) {
            graphics.drawImage(this.m_backgroundImage, this.m_bounds.x + this.m_imageX, this.m_bounds.y + this.m_imageY, this);
        }
        if (this.m_eyesUpdater != null) {
            this.m_eyesUpdater.draw(graphics);
        }
        if (this.m_shootUpdater != null) {
            this.m_shootUpdater.draw(graphics);
        }
        if (this.m_textUpdater != null) {
            this.m_textUpdater.draw(graphics);
        }
        graphics.dispose();
        if (this.m_hotSpotTracker != null) {
            this.m_hotSpotTracker.drawHotSpot(image);
        }
    }
    
    final void updateStare() {
        this.m_staringAtPointer = false;
        if (this.m_eyesUpdater != null) {
            if (this.m_shootUpdater != null) {
                this.m_eyesUpdater.updateStare(this.m_shootUpdater.getX(), this.m_shootUpdater.getY());
                return;
            }
            if (this.m_textUpdater != null) {
                this.m_eyesUpdater.updateStare(this.m_bounds.x + this.m_textUpdater.m_textX + this.m_textUpdater.m_textWidth / 2, this.m_bounds.y + this.m_textUpdater.getTextTop() + this.m_textUpdater.m_textHeight / 2);
            }
        }
    }
    
    final void updateStare(final int n, final int n2) {
        this.m_staringAtPointer = true;
        if (this.m_eyesUpdater != null) {
            this.m_eyesUpdater.updateStare(n, n2);
        }
    }
    
    final void resume() {
        if (this.m_textUpdater != null) {
            this.m_textUpdater.resume();
        }
        if (this.m_eyesUpdater != null) {
            this.m_eyesUpdater.resume();
        }
        if (this.m_shootUpdater != null) {
            this.m_shootUpdater.resume();
        }
    }
    
    final void suspend() {
        if (this.m_textUpdater != null) {
            this.m_textUpdater.suspend();
        }
        if (this.m_eyesUpdater != null) {
            this.m_eyesUpdater.suspend();
        }
        if (this.m_shootUpdater != null) {
            this.m_shootUpdater.suspend();
        }
    }
    
    final void destroy() {
        if (this.m_textUpdater != null) {
            this.m_textUpdater.destroy();
        }
        if (this.m_eyesUpdater != null) {
            this.m_eyesUpdater.destroy();
        }
        if (this.m_shootUpdater != null) {
            this.m_shootUpdater.destroy();
        }
    }
    
    final void notifyUpdaterChanged(final Object o) {
        if (!this.m_staringAtPointer) {
            this.updateStare();
        }
        this.m_imageSink.repaint();
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x60) != 0x0) {
            this.m_imageSink.repaint();
            return false;
        }
        return true;
    }
}
