import java.io.IOException;
import java.math.BigInteger;
import java.io.OutputStream;
import java.io.DataOutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class bu extends DataOutputStream
{
    public bu(final OutputStream outputStream) {
        super(outputStream);
    }
    
    public final void ju(final BigInteger bigInteger) throws IOException {
        final short n = (short)((bigInteger.bitLength() + 7) / 8);
        final byte[] byteArray = bigInteger.toByteArray();
        this.writeShort(bigInteger.bitLength());
        if (byteArray[0] == 0) {
            this.write(byteArray, 1, n);
        }
        else {
            this.write(byteArray, 0, n);
        }
    }
    
    public final void jt(final String s) throws IOException {
        final byte[] bytes = s.getBytes();
        this.writeInt(bytes.length);
        this.write(bytes);
    }
}
