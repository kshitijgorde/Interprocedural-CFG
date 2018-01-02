// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.evaluator.ASTInterpreter;
import org.jruby.Ruby;
import org.jruby.exceptions.JumpException;
import org.jruby.RubyModule;
import org.jruby.ast.NilImplicitNode;
import org.jruby.ast.ListNode;
import org.jruby.runtime.assigner.Pre1ExpandedRest0Post0Assigner;
import org.jruby.runtime.assigner.Pre1ExpandedRest0Post0BlockAssigner;
import org.jruby.runtime.assigner.PreManyRest0Post0Assigner;
import org.jruby.runtime.assigner.PreManyRest0Post0BlockAssigner;
import org.jruby.runtime.assigner.PreManyRest1Post0Assigner;
import org.jruby.runtime.assigner.PreManyRest1Post0BlockAssigner;
import org.jruby.runtime.assigner.Pre3Rest0Post0Assigner;
import org.jruby.runtime.assigner.Pre3Rest0Post0BlockAssigner;
import org.jruby.runtime.assigner.Pre3Rest1Post0Assigner;
import org.jruby.runtime.assigner.Pre3Rest1Post0BlockAssigner;
import org.jruby.runtime.assigner.Pre2Rest0Post0Assigner;
import org.jruby.runtime.assigner.Pre2Rest0Post0BlockAssigner;
import org.jruby.runtime.assigner.Pre2Rest1Post0Assigner;
import org.jruby.runtime.assigner.Pre2Rest1Post0BlockAssigner;
import org.jruby.runtime.assigner.Pre1Rest0Post0Assigner;
import org.jruby.runtime.assigner.Pre1Rest0Post0BlockAssigner;
import org.jruby.runtime.assigner.Pre1Rest1Post0Assigner;
import org.jruby.runtime.assigner.Pre1Rest1Post0BlockAssigner;
import org.jruby.runtime.assigner.Pre0Rest1Post0Assigner;
import org.jruby.runtime.assigner.Pre0Rest1Post0BlockAssigner;
import org.jruby.ast.MultipleAsgnNode;
import org.jruby.runtime.assigner.Pre0Rest0Post0Assigner;
import org.jruby.runtime.assigner.Pre0Rest0Post0BlockAssigner;
import org.jruby.ast.ZeroArgNode;
import org.jruby.ast.NodeType;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.IterNode;
import org.jruby.runtime.assigner.Assigner;
import org.jruby.ast.Node;
import org.jruby.lexer.yacc.ISourcePosition;

public class InterpretedBlock extends ContextAwareBlockBody
{
    private boolean noargblock;
    private final ISourcePosition position;
    private final String file;
    private final int line;
    private final Node bodyNode;
    protected Assigner assigner;
    
    public static Block newInterpretedClosure(final ThreadContext context, final IterNode iterNode, final IRubyObject self) {
        final Binding binding = context.currentBinding(self);
        final NodeType argsNodeId = BlockBody.getArgumentTypeWackyHack(iterNode);
        final BlockBody body = new InterpretedBlock(iterNode, Arity.procArityOf(iterNode.getVarNode()), BlockBody.asArgumentType(argsNodeId));
        return new Block(body, binding);
    }
    
    public static Block newInterpretedClosure(final ThreadContext context, final BlockBody body, final IRubyObject self) {
        final Binding binding = context.currentBinding(self);
        return new Block(body, binding);
    }
    
    public static BlockBody newBlockBody(final IterNode iter, final Arity arity, final int argumentType) {
        return new InterpretedBlock(iter, arity, argumentType);
    }
    
