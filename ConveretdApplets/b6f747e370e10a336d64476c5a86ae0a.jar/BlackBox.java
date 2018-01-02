import java.awt.Graphics;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JComponent;

// 
// Decompiled by Procyon v0.5.30
// 

public class BlackBox extends JComponent
{
    public BlackBox(final int x, final int y, final int width, final int height) {
        this.setBounds(x, y, width, height);
        this.setBorder(new LineBorder(Color.ORANGE, 1));
        this.setBackground(Color.BLACK);
        this.setForeground(Color.BLACK);
        this.setOpaque(true);
    }
    
    protected void paintComponent(final Graphics g) {
        g.setColor(Color.black);
        final int x1 = 0;
        final int y1 = 0;
        final int x2 = this.getWidth();
        final int y2 = this.getHeight();
        g.fillRect(x1, y1, x2, y2);
    }
}
