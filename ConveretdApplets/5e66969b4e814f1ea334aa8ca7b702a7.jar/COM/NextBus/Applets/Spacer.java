// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.Applets;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;

public class Spacer extends Component
{
    private static final long serialVersionUID = 8290078872182650534L;
    private Dimension size;
    
    public Spacer(final Dimension size) {
        this.size = size;
    }
    
    public Dimension getPreferredSize() {
        return this.size;
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
    }
}
