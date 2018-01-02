// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

public class AliasNode extends Node
{
    private Node oldName;
    private Node newName;
    
    public AliasNode(final ISourcePosition position, final Node newName, final Node oldName) {
        super(position);
        this.oldName = oldName;
        this.newName = newName;
    }
    
    public NodeType getNodeType() {
        return NodeType.ALIASNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitAliasNode(this);
    }
    
    public Node getNewName() {
        return this.newName;
    }
    
    public Node getOldName() {
        return this.oldName;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.newName, this.oldName);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final String newerName = RuntimeHelpers.interpretAliasUndefName(this.newName, runtime, context, self, aBlock);
        final String olderName = RuntimeHelpers.interpretAliasUndefName(this.oldName, runtime, context, self, aBlock);
        return RuntimeHelpers.defineAlias(context, self, newerName, olderName);
    }
}
