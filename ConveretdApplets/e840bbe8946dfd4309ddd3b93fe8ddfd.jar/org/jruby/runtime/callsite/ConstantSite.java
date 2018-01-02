// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.callsite;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;

public interface ConstantSite
{
    IRubyObject getValue(final ThreadContext p0);
    
    void invalidate();
}