    private void assignerFor(final IterNode iter) {
        final Node varNode = iter.getVarNode();
        final Node block = iter.getBlockVarNode();
        final boolean hasBlock = block != null;
        if (varNode == null || varNode instanceof ZeroArgNode) {
            this.noargblock = !hasBlock;
            this.assigner = (hasBlock ? new Pre0Rest0Post0BlockAssigner(block) : new Pre0Rest0Post0Assigner());
        }
        else if (varNode instanceof MultipleAsgnNode) {
            final MultipleAsgnNode masgn = (MultipleAsgnNode)varNode;
            final int preCount = masgn.getPreCount();
            final boolean isRest = masgn.getRest() != null;
            final Node rest = masgn.getRest();
            final ListNode pre = masgn.getPre();
            this.noargblock = false;
            switch (preCount) {
                case 0: {
                    if (isRest) {
                        this.assigner = (hasBlock ? new Pre0Rest1Post0BlockAssigner(rest, block) : new Pre0Rest1Post0Assigner(rest));
                        break;
                    }
                    if (hasBlock) {
                        this.assigner = new Pre0Rest0Post0BlockAssigner(block);
                        break;
                    }
                    this.noargblock = true;
                    this.assigner = new Pre0Rest0Post0Assigner();
                    break;
                }
                case 1: {
                    if (isRest) {
                        this.assigner = (hasBlock ? new Pre1Rest1Post0BlockAssigner(pre.get(0), rest, block) : new Pre1Rest1Post0Assigner(pre.get(0), rest));
                        break;
                    }
                    if (hasBlock) {
                        this.assigner = new Pre1Rest0Post0BlockAssigner(pre.get(0), block);
                        break;
                    }
                    this.assigner = new Pre1Rest0Post0Assigner(pre.get(0));
                    break;
                }
                case 2: {
                    if (isRest) {
                        this.assigner = (hasBlock ? new Pre2Rest1Post0BlockAssigner(pre.get(0), pre.get(1), rest, block) : new Pre2Rest1Post0Assigner(pre.get(0), pre.get(1), rest));
                        break;
                    }
                    if (hasBlock) {
                        this.assigner = new Pre2Rest0Post0BlockAssigner(pre.get(0), pre.get(1), block);
                        break;
                    }
                    this.assigner = new Pre2Rest0Post0Assigner(pre.get(0), pre.get(1));
                    break;
                }
                case 3: {
                    if (isRest) {
                        this.assigner = (hasBlock ? new Pre3Rest1Post0BlockAssigner(pre.get(0), pre.get(1), pre.get(2), rest, block) : new Pre3Rest1Post0Assigner(pre.get(0), pre.get(1), pre.get(2), rest));
                        break;
                    }
                    if (hasBlock) {
                        this.assigner = new Pre3Rest0Post0BlockAssigner(pre.get(0), pre.get(1), pre.get(2), block);
                        break;
                    }
                    this.assigner = new Pre3Rest0Post0Assigner(pre.get(0), pre.get(1), pre.get(2));
                    break;
                }
                default: {
                    if (isRest) {
                        this.assigner = (hasBlock ? new PreManyRest1Post0BlockAssigner(pre, preCount, rest, block) : new PreManyRest1Post0Assigner(pre, preCount, rest));
                        break;
                    }
                    if (hasBlock) {
                        this.assigner = new PreManyRest0Post0BlockAssigner(pre, preCount, block);
                        break;
                    }
                    this.assigner = new PreManyRest0Post0Assigner(pre, preCount);
                    break;
                }
            }
        }
        else {
            this.assigner = (hasBlock ? new Pre1ExpandedRest0Post0BlockAssigner(varNode, block) : new Pre1ExpandedRest0Post0Assigner(varNode));
        }
    }
    
    public InterpretedBlock(final IterNode iterNode, final int argumentType) {
        this(iterNode, Arity.procArityOf((iterNode == null) ? null : iterNode.getVarNode()), argumentType);
    }
    
    public InterpretedBlock(final IterNode iterNode, final Arity arity, final int argumentType) {
        super(iterNode.getScope(), arity, argumentType);
        this.bodyNode = ((iterNode.getBodyNode() == null) ? NilImplicitNode.NIL : iterNode.getBodyNode());
        this.scope = iterNode.getScope();
        this.position = iterNode.getPosition();
        this.file = this.position.getFile();
        this.line = this.position.getLine();
        this.assignerFor(iterNode);
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final Binding binding, final Block.Type type) {
        return this.yield(context, binding, type);
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final IRubyObject arg0, final Binding binding, final Block.Type type) {
        final Visibility oldVis = binding.getFrame().getVisibility();
        final Frame lastFrame = this.pre(context, null, binding);
        final IRubyObject self = this.prepareSelf(binding);
        try {
            if (!this.noargblock) {
                this.assigner.assign(context.getRuntime(), context, self, arg0, Block.NULL_BLOCK);
            }
            return this.evalBlockBody(context, binding, self);
        }
        catch (JumpException.NextJump nj) {
            return this.handleNextJump(context, nj, type);
        }
        finally {
            this.post(context, binding, oldVis, lastFrame);
        }
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Binding binding, final Block.Type type) {
        final Visibility oldVis = binding.getFrame().getVisibility();
        final Frame lastFrame = this.pre(context, null, binding);
        final IRubyObject self = this.prepareSelf(binding);
        try {
            if (!this.noargblock) {
                this.assigner.assign(context.getRuntime(), context, self, arg0, arg1, Block.NULL_BLOCK);
            }
            return this.evalBlockBody(context, binding, self);
        }
        catch (JumpException.NextJump nj) {
            return this.handleNextJump(context, nj, type);
        }
        finally {
            this.post(context, binding, oldVis, lastFrame);
        }
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Binding binding, final Block.Type type) {
        final Visibility oldVis = binding.getFrame().getVisibility();
        final Frame lastFrame = this.pre(context, null, binding);
        final IRubyObject self = this.prepareSelf(binding);
        try {
            if (!this.noargblock) {
                this.assigner.assign(context.getRuntime(), context, self, arg0, arg1, arg2, Block.NULL_BLOCK);
            }
            return this.evalBlockBody(context, binding, self);
        }
        catch (JumpException.NextJump nj) {
            return this.handleNextJump(context, nj, type);
        }
        finally {
            this.post(context, binding, oldVis, lastFrame);
        }
    }
    
