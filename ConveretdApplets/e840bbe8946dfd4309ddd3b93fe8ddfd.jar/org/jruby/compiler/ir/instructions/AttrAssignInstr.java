// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.RubyString;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import java.util.Map;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Operand;

public class AttrAssignInstr extends MultiOperandInstr
{
    private Operand obj;
    private Operand attr;
    private Operand value;
    private Operand[] args;
    
    public AttrAssignInstr(final Operand obj, final Operand attr, final Operand[] args) {
        super(Operation.ATTR_ASSIGN, null);
        this.obj = obj;
        this.attr = attr;
        this.args = args;
        this.value = null;
    }
    
    public AttrAssignInstr(final Operand obj, final Operand attr, final Operand[] args, final Operand value) {
        super(Operation.ATTR_ASSIGN, null);
        this.obj = obj;
        this.attr = attr;
        this.args = args;
        this.value = value;
    }
    
    public Operand[] getOperands() {
        final Operand[] argsArray = new Operand[this.args.length + ((this.value == null) ? 2 : 3)];
        int i = 2;
        argsArray[0] = this.obj;
        argsArray[1] = this.attr;
        if (this.value != null) {
            argsArray[2] = this.value;
            ++i;
        }
        for (final Operand o : this.args) {
            argsArray[i++] = o;
        }
        return argsArray;
    }
    
    public void simplifyOperands(final Map<Operand, Operand> valueMap) {
        this.obj = this.obj.getSimplifiedOperand(valueMap);
        this.attr = this.attr.getSimplifiedOperand(valueMap);
        for (int i = 0; i < this.args.length; ++i) {
            this.args[i] = this.args[i].getSimplifiedOperand(valueMap);
        }
        if (this.value != null) {
            this.value = this.value.getSimplifiedOperand(valueMap);
        }
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        int i = 0;
        final Operand[] clonedArgs = new Operand[this.args.length];
        for (final Operand a : this.args) {
            clonedArgs[i++] = a.cloneForInlining(ii);
        }
        return new AttrAssignInstr(this.obj.cloneForInlining(ii), this.attr.cloneForInlining(ii), clonedArgs, (this.value == null) ? null : this.value.cloneForInlining(ii));
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        final IRubyObject receiver = (IRubyObject)this.obj.retrieve(interp);
        final String attrMeth = ((RubyString)this.attr.retrieve(interp)).asJavaString();
        final IRubyObject[] callArgs = new IRubyObject[this.args.length + ((this.value != null) ? 1 : 0)];
        int i = 0;
        if (this.value != null) {
            callArgs[0] = (IRubyObject)this.value.retrieve(interp);
            ++i;
        }
        while (i < callArgs.length) {
            callArgs[i] = (IRubyObject)this.args[i].retrieve(interp);
            ++i;
        }
        receiver.callMethod(interp.getContext(), attrMeth, callArgs);
        return null;
    }
}
