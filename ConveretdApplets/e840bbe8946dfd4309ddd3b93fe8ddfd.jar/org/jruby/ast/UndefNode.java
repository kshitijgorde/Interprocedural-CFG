// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.RubyModule;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

public class UndefNode extends Node
{
    private Node name;
    
    public UndefNode(final ISourcePosition position, final Node name) {
        super(position);
        this.name = name;
    }
    
    public NodeType getNodeType() {
        return NodeType.UNDEFNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitUndefNode(this);
    }
    
    public Node getName() {
        return this.name;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.name);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final RubyModule module = context.getRubyClass();
        final String undefName = RuntimeHelpers.interpretAliasUndefName(this.name, runtime, context, self, aBlock);
        if (module == null) {
            throw runtime.newTypeError("No class to undef method '" + undefName + "'.");
        }
        module.undef(context, undefName);
        return runtime.getNil();
    }
}
