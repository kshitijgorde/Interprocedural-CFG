// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.socket;

import org.jruby.runtime.Block;
import java.nio.channels.Selector;
import org.jruby.util.io.SelectorFactory;
import java.nio.channels.spi.SelectorProvider;
import java.nio.channels.SocketChannel;
import org.jruby.RubyIO;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import java.io.IOException;
import java.net.SocketException;
import java.net.BindException;
import java.net.UnknownHostException;
import org.jruby.util.io.InvalidValueException;
import java.nio.channels.Channel;
import org.jruby.util.io.ChannelDescriptor;
import org.jruby.util.io.ModeFlags;
import java.net.SocketAddress;
import org.jruby.RubyNumeric;
import org.jruby.RubyInteger;
import java.net.InetAddress;
import org.jruby.RubyFixnum;
import org.jruby.RubyString;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyClass;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;
import java.util.regex.Pattern;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "TCPServer" }, parent = "TCPSocket")
public class RubyTCPServer extends RubyTCPSocket
{
    private static ObjectAllocator TCPSERVER_ALLOCATOR;
    private ServerSocketChannel ssc;
    private InetSocketAddress socket_address;
    private static final Pattern ADDR_NOT_AVAIL_PATTERN;
    
    static void createTCPServer(final Ruby runtime) {
        final RubyClass rb_cTCPServer = runtime.defineClass("TCPServer", runtime.fastGetClass("TCPSocket"), RubyTCPServer.TCPSERVER_ALLOCATOR);
        rb_cTCPServer.defineAnnotatedMethods(RubyTCPServer.class);
        runtime.getObject().fastSetConstant("TCPserver", rb_cTCPServer);
    }
    
    public RubyTCPServer(final Ruby runtime, final RubyClass type) {
        super(runtime, type);
    }
    
