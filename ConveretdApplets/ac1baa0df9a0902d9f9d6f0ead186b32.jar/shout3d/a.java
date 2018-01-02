// 
// Decompiled by Procyon v0.5.30
// 

package shout3d;

import java.io.ByteArrayInputStream;

class a extends ByteArrayInputStream
{
    protected float a;
    protected int b;
    protected int c;
    
    public a(final byte[] array) {
        super(array);
        this.b = super.count / 20;
        this.c = super.pos;
    }
    
    public synchronized int read() {
        if (super.pos - this.c > this.b) {
            this.a = this.c / super.count;
            this.c = super.pos;
        }
        return super.read();
    }
    
    public synchronized int read(final byte[] array, final int n, final int n2) {
        if (super.pos - this.c > this.b) {
            this.a = this.c / super.count;
            this.c = super.pos;
        }
        return super.read(array, n, n2);
    }
    
    public float a() {
        return this.a;
    }
}
