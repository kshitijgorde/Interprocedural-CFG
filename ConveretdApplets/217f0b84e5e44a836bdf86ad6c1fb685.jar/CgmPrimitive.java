import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class CgmPrimitive implements Visibility
{
    static final double H5 = 0.5;
    static final double H6 = 0.6;
    double x;
    double y;
    double Width;
    double Height;
    Color LineColor;
    Color FillColor;
    double LineWidth;
    int LineType;
    int IntStyle;
    int visibility;
    boolean noclip;
    boolean closed;
    Graphics g;
    
    public CgmPrimitive() {
        this.x = 0.0;
        this.y = 0.0;
        this.Width = 0.0;
        this.Height = 0.0;
        this.LineColor = null;
        this.FillColor = null;
        this.LineWidth = 0.0;
        this.LineType = 0;
        this.IntStyle = 0;
        this.visibility = 3;
        this.noclip = false;
        this.closed = false;
    }
    
    abstract void draw(final Graphics p0, final double p1, final double p2, final boolean p3) throws AbstractMethodError;
    
    boolean find(final double n, final double n2) {
        return false;
    }
    
    int find(final String s, final int n) {
        return n;
    }
    
    void move(final double n, final double n2) {
        this.x += n;
        this.y += n2;
    }
    
    void setClosed() {
        this.closed = true;
    }
}
