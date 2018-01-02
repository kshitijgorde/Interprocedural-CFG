// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.callsite;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.CallType;

public class NormalCachingCallSite extends CachingCallSite
{
    public NormalCachingCallSite(final String methodName) {
        super(methodName, CallType.NORMAL);
        ++NormalCachingCallSite.totalCallSites;
    }
    
    protected boolean methodMissing(final DynamicMethod method, final IRubyObject caller) {
        return method.isUndefined() || (!this.methodName.equals("method_missing") && !method.isCallableFrom(caller, this.callType));
    }
}
