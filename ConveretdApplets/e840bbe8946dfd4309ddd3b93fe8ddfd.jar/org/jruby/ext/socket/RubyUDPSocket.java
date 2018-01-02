// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.socket;

import org.jruby.runtime.Block;
import jnr.netdb.Service;
import java.net.MulticastSocket;
import java.net.PortUnreachableException;
import org.jruby.util.ByteList;
import org.jruby.RubyIO;
import java.net.DatagramPacket;
import java.nio.ByteBuffer;
import org.jruby.RubyModule;
import java.net.SocketException;
import java.net.SocketAddress;
import java.net.InetAddress;
import org.jruby.RubyFixnum;
import java.net.InetSocketAddress;
import org.jruby.RubyNumeric;
import org.jruby.RubyString;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import java.io.IOException;
import java.net.UnknownHostException;
import java.net.ConnectException;
import org.jruby.util.io.InvalidValueException;
import java.nio.channels.Channel;
import org.jruby.util.io.ChannelDescriptor;
import org.jruby.util.io.ModeFlags;
import java.nio.channels.DatagramChannel;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyClass;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "UDPSocket" }, parent = "IPSocket")
public class RubyUDPSocket extends RubyIPSocket
{
    private static ObjectAllocator UDPSOCKET_ALLOCATOR;
    
    static void createUDPSocket(final Ruby runtime) {
        final RubyClass rb_cUDPSocket = runtime.defineClass("UDPSocket", runtime.fastGetClass("IPSocket"), RubyUDPSocket.UDPSOCKET_ALLOCATOR);
        rb_cUDPSocket.includeModule(runtime.fastGetClass("Socket").fastGetConstant("Constants"));
        rb_cUDPSocket.defineAnnotatedMethods(RubyUDPSocket.class);
        runtime.getObject().fastSetConstant("UDPsocket", rb_cUDPSocket);
    }
    
    public RubyUDPSocket(final Ruby runtime, final RubyClass type) {
        super(runtime, type);
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context) {
        try {
            final DatagramChannel channel = DatagramChannel.open();
            this.initSocket(context.getRuntime(), new ChannelDescriptor(channel, new ModeFlags(ModeFlags.RDWR)));
        }
        catch (InvalidValueException ex) {
            throw context.getRuntime().newErrnoEINVALError();
        }
        catch (ConnectException e) {
            throw context.getRuntime().newErrnoECONNREFUSEDError();
        }
        catch (UnknownHostException e2) {
            throw RubyIPSocket.sockerr(context.getRuntime(), "initialize: name or service not known");
        }
        catch (IOException e3) {
            throw RubyIPSocket.sockerr(context.getRuntime(), "initialize: name or service not known");
        }
        return this;
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject protocol) {
        return this.initialize(context);
    }
    
    @Deprecated
    public IRubyObject bind(final IRubyObject host, final IRubyObject port) {
        return this.bind(this.getRuntime().getCurrentContext(), host, port);
    }
    
    @JRubyMethod
    public IRubyObject bind(final ThreadContext context, final IRubyObject host, final IRubyObject port) {
        InetSocketAddress addr = null;
        try {
            if (host.isNil() || (host instanceof RubyString && ((RubyString)host).isEmpty())) {
                addr = new InetSocketAddress(RubyNumeric.fix2int(port));
            }
            else if (host instanceof RubyFixnum) {
                final int intAddr = RubyNumeric.fix2int(host);
                final RubyModule socketMod = context.getRuntime().getModule("Socket");
                if (intAddr == RubyNumeric.fix2int(socketMod.fastGetConstant("INADDR_ANY"))) {
                    addr = new InetSocketAddress(InetAddress.getByName("0.0.0.0"), RubyNumeric.fix2int(port));
                }
            }
            else {
                addr = new InetSocketAddress(InetAddress.getByName(host.convertToString().toString()), RubyNumeric.fix2int(port));
            }
            if (this.multicastStateManager == null) {
                ((DatagramChannel)this.getChannel()).socket().bind(addr);
            }
            else {
                this.multicastStateManager.rebindToPort(RubyNumeric.fix2int(port));
            }
            return RubyFixnum.zero(context.getRuntime());
        }
        catch (UnknownHostException e2) {
            throw RubyIPSocket.sockerr(context.getRuntime(), "bind: name or service not known");
        }
        catch (SocketException e3) {
            throw RubyIPSocket.sockerr(context.getRuntime(), "bind: name or service not known");
        }
        catch (IOException e4) {
            throw RubyIPSocket.sockerr(context.getRuntime(), "bind: name or service not known");
        }
        catch (Error e) {
            if (e.getCause() instanceof SocketException) {
                throw RubyIPSocket.sockerr(context.getRuntime(), "bind: name or service not known");
            }
            throw e;
        }
    }
    
    @Deprecated
    public IRubyObject connect(final IRubyObject host, final IRubyObject port) {
        return this.connect(this.getRuntime().getCurrentContext(), host, port);
    }
    
    @JRubyMethod
    public IRubyObject connect(final ThreadContext context, final IRubyObject host, final IRubyObject port) {
        try {
            final InetSocketAddress addr = new InetSocketAddress(InetAddress.getByName(host.convertToString().toString()), RubyNumeric.fix2int(port));
            ((DatagramChannel)this.getChannel()).connect(addr);
            return RubyFixnum.zero(context.getRuntime());
        }
        catch (UnknownHostException e) {
            throw RubyIPSocket.sockerr(context.getRuntime(), "connect: name or service not known");
        }
        catch (IOException e2) {
            throw RubyIPSocket.sockerr(context.getRuntime(), "connect: name or service not known");
        }
    }
    
