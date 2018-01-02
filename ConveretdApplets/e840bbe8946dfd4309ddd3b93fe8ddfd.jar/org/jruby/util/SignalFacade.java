// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import org.jruby.runtime.BlockCallback;
import org.jruby.Ruby;
import org.jruby.runtime.builtin.IRubyObject;

public interface SignalFacade
{
    IRubyObject trap(final IRubyObject p0, final IRubyObject p1, final IRubyObject p2);
    
    IRubyObject trap(final Ruby p0, final BlockCallback p1, final String p2);
}
