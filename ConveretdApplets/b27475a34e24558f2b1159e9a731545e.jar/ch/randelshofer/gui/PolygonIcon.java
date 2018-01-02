// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.gui;

import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Polygon;

public class PolygonIcon implements Icon
{
    private Polygon[] polygons;
    private Dimension size;
    private Color color;
    
    public PolygonIcon(final Polygon[] polygons, final Dimension size) {
        this.polygons = polygons;
        this.size = size;
    }
    
    public PolygonIcon(final Polygon polygon, final Dimension size) {
        this.polygons = new Polygon[] { polygon };
        this.size = size;
    }
    
    public void setForeground(final Color color) {
        this.color = color;
    }
    
    public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
        graphics.setColor(component.isEnabled() ? ((this.color != null) ? this.color : component.getForeground()) : Color.gray);
        graphics.translate(n, n2);
        if (this.polygons != null) {
            for (int i = 0; i < this.polygons.length; ++i) {
                graphics.fillPolygon(this.polygons[i]);
                graphics.drawPolygon(this.polygons[i]);
            }
        }
        graphics.translate(-n, -n2);
    }
    
    public int getIconWidth() {
        return this.size.width;
    }
    
    public int getIconHeight() {
        return this.size.height;
    }
}
