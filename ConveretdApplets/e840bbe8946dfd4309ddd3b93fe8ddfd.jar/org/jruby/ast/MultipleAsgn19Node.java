// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.evaluator.AssignmentVisitor;
import org.jruby.ast.util.ArgsUtil;
import org.jruby.RubyArray;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.runtime.Arity;

public class MultipleAsgn19Node extends AssignableNode
{
    private final ListNode pre;
    private final Node rest;
    private final ListNode post;
    private final Arity arity;
    
    public MultipleAsgn19Node(final ISourcePosition position, final ListNode pre, final Node rest, final ListNode post) {
        super(position);
        this.pre = pre;
        this.rest = rest;
        this.post = post;
        if (this.getRest() != null) {
            this.arity = Arity.required(this.getPreCount() + this.getPostCount());
        }
        else {
            this.arity = Arity.fixed(this.getPreCount() + this.getPostCount());
        }
    }
    
    public NodeType getNodeType() {
        return NodeType.MULTIPLEASGN19NODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitMultipleAsgnNode(this);
    }
    
    public Node getRest() {
        return this.rest;
    }
    
    public ListNode getPre() {
        return this.pre;
    }
    
    public int getPreCount() {
        return (this.pre == null) ? 0 : this.pre.size();
    }
    
    public int getPostCount() {
        return (this.post == null) ? 0 : this.post.size();
    }
    
    public ListNode getPost() {
        return this.post;
    }
    
    public Arity getArity() {
        return this.arity;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.pre, this.rest, this.getValueNode());
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        IRubyObject value = this.getValueNode().interpret(runtime, context, self, aBlock);
        if (!(value instanceof RubyArray)) {
            value = ArgsUtil.convertToRubyArray(runtime, value, this.pre != null);
        }
        return AssignmentVisitor.multiAssign(runtime, context, self, this, (RubyArray)value);
    }
    
    public IRubyObject assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, IRubyObject value, final Block block, final boolean checkArity) {
        if (!(value instanceof RubyArray)) {
            value = ArgsUtil.convertToRubyArray(runtime, value, this.pre != null);
        }
        return AssignmentVisitor.multiAssign(runtime, context, self, this, (RubyArray)value, checkArity);
    }
}
