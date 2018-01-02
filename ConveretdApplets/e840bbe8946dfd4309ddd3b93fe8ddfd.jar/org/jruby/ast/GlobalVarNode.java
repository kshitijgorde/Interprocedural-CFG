// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.util.ByteList;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.ast.types.INameNode;

public class GlobalVarNode extends Node implements INameNode
{
    private String name;
    
    public GlobalVarNode(final ISourcePosition position, final String name) {
        super(position);
        this.name = name;
    }
    
    public NodeType getNodeType() {
        return NodeType.GLOBALVARNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitGlobalVarNode(this);
    }
    
    public String getName() {
        return this.name;
    }
    
    public List<Node> childNodes() {
        return GlobalVarNode.EMPTY_LIST;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return runtime.getGlobalVariables().get(this.name);
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return runtime.getGlobalVariables().isDefined(this.name) ? GlobalVarNode.GLOBAL_VARIABLE_BYTELIST : null;
    }
}