    @Deprecated
    public IRubyObject recvfrom(final IRubyObject[] args) {
        return this.recvfrom(this.getRuntime().getCurrentContext(), args);
    }
    
    @JRubyMethod(required = 1, rest = true)
    public IRubyObject recvfrom(final ThreadContext context, final IRubyObject[] args) {
        try {
            InetSocketAddress sender = null;
            final int length = RubyNumeric.fix2int(args[0]);
            final ByteBuffer buf = ByteBuffer.allocate(length);
            final byte[] buf2 = new byte[length];
            final DatagramPacket recv = new DatagramPacket(buf2, buf2.length);
            if (this.multicastStateManager == null) {
                ((DatagramChannel)this.getChannel()).configureBlocking(false);
                context.getThread().select(this, 1);
                sender = (InetSocketAddress)((DatagramChannel)this.getChannel()).receive(buf);
            }
            else {
                final MulticastSocket ms = this.multicastStateManager.getMulticastSocket();
                ms.receive(recv);
                sender = (InetSocketAddress)recv.getSocketAddress();
            }
            if (sender == null) {
                throw context.getRuntime().newErrnoECONNRESETError();
            }
            final IRubyObject addressArray = context.getRuntime().newArray(context.getRuntime().newString("AF_INET"), context.getRuntime().newFixnum(sender.getPort()), context.getRuntime().newString(sender.getHostName()), context.getRuntime().newString(sender.getAddress().getHostAddress()));
            IRubyObject result = null;
            if (this.multicastStateManager == null) {
                result = context.getRuntime().newString(new ByteList(buf.array(), 0, buf.position()));
            }
            else {
                result = context.getRuntime().newString(new ByteList(recv.getData(), 0, recv.getLength()));
            }
            return context.getRuntime().newArray(new IRubyObject[] { result, addressArray });
        }
        catch (UnknownHostException e) {
            throw RubyIPSocket.sockerr(context.getRuntime(), "recvfrom: name or service not known");
        }
        catch (PortUnreachableException e2) {
            throw context.getRuntime().newErrnoECONNREFUSEDError();
        }
        catch (IOException e3) {
            throw RubyIPSocket.sockerr(context.getRuntime(), "recvfrom: name or service not known");
        }
    }
    
    public IRubyObject recv(final ThreadContext context, final IRubyObject[] args) {
        try {
            final int length = RubyNumeric.fix2int(args[0]);
            final ByteBuffer buf = ByteBuffer.allocate(length);
            ((DatagramChannel)this.getChannel()).configureBlocking(false);
            context.getThread().select(this, 1);
            final InetSocketAddress sender = (InetSocketAddress)((DatagramChannel)this.getChannel()).receive(buf);
            if (sender == null) {
                throw context.getRuntime().newErrnoECONNRESETError();
            }
            return context.getRuntime().newString(new ByteList(buf.array(), 0, buf.position()));
        }
        catch (IOException e) {
            throw RubyIPSocket.sockerr(context.getRuntime(), "recv: name or service not known");
        }
    }
    
    @Deprecated
    public IRubyObject send(final IRubyObject[] args) {
        return this.send(this.getRuntime().getCurrentContext(), args);
    }
    
    @JRubyMethod(required = 1, rest = true)
    public IRubyObject send(final ThreadContext context, final IRubyObject[] args) {
        try {
            int written;
            if (args.length >= 3) {
                final RubyString nameStr = args[2].convertToString();
                final RubyString data = args[0].convertToString();
                final ByteBuffer buf = ByteBuffer.wrap(data.getBytes());
                final byte[] buf2 = data.getBytes();
                DatagramPacket sendDP = null;
                int port;
                if (args[3] instanceof RubyString) {
                    final Service service = Service.getServiceByName(args[3].asJavaString(), "udp");
                    if (service != null) {
                        port = service.getPort();
                    }
                    else {
                        port = (int)args[3].convertToInteger("to_i").getLongValue();
                    }
                }
                else {
                    port = (int)args[3].convertToInteger().getLongValue();
                }
                final InetAddress address = RubySocket.getRubyInetAddress(nameStr.getByteList());
                final InetSocketAddress addr = new InetSocketAddress(address, port);
                if (this.multicastStateManager == null) {
                    written = ((DatagramChannel)this.getChannel()).send(buf, addr);
                }
                else {
                    sendDP = new DatagramPacket(buf2, buf2.length, address, port);
                    final MulticastSocket ms = this.multicastStateManager.getMulticastSocket();
                    ms.send(sendDP);
                    written = sendDP.getLength();
                }
            }
            else {
                final RubyString data2 = args[0].convertToString();
                final ByteBuffer buf3 = ByteBuffer.wrap(data2.getBytes());
                written = ((DatagramChannel)this.getChannel()).write(buf3);
            }
            return context.getRuntime().newFixnum(written);
        }
        catch (UnknownHostException e) {
            throw RubyIPSocket.sockerr(context.getRuntime(), "send: name or service not known");
        }
        catch (IOException e2) {
            throw RubyIPSocket.sockerr(context.getRuntime(), "send: name or service not known");
        }
    }
    
    @Deprecated
    public static IRubyObject open(final IRubyObject recv, final IRubyObject[] args, final Block block) {
        return open(recv.getRuntime().getCurrentContext(), recv, args, block);
    }
    
    @JRubyMethod(rest = true, meta = true)
    public static IRubyObject open(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final RubyUDPSocket sock = (RubyUDPSocket)recv.callMethod(context, "new", args);
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
    
    static {
        RubyUDPSocket.UDPSOCKET_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyUDPSocket(runtime, klass);
            }
        };
    }
}
