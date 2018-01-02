// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.RubyClass;
import org.jruby.evaluator.ASTInterpreter;
import org.jruby.RubyModule;
import org.jruby.RubySymbol;
import org.jruby.RubyFixnum;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.parser.StaticScope;

public class SClassNode extends Node
{
    private final Node receiverNode;
    private final StaticScope scope;
    private final Node bodyNode;
    
    public SClassNode(final ISourcePosition position, final Node recvNode, final StaticScope scope, final Node bodyNode) {
        super(position);
        assert scope != null : "scope is not null";
        assert recvNode != null : "receiverNode is not null";
        this.receiverNode = recvNode;
        this.scope = scope;
        this.bodyNode = bodyNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.SCLASSNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitSClassNode(this);
    }
    
    public Node getBodyNode() {
        return this.bodyNode;
    }
    
    public StaticScope getScope() {
        return this.scope;
    }
    
    public Node getReceiverNode() {
        return this.receiverNode;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.receiverNode, this.bodyNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject receiver = this.receiverNode.interpret(runtime, context, self, aBlock);
        if (receiver instanceof RubyFixnum || receiver instanceof RubySymbol) {
            throw runtime.newTypeError("no virtual class for " + receiver.getMetaClass().getBaseName());
        }
        if (runtime.getSafeLevel() >= 4 && !receiver.isTaint()) {
            throw runtime.newSecurityError("Insecure: can't extend object.");
        }
        final RubyClass singletonClass = receiver.getSingletonClass();
        this.scope.setModule(singletonClass);
        return ASTInterpreter.evalClassDefinitionBody(runtime, context, this.scope, this.bodyNode, singletonClass, self, aBlock);
    }
}
