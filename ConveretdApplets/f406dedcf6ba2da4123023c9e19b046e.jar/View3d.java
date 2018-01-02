import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class View3d extends View3dInfo implements Cloneable
{
    public Graphics g;
    private int width;
    private int height;
    private double adjwx;
    private double adjwy;
    protected int frameNo;
    protected int lastFrame;
    public double xscale;
    public double yscale;
    public static Color bgcolor;
    protected Color ambient;
    protected Color lightColor;
    protected Color[] colors;
    protected int defaultColor;
    protected Point3d lightDirection;
    
    public View3d(final Point3d point3d) {
        super(point3d);
        this.width = 100;
        this.height = 100;
        this.frameNo = 0;
        this.lastFrame = 0;
        this.ambient = new Color(50, 50, 50);
        this.lightColor = new Color(205, 205, 205);
        this.colors = new Color[] { Color.blue, Color.green, Color.red, Color.yellow, Color.black, Color.black, Color.cyan, Color.magenta, Color.cyan, Color.magenta };
        this.defaultColor = 0;
        this.lightDirection = null;
    }
    
    public void set(final View3dInfo view3dInfo) {
        super.set(view3dInfo);
    }
    
    public void setGraphics(final Graphics g, final int width, final int height) {
        this.g = g;
        this.width = width;
        this.height = height;
        this.setWindow(super.wx, super.wy, super.wwidth, super.wheight);
    }
    
    public void setWindow(final double adjwx, final double adjwy, final double n, final double n2) {
        super.setWindow(adjwx, adjwy, n, n2);
        if (this.width == 0) {
            return;
        }
        this.xscale = this.width / n;
        this.yscale = this.height / n2;
        if (this.yscale > this.xscale) {
            this.yscale = this.xscale;
            this.adjwy += (n2 - this.height / this.yscale) / 2.0;
            this.adjwx = adjwx;
        }
        else {
            this.xscale = this.yscale;
            this.adjwx += (n - this.width / this.xscale) / 2.0;
            this.adjwy = adjwy;
        }
    }
    
    public void setFrameNo(final int frameNo) {
        this.frameNo = frameNo;
    }
    
    public int getFrameNo() {
        return this.frameNo;
    }
    
    public void setLastFrame(final int lastFrame) {
        this.lastFrame = lastFrame;
    }
    
    public int getLastFrame() {
        return this.lastFrame;
    }
    
    public void setColor(final int n, final Color color) {
        this.colors[n] = color;
    }
    
    public int getColorIndex(final int n) {
        return (n == -1) ? this.defaultColor : n;
    }
    
    public Color getColor(final int n) {
        return this.colors[this.getColorIndex(n)];
    }
    
    public int setDefaultColor(final int defaultColor) {
        final int defaultColor2 = this.defaultColor;
        this.defaultColor = defaultColor;
        return defaultColor2;
    }
    
    private static int clamp(final double n) {
        if (n >= 255.0) {
            return 255;
        }
        if (n <= 0.0) {
            return 0;
        }
        return (int)n;
    }
    
    public Point toPoint(final Point3d point3d) {
        final double n = 1.0 - super.dinverse * super.w.dot(point3d);
        return new Point((int)((super.u.dot(point3d) / n - super.wx) * this.xscale), this.height - (int)((super.v.dot(point3d) / n - super.wy) * this.yscale));
    }
    
    public void clear() {
        this.g.setColor(View3d.bgcolor);
        this.g.fillRect(0, 0, this.width, this.height);
    }
    
    public void drawLine(final Point3d point3d, final Point3d point3d2) {
        final Point point = this.toPoint(point3d);
        final Point point2 = this.toPoint(point3d2);
        this.g.drawLine(point.x, point.y, point2.x, point2.y);
    }
    
    public void drawString(final String s, final Point3d point3d) {
        final Point point = this.toPoint(point3d);
        this.g.drawString(s, point.x, point.y);
    }
    
    public void drawStringBelow(final String s, final Point3d point3d) {
        final Point point = this.toPoint(point3d);
        final FontMetrics fontMetrics = this.g.getFontMetrics();
        this.g.drawString(s, point.x - fontMetrics.stringWidth(s) / 2, point.y + fontMetrics.getAscent() + 2);
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
    }
    
    static {
        View3d.bgcolor = Color.white;
    }
}
