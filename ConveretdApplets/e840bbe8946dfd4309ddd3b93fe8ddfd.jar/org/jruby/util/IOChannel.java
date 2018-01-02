// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.nio.channels.WritableByteChannel;
import java.nio.channels.ReadableByteChannel;
import org.jruby.RubyString;
import java.nio.ByteBuffer;
import java.io.IOException;
import org.jruby.runtime.MethodIndex;
import org.jruby.runtime.CallSite;
import org.jruby.runtime.builtin.IRubyObject;
import java.nio.channels.Channel;

public abstract class IOChannel implements Channel
{
    private final IRubyObject io;
    private final CallSite closeAdapter;
    
    protected IOChannel(final IRubyObject io) {
        this.closeAdapter = MethodIndex.getFunctionalCallSite("close");
        this.io = io;
    }
    
    public void close() throws IOException {
        if (this.io.respondsTo("close")) {
            this.closeAdapter.call(this.io.getRuntime().getCurrentContext(), this.io, this.io);
        }
    }
    
    public boolean isOpen() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    protected int read(final CallSite read, final ByteBuffer dst) throws IOException {
        final IRubyObject readValue = read.call(this.io.getRuntime().getCurrentContext(), this.io, this.io, this.io.getRuntime().newFixnum(dst.remaining()));
        int returnValue = -1;
        if (!readValue.isNil()) {
            final ByteList str = ((RubyString)readValue).getByteList();
            dst.put(str.getUnsafeBytes(), str.getBegin(), str.getRealSize());
            returnValue = str.getRealSize();
        }
        return returnValue;
    }
    
    protected int write(final CallSite write, final ByteBuffer src) throws IOException {
        final ByteList buffer = new ByteList(src.array(), src.position(), src.remaining(), false);
        final IRubyObject written = write.call(this.io.getRuntime().getCurrentContext(), this.io, this.io, RubyString.newStringLight(this.io.getRuntime(), buffer));
        return (int)written.convertToInteger().getLongValue();
    }
    
    protected CallSite initReadSite() {
        if (this.io.respondsTo("read")) {
            return MethodIndex.getFunctionalCallSite("read");
        }
        throw new IllegalArgumentException(this.io.getMetaClass() + "not coercible to " + this.getClass().getSimpleName() + ": no `read' method");
    }
    
    protected CallSite initWriteSite() {
        if (this.io.respondsTo("write")) {
            return MethodIndex.getFunctionalCallSite("write");
        }
        if (this.io.respondsTo("<<")) {
            return MethodIndex.getFunctionalCallSite("<<");
        }
        throw new IllegalArgumentException(this.io.getMetaClass() + "not coercible to " + this.getClass().getSimpleName() + ": no `write' method");
    }
    
    public static class IOReadableByteChannel extends IOChannel implements ReadableByteChannel
    {
        private final CallSite read;
        
        public IOReadableByteChannel(final IRubyObject io) {
            super(io);
            this.read = this.initReadSite();
        }
        
        public int read(final ByteBuffer dst) throws IOException {
            return this.read(this.read, dst);
        }
    }
    
    public static class IOWritableByteChannel extends IOChannel implements WritableByteChannel
    {
        private final CallSite write;
        
        public IOWritableByteChannel(final IRubyObject io) {
            super(io);
            this.write = this.initWriteSite();
        }
        
        public int write(final ByteBuffer src) throws IOException {
            return this.write(this.write, src);
        }
    }
    
    public static class IOReadableWritableByteChannel extends IOChannel implements ReadableByteChannel, WritableByteChannel
    {
        private final CallSite write;
        private final CallSite read;
        
        public IOReadableWritableByteChannel(final IRubyObject io) {
            super(io);
            this.read = this.initReadSite();
            this.write = this.initWriteSite();
        }
        
        public int read(final ByteBuffer dst) throws IOException {
            return this.read(this.read, dst);
        }
        
        public int write(final ByteBuffer src) throws IOException {
            return this.write(this.write, src);
        }
    }
}
