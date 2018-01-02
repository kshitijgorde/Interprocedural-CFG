// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.io;

import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyModule;
import org.jruby.util.io.InvalidValueException;
import org.jruby.util.io.BadDescriptorException;
import org.jruby.util.io.ChannelStream;
import java.nio.channels.Channel;
import org.jruby.util.io.ChannelDescriptor;
import org.jruby.RubyNumeric;
import org.jruby.util.io.ModeFlags;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyClass;
import org.jruby.Ruby;
import org.jruby.anno.JRubyClass;
import org.jruby.RubyIO;

@JRubyClass(name = { "FFI::FileDescriptorIO" }, parent = "IO")
public class FileDescriptorIO extends RubyIO
{
    public static final String CLASS_NAME = "FileDescriptorIO";
    
    public FileDescriptorIO(final Ruby runtime, final RubyClass klass) {
        super(runtime, klass);
    }
    
    public FileDescriptorIO(final Ruby runtime, final IRubyObject fd) {
        super(runtime, runtime.fastGetModule("FFI").fastGetClass("FileDescriptorIO"));
        try {
            final ModeFlags modes = new ModeFlags(ModeFlags.RDWR);
            this.openFile.setMainStream(ChannelStream.open(this.getRuntime(), new ChannelDescriptor(new FileDescriptorByteChannel(this.getRuntime(), RubyNumeric.fix2int(fd)), modes)));
            this.openFile.setPipeStream(this.openFile.getMainStreamSafe());
            this.openFile.setMode(modes.getOpenFileFlags());
            this.openFile.getMainStreamSafe().setSync(true);
        }
        catch (BadDescriptorException e) {
            throw runtime.newErrnoEBADFError();
        }
        catch (InvalidValueException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static RubyClass createFileDescriptorIOClass(final Ruby runtime, final RubyModule module) {
        final RubyClass result = runtime.defineClassUnder("FileDescriptorIO", runtime.fastGetClass("IO"), Allocator.INSTANCE, module);
        result.defineAnnotatedMethods(FileDescriptorIO.class);
        result.defineAnnotatedConstants(FileDescriptorIO.class);
        return result;
    }
    
    @JRubyMethod(name = { "new" }, meta = true)
    public static FileDescriptorIO newInstance(final ThreadContext context, final IRubyObject recv, final IRubyObject fd) {
        return new FileDescriptorIO(context.getRuntime(), fd);
    }
    
    @JRubyMethod(name = { "wrap" }, required = 1, meta = true)
    public static RubyIO wrap(final ThreadContext context, final IRubyObject recv, final IRubyObject fd) {
        return new FileDescriptorIO(context.getRuntime(), fd);
    }
    
    private static final class Allocator implements ObjectAllocator
    {
        private static final ObjectAllocator INSTANCE;
        
        public final IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
            return new FileDescriptorIO(runtime, klass);
        }
        
        static {
            INSTANCE = new Allocator();
        }
    }
}
