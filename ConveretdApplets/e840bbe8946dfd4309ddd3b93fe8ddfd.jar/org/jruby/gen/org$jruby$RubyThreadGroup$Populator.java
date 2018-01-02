// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyThreadGroup$i$0$0$enclosed_p;
import org.jruby.RubyThreadGroup$i$0$0$enclose;
import org.jruby.RubyThreadGroup$i$1$0$add;
import org.jruby.RubyThreadGroup$i$0$0$list;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyThreadGroup;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyThreadGroup$s$0$0$newInstance;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyThreadGroup$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyThreadGroup$s$0$0$newInstance(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "newInstance", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyThreadGroup.class, "newInstance", IRubyObject.class, new Class[] { IRubyObject.class, Block.class }, true);
        singletonClass.addMethodAtBootTimeOnly("new", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyThreadGroup.newInstance", "new");
        javaMethod = new RubyThreadGroup$i$0$0$list(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "list", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyThreadGroup.class, "list", IRubyObject.class, new Class[] { Block.class }, false);
        cls.addMethodAtBootTimeOnly("list", javaMethod);
        javaMethod = new RubyThreadGroup$i$1$0$add(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "add", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyThreadGroup.class, "add", IRubyObject.class, new Class[] { IRubyObject.class, Block.class }, false);
        cls.addMethodAtBootTimeOnly("add", javaMethod);
        javaMethod = new RubyThreadGroup$i$0$0$enclose(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "enclose", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyThreadGroup.class, "enclose", IRubyObject.class, new Class[] { Block.class }, false);
        cls.addMethodAtBootTimeOnly("enclose", javaMethod);
        javaMethod = new RubyThreadGroup$i$0$0$enclosed_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "enclosed_p", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyThreadGroup.class, "enclosed_p", IRubyObject.class, new Class[] { Block.class }, false);
        cls.addMethodAtBootTimeOnly("enclosed?", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyThreadGroup.list", "list");
        runtime.addBoundMethod("org.jruby.RubyThreadGroup.add", "add");
        runtime.addBoundMethod("org.jruby.RubyThreadGroup.enclose", "enclose");
        runtime.addBoundMethod("org.jruby.RubyThreadGroup.enclosed_p", "enclosed?");
    }
}
