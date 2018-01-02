import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class LinePanel extends Panel
{
    private Color borderColor;
    
    public LinePanel() {
        this.borderColor = Color.black;
        this.setLayout(null);
    }
    
    public void setBorderColor(final Color borderColor) {
        this.borderColor = borderColor;
    }
    
    public void paint(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        super.paint(graphics);
        graphics.setColor(this.borderColor);
        graphics.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
    }
}
