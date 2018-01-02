// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.util.ByteList;
import org.jruby.RubyModule;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

public class YieldNode extends Node
{
    private final Node argsNode;
    private final boolean expandedArguments;
    
    public YieldNode(final ISourcePosition position, final Node argsNode, final boolean expandedArguments) {
        super(position);
        this.argsNode = argsNode;
        if (argsNode instanceof ArrayNode) {
            ((ArrayNode)argsNode).setLightweight(true);
        }
        this.expandedArguments = expandedArguments;
    }
    
    public NodeType getNodeType() {
        return NodeType.YIELDNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitYieldNode(this);
    }
    
    public Node getArgsNode() {
        return this.argsNode;
    }
    
    @Deprecated
    public boolean getCheckState() {
        return this.expandedArguments;
    }
    
    public boolean getExpandArguments() {
        return this.expandedArguments;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.argsNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        if (this.expandedArguments) {
            return context.getCurrentFrame().getBlock().yieldArray(context, this.argsNode.interpret(runtime, context, self, aBlock), null, null);
        }
        return context.getCurrentFrame().getBlock().yield(context, this.argsNode.interpret(runtime, context, self, aBlock));
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return aBlock.isGiven() ? YieldNode.YIELD_BYTELIST : null;
    }
}
