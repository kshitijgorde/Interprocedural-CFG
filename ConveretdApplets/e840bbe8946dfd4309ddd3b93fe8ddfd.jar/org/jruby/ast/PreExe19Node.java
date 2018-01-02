// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.parser.StaticScope;
import org.jruby.lexer.yacc.ISourcePosition;

public class PreExe19Node extends PreExeNode
{
    public PreExe19Node(final ISourcePosition position, final StaticScope scope, final Node body) {
        super(position, scope, body);
    }
    
    public NodeType getNodeType() {
        return NodeType.PREEXENODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitPreExeNode(this);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        this.getBodyNode().interpret(runtime, context, self, aBlock);
        return runtime.getNil();
    }
}
