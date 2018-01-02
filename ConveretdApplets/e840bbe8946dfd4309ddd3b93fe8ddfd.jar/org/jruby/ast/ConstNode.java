// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.util.ByteList;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.types.INameNode;

public class ConstNode extends Node implements INameNode
{
    private String name;
    private transient volatile IRubyObject cachedValue;
    private int generation;
    
    public ConstNode(final ISourcePosition position, final String name) {
        super(position);
        this.cachedValue = null;
        this.generation = -1;
        this.name = name;
    }
    
    public NodeType getNodeType() {
        return NodeType.CONSTNODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitConstNode(this);
    }
    
    public String getName() {
        return this.name;
    }
    
    public List<Node> childNodes() {
        return ConstNode.EMPTY_LIST;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject value = this.getValue(context);
        return (value != null) ? value : context.getCurrentScope().getStaticScope().getModule().callMethod(context, "const_missing", runtime.fastNewSymbol(this.name));
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return context.getConstantDefined(this.name) ? ConstNode.CONSTANT_BYTELIST : null;
    }
    
    public IRubyObject getValue(final ThreadContext context) {
        final IRubyObject value = this.cachedValue;
        return this.isCached(context, value) ? value : this.reCache(context, this.name);
    }
    
    private boolean isCached(final ThreadContext context, final IRubyObject value) {
        return value != null && this.generation == context.getRuntime().getConstantGeneration();
    }
    
    public IRubyObject reCache(final ThreadContext context, final String name) {
        final int newGeneration = context.getRuntime().getConstantGeneration();
        final IRubyObject value = context.getConstant(name);
        this.cachedValue = value;
        if (value != null) {
            this.generation = newGeneration;
        }
        return value;
    }
}
