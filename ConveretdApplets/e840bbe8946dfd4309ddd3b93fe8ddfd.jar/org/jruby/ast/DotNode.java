// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.RubyRange;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

public class DotNode extends Node
{
    private final Node beginNode;
    private final Node endNode;
    private final boolean exclusive;
    private final boolean isLiteral;
    
    public DotNode(final ISourcePosition position, final Node beginNode, final Node endNode, final boolean exclusive, final boolean isLiteral) {
        super(position);
        assert beginNode != null : "beginNode is not null";
        assert endNode != null : "endNode is not null";
        this.beginNode = beginNode;
        this.endNode = endNode;
        this.exclusive = exclusive;
        this.isLiteral = isLiteral;
    }
    
    public NodeType getNodeType() {
        return NodeType.DOTNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitDotNode(this);
    }
    
    public Node getBeginNode() {
        return this.beginNode;
    }
    
    public Node getEndNode() {
        return this.endNode;
    }
    
    public boolean isExclusive() {
        return this.exclusive;
    }
    
    public boolean isLiteral() {
        return this.isLiteral;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.beginNode, this.endNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return RubyRange.newRange(runtime, context, this.beginNode.interpret(runtime, context, self, aBlock), this.endNode.interpret(runtime, context, self, aBlock), this.exclusive);
    }
}
