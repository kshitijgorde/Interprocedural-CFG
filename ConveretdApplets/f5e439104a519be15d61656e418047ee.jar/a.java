// 
// Decompiled by Procyon v0.5.30
// 

public class a
{
    protected static final long for = 500L;
    protected static final long byte = 100L;
    protected static final long char = 100L;
    protected static final int case = 6;
    protected static final int a = 64;
    protected static final int else = 63;
    protected long[] try;
    protected int new;
    protected int if;
    protected int int;
    protected long do;
    protected float goto;
    
    public a() {
        this.new = 0;
        this.if = 0;
        this.int = 0;
        this.try = new long[64];
    }
    
    public float a(final long n) {
        this.if(n);
        return this.if();
    }
    
    public void if(long min) {
        ++this.int;
        min = Math.min(min, 100L);
        this.try[this.if + this.new & 0x3F] = min;
        if (this.new == this.try.length) {
            this.if = (this.if + 1 & 0x3F);
        }
        else {
            ++this.new;
        }
    }
    
    public float if() {
        long n = 0L;
        for (int i = this.new - 1; i >= 0; --i) {
            n += this.try[this.if + i & 0x3F];
            if (n >= 100L) {
                return n / (this.new - i);
            }
        }
        return n / this.new;
    }
    
    public float a() {
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis > this.do + 500L) {
            this.goto = this.int * 1000.0f / (currentTimeMillis - this.do);
            this.do = currentTimeMillis;
            this.int = 0;
        }
        return this.goto;
    }
}
