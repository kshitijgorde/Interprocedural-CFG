import java.net.URL;
import java.util.Vector;
import java.awt.Polygon;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class hypermapareav6
{
    private static final int RECT = 1;
    private static final int ELLIPSE = 2;
    private static final int POLY = 3;
    private int shape;
    private Rectangle rect;
    private int x;
    private int y;
    private int a;
    private int b;
    private Polygon poly;
    public String status;
    public int width;
    public int height;
    public Vector areaurlvector;
    private String description;
    private String target;
    private int lbx;
    private int lby;
    private boolean lbsorted;
    private int top;
    private int bottom;
    private int left;
    private int right;
    
    public hypermapareav6(final Rectangle rect, final int n, final int n2, final int n3, final int n4, final String s, final String s2, final String s3, final String s4) {
        this.areaurlvector = new Vector(10, 5);
        this.lbsorted = false;
        this.shape = 1;
        this.rect = rect;
        this.top = this.getY();
        this.bottom = this.getY() + this.getRect().height;
        this.left = this.getX();
        this.right = this.getX() + this.getRect().width;
        this.setlistboxlocation(n, n2, n3, n4, s, s2, s3, s4);
    }
    
    public hypermapareav6(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final String s, final String s2, final String s3, final String s4) {
        this.areaurlvector = new Vector(10, 5);
        this.lbsorted = false;
        this.shape = 2;
        this.x = n;
        this.y = n2;
        this.a = Math.abs(n3);
        this.b = Math.abs(n4);
        this.top = n2;
        this.bottom = this.b;
        this.left = n;
        this.right = this.a;
        this.setlistboxlocation(n5, n6, n7, n8, s, s2, s3, s4);
    }
    
    public hypermapareav6(final Polygon poly, final int n, final int n2, final int n3, final int n4, final String s, final String s2, final String s3, final String s4) {
        this.areaurlvector = new Vector(10, 5);
        this.lbsorted = false;
        this.shape = 3;
        this.poly = poly;
        this.top = this.getY();
        this.bottom = this.getY();
        this.left = this.getX();
        this.right = this.getX();
        this.setlistboxlocation(n, n2, n3, n4, s, s2, s3, s4);
    }
    
    private void setlistboxlocation(final int n, final int n2, final int n3, final int n4, final String s, final String s2, final String s3, final String s4) {
        this.setlbxlby(n, n2, n3, n4, s);
        if (this.lbsorted) {
            return;
        }
        this.setlbxlby(n, n2, n3, n4, s2);
        if (this.lbsorted) {
            return;
        }
        this.setlbxlby(n, n2, n3, n4, s3);
        if (this.lbsorted) {
            return;
        }
        this.setlbxlby(n, n2, n3, n4, s4);
        if (this.lbsorted) {
            return;
        }
        this.setlbxlby(n, n2, n3, n4, "topleft");
        if (this.lbsorted) {
            return;
        }
        this.setlbxlby(n, n2, n3, n4, "topright");
        if (this.lbsorted) {
            return;
        }
        this.setlbxlby(n, n2, n3, n4, "bottomleft");
        if (this.lbsorted) {
            return;
        }
        this.setlbxlby(n, n2, n3, n4, "bottomright");
        if (this.lbsorted) {
            return;
        }
        this.setlbxlby(n, n2, n3, n4, "end");
    }
    
    private void setlbxlby(final int n, final int n2, final int n3, final int n4, final String s) {
        if (s.equalsIgnoreCase("left") && this.left - n3 >= 0 && n2 - (this.top + n4) >= 0) {
            this.lbx = this.left - n3 - 1;
            this.lby = this.top;
            this.lbsorted = true;
            return;
        }
        if (s.equalsIgnoreCase("right") && n - (this.right + n3) >= 0 && n2 - (this.top + n4) >= 0) {
            this.lbx = this.right + 1;
            this.lby = this.top;
            this.lbsorted = true;
            return;
        }
        if (s.equalsIgnoreCase("top") && this.top - n4 - 1 >= 0 && n - (this.left + n3) >= 0) {
            this.lbx = this.left;
            this.lby = this.top - n4 - 1;
            this.lbsorted = true;
            return;
        }
        if (s.equalsIgnoreCase("bottom") && n2 - (this.bottom + n4) >= 0 && n - (this.left + n3) >= 0) {
            this.lbx = this.left;
            this.lby = this.bottom + 1;
            this.lbsorted = true;
            return;
        }
        if (s.equalsIgnoreCase("topleft") && this.left - n3 >= 0 && n2 - (this.top - n4) >= 0) {
            this.lbx = this.left - n3 - 1;
            this.lby = this.top - n4;
            this.lbsorted = true;
            return;
        }
        if (s.equalsIgnoreCase("topright") && n - (this.right + n3) >= 0 && n2 - (this.top - n4) >= 0) {
            this.lbx = this.right;
            this.lby = this.top - n4;
            this.lbsorted = true;
            return;
        }
        if (s.equalsIgnoreCase("bottomleft") && this.left - n3 >= 0 && n2 - (this.bottom + n4) >= 0) {
            this.lbx = this.left - n3;
            this.lby = this.bottom;
            this.lbsorted = true;
            return;
        }
        if (s.equalsIgnoreCase("bottomright") && n - (this.right + n3) >= 0 && n2 - (this.bottom + n4) >= 0) {
            this.lbx = this.right;
            this.lby = this.bottom;
            this.lbsorted = true;
            return;
        }
        if (s.equalsIgnoreCase("end")) {
            this.lbx = 0;
            this.lby = 0;
            this.lbsorted = true;
        }
    }
    
    public int getX() {
        int n = 0;
        if (this.isRect()) {
            n = this.getRect().x;
        }
        if (this.isEllipse()) {
            n = this.x;
        }
        if (this.isPoly()) {
            n = this.getPoly().xpoints[0];
        }
        return n;
    }
    
    public int getY() {
        int n = 0;
        if (this.isRect()) {
            n = this.getRect().y;
        }
        if (this.isEllipse()) {
            n = this.y;
        }
        if (this.isPoly()) {
            n = this.getPoly().ypoints[0];
        }
        return n;
    }
    
    public boolean isRect() {
        return this.shape == 1;
    }
    
    public boolean isEllipse() {
        return this.shape == 2;
    }
    
    public boolean isPoly() {
        return this.shape == 3;
    }
    
    public Rectangle getRect() {
        if (this.isRect()) {
            return this.rect;
        }
        return new Rectangle();
    }
    
    public Rectangle getEllipse() {
        if (this.isEllipse()) {
            return new Rectangle(this.x - this.a, this.y - this.b, this.a * 2, this.b * 2);
        }
        return new Rectangle();
    }
    
    public Polygon getPoly() {
        if (this.isPoly()) {
            return this.poly;
        }
        return new Polygon();
    }
    
    public Rectangle getBoundingBox() {
        Rectangle rectangle = new Rectangle();
        if (this.isRect()) {
            rectangle = this.rect;
        }
        if (this.isEllipse()) {
            rectangle = this.getEllipse();
        }
        if (this.isPoly()) {
            rectangle = this.poly.getBounds();
        }
        return rectangle;
    }
    
    public void setURL(final URL url, final String s) {
        this.areaurlvector.addElement(new hypermapareaurlv6(url, s));
    }
    
    public Vector getareaurlvector() {
        return this.areaurlvector;
    }
    
    public void setStatusMsg(final String status) {
        this.status = status;
    }
    
    public boolean inside(int n, int n2) {
        if (this.isRect()) {
            return this.rect.contains(n, n2);
        }
        if (this.isEllipse()) {
            n -= this.x;
            n2 -= this.y;
            if (Math.abs(n) > this.a || Math.abs(n2) > this.b) {
                return false;
            }
            if (Math.abs(n2) <= (int)(this.b * Math.sqrt(1.0 - n * n / (this.a * this.a)))) {
                return true;
            }
        }
        return this.isPoly() && this.poly.contains(n, n2);
    }
    
    public void addDescription(final String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void addTarget(final String target) {
        this.target = target;
    }
    
    public String getTarget() {
        if (this.target.equalsIgnoreCase("current")) {
            return "current";
        }
        if (this.target.equalsIgnoreCase("parent")) {
            return "_parent";
        }
        if (this.target.equalsIgnoreCase("top")) {
            return "_top";
        }
        if (this.target == null) {
            return "_blank";
        }
        return this.target;
    }
    
    public int getlbx() {
        return this.lbx;
    }
    
    public int getlby() {
        return this.lby;
    }
    
    public int getlabelx(final int n, final int n2) {
        final int n3 = (this.right + this.left) / 2 - n / 2;
        int left;
        if (n3 + n > n2) {
            left = n2 - n - 2;
        }
        else {
            left = n3;
        }
        if (left < 0) {
            left = this.left;
        }
        return left;
    }
    
    public int getlabely() {
        return this.bottom + 2;
    }
    
    public int getsize() {
        return this.areaurlvector.size();
    }
    
    public void sortIt() {
        for (int i = 0; i < this.areaurlvector.size(); ++i) {
            for (int j = 0; j < this.areaurlvector.size() - 1; ++j) {
                if (((hypermapareaurlv6)this.getareaurlvector().elementAt(j)).getDescription().compareTo(((hypermapareaurlv6)this.getareaurlvector().elementAt(j + 1)).getDescription()) > 0) {
                    final hypermapareaurlv6 hypermapareaurlv6 = this.getareaurlvector().elementAt(j);
                    this.areaurlvector.setElementAt(this.getareaurlvector().elementAt(j + 1), j);
                    this.areaurlvector.setElementAt(hypermapareaurlv6, j + 1);
                }
            }
        }
    }
}
