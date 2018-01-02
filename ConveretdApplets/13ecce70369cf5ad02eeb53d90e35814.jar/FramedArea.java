import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class FramedArea extends Panel
{
    private final Color appColor;
    private final Color borderColor;
    
    public FramedArea(final Panel panel, final Color appColor, final Color borderColor) {
        this.appColor = appColor;
        this.borderColor = borderColor;
        this.setLayout(new GridLayout(1, 0));
        this.add(panel);
    }
    
    public Insets getInsets() {
        return new Insets(4, 4, 5, 5);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        graphics.setColor(this.appColor);
        graphics.fillRoundRect(0, 0, size.width - 1, size.height - 1, 15, 15);
        graphics.setColor(this.borderColor);
        graphics.drawRoundRect(0, 0, size.width - 1, size.height - 1, 15, 15);
    }
}