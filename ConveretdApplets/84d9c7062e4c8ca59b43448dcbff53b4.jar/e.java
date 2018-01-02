import java.util.zip.DataFormatException;
import java.io.IOException;
import java.util.zip.Inflater;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class e
{
    public static final byte[] a(final byte[] input) throws IOException {
        try {
            final Vector<byte[]> vector = new Vector<byte[]>();
            final Inflater inflater = new Inflater();
            inflater.setInput(input);
            while (inflater.getRemaining() > 0) {
                final byte[] array = new byte[8192];
                inflater.inflate(array, 0, array.length);
                vector.addElement(array);
            }
            final byte[] array2 = new byte[inflater.getTotalOut()];
            int n = 0;
            for (int i = 0; i < vector.size(); ++i) {
                final byte[] array3 = vector.elementAt(i);
                final int min = Math.min(array3.length, array2.length - n);
                System.arraycopy(array3, 0, array2, n, min);
                n += min;
            }
            return array2;
        }
        catch (DataFormatException ex) {
            throw new IOException(ex.toString());
        }
    }
}
