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

public class RescueBodyNode extends Node
{
    private final Node exceptionNodes;
    private final Node bodyNode;
    private final RescueBodyNode optRescueNode;
    
    public RescueBodyNode(final ISourcePosition position, final Node exceptionNodes, final Node bodyNode, final RescueBodyNode optRescueNode) {
        super(position);
        assert bodyNode != null : "bodyNode is not null";
        this.exceptionNodes = exceptionNodes;
        if (exceptionNodes instanceof ArrayNode) {
            ((ArrayNode)exceptionNodes).setLightweight(true);
        }
        this.bodyNode = bodyNode;
        this.optRescueNode = optRescueNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.RESCUEBODYNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitRescueBodyNode(this);
    }
    
    public Node getBodyNode() {
        return this.bodyNode;
    }
    
    public RescueBodyNode getOptRescueNode() {
        return this.optRescueNode;
    }
    
    public Node getExceptionNodes() {
        return this.exceptionNodes;
    }
    
    public List<Node> childNodes() {
        if (this.optRescueNode != null) {
            return Node.createList(this.exceptionNodes, this.bodyNode, this.optRescueNode);
        }
        return Node.createList(this.exceptionNodes, this.bodyNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return this.bodyNode.interpret(runtime, context, self, aBlock);
    }
}
