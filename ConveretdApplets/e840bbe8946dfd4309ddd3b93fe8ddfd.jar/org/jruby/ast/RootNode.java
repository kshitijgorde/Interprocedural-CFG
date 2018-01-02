// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.RubyModule;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.parser.StaticScope;
import org.jruby.runtime.DynamicScope;

public class RootNode extends Node
{
    private transient DynamicScope scope;
    private StaticScope staticScope;
    private Node bodyNode;
    
    public RootNode(final ISourcePosition position, final DynamicScope scope, final Node bodyNode) {
        super(position);
        assert bodyNode != null : "bodyNode is not null";
        this.scope = scope;
        this.staticScope = scope.getStaticScope();
        this.bodyNode = bodyNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.ROOTNODE;
    }
    
    public DynamicScope getScope() {
        return this.scope;
    }
    
    public StaticScope getStaticScope() {
        return this.staticScope;
    }
    
    public Node getBodyNode() {
        return this.bodyNode;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitRootNode(this);
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.bodyNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        if (this.scope == null) {
            this.scope = DynamicScope.newDynamicScope(this.staticScope);
        }
        final StaticScope theStaticScope = this.scope.getStaticScope();
        context.preScopedBody(this.scope);
        if (theStaticScope.getModule() == null) {
            theStaticScope.setModule(runtime.getObject());
        }
        try {
            return this.bodyNode.interpret(runtime, context, self, aBlock);
        }
        finally {
            context.postScopedBody();
        }
    }
}
