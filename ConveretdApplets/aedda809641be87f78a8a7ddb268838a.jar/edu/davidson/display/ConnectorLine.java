// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.display;

import java.awt.Graphics;
import edu.davidson.tools.SApplet;

public class ConnectorLine extends Thing
{
    Thing thing1;
    Thing thing2;
    
    public ConnectorLine(final SApplet applet, final SScalable sScalable, final Thing thing1, final Thing thing2) {
        super(sScalable, 0.0, 0.0);
        super.s = 1;
        this.thing1 = thing1;
        this.thing2 = thing2;
        super.applet = applet;
        super.visible = true;
    }
    
    public void setConnectorIDs(final Thing thing1, final Thing thing2) {
        this.thing1 = thing1;
        this.thing2 = thing2;
    }
    
    public void paint(final Graphics graphics) {
        if (!super.visible || this.thing1 == null || this.thing2 == null) {
            return;
        }
        final int pixFromX = super.canvas.pixFromX(this.thing1.getX());
        final int pixFromY = super.canvas.pixFromY(this.thing1.getY());
        final int pixFromX2 = super.canvas.pixFromX(this.thing2.getX());
        final int pixFromY2 = super.canvas.pixFromY(this.thing2.getY());
        graphics.setColor(super.color);
        graphics.drawLine(pixFromX, pixFromY, pixFromX2, pixFromY2);
    }
    
    public final boolean isInsideThing(final int n, final int n2) {
        return false;
    }
}
