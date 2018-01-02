// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

public class VAliasNode extends Node
{
    private String oldName;
    private String newName;
    
    public VAliasNode(final ISourcePosition position, final String newName, final String oldName) {
        super(position);
        this.oldName = oldName;
        this.newName = newName;
    }
    
    public NodeType getNodeType() {
        return NodeType.VALIASNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitVAliasNode(this);
    }
    
    public String getNewName() {
        return this.newName;
    }
    
    public String getOldName() {
        return this.oldName;
    }
    
    public List<Node> childNodes() {
        return VAliasNode.EMPTY_LIST;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        runtime.getGlobalVariables().alias(this.newName, this.oldName);
        return runtime.getNil();
    }
}
