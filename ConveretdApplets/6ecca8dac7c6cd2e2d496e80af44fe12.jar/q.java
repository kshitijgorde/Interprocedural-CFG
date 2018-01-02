import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;

// 
// Decompiled by Procyon v0.5.30
// 

public class q extends Label
{
    private Image a;
    private String b;
    private Font c;
    private Color d;
    
    public void paint(final Graphics graphics) {
        if (this.a != null) {
            final Dimension size = this.getSize();
            graphics.drawImage(this.a, 0, 0, size.width, size.height, this);
            if (this.b != null) {
                if (this.c != null) {
                    graphics.setFont(this.c);
                }
                if (this.d != null) {
                    graphics.setColor(this.d);
                }
                if (this.b != null) {
                    graphics.drawString(this.b, 5, this.c.getSize() + 1);
                }
            }
        }
    }
    
    public q() {
        this.a = null;
    }
    
    public void setFont(final Font font) {
        this.c = font;
        if (this.a == null) {
            super.setFont(font);
            return;
        }
        this.repaint();
    }
    
    public void a(final Image a) {
        this.a = a;
    }
    
    public void setForeground(final Color color) {
        this.d = color;
        if (this.a == null) {
            super.setForeground(color);
            return;
        }
        this.repaint();
    }
    
    public void setText(final String s) {
        if (this.a == null) {
            super.setText(s);
            return;
        }
        this.b = s;
        this.repaint();
    }
}
