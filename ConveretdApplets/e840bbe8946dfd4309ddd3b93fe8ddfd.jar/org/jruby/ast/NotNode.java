// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.util.ByteList;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

public class NotNode extends Node
{
    private final Node conditionNode;
    
    public NotNode(final ISourcePosition position, final Node conditionNode) {
        super(position);
        assert conditionNode != null : "conditionNode is not null";
        this.conditionNode = conditionNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.NOTNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitNotNode(this);
    }
    
    public Node getConditionNode() {
        return this.conditionNode;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.conditionNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return runtime.newBoolean(!this.conditionNode.interpret(runtime, context, self, aBlock).isTrue());
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final ByteList definition = super.definition(runtime, context, self, aBlock);
        return RuntimeHelpers.getDefinedNot(runtime, definition);
    }
}
