// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.io.IOException;
import java.math.BigInteger;
import java.io.InputStream;
import java.io.DataInputStream;

public class SSHDataInputStream extends DataInputStream
{
    SSHDataInputStream(final InputStream in) {
        super(in);
    }
    
    public BigInteger readBigInteger() throws IOException {
        final short bits = this.readShort();
        final byte[] raw = new byte[(bits + 7) / 8 + 1];
        raw[0] = 0;
        this.read(raw, 1, raw.length - 1);
        return new BigInteger(raw);
    }
    
    public String readString() throws IOException {
        final int len = this.readInt();
        final byte[] raw = new byte[len];
        this.read(raw, 0, raw.length);
        return new String(raw);
    }
    
    public byte[] readStringAsBytes() throws IOException {
        final int len = this.readInt();
        final byte[] raw = new byte[len];
        this.read(raw, 0, raw.length);
        return raw;
    }
}
