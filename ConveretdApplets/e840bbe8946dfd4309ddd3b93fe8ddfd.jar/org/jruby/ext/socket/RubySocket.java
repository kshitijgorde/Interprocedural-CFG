// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.socket;

import org.jruby.anno.JRubyModule;
import java.util.regex.Matcher;
import java.net.Inet6Address;
import java.util.List;
import com.kenai.constantine.Constant;
import java.util.ArrayList;
import org.jruby.platform.Platform;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.net.DatagramSocket;
import java.net.Socket;
import java.nio.channels.ConnectionPendingException;
import java.nio.channels.AlreadyConnectedException;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import org.jruby.RubyArray;
import java.net.SocketException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.spi.AbstractSelectableChannel;
import jnr.netdb.Service;
import org.jruby.runtime.Arity;
import java.net.UnknownHostException;
import java.net.InetAddress;
import org.jruby.exceptions.RaiseException;
import java.io.IOException;
import org.jruby.util.io.InvalidValueException;
import org.jruby.util.io.ModeFlags;
import org.jruby.RubyNumeric;
import org.jruby.RubyString;
import org.jruby.anno.JRubyMethod;
import java.nio.channels.Channel;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SocketChannel;
import org.jruby.util.io.ChannelDescriptor;
import org.jruby.RubyFixnum;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyModule;
import org.jruby.RubyClass;
import org.jruby.runtime.builtin.IRubyObject;
import com.kenai.constantine.platform.NameInfo;
import com.kenai.constantine.platform.TCP;
import com.kenai.constantine.platform.Shutdown;
import com.kenai.constantine.platform.IPProto;
import com.kenai.constantine.platform.INAddr;
import com.kenai.constantine.platform.AddressFamily;
import com.kenai.constantine.platform.ProtocolFamily;
import com.kenai.constantine.platform.SocketLevel;
import com.kenai.constantine.platform.SocketOption;
import com.kenai.constantine.platform.Sock;
import org.jruby.Ruby;
import java.util.regex.Pattern;
import org.jruby.util.ByteList;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Socket" }, parent = "BasicSocket", include = { "Socket::Constants" })
public class RubySocket extends RubyBasicSocket
{
    private static ObjectAllocator SOCKET_ALLOCATOR;
    public static final int MSG_OOB = 1;
    public static final int MSG_PEEK = 2;
    public static final int MSG_DONTROUTE = 4;
    private int soDomain;
    private int soType;
    private int soProtocol;
    private static final ByteList BROADCAST;
    private static final byte[] INADDR_BROADCAST;
    private static final ByteList ANY;
    private static final byte[] INADDR_ANY;
    private static final Pattern STRING_IPV4_ADDRESS_PATTERN;
    private static final Pattern ALREADY_BOUND_PATTERN;
    private static final Pattern ADDR_NOT_AVAIL_PATTERN;
    private static final Pattern PERM_DENIED_PATTERN;
    private static final int IPV4_HOST_GROUP = 3;
    private static final int IPV4_PORT_GROUP = 5;
    
