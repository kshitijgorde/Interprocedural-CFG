// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.exceptions.JumpException;
import org.jruby.util.ByteList;
import org.jruby.RubyModule;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.runtime.builtin.IRubyObject;

public class Colon2ConstNode extends Colon2Node
{
    private transient volatile IRubyObject cachedValue;
    private volatile int generation;
    private volatile int hash;
    
    public Colon2ConstNode(final ISourcePosition position, final Node leftNode, final String name) {
        super(position, leftNode, name);
        this.cachedValue = null;
        this.generation = -1;
        this.hash = -1;
        assert leftNode != null : "Colon2ConstNode cannot have null leftNode";
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final RubyModule target = RuntimeHelpers.checkIsModule(this.leftNode.interpret(runtime, context, self, aBlock));
        final IRubyObject value = this.getValue(context, target);
        return (value != null) ? value : target.fastGetConstantFromConstMissing(this.name);
    }
    
    public ByteList definition(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final IRubyObject lastError = context.getErrorInfo();
        try {
            if (RuntimeHelpers.isModuleAndHasConstant(this.leftNode.interpret(runtime, context, self, aBlock), this.name)) {
                return Colon2ConstNode.CONSTANT_BYTELIST;
            }
        }
        catch (JumpException e) {
            context.setErrorInfo(lastError);
        }
        return null;
    }
    
    public IRubyObject getValue(final ThreadContext context, final RubyModule target) {
        final IRubyObject value = this.cachedValue;
        return this.isCached(context, target, value) ? value : this.reCache(context, target);
    }
    
    private boolean isCached(final ThreadContext context, final RubyModule target, final IRubyObject value) {
        return value != null && this.generation == context.getRuntime().getConstantGeneration() && this.hash == target.hashCode();
    }
    
    public IRubyObject reCache(final ThreadContext context, final RubyModule target) {
        final int newGeneration = context.getRuntime().getConstantGeneration();
        final IRubyObject value = target.fastGetConstantFromNoConstMissing(this.name);
        this.cachedValue = value;
        if (value != null) {
            this.generation = newGeneration;
            this.hash = target.hashCode();
        }
        return value;
    }
}
