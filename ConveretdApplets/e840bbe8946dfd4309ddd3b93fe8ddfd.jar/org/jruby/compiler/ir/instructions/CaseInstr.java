// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import java.util.Map;
import java.util.List;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.Label;

public class CaseInstr extends OneOperandInstr
{
    Label[] labels;
    Operand[] variables;
    Label endLabel;
    Label elseLabel;
    
    public CaseInstr(final Variable result, final Operand arg, final Label endLabel) {
        super(Operation.CASE, result, arg);
        this.endLabel = endLabel;
    }
    
    public void setLabels(final List<Label> labels) {
        this.labels = labels.toArray(new Label[labels.size()]);
    }
    
    public void setVariables(final List<Operand> variables) {
        this.variables = variables.toArray(new Operand[variables.size()]);
    }
    
    public void setElse(final Label elseLabel) {
        this.elseLabel = elseLabel;
    }
    
    public String toString() {
        return "\t" + this.result + " = CASE(" + this.argument + ", ELSE: " + this.elseLabel + ")";
    }
    
    public void simplifyOperands(final Map<Operand, Operand> valueMap) {
        super.simplifyOperands(valueMap);
        for (int i = 0; i < this.variables.length; ++i) {
            this.variables[i] = this.variables[i].getSimplifiedOperand(valueMap);
        }
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        throw new RuntimeException("Not implemented yet!");
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        return null;
    }
}
