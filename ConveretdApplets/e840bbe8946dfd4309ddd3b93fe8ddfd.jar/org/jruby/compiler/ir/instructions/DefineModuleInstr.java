// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.RubyModule;
import org.jruby.compiler.ir.IRScope;
import org.jruby.Ruby;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.ModuleMetaObject;

public class DefineModuleInstr extends OneOperandInstr
{
    public DefineModuleInstr(final ModuleMetaObject m) {
        super(Operation.DEF_MODULE, null, m);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return this;
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        final Ruby runtime = interp.getRuntime();
        final ModuleMetaObject mmo = (ModuleMetaObject)this.getArg();
        final IRScope scope = mmo.scope;
        final RubyModule container = mmo.getContainer(interp, runtime);
        final RubyModule module = container.defineOrGetModuleUnder(scope.getName());
        mmo.interpretBody(interp, interp.getContext(), module);
        return null;
    }
    
    public String toString() {
        return "\t" + this.operation + "(" + this.getArg() + ")";
    }
}
