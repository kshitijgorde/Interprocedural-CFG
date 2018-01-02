// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;

public class WmFillPainter extends WmPainter
{
    private Image bgImage;
    private boolean isTiled;
    
    public WmFillPainter() {
        this.bgImage = null;
        this.isTiled = false;
    }
    
    public void setImage(final Image bgImage) {
        this.bgImage = bgImage;
    }
    
    public void setTiled(final boolean isTiled) {
        this.isTiled = isTiled;
    }
    
    public void paint(final Graphics graphics) {
        if (this.bgImage == null) {
            return;
        }
        final int width = this.getComponent().getWidth();
        final int height = this.getComponent().getHeight();
        final int width2 = this.bgImage.getWidth(null);
        final int height2 = this.bgImage.getHeight(null);
        if (this.isTiled) {
            for (int i = 0; i < width; i += width2) {
                for (int j = 0; j < height; j += height2) {
                    graphics.drawImage(this.bgImage, i, j, this.getComponent());
                }
            }
        }
        else {
            graphics.drawImage(this.bgImage, (width - width2) / 2, (height - height2) / 2, this.getComponent());
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.bgImage = WmPainter.getImage(this.getClass().getResource(actionEvent.getActionCommand()));
        this.getComponent().repaint();
    }
}
