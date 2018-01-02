// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.runtime.Arity;
import org.jruby.Ruby;
import org.jruby.exceptions.JumpException;
import org.jruby.evaluator.ASTInterpreter;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.compiler.ASTInspector;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.ast.ArgsNode;
import org.jruby.ast.Node;
import org.jruby.parser.StaticScope;
import org.jruby.runtime.PositionAware;

public class InterpretedMethod extends DynamicMethod implements MethodArgs, PositionAware
{
    private StaticScope staticScope;
    private Node body;
    private ArgsNode argsNode;
    private ISourcePosition position;
    private String file;
    private int line;
    private boolean needsScope;
    
    public InterpretedMethod(final RubyModule implementationClass, final StaticScope staticScope, final Node body, final String name, final ArgsNode argsNode, final Visibility visibility, final ISourcePosition position) {
        super(implementationClass, visibility, CallConfiguration.FrameFullScopeFull, name);
        this.body = body;
        this.staticScope = staticScope;
        this.argsNode = argsNode;
        this.position = position;
        this.file = position.getFile();
        this.line = position.getLine();
        final ASTInspector inspector = new ASTInspector();
        inspector.inspect(body);
        inspector.inspect(argsNode);
        this.needsScope = true;
        assert argsNode != null;
    }
    
    public Node getBodyNode() {
        return this.body;
    }
    
    public ArgsNode getArgsNode() {
        return this.argsNode;
    }
    
