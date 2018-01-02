// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.impl;

import org.jruby.compiler.BodyCompiler;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.Block;
import org.jruby.util.CodegenUtils;
import org.jruby.exceptions.JumpException;
import org.jruby.compiler.NotCompilableException;
import org.jruby.org.objectweb.asm.Label;
import org.jruby.compiler.CompilerCallback;
import org.jruby.parser.StaticScope;
import org.jruby.compiler.ASTInspector;

public class ChildScopedBodyCompiler extends BaseBodyCompiler
{
    public ChildScopedBodyCompiler(final StandardASMCompiler scriptCompiler, final String closureMethodName, final String rubyName, final ASTInspector inspector, final StaticScope scope) {
        super(scriptCompiler, closureMethodName, rubyName, inspector, scope);
    }
    
    protected int getActualArgsCount(final StaticScope scope) {
        return 1;
    }
    
    protected String getSignature() {
        return StandardASMCompiler.getStaticClosureSignature(this.script.getClassname());
    }
    
    protected void createVariableCompiler() {
        if (this.inspector == null) {
            this.variableCompiler = new HeapBasedVariableCompiler(this, this.method, this.scope, false, 3, this.getFirstTempIndex());
        }
        else if (this.inspector.hasClosure() || this.inspector.hasScopeAwareMethods()) {
            this.variableCompiler = new HeapBasedVariableCompiler(this, this.method, this.scope, false, 3, this.getFirstTempIndex());
        }
        else {
            this.variableCompiler = new StackBasedVariableCompiler(this, this.method, this.scope, false, 3, this.getFirstTempIndex());
        }
    }
    
    public void beginMethod(final CompilerCallback args, final StaticScope scope) {
        this.method.start();
        if (scope == null) {
            this.variableCompiler.beginFlatClosure(args, this.scope);
        }
        else {
            this.variableCompiler.beginClosure(args, scope);
        }
        this.redoJump = new Label();
        this.method.label(this.scopeStart);
    }
    
    public void beginClass(final CompilerCallback bodyPrep, final StaticScope scope) {
        throw new NotCompilableException("ERROR: closure compiler should not be used for class bodies");
    }
    
    public ChainedChildBodyCompiler outline(String methodName) {
        this.method.aload(0);
        for (int i = 1; i <= 4; ++i) {
            this.method.aload(i);
        }
        methodName = "chained_" + this.script.getAndIncrementMethodIndex() + "_" + methodName;
        this.method.invokestatic(this.script.getClassname(), methodName, this.getSignature());
        final ChainedChildBodyCompiler methodCompiler = new ChainedChildBodyCompiler(this.script, methodName, this.rubyName, this.inspector, this.scope, this);
        methodCompiler.beginChainedMethod();
        return methodCompiler;
    }
    
    public void endBody() {
        this.method.areturn();
        this.method.label(this.scopeEnd);
        if (this.inspector == null || this.inspector.hasScopeAwareMethods()) {
            this.method.pop();
            this.method.go_to(this.scopeStart);
            this.method.trycatch(this.scopeStart, this.scopeEnd, this.scopeEnd, CodegenUtils.p(JumpException.RedoJump.class));
        }
        this.variableCompiler.declareLocals(this.scope, this.scopeStart, this.scopeEnd);
        this.method.end();
    }
    
    public void loadBlock() {
        this.loadThreadContext();
        this.invokeThreadContext("getFrameBlock", CodegenUtils.sig(Block.class, new Class[0]));
    }
    
    public void performReturn() {
        this.loadThreadContext();
        this.invokeUtilityMethod("returnJump", CodegenUtils.sig(JumpException.ReturnJump.class, IRubyObject.class, ThreadContext.class));
        this.method.athrow();
    }
    
    public void issueBreakEvent(final CompilerCallback value) {
        if (this.currentLoopLabels != null) {
            value.call(this);
            this.issueLoopBreak();
        }
        else {
            this.loadThreadContext();
            value.call(this);
            this.invokeUtilityMethod("breakJump", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class));
        }
    }
    
    public void issueNextEvent(final CompilerCallback value) {
        if (this.currentLoopLabels != null) {
            value.call(this);
            this.issueLoopNext();
        }
        else {
            value.call(this);
            this.invokeUtilityMethod("nextJump", CodegenUtils.sig(IRubyObject.class, IRubyObject.class));
        }
    }
    
    public void issueRedoEvent() {
        if (this.currentLoopLabels != null) {
            this.issueLoopRedo();
        }
        else if (this.inNestedMethod) {
            this.invokeUtilityMethod("redoJump", CodegenUtils.sig(IRubyObject.class, new Class[0]));
        }
        else {
            this.method.go_to(this.scopeStart);
        }
    }
    
    public boolean isSimpleRoot() {
        return false;
    }
}
