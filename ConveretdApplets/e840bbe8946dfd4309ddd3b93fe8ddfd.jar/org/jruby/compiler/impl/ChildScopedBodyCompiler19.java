// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.impl;

import org.jruby.compiler.BodyCompiler;
import org.jruby.parser.StaticScope;
import org.jruby.compiler.ASTInspector;

public class ChildScopedBodyCompiler19 extends ChildScopedBodyCompiler
{
    public ChildScopedBodyCompiler19(final StandardASMCompiler scriptCompiler, final String closureMethodName, final String rubyName, final ASTInspector inspector, final StaticScope scope) {
        super(scriptCompiler, closureMethodName, rubyName, inspector, scope);
        this.argParamCount = 1;
    }
    
    protected String getSignature() {
        return StandardASMCompiler.getStaticClosure19Signature(this.script.getClassname());
    }
    
    public ChainedChildBodyCompiler outline(String methodName) {
        this.method.aload(0);
        for (int i = 1; i <= 4; ++i) {
            this.method.aload(i);
        }
        methodName = "chained_" + this.script.getAndIncrementMethodIndex() + "_" + methodName;
        this.method.invokestatic(this.script.getClassname(), methodName, this.getSignature());
        final ChainedChildBodyCompiler19 methodCompiler = new ChainedChildBodyCompiler19(this.script, methodName, this.rubyName, this.inspector, this.scope, this);
        methodCompiler.beginChainedMethod();
        return methodCompiler;
    }
}
