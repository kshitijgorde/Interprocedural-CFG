// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.runtime.builtin.IRubyObject;

public interface CompiledBlockCallback19
{
    IRubyObject call(final ThreadContext p0, final IRubyObject p1, final IRubyObject[] p2, final Block p3);
    
    String getFile();
    
    int getLine();
}
