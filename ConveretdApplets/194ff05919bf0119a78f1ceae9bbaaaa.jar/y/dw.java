// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.io.DataInput;
import java.io.DataOutput;

public final class dw implements cw
{
    private long a;
    
    public dw() {
        this(System.currentTimeMillis());
    }
    
    public dw(final long n) {
        this.a(n);
    }
    
    private synchronized void a(final long n) {
        this.a = ((n ^ 0x5DEECE66DL) & 0xFFFFFFFFFFFFL);
    }
    
    private synchronized int a() {
        final long a = this.a * 25214903917L + 11L & 0xFFFFFFFFFFFFL;
        this.a = a;
        return (int)(a >>> 16);
    }
    
    public final int a(final int n) {
        return (this.a() & Integer.MAX_VALUE) % n;
    }
    
    public final void a(final DataOutput dataOutput) {
        dataOutput.writeLong(this.a);
    }
    
    public final void a(final DataInput dataInput) {
        this.a = dataInput.readLong();
    }
}
