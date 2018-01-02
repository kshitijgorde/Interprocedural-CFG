// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.RubyProc;
import org.jruby.runtime.Block;
import org.jruby.RubyClass;
import org.jruby.runtime.ThreadContext;
import org.jruby.interpreter.InlineMethodHint;
import org.jruby.internal.runtime.methods.InterpretedIRMethod;
import org.jruby.exceptions.JumpException;
import org.jruby.RubyModule;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.CallType;
import org.jruby.compiler.ir.operands.MethodHandle;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import java.util.Arrays;
import org.jruby.compiler.ir.IRScope;
import org.jruby.compiler.ir.IRClosure;
import org.jruby.compiler.ir.operands.StringLiteral;
import org.jruby.compiler.ir.IRClass;
import org.jruby.compiler.ir.operands.LocalVariable;
import org.jruby.compiler.ir.IRModule;
import org.jruby.compiler.ir.operands.MetaObject;
import org.jruby.compiler.ir.IRMethod;
import org.jruby.compiler.ir.representations.InlinerInfo;
import java.util.Map;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.internal.runtime.methods.DynamicMethod;
import java.util.HashMap;
import org.jruby.compiler.ir.operands.MethAddr;
import org.jruby.compiler.ir.operands.Operand;

public class CallInstr extends MultiOperandInstr
{
    private Operand receiver;
    private Operand[] arguments;
    MethAddr methAddr;
    Operand closure;
    private boolean _flagsComputed;
    private boolean _canBeEval;
    private boolean _requiresBinding;
    public HashMap<DynamicMethod, Integer> _profile;
    
    public CallInstr(final Variable result, final MethAddr methAddr, final Operand receiver, final Operand[] args, final Operand closure) {
        super(Operation.CALL, result);
        this.receiver = receiver;
        this.arguments = args;
        this.methAddr = methAddr;
        this.closure = closure;
        this._flagsComputed = false;
        this._canBeEval = true;
        this._requiresBinding = true;
    }
    
    public CallInstr(final Operation op, final Variable result, final MethAddr methAddr, final Operand receiver, final Operand[] args, final Operand closure) {
        super(op, result);
        this.receiver = receiver;
        this.arguments = args;
        this.methAddr = methAddr;
        this.closure = closure;
        this._flagsComputed = false;
        this._canBeEval = true;
        this._requiresBinding = true;
    }
    
    public Operand[] getOperands() {
        return buildAllArgs(this.methAddr, this.receiver, this.arguments, this.closure);
    }
    
    public void setMethodAddr(final MethAddr mh) {
        this.methAddr = mh;
    }
    
    public MethAddr getMethodAddr() {
        return this.methAddr;
    }
    
    public Operand getClosureArg() {
        return this.closure;
    }
    
    public Operand getReceiver() {
        return this.receiver;
    }
    
    public Operand[] getCallArgs() {
        return this.arguments;
    }
    
    public void simplifyOperands(final Map<Operand, Operand> valueMap) {
        this.receiver = this.receiver.getSimplifiedOperand(valueMap);
        this.methAddr = (MethAddr)this.methAddr.getSimplifiedOperand(valueMap);
        for (int i = 0; i < this.arguments.length; ++i) {
            this.arguments[i] = this.arguments[i].getSimplifiedOperand(valueMap);
        }
        if (this.closure != null) {
            this.closure = this.closure.getSimplifiedOperand(valueMap);
        }
        this._flagsComputed = false;
    }
    
    public Operand[] cloneCallArgs(final InlinerInfo ii) {
        final int length = this.arguments.length;
        final Operand[] clonedArgs = new Operand[length];
        for (int i = 0; i < length; ++i) {
            clonedArgs[i] = this.arguments[i].cloneForInlining(ii);
        }
        return clonedArgs;
    }
    
    public boolean isRubyInternalsCall() {
        return false;
    }
    
    public boolean isStaticCallTarget() {
        return this.getTargetMethod() != null;
    }
    
