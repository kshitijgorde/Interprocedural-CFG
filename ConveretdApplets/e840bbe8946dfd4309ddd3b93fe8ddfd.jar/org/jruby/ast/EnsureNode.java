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

public class EnsureNode extends Node
{
    private final Node bodyNode;
    private final Node ensureNode;
    
    public EnsureNode(final ISourcePosition position, final Node bodyNode, final Node ensureNode) {
        super(position);
        assert bodyNode != null : "bodyNode is not null";
        this.bodyNode = bodyNode;
        this.ensureNode = ensureNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.ENSURENODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitEnsureNode(this);
    }
    
    public Node getBodyNode() {
        return this.bodyNode;
    }
    
    public Node getEnsureNode() {
        return this.ensureNode;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.bodyNode, this.ensureNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        if (this.ensureNode != null) {
            try {
                return this.bodyNode.interpret(runtime, context, self, aBlock);
            }
            finally {
                this.ensureNode.interpret(runtime, context, self, aBlock);
            }
        }
        return this.bodyNode.interpret(runtime, context, self, aBlock);
    }
}
