// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.ext.socket.RubyUNIXSocket$i$0$0$path;
import org.jruby.ext.socket.RubyUNIXSocket$i$1$0$send_io;
import org.jruby.ext.socket.RubyUNIXSocket$i$0$0$addr;
import org.jruby.ext.socket.RubyUNIXSocket$i$0$1$recvfrom;
import org.jruby.ext.socket.RubyUNIXSocket$i$0$0$peeraddr;
import org.jruby.ext.socket.RubyUNIXSocket$i$1$0$initialize;
import org.jruby.ext.socket.RubyUNIXSocket$i$0$0$recv_io;
import org.jruby.ext.socket.RubyUNIXSocket$s$0$2$socketpair;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.socket.RubyUNIXSocket;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.socket.RubyUNIXSocket$s$1$0$open;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$socket$RubyUNIXSocket$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyUNIXSocket$s$1$0$open(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "open", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyUNIXSocket.class, "open", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("open", javaMethod);
        javaMethod = new RubyUNIXSocket$s$0$2$socketpair(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "socketpair", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyUNIXSocket.class, "socketpair", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class }, true);
        singletonClass.addMethodAtBootTimeOnly("socketpair", javaMethod);
        singletonClass.addMethodAtBootTimeOnly("pair", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.socket.RubyUNIXSocket.open", "open");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyUNIXSocket.socketpair", "socketpair");
        javaMethod = new RubyUNIXSocket$i$0$0$recv_io(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "recv_io", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyUNIXSocket.class, "recv_io", IRubyObject.class, new Class[] { IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("recv_io", javaMethod);
        javaMethod = new RubyUNIXSocket$i$1$0$initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyUNIXSocket.class, "initialize", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new RubyUNIXSocket$i$0$0$peeraddr(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "peeraddr", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyUNIXSocket.class, "peeraddr", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("peeraddr", javaMethod);
        javaMethod = new RubyUNIXSocket$i$0$1$recvfrom(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "recvfrom", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyUNIXSocket.class, "recvfrom", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("recvfrom", javaMethod);
        javaMethod = new RubyUNIXSocket$i$0$0$addr(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "addr", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyUNIXSocket.class, "addr", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("addr", javaMethod);
        javaMethod = new RubyUNIXSocket$i$1$0$send_io(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "send_io", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyUNIXSocket.class, "send_io", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("send_io", javaMethod);
        javaMethod = new RubyUNIXSocket$i$0$0$path(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "path", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyUNIXSocket.class, "path", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("path", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.socket.RubyUNIXSocket.recv_io", "recv_io");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyUNIXSocket.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyUNIXSocket.peeraddr", "peeraddr");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyUNIXSocket.recvfrom", "recvfrom");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyUNIXSocket.addr", "addr");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyUNIXSocket.send_io", "send_io");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyUNIXSocket.path", "path");
    }
}
