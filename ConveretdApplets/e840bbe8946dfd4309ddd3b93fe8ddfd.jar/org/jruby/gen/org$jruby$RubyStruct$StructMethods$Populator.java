// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyStruct$StructMethods$s$newStruct;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyStruct;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyStruct$StructMethods$s$0$0$members;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyStruct$StructMethods$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyStruct$StructMethods$s$0$0$members(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "members", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyStruct.StructMethods.class, "members", IRubyObject.class, new Class[] { IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("members", javaMethod);
        javaMethod = new RubyStruct$StructMethods$s$newStruct(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "newStruct", true, CallConfiguration.FrameNoneScopeNone, false);
        cls.addMethodAtBootTimeOnly("new", javaMethod);
        cls.addMethodAtBootTimeOnly("[]", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyStruct.StructMethods.members", "members");
        runtime.addBoundMethod("org.jruby.RubyStruct.StructMethods.newStruct", "new");
    }
}
