// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.runtime.builtin.IRubyObject;

public abstract class CallSite
{
    public final String methodName;
    protected final CallType callType;
    
    public CallSite(final String methodName, final CallType callType) {
        this.methodName = methodName;
        this.callType = callType;
    }
    
    public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final IRubyObject p2, final long p3);
    
    public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final IRubyObject p2, final double p3);
    
    public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final IRubyObject p2);
    
    public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final IRubyObject p2, final IRubyObject p3);
    
    public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4);
    
    public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final IRubyObject p5);
    
    public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final IRubyObject p2, final IRubyObject... p3);
    
    public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final IRubyObject p2, final Block p3);
    
    public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final IRubyObject p2, final IRubyObject p3, final Block p4);
    
    public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5);
    
    public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final IRubyObject p5, final Block p6);
    
    public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4);
    
    public abstract IRubyObject callIter(final ThreadContext p0, final IRubyObject p1, final IRubyObject p2, final Block p3);
    
    public abstract IRubyObject callIter(final ThreadContext p0, final IRubyObject p1, final IRubyObject p2, final IRubyObject p3, final Block p4);
    
    public abstract IRubyObject callIter(final ThreadContext p0, final IRubyObject p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5);
    
    public abstract IRubyObject callIter(final ThreadContext p0, final IRubyObject p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final IRubyObject p5, final Block p6);
    
    public abstract IRubyObject callIter(final ThreadContext p0, final IRubyObject p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4);
}
