// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.load;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;

public interface IAutoloadMethod
{
    String file();
    
    IRubyObject load(final Ruby p0, final String p1);
}
