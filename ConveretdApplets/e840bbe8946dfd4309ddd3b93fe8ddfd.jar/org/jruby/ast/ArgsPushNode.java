// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.RubyArray;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

public class ArgsPushNode extends Node
{
    private Node firstNode;
    private Node secondNode;
    
    public ArgsPushNode(final ISourcePosition position, final Node firstNode, final Node secondNode) {
        super(position);
        assert firstNode != null : "ArgsPushNode.first == null";
        assert secondNode != null : "ArgsPushNode.second == null";
        this.firstNode = firstNode;
        this.secondNode = secondNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.ARGSPUSHNODE;
    }
    
    public Object accept(final NodeVisitor visitor) {
        return visitor.visitArgsPushNode(this);
    }
    
    public Node getFirstNode() {
        return this.firstNode;
    }
    
    public Node getSecondNode() {
        return this.secondNode;
    }
    
    public List<Node> childNodes() {
        return ArgsPushNode.EMPTY_LIST;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final RubyArray args = (RubyArray)this.firstNode.interpret(runtime, context, self, aBlock).dup();
        return args.append(this.secondNode.interpret(runtime, context, self, aBlock));
    }
}
