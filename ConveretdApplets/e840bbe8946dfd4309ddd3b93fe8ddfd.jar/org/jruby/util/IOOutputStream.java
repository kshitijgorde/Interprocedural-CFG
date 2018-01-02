// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.io.IOException;
import org.jruby.RubyString;
import org.jruby.RubyIO;
import org.jruby.runtime.MethodIndex;
import org.jruby.runtime.CallSite;
import org.jruby.runtime.builtin.IRubyObject;
import java.io.OutputStream;

public class IOOutputStream extends OutputStream
{
    private final IRubyObject io;
    private final OutputStream out;
    private final CallSite writeAdapter;
    private static final CallSite closeAdapter;
    
    public IOOutputStream(final IRubyObject io, final boolean checkAppend, final boolean verifyCanWrite) {
        this.io = io;
        final CallSite writeSite = MethodIndex.getFunctionalCallSite("write");
        if (io.respondsTo("write")) {
            this.writeAdapter = writeSite;
        }
        else if (checkAppend && io.respondsTo("<<")) {
            this.writeAdapter = MethodIndex.getFunctionalCallSite("<<");
        }
        else {
            if (verifyCanWrite) {
                throw io.getRuntime().newArgumentError("Object: " + io + " is not a legal argument to this wrapper, " + "cause it doesn't respond to \"write\".");
            }
            this.writeAdapter = writeSite;
        }
        this.out = ((io instanceof RubyIO && !((RubyIO)io).isClosed() && ((RubyIO)io).isBuiltin("write")) ? ((RubyIO)io).getOutStream() : null);
    }
    
    public IOOutputStream(final IRubyObject io) {
        this(io, true, true);
    }
    
    public void write(final int bite) throws IOException {
        if (this.out != null) {
            this.out.write(bite);
        }
        else {
            this.writeAdapter.call(this.io.getRuntime().getCurrentContext(), this.io, this.io, RubyString.newStringLight(this.io.getRuntime(), new ByteList(new byte[] { (byte)bite }, false)));
        }
    }
    
    public void write(final byte[] b) throws IOException {
        this.write(b, 0, b.length);
    }
    
    public void write(final byte[] b, final int off, final int len) throws IOException {
        if (this.out != null) {
            this.out.write(b, off, len);
        }
        else {
            this.writeAdapter.call(this.io.getRuntime().getCurrentContext(), this.io, this.io, RubyString.newStringLight(this.io.getRuntime(), new ByteList(b, off, len, false)));
        }
    }
    
    public void close() throws IOException {
        if (this.out != null) {
            this.out.close();
        }
        else if (this.io.respondsTo("close")) {
            IOOutputStream.closeAdapter.call(this.io.getRuntime().getCurrentContext(), this.io, this.io);
        }
    }
    
    static {
        closeAdapter = MethodIndex.getFunctionalCallSite("close");
    }
}
