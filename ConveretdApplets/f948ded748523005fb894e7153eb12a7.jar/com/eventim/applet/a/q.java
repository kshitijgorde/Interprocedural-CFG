// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.a;

import com.eventim.common.transfer.saalplan.TextDetails;
import com.eventim.common.transfer.saalplan.StehplatzbereichDetails;
import com.eventim.common.transfer.saalplan.SitzDetails;
import com.eventim.common.transfer.saalplan.ReiheDetails;
import com.eventim.common.transfer.saalplan.RechteckDetails;
import com.eventim.common.transfer.saalplan.PolylineDetails;
import com.eventim.common.transfer.saalplan.PolygonDetails;
import com.eventim.common.transfer.saalplan.EllipseDetails;
import com.eventim.common.transfer.saalplan.GraphDetails;
import java.awt.BasicStroke;
import java.awt.Stroke;
import java.awt.Color;

public abstract class q
{
    public static final k g;
    public static final Color h;
    public static final Color i;
    public static final Color j;
    public static final Color k;
    public static final Stroke l;
    public static final Stroke m;
    
    static {
        g = new l();
        l = new BasicStroke(1.0f, 1, 1);
        new BasicStroke(1.0f, 1, 1);
        m = new BasicStroke(1.0f, 1, 2, 10.0f, new float[] { 1.0f, 2.0f }, 0.0f);
        h = new Color(8, 156, 24);
        j = new Color(0.8f, 0.8f, 0.8f, 0.9f);
        k = Color.black;
        i = Color.red;
    }
    
    public static double a(final double n) {
        if (n % 2.0 == 1.0) {
            if (n < 4.0) {
                return n;
            }
            return n - 3.0;
        }
        else {
            if (n < 3.0) {
                return n;
            }
            return n - 2.0;
        }
    }
    
    public final k a(final GraphDetails graphDetails) {
        if (graphDetails.isEllipse()) {
            return this.a((EllipseDetails)graphDetails);
        }
        if (graphDetails.isPolygon()) {
            return this.a((PolygonDetails)graphDetails);
        }
        if (graphDetails.isPolyline()) {
            return this.a((PolylineDetails)graphDetails);
        }
        if (graphDetails.isRechteck()) {
            return this.a((RechteckDetails)graphDetails);
        }
        if (graphDetails.isReiheBez()) {
            return this.a((ReiheDetails)graphDetails);
        }
        if (graphDetails.isSitz()) {
            return this.a((SitzDetails)graphDetails);
        }
        if (graphDetails.isStehplatzbereich()) {
            return this.a((StehplatzbereichDetails)graphDetails);
        }
        if (graphDetails.isText()) {
            return this.a((TextDetails)graphDetails);
        }
        return null;
    }
    
    public abstract k a(final EllipseDetails p0);
    
    public abstract k a(final PolygonDetails p0);
    
    public abstract k a(final PolylineDetails p0);
    
    public abstract k a(final RechteckDetails p0);
    
    public abstract k a(final ReiheDetails p0);
    
    public abstract k a(final SitzDetails p0);
    
    public abstract c a(final StehplatzbereichDetails p0);
    
    public abstract d a(final TextDetails p0);
    
    public static q a(final String s) {
        if (s.equals("Section overview factory")) {
            return new h();
        }
        if (s.equals("Section detail factory")) {
            return new m();
        }
        return null;
    }
}
