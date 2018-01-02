// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.impl;

import org.jruby.runtime.Block;
import org.jruby.org.objectweb.asm.Label;
import org.jruby.compiler.BodyCompiler;
import org.jruby.Ruby;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.util.CodegenUtils;
import org.jruby.runtime.DynamicScope;
import org.jruby.compiler.CompilerCallback;
import org.jruby.parser.StaticScope;

public class HeapBasedVariableCompiler extends AbstractVariableCompiler
{
    public HeapBasedVariableCompiler(final BaseBodyCompiler methodCompiler, final SkinnyMethodAdapter method, final StaticScope scope, final boolean specificArity, final int argsIndex, final int firstTempIndex) {
        super(methodCompiler, method, scope, specificArity, argsIndex, firstTempIndex);
    }
    
    public void beginMethod(final CompilerCallback argsCallback, final StaticScope scope) {
        if (scope.getNumberOfVariables() > 0) {
            this.methodCompiler.loadThreadContext();
            this.methodCompiler.invokeThreadContext("getCurrentScope", CodegenUtils.sig(DynamicScope.class, new Class[0]));
            this.method.astore(this.methodCompiler.getDynamicScopeIndex());
            if (scope.getNumberOfVariables() > 4) {
                this.method.aload(this.methodCompiler.getDynamicScopeIndex());
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "getValues", CodegenUtils.sig(IRubyObject[].class, new Class[0]));
                this.method.astore(this.methodCompiler.getVarsArrayIndex());
                this.method.aload(this.methodCompiler.getVarsArrayIndex());
                this.methodCompiler.loadRuntime();
                this.methodCompiler.invokeUtilityMethod("fillNil", CodegenUtils.sig(Void.TYPE, IRubyObject[].class, Ruby.class));
            }
        }
        if (argsCallback != null) {
            argsCallback.call(this.methodCompiler);
        }
    }
    
    public void declareLocals(final StaticScope scope, final Label start, final Label end) {
        this.method.visitLocalVariable("locals", CodegenUtils.ci(DynamicScope.class), null, start, end, this.methodCompiler.getDynamicScopeIndex());
    }
    
    public void beginClass(final CompilerCallback bodyPrep, final StaticScope scope) {
        this.methodCompiler.loadThreadContext();
        this.methodCompiler.invokeThreadContext("getCurrentScope", CodegenUtils.sig(DynamicScope.class, new Class[0]));
        this.method.dup();
        this.method.astore(this.methodCompiler.getDynamicScopeIndex());
        this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "getValues", CodegenUtils.sig(IRubyObject[].class, new Class[0]));
        this.method.astore(this.methodCompiler.getVarsArrayIndex());
        bodyPrep.call(this.methodCompiler);
        this.methodCompiler.loadThreadContext();
        this.methodCompiler.invokeThreadContext("getCurrentScope", CodegenUtils.sig(DynamicScope.class, new Class[0]));
        this.method.astore(this.methodCompiler.getDynamicScopeIndex());
        if (scope.getNumberOfVariables() > 4) {
            this.method.aload(this.methodCompiler.getDynamicScopeIndex());
            this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "getValues", CodegenUtils.sig(IRubyObject[].class, new Class[0]));
            this.method.astore(this.methodCompiler.getVarsArrayIndex());
        }
        if (scope != null && scope.getNumberOfVariables() >= 1) {
            switch (scope.getNumberOfVariables()) {
                case 1: {
                    this.methodCompiler.loadNil();
                    this.assignLocalVariable(0, false);
                    break;
                }
                case 2: {
                    this.methodCompiler.loadNil();
                    this.assignLocalVariable(0, true);
                    this.assignLocalVariable(1, false);
                    break;
                }
                case 3: {
                    this.methodCompiler.loadNil();
                    this.assignLocalVariable(0, true);
                    this.assignLocalVariable(1, true);
                    this.assignLocalVariable(2, false);
                    break;
                }
                case 4: {
                    this.methodCompiler.loadNil();
                    this.assignLocalVariable(0, true);
                    this.assignLocalVariable(1, true);
                    this.assignLocalVariable(2, true);
                    this.assignLocalVariable(3, false);
                    break;
                }
                default: {
                    this.method.aload(this.methodCompiler.getVarsArrayIndex());
                    this.methodCompiler.loadNil();
                    this.assignLocalVariable(0, true);
                    this.assignLocalVariable(1, true);
                    this.assignLocalVariable(2, true);
                    this.assignLocalVariable(3, false);
                    this.methodCompiler.loadRuntime();
                    this.methodCompiler.invokeUtilityMethod("fillNil", CodegenUtils.sig(Void.TYPE, IRubyObject[].class, Ruby.class));
                    break;
                }
            }
        }
    }
    
    public void beginClosure(final CompilerCallback argsCallback, final StaticScope scope) {
        this.methodCompiler.loadThreadContext();
        this.methodCompiler.invokeThreadContext("getCurrentScope", CodegenUtils.sig(DynamicScope.class, new Class[0]));
        this.method.astore(this.methodCompiler.getDynamicScopeIndex());
        if (scope.getNumberOfVariables() > 4) {
            this.method.aload(this.methodCompiler.getDynamicScopeIndex());
            this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "getValues", CodegenUtils.sig(IRubyObject[].class, new Class[0]));
            this.method.astore(this.methodCompiler.getVarsArrayIndex());
        }
        if (scope != null && scope.getNumberOfVariables() >= 1) {
            switch (scope.getNumberOfVariables()) {
                case 1: {
                    this.methodCompiler.loadNil();
                    this.assignLocalVariable(0, false);
                    break;
                }
                case 2: {
                    this.methodCompiler.loadNil();
                    this.assignLocalVariable(0, true);
                    this.assignLocalVariable(1, false);
                    break;
                }
                case 3: {
                    this.methodCompiler.loadNil();
                    this.assignLocalVariable(0, true);
                    this.assignLocalVariable(1, true);
                    this.assignLocalVariable(2, false);
                    break;
                }
                case 4: {
                    this.methodCompiler.loadNil();
                    this.assignLocalVariable(0, true);
                    this.assignLocalVariable(1, true);
                    this.assignLocalVariable(2, true);
                    this.assignLocalVariable(3, false);
                    break;
                }
                default: {
                    this.method.aload(this.methodCompiler.getVarsArrayIndex());
                    this.methodCompiler.loadNil();
                    this.assignLocalVariable(0, true);
                    this.assignLocalVariable(1, true);
                    this.assignLocalVariable(2, true);
                    this.assignLocalVariable(3, false);
                    this.methodCompiler.loadRuntime();
                    this.methodCompiler.invokeUtilityMethod("fillNil", CodegenUtils.sig(Void.TYPE, IRubyObject[].class, Ruby.class));
                    break;
                }
            }
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
        this.methodCompiler.loadThreadContext();
        this.methodCompiler.invokeThreadContext("getCurrentScope", CodegenUtils.sig(DynamicScope.class, new Class[0]));
        this.method.astore(this.methodCompiler.getDynamicScopeIndex());
        if (scope.getNumberOfVariables() > 4) {
            this.method.aload(this.methodCompiler.getDynamicScopeIndex());
            this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "getValues", CodegenUtils.sig(IRubyObject[].class, new Class[0]));
            this.method.astore(this.methodCompiler.getVarsArrayIndex());
        }
        if (argsCallback != null) {
            this.methodCompiler.loadRuntime();
            this.method.aload(this.methodCompiler.getClosureIndex());
            this.methodCompiler.invokeUtilityMethod("processBlockArgument", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(Ruby.class, Block.class)));
            this.method.aload(this.argsIndex);
            argsCallback.call(this.methodCompiler);
        }
    }
    
    public void assignLocalVariable(final int index, final boolean expr) {
        switch (index) {
            case 0: {
                this.method.aload(this.methodCompiler.getDynamicScopeIndex());
                this.method.swap();
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "setValueZeroDepthZero", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(IRubyObject.class)));
                if (!expr) {
                    this.method.pop();
                    break;
                }
                break;
            }
            case 1: {
                this.method.aload(this.methodCompiler.getDynamicScopeIndex());
                this.method.swap();
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "setValueOneDepthZero", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(IRubyObject.class)));
                if (!expr) {
                    this.method.pop();
                    break;
                }
                break;
            }
            case 2: {
                this.method.aload(this.methodCompiler.getDynamicScopeIndex());
                this.method.swap();
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "setValueTwoDepthZero", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(IRubyObject.class)));
                if (!expr) {
                    this.method.pop();
                    break;
                }
                break;
            }
            case 3: {
                this.method.aload(this.methodCompiler.getDynamicScopeIndex());
                this.method.swap();
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "setValueThreeDepthZero", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(IRubyObject.class)));
                if (!expr) {
                    this.method.pop();
                    break;
                }
                break;
            }
            default: {
                if (expr) {
                    this.method.dup();
                }
                this.method.aload(this.methodCompiler.getVarsArrayIndex());
                this.method.swap();
                this.method.pushInt(index);
                this.method.swap();
                this.method.arraystore();
                break;
            }
        }
    }
    
    public void assignLocalVariable(final int index, final CompilerCallback value, final boolean expr) {
        switch (index) {
            case 0: {
                this.method.aload(this.methodCompiler.getDynamicScopeIndex());
                value.call(this.methodCompiler);
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "setValueZeroDepthZero", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(IRubyObject.class)));
                if (!expr) {
                    this.method.pop();
                    break;
                }
                break;
            }
            case 1: {
                this.method.aload(this.methodCompiler.getDynamicScopeIndex());
                value.call(this.methodCompiler);
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "setValueOneDepthZero", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(IRubyObject.class)));
                if (!expr) {
                    this.method.pop();
                    break;
                }
                break;
            }
            case 2: {
                this.method.aload(this.methodCompiler.getDynamicScopeIndex());
                value.call(this.methodCompiler);
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "setValueTwoDepthZero", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(IRubyObject.class)));
                if (!expr) {
                    this.method.pop();
                    break;
                }
                break;
            }
            case 3: {
                this.method.aload(this.methodCompiler.getDynamicScopeIndex());
                value.call(this.methodCompiler);
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "setValueThreeDepthZero", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(IRubyObject.class)));
                if (!expr) {
                    this.method.pop();
                    break;
                }
                break;
            }
            default: {
                this.method.aload(this.methodCompiler.getVarsArrayIndex());
                this.method.pushInt(index);
                value.call(this.methodCompiler);
                if (expr) {
                    this.method.dup_x2();
                }
                this.method.arraystore();
                break;
            }
        }
    }
    
    public void assignLocalVariable(final int index, final int depth, final boolean expr) {
        if (depth == 0) {
            this.assignLocalVariable(index, expr);
            return;
        }
        this.assignHeapLocal(depth, index, expr);
    }
    
    public void assignLocalVariable(final int index, final int depth, final CompilerCallback value, final boolean expr) {
        if (depth == 0) {
            this.assignLocalVariable(index, value, expr);
            return;
        }
        this.assignHeapLocal(value, depth, index, expr);
    }
    
    public void retrieveLocalVariable(final int index) {
        switch (index) {
            case 0: {
                this.method.aload(this.methodCompiler.getDynamicScopeIndex());
                this.methodCompiler.loadNil();
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "getValueZeroDepthZeroOrNil", CodegenUtils.sig(IRubyObject.class, IRubyObject.class));
                break;
            }
            case 1: {
                this.method.aload(this.methodCompiler.getDynamicScopeIndex());
                this.methodCompiler.loadNil();
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "getValueOneDepthZeroOrNil", CodegenUtils.sig(IRubyObject.class, IRubyObject.class));
                break;
            }
            case 2: {
                this.method.aload(this.methodCompiler.getDynamicScopeIndex());
                this.methodCompiler.loadNil();
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "getValueTwoDepthZeroOrNil", CodegenUtils.sig(IRubyObject.class, IRubyObject.class));
                break;
            }
            case 3: {
                this.method.aload(this.methodCompiler.getDynamicScopeIndex());
                this.methodCompiler.loadNil();
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "getValueThreeDepthZeroOrNil", CodegenUtils.sig(IRubyObject.class, IRubyObject.class));
                break;
            }
            default: {
                this.method.aload(this.methodCompiler.getVarsArrayIndex());
                this.method.pushInt(index);
                this.method.arrayload();
                break;
            }
        }
    }
    
    public void retrieveLocalVariable(final int index, final int depth) {
        if (depth == 0) {
            this.retrieveLocalVariable(index);
            return;
        }
        this.retrieveHeapLocal(depth, index);
    }
    
    public boolean isHeap() {
        return true;
    }
}
