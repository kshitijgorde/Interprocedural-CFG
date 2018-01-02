// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.RubyModule;
import org.jruby.parser.StaticScope;

public abstract class ContextAwareBlockBody extends BlockBody
{
    protected StaticScope scope;
    private final Arity arity;
    
    public ContextAwareBlockBody(final StaticScope scope, final Arity arity, final int argumentType) {
        super(argumentType);
        this.scope = scope;
        this.arity = arity;
    }
    
    protected Frame pre(final ThreadContext context, final RubyModule klass, final Binding binding) {
        return context.preYieldSpecificBlock(binding, this.scope, klass);
    }
    
    protected void post(final ThreadContext context, final Binding binding, final Visibility vis, final Frame lastFrame) {
        binding.getFrame().setVisibility(vis);
        context.postYield(binding, lastFrame);
    }
    
    public StaticScope getStaticScope() {
        return this.scope;
    }
    
    public void setStaticScope(final StaticScope newScope) {
        this.scope = newScope;
    }
    
    public Arity arity() {
        return this.arity;
    }
    
    public Block cloneBlock(final Binding binding) {
        return new Block(this, binding.clone());
    }
}
