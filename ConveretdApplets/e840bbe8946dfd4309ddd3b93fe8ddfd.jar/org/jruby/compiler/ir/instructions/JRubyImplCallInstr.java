// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.Ruby;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.RubyRegexp;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.MethAddr;
import org.jruby.compiler.ir.operands.Variable;

public class JRubyImplCallInstr extends CallInstr
{
    public JRubyImplCallInstr(final Variable result, final MethAddr methAddr, final Operand receiver, final Operand[] args) {
        super(Operation.JRUBY_IMPL, result, methAddr, receiver, args, null);
    }
    
    public JRubyImplCallInstr(final Variable result, final MethAddr methAddr, final Operand receiver, final Operand[] args, final Operand closure) {
        super(result, methAddr, receiver, args, closure);
    }
    
    public boolean isStaticCallTarget() {
        return true;
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new JRubyImplCallInstr(ii.getRenamedVariable(this.result), (MethAddr)this.methAddr.cloneForInlining(ii), this.getReceiver().cloneForInlining(ii), this.cloneCallArgs(ii), (this.closure == null) ? null : this.closure.cloneForInlining(ii));
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        final Ruby rt = interp.getRuntime();
        if (this.getMethodAddr() == MethAddr.MATCH2) {
            final Object receiver = this.getReceiver().retrieve(interp);
            this.getResult().store(interp, ((RubyRegexp)receiver).op_match(interp.getContext(), (IRubyObject)this.getCallArgs()[0].retrieve(interp)));
        }
        else if (this.getMethodAddr() == MethAddr.MATCH3) {
            final Object receiver = this.getReceiver().retrieve(interp);
            this.getResult().store(interp, ((RubyRegexp)receiver).op_match(interp.getContext(), (IRubyObject)this.getCallArgs()[0].retrieve(interp)));
        }
        else if (this.getMethodAddr() == MethAddr.TO_ARY) {
            final Object receiver = this.getReceiver().retrieve(interp);
            this.getResult().store(interp, RuntimeHelpers.aryToAry((IRubyObject)receiver));
        }
        else if (this.getMethodAddr().getName().equals("threadContext_saveErrInfo")) {
            this.getResult().store(interp, interp.getContext().getErrorInfo());
        }
        else if (this.getMethodAddr().getName().equals("threadContext_restoreErrInfo")) {
            interp.getContext().setErrorInfo((IRubyObject)this.getCallArgs()[0].retrieve(interp));
        }
        else if (this.getMethodAddr().getName().equals("threadContext_getConstantDefined")) {
            final String name = this.getCallArgs()[0].retrieve(interp).toString();
            this.getResult().store(interp, rt.newBoolean(interp.getContext().getConstantDefined(name)));
        }
        else if (this.getMethodAddr().getName().equals("self_hasInstanceVariable")) {
            final Object receiver = this.getReceiver().retrieve(interp);
            final String name = this.getCallArgs()[0].retrieve(interp).toString();
            this.getResult().store(interp, rt.newBoolean(((IRubyObject)receiver).getInstanceVariables().fastHasInstanceVariable(name)));
        }
        else if (this.getMethodAddr().getName().equals("runtime_isGlobalDefined")) {
            final String name = this.getCallArgs()[0].retrieve(interp).toString();
            this.getResult().store(interp, rt.newBoolean(rt.getGlobalVariables().isDefined(name)));
        }
        else if (this.getMethodAddr().getName().equals("runtime_getObject")) {
            this.getResult().store(interp, rt.getObject());
        }
        else if (this.getMethodAddr().getName().equals("block_isGiven")) {
            this.getResult().store(interp, rt.newBoolean(interp.getBlock().isGiven()));
        }
        else {
            super.interpret(interp, self);
        }
        return null;
    }
}
