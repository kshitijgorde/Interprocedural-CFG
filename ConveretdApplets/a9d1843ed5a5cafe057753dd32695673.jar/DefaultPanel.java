import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class DefaultPanel extends Panel
{
    private String chr;
    private int size;
    
    public DefaultPanel() {
        this.chr = "A";
        this.size = 100;
        this.setBackground(Color.white);
    }
    
    public void paint(final Graphics graphics) {
        final Font font = new Font("Helvetica", 0, this.size);
        graphics.setFont(font);
        final FontMetrics fontMetrics = graphics.getFontMetrics(font);
        graphics.drawString(this.chr, (this.getSize().width - fontMetrics.stringWidth(this.chr)) / 2, (this.getSize().height + fontMetrics.getMaxAscent() - fontMetrics.getMaxDescent()) / 2);
    }
    
    public void setString(final String chr) {
        this.chr = chr;
        this.repaint();
    }
    
    public String getString() {
        return this.chr;
    }
    
    public void setStringSize(final int size) {
        this.size = size;
        this.repaint();
    }
    
    public int getStringSize() {
        return this.size;
    }
}
