// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.Ruby;
import org.jruby.ast.ArgsNoArgNode;
import org.jruby.common.IRubyWarnings;
import org.jruby.RubyArray;
import org.jruby.ast.util.ArgsUtil;
import org.jruby.evaluator.ASTInterpreter;
import org.jruby.exceptions.JumpException;
import org.jruby.RubyModule;
import org.jruby.ast.NilImplicitNode;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.ast.LambdaNode;
import org.jruby.ast.IterNode;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.Node;
import org.jruby.ast.ArgsNode;
import org.jruby.lexer.yacc.ISourcePosition;

public class Interpreted19Block extends ContextAwareBlockBody
{
    private static final boolean ALREADY_ARRAY = true;
    private final ISourcePosition position;
    private final String file;
    private final int line;
    private final ArgsNode args;
    private final String[] parameterList;
    private final Node body;
    
    public static Block newInterpretedClosure(final ThreadContext context, final BlockBody body, final IRubyObject self) {
        final Binding binding = context.currentBinding(self);
        return new Block(body, binding);
    }
    
    public static BlockBody newBlockBody(final IterNode iter) {
        if (iter instanceof LambdaNode) {
            return new Interpreted19Block((LambdaNode)iter);
        }
        return new Interpreted19Block(iter);
    }
    
    public Interpreted19Block(final IterNode iterNode) {
        super(iterNode.getScope(), ((ArgsNode)iterNode.getVarNode()).getArity(), -1);
        this.args = (ArgsNode)iterNode.getVarNode();
        this.parameterList = RuntimeHelpers.encodeParameterList(this.args).split(";");
        this.body = ((iterNode.getBodyNode() == null) ? NilImplicitNode.NIL : iterNode.getBodyNode());
        this.position = iterNode.getPosition();
        this.file = this.position.getFile();
        this.line = this.position.getLine();
    }
    
    public Interpreted19Block(final LambdaNode lambdaNode) {
        super(lambdaNode.getScope(), lambdaNode.getArgs().getArity(), -1);
        this.args = lambdaNode.getArgs();
        this.parameterList = RuntimeHelpers.encodeParameterList(this.args).split(";");
        this.body = ((lambdaNode.getBody() == null) ? NilImplicitNode.NIL : lambdaNode.getBody());
        this.position = lambdaNode.getPosition();
        this.file = this.position.getFile();
        this.line = this.position.getLine();
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject[] args, final Binding binding, final Block.Type type) {
        final IRubyObject value = (args.length == 1) ? args[0] : context.getRuntime().newArrayNoCopy(args);
        return this.yield(context, value, null, null, true, binding, type, Block.NULL_BLOCK);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject[] args, final Binding binding, final Block.Type type, final Block block) {
        return this.yield(context, context.getRuntime().newArrayNoCopy(args), null, null, true, binding, type, block);
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
        final IRubyObject self = this.prepareSelf(binding);
        final Visibility oldVis = binding.getFrame().getVisibility();
        final Frame lastFrame = this.pre(context, null, binding);
        try {
            this.setupBlockArg(context, value, self, Block.NULL_BLOCK, type);
            return this.evalBlockBody(context, binding, self);
        }
        catch (JumpException.NextJump nj) {
            return this.handleNextJump(context, nj, type);
        }
        finally {
            this.post(context, binding, oldVis, lastFrame);
        }
    }
    
    public IRubyObject yield(final ThreadContext context, final IRubyObject value, final IRubyObject self, final RubyModule klass, final boolean aValue, final Binding binding, final Block.Type type) {
        return this.yield(context, value, self, klass, aValue, binding, type, Block.NULL_BLOCK);
    }
    
    public IRubyObject yield(final ThreadContext context, final IRubyObject value, IRubyObject self, final RubyModule klass, final boolean aValue, final Binding binding, final Block.Type type, final Block block) {
        if (klass == null) {
            self = this.prepareSelf(binding);
        }
        final Visibility oldVis = binding.getFrame().getVisibility();
        final Frame lastFrame = this.pre(context, klass, binding);
        try {
            this.setupBlockArgs(context, value, self, block, type, aValue);
            return this.evalBlockBody(context, binding, self);
        }
        catch (JumpException.NextJump nj) {
            return this.handleNextJump(context, nj, type);
        }
        finally {
            this.post(context, binding, oldVis, lastFrame);
        }
    }
    
    private IRubyObject evalBlockBody(final ThreadContext context, final Binding binding, final IRubyObject self) {
        try {
            return ASTInterpreter.INTERPRET_BLOCK(context.getRuntime(), context, this.file, this.line, this.body, binding.getMethod(), self, Block.NULL_BLOCK);
        }
        catch (JumpException.RedoJump rj) {
            context.pollThreadEvents();
            return ASTInterpreter.INTERPRET_BLOCK(context.getRuntime(), context, this.file, this.line, this.body, binding.getMethod(), self, Block.NULL_BLOCK);
        }
        catch (StackOverflowError soe) {
            throw context.getRuntime().newSystemStackError("stack level too deep", soe);
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
    
    private IRubyObject convertIfAlreadyArray(final ThreadContext context, IRubyObject value) {
        final int length = ArgsUtil.arrayLength(value);
        switch (length) {
            case 0: {
                value = context.getRuntime().getNil();
                break;
            }
            case 1: {
                value = ((RubyArray)value).eltInternal(0);
                break;
            }
            default: {
                context.getRuntime().getWarnings().warn(IRubyWarnings.ID.MULTIPLE_VALUES_FOR_BLOCK, "multiple values for a block parameter (" + length + " for 1)", new Object[0]);
                break;
            }
        }
        return value;
    }
    
    private void setupBlockArg(final ThreadContext context, final IRubyObject value, final IRubyObject self, final Block block, final Block.Type type) {
        final int requiredCount = this.args.getRequiredArgsCount();
        final boolean isRest = this.args.getRestArg() != -1;
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
        if (!(this.args instanceof ArgsNoArgNode)) {
            final Ruby runtime = context.getRuntime();
            this.args.prepare(context, runtime, self, parameters, block);
        }
    }
    
    private void setupBlockArgs(final ThreadContext context, final IRubyObject value, final IRubyObject self, final Block block, final Block.Type type, final boolean alreadyArray) {
        final int requiredCount = this.args.getRequiredArgsCount();
        final boolean isRest = this.args.getRestArg() != -1;
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
        if (!(this.args instanceof ArgsNoArgNode)) {
            final Ruby runtime = context.getRuntime();
            this.args.prepare(context, runtime, self, parameters, block);
        }
    }
    
    public ArgsNode getArgs() {
        return this.args;
    }
    
    public Node getBody() {
        return this.body;
    }
    
    public String getFile() {
        return this.position.getFile();
    }
    
    public int getLine() {
        return this.position.getLine();
    }
    
    public String[] getParameterList() {
        return this.parameterList;
    }
}