    static void createSocket(final Ruby runtime) {
        final RubyClass rb_cSocket = runtime.defineClass("Socket", runtime.fastGetClass("BasicSocket"), RubySocket.SOCKET_ALLOCATOR);
        final RubyModule rb_mConstants = rb_cSocket.defineModuleUnder("Constants");
        runtime.loadConstantSet(rb_mConstants, Sock.class);
        runtime.loadConstantSet(rb_mConstants, SocketOption.class);
        runtime.loadConstantSet(rb_mConstants, SocketLevel.class);
        runtime.loadConstantSet(rb_mConstants, ProtocolFamily.class);
        runtime.loadConstantSet(rb_mConstants, AddressFamily.class);
        runtime.loadConstantSet(rb_mConstants, INAddr.class);
        runtime.loadConstantSet(rb_mConstants, IPProto.class);
        runtime.loadConstantSet(rb_mConstants, Shutdown.class);
        runtime.loadConstantSet(rb_mConstants, TCP.class);
        runtime.loadConstantSet(rb_mConstants, NameInfo.class);
        rb_mConstants.fastSetConstant("MSG_OOB", runtime.newFixnum(1));
        rb_mConstants.fastSetConstant("MSG_PEEK", runtime.newFixnum(2));
        rb_mConstants.fastSetConstant("MSG_DONTROUTE", runtime.newFixnum(4));
        rb_mConstants.fastSetConstant("AI_PASSIVE", runtime.newFixnum(1));
        rb_mConstants.fastSetConstant("IP_MULTICAST_TTL", runtime.newFixnum(10));
        rb_mConstants.fastSetConstant("IP_MULTICAST_LOOP", runtime.newFixnum(11));
        rb_mConstants.fastSetConstant("IP_ADD_MEMBERSHIP", runtime.newFixnum(12));
        rb_mConstants.fastSetConstant("IP_MAX_MEMBERSHIPS", runtime.newFixnum(20));
        rb_mConstants.fastSetConstant("IP_DEFAULT_MULTICAST_LOOP", runtime.newFixnum(1));
        rb_mConstants.fastSetConstant("IP_DEFAULT_MULTICAST_TTL", runtime.newFixnum(1));
        rb_cSocket.includeModule(rb_mConstants);
        rb_cSocket.defineAnnotatedMethods(RubySocket.class);
    }
    
    public RubySocket(final Ruby runtime, final RubyClass type) {
        super(runtime, type);
    }
    
    protected int getSoTypeDefault() {
        return this.soType;
    }
    
    @Deprecated
    public static IRubyObject for_fd(final IRubyObject socketClass, final IRubyObject fd) {
        return for_fd(socketClass.getRuntime().getCurrentContext(), socketClass, fd);
    }
    
    @JRubyMethod(meta = true)
    public static IRubyObject for_fd(final ThreadContext context, final IRubyObject socketClass, final IRubyObject fd) {
        final Ruby ruby = context.getRuntime();
        if (!(fd instanceof RubyFixnum)) {
            throw context.getRuntime().newTypeError(fd, context.getRuntime().getFixnum());
        }
        final RubySocket socket = (RubySocket)((RubyClass)socketClass).allocate();
        final ChannelDescriptor descriptor = ChannelDescriptor.getDescriptorByFileno((int)((RubyFixnum)fd).getLongValue());
        if (descriptor == null) {
            throw ruby.newErrnoEBADFError();
        }
        final Channel mainChannel = descriptor.getChannel();
        if (mainChannel instanceof SocketChannel) {
            socket.soDomain = AddressFamily.AF_INET.value();
            socket.soType = Sock.SOCK_STREAM.value();
            socket.soProtocol = 0;
        }
        else {
            if (!(mainChannel instanceof DatagramChannel)) {
                throw context.getRuntime().newErrnoENOTSOCKError("can't Socket.new/for_fd against a non-socket");
            }
            socket.soDomain = AddressFamily.AF_INET.value();
            socket.soType = Sock.SOCK_DGRAM.value();
            socket.soProtocol = 0;
        }
        socket.initSocket(ruby, descriptor);
        return socket;
    }
    
    @JRubyMethod
    public IRubyObject initialize(final ThreadContext context, final IRubyObject domain, final IRubyObject type, final IRubyObject protocol) {
        try {
            if (domain instanceof RubyString) {
                final String domainString = domain.toString();
                if (domainString.equals("AF_INET")) {
                    this.soDomain = AddressFamily.AF_INET.value();
                }
                else {
                    if (!domainString.equals("PF_INET")) {
                        throw sockerr(context.getRuntime(), "unknown socket domain " + domainString);
                    }
                    this.soDomain = ProtocolFamily.PF_INET.value();
                }
            }
            else {
                this.soDomain = RubyNumeric.fix2int(domain);
            }
            if (type instanceof RubyString) {
                final String typeString = type.toString();
                if (typeString.equals("SOCK_STREAM")) {
                    this.soType = Sock.SOCK_STREAM.value();
                }
                else {
                    if (!typeString.equals("SOCK_DGRAM")) {
                        throw sockerr(context.getRuntime(), "unknown socket type " + typeString);
                    }
                    this.soType = Sock.SOCK_DGRAM.value();
                }
            }
            else {
                this.soType = RubyNumeric.fix2int(type);
            }
            this.soProtocol = RubyNumeric.fix2int(protocol);
            Channel channel = null;
            if (this.soType == Sock.SOCK_STREAM.value()) {
                channel = SocketChannel.open();
            }
            else if (this.soType == Sock.SOCK_DGRAM.value()) {
                channel = DatagramChannel.open();
            }
            this.initSocket(context.getRuntime(), new ChannelDescriptor(channel, new ModeFlags(ModeFlags.RDWR)));
        }
        catch (InvalidValueException ex) {
            throw context.getRuntime().newErrnoEINVALError();
        }
        catch (IOException e) {
            throw sockerr(context.getRuntime(), "initialize: " + e.toString());
        }
        return this;
    }
    
