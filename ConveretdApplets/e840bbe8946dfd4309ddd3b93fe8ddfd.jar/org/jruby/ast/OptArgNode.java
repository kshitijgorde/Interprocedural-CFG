// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.ast.types.INameNode;

public class OptArgNode extends Node implements INameNode
{
    private Node value;
    
    public OptArgNode(final ISourcePosition position, final Node value) {
        super(position);
        this.value = value;
    }
    
    public NodeType getNodeType() {
        return NodeType.OPTARGNODE;
    }
    
    public Node getValue() {
        return this.value;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return this.value.interpret(runtime, context, self, aBlock);
    }
    
    public IRubyObject assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final IRubyObject assignValue, final Block block, final boolean checkArity) {
        return this.value.assign(runtime, context, self, assignValue, block, checkArity);
    }
    
    public Object accept(final NodeVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.value);
    }
    
    public String getName() {
        if (this.value instanceof INameNode) {
            return ((INameNode)this.value).getName();
        }
        return null;
    }
}
