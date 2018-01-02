// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.b;

public abstract class e
{
    protected int int;
    protected int if;
    protected int for;
    public static final long a = Long.MAX_VALUE;
    public static final float do = 9.223372E18f;
    
    public e() {
        this.int = 0;
        this.if = 0;
        this.for = 0;
    }
    
    public void a(final int if1, final int int1) {
        this.if = if1;
        this.int = int1;
        this.for = this.int - this.if;
    }
    
    public abstract int for();
    
    public abstract void if(final int p0, final Object p1);
    
    public abstract void a(final int p0, final Object p1);
    
    public abstract void a(final Object p0, final int p1, final int p2);
    
    public abstract void a(final Object p0, final int p1);
    
    public abstract void if();
    
    public abstract void do();
    
    public int a() {
        return this.int - this.if + 1;
    }
}
