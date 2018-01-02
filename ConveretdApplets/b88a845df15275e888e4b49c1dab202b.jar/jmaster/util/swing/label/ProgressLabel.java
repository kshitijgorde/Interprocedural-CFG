// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing.label;

import java.awt.Component;
import java.awt.Graphics;
import jmaster.util.swing.icon.ProgressIcon;
import javax.swing.JLabel;

public class ProgressLabel extends JLabel
{
    private static final long B = -2435015799795172390L;
    private ProgressIcon A;
    
    public ProgressLabel() {
        this.A = new ProgressIcon();
    }
    
    public ProgressIcon getProgressIcon() {
        return this.A;
    }
    
    public void setProgressIcon(final ProgressIcon a) {
        this.A = a;
    }
    
    public void paint(final Graphics graphics) {
        this.A.setWidth(this.getWidth());
        this.A.setHeight(this.getHeight());
        this.A.paintIcon(this, graphics);
    }
    
    public void setCompleted(final double completed) {
        this.A.setCompleted(completed);
        this.repaint();
    }
}
