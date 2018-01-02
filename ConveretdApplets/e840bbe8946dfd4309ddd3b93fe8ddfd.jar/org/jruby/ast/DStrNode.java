// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.ast.visitor.NodeVisitor;
import org.jcodings.Encoding;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.ast.types.ILiteralNode;

public class DStrNode extends DNode implements ILiteralNode
{
    public DStrNode(final ISourcePosition position) {
        this(position, (Encoding)null);
    }
    
    public DStrNode(final ISourcePosition position, final Encoding encoding) {
        super(position, encoding);
    }
    
    public NodeType getNodeType() {
        return NodeType.DSTRNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitDStrNode(this);
    }
}
