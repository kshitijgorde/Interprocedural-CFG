// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.ext.socket.RubyBasicSocket$i$1$0$set_do_not_reverse_lookup19;
import org.jruby.ext.socket.RubyBasicSocket$i$0$0$do_not_reverse_lookup19;
import org.jruby.CompatVersion;
import org.jruby.ext.socket.RubyBasicSocket$i$0$0$getsockname;
import org.jruby.ext.socket.RubyBasicSocket$i$0$1$shutdown;
import org.jruby.ext.socket.RubyBasicSocket$i$3$0$setsockopt;
import org.jruby.ext.socket.RubyBasicSocket$i$0$0$getpeername;
import org.jruby.ext.socket.RubyBasicSocket$i$0$0$recv;
import org.jruby.ext.socket.RubyBasicSocket$i$0$0$getsockname_u;
import org.jruby.ext.socket.RubyBasicSocket$i$2$0$getsockopt;
import org.jruby.runtime.ThreadContext;
import org.jruby.ext.socket.RubyBasicSocket$i$0$0$write_send;
import org.jruby.ext.socket.RubyBasicSocket$s$1$0$set_do_not_reverse_lookup;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.socket.RubyBasicSocket;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.socket.RubyBasicSocket$s$0$0$do_not_reverse_lookup;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$socket$RubyBasicSocket$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyBasicSocket$s$0$0$do_not_reverse_lookup(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "do_not_reverse_lookup", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBasicSocket.class, "do_not_reverse_lookup", IRubyObject.class, new Class[] { IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("do_not_reverse_lookup", javaMethod);
        javaMethod = new RubyBasicSocket$s$1$0$set_do_not_reverse_lookup(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "set_do_not_reverse_lookup", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBasicSocket.class, "set_do_not_reverse_lookup", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("do_not_reverse_lookup=", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.socket.RubyBasicSocket.do_not_reverse_lookup", "do_not_reverse_lookup");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyBasicSocket.set_do_not_reverse_lookup", "do_not_reverse_lookup=");
        javaMethod = new RubyBasicSocket$i$0$0$write_send(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "write_send", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBasicSocket.class, "write_send", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("send", javaMethod);
        javaMethod = new RubyBasicSocket$i$2$0$getsockopt(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "getsockopt", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBasicSocket.class, "getsockopt", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("getsockopt", javaMethod);
        javaMethod = new RubyBasicSocket$i$0$0$getsockname_u(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "getsockname_u", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBasicSocket.class, "getsockname_u", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("__getsockname", javaMethod);
        javaMethod = new RubyBasicSocket$i$0$0$recv(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "recv", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBasicSocket.class, "recv", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("recv", javaMethod);
        javaMethod = new RubyBasicSocket$i$0$0$getpeername(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "getpeername", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBasicSocket.class, "getpeername", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("getpeername", javaMethod);
        cls.addMethodAtBootTimeOnly("__getpeername", javaMethod);
        javaMethod = new RubyBasicSocket$i$3$0$setsockopt(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 3, "setsockopt", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBasicSocket.class, "setsockopt", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("setsockopt", javaMethod);
        javaMethod = new RubyBasicSocket$i$0$1$shutdown(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "shutdown", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBasicSocket.class, "shutdown", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("shutdown", javaMethod);
        javaMethod = new RubyBasicSocket$i$0$0$getsockname(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "getsockname", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyBasicSocket.class, "getsockname", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("getsockname", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.socket.RubyBasicSocket.write_send", "send");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyBasicSocket.getsockopt", "getsockopt");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyBasicSocket.getsockname_u", "__getsockname");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyBasicSocket.recv", "recv");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyBasicSocket.getpeername", "getpeername");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyBasicSocket.setsockopt", "setsockopt");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyBasicSocket.shutdown", "shutdown");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyBasicSocket.getsockname", "getsockname");
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyBasicSocket$i$0$0$do_not_reverse_lookup19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 0, "do_not_reverse_lookup19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBasicSocket.class, "do_not_reverse_lookup19", IRubyObject.class, new Class[] { ThreadContext.class }, false);
            cls.addMethodAtBootTimeOnly("do_not_reverse_lookup19", javaMethod);
            javaMethod = new RubyBasicSocket$i$1$0$set_do_not_reverse_lookup19(cls, Visibility.PUBLIC);
            TypePopulator.populateMethod(javaMethod, 1, "set_do_not_reverse_lookup19", false, CallConfiguration.FrameNoneScopeNone, false);
            javaMethod.setNativeCall(RubyBasicSocket.class, "set_do_not_reverse_lookup19", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
            cls.addMethodAtBootTimeOnly("do_not_reverse_lookup=", javaMethod);
            runtime.addBoundMethod("org.jruby.ext.socket.RubyBasicSocket.do_not_reverse_lookup19", "do_not_reverse_lookup19");
            runtime.addBoundMethod("org.jruby.ext.socket.RubyBasicSocket.set_do_not_reverse_lookup19", "do_not_reverse_lookup=");
        }
    }
}
