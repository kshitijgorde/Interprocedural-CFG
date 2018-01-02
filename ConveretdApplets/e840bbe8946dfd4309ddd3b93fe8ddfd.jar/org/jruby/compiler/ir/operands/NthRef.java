// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.runtime.ThreadContext;
import org.jruby.RubyRegexp;
import org.jruby.interpreter.InterpreterContext;

public class NthRef extends Operand
{
    public final int matchNumber;
    
    public NthRef(final int matchNumber) {
        this.matchNumber = matchNumber;
    }
    
    public String toString() {
        return "$" + this.matchNumber;
    }
    
    public Object retrieve(final InterpreterContext interp) {
        final ThreadContext context = interp.getContext();
        return RubyRegexp.nth_match(this.matchNumber, context.getCurrentScope().getBackRef(context.getRuntime()));
    }
}
