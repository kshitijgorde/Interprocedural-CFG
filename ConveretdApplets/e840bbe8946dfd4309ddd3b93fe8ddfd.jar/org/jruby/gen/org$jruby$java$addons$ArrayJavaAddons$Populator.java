// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.java.addons.ArrayJavaAddons$s$dimensions;
import org.jruby.java.addons.ArrayJavaAddons$s$2$0$copy_data;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.java.addons.ArrayJavaAddons;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.java.addons.ArrayJavaAddons$s$1$0$copy_data_simple;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$java$addons$ArrayJavaAddons$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new ArrayJavaAddons$s$1$0$copy_data_simple(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "copy_data_simple", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ArrayJavaAddons.class, "copy_data_simple", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("copy_data_simple", javaMethod);
        javaMethod = new ArrayJavaAddons$s$2$0$copy_data(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "copy_data", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(ArrayJavaAddons.class, "copy_data", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("copy_data", javaMethod);
        javaMethod = new ArrayJavaAddons$s$dimensions(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "dimensions", true, CallConfiguration.FrameNoneScopeNone, false);
        cls.addMethodAtBootTimeOnly("dimensions", javaMethod);
        runtime.addBoundMethod("org.jruby.java.addons.ArrayJavaAddons.copy_data_simple", "copy_data_simple");
        runtime.addBoundMethod("org.jruby.java.addons.ArrayJavaAddons.copy_data", "copy_data");
        runtime.addBoundMethod("org.jruby.java.addons.ArrayJavaAddons.dimensions", "dimensions");
    }
}
