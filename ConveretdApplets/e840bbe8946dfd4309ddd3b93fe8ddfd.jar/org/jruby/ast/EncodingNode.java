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
import org.jcodings.Encoding;

public class EncodingNode extends Node
{
    private final Encoding encoding;
    
    public EncodingNode(final ISourcePosition position, final Encoding encoding) {
        super(position);
        this.encoding = encoding;
    }
    
    public Object accept(final NodeVisitor visitor) {
        return visitor.visitEncodingNode(this);
    }
    
    public List<Node> childNodes() {
        return Node.EMPTY_LIST;
    }
    
    public Encoding getEncoding() {
        return this.encoding;
    }
    
    public NodeType getNodeType() {
        return NodeType.ENCODINGNODE;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return runtime.getEncodingService().getEncoding(this.encoding);
    }
}
