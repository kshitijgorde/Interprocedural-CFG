// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.socket;

import org.jruby.RubyNumeric;
import org.jruby.javasupport.util.RuntimeHelpers;
import com.kenai.jaffl.byref.IntByReference;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyClass;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "UNIXServer" }, parent = "UNIXSocket")
public class RubyUNIXServer extends RubyUNIXSocket
{
    private static ObjectAllocator UNIXSERVER_ALLOCATOR;
    
    static void createUNIXServer(final Ruby runtime) {
        final RubyClass rb_cUNIXServer = runtime.defineClass("UNIXServer", runtime.fastGetClass("UNIXSocket"), RubyUNIXServer.UNIXSERVER_ALLOCATOR);
        runtime.getObject().fastSetConstant("UNIXserver", rb_cUNIXServer);
        rb_cUNIXServer.defineAnnotatedMethods(RubyUNIXServer.class);
    }
    
    public RubyUNIXServer(final Ruby runtime, final RubyClass type) {
        super(runtime, type);
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final ThreadContext context, final IRubyObject path) {
        this.init_unixsock(context.getRuntime(), path, true);
        return this;
    }
    
    @Deprecated
    public IRubyObject accept() {
        return this.accept(this.getRuntime().getCurrentContext());
    }
    
    @JRubyMethod
    public IRubyObject accept(final ThreadContext context) {
        final LibCSocket.sockaddr_un from = LibCSocket.sockaddr_un.newInstance();
        final int fd2 = RubyUNIXServer.INSTANCE.accept(this.fd, from, new IntByReference(Integer.valueOf(106)));
        if (fd2 < 0) {
            RubyUNIXSocket.rb_sys_fail(context.getRuntime(), null);
        }
        final Ruby runtime = context.getRuntime();
        final RubyUNIXSocket sock = (RubyUNIXSocket)RuntimeHelpers.invoke(context, runtime.fastGetClass("UNIXSocket"), "allocate");
        sock.fd = fd2;
        sock.fpath = from.path().toString();
        sock.init_sock(context.getRuntime());
        return sock;
    }
    
    @Deprecated
    public IRubyObject accept_nonblock() {
        return this.accept_nonblock(this.getRuntime().getCurrentContext());
    }
    
    @JRubyMethod
    public IRubyObject accept_nonblock(final ThreadContext context) {
        final LibCSocket.sockaddr_un from = LibCSocket.sockaddr_un.newInstance();
        final IntByReference fromlen = new IntByReference(Integer.valueOf(106));
        final int flags = RubyUNIXServer.INSTANCE.fcntl(this.fd, RubyUNIXSocket.F_GETFL, 0);
        RubyUNIXServer.INSTANCE.fcntl(this.fd, RubyUNIXSocket.F_SETFL, flags | RubyUNIXSocket.O_NONBLOCK);
        final int fd2 = RubyUNIXServer.INSTANCE.accept(this.fd, from, new IntByReference(Integer.valueOf(106)));
        if (fd2 < 0) {
            RubyUNIXSocket.rb_sys_fail(context.getRuntime(), null);
        }
        final Ruby runtime = context.getRuntime();
        final RubyUNIXSocket sock = (RubyUNIXSocket)RuntimeHelpers.invoke(context, runtime.fastGetClass("UNIXSocket"), "allocate");
        sock.fd = fd2;
        sock.fpath = from.path().toString();
        sock.init_sock(context.getRuntime());
        return sock;
    }
    
    @Deprecated
    public IRubyObject sysaccept() {
        return this.accept(this.getRuntime().getCurrentContext());
    }
    
    @JRubyMethod
    public IRubyObject sysaccept(final ThreadContext context) {
        return this.accept(context);
    }
    
    @Deprecated
    public IRubyObject listen(final IRubyObject log) {
        return this.listen(this.getRuntime().getCurrentContext(), log);
    }
    
    @JRubyMethod
    public IRubyObject listen(final ThreadContext context, final IRubyObject log) {
        if (RubyUNIXServer.INSTANCE.listen(this.fd, RubyNumeric.fix2int(log)) < 0) {
            RubyUNIXSocket.rb_sys_fail(context.getRuntime(), "listen(2)");
        }
        return context.getRuntime().newFixnum(0);
    }
    
    static {
        RubyUNIXServer.UNIXSERVER_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyUNIXServer(runtime, klass);
            }
        };
    }
}