    public IRMethod getTargetMethodWithReceiver(final Operand receiver) {
        final String mname = this.methAddr.getName();
        if (receiver instanceof MetaObject) {
            final IRModule m = (IRModule)((MetaObject)receiver).scope;
            return m.getClassMethod(mname);
        }
        if (receiver instanceof LocalVariable && ((LocalVariable)receiver).isSelf()) {
            return null;
        }
        final IRClass c = receiver.getTargetClass();
        return (c == null) ? null : c.getInstanceMethod(mname);
    }
    
    public IRMethod getTargetMethod() {
        return this.getTargetMethodWithReceiver(this.getReceiver());
    }
    
    public boolean canModifyCode() {
        final IRMethod method = this.getTargetMethod();
        return method == null || method.modifiesCode();
    }
    
    private boolean getEvalFlag() {
        final String mname = this.getMethodAddr().getName();
        if (mname.equals("call") || mname.equals("eval")) {
            return true;
        }
        if (mname.equals("send")) {
            final Operand[] args = this.getCallArgs();
            if (args.length >= 2) {
                final Operand meth = args[0];
                if (!(meth instanceof StringLiteral)) {
                    return true;
                }
                final String name = ((StringLiteral)meth)._str_value;
                if (name.equals("call") || name.equals("eval") || name.equals("send")) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean getRequiresBindingFlag() {
        if (this.canBeEval()) {
            return true;
        }
        if (this.closure != null) {
            if (!(this.closure instanceof MetaObject)) {
                return false;
            }
            final IRClosure cl = (IRClosure)((MetaObject)this.closure).scope;
            if (cl.requiresBinding()) {
                return true;
            }
        }
        final String mname = this.getMethodAddr().getName();
        if (mname.equals("lambda")) {
            return true;
        }
        if (mname.equals("new")) {
            final Operand object = this.getReceiver();
            if (!(object instanceof MetaObject)) {
                return true;
            }
            final IRScope c = ((MetaObject)object).scope;
            if (c instanceof IRClass && c.getName().equals("Proc")) {
                return true;
            }
        }
        return false;
    }
    
    private void computeFlags() {
        this._flagsComputed = true;
        this._canBeEval = this.getEvalFlag();
        this._requiresBinding = (this._canBeEval || this.getRequiresBindingFlag());
    }
    
    public boolean canBeEval() {
        if (!this._flagsComputed) {
            this.computeFlags();
        }
        return this._canBeEval;
    }
    
    public boolean requiresBinding() {
        if (!this._flagsComputed) {
            this.computeFlags();
        }
        return this._requiresBinding;
    }
    
    public boolean canCaptureCallersBinding() {
        final Operand r = this.getReceiver();
        final IRMethod rm = this.getTargetMethodWithReceiver(r);
        return rm == null || rm.canCaptureCallersBinding();
    }
    
    public boolean isLVADataflowBarrier() {
        return this.canBeEval() || (this.getClosureArg() != null && this.canCaptureCallersBinding());
    }
    
    public String toString() {
        return "\t" + ((this.result == null) ? "" : (this.result + " = ")) + this.operation + "(" + this.methAddr + ", " + this.receiver + ", " + Arrays.toString(this.getCallArgs()) + ((this.closure == null) ? "" : (", &" + this.closure)) + ")";
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new CallInstr(ii.getRenamedVariable(this.result), (MethAddr)this.methAddr.cloneForInlining(ii), this.receiver.cloneForInlining(ii), this.cloneCallArgs(ii), (this.closure == null) ? null : this.closure.cloneForInlining(ii));
    }
    
    private static Operand[] buildAllArgs(final Operand methAddr, final Operand receiver, final Operand[] callArgs, final Operand closure) {
        final Operand[] allArgs = new Operand[callArgs.length + 2 + ((closure != null) ? 1 : 0)];
        assert methAddr != null : "METHADDR is null";
        assert receiver != null : "RECEIVER is null";
        allArgs[0] = methAddr;
        allArgs[1] = receiver;
        for (int i = 0; i < callArgs.length; ++i) {
            assert callArgs[i] != null : "ARG " + i + " is null";
            allArgs[i + 2] = callArgs[i];
        }
        if (closure != null) {
            allArgs[callArgs.length + 2] = closure;
        }
        return allArgs;
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        final Object ma = this.methAddr.retrieve(interp);
        final IRubyObject[] args = this.prepareArguments(this.getCallArgs(), interp);
        Object resultValue;
        if (ma instanceof MethodHandle) {
            final MethodHandle mh = (MethodHandle)ma;
            assert mh.getMethodNameOperand() == this.getReceiver();
            final DynamicMethod m = mh.getResolvedMethod();
            final String mn = mh.getResolvedMethodName();
            final IRubyObject ro = mh.getReceiverObj();
            if (m.isUndefined()) {
                resultValue = RuntimeHelpers.callMethodMissing(interp.getContext(), ro, m.getVisibility(), mn, CallType.FUNCTIONAL, args, this.prepareBlock(interp));
            }
            else {
                try {
                    resultValue = m.call(interp.getContext(), ro, ro.getMetaClass(), mn, args, this.prepareBlock(interp));
                }
                catch (JumpException.BreakJump bj) {
                    resultValue = bj.getValue();
                }
            }
        }
        else {
            final IRubyObject object = (IRubyObject)this.getReceiver().retrieve(interp);
            final String name = ma.toString();
            try {
                resultValue = object.callMethod(interp.getContext(), name, args, this.prepareBlock(interp));
            }
            catch (JumpException.BreakJump bj2) {
                resultValue = bj2.getValue();
            }
        }
        this.getResult().store(interp, resultValue);
        return null;
    }
    
    public Label interpret_with_inline(final InterpreterContext interp, final IRubyObject self) {
        final Object ma = this.methAddr.retrieve(interp);
        final IRubyObject[] args = this.prepareArguments(this.getCallArgs(), interp);
        Object resultValue;
        if (ma instanceof MethodHandle) {
            final MethodHandle mh = (MethodHandle)ma;
            assert mh.getMethodNameOperand() == this.getReceiver();
            final DynamicMethod m = mh.getResolvedMethod();
            final String mn = mh.getResolvedMethodName();
            final IRubyObject ro = mh.getReceiverObj();
            if (m.isUndefined()) {
                resultValue = RuntimeHelpers.callMethodMissing(interp.getContext(), ro, m.getVisibility(), mn, CallType.FUNCTIONAL, args, this.prepareBlock(interp));
            }
            else {
                final ThreadContext tc = interp.getContext();
                final RubyClass rc = ro.getMetaClass();
                if (this._profile == null) {
                    this._profile = new HashMap<DynamicMethod, Integer>();
                }
                Integer count = this._profile.get(m);
                if (count == null) {
                    count = new Integer(1);
                }
                else {
                    count = new Integer(count + 1);
                    if (count > 50 && m instanceof InterpretedIRMethod && this._profile.size() == 1) {
                        final IRMethod inlineableMethod = ((InterpretedIRMethod)m).method;
                        this._profile.remove(m);
                        throw new InlineMethodHint(inlineableMethod);
                    }
                }
                this._profile.put(m, count);
                resultValue = m.call(tc, ro, rc, mn, args, this.prepareBlock(interp));
            }
        }
        else {
            final IRubyObject object = (IRubyObject)this.getReceiver().retrieve(interp);
            final String name = ma.toString();
            resultValue = object.callMethod(interp.getContext(), name, args, this.prepareBlock(interp));
        }
        this.getResult().store(interp, resultValue);
        return null;
    }
    
    private Block prepareBlock(final InterpreterContext interp) {
        if (this.closure == null) {
            return Block.NULL_BLOCK;
        }
        final Object value = this.closure.retrieve(interp);
        return (value instanceof RubyProc) ? ((RubyProc)value).getBlock() : value;
    }
}
