// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.runtime.builtin.IRubyObject;

public interface IAccessor
{
    IRubyObject getValue();
    
    IRubyObject setValue(final IRubyObject p0);
}
