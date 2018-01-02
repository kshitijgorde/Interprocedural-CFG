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

public class BlockPassNode extends Node
{
    private final Node bodyNode;
    private Node argsNode;
    
    public BlockPassNode(final ISourcePosition position, final Node bodyNode) {
        super(position);
        this.bodyNode = bodyNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.BLOCKPASSNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitBlockPassNode(this);
    }
    
    public Node getBodyNode() {
        return this.bodyNode;
    }
    
    public Node getArgsNode() {
        return this.argsNode;
    }
    
    public void setArgsNode(final Node argsNode) {
        this.argsNode = argsNode;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.argsNode, this.bodyNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block block) {
        return (this.bodyNode == null) ? runtime.getNil() : this.bodyNode.interpret(runtime, context, self, block);
    }
}
