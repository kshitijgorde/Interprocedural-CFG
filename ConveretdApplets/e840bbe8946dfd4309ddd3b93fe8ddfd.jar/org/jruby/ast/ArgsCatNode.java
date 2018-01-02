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

public class ArgsCatNode extends Node
{
    private final Node firstNode;
    private final Node secondNode;
    
    public ArgsCatNode(final ISourcePosition position, final Node firstNode, final Node secondNode) {
        super(position);
        assert firstNode != null : "ArgsCatNode.first == null";
        assert secondNode != null : "ArgsCatNode.second == null";
        this.firstNode = firstNode;
        this.secondNode = secondNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.ARGSCATNODE;
    }
    
    public Object accept(final NodeVisitor visitor) {
        return visitor.visitArgsCatNode(this);
    }
    
    public Node getFirstNode() {
        return this.firstNode;
    }
    
    public Node getSecondNode() {
        return this.secondNode;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.firstNode, this.secondNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject args = this.firstNode.interpret(runtime, context, self, aBlock);
        final IRubyObject secondInterpret = this.secondNode.interpret(runtime, context, self, aBlock);
        return RuntimeHelpers.argsCat(args, secondInterpret);
    }
}
