// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.socket;

import com.kenai.jaffl.struct.Struct;
import com.kenai.jaffl.annotations.Out;
import com.kenai.jaffl.annotations.Transient;
import com.kenai.jaffl.annotations.In;
import com.kenai.constantine.platform.Shutdown;
import java.io.IOException;
import java.nio.channels.WritableByteChannel;
import java.nio.channels.ReadableByteChannel;
import com.kenai.constantine.platform.OpenFlags;
import com.kenai.constantine.platform.Fcntl;
import com.kenai.constantine.platform.ProtocolFamily;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.RubyString;
import java.nio.ByteBuffer;
import org.jruby.runtime.Arity;
import com.kenai.jaffl.byref.IntByReference;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.util.io.InvalidValueException;
import org.jruby.util.io.BadDescriptorException;
import org.jruby.util.io.ChannelStream;
import java.nio.channels.Channel;
import org.jruby.util.io.ChannelDescriptor;
import org.jruby.util.io.ModeFlags;
import com.kenai.constantine.platform.SocketOption;
import com.kenai.constantine.platform.SocketLevel;
import org.jruby.RubyNumeric;
import org.jruby.runtime.ThreadContext;
import org.jruby.util.ByteList;
import com.kenai.constantine.platform.Sock;
import com.kenai.constantine.platform.AddressFamily;
import org.jruby.exceptions.RaiseException;
import org.jruby.runtime.Block;
import org.jruby.RubyException;
import com.kenai.jaffl.LastError;
import org.jruby.RubyClass;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;
import com.kenai.jaffl.Library;
import org.jruby.ext.posix.util.Platform;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "UNIXSocket" }, parent = "BasicSocket")
public class RubyUNIXSocket extends RubyBasicSocket
{
    protected static volatile LibCSocket INSTANCE;
    private static ObjectAllocator UNIXSOCKET_ALLOCATOR;
    protected int fd;
    protected String fpath;
    protected static final int F_GETFL;
    protected static final int F_SETFL;
    protected static final int O_NONBLOCK;
    
    public static boolean tryUnixDomainSocket() {
        if (RubyUNIXSocket.INSTANCE != null) {
            return true;
        }
        if (Platform.IS_WINDOWS) {
            return false;
        }
        try {
            synchronized (RubyUNIXSocket.class) {
                if (RubyUNIXSocket.INSTANCE != null) {
                    return true;
                }
                final String[] array3;
                if (Platform.IS_SOLARIS) {
                    final String[] array2;
                    final String[] array = array2 = new String[3];
                    array[0] = "socket";
                    array[1] = "nsl";
                    array[2] = "c";
                }
                else {
                    array3 = new String[] { "c" };
                }
                final String[] libnames = array3;
                RubyUNIXSocket.INSTANCE = Library.loadLibrary(LibCSocket.class, libnames);
                return true;
            }
        }
        catch (Throwable e) {
            return false;
        }
    }
    
    static void createUNIXSocket(final Ruby runtime) {
        final RubyClass rb_cUNIXSocket = runtime.defineClass("UNIXSocket", runtime.fastGetClass("BasicSocket"), RubyUNIXSocket.UNIXSOCKET_ALLOCATOR);
        runtime.getObject().fastSetConstant("UNIXsocket", rb_cUNIXSocket);
        rb_cUNIXSocket.defineAnnotatedMethods(RubyUNIXSocket.class);
    }
    
    public RubyUNIXSocket(final Ruby runtime, final RubyClass type) {
        super(runtime, type);
    }
    
    protected static void rb_sys_fail(final Ruby runtime, final String message) {
        final int n = LastError.getLastError();
        final IRubyObject arg = (message != null) ? runtime.newString(message) : runtime.getNil();
        RubyClass instance = runtime.getErrno(n);
        if (instance == null) {
            instance = runtime.getSystemCallError();
            throw new RaiseException((RubyException)instance.newInstance(runtime.getCurrentContext(), new IRubyObject[] { arg, runtime.newFixnum(n) }, Block.NULL_BLOCK));
        }
        throw new RaiseException((RubyException)instance.newInstance(runtime.getCurrentContext(), new IRubyObject[] { arg }, Block.NULL_BLOCK));
    }
    