    private static RuntimeException sockerr(final Ruby runtime, final String msg) {
        return new RaiseException(runtime, runtime.fastGetClass("SocketError"), msg, true);
    }
    
    @Deprecated
    public static IRubyObject gethostname(final IRubyObject recv) {
        return gethostname(recv.getRuntime().getCurrentContext(), recv);
    }
    
    @JRubyMethod(meta = true)
    public static IRubyObject gethostname(final ThreadContext context, final IRubyObject recv) {
        try {
            return context.getRuntime().newString(InetAddress.getLocalHost().getHostName());
        }
        catch (UnknownHostException e) {
            try {
                return context.getRuntime().newString(InetAddress.getByAddress(new byte[] { 0, 0, 0, 0 }).getHostName());
            }
            catch (UnknownHostException e2) {
                throw sockerr(context.getRuntime(), "gethostname: name or service not known");
            }
        }
    }
    
    private static InetAddress intoAddress(final Ruby runtime, final String s) {
        try {
            final byte[] bs = ByteList.plain(s);
            return InetAddress.getByAddress(bs);
        }
        catch (Exception e) {
            throw sockerr(runtime, "strtoaddr: " + e.toString());
        }
    }
    
    private static String intoString(final Ruby runtime, final InetAddress as) {
        try {
            return new String(ByteList.plain(as.getAddress()));
        }
        catch (Exception e) {
            throw sockerr(runtime, "addrtostr: " + e.toString());
        }
    }
    
    @Deprecated
    public static IRubyObject gethostbyaddr(final IRubyObject recv, final IRubyObject[] args) {
        return gethostbyaddr(recv.getRuntime().getCurrentContext(), recv, args);
    }
    
    @JRubyMethod(required = 1, rest = true, meta = true)
    public static IRubyObject gethostbyaddr(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        final IRubyObject[] ret = { runtime.newString(intoAddress(runtime, args[0].convertToString().toString()).getCanonicalHostName()), runtime.newArray(), runtime.newFixnum(2), args[0] };
        return runtime.newArrayNoCopy(ret);
    }
    
    @Deprecated
    public static IRubyObject getservbyname(final IRubyObject recv, final IRubyObject[] args) {
        return getservbyname(recv.getRuntime().getCurrentContext(), recv, args);
    }
    
    @JRubyMethod(required = 1, optional = 1, meta = true)
    public static IRubyObject getservbyname(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        final int argc = Arity.checkArgumentCount(runtime, args, 1, 2);
        final String name = args[0].convertToString().toString();
        final String proto = (argc == 1) ? "tcp" : args[1].convertToString().toString();
        final Service service = Service.getServiceByName(name, proto);
        int port;
        if (service != null) {
            port = service.getPort();
        }
        else {
            try {
                port = Integer.parseInt(name.trim());
            }
            catch (NumberFormatException nfe) {
                throw sockerr(runtime, "no such service " + name + "/" + proto);
            }
        }
        return runtime.newFixnum(port);
    }
    
    @JRubyMethod(name = { "listen" }, backtrace = true)
    public IRubyObject listen(final ThreadContext context, final IRubyObject backlog) {
        return context.getRuntime().newFixnum(0);
    }
    
    @Deprecated
    public static IRubyObject pack_sockaddr_un(final IRubyObject recv, final IRubyObject filename) {
        return pack_sockaddr_un(recv.getRuntime().getCurrentContext(), recv, filename);
    }
    
