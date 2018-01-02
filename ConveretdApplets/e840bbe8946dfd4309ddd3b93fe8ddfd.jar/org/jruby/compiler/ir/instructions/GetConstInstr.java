// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.RubyModule;
import org.jruby.runtime.Block;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.IRScope;
import org.jruby.compiler.ir.IRModule;
import org.jruby.compiler.ir.operands.MetaObject;
import java.util.Map;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.Variable;

public class GetConstInstr extends GetInstr
{
    public GetConstInstr(final Variable dest, final Operand scopeOrObj, final String constName) {
        super(Operation.GET_CONST, dest, scopeOrObj, constName);
    }
    
    public Operand simplifyAndGetResult(final Map<Operand, Operand> valueMap) {
        this.simplifyOperands(valueMap);
        if (!(this.getSource() instanceof MetaObject)) {
            return null;
        }
        final IRScope s = ((MetaObject)this.getSource()).scope;
        return (s instanceof IRModule) ? ((IRModule)s).getConstantValue(this.getName()) : null;
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new SearchConstInstr(ii.getRenamedVariable(this.result), this.getSource().cloneForInlining(ii), this.getName());
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        final Object source = this.getSource().retrieve(interp);
        RubyModule module;
        if (source instanceof Block) {
            module = ((Block)source).getBinding().getKlass();
        }
        else {
            module = (RubyModule)source;
        }
        this.getResult().store(interp, module.getConstant(this.getName()));
        return null;
    }
}
