// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyZlib$Deflate$i$1$0$set_dictionary;
import org.jruby.RubyZlib$Deflate$i$0$1$deflate;
import org.jruby.RubyZlib$Deflate$i$1$0$initialize_copy;
import org.jruby.RubyZlib$Deflate$i$0$1$flush;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyZlib$Deflate$i$2$0$params;
import org.jruby.RubyZlib$Deflate$i$1$0$append;
import org.jruby.RubyZlib$Deflate$i$0$4$_initialize;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyZlib;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyZlib$Deflate$s$0$1$s_deflate;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyZlib$Deflate$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyZlib$Deflate$s$0$1$s_deflate(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "s_deflate", true, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.Deflate.class, "s_deflate", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject[].class }, true);
        singletonClass.addMethodAtBootTimeOnly("deflate", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyZlib.Deflate.s_deflate", "deflate");
        javaMethod = new RubyZlib$Deflate$i$0$4$_initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, -1, "_initialize", false, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.Deflate.class, "_initialize", IRubyObject.class, new Class[] { IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new RubyZlib$Deflate$i$1$0$append(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "append", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.Deflate.class, "append", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("<<", javaMethod);
        javaMethod = new RubyZlib$Deflate$i$2$0$params(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "params", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.Deflate.class, "params", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("params", javaMethod);
        javaMethod = new RubyZlib$Deflate$i$0$1$flush(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "flush", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.Deflate.class, "flush", IRubyObject.class, new Class[] { IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("flush", javaMethod);
        javaMethod = new RubyZlib$Deflate$i$1$0$initialize_copy(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "initialize_copy", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.Deflate.class, "initialize_copy", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("initialize_copy", javaMethod);
        javaMethod = new RubyZlib$Deflate$i$0$1$deflate(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "deflate", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.Deflate.class, "deflate", IRubyObject.class, new Class[] { IRubyObject[].class }, false);
        cls.addMethodAtBootTimeOnly("deflate", javaMethod);
        javaMethod = new RubyZlib$Deflate$i$1$0$set_dictionary(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "set_dictionary", false, CallConfiguration.FrameBacktraceScopeNone, false);
        javaMethod.setNativeCall(RubyZlib.Deflate.class, "set_dictionary", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("set_dictionary", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyZlib.Deflate._initialize", "initialize");
        runtime.addBoundMethod("org.jruby.RubyZlib.Deflate.append", "<<");
        runtime.addBoundMethod("org.jruby.RubyZlib.Deflate.params", "params");
        runtime.addBoundMethod("org.jruby.RubyZlib.Deflate.flush", "flush");
        runtime.addBoundMethod("org.jruby.RubyZlib.Deflate.initialize_copy", "initialize_copy");
        runtime.addBoundMethod("org.jruby.RubyZlib.Deflate.deflate", "deflate");
        runtime.addBoundMethod("org.jruby.RubyZlib.Deflate.set_dictionary", "set_dictionary");
    }
}
