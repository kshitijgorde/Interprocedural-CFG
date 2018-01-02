// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.RubyMatchData;
import org.jruby.RubyRegexp;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.util.ByteList;

public class NthRefNode extends Node
{
    private final int matchNumber;
    private final ByteList nameByteList;
    
    public NthRefNode(final ISourcePosition position, final int matchNumber) {
        super(position);
        this.matchNumber = matchNumber;
        this.nameByteList = ByteList.create("$" + matchNumber);
    }
    
    public NodeType getNodeType() {
        return NodeType.NTHREFNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitNthRefNode(this);
    }
    
    public int getMatchNumber() {
        return this.matchNumber;
    }
    
    public List<Node> childNodes() {
        return NthRefNode.EMPTY_LIST;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return RubyRegexp.nth_match(this.matchNumber, context.getCurrentScope().getBackRef(runtime));
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject backref = context.getCurrentScope().getBackRef(runtime);
        if (!(backref instanceof RubyMatchData) || ((RubyMatchData)backref).group(this.matchNumber).isNil()) {
            return null;
        }
        if (!context.getRuntime().is1_9()) {
            return this.nameByteList;
        }
        return NthRefNode.GLOBAL_VARIABLE_BYTELIST;
    }
}
