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
import org.jruby.util.StringSupport;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.util.ByteList;
import org.jruby.ast.types.ILiteralNode;

public class StrNode extends Node implements ILiteralNode
{
    private final ByteList value;
    private final int codeRange;
    
    public StrNode(final ISourcePosition position, final ByteList value) {
        this(position, value, StringSupport.codeRangeScan(value.getEncoding(), value));
    }
    
    public StrNode(final ISourcePosition position, final ByteList value, final int codeRange) {
        super(position);
        this.value = value;
        this.codeRange = codeRange;
    }
    
    public StrNode(final ISourcePosition position, final StrNode head, final StrNode tail) {
        super(position);
        final ByteList headBL = head.getValue();
        final ByteList tailBL = tail.getValue();
        final ByteList myValue = new ByteList(headBL.getRealSize() + tailBL.getRealSize());
        myValue.setEncoding(headBL.getEncoding());
        myValue.append(headBL);
        myValue.append(tailBL);
        this.value = myValue;
        this.codeRange = StringSupport.codeRangeScan(this.value.getEncoding(), this.value);
    }
    
    public NodeType getNodeType() {
        return NodeType.STRNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitStrNode(this);
    }
    
    public ByteList getValue() {
        return this.value;
    }
    
    public int getCodeRange() {
        return this.codeRange;
    }
    
    public List<Node> childNodes() {
        return StrNode.EMPTY_LIST;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return RubyString.newStringShared(runtime, this.value, this.codeRange);
    }
}
