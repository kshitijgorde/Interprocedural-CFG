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

public class EvStrNode extends Node
{
    private final Node body;
    
    public EvStrNode(final ISourcePosition position, final Node body) {
        super(position);
        this.body = body;
    }
    
    public NodeType getNodeType() {
        return NodeType.EVSTRNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitEvStrNode(this);
    }
    
    public Node getBody() {
        return this.body;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.body);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        if (this.body == null) {
            return runtime.getNil().asString();
        }
        return this.body.interpret(runtime, context, self, aBlock).asString();
    }
}
