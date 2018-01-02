// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.impl;

import org.jruby.parser.StaticScope;
import org.jruby.compiler.ASTInspector;
import org.jruby.org.objectweb.asm.Label;

public class ChainedChildBodyCompiler extends ChildScopedBodyCompiler
{
    public void endBody() {
        this.method.areturn();
        final Label end = new Label();
        this.method.label(end);
        this.method.end();
    }
    
    public ChainedChildBodyCompiler(final StandardASMCompiler scriptCompiler, final String methodName, final String rubyName, final ASTInspector inspector, final StaticScope scope, final ChildScopedBodyCompiler parent) {
        super(scriptCompiler, methodName, rubyName, inspector, scope);
        this.inNestedMethod = true;
    }
}
