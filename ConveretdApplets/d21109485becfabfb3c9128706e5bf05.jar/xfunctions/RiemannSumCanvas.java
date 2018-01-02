// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions;

import java.awt.Event;
import xfunctions.functions.Utilities;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import xfunctions.graphs.DisplayCanvas;

class RiemannSumCanvas extends DisplayCanvas
{
    private String[] displayData;
    private Color backColor;
    private Color foreColor;
    private Rectangle displayRect;
    
    public synchronized void invalidateCanvas() {
        this.displayData = null;
        this.displayRect = null;
        super.invalidateCanvas();
    }
    
    public synchronized void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this.displayData == null) {
            return;
        }
        final FontMetrics fontMetrics = graphics.getFontMetrics(graphics.getFont());
        final int height = fontMetrics.getHeight();
        final int ascent = fontMetrics.getAscent();
        int max = 0;
        for (int i = 0; i < this.displayData.length; ++i) {
            max = Math.max(max, fontMetrics.stringWidth(this.displayData[i]));
        }
        max += 20;
        final int n = this.displayData.length * height - fontMetrics.getLeading() + 20;
        final int n2 = this.size().width - max - 20;
        graphics.setColor(this.backColor);
        graphics.fillRect(n2, 10, max, n);
        graphics.setColor(this.foreColor);
        graphics.drawRect(n2, 10, max, n);
        graphics.drawRect(n2 + 1, 11, max - 2, n - 2);
        for (int j = 0; j < this.displayData.length; ++j) {
            graphics.drawString(this.displayData[j], n2 + 10, 20 + j * height + ascent);
        }
        this.displayRect = new Rectangle(n2 - 5, 5, max + 10, n + 10);
    }
    
    synchronized void setDisplayData(final String s, final int n, final double n2, final double n3, final double n4, final double n5, final boolean b) {
        if (b) {
            this.displayData = new String[7];
        }
        else {
            this.displayData = new String[6];
        }
        this.displayData[0] = s;
        this.displayData[1] = "Interval #" + n;
        this.displayData[2] = "left x = " + Utilities.realToString(n2);
        this.displayData[3] = "right x = " + Utilities.realToString(n3);
        if (b) {
            this.displayData[4] = "left y = " + Utilities.realToString(n4);
            this.displayData[5] = "right y = " + Utilities.realToString(n5);
            this.displayData[6] = "Area = " + Utilities.realToString((n3 - n2) * (n4 + n5) / 2.0);
        }
        else {
            this.displayData[4] = "Height = " + Utilities.realToString(n4);
            this.displayData[5] = "Area = " + Utilities.realToString((n3 - n2) * n4);
        }
        this.repaint();
    }
    
    public synchronized boolean mouseDown(final Event event, final int n, final int n2) {
        if (super.mouseDown(event, n, n2)) {
            return true;
        }
        if (this.displayRect != null && this.displayRect.inside(n, n2)) {
            this.invalidateCanvas();
            return true;
        }
        return false;
    }
    
    public synchronized void setErrorMessage(final String errorMessage) {
        this.displayData = null;
        this.displayRect = null;
        super.setErrorMessage(errorMessage);
    }
    
    RiemannSumCanvas() {
        this.backColor = new Color(200, 200, 255);
        this.foreColor = Color.blue;
    }
}
