// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.OutputStream;
import java.io.InputStream;

public final class ae extends g
{
    public ae(final InputStream inputStream) {
        this.a(inputStream);
    }
    
    protected final void a() {
    }
    
    public final void a(final OutputStream outputStream) {
        outputStream.write(ae.c);
        outputStream.write(VT_6_1_0_11.g.a(this.i));
    }
    
    public final int a(final byte[] array, final int n) {
        System.arraycopy(ae.c, 0, array, n, 4);
        System.arraycopy(VT_6_1_0_11.g.a(this.i), 0, array, n + 4, 4);
        return 8;
    }
    
    public final String toString() {
        final StringBuffer sb;
        (sb = new StringBuffer("    CUE POINTS CHUNK\n")).append("  HEADER\n");
        sb.append("Chunk identifier(4): ").append("cue ").append("\n");
        sb.append("Chunk size (4):      ").append(this.i).append("\n");
        sb.append("  DATA\n");
        return sb.toString();
    }
}
