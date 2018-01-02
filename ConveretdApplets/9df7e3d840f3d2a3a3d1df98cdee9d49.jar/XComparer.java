// 
// Decompiled by Procyon v0.5.30
// 

class XComparer extends Comparer
{
    public void XComparer() {
    }
    
    public int compare(final Object o, final Object o2) {
        final DoublePoint doublePoint = (DoublePoint)o;
        final DoublePoint doublePoint2 = (DoublePoint)o2;
        if (doublePoint.x > doublePoint2.x) {
            return 1;
        }
        if (doublePoint.x == doublePoint2.x) {
            return 0;
        }
        return -1;
    }
}
