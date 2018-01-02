// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.display;

import java.awt.Color;
import java.awt.Graphics;
import edu.davidson.tools.SApplet;

public class TangentThing extends Thing
{
    double rise;
    double run;
    
    public TangentThing(final SApplet applet, final SScalable sScalable, final double n, final double n2, final double run, final double rise) {
        super(sScalable, n, n2);
        this.rise = 1.0;
        this.run = 1.0;
        super.s = 1;
        this.rise = rise;
        this.run = run;
        super.applet = applet;
        super.resizable = true;
    }
    
    public void paint(final Graphics graphics) {
        if (!super.visible) {
            return;
        }
        final int n = super.canvas.pixFromX(super.x) + super.xDisplayOff + (int)this.run;
        final int n2 = super.canvas.pixFromY(super.y) - super.yDisplayOff - (int)this.rise;
        final int n3 = super.canvas.pixFromX(super.x) + super.xDisplayOff - (int)this.run;
        final int n4 = super.canvas.pixFromY(super.y) - super.yDisplayOff + (int)this.rise;
        graphics.setColor(super.color);
        graphics.drawLine(n, n2, n3, n4);
        if (!super.noDrag) {
            graphics.drawOval((n + n3) / 2 - 1, (n2 + n4) / 2 - 1, 3, 3);
        }
        if (super.resizable) {
            graphics.drawOval(n - 1, n2 - 1, 3, 3);
            graphics.drawOval(n3 - 1, n4 - 1, 3, 3);
        }
        graphics.getFont();
        graphics.setFont(super.font);
        if (this.run == 0) {
            graphics.drawString("m=infinite", (n + n3) / 2, (n2 + n4) / 2);
        }
        else {
            graphics.drawString("m=".concat(String.valueOf(String.valueOf(super.format.form(this.rise / this.run)))), (n + n3) / 2, (n2 + n4) / 2);
        }
        graphics.setColor(Color.black);
    }
    
    public void paintHighlight(final Graphics graphics) {
        if (!super.visible) {
            return;
        }
        final int n = super.canvas.pixFromX(super.x) + super.xDisplayOff + (int)this.run;
        final int n2 = super.canvas.pixFromY(super.y) - super.yDisplayOff - (int)this.rise;
        final int n3 = super.canvas.pixFromX(super.x) + super.xDisplayOff - (int)this.run;
        final int n4 = super.canvas.pixFromY(super.y) - super.yDisplayOff + (int)this.rise;
        graphics.setColor(super.color);
        graphics.drawLine(n, n2, n3, n4);
        if (!super.noDrag) {
            graphics.drawOval((n + n3) / 2 - 1, (n2 + n4) / 2 - 1, 3, 3);
        }
        if (super.resizable) {
            graphics.drawOval(n - 1, n2 - 1, 3, 3);
            graphics.drawOval(n3 - 1, n4 - 1, 3, 3);
        }
        graphics.getFont();
        graphics.setFont(super.font);
        if (this.run == 0) {
            graphics.drawString("m=infinite", (n + n3) / 2, (n2 + n4) / 2);
        }
        else {
            graphics.drawString("m=".concat(String.valueOf(String.valueOf(super.format.form(this.rise / this.run)))), (n + n3) / 2, (n2 + n4) / 2);
        }
        graphics.setColor(Color.black);
    }
    
    public final boolean isInsideThing(final int n, final int n2) {
        final int n3 = super.canvas.pixFromX(super.x) + super.xDisplayOff + (int)this.run;
        final int n4 = super.canvas.pixFromY(super.y) - super.yDisplayOff - (int)this.rise;
        final int n5 = super.canvas.pixFromX(super.x) + super.xDisplayOff - (int)this.run;
        final int n6 = super.canvas.pixFromY(super.y) - super.yDisplayOff + (int)this.rise;
        final int n7 = (n3 + n5) / 2;
        final int n8 = (n4 + n6) / 2;
        if (!super.noDrag && Math.abs(n - n7) < 3 && Math.abs(n2 - n8) < 3) {
            super.hotSpot = 0;
            return true;
        }
        if (super.resizable && Math.abs(n - n3) < 3 && Math.abs(n2 - n4) < 3) {
            super.hotSpot = 1;
            return true;
        }
        if (super.resizable && Math.abs(n - n5) < 3 && Math.abs(n2 - n6) < 3) {
            super.hotSpot = 2;
            return true;
        }
        return false;
    }
    
    public void dragMe(final double n, final double n2) {
        if (super.hotSpot == 0) {
            this.setXY(n, n2);
        }
        else if (super.hotSpot == 1) {
            this.run = super.canvas.pixFromX(n) - super.canvas.pixFromX(super.x);
            this.rise = -super.canvas.pixFromY(n2) + super.canvas.pixFromY(super.y);
        }
        else if (super.hotSpot == 2) {
            this.run = -super.canvas.pixFromX(n) + super.canvas.pixFromX(super.x);
            this.rise = super.canvas.pixFromY(n2) - super.canvas.pixFromY(super.y);
        }
    }
}
