// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.gen;

import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.javasupport.Java$JavaProxyClassMethods$s$java_alias;
import org.jruby.javasupport.Java$JavaProxyClassMethods$s$java_method;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.javasupport.Java$JavaProxyClassMethods$s$java_send;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.anno.TypePopulator;

public class org$jruby$javasupport$Java$JavaProxyClassMethods$Populator extends TypePopulator
{
    public void populate(final RubyModule cls, final Class clazz) {
        final RubyClass singletonClass = cls.getSingletonClass();
        final Ruby runtime = cls.getRuntime();
        JavaMethod javaMethod = new Java$JavaProxyClassMethods$s$java_send(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "java_send", true, CallConfiguration.FrameBacktraceScopeNone, false);
        singletonClass.addMethodAtBootTimeOnly("java_send", javaMethod);
        javaMethod = new Java$JavaProxyClassMethods$s$java_method(singletonClass, Visibility.PUBLIC);
        TypePopulator.populateMethod(javaMethod, -1, "java_method", true, CallConfiguration.FrameBacktraceScopeNone, false);
        singletonClass.addMethodAtBootTimeOnly("java_method", javaMethod);
        javaMethod = new Java$JavaProxyClassMethods$s$java_alias(singletonClass, Visibility.PRIVATE);
        TypePopulator.populateMethod(javaMethod, -1, "java_alias", true, CallConfiguration.FrameBacktraceScopeNone, false);
        singletonClass.addMethodAtBootTimeOnly("java_alias", javaMethod);
        runtime.addBoundMethod("org.jruby.javasupport.Java.JavaProxyClassMethods.java_send", "java_send");
        runtime.addBoundMethod("org.jruby.javasupport.Java.JavaProxyClassMethods.java_method", "java_method");
        runtime.addBoundMethod("org.jruby.javasupport.Java.JavaProxyClassMethods.java_alias", "java_alias");
    }
}