    @JRubyMethod(name = { "pack_sockaddr_un", "sockaddr_un" }, meta = true)
    public static IRubyObject pack_sockaddr_un(final ThreadContext context, final IRubyObject recv, final IRubyObject filename) {
        final StringBuilder sb = new StringBuilder();
        sb.append('\0');
        sb.append('\u0001');
        final String str = filename.convertToString().toString();
        sb.append(str);
        for (int i = str.length(); i < 104; ++i) {
            sb.append('\0');
        }
        return context.getRuntime().newString(sb.toString());
    }
    
    @JRubyMethod(backtrace = true)
    public IRubyObject connect_nonblock(final ThreadContext context, final IRubyObject arg) {
        final Channel socketChannel = this.getChannel();
        try {
            if (!(socketChannel instanceof AbstractSelectableChannel)) {
                throw this.getRuntime().newErrnoENOPROTOOPTError();
            }
            ((AbstractSelectableChannel)socketChannel).configureBlocking(false);
            this.connect(context, arg);
        }
        catch (ClosedChannelException e2) {
            throw context.getRuntime().newErrnoECONNREFUSEDError();
        }
        catch (IOException e3) {
            throw sockerr(context.getRuntime(), "connect(2): name or service not known");
        }
        catch (Error e) {
            final Throwable cause = e.getCause();
            if (!(cause instanceof SocketException)) {
                throw e;
            }
            this.handleSocketException(context.getRuntime(), "connect", (SocketException)cause);
        }
        return RubyFixnum.zero(context.getRuntime());
    }
    
    @JRubyMethod(backtrace = true)
    public IRubyObject connect(final ThreadContext context, final IRubyObject arg) {
        final RubyArray sockaddr = (RubyArray)unpack_sockaddr_in(context, this, arg);
        try {
            final IRubyObject addr = sockaddr.pop(context);
            final IRubyObject port = sockaddr.pop(context);
            final InetSocketAddress iaddr = new InetSocketAddress(addr.convertToString().toString(), RubyNumeric.fix2int(port));
            final Channel socketChannel = this.getChannel();
            if (socketChannel instanceof SocketChannel) {
                if (!((SocketChannel)socketChannel).connect(iaddr)) {
                    throw context.getRuntime().newErrnoEINPROGRESSError();
                }
            }
            else {
                if (!(socketChannel instanceof DatagramChannel)) {
                    throw this.getRuntime().newErrnoENOPROTOOPTError();
                }
                ((DatagramChannel)socketChannel).connect(iaddr);
            }
        }
        catch (AlreadyConnectedException e3) {
            throw context.getRuntime().newErrnoEISCONNError();
        }
        catch (ConnectionPendingException e4) {
            final Channel socketChannel2 = this.getChannel();
            if (socketChannel2 instanceof SocketChannel) {
                try {
                    if (((SocketChannel)socketChannel2).finishConnect()) {
                        throw context.getRuntime().newErrnoEISCONNError();
                    }
                    throw context.getRuntime().newErrnoEINPROGRESSError();
                }
                catch (IOException ex) {
                    throw sockerr(context.getRuntime(), "connect(2): name or service not known");
                }
            }
            throw context.getRuntime().newErrnoEINPROGRESSError();
        }
        catch (UnknownHostException e5) {
            throw sockerr(context.getRuntime(), "connect(2): unknown host");
        }
        catch (SocketException e) {
            this.handleSocketException(context.getRuntime(), "connect", e);
        }
        catch (IOException e6) {
            throw sockerr(context.getRuntime(), "connect(2): name or service not known");
        }
        catch (IllegalArgumentException iae) {
            throw sockerr(context.getRuntime(), iae.getMessage());
        }
        catch (Error e2) {
            final Throwable cause = e2.getCause();
            if (!(cause instanceof SocketException)) {
                throw e2;
            }
            this.handleSocketException(context.getRuntime(), "connect", (SocketException)cause);
        }
        return RubyFixnum.zero(context.getRuntime());
    }
    
