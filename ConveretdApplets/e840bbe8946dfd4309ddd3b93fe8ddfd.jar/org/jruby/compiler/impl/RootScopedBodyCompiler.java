// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.impl;

import org.jruby.compiler.BodyCompiler;
import org.jruby.exceptions.JumpException;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.Arity;
import org.jruby.Ruby;
import org.jruby.util.CodegenUtils;
import org.jruby.runtime.ThreadContext;
import org.jruby.compiler.CompilerCallback;
import org.jruby.parser.StaticScope;
import org.jruby.compiler.ASTInspector;

public abstract class RootScopedBodyCompiler extends BaseBodyCompiler
{
    private boolean specificArity;
    
    protected RootScopedBodyCompiler(final StandardASMCompiler scriptCompiler, final String friendlyName, final String rubyName, final ASTInspector inspector, final StaticScope scope) {
        super(scriptCompiler, friendlyName, rubyName, inspector, scope);
    }
    
    public String getSignature() {
        if (this.shouldUseBoxedArgs(this.scope)) {
            this.specificArity = false;
            return StandardASMCompiler.getStaticMethodSignature(this.script.getClassname(), 4);
        }
        this.specificArity = true;
        return StandardASMCompiler.getStaticMethodSignature(this.script.getClassname(), this.scope.getRequiredArgs());
    }
    
    protected void createVariableCompiler() {
        if (this.inspector == null) {
            this.variableCompiler = new HeapBasedVariableCompiler(this, this.method, this.scope, this.specificArity, 3, this.getFirstTempIndex());
        }
        else if (this.inspector.hasClosure() || this.inspector.hasScopeAwareMethods()) {
            this.variableCompiler = new HeapBasedVariableCompiler(this, this.method, this.scope, this.specificArity, 3, this.getFirstTempIndex());
        }
        else {
            this.variableCompiler = new StackBasedVariableCompiler(this, this.method, this.scope, this.specificArity, 3, this.getFirstTempIndex());
        }
    }
    
    public void beginMethod(final CompilerCallback args, final StaticScope scope) {
        this.method.start();
        this.variableCompiler.beginMethod(args, scope);
        this.method.label(this.scopeStart);
    }
    
    public ChainedRootBodyCompiler outline(String methodName) {
        this.method.aload(0);
        for (int i = 1; i <= this.getClosureIndex(); ++i) {
            this.method.aload(i);
        }
        methodName = "chained_" + this.script.getAndIncrementMethodIndex() + "_" + methodName;
        this.method.invokestatic(this.script.getClassname(), methodName, this.getSignature());
        final ChainedRootBodyCompiler methodCompiler = new ChainedRootBodyCompiler(this.script, methodName, this.rubyName, this.inspector, this.scope, this);
        methodCompiler.beginChainedMethod();
        return methodCompiler;
    }
    
    public void endBody() {
        this.method.areturn();
        this.method.label(this.scopeEnd);
        this.variableCompiler.declareLocals(this.scope, this.scopeStart, this.scopeEnd);
        this.method.end();
        if (this.specificArity) {
            (this.method = new SkinnyMethodAdapter(this.script.getClassVisitor(), 9, this.methodName, StandardASMCompiler.getStaticMethodSignature(this.script.getClassname(), 4), null, null)).start();
            this.method.aload(1);
            this.method.invokevirtual(CodegenUtils.p(ThreadContext.class), "getRuntime", CodegenUtils.sig(Ruby.class, new Class[0]));
            this.method.aload(3);
            this.method.pushInt(this.scope.getRequiredArgs());
            this.method.pushInt(this.scope.getRequiredArgs());
            this.method.invokestatic(CodegenUtils.p(Arity.class), "checkArgumentCount", CodegenUtils.sig(Integer.TYPE, Ruby.class, IRubyObject[].class, Integer.TYPE, Integer.TYPE));
            this.method.pop();
            this.loadThis();
            this.loadThreadContext();
            this.loadSelf();
            for (int i = 0; i < this.scope.getRequiredArgs(); ++i) {
                this.method.aload(3);
                this.method.ldc(i);
                this.method.arrayload();
            }
            this.method.aload(4);
            this.method.invokestatic(this.script.getClassname(), this.methodName, this.getSignature());
            this.method.areturn();
            this.method.end();
        }
    }
    
    public void performReturn() {
        if (this.inNestedMethod) {
            this.loadThreadContext();
            this.invokeUtilityMethod("returnJump", CodegenUtils.sig(JumpException.ReturnJump.class, IRubyObject.class, ThreadContext.class));
            this.method.athrow();
        }
        else {
            this.method.areturn();
        }
    }
    
    public void issueBreakEvent(final CompilerCallback value) {
        if (this.currentLoopLabels != null) {
            value.call(this);
            this.issueLoopBreak();
        }
        else if (this.inNestedMethod) {
            this.loadThreadContext();
            value.call(this);
            this.invokeUtilityMethod("breakJump", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class));
        }
        else {
            this.loadRuntime();
            value.call(this);
            this.invokeUtilityMethod("breakLocalJumpError", CodegenUtils.sig(IRubyObject.class, Ruby.class, IRubyObject.class));
        }
    }
    
    public void issueNextEvent(final CompilerCallback value) {
        if (this.currentLoopLabels != null) {
            value.call(this);
            this.issueLoopNext();
        }
        else if (this.inNestedMethod) {
            value.call(this);
            this.invokeUtilityMethod("nextJump", CodegenUtils.sig(IRubyObject.class, IRubyObject.class));
        }
        else {
            this.loadRuntime();
            value.call(this);
            this.invokeUtilityMethod("nextLocalJumpError", CodegenUtils.sig(IRubyObject.class, Ruby.class, IRubyObject.class));
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
            this.loadRuntime();
            this.invokeUtilityMethod("redoLocalJumpError", CodegenUtils.sig(IRubyObject.class, Ruby.class));
        }
    }
}
