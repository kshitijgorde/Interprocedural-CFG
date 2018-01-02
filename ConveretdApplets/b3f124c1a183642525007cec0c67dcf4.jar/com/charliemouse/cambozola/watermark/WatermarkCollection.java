// 
// Decompiled by Procyon v0.5.30
// 

package com.charliemouse.cambozola.watermark;

import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Enumeration;
import java.awt.Point;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Panel;
import java.awt.Toolkit;
import java.util.Vector;

public class WatermarkCollection
{
    public static final String NO_URL;
    public static final int DEFAULT_INDENT = 5;
    private Vector m_watermarks;
    
    public WatermarkCollection() {
        Toolkit.getDefaultToolkit();
        final MediaTracker mediaTracker = new MediaTracker(new Panel());
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        this.m_watermarks = new Vector();
    }
    
    public Watermark isOverClickableWatermark(final Point point) {
        final Watermark watermarkAtPoint = this.getWatermarkAtPoint(point);
        if (watermarkAtPoint != null && watermarkAtPoint.isClickable()) {
            return watermarkAtPoint;
        }
        return null;
    }
    
    public Watermark getWatermarkAtPoint(final Point point) {
        final Enumeration<Watermark> elements = this.m_watermarks.elements();
        while (elements.hasMoreElements()) {
            final Watermark watermark = elements.nextElement();
            if (watermark.hitsPoint(point) && watermark.isVisible()) {
                return watermark;
            }
        }
        return null;
    }
    
    public void recalculateLocations(final Dimension dimension) {
        final Enumeration<Watermark> elements = this.m_watermarks.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().recalculateLocation(dimension);
        }
    }
    
    public void paint(final Graphics graphics) {
        final Enumeration<Watermark> elements = this.m_watermarks.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().paint(graphics);
        }
    }
    
    static {
        NO_URL = null;
    }
}
