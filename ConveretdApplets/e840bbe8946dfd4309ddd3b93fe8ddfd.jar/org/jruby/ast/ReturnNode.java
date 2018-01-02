// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

public class ReturnNode extends Node implements NonLocalControlFlowNode
{
    private final Node valueNode;
    private Object target;
    
    public ReturnNode(final ISourcePosition position, final Node valueNode) {
        super(position);
        assert valueNode != null : "valueNode is not null";
        this.valueNode = valueNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.RETURNNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitReturnNode(this);
    }
    
    public Node getValueNode() {
        return this.valueNode;
    }
    
    public boolean hasValue() {
        return this.valueNode != NilImplicitNode.NIL;
    }
    
    public Object getTarget() {
        return this.target;
    }
    
    public void setTarget(final Object target) {
        this.target = target;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.valueNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        throw context.returnJump(this.valueNode.interpret(runtime, context, self, aBlock));
    }
}
