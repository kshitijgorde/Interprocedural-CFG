// 
// Decompiled by Procyon v0.5.30
// 

package lecture;

import java.io.IOException;
import java.io.OutputStream;

public class c extends a
{
    private static final char[] if;
    
    static {
        if = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
    }
    
    protected int if() {
        return 3;
    }
    
    protected int a() {
        return 57;
    }
    
    protected void a(final OutputStream outputStream, final byte[] array, final int n, final int n2) throws IOException {
        if (n2 == 1) {
            final byte b = array[n];
            final int n3 = 0;
            outputStream.write(c.if[b >>> 2 & 0x3F]);
            outputStream.write(c.if[(b << 4 & 0x30) + (n3 >>> 4 & 0xF)]);
            outputStream.write(61);
            outputStream.write(61);
            return;
        }
        if (n2 == 2) {
            final byte b2 = array[n];
            final byte b3 = array[n + 1];
            final int n4 = 0;
            outputStream.write(c.if[b2 >>> 2 & 0x3F]);
            outputStream.write(c.if[(b2 << 4 & 0x30) + (b3 >>> 4 & 0xF)]);
            outputStream.write(c.if[(b3 << 2 & 0x3C) + (n4 >>> 6 & 0x3)]);
            outputStream.write(61);
            return;
        }
        final byte b4 = array[n];
        final byte b5 = array[n + 1];
        final byte b6 = array[n + 2];
        outputStream.write(c.if[b4 >>> 2 & 0x3F]);
        outputStream.write(c.if[(b4 << 4 & 0x30) + (b5 >>> 4 & 0xF)]);
        outputStream.write(c.if[(b5 << 2 & 0x3C) + (b6 >>> 6 & 0x3)]);
        outputStream.write(c.if[b6 & 0x3F]);
    }
}
