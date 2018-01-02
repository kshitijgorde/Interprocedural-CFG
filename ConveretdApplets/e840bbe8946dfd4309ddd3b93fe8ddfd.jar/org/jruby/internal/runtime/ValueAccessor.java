// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.IAccessor;

public class ValueAccessor implements IAccessor
{
    private IRubyObject value;
    
    public ValueAccessor(final IRubyObject value) {
        assert value != null;
        this.value = value;
    }
    
    public IRubyObject getValue() {
        return this.value;
    }
    
    public IRubyObject setValue(final IRubyObject newValue) {
        return this.value = newValue;
    }
}
