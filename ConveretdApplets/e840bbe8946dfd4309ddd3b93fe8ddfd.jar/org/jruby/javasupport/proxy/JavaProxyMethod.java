// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.jruby.javasupport.ParameterTypes;
import org.jruby.runtime.builtin.IRubyObject;

public interface JavaProxyMethod extends IRubyObject, ParameterTypes
{
    Method getSuperMethod();
    
    Object getState();
    
    void setState(final Object p0);
    
    boolean hasSuperImplementation();
    
    Object invoke(final Object p0, final Object[] p1) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;
    
    Object defaultResult();
    
    String getName();
    
    Class<?> getReturnType();
    
    JavaProxyClass getDeclaringClass();
    
    int getModifiers();
}
