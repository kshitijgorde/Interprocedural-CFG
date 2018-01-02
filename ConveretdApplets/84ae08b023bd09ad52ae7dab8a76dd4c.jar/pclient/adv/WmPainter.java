// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import java.awt.event.HierarchyEvent;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.net.URL;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.ActionListener;

public abstract class WmPainter implements ActionListener, HierarchyListener
{
    private WmViewport paintComp;
    
    final void setComponent(final WmViewport paintComp) {
        if (this.paintComp != null) {
            this.paintComp.removeHierarchyListener(this);
            this.stop();
        }
        this.paintComp = paintComp;
        if (this.paintComp != null) {
            if (this.paintComp.isShowing()) {
                this.start();
            }
            this.paintComp.addHierarchyListener(this);
        }
    }
    
    protected final WmViewport getComponent() {
        return this.paintComp;
    }
    
    public String[] getCommands() {
        return null;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
    }
    
    protected void start() {
    }
    
    protected void stop() {
    }
    
    protected abstract void paint(final Graphics p0);
    
    protected static Image getImage(final URL url) {
        Image read = null;
        try {
            read = ImageIO.read(url);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return read;
    }
    
    public void hierarchyChanged(final HierarchyEvent hierarchyEvent) {
        if ((hierarchyEvent.getChangeFlags() & 0x4L) != 0x4L) {
            return;
        }
        if (this.paintComp.isShowing()) {
            this.start();
        }
        else {
            this.stop();
        }
    }
}
