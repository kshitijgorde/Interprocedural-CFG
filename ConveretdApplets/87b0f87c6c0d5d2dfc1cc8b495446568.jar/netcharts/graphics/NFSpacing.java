// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.awt.Point;
import netcharts.util.NFUtil;
import java.util.Vector;

public final class NFSpacing
{
    public static final int DOUBLE = 1;
    public static final int LINEPOINT = 2;
    public static final int OBJECT = 3;
    private int a;
    private Vector b;
    private static final boolean c = false;
    
    public NFSpacing(final double n, final double n2, final int n3) {
        this.a = 1;
        this.a = 1;
        this.b = new Vector();
        if (n3 < 1) {
            this.b.addElement(new Double(n));
            this.b.addElement(new Double(n2));
            return;
        }
        final double n4 = Math.abs(n2 - n) / n3;
        for (int i = 0; i < n3; ++i) {
            double n5;
            if (n2 > n) {
                n5 = n4 * i + n;
            }
            else {
                n5 = n - n4 * i;
            }
            this.b.addElement(new Double(n5));
        }
        this.b.addElement(new Double(n2));
    }
    
    public NFSpacing(final double n, final double n2, double n3) {
        this.a = 1;
        this.a = 1;
        this.b = new Vector();
        if ((n < n2 && n3 < 0.0) || (n2 < n && n3 > 0.0)) {
            n3 = -n3;
        }
        if (n3 == 0.0) {
            this.b.addElement(new Double(n));
            this.b.addElement(new Double(n2));
            return;
        }
        for (int n4 = (int)((n2 - n) / n3), i = 0; i <= n4; ++i) {
            this.b.addElement(new Double(n3 * i + n));
        }
    }
    
    public NFSpacing(final Vector b) {
        this.a = 1;
        this.b = b;
        this.a = 3;
    }
    
    public NFSpacing(final int n, final int n2, final double n3, final double n4, final double n5) {
        this.a = 1;
        final double sqrt = Math.sqrt(Math.pow(n3, 2.0) + Math.pow(n4, 2.0));
        this.a = 2;
        this.b = new Vector();
        final int n6 = (int)(sqrt / n5) + 1;
        final double atan2 = Math.atan2(n4, n3);
        for (int i = 0; i < n6; ++i) {
            final double n7 = n5 * i;
            this.b.addElement(new Point(n + (int)NFUtil.rint(n7 * Math.cos(atan2)), n2 - (int)NFUtil.rint(n7 * Math.sin(atan2))));
        }
    }
    
    public NFSpacing(final int n, final int n2, final double n3, final double n4, final int n5) {
        this.a = 1;
        final double sqrt = Math.sqrt(Math.pow(n3, 2.0) + Math.pow(n4, 2.0));
        this.a = 2;
        this.b = new Vector();
        final double n6 = sqrt / n5;
        final double atan2 = Math.atan2(n4, n3);
        for (int i = 0; i < n5; ++i) {
            final double n7 = n6 * i;
            this.b.addElement(new Point(n + (int)NFUtil.rint(n7 * Math.cos(atan2)), n2 - (int)NFUtil.rint(n7 * Math.sin(atan2))));
        }
        this.b.addElement(new Point(n + (int)NFUtil.rint(sqrt * Math.cos(atan2)), n2 - (int)NFUtil.rint(sqrt * Math.sin(atan2))));
    }
    
    public NFSpacing(final NFSpacing nfSpacing) {
        this.a = 1;
        final int size = nfSpacing.size();
        this.b = new Vector();
        for (int i = 0; i < size; ++i) {
            final Point nthPoint = nfSpacing.getNthPoint(i);
            this.b.addElement(new Point(nthPoint.x, nthPoint.y));
        }
    }
    
    public NFSpacing(int n) {
        this.a = 1;
        this.b = new Vector();
        while (n-- > 0) {
            this.b.addElement(new Point(0, 0));
        }
    }
    
    public double getNthValue(final int n) {
        return this.b.elementAt(n);
    }
    
    public Point getNthPoint(final int n) {
        return this.b.elementAt(n);
    }
    
    public void deleteNthItem(final int n) {
        this.b.removeElementAt(n);
    }
    
    public Object getNthObject(int n) {
        if (n >= this.b.size()) {
            n = this.b.size() - 1;
        }
        return this.b.elementAt(n);
    }
    
    public int getDataType() {
        return this.a;
    }
    
    public void setNthPoint(final int n, final Point point) {
        this.b.setElementAt(point, n);
    }
    
    public int size() {
        return this.b.size();
    }
    
    public static final NFSpacing adjustPointSpacing(final NFSpacing nfSpacing, final int n, final int n2) {
        final NFSpacing nfSpacing2 = new NFSpacing(nfSpacing);
        for (int i = 0; i < nfSpacing2.size(); ++i) {
            final Point nthPoint;
            final Point point = nthPoint = nfSpacing2.getNthPoint(i);
            nthPoint.x += n;
            final Point point2 = point;
            point2.y += n2;
            nfSpacing2.setNthPoint(i, point);
        }
        return nfSpacing2;
    }
}
