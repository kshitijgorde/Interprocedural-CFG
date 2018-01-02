// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.display;

import java.awt.Color;
import java.awt.Graphics;
import edu.davidson.tools.SApplet;

public class BoxThing extends Thing
{
    public BoxThing(final SApplet applet, final SScalable sScalable, final double n, final double n2, final int w, final int h) {
        super(sScalable, n, n2);
        super.s = 1;
        super.w = w;
        super.h = h;
        super.applet = applet;
    }
    
    public void paint(final Graphics graphics) {
        if (!super.visible) {
            return;
        }
        final int n = super.canvas.pixFromX(super.x) - super.w / 2 + super.xDisplayOff;
        final int n2 = super.canvas.pixFromY(super.y) - super.h / 2 - super.yDisplayOff;
        graphics.setColor(super.color);
        for (int i = 0; i <= super.s; ++i) {
            graphics.drawRect(n + i, n2 + i, super.w - 2 * i, super.h - 2 * i);
        }
    }
    
    public void paintHighlight(final Graphics graphics) {
        if (!super.visible) {
            return;
        }
        final int n = super.canvas.pixFromX(super.x) - super.w / 2 + super.xDisplayOff;
        final int n2 = super.canvas.pixFromY(super.y) - super.h / 2 - super.yDisplayOff;
        if (super.color == Color.red) {
            graphics.setColor(Color.blue);
        }
        else {
            graphics.setColor(Color.red);
        }
        graphics.drawRect(n, n2, super.w, super.h);
        graphics.drawRect(n - 1, n2 - 1, super.w + 2, super.h + 2);
    }
    
    public final boolean isInsideThing(final int n, final int n2) {
        final int n3 = super.canvas.pixFromX(super.x) + super.xDisplayOff;
        final int n4 = super.canvas.pixFromY(super.y) - super.yDisplayOff;
        return Math.abs(n - n3) < super.w / 2 + 1 && Math.abs(n2 - n4) < super.h / 2 + 1;
    }
}