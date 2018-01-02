// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.impl;

import org.jruby.util.CodegenUtils;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.exceptions.JumpException;
import org.jruby.compiler.CompilerCallback;
import org.jruby.parser.StaticScope;
import org.jruby.compiler.ASTInspector;

public class ClassBodyCompiler extends RootScopedBodyCompiler
{
    public ClassBodyCompiler(final StandardASMCompiler scriptCompiler, final String friendlyName, final String rubyName, final ASTInspector inspector, final StaticScope scope) {
        super(scriptCompiler, friendlyName, rubyName, inspector, scope);
    }
    
    public void beginMethod(final CompilerCallback bodyPrep, final StaticScope scope) {
        this.method.start();
        this.variableCompiler.beginClass(bodyPrep, scope);
        this.method.label(this.scopeStart);
    }
    
    public void performReturn() {
        this.loadThreadContext();
        this.invokeUtilityMethod("returnJump", CodegenUtils.sig(JumpException.ReturnJump.class, IRubyObject.class, ThreadContext.class));
        this.method.athrow();
    }
    
    public boolean isSimpleRoot() {
        return false;
    }
}
