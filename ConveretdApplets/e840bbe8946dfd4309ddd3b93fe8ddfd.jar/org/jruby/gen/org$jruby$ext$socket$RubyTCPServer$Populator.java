// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.ext.socket.RubyTCPServer$i$1$0$listen;
import org.jruby.ext.socket.RubyTCPServer$i$0$0$accept;
import org.jruby.ext.socket.RubyTCPServer$i$0$0$getpeername;
import org.jruby.ext.socket.RubyTCPServer$i$0$0$accept_nonblock;
import org.jruby.ext.socket.RubyTCPServer$i$0$0$peeraddr;
import org.jruby.ext.socket.RubyTCPServer$i$0$1$initialize;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.socket.RubyTCPServer;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.socket.RubyTCPServer$s$0$0$open;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$socket$RubyTCPServer$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyTCPServer$s$0$0$open(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "open", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyTCPServer.class, "open", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class }, true);
        singletonClass.addMethodAtBootTimeOnly("open", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.socket.RubyTCPServer.open", "open");
        javaMethod = new RubyTCPServer$i$0$1$initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, -1, "initialize", false, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(RubyTCPServer.class, "initialize", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new RubyTCPServer$i$0$0$peeraddr(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "peeraddr", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyTCPServer.class, "peeraddr", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("peeraddr", javaMethod);
        javaMethod = new RubyTCPServer$i$0$0$accept_nonblock(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "accept_nonblock", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyTCPServer.class, "accept_nonblock", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("accept_nonblock", javaMethod);
        javaMethod = new RubyTCPServer$i$0$0$getpeername(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "getpeername", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyTCPServer.class, "getpeername", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("getpeername", javaMethod);
        javaMethod = new RubyTCPServer$i$0$0$accept(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "accept", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyTCPServer.class, "accept", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("accept", javaMethod);
        javaMethod = new RubyTCPServer$i$1$0$listen(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "listen", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyTCPServer.class, "listen", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("listen", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.socket.RubyTCPServer.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyTCPServer.peeraddr", "peeraddr");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyTCPServer.accept_nonblock", "accept_nonblock");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyTCPServer.getpeername", "getpeername");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyTCPServer.accept", "accept");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyTCPServer.listen", "listen");
    }
}