    @JRubyMethod(backtrace = true)
    public IRubyObject bind(final ThreadContext context, final IRubyObject arg) {
        final RubyArray sockaddr = (RubyArray)unpack_sockaddr_in(context, this, arg);
        try {
            final IRubyObject addr = sockaddr.pop(context);
            final IRubyObject port = sockaddr.pop(context);
            final InetSocketAddress iaddr = new InetSocketAddress(addr.convertToString().toString(), RubyNumeric.fix2int(port));
            final Channel socketChannel = this.getChannel();
            if (socketChannel instanceof SocketChannel) {
                final Socket socket = ((SocketChannel)socketChannel).socket();
                socket.bind(iaddr);
            }
            else {
                if (!(socketChannel instanceof DatagramChannel)) {
                    throw this.getRuntime().newErrnoENOPROTOOPTError();
                }
                final DatagramSocket socket2 = ((DatagramChannel)socketChannel).socket();
                socket2.bind(iaddr);
            }
        }
        catch (UnknownHostException e3) {
            throw sockerr(context.getRuntime(), "bind(2): unknown host");
        }
        catch (SocketException e) {
            this.handleSocketException(context.getRuntime(), "bind", e);
        }
        catch (IOException e4) {
            throw sockerr(context.getRuntime(), "bind(2): name or service not known");
        }
        catch (IllegalArgumentException iae) {
            throw sockerr(context.getRuntime(), iae.getMessage());
        }
        catch (Error e2) {
            final Throwable cause = e2.getCause();
            if (!(cause instanceof SocketException)) {
                throw e2;
            }
            this.handleSocketException(context.getRuntime(), "bind", (SocketException)cause);
        }
        return RubyFixnum.zero(context.getRuntime());
    }
    
    private void handleSocketException(final Ruby runtime, final String caller, final SocketException e) {
        final String msg = formatMessage(e, "bind");
        if (RubySocket.ALREADY_BOUND_PATTERN.matcher(msg).find()) {
            throw runtime.newErrnoEINVALError(msg);
        }
        if (RubySocket.ADDR_NOT_AVAIL_PATTERN.matcher(msg).find()) {
            throw runtime.newErrnoEADDRNOTAVAILError(msg);
        }
        if (RubySocket.PERM_DENIED_PATTERN.matcher(msg).find()) {
            throw runtime.newErrnoEACCESError(msg);
        }
        throw runtime.newErrnoEADDRINUSEError(msg);
    }
    
    private static String formatMessage(final Throwable e, final String defaultMsg) {
        String msg = e.getMessage();
        if (msg == null) {
            msg = defaultMsg;
        }
        else {
            msg = defaultMsg + " - " + msg;
        }
        return msg;
    }
    
    @Deprecated
    public static IRubyObject pack_sockaddr_in(final IRubyObject recv, final IRubyObject port, final IRubyObject host) {
        return pack_sockaddr_in(recv.getRuntime().getCurrentContext(), recv, port, host);
    }
    
    @JRubyMethod(name = { "pack_sockaddr_in", "sockaddr_in" }, meta = true)
    public static IRubyObject pack_sockaddr_in(final ThreadContext context, final IRubyObject recv, final IRubyObject port, final IRubyObject host) {
        return pack_sockaddr_in(context, recv, RubyNumeric.fix2int(port), host.isNil() ? null : host.convertToString().toString());
    }
    
    public static IRubyObject pack_sockaddr_in(final ThreadContext context, final IRubyObject recv, final int iport, final String host) {
        final ByteArrayOutputStream bufS = new ByteArrayOutputStream();
        try {
            final DataOutputStream ds = new DataOutputStream(bufS);
            writeSockaddrHeader(ds);
            writeSockaddrPort(ds, iport);
            try {
                if (host != null && "".equals(host)) {
                    ds.writeInt(0);
                }
                else {
                    final InetAddress[] addrs = InetAddress.getAllByName(host);
                    final byte[] addr = addrs[0].getAddress();
                    ds.write(addr, 0, addr.length);
                }
            }
            catch (UnknownHostException e) {
                throw sockerr(context.getRuntime(), "getaddrinfo: No address associated with nodename");
            }
            writeSockaddrFooter(ds);
        }
        catch (IOException e2) {
            throw sockerr(context.getRuntime(), "pack_sockaddr_in: internal error");
        }
        return context.getRuntime().newString(new ByteList(bufS.toByteArray(), false));
    }
    
    static IRubyObject pack_sockaddr_in(final ThreadContext context, final InetSocketAddress sock) {
        final ByteArrayOutputStream bufS = new ByteArrayOutputStream();
        try {
            final DataOutputStream ds = new DataOutputStream(bufS);
            writeSockaddrHeader(ds);
            writeSockaddrPort(ds, sock);
            final String host = sock.getAddress().getHostAddress();
            if (host != null && "".equals(host)) {
                ds.writeInt(0);
            }
            else {
                final byte[] addr = sock.getAddress().getAddress();
                ds.write(addr, 0, addr.length);
            }
            writeSockaddrFooter(ds);
        }
        catch (IOException e) {
            throw sockerr(context.getRuntime(), "pack_sockaddr_in: internal error");
        }
        return context.getRuntime().newString(new ByteList(bufS.toByteArray(), false));
    }
    
    @Deprecated
    public static IRubyObject unpack_sockaddr_in(final IRubyObject recv, final IRubyObject addr) {
        return unpack_sockaddr_in(recv.getRuntime().getCurrentContext(), recv, addr);
    }
    
    @JRubyMethod(meta = true)
    public static IRubyObject unpack_sockaddr_in(final ThreadContext context, final IRubyObject recv, final IRubyObject addr) {
        final ByteList val = addr.convertToString().getByteList();
        if ((Platform.IS_BSD && val.get(0) != 16 && val.get(1) != 2) || (!Platform.IS_BSD && val.get(0) != 2)) {
            throw context.getRuntime().newArgumentError("can't resolve socket address of wrong type");
        }
        final int port = ((val.get(2) & 0xFF) << 8) + (val.get(3) & 0xFF);
        final StringBuilder sb = new StringBuilder();
        sb.append(val.get(4) & 0xFF);
        sb.append(".");
        sb.append(val.get(5) & 0xFF);
        sb.append(".");
        sb.append(val.get(6) & 0xFF);
        sb.append(".");
        sb.append(val.get(7) & 0xFF);
        final IRubyObject[] result = { context.getRuntime().newFixnum(port), context.getRuntime().newString(sb.toString()) };
        return context.getRuntime().newArrayNoCopy(result);
    }
    
    public static InetAddress getRubyInetAddress(final ByteList address) throws UnknownHostException {
        if (address.equal(RubySocket.BROADCAST)) {
            return InetAddress.getByAddress(RubySocket.INADDR_BROADCAST);
        }
        if (address.equal(RubySocket.ANY)) {
            return InetAddress.getByAddress(RubySocket.INADDR_ANY);
        }
        return InetAddress.getByName(address.toString());
    }
    
    @Deprecated
    public static IRubyObject gethostbyname(final IRubyObject recv, final IRubyObject hostname) {
        return gethostbyname(recv.getRuntime().getCurrentContext(), recv, hostname);
    }
    
    @JRubyMethod(meta = true)
    public static IRubyObject gethostbyname(final ThreadContext context, final IRubyObject recv, final IRubyObject hostname) {
        try {
            final InetAddress addr = getRubyInetAddress(hostname.convertToString().getByteList());
            final Ruby runtime = context.getRuntime();
            final IRubyObject[] ret = { runtime.newString(addr.getHostAddress()), runtime.newArray(), runtime.newFixnum(2), runtime.newString(new ByteList(addr.getAddress())) };
            return runtime.newArrayNoCopy(ret);
        }
        catch (UnknownHostException e) {
            throw sockerr(context.getRuntime(), "gethostbyname: name or service not known");
        }
    }
    
    @Deprecated
    public static IRubyObject getaddrinfo(final IRubyObject recv, final IRubyObject[] args) {
        return getaddrinfo(recv.getRuntime().getCurrentContext(), recv, args);
    }
    
    @JRubyMethod(required = 2, optional = 4, meta = true)
    public static IRubyObject getaddrinfo(final ThreadContext context, final IRubyObject recv, IRubyObject[] args) {
        args = Arity.scanArgs(context.getRuntime(), args, 2, 4);
        try {
            final Ruby r = context.getRuntime();
            final IRubyObject host = args[0];
            IRubyObject port = args[1];
            final boolean emptyHost = host.isNil() || host.convertToString().isEmpty();
            if (port instanceof RubyString) {
                port = getservbyname(context, recv, new IRubyObject[] { port });
            }
            final IRubyObject family = args[2];
            final IRubyObject socktype = args[3];
            final IRubyObject flags = args[5];
            final boolean is_ipv6 = !family.isNil() && (RubyNumeric.fix2int(family) & AddressFamily.AF_INET6.value()) == AddressFamily.AF_INET6.value();
            boolean sock_stream = true;
            boolean sock_dgram = true;
            if (!socktype.isNil()) {
                final int val = RubyNumeric.fix2int(socktype);
                if (val == Sock.SOCK_STREAM.value()) {
                    sock_dgram = false;
                }
                else if (val == Sock.SOCK_DGRAM.value()) {
                    sock_stream = false;
                }
            }
            InetAddress[] addrs = null;
            if (!flags.isNil() && RubyNumeric.fix2int(flags) > 0) {
                final int flag = RubyNumeric.fix2int(flags);
                if (flag == 1 && emptyHost) {
                    addrs = InetAddress.getAllByName(is_ipv6 ? "[::]" : "0.0.0.0");
                }
            }
            if (addrs == null) {
                addrs = InetAddress.getAllByName(emptyHost ? (is_ipv6 ? "[::1]" : null) : host.convertToString().toString());
            }
            final List<IRubyObject> l = new ArrayList<IRubyObject>();
            for (int i = 0; i < addrs.length; ++i) {
                if (sock_dgram) {
                    final IRubyObject[] c = { r.newString(is_ipv6 ? "AF_INET6" : "AF_INET"), port, r.newString(getHostAddress(recv, addrs[i])), r.newString(addrs[i].getHostAddress()), r.newFixnum(is_ipv6 ? ProtocolFamily.PF_INET6 : ProtocolFamily.PF_INET), r.newFixnum(Sock.SOCK_DGRAM), r.newFixnum(IPProto.IPPROTO_UDP) };
                    l.add(r.newArrayNoCopy(c));
                }
                if (sock_stream) {
                    final IRubyObject[] c = { r.newString(is_ipv6 ? "AF_INET6" : "AF_INET"), port, r.newString(getHostAddress(recv, addrs[i])), r.newString(addrs[i].getHostAddress()), r.newFixnum(is_ipv6 ? ProtocolFamily.PF_INET6 : ProtocolFamily.PF_INET), r.newFixnum(Sock.SOCK_STREAM), r.newFixnum(IPProto.IPPROTO_TCP) };
                    l.add(r.newArrayNoCopy(c));
                }
            }
            return r.newArray(l);
        }
        catch (UnknownHostException e) {
            throw sockerr(context.getRuntime(), "getaddrinfo: name or service not known");
        }
    }
    
    @Deprecated
    public static IRubyObject getnameinfo(final IRubyObject recv, final IRubyObject[] args) {
        return getnameinfo(recv.getRuntime().getCurrentContext(), recv, args);
    }
    
