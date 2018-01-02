// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.util.ByteList;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

public class OrNode extends Node implements BinaryOperatorNode
{
    private final Node firstNode;
    private final Node secondNode;
    
    public OrNode(final ISourcePosition position, final Node firstNode, final Node secondNode) {
        super(position);
        assert firstNode != null : "firstNode is not null";
        assert secondNode != null : "secondNode is not null";
        this.firstNode = firstNode;
        this.secondNode = secondNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.ORNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitOrNode(this);
    }
    
    public Node getFirstNode() {
        return this.firstNode;
    }
    
    public Node getSecondNode() {
        return this.secondNode;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.firstNode, this.secondNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        IRubyObject result = this.firstNode.interpret(runtime, context, self, aBlock);
        if (!result.isTrue()) {
            result = this.secondNode.interpret(runtime, context, self, aBlock);
        }
        return result;
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        if (!context.getRuntime().is1_9()) {
            return super.definition(runtime, context, self, aBlock);
        }
        return OrNode.EXPRESSION_BYTELIST;
    }
}
