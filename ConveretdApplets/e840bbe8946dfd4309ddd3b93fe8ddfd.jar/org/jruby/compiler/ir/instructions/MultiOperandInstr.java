// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.representations.InlinerInfo;
import java.util.Arrays;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.Operation;
import org.jruby.runtime.builtin.IRubyObject;

public abstract class MultiOperandInstr extends Instr
{
    private boolean constArgs;
    private IRubyObject[] preparedArgs;
    
    public MultiOperandInstr(final Operation opType, final Variable result) {
        super(opType, result);
        this.constArgs = false;
        this.preparedArgs = null;
    }
    
    public String toString() {
        return super.toString() + Arrays.toString(this.getOperands());
    }
    
    public Operand[] cloneOperandsForInlining(final InlinerInfo ii) {
        final Operand[] oldArgs = this.getOperands();
        final Operand[] newArgs = new Operand[oldArgs.length];
        for (int i = 0; i < oldArgs.length; ++i) {
            newArgs[i] = oldArgs[i].cloneForInlining(ii);
        }
        return newArgs;
    }
    
    protected IRubyObject[] prepareArguments(final Operand[] args, final InterpreterContext interp) {
        if (this.preparedArgs == null) {
            this.preparedArgs = new IRubyObject[args.length];
            this.constArgs = true;
            for (int i = 0; i < args.length; ++i) {
                if (!args[i].isConstant()) {
                    this.constArgs = false;
                    break;
                }
                this.preparedArgs[i] = (IRubyObject)args[i].retrieve(interp);
            }
        }
        if (!this.constArgs) {
            for (int i = 0; i < args.length; ++i) {
                this.preparedArgs[i] = (IRubyObject)args[i].retrieve(interp);
            }
        }
        return this.preparedArgs;
    }
}
