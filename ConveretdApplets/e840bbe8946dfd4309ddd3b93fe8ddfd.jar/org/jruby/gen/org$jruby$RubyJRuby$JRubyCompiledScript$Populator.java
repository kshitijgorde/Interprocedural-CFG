// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyJRuby$JRubyCompiledScript$s$0$0$compiled_script_to_s;
import org.jruby.RubyJRuby$JRubyCompiledScript$s$0$0$compiled_script_inspect_bytecode;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyJRuby;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyJRuby$JRubyCompiledScript$s$0$0$compiled_script_inspect;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$RubyJRuby$JRubyCompiledScript$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new RubyJRuby$JRubyCompiledScript$s$0$0$compiled_script_inspect(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "compiled_script_inspect", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.JRubyCompiledScript.class, "compiled_script_inspect", IRubyObject.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("inspect", javaMethod);
        javaMethod = new RubyJRuby$JRubyCompiledScript$s$0$0$compiled_script_inspect_bytecode(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "compiled_script_inspect_bytecode", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.JRubyCompiledScript.class, "compiled_script_inspect_bytecode", IRubyObject.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("inspect_bytecode", javaMethod);
        javaMethod = new RubyJRuby$JRubyCompiledScript$s$0$0$compiled_script_to_s(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "compiled_script_to_s", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(RubyJRuby.JRubyCompiledScript.class, "compiled_script_to_s", IRubyObject.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        runtime.addBoundMethod("org.jruby.RubyJRuby.JRubyCompiledScript.compiled_script_inspect", "inspect");
        runtime.addBoundMethod("org.jruby.RubyJRuby.JRubyCompiledScript.compiled_script_inspect_bytecode", "inspect_bytecode");
        runtime.addBoundMethod("org.jruby.RubyJRuby.JRubyCompiledScript.compiled_script_to_s", "to_s");
    }
}
