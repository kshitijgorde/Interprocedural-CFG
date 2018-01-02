// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.ext.rubinius.RubyTuple$i$2$0$op_aset;
import org.jruby.ext.rubinius.RubyTuple$i$1$0$op_aref;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.rubinius.RubyTuple;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.rubinius.RubyTuple$s$1$0$rbNew;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$rubinius$RubyTuple$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyTuple$s$1$0$rbNew(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "rbNew", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyTuple.class, "rbNew", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, true);
        singletonClass.addMethodAtBootTimeOnly("new", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.rubinius.RubyTuple.rbNew", "new");
        javaMethod = new RubyTuple$i$1$0$op_aref(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "op_aref", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyTuple.class, "op_aref", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("[]", javaMethod);
        javaMethod = new RubyTuple$i$2$0$op_aset(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "op_aset", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyTuple.class, "op_aset", IRubyObject.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("[]=", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.rubinius.RubyTuple.op_aref", "[]");
        runtime.addBoundMethod("org.jruby.ext.rubinius.RubyTuple.op_aset", "[]=");
    }
}