    protected void init_unixsock(final Ruby runtime, final IRubyObject _path, final boolean server) {
        this.fd = -1;
        final ByteList path = _path.convertToString().getByteList();
        this.fpath = path.toString();
        try {
            this.fd = RubyUNIXSocket.INSTANCE.socket(AddressFamily.AF_UNIX.value(), Sock.SOCK_STREAM.value(), 0);
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {}
        if (this.fd < 0) {
            rb_sys_fail(runtime, "socket(2)");
        }
        final LibCSocket.sockaddr_un sockaddr = LibCSocket.sockaddr_un.newInstance();
        sockaddr.setFamily(AddressFamily.AF_UNIX.value());
        if (sockaddr.path().length() <= path.getRealSize()) {
            throw runtime.newArgumentError("too long unix socket path (max: " + (sockaddr.path().length() - 1) + "bytes)");
        }
        sockaddr.path().set(this.fpath);
        int status;
        if (server) {
            status = RubyUNIXSocket.INSTANCE.bind(this.fd, sockaddr, 106);
        }
        else {
            try {
                status = RubyUNIXSocket.INSTANCE.connect(this.fd, sockaddr, 106);
            }
            catch (RuntimeException e) {
                RubyUNIXSocket.INSTANCE.close(this.fd);
                throw e;
            }
        }
        if (status < 0) {
            RubyUNIXSocket.INSTANCE.close(this.fd);
            rb_sys_fail(runtime, this.fpath);
        }
        if (server) {
            RubyUNIXSocket.INSTANCE.listen(this.fd, 5);
        }
        this.init_sock(runtime);
        if (server) {
            this.openFile.setPath(this.fpath);
        }
    }
    
    public IRubyObject setsockopt(final ThreadContext context, final IRubyObject lev, final IRubyObject optname, final IRubyObject val) {
        final int level = RubyNumeric.fix2int(lev);
        final int opt = RubyNumeric.fix2int(optname);
        switch (SocketLevel.valueOf(level)) {
            case SOL_SOCKET: {
                switch (SocketOption.valueOf(opt)) {
                    case SO_KEEPALIVE: {
                        final LibCSocket instance = RubyUNIXSocket.INSTANCE;
                        final int fd = this.fd;
                        final int n = level;
                        final int n2 = opt;
                        byte[] array2;
                        if (this.asBoolean(val)) {
                            final byte[] array = array2 = new byte[4];
                            array[0] = 32;
                            array[1] = 0;
                            array[3] = (array[2] = 0);
                        }
                        else {
                            final byte[] array3 = array2 = new byte[4];
                            array3[1] = (array3[0] = 0);
                            array3[3] = (array3[2] = 0);
                        }
                        final int res = instance.setsockopt(fd, n, n2, array2, 4);
                        if (res == -1) {
                            rb_sys_fail(context.getRuntime(), this.openFile.getPath());
                        }
                        return context.getRuntime().newFixnum(0);
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
    
    protected void init_sock(final Ruby runtime) {
        try {
            final ModeFlags modes = new ModeFlags(ModeFlags.RDWR);
            this.openFile.setMainStream(ChannelStream.open(runtime, new ChannelDescriptor(new UnixDomainSocketChannel(runtime, this.fd), modes)));
            this.openFile.setPipeStream(this.openFile.getMainStreamSafe());
            this.openFile.setMode(modes.getOpenFileFlags());
            this.openFile.getMainStreamSafe().setSync(true);
        }
        catch (BadDescriptorException e) {
            throw runtime.newErrnoEBADFError();
        }
        catch (InvalidValueException ive) {
            throw runtime.newErrnoEINVALError();
        }
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject path) {
        this.init_unixsock(context.getRuntime(), path, false);
        return this;
    }
    
    private String unixpath(final LibCSocket.sockaddr_un addr, final IntByReference len) {
        if (len.getValue() > 2) {
            return addr.path().toString();
        }
        return "";
    }
    
    private IRubyObject unixaddr(final Ruby runtime, final LibCSocket.sockaddr_un addr, final IntByReference len) {
        return runtime.newArrayNoCopy(runtime.newString("AF_UNIX"), runtime.newString(this.unixpath(addr, len)));
    }
    
    @Deprecated
    public IRubyObject path() {
        return this.path(this.getRuntime().getCurrentContext());
    }
    
    @JRubyMethod
    public IRubyObject path(final ThreadContext context) {
        if (this.openFile.getPath() == null) {
            final LibCSocket.sockaddr_un addr = LibCSocket.sockaddr_un.newInstance();
            final IntByReference len = new IntByReference(Integer.valueOf(106));
            if (RubyUNIXSocket.INSTANCE.getsockname(this.fd, addr, len) < 0) {
                rb_sys_fail(context.getRuntime(), null);
            }
            this.openFile.setPath(this.unixpath(addr, len));
        }
        return context.getRuntime().newString(this.openFile.getPath());
    }
    
    @Deprecated
    public IRubyObject addr() {
        return this.addr(this.getRuntime().getCurrentContext());
    }
    
    @JRubyMethod
    public IRubyObject addr(final ThreadContext context) {
        final LibCSocket.sockaddr_un addr = LibCSocket.sockaddr_un.newInstance();
        final IntByReference len = new IntByReference(Integer.valueOf(106));
        if (RubyUNIXSocket.INSTANCE.getsockname(this.fd, addr, len) < 0) {
            rb_sys_fail(context.getRuntime(), "getsockname(2)");
        }
        return this.unixaddr(context.getRuntime(), addr, len);
    }
    
    @Deprecated
    public IRubyObject peeraddr() {
        return this.peeraddr(this.getRuntime().getCurrentContext());
    }
    
    @JRubyMethod
    public IRubyObject peeraddr(final ThreadContext context) {
        final LibCSocket.sockaddr_un addr = LibCSocket.sockaddr_un.newInstance();
        final IntByReference len = new IntByReference(Integer.valueOf(106));
        if (RubyUNIXSocket.INSTANCE.getpeername(this.fd, addr, len) < 0) {
            rb_sys_fail(context.getRuntime(), "getpeername(2)");
        }
        return this.unixaddr(context.getRuntime(), addr, len);
    }
    
    @Deprecated
    public IRubyObject recvfrom(final IRubyObject[] args) {
        return this.recvfrom(this.getRuntime().getCurrentContext(), args);
    }
    
    @JRubyMethod(name = { "recvfrom" }, required = 1, optional = 1)
    public IRubyObject recvfrom(final ThreadContext context, final IRubyObject[] args) {
        final LibCSocket.sockaddr_un buf = LibCSocket.sockaddr_un.newInstance();
        final IntByReference alen = new IntByReference(Integer.valueOf(106));
        IRubyObject flg;
        if (Arity.checkArgumentCount(context.getRuntime(), args, 1, 2) == 2) {
            flg = args[1];
        }
        else {
            flg = context.getRuntime().getNil();
        }
        final IRubyObject len = args[0];
        int flags;
        if (flg.isNil()) {
            flags = 0;
        }
        else {
            flags = RubyNumeric.fix2int(flg);
        }
        int buflen = RubyNumeric.fix2int(len);
        final byte[] tmpbuf = new byte[buflen];
        final ByteBuffer str = ByteBuffer.wrap(tmpbuf);
        final int slen = RubyUNIXSocket.INSTANCE.recvfrom(this.fd, str, buflen, flags, buf, alen);
        if (slen < 0) {
            rb_sys_fail(context.getRuntime(), "recvfrom(2)");
        }
        if (slen < buflen) {
            buflen = slen;
        }
        final RubyString _str = context.getRuntime().newString(new ByteList(tmpbuf, 0, buflen, true));
        return context.getRuntime().newArrayNoCopy(_str, this.unixaddr(context.getRuntime(), buf, alen));
    }
    
    @JRubyMethod
    public IRubyObject send_io(final IRubyObject path) {
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod(rest = true)
    public IRubyObject recv_io(final IRubyObject[] args) {
        return this.getRuntime().getNil();
    }
    
    public IRubyObject close() {
        super.close();
        RubyUNIXSocket.INSTANCE.close(this.fd);
        return this.getRuntime().getNil();
    }
    
    @Deprecated
    public static IRubyObject open(final IRubyObject recv, final IRubyObject path) {
        return open(recv.getRuntime().getCurrentContext(), recv, path);
    }
    
    @JRubyMethod(meta = true)
    public static IRubyObject open(final ThreadContext context, final IRubyObject recv, final IRubyObject path) {
        return RuntimeHelpers.invoke(context, recv, "new", path);
    }
    
    private static int getSocketType(final IRubyObject tp) {
        if (!(tp instanceof RubyString)) {
            return RubyNumeric.fix2int(tp);
        }
        final String str = tp.toString();
        if ("SOCK_STREAM".equals(str)) {
            return Sock.SOCK_STREAM.value();
        }
        if ("SOCK_DGRAM".equals(str)) {
            return Sock.SOCK_DGRAM.value();
        }
        if ("SOCK_RAW".equals(str)) {
            return Sock.SOCK_RAW.value();
        }
        return -1;
    }
    
    @Deprecated
    public static IRubyObject socketpair(final IRubyObject recv, final IRubyObject[] args) {
        return socketpair(recv.getRuntime().getCurrentContext(), recv, args);
    }
    
    @JRubyMethod(name = { "socketpair", "pair" }, optional = 2, meta = true)
    public static IRubyObject socketpair(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        final int domain = ProtocolFamily.PF_UNIX.value();
        final Ruby runtime = context.getRuntime();
        Arity.checkArgumentCount(runtime, args, 0, 2);
        int type;
        if (args.length == 0) {
            type = Sock.SOCK_STREAM.value();
        }
        else {
            type = getSocketType(args[0]);
        }
        int protocol;
        if (args.length <= 1) {
            protocol = 0;
        }
        else {
            protocol = RubyNumeric.fix2int(args[1]);
        }
        final int[] sp = new int[2];
        int ret = -1;
        try {
            ret = RubyUNIXSocket.INSTANCE.socketpair(domain, type, protocol, sp);
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {}
        if (ret < 0) {
            rb_sys_fail(runtime, "socketpair(2)");
        }
        final RubyUNIXSocket sock = (RubyUNIXSocket)RuntimeHelpers.invoke(context, runtime.fastGetClass("UNIXSocket"), "allocate");
        sock.fd = sp[0];
        sock.init_sock(runtime);
        final RubyUNIXSocket sock2 = (RubyUNIXSocket)RuntimeHelpers.invoke(context, runtime.fastGetClass("UNIXSocket"), "allocate");
        sock2.fd = sp[1];
        sock2.init_sock(runtime);
        return runtime.newArrayNoCopy(sock, sock2);
    }
    
    static {
        RubyUNIXSocket.INSTANCE = null;
        RubyUNIXSocket.UNIXSOCKET_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyUNIXSocket(runtime, klass);
            }
        };
        F_GETFL = Fcntl.F_GETFL.value();
        F_SETFL = Fcntl.F_SETFL.value();
        O_NONBLOCK = OpenFlags.O_NONBLOCK.value();
    }
    
    private static class UnixDomainSocketChannel implements ReadableByteChannel, WritableByteChannel, Shutdownable
    {
        private static final int SHUT_RD;
        private static final int SHUT_WR;
        private final Ruby runtime;
        private final int fd;
        private boolean open;
        
        public UnixDomainSocketChannel(final Ruby runtime, final int fd) {
            this.open = true;
            this.fd = fd;
            this.runtime = runtime;
        }
        
        public void close() throws IOException {
            this.open = false;
        }
        
        public boolean isOpen() {
            return this.open;
        }
        
        public int read(final ByteBuffer dst) throws IOException {
            final int max = dst.remaining();
            final int v = RubyUNIXSocket.INSTANCE.recv(this.fd, dst, max, 0);
            if (v != -1) {
                dst.position(dst.position() + v);
            }
            if (v == 0) {
                return -1;
            }
            return v;
        }
        
        public int write(final ByteBuffer src) throws IOException {
            final int max = src.remaining();
            final int v = RubyUNIXSocket.INSTANCE.send(this.fd, src, max, 0);
            if (v != -1) {
                src.position(src.position() + v);
            }
            return v;
        }
        
        public void shutdownInput() throws IOException {
            final int v = RubyUNIXSocket.INSTANCE.shutdown(this.fd, UnixDomainSocketChannel.SHUT_RD);
            if (v == -1) {
                RubyUNIXSocket.rb_sys_fail(this.runtime, "shutdown(2)");
            }
        }
        
        public void shutdownOutput() throws IOException {
            final int v = RubyUNIXSocket.INSTANCE.shutdown(this.fd, UnixDomainSocketChannel.SHUT_WR);
            if (v == -1) {
                RubyUNIXSocket.rb_sys_fail(this.runtime, "shutdown(2)");
            }
        }
        
        static {
            SHUT_RD = Shutdown.SHUT_RD.value();
            SHUT_WR = Shutdown.SHUT_WR.value();
        }
    }
    
    public interface LibCSocket
    {
        int socketpair(final int p0, final int p1, final int p2, final int[] p3);
        
        int socket(final int p0, final int p1, final int p2);
        
        int connect(final int p0, @In @Transient final sockaddr_un p1, final int p2);
        
        int bind(final int p0, @Transient final sockaddr_un p1, final int p2);
        
        int listen(final int p0, final int p1);
        
        int accept(final int p0, @Transient final sockaddr_un p1, final IntByReference p2);
        
        int getsockname(final int p0, @Out @Transient final sockaddr_un p1, final IntByReference p2);
        
        int getpeername(final int p0, @Out @Transient final sockaddr_un p1, final IntByReference p2);
        
        int getsockopt(final int p0, final int p1, final int p2, @Out final byte[] p3, final IntByReference p4);
        
        int setsockopt(final int p0, final int p1, final int p2, @In final byte[] p3, final int p4);
        
        int recv(final int p0, @Out final ByteBuffer p1, final int p2, final int p3);
        
        int recvfrom(final int p0, @Out final ByteBuffer p1, final int p2, final int p3, @Out @Transient final sockaddr_un p4, final IntByReference p5);
        
        int send(final int p0, @In final ByteBuffer p1, final int p2, final int p3);
        
        int fcntl(final int p0, final int p1, final int p2);
        
        int unlink(final String p0);
        
        int close(final int p0);
        
        int shutdown(final int p0, final int p1);
        
        void perror(final String p0);
        
        public abstract static class sockaddr_un extends Struct
        {
            public static final int LENGTH = 106;
            
            public abstract void setFamily(final int p0);
            
            public abstract int getFamily();
            
            public abstract UTF8String path();
            
            public static final sockaddr_un newInstance() {
                return Platform.IS_BSD ? new BSDSockAddrUnix() : new DefaultSockAddrUnix();
            }
        }
        
        public static final class BSDSockAddrUnix extends sockaddr_un
        {
            public final Signed8 sun_len;
            public final Signed8 sun_family;
            public final UTF8String sun_path;
            
            public BSDSockAddrUnix() {
                this.sun_len = new Signed8(this);
                this.sun_family = new Signed8(this);
                this.sun_path = new UTF8String(this, 104);
            }
            
            public final void setFamily(final int family) {
                this.sun_family.set((byte)family);
            }
            
            public final int getFamily() {
                return this.sun_family.get();
            }
            
            public final UTF8String path() {
                return this.sun_path;
            }
        }
        
        public static final class DefaultSockAddrUnix extends sockaddr_un
        {
            public final Signed16 sun_family;
            public final UTF8String sun_path;
            
            public DefaultSockAddrUnix() {
                this.sun_family = new Signed16(this);
                this.sun_path = new UTF8String(this, 104);
            }
            
            public final void setFamily(final int family) {
                this.sun_family.set((short)family);
            }
            
            public final int getFamily() {
                return this.sun_family.get();
            }
            
            public final UTF8String path() {
                return this.sun_path;
            }
        }
    }
}
