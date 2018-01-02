import java.awt.Font;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class FButton
{
    private String text;
    private String actionCommand;
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private Color background;
    private Color foreground;
    private Color mouseOver;
    private Color unabled;
    private FontMetrics metrics;
    private boolean mouseEntered;
    private boolean buttonDown;
    private boolean enabled;
    
    public FButton(final String text, final String actionCommand, final int x, final int y, final int width, final int height) {
        this.background = Color.lightGray;
        this.foreground = Color.black;
        this.mouseOver = new Color(60, 115, 210);
        this.unabled = Color.gray;
        this.xPos = x;
        this.yPos = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.actionCommand = actionCommand;
        this.mouseEntered = false;
        this.buttonDown = false;
        this.enabled = true;
    }
    
    public void paint(final Graphics g) {
        g.setFont(new Font("SansSerif", 1, 11));
        this.metrics = g.getFontMetrics();
        final int tx = this.xPos + (this.width - this.metrics.stringWidth(this.text)) / 2;
        final int ty = this.yPos + (this.height - this.metrics.getHeight()) / 2 + this.metrics.getAscent();
        g.setColor(this.background);
        g.fillRect(this.xPos, this.yPos, this.width - 1, this.height - 1);
        g.draw3DRect(this.xPos, this.yPos, this.width - 1, this.height - 1, false);
        if (!this.buttonDown) {
            g.draw3DRect(this.xPos + 1, this.yPos + 1, this.width - 3, this.height - 3, true);
        }
        if (this.enabled) {
            final Color c = this.mouseEntered ? this.mouseOver : this.foreground;
            g.setColor(c);
            final int delta = this.buttonDown ? 1 : 0;
            g.drawString(this.text, tx + delta, ty + delta);
        }
        else {
            g.setColor(Color.white);
            g.drawString(this.text, tx + 1, ty + 1);
            g.setColor(this.unabled);
            g.drawString(this.text, tx, ty);
        }
    }
    
    public void setEnabled(final boolean b) {
        this.enabled = b;
    }
    
    public void checkMouseMoved(final int x, final int y) {
        if (this.enabled) {
            this.mouseEntered = (x >= this.xPos && x <= this.xPos + this.width && y >= this.yPos && y <= this.yPos + this.height);
        }
    }
    
    public void checkMousePressed(final int x, final int y) {
        if (this.enabled) {
            this.buttonDown = (x >= this.xPos && x <= this.xPos + this.width && y >= this.yPos && y <= this.yPos + this.height);
        }
    }
    
    public String checkMouseReleased(final int x, final int y) {
        if (!this.enabled) {
            return null;
        }
        if (this.buttonDown && x >= this.xPos && x <= this.xPos + this.width && y >= this.yPos && y <= this.yPos + this.height) {
            this.buttonDown = false;
            return this.actionCommand;
        }
        return null;
    }
}
