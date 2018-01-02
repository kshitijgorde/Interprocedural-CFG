// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.RubyModule;
import org.jruby.parser.StaticScope;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.IRModule;
import java.util.Map;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.MetaObject;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.IRScope;
import org.jruby.compiler.ir.operands.Variable;

public class SearchConstInstr extends GetInstr
{
    public SearchConstInstr(final Variable dest, final IRScope scope, final String constName) {
        super(Operation.SEARCH_CONST, dest, MetaObject.create(scope), constName);
    }
    
    public SearchConstInstr(final Variable dest, final Operand scopeOrObj, final String constName) {
        super(Operation.SEARCH_CONST, dest, scopeOrObj, constName);
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
        final Object n = this.getSource();
        assert n instanceof MetaObject : "All sources should be a meta object";
        final StaticScope staticScope = ((MetaObject)n).getScope().getStaticScope();
        final RubyModule object = interp.getRuntime().getObject();
        this.getResult().store(interp, staticScope.getConstant(interp.getRuntime(), this.getName(), object));
        return null;
    }
}
