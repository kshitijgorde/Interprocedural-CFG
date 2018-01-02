// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import java.util.Map;
import org.jruby.compiler.ir.representations.InlinerInfo;
import java.util.ArrayList;
import java.util.List;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.Interp;
import org.jruby.compiler.ir.operands.Attribute;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.Operation;

public abstract class Instr
{
    public final Operation operation;
    public final Variable result;
    private Attribute[] attributes;
    private boolean isDead;
    
    public Instr(final Operation operation) {
        this.operation = operation;
        this.result = null;
    }
    
    public Instr(final Operation operation, final Variable result) {
        this.operation = operation;
        this.result = result;
    }
    
    public String toString() {
        return "\t" + (this.isDead() ? "[DEAD]" : "") + ((this.result == null) ? "" : (this.result + " = ")) + this.operation;
    }
    
    @Interp
    public Variable getResult() {
        return this.result;
    }
    
    @Interp
    public Operation getOperation() {
        return this.operation;
    }
    
    public boolean hasSideEffects() {
        return this.operation.hasSideEffects();
    }
    
    public boolean canRaiseException() {
        return this.operation.canRaiseException();
    }
    
    public void markDead() {
        this.isDead = true;
    }
    
    @Interp
    public boolean isDead() {
        return this.isDead;
    }
    
    @Interp
    public abstract Operand[] getOperands();
    
    public List<Variable> getUsedVariables() {
        final ArrayList<Variable> vars = new ArrayList<Variable>();
        for (final Operand o : this.getOperands()) {
            o.addUsedVariables(vars);
        }
        return vars;
    }
    
    public abstract Instr cloneForInlining(final InlinerInfo p0);
    
    public abstract void simplifyOperands(final Map<Operand, Operand> p0);
    
    public Operand simplifyAndGetResult(final Map<Operand, Operand> valueMap) {
        this.simplifyOperands(valueMap);
        return null;
    }
    
    @Interp
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        throw new RuntimeException(this.getClass().getSimpleName() + " should not be directly interpreted");
    }
}
