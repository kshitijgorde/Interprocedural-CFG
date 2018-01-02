// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.RubyModule;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.ast.types.INameNode;

public abstract class Colon2Node extends Colon3Node implements INameNode
{
    protected final Node leftNode;
    
    public Colon2Node(final ISourcePosition position, final Node leftNode, final String name) {
        super(position, name);
        this.leftNode = leftNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.COLON2NODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitColon2Node(this);
    }
    
    public Node getLeftNode() {
        return this.leftNode;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.leftNode);
    }
    
    public RubyModule getEnclosingModule(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        if (this.leftNode != null && this.leftNode instanceof NilNode) {
            throw context.getRuntime().newTypeError("no outer class/module");
        }
        return RuntimeHelpers.prepareClassNamespace(context, this.leftNode.interpret(runtime, context, self, aBlock));
    }
}
