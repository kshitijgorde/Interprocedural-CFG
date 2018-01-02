// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.socket;

import java.net.UnknownHostException;
import java.net.InetAddress;
import org.jruby.anno.JRubyMethod;
import org.jruby.util.io.BadDescriptorException;
import org.jruby.runtime.ThreadContext;
import java.net.InetSocketAddress;
import org.jruby.exceptions.RaiseException;
import org.jruby.RubyClass;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "IPSocket" }, parent = "BasicSocket")
public class RubyIPSocket extends RubyBasicSocket
{
    private static ObjectAllocator IPSOCKET_ALLOCATOR;
    
    static void createIPSocket(final Ruby runtime) {
        final RubyClass rb_cIPSocket = runtime.defineClass("IPSocket", runtime.fastGetClass("BasicSocket"), RubyIPSocket.IPSOCKET_ALLOCATOR);
        rb_cIPSocket.defineAnnotatedMethods(RubyIPSocket.class);
        runtime.getObject().fastSetConstant("IPsocket", rb_cIPSocket);
    }
    
    public RubyIPSocket(final Ruby runtime, final RubyClass type) {
        super(runtime, type);
    }
    
    protected static RuntimeException sockerr(final Ruby runtime, final String msg) {
        return new RaiseException(runtime, runtime.fastGetClass("SocketError"), msg, true);
    }
    
    public IRubyObject packSockaddrFromAddress(final InetSocketAddress sock, final ThreadContext context) {
        if (sock == null) {
            return RubySocket.pack_sockaddr_in(context, this, 0, "");
        }
        return RubySocket.pack_sockaddr_in(context, sock);
    }
    
    private IRubyObject addrFor(final ThreadContext context, final InetSocketAddress addr) {
        final Ruby r = context.getRuntime();
        final IRubyObject[] ret = { r.newString("AF_INET"), r.newFixnum(addr.getPort()), null, null };
        final String hostAddress = addr.getAddress().getHostAddress();
        if (this.doNotReverseLookup(context)) {
            ret[2] = r.newString(hostAddress);
        }
        else {
            ret[2] = r.newString(addr.getHostName());
        }
        ret[3] = r.newString(hostAddress);
        return r.newArrayNoCopy(ret);
    }
    
    @Deprecated
    public IRubyObject addr() {
        return this.addr(this.getRuntime().getCurrentContext());
    }
    
    @JRubyMethod
    public IRubyObject addr(final ThreadContext context) {
        try {
            final InetSocketAddress address = this.getLocalSocket("addr");
            if (address == null) {
                throw context.getRuntime().newErrnoENOTSOCKError("Not socket or not connected");
            }
            return this.addrFor(context, address);
        }
        catch (BadDescriptorException e) {
            throw context.runtime.newErrnoEBADFError();
        }
    }
    
    @Deprecated
    public IRubyObject peeraddr() {
        return this.peeraddr(this.getRuntime().getCurrentContext());
    }
    
    @JRubyMethod
    public IRubyObject peeraddr(final ThreadContext context) {
        try {
            final InetSocketAddress address = this.getRemoteSocket();
            if (address == null) {
                throw context.getRuntime().newErrnoENOTSOCKError("Not socket or not connected");
            }
            return this.addrFor(context, address);
        }
        catch (BadDescriptorException e) {
            throw context.runtime.newErrnoEBADFError();
        }
    }
    
    protected IRubyObject getSocknameCommon(final ThreadContext context, final String caller) {
        try {
            final InetSocketAddress sock = this.getLocalSocket(caller);
            return this.packSockaddrFromAddress(sock, context);
        }
        catch (BadDescriptorException e) {
            throw context.runtime.newErrnoEBADFError();
        }
    }
    
    public IRubyObject getpeername(final ThreadContext context) {
        try {
            final InetSocketAddress sock = this.getRemoteSocket();
            return this.packSockaddrFromAddress(sock, context);
        }
        catch (BadDescriptorException e) {
            throw context.runtime.newErrnoEBADFError();
        }
    }
    
    @Deprecated
    public static IRubyObject getaddress(final IRubyObject recv, final IRubyObject hostname) {
        return getaddress(recv.getRuntime().getCurrentContext(), recv, hostname);
    }
    
    @JRubyMethod(meta = true)
    public static IRubyObject getaddress(final ThreadContext context, final IRubyObject recv, final IRubyObject hostname) {
        try {
            return context.getRuntime().newString(InetAddress.getByName(hostname.convertToString().toString()).getHostAddress());
        }
        catch (UnknownHostException e) {
            throw sockerr(context.getRuntime(), "getaddress: name or service not known");
        }
    }
    
    @JRubyMethod(required = 1, optional = 1)
    public IRubyObject recvfrom(final ThreadContext context, final IRubyObject[] args) {
        try {
            final IRubyObject result = this.recv(context, args);
            final InetSocketAddress sender = this.getRemoteSocket();
            int port;
            String hostName;
            String hostAddress;
            if (sender == null) {
                port = 0;
                hostAddress = (hostName = "0.0.0.0");
            }
            else {
                port = sender.getPort();
                hostName = sender.getHostName();
                hostAddress = sender.getAddress().getHostAddress();
            }
            final IRubyObject addressArray = context.getRuntime().newArray(context.getRuntime().newString("AF_INET"), context.getRuntime().newFixnum(port), context.getRuntime().newString(hostName), context.getRuntime().newString(hostAddress));
            return context.getRuntime().newArray(new IRubyObject[] { result, addressArray });
        }
        catch (BadDescriptorException e) {
            throw context.runtime.newErrnoEBADFError();
        }
    }
    
    static {
        RubyIPSocket.IPSOCKET_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyIPSocket(runtime, klass);
            }
        };
    }
}
