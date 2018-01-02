import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

// 
// Decompiled by Procyon v0.5.30
// 

public class Arrow extends JComponent
{
    private boolean _boolOut;
    
    public Arrow(final int x, final int y, final int width, final int height) {
        this._boolOut = false;
        this.setBounds(x, y, width, height);
        this.setOpaque(false);
    }
    
    public Arrow(final int x, final int y, final int width, final int height, final boolean out) {
        this._boolOut = out;
        this.setBounds(x, y, width, height);
        this.setOpaque(false);
    }
    
    protected void paintComponent(final Graphics g) {
        g.setColor(Color.black);
        final int x1 = 1;
        final int x2 = this.getWidth() - 1;
        final int y = this.getHeight() / 2;
        g.drawLine(x1, y, x2, y);
        g.drawLine(x1, y + 1, x2, y + 1);
        if (this._boolOut) {
            g.drawLine(x1, y, x1 + 5, y - 5);
            g.drawLine(x1 + 1, y, x1 + 6, y - 5);
            g.drawLine(x1, y + 1, x1 + 5, y + 6);
            g.drawLine(x1 + 1, y + 1, x1 + 6, y + 6);
        }
        else {
            g.drawLine(x2, y, x2 - 5, y - 5);
            g.drawLine(x2 - 1, y, x2 - 6, y - 5);
            g.drawLine(x2, y + 1, x2 - 5, y + 6);
            g.drawLine(x2 - 1, y + 1, x2 - 6, y + 6);
        }
    }
}
