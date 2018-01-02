// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.display;

import java.awt.Graphics;
import edu.davidson.tools.SApplet;

public class ArrowThing extends Thing
{
    private double horz;
    private double vert;
    
    public ArrowThing(final SApplet applet, final SScalable sScalable, final int s, final double horz, final double vert, final double n, final double n2) {
        super(sScalable, n, n2);
        super.s = s;
        this.horz = horz;
        this.vert = vert;
        super.applet = applet;
    }
    
    protected double getHorz() {
        return this.horz;
    }
    
    protected double getVert() {
        return this.vert;
    }
    
    public void paint(final Graphics graphics) {
        if (!super.visible) {
            return;
        }
        final int n = super.canvas.pixFromX(super.x) + super.xDisplayOff;
        final int n2 = super.canvas.pixFromY(super.y) - super.yDisplayOff;
        final double n3 = super.canvas.pixFromX(1.0) - super.canvas.pixFromX(0.0);
        final double n4 = n3 * this.getHorz();
        final double n5 = n3 * this.getVert();
        graphics.setColor(super.color);
        final int n6 = (int)(n + n4);
        final int n7 = (int)(n2 - n5);
        graphics.drawLine(n, n2, n6, n7);
        final double sqrt = Math.sqrt(n4 * n4 + n5 * n5);
        double n8;
        if (sqrt > 3 * super.s) {
            n8 = super.s;
        }
        else {
            n8 = sqrt / 3;
        }
        if (sqrt > 1) {
            final double n9 = n8 * n4 / sqrt;
            final double n10 = -(n8 * n5 / sqrt);
            final double n11 = n6 - 3 * n9;
            final double n12 = n7 - 3 * n10;
            graphics.drawLine((int)(n11 - n10), (int)(n12 + n9), n6, n7);
            graphics.drawLine((int)(n11 + n10), (int)(n12 - n9), n6, n7);
        }
    }
    
    public void paintHighlight(final Graphics graphics) {
        if (!super.visible) {
            return;
        }
        final int n = super.canvas.pixFromX(super.x) + super.xDisplayOff;
        final int n2 = super.canvas.pixFromY(super.y) - super.yDisplayOff;
        final double n3 = super.canvas.pixFromX(1.0) - super.canvas.pixFromX(0.0);
        final double n4 = n3 * this.horz;
        final double n5 = n3 * this.vert;
        graphics.setColor(super.highlightColor);
        final int n6 = (int)(n + n4);
        final int n7 = (int)(n2 - n5);
        graphics.drawLine(n, n2, n6, n7);
        final double sqrt = Math.sqrt(n4 * n4 + n5 * n5);
        double n8;
        if (sqrt > 3 * super.s) {
            n8 = super.s;
        }
        else {
            n8 = sqrt / 3;
        }
        if (sqrt > 1) {
            final double n9 = n8 * n4 / sqrt;
            final double n10 = -(n8 * n5 / sqrt);
            final double n11 = n6 - 3 * n9;
            final double n12 = n7 - 3 * n10;
            graphics.drawLine((int)(n11 - n10), (int)(n12 + n9), n6, n7);
            graphics.drawLine((int)(n11 + n10), (int)(n12 - n9), n6, n7);
        }
    }
    
    public final boolean isInsideThing(final int n, final int n2) {
        final int n3 = super.canvas.pixFromX(super.x) + super.xDisplayOff;
        final int n4 = super.canvas.pixFromY(super.y) - super.yDisplayOff;
        return Math.abs(n - n3) < 5 && Math.abs(n2 - n4) < 5;
    }
}
