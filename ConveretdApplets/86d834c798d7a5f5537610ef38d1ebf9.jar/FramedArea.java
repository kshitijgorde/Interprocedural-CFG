import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class FramedArea extends Panel
{
    String title;
    SlidePuzzle controller;
    
    public FramedArea(final SlidePuzzle controller, final String title) {
        this.title = title;
        this.controller = controller;
    }
    
    public Insets getInsets() {
        return new Insets(30, 6, 6, 6);
    }
    
    public Insets insets() {
        return this.getInsets();
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        graphics.setColor(new Color(191, 191, 191));
        graphics.fillRect(0, 0, size.width - 1, size.height - 1);
        GraphicsExtension.drawBetter3DRect(graphics, 0, 0, size.width - 1, size.height - 1, true);
        GraphicsExtension.drawBetter3DRect(graphics, 4, 27, size.width - 5 - 4, size.height - 5 - 27, false);
        graphics.setColor(new Color(0, 0, 127));
        graphics.fillRect(4, 4, size.width - 5 - 4, 23);
        graphics.setColor(Color.white);
        graphics.setFont(new Font(graphics.getFont().getName(), 0, 8));
        graphics.drawString(this.title, 8, 26 - (23 - graphics.getFontMetrics().getAscent()) / 2);
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
}
