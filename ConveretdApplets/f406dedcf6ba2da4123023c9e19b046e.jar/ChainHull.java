import java.util.Comparator;
import java.util.Arrays;
import java.util.Stack;

// 
// Decompiled by Procyon v0.5.30
// 

public class ChainHull extends HullAlgorithm
{
    public ChainHull(final Point3dObject3d[] array) {
        super(array);
    }
    
    public Object3dList build2D() {
        final int length = super.pts.length;
        final Stack stack = new Stack<Point3dObject3d>();
        Arrays.sort(super.pts, 0, length, new PolarComparator());
        final int n = 0;
        double x;
        int n2;
        for (x = super.pts[0].x(), n2 = 1; n2 < length && super.pts[n2].x() == x; ++n2) {}
        final int n3 = n2 - 1;
        final int n4 = length - 1;
        double x2;
        int n5;
        for (x2 = super.pts[length - 1].x(), n5 = length - 2; n5 >= 0 && super.pts[n5].x() == x2; --n5) {}
        final int n6 = n5 + 1;
        stack.push(super.pts[n]);
        int n7 = n3;
        while (++n7 <= n6) {
            if ((0 == HullAlgorithm.classify(super.pts[n], super.pts[n6], super.pts[n7]) || 4 == HullAlgorithm.classify(super.pts[n], super.pts[n6], super.pts[n7])) && n7 < n6) {
                continue;
            }
            while (stack.size() >= 2 && 0 != HullAlgorithm.classify(stack.get(stack.size() - 2), stack.get(stack.size() - 1), super.pts[n7])) {
                stack.pop();
            }
            stack.push(super.pts[n7]);
        }
        if (n4 != n6) {
            stack.push(super.pts[n4]);
        }
        final int size = stack.size();
        int n8 = n6;
        while (--n8 >= n3) {
            if ((0 == HullAlgorithm.classify(super.pts[n4], super.pts[n3], super.pts[n8]) || 4 == HullAlgorithm.classify(super.pts[n4], super.pts[n3], super.pts[n8])) && n8 > n3) {
                continue;
            }
            while (stack.size() > size && 0 != HullAlgorithm.classify(stack.get(stack.size() - 2), stack.get(stack.size() - 1), super.pts[n8])) {
                stack.pop();
            }
            stack.push(super.pts[n8]);
        }
        if (n3 != n) {
            stack.push(super.pts[n]);
        }
        final Object3dList list = new Object3dList(20);
        for (int i = 1; i < stack.size(); ++i) {
            list.addElement(new Edge3d((Point3d)stack.get(i - 1), (Point3d)stack.get(i), i));
        }
        list.lastFrame = stack.size();
        return list;
    }
    
    private static class PolarComparator implements Comparator
    {
        public int compare(final Object o, final Object o2) {
            final Point3d point3d = (Point3d)o;
            final Point3d point3d2 = (Point3d)o2;
            if (point3d.x() == point3d2.x() && point3d.y() == point3d2.y()) {
                return 0;
            }
            if (point3d.x() < point3d2.x() || (point3d.x() == point3d2.x() && point3d.y() < point3d2.y())) {
                return -1;
            }
            return 1;
        }
    }
}
