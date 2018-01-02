// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass$i$initialize19;
import org.jruby.RubyClass$i$initialize;
import org.jruby.CompatVersion;
import org.jruby.RubyClass$i$0$0$allocate;
import org.jruby.RubyClass$i$1$0$inherited;
import org.jruby.RubyClass$i$newInstance;
import org.jruby.RubyClass$i$1$0$initialize_copy;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyClass;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyClass$i$0$0$superclass;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyClass$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final CompatVersion compatVersion = cls.getRuntime().getInstanceConfig().getCompatVersion();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyClass$i$0$0$superclass(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "superclass", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyClass.class, "superclass", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("superclass", javaMethod);
        javaMethod = new RubyClass$i$1$0$initialize_copy(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "initialize_copy", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyClass.class, "initialize_copy", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("initialize_copy", javaMethod);
        javaMethod = new RubyClass$i$newInstance(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "newInstance", false, CallConfiguration.FrameNoneScopeNone, false);
        cls.addMethodAtBootTimeOnly("new", javaMethod);
        javaMethod = new RubyClass$i$1$0$inherited(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "inherited", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyClass.class, "inherited", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("inherited", javaMethod);
        javaMethod = new RubyClass$i$0$0$allocate(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "allocate", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyClass.class, "allocate", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("allocate", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyClass.superclass", "superclass");
        runtime.addBoundMethod("org.jruby.RubyClass.initialize_copy", "initialize_copy");
        runtime.addBoundMethod("org.jruby.RubyClass.inherited", "inherited");
        runtime.addBoundMethod("org.jruby.RubyClass.allocate", "allocate");
        if (compatVersion == CompatVersion.RUBY1_8 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyClass$i$initialize(cls, Visibility.PRIVATE);
            TypePopulator.populateMethod(javaMethod, -1, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
            cls.addMethodAtBootTimeOnly("initialize", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyClass.initialize", "initialize");
        }
        if (compatVersion == CompatVersion.RUBY1_9 || compatVersion == CompatVersion.BOTH) {
            javaMethod = new RubyClass$i$initialize19(cls, Visibility.PRIVATE);
            TypePopulator.populateMethod(javaMethod, -1, "initialize19", false, CallConfiguration.FrameNoneScopeNone, false);
            cls.addMethodAtBootTimeOnly("initialize", javaMethod);
            runtime.addBoundMethod("org.jruby.RubyClass.initialize19", "initialize");
        }
    }
}
