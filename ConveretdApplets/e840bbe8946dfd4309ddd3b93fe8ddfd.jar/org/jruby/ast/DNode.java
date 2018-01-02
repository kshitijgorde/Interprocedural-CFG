// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.util.ByteList;
import org.jruby.RubyString;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jcodings.Encoding;

public abstract class DNode extends ListNode
{
    protected Encoding encoding;
    
    public DNode(final ISourcePosition position) {
        this(position, (Encoding)null);
    }
    
    public DNode(final ISourcePosition position, final Encoding encoding) {
        super(position);
        this.encoding = encoding;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return this.buildDynamicString(runtime, context, self, aBlock);
    }
    
    public boolean is19() {
        return this.encoding != null;
    }
    
    public boolean isSameEncoding(final StrNode strNode) {
        return strNode.getValue().getEncoding() == this.encoding;
    }
    
    protected RubyString allocateString(final Ruby runtime) {
        final ByteList empty = new ByteList();
        if (this.is19()) {
            empty.setEncoding(this.encoding);
        }
        return runtime.newString(empty);
    }
    
    public void appendToString(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock, final RubyString string, final Node node) {
        if (node instanceof StrNode && (!this.is19() || this.isSameEncoding((StrNode)node))) {
            string.getByteList().append(((StrNode)node).getValue());
        }
        else if (this.is19()) {
            string.append19(node.interpret(runtime, context, self, aBlock));
        }
        else {
            string.append(node.interpret(runtime, context, self, aBlock));
        }
    }
    
    public RubyString buildDynamicString(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final RubyString string = this.allocateString(runtime);
        for (int size = this.size(), i = 0; i < size; ++i) {
            this.appendToString(runtime, context, self, aBlock, string, this.get(i));
        }
        return string;
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final ByteList definition = super.definition(runtime, context, self, aBlock);
        return (this.is19() && definition == null) ? DNode.EXPRESSION_BYTELIST : definition;
    }
}
