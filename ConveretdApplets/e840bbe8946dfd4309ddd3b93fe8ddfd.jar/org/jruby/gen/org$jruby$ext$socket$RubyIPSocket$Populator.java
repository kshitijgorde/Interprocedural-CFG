// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.ext.socket.RubyIPSocket$i$0$0$addr;
import org.jruby.ext.socket.RubyIPSocket$i$0$1$recvfrom;
import org.jruby.ext.socket.RubyIPSocket$i$0$0$peeraddr;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.socket.RubyIPSocket;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.socket.RubyIPSocket$s$1$0$getaddress;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$socket$RubyIPSocket$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyIPSocket$s$1$0$getaddress(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "getaddress", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyIPSocket.class, "getaddress", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("getaddress", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.socket.RubyIPSocket.getaddress", "getaddress");
        javaMethod = new RubyIPSocket$i$0$0$peeraddr(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "peeraddr", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyIPSocket.class, "peeraddr", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("peeraddr", javaMethod);
        javaMethod = new RubyIPSocket$i$0$1$recvfrom(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "recvfrom", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyIPSocket.class, "recvfrom", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("recvfrom", javaMethod);
        javaMethod = new RubyIPSocket$i$0$0$addr(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "addr", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyIPSocket.class, "addr", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("addr", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.socket.RubyIPSocket.peeraddr", "peeraddr");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyIPSocket.recvfrom", "recvfrom");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyIPSocket.addr", "addr");
    }
}
