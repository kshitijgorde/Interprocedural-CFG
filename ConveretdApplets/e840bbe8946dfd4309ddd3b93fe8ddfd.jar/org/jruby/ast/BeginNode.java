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

public class BeginNode extends Node
{
    private final Node bodyNode;
    
    public BeginNode(final ISourcePosition position, final Node bodyNode) {
        super(position);
        assert bodyNode != null : "bodyNode is not null";
        this.bodyNode = bodyNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.BEGINNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitBeginNode(this);
    }
    
    public Node getBodyNode() {
        return this.bodyNode;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.bodyNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return this.bodyNode.interpret(runtime, context, self, aBlock);
    }
}
