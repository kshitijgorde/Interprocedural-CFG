// 
// Decompiled by Procyon v0.5.30
// 

package modules.bsx;

import java.awt.Color;
import java.awt.Polygon;

public class BSXPolygon extends Polygon
{
    private Color[] colTable;
    private Color Pcolor;
    
    public BSXPolygon() {
        this.colTable = new Color[] { new Color(0, 0, 0), new Color(0, 0, 255), new Color(34, 139, 34), new Color(135, 206, 235), new Color(205, 92, 92), new Color(255, 105, 180), new Color(165, 42, 42), new Color(211, 211, 211), new Color(105, 105, 105), new Color(0, 191, 255), new Color(0, 255, 0), new Color(0, 255, 255), new Color(255, 99, 71), new Color(255, 0, 255), new Color(255, 255, 0), new Color(255, 255, 255) };
        this.Pcolor = Color.black;
    }
    
    public BSXPolygon(final int color) {
        this.colTable = new Color[] { new Color(0, 0, 0), new Color(0, 0, 255), new Color(34, 139, 34), new Color(135, 206, 235), new Color(205, 92, 92), new Color(255, 105, 180), new Color(165, 42, 42), new Color(211, 211, 211), new Color(105, 105, 105), new Color(0, 191, 255), new Color(0, 255, 0), new Color(0, 255, 255), new Color(255, 99, 71), new Color(255, 0, 255), new Color(255, 255, 0), new Color(255, 255, 255) };
        this.Pcolor = Color.black;
        this.Pcolor = new Color(color);
    }
    
    public BSXPolygon(final int[] xpoints, final int[] ypoints, final int npoints, final int color) {
        super(xpoints, ypoints, npoints);
        this.colTable = new Color[] { new Color(0, 0, 0), new Color(0, 0, 255), new Color(34, 139, 34), new Color(135, 206, 235), new Color(205, 92, 92), new Color(255, 105, 180), new Color(165, 42, 42), new Color(211, 211, 211), new Color(105, 105, 105), new Color(0, 191, 255), new Color(0, 255, 0), new Color(0, 255, 255), new Color(255, 99, 71), new Color(255, 0, 255), new Color(255, 255, 0), new Color(255, 255, 255) };
        this.Pcolor = Color.black;
        this.Pcolor = this.colTable[color];
    }
    
    public Color getColor() {
        return this.Pcolor;
    }
    
    public void setColor(final int color) {
        this.Pcolor = this.colTable[color];
    }
}
