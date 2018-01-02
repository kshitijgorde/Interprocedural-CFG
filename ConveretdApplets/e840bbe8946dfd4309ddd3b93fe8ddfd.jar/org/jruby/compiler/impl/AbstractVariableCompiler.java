// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.impl;

import org.jruby.runtime.DynamicScope;
import org.jruby.runtime.Block;
import org.jruby.RubyArray;
import org.jruby.org.objectweb.asm.Label;
import org.jruby.compiler.ArrayCallback;
import org.jruby.compiler.BodyCompiler;
import org.jruby.compiler.CompilerCallback;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.util.CodegenUtils;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.parser.StaticScope;
import org.jruby.runtime.Arity;
import org.jruby.compiler.VariableCompiler;

public abstract class AbstractVariableCompiler implements VariableCompiler
{
    protected SkinnyMethodAdapter method;
    protected BaseBodyCompiler methodCompiler;
    protected int argsIndex;
    protected int tempVariableIndex;
    protected Arity arity;
    protected StaticScope scope;
    protected boolean specificArity;
    
    public AbstractVariableCompiler(final BaseBodyCompiler methodCompiler, final SkinnyMethodAdapter method, final StaticScope scope, final boolean specificArity, final int argsIndex, final int firstTempIndex) {
        this.methodCompiler = methodCompiler;
        this.method = method;
        this.argsIndex = argsIndex;
        this.tempVariableIndex = firstTempIndex;
        this.scope = scope;
        this.specificArity = specificArity;
    }
    
    public SkinnyMethodAdapter getMethodAdapter() {
        return this.method;
    }
    
    public void setMethodAdapter(final SkinnyMethodAdapter sma) {
        this.method = sma;
    }
    
    public void assignLastLine() {
        this.methodCompiler.loadRuntime();
        this.method.swap();
        this.methodCompiler.loadThreadContext();
        this.method.swap();
        this.method.invokestatic(CodegenUtils.p(RuntimeHelpers.class), "setLastLine", CodegenUtils.sig(IRubyObject.class, Ruby.class, ThreadContext.class, IRubyObject.class));
    }
    
    public void assignLastLine(final CompilerCallback value) {
        this.methodCompiler.loadRuntime();
        this.methodCompiler.loadThreadContext();
        value.call(this.methodCompiler);
        this.method.invokestatic(CodegenUtils.p(RuntimeHelpers.class), "setLastLine", CodegenUtils.sig(IRubyObject.class, Ruby.class, ThreadContext.class, IRubyObject.class));
    }
    
    public void retrieveLastLine() {
        this.methodCompiler.loadRuntime();
        this.methodCompiler.loadThreadContext();
        this.method.invokestatic(CodegenUtils.p(RuntimeHelpers.class), "getLastLine", CodegenUtils.sig(IRubyObject.class, Ruby.class, ThreadContext.class));
    }
    
    public void assignBackRef() {
        this.methodCompiler.loadRuntime();
        this.method.swap();
        this.methodCompiler.loadThreadContext();
        this.method.swap();
        this.method.invokestatic(CodegenUtils.p(RuntimeHelpers.class), "setBackref", CodegenUtils.sig(IRubyObject.class, Ruby.class, ThreadContext.class, IRubyObject.class));
    }
    
    public void assignBackRef(final CompilerCallback value) {
        this.methodCompiler.loadRuntime();
        this.methodCompiler.loadThreadContext();
        value.call(this.methodCompiler);
        this.method.invokestatic(CodegenUtils.p(RuntimeHelpers.class), "setBackref", CodegenUtils.sig(IRubyObject.class, Ruby.class, ThreadContext.class, IRubyObject.class));
    }
    
    public void retrieveBackRef() {
        this.methodCompiler.loadRuntime();
        this.methodCompiler.loadThreadContext();
        this.method.invokestatic(CodegenUtils.p(RuntimeHelpers.class), "getBackref", CodegenUtils.sig(IRubyObject.class, Ruby.class, ThreadContext.class));
    }
    