    @JRubyMethod(name = { "initialize" }, required = 1, optional = 1, visibility = Visibility.PRIVATE, backtrace = true)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject[] args) {
        IRubyObject hostname = args[0];
        IRubyObject port = (args.length > 1) ? args[1] : context.getRuntime().getNil();
        if (hostname.isNil() || (hostname instanceof RubyString && ((RubyString)hostname).isEmpty())) {
            hostname = context.getRuntime().newString("0.0.0.0");
        }
        else if (hostname instanceof RubyFixnum) {
            port = hostname;
            hostname = context.getRuntime().newString("0.0.0.0");
        }
        final String shost = hostname.convertToString().toString();
        try {
            final InetAddress addr = InetAddress.getByName(shost);
            this.ssc = ServerSocketChannel.open();
            int portInt;
            if (port instanceof RubyInteger) {
                portInt = RubyNumeric.fix2int(port);
            }
            else {
                final IRubyObject portString = port.convertToString();
                final IRubyObject portInteger = portString.convertToInteger("to_i");
                portInt = RubyNumeric.fix2int(portInteger);
                if (portInt <= 0) {
                    portInt = RubyNumeric.fix2int(RubySocket.getservbyname(context, context.getRuntime().getObject(), new IRubyObject[] { portString }));
                }
            }
            this.socket_address = new InetSocketAddress(addr, portInt);
            this.ssc.socket().bind(this.socket_address);
            this.initSocket(context.getRuntime(), new ChannelDescriptor(this.ssc, new ModeFlags(ModeFlags.RDWR)));
        }
        catch (InvalidValueException ex) {
            throw context.getRuntime().newErrnoEINVALError();
        }
        catch (UnknownHostException e3) {
            throw RubyIPSocket.sockerr(context.getRuntime(), "initialize: name or service not known");
        }
        catch (BindException e) {
            String msg = e.getMessage();
            if (msg == null) {
                msg = "bind";
            }
            else {
                msg = "bind - " + msg;
            }
            if (RubyTCPServer.ADDR_NOT_AVAIL_PATTERN.matcher(msg).find()) {
                throw context.getRuntime().newErrnoEADDRNOTAVAILError(msg);
            }
            throw context.getRuntime().newErrnoEADDRINUSEError(msg);
        }
        catch (SocketException e2) {
            final String msg = e2.getMessage();
            if (msg.indexOf("Permission denied") != -1) {
                throw context.getRuntime().newErrnoEACCESError("bind(2)");
            }
            throw RubyIPSocket.sockerr(context.getRuntime(), "initialize: name or service not known");
        }
        catch (IOException e4) {
            throw RubyIPSocket.sockerr(context.getRuntime(), "initialize: name or service not known");
        }
        catch (IllegalArgumentException iae) {
            throw RubyIPSocket.sockerr(context.getRuntime(), iae.getMessage());
        }
        return this;
    }
    
    @Deprecated
    public IRubyObject accept() {
        return this.accept(this.getRuntime().getCurrentContext());
    }
    
    @JRubyMethod(name = { "accept" })
    public IRubyObject accept(final ThreadContext context) {
        final RubyTCPSocket socket = new RubyTCPSocket(context.getRuntime(), context.getRuntime().fastGetClass("TCPSocket"));
        try {
            while (true) {
                final boolean ready = context.getThread().select(this, 16);
                if (ready) {
                    break;
                }
                context.pollThreadEvents();
            }
            try {
                final SocketChannel connected = this.ssc.accept();
                connected.finishConnect();
                synchronized (connected.blockingLock()) {
                    connected.configureBlocking(false);
                    connected.configureBlocking(true);
                }
                socket.initSocket(context.getRuntime(), new ChannelDescriptor(connected, new ModeFlags(ModeFlags.RDWR)));
            }
            catch (InvalidValueException ex) {
                throw context.getRuntime().newErrnoEINVALError();
            }
            return socket;
        }
        catch (IOException e) {
            throw RubyIPSocket.sockerr(context.getRuntime(), "problem when accepting");
        }
    }
    
    @Deprecated
    public IRubyObject accept_nonblock() {
        return this.accept_nonblock(this.getRuntime().getCurrentContext());
    }
    
    @JRubyMethod(name = { "accept_nonblock" })
    public IRubyObject accept_nonblock(final ThreadContext context) {
        final RubyTCPSocket socket = new RubyTCPSocket(context.getRuntime(), context.getRuntime().fastGetClass("TCPSocket"));
        Selector selector = null;
        synchronized (this.ssc.blockingLock()) {
            final boolean oldBlocking = this.ssc.isBlocking();
            try {
                this.ssc.configureBlocking(false);
                selector = SelectorFactory.openWithRetryFrom(this.getRuntime(), SelectorProvider.provider());
                final boolean ready = context.getThread().select(this, 16, 0L);
                if (!ready) {
                    throw context.getRuntime().newErrnoEAGAINError("Resource temporarily unavailable");
                }
                try {
                    socket.initSocket(context.getRuntime(), new ChannelDescriptor(this.ssc.accept(), new ModeFlags(ModeFlags.RDWR)));
                }
                catch (InvalidValueException ex) {
                    throw context.getRuntime().newErrnoEINVALError();
                }
                return socket;
            }
            catch (IOException e) {
                throw RubyIPSocket.sockerr(context.getRuntime(), "problem when accepting");
            }
            finally {
                try {
                    if (selector != null) {
                        selector.close();
                    }
                }
                catch (Exception ex2) {}
                try {
                    this.ssc.configureBlocking(oldBlocking);
                }
                catch (IOException ex3) {}
            }
        }
    }
    
    @Deprecated
    public IRubyObject listen(final IRubyObject backlog) {
        return this.listen(this.getRuntime().getCurrentContext(), backlog);
    }
    
    @JRubyMethod(name = { "listen" }, required = 1)
    public IRubyObject listen(final ThreadContext context, final IRubyObject backlog) {
        return RubyFixnum.zero(context.getRuntime());
    }
    
    @JRubyMethod(name = { "peeraddr" }, rest = true)
    public IRubyObject peeraddr(final ThreadContext context, final IRubyObject[] args) {
        throw context.getRuntime().newNotImplementedError("not supported");
    }
    
    @JRubyMethod(name = { "getpeername" }, rest = true)
    public IRubyObject getpeername(final ThreadContext context, final IRubyObject[] args) {
        throw context.getRuntime().newNotImplementedError("not supported");
    }
    
    @Deprecated
    public static IRubyObject open(final IRubyObject recv, final IRubyObject[] args, final Block block) {
        return open(recv.getRuntime().getCurrentContext(), recv, args, block);
    }
    
    @JRubyMethod(rest = true, meta = true)
    public static IRubyObject open(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final IRubyObject tcpServer = recv.callMethod(context, "new", args);
        if (!block.isGiven()) {
            return tcpServer;
        }
        try {
            return block.yield(context, tcpServer);
        }
        finally {
            tcpServer.callMethod(context, "close");
        }
    }
    
    public IRubyObject gets(final ThreadContext context) {
        throw context.getRuntime().newErrnoENOTCONNError();
    }
    
    static {
        RubyTCPServer.TCPSERVER_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyTCPServer(runtime, klass);
            }
        };
        ADDR_NOT_AVAIL_PATTERN = Pattern.compile("assign.*address");
    }
}
