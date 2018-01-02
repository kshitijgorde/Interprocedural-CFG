// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

public abstract class IntIterator extends AbstractLiteralIterator
{
    public Object next() {
        return new Integer(this.nextInt());
    }
    
    public boolean isDoubleSupported() {
        return true;
    }
    
    public boolean isFloatSupported() {
        return true;
    }
    
    public boolean isIntSupported() {
        return true;
    }
    
    public boolean isLongSupported() {
        return true;
    }
    
    public double nextDouble() {
        return this.nextInt();
    }
    
    public float nextFloat() {
        return this.nextInt();
    }
    
    public long nextLong() {
        return this.nextInt();
    }
    
    public abstract int nextInt();
}
