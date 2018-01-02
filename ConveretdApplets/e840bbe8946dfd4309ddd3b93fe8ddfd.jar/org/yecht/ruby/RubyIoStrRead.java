// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.ruby;

import org.jruby.util.ByteList;
import org.jruby.RubyNumeric;
import org.yecht.JechtIO;
import org.yecht.Pointer;
import org.jruby.runtime.builtin.IRubyObject;
import org.yecht.IoStrRead;

public class RubyIoStrRead implements IoStrRead
{
    private IRubyObject port;
    
    public RubyIoStrRead(final IRubyObject port) {
        this.port = port;
    }
    
    public int read(final Pointer buf, final JechtIO.Str str, int max_size, final int skip) {
        int len = 0;
        max_size -= skip;
        if (max_size <= 0) {
            max_size = 0;
        }
        else {
            final IRubyObject src = this.port;
            final IRubyObject n = RubyNumeric.int2fix(this.port.getRuntime(), max_size);
            final IRubyObject str2 = src.callMethod(this.port.getRuntime().getCurrentContext(), "read", n);
            if (!str2.isNil()) {
                final ByteList res = str2.convertToString().getByteList();
                len = res.realSize;
                System.arraycopy(res.bytes, res.begin, buf.buffer, buf.start + skip, len);
            }
        }
        len += skip;
        buf.buffer[buf.start + len] = 0;
        return len;
    }
}
