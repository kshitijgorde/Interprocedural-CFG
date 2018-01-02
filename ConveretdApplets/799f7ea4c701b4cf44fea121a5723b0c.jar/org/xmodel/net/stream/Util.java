// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.net.stream;

import java.nio.ByteBuffer;

public class Util
{
    public static final String dump(final ByteBuffer byteBuffer) {
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("[%d, %d] ", byteBuffer.position(), byteBuffer.limit()));
        for (int n = 0, i = byteBuffer.position(); i < byteBuffer.limit(); ++i, ++n) {
            if (n == 8) {
                sb.append(" ");
            }
            if (n == 16) {
                sb.append("  ");
            }
            if (n == 32) {
                sb.append(String.format("\n[%d, %d] ", i, byteBuffer.limit()));
                n = 0;
            }
            sb.append(String.format("%02X", byteBuffer.get(i)));
            if (i - byteBuffer.position() > 128) {
                sb.append("...");
                break;
            }
        }
        sb.append("\n");
        return sb.toString();
    }
}
