// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;

public class ReadonlyGlobalVariable extends GlobalVariable
{
    public ReadonlyGlobalVariable(final Ruby runtime, final String name, final IRubyObject value) {
        super(runtime, name, value);
    }
    
    public IRubyObject set(final IRubyObject value) {
        throw this.runtime.newNameError(this.name() + " is a read-only variable", this.name());
    }
}
