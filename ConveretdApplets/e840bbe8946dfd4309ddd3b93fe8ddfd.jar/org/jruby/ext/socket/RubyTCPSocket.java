// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.socket;

import java.net.Inet6Address;
import com.kenai.constantine.Constant;
import com.kenai.constantine.platform.AddressFamily;
import java.net.Inet4Address;
import org.jruby.runtime.Block;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import java.net.Socket;
import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import org.jruby.util.io.InvalidValueException;
import org.jruby.util.io.ChannelDescriptor;
import org.jruby.util.io.ModeFlags;
import java.net.UnknownHostException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import org.jruby.RubyIO;
import java.nio.channels.Channel;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.net.InetAddress;
import java.nio.channels.SocketChannel;
import org.jruby.runtime.Arity;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyNumeric;
import jnr.netdb.Service;
import org.jruby.RubyString;
import org.jruby.RubyClass;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;
import org.jruby.runtime.ObjectAllocator;

public class RubyTCPSocket extends RubyIPSocket
{
    private static ObjectAllocator TCPSOCKET_ALLOCATOR;
    
    static void createTCPSocket(final Ruby runtime) {
        final RubyClass rb_cTCPSocket = runtime.defineClass("TCPSocket", runtime.fastGetClass("IPSocket"), RubyTCPSocket.TCPSOCKET_ALLOCATOR);
        rb_cTCPSocket.includeModule(runtime.fastGetClass("Socket").fastGetConstant("Constants"));
        rb_cTCPSocket.defineAnnotatedMethods(RubyTCPSocket.class);
        runtime.getObject().fastSetConstant("TCPsocket", rb_cTCPSocket);
    }
    
    public RubyTCPSocket(final Ruby runtime, final RubyClass type) {
        super(runtime, type);
    }
    
    private int getPortFrom(final Ruby runtime, final IRubyObject arg) {
        if (arg instanceof RubyString) {
            final Service service = Service.getServiceByName(arg.asJavaString(), "tcp");
            return (service != null) ? service.getPort() : RubyNumeric.fix2int(RubyNumeric.str2inum(runtime, (RubyString)arg, 0, true));
        }
        return RubyNumeric.fix2int(arg);
    }
    
    @JRubyMethod(required = 2, optional = 2, visibility = Visibility.PRIVATE, backtrace = true)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject[] args) {
        Arity.checkArgumentCount(context.getRuntime(), args, 2, 4);
        final String remoteHost = args[0].isNil() ? "localhost" : args[0].convertToString().toString();
        final int remotePort = this.getPortFrom(context.getRuntime(), args[1]);
        final String localHost = (args.length >= 3 && !args[2].isNil()) ? args[2].convertToString().toString() : null;
        final int localPort = (args.length == 4) ? this.getPortFrom(context.getRuntime(), args[3]) : 0;
        try {
            final SocketChannel channel = SocketChannel.open();
            final Socket socket = channel.socket();
            if (localHost != null) {
                socket.bind(new InetSocketAddress(InetAddress.getByName(localHost), localPort));
            }
            boolean success = false;
            try {
                channel.configureBlocking(false);
                channel.connect(new InetSocketAddress(InetAddress.getByName(remoteHost), remotePort));
                context.getThread().select(channel, this, 8);
                channel.finishConnect();
                success = true;
            }
            catch (NoRouteToHostException nrthe) {
                throw context.getRuntime().newErrnoEHOSTUNREACHError("SocketChannel.connect");
            }
            catch (ConnectException e2) {
                throw context.getRuntime().newErrnoECONNREFUSEDError();
            }
            catch (UnknownHostException e3) {
                throw RubyIPSocket.sockerr(context.getRuntime(), "initialize: name or service not known");
            }
            finally {
                if (success) {
                    channel.configureBlocking(true);
                }
            }
            this.initSocket(context.getRuntime(), new ChannelDescriptor(channel, new ModeFlags(ModeFlags.RDWR)));
        }
        catch (InvalidValueException ex) {
            throw context.getRuntime().newErrnoEINVALError();
        }
        catch (ClosedChannelException cce) {
            throw context.getRuntime().newErrnoECONNREFUSEDError();
        }
        catch (IOException e) {
            throw RubyIPSocket.sockerr(context.getRuntime(), e.getLocalizedMessage());
        }
        catch (IllegalArgumentException iae) {
            throw RubyIPSocket.sockerr(context.getRuntime(), iae.getMessage());
        }
        return this;
    }
    
    @Deprecated
    public static IRubyObject open(final IRubyObject recv, final IRubyObject[] args, final Block block) {
        return open(recv.getRuntime().getCurrentContext(), recv, args, block);
    }
    
    @JRubyMethod(rest = true, meta = true)
    public static IRubyObject open(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final RubyTCPSocket sock = (RubyTCPSocket)recv.callMethod(context, "new", args);
        if (!block.isGiven()) {
            return sock;
        }
        try {
            return block.yield(context, sock);
        }
        finally {
            if (sock.openFile.isOpen()) {
                sock.close();
            }
        }
    }
    
    @Deprecated
    public static IRubyObject gethostbyname(final IRubyObject recv, final IRubyObject hostname) {
        return gethostbyname(recv.getRuntime().getCurrentContext(), recv, hostname);
    }
    
    @JRubyMethod(meta = true)
    public static IRubyObject gethostbyname(final ThreadContext context, final IRubyObject recv, final IRubyObject hostname) {
        try {
            final IRubyObject[] ret = new IRubyObject[4];
            final Ruby r = context.getRuntime();
            final String hostString = hostname.convertToString().toString();
            final InetAddress addr = InetAddress.getByName(hostString);
            ret[0] = r.newString(RubyBasicSocket.do_not_reverse_lookup(recv).isTrue() ? addr.getHostAddress() : addr.getCanonicalHostName());
            ret[1] = r.newArray();
            ret[3] = r.newString(addr.getHostAddress());
            if (addr instanceof Inet4Address) {
                final Inet4Address addr2 = (Inet4Address)addr;
                ret[2] = r.newFixnum(AddressFamily.AF_INET);
            }
            else if (addr instanceof Inet6Address) {
                final Inet6Address addr3 = (Inet6Address)addr;
                ret[2] = r.newFixnum(AddressFamily.AF_INET6);
            }
            return r.newArrayNoCopy(ret);
        }
        catch (UnknownHostException e) {
            throw RubyIPSocket.sockerr(context.getRuntime(), "gethostbyname: name or service not known");
        }
    }
    
    static {
        RubyTCPSocket.TCPSOCKET_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyTCPSocket(runtime, klass);
            }
        };
    }
}
