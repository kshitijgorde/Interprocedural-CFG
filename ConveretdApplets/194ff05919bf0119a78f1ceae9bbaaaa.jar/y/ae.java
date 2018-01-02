// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.io.DataInput;
import java.io.DataOutput;

public final class ae implements cw
{
    private byte[] a;
    
    public ae() {
        this.a = new byte[4];
    }
    
    public final void a(final int n, final int n2) {
        this.a[n] = (byte)n2;
    }
    
    public final void a(final DataOutput dataOutput) {
        dataOutput.writeByte(this.a.length);
        for (int i = 0; i < this.a.length; ++i) {
            dataOutput.writeByte(this.a[i]);
        }
    }
    
    public final void a(final DataInput dataInput) {
        final byte byte1 = dataInput.readByte();
        this.a = new byte[byte1];
        for (byte b = 0; b < byte1; ++b) {
            this.a[b] = dataInput.readByte();
        }
    }
}
