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
import org.jruby.RubySymbol;
import org.jruby.ast.types.IEqlNode;
import org.jruby.ast.types.INameNode;
import org.jruby.ast.types.ILiteralNode;

public class SymbolNode extends Node implements ILiteralNode, INameNode, IEqlNode
{
    private String name;
    private RubySymbol symbol;
    
    public SymbolNode(final ISourcePosition position, final String name) {
        super(position);
        this.name = name;
    }
    
    public NodeType getNodeType() {
        return NodeType.SYMBOLNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitSymbolNode(this);
    }
    
    public String getName() {
        return this.name;
    }
    
    public List<Node> childNodes() {
        return SymbolNode.EMPTY_LIST;
    }
    
    public RubySymbol getSymbol(final Ruby runtime) {
        final RubySymbol sym;
        if ((sym = this.symbol) != null) {
            return sym;
        }
        return this.symbol = runtime.fastNewSymbol(this.name);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return this.getSymbol(runtime);
    }
    
    public boolean eql(final IRubyObject otherValue, final ThreadContext context, final Ruby runtime, final IRubyObject self, final Block aBlock) {
        return otherValue instanceof RubySymbol && ((RubySymbol)otherValue).asJavaString() == this.name;
    }
}