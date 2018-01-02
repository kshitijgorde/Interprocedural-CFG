// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.socket;

import org.jruby.CompatVersion;
import org.jruby.RubyFixnum;
import org.jruby.util.io.Stream;
import java.net.SocketAddress;
import com.kenai.constantine.platform.TCP;
import com.kenai.constantine.platform.IPProto;
import com.kenai.constantine.platform.SocketOption;
import com.kenai.constantine.platform.SocketLevel;
import org.jruby.RubyArray;
import com.kenai.constantine.platform.Sock;
import org.jruby.util.Pack;
import org.jruby.RubyBoolean;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.Channel;
import java.nio.channels.DatagramChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.io.EOFException;
import org.jruby.RubyString;
import org.jruby.RubyNumeric;
import org.jruby.anno.JRubyMethod;
import org.jruby.util.io.BadDescriptorException;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.util.io.InvalidValueException;
import org.jruby.util.io.ChannelStream;
import org.jruby.util.io.ModeFlags;
import org.jruby.util.io.OpenFile;
import org.jruby.util.io.ChannelDescriptor;
import org.jruby.RubyClass;
import org.jruby.Ruby;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.util.ByteList;
import org.jruby.anno.JRubyClass;
import org.jruby.RubyIO;

@JRubyClass(name = { "BasicSocket" }, parent = "IO")
public class RubyBasicSocket extends RubyIO
{
    private static final ByteList FORMAT_SMALL_I;
    protected MulticastStateManager multicastStateManager;
    private static ObjectAllocator BASICSOCKET_ALLOCATOR;
    private boolean doNotReverseLookup;
    
    static void createBasicSocket(final Ruby runtime) {
        final RubyClass rb_cBasicSocket = runtime.defineClass("BasicSocket", runtime.getIO(), RubyBasicSocket.BASICSOCKET_ALLOCATOR);
        rb_cBasicSocket.defineAnnotatedMethods(RubyBasicSocket.class);
    }
    
    public RubyBasicSocket(final Ruby runtime, final RubyClass type) {
        super(runtime, type);
        this.multicastStateManager = null;
        this.doNotReverseLookup = false;
        this.doNotReverseLookup = runtime.is1_9();
    }
    
    protected void initSocket(final Ruby runtime, final ChannelDescriptor descriptor) {
        this.openFile = new OpenFile();
        try {
            this.openFile.setMainStream(ChannelStream.fdopen(runtime, descriptor, new ModeFlags(ModeFlags.RDONLY)));
            this.openFile.setPipeStream(ChannelStream.fdopen(runtime, descriptor, new ModeFlags(ModeFlags.WRONLY)));
            this.openFile.getPipeStream().setSync(true);
        }
        catch (InvalidValueException ex) {
            throw runtime.newErrnoEINVALError();
        }
        this.openFile.setMode(11);
    }
    
    public IRubyObject close_write(final ThreadContext context) {
        if (context.getRuntime().getSafeLevel() >= 4 && this.isTaint()) {
            throw context.getRuntime().newSecurityError("Insecure: can't close");
        }
        if (!this.openFile.isWritable()) {
            return context.getRuntime().getNil();
        }
        if (this.openFile.getPipeStream() == null && this.openFile.isReadable()) {
            throw context.getRuntime().newIOError("closing non-duplex IO for writing");
        }
        if (!this.openFile.isReadable()) {
            this.close();
        }
        else {
            try {
                this.shutdownInternal(context, 1);
            }
            catch (BadDescriptorException e) {
                throw context.runtime.newErrnoEBADFError();
            }
        }
        return context.getRuntime().getNil();
    }
    
    public IRubyObject close_read(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (runtime.getSafeLevel() >= 4 && this.isTaint()) {
            throw runtime.newSecurityError("Insecure: can't close");
        }
        if (!this.openFile.isOpen()) {
            throw context.getRuntime().newIOError("not opened for reading");
        }
        if (!this.openFile.isWritable()) {
            this.close();
        }
        else {
            try {
                this.shutdownInternal(context, 0);
            }
            catch (BadDescriptorException e) {
                throw context.runtime.newErrnoEBADFError();
            }
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "send" }, rest = true)
    public IRubyObject write_send(final ThreadContext context, final IRubyObject[] args) {
        return this.syswrite(context, args[0]);
    }
    
