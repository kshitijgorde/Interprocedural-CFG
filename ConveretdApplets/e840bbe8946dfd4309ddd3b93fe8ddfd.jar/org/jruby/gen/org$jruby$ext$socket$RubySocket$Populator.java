// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.ext.socket.RubySocket$i$1$0$listen;
import org.jruby.ext.socket.RubySocket$i$1$0$connect;
import org.jruby.ext.socket.RubySocket$i$1$0$bind;
import org.jruby.ext.socket.RubySocket$i$1$0$connect_nonblock;
import org.jruby.ext.socket.RubySocket$i$3$0$initialize;
import org.jruby.ext.socket.RubySocket$s$0$0$gethostname;
import org.jruby.ext.socket.RubySocket$s$1$0$gethostbyname;
import org.jruby.ext.socket.RubySocket$s$1$0$unpack_sockaddr_in;
import org.jruby.ext.socket.RubySocket$s$0$1$getnameinfo;
import org.jruby.ext.socket.RubySocket$s$1$0$for_fd;
import org.jruby.ext.socket.RubySocket$s$0$1$getservbyname;
import org.jruby.ext.socket.RubySocket$s$1$0$pack_sockaddr_un;
import org.jruby.ext.socket.RubySocket$s$0$4$getaddrinfo;
import org.jruby.ext.socket.RubySocket$s$0$0$gethostbyaddr;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.socket.RubySocket;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.socket.RubySocket$s$2$0$pack_sockaddr_in;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$socket$RubySocket$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubySocket$s$2$0$pack_sockaddr_in(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "pack_sockaddr_in", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubySocket.class, "pack_sockaddr_in", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("pack_sockaddr_in", javaMethod);
        singletonClass.addMethodAtBootTimeOnly("sockaddr_in", javaMethod);
        javaMethod = new RubySocket$s$0$0$gethostbyaddr(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "gethostbyaddr", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubySocket.class, "gethostbyaddr", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class }, true);
        singletonClass.addMethodAtBootTimeOnly("gethostbyaddr", javaMethod);
        javaMethod = new RubySocket$s$0$4$getaddrinfo(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "getaddrinfo", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubySocket.class, "getaddrinfo", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class }, true);
        singletonClass.addMethodAtBootTimeOnly("getaddrinfo", javaMethod);
        javaMethod = new RubySocket$s$1$0$pack_sockaddr_un(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "pack_sockaddr_un", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubySocket.class, "pack_sockaddr_un", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("pack_sockaddr_un", javaMethod);
        singletonClass.addMethodAtBootTimeOnly("sockaddr_un", javaMethod);
        javaMethod = new RubySocket$s$0$1$getservbyname(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "getservbyname", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubySocket.class, "getservbyname", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class }, true);
        singletonClass.addMethodAtBootTimeOnly("getservbyname", javaMethod);
        javaMethod = new RubySocket$s$1$0$for_fd(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "for_fd", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubySocket.class, "for_fd", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("for_fd", javaMethod);
        javaMethod = new RubySocket$s$0$1$getnameinfo(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "getnameinfo", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubySocket.class, "getnameinfo", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class }, true);
        singletonClass.addMethodAtBootTimeOnly("getnameinfo", javaMethod);
        javaMethod = new RubySocket$s$1$0$unpack_sockaddr_in(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "unpack_sockaddr_in", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubySocket.class, "unpack_sockaddr_in", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("unpack_sockaddr_in", javaMethod);
        javaMethod = new RubySocket$s$1$0$gethostbyname(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "gethostbyname", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubySocket.class, "gethostbyname", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("gethostbyname", javaMethod);
        javaMethod = new RubySocket$s$0$0$gethostname(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "gethostname", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubySocket.class, "gethostname", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("gethostname", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.socket.RubySocket.pack_sockaddr_in", "pack_sockaddr_in");
        runtime.addBoundMethod("org.jruby.ext.socket.RubySocket.gethostbyaddr", "gethostbyaddr");
        runtime.addBoundMethod("org.jruby.ext.socket.RubySocket.getaddrinfo", "getaddrinfo");
        runtime.addBoundMethod("org.jruby.ext.socket.RubySocket.pack_sockaddr_un", "pack_sockaddr_un");
        runtime.addBoundMethod("org.jruby.ext.socket.RubySocket.getservbyname", "getservbyname");
        runtime.addBoundMethod("org.jruby.ext.socket.RubySocket.for_fd", "for_fd");
        runtime.addBoundMethod("org.jruby.ext.socket.RubySocket.getnameinfo", "getnameinfo");
        runtime.addBoundMethod("org.jruby.ext.socket.RubySocket.unpack_sockaddr_in", "unpack_sockaddr_in");
        runtime.addBoundMethod("org.jruby.ext.socket.RubySocket.gethostbyname", "gethostbyname");
        runtime.addBoundMethod("org.jruby.ext.socket.RubySocket.gethostname", "gethostname");
        javaMethod = new RubySocket$i$3$0$initialize(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 3, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubySocket.class, "initialize", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new RubySocket$i$1$0$connect_nonblock(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "connect_nonblock", false, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(RubySocket.class, "connect_nonblock", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("connect_nonblock", javaMethod);
        javaMethod = new RubySocket$i$1$0$bind(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "bind", false, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(RubySocket.class, "bind", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("bind", javaMethod);
        javaMethod = new RubySocket$i$1$0$connect(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "connect", false, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(RubySocket.class, "connect", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("connect", javaMethod);
        javaMethod = new RubySocket$i$1$0$listen(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "listen", false, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(RubySocket.class, "listen", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("listen", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.socket.RubySocket.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.ext.socket.RubySocket.connect_nonblock", "connect_nonblock");
        runtime.addBoundMethod("org.jruby.ext.socket.RubySocket.bind", "bind");
        runtime.addBoundMethod("org.jruby.ext.socket.RubySocket.connect", "connect");
        runtime.addBoundMethod("org.jruby.ext.socket.RubySocket.listen", "listen");
    }
}
