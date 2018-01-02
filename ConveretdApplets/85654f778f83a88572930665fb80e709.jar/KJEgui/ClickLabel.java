// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

public class ClickLabel extends JLabel implements MouseListener
{
    public static Color plainColor;
    public static Color overColor;
    public static Color downColor;
    public static int iHoverWidth;
    public Color textColor;
    
    static {
        ClickLabel.plainColor = Color.black;
        ClickLabel.overColor = Color.blue;
        ClickLabel.downColor = Color.blue;
        ClickLabel.iHoverWidth = 80;
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
    
    public static String getFormatHelpText(final String s) {
        if (s == null) {
            return null;
        }
        int n = 0;
        final String s2 = " ";
        final String s3 = "<br>";
        final int length = s2.length();
        int i = s.indexOf(s2, ClickLabel.iHoverWidth);
        if (i < 0) {
            return "<html>" + s + "</html>";
        }
        final StringBuffer sb = new StringBuffer();
        while (i != -1) {
            sb.append(s.substring(n, i));
            sb.append(s3);
            n = i + length;
            i = s.indexOf(s2, n + ClickLabel.iHoverWidth);
        }
        sb.append(s.substring(n));
        System.out.println(sb.toString());
        return "<html>" + sb.toString() + "</html>";
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
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
