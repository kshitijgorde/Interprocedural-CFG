// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.RubyModule;
import org.jruby.compiler.ir.IRScope;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.compiler.ir.IRMetaClass;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.ClassMetaObject;

public class DefineClassInstr extends TwoOperandInstr
{
    public DefineClassInstr(final ClassMetaObject cmo, final Operand superClass) {
        super(Operation.DEF_CLASS, null, cmo, superClass);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new DefineClassInstr((ClassMetaObject)this.getOperand1(), this.getOperand2().cloneForInlining(ii));
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        final Ruby runtime = interp.getRuntime();
        final ClassMetaObject cmo = (ClassMetaObject)this.getOperand1();
        final IRScope scope = cmo.scope;
        final RubyModule container = cmo.getContainer(interp, runtime);
        RubyModule module;
        if (scope instanceof IRMetaClass) {
            module = container.getMetaClass();
        }
        else {
            final RubyClass sc = (RubyClass)this.getOperand2().retrieve(interp);
            module = container.defineOrGetClassUnder(scope.getName(), (sc == null) ? runtime.getObject() : sc);
        }
        cmo.interpretBody(interp, interp.getContext(), module);
        return null;
    }
    
    public String toString() {
        return "\t" + this.operation + "(" + this.getOperand1() + ", " + this.getOperand2() + ")";
    }
}
