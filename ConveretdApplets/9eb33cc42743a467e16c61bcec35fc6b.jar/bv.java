import java.io.IOException;
import java.math.BigInteger;
import java.io.InputStream;
import java.io.DataInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class bv extends DataInputStream
{
    public bv(final InputStream inputStream) {
        super(inputStream);
    }
    
    public final BigInteger jx() throws IOException {
        final byte[] array = new byte[(this.readShort() + 7) / 8 + 1];
        array[0] = 0;
        this.read(array, 1, array.length - 1);
        return new BigInteger(array);
    }
    
    public final String jw() throws IOException {
        final byte[] array = new byte[this.readInt()];
        this.read(array, 0, array.length);
        return new String(array);
    }
    
    public final byte[] jv() throws IOException {
        final byte[] array = new byte[this.readInt()];
        this.read(array, 0, array.length);
        return array;
    }
}
