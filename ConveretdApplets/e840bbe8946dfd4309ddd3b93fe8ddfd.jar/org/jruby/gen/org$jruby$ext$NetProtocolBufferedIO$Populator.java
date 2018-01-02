// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.NetProtocolBufferedIO;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.NetProtocolBufferedIO$s$1$0$initialize;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$NetProtocolBufferedIO$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        final JavaMethod javaMethod = new NetProtocolBufferedIO$s$1$0$initialize(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "initialize", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(NetProtocolBufferedIO.class, "initialize", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.NetProtocolBufferedIO.initialize", "initialize");
    }
}
