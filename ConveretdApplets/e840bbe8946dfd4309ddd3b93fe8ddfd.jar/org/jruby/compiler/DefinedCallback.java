// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler;

public class DefinedCallback extends TwoBranchCallback
{
    private BodyCompiler context;
    
    DefinedCallback(final BodyCompiler context) {
        this.context = null;
        this.context = context;
    }
    
    public void invalid() {
        this.context.pushNull();
    }
}
