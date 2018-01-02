// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport.proxy;

public class InternalJavaProxyHelper
{
    public static JavaProxyClass initProxyClass(final Class proxy) {
        return new JavaProxyClass(proxy);
    }
    
    public static JavaProxyMethod initProxyMethod(final JavaProxyClass proxyClass, final String name, final String desc, final boolean hasSuper) {
        return proxyClass.initMethod(name, desc, hasSuper);
    }
}
