// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringBufferInputStream;

public class NFBase64
{
    private static final int a = 1024;
    private static byte[] b;
    
    private static final int a(final byte[] array, final int n) {
        return (array[n] & 0xFC) >> 2;
    }
    
    private static final int b(final byte[] array, final int n) {
        return (array[n] & 0x3) << 4 | (array[n + 1] & 0xF0) >>> 4;
    }
    
    private static final int c(final byte[] array, final int n) {
        return (array[n + 1] & 0xF) << 2 | (array[n + 2] & 0xC0) >>> 6;
    }
    
    private static final int d(final byte[] array, final int n) {
        return array[n + 2] & 0x3F;
    }
    
    public static String encode(final String s) throws IOException {
        final StringBufferInputStream stringBufferInputStream = new StringBufferInputStream(s);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        encode(stringBufferInputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toString();
    }
    
    public static byte[] encode(final byte[] array) throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        encode(byteArrayInputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    
    public static void encode(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final byte[] array = new byte[1024];
        int n = 0;
        int n2 = 0;
        int read;
        while ((read = inputStream.read(array, n, 1024 - n)) > 0) {
            if (read + n >= 3) {
                int n3;
                int n4;
                for (n3 = read + n, n4 = 0; n4 + 3 <= n3; n4 += 3) {
                    final int a = a(array, n4);
                    final int b = b(array, n4);
                    final int c = c(array, n4);
                    final int d = d(array, n4);
                    switch (n2) {
                        case 73: {
                            outputStream.write(NFBase64.b[a]);
                            outputStream.write(NFBase64.b[b]);
                            outputStream.write(NFBase64.b[c]);
                            outputStream.write(NFBase64.b[d]);
                            n2 = 1;
                            break;
                        }
                        case 74: {
                            outputStream.write(NFBase64.b[a]);
                            outputStream.write(NFBase64.b[b]);
                            outputStream.write(NFBase64.b[c]);
                            outputStream.write(NFBase64.b[d]);
                            n2 = 2;
                            break;
                        }
                        case 75: {
                            outputStream.write(NFBase64.b[a]);
                            outputStream.write(NFBase64.b[b]);
                            outputStream.write(NFBase64.b[c]);
                            outputStream.write(NFBase64.b[d]);
                            n2 = 3;
                            break;
                        }
                        case 76: {
                            outputStream.write(NFBase64.b[a]);
                            outputStream.write(NFBase64.b[b]);
                            outputStream.write(NFBase64.b[c]);
                            outputStream.write(NFBase64.b[d]);
                            n2 = 4;
                            break;
                        }
                        default: {
                            outputStream.write(NFBase64.b[a]);
                            outputStream.write(NFBase64.b[b]);
                            outputStream.write(NFBase64.b[c]);
                            outputStream.write(NFBase64.b[d]);
                            n2 += 4;
                            break;
                        }
                    }
                }
                for (int i = 0; i < 3; ++i) {
                    array[i] = (byte)((i < n3 - n4) ? array[n4 + i] : 0);
                }
                n = n3 - n4;
            }
            else {
                n += read;
            }
        }
        switch (n) {
            case 1: {
                outputStream.write(NFBase64.b[a(array, 0)]);
                outputStream.write(NFBase64.b[b(array, 0)]);
                outputStream.write(61);
                outputStream.write(61);
                break;
            }
            case 2: {
                outputStream.write(NFBase64.b[a(array, 0)]);
                outputStream.write(NFBase64.b[b(array, 0)]);
                outputStream.write(NFBase64.b[c(array, 0)]);
                outputStream.write(61);
                break;
            }
        }
        outputStream.flush();
    }
    
    private static final int e(final byte[] array, final int n) {
        return (array[n] & 0x3F) << 2 | (array[n + 1] & 0x30) >>> 4;
    }
    
    private static final int f(final byte[] array, final int n) {
        return (array[n + 1] & 0xF) << 4 | (array[n + 2] & 0x3C) >>> 2;
    }
    
    private static final int g(final byte[] array, final int n) {
        return (array[n + 2] & 0x3) << 6 | (array[n + 3] & 0x3F);
    }
    
    private static final int a(final int n) {
        if (n >= 65 && n <= 90) {
            return n - 65;
        }
        if (n >= 97 && n <= 122) {
            return n - 97 + 26;
        }
        if (n >= 48 && n <= 57) {
            return n - 48 + 52;
        }
        switch (n) {
            case 61: {
                return 65;
            }
            case 43: {
                return 62;
            }
            case 47: {
                return 63;
            }
            default: {
                return -1;
            }
        }
    }
    
    public static String decode(final String s) throws IOException {
        final StringBufferInputStream stringBufferInputStream = new StringBufferInputStream(s);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        decode(stringBufferInputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toString();
    }
    
    public static byte[] decode(final byte[] array) throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        decode(byteArrayInputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    
    public static void decode(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final byte[] array = new byte[1024];
        final byte[] array2 = new byte[4];
        int i = 0;
    Label_0161:
        while (true) {
            int read;
            while ((read = inputStream.read(array)) > 0) {
                int j = 0;
                while (j < read) {
                    while (i < 4) {
                        if (j >= read) {
                            continue Label_0161;
                        }
                        final int a = a(array[j++]);
                        if (a < 0) {
                            continue;
                        }
                        array2[i++] = (byte)a;
                    }
                    if (array2[2] == 65) {
                        outputStream.write(e(array2, 0));
                        outputStream.flush();
                        return;
                    }
                    if (array2[3] == 65) {
                        outputStream.write(e(array2, 0));
                        outputStream.write(f(array2, 0));
                        outputStream.flush();
                        return;
                    }
                    outputStream.write(e(array2, 0));
                    outputStream.write(f(array2, 0));
                    outputStream.write(g(array2, 0));
                    i = 0;
                }
            }
            break;
        }
        if (i != 0) {
            throw new IOException("Base64: Invalid length.");
        }
        outputStream.flush();
    }
    
    public static void main(final String[] array) {
        if (array.length != 2) {
            System.out.println("usage: NFBase64 [encode|decode] what");
            System.exit(-1);
        }
        if (array[0].equalsIgnoreCase("encode")) {
            try {
                System.out.println(encode(array[1]));
            }
            catch (IOException ex) {
                System.out.println("NFBase64: Could not encode " + array[1]);
            }
        }
        else if (array[0].equalsIgnoreCase("decode")) {
            try {
                System.out.println(decode(array[1]));
            }
            catch (IOException ex2) {
                System.out.println("NFBase64: Could not decode " + array[1]);
            }
        }
        else {
            System.out.println("usage: NFBase64 [encode|decode] what");
        }
    }
    
    static {
        NFBase64.b = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47, 61 };
    }
}
