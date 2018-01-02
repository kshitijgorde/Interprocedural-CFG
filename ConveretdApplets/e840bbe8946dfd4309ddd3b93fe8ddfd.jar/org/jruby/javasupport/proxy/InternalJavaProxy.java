// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport.proxy;

public interface InternalJavaProxy
{
    public static final Object[] NO_ARGS = new Object[0];
    
    JavaProxyClass ___getProxyClass();
    
    JavaProxyInvocationHandler ___getInvocationHandler();
}
