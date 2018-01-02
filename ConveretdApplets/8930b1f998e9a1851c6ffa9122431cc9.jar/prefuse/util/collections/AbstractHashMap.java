// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

public abstract class AbstractHashMap
{
    protected int distinct;
    protected int lowWaterMark;
    protected int highWaterMark;
    protected double minLoadFactor;
    protected double maxLoadFactor;
    protected static final int defaultCapacity = 277;
    protected static final double defaultMinLoadFactor = 0.2;
    protected static final double defaultMaxLoadFactor = 0.5;
    
    protected int chooseGrowCapacity(final int n, final double n2, final double n3) {
        return this.nextPrime(Math.max(n + 1, (int)(4 * n / (3.0 * n2 + n3))));
    }
    
    protected int chooseHighWaterMark(final int n, final double n2) {
        return Math.min(n - 2, (int)(n * n2));
    }
    
    protected int chooseLowWaterMark(final int n, final double n2) {
        return (int)(n * n2);
    }
    
    protected int chooseMeanCapacity(final int n, final double n2, final double n3) {
        return this.nextPrime(Math.max(n + 1, (int)(2 * n / (n2 + n3))));
    }
    
    protected int chooseShrinkCapacity(final int n, final double n2, final double n3) {
        return this.nextPrime(Math.max(n + 1, (int)(4 * n / (n2 + 3.0 * n3))));
    }
    
    public abstract void clear();
    
    public void ensureCapacity(final int n) {
    }
    
    public boolean isEmpty() {
        return this.distinct == 0;
    }
    
    protected int nextPrime(final int n) {
        return PrimeFinder.nextPrime(n);
    }
    
    protected void setUp(final int n, final double n2, final double n3) {
        if (n < 0) {
            throw new IllegalArgumentException("Initial Capacity must not be less than zero: " + n);
        }
        if (n2 < 0.0 || n2 >= 1.0) {
            throw new IllegalArgumentException("Illegal minLoadFactor: " + n2);
        }
        if (n3 <= 0.0 || n3 >= 1.0) {
            throw new IllegalArgumentException("Illegal maxLoadFactor: " + n3);
        }
        if (n2 >= n3) {
            throw new IllegalArgumentException("Illegal minLoadFactor: " + n2 + " and maxLoadFactor: " + n3);
        }
    }
    
    public int size() {
        return this.distinct;
    }
    
    public void trimToSize() {
    }
}
