// 
// Decompiled by Procyon v0.5.30
// 

class m extends f
{
    public static int Za;
    public static int _b;
    int cb;
    protected int bb;
    protected boolean ab;
    
    public m(final int n, final int n2, final int n3, final int n4, final int n5, final int bb) {
        super(n, n2, n3, n4, n5);
        this.bb = bb;
        this.ab = false;
    }
    
    public void b(final int bb) {
        this.bb = bb;
    }
    
    public int a() {
        if (this.ab) {
            return this.bb;
        }
        return Math.max(10, this.bb - m.Za / 40);
    }
    
    public void stop() {
        this.bb = this.a() * l.db;
        this.ab = true;
        this.cb = 50;
    }
}
