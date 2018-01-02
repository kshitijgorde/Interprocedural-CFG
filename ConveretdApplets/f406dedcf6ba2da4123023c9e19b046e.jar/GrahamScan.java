import java.util.Iterator;
import java.util.Comparator;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Collection;

// 
// Decompiled by Procyon v0.5.30
// 

public class GrahamScan extends HullAlgorithm
{
    public GrahamScan(final Point3dObject3d[] array) {
        super(array);
    }
    
    public List getHullPoints(final Collection collection) {
        if (collection == null || collection.size() <= 0) {
            return Collections.EMPTY_LIST;
        }
        int n = 0;
        final Iterator<Point3d> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof Point3d) {
                ++n;
            }
        }
        if (n <= 0) {
            return Collections.EMPTY_LIST;
        }
        final Point3d[] array = new Point3d[n];
        int n2 = 0;
        for (final Point3d next : collection) {
            if (next instanceof Point3d) {
                array[n2++] = next;
            }
        }
        final ArrayList list = new ArrayList<Point3d>();
        if (n < 3) {
            list.add(array[0]);
            if (n == 2) {
                list.add(array[1]);
            }
        }
        else {
            int n3 = 0;
            Point3d point3d = array[0];
            for (int i = 1; i < n; ++i) {
                final Point3d point3d2 = array[i];
                if (point3d2.y() < point3d.y() || (point3d2.x() == point3d.x() && point3d2.x() < point3d.x())) {
                    n3 = i;
                    point3d = point3d2;
                }
            }
            if (n3 != 0) {
                array[n3] = array[0];
                array[0] = point3d;
            }
            Arrays.sort(array, 1, n, new PolarComparator(point3d));
            int n4;
            for (n4 = 1; n4 + 1 < n && HullAlgorithm.colinear(point3d, array[n4], array[n4 + 1]); ++n4) {}
            list.add(point3d);
            list.add(array[n4]);
            for (int j = n4 + 1; j < n; ++j) {
                final Point3d point3d3 = array[j];
                for (Point3d point3d4 = list.get(list.size() - 1), point3d5 = list.get(list.size() - 2); 0 != HullAlgorithm.classify(point3d5, point3d4, point3d3); point3d5 = list.get(list.size() - 2)) {
                    point3d4 = point3d5;
                    list.remove(list.size() - 1);
                }
                list.add(point3d3);
            }
        }
        return list;
    }
    
    public Object3dList build2D() {
        final int length = super.pts.length;
        final ArrayList<Point3dObject3d> list = new ArrayList<Point3dObject3d>();
        for (int i = 0; i < length; ++i) {
            list.add(super.pts[i]);
        }
        final List hullPoints = this.getHullPoints(list);
        final Object3dList list2 = new Object3dList(20);
        int size = hullPoints.size();
        Point3d point3d = null;
        Point3d point3d2 = null;
        int j;
        for (j = 0; j < size; ++j) {
            final Point3d point3d3 = hullPoints.get(j);
            final Point3d point3d4 = new Point3d(point3d3.x(), point3d3.y(), 0.0);
            if (j == 0) {
                list2.addElement(new Point3dObject3d(point3d4, j));
                point3d2 = point3d4;
            }
            else {
                list2.addElement(new Edge3d(point3d, point3d4, j));
            }
            point3d = point3d4;
        }
        list2.addElement(new Edge3d(point3d, point3d2, j++));
        list2.lastFrame = ++size;
        return list2;
    }
    
    private static class PolarComparator implements Comparator
    {
        private Point3d p0;
        
        public PolarComparator(final Point3d p) {
            this.p0 = p;
        }
        
        public int compare(final Object o, final Object o2) {
            final Point3d point3d = (Point3d)o;
            final Point3d point3d2 = (Point3d)o2;
            if (point3d.equals(this.p0)) {
                return point3d2.equals(this.p0) ? 0 : -1;
            }
            if (point3d2.equals(this.p0)) {
                return 1;
            }
            final double n = (point3d2.x() - this.p0.x()) * (point3d.y() - this.p0.y()) - (point3d.x() - this.p0.x()) * (point3d2.y() - this.p0.y());
            return (n < 0.0) ? -1 : (n > 0.0);
        }
    }
}
