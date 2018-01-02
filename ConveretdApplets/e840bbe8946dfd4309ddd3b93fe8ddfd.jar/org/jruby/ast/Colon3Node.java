// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.exceptions.JumpException;
import org.jruby.util.ByteList;
import org.jruby.RubyModule;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.types.INameNode;

public class Colon3Node extends Node implements INameNode
{
    protected String name;
    private transient volatile IRubyObject cachedValue;
    private volatile int generation;
    
    public Colon3Node(final ISourcePosition position, final String name) {
        super(position);
        this.name = name;
    }
    
    public NodeType getNodeType() {
        return NodeType.COLON3NODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitColon3Node(this);
    }
    
    public String getName() {
        return this.name;
    }
    
    public List<Node> childNodes() {
        return Colon3Node.EMPTY_LIST;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public RubyModule getEnclosingModule(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return runtime.getObject();
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject value = this.getValue(context);
        return (value != null) ? value : runtime.getObject().fastGetConstantFromConstMissing(this.name);
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        try {
            final RubyModule left = runtime.getObject();
            if (this.hasConstant(left)) {
                return Colon3Node.CONSTANT_BYTELIST;
            }
            if (this.hasMethod(left)) {
                return Colon3Node.METHOD_BYTELIST;
            }
        }
        catch (JumpException ex) {}
        return null;
    }
    
    private boolean hasConstant(final RubyModule left) {
        return left.fastGetConstantAt(this.name) != null;
    }
    
    private boolean hasMethod(final IRubyObject left) {
        return left.getMetaClass().isMethodBound(this.name, true);
    }
    
    public IRubyObject getValue(final ThreadContext context) {
        final IRubyObject value = this.cachedValue;
        return this.isCached(context, value) ? value : this.reCache(context, this.name);
    }
    
    private boolean isCached(final ThreadContext context, final IRubyObject value) {
        return value != null && this.generation == context.getRuntime().getConstantGeneration();
    }
    
    public IRubyObject reCache(final ThreadContext context, final String name) {
        final Ruby runtime = context.getRuntime();
        final int newGeneration = runtime.getConstantGeneration();
        final IRubyObject value = runtime.getObject().fastGetConstantFromNoConstMissing(name);
        this.cachedValue = value;
        if (value != null) {
            this.generation = newGeneration;
        }
        return value;
    }
}
