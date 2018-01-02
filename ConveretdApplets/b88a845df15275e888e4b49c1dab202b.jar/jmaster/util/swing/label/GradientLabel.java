// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing.label;

import java.awt.Component;
import java.awt.Graphics;
import jmaster.util.swing.icon.GradientIcon;
import javax.swing.JLabel;

public class GradientLabel extends JLabel
{
    private static final long B = -2165684687884478481L;
    protected GradientIcon A;
    
    public GradientLabel() {
        (this.A = new GradientIcon()).setMode(1);
    }
    
    public GradientIcon getGradientIcon() {
        return this.A;
    }
    
    public int getBeginColor() {
        return this.A.getBeginColor();
    }
    
    public int getEndColor() {
        return this.A.getEndColor();
    }
    
    public void setEndColor(final int endColor) {
        this.A.setEndColor(endColor);
    }
    
    public void setBeginColor(final int beginColor) {
        this.A.setBeginColor(beginColor);
    }
    
    public void paint(final Graphics graphics) {
        if (this.A.getWidth() != this.getWidth()) {
            this.A.setWidth(this.getWidth());
        }
        if (this.A.getHeight() != this.getHeight()) {
            this.A.setHeight(this.getHeight());
        }
        this.A.paintIcon(this, graphics, 0, 0);
        super.paint(graphics);
    }
}
