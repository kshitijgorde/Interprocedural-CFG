// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.display;

import java.awt.Color;
import java.awt.Graphics;
import edu.davidson.tools.SApplet;

public class MarkerThing extends Thing
{
    boolean longCross;
    
    public MarkerThing(final SApplet applet, final SScalable sScalable, final int n, final double n2, final double n3) {
        super(sScalable, n2, n3);
        this.longCross = false;
        super.s = 1;
        super.w = n;
        super.h = n;
        super.noDrag = false;
        super.applet = applet;
    }
    
    public void paint(final Graphics graphics) {
        if (!super.visible) {
            return;
        }
        final int n = super.canvas.pixFromX(super.x) + super.xDisplayOff;
        final int n2 = super.canvas.pixFromY(super.y) - super.yDisplayOff;
        graphics.setColor(super.color);
        graphics.drawOval(n - super.w / 2, n2 - super.h / 2, super.w, super.h);
        if (this.longCross) {
            graphics.drawLine(0, n2, n - 1, n2);
            graphics.drawLine(n + 1, n2, super.canvas.getPixWidth(), n2);
            graphics.drawLine(n, 0, n, n2 - 1);
            graphics.drawLine(n, n2 + 1, n, super.canvas.getPixHeight());
        }
        else {
            graphics.drawLine(n - super.w / 2, n2, n - 1, n2);
            graphics.drawLine(n + 1, n2, n + super.w / 2, n2);
            graphics.drawLine(n, n2 - super.h / 2, n, n2 - 1);
            graphics.drawLine(n, n2 + 1, n, n2 + super.h / 2);
        }
    }
    
    public void paintHighlight(final Graphics graphics) {
        if (!super.visible) {
            return;
        }
        final int n = super.canvas.pixFromX(super.x) + super.xDisplayOff;
        final int n2 = super.canvas.pixFromY(super.y) - super.yDisplayOff;
        if (super.color == Color.red) {
            graphics.setColor(Color.blue);
        }
        else {
            graphics.setColor(Color.red);
        }
        graphics.drawOval(n - super.w / 2, n2 - super.h / 2, super.w, super.h);
    }
    
    public final boolean isInsideThing(final int n, final int n2) {
        final int n3 = super.canvas.pixFromX(super.x) + super.xDisplayOff;
        final int n4 = super.canvas.pixFromY(super.y) - super.yDisplayOff;
        return Math.abs(n - n3) < super.w / 2 + 1 && Math.abs(n2 - n4) < super.h / 2 + 1;
    }
}
