// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.awt.Polygon;
import java.awt.Rectangle;
import netcharts.util.NFParamDef;
import netcharts.util.NFUtil;
import java.util.Vector;
import netcharts.util.NFParam;
import java.awt.Graphics;
import java.awt.Color;
import netcharts.util.NFCompare;

public class NFRadialGrid implements NFCompare
{
    protected NFLine line;
    protected static double PERCENTAGE_DEFAULT;
    protected double percentage;
    protected static Color FILL_COLOR_DEFAULT;
    protected Color fillColor;
    
    public NFRadialGrid() {
        this.line = new NFLine(null);
        this.percentage = NFRadialGrid.PERCENTAGE_DEFAULT;
        this.fillColor = NFRadialGrid.FILL_COLOR_DEFAULT;
    }
    
    public int compare(final Object o, final Object o2) {
        final NFRadialGrid nfRadialGrid = (NFRadialGrid)o;
        final NFRadialGrid nfRadialGrid2 = (NFRadialGrid)o2;
        if (nfRadialGrid.getPercentage() < nfRadialGrid2.getPercentage()) {
            return -1;
        }
        if (nfRadialGrid.getPercentage() > nfRadialGrid2.getPercentage()) {
            return 1;
        }
        return 0;
    }
    
    public void setPercentage(final double percentage) {
        this.percentage = percentage;
    }
    
    public double getPercentage() {
        return this.percentage;
    }
    
    public void setLine(final NFLine line) {
        this.line = line;
    }
    
    public NFLine getLine() {
        return this.line;
    }
    
    public void setFillColor(final Color fillColor) {
        this.fillColor = fillColor;
    }
    
    public Color getFillColor() {
        return this.fillColor;
    }
    
    public static NFRadialGrid loadRadialGrid(final NFParam nfParam, final Vector vector) {
        if (vector == null) {
            return null;
        }
        final NFRadialGrid nfRadialGrid = new NFRadialGrid();
        double number = NFUtil.getNumber(vector, 0, NFRadialGrid.PERCENTAGE_DEFAULT);
        if (number < 0.0) {
            number = 0.0;
        }
        if (number > 100.0) {
            number = 100.0;
        }
        nfRadialGrid.setPercentage(number / 100.0);
        nfRadialGrid.setLine(NFLine.loadParams(nfParam, vector, 1));
        nfRadialGrid.setFillColor(NFUtil.getColor(vector, 4, NFRadialGrid.FILL_COLOR_DEFAULT));
        return nfRadialGrid;
    }
    
    public static Vector loadRadialGrids(final NFParam nfParam) throws Exception {
        final Vector<NFRadialGrid> vector = new Vector<NFRadialGrid>();
        final Vector vector2 = (Vector)nfParam.get("RadialGrids");
        for (int n = 0; vector2 != null && n < vector2.size(); ++n) {
            final NFRadialGrid loadRadialGrid = loadRadialGrid(nfParam, vector2.elementAt(n));
            if (loadRadialGrid != null) {
                vector.addElement(loadRadialGrid);
            }
        }
        return vector;
    }
    
    public static void defineRadialGrids(final NFParam nfParam) {
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(nfParam.defineNumber("RadialGridPercentage", new Double(0.0)));
        nfParam.defineLine("RadialGrids", vector);
        vector.addElement(nfParam.defineColor("RadialGridFill", null));
        nfParam.defineVector("RadialGrids", nfParam.defineTuple("RadialGridTuple", vector));
    }
    
    public void draw(final Graphics graphics, final Rectangle rectangle, final double n) {
        final Polygon polygon = new Polygon();
        final Rectangle rectangle2 = new Rectangle();
        final double n2 = (1.0 - n) * this.percentage + n;
        rectangle2.width = (int)(rectangle.width * n2);
        rectangle2.height = (int)(rectangle.height * n2);
        rectangle2.x = rectangle.x + (rectangle.width - rectangle2.width) / 2;
        rectangle2.y = rectangle.y + (rectangle.height - rectangle2.height) / 2;
        NFGraphSymbol.generateArc(polygon, rectangle2, 0.0, 360.0, 0);
        if (polygon.xpoints[0] != polygon.xpoints[polygon.npoints - 1] || polygon.ypoints[0] != polygon.ypoints[polygon.npoints - 1]) {
            polygon.addPoint(polygon.xpoints[0], polygon.ypoints[0]);
        }
        if (this.fillColor != null) {
            graphics.setColor(this.fillColor);
            graphics.fillPolygon(polygon);
        }
        if (this.line != null) {
            this.line.drawPoly(graphics, polygon);
        }
        else {
            graphics.drawPolygon(polygon);
        }
    }
    
    static {
        NFRadialGrid.PERCENTAGE_DEFAULT = 0.0;
        NFRadialGrid.FILL_COLOR_DEFAULT = null;
    }
}
