// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.javasupport.JavaArrayUtilities$s$1$0$ruby_string_to_bytes;
import org.jruby.runtime.ThreadContext;
import org.jruby.javasupport.JavaArrayUtilities$s$1$0$java_to_ruby;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.JavaArrayUtilities;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.javasupport.JavaArrayUtilities$s$1$0$bytes_to_ruby_string;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$javasupport$JavaArrayUtilities$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new JavaArrayUtilities$s$1$0$bytes_to_ruby_string(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "bytes_to_ruby_string", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaArrayUtilities.class, "bytes_to_ruby_string", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("bytes_to_ruby_string", javaMethod);
        DynamicMethod moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("bytes_to_ruby_string", moduleMethod);
        javaMethod = new JavaArrayUtilities$s$1$0$java_to_ruby(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "java_to_ruby", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaArrayUtilities.class, "java_to_ruby", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("java_to_ruby", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("java_to_ruby", moduleMethod);
        javaMethod = new JavaArrayUtilities$s$1$0$ruby_string_to_bytes(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "ruby_string_to_bytes", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JavaArrayUtilities.class, "ruby_string_to_bytes", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("ruby_string_to_bytes", javaMethod);
        moduleMethod = TypePopulator.populateModuleMethod(cls, javaMethod);
        singletonClass.addMethodAtBootTimeOnly("ruby_string_to_bytes", moduleMethod);
        runtime.addBoundMethod("org.jruby.javasupport.JavaArrayUtilities.bytes_to_ruby_string", "bytes_to_ruby_string");
        runtime.addBoundMethod("org.jruby.javasupport.JavaArrayUtilities.java_to_ruby", "java_to_ruby");
        runtime.addBoundMethod("org.jruby.javasupport.JavaArrayUtilities.ruby_string_to_bytes", "ruby_string_to_bytes");
    }
}
