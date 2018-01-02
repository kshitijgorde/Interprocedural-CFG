// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyNameError$RubyNameErrorMessage$i$1$0$dump;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyNameError$RubyNameErrorMessage$i$0$0$to_str;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyNameError;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyNameError$RubyNameErrorMessage$s$1$0$load;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyNameError$RubyNameErrorMessage$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyNameError$RubyNameErrorMessage$s$1$0$load(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "load", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNameError.RubyNameErrorMessage.class, "load", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("_load", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyNameError.RubyNameErrorMessage.load", "_load");
        javaMethod = new RubyNameError$RubyNameErrorMessage$i$0$0$to_str(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "to_str", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNameError.RubyNameErrorMessage.class, "to_str", IRubyObject.class, new Class[] { ThreadContext.class }, false);
        cls.addMethodAtBootTimeOnly("to_str", javaMethod);
        javaMethod = new RubyNameError$RubyNameErrorMessage$i$1$0$dump(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "dump", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyNameError.RubyNameErrorMessage.class, "dump", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("_dump", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyNameError.RubyNameErrorMessage.to_str", "to_str");
        runtime.addBoundMethod("org.jruby.RubyNameError.RubyNameErrorMessage.dump", "_dump");
    }
}
