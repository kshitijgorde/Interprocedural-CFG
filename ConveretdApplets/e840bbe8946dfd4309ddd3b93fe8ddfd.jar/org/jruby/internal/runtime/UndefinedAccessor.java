// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime;

import org.jruby.common.IRubyWarnings;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;
import org.jruby.runtime.IAccessor;

public class UndefinedAccessor implements IAccessor
{
    private final Ruby runtime;
    private final GlobalVariable globalVariable;
    private final String name;
    
    public UndefinedAccessor(final Ruby runtime, final GlobalVariable globalVariable, final String name) {
        assert runtime != null;
        assert globalVariable != null;
        assert name != null;
        this.runtime = runtime;
        this.globalVariable = globalVariable;
        this.name = name;
    }
    
    public IRubyObject getValue() {
        if (this.runtime.isVerbose()) {
            this.runtime.getWarnings().warning(IRubyWarnings.ID.ACCESSOR_NOT_INITIALIZED, "global variable `" + this.name + "' not initialized", this.name);
        }
        return this.runtime.getNil();
    }
    
    public IRubyObject setValue(final IRubyObject newValue) {
        assert newValue != null;
        this.globalVariable.setAccessor(new ValueAccessor(newValue));
        return newValue;
    }
}
