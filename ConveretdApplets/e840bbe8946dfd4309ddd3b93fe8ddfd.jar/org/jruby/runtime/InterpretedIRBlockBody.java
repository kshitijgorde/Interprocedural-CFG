// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.common.IRubyWarnings;
import org.jruby.ast.util.ArgsUtil;
import org.jruby.Ruby;
import org.jruby.exceptions.JumpException;
import org.jruby.RubyModule;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.interpreter.Interpreter;
import org.jruby.interpreter.NaiveInterpreterContext;
import org.jruby.RubyArray;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.compiler.ir.IRClosure;

public class InterpretedIRBlockBody extends ContextAwareBlockBody
{
    private final IRClosure closure;
    private final boolean hasMultipleArgsHead;
    
    public InterpretedIRBlockBody(final IRClosure closure, final Arity arity, final int argumentType) {
        super(closure.getStaticScope(), arity, argumentType);
        this.closure = closure;
        this.hasMultipleArgsHead = false;
    }
    
    public IRubyObject yield(final ThreadContext context, final IRubyObject value, final Binding binding, final Block.Type type) {
        return this.call(context, new IRubyObject[] { value }, binding, type);
    }
    
    private IRubyObject prepareSelf(final Binding binding) {
        final IRubyObject self = binding.getSelf();
        binding.getFrame().setSelf(self);
        return self;
    }
    
    public IRubyObject call(final ThreadContext context, IRubyObject[] args, final Binding binding, final Block.Type type) {
        final IRubyObject self = context.getFrameSelf();
        final int numBlockArgs = this.arity().required();
        if (numBlockArgs > 1 && args.length == 1 && args[0] instanceof RubyArray) {
            final RubyArray array = (RubyArray)args[0];
            final int size = array.getLength();
            args = new IRubyObject[numBlockArgs];
            int i;
            for (i = 0; i < numBlockArgs && i < size; ++i) {
                args[i] = array.eltInternal(i);
            }
            while (i < size) {
                args[i] = context.getRuntime().getNil();
                ++i;
            }
        }
        final InterpreterContext interp = new NaiveInterpreterContext(context, self, this.closure.getLocalVariablesCount(), this.closure.getTemporaryVariableSize(), this.closure.getRenamedVariableSize(), args, Block.NULL_BLOCK);
        interp.setDynamicScope(binding.getDynamicScope());
        return Interpreter.interpret(context, this.closure.getCFG(), interp);
    }
    
    public IRubyObject yield(final ThreadContext context, final IRubyObject value, IRubyObject self, final RubyModule klass, final boolean aValue, final Binding binding, final Block.Type type) {
        if (self == null) {
            self = value;
        }
        IRubyObject[] args = { value };
        final int numBlockArgs = this.arity().required();
        if (numBlockArgs > 1 && value instanceof RubyArray) {
            final RubyArray array = (RubyArray)value;
            final int size = array.getLength();
            args = new IRubyObject[numBlockArgs];
            int i;
            for (i = 0; i < numBlockArgs && i < size; ++i) {
                args[i] = array.eltInternal(i);
            }
            while (i < size) {
                args[i] = context.getRuntime().getNil();
                ++i;
            }
        }
        final InterpreterContext interp = new NaiveInterpreterContext(context, self, this.closure.getLocalVariablesCount(), this.closure.getTemporaryVariableSize(), this.closure.getRenamedVariableSize(), args, Block.NULL_BLOCK);
        interp.setDynamicScope(binding.getDynamicScope());
        return Interpreter.interpret(context, this.closure.getCFG(), interp);
    }
    
    private IRubyObject handleNextJump(final ThreadContext context, final JumpException.NextJump nj, final Block.Type type) {
        return (IRubyObject)((nj.getValue() == null) ? context.getRuntime().getNil() : nj.getValue());
    }
    
    protected IRubyObject setupBlockArgs(final ThreadContext context, final IRubyObject value, final IRubyObject self) {
        switch (this.argumentType) {
            case 0: {
                return null;
            }
            case 1:
            case 3: {
                return value;
            }
            default: {
                return this.defaultArgsLogic(context.getRuntime(), value);
            }
        }
    }
    
    private IRubyObject defaultArgsLogic(final Ruby ruby, final IRubyObject value) {
        final int length = ArgsUtil.arrayLength(value);
        switch (length) {
            case 0: {
                return ruby.getNil();
            }
            case 1: {
                return ((RubyArray)value).eltInternal(0);
            }
            default: {
                this.blockArgWarning(ruby, length);
                return value;
            }
        }
    }
    
    private IRubyObject warnMultiReturnNil(final Ruby ruby) {
        ruby.getWarnings().warn(IRubyWarnings.ID.MULTIPLE_VALUES_FOR_BLOCK, "multiple values for a block parameter (0 for 1)", new Object[0]);
        return ruby.getNil();
    }
    
    private void blockArgWarning(final Ruby ruby, final int length) {
        ruby.getWarnings().warn(IRubyWarnings.ID.MULTIPLE_VALUES_FOR_BLOCK, "multiple values for a block parameter (" + length + " for 1)", new Object[0]);
    }
    
    protected IRubyObject setupBlockArg(final Ruby ruby, final IRubyObject value, final IRubyObject self) {
        switch (this.argumentType) {
            case 0: {
                return null;
            }
            case 1:
            case 3: {
                return ArgsUtil.convertToRubyArray(ruby, value, this.hasMultipleArgsHead);
            }
            default: {
                return this.defaultArgLogic(ruby, value);
            }
        }
    }
    
    private IRubyObject defaultArgLogic(final Ruby ruby, final IRubyObject value) {
        if (value == null) {
            return this.warnMultiReturnNil(ruby);
        }
        return value;
    }
    
    public String getFile() {
        return "(unknown)";
    }
    
    public int getLine() {
        return -1;
    }
}
