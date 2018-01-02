import java.awt.Point;
import java.net.URL;
import java.awt.Polygon;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class area
{
    private static final int RECT = 1;
    private static final int ELLIPSE = 2;
    private static final int POLYGON = 3;
    private int type;
    private Rectangle rect;
    private int x;
    private int y;
    private int a;
    private int b;
    private Polygon poly;
    public URL url;
    public String target;
    public String[] text;
    public int numlines;
    public int boxLeft;
    public int boxTop;
    public int boxWidth;
    public int boxHeight;
    
    public area(final Rectangle rect) {
        this.url = null;
        this.target = null;
        this.text = new String[20];
        this.numlines = 0;
        this.boxLeft = 0;
        this.boxTop = 0;
        this.boxWidth = 0;
        this.boxHeight = 0;
        this.type = 1;
        this.rect = rect;
    }
    
    public area(final int x, final int y, final int a, final int b) {
        this.url = null;
        this.target = null;
        this.text = new String[20];
        this.numlines = 0;
        this.boxLeft = 0;
        this.boxTop = 0;
        this.boxWidth = 0;
        this.boxHeight = 0;
        this.type = 2;
        this.x = x;
        this.y = y;
        this.a = Math.abs(a);
        this.b = Math.abs(b);
    }
    
    public area(final Polygon poly) {
        this.url = null;
        this.target = null;
        this.text = new String[20];
        this.numlines = 0;
        this.boxLeft = 0;
        this.boxTop = 0;
        this.boxWidth = 0;
        this.boxHeight = 0;
        this.type = 3;
        this.poly = poly;
    }
    
    public Rectangle getRect() {
        if (this.type == 1) {
            return this.rect;
        }
        return new Rectangle();
    }
    
    public Rectangle getEllipse() {
        if (this.type == 2) {
            return new Rectangle(this.x - this.a, this.y - this.b, this.a * 2, this.b * 2);
        }
        return new Rectangle();
    }
    
    public Polygon getPolygon() {
        if (this.type == 3) {
            return this.poly;
        }
        return new Polygon();
    }
    
    public boolean isRect() {
        return this.type == 1;
    }
    
    public boolean isEllipse() {
        return this.type == 2;
    }
    
    public boolean isPolygon() {
        return this.type == 3;
    }
    
    public Rectangle getBounds() {
        Rectangle rect = null;
        if (this.isRect()) {
            rect = this.rect;
        }
        if (this.isEllipse()) {
            rect = this.getEllipse();
        }
        if (this.isPolygon()) {
            rect = this.poly.getBounds();
        }
        return rect;
    }
    
    public Point getCentroid() {
        final Point point = new Point();
        final Rectangle rect = this.getBounds();
        point.x = rect.x + rect.width / 2;
        point.y = rect.y + rect.height / 2;
        return point;
    }
    
    public boolean inside(int x, int y) {
        if (this.isRect()) {
            return this.rect.contains(x, y);
        }
        if (this.isEllipse()) {
            x -= this.x;
            y -= this.y;
            if (Math.abs(x) > this.a || Math.abs(y) > this.b) {
                return false;
            }
            final double v = (int)(this.b * Math.sqrt(1.0 - x * x / (this.a * this.a)));
            if (Math.abs(y) <= v) {
                return true;
            }
        }
        return this.isPolygon() && this.poly.contains(x, y);
    }
    
    public void setURL(final URL url) {
        this.url = url;
    }
    
    public void setURL(final URL url, final String target) {
        this.url = url;
        this.target = target;
    }
    
    public void setText(final String text) {
        this.text[this.numlines] = text;
        ++this.numlines;
    }
    
    public void setTextBoxPosition(final int x, final int y) {
        this.boxLeft = x;
        this.boxTop = y;
    }
    
    public void setTextBoxSize(final int w, final int h) {
        this.boxWidth = w;
        this.boxHeight = h;
    }
}
