// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.runtime.builtin.IRubyObject;

public abstract class CompiledBlockCallback
{
    public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final IRubyObject p2, final Block p3);
    
    public abstract String getFile();
    
    public abstract int getLine();
}
