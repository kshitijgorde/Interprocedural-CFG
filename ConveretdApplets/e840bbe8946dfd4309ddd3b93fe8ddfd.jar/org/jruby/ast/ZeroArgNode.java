// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import java.util.List;
import org.jruby.runtime.Arity;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.ast.types.IArityNode;

public class ZeroArgNode extends Node implements IArityNode
{
    public ZeroArgNode(final ISourcePosition position) {
        super(position);
    }
    
    public NodeType getNodeType() {
        return NodeType.ZEROARGNODE;
    }
    
    public Object accept(final NodeVisitor visitor) {
        return null;
    }
    
    public Arity getArity() {
        return Arity.noArguments();
    }
    
    public List<Node> childNodes() {
        return ZeroArgNode.EMPTY_LIST;
    }
}
