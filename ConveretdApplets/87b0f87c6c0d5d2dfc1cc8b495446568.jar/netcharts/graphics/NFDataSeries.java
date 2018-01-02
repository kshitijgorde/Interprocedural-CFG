// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.util.Vector;
import java.awt.Color;
import netcharts.util.NFDataSet;

public class NFDataSeries
{
    public String name;
    public NFDataSet dataset;
    public Color c;
    public NFGraphSymbol sym;
    public NFLine line;
    public Color fillColor;
    public int type;
    public NFRegion region;
    public Vector activeLabels;
    public Object info;
    public NFAxis XAxis;
    public NFAxis YAxis;
    public int lineCurveType;
    public NFPatternFill pattern;
    public int valueLabelStyle;
    
    public NFDataSeries() {
        this.name = null;
        this.dataset = null;
        this.c = null;
        this.sym = null;
        this.line = null;
        this.fillColor = null;
        this.type = 0;
        this.region = null;
        this.activeLabels = new Vector();
        this.info = null;
        this.XAxis = null;
        this.YAxis = null;
        this.lineCurveType = 0;
        this.pattern = null;
        this.valueLabelStyle = 0;
    }
}
