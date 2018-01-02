// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.display;

import java.awt.Polygon;
import java.awt.Graphics;
import edu.davidson.tools.SApplet;
import edu.davidson.graph.DataSet;

public class TrailThing extends Thing
{
    DataSet dataset;
    double[] point;
    
    public TrailThing(final SApplet applet, final SScalable sScalable, final int s) {
        super(sScalable, 0.0, 0.0);
        this.dataset = new DataSet();
        this.point = new double[2];
        super.s = s;
        super.applet = applet;
    }
    
    public DataSet getDataSet() {
        return this.dataset;
    }
    
    public final void incTrail(final double x, final double y) {
        super.x = x;
        super.y = y;
        this.point[0] = x;
        this.point[1] = y;
        try {
            this.dataset.append(this.point, 1);
        }
        catch (Exception ex) {
            System.out.println("Error appending Data!");
        }
        if (super.trail == null || super.trailSize < 1) {
            return;
        }
        final int pixFromX = super.canvas.pixFromX(x);
        final int pixFromY = super.canvas.pixFromY(y);
        if (super.trail.npoints < super.trailSize) {
            super.trail.addPoint(pixFromX, pixFromY);
        }
        else {
            System.arraycopy(super.trail.xpoints, 1, super.trail.xpoints, 0, super.trailSize - 1);
            System.arraycopy(super.trail.ypoints, 1, super.trail.ypoints, 0, super.trailSize - 1);
            super.trail.xpoints[super.trailSize - 1] = pixFromX;
            super.trail.ypoints[super.trailSize - 1] = pixFromY;
        }
    }
    
    public final void paint(final Graphics graphics) {
        graphics.setColor(super.color);
        if (super.trailSize > 1 && super.trail.npoints > 1) {
            graphics.drawPolyline(super.trail.xpoints, super.trail.ypoints, super.trail.npoints);
        }
    }
    
    public final void setTrailSize(final int trailSize) {
        super.trailSize = trailSize;
        this.clearTrail();
    }
    
    public void clearTrail() {
        if (super.trail == null || super.trail.npoints != 0) {
            super.trail = new Polygon();
            this.dataset = new DataSet();
        }
    }
}
