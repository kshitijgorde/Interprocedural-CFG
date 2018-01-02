// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.java.addons.IOJavaAddons$s$0$0$to_outputstream;
import org.jruby.java.addons.IOJavaAddons$s$0$0$to_inputstream;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.java.addons.IOJavaAddons;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.java.addons.IOJavaAddons$s$0$0$to_channel;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$java$addons$IOJavaAddons$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new IOJavaAddons$s$0$0$to_channel(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_channel", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(IOJavaAddons.class, "to_channel", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("to_channel", javaMethod);
        javaMethod = new IOJavaAddons$s$0$0$to_inputstream(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_inputstream", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(IOJavaAddons.class, "to_inputstream", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("to_inputstream", javaMethod);
        javaMethod = new IOJavaAddons$s$0$0$to_outputstream(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_outputstream", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(IOJavaAddons.class, "to_outputstream", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("to_outputstream", javaMethod);
        runtime.addBoundMethod("org.jruby.java.addons.IOJavaAddons.to_channel", "to_channel");
        runtime.addBoundMethod("org.jruby.java.addons.IOJavaAddons.to_inputstream", "to_inputstream");
        runtime.addBoundMethod("org.jruby.java.addons.IOJavaAddons.to_outputstream", "to_outputstream");
    }
}
