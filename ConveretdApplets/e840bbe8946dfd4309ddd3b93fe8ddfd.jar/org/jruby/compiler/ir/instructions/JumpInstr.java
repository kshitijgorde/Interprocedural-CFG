// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Label;

public class JumpInstr extends NoOperandInstr
{
    public final Label target;
    
    public JumpInstr(final Label target) {
        super(Operation.JUMP);
        this.target = target;
    }
    
    public String toString() {
        return super.toString() + " " + this.target;
    }
    
    public Label getJumpTarget() {
        return this.target;
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new JumpInstr(ii.getRenamedLabel(this.target));
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        return this.target;
    }
}
