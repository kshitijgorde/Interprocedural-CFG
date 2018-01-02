// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.JRubyApplet$RubyMethods$s$0$0$on_start;
import org.jruby.JRubyApplet$RubyMethods$s$0$0$on_stop;
import org.jruby.JRubyApplet$RubyMethods$s$0$0$on_destroy;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.JRubyApplet;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.JRubyApplet$RubyMethods$s$0$0$on_paint;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$JRubyApplet$RubyMethods$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new JRubyApplet$RubyMethods$s$0$0$on_paint(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "on_paint", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JRubyApplet.RubyMethods.class, "on_paint", IRubyObject.class, new Class[] { IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("on_paint", javaMethod);
        javaMethod = new JRubyApplet$RubyMethods$s$0$0$on_destroy(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "on_destroy", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JRubyApplet.RubyMethods.class, "on_destroy", IRubyObject.class, new Class[] { IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("on_destroy", javaMethod);
        javaMethod = new JRubyApplet$RubyMethods$s$0$0$on_stop(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "on_stop", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JRubyApplet.RubyMethods.class, "on_stop", IRubyObject.class, new Class[] { IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("on_stop", javaMethod);
        javaMethod = new JRubyApplet$RubyMethods$s$0$0$on_start(cls, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, 0, "on_start", true, CallConfiguration.FrameNoneScopeNone, false);
        javaMethod.setNativeCall(JRubyApplet.RubyMethods.class, "on_start", IRubyObject.class, new Class[] { IRubyObject.class, Block.class }, true);
        cls.addMethodAtBootTimeOnly("on_start", javaMethod);
        runtime.addBoundMethod("org.jruby.JRubyApplet.RubyMethods.on_paint", "on_paint");
        runtime.addBoundMethod("org.jruby.JRubyApplet.RubyMethods.on_destroy", "on_destroy");
        runtime.addBoundMethod("org.jruby.JRubyApplet.RubyMethods.on_stop", "on_stop");
        runtime.addBoundMethod("org.jruby.JRubyApplet.RubyMethods.on_start", "on_start");
    }
}
