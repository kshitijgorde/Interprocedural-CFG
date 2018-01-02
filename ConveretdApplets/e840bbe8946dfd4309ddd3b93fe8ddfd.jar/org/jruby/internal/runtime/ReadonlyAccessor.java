// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.IAccessor;

public class ReadonlyAccessor implements IAccessor
{
    private String name;
    private IAccessor accessor;
    
    public ReadonlyAccessor(final String name, final IAccessor accessor) {
        assert name != null;
        assert accessor != null;
        this.name = name;
        this.accessor = accessor;
    }
    
    public IRubyObject getValue() {
        return this.accessor.getValue();
    }
    
    public IRubyObject setValue(final IRubyObject newValue) {
        assert newValue != null;
        throw newValue.getRuntime().newNameError(this.name + " is a read-only variable", this.name);
    }
}
