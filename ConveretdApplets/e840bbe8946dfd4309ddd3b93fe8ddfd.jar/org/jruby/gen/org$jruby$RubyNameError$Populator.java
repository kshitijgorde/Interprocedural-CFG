// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyNameError$i$0$0$name;
import org.jruby.RubyNameError$i$0$0$to_s;
import org.jruby.runtime.Block;
import org.jruby.RubyNameError$i$0$2$initialize;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyException;
import org.jruby.RubyNameError;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyNameError$s$0$0$newRubyNameError;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyNameError$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyNameError$s$0$0$newRubyNameError(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "newRubyNameError", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNameError.class, "newRubyNameError", RubyException.class, new Class[] { IRubyObject.class, IRubyObject[].class }, true);
        singletonClass.addMethodAtBootTimeOnly("exception", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyNameError.newRubyNameError", "exception");
        javaMethod = new RubyNameError$i$0$2$initialize(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNameError.class, "initialize", IRubyObject.class, new Class[] { IRubyObject[].class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new RubyNameError$i$0$0$to_s(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_s", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNameError.class, "to_s", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        javaMethod = new RubyNameError$i$0$0$name(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "name", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNameError.class, "name", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("name", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyNameError.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.RubyNameError.to_s", "to_s");
        runtime.addBoundMethod("org.jruby.RubyNameError.name", "name");
    }
}
