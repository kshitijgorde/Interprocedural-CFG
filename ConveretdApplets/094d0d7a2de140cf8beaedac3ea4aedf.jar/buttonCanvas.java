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

class buttonCanvas extends Canvas
{
    Image offS;
    Graphics offscreen;
    boolean first;
    int barLength;
    
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
        final Font font = new Font("System", 1, 15);
        final FontMetrics fontMetrics = graphics2.getFontMetrics(font);
        graphics2.setColor(new Color(200, 28, 0).brighter());
        graphics2.setFont(font);
        final int w = thetriv.w;
        final int n = thetriv.h / 6;
        if (thetriv.start) {
            final String s = "CLICK HERE TO PLAY";
            graphics2.fill3DRect(0, 0, 310, 20, true);
            graphics2.setColor(Color.black);
            graphics2.drawString(s, (w - fontMetrics.stringWidth(s)) / 2, 16);
        }
        else {
            if (thetriv.buttonMode == 0) {
                graphics2.drawImage(thetriv.bPicture, 0, 0, w, n * 3, this);
            }
            if (thetriv.buttonMode == 1) {
                graphics2.drawImage(thetriv.bPicture, 0, -20, w, n * 3, this);
            }
            if (thetriv.buttonMode == 2) {
                graphics2.drawImage(thetriv.bPicture, 0, -40, w, n * 3, this);
            }
        }
        if (thetriv.inPlay && !thetriv.start) {
            graphics2.setColor(Color.black);
            graphics2.fill3DRect(155, 0, 155, 20, true);
            graphics2.setColor(new Color(200, 28, 0).brighter());
            graphics2.fill3DRect(155, 0, this.barLength, 20, true);
            ++this.barLength;
            if (this.barLength > 155) {
                this.barLength = 0;
                if (thetriv.inPlay) {
                    thetriv.checkAnswer();
                }
                thetriv.currentAnswer = 0;
                thetriv.sPage.repaint();
                thetriv.qPage.repaint();
                ++thetriv.i;
                if (thetriv.inPlay) {
                    thetriv.questionUser();
                }
                if (thetriv.i >= thetriv.qTotal) {
                    thetriv.inPlay = false;
                    thetriv.i = thetriv.qTotal - 1;
                    thetriv.qPage.repaint();
                }
            }
        }
        graphics.drawImage(thetriv.bufImage, 0, 0, null);
    }
    
    public void resetTimer() {
        this.barLength = 0;
    }
    
    buttonCanvas() {
        this.first = true;
    }
}