    @Deprecated
    public IRubyObject recv(final IRubyObject[] args) {
        return this.recv(this.getRuntime().getCurrentContext(), args);
    }
    
    @JRubyMethod(rest = true)
    public IRubyObject recv(final ThreadContext context, final IRubyObject[] args) {
        final OpenFile openFile = this.getOpenFileChecked();
        try {
            context.getThread().beforeBlockingCall();
            return RubyString.newString(context.getRuntime(), openFile.getMainStreamSafe().read(RubyNumeric.fix2int(args[0])));
        }
        catch (BadDescriptorException e2) {
            throw context.getRuntime().newErrnoEBADFError();
        }
        catch (EOFException e3) {
            return context.getRuntime().getNil();
        }
        catch (IOException e) {
            if ("Socket not open".equals(e.getMessage())) {
                throw context.getRuntime().newIOError(e.getMessage());
            }
            throw context.getRuntime().newSystemCallError(e.getMessage());
        }
        finally {
            context.getThread().afterBlockingCall();
        }
    }
    
    protected InetSocketAddress getLocalSocket(final String caller) throws BadDescriptorException {
        final Channel socketChannel = this.getOpenChannel();
        if (socketChannel instanceof SocketChannel) {
            return (InetSocketAddress)((SocketChannel)socketChannel).socket().getLocalSocketAddress();
        }
        if (socketChannel instanceof ServerSocketChannel) {
            return (InetSocketAddress)((ServerSocketChannel)socketChannel).socket().getLocalSocketAddress();
        }
        if (socketChannel instanceof DatagramChannel) {
            return (InetSocketAddress)((DatagramChannel)socketChannel).socket().getLocalSocketAddress();
        }
        return null;
    }
    
    protected InetSocketAddress getRemoteSocket() throws BadDescriptorException {
        final Channel socketChannel = this.getOpenChannel();
        if (socketChannel instanceof SocketChannel) {
            return (InetSocketAddress)((SocketChannel)socketChannel).socket().getRemoteSocketAddress();
        }
        return null;
    }
    
    private Socket asSocket() throws BadDescriptorException {
        final Channel socketChannel = this.getOpenChannel();
        if (!(socketChannel instanceof SocketChannel)) {
            throw this.getRuntime().newErrnoENOPROTOOPTError();
        }
        return ((SocketChannel)socketChannel).socket();
    }
    
    private ServerSocket asServerSocket() throws BadDescriptorException {
        final Channel socketChannel = this.getOpenChannel();
        if (!(socketChannel instanceof ServerSocketChannel)) {
            throw this.getRuntime().newErrnoENOPROTOOPTError();
        }
        return ((ServerSocketChannel)socketChannel).socket();
    }
    
    private DatagramSocket asDatagramSocket() throws BadDescriptorException {
        final Channel socketChannel = this.getOpenChannel();
        if (!(socketChannel instanceof DatagramChannel)) {
            throw this.getRuntime().newErrnoENOPROTOOPTError();
        }
        return ((DatagramChannel)socketChannel).socket();
    }
    
    private IRubyObject getBroadcast(final Ruby runtime) throws IOException, BadDescriptorException {
        final Channel socketChannel = this.getOpenChannel();
        return this.trueFalse(runtime, socketChannel instanceof DatagramChannel && this.asDatagramSocket().getBroadcast());
    }
    
    private void setBroadcast(final IRubyObject val) throws IOException, BadDescriptorException {
        final Channel socketChannel = this.getOpenChannel();
        if (socketChannel instanceof DatagramChannel) {
            this.asDatagramSocket().setBroadcast(this.asBoolean(val));
        }
    }
    
    private void setKeepAlive(final IRubyObject val) throws IOException, BadDescriptorException {
        final Channel socketChannel = this.getOpenChannel();
        if (socketChannel instanceof SocketChannel) {
            this.asSocket().setKeepAlive(this.asBoolean(val));
        }
    }
    
