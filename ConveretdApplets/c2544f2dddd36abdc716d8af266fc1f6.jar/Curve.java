import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Polygon;
import java.awt.geom.Point2D;

// 
// Decompiled by Procyon v0.5.30
// 

public class Curve
{
    protected Point2D.Double p1;
    protected Point2D.Double p2;
    protected Point2D.Double p3;
    protected Point2D.Double p4;
    protected Point2D.Double p5;
    protected Point2D.Double p3Point5;
    protected Point2D.Double p4Point5;
    
    public Curve(final Point2D.Double p1, final Point2D.Double p2, final Point2D.Double p3, final Point2D.Double p4, final Point2D.Double p5) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.p3Point5 = null;
        this.p4Point5 = null;
    }
    
    public Point2D.Double getP1() {
        return this.p1;
    }
    
    public Point2D.Double getP2() {
        return this.p2;
    }
    
    public Point2D.Double getP3() {
        return this.p3;
    }
    
    public Point2D.Double getP4() {
        return this.p4;
    }
    
    public Point2D.Double getP5() {
        return this.p5;
    }
    
    public Point2D.Double getP3Point5() {
        return this.p3Point5;
    }
    
    public Point2D.Double getP4Point5() {
        return this.p4Point5;
    }
    
    public void setP1(final Point2D.Double p) {
        this.p1 = p;
    }
    
    public void setP2(final Point2D.Double p) {
        this.p2 = p;
    }
    
    public void setP3(final Point2D.Double p) {
        this.p3 = p;
    }
    
    public void setP4(final Point2D.Double p) {
        this.p4 = p;
    }
    
    public void setP5(final Point2D.Double p) {
        this.p5 = p;
    }
    
    public void setP3Point5(final Point2D.Double p) {
        this.p3Point5 = p;
    }
    
    public void setP4Point5(final Point2D.Double p) {
        this.p4Point5 = p;
    }
    
    public Polygon getPolygon() {
        final Polygon p = new Polygon();
        for (final Point2D.Double point : this.getPointList()) {
            p.addPoint(Util.convertToInteger(point.getX()), Util.convertToInteger(point.getY()));
        }
        return p;
    }
    
    public ArrayList<Point2D.Double> getPointList() {
        final ArrayList<Point2D.Double> ps = new ArrayList<Point2D.Double>();
        ps.add(this.p1);
        ps.add(this.p2);
        ps.add(this.p3);
        if (this.p3Point5 != null) {
            ps.add(this.p3Point5);
        }
        ps.add(this.p4);
        if (this.p4Point5 != null) {
            ps.add(this.p4Point5);
        }
        ps.add(this.p5);
        return ps;
    }
    
    public void scale(final Point centre, final double multiplier) {
        for (final Point2D.Double point : this.getPointList()) {
            point.x = Util.scaleCoordinate(point.x, centre.x, multiplier);
            point.y = Util.scaleCoordinate(point.y, centre.y, multiplier);
        }
    }
    
    public void scale(final double multiplier) {
        for (final Point2D.Double point : this.getPointList()) {
            point.x *= multiplier;
            point.y *= multiplier;
        }
    }
    
    public Point2D.Double findCentre() {
        double xCount = 0.0;
        double yCount = 0.0;
        int count = 0;
        for (final Point2D.Double point : this.getPointList()) {
            xCount += point.x;
            yCount += point.y;
            ++count;
        }
        return new Point2D.Double(xCount / count, yCount / count);
    }
    
    public void move(final double xMove, final double yMove) {
        for (final Point2D.Double point : this.getPointList()) {
            point.setLocation(point.x + xMove, point.y + yMove);
        }
    }
    
    @Override
    public String toString() {
        final StringBuffer retBuf = new StringBuffer();
        for (final Point2D.Double point : this.getPointList()) {
            retBuf.append(String.valueOf(point.x) + "," + point.y + " ");
        }
        final String ret = retBuf.toString();
        return ret.trim();
    }
    
    public String generateSVGPolygon() {
        final String ret = this.generateSVGPolygon("fill:none;stroke:black");
        return ret;
    }
    
    public String generateSVGPolygon(final String style) {
        final StringBuffer retBuf = new StringBuffer();
        retBuf.append("<polygon points=\"");
        for (final Point2D.Double p : this.getPointList()) {
            retBuf.append(String.valueOf(p.x) + "," + p.y + " ");
        }
        String ret = retBuf.toString().trim();
        ret = String.valueOf(ret) + "\" style=\"";
        ret = String.valueOf(ret) + style;
        ret = String.valueOf(ret) + "\"/>";
        return ret.toString();
    }
    
    public Curve clone() {
        final Point2D.Double newP1 = new Point2D.Double(this.p1.x, this.p1.y);
        final Point2D.Double newP2 = new Point2D.Double(this.p2.x, this.p2.y);
        final Point2D.Double newP3 = new Point2D.Double(this.p3.x, this.p3.y);
        final Point2D.Double newP4 = new Point2D.Double(this.p4.x, this.p4.y);
        final Point2D.Double newP5 = new Point2D.Double(this.p5.x, this.p5.y);
        Point2D.Double newP3Point5 = null;
        if (this.p3Point5 != null) {
            newP3Point5 = new Point2D.Double(this.p3Point5.x, this.p3Point5.y);
        }
        Point2D.Double newP4Point5 = null;
        if (this.p4Point5 != null) {
            newP4Point5 = new Point2D.Double(this.p4Point5.x, this.p4Point5.y);
        }
        final Curve cloneCurve = new Curve(newP1, newP2, newP3, newP4, newP5);
        cloneCurve.setP3Point5(newP3Point5);
        cloneCurve.setP4Point5(newP4Point5);
        return cloneCurve;
    }
}
