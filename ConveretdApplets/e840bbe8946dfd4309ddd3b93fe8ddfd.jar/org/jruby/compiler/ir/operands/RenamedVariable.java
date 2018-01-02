// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.interpreter.InterpreterContext;

public class RenamedVariable extends TemporaryVariable
{
    final String prefix;
    
    public RenamedVariable(final String prefix, final int offset) {
        super(offset);
        this.prefix = prefix;
    }
    
    public String getPrefix() {
        return this.prefix + "_";
    }
    
    public Object retrieve(final InterpreterContext interp) {
        return interp.getRenamedVariable(this.offset);
    }
    
    public Object store(final InterpreterContext interp, final Object value) {
        return interp.setRenamedVariable(this.offset, value);
    }
}
