// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.common.IRubyWarnings;
import org.jruby.RubyArray;
import org.jruby.ast.util.ArgsUtil;
import org.jruby.Ruby;
import org.jruby.exceptions.JumpException;
import org.jruby.RubyModule;
import org.jruby.parser.StaticScope;
import org.jruby.runtime.builtin.IRubyObject;

public class CompiledBlock extends ContextAwareBlockBody
{
    protected final CompiledBlockCallback callback;
    protected final boolean hasMultipleArgsHead;
    
    public static Block newCompiledClosure(final ThreadContext context, final IRubyObject self, final Arity arity, final StaticScope scope, final CompiledBlockCallback callback, final boolean hasMultipleArgsHead, final int argumentType) {
        final Binding binding = context.currentBinding(self, Visibility.PUBLIC);
        final BlockBody body = new CompiledBlock(arity, scope, callback, hasMultipleArgsHead, argumentType);
        return new Block(body, binding);
    }
    
    public static Block newCompiledClosure(final ThreadContext context, final IRubyObject self, final BlockBody body) {
        final Binding binding = context.currentBinding(self, Visibility.PUBLIC);
        return new Block(body, binding);
    }
    
    public static BlockBody newCompiledBlock(final Arity arity, final StaticScope scope, final CompiledBlockCallback callback, final boolean hasMultipleArgsHead, final int argumentType) {
        return new CompiledBlock(arity, scope, callback, hasMultipleArgsHead, argumentType);
    }
    
    protected CompiledBlock(final Arity arity, final StaticScope scope, final CompiledBlockCallback callback, final boolean hasMultipleArgsHead, final int argumentType) {
        super(scope, arity, argumentType);
        this.callback = callback;
        this.hasMultipleArgsHead = hasMultipleArgsHead;
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final Binding binding, final Block.Type type) {
        return this.yield(context, null, binding, type);
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final IRubyObject arg0, final Binding binding, final Block.Type type) {
        return this.yield(context, arg0, binding, type);
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Binding binding, final Block.Type type) {
        return this.yield(context, context.getRuntime().newArrayNoCopyLight(arg0, arg1), null, null, true, binding, type);
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Binding binding, final Block.Type type) {
        return this.yield(context, context.getRuntime().newArrayNoCopyLight(arg0, arg1, arg2), null, null, true, binding, type);
    }
    
    public IRubyObject yield(final ThreadContext context, final IRubyObject value, final Binding binding, final Block.Type type) {
        return this.yield(context, value, binding, type, Block.NULL_BLOCK);
    }
    
    public IRubyObject yield(final ThreadContext context, final IRubyObject args, final IRubyObject self, final RubyModule klass, final boolean aValue, final Binding binding, final Block.Type type) {
        return this.yield(context, args, self, klass, aValue, binding, type, Block.NULL_BLOCK);
    }
    
    public IRubyObject yield(final ThreadContext context, final IRubyObject value, final Binding binding, final Block.Type type, final Block block) {
        final IRubyObject self = this.prepareSelf(binding);
        final IRubyObject realArg = this.setupBlockArg(context.getRuntime(), value, self);
        final Visibility oldVis = binding.getFrame().getVisibility();
        final Frame lastFrame = this.pre(context, null, binding);
        try {
            return this.callback.call(context, self, realArg, block);
        }
        catch (JumpException.NextJump nj) {
            return this.handleNextJump(context, nj, type);
        }
        finally {
            this.post(context, binding, oldVis, lastFrame);
        }
    }
    
    public IRubyObject yield(final ThreadContext context, final IRubyObject args, IRubyObject self, final RubyModule klass, final boolean aValue, final Binding binding, final Block.Type type, final Block block) {
        if (klass == null) {
            self = this.prepareSelf(binding);
        }
        final IRubyObject realArg = aValue ? this.setupBlockArgs(context, args, self) : this.setupBlockArg(context.getRuntime(), args, self);
        final Visibility oldVis = binding.getFrame().getVisibility();
        final Frame lastFrame = this.pre(context, klass, binding);
        try {
            return this.callback.call(context, self, realArg, block);
        }
        catch (JumpException.NextJump nj) {
            return this.handleNextJump(context, nj, type);
        }
        finally {
            this.post(context, binding, oldVis, lastFrame);
        }
    }
    
    private IRubyObject prepareSelf(final Binding binding) {
        final IRubyObject self = binding.getSelf();
        binding.getFrame().setSelf(self);
        return self;
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
        return this.callback.getFile();
    }
    
    public int getLine() {
        return this.callback.getLine();
    }
    
    private IRubyObject warnMultiReturnNil(final Ruby ruby) {
        ruby.getWarnings().warn(IRubyWarnings.ID.MULTIPLE_VALUES_FOR_BLOCK, "multiple values for a block parameter (0 for 1)", new Object[0]);
        return ruby.getNil();
    }
}
