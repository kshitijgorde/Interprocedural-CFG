// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.ast.types.INameNode;

public class GlobalAsgnNode extends AssignableNode implements INameNode
{
    private String name;
    
    public GlobalAsgnNode(final ISourcePosition position, final String name, final Node valueNode) {
        super(position, valueNode);
        this.name = name;
    }
    
    public NodeType getNodeType() {
        return NodeType.GLOBALASGNNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitGlobalAsgnNode(this);
    }
    
    public String getName() {
        return this.name;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.getValueNode());
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject result = this.getValueNode().interpret(runtime, context, self, aBlock);
        runtime.getGlobalVariables().set(this.name, result);
        return result;
    }
    
    public IRubyObject assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject value, final Block block, final boolean checkArity) {
        runtime.getGlobalVariables().set(this.name, value);
        return runtime.getNil();
    }
}
