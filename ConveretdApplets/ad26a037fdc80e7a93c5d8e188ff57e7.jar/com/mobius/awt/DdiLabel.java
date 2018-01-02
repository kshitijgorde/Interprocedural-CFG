// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.awt;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;

public class DdiLabel extends Component
{
    private String labelText;
    
    public DdiLabel() {
        this.labelText = "";
        this.setBackground(Color.lightGray);
    }
    
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        this.repaint();
    }
    
    public void setText(final String labelText) {
        this.labelText = labelText;
        this.repaint();
    }
    
    public String getText() {
        return this.labelText;
    }
    
    public void setLabelColor(final Color background) {
        this.setBackground(background);
    }
    
    public Color getLabelColor() {
        return this.getBackground();
    }
    
    public void setTextColor(final Color foreground) {
        this.setForeground(foreground);
    }
    
    public Color getTextColor() {
        return this.getForeground();
    }
    
    public void paint(final Graphics graphics) {
        if (this.isVisible()) {
            final Dimension size = this.getSize();
            Color color;
            if (this.isEnabled()) {
                color = this.getBackground();
            }
            else {
                color = Color.gray;
            }
            graphics.setColor(color);
            graphics.draw3DRect(0, 0, size.width - 1, size.height - 1, false);
            graphics.fillRect(1, 1, size.width - 2, size.height - 2);
            graphics.clipRect(2, 1, size.width - 7, size.height - 2);
            if (this.labelText.length() > 0) {
                Color color2;
                if (this.isEnabled()) {
                    color2 = this.getForeground();
                }
                else {
                    color2 = Color.darkGray;
                }
                graphics.setColor(color2);
                graphics.setFont(this.getFont());
                graphics.drawString(this.labelText, 10, (size.height + graphics.getFontMetrics().getAscent()) / 2 - 2);
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
