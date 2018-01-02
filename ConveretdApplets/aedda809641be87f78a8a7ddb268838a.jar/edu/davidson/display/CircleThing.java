// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.display;

import java.awt.Color;
import java.awt.Graphics;
import edu.davidson.tools.SApplet;

public class CircleThing extends Thing
{
    public CircleThing(final SApplet applet, final SScalable sScalable, final double n, final double n2, final int n3) {
        super(sScalable, n, n2);
        super.s = 1;
        super.w = n3;
        super.h = n3;
        super.applet = applet;
    }
    
    public void paint(final Graphics graphics) {
        if (!super.visible) {
            return;
        }
        final int n = super.canvas.pixFromX(super.x) - super.w + super.xDisplayOff;
        final int n2 = super.canvas.pixFromY(super.y) - super.w - super.yDisplayOff;
        graphics.setColor(super.color);
        graphics.fillOval(n, n2, 2 * super.w + 1, 2 * super.h + 1);
    }
    
    public void paintHighlight(final Graphics graphics) {
        if (!super.visible) {
            return;
        }
        final int n = super.canvas.pixFromX(super.x) - super.w + super.xDisplayOff;
        final int n2 = super.canvas.pixFromY(super.y) - super.w - super.yDisplayOff;
        if (super.color == Color.red) {
            graphics.setColor(Color.blue);
        }
        else {
            graphics.setColor(Color.red);
        }
        graphics.fillOval(n, n2, 2 * super.w + 1, 2 * super.h + 1);
        graphics.fillOval(n - 1, n2 - 1, 2 * super.w + 3, 2 * super.h + 3);
    }
    
    public final boolean isInsideThing(final int n, final int n2) {
        final int n3 = super.canvas.pixFromX(super.x) + super.xDisplayOff;
        final int n4 = super.canvas.pixFromY(super.y) - super.yDisplayOff;
        return Math.abs(n - n3) < super.w + 1 && Math.abs(n2 - n4) < super.w + 1;
    }
}
