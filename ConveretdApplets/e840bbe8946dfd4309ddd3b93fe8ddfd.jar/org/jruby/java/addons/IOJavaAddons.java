// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.addons;

import org.jruby.util.IOChannel;
import org.jruby.util.IOOutputStream;
import org.jruby.util.IOInputStream;
import org.jruby.anno.JRubyMethod;
import org.jruby.Ruby;
import org.jruby.javasupport.JavaUtil;
import org.jruby.util.io.InvalidValueException;
import org.jruby.util.io.BadDescriptorException;
import java.io.IOException;
import org.jruby.RubyIO;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;

public class IOJavaAddons
{
    @JRubyMethod
    public static IRubyObject to_inputstream(final ThreadContext context, final IRubyObject self) {
        final RubyIO io = (RubyIO)self;
        final Ruby runtime = context.getRuntime();
        try {
            io.getOpenFile().checkReadable(context.getRuntime());
        }
        catch (IOException ex) {
            throw runtime.newIOErrorFromException(ex);
        }
        catch (BadDescriptorException ex2) {
            throw runtime.newErrnoEBADFError();
        }
        catch (InvalidValueException e) {
            throw runtime.newErrnoEINVALError();
        }
        return JavaUtil.convertJavaToUsableRubyObject(context.getRuntime(), io.getInStream());
    }
    
    @JRubyMethod
    public static IRubyObject to_outputstream(final ThreadContext context, final IRubyObject self) {
        final RubyIO io = (RubyIO)self;
        final Ruby runtime = context.getRuntime();
        try {
            io.getOpenFile().checkWritable(context.getRuntime());
        }
        catch (IOException ex) {
            throw runtime.newIOErrorFromException(ex);
        }
        catch (BadDescriptorException ex2) {
            throw runtime.newErrnoEBADFError();
        }
        catch (InvalidValueException e) {
            throw runtime.newErrnoEINVALError();
        }
        return JavaUtil.convertJavaToUsableRubyObject(context.getRuntime(), io.getOutStream());
    }
    
    @JRubyMethod
    public static IRubyObject to_channel(final ThreadContext context, final IRubyObject self) {
        final RubyIO io = (RubyIO)self;
        return JavaUtil.convertJavaToUsableRubyObject(context.getRuntime(), io.getChannel());
    }
    
    public static class AnyIO
    {
        @JRubyMethod(name = { "to_inputstream" })
        public static IRubyObject any_to_inputstream(final ThreadContext context, final IRubyObject self) {
            return JavaUtil.convertJavaToUsableRubyObject(context.getRuntime(), new IOInputStream(self));
        }
        
        @JRubyMethod(name = { "to_outputstream" })
        public static IRubyObject any_to_outputstream(final ThreadContext context, final IRubyObject self) {
            return JavaUtil.convertJavaToUsableRubyObject(context.getRuntime(), new IOOutputStream(self));
        }
        
        @JRubyMethod(name = { "to_channel" })
        public static IRubyObject any_to_channel(final ThreadContext context, final IRubyObject self) {
            IOChannel channel;
            if (self.respondsTo("read")) {
                if (self.respondsTo("write") || self.respondsTo("<<")) {
                    channel = new IOChannel.IOReadableWritableByteChannel(self);
                }
                else {
                    channel = new IOChannel.IOReadableByteChannel(self);
                }
            }
            else {
                if (!self.respondsTo("write") && !self.respondsTo("<<")) {
                    throw context.getRuntime().newTypeError(self.inspect().toString() + " does not respond to any of read, write, or <<");
                }
                channel = new IOChannel.IOWritableByteChannel(self);
            }
            return JavaUtil.convertJavaToUsableRubyObject(context.getRuntime(), channel);
        }
    }
}
