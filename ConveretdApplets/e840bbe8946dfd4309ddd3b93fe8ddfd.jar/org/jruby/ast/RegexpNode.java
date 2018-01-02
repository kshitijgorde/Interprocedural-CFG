// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import java.util.List;
import org.jruby.Ruby;
import org.jruby.ast.visitor.NodeVisitor;
import org.jcodings.Encoding;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.util.RegexpOptions;
import org.jruby.util.ByteList;
import org.jruby.RubyRegexp;
import org.jruby.ast.types.ILiteralNode;

public class RegexpNode extends Node implements ILiteralNode
{
    private RubyRegexp pattern;
    private final ByteList value;
    private final RegexpOptions options;
    
    public RegexpNode(final ISourcePosition position, final ByteList value, final RegexpOptions options) {
        super(position);
        this.value = value;
        this.options = options;
    }
    
    public Encoding getEncoding() {
        return this.value.getEncoding();
    }
    
    public NodeType getNodeType() {
        return NodeType.REGEXPNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitRegexpNode(this);
    }
    
    public RegexpOptions getOptions() {
        return this.options;
    }
    
    public ByteList getValue() {
        return this.value;
    }
    
    public RubyRegexp loadPattern(final Ruby runtime) {
        if (this.pattern == null || runtime.getKCode() != this.pattern.getKCode()) {
            this.setPattern(RubyRegexp.newRegexp(runtime, this.value, this.options));
        }
        return this.pattern;
    }
    
    public void setPattern(final RubyRegexp p) {
        (this.pattern = p).setLiteral();
    }
    
    public RubyRegexp getPattern() {
        return this.pattern;
    }
    
    public List<Node> childNodes() {
        return RegexpNode.EMPTY_LIST;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return this.loadPattern(runtime);
    }
}
