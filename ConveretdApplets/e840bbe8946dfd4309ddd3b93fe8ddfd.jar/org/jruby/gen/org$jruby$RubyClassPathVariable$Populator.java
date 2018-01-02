// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClassPathVariable$i$0$0$to_s;
import org.jruby.RubyClassPathVariable$i$0$0$size;
import org.jruby.runtime.Block;
import org.jruby.RubyClassPathVariable$i$0$0$each;
import org.jruby.RubyClassPathVariable$i$1$0$append;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyClassPathVariable;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyClassPathVariable$i$0$0$inspect;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyClassPathVariable$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyClassPathVariable$i$0$0$inspect(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "inspect", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyClassPathVariable.class, "inspect", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("inspect", javaMethod);
        javaMethod = new RubyClassPathVariable$i$1$0$append(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "append", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyClassPathVariable.class, "append", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("append", javaMethod);
        cls.addMethodAtBootTimeOnly("<<", javaMethod);
        javaMethod = new RubyClassPathVariable$i$0$0$each(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "each", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyClassPathVariable.class, "each", IRubyObject.class, new Class[] { Block.class }, false);
        cls.addMethodAtBootTimeOnly("each", javaMethod);
        javaMethod = new RubyClassPathVariable$i$0$0$size(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "size", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyClassPathVariable.class, "size", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("size", javaMethod);
        cls.addMethodAtBootTimeOnly("length", javaMethod);
        javaMethod = new RubyClassPathVariable$i$0$0$to_s(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_s", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyClassPathVariable.class, "to_s", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyClassPathVariable.inspect", "inspect");
        runtime.addBoundMethod("org.jruby.RubyClassPathVariable.append", "append");
        runtime.addBoundMethod("org.jruby.RubyClassPathVariable.each", "each");
        runtime.addBoundMethod("org.jruby.RubyClassPathVariable.size", "size");
        runtime.addBoundMethod("org.jruby.RubyClassPathVariable.to_s", "to_s");
    }
}
