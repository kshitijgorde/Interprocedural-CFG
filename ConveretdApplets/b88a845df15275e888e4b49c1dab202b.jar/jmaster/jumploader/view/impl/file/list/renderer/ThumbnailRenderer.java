// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.file.list.renderer;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Component;
import jmaster.jumploader.model.api.C.C;
import java.awt.image.ImageObserver;
import java.awt.Image;

public class ThumbnailRenderer extends FrameRenderer
{
    private Image º;
    private Image \u00c3;
    private Image ª;
    private Image µ;
    private Image \u00c5;
    private Image \u00c4;
    private Image \u00c2;
    private boolean \u00c6;
    private int \u00c1;
    private int \u00c0;
    
    public Image getImageFailed() {
        return this.ª;
    }
    
    public void setImageFailed(final Image ª) {
        this.ª = ª;
    }
    
    public Image getImagePending() {
        return this.\u00c3;
    }
    
    public void setImagePending(final Image \u00e3) {
        this.\u00c3 = \u00e3;
    }
    
    public Image getImageDocument() {
        return this.µ;
    }
    
    public void setImageDocument(final Image µ) {
        this.µ = µ;
    }
    
    public int getCenterX() {
        return this.\u00c1;
    }
    
    public void setCenterX(final int \u00e1) {
        this.\u00c1 = \u00e1;
    }
    
    public int getCenterY() {
        return this.\u00c0;
    }
    
    public void setCenterY(final int \u00e0) {
        this.\u00c0 = \u00e0;
    }
    
    public Image getImageFolder() {
        return this.\u00c5;
    }
    
    public void setImageFolder(final Image \u00e5) {
        this.\u00c5 = \u00e5;
    }
    
    public Image getImageDisk() {
        return this.\u00c4;
    }
    
    public void setImageDisk(final Image \u00e4) {
        this.\u00c4 = \u00e4;
    }
    
    public Image getImageNotFileSystem() {
        return this.\u00c2;
    }
    
    public void setImageNotFileSystem(final Image \u00e2) {
        this.\u00c2 = \u00e2;
    }
    
    public void prepare() {
        final C b = this.V.A().B(this.getFile());
        this.\u00c6 = false;
        if (b != null) {
            if (b.D()) {
                this.º = this.\u00c3;
            }
            else if (b.C()) {
                this.º = this.ª;
            }
            else if (b.B()) {
                if (b.A() != null) {
                    this.º = b.A();
                    this.\u00c6 = true;
                }
                else {
                    this.º = this.µ;
                }
            }
        }
        else if (!this.getFile().isFileSystem()) {
            this.º = this.\u00c2;
        }
        else if (this.getFile().isDrive()) {
            this.º = this.\u00c4;
        }
        else if (this.getFile().isDirectory()) {
            this.º = this.\u00c5;
        }
        else if (this.getFile().isFile()) {
            this.º = this.µ;
        }
        if (this.º != null) {
            this.¥.setSize(this.º.getWidth(null) + this.¥.getIconL().getIconWidth() + this.¥.getIconR().getIconWidth(), this.º.getHeight(null) + this.¥.getIconT().getIconHeight() + this.¥.getIconB().getIconHeight());
            this.¥.setAnchor("NW");
            this.¥.setHorizontalSpace(this.\u00c1 - this.¥.getWidth() / 2);
            this.¥.setVerticalSpace(this.\u00c0 - this.¥.getHeight() / 2);
        }
    }
    
    public void render(final Component component, final Graphics graphics) {
        if (this.\u00c6) {
            this.¥.paintIcon(component, graphics);
        }
        final Rectangle iconRect = this.¥.getIconRect(component, null);
        graphics.drawImage(this.º, iconRect.x + (iconRect.width - this.º.getWidth(null)) / 2, iconRect.y + (iconRect.height - this.º.getHeight(null)) / 2, component);
    }
}
