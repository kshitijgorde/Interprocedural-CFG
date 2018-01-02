// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.components;

import java.awt.FontMetrics;
import javax.swing.BorderFactory;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PopupInfos extends JPanel
{
    private int xx;
    private int yy;
    private String text;
    private JLabel label;
    
    public PopupInfos(final String text) {
        this.text = text;
        this.setForeground(Color.black);
        this.setBackground(Color.CYAN);
        this.label = new JLabel(this.getText());
        this.setLayout(null);
        this.setOpaque(true);
        this.add(this.label);
        this.label.setLocation(0, 0);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }
    
    public String getText() {
        return this.text;
    }
    
    public int getXx() {
        return this.xx;
    }
    
    public int getYy() {
        return this.yy;
    }
    
    public void setText(final String s) {
        if (s != null) {
            this.text = s;
            this.label.setText(s);
            final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
            if (fontMetrics.stringWidth(s) > 0 && fontMetrics.getHeight() > 0) {
                this.setSize(fontMetrics.stringWidth(s) + 20, fontMetrics.getHeight() + 3);
                this.label.setSize(fontMetrics.stringWidth(s) + 20, fontMetrics.getHeight() + 3);
            }
        }
    }
    
    public void setXx(final int xx) {
        this.xx = xx;
    }
    
    public void setYy(final int yy) {
        this.yy = yy;
    }
}
