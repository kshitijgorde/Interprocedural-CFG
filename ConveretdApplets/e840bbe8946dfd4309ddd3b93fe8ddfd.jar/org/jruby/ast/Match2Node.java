// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.util.ByteList;
import org.jruby.RubyRegexp;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

public class Match2Node extends Node
{
    private final Node receiverNode;
    private final Node valueNode;
    
    public Match2Node(final ISourcePosition position, final Node receiverNode, final Node valueNode) {
        super(position);
        assert receiverNode != null : "receiverNode is not null";
        assert valueNode != null : "valueNode is not null";
        this.receiverNode = receiverNode;
        this.valueNode = valueNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.MATCH2NODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitMatch2Node(this);
    }
    
    public Node getReceiverNode() {
        return this.receiverNode;
    }
    
    public Node getValueNode() {
        return this.valueNode;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.receiverNode, this.valueNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject recv = this.receiverNode.interpret(runtime, context, self, aBlock);
        final IRubyObject value = this.valueNode.interpret(runtime, context, self, aBlock);
        return ((RubyRegexp)recv).op_match(context, value);
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return Match2Node.METHOD_BYTELIST;
    }
}