    private void setTcpNoDelay(final IRubyObject val) throws IOException, BadDescriptorException {
        final Channel socketChannel = this.getOpenChannel();
        if (socketChannel instanceof SocketChannel) {
            this.asSocket().setTcpNoDelay(this.asBoolean(val));
        }
    }
    
    private void joinMulticastGroup(final IRubyObject val) throws IOException, BadDescriptorException {
        final Channel socketChannel = this.getOpenChannel();
        if (socketChannel instanceof DatagramChannel) {
            if (this.multicastStateManager == null) {
                this.multicastStateManager = new MulticastStateManager();
            }
            if (val instanceof RubyString) {
                final byte[] ipaddr_buf = val.convertToString().getBytes();
                this.multicastStateManager.addMembership(ipaddr_buf);
            }
        }
    }
    
    private void setReuseAddr(final IRubyObject val) throws IOException, BadDescriptorException {
        final Channel socketChannel = this.getOpenChannel();
        if (socketChannel instanceof ServerSocketChannel) {
            this.asServerSocket().setReuseAddress(this.asBoolean(val));
        }
        else if (socketChannel instanceof SocketChannel) {
            this.asSocket().setReuseAddress(this.asBoolean(val));
        }
        else if (socketChannel instanceof DatagramChannel) {
            this.asDatagramSocket().setReuseAddress(this.asBoolean(val));
        }
    }
    
    private void setRcvBuf(final IRubyObject val) throws IOException, BadDescriptorException {
        final Channel socketChannel = this.getOpenChannel();
        if (socketChannel instanceof SocketChannel) {
            this.asSocket().setReceiveBufferSize(this.asNumber(val));
        }
        else if (socketChannel instanceof ServerSocketChannel) {
            this.asServerSocket().setReceiveBufferSize(this.asNumber(val));
        }
        else if (socketChannel instanceof DatagramChannel) {
            this.asDatagramSocket().setReceiveBufferSize(this.asNumber(val));
        }
    }
    
    private void setTimeout(final IRubyObject val) throws IOException, BadDescriptorException {
        final Channel socketChannel = this.getOpenChannel();
        if (socketChannel instanceof SocketChannel) {
            this.asSocket().setSoTimeout(this.asNumber(val));
        }
        else if (socketChannel instanceof ServerSocketChannel) {
            this.asServerSocket().setSoTimeout(this.asNumber(val));
        }
        else if (socketChannel instanceof DatagramChannel) {
            this.asDatagramSocket().setSoTimeout(this.asNumber(val));
        }
    }
    
    private void setSndBuf(final IRubyObject val) throws IOException, BadDescriptorException {
        try {
            final Channel socketChannel = this.getOpenChannel();
            if (socketChannel instanceof SocketChannel) {
                this.asSocket().setSendBufferSize(this.asNumber(val));
            }
            else if (socketChannel instanceof DatagramChannel) {
                this.asDatagramSocket().setSendBufferSize(this.asNumber(val));
            }
        }
        catch (IllegalArgumentException iae) {
            throw this.getRuntime().newErrnoEINVALError(iae.getMessage());
        }
    }
    
    private void setLinger(final IRubyObject val) throws IOException, BadDescriptorException {
        final Channel socketChannel = this.getOpenChannel();
        if (socketChannel instanceof SocketChannel) {
            if (val instanceof RubyBoolean && !val.isTrue()) {
                this.asSocket().setSoLinger(false, 0);
            }
            else {
                final int num = this.asNumber(val);
                if (num == -1) {
                    this.asSocket().setSoLinger(false, 0);
                }
                else {
                    this.asSocket().setSoLinger(true, num);
                }
            }
        }
    }
    
    private void setOOBInline(final IRubyObject val) throws IOException, BadDescriptorException {
        final Channel socketChannel = this.getOpenChannel();
        if (socketChannel instanceof SocketChannel) {
            this.asSocket().setOOBInline(this.asBoolean(val));
        }
    }
    
