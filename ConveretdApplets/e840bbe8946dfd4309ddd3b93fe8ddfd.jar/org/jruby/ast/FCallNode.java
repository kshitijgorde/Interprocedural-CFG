// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.evaluator.ASTInterpreter;
import org.jruby.util.ByteList;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.runtime.MethodIndex;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.runtime.CallSite;
import org.jruby.ast.types.INameNode;

public class FCallNode extends Node implements INameNode, IArgumentNode, BlockAcceptingNode
{
    protected Node argsNode;
    protected Node iterNode;
    public CallSite callAdapter;
    
    public FCallNode(final ISourcePosition position, final String name, final Node argsNode) {
        this(position, name, argsNode, null);
    }
    
    protected FCallNode(final ISourcePosition position, final String name, final Node argsNode, final Node iterNode) {
        super(position);
        this.setArgsNode(argsNode);
        this.iterNode = iterNode;
        this.callAdapter = MethodIndex.getFunctionalCallSite(name);
    }
    
    public NodeType getNodeType() {
        return NodeType.FCALLNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitFCallNode(this);
    }
    
    public Node getIterNode() {
        return this.iterNode;
    }
    
    public Node setIterNode(final Node iterNode) {
        this.iterNode = iterNode;
        this.callAdapter = MethodIndex.getFunctionalCallSite(this.callAdapter.methodName);
        return this;
    }
    
    public Node getArgsNode() {
        return this.argsNode;
    }
    
    public Node setArgsNode(final Node argsNode) {
        this.argsNode = argsNode;
        if (argsNode instanceof ArrayNode) {
            ((ArrayNode)argsNode).setLightweight(true);
        }
        return argsNode;
    }
    
    public String getName() {
        return this.callAdapter.methodName;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.argsNode, this.iterNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        assert false : "Should not happen anymore";
        return null;
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        if (self.getMetaClass().isMethodBound(this.getName(), false)) {
            return ASTInterpreter.getArgumentDefinition(runtime, context, this.getArgsNode(), FCallNode.METHOD_BYTELIST, self, aBlock);
        }
        return null;
    }
}
