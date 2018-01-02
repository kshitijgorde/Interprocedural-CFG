import java.applet.AudioClip;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class Area
{
    public static final int RECT = 1;
    public static final int CIRC = 2;
    public static final int POLY = 3;
    public static final int THREED = 0;
    public static final int CENTER = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public int shape;
    public int items;
    public int alignment;
    boolean poped;
    private Rectangle rect;
    private int x;
    private int y;
    private int r;
    private Polygon poly;
    public int width;
    public int height;
    public int style;
    public Rectangle popBox;
    public String[] item;
    public String[] url;
    public String[] status;
    public String defaultUrl;
    public String defaultStatus;
    public String targetFrame;
    public Image image;
    public int imageX;
    public int imageY;
    public boolean hasImage;
    public AudioClip audio;
    public boolean hasAudio;
    
    public Area(final int items, final Rectangle rect) {
        this.alignment = 1;
        this.poped = false;
        this.style = 0;
        this.hasImage = false;
        this.hasAudio = false;
        this.items = items;
        this.item = new String[items];
        this.url = new String[items];
        this.status = new String[items];
        this.shape = 1;
        this.rect = rect;
    }
    
    public Area(final int items, final int x, final int y, final int r) {
        this.alignment = 1;
        this.poped = false;
        this.style = 0;
        this.hasImage = false;
        this.hasAudio = false;
        this.items = items;
        this.item = new String[items];
        this.url = new String[items];
        this.status = new String[items];
        this.shape = 2;
        this.x = x;
        this.y = y;
        this.r = r;
    }
    
    public Area(final int items, final Polygon poly) {
        this.alignment = 1;
        this.poped = false;
        this.style = 0;
        this.hasImage = false;
        this.hasAudio = false;
        this.items = items;
        this.item = new String[items];
        this.url = new String[items];
        this.status = new String[items];
        this.shape = 3;
        this.poly = poly;
    }
    
    public final Rectangle getRect() {
        if (this.shape == 1) {
            return this.rect;
        }
        return new Rectangle();
    }
    
    public final Rectangle getCirc() {
        if (this.shape == 2) {
            return new Rectangle(this.x - this.r, this.y - this.r, this.r * 2, this.r * 2);
        }
        return new Rectangle();
    }
    
    public final Polygon getPoly() {
        if (this.shape == 3) {
            return this.poly;
        }
        return new Polygon();
    }
    
    public final Rectangle getBoundingBox() {
        Rectangle rectangle = new Rectangle();
        if (this.shape == 1) {
            rectangle = this.rect;
        }
        if (this.shape == 2) {
            rectangle = new Rectangle(this.x - this.r, this.y - this.r, 2 * this.r, 2 * this.r);
        }
        if (this.shape == 3) {
            rectangle = this.poly.getBoundingBox();
        }
        return rectangle;
    }
    
    public final boolean inside(final int n, final int n2) {
        if (this.poped && this.popBox.inside(n, n2)) {
            return true;
        }
        if (this.shape == 1) {
            return this.rect.inside(n, n2);
        }
        if (this.shape == 2) {
            final int n3 = this.x - n;
            final int n4 = this.y - n2;
            if (Math.sqrt(n3 * n3 + n4 * n4) <= this.r) {
                return true;
            }
        }
        return this.shape == 3 && this.poly.inside(n, n2);
    }
    
    public final int overItem(final int n, final int n2, final int n3) {
        int n4 = -1;
        if (this.poped && this.popBox.inside(n, n2)) {
            n4 = (n2 - this.popBox.y - 10) / n3;
            if (n4 >= this.items) {
                n4 = this.items - 1;
            }
        }
        return n4;
    }
}
