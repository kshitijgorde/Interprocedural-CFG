// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

public class SplatNode extends Node
{
    protected final Node node;
    
    public SplatNode(final ISourcePosition position, final Node node) {
        super(position);
        assert node != null : "node is not null";
        this.node = node;
    }
    
    public NodeType getNodeType() {
        return NodeType.SPLATNODE;
    }
    
    public Object accept(final NodeVisitor visitor) {
        return visitor.visitSplatNode(this);
    }
    
    public Node getValue() {
        return this.node;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.node);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return RuntimeHelpers.splatValue(this.node.interpret(runtime, context, self, aBlock));
    }
}
