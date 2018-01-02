// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.ext.socket.RubyUDPSocket$i$2$0$connect;
import org.jruby.ext.socket.RubyUDPSocket$i$0$0$recvfrom;
import org.jruby.ext.socket.RubyUDPSocket$i$2$0$bind;
import org.jruby.ext.socket.RubyUDPSocket$i$initialize;
import org.jruby.ext.socket.RubyUDPSocket$i$0$0$send;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.socket.RubyUDPSocket;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.socket.RubyUDPSocket$s$0$0$open;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$socket$RubyUDPSocket$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyUDPSocket$s$0$0$open(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "open", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyUDPSocket.class, "open", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class }, true);
        singletonClass.addMethodAtBootTimeOnly("open", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.socket.RubyUDPSocket.open", "open");
        javaMethod = new RubyUDPSocket$i$0$0$send(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "send", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyUDPSocket.class, "send", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("send", javaMethod);
        javaMethod = new RubyUDPSocket$i$initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, -1, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new RubyUDPSocket$i$2$0$bind(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "bind", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyUDPSocket.class, "bind", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("bind", javaMethod);
        javaMethod = new RubyUDPSocket$i$0$0$recvfrom(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "recvfrom", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyUDPSocket.class, "recvfrom", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("recvfrom", javaMethod);
        javaMethod = new RubyUDPSocket$i$2$0$connect(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "connect", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyUDPSocket.class, "connect", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("connect", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.socket.RubyUDPSocket.send", "send");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyUDPSocket.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyUDPSocket.bind", "bind");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyUDPSocket.recvfrom", "recvfrom");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyUDPSocket.connect", "connect");
    }
}