    public IRubyObject yield(final ThreadContext context, final Binding binding, final Block.Type type) {
        final IRubyObject self = this.prepareSelf(binding);
        final Visibility oldVis = binding.getFrame().getVisibility();
        final Frame lastFrame = this.pre(context, null, binding);
        try {
            if (!this.noargblock) {
                this.assigner.assign(context.getRuntime(), context, self, Block.NULL_BLOCK);
            }
            return this.evalBlockBody(context, binding, self);
        }
        catch (JumpException.NextJump nj) {
            return this.handleNextJump(context, nj, type);
        }
        finally {
            this.post(context, binding, oldVis, lastFrame);
        }
    }
    
    public IRubyObject yield(final ThreadContext context, final IRubyObject value, final Binding binding, final Block.Type type) {
        return this.yield(context, value, binding, type, Block.NULL_BLOCK);
    }
    
    public IRubyObject yield(final ThreadContext context, IRubyObject value, IRubyObject self, final RubyModule klass, final boolean alreadyArray, final Binding binding, final Block.Type type, final Block block) {
        if (klass == null) {
            self = this.prepareSelf(binding);
        }
        final Visibility oldVis = binding.getFrame().getVisibility();
        final Frame lastFrame = this.pre(context, klass, binding);
        final Ruby runtime = context.getRuntime();
        try {
            if (!this.noargblock) {
                value = (alreadyArray ? this.assigner.convertIfAlreadyArray(runtime, value) : this.assigner.convertToArray(runtime, value));
                this.assigner.assignArray(runtime, context, self, value, block);
            }
            return this.evalBlockBody(context, binding, self);
        }
        catch (JumpException.NextJump nj) {
            return this.handleNextJump(context, nj, type);
        }
        finally {
            this.post(context, binding, oldVis, lastFrame);
        }
    }
    
    public IRubyObject yield(final ThreadContext context, final IRubyObject value, final Binding binding, final Block.Type type, final Block block) {
        final IRubyObject self = this.prepareSelf(binding);
        final Visibility oldVis = binding.getFrame().getVisibility();
        final Frame lastFrame = this.pre(context, null, binding);
        try {
            if (!this.noargblock) {
                this.assigner.assignArray(context.getRuntime(), context, self, this.assigner.convertToArray(context.getRuntime(), value), block);
            }
            return this.evalBlockBody(context, binding, self);
        }
        catch (JumpException.NextJump nj) {
            return this.handleNextJump(context, nj, type);
        }
        finally {
            this.post(context, binding, oldVis, lastFrame);
        }
    }
    
    public IRubyObject yield(final ThreadContext context, final IRubyObject value, final IRubyObject self, final RubyModule klass, final boolean alreadyArray, final Binding binding, final Block.Type type) {
        return this.yield(context, value, self, klass, alreadyArray, binding, type, Block.NULL_BLOCK);
    }
    
    private IRubyObject evalBlockBody(final ThreadContext context, final Binding binding, final IRubyObject self) {
        try {
            return ASTInterpreter.INTERPRET_BLOCK(context.getRuntime(), context, this.file, this.line, this.bodyNode, binding.getMethod(), self, Block.NULL_BLOCK);
        }
        catch (JumpException.RedoJump rj) {
            context.pollThreadEvents();
            return ASTInterpreter.INTERPRET_BLOCK(context.getRuntime(), context, this.file, this.line, this.bodyNode, binding.getMethod(), self, Block.NULL_BLOCK);
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
    
    public Node getBodyNode() {
        return this.bodyNode;
    }
    
    public String getFile() {
        return this.position.getFile();
    }
    
    public int getLine() {
        return this.position.getLine();
    }
}
