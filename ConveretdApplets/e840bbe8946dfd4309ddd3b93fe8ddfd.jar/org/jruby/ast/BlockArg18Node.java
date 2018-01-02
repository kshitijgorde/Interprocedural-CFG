// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

public class BlockArg18Node extends Node
{
    private Node normalBlockArgs;
    private Node blockArgAssignee;
    
    public BlockArg18Node(final ISourcePosition position, final Node blockArgAssignee, final Node normalBlockArgs) {
        super(position);
        assert blockArgAssignee != null : "Must be a value to assign too";
        this.blockArgAssignee = blockArgAssignee;
        this.normalBlockArgs = normalBlockArgs;
    }
    
    public Node getArgs() {
        return this.normalBlockArgs;
    }
    
    public Node getBlockArg() {
        return this.blockArgAssignee;
    }
    
    public Object accept(final NodeVisitor visitor) {
        return visitor.visitBlockArg18Node(this);
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.normalBlockArgs, this.blockArgAssignee);
    }
    
    public NodeType getNodeType() {
        return NodeType.BLOCKARG18NODE;
    }
}
