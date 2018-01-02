// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.RubyModule;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.IRMethod;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.MethAddr;
import org.jruby.compiler.ir.operands.Variable;

public class RubyInternalCallInstr extends CallInstr
{
    public RubyInternalCallInstr(final Variable result, final MethAddr methAddr, final Operand receiver, final Operand[] args) {
        super(Operation.RUBY_INTERNALS, result, methAddr, receiver, args, null);
    }
    
    public RubyInternalCallInstr(final Variable result, final MethAddr methAddr, final Operand receiver, final Operand[] args, final Operand closure) {
        super(result, methAddr, receiver, args, closure);
    }
    
    public boolean isRubyInternalsCall() {
        return true;
    }
    
    public boolean isStaticCallTarget() {
        return true;
    }
    
    public IRMethod getTargetMethodWithReceiver(final Operand receiver) {
        return null;
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new RubyInternalCallInstr(ii.getRenamedVariable(this.result), (MethAddr)this.methAddr.cloneForInlining(ii), this.getReceiver().cloneForInlining(ii), this.cloneCallArgs(ii), (this.closure == null) ? null : this.closure.cloneForInlining(ii));
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        if (this.getMethodAddr() == MethAddr.DEFINE_ALIAS) {
            final Operand[] args = this.getCallArgs();
            final RubyModule clazz = (self instanceof RubyModule) ? ((RubyModule)self) : self.getMetaClass();
            clazz.defineAlias(args[0].retrieve(interp).toString(), args[1].retrieve(interp).toString());
        }
        else {
            super.interpret(interp, self);
        }
        return null;
    }
}
