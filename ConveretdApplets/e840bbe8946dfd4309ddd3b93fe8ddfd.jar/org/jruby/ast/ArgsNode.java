// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import java.util.List;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.RubyArray;
import org.jruby.runtime.DynamicScope;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.visitor.NodeVisitor;
import java.util.Iterator;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.runtime.Arity;

public class ArgsNode extends Node
{
    private final ListNode pre;
    private final int preCount;
    private final ListNode optArgs;
    protected final ArgumentNode restArgNode;
    protected final int restArg;
    private final BlockArgNode blockArgNode;
    protected Arity arity;
    private final int requiredArgsCount;
    protected final boolean hasOptArgs;
    protected final boolean hasMasgnArgs;
    protected int maxArgsCount;
    protected final boolean isSimple;
    private final ListNode post;
    private final int postCount;
    private final int postIndex;
    
    public ArgsNode(final ISourcePosition position, final ListNode pre, final ListNode optionalArguments, final RestArgNode rest, final ListNode post, final BlockArgNode blockArgNode) {
        super(position);
        this.pre = pre;
        this.preCount = ((pre == null) ? 0 : pre.size());
        this.post = post;
        this.postCount = ((post == null) ? 0 : post.size());
        final int optArgCount = (optionalArguments == null) ? 0 : optionalArguments.size();
        this.postIndex = this.getPostCount(this.preCount, optArgCount, rest);
        this.optArgs = optionalArguments;
        this.restArg = ((rest == null) ? -1 : rest.getIndex());
        this.restArgNode = rest;
        this.blockArgNode = blockArgNode;
        this.requiredArgsCount = this.preCount + this.postCount;
        this.hasOptArgs = (this.getOptArgs() != null);
        this.hasMasgnArgs = this.hasMasgnArgs();
        this.maxArgsCount = ((this.getRestArg() >= 0) ? -1 : (this.getRequiredArgsCount() + this.getOptionalArgsCount()));
        this.arity = this.calculateArity();
        this.isSimple = (!this.hasMasgnArgs && !this.hasOptArgs && this.restArg < 0 && this.postCount <= 0);
    }
    
    private int getPostCount(final int preCount, final int optArgCount, final RestArgNode rest) {
        if (rest != null) {
            return rest.getIndex() + 1;
        }
        return preCount + optArgCount;
    }
    
    public NodeType getNodeType() {
        return NodeType.ARGSNODE;
    }
    
    protected Arity calculateArity() {
        if (this.getOptArgs() != null || this.getRestArg() >= 0) {
            return Arity.required(this.getRequiredArgsCount());
        }
        return Arity.createArity(this.getRequiredArgsCount());
    }
    