    private int asNumber(final IRubyObject val) {
        if (val instanceof RubyNumeric) {
            return RubyNumeric.fix2int(val);
        }
        if (val instanceof RubyBoolean) {
            return val.isTrue() ? 1 : 0;
        }
        return this.stringAsNumber(val);
    }
    
    private int stringAsNumber(final IRubyObject val) {
        final ByteList str = val.convertToString().getByteList();
        final IRubyObject res = Pack.unpack(this.getRuntime(), str, RubyBasicSocket.FORMAT_SMALL_I).entry(0);
        if (res.isNil()) {
            throw this.getRuntime().newErrnoEINVALError();
        }
        return RubyNumeric.fix2int(res);
    }
    
    protected boolean asBoolean(final IRubyObject val) {
        if (val instanceof RubyString) {
            return this.stringAsNumber(val) != 0;
        }
        if (val instanceof RubyNumeric) {
            return RubyNumeric.fix2int(val) != 0;
        }
        return val.isTrue();
    }
    
    private IRubyObject getKeepAlive(final Ruby runtime) throws IOException, BadDescriptorException {
        final Channel socketChannel = this.getOpenChannel();
        return this.trueFalse(runtime, socketChannel instanceof SocketChannel && this.asSocket().getKeepAlive());
    }
    
    private IRubyObject getLinger(final Ruby runtime) throws IOException, BadDescriptorException {
        final Channel socketChannel = this.getOpenChannel();
        int linger = 0;
        if (socketChannel instanceof SocketChannel) {
            linger = this.asSocket().getSoLinger();
            if (linger < 0) {
                linger = 0;
            }
        }
        return number(runtime, linger);
    }
    
    private IRubyObject getOOBInline(final Ruby runtime) throws IOException, BadDescriptorException {
        final Channel socketChannel = this.getOpenChannel();
        return this.trueFalse(runtime, socketChannel instanceof SocketChannel && this.asSocket().getOOBInline());
    }
    
    private IRubyObject getRcvBuf(final Ruby runtime) throws IOException, BadDescriptorException {
        final Channel socketChannel = this.getOpenChannel();
        return number(runtime, (socketChannel instanceof SocketChannel) ? this.asSocket().getReceiveBufferSize() : ((socketChannel instanceof ServerSocketChannel) ? this.asServerSocket().getReceiveBufferSize() : this.asDatagramSocket().getReceiveBufferSize()));
    }
    
    private IRubyObject getSndBuf(final Ruby runtime) throws IOException, BadDescriptorException {
        final Channel socketChannel = this.getOpenChannel();
        return number(runtime, (socketChannel instanceof SocketChannel) ? this.asSocket().getSendBufferSize() : ((socketChannel instanceof DatagramChannel) ? this.asDatagramSocket().getSendBufferSize() : 0));
    }
    
    private IRubyObject getReuseAddr(final Ruby runtime) throws IOException, BadDescriptorException {
        final Channel socketChannel = this.getOpenChannel();
        boolean reuse = false;
        if (socketChannel instanceof ServerSocketChannel) {
            reuse = this.asServerSocket().getReuseAddress();
        }
        else if (socketChannel instanceof SocketChannel) {
            reuse = this.asSocket().getReuseAddress();
        }
        else if (socketChannel instanceof DatagramChannel) {
            reuse = this.asDatagramSocket().getReuseAddress();
        }
        return this.trueFalse(runtime, reuse);
    }
    
    private IRubyObject getTimeout(final Ruby runtime) throws IOException, BadDescriptorException {
        final Channel socketChannel = this.getOpenChannel();
        return number(runtime, (socketChannel instanceof SocketChannel) ? this.asSocket().getSoTimeout() : ((socketChannel instanceof ServerSocketChannel) ? this.asServerSocket().getSoTimeout() : ((socketChannel instanceof DatagramChannel) ? this.asDatagramSocket().getSoTimeout() : 0)));
    }
    
    protected int getSoTypeDefault() {
        return 0;
    }
    
    private int getChannelSoType(final Channel channel) {
        if (channel instanceof SocketChannel || channel instanceof ServerSocketChannel) {
            return Sock.SOCK_STREAM.value();
        }
        if (channel instanceof DatagramChannel) {
            return Sock.SOCK_DGRAM.value();
        }
        return this.getSoTypeDefault();
    }
    
