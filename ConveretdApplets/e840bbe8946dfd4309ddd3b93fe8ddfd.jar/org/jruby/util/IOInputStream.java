// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import org.jruby.runtime.MethodIndex;
import org.jruby.RubyString;
import java.io.IOException;
import org.jruby.RubyFixnum;
import org.jruby.RubyIO;
import org.jruby.runtime.CallSite;
import org.jruby.runtime.builtin.IRubyObject;
import java.io.InputStream;

public class IOInputStream extends InputStream
{
    private final IRubyObject io;
    private final InputStream in;
    private final IRubyObject numOne;
    private static final CallSite readAdapter;
    private static final CallSite closeAdapter;
    
    public IOInputStream(final IRubyObject io) {
        if (!io.respondsTo("read")) {
            throw new IllegalArgumentException("Object: " + io + " is not a legal argument to this wrapper, cause it doesn't respond to \"read\".");
        }
        this.io = io;
        this.in = ((io instanceof RubyIO && !((RubyIO)io).isClosed() && ((RubyIO)io).isBuiltin("read")) ? ((RubyIO)io).getInStream() : null);
        this.numOne = RubyFixnum.one(this.io.getRuntime());
    }
    
    public void close() throws IOException {
        if (this.in != null) {
            this.in.close();
        }
        else if (this.io.respondsTo("close")) {
            IOInputStream.closeAdapter.call(this.io.getRuntime().getCurrentContext(), this.io, this.io);
        }
    }
    
    public int available() throws IOException {
        if (this.in != null) {
            return this.in.available();
        }
        return 0;
    }
    
    public int read() throws IOException {
        if (this.in != null) {
            return this.in.read();
        }
        final IRubyObject readValue = IOInputStream.readAdapter.call(this.io.getRuntime().getCurrentContext(), this.io, this.io, this.numOne);
        int returnValue = -1;
        if (!readValue.isNil()) {
            returnValue = readValue.toString().charAt(0);
        }
        return returnValue;
    }
    
    public int read(final byte[] b) throws IOException {
        if (this.in != null) {
            return this.in.read(b, 0, b.length);
        }
        final IRubyObject readValue = IOInputStream.readAdapter.call(this.io.getRuntime().getCurrentContext(), this.io, this.io, this.io.getRuntime().newFixnum(b.length));
        int returnValue = -1;
        if (!readValue.isNil()) {
            final ByteList str = ((RubyString)readValue).getByteList();
            System.arraycopy(str.getUnsafeBytes(), str.getBegin(), b, 0, str.getRealSize());
            returnValue = str.getRealSize();
        }
        return returnValue;
    }
    
    public int read(final byte[] b, final int off, final int len) throws IOException {
        if (this.in != null) {
            return this.in.read(b, off, len);
        }
        final IRubyObject readValue = IOInputStream.readAdapter.call(this.io.getRuntime().getCurrentContext(), this.io, this.io, this.io.getRuntime().newFixnum(len));
        int returnValue = -1;
        if (!readValue.isNil()) {
            final ByteList str = ((RubyString)readValue).getByteList();
            System.arraycopy(str.getUnsafeBytes(), str.getBegin(), b, off, str.getRealSize());
            returnValue = str.getRealSize();
        }
        return returnValue;
    }
    
    static {
        readAdapter = MethodIndex.getFunctionalCallSite("read");
        closeAdapter = MethodIndex.getFunctionalCallSite("close");
    }
}
