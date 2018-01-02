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
import org.jruby.runtime.Arity;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.runtime.MethodIndex;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.runtime.CallSite;
import org.jruby.ast.types.IArityNode;

public class ZSuperNode extends Node implements IArityNode, BlockAcceptingNode
{
    private Node iterNode;
    private CallSite callSite;
    
    public ZSuperNode(final ISourcePosition position) {
        super(position);
        this.callSite = MethodIndex.getSuperCallSite();
    }
    
    public NodeType getNodeType() {
        return NodeType.ZSUPERNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitZSuperNode(this);
    }
    
    public Arity getArity() {
        return Arity.optional();
    }
    
    public List<Node> childNodes() {
        return (this.iterNode != null) ? Node.createList(this.iterNode) : ZSuperNode.EMPTY_LIST;
    }
    
    public Node getIterNode() {
        return this.iterNode;
    }
    
    public Node setIterNode(final Node iterNode) {
        this.iterNode = iterNode;
        return this;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        Block block = ASTInterpreter.getBlock(runtime, context, self, aBlock, this.iterNode);
        if (block == null || !block.isGiven()) {
            block = context.getFrameBlock();
        }
        return this.callSite.call(context, self, self, context.getCurrentScope().getArgValues(), block);
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final String name = context.getFrameName();
        final RubyModule klazz = context.getFrameKlazz();
        if (name != null && klazz != null && RuntimeHelpers.findImplementerIfNecessary(self.getMetaClass(), klazz).getSuperClass().isMethodBound(name, false)) {
            return ZSuperNode.SUPER_BYTELIST;
        }
        return null;
    }
}