    private IRubyObject getSoType(final Ruby runtime) throws IOException, BadDescriptorException {
        final Channel socketChannel = this.getOpenChannel();
        return number(runtime, this.getChannelSoType(socketChannel));
    }
    
    private IRubyObject trueFalse(final Ruby runtime, final boolean val) {
        return number(runtime, val ? 1 : 0);
    }
    
    private static IRubyObject number(final Ruby runtime, final int s) {
        final RubyArray array = runtime.newArray(runtime.newFixnum(s));
        return Pack.pack(runtime, array, RubyBasicSocket.FORMAT_SMALL_I);
    }
    
    @Deprecated
    public IRubyObject getsockopt(final IRubyObject lev, final IRubyObject optname) {
        return this.getsockopt(this.getRuntime().getCurrentContext(), lev, optname);
    }
    
    @JRubyMethod
    public IRubyObject getsockopt(final ThreadContext context, final IRubyObject lev, final IRubyObject optname) {
        final int level = RubyNumeric.fix2int(lev);
        final int opt = RubyNumeric.fix2int(optname);
        final Ruby runtime = context.getRuntime();
        try {
            switch (SocketLevel.valueOf(level)) {
                case SOL_IP:
                case SOL_SOCKET:
                case SOL_TCP:
                case SOL_UDP: {
                    switch (SocketOption.valueOf(opt)) {
                        case SO_BROADCAST: {
                            return this.getBroadcast(runtime);
                        }
                        case SO_KEEPALIVE: {
                            return this.getKeepAlive(runtime);
                        }
                        case SO_LINGER: {
                            return this.getLinger(runtime);
                        }
                        case SO_OOBINLINE: {
                            return this.getOOBInline(runtime);
                        }
                        case SO_RCVBUF: {
                            return this.getRcvBuf(runtime);
                        }
                        case SO_REUSEADDR: {
                            return this.getReuseAddr(runtime);
                        }
                        case SO_SNDBUF: {
                            return this.getSndBuf(runtime);
                        }
                        case SO_RCVTIMEO:
                        case SO_SNDTIMEO: {
                            return this.getTimeout(runtime);
                        }
                        case SO_TYPE: {
                            return this.getSoType(runtime);
                        }
                        case SO_RCVLOWAT: {
                            return number(runtime, 1);
                        }
                        case SO_SNDLOWAT: {
                            return number(runtime, 2048);
                        }
                        case SO_DEBUG:
                        case SO_ERROR:
                        case SO_DONTROUTE:
                        case SO_TIMESTAMP: {
                            return this.trueFalse(runtime, false);
                        }
                        default: {
                            throw context.getRuntime().newErrnoENOPROTOOPTError();
                        }
                    }
                    break;
                }
                default: {
                    throw context.getRuntime().newErrnoENOPROTOOPTError();
                }
            }
        }
        catch (BadDescriptorException e) {
            throw context.getRuntime().newErrnoEBADFError();
        }
        catch (IOException e2) {
            throw context.getRuntime().newErrnoENOPROTOOPTError();
        }
    }
    
    @Deprecated
    public IRubyObject setsockopt(final IRubyObject lev, final IRubyObject optname, final IRubyObject val) {
        return this.setsockopt(this.getRuntime().getCurrentContext(), lev, optname, val);
    }
    
