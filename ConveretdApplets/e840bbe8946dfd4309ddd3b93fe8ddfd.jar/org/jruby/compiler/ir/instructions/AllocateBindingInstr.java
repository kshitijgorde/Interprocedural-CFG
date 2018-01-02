// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.RubyModule;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import java.util.Map;
import org.jruby.compiler.ir.IRScope;
import org.jruby.compiler.ir.operands.MetaObject;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.IRExecutionScope;
import org.jruby.compiler.ir.IRMethod;

public class AllocateBindingInstr extends Instr
{
    IRMethod scope;
    
    public AllocateBindingInstr(final IRExecutionScope scope) {
        super(Operation.ALLOC_BINDING);
        this.scope = scope.getClosestMethodAncestor();
    }
    
    public Operand[] getOperands() {
        return new Operand[] { MetaObject.create(this.scope) };
    }
    
    public void simplifyOperands(final Map<Operand, Operand> valueMap) {
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new AllocateBindingInstr(ii.callerCFG.getScope());
    }
    
    public boolean canRaiseException() {
        return false;
    }
    
    public String toString() {
        return "\t" + this.operation + "(" + this.scope + ")";
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        RubyModule implementationClass = this.scope.getStaticScope().getModule();
        if (implementationClass == null) {
            implementationClass = interp.getRuntime().getObject();
        }
        interp.allocateSharedBindingScope(this.scope);
        return null;
    }
}
