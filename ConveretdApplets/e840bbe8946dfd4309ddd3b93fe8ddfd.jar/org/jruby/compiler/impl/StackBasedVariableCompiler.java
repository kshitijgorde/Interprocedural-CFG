// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.impl;

import org.jruby.compiler.NotCompilableException;
import org.jruby.runtime.Block;
import org.jruby.Ruby;
import org.jruby.runtime.DynamicScope;
import org.jruby.util.CodegenUtils;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.org.objectweb.asm.Label;
import org.jruby.compiler.BodyCompiler;
import org.jruby.compiler.CompilerCallback;
import org.jruby.parser.StaticScope;

public class StackBasedVariableCompiler extends AbstractVariableCompiler
{
    private int baseVariableIndex;
    
    public StackBasedVariableCompiler(final BaseBodyCompiler methodCompiler, final SkinnyMethodAdapter method, final StaticScope scope, final boolean specificArity, final int argsIndex, final int firstTempIndex) {
        super(methodCompiler, method, scope, specificArity, argsIndex, firstTempIndex);
        this.baseVariableIndex = firstTempIndex;
    }
    
    public void beginMethod(final CompilerCallback argsCallback, final StaticScope scope) {
        if (scope.getNumberOfVariables() > 0) {
            if (scope.getRequiredArgs() < scope.getNumberOfVariables()) {
                final int start = scope.getRequiredArgs();
                this.methodCompiler.loadNil();
                for (int i = start; i < scope.getNumberOfVariables(); ++i) {
                    if (i + 1 < scope.getNumberOfVariables()) {
                        this.methodCompiler.method.dup();
                    }
                    this.assignLocalVariable(i, false);
                }
            }
            this.tempVariableIndex += scope.getNumberOfVariables();
        }
        if (argsCallback != null) {
            argsCallback.call(this.methodCompiler);
        }
    }
    
    public void declareLocals(final StaticScope scope, final Label start, final Label end) {
        final String[] variables = scope.getVariables();
        for (int i = 0; i < variables.length; ++i) {
            this.method.visitLocalVariable(variables[i], CodegenUtils.ci(IRubyObject.class), null, start, end, this.baseVariableIndex + i);
        }
    }
    
    public void beginClass(final CompilerCallback bodyPrep, final StaticScope scope) {
        bodyPrep.call(this.methodCompiler);
        if (scope.getNumberOfVariables() > 0) {
            int i;
            for (int start = i = scope.getRequiredArgs(); i < scope.getNumberOfVariables(); ++i) {
                this.methodCompiler.loadNil();
                this.assignLocalVariable(i, false);
            }
            this.tempVariableIndex += scope.getNumberOfVariables();
        }
    }
    
    public void beginClosure(final CompilerCallback argsCallback, final StaticScope scope) {
        this.methodCompiler.loadThreadContext();
        this.methodCompiler.invokeThreadContext("getCurrentScope", CodegenUtils.sig(DynamicScope.class, new Class[0]));
        this.method.astore(this.methodCompiler.getDynamicScopeIndex());
        if (scope != null) {
            for (int i = 0; i < scope.getNumberOfVariables(); ++i) {
                this.methodCompiler.loadNil();
                this.assignLocalVariable(i, false);
            }
            this.tempVariableIndex += scope.getNumberOfVariables();
        }
        if (argsCallback != null) {
            this.methodCompiler.loadRuntime();
            this.method.aload(this.methodCompiler.getClosureIndex());
            this.methodCompiler.invokeUtilityMethod("processBlockArgument", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(Ruby.class, Block.class)));
            this.method.aload(this.argsIndex);
            argsCallback.call(this.methodCompiler);
        }
    }
    
    public void beginFlatClosure(final CompilerCallback argsCallback, final StaticScope scope) {
        throw new NotCompilableException("Can't have flat closure with stack-based scope");
    }
    
    public void assignLocalVariable(final int index, final boolean expr) {
        if (expr) {
            this.method.dup();
        }
        this.method.astore(this.baseVariableIndex + index);
    }
    
    private void assignLocalVariable(final int index, final CompilerCallback value, final boolean expr) {
        value.call(this.methodCompiler);
        this.assignLocalVariable(index, expr);
    }
    
    public void assignLocalVariable(final int index, final int depth, final boolean expr) {
        if (depth == 0) {
            this.assignLocalVariable(index, expr);
        }
        else {
            this.assignHeapLocal(depth, index, expr);
        }
    }
    
    public void assignLocalVariable(final int index, final int depth, final CompilerCallback value, final boolean expr) {
        if (depth == 0) {
            this.assignLocalVariable(index, value, expr);
        }
        else {
            this.assignHeapLocal(value, depth, index, expr);
        }
    }
    
    public void retrieveLocalVariable(final int index) {
        this.method.aload(this.baseVariableIndex + index);
    }
    
    public void retrieveLocalVariable(final int index, final int depth) {
        if (depth == 0) {
            this.retrieveLocalVariable(index);
        }
        else {
            this.retrieveHeapLocal(depth, index);
        }
    }
}
