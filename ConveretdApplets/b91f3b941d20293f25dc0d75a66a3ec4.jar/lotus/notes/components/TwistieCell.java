// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.components;

import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Image;

public class TwistieCell extends ImageCell
{
    public TwistieCell(final Image image, final Image image2, final boolean b) {
        super(image, image2, !b);
    }
    
    public void setWellInfo(final Component component, final Image image, final int n, final int n2) {
        if (image != null) {
            final int width = image.getWidth(component);
            if (n >= 2 && width > n) {
                super.twistChunkWidth = (width - (n - 1)) / n;
            }
        }
    }
    
    public boolean IsCollapsed() {
        return !super.bToggle;
    }
    
    public void setCollapsed(final boolean b) {
        super.bToggle = !b;
    }
}
