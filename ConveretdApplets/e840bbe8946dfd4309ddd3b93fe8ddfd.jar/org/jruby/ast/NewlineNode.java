// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.util.ByteList;
import org.jruby.evaluator.ASTInterpreter;
import org.jruby.runtime.RubyEvent;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

public class NewlineNode extends Node
{
    private final Node nextNode;
    
    public NewlineNode(final ISourcePosition position, final Node nextNode) {
        super(position);
        assert nextNode != null : "nextNode is not null";
        this.nextNode = nextNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.NEWLINENODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitNewlineNode(this);
    }
    
    public Node getNextNode() {
        return this.nextNode;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.nextNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final ISourcePosition position = this.getPosition();
        context.setLine(position.getLine());
        if (runtime.hasEventHooks()) {
            ASTInterpreter.callTraceFunction(runtime, context, RubyEvent.LINE);
        }
        return this.nextNode.interpret(runtime, context, self, aBlock);
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return this.nextNode.definition(runtime, context, self, aBlock);
    }
}
