// 
// Decompiled by Procyon v0.5.30
// 

class q
{
    protected static final float b = 0.017453292f;
    float c;
    float d;
    float e;
    static int a;
    
    q() {
        this(0.0f, 0.0f, 1.0f);
    }
    
    q(final float c, final float d, final float e) {
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    q(final q q) {
        this(q.c, q.d, q.e);
    }
    
    q a() {
        return new q(this.c * 0.017453292f, this.d * 0.017453292f, this.e);
    }
}
