// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.parser;

public class EvalStaticScope extends BlockStaticScope
{
    public EvalStaticScope(final StaticScope parentScope) {
        super(parentScope, new String[0]);
    }
    
    public EvalStaticScope(final StaticScope parentScope, final String[] names) {
        super(parentScope, names);
    }
    
    public StaticScope getLocalScope() {
        return this;
    }
}