    @JRubyMethod(required = 1, optional = 1, meta = true)
    public static IRubyObject getnameinfo(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        final int argc = Arity.checkArgumentCount(runtime, args, 1, 2);
        final int flags = (argc == 2) ? RubyNumeric.num2int(args[1]) : 0;
        final IRubyObject arg0 = args[0];
        String host;
        String port;
        if (arg0 instanceof RubyArray) {
            final List list = ((RubyArray)arg0).getList();
            final int len = list.size();
            if (len < 3 || len > 4) {
                throw runtime.newArgumentError("array size should be 3 or 4, " + len + " given");
            }
            host = ((list.size() == 3) ? list.get(2).toString() : list.get(3).toString());
            port = list.get(1).toString();
        }
        else {
            if (!(arg0 instanceof RubyString)) {
                throw runtime.newArgumentError("invalid args");
            }
            final String arg2 = ((RubyString)arg0).toString();
            final Matcher m = RubySocket.STRING_IPV4_ADDRESS_PATTERN.matcher(arg2);
            if (!m.matches()) {
                final IRubyObject obj = unpack_sockaddr_in(context, recv, arg0);
                if (!(obj instanceof RubyArray)) {
                    throw runtime.newArgumentError("invalid address string");
                }
                final List list2 = ((RubyArray)obj).getList();
                final int len2 = list2.size();
                if (len2 != 2) {
                    throw runtime.newArgumentError("invalid address representation");
                }
                host = list2.get(1).toString();
                port = list2.get(0).toString();
            }
            else {
                if ((host = m.group(3)) == null || host.length() == 0 || (port = m.group(5)) == null || port.length() == 0) {
                    throw runtime.newArgumentError("invalid address string");
                }
                try {
                    final InetAddress ipv6_addr = InetAddress.getByName(host);
                    if (ipv6_addr instanceof Inet6Address) {
                        host = ipv6_addr.getHostAddress();
                    }
                }
                catch (UnknownHostException uhe) {
                    throw runtime.newArgumentError("invalid address string");
                }
            }
        }
        InetAddress addr;
        try {
            addr = InetAddress.getByName(host);
        }
        catch (UnknownHostException e) {
            throw sockerr(runtime, "unknown host: " + host);
        }
        if ((flags & NameInfo.NI_NUMERICHOST.value()) == 0x0) {
            host = addr.getCanonicalHostName();
        }
        else {
            host = addr.getHostAddress();
        }
        final Service serv = Service.getServiceByPort(Integer.parseInt(port), (String)null);
        if (serv != null) {
            if ((flags & NameInfo.NI_NUMERICSERV.value()) == 0x0) {
                port = serv.getName();
            }
            else {
                port = Integer.toString(serv.getPort());
            }
        }
        return runtime.newArray(runtime.newString(host), runtime.newString(port));
    }
    
    private static String getHostAddress(final IRubyObject recv, final InetAddress addr) {
        return RubyBasicSocket.do_not_reverse_lookup(recv).isTrue() ? addr.getHostAddress() : addr.getCanonicalHostName();
    }
    
    private static void writeSockaddrHeader(final DataOutputStream ds) throws IOException {
        if (Platform.IS_BSD) {
            ds.write(16);
            ds.write(2);
        }
        else {
            ds.write(2);
            ds.write(0);
        }
    }
    
    private static void writeSockaddrFooter(final DataOutputStream ds) throws IOException {
        ds.writeInt(0);
        ds.writeInt(0);
    }
    
    private static void writeSockaddrPort(final DataOutputStream ds, final InetSocketAddress sockaddr) throws IOException {
        writeSockaddrPort(ds, sockaddr.getPort());
    }
    
    private static void writeSockaddrPort(final DataOutputStream ds, final int port) throws IOException {
        ds.write(port >> 8);
        ds.write(port);
    }
    
    static {
        RubySocket.SOCKET_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubySocket(runtime, klass);
            }
        };
        BROADCAST = new ByteList("<broadcast>".getBytes());
        INADDR_BROADCAST = new byte[] { -1, -1, -1, -1 };
        ANY = new ByteList("<any>".getBytes());
        INADDR_ANY = new byte[] { 0, 0, 0, 0 };
        STRING_IPV4_ADDRESS_PATTERN = Pattern.compile("((.*)\\/)?([\\.0-9]+)(:([0-9]+))?");
        ALREADY_BOUND_PATTERN = Pattern.compile("[Aa]lready.*bound");
        ADDR_NOT_AVAIL_PATTERN = Pattern.compile("assign.*address");
        PERM_DENIED_PATTERN = Pattern.compile("[Pp]ermission.*denied");
    }
    
    @JRubyClass(name = { "SocketError" }, parent = "StandardError")
    public static class SocketError
    {
    }
    
    @JRubyModule(name = { "Socket::Constants" })
    public static class Constants
    {
    }
}
