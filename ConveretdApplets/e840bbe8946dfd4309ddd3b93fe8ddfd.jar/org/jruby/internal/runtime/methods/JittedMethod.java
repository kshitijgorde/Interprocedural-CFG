// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.ast.ArgsNode;
import org.jruby.Ruby;
import org.jruby.exceptions.JumpException;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.runtime.Arity;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.ast.executable.Script;
import org.jruby.parser.StaticScope;
import org.jruby.runtime.PositionAware;

public class JittedMethod extends DynamicMethod implements MethodArgs, PositionAware
{
    private final StaticScope staticScope;
    private final Script jitCompiledScript;
    private final ISourcePosition position;
    private final Arity arity;
    private final DefaultMethod realMethod;
    
    public JittedMethod(final RubyModule implementationClass, final StaticScope staticScope, final Script jitCompiledScript, final String name, final CallConfiguration jitCallConfig, final Visibility visibility, final Arity arity, final ISourcePosition position, final DefaultMethod realMethod) {
        super(implementationClass, visibility, jitCallConfig, name);
        this.position = position;
        this.jitCompiledScript = jitCompiledScript;
        this.staticScope = staticScope;
        this.arity = arity;
        this.realMethod = realMethod;
    }
    
    public StaticScope getStaticScope() {
        return this.staticScope;
    }
    
    public DynamicMethod getRealMethod() {
        return this.realMethod;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, self, name, block, args.length);
            return this.jitCompiledScript.__file__(context, self, args, block);
        }
        catch (JumpException.ReturnJump rj) {
            return this.handleReturn(context, rj, callNumber);
        }
        catch (JumpException.RedoJump rj2) {
            return this.handleRedo(runtime);
        }
        catch (JumpException.BreakJump bj) {
            return this.handleBreak(context, runtime, bj, callNumber);
        }
        finally {
            this.post(runtime, context, name);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args) {
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, self, name, Block.NULL_BLOCK, args.length);
            return this.jitCompiledScript.__file__(context, self, args, Block.NULL_BLOCK);
        }
        catch (JumpException.ReturnJump rj) {
            return this.handleReturn(context, rj, callNumber);
        }
        catch (JumpException.RedoJump rj2) {
            return this.handleRedo(runtime);
        }
        catch (JumpException.BreakJump bj) {
            return this.handleBreak(context, runtime, bj, callNumber);
        }
        finally {
            this.post(runtime, context, name);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name) {
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, self, name, Block.NULL_BLOCK, 0);
            return this.jitCompiledScript.__file__(context, self, Block.NULL_BLOCK);
        }
        catch (JumpException.ReturnJump rj) {
            return this.handleReturn(context, rj, callNumber);
        }
        catch (JumpException.RedoJump rj2) {
            return this.handleRedo(runtime);
        }
        catch (JumpException.BreakJump bj) {
            return this.handleBreak(context, runtime, bj, callNumber);
        }
        finally {
            this.post(runtime, context, name);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final Block block) {
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, self, name, block, 0);
            return this.jitCompiledScript.__file__(context, self, block);
        }
        catch (JumpException.ReturnJump rj) {
            return this.handleReturn(context, rj, callNumber);
        }
        catch (JumpException.RedoJump rj2) {
            return this.handleRedo(runtime);
        }
        catch (JumpException.BreakJump bj) {
            return this.handleBreak(context, runtime, bj, callNumber);
        }
        finally {
            this.post(runtime, context, name);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0) {
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, self, name, Block.NULL_BLOCK, 1);
            return this.jitCompiledScript.__file__(context, self, arg0, Block.NULL_BLOCK);
        }
        catch (JumpException.ReturnJump rj) {
            return this.handleReturn(context, rj, callNumber);
        }
        catch (JumpException.RedoJump rj2) {
            return this.handleRedo(runtime);
        }
        catch (JumpException.BreakJump bj) {
            return this.handleBreak(context, runtime, bj, callNumber);
        }
        finally {
            this.post(runtime, context, name);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final Block block) {
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, self, name, block, 1);
            return this.jitCompiledScript.__file__(context, self, arg0, block);
        }
        catch (JumpException.ReturnJump rj) {
            return this.handleReturn(context, rj, callNumber);
        }
        catch (JumpException.RedoJump rj2) {
            return this.handleRedo(runtime);
        }
        catch (JumpException.BreakJump bj) {
            return this.handleBreak(context, runtime, bj, callNumber);
        }
        finally {
            this.post(runtime, context, name);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1) {
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, self, name, Block.NULL_BLOCK, 2);
            return this.jitCompiledScript.__file__(context, self, arg0, arg1, Block.NULL_BLOCK);
        }
        catch (JumpException.ReturnJump rj) {
            return this.handleReturn(context, rj, callNumber);
        }
        catch (JumpException.RedoJump rj2) {
            return this.handleRedo(runtime);
        }
        catch (JumpException.BreakJump bj) {
            return this.handleBreak(context, runtime, bj, callNumber);
        }
        finally {
            this.post(runtime, context, name);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, self, name, block, 2);
            return this.jitCompiledScript.__file__(context, self, arg0, arg1, block);
        }
        catch (JumpException.ReturnJump rj) {
            return this.handleReturn(context, rj, callNumber);
        }
        catch (JumpException.RedoJump rj2) {
            return this.handleRedo(runtime);
        }
        catch (JumpException.BreakJump bj) {
            return this.handleBreak(context, runtime, bj, callNumber);
        }
        finally {
            this.post(runtime, context, name);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, self, name, Block.NULL_BLOCK, 3);
            return this.jitCompiledScript.__file__(context, self, arg0, arg1, arg2, Block.NULL_BLOCK);
        }
        catch (JumpException.ReturnJump rj) {
            return this.handleReturn(context, rj, callNumber);
        }
        catch (JumpException.RedoJump rj2) {
            return this.handleRedo(runtime);
        }
        catch (JumpException.BreakJump bj) {
            return this.handleBreak(context, runtime, bj, callNumber);
        }
        finally {
            this.post(runtime, context, name);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, self, name, block, 3);
            return this.jitCompiledScript.__file__(context, self, arg0, arg1, arg2, block);
        }
        catch (JumpException.ReturnJump rj) {
            return this.handleReturn(context, rj, callNumber);
        }
        catch (JumpException.RedoJump rj2) {
            return this.handleRedo(runtime);
        }
        catch (JumpException.BreakJump bj) {
            return this.handleBreak(context, runtime, bj, callNumber);
        }
        finally {
            this.post(runtime, context, name);
        }
    }
    
    protected void pre(final ThreadContext context, final IRubyObject self, final String name, final Block block, final int argsLength) {
        this.callConfig.pre(context, self, this.getImplementationClass(), name, block, this.staticScope);
        this.getArity().checkArity(context.getRuntime(), argsLength);
    }
    
    protected void post(final Ruby runtime, final ThreadContext context, final String name) {
        this.callConfig.post(context);
    }
    
    public ISourcePosition getPosition() {
        return this.position;
    }
    
    public String getFile() {
        return this.position.getFile();
    }
    
    public int getLine() {
        return this.position.getLine();
    }
    
    public Arity getArity() {
        return this.arity;
    }
    
    public ArgsNode getArgsNode() {
        return this.realMethod.getArgsNode();
    }
    
    public DynamicMethod dup() {
        return new JittedMethod(this.getImplementationClass(), this.staticScope, this.jitCompiledScript, this.name, this.callConfig, this.getVisibility(), this.arity, this.position, this.realMethod);
    }
}
