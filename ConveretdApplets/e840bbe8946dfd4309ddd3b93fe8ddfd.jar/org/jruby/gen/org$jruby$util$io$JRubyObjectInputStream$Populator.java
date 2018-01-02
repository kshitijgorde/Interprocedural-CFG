// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.util.io.JRubyObjectInputStream$i$0$0$close;
import org.jruby.util.io.JRubyObjectInputStream$i$0$0$readObject;
import org.jruby.util.io.JRubyObjectInputStream$i$1$0$initialize;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.util.io.JRubyObjectInputStream;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.util.io.JRubyObjectInputStream$s$0$0$newInstance;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$util$io$JRubyObjectInputStream$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new JRubyObjectInputStream$s$0$0$newInstance(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "newInstance", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JRubyObjectInputStream.class, "newInstance", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject[].class, Block.class }, true);
        singletonClass.addMethodAtBootTimeOnly("new", javaMethod);
        runtime.addBoundMethod("org.jruby.util.io.JRubyObjectInputStream.newInstance", "new");
        javaMethod = new JRubyObjectInputStream$i$1$0$initialize(cls, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, 1, "initialize", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JRubyObjectInputStream.class, "initialize", IRubyObject.class, new Class[] { IRubyObject.class }, false);
        cls.addMethodAtBootTimeOnly("initialize", javaMethod);
        javaMethod = new JRubyObjectInputStream$i$0$0$readObject(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "readObject", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JRubyObjectInputStream.class, "readObject", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("read_object", javaMethod);
        cls.defineAlias("readObject", "read_object");
        javaMethod = new JRubyObjectInputStream$i$0$0$close(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "close", false, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JRubyObjectInputStream.class, "close", IRubyObject.class, new Class[0], false);
        cls.addMethodAtBootTimeOnly("close", javaMethod);
        runtime.addBoundMethod("org.jruby.util.io.JRubyObjectInputStream.initialize", "initialize");
        runtime.addBoundMethod("org.jruby.util.io.JRubyObjectInputStream.readObject", "read_object");
        runtime.addBoundMethod("org.jruby.util.io.JRubyObjectInputStream.close", "close");
    }
}
