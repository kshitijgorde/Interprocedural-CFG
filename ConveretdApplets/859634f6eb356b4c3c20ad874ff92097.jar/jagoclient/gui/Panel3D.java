// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.gui;

import java.awt.Graphics;
import rene.gui.Global;
import java.awt.Component;
import java.awt.Panel;

public class Panel3D extends Panel
{
    Component C;
    
    public Panel3D(final Component c) {
        this.add(this.C = c);
        if (Global.ControlBackground != null) {
            this.setBackground(Global.ControlBackground);
        }
    }
    
    public Panel3D() {
        this.C = null;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.getBackground());
        graphics.fill3DRect(0, 0, this.getSize().width - 1, this.getSize().height - 1, true);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void doLayout() {
        if (this.C != null) {
            this.C.setLocation(5, 5);
            this.C.setSize(this.getSize().width - 10, this.getSize().height - 10);
            this.C.doLayout();
            return;
        }
        super.doLayout();
    }
}