    public StaticScope getStaticScope() {
        return this.staticScope;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
        assert args != null;
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, name, self, block, runtime);
            this.argsNode.checkArgCount(runtime, args.length);
            this.argsNode.prepare(context, runtime, self, args, block);
            return ASTInterpreter.INTERPRET_METHOD(runtime, context, this.file, this.line, this.getImplementationClass(), this.body, name, self, block, this.isTraceable());
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
        return this.call(context, self, clazz, name, args, Block.NULL_BLOCK);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name) {
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, name, self, Block.NULL_BLOCK, runtime);
            this.argsNode.checkArgCount(runtime, 0);
            this.argsNode.prepare(context, runtime, self, Block.NULL_BLOCK);
            return ASTInterpreter.INTERPRET_METHOD(runtime, context, this.file, this.line, this.getImplementationClass(), this.body, name, self, Block.NULL_BLOCK, this.isTraceable());
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
            this.pre(context, name, self, block, runtime);
            this.argsNode.checkArgCount(runtime, 0);
            this.argsNode.prepare(context, runtime, self, block);
            return ASTInterpreter.INTERPRET_METHOD(runtime, context, this.file, this.line, this.getImplementationClass(), this.body, name, self, block, this.isTraceable());
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
            this.pre(context, name, self, Block.NULL_BLOCK, runtime);
            this.argsNode.checkArgCount(runtime, 1);
            this.argsNode.prepare(context, runtime, self, arg0, Block.NULL_BLOCK);
            return ASTInterpreter.INTERPRET_METHOD(runtime, context, this.file, this.line, this.getImplementationClass(), this.body, name, self, Block.NULL_BLOCK, this.isTraceable());
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
            this.pre(context, name, self, block, runtime);
            this.argsNode.checkArgCount(runtime, 1);
            this.argsNode.prepare(context, runtime, self, arg0, block);
            return ASTInterpreter.INTERPRET_METHOD(runtime, context, this.file, this.line, this.getImplementationClass(), this.body, name, self, block, this.isTraceable());
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
            this.pre(context, name, self, Block.NULL_BLOCK, runtime);
            this.argsNode.checkArgCount(runtime, 2);
            this.argsNode.prepare(context, runtime, self, arg0, arg1, Block.NULL_BLOCK);
            return ASTInterpreter.INTERPRET_METHOD(runtime, context, this.file, this.line, this.getImplementationClass(), this.body, name, self, Block.NULL_BLOCK, this.isTraceable());
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
            this.pre(context, name, self, block, runtime);
            this.argsNode.checkArgCount(runtime, 2);
            this.argsNode.prepare(context, runtime, self, arg0, arg1, block);
            return ASTInterpreter.INTERPRET_METHOD(runtime, context, this.file, this.line, this.getImplementationClass(), this.body, name, self, block, this.isTraceable());
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
            this.pre(context, name, self, Block.NULL_BLOCK, runtime);
            this.argsNode.checkArgCount(runtime, 3);
            this.argsNode.prepare(context, runtime, self, arg0, arg1, arg2, Block.NULL_BLOCK);
            return ASTInterpreter.INTERPRET_METHOD(runtime, context, this.file, this.line, this.getImplementationClass(), this.body, name, self, Block.NULL_BLOCK, this.isTraceable());
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
            this.pre(context, name, self, block, runtime);
            this.argsNode.checkArgCount(runtime, 3);
            this.argsNode.prepare(context, runtime, self, arg0, arg1, arg2, block);
            return ASTInterpreter.INTERPRET_METHOD(runtime, context, this.file, this.line, this.getImplementationClass(), this.body, name, self, block, this.isTraceable());
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
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3) {
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, name, self, Block.NULL_BLOCK, runtime);
            this.argsNode.checkArgCount(runtime, 4);
            this.argsNode.prepare(context, runtime, self, arg0, arg1, arg2, arg3, Block.NULL_BLOCK);
            return ASTInterpreter.INTERPRET_METHOD(runtime, context, this.file, this.line, this.getImplementationClass(), this.body, name, self, Block.NULL_BLOCK, this.isTraceable());
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
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final Block block) {
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, name, self, block, runtime);
            this.argsNode.checkArgCount(runtime, 4);
            this.argsNode.prepare(context, runtime, self, arg0, arg1, arg2, arg3, block);
            return ASTInterpreter.INTERPRET_METHOD(runtime, context, this.file, this.line, this.getImplementationClass(), this.body, name, self, block, this.isTraceable());
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
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4) {
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, name, self, Block.NULL_BLOCK, runtime);
            this.argsNode.checkArgCount(runtime, 5);
            this.argsNode.prepare(context, runtime, self, arg0, arg1, arg2, arg3, arg4, Block.NULL_BLOCK);
            return ASTInterpreter.INTERPRET_METHOD(runtime, context, this.file, this.line, this.getImplementationClass(), this.body, name, self, Block.NULL_BLOCK, this.isTraceable());
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
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final Block block) {
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, name, self, block, runtime);
            this.argsNode.checkArgCount(runtime, 5);
            this.argsNode.prepare(context, runtime, self, arg0, arg1, arg2, arg3, arg4, block);
            return ASTInterpreter.INTERPRET_METHOD(runtime, context, this.file, this.line, this.getImplementationClass(), this.body, name, self, block, this.isTraceable());
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
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5) {
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, name, self, Block.NULL_BLOCK, runtime);
            this.argsNode.checkArgCount(runtime, 6);
            this.argsNode.prepare(context, runtime, self, arg0, arg1, arg2, arg3, arg4, arg5, Block.NULL_BLOCK);
            return ASTInterpreter.INTERPRET_METHOD(runtime, context, this.file, this.line, this.getImplementationClass(), this.body, name, self, Block.NULL_BLOCK, this.isTraceable());
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
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final Block block) {
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, name, self, block, runtime);
            this.argsNode.checkArgCount(runtime, 6);
            this.argsNode.prepare(context, runtime, self, arg0, arg1, arg2, arg3, arg4, arg5, block);
            return ASTInterpreter.INTERPRET_METHOD(runtime, context, this.file, this.line, this.getImplementationClass(), this.body, name, self, block, this.isTraceable());
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
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6) {
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, name, self, Block.NULL_BLOCK, runtime);
            this.argsNode.checkArgCount(runtime, 7);
            this.argsNode.prepare(context, runtime, self, arg0, arg1, arg2, arg3, arg4, arg5, arg6, Block.NULL_BLOCK);
            return ASTInterpreter.INTERPRET_METHOD(runtime, context, this.file, this.line, this.getImplementationClass(), this.body, name, self, Block.NULL_BLOCK, this.isTraceable());
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
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final Block block) {
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, name, self, block, runtime);
            this.argsNode.checkArgCount(runtime, 7);
            this.argsNode.prepare(context, runtime, self, arg0, arg1, arg2, arg3, arg4, arg5, arg6, block);
            return ASTInterpreter.INTERPRET_METHOD(runtime, context, this.file, this.line, this.getImplementationClass(), this.body, name, self, block, this.isTraceable());
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
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final IRubyObject arg7) {
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, name, self, Block.NULL_BLOCK, runtime);
            this.argsNode.checkArgCount(runtime, 8);
            this.argsNode.prepare(context, runtime, self, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, Block.NULL_BLOCK);
            return ASTInterpreter.INTERPRET_METHOD(runtime, context, this.file, this.line, this.getImplementationClass(), this.body, name, self, Block.NULL_BLOCK, this.isTraceable());
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
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final IRubyObject arg7, final Block block) {
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, name, self, block, runtime);
            this.argsNode.checkArgCount(runtime, 8);
            this.argsNode.prepare(context, runtime, self, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, block);
            return ASTInterpreter.INTERPRET_METHOD(runtime, context, this.file, this.line, this.getImplementationClass(), this.body, name, self, block, this.isTraceable());
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
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final IRubyObject arg7, final IRubyObject arg8) {
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, name, self, Block.NULL_BLOCK, runtime);
            this.argsNode.checkArgCount(runtime, 9);
            this.argsNode.prepare(context, runtime, self, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, Block.NULL_BLOCK);
            return ASTInterpreter.INTERPRET_METHOD(runtime, context, this.file, this.line, this.getImplementationClass(), this.body, name, self, Block.NULL_BLOCK, this.isTraceable());
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
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final IRubyObject arg7, final IRubyObject arg8, final Block block) {
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, name, self, block, runtime);
            this.argsNode.checkArgCount(runtime, 9);
            this.argsNode.prepare(context, runtime, self, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, block);
            return ASTInterpreter.INTERPRET_METHOD(runtime, context, this.file, this.line, this.getImplementationClass(), this.body, name, self, block, this.isTraceable());
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
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final IRubyObject arg7, final IRubyObject arg8, final IRubyObject arg9) {
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, name, self, Block.NULL_BLOCK, runtime);
            this.argsNode.checkArgCount(runtime, 10);
            this.argsNode.prepare(context, runtime, self, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, Block.NULL_BLOCK);
            return ASTInterpreter.INTERPRET_METHOD(runtime, context, this.file, this.line, this.getImplementationClass(), this.body, name, self, Block.NULL_BLOCK, this.isTraceable());
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
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final IRubyObject arg7, final IRubyObject arg8, final IRubyObject arg9, final Block block) {
        final Ruby runtime = context.getRuntime();
        final int callNumber = context.callNumber;
        try {
            this.pre(context, name, self, block, runtime);
            this.argsNode.checkArgCount(runtime, 10);
            this.argsNode.prepare(context, runtime, self, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, block);
            return ASTInterpreter.INTERPRET_METHOD(runtime, context, this.file, this.line, this.getImplementationClass(), this.body, name, self, block, this.isTraceable());
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
    
    protected void pre(final ThreadContext context, final String name, final IRubyObject self, final Block block, final Ruby runtime) {
        if (this.needsScope) {
            context.preMethodFrameAndScope(this.getImplementationClass(), name, self, block, this.staticScope);
        }
        else {
            context.preMethodFrameAndDummyScope(this.getImplementationClass(), name, self, block, this.staticScope);
        }
    }
    
    protected void post(final Ruby runtime, final ThreadContext context, final String name) {
        context.postMethodFrameAndScope();
    }
    
    protected boolean isTraceable() {
        return false;
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
        return this.argsNode.getArity();
    }
    
    public DynamicMethod dup() {
        return new InterpretedMethod(this.getImplementationClass(), this.staticScope, this.body, this.name, this.argsNode, this.getVisibility(), this.position);
    }
}
