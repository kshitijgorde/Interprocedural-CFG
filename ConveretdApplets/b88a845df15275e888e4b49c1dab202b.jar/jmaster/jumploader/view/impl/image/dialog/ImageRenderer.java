// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.image.dialog;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Image;
import jmaster.jumploader.view.impl.file.list.renderer.FrameRenderer;

public class ImageRenderer extends FrameRenderer
{
    private Image \u00c9;
    private int \u00c8;
    private int \u00c7;
    
    public int getCenterX() {
        return this.\u00c8;
    }
    
    public void setCenterX(final int \u00e8) {
        this.\u00c8 = \u00e8;
    }
    
    public int getCenterY() {
        return this.\u00c7;
    }
    
    public void setCenterY(final int \u00e7) {
        this.\u00c7 = \u00e7;
    }
    
    public void prepare() {
        if (this.getValue() != null && this.getValue() instanceof Image) {
            this.\u00c9 = (Image)this.getValue();
        }
        if (this.\u00c9 != null) {
            this.¥.setSize(this.\u00c9.getWidth(null) + this.¥.getIconL().getIconWidth() + this.¥.getIconR().getIconWidth(), this.\u00c9.getHeight(null) + this.¥.getIconT().getIconHeight() + this.¥.getIconB().getIconHeight());
            this.¥.setAnchor("NW");
            this.¥.setHorizontalSpace(this.\u00c8 - this.¥.getWidth() / 2);
            this.¥.setVerticalSpace(this.\u00c7 - this.¥.getHeight() / 2);
        }
    }
    
    public void render(final Component component, final Graphics graphics) {
        this.¥.paintIcon(component, graphics);
        final Rectangle iconRect = this.¥.getIconRect(component, null);
        graphics.drawImage(this.\u00c9, iconRect.x + (iconRect.width - this.\u00c9.getWidth(null)) / 2, iconRect.y + (iconRect.height - this.\u00c9.getHeight(null)) / 2, component);
    }
}
