// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.display;

import java.awt.Graphics;
import java.awt.Color;
import edu.davidson.tools.SApplet;

public class Constraint extends Thing
{
    private double left;
    private double right;
    private double top;
    private double bottom;
    
    public Constraint(final SApplet applet, final SScalable sScalable, final double left, final double right, final double bottom, final double top) {
        super(sScalable, 0.0, 0.0);
        super.applet = applet;
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
        super.visible = true;
        super.color = Color.black;
    }
    
    public void paint(final Graphics graphics) {
        if (!super.visible) {
            return;
        }
        final int pixFromX = super.canvas.pixFromX(this.left);
        final int pixFromY = super.canvas.pixFromY(this.top);
        super.w = super.canvas.pixFromX(this.right) - pixFromX;
        super.h = super.canvas.pixFromY(this.bottom) - pixFromY;
        graphics.setColor(super.color);
        if (super.w > 0 && super.h > 0) {
            graphics.drawRect(pixFromX, pixFromY, super.w, super.h);
        }
        else if (super.w > 0 && super.h == 0) {
            graphics.drawLine(pixFromX, pixFromY, pixFromX + super.w, pixFromY);
        }
        else if (super.w == 0 && super.h > 0) {
            graphics.drawLine(pixFromX, pixFromY, pixFromX, pixFromY + super.h);
        }
    }
    
    public void paintHighlight(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void enforceConstraint(final Thing thing) {
        double x = thing.x;
        double y = thing.y;
        if (this.right > this.left) {
            x = Math.min(this.right, Math.max(this.left, x));
            if (this.top == this.bottom) {
                y = this.top;
            }
        }
        if (this.top > this.bottom) {
            y = Math.min(this.top, Math.max(this.bottom, y));
            if (this.left == this.right) {
                x = this.left;
            }
        }
        thing.x = x;
        thing.y = y;
    }
    
    public final boolean isInsideThing(final int n, final int n2) {
        return false;
    }
}
