// 
// Decompiled by Procyon v0.5.30
// 

package soht.client.java.core;

public final class Hex
{
    private static final char[] ALPHABET;
    
    public static String asBlob(final byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        final StringBuffer sb = new StringBuffer(bytes.length * 2);
        int i = bytes.length;
        while (i-- > 0) {
            sb.append(Hex.ALPHABET[(0xF0 & bytes[i]) >> 4]);
            sb.append(Hex.ALPHABET[0xF & bytes[i]]);
        }
        return sb.toString();
    }
    
    public static byte[] fromBlob(final String blob) {
        if (blob == null) {
            return null;
        }
        if ((blob.length() & 0x1) == 0x1) {
            throw new IllegalArgumentException("odd length blob");
        }
        final byte[] x = new byte[blob.length() / 2];
        int i = blob.length() - 2;
        int j = x.length;
        while (j-- > 0) {
            x[j] = (byte)(Integer.parseInt(blob.substring(i, i + 2), Hex.ALPHABET.length) & 0xFF);
            i -= 2;
        }
        return x;
    }
    
    static {
        ALPHABET = "0123456789abcdef".toCharArray();
    }
}
