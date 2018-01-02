// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht;

public interface IoStrRead
{
    int read(final Pointer p0, final JechtIO.Str p1, final int p2, final int p3);
    
    public static class Default implements IoStrRead
    {
        public int read(final Pointer buf, final JechtIO.Str str, int max_size, final int skip) {
            final int beg = str.ptr.start;
            if (max_size >= 0) {
                max_size -= skip;
                if (max_size <= 0) {
                    max_size = 0;
                }
                else {
                    final Pointer ptr = str.ptr;
                    ptr.start += max_size;
                }
                if (str.ptr.start > str.end) {
                    str.ptr.start = str.end;
                }
            }
            else {
                while (str.ptr.start < str.end && str.ptr.buffer[str.ptr.start++] != 10) {}
            }
            int len = 0;
            if (beg < str.ptr.start) {
                len = str.ptr.start - beg;
                System.arraycopy(str.ptr.buffer, beg, buf.buffer, buf.start + skip, len);
            }
            len += skip;
            buf.buffer[buf.start + len] = 0;
            return len;
        }
    }
}
