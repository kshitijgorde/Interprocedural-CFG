// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editorpanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Panel;

public class BorderedPanel extends Panel
{
    private Insets insets;
    
    public void setInsets(final int n, final int n2, final int n3, final int n4) {
        this.insets = new Insets(n, n2, n3, n4);
    }
    
    public Insets insets() {
        return this.insets;
    }
    
    public BorderedPanel() {
        this.insets = new Insets(0, 0, 0, 0);
        this.setInsets(3, 3, 3, 3);
        this.setLayout(new BorderLayout());
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.lightGray);
        graphics.drawLine(0, 0, 0, this.size().height - 1);
        graphics.drawLine(0, 0, this.size().width - 1, 0);
        graphics.drawLine(0, this.size().height - 1, this.size().width - 1, this.size().height - 1);
        graphics.drawLine(this.size().width - 1, 0, this.size().width - 1, this.size().height - 1);
        graphics.drawLine(2, this.size().height - 3, this.size().width - 3, this.size().height - 3);
        graphics.drawLine(this.size().width - 3, 2, this.size().width - 3, this.size().height - 3);
        graphics.setColor(Color.darkGray);
        graphics.drawLine(1, 1, 1, this.size().height - 3);
        graphics.drawLine(1, 1, this.size().width - 3, 1);
        graphics.setColor(Color.black);
        graphics.drawLine(2, 2, this.size().width - 4, 2);
        graphics.drawLine(2, 2, 2, this.size().height - 4);
        graphics.dispose();
    }
}
