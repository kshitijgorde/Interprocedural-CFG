// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.ext.socket.RubyTCPSocket$i$0$2$initialize;
import org.jruby.ext.socket.RubyTCPSocket$s$1$0$gethostbyname;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.socket.RubyTCPSocket;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.socket.RubyTCPSocket$s$0$0$open;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$socket$RubyTCPSocket$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyTCPSocket$s$0$0$open(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "open", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyTCPSocket.class, "open", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject[].class, Block.class }, true);
        singletonClass.addMethodAtBootTimeOnly("open", javaMethod);
        javaMethod = new RubyTCPSocket$s$1$0$gethostbyname(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "gethostbyname", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyTCPSocket.class, "gethostbyname", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("gethostbyname", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.socket.RubyTCPSocket.open", "open");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyTCPSocket.gethostbyname", "gethostbyname");
        javaMethod = new RubyTCPSocket$i$0$2$initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, -1, "initialize", false, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(RubyTCPSocket.class, "initialize", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.socket.RubyTCPSocket.initialize", "initialize");
    }
}