    @JRubyMethod
    public IRubyObject setsockopt(final ThreadContext context, final IRubyObject lev, final IRubyObject optname, final IRubyObject val) {
        final int level = RubyNumeric.fix2int(lev);
        final int opt = RubyNumeric.fix2int(optname);
        try {
            Label_0355: {
                switch (SocketLevel.valueOf(level)) {
                    case SOL_IP:
                    case SOL_SOCKET:
                    case SOL_TCP:
                    case SOL_UDP: {
                        switch (SocketOption.valueOf(opt)) {
                            case SO_BROADCAST: {
                                this.setBroadcast(val);
                                break Label_0355;
                            }
                            case SO_KEEPALIVE: {
                                this.setKeepAlive(val);
                                break Label_0355;
                            }
                            case SO_LINGER: {
                                this.setLinger(val);
                                break Label_0355;
                            }
                            case SO_OOBINLINE: {
                                this.setOOBInline(val);
                                break Label_0355;
                            }
                            case SO_RCVBUF: {
                                this.setRcvBuf(val);
                                break Label_0355;
                            }
                            case SO_REUSEADDR: {
                                this.setReuseAddr(val);
                                break Label_0355;
                            }
                            case SO_SNDBUF: {
                                this.setSndBuf(val);
                                break Label_0355;
                            }
                            case SO_RCVTIMEO:
                            case SO_SNDTIMEO: {
                                this.setTimeout(val);
                                break Label_0355;
                            }
                            case SO_TYPE:
                            case SO_RCVLOWAT:
                            case SO_SNDLOWAT:
                            case SO_DEBUG:
                            case SO_ERROR:
                            case SO_DONTROUTE:
                            case SO_TIMESTAMP: {
                                break Label_0355;
                            }
                            default: {
                                if (IPProto.IPPROTO_TCP.value() == level && TCP.TCP_NODELAY.value() == opt) {
                                    this.setTcpNoDelay(val);
                                    break Label_0355;
                                }
                                if (IPProto.IPPROTO_IP.value() != level) {
                                    throw context.getRuntime().newErrnoENOPROTOOPTError();
                                }
                                if (12 == opt) {
                                    this.joinMulticastGroup(val);
                                    break Label_0355;
                                }
                                break Label_0355;
                            }
                        }
                        break;
                    }
                    default: {
                        if (IPProto.IPPROTO_TCP.value() == level && TCP.TCP_NODELAY.value() == opt) {
                            this.setTcpNoDelay(val);
                            break;
                        }
                        if (IPProto.IPPROTO_IP.value() != level) {
                            throw context.getRuntime().newErrnoENOPROTOOPTError();
                        }
                        if (12 == opt) {
                            this.joinMulticastGroup(val);
                            break;
                        }
                        break;
                    }
                }
            }
        }
        catch (BadDescriptorException e) {
            throw context.getRuntime().newErrnoEBADFError();
        }
        catch (IOException e2) {
            throw context.getRuntime().newErrnoENOPROTOOPTError();
        }
        return context.getRuntime().newFixnum(0);
    }
    
    @Deprecated
    public IRubyObject getsockname() {
        return this.getsockname(this.getRuntime().getCurrentContext());
    }
    
    @JRubyMethod(name = { "getsockname" })
    public IRubyObject getsockname(final ThreadContext context) {
        return this.getSocknameCommon(context, "getsockname");
    }
    
    @JRubyMethod(name = { "__getsockname" })
    public IRubyObject getsockname_u(final ThreadContext context) {
        return this.getSocknameCommon(context, "__getsockname");
    }
    
    protected IRubyObject getSocknameCommon(final ThreadContext context, final String caller) {
        try {
            final InetSocketAddress sock = this.getLocalSocket(caller);
            if (null == sock) {
                return RubySocket.pack_sockaddr_in(context, null, 0, "0.0.0.0");
            }
            return RubySocket.pack_sockaddr_in(context, sock);
        }
        catch (BadDescriptorException e) {
            throw context.runtime.newErrnoEBADFError();
        }
    }
    
    @Deprecated
    public IRubyObject getpeername() {
        return this.getpeername(this.getRuntime().getCurrentContext());
    }
    
    @JRubyMethod(name = { "getpeername", "__getpeername" })
    public IRubyObject getpeername(final ThreadContext context) {
        try {
            final SocketAddress sock = this.getRemoteSocket();
            if (null == sock) {
                throw context.getRuntime().newIOError("Not Supported");
            }
            return context.getRuntime().newString(sock.toString());
        }
        catch (BadDescriptorException e) {
            throw context.runtime.newErrnoEBADFError();
        }
    }
    
