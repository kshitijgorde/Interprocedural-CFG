// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.graphs;

import java.awt.Event;
import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Canvas;

public class DisplayCanvas extends Canvas
{
    private Color errorBackground;
    private Color errorForeground;
    private boolean useOffscreenCanvas;
    protected CoordinateRect coords;
    private String errorMessage;
    private Image OSC;
    private Graphics OSG;
    private boolean OSCvalid;
    private int OSCwidth;
    private int OSCheight;
    
    public Color getErrorBackground() {
        return this.errorBackground;
    }
    
    public void setErrorBackground(final Color errorBackground) {
        this.errorBackground = errorBackground;
    }
    
    public Color getErrorForeground() {
        return this.errorForeground;
    }
    
    public void setErrorForeground(final Color errorForeground) {
        this.errorForeground = errorForeground;
    }
    
    public boolean getUseOffscreenCanvas() {
        return this.useOffscreenCanvas;
    }
    
    public void setUseOffscreenCanvas(final boolean useOffscreenCanvas) {
        if (!(this.useOffscreenCanvas = useOffscreenCanvas)) {
            this.OSC = null;
            this.OSG = null;
        }
    }
    
    public CoordinateRect getCoords() {
        return this.coords;
    }
    
    public void setCoords(final CoordinateRect coords) {
        this.coords = coords;
    }
    
    public synchronized String getErrorMessage() {
        return this.errorMessage;
    }
    
    public synchronized void setErrorMessage(final String s) {
        if (s == null || s.trim().length() == 0) {
            this.clearErrorMessage();
        }
        else {
            this.errorMessage = s.trim();
            this.repaint();
        }
    }
    
    public synchronized void clearErrorMessage() {
        if (this.errorMessage == null) {
            return;
        }
        this.errorMessage = null;
        this.repaint();
    }
    
    public synchronized void invalidateCanvas() {
        this.OSCvalid = false;
        if (this.errorMessage != null) {
            this.clearErrorMessage();
        }
        else {
            this.repaint();
        }
    }
    
    public void releaseResources() {
        this.OSC = null;
        this.OSG = null;
        if (this.coords != null) {
            this.coords.releaseResources();
        }
    }
    
    public DisplayCanvas() {
        this.errorBackground = new Color(220, 255, 220);
        this.errorForeground = new Color(0, 120, 0);
        this.useOffscreenCanvas = true;
        this.OSCwidth = -1;
        this.OSCheight = -1;
        this.setBackground(Color.white);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (this.errorMessage != null) {
            this.drawErrorMessage(graphics);
            return;
        }
        this.checkOSC();
        if (this.OSC != null) {
            graphics.drawImage(this.OSC, 0, 0, this);
        }
        else {
            graphics.setColor(this.getBackground());
            graphics.fillRect(0, 0, this.size().width, this.size().height);
            graphics.setColor(this.getForeground());
            if (this.coords != null) {
                this.coords.draw(graphics, 0, 0, this.size().width, this.size().height);
            }
            this.drawExtraStuff(graphics);
        }
    }
    
    private void drawErrorMessage(final Graphics graphics) {
        if (this.errorMessage == null) {
            return;
        }
        final Font font = new Font("Helvetica", 1, 12);
        final FontMetrics fontMetrics = graphics.getFontMetrics(font);
        final int width = this.size().width;
        final int height = this.size().height;
        final int height2 = fontMetrics.getHeight();
        final int leading = fontMetrics.getLeading();
        final int n = width - 80;
        final int n2 = 30;
        int n3 = (height - 60 - height2) / height2;
        if (n3 <= 0) {
            n3 = 1;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(this.errorMessage, " \t\r\n");
        int n4 = 0;
        final String[] array = new String[n3];
        String s = "   ";
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final String string = String.valueOf(s) + " " + nextToken;
            if (fontMetrics.stringWidth(string) > n) {
                array[n4] = s;
                if (++n4 == n3) {
                    break;
                }
                s = nextToken;
            }
            else {
                s = string;
            }
        }
        if (n4 < n3) {
            array[n4] = s;
            ++n4;
        }
        if (n4 == 1) {
            final String[] array2 = array;
            final int n5 = 0;
            array2[n5] = String.valueOf(array2[n5]) + "    ";
        }
        final int n6 = width - 60;
        final int n7 = (n4 + 1) * height2 + 50;
        int n8 = height / 2 - n7 / 2;
        if (n8 < 0) {
            n8 = 0;
        }
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(this.errorBackground);
        graphics.fillRect(n2, n8, n6, n7);
        graphics.setColor(this.errorForeground);
        graphics.drawRect(n2, n8, n6, n7);
        graphics.drawRect(n2 + 1, n8 + 1, n6 - 2, n7 - 2);
        graphics.drawLine(n2, n8 + 23 + height2, n2 + n6 - 2, n8 + 23 + height2);
        graphics.drawLine(n2, n8 + 24 + height2, n2 + n6 - 2, n8 + 24 + height2);
        graphics.setFont(font);
        graphics.drawString("(Error Message)", width / 2 - fontMetrics.stringWidth("(Error Message)") / 2, n8 + 10 + height2);
        if (n4 == 1) {
            graphics.drawString(array[0], width / 2 - fontMetrics.stringWidth(array[0]) / 2, n8 + 35 + 2 * height2);
        }
        else {
            for (int i = 0; i < n4; ++i) {
                graphics.drawString(array[i], n2 + 10, n8 + 35 + (i + 2) * height2 - leading);
            }
        }
    }
    
    private void checkOSC() {
        if (!this.useOffscreenCanvas) {
            return;
        }
        final int width = this.size().width;
        final int height = this.size().height;
        if (this.OSC == null || this.size().width != this.OSCwidth || this.size().height != this.OSCheight) {
            this.OSCvalid = false;
            this.OSCwidth = this.size().width;
            this.OSCheight = this.size().height;
            try {
                this.OSC = this.createImage(this.OSCwidth, this.OSCheight);
                this.OSG = this.OSC.getGraphics();
            }
            catch (OutOfMemoryError outOfMemoryError) {
                this.OSC = null;
                this.OSG = null;
            }
        }
        if (this.OSC == null || this.OSCvalid) {
            return;
        }
        this.OSG.setColor(this.getBackground());
        this.OSG.fillRect(0, 0, this.OSCwidth, this.OSCheight);
        if (this.coords != null) {
            this.coords.draw(this.OSG, 0, 0, this.OSCwidth, this.OSCheight);
        }
        this.drawExtraStuff(this.OSG);
        this.OSCvalid = true;
    }
    
    protected void drawExtraStuff(final Graphics graphics) {
    }
    
    protected Graphics getOSG() {
        if (this.OSC != null && this.OSCvalid) {
            return this.OSG;
        }
        return null;
    }
    
    public synchronized boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.errorMessage != null) {
            this.clearErrorMessage();
            return true;
        }
        return super.mouseDown(event, n, n2);
    }
}
