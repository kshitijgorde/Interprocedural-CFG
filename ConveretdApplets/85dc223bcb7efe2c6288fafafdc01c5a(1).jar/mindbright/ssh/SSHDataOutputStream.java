// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.io.IOException;
import java.math.BigInteger;
import java.io.OutputStream;
import java.io.DataOutputStream;

public class SSHDataOutputStream extends DataOutputStream
{
    SSHDataOutputStream(final OutputStream out) {
        super(out);
    }
    
    public void writeBigInteger(final BigInteger bi) throws IOException {
        final short bytes = (short)((bi.bitLength() + 7) / 8);
        final byte[] raw = bi.toByteArray();
        this.writeShort(bi.bitLength());
        if (raw[0] == 0) {
            this.write(raw, 1, bytes);
        }
        else {
            this.write(raw, 0, bytes);
        }
    }
    
    public void writeString(final String str) throws IOException {
        final byte[] raw = str.getBytes();
        this.writeInt(raw.length);
        this.write(raw);
    }
}
