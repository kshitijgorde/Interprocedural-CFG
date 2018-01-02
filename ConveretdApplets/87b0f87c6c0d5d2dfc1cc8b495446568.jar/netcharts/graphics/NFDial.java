// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.util.Vector;
import java.awt.Color;
import java.awt.Polygon;

public class NFDial
{
    public static final int NONE = 0;
    public static final int INSIDE = 1;
    public static final int OUTSIDE = 2;
    public static final int CENTER = 3;
    public static final int END_TO_END = 4;
    public Polygon fill;
    public int fillStyle;
    public Color fillColor;
    public Polygon border;
    public int borderStyle;
    public NFLine borderLine;
    public String name;
    public double startAngle;
    public double stopAngle;
    public double radiusPercentage;
    public int ticLocation;
    public double min;
    public double max;
    public double step;
    public NFSpacing spacing;
    public int formatType;
    public String formatStr;
    public Vector sectors;
    public Vector hands;
    public NFPatternFill pattern;
    public NFLabel ticLabel;
    public Vector ticLabels;
    public double ticLength;
    public int ticWidth;
    public double ticLabelPos;
    public Color ticColor;
    public NFActiveLabel activeLabel;
    
    public NFDial() {
        this.fill = null;
        this.fillStyle = 3;
        this.fillColor = null;
        this.border = null;
        this.borderStyle = 0;
        this.borderLine = null;
        this.formatType = 2;
        this.pattern = null;
        this.ticLabel = new NFLabel();
        this.ticLabels = null;
        this.ticLength = 0.05;
        this.ticWidth = 1;
        this.ticLabelPos = 1.1;
        this.ticColor = Color.black;
        this.activeLabel = null;
    }
}
