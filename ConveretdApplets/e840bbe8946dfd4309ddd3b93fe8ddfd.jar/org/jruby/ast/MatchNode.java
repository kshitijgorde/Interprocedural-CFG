// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.RubyRegexp;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

public class MatchNode extends Node
{
    private final Node regexpNode;
    
    public MatchNode(final ISourcePosition position, final Node regexpNode) {
        super(position);
        assert regexpNode != null : "regexpNode is not null";
        this.regexpNode = regexpNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.MATCHNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitMatchNode(this);
    }
    
    public Node getRegexpNode() {
        return this.regexpNode;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.regexpNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return ((RubyRegexp)this.regexpNode.interpret(runtime, context, self, aBlock)).op_match2(context);
    }
}
