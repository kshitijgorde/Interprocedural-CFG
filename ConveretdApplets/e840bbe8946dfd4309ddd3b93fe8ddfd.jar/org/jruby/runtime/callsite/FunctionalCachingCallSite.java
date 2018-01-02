// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.callsite;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.CallType;

public class FunctionalCachingCallSite extends CachingCallSite
{
    public FunctionalCachingCallSite(final String methodName) {
        super(methodName, CallType.FUNCTIONAL);
        ++FunctionalCachingCallSite.totalCallSites;
    }
    
    protected boolean methodMissing(final DynamicMethod method, final IRubyObject caller) {
        return method.isUndefined();
    }
}
