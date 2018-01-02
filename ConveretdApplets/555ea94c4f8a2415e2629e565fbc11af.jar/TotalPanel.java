import java.awt.Graphics;
import java.awt.Color;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class TotalPanel extends Panel
{
    String s;
    
    public TotalPanel() {
        this.setSize(24, 15);
        this.setBackground(Color.black);
        this.setForeground(Color.white);
    }
    
    public TotalPanel(final int n, final int n2) {
        this();
        this.setLocation(n, n2);
    }
    
    public void setString(final String s) {
        this.s = s;
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        super.paint(graphics);
        graphics.drawString(this.s, 2, 13);
    }
}
