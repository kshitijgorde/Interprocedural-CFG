// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.exceptions.JumpException;
import org.jruby.util.ByteList;
import org.jruby.evaluator.ASTInterpreter;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

public class OpAsgnAndNode extends Node implements BinaryOperatorNode
{
    private final Node firstNode;
    private final Node secondNode;
    
    public OpAsgnAndNode(final ISourcePosition position, final Node headNode, final Node valueNode) {
        super(position);
        assert headNode != null : "headNode is not null";
        assert valueNode != null : "valueNode is not null";
        this.firstNode = headNode;
        this.secondNode = valueNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.OPASGNANDNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitOpAsgnAndNode(this);
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
        final IRubyObject result = this.firstNode.interpret(runtime, context, self, aBlock);
        if (!result.isTrue()) {
            return ASTInterpreter.pollAndReturn(context, result);
        }
        return this.secondNode.interpret(runtime, context, self, aBlock);
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        try {
            this.interpret(runtime, context, self, aBlock);
            return OpAsgnAndNode.ASSIGNMENT_BYTELIST;
        }
        catch (JumpException jumpExcptn) {
            return null;
        }
    }
}
