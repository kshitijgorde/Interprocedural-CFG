// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht;

import java.io.IOException;

public interface IoFileRead
{
    int read(final Pointer p0, final JechtIO.File p1, final int p2, final int p3) throws IOException;
    
    public static class Default implements IoFileRead
    {
        public int read(final Pointer buf, final JechtIO.File file, int max_size, final int skip) throws IOException {
            max_size -= skip;
            int len = file.ptr.read(buf.buffer, buf.start + skip, max_size);
            len += skip;
            buf.buffer[buf.start + len] = 0;
            return len;
        }
    }
}
