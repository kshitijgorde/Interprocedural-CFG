import java.awt.Point;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class RangeSet
{
    Vector ranges;
    
    RangeSet() {
        this.ranges = new Vector();
    }
    
    public boolean isEmpty() {
        return this.ranges.isEmpty();
    }
    
    public void set(final int n, final int n2) {
        this.ranges.removeAllElements();
        this.ranges.addElement(new RangeSetElement(n, n2));
    }
    
    public void remove(int n, int n2) {
        if (n2 < n) {
            final int n3 = n2;
            n2 = n;
            n = n3;
        }
        for (int i = 0; i != this.ranges.size(); ++i) {
            final RangeSetElement rangeSetElement = this.ranges.elementAt(i);
            if (rangeSetElement.max >= n) {
                if (rangeSetElement.min > n2) {
                    return;
                }
                if (n <= rangeSetElement.min) {
                    if (rangeSetElement.max > n2) {
                        rangeSetElement.min = n2 + 1;
                        return;
                    }
                    this.ranges.removeElementAt(i--);
                }
                else {
                    if (n > rangeSetElement.max || n2 < rangeSetElement.max) {
                        this.ranges.insertElementAt(new RangeSetElement(rangeSetElement.min, n - 1), i);
                        rangeSetElement.min = n2 + 1;
                        return;
                    }
                    rangeSetElement.max = n - 1;
                }
            }
        }
    }
    
    public boolean intersect(final Point point) {
        final int x = point.x;
        final int y = point.y;
        int i = 0;
        while (i != this.ranges.size()) {
            final RangeSetElement rangeSetElement = this.ranges.elementAt(i);
            if (rangeSetElement.max < x) {
                ++i;
            }
            else {
                if (rangeSetElement.min > y) {
                    return false;
                }
                if (rangeSetElement.min > x) {
                    point.x = rangeSetElement.min;
                }
                if (rangeSetElement.max < y) {
                    point.y = rangeSetElement.max;
                }
                return true;
            }
        }
        return false;
    }
}
