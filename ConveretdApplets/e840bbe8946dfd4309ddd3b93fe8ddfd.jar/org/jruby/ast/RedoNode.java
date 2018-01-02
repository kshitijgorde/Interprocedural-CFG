// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.exceptions.JumpException;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

public class RedoNode extends Node implements NonLocalControlFlowNode
{
    public RedoNode(final ISourcePosition position) {
        super(position);
    }
    
    public NodeType getNodeType() {
        return NodeType.REDONODE;
    }
    
    public Node getValueNode() {
        return null;
    }
    
    public boolean hasValue() {
        return false;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitRedoNode(this);
    }
    
    public List<Node> childNodes() {
        return RedoNode.EMPTY_LIST;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        context.pollThreadEvents();
        throw JumpException.REDO_JUMP;
    }
}
