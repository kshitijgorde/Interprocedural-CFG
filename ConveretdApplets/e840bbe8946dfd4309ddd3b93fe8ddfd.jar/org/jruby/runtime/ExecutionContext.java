// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.runtime.builtin.IRubyObject;
import java.util.Map;

public interface ExecutionContext
{
    Map<Object, IRubyObject> getContextVariables();
}
