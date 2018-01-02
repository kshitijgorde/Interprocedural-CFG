// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.io.IOException;
import org.jruby.exceptions.RaiseException;
import org.jruby.RubyException;
import org.jruby.util.io.SelectorFactory;
import java.nio.channels.spi.SelectorProvider;
import org.jruby.RubyNumeric;
import org.jruby.anno.JRubyModule;
import org.jruby.anno.JRubyMethod;
import org.jruby.util.io.Stream;
import org.jruby.util.io.OpenFile;
import org.jruby.util.io.BadDescriptorException;
import org.jruby.RubyString;
import org.jruby.RubyObject;
import java.nio.channels.SelectableChannel;
import org.jruby.util.io.ChannelStream;
import org.jruby.RubyIO;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyModule;
import org.jruby.RubyClass;
import org.jruby.Ruby;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Net::BufferedIO" })
public class NetProtocolBufferedIO
{
    public static void create(final Ruby runtime) {
        final RubyModule mNet = runtime.getModule("Net");
        final RubyClass cBufferedIO = (RubyClass)mNet.getConstant("BufferedIO");
        cBufferedIO.defineAnnotatedMethods(NetProtocolBufferedIO.class);
        final RubyModule mNativeImpl = cBufferedIO.defineModuleUnder("NativeImplementation");
        mNativeImpl.defineAnnotatedMethods(NativeImpl.class);
    }
    
    @JRubyMethod(required = 1)
    public static IRubyObject initialize(final IRubyObject recv, final IRubyObject io) {
        try {
            if (io instanceof RubyIO) {
                final RubyIO rubyIO = (RubyIO)io;
                final OpenFile of = rubyIO.getOpenFile();
                final Stream stream = of.getMainStreamSafe();
                if (stream instanceof ChannelStream) {
                    final ChannelStream cStream = (ChannelStream)stream;
                    if (cStream.getDescriptor().getChannel() instanceof SelectableChannel) {
                        final SelectableChannel selChannel = (SelectableChannel)cStream.getDescriptor().getChannel();
                        ((RubyObject)recv).extend(new IRubyObject[] { ((RubyModule)recv.getRuntime().getModule("Net").getConstant("BufferedIO")).getConstant("NativeImplementation") });
                        final SelectableChannel sc = selChannel;
                        recv.dataWrapStruct(new NativeImpl(sc));
                    }
                }
            }
            recv.getInstanceVariables().setInstanceVariable("@io", io);
            recv.getInstanceVariables().setInstanceVariable("@read_timeout", recv.getRuntime().newFixnum(60));
            recv.getInstanceVariables().setInstanceVariable("@debug_output", recv.getRuntime().getNil());
            recv.getInstanceVariables().setInstanceVariable("@rbuf", RubyString.newEmptyString(recv.getRuntime()));
            return recv;
        }
        catch (BadDescriptorException e) {
            throw recv.getRuntime().newErrnoEBADFError();
        }
    }
    
    @JRubyModule(name = { "Net::BufferedIO::NativeImplementation" })
    public static class NativeImpl
    {
        private SelectableChannel channel;
        
        public NativeImpl(final SelectableChannel channel) {
            this.channel = channel;
        }
        
        @JRubyMethod
        public static IRubyObject rbuf_fill(final IRubyObject recv) {
            final RubyString buf = (RubyString)recv.getInstanceVariables().getInstanceVariable("@rbuf");
            final RubyIO io = (RubyIO)recv.getInstanceVariables().getInstanceVariable("@io");
            final int timeout = RubyNumeric.fix2int(recv.getInstanceVariables().getInstanceVariable("@read_timeout")) * 1000;
            final NativeImpl nim = (NativeImpl)recv.dataGetStruct();
            Selector selector = null;
            synchronized (nim.channel.blockingLock()) {
                final boolean oldBlocking = nim.channel.isBlocking();
                try {
                    selector = SelectorFactory.openWithRetryFrom(recv.getRuntime(), SelectorProvider.provider());
                    nim.channel.configureBlocking(false);
                    final SelectionKey key = nim.channel.register(selector, 1);
                    final int n = selector.select(timeout);
                    if (n > 0) {
                        final IRubyObject readItems = io.read(new IRubyObject[] { recv.getRuntime().newFixnum(16384) });
                        return buf.concat(readItems);
                    }
                    final RubyClass exc = (RubyClass)recv.getRuntime().getModule("Timeout").getConstant("Error");
                    throw new RaiseException(RubyException.newException(recv.getRuntime(), exc, "execution expired"), false);
                }
                catch (IOException exception) {
                    throw recv.getRuntime().newIOErrorFromException(exception);
                }
                finally {
                    if (selector != null) {
                        try {
                            selector.close();
                        }
                        catch (Exception ex) {}
                    }
                    try {
                        nim.channel.configureBlocking(oldBlocking);
                    }
                    catch (IOException ex2) {}
                }
            }
        }
    }
}