    protected boolean hasMasgnArgs() {
        if (this.preCount > 0) {
            for (final Node node : this.pre.childNodes()) {
                if (node instanceof AssignableNode) {
                    return true;
                }
            }
        }
        if (this.postCount > 0) {
            for (final Node node : this.post.childNodes()) {
                if (node instanceof AssignableNode) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitArgsNode(this);
    }
    
    public ListNode getPre() {
        return this.pre;
    }
    
    @Deprecated
    public ListNode getArgs() {
        return this.pre;
    }
    
    public Arity getArity() {
        return this.arity;
    }
    
    public int getRequiredArgsCount() {
        return this.requiredArgsCount;
    }
    
    public int getOptionalArgsCount() {
        return (this.optArgs == null) ? 0 : this.optArgs.size();
    }
    
    public ListNode getPost() {
        return this.post;
    }
    
    public int getMaxArgumentsCount() {
        return this.maxArgsCount;
    }
    
    public ListNode getOptArgs() {
        return this.optArgs;
    }
    
    public int getRestArg() {
        return this.restArg;
    }
    
    public ArgumentNode getRestArgNode() {
        return this.restArgNode;
    }
    
    @Deprecated
    public BlockArgNode getBlockArgNode() {
        return this.blockArgNode;
    }
    
    public BlockArgNode getBlock() {
        return this.blockArgNode;
    }
    
    public int getPostCount() {
        return this.postCount;
    }
    
    public int getPostIndex() {
        return this.postIndex;
    }
    
    public int getPreCount() {
        return this.preCount;
    }
    
    public void prepare(final ThreadContext context, final Ruby runtime, final IRubyObject self, final IRubyObject[] args, final Block block) {
        final DynamicScope scope = context.getCurrentScope();
        if (!this.hasMasgnArgs) {
            if (this.preCount > 0) {
                scope.setArgValues(args, Math.min(args.length, this.preCount));
            }
            if (this.postCount > 0 && args.length > this.preCount) {
                scope.setEndArgValues(args, this.postIndex, Math.min(args.length - this.preCount, this.postCount));
            }
        }
        else {
            this.masgnAwareArgAssign(context, runtime, self, args, block, scope);
        }
        if (this.hasOptArgs || this.restArg != -1) {
            this.prepareOptOrRestArgs(context, runtime, scope, self, args);
        }
        if (this.getBlock() != null) {
            this.processBlockArg(scope, runtime, block);
        }
    }
    
    private void masgnAwareArgAssign(final ThreadContext context, final Ruby runtime, final IRubyObject self, final IRubyObject[] args, final Block block, final DynamicScope scope) {
        if (this.preCount > 0) {
            for (int size = this.pre.size(), i = 0; i < size; ++i) {
                final Node next = this.pre.get(i);
                if (next instanceof AssignableNode) {
                    ((AssignableNode)next).assign(runtime, context, self, args[i], block, false);
                }
                else {
                    if (!(next instanceof ArgumentNode)) {
                        throw new RuntimeException("Whoa..not assignable and not an argument...what is it: " + next);
                    }
                    final ArgumentNode argNode = (ArgumentNode)next;
                    scope.setValue(argNode.getIndex(), args[i], argNode.getDepth());
                }
            }
        }
        if (this.postCount > 0) {
            final int size = this.post.size();
            final int argsLength = args.length;
            for (int j = 0; j < size; ++j) {
                final Node next2 = this.post.get(j);
                if (next2 instanceof AssignableNode) {
                    ((AssignableNode)next2).assign(runtime, context, self, args[argsLength - this.postCount + j], block, false);
                }
                else {
                    if (!(next2 instanceof ArgumentNode)) {
                        throw new RuntimeException("Whoa..not assignable and not an argument...what is it: " + next2);
                    }
                    final ArgumentNode argNode2 = (ArgumentNode)next2;
                    scope.setValue(argNode2.getIndex(), args[argsLength - this.postCount + j], argNode2.getDepth());
                }
            }
        }
    }
    
    public void prepare(final ThreadContext context, final Ruby runtime, final IRubyObject self, final Block block) {
        final DynamicScope scope = context.getCurrentScope();
        if (this.isSimple) {
            scope.setArgValues();
        }
        else {
            this.prepare(context, runtime, self, IRubyObject.NULL_ARRAY, block);
        }
        if (this.getBlock() != null) {
            this.processBlockArg(scope, runtime, block);
        }
    }
    
    public void prepare(final ThreadContext context, final Ruby runtime, final IRubyObject self, final IRubyObject arg0, final Block block) {
        final DynamicScope scope = context.getCurrentScope();
        if (this.isSimple) {
            scope.setArgValues(arg0);
        }
        else {
            this.prepare(context, runtime, self, new IRubyObject[] { arg0 }, block);
        }
        if (this.getBlock() != null) {
            this.processBlockArg(scope, runtime, block);
        }
    }
    
    public void prepare(final ThreadContext context, final Ruby runtime, final IRubyObject self, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        final DynamicScope scope = context.getCurrentScope();
        if (this.isSimple) {
            scope.setArgValues(arg0, arg1);
        }
        else {
            this.prepare(context, runtime, self, new IRubyObject[] { arg0, arg1 }, block);
        }
        if (this.getBlock() != null) {
            this.processBlockArg(scope, runtime, block);
        }
    }
    
    public void prepare(final ThreadContext context, final Ruby runtime, final IRubyObject self, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        final DynamicScope scope = context.getCurrentScope();
        if (this.isSimple) {
            scope.setArgValues(arg0, arg1, arg2);
        }
        else {
            this.prepare(context, runtime, self, new IRubyObject[] { arg0, arg1, arg2 }, block);
        }
        if (this.getBlock() != null) {
            this.processBlockArg(scope, runtime, block);
        }
    }
    
    public void prepare(final ThreadContext context, final Ruby runtime, final IRubyObject self, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final Block block) {
        final DynamicScope scope = context.getCurrentScope();
        if (this.isSimple) {
            scope.setArgValues(arg0, arg1, arg2, arg3);
        }
        else {
            this.prepare(context, runtime, self, new IRubyObject[] { arg0, arg1, arg2, arg3 }, block);
        }
        if (this.getBlock() != null) {
            this.processBlockArg(scope, runtime, block);
        }
    }
    
    public void prepare(final ThreadContext context, final Ruby runtime, final IRubyObject self, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final Block block) {
        final DynamicScope scope = context.getCurrentScope();
        if (this.isSimple) {
            scope.setArgValues(arg0, arg1, arg2, arg3, arg4);
        }
        else {
            this.prepare(context, runtime, self, new IRubyObject[] { arg0, arg1, arg2, arg3, arg4 }, block);
        }
        if (this.getBlock() != null) {
            this.processBlockArg(scope, runtime, block);
        }
    }
    
    public void prepare(final ThreadContext context, final Ruby runtime, final IRubyObject self, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final Block block) {
        final DynamicScope scope = context.getCurrentScope();
        if (this.isSimple) {
            scope.setArgValues(arg0, arg1, arg2, arg3, arg4, arg5);
        }
        else {
            this.prepare(context, runtime, self, new IRubyObject[] { arg0, arg1, arg2, arg3, arg4, arg5 }, block);
        }
        if (this.getBlock() != null) {
            this.processBlockArg(scope, runtime, block);
        }
    }
    
    public void prepare(final ThreadContext context, final Ruby runtime, final IRubyObject self, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final Block block) {
        final DynamicScope scope = context.getCurrentScope();
        if (this.isSimple) {
            scope.setArgValues(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
        }
        else {
            this.prepare(context, runtime, self, new IRubyObject[] { arg0, arg1, arg2, arg3, arg4, arg5, arg6 }, block);
        }
        if (this.getBlock() != null) {
            this.processBlockArg(scope, runtime, block);
        }
    }
    
    public void prepare(final ThreadContext context, final Ruby runtime, final IRubyObject self, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final IRubyObject arg7, final Block block) {
        final DynamicScope scope = context.getCurrentScope();
        if (this.isSimple) {
            scope.setArgValues(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
        }
        else {
            this.prepare(context, runtime, self, new IRubyObject[] { arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7 }, block);
        }
        if (this.getBlock() != null) {
            this.processBlockArg(scope, runtime, block);
        }
    }
    
    public void prepare(final ThreadContext context, final Ruby runtime, final IRubyObject self, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final IRubyObject arg7, final IRubyObject arg8, final Block block) {
        final DynamicScope scope = context.getCurrentScope();
        if (this.isSimple) {
            scope.setArgValues(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
        }
        else {
            this.prepare(context, runtime, self, new IRubyObject[] { arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8 }, block);
        }
        if (this.getBlock() != null) {
            this.processBlockArg(scope, runtime, block);
        }
    }
    
    public void prepare(final ThreadContext context, final Ruby runtime, final IRubyObject self, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final IRubyObject arg7, final IRubyObject arg8, final IRubyObject arg9, final Block block) {
        final DynamicScope scope = context.getCurrentScope();
        if (this.isSimple) {
            scope.setArgValues(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
        }
        else {
            this.prepare(context, runtime, self, new IRubyObject[] { arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9 }, block);
        }
        if (this.getBlock() != null) {
            this.processBlockArg(scope, runtime, block);
        }
    }
    
    public void checkArgCount(final Ruby runtime, final int argsLength) {
        Arity.checkArgumentCount(runtime, argsLength, this.requiredArgsCount, this.maxArgsCount);
    }
    
    protected void prepareOptOrRestArgs(final ThreadContext context, final Ruby runtime, final DynamicScope scope, final IRubyObject self, final IRubyObject[] args) {
        this.prepareRestArg(context, runtime, scope, args, this.prepareOptionalArguments(context, runtime, self, args));
    }
    
    protected int prepareOptionalArguments(final ThreadContext context, final Ruby runtime, final IRubyObject self, final IRubyObject[] args) {
        return this.hasOptArgs ? this.assignOptArgs(args, runtime, context, self, this.preCount) : this.preCount;
    }
    
    protected void prepareRestArg(final ThreadContext context, final Ruby runtime, final DynamicScope scope, final IRubyObject[] args, final int givenArgsCount) {
        if (this.restArg >= 0) {
            final int sizeOfRestArg = args.length - this.postCount - givenArgsCount;
            if (sizeOfRestArg <= 0) {
                scope.setValue(this.restArg, RubyArray.newArray(runtime), 0);
            }
            else {
                scope.setValue(this.restArg, RubyArray.newArrayNoCopy(runtime, args, givenArgsCount, sizeOfRestArg), 0);
            }
        }
    }
    
    protected int assignOptArgs(final IRubyObject[] args, final Ruby runtime, final ThreadContext context, final IRubyObject self, int givenArgsCount) {
        int j = 0;
        for (int i = this.preCount; i < args.length - this.postCount && j < this.optArgs.size(); ++i, ++j) {
            this.optArgs.get(j).assign(runtime, context, self, args[i], Block.NULL_BLOCK, true);
            ++givenArgsCount;
        }
        int i = 0;
        while (j < this.optArgs.size()) {
            this.optArgs.get(j).interpret(runtime, context, self, Block.NULL_BLOCK);
            ++i;
            ++j;
        }
        return givenArgsCount;
    }
    
    protected void processBlockArg(final DynamicScope scope, final Ruby runtime, final Block block) {
        scope.setValue(this.getBlock().getCount(), RuntimeHelpers.processBlockArgument(runtime, block), 0);
    }
    
    public List<Node> childNodes() {
        if (this.post != null) {
            return Node.createList(this.pre, this.optArgs, this.restArgNode, this.post, this.blockArgNode);
        }
        return Node.createList(this.pre, this.optArgs, this.restArgNode, this.blockArgNode);
    }
}
