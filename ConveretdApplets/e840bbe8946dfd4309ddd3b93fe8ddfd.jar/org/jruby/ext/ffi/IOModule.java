// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import org.jruby.anno.JRubyMethod;
import java.nio.ByteBuffer;
import org.jruby.util.io.OpenFile;
import java.io.IOException;
import org.jruby.util.io.BadDescriptorException;
import java.io.EOFException;
import org.jruby.util.io.InvalidValueException;
import org.jruby.RubyNumeric;
import org.jruby.util.io.ChannelStream;
import org.jruby.RubyIO;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyModule;
import org.jruby.Ruby;

public class IOModule
{
    public static void createIOModule(final Ruby runtime, final RubyModule ffi) {
        final RubyModule module = ffi.defineModuleUnder("IO");
        module.defineAnnotatedMethods(IOModule.class);
    }
    
    @JRubyMethod(name = { "native_read" }, module = true)
    public static final IRubyObject native_read(final ThreadContext context, final IRubyObject self, final IRubyObject src, final IRubyObject dst, final IRubyObject rbLength) {
        if (!(src instanceof RubyIO)) {
            throw context.getRuntime().newTypeError("wrong argument (expected IO)");
        }
        if (!(dst instanceof AbstractMemory)) {
            throw context.getRuntime().newTypeError("wrong argument (expected FFI memory)");
        }
        final Ruby runtime = context.getRuntime();
        try {
            final OpenFile openFile = ((RubyIO)src).getOpenFile();
            openFile.checkClosed(runtime);
            openFile.checkReadable(runtime);
            final ChannelStream stream = (ChannelStream)openFile.getMainStreamSafe();
            ByteBuffer buffer = ((AbstractMemory)dst).getMemoryIO().asByteBuffer();
            final int count = RubyNumeric.num2int(rbLength);
            if (count > buffer.remaining()) {
                throw runtime.newIndexError("read count too big for output buffer");
            }
            if (count < buffer.remaining()) {
                buffer = buffer.duplicate();
                buffer.limit(count);
            }
            return runtime.newFixnum(stream.read(buffer));
        }
        catch (InvalidValueException ex) {
            throw runtime.newErrnoEINVALError();
        }
        catch (EOFException e2) {
            return runtime.newFixnum(-1);
        }
        catch (BadDescriptorException e3) {
            throw runtime.newErrnoEBADFError();
        }
        catch (IOException e) {
            throw runtime.newIOErrorFromException(e);
        }
    }
}
