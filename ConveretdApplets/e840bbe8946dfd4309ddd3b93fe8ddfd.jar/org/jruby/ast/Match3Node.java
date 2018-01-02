// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.util.ByteList;
import org.jruby.RubyRegexp;
import org.jruby.RubyString;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.runtime.MethodIndex;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.runtime.CallSite;

public class Match3Node extends Node
{
    private final Node receiverNode;
    private final Node valueNode;
    public final CallSite callAdapter;
    
    public Match3Node(final ISourcePosition position, final Node receiverNode, final Node valueNode) {
        super(position);
        this.callAdapter = MethodIndex.getFunctionalCallSite("=~");
        assert receiverNode != null : "receiverNode is not null";
        assert valueNode != null : "valueNode is not null";
        this.receiverNode = receiverNode;
        this.valueNode = valueNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.MATCH3NODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitMatch3Node(this);
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
        if (value instanceof RubyString) {
            return ((RubyRegexp)recv).op_match(context, value);
        }
        return this.callAdapter.call(context, self, value, recv);
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return Match3Node.METHOD_BYTELIST;
    }
}
