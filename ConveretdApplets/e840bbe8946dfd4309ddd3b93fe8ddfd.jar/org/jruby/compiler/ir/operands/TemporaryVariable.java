// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.interpreter.InterpreterContext;

public class TemporaryVariable extends Variable
{
    final int offset;
    
    public TemporaryVariable(final int offset) {
        this.offset = offset;
    }
    
    public String getName() {
        return this.getPrefix() + this.offset;
    }
    
    public int compareTo(final Object other) {
        if (!(other instanceof TemporaryVariable)) {
            return 0;
        }
        final TemporaryVariable temporary = (TemporaryVariable)other;
        final int prefixCompare = this.getPrefix().compareTo(temporary.getPrefix());
        if (prefixCompare != 0) {
            return prefixCompare;
        }
        if (this.offset < temporary.offset) {
            return -1;
        }
        if (this.offset > temporary.offset) {
            return 1;
        }
        return 0;
    }
    
    public String getPrefix() {
        return "%v_";
    }
    
    public String toString() {
        return this.getPrefix() + this.offset;
    }
    
    public Object retrieve(final InterpreterContext interp) {
        return interp.getTemporaryVariable(this.offset);
    }
    
    public Object store(final InterpreterContext interp, final Object value) {
        return interp.setTemporaryVariable(this.offset, value);
    }
}
