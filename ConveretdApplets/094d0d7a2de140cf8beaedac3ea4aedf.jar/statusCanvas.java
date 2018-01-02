import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class statusCanvas extends Canvas
{
    Image offS;
    Graphics offscreen;
    boolean first;
    
    public void initial() {
        this.offS = this.createImage(thetriv.w, thetriv.h);
        this.offscreen = this.offS.getGraphics();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.first) {
            this.initial();
            this.first = false;
        }
        if (this.offscreen != null) {
            this.paintApplet(this.offscreen);
            graphics.drawImage(this.offS, 0, 0, this);
            return;
        }
        this.paintApplet(graphics);
    }
    
    public void paintApplet(final Graphics graphics) {
        final Graphics graphics2 = thetriv.bufImage.getGraphics();
        int n;
        if (thetriv.qTotal > 99) {
            n = 10;
        }
        else {
            n = 12;
        }
        final Font font = new Font("System", 1, n);
        final FontMetrics fontMetrics = graphics2.getFontMetrics(font);
        graphics2.setColor(new Color(200, 28, 0).brighter());
        graphics2.setFont(font);
        final String string = "CORRECT: " + thetriv.qCorrect + "  |  " + " INCORRECT: " + thetriv.qIncorrect + "  |  " + "QUESTION " + (thetriv.i + 1) + " OF " + thetriv.qTotal;
        final int w = thetriv.w;
        final int n2 = thetriv.h / 6;
        graphics2.drawImage(thetriv.qPicture, 0, 0, w, n2, this);
        graphics2.drawString(string, (w - fontMetrics.stringWidth(string)) / 2, n2 - 3);
        graphics.drawImage(thetriv.bufImage, 0, 0, null);
    }
    
    statusCanvas() {
        this.first = true;
    }
}
