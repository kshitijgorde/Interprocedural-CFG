// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.util.ByteList;
import org.jruby.RubyString;
import org.jruby.Ruby;
import org.jcodings.Encoding;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.RubyRegexp;
import org.jruby.util.RegexpOptions;
import org.jruby.ast.types.ILiteralNode;

public class DRegexpNode extends DNode implements ILiteralNode
{
    private final RegexpOptions options;
    private RubyRegexp onceRegexp;
    private boolean is19;
    
    public DRegexpNode(final ISourcePosition position, final RegexpOptions options) {
        this(position, options, false);
    }
    
    public DRegexpNode(final ISourcePosition position, final RegexpOptions options, final boolean is19) {
        super(position, (Encoding)null);
        this.options = options;
        this.is19 = is19;
    }
    
    public NodeType getNodeType() {
        return NodeType.DREGEXPNODE;
    }
    
    protected RubyString allocateString(final Ruby runtime) {
        return runtime.newString(new ByteList());
    }
    
    public boolean is19() {
        return this.is19;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitDRegxNode(this);
    }
    
    public boolean getOnce() {
        return this.options.isOnce();
    }
    
    public RegexpOptions getOptions() {
        return this.options;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        if (this.getOnce() && this.onceRegexp != null) {
            return this.onceRegexp;
        }
        final RubyString string = (RubyString)super.interpret(runtime, context, self, aBlock);
        final RubyRegexp regexp = RubyRegexp.newDRegexp(runtime, string, this.options);
        if (this.getOnce() && this.onceRegexp == null) {
            this.onceRegexp = regexp;
        }
        return regexp;
    }
}
