// 
// Decompiled by Procyon v0.5.30
// 

package pa.a.b.a;

import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Container;

public class a extends Container
{
    private Image background;
    private Dimension a;
    private int if;
    pa.a.a.a do;
    
    public a(final int if1) {
        this.if = if1;
        this.a(this.background = f.a("panel"), this);
        final BorderLayout layout = new BorderLayout(0, 0);
        this.setLayout(layout);
        this.add(this.do = new pa.a.a.a(this.background), "Center");
        this.setVisible(true);
        this.a = layout.preferredLayoutSize(this);
    }
    
    private boolean a(final Image image, final Component component) {
        if (image == null) {
            return false;
        }
        final MediaTracker mediaTracker = new MediaTracker(component);
        mediaTracker.addImage(image, 1);
        try {
            mediaTracker.waitForID(1);
        }
        catch (InterruptedException ex) {
            return false;
        }
        return !mediaTracker.isErrorAny();
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.if, this.a.height);
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
    }
}
