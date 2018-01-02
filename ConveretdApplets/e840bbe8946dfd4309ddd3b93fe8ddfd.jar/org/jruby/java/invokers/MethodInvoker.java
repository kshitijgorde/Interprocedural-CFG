// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.invokers;

import org.jruby.javasupport.JavaMethod;
import org.jruby.javasupport.JavaCallable;
import org.jruby.Ruby;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.List;
import org.jruby.RubyModule;

public abstract class MethodInvoker extends RubyToJavaInvoker
{
    MethodInvoker(final RubyModule host, final List<Method> methods) {
        super(host, methods.toArray(new Method[methods.size()]));
        RubyToJavaInvoker.trySetAccessible(this.getAccessibleObjects());
    }
    
    MethodInvoker(final RubyModule host, final Method method) {
        super(host, new Method[] { method });
        RubyToJavaInvoker.trySetAccessible(this.getAccessibleObjects());
    }
    
    protected JavaCallable createCallable(final Ruby ruby, final Member member) {
        return org.jruby.javasupport.JavaMethod.create(ruby, (Method)member);
    }
    
    protected JavaCallable[] createCallableArray(final JavaCallable callable) {
        return new org.jruby.javasupport.JavaMethod[] { (org.jruby.javasupport.JavaMethod)callable };
    }
    
    protected JavaCallable[] createCallableArray(final int size) {
        return new org.jruby.javasupport.JavaMethod[size];
    }
    
    protected JavaCallable[][] createCallableArrayArray(final int size) {
        return new org.jruby.javasupport.JavaMethod[size][];
    }
    
    protected Class[] getMemberParameterTypes(final Member member) {
        return ((Method)member).getParameterTypes();
    }
    
    protected boolean isMemberVarArgs(final Member member) {
        return ((Method)member).isVarArgs();
    }
}
