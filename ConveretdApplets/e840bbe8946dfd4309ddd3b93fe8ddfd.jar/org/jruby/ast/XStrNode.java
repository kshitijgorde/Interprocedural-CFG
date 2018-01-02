// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.RubyString;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.util.ByteList;
import org.jruby.ast.types.ILiteralNode;

public class XStrNode extends Node implements ILiteralNode
{
    private final ByteList value;
    
    public XStrNode(final ISourcePosition position, final ByteList value) {
        super(position);
        this.value = ((value == null) ? ByteList.create("") : value);
    }
    
    public NodeType getNodeType() {
        return NodeType.XSTRNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitXStrNode(this);
    }
    
    public ByteList getValue() {
        return this.value;
    }
    
    public List<Node> childNodes() {
        return XStrNode.EMPTY_LIST;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return self.callMethod(context, "`", RubyString.newStringShared(runtime, this.value));
    }
}
