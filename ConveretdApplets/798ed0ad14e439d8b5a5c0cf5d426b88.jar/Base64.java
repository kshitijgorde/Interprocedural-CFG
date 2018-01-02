import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

public class Base64
{
    private static final char[] CHAR_TABLE;
    private static final int[] INDEX_TABLE;
    
    public static final String uncompressString(final String s) {
        try {
            final byte[] uncompress = uncompress(s);
            if (uncompress != null) {
                return new String(uncompress, "UTF-8");
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static final byte[] uncompress(final String s) {
        try {
            final GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(Utils.decode(s)), 1024);
            final byte[] array = new byte[1024];
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
            for (int i = gzipInputStream.read(array); i >= 0; i = gzipInputStream.read(array)) {
                byteArrayOutputStream.write(array, 0, i);
            }
            return byteArrayOutputStream.toByteArray();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    static {
        CHAR_TABLE = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
        INDEX_TABLE = new int[] { 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51 };
    }
}
