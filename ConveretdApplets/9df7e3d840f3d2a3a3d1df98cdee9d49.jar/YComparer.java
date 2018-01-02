// 
// Decompiled by Procyon v0.5.30
// 

class YComparer extends Comparer
{
    public void YComparer() {
    }
    
    public int compare(final Object o, final Object o2) {
        final DoublePoint doublePoint = (DoublePoint)o;
        final DoublePoint doublePoint2 = (DoublePoint)o2;
        if (doublePoint.y > doublePoint2.y) {
            return 1;
        }
        if (doublePoint.y == doublePoint2.y) {
            return 0;
        }
        return -1;
    }
}
