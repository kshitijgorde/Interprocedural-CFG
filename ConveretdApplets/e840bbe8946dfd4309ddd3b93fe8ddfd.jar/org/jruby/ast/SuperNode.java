// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.RubyModule;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.util.ByteList;
import org.jruby.evaluator.ASTInterpreter;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.runtime.MethodIndex;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.runtime.CallSite;

public class SuperNode extends Node implements BlockAcceptingNode
{
    private final Node argsNode;
    private Node iterNode;
    private final CallSite callSite;
    
    public SuperNode(final ISourcePosition position, final Node argsNode) {
        this(position, argsNode, null);
    }
    
    public SuperNode(final ISourcePosition position, final Node argsNode, final Node iterNode) {
        super(position);
        this.argsNode = argsNode;
        this.iterNode = iterNode;
        if (argsNode instanceof ArrayNode) {
            ((ArrayNode)argsNode).setLightweight(true);
        }
        this.callSite = MethodIndex.getSuperCallSite();
    }
    
    public NodeType getNodeType() {
        return NodeType.SUPERNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitSuperNode(this);
    }
    
    public Node getArgsNode() {
        return this.argsNode;
    }
    
    public List<Node> childNodes() {
        return (this.iterNode != null) ? Node.createList(this.argsNode, this.iterNode) : Node.createList(this.argsNode);
    }
    
    public Node getIterNode() {
        return this.iterNode;
    }
    
    public Node setIterNode(final Node iterNode) {
        this.iterNode = iterNode;
        return this;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject[] args = ASTInterpreter.setupArgs(runtime, context, this.argsNode, self, aBlock);
        Block block = ASTInterpreter.getBlock(runtime, context, self, aBlock, this.iterNode);
        if (this.iterNode == null && !block.isGiven()) {
            block = aBlock;
        }
        return this.callSite.call(context, self, self, args, block);
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final String name = context.getFrameName();
        final RubyModule klazz = context.getFrameKlazz();
        if (name != null && klazz != null && RuntimeHelpers.findImplementerIfNecessary(self.getMetaClass(), klazz).getSuperClass().isMethodBound(name, false)) {
            return ASTInterpreter.getArgumentDefinition(runtime, context, this.argsNode, SuperNode.SUPER_BYTELIST, self, aBlock);
        }
        return null;
    }
}