    public void checkMethodArity(final int requiredArgs, final int optArgs, final int restArg) {
        if (!this.specificArity) {
            boolean needsError = false;
            if (restArg != -1) {
                if (requiredArgs > 0) {
                    needsError = true;
                    this.methodCompiler.loadRuntime();
                    this.method.aload(this.argsIndex);
                    this.method.pushInt(requiredArgs);
                    this.method.pushInt(-1);
                }
            }
            else if (optArgs > 0) {
                needsError = true;
                this.methodCompiler.loadRuntime();
                this.method.aload(this.argsIndex);
                this.method.pushInt(requiredArgs);
                this.method.pushInt(requiredArgs + optArgs);
            }
            else {
                needsError = true;
                this.methodCompiler.loadRuntime();
                this.method.aload(this.argsIndex);
                this.method.pushInt(requiredArgs);
                this.method.pushInt(requiredArgs);
            }
            if (needsError) {
                this.method.invokestatic(CodegenUtils.p(Arity.class), "raiseArgumentError", CodegenUtils.sig(Void.TYPE, Ruby.class, IRubyObject[].class, Integer.TYPE, Integer.TYPE));
            }
        }
    }
    
    public void assignMethodArguments(final Object requiredArgs, final int requiredArgsCount, final Object optArgs, final int optArgsCount, final ArrayCallback requiredAssignment, final ArrayCallback optGivenAssignment, final ArrayCallback optNotGivenAssignment, final CompilerCallback restAssignment, final CompilerCallback blockAssignment) {
        if (this.specificArity) {
            for (int currentArgElement = 0; currentArgElement < this.scope.getRequiredArgs(); ++currentArgElement) {
                this.method.aload(this.argsIndex + currentArgElement);
                requiredAssignment.nextValue(this.methodCompiler, requiredArgs, currentArgElement);
            }
        }
        else if (requiredArgsCount > 0 || optArgsCount > 0 || restAssignment != null) {
            int currentArgElement;
            for (currentArgElement = 0; currentArgElement < requiredArgsCount; ++currentArgElement) {
                this.method.aload(this.argsIndex);
                this.method.pushInt(currentArgElement);
                this.method.arrayload();
                requiredAssignment.nextValue(this.methodCompiler, requiredArgs, currentArgElement);
            }
            if (optArgsCount > 0) {
                final Label doneWithOpt = new Label();
                final Label[] optLabels = new Label[optArgsCount];
                for (int i = 0; i < optLabels.length; ++i) {
                    optLabels[i] = new Label();
                }
                for (int optArgElement = 0; optArgElement < optArgsCount; ++optArgElement) {
                    this.method.aload(this.argsIndex);
                    this.method.pushInt(currentArgElement);
                    this.methodCompiler.invokeUtilityMethod("elementOrNull", CodegenUtils.sig(IRubyObject.class, IRubyObject[].class, Integer.TYPE));
                    this.method.dup();
                    this.method.ifnull(optLabels[optArgElement]);
                    optGivenAssignment.nextValue(this.methodCompiler, optArgs, optArgElement);
                    ++currentArgElement;
                }
                this.method.go_to(doneWithOpt);
                for (int optArgElement = 0; optArgElement < optArgsCount; ++optArgElement) {
                    this.method.label(optLabels[optArgElement]);
                    optNotGivenAssignment.nextValue(this.methodCompiler, optArgs, optArgElement);
                }
                this.method.pop();
                this.method.label(doneWithOpt);
            }
            if (restAssignment != null) {
                this.method.aload(this.argsIndex);
                this.methodCompiler.loadRuntime();
                this.method.pushInt(currentArgElement);
                this.methodCompiler.invokeUtilityMethod("createSubarray", CodegenUtils.sig(RubyArray.class, IRubyObject[].class, Ruby.class, Integer.TYPE));
                restAssignment.call(this.methodCompiler);
            }
        }
        if (blockAssignment != null) {
            this.methodCompiler.loadRuntime();
            this.method.aload(this.methodCompiler.getClosureIndex());
            this.methodCompiler.invokeUtilityMethod("processBlockArgument", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(Ruby.class, Block.class)));
            blockAssignment.call(this.methodCompiler);
        }
    }
    
    public void assignMethodArguments19(final Object preArgs, final int preArgsCount, final Object postArgs, final int postArgsCount, final int postArgsIndex, final Object optArgs, final int optArgsCount, final ArrayCallback requiredAssignment, final ArrayCallback optGivenAssignment, final ArrayCallback optNotGivenAssignment, final CompilerCallback restAssignment, final CompilerCallback blockAssignment) {
        if (this.specificArity) {
            for (int currentArgElement = 0; currentArgElement < this.scope.getRequiredArgs(); ++currentArgElement) {
                this.method.aload(this.argsIndex + currentArgElement);
                requiredAssignment.nextValue(this.methodCompiler, preArgs, currentArgElement);
            }
        }
        else if (preArgsCount > 0 || postArgsCount > 0 || optArgsCount > 0 || restAssignment != null) {
            int currentArgElement;
            for (currentArgElement = 0; currentArgElement < preArgsCount; ++currentArgElement) {
                this.method.aload(this.argsIndex);
                this.method.pushInt(currentArgElement);
                this.methodCompiler.loadNil();
                this.methodCompiler.invokeUtilityMethod("elementOrNil", CodegenUtils.sig(IRubyObject.class, IRubyObject[].class, Integer.TYPE, IRubyObject.class));
                requiredAssignment.nextValue(this.methodCompiler, preArgs, currentArgElement);
            }
            if (optArgsCount > 0) {
                final Label doneWithOpt = new Label();
                final Label[] optLabels = new Label[optArgsCount];
                for (int i = 0; i < optLabels.length; ++i) {
                    optLabels[i] = new Label();
                }
                for (int optArgElement = 0; optArgElement < optArgsCount; ++optArgElement) {
                    this.method.aload(this.argsIndex);
                    this.method.pushInt(currentArgElement);
                    this.method.pushInt(postArgsCount);
                    this.methodCompiler.invokeUtilityMethod("optElementOrNull", CodegenUtils.sig(IRubyObject.class, IRubyObject[].class, Integer.TYPE, Integer.TYPE));
                    this.method.dup();
                    this.method.ifnull(optLabels[optArgElement]);
                    optGivenAssignment.nextValue(this.methodCompiler, optArgs, optArgElement);
                    ++currentArgElement;
                }
                this.method.go_to(doneWithOpt);
                for (int optArgElement = 0; optArgElement < optArgsCount; ++optArgElement) {
                    this.method.label(optLabels[optArgElement]);
                    optNotGivenAssignment.nextValue(this.methodCompiler, optArgs, optArgElement);
                }
                this.method.pop();
                this.method.label(doneWithOpt);
            }
            if (restAssignment != null) {
                this.method.aload(this.argsIndex);
                this.methodCompiler.loadRuntime();
                this.method.pushInt(currentArgElement);
                this.method.pushInt(postArgsCount);
                this.methodCompiler.invokeUtilityMethod("createSubarray", CodegenUtils.sig(RubyArray.class, IRubyObject[].class, Ruby.class, Integer.TYPE, Integer.TYPE));
                restAssignment.call(this.methodCompiler);
            }
            for (int postArgIndex = 0; postArgIndex < postArgsCount; ++postArgIndex) {
                this.method.aload(this.argsIndex);
                this.method.pushInt(postArgsCount);
                this.method.pushInt(postArgIndex);
                this.methodCompiler.loadNil();
                this.methodCompiler.invokeUtilityMethod("postElementOrNil", CodegenUtils.sig(IRubyObject.class, IRubyObject[].class, Integer.TYPE, Integer.TYPE, IRubyObject.class));
                requiredAssignment.nextValue(this.methodCompiler, postArgs, postArgIndex);
            }
        }
        if (blockAssignment != null) {
            this.methodCompiler.loadRuntime();
            this.method.aload(this.methodCompiler.getClosureIndex());
            this.methodCompiler.invokeUtilityMethod("processBlockArgument", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(Ruby.class, Block.class)));
            blockAssignment.call(this.methodCompiler);
        }
    }
    
    public void assignClosureArguments(final CompilerCallback masgnCallback, final CompilerCallback blockAssignment) {
        masgnCallback.call(this.methodCompiler);
        if (blockAssignment != null) {
            this.methodCompiler.loadRuntime();
            this.method.aload(this.methodCompiler.getClosureIndex());
            this.methodCompiler.invokeUtilityMethod("processBlockArgument", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(Ruby.class, Block.class)));
            blockAssignment.call(this.methodCompiler);
        }
    }
    
    public int grabTempLocal() {
        return this.tempVariableIndex++;
    }
    
    public void setTempLocal(final int index) {
        this.method.astore(index);
    }
    
    public void getTempLocal(final int index) {
        this.method.aload(index);
    }
    
    public void releaseTempLocal() {
        --this.tempVariableIndex;
    }
    
    public boolean isHeap() {
        return false;
    }
    
    protected void assignHeapLocal(final CompilerCallback value, final int depth, final int index, final boolean expr) {
        switch (index) {
            case 0: {
                this.unwrapParentScopes(depth);
                value.call(this.methodCompiler);
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "setValueZeroDepthZero", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(IRubyObject.class)));
                break;
            }
            case 1: {
                this.unwrapParentScopes(depth);
                value.call(this.methodCompiler);
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "setValueOneDepthZero", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(IRubyObject.class)));
                break;
            }
            case 2: {
                this.unwrapParentScopes(depth);
                value.call(this.methodCompiler);
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "setValueTwoDepthZero", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(IRubyObject.class)));
                break;
            }
            case 3: {
                this.unwrapParentScopes(depth);
                value.call(this.methodCompiler);
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "setValueThreeDepthZero", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(IRubyObject.class)));
                break;
            }
            default: {
                this.method.aload(this.methodCompiler.getDynamicScopeIndex());
                this.method.pushInt(index);
                value.call(this.methodCompiler);
                this.method.pushInt(depth);
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "setValue", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(Integer.TYPE, IRubyObject.class, Integer.TYPE)));
                break;
            }
        }
        if (!expr) {
            this.method.pop();
        }
    }
    
    protected void assignHeapLocal(final int depth, final int index, final boolean expr) {
        switch (index) {
            case 0: {
                this.unwrapParentScopes(depth);
                this.method.swap();
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "setValueZeroDepthZero", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(IRubyObject.class)));
                break;
            }
            case 1: {
                this.unwrapParentScopes(depth);
                this.method.swap();
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "setValueOneDepthZero", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(IRubyObject.class)));
                break;
            }
            case 2: {
                this.unwrapParentScopes(depth);
                this.method.swap();
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "setValueTwoDepthZero", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(IRubyObject.class)));
                break;
            }
            case 3: {
                this.unwrapParentScopes(depth);
                this.method.swap();
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "setValueThreeDepthZero", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(IRubyObject.class)));
                break;
            }
            default: {
                this.method.aload(this.methodCompiler.getDynamicScopeIndex());
                this.method.swap();
                this.method.pushInt(index);
                this.method.pushInt(depth);
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "setValue", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(IRubyObject.class, Integer.TYPE, Integer.TYPE)));
                break;
            }
        }
        if (!expr) {
            this.method.pop();
        }
    }
    
    protected void retrieveHeapLocal(final int depth, final int index) {
        switch (index) {
            case 0: {
                this.unwrapParentScopes(depth);
                this.methodCompiler.loadNil();
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "getValueZeroDepthZeroOrNil", CodegenUtils.sig(IRubyObject.class, IRubyObject.class));
                break;
            }
            case 1: {
                this.unwrapParentScopes(depth);
                this.methodCompiler.loadNil();
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "getValueOneDepthZeroOrNil", CodegenUtils.sig(IRubyObject.class, IRubyObject.class));
                break;
            }
            case 2: {
                this.unwrapParentScopes(depth);
                this.methodCompiler.loadNil();
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "getValueTwoDepthZeroOrNil", CodegenUtils.sig(IRubyObject.class, IRubyObject.class));
                break;
            }
            case 3: {
                this.unwrapParentScopes(depth);
                this.methodCompiler.loadNil();
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "getValueThreeDepthZeroOrNil", CodegenUtils.sig(IRubyObject.class, IRubyObject.class));
                break;
            }
            default: {
                this.method.aload(this.methodCompiler.getDynamicScopeIndex());
                this.method.pushInt(index);
                this.method.pushInt(depth);
                this.methodCompiler.loadNil();
                this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "getValueOrNil", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(Integer.TYPE, Integer.TYPE, IRubyObject.class)));
                break;
            }
        }
    }
    
    protected void unwrapParentScopes(int depth) {
        this.method.aload(this.methodCompiler.getDynamicScopeIndex());
        while (depth > 0) {
            this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "getNextCapturedScope", CodegenUtils.sig(DynamicScope.class, new Class[0]));
            --depth;
        }
    }
}
