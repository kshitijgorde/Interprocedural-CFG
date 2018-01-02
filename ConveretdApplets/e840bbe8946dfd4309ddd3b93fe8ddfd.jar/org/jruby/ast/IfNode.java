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

public class IfNode extends Node
{
    private final Node condition;
    private final Node thenBody;
    private final Node elseBody;
    
    public IfNode(final ISourcePosition position, final Node condition, final Node thenBody, final Node elseBody) {
        super(position);
        assert condition != null : "condition is not null";
        this.condition = condition;
        this.thenBody = thenBody;
        this.elseBody = elseBody;
    }
    
    public NodeType getNodeType() {
        return NodeType.IFNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitIfNode(this);
    }
    
    public Node getCondition() {
        return this.condition;
    }
    
    public Node getElseBody() {
        return this.elseBody;
    }
    
    public Node getThenBody() {
        return this.thenBody;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.condition, this.thenBody, this.elseBody);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final ISourcePosition position = this.getPosition();
        context.setFile(position.getFile());
        context.setLine(position.getStartLine());
        final IRubyObject result = this.condition.interpret(runtime, context, self, aBlock);
        if (result.isTrue()) {
            return (this.thenBody == null) ? runtime.getNil() : this.thenBody.interpret(runtime, context, self, aBlock);
        }
        return (this.elseBody == null) ? runtime.getNil() : this.elseBody.interpret(runtime, context, self, aBlock);
    }
}
