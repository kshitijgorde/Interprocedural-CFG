import java.awt.Insets;
import java.awt.Event;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class suiTablabel extends Canvas
{
    int top;
    int left;
    int down;
    int right;
    String title;
    int length;
    int width;
    boolean on;
    boolean enter;
    
    suiTablabel(final String title) {
        this.on = false;
        this.enter = false;
        this.title = title;
        this.length = 20 + title.length() * 10;
        this.width = 25;
    }
    
    public Dimension getMinimizeSize() {
        return new Dimension(this.length, this.width);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.length, this.width);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setFont(new Font("Helvetica", 0, 10));
        final int descent = graphics.getFontMetrics().getDescent();
        final int height = graphics.getFontMetrics().getHeight();
        graphics.setColor(new Color(192, 192, 192));
        graphics.fillRect(0, 0, this.length, this.width);
        if (!this.on) {
            if (!this.enter) {
                graphics.setColor(Color.black);
            }
            else {
                graphics.setColor(Color.blue);
            }
            graphics.drawString(this.title, 10, 4 + height - descent);
            graphics.setColor(new Color(0, 0, 0));
            graphics.drawLine(this.length - 1, 4, this.length - 1, this.width - 1);
            graphics.drawLine(this.length - 1, 4, this.length - 3, 2);
            graphics.setColor(new Color(240, 240, 240));
            graphics.drawLine(3, 2, this.length - 3, 2);
            graphics.drawLine(1, 4, 1, this.width - 1);
            graphics.drawLine(0, this.width - 1, this.length - 1, this.width - 1);
            graphics.drawLine(3, 2, 1, 4);
            return;
        }
        graphics.setFont(new Font("Helvetica", 1, 10));
        if (!this.enter) {
            graphics.setColor(Color.black);
        }
        else {
            graphics.setColor(Color.blue);
        }
        graphics.drawString(this.title, 8, 2 + height - descent);
        graphics.setColor(new Color(0, 0, 0));
        graphics.drawLine(this.length - 2, 2, this.length - 2, this.width - 1);
        graphics.drawLine(this.length - 2, 2, this.length - 4, 0);
        graphics.setColor(new Color(240, 240, 240));
        graphics.drawLine(2, 0, this.length - 4, 0);
        graphics.drawLine(0, 2, 0, this.width - 1);
        graphics.drawLine(this.length - 4, this.width - 1, this.length - 1, this.width - 1);
        graphics.drawLine(2, 0, 0, 2);
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.enter = false;
        this.repaint();
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.enter = true;
        this.repaint();
        return true;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public Insets getInsets() {
        return new Insets(this.top, this.left, this.down, this.right);
    }
}
