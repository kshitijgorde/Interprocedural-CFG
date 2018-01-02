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
import org.jruby.ext.NetProtocolBufferedIO$NativeImpl$s$0$0$rbuf_fill;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$NetProtocolBufferedIO$NativeImpl$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        final JavaMethod javaMethod = new NetProtocolBufferedIO$NativeImpl$s$0$0$rbuf_fill(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "rbuf_fill", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(NetProtocolBufferedIO.NativeImpl.class, "rbuf_fill", IRubyObject.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("rbuf_fill", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.NetProtocolBufferedIO.NativeImpl.rbuf_fill", "rbuf_fill");
    }
}
