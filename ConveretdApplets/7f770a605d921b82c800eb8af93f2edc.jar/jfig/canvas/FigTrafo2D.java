// 
// Decompiled by Procyon v0.5.30
// 

package jfig.canvas;

import jfig.utils.ExceptionTracer;
import java.awt.Point;

public class FigTrafo2D implements Cloneable
{
    public static final int dpi = 2400;
    public static final int DPI = 2400;
    public static final int DOTS_PER_MM = 96;
    public static final int DOTS_PER_INCH = 75;
    public static final int DOTS_PER_XMM = 80;
    public static final int UNITS_MM = 1;
    public static final int UNITS_INCHES = 2;
    public static final int UNITS_XMM = 3;
    public static String[] UNITS_STRING;
    public static final int NO_GRID = 0;
    public static final int FINE_GRID = 240;
    public static final int MEDIUM_GRID = 480;
    public static final int COARSE_GRID = 960;
    public static final int TEN_GRID = 961;
    public static final int NO_SNAP = 1;
    public static final int COARSE_SNAP = 2;
    public static final int MEDIUM_SNAP = 3;
    public static final int FINE_SNAP = 4;
    public static final int TEN_SNAP = 10;
    public static final double MIN_ZOOM_FACTOR = 0.03125;
    public static final double MAX_ZOOM_FACTOR = 32.0;
    public static final boolean debug = false;
    static final double[] offsets;
    static final double[] factors;
    static final double FACTOR = 2.0;
    public int units;
    protected double zoom_factor;
    protected int zoom_factor75;
    protected double d_zoom_factor;
    protected double snapDelta;
    public int snapMode;
    public int gridMode;
    public long timestamp;
    protected Point anchor;
    protected Point p;
    
    public void setAnchor(final Point point) {
        this.anchor.x = point.x;
        this.anchor.y = point.y;
        this.timestamp = System.currentTimeMillis();
    }
    
    public Point getAnchor() {
        return this.anchor;
    }
    
    public void set_zoom(double zoom_factor) {
        if (zoom_factor >= 32.0) {
            zoom_factor = 32.0;
        }
        if (zoom_factor <= 0.03125) {
            zoom_factor = 0.03125;
        }
        this.zoom_factor = zoom_factor;
        this.zoom_factor75 = (int)(75.0 * this.zoom_factor);
        if (this.zoom_factor75 < 1) {
            this.zoom_factor75 = 1;
        }
        this.d_zoom_factor = this.zoom_factor * 75.0 / 2400.0;
        this.setSnapRelative(this.snapMode);
        this.timestamp = System.currentTimeMillis();
    }
    
    public void set_zoom_region(int x, int y, int n, int n2, final int n3, final int n4) {
        if (x > n) {
            final int n5 = x;
            x = n;
            n = n5;
        }
        if (y > n2) {
            final int n6 = y;
            y = n2;
            n2 = n6;
        }
        if (n == x) {
            n = x + 1;
        }
        if (n2 == y) {
            n2 = y + 1;
        }
        this.anchor.x = x;
        this.anchor.y = y;
        double min = Math.min(32.0 * n3 / (n - x), 32.0 * n4 / (n2 - y));
        for (int i = 0; i < FigTrafo2D.factors.length; ++i) {
            if (1.1 * min > FigTrafo2D.factors[i]) {
                min = FigTrafo2D.factors[i];
                break;
            }
        }
        this.set_zoom(Math.max(0.013333333333333334, min));
    }
    
    public void set_zoom_region_ooooolllllldddd(int x, int y, int n, int n2, final int n3, final int n4) {
        if (x > n) {
            final int n5 = x;
            x = n;
            n = n5;
        }
        if (y > n2) {
            final int n6 = y;
            y = n2;
            n2 = n6;
        }
        if (n == x) {
            n = x + 1;
        }
        if (n2 == y) {
            n2 = y + 1;
        }
        this.anchor.x = x;
        this.anchor.y = y;
        double min;
        double n7;
        for (min = Math.min(32.0 * n3 / (n - x), 32.0 * n4 / (n2 - y)), n7 = 1.52587890625E-5; n7 < min; n7 *= 2.0) {}
        this.set_zoom(Math.max(0.013333333333333334, n7));
    }
    
    public double getZoomFactor() {
        return this.zoom_factor;
    }
    
    public double getZoom() {
        return this.zoom_factor;
    }
    
    public double getMinZoomFactor() {
        return 0.03125;
    }
    
