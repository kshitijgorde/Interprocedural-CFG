import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Jumptext extends Applet implements Runnable
{
    Thread blinker;
    String message1;
    String message2;
    Font font1;
    Font font2;
    int speed;
    int lastX1;
    int lastY1;
    int directX1;
    int directY1;
    int lastX2;
    int lastY2;
    int directX2;
    int directY2;
    
    public void init() {
        final Dimension size = this.size();
        final String parameter = this.getParameter("speed");
        this.speed = ((parameter == null) ? 50 : Integer.valueOf(parameter));
        this.font1 = new Font("TimesRoman", 2, 24);
        final String parameter2 = this.getParameter("message1");
        this.message1 = ((parameter2 == null) ? "CATNET Internet Service" : parameter2);
        this.lastX1 = (int)(Math.random() * (size.width - 1));
        this.lastY1 = (int)((size.height - this.font1.getSize() - 1) * Math.random());
        this.directX1 = 3;
        this.directY1 = 3;
        this.font2 = new Font("TimesRoman", 0, 20);
        final String parameter3 = this.getParameter("message2");
        this.message2 = ((parameter3 == null) ? "System Intelligent" : parameter3);
        this.lastX2 = (int)(Math.random() * (size.width - 1));
        this.lastY2 = (int)((size.height - this.font2.getSize() - 1) * Math.random());
        this.directX2 = -3;
        this.directY2 = -3;
    }
    
    public void start() {
        this.setBackground(Color.black);
        if (this.blinker == null) {
            (this.blinker = new Thread(this, "Blink")).start();
        }
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        graphics.setColor(Color.black);
        graphics.setFont(this.font1);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int stringWidth = fontMetrics.stringWidth(" ");
        int lastX1 = this.lastX1;
        final int lastY1 = this.lastY1;
        final StringTokenizer stringTokenizer = new StringTokenizer(this.message1);
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final int n = fontMetrics.stringWidth(nextToken) + stringWidth;
            if (lastX1 > size.width) {
                lastX1 -= size.width;
            }
            graphics.setColor(new Color((int)(Math.random() * 256.0), (int)(Math.random() * 256.0), (int)(Math.random() * 256.0)));
            graphics.drawString(nextToken, lastX1, lastY1);
            lastX1 += n;
        }
        if (Math.random() > 0.99) {
            this.directX1 = -this.directX1;
        }
        this.lastX1 += this.directX1;
        if (this.lastX1 >= size.width) {
            this.lastX1 = 0;
        }
        else if (this.lastX1 < 0) {
            this.lastX1 = size.width - 1;
        }
        this.lastY1 += this.directY1;
        if (this.lastY1 >= size.height) {
            this.directY1 = -3;
        }
        else if (this.lastY1 < this.font1.getSize()) {
            this.directY1 = 3;
        }
        graphics.setColor(Color.black);
        graphics.setFont(this.font2);
        final FontMetrics fontMetrics2 = graphics.getFontMetrics();
        final int stringWidth2 = fontMetrics2.stringWidth(" ");
        int lastX2 = this.lastX2;
        final int lastY2 = this.lastY2;
        final StringTokenizer stringTokenizer2 = new StringTokenizer(this.message2);
        while (stringTokenizer2.hasMoreTokens()) {
            final String nextToken2 = stringTokenizer2.nextToken();
            final int n2 = fontMetrics2.stringWidth(nextToken2) + stringWidth2;
            if (lastX2 > size.width) {
                lastX2 -= size.width;
            }
            graphics.setColor(new Color((int)(Math.random() * 256.0), (int)(Math.random() * 256.0), (int)(Math.random() * 256.0)));
            graphics.drawString(nextToken2, lastX2, lastY2);
            lastX2 += n2;
        }
        if (Math.random() > 0.99) {
            this.directX2 = -this.directX2;
        }
        this.lastX2 += this.directX2;
        if (this.lastX2 >= size.width) {
            this.lastX2 = 0;
        }
        else if (this.lastX2 < 0) {
            this.lastX2 = size.width - 1;
        }
        this.lastY2 += this.directY2;
        if (this.lastY2 >= size.height) {
            this.directY2 = -3;
            return;
        }
        if (this.lastY2 < this.font1.getSize()) {
            this.directY2 = 3;
        }
    }
    
    public void stop() {
        (this.blinker = null).stop();
    }
    
    public void run() {
        while (this.blinker != null) {
            this.repaint();
            try {
                Thread.sleep(this.speed);
            }
            catch (InterruptedException ex) {}
        }
    }
}
