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
import org.jruby.ast.types.INameNode;

public class ConstDeclNode extends AssignableNode implements INameNode
{
    private final String name;
    private final INameNode constNode;
    
    public ConstDeclNode(final ISourcePosition position, final String name, final INameNode constNode, final Node valueNode) {
        super(position, valueNode);
        this.name = name;
        this.constNode = constNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.CONSTDECLNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitConstDeclNode(this);
    }
    
    public String getName() {
        return (this.name == null) ? this.constNode.getName() : this.name;
    }
    
    public Node getConstNode() {
        return (Node)this.constNode;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.getValueNode());
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject result = this.getValueNode().interpret(runtime, context, self, aBlock);
        if (this.constNode == null) {
            return context.setConstantInCurrent(this.name, result);
        }
        if (((Node)this.constNode).getNodeType() != NodeType.COLON2NODE) {
            return context.setConstantInObject(this.constNode.getName(), result);
        }
        final Node leftNode = ((Colon2Node)this.constNode).getLeftNode();
        assert leftNode != null : "leftNode is not null";
        final IRubyObject obj = leftNode.interpret(runtime, context, self, aBlock);
        return context.setConstantInModule(this.constNode.getName(), obj, result);
    }
    
    public IRubyObject assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value, final Block block, final boolean checkArity) {
        IRubyObject module;
        if (this.constNode == null) {
            module = context.getCurrentScope().getStaticScope().getModule();
            if (module == null) {
                throw runtime.newTypeError("no class/module to define constant");
            }
        }
        else if (this.constNode instanceof Colon2Node) {
            final Node leftNode = ((Colon2Node)this.constNode).getLeftNode();
            if (leftNode == null) {
                module = runtime.getNil();
            }
            else {
                module = leftNode.interpret(runtime, context, self, block);
            }
        }
        else {
            module = runtime.getObject();
        }
        ((RubyModule)module).fastSetConstant(this.getName(), value);
        return runtime.getNil();
    }
}
