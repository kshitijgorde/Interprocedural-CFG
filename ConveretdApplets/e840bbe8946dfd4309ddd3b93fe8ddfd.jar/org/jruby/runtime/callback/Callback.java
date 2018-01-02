// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.callback;

import org.jruby.runtime.Arity;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;

public interface Callback
{
    IRubyObject execute(final IRubyObject p0, final IRubyObject[] p1, final Block p2);
    
    Arity getArity();
}
