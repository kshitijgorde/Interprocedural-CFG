import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

class EyedMarqueeTextUpdater extends Thread
{
    EyedMarqueeImageTracker m_imageTracker;
    Font m_textFont;
    Color m_textColor;
    int m_textWidth;
    int m_textHeight;
    int m_textX;
    EyedMarqueeTextInfoProvider m_textInfoProvider;
    
    EyedMarqueeTextUpdater(final EyedMarqueeImageTracker imageTracker, final EyedMarqueeTextInfoProvider textInfoProvider, final Color textColor) {
        this.m_imageTracker = imageTracker;
        this.m_imageTracker.m_textUpdater = this;
        this.m_textInfoProvider = textInfoProvider;
        this.m_textColor = textColor;
    }
    
    public String getText() {
        return this.m_textInfoProvider.getText();
    }
    
    public int getTextTop() {
        return this.m_textInfoProvider.getTextTop();
    }
    
    public synchronized void reset() {
        this.m_textFont = null;
        final String text = this.m_textInfoProvider.getText();
        final int textSpeed = this.m_textInfoProvider.getTextSpeed();
        if (text == null || text.equals("") || textSpeed == 0) {
            this.suspend();
        }
        else {
            this.resume();
        }
        final EyedMarqueeImageTracker imageTracker = this.m_imageTracker;
        if (!imageTracker.m_staringAtPointer) {
            imageTracker.updateStare();
        }
        imageTracker.m_imageSink.repaint();
    }
    
    public void run() {
        while (true) {
            final EyedMarqueeImageTracker imageTracker = this.m_imageTracker;
            if (!imageTracker.m_staringAtPointer) {
                imageTracker.updateStare();
            }
            imageTracker.m_imageSink.repaint();
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
            this.moveMessage();
        }
    }
    
    final synchronized void draw(final Graphics graphics) {
        String text = this.m_textInfoProvider.getText();
        if (text == null) {
            text = "";
        }
        if (this.m_textFont == null) {
            final int textSpeed = this.m_textInfoProvider.getTextSpeed();
            this.m_textFont = this.m_textInfoProvider.getTextFont();
            if (this.m_textFont == null) {
                this.m_textFont = graphics.getFont();
            }
            graphics.setFont(this.m_textFont);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            this.m_textWidth = fontMetrics.stringWidth(text);
            this.m_textHeight = fontMetrics.getHeight();
            if (textSpeed > 0) {
                this.m_textX = this.m_imageTracker.m_bounds.width - 5;
            }
            else if (textSpeed < 0) {
                this.m_textX = -this.m_textWidth;
            }
            else {
                this.m_textX = (this.m_imageTracker.m_bounds.width - this.m_textWidth) / 2;
            }
        }
        if (!text.equals("")) {
            final int textTop = this.m_textInfoProvider.getTextTop();
            graphics.setFont(this.m_textFont);
            graphics.setColor(this.m_textColor);
            graphics.drawString(text, this.m_textX, textTop);
        }
    }
    
    final synchronized void moveMessage() {
        final int textSpeed = this.m_textInfoProvider.getTextSpeed();
        if (textSpeed != 0) {
            this.m_textX -= textSpeed;
            if (textSpeed > 0 && this.m_textX + this.m_textWidth < 0) {
                this.m_textX = this.m_imageTracker.m_bounds.width;
                return;
            }
            if (textSpeed < 0 && this.m_textX > this.m_imageTracker.m_bounds.width) {
                this.m_textX = -this.m_textWidth;
            }
        }
    }
}
