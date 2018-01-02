import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class SVDVideoPanel extends Panel
{
    public void paint(final Graphics g) {
        g.drawImage(SVDCommunicator.imgFrame, 0, 0, null);
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
