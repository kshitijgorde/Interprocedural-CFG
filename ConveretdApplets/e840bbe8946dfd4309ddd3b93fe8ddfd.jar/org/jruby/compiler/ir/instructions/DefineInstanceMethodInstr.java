// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.internal.runtime.methods.InterpretedIRMethod;
import org.jruby.RubyModule;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import java.util.Map;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.IRMethod;
import org.jruby.compiler.ir.operands.Operand;

public class DefineInstanceMethodInstr extends OneOperandInstr
{
    public final Operand container;
    public final IRMethod method;
    
    public DefineInstanceMethodInstr(final Operand container, final IRMethod method) {
        super(Operation.DEF_INST_METH, null, container);
        this.container = container;
        this.method = method;
    }
    
    public String toString() {
        return super.toString() + "(" + this.container + ", " + this.method.getName() + ")";
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return this;
    }
    
    public void simplifyOperands(final Map<Operand, Operand> valueMap) {
        super.simplifyOperands(valueMap);
        final Operand o = this.method.getContainer();
        final Operand v = valueMap.get(o);
        if (v != null) {
            this.method.setContainer(v);
        }
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        final RubyModule clazz = (RubyModule)this.method.getContainer().retrieve(interp);
        clazz.addMethod(this.method.getName(), new InterpretedIRMethod(this.method, clazz));
        return null;
    }
}
