// 
// Decompiled by Procyon v0.5.30
// 

package jfig.utils;

import java.awt.Point;

public class GeometryManager
{
    public static final int UNCONSTRAINED = 0;
    public static final int LATEX_LINE = 1;
    public static final int LATEX_VECTOR = 2;
    public static final int MANHATTAN = 3;
    public static final int MOUNTAIN = 4;
    public static final int MOUNTHATTAN = 5;
    public static final String[] names;
    private static final double[] latex_line_angles;
    private static final double[] latex_vector_angles;
    private static final double[] manhattan_angles;
    private static final double[] mountain_angles;
    private static final double[] mounthattan_angles;
    private int mode;
    
    public void setMode(final int mode) throws IllegalArgumentException {
        if (mode < 0 || mode > 5) {
            throw new IllegalArgumentException("Unknown geometry mode: " + mode);
        }
        this.mode = mode;
    }
    
    public int getMode() {
        return this.mode;
    }
    
    public Point getNearestAllowedPoint(final Point point, final Point point2) {
        switch (this.mode) {
            case 0: {
                return point2;
            }
            case 1: {
                return this._get(point, point2, GeometryManager.latex_line_angles);
            }
            case 2: {
                return this._get(point, point2, GeometryManager.latex_vector_angles);
            }
            case 3: {
                return this.getNearestManhattanPoint(point, point2);
            }
            case 4: {
                return this._get(point, point2, GeometryManager.mountain_angles);
            }
            case 5: {
                return this._get(point, point2, GeometryManager.mounthattan_angles);
            }
            default: {
                System.out.println("-E- GeometryManager: unsupported mode: " + this.mode);
                return point2;
            }
        }
    }
    
    public Point getNearestManhattanPoint(final Point point, final Point point2) {
        if (Math.abs((double)(point2.x - point.x)) > Math.abs((double)(point2.y - point.y))) {
            return new Point(point2.x, point.y);
        }
        return new Point(point.x, point2.y);
    }
    
    private Point _get(final Point point, final Point point2, final double[] array) {
        final double n = point2.x - point.x;
        final double n2 = point2.y - point.y;
        double n3;
        if (Math.abs(n) > Math.abs(n2)) {
            n3 = Math.abs(n2 / n);
        }
        else {
            n3 = Math.abs(n / n2);
        }
        double n4 = 42.0;
        int n5 = 0;
        for (int i = 0; i < array.length; ++i) {
            final double abs = Math.abs(n3 - array[i]);
            if (abs < n4) {
                n4 = abs;
                n5 = i;
            }
        }
        if (Math.abs(n) > Math.abs(n2)) {
            double n6 = Math.abs(n) * array[n5] + 0.5;
            if (n2 < 0.0) {
                n6 = -n6;
            }
            return new Point(point2.x, point.y + (int)n6);
        }
        double n7 = Math.abs(n2) * array[n5] + 0.5;
        if (n < 0.0) {
            n7 = -n7;
        }
        return new Point(point.x + (int)n7, point2.y);
    }
    
    public void convertToRadians(final double[] array) {
        if (array == null) {
            return;
        }
        final double n = 0.017453292519943295;
        for (int i = 0; i < array.length; ++i) {
            array[i] *= n;
        }
    }
    
    public void convertToDegrees(final double[] array) {
        if (array == null) {
            return;
        }
        final double n = 57.29577951308232;
        for (int i = 0; i < array.length; ++i) {
            array[i] *= n;
        }
    }
    
    public GeometryManager() {
        this.mode = 0;
    }
    
    static {
        names = new String[] { "Unconstrained", "Latex Line", "Latex Vector", "Manhattan", "Mountain", "Mount-hattan" };
        latex_line_angles = new double[] { 0.0, 0.16666666666666666, 0.2, 0.25, 0.3333333333333333, 0.4, 0.5, 0.6, 0.6666666666666666, 0.75, 0.8, 0.8333333333333334, 1.0 };
        latex_vector_angles = new double[] { 0.0, 0.25, 0.3333333333333333, 0.5, 0.6666666666666666, 0.75, 1.0 };
        manhattan_angles = new double[] { 0.0 };
        mountain_angles = new double[] { 1.0 };
        mounthattan_angles = new double[] { 0.0, 1.0 };
    }
}
