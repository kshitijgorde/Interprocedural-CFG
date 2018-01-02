// 
// Decompiled by Procyon v0.5.30
// 

package sGraphics;

import java.awt.Component;
import java.awt.Window;

public class Bubble extends Window
{
    private BubblePanel panel;
    
    public Bubble(final Component component, final String s) {
        super(Util.getFrame(component));
        this.add(this.panel = new BubblePanel(s), "Center");
    }
    
    public void setVisible(final boolean visible) {
        this.pack();
        super.setVisible(visible);
    }
    
    public void setText(final String text) {
        this.panel.setText(text);
        if (text == null) {
            this.setVisible(false);
        }
        else {
            this.pack();
            this.panel.repaint();
        }
    }
}
