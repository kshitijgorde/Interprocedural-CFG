// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.runtime.builtin.IRubyObject;

public interface BlockCallback
{
    IRubyObject call(final ThreadContext p0, final IRubyObject[] p1, final Block p2);
}
