// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import javax.swing.ToolTipManager;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

public class ClickLabel extends JLabel implements MouseListener
{
    public static Color plainColor;
    public static Color overColor;
    public static Color downColor;
    public Color textColor;
    
    static {
        ClickLabel.plainColor = Color.black;
        ClickLabel.overColor = Color.red;
        ClickLabel.downColor = Color.blue;
    }
    
    public ClickLabel() {
        this.textColor = ClickLabel.plainColor;
        this.addMouseListener(this);
    }
    
    public ClickLabel(final String s) {
        super(s);
        this.textColor = ClickLabel.plainColor;
        this.addMouseListener(this);
    }
    
    public ClickLabel(final String s, final int n) {
        super(s, n);
        this.textColor = ClickLabel.plainColor;
        this.addMouseListener(this);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final int initialDelay = ToolTipManager.sharedInstance().getInitialDelay();
        ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setInitialDelay(initialDelay);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.textColor != ClickLabel.overColor) {
            this.textColor = ClickLabel.overColor;
            this.setForeground(ClickLabel.overColor);
            this.repaint();
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.textColor != ClickLabel.plainColor) {
            this.textColor = ClickLabel.plainColor;
            this.setForeground(ClickLabel.plainColor);
            this.repaint();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.textColor != ClickLabel.downColor) {
            this.textColor = ClickLabel.downColor;
            this.setForeground(ClickLabel.downColor);
            this.repaint();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.textColor != ClickLabel.plainColor) {
            this.textColor = ClickLabel.plainColor;
            this.setForeground(ClickLabel.plainColor);
            this.repaint();
        }
    }
    
    public static void setAttributes(final Color plainColor, final Color overColor, final Color downColor) {
        ClickLabel.plainColor = plainColor;
        ClickLabel.overColor = overColor;
        ClickLabel.downColor = downColor;
    }
}
