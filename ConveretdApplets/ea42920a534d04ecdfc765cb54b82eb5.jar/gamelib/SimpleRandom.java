// 
// Decompiled by Procyon v0.5.30
// 

package gamelib;

public class SimpleRandom
{
    private long seed;
    
    public SimpleRandom() {
        this.setSeed();
    }
    
    public SimpleRandom(final long seed) {
        this.setSeed(seed);
    }
    
    private final void next() {
        this.seed = (this.seed * 25214903917L + 11L & 0xFFFFFFFFFFFFL);
    }
    
    public final float nextFloat() {
        this.next();
        return (this.seed & 0xFFFFFFL) / 1.6777216E7f;
    }
    
    public final float nextFloat(final float n) {
        return this.nextFloat() * n;
    }
    
    public final int nextInt() {
        this.next();
        return (int)this.seed;
    }
    
    public final int nextInt(final int n) {
        return this.nextInt() % (n + 1);
    }
    
    public final int nextUnsigned() {
        this.next();
        return (int)this.seed & Integer.MAX_VALUE;
    }
    
    public final int nextUnsigned(final int n) {
        return this.nextUnsigned() % (n + 1);
    }
    
    public void setSeed() {
        this.setSeed(System.currentTimeMillis());
    }
    
    public void setSeed(final long seed) {
        this.seed = seed;
    }
}