    public double getMaxZoomFactor() {
        return 32.0;
    }
    
    public double getZoomFactor_MultipleOfTwo() {
        double n = 1.0;
        if (this.zoom_factor >= 1.0) {
            while (n < this.zoom_factor) {
                n *= 2.0;
            }
        }
        else {
            while (n >= this.zoom_factor) {
                n *= 0.5;
            }
            n *= 2.0;
        }
        return n;
    }
    
    public int get_units() {
        return this.units;
    }
    
    public int getUnits() {
        return this.get_units();
    }
    
    public String get_units_string() {
        return FigTrafo2D.UNITS_STRING[this.units];
    }
    
    public double getValueInUnits(final int n) {
        if (this.units == 1) {
            return n / 960.0;
        }
        if (this.units == 2) {
            return n / 2400.0;
        }
        if (this.units == 3) {
            return n / 80.0;
        }
        System.err.println("-E- FigTrafo2D: units not set?");
        return n;
    }
    
    public void setSnapAbsolute(final double snapDelta) {
        this.snapDelta = snapDelta;
    }
    
    public void setSnapRelative(final int snapMode) {
        this.snapDelta = 1.0;
        this.snapMode = snapMode;
        if (snapMode == 1) {
            return;
        }
        int n;
        if (snapMode == 2) {
            n = 2;
        }
        else if (snapMode == 3) {
            n = 4;
        }
        else if (snapMode == 4) {
            n = 8;
        }
        else {
            if (snapMode != 10) {
                this.message("-E- FigTrafo2D.setSnapRelative: illegal snap mode " + snapMode);
                this.snapMode = 1;
                return;
            }
            n = 10;
        }
        if (this.units == 1) {
            this.snapDelta = 1920.0;
        }
        else if (this.units == 2) {
            this.snapDelta = 2400.0;
        }
        else {
            if (this.units != 3) {
                this.message("-E- FigTrafo2D.setSnapRelative: illegal units:" + this.units);
                this.snapDelta = 1.0;
                return;
            }
            this.snapDelta = 1800.0;
        }
        this.snapDelta /= n * this.getZoomFactor_MultipleOfTwo();
    }
    
    public double getSnap() {
        return Math.max(this.snapDelta, 1.0);
    }
    
    public int getSnapRelative() {
        if (this.snapDelta > 1.0) {
            return (int)this.snapDelta;
        }
        return this.screen_to_wc(8);
    }
    
    public boolean insideSnap(final int n, final int n2) {
        return Math.abs(n - n2) <= this.getSnapRelative();
    }
    
    public double getGridSpacingValue() {
        double n;
        if (this.units == 1) {
            n = 1920.0 / this.zoom_factor;
        }
        else if (this.units == 2) {
            n = 2400.0 / this.zoom_factor;
        }
        else if (this.units == 3) {
            n = 1800.0 / this.zoom_factor;
        }
        else {
            System.out.println("FigTrafo2D.getGridSpacing: illegal units!");
            n = 1.0;
        }
        return n;
    }
    
    public int screen_to_wc(final int n) {
        return (int)(n * 32.0 / this.zoom_factor);
    }
    
    public int screen_to_wc_x(final int n) {
        return this.anchor.x + (int)(n * 32.0 / this.zoom_factor);
    }
    
    public int screen_to_wc_y(final int n) {
        return this.anchor.y + (int)(n * 32.0 / this.zoom_factor);
    }
    
    public Point screen_to_wc(final Point point, final Point point2) {
        point2.x = this.anchor.x + (int)(point.x * 32.0 / this.zoom_factor);
        point2.y = this.anchor.y + (int)(point.y * 32.0 / this.zoom_factor);
        return point2;
    }
    
    public int wc_to_screen(final int n) {
        return (int)(n * this.zoom_factor / 32.0);
    }
    
    public double wc_to_screen(final double n) {
        return n * this.zoom_factor / 32.0;
    }
    
    public int wc_to_screen_x(final int n) {
        return (int)((n - this.anchor.x) * this.zoom_factor / 32.0);
    }
    
    public int wc_to_screen_y(final int n) {
        return (int)((n - this.anchor.y) * this.zoom_factor / 32.0);
    }
    
    public Point wc_to_screen(final Point point, final Point point2) {
        if (point == null) {
            this.message("-F- FigTrafo2D.wc_to_screen: wp is null!");
            return new Point(0, 0);
        }
        if (point2 == null) {
            this.message("-F- FigTrafo2D.wc_to_screen: sp is null!");
            return new Point(0, 0);
        }
        point2.x = (int)((point.x - this.anchor.x) * this.zoom_factor / 32.0);
        point2.y = (int)((point.y - this.anchor.y) * this.zoom_factor / 32.0);
        return point2;
    }
    
