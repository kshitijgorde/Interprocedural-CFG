// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.ext.Readline$HistoryMethods$s$2$0$s_hist_set;
import org.jruby.ext.Readline$HistoryMethods$s$0$0$s_hist_to_s;
import org.jruby.ext.Readline$HistoryMethods$s$0$0$s_pop;
import org.jruby.ext.Readline$HistoryMethods$s$1$0$s_hist_get;
import org.jruby.ext.Readline$HistoryMethods$s$0$0$s_hist_to_a;
import org.jruby.ext.Readline$HistoryMethods$s$0$0$s_hist_length;
import org.jruby.ext.Readline$HistoryMethods$s$1$0$s_hist_delete_at;
import org.jruby.ext.Readline$HistoryMethods$s$0$0$s_push;
import org.jruby.ext.Readline$HistoryMethods$s$0$0$s_hist_empty_p;
import org.jruby.ext.Readline$HistoryMethods$s$0$0$s_hist_shift;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ext.Readline;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.ext.Readline$HistoryMethods$s$0$0$s_hist_each;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$ext$Readline$HistoryMethods$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new Readline$HistoryMethods$s$0$0$s_hist_each(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "s_hist_each", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Readline.HistoryMethods.class, "s_hist_each", IRubyObject.class, new Class[] { IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("each", javaMethod);
        javaMethod = new Readline$HistoryMethods$s$0$0$s_hist_shift(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "s_hist_shift", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Readline.HistoryMethods.class, "s_hist_shift", IRubyObject.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("shift", javaMethod);
        javaMethod = new Readline$HistoryMethods$s$0$0$s_hist_empty_p(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "s_hist_empty_p", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Readline.HistoryMethods.class, "s_hist_empty_p", IRubyObject.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("empty?", javaMethod);
        javaMethod = new Readline$HistoryMethods$s$0$0$s_push(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "s_push", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Readline.HistoryMethods.class, "s_push", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject[].class }, true);
        cls.addMethodAtBootTimeOnly("push", javaMethod);
        cls.addMethodAtBootTimeOnly("<<", javaMethod);
        javaMethod = new Readline$HistoryMethods$s$1$0$s_hist_delete_at(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "s_hist_delete_at", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Readline.HistoryMethods.class, "s_hist_delete_at", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("delete_at", javaMethod);
        javaMethod = new Readline$HistoryMethods$s$0$0$s_hist_length(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "s_hist_length", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Readline.HistoryMethods.class, "s_hist_length", IRubyObject.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("length", javaMethod);
        cls.addMethodAtBootTimeOnly("size", javaMethod);
        javaMethod = new Readline$HistoryMethods$s$0$0$s_hist_to_a(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "s_hist_to_a", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Readline.HistoryMethods.class, "s_hist_to_a", IRubyObject.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("to_a", javaMethod);
        javaMethod = new Readline$HistoryMethods$s$1$0$s_hist_get(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 1, "s_hist_get", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Readline.HistoryMethods.class, "s_hist_get", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("[]", javaMethod);
        javaMethod = new Readline$HistoryMethods$s$0$0$s_pop(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "s_pop", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Readline.HistoryMethods.class, "s_pop", IRubyObject.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("pop", javaMethod);
        javaMethod = new Readline$HistoryMethods$s$0$0$s_hist_to_s(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "s_hist_to_s", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Readline.HistoryMethods.class, "s_hist_to_s", IRubyObject.class, new Class[] { IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("to_s", javaMethod);
        javaMethod = new Readline$HistoryMethods$s$2$0$s_hist_set(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 2, "s_hist_set", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(Readline.HistoryMethods.class, "s_hist_set", IRubyObject.class, new Class[] { IRubyObject.class, IRubyObject.class, IRubyObject.class }, true);
        cls.addMethodAtBootTimeOnly("[]=", javaMethod);
        runtime.addBoundMethod("org.jruby.ext.Readline.HistoryMethods.s_hist_each", "each");
        runtime.addBoundMethod("org.jruby.ext.Readline.HistoryMethods.s_hist_shift", "shift");
        runtime.addBoundMethod("org.jruby.ext.Readline.HistoryMethods.s_hist_empty_p", "empty?");
        runtime.addBoundMethod("org.jruby.ext.Readline.HistoryMethods.s_push", "push");
        runtime.addBoundMethod("org.jruby.ext.Readline.HistoryMethods.s_hist_delete_at", "delete_at");
        runtime.addBoundMethod("org.jruby.ext.Readline.HistoryMethods.s_hist_length", "length");
        runtime.addBoundMethod("org.jruby.ext.Readline.HistoryMethods.s_hist_to_a", "to_a");
        runtime.addBoundMethod("org.jruby.ext.Readline.HistoryMethods.s_hist_get", "[]");
        runtime.addBoundMethod("org.jruby.ext.Readline.HistoryMethods.s_pop", "pop");
        runtime.addBoundMethod("org.jruby.ext.Readline.HistoryMethods.s_hist_to_s", "to_s");
        runtime.addBoundMethod("org.jruby.ext.Readline.HistoryMethods.s_hist_set", "[]=");
    }
}
