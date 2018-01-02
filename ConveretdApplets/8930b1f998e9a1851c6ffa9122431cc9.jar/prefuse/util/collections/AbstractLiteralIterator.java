// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

public abstract class AbstractLiteralIterator implements LiteralIterator
{
    public int nextInt() {
        throw new UnsupportedOperationException("int type unsupported");
    }
    
    public long nextLong() {
        throw new UnsupportedOperationException("long type unsupported");
    }
    
    public float nextFloat() {
        throw new UnsupportedOperationException("float type unsupported");
    }
    
    public double nextDouble() {
        throw new UnsupportedOperationException("double type unsupported");
    }
    
    public boolean nextBoolean() {
        throw new UnsupportedOperationException("boolean type unsupported");
    }
    
    public boolean isBooleanSupported() {
        return false;
    }
    
    public boolean isDoubleSupported() {
        return false;
    }
    
    public boolean isFloatSupported() {
        return false;
    }
    
    public boolean isIntSupported() {
        return false;
    }
    
    public boolean isLongSupported() {
        return false;
    }
}