    @JRubyMethod(optional = 1)
    public IRubyObject shutdown(final ThreadContext context, final IRubyObject[] args) {
        if (context.getRuntime().getSafeLevel() >= 4 && this.tainted_p(context).isFalse()) {
            throw context.getRuntime().newSecurityError("Insecure: can't shutdown socket");
        }
        int how = 2;
        if (args.length > 0) {
            how = RubyNumeric.fix2int(args[0]);
        }
        try {
            return this.shutdownInternal(context, how);
        }
        catch (BadDescriptorException e) {
            throw context.runtime.newErrnoEBADFError();
        }
    }
    
    private IRubyObject shutdownInternal(final ThreadContext context, final int how) throws BadDescriptorException {
        switch (how) {
            case 0: {
                final Channel socketChannel = this.getOpenChannel();
                try {
                    if (socketChannel instanceof SocketChannel || socketChannel instanceof DatagramChannel) {
                        this.asSocket().shutdownInput();
                    }
                    else if (socketChannel instanceof Shutdownable) {
                        ((Shutdownable)socketChannel).shutdownInput();
                    }
                }
                catch (IOException e) {
                    throw context.getRuntime().newIOError(e.getMessage());
                }
                if (this.openFile.getPipeStream() != null) {
                    this.openFile.setMainStream(this.openFile.getPipeStream());
                    this.openFile.setPipeStream(null);
                }
                this.openFile.setMode(this.openFile.getMode() & 0xFFFFFFFE);
                return RubyFixnum.zero(context.getRuntime());
            }
            case 1: {
                final Channel socketChannel = this.getOpenChannel();
                try {
                    if (socketChannel instanceof SocketChannel || socketChannel instanceof DatagramChannel) {
                        this.asSocket().shutdownOutput();
                    }
                    else if (socketChannel instanceof Shutdownable) {
                        ((Shutdownable)socketChannel).shutdownOutput();
                    }
                }
                catch (IOException e) {
                    throw context.getRuntime().newIOError(e.getMessage());
                }
                this.openFile.setPipeStream(null);
                this.openFile.setMode(this.openFile.getMode() & 0xFFFFFFFD);
                return RubyFixnum.zero(context.getRuntime());
            }
            case 2: {
                this.shutdownInternal(context, 0);
                this.shutdownInternal(context, 1);
                return RubyFixnum.zero(context.getRuntime());
            }
            default: {
                throw context.getRuntime().newArgumentError("`how' should be either 0, 1, 2");
            }
        }
    }
    
    protected boolean doNotReverseLookup(final ThreadContext context) {
        return context.getRuntime().isDoNotReverseLookupEnabled() || this.doNotReverseLookup;
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_9)
    public IRubyObject do_not_reverse_lookup19(final ThreadContext context) {
        return context.getRuntime().newBoolean(this.doNotReverseLookup);
    }
    
    @JRubyMethod(name = { "do_not_reverse_lookup=" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject set_do_not_reverse_lookup19(final ThreadContext context, final IRubyObject flag) {
        this.doNotReverseLookup = flag.isTrue();
        return this.do_not_reverse_lookup19(context);
    }
    
    @JRubyMethod(meta = true)
    public static IRubyObject do_not_reverse_lookup(final IRubyObject recv) {
        return recv.getRuntime().isDoNotReverseLookupEnabled() ? recv.getRuntime().getTrue() : recv.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "do_not_reverse_lookup=" }, meta = true)
    public static IRubyObject set_do_not_reverse_lookup(final IRubyObject recv, final IRubyObject flag) {
        recv.getRuntime().setDoNotReverseLookupEnabled(flag.isTrue());
        return recv.getRuntime().isDoNotReverseLookupEnabled() ? recv.getRuntime().getTrue() : recv.getRuntime().getFalse();
    }
    
    private Channel getOpenChannel() throws BadDescriptorException {
        return this.getOpenFileChecked().getMainStreamSafe().getDescriptor().getChannel();
    }
    
    static {
        FORMAT_SMALL_I = new ByteList(ByteList.plain("i"));
        RubyBasicSocket.BASICSOCKET_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyBasicSocket(runtime, klass);
            }
        };
    }
}
