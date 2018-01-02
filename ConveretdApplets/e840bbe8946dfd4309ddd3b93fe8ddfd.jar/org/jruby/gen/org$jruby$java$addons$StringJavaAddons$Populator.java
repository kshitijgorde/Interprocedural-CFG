// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.java.addons.StringJavaAddons$s$0$0$to_java_bytes;
import org.jruby.java.addons.StringJavaAddons$s$1$0$from_java_bytes;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.java.addons.StringJavaAddons;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.java.addons.StringJavaAddons$s$0$0$to_java_string;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$java$addons$StringJavaAddons$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new StringJavaAddons$s$0$0$to_java_string(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_java_string", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StringJavaAddons.class, "to_java_string", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("to_java_string", javaMethod);
        javaMethod = new StringJavaAddons$s$1$0$from_java_bytes(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "from_java_bytes", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StringJavaAddons.class, "from_java_bytes", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("from_java_bytes", javaMethod);
        javaMethod = new StringJavaAddons$s$0$0$to_java_bytes(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_java_bytes", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(StringJavaAddons.class, "to_java_bytes", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("to_java_bytes", javaMethod);
        runtime.addBoundMethod("org.jruby.java.addons.StringJavaAddons.to_java_string", "to_java_string");
        runtime.addBoundMethod("org.jruby.java.addons.StringJavaAddons.from_java_bytes", "from_java_bytes");
        runtime.addBoundMethod("org.jruby.java.addons.StringJavaAddons.to_java_bytes", "to_java_bytes");
    }
}
