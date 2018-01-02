// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.util.ByteList;
import org.jruby.RubyString;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

public class DefinedNode extends Node
{
    private final Node expressionNode;
    
    public DefinedNode(final ISourcePosition position, final Node expressionNode) {
        super(position);
        assert expressionNode != null : "expressionNode is not null";
        this.expressionNode = expressionNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.DEFINEDNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitDefinedNode(this);
    }
    
    public Node getExpressionNode() {
        return this.expressionNode;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.expressionNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        try {
            context.setWithinDefined(true);
            final ByteList definition = this.expressionNode.definition(runtime, context, self, aBlock);
            return (definition != null) ? RubyString.newStringShared(runtime, definition) : runtime.getNil();
        }
        finally {
            context.setWithinDefined(false);
        }
    }
}
