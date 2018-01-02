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

public class BackRefNode extends Node
{
    private final char type;
    private final ByteList nameByteList;
    
    public BackRefNode(final ISourcePosition position, final int type) {
        super(position);
        this.type = (char)type;
        this.nameByteList = ByteList.create("$" + (char)type);
    }
    
    public NodeType getNodeType() {
        return NodeType.BACKREFNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitBackRefNode(this);
    }
    
    public char getType() {
        return this.type;
    }
    
    public List<Node> childNodes() {
        return BackRefNode.EMPTY_LIST;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject backref = context.getCurrentScope().getBackRef(runtime);
        switch (this.type) {
            case '&': {
                return RubyRegexp.last_match(backref);
            }
            case '`': {
                return RubyRegexp.match_pre(backref);
            }
            case '\'': {
                return RubyRegexp.match_post(backref);
            }
            case '+': {
                return RubyRegexp.match_last(backref);
            }
            default: {
                assert false : "backref with invalid type";
                return null;
            }
        }
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject backref = context.getCurrentScope().getBackRef(runtime);
        if (backref instanceof RubyMatchData) {
            return context.getRuntime().is1_9() ? BackRefNode.GLOBAL_VARIABLE_BYTELIST : this.nameByteList;
        }
        return null;
    }
}
