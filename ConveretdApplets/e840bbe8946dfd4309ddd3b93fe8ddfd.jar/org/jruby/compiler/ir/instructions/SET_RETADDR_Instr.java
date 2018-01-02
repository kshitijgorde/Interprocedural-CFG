// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.compiler.ir.operands.Variable;

public class SET_RETADDR_Instr extends OneOperandInstr
{
    public SET_RETADDR_Instr(final Variable d, final Label l) {
        super(Operation.SET_RETADDR, d, l);
    }
    
    public Label getReturnAddr() {
        return (Label)this.argument;
    }
    
    public String toString() {
        return "\t" + this.result + " = " + this.argument;
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new SET_RETADDR_Instr(ii.getRenamedVariable(this.result), ii.getRenamedLabel((Label)this.argument));
    }
    
    public boolean canRaiseException() {
        return false;
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        this.getResult().store(interp, this.getArg());
        return null;
    }
}
