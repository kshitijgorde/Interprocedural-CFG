// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.buffer;

final class IoBufferHexDumper
{
    private static final byte[] highDigits;
    private static final byte[] lowDigits;
    
    public static String getHexdump(final IoBuffer ioBuffer, int i) {
        if (i == 0) {
            throw new IllegalArgumentException("lengthLimit: " + i + " (expected: 1+)");
        }
        final boolean b;
        if (b = (ioBuffer.remaining() > i)) {
            i = i;
        }
        else {
            i = ioBuffer.remaining();
        }
        if (i == 0) {
            return "empty";
        }
        final StringBuilder sb = new StringBuilder(i * 3 + 3);
        final int position = ioBuffer.position();
        final int n = ioBuffer.get() & 0xFF;
        sb.append((char)IoBufferHexDumper.highDigits[n]);
        sb.append((char)IoBufferHexDumper.lowDigits[n]);
        --i;
        while (i > 0) {
            sb.append(' ');
            final int n2 = ioBuffer.get() & 0xFF;
            sb.append((char)IoBufferHexDumper.highDigits[n2]);
            sb.append((char)IoBufferHexDumper.lowDigits[n2]);
            --i;
        }
        ioBuffer.position(position);
        if (b) {
            sb.append("...");
        }
        return sb.toString();
    }
    
    static {
        final byte[] array = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
        final byte[] highDigits2 = new byte[256];
        final byte[] lowDigits2 = new byte[256];
        for (int i = 0; i < 256; ++i) {
            highDigits2[i] = array[i >>> 4];
            lowDigits2[i] = array[i & 0xF];
        }
        highDigits = highDigits2;
        lowDigits = lowDigits2;
    }
}
