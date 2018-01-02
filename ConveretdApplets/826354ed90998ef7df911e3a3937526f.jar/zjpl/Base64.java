// 
// Decompiled by Procyon v0.5.30
// 

package zjpl;

public class Base64
{
    static byte[] encodeData;
    static String charSet;
    
    public static String encode(final String s) {
        return encode(s.getBytes());
    }
    
    public static String encode(final byte[] src) {
        return encode(src, 0, src.length);
    }
    
    public static String encode(final byte[] src, final int start, final int length) {
        final byte[] dst = new byte[(length + 2) / 3 * 4 + length / 72];
        int x = 0;
        int dstIndex = 0;
        int state = 0;
        int old = 0;
        int len = 0;
        for (int max = length + start, srcIndex = start; srcIndex < max; ++srcIndex) {
            x = src[srcIndex];
            switch (++state) {
                case 1: {
                    dst[dstIndex++] = Base64.encodeData[x >> 2 & 0x3F];
                    break;
                }
                case 2: {
                    dst[dstIndex++] = Base64.encodeData[(old << 4 & 0x30) | (x >> 4 & 0xF)];
                    break;
                }
                case 3: {
                    dst[dstIndex++] = Base64.encodeData[(old << 2 & 0x3C) | (x >> 6 & 0x3)];
                    dst[dstIndex++] = Base64.encodeData[x & 0x3F];
                    state = 0;
                    break;
                }
            }
            old = x;
            if (++len >= 72) {
                dst[dstIndex++] = 10;
                len = 0;
            }
        }
        switch (state) {
            case 1: {
                dst[dstIndex++] = Base64.encodeData[old << 4 & 0x30];
                dst[dstIndex++] = 61;
                dst[dstIndex++] = 61;
                break;
            }
            case 2: {
                dst[dstIndex++] = Base64.encodeData[old << 2 & 0x3C];
                dst[dstIndex++] = 61;
                break;
            }
        }
        return new String(dst);
    }
    
    public static byte[] decode(final String s) {
        int end = 0;
        if (s.endsWith("=")) {
            ++end;
        }
        if (s.endsWith("==")) {
            ++end;
        }
        final int len = (s.length() + 3) / 4 * 3 - end;
        final byte[] result = new byte[len];
        int dst = 0;
        try {
            for (int src = 0; src < s.length(); ++src) {
                final int code = Base64.charSet.indexOf(s.charAt(src));
                if (code == -1) {
                    break;
                }
                switch (src % 4) {
                    case 0: {
                        result[dst] = (byte)(code << 2);
                        break;
                    }
                    case 1: {
                        final byte[] array = result;
                        final int n = dst++;
                        array[n] |= (byte)(code >> 4 & 0x3);
                        result[dst] = (byte)(code << 4);
                        break;
                    }
                    case 2: {
                        final byte[] array2 = result;
                        final int n2 = dst++;
                        array2[n2] |= (byte)(code >> 2 & 0xF);
                        result[dst] = (byte)(code << 6);
                        break;
                    }
                    case 3: {
                        final byte[] array3 = result;
                        final int n3 = dst++;
                        array3[n3] |= (byte)(code & 0x3F);
                        break;
                    }
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {}
        return result;
    }
    
    public static void main(final String[] args) {
        System.out.println("encode: " + args[0] + " -> (" + encode(args[0]) + ")");
        System.out.println("decode: " + args[0] + " -> (" + new String(decode(args[0])) + ")");
    }
    
    static {
        Base64.charSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        Base64.encodeData = new byte[64];
        for (int i = 0; i < 64; ++i) {
            final byte c = (byte)Base64.charSet.charAt(i);
            Base64.encodeData[i] = c;
        }
    }
}