    public Point wc_to_screen(final double n, final double n2, final Point point) {
        point.x = (int)((n - this.anchor.x) * this.zoom_factor / 32.0);
        point.y = (int)((n2 - this.anchor.y) * this.zoom_factor / 32.0);
        return point;
    }
    
    public Point screen_to_wc_snapped(final Point point, final Point point2) {
        return this.screen_to_wc_snapped(point.x, point.y, point2);
    }
    
    public Point screen_to_wc_snapped(final int n, final int n2, final Point point) {
        final double n3 = this.anchor.x + n * 32.0 / this.zoom_factor + 0.5 * this.snapDelta;
        final double n4 = this.anchor.y + n2 * 32.0 / this.zoom_factor + 0.5 * this.snapDelta;
        point.x = (int)(Math.floor(n3 / this.snapDelta) * this.snapDelta);
        point.y = (int)(Math.floor(n4 / this.snapDelta) * this.snapDelta);
        return point;
    }
    
    public Point getWorldCoords(final int n, final int n2) {
        final Point point = new Point(0, 0);
        point.x = this.anchor.x + (int)(n * 32.0 / this.zoom_factor);
        point.y = this.anchor.y + (int)(n2 * 32.0 / this.zoom_factor);
        return point;
    }
    
    public Point getWorldCoords(final Point point) {
        return this.getWorldCoords(point.x, point.y);
    }
    
    public Point getWorldCoordsSnapped(final int n, final int n2) {
        return this.screen_to_wc_snapped(n, n2, new Point(0, 0));
    }
    
    public Point getWorldCoordsSnapped(final Point point) {
        return this.getWorldCoordsSnapped(point.x, point.y);
    }
    
    public Point getScreenCoords(final Point point) {
        return this.wc_to_screen(point.x, point.y, new Point(0, 0));
    }
    
    public void setGridMode(final int gridMode) {
        this.gridMode = gridMode;
    }
    
    public int getGridMode() {
        return this.gridMode;
    }
    
    public long getTimestamp() {
        return this.timestamp;
    }
    
    public void doChangeUnits() {
        ++this.units;
        if (this.units > 3) {
            this.units = 1;
        }
        this.setSnapRelative(this.snapMode);
    }
    
    public void setUnits(final int n) {
        if (n == 1) {
            this.units = 1;
        }
        else if (n == 2) {
            this.units = 2;
        }
        else {
            if (n != 3) {
                throw new IllegalArgumentException("setUnits: unknown units value: " + n);
            }
            this.units = 3;
        }
        this.setSnapRelative(this.snapMode);
    }
    
    public FigTrafo2D getClone() {
        try {
            final FigTrafo2D figTrafo2D = (FigTrafo2D)this.clone();
            figTrafo2D.anchor = new Point(this.anchor.x, this.anchor.y);
            return figTrafo2D;
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
    }
    
    public void message(final String s) {
        ExceptionTracer.message(s);
    }
    
    public FigTrafo2D() {
        this.units = 2;
        this.snapMode = 3;
        this.gridMode = 960;
        this.zoom_factor = 1.0;
        this.zoom_factor75 = (int)(75.0 * this.zoom_factor);
        this.d_zoom_factor = this.zoom_factor * 75.0 / 2400.0;
        this.snapDelta = 48.0;
        this.gridMode = 960;
        this.anchor = new Point(0, 0);
        this.p = new Point(0, 0);
        this.units = 2;
        this.timestamp = System.currentTimeMillis();
    }
    
    static {
        FigTrafo2D.UNITS_STRING = new String[] { "??", "cm", "in", "xmm" };
        offsets = new double[] { -15.5, -10.814, -7.5, -5.157, -3.5, -2.3284, -1.5, -0.9142, -0.5, -0.2071, 0.0, 0.14644, 0.25, 0.32322, 0.375, 0.4116, 0.4375, 0.4558, 0.46875, 0.4779, 0.484375 };
        factors = new double[] { 32.0, 22.627, 16.0, 11.3137, 8.0, 5.6569, 4.0, 2.82843, 2.0, 1.41421, 1.0, 0.70711, 0.5, 0.35355, 0.25, 0.1767, 0.125, 0.08839, 0.0625, 0.04419, 0.03125 };
    }
}
