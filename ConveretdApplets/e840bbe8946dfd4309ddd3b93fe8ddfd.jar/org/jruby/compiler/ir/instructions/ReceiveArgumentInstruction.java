// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.Interp;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Variable;

public class ReceiveArgumentInstruction extends NoOperandInstr
{
    int argIndex;
    boolean restOfArgArray;
    
    public ReceiveArgumentInstruction(final Variable destination, final int argIndex, final boolean restOfArgArray) {
        super(Operation.RECV_ARG, destination);
        this.argIndex = argIndex;
        this.restOfArgArray = restOfArgArray;
    }
    
    public ReceiveArgumentInstruction(final Variable destination, final int index) {
        this(destination, index, false);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new CopyInstr(ii.getRenamedVariable(this.result), ii.getCallArg(this.argIndex, this.restOfArgArray));
    }
    
    public String toString() {
        return super.toString() + "(" + this.argIndex + (this.restOfArgArray ? ", ALL" : "") + ")";
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        final Operand destination = this.getResult();
        if (this.restOfArgArray) {
            this.interpretAsRestArg(destination, interp);
        }
        else {
            destination.store(interp, interp.getParameter(this.argIndex));
        }
        return null;
    }
    
    @Interp
    private void interpretAsRestArg(final Operand destination, final InterpreterContext interp) {
        destination.store(interp, interp.getRuntime().newArray(interp.getParametersFrom(this.argIndex)));
    }
}
