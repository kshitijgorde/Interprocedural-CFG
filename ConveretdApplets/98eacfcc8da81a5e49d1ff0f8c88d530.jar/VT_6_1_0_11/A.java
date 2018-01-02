// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.OutputStream;

public final class A extends g
{
    public A(final int i) {
        this.h = null;
        this.i = i;
    }
    
    protected final void a() {
        this.i = this.h.length;
    }
    
    public final void a(final OutputStream outputStream) {
        outputStream.write(A.g);
        outputStream.write(VT_6_1_0_11.g.a(this.i));
    }
    
    public final int a(final byte[] array, final int n) {
        System.arraycopy(A.g, 0, array, n, 4);
        System.arraycopy(VT_6_1_0_11.g.a(this.i), 0, array, n + 4, 4);
        return 8;
    }
    
    public final int b() {
        return this.i;
    }
    
    public final String toString() {
        final StringBuffer sb;
        (sb = new StringBuffer("    DATA CHUNK\n")).append("  HEADER\n");
        sb.append("Chunk identifier (4): ").append("data").append("\n");
        sb.append("Chunk size       (4): ").append(this.i).append("\n");
        return sb.toString();
    }
}
