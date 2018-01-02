// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.ext.socket.RubyUNIXServer$i$1$0$listen;
import org.jruby.ext.socket.RubyUNIXServer$i$0$0$accept;
import org.jruby.ext.socket.RubyUNIXServer$i$0$0$accept_nonblock;
import org.jruby.ext.socket.RubyUNIXServer$i$0$0$sysaccept;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.socket.RubyUNIXServer;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.socket.RubyUNIXServer$i$1$0$initialize;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$socket$RubyUNIXServer$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyUNIXServer$i$1$0$initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyUNIXServer.class, "initialize", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new RubyUNIXServer$i$0$0$sysaccept(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "sysaccept", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyUNIXServer.class, "sysaccept", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("sysaccept", javaMethod);
        javaMethod = new RubyUNIXServer$i$0$0$accept_nonblock(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "accept_nonblock", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyUNIXServer.class, "accept_nonblock", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("accept_nonblock", javaMethod);
        javaMethod = new RubyUNIXServer$i$0$0$accept(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "accept", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyUNIXServer.class, "accept", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("accept", javaMethod);
        javaMethod = new RubyUNIXServer$i$1$0$listen(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "listen", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyUNIXServer.class, "listen", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("listen", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.socket.RubyUNIXServer.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyUNIXServer.sysaccept", "sysaccept");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyUNIXServer.accept_nonblock", "accept_nonblock");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyUNIXServer.accept", "accept");
        runtime.addBoundMethod("org.jruby.ext.socket.RubyUNIXServer.listen", "listen");
    }
}
