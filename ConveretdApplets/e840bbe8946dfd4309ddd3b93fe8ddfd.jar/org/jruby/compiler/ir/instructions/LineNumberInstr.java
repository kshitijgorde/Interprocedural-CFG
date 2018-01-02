// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.IRScope;

public class LineNumberInstr extends NoOperandInstr
{
    public final int lineNumber;
    public final IRScope scope;
    
    public LineNumberInstr(final IRScope scope, final int lineNumber) {
        super(Operation.LINE_NUM);
        this.scope = scope;
        this.lineNumber = lineNumber;
    }
    
    public String toString() {
        return super.toString() + "(" + this.lineNumber + ")";
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return this;
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        interp.getContext().setLine(this.lineNumber);
        return null;
    }
}
