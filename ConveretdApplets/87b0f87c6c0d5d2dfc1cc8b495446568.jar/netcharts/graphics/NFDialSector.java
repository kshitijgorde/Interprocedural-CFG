// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Polygon;
import java.awt.Color;

public class NFDialSector
{
    public String name;
    public String text;
    public Color color;
    public NFLabel label;
    public double labelPos;
    public NFDial dial;
    public double orp;
    public double irp;
    public double startValue;
    public double stopValue;
    public NFPatternFill pattern;
    public NFActiveLabel activeLabel;
    public double angle;
    public double arc;
    public Polygon poly;
    public Rectangle rect;
    public Point center;
    public NFLine borderLine;
    
    public NFDialSector() {
        this.labelPos = 1.1;
        this.orp = 0.0;
        this.irp = 0.0;
        this.pattern = null;
        this.activeLabel = null;
        this.borderLine = null;
    }
}
