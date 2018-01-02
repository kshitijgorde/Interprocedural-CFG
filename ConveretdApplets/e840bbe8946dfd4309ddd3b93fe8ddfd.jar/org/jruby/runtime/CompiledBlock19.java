// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.Ruby;
import org.jruby.RubyArray;
import org.jruby.exceptions.JumpException;
import org.jruby.RubyModule;
import org.jruby.parser.StaticScope;
import org.jruby.runtime.builtin.IRubyObject;

public class CompiledBlock19 extends ContextAwareBlockBody
{
    protected final CompiledBlockCallback19 callback;
    protected final boolean hasMultipleArgsHead;
    protected final String[] parameterList;
    
    public static Block newCompiledClosure(final ThreadContext context, final IRubyObject self, final Arity arity, final StaticScope scope, final CompiledBlockCallback19 callback, final boolean hasMultipleArgsHead, final int argumentType) {
        final Binding binding = context.currentBinding(self, Visibility.PUBLIC);
        final BlockBody body = new CompiledBlock19(arity, scope, callback, hasMultipleArgsHead, argumentType, CompiledBlock19.EMPTY_PARAMETER_LIST);
        return new Block(body, binding);
    }
    
    public static Block newCompiledClosure(final ThreadContext context, final IRubyObject self, final BlockBody body) {
        final Binding binding = context.currentBinding(self, Visibility.PUBLIC);
        return new Block(body, binding);
    }
    
    public static BlockBody newCompiledBlock(final Arity arity, final StaticScope scope, final CompiledBlockCallback19 callback, final boolean hasMultipleArgsHead, final int argumentType, final String[] parameterList) {
        return new CompiledBlock19(arity, scope, callback, hasMultipleArgsHead, argumentType, parameterList);
    }
    
    protected CompiledBlock19(final Arity arity, final StaticScope scope, final CompiledBlockCallback19 callback, final boolean hasMultipleArgsHead, final int argumentType, final String[] parameterList) {
        super(scope, arity, argumentType);
        this.callback = callback;
        this.hasMultipleArgsHead = hasMultipleArgsHead;
        this.parameterList = parameterList;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject[] args, final Binding binding, final Block.Type type) {
        final IRubyObject value = (args.length == 1) ? args[0] : context.getRuntime().newArrayNoCopy(args);
        return this.yield(context, value, null, null, true, binding, type, Block.NULL_BLOCK);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject[] args, final Binding binding, final Block.Type type, final Block block) {
        return this.yield(context, context.getRuntime().newArrayNoCopy(args), null, null, true, binding, type, block);
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final Binding binding, final Block.Type type) {
        return this.yieldSpecificInternal(context, IRubyObject.NULL_ARRAY, binding, type);
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final IRubyObject arg0, final Binding binding, final Block.Type type) {
        return this.yield(context, arg0, binding, type);
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Binding binding, final Block.Type type) {
        return this.yieldSpecificInternal(context, new IRubyObject[] { arg0, arg1 }, binding, type);
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Binding binding, final Block.Type type) {
        return this.yieldSpecificInternal(context, new IRubyObject[] { arg0, arg1, arg2 }, binding, type);
    }
    
    private IRubyObject yieldSpecificInternal(final ThreadContext context, final IRubyObject[] args, final Binding binding, final Block.Type type) {
        final IRubyObject self = this.prepareSelf(binding);
        final Visibility oldVis = binding.getFrame().getVisibility();
        final Frame lastFrame = this.pre(context, null, binding);
        try {
            return this.callback.call(context, self, args, Block.NULL_BLOCK);
        }
        catch (JumpException.NextJump nj) {
            return this.handleNextJump(context, nj, type);
        }
        finally {
            this.post(context, binding, oldVis, lastFrame);
        }
    }
    
    public IRubyObject yield(final ThreadContext context, final IRubyObject value, final Binding binding, final Block.Type type) {
        final IRubyObject self = this.prepareSelf(binding);
        final Visibility oldVis = binding.getFrame().getVisibility();
        final Frame lastFrame = this.pre(context, null, binding);
        try {
            final IRubyObject[] realArgs = this.setupBlockArg(context.getRuntime(), value, self);
            return this.callback.call(context, self, realArgs, Block.NULL_BLOCK);
        }
        catch (JumpException.NextJump nj) {
            return this.handleNextJump(context, nj, type);
        }
        finally {
            this.post(context, binding, oldVis, lastFrame);
        }
    }
    
    public IRubyObject yield(final ThreadContext context, final IRubyObject args, final IRubyObject self, final RubyModule klass, final boolean aValue, final Binding binding, final Block.Type type) {
        return this.yield(context, args, self, klass, aValue, binding, type, Block.NULL_BLOCK);
    }
    
    public IRubyObject yield(final ThreadContext context, final IRubyObject args, IRubyObject self, final RubyModule klass, final boolean aValue, final Binding binding, final Block.Type type, final Block block) {
        if (klass == null) {
            self = this.prepareSelf(binding);
        }
        final Visibility oldVis = binding.getFrame().getVisibility();
        final Frame lastFrame = this.pre(context, klass, binding);
        try {
            final IRubyObject[] realArgs = this.setupBlockArgs(args, aValue);
            return this.callback.call(context, self, realArgs, block);
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
    
    private IRubyObject[] setupBlockArgs(final IRubyObject value, final boolean alreadyArray) {
        final Arity arity = this.arity();
        final int requiredCount = arity.required();
        final boolean isRest = !arity.isFixed();
        IRubyObject[] parameters;
        if (value == null) {
            parameters = IRubyObject.NULL_ARRAY;
        }
        else if (value instanceof RubyArray && (alreadyArray || (isRest && requiredCount > 0))) {
            parameters = ((RubyArray)value).toJavaArray();
        }
        else {
            parameters = new IRubyObject[] { value };
        }
        return parameters;
    }
    
    protected IRubyObject[] setupBlockArg(final Ruby ruby, final IRubyObject value, final IRubyObject self) {
        final Arity arity = this.arity();
        final int requiredCount = arity.required();
        final boolean isRest = !arity.isFixed();
        IRubyObject[] parameters;
        if (value == null) {
            parameters = IRubyObject.NULL_ARRAY;
        }
        else if (value instanceof RubyArray && ((isRest && requiredCount > 0) || (!isRest && requiredCount > 1))) {
            parameters = ((RubyArray)value).toJavaArray();
        }
        else {
            parameters = new IRubyObject[] { value };
        }
        return parameters;
    }
    
    public String getFile() {
        return this.callback.getFile();
    }
    
    public int getLine() {
        return this.callback.getLine();
    }
    
    public String[] getParameterList() {
        return this.parameterList;
    }
}
