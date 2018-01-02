// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.RubyArray;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.runtime.MethodIndex;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.runtime.CallSite;

public class WhenNode extends Node
{
    protected final Node expressionNodes;
    protected final Node bodyNode;
    private final Node nextCase;
    public final CallSite eqq;
    
    public WhenNode(final ISourcePosition position, final Node expressionNodes, final Node bodyNode, final Node nextCase) {
        super(position);
        this.eqq = MethodIndex.getFunctionalCallSite("===");
        this.expressionNodes = expressionNodes;
        if (expressionNodes instanceof ArrayNode) {
            ((ArrayNode)expressionNodes).setLightweight(true);
        }
        assert bodyNode != null : "bodyNode is not null";
        this.bodyNode = bodyNode;
        this.nextCase = nextCase;
    }
    
    public NodeType getNodeType() {
        return NodeType.WHENNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitWhenNode(this);
    }
    
    public Node getBodyNode() {
        return this.bodyNode;
    }
    
    public Node getNextCase() {
        return this.nextCase;
    }
    
    public Node getExpressionNodes() {
        return this.expressionNodes;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.expressionNodes, this.bodyNode, this.nextCase);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return this.bodyNode.interpret(runtime, context, self, aBlock);
    }
    
    public IRubyObject when(final IRubyObject value, final ThreadContext context, final Ruby runtime, final IRubyObject self, final Block aBlock) {
        final RubyArray expressions = RuntimeHelpers.splatValue(this.expressionNodes.interpret(runtime, context, self, aBlock));
        for (int j = 0, k = expressions.getLength(); j < k; ++j) {
            final IRubyObject test = expressions.eltInternal(j);
            if ((value != null && this.eqq.call(context, self, test, value).isTrue()) || (value == null && test.isTrue())) {
                return this.bodyNode.interpret(runtime, context, self, aBlock);
            }
        }
        return null;
    }
}
