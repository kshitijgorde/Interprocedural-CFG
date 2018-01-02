// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.ast.util.ArgsUtil;
import org.jruby.evaluator.AssignmentVisitor;
import org.jruby.RubyArray;
import org.jruby.evaluator.ASTInterpreter;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.runtime.Arity;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

public class MultipleAsgnNode extends AssignableNode
{
    private final ListNode pre;
    private final Node rest;
    
    public MultipleAsgnNode(final ISourcePosition position, final ListNode pre, final Node rest) {
        super(position);
        this.pre = pre;
        this.rest = rest;
    }
    
    public NodeType getNodeType() {
        return NodeType.MULTIPLEASGNNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitMultipleAsgnNode(this);
    }
    
    public ListNode getHeadNode() {
        return this.pre;
    }
    
    public ListNode getPre() {
        return this.pre;
    }
    
    public int getPreCount() {
        return (this.pre == null) ? 0 : this.pre.size();
    }
    
    public Node getArgsNode() {
        return this.rest;
    }
    
    public Node getRest() {
        return this.rest;
    }
    
    public Arity getArity() {
        if (this.rest != null) {
            return Arity.required((this.pre == null) ? 0 : this.pre.size());
        }
        return Arity.fixed(this.pre.size());
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.pre, this.rest, this.getValueNode());
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        switch (this.getValueNode().getNodeType()) {
            case ARRAYNODE: {
                final ArrayNode iVisited2 = (ArrayNode)this.getValueNode();
                return ASTInterpreter.multipleAsgnArrayNode(runtime, context, this, iVisited2, self, aBlock);
            }
            case SPLATNODE: {
                final SplatNode splatNode = (SplatNode)this.getValueNode();
                final RubyArray rubyArray = (RubyArray)splatNode.interpret(runtime, context, self, aBlock);
                return AssignmentVisitor.multiAssign(runtime, context, self, this, rubyArray, false);
            }
            default: {
                IRubyObject value = this.getValueNode().interpret(runtime, context, self, aBlock);
                if (!(value instanceof RubyArray)) {
                    value = RubyArray.newArray(runtime, value);
                }
                return AssignmentVisitor.multiAssign(runtime, context, self, this, (RubyArray)value, false);
            }
        }
    }
    
    public IRubyObject assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, IRubyObject value, final Block block, final boolean checkArity) {
        if (!(value instanceof RubyArray)) {
            value = ArgsUtil.convertToRubyArray(runtime, value, this.pre != null);
        }
        return AssignmentVisitor.multiAssign(runtime, context, self, this, (RubyArray)value, checkArity);
    }
}
