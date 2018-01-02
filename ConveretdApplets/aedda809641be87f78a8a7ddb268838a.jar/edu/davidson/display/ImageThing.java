// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.display;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import edu.davidson.tools.SApplet;
import java.awt.Component;
import java.awt.Image;

public class ImageThing extends Thing
{
    private Image image;
    private Component drawingComponent;
    
    public ImageThing(final SApplet applet, final SScalable sScalable, final Image image, final double n, final double n2) {
        super(sScalable, n, n2);
        this.drawingComponent = null;
        this.image = image;
        super.s = 1;
        super.applet = applet;
        if (sScalable instanceof Component) {
            this.drawingComponent = (Component)sScalable;
            super.w = image.getWidth(this.drawingComponent);
            super.h = image.getHeight(this.drawingComponent);
        }
        else {
            this.drawingComponent = null;
        }
    }
    
    public final Image getImage() {
        return this.image;
    }
    
    public final boolean isInsideThing(final int n, final int n2) {
        final int n3 = super.canvas.pixFromX(super.x) + super.xDisplayOff + super.w / 2;
        final int n4 = super.canvas.pixFromY(super.y) - super.yDisplayOff + super.h / 2;
        return Math.abs(n - n3) < super.w / 2 + 1 && Math.abs(n2 - n4) < super.h / 2 + 1;
    }
    
    public void paint(final Graphics graphics) {
        if (!super.visible || this.drawingComponent == null) {
            return;
        }
        super.w = this.image.getWidth(this.drawingComponent);
        super.h = this.image.getHeight(this.drawingComponent);
        if (super.w == -1 || super.h == -1) {
            return;
        }
        graphics.drawImage(this.image, Math.round(super.canvas.pixFromX(super.x)) + super.xDisplayOff, Math.round(super.canvas.pixFromY(super.y)) - super.yDisplayOff, this.drawingComponent);
        super.paint(graphics);
    }
}
