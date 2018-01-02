import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class QuickPanel extends Panel
{
    public int typeofpanel;
    public static final int RAISE = 1;
    public static final int SUNK = 2;
    public static final int ETCH = 3;
    protected Insets insets;
    
    public QuickPanel() {
        this.typeofpanel = 0;
        this.insets = new Insets(3, 3, 3, 3);
        this.typeofpanel = 3;
    }
    
    public QuickPanel(final int typeofpanel) {
        this.typeofpanel = 0;
        this.insets = new Insets(3, 3, 3, 3);
        this.typeofpanel = typeofpanel;
    }
    
    public void setPanelInsets(final Insets insets) {
        this.insets = insets;
    }
    
    public Insets insets() {
        return this.insets;
    }
    
    public void paint(final Graphics graphics) {
        if (this.typeofpanel == 1) {
            this.paintRaisedPanel(graphics);
        }
        else if (this.typeofpanel == 2) {
            this.paintSunkPanel(graphics);
        }
        else if (this.typeofpanel == 3) {
            this.paintEtchPanel(graphics);
        }
    }
    
    protected void paintRaisedPanel(final Graphics graphics) {
        super.paint(graphics);
        final int n = this.size().width - 1;
        final int n2 = this.size().height - 1;
        graphics.setColor(Color.darkGray);
        graphics.drawLine(0, n2, n - 1, n2);
        graphics.drawLine(n, 0, n, n2);
        graphics.setColor(Color.gray);
        graphics.drawLine(1, n2 - 1, n - 2, n2 - 1);
        graphics.drawLine(n - 1, 1, n - 1, n2 - 1);
        graphics.setColor(Color.white);
        graphics.drawLine(0, 0, 0, n2 - 1);
        graphics.drawLine(0, 0, n - 1, 0);
    }
    
    protected void paintSunkPanel(final Graphics graphics) {
        super.paint(graphics);
        final int n = this.size().width - 1;
        final int n2 = this.size().height - 1;
        graphics.setColor(Color.gray);
        graphics.drawLine(0, 0, n - 1, 0);
        graphics.drawLine(0, 0, 0, n2 - 1);
        graphics.setColor(Color.black);
        graphics.drawLine(1, 1, n - 2, 1);
        graphics.drawLine(1, 1, 1, n2 - 2);
        graphics.setColor(Color.white);
        graphics.drawLine(0, n2, n, n2);
        graphics.drawLine(1, n2 - 1, n - 1, n2 - 1);
        graphics.drawLine(n, 0, n, n2);
        graphics.drawLine(n - 1, 1, n - 1, n2 - 1);
    }
    
    protected void paintEtchPanel(final Graphics graphics) {
        super.paint(graphics);
        final int n = this.size().width - 1;
        final int n2 = this.size().height - 1;
        graphics.setColor(Color.gray);
        graphics.drawLine(0, 0, 0, n2 - 1);
        graphics.drawLine(0, 0, n - 1, 0);
        graphics.setColor(Color.white);
        graphics.drawLine(1, 1, 1, n2 - 2);
        graphics.drawLine(1, 1, n - 3, 1);
        graphics.setColor(Color.gray);
        graphics.drawLine(0, n2 - 1, n - 1, n2 - 1);
        graphics.drawLine(n - 1, 2, n - 1, n2);
        graphics.setColor(Color.white);
        graphics.drawLine(0, n2, n, n2);
        graphics.drawLine(n, 0, n, n2);
    }
}
