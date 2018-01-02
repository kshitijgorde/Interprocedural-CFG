// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

public class DefaultLiteralComparator implements LiteralComparator
{
    private static DefaultLiteralComparator s_instance;
    
    public static DefaultLiteralComparator getInstance() {
        if (DefaultLiteralComparator.s_instance == null) {
            DefaultLiteralComparator.s_instance = new DefaultLiteralComparator();
        }
        return DefaultLiteralComparator.s_instance;
    }
    
    public int compare(final byte b, final byte b2) {
        return (b < b2) ? -1 : ((b > b2) ? 1 : 0);
    }
    
    public int compare(final int n, final int n2) {
        return (n < n2) ? -1 : ((n > n2) ? 1 : 0);
    }
    
    public int compare(final long n, final long n2) {
        return (n < n2) ? -1 : ((n > n2) ? 1 : 0);
    }
    
    public int compare(final float n, final float n2) {
        return Float.compare(n, n2);
    }
    
    public int compare(final double n, final double n2) {
        return Double.compare(n, n2);
    }
    
    public int compare(final boolean b, final boolean b2) {
        return b ? (b2 ? 0 : 1) : (b2 ? -1 : 0);
    }
    
    public int compare(final Object o, final Object o2) {
        if (o == null) {
            return (o2 == null) ? 0 : -1;
        }
        if (o2 == null) {
            return 1;
        }
        if (o instanceof Comparable) {
            return ((Comparable)o).compareTo(o2);
        }
        if (o2 instanceof Comparable) {
            return -1 * ((Comparable)o2).compareTo(o);
        }
        if (o instanceof Boolean && o2 instanceof Boolean) {
            return this.compare((boolean)o, (boolean)o2);
        }
        throw new IllegalArgumentException("Incomparable arguments.");
    }
    
    static {
        DefaultLiteralComparator.s_instance = null;
    }
}
