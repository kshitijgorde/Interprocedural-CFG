// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport.proxy;

import org.jruby.runtime.builtin.IRubyObject;

public interface JavaProxyInvocationHandler
{
    IRubyObject getOrig();
    
    Object invoke(final Object p0, final JavaProxyMethod p1, final Object[] p2) throws Throwable;
}
