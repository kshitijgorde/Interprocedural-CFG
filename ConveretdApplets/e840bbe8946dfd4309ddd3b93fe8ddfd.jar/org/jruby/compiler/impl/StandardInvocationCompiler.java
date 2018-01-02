// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.impl;

import org.jruby.RubyModule;
import org.jruby.RubyClass;
import org.jruby.runtime.Block;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.RubyFloat;
import org.jruby.RubyFixnum;
import org.jruby.RubyInstanceConfig;
import org.jruby.compiler.NotCompilableException;
import org.jruby.org.objectweb.asm.Label;
import org.jruby.runtime.ThreadContext;
import org.jruby.util.CodegenUtils;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.CallSite;
import org.jruby.runtime.CallType;
import org.jruby.compiler.BodyCompiler;
import org.jruby.compiler.ArgumentsCallback;
import org.jruby.compiler.CompilerCallback;
import org.jruby.compiler.InvocationCompiler;

public class StandardInvocationCompiler implements InvocationCompiler
{
    protected BaseBodyCompiler methodCompiler;
    protected SkinnyMethodAdapter method;
    
    public StandardInvocationCompiler(final BaseBodyCompiler methodCompiler, final SkinnyMethodAdapter method) {
        this.methodCompiler = methodCompiler;
        this.method = method;
    }
    
    public SkinnyMethodAdapter getMethodAdapter() {
        return this.method;
    }
    
    public void setMethodAdapter(final SkinnyMethodAdapter sma) {
        this.method = sma;
    }
    
    public void invokeAttrAssignMasgn(final String name, final CompilerCallback receiverCallback, final ArgumentsCallback argsCallback) {
        final int temp = this.methodCompiler.getVariableCompiler().grabTempLocal();
        this.methodCompiler.getVariableCompiler().setTempLocal(temp);
        receiverCallback.call(this.methodCompiler);
        this.method.dup();
        this.methodCompiler.loadSelf();
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheCallSite(this.methodCompiler, name, CallType.NORMAL);
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheCallSite(this.methodCompiler, name, CallType.VARIABLE);
        this.methodCompiler.invokeUtilityMethod("selectAttrAsgnCallSite", CodegenUtils.sig(CallSite.class, IRubyObject.class, IRubyObject.class, CallSite.class, CallSite.class));
        String signature = null;
        if (argsCallback == null) {
            signature = CodegenUtils.sig(IRubyObject.class, IRubyObject.class, CallSite.class, IRubyObject.class, ThreadContext.class, IRubyObject.class);
        }
        else {
            switch (argsCallback.getArity()) {
                case 1: {
                    argsCallback.call(this.methodCompiler);
                    signature = CodegenUtils.sig(IRubyObject.class, IRubyObject.class, CallSite.class, IRubyObject.class, IRubyObject.class, ThreadContext.class, IRubyObject.class);
                    break;
                }
                case 2: {
                    argsCallback.call(this.methodCompiler);
                    signature = CodegenUtils.sig(IRubyObject.class, IRubyObject.class, CallSite.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, ThreadContext.class, IRubyObject.class);
                    break;
                }
                case 3: {
                    argsCallback.call(this.methodCompiler);
                    signature = CodegenUtils.sig(IRubyObject.class, IRubyObject.class, CallSite.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, ThreadContext.class, IRubyObject.class);
                    break;
                }
                default: {
                    argsCallback.call(this.methodCompiler);
                    signature = CodegenUtils.sig(IRubyObject.class, IRubyObject.class, CallSite.class, IRubyObject[].class, IRubyObject.class, ThreadContext.class, IRubyObject.class);
                    break;
                }
            }
        }
        this.methodCompiler.getVariableCompiler().getTempLocal(temp);
        this.methodCompiler.getVariableCompiler().releaseTempLocal();
        this.methodCompiler.loadThreadContext();
        this.methodCompiler.loadSelf();
        this.methodCompiler.invokeUtilityMethod("doAttrAsgn", signature);
    }
    
    public void invokeAttrAssign(final String name, final CompilerCallback receiverCallback, final ArgumentsCallback argsCallback) {
        receiverCallback.call(this.methodCompiler);
        this.method.dup();
        this.methodCompiler.loadSelf();
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheCallSite(this.methodCompiler, name, CallType.NORMAL);
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheCallSite(this.methodCompiler, name, CallType.VARIABLE);
        this.methodCompiler.invokeUtilityMethod("selectAttrAsgnCallSite", CodegenUtils.sig(CallSite.class, IRubyObject.class, IRubyObject.class, CallSite.class, CallSite.class));
        String signature = null;
        switch (argsCallback.getArity()) {
            case 1: {
                signature = CodegenUtils.sig(IRubyObject.class, IRubyObject.class, CallSite.class, IRubyObject.class, ThreadContext.class, IRubyObject.class);
                break;
            }
            case 2: {
                signature = CodegenUtils.sig(IRubyObject.class, IRubyObject.class, CallSite.class, IRubyObject.class, IRubyObject.class, ThreadContext.class, IRubyObject.class);
                break;
            }
            case 3: {
                signature = CodegenUtils.sig(IRubyObject.class, IRubyObject.class, CallSite.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, ThreadContext.class, IRubyObject.class);
                break;
            }
            default: {
                signature = CodegenUtils.sig(IRubyObject.class, IRubyObject.class, CallSite.class, IRubyObject[].class, ThreadContext.class, IRubyObject.class);
                break;
            }
        }
        argsCallback.call(this.methodCompiler);
        this.methodCompiler.loadThreadContext();
        this.methodCompiler.loadSelf();
        this.methodCompiler.invokeUtilityMethod("doAttrAsgn", signature);
    }
    
    public void opElementAsgnWithOr(final CompilerCallback receiver, final ArgumentsCallback args, final CompilerCallback valueCallback) {
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheCallSite(this.methodCompiler, "[]", CallType.FUNCTIONAL);
        this.methodCompiler.loadThreadContext();
        this.methodCompiler.loadSelf();
        receiver.call(this.methodCompiler);
        args.call(this.methodCompiler);
        this.method.dup2();
        final int argsLocal = this.methodCompiler.getVariableCompiler().grabTempLocal();
        this.methodCompiler.getVariableCompiler().setTempLocal(argsLocal);
        final int receiverLocal = this.methodCompiler.getVariableCompiler().grabTempLocal();
        this.methodCompiler.getVariableCompiler().setTempLocal(receiverLocal);
        switch (args.getArity()) {
            case 1: {
                this.method.invokevirtual(CodegenUtils.p(CallSite.class), "call", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class));
                break;
            }
            default: {
                this.method.invokevirtual(CodegenUtils.p(CallSite.class), "call", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject[].class));
                break;
            }
        }
        this.method.dup();
        this.methodCompiler.invokeIRubyObject("isTrue", CodegenUtils.sig(Boolean.TYPE, new Class[0]));
        final Label done = new Label();
        this.method.ifne(done);
        this.method.pop();
        this.methodCompiler.loadThreadContext();
        this.methodCompiler.loadSelf();
        this.methodCompiler.getVariableCompiler().getTempLocal(receiverLocal);
        this.methodCompiler.getVariableCompiler().getTempLocal(argsLocal);
        valueCallback.call(this.methodCompiler);
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheCallSite(this.methodCompiler, "[]=", CallType.FUNCTIONAL);
        switch (args.getArity()) {
            case 0: {
                throw new NotCompilableException("Op Element Asgn with zero-arity args");
            }
            case 1: {
                this.methodCompiler.invokeUtilityMethod("opElementAsgnWithOrPartTwoOneArg", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, CallSite.class));
                break;
            }
            case 2: {
                this.methodCompiler.invokeUtilityMethod("opElementAsgnWithOrPartTwoTwoArgs", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject[].class, IRubyObject.class, CallSite.class));
                break;
            }
            case 3: {
                this.methodCompiler.invokeUtilityMethod("opElementAsgnWithOrPartTwoThreeArgs", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject[].class, IRubyObject.class, CallSite.class));
                break;
            }
            default: {
                this.methodCompiler.invokeUtilityMethod("opElementAsgnWithOrPartTwoNArgs", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject[].class, IRubyObject.class, CallSite.class));
                break;
            }
        }
        this.method.label(done);
        this.methodCompiler.getVariableCompiler().releaseTempLocal();
        this.methodCompiler.getVariableCompiler().releaseTempLocal();
    }
    
    public void opElementAsgnWithAnd(final CompilerCallback receiver, final ArgumentsCallback args, final CompilerCallback valueCallback) {
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheCallSite(this.methodCompiler, "[]", CallType.FUNCTIONAL);
        this.methodCompiler.loadThreadContext();
        this.methodCompiler.loadSelf();
        receiver.call(this.methodCompiler);
        args.call(this.methodCompiler);
        this.method.dup2();
        final int argsLocal = this.methodCompiler.getVariableCompiler().grabTempLocal();
        this.methodCompiler.getVariableCompiler().setTempLocal(argsLocal);
        final int receiverLocal = this.methodCompiler.getVariableCompiler().grabTempLocal();
        this.methodCompiler.getVariableCompiler().setTempLocal(receiverLocal);
        switch (args.getArity()) {
            case 1: {
                this.method.invokevirtual(CodegenUtils.p(CallSite.class), "call", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class));
                break;
            }
            default: {
                this.method.invokevirtual(CodegenUtils.p(CallSite.class), "call", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject[].class));
                break;
            }
        }
        this.method.dup();
        this.methodCompiler.invokeIRubyObject("isTrue", CodegenUtils.sig(Boolean.TYPE, new Class[0]));
        final Label done = new Label();
        this.method.ifeq(done);
        this.method.pop();
        this.methodCompiler.loadThreadContext();
        this.methodCompiler.loadSelf();
        this.methodCompiler.getVariableCompiler().getTempLocal(receiverLocal);
        this.methodCompiler.getVariableCompiler().getTempLocal(argsLocal);
        valueCallback.call(this.methodCompiler);
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheCallSite(this.methodCompiler, "[]=", CallType.FUNCTIONAL);
        switch (args.getArity()) {
            case 0: {
                throw new NotCompilableException("Op Element Asgn with zero-arity args");
            }
            case 1: {
                this.methodCompiler.invokeUtilityMethod("opElementAsgnWithOrPartTwoOneArg", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, CallSite.class));
                break;
            }
            case 2: {
                this.methodCompiler.invokeUtilityMethod("opElementAsgnWithOrPartTwoTwoArgs", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject[].class, IRubyObject.class, CallSite.class));
                break;
            }
            case 3: {
                this.methodCompiler.invokeUtilityMethod("opElementAsgnWithOrPartTwoThreeArgs", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject[].class, IRubyObject.class, CallSite.class));
                break;
            }
            default: {
                this.methodCompiler.invokeUtilityMethod("opElementAsgnWithOrPartTwoNArgs", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject[].class, IRubyObject.class, CallSite.class));
                break;
            }
        }
        this.method.label(done);
        this.methodCompiler.getVariableCompiler().releaseTempLocal();
        this.methodCompiler.getVariableCompiler().releaseTempLocal();
    }
    
    public void opElementAsgnWithMethod(final CompilerCallback receiver, final ArgumentsCallback args, final CompilerCallback valueCallback, final String operator) {
        this.methodCompiler.loadThreadContext();
        this.methodCompiler.loadSelf();
        receiver.call(this.methodCompiler);
        args.call(this.methodCompiler);
        valueCallback.call(this.methodCompiler);
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheCallSite(this.methodCompiler, "[]", CallType.FUNCTIONAL);
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheCallSite(this.methodCompiler, operator, CallType.NORMAL);
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheCallSite(this.methodCompiler, "[]=", CallType.FUNCTIONAL);
        switch (args.getArity()) {
            case 0: {
                this.methodCompiler.invokeUtilityMethod("opElementAsgnWithMethod", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, CallSite.class, CallSite.class, CallSite.class));
                break;
            }
            case 1: {
                this.methodCompiler.invokeUtilityMethod("opElementAsgnWithMethod", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, CallSite.class, CallSite.class, CallSite.class));
                break;
            }
            case 2: {
                this.methodCompiler.invokeUtilityMethod("opElementAsgnWithMethod", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, CallSite.class, CallSite.class, CallSite.class));
                break;
            }
            case 3: {
                this.methodCompiler.invokeUtilityMethod("opElementAsgnWithMethod", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, CallSite.class, CallSite.class, CallSite.class));
                break;
            }
            default: {
                this.methodCompiler.invokeUtilityMethod("opElementAsgnWithMethod", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject[].class, IRubyObject.class, CallSite.class, CallSite.class, CallSite.class));
                break;
            }
        }
    }
    
    public void invokeBinaryFixnumRHS(final String name, final CompilerCallback receiverCallback, final long fixnum) {
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheCallSite(this.methodCompiler, name, CallType.NORMAL);
        this.methodCompiler.loadThreadContext();
        this.methodCompiler.loadSelf();
        if (receiverCallback != null) {
            receiverCallback.call(this.methodCompiler);
        }
        else {
            this.methodCompiler.loadSelf();
        }
        this.method.ldc(fixnum);
        final String signature = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, IRubyObject.class, Long.TYPE));
        final String callSiteMethod = "call";
        this.method.invokevirtual(CodegenUtils.p(CallSite.class), callSiteMethod, signature);
    }
    
    public void invokeBinaryFloatRHS(final String name, final CompilerCallback receiverCallback, final double flote) {
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheCallSite(this.methodCompiler, name, CallType.NORMAL);
        this.methodCompiler.loadThreadContext();
        this.methodCompiler.loadSelf();
        if (receiverCallback != null) {
            receiverCallback.call(this.methodCompiler);
        }
        else {
            this.methodCompiler.loadSelf();
        }
        this.method.ldc(flote);
        final String signature = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, IRubyObject.class, Double.TYPE));
        final String callSiteMethod = "call";
        this.method.invokevirtual(CodegenUtils.p(CallSite.class), callSiteMethod, signature);
    }
    
    public void invokeFixnumLong(final String rubyName, final int moduleGeneration, final CompilerCallback receiverCallback, final String methodName, final long fixnum) {
        receiverCallback.call(this.methodCompiler);
        final int tmp = this.methodCompiler.getVariableCompiler().grabTempLocal();
        this.method.astore(tmp);
        final Label slow = new Label();
        final Label after = new Label();
        if (!RubyInstanceConfig.NOGUARDS_COMPILE_ENABLED) {
            this.method.aload(tmp);
            this.method.ldc(moduleGeneration);
            this.methodCompiler.invokeUtilityMethod("isGenerationEqual", CodegenUtils.sig(Boolean.TYPE, IRubyObject.class, Integer.TYPE));
            this.method.ifeq(slow);
        }
        this.method.aload(tmp);
        this.method.checkcast(CodegenUtils.p(RubyFixnum.class));
        this.methodCompiler.loadThreadContext();
        this.method.ldc(fixnum);
        this.method.invokevirtual(CodegenUtils.p(RubyFixnum.class), methodName, CodegenUtils.sig(IRubyObject.class, ThreadContext.class, Long.TYPE));
        if (!RubyInstanceConfig.NOGUARDS_COMPILE_ENABLED) {
            this.method.go_to(after);
            this.method.label(slow);
            this.invokeBinaryFixnumRHS(rubyName, new CompilerCallback() {
                public void call(final BodyCompiler context) {
                    StandardInvocationCompiler.this.method.aload(tmp);
                }
            }, fixnum);
            this.method.label(after);
        }
        this.methodCompiler.getVariableCompiler().releaseTempLocal();
    }
    
    public void invokeFloatDouble(final String rubyName, final int moduleGeneration, final CompilerCallback receiverCallback, final String methodName, final double flote) {
        receiverCallback.call(this.methodCompiler);
        final int tmp = this.methodCompiler.getVariableCompiler().grabTempLocal();
        this.method.astore(tmp);
        final Label slow = new Label();
        final Label after = new Label();
        if (!RubyInstanceConfig.NOGUARDS_COMPILE_ENABLED) {
            this.method.aload(tmp);
            this.method.ldc(moduleGeneration);
            this.methodCompiler.invokeUtilityMethod("isGenerationEqual", CodegenUtils.sig(Boolean.TYPE, IRubyObject.class, Integer.TYPE));
            this.method.ifeq(slow);
        }
        this.method.aload(tmp);
        this.method.checkcast(CodegenUtils.p(RubyFloat.class));
        this.methodCompiler.loadThreadContext();
        this.method.ldc(flote);
        this.method.invokevirtual(CodegenUtils.p(RubyFloat.class), methodName, CodegenUtils.sig(IRubyObject.class, ThreadContext.class, Double.TYPE));
        if (!RubyInstanceConfig.NOGUARDS_COMPILE_ENABLED) {
            this.method.go_to(after);
            this.method.label(slow);
            this.invokeBinaryFloatRHS(rubyName, new CompilerCallback() {
                public void call(final BodyCompiler context) {
                    StandardInvocationCompiler.this.method.aload(tmp);
                }
            }, flote);
            this.method.label(after);
        }
        this.methodCompiler.getVariableCompiler().releaseTempLocal();
    }
    
    public void invokeRecursive(final String name, final int moduleGeneration, final ArgumentsCallback argsCallback, final CompilerCallback closure, final CallType callType, final boolean iterator) {
        if (this.methodCompiler.getVariableCompiler().isHeap()) {
            this.invokeDynamic(name, null, argsCallback, callType, closure, iterator);
        }
        else {
            final Label slow = new Label();
            final Label after = new Label();
            if (!RubyInstanceConfig.NOGUARDS_COMPILE_ENABLED) {
                this.methodCompiler.loadSelf();
                this.method.ldc(moduleGeneration);
                this.methodCompiler.invokeUtilityMethod("isGenerationEqual", CodegenUtils.sig(Boolean.TYPE, IRubyObject.class, Integer.TYPE));
                this.method.ifeq(slow);
            }
            this.method.aload(0);
            this.methodCompiler.loadThreadContext();
            this.methodCompiler.loadSelf();
            if (argsCallback != null) {
                argsCallback.call(this.methodCompiler);
            }
            this.method.aconst_null();
            this.method.invokestatic(this.methodCompiler.getScriptCompiler().getClassname(), this.methodCompiler.getNativeMethodName(), this.methodCompiler.getSignature());
            if (!RubyInstanceConfig.NOGUARDS_COMPILE_ENABLED) {
                this.method.go_to(after);
                this.method.label(slow);
                this.invokeDynamic(name, null, argsCallback, callType, closure, iterator);
                this.method.label(after);
            }
        }
    }
    
    public void invokeNative(final String name, final DynamicMethod.NativeCall nativeCall, final int moduleGeneration, final CompilerCallback receiver, final ArgumentsCallback args, final CompilerCallback closure, final CallType callType, final boolean iterator) {
        final Class[] nativeSignature = nativeCall.getNativeSignature();
        int leadingArgs = 0;
        receiver.call(this.methodCompiler);
        final int tmp = this.methodCompiler.getVariableCompiler().grabTempLocal();
        this.method.astore(tmp);
        int[] _argTmp = null;
        if (args != null) {
            args.call(this.methodCompiler);
            switch (args.getArity()) {
                case 3: {
                    _argTmp = new int[3];
                    this.method.astore(_argTmp[2] = this.methodCompiler.getVariableCompiler().grabTempLocal());
                }
                case 2: {
                    if (_argTmp == null) {
                        _argTmp = new int[2];
                    }
                    this.method.astore(_argTmp[1] = this.methodCompiler.getVariableCompiler().grabTempLocal());
                    break;
                }
            }
            if (_argTmp == null) {
                _argTmp = new int[] { 0 };
            }
            this.method.astore(_argTmp[0] = this.methodCompiler.getVariableCompiler().grabTempLocal());
            leadingArgs += args.getArity();
        }
        final int[] argTmp = _argTmp;
        final Label slow = new Label();
        final Label after = new Label();
        if (!RubyInstanceConfig.NOGUARDS_COMPILE_ENABLED) {
            this.method.aload(tmp);
            this.method.ldc(moduleGeneration);
            this.methodCompiler.invokeUtilityMethod("isGenerationEqual", CodegenUtils.sig(Boolean.TYPE, IRubyObject.class, Integer.TYPE));
            this.method.ifeq(slow);
        }
        if (nativeCall.isStatic()) {
            if (nativeSignature.length > 0 && nativeSignature[0] == ThreadContext.class) {
                this.methodCompiler.loadThreadContext();
                ++leadingArgs;
            }
            this.method.aload(tmp);
            ++leadingArgs;
        }
        else {
            this.method.aload(tmp);
            this.method.checkcast(CodegenUtils.p(nativeCall.getNativeTarget()));
            if (nativeSignature.length > 0 && nativeSignature[0] == ThreadContext.class) {
                this.methodCompiler.loadThreadContext();
                ++leadingArgs;
            }
        }
        if (args != null) {
            switch (args.getArity()) {
                default: {
                    this.method.aload(argTmp[0]);
                    break;
                }
                case 2: {
                    this.method.aload(argTmp[0]);
                    this.method.aload(argTmp[1]);
                    break;
                }
                case 3: {
                    this.method.aload(argTmp[0]);
                    this.method.aload(argTmp[1]);
                    this.method.aload(argTmp[2]);
                    break;
                }
            }
        }
        if (closure != null) {
            closure.call(this.methodCompiler);
            if (nativeSignature.length != leadingArgs + 1 || nativeSignature[leadingArgs] != Block.class) {
                this.method.pop();
            }
        }
        else if (nativeSignature.length == leadingArgs + 1 && nativeSignature[leadingArgs] == Block.class) {
            this.method.getstatic(CodegenUtils.p(Block.class), "NULL_BLOCK", CodegenUtils.ci(Block.class));
        }
        if (nativeCall.isStatic()) {
            this.method.invokestatic(CodegenUtils.p(nativeCall.getNativeTarget()), nativeCall.getNativeName(), CodegenUtils.sig(nativeCall.getNativeReturn(), nativeSignature));
        }
        else {
            this.method.invokevirtual(CodegenUtils.p(nativeCall.getNativeTarget()), nativeCall.getNativeName(), CodegenUtils.sig(nativeCall.getNativeReturn(), nativeSignature));
        }
        if (!RubyInstanceConfig.NOGUARDS_COMPILE_ENABLED) {
            this.method.go_to(after);
            this.method.label(slow);
            ArgumentsCallback newArgs = null;
            if (args != null) {
                newArgs = new ArgumentsCallback() {
                    public int getArity() {
                        return args.getArity();
                    }
                    
                    public void call(final BodyCompiler context) {
                        switch (args.getArity()) {
                            default: {
                                StandardInvocationCompiler.this.method.aload(argTmp[0]);
                                break;
                            }
                            case 2: {
                                StandardInvocationCompiler.this.method.aload(argTmp[0]);
                                StandardInvocationCompiler.this.method.aload(argTmp[1]);
                                break;
                            }
                            case 3: {
                                StandardInvocationCompiler.this.method.aload(argTmp[0]);
                                StandardInvocationCompiler.this.method.aload(argTmp[1]);
                                StandardInvocationCompiler.this.method.aload(argTmp[2]);
                                break;
                            }
                        }
                    }
                };
            }
            this.invokeDynamic(name, new CompilerCallback() {
                public void call(final BodyCompiler context) {
                    StandardInvocationCompiler.this.method.aload(tmp);
                }
            }, newArgs, callType, closure, iterator);
            this.method.label(after);
        }
        this.methodCompiler.getVariableCompiler().releaseTempLocal();
        if (argTmp != null) {
            for (final int i : argTmp) {
                this.methodCompiler.getVariableCompiler().releaseTempLocal();
            }
        }
    }
    
    public void invokeTrivial(final String name, final int moduleGeneration, final CompilerCallback body) {
        final Label slow = new Label();
        final Label after = new Label();
        if (!RubyInstanceConfig.NOGUARDS_COMPILE_ENABLED) {
            this.methodCompiler.loadSelf();
            this.method.ldc(moduleGeneration);
            this.methodCompiler.invokeUtilityMethod("isGenerationEqual", CodegenUtils.sig(Boolean.TYPE, IRubyObject.class, Integer.TYPE));
            this.method.iffalse(slow);
        }
        body.call(this.methodCompiler);
        if (!RubyInstanceConfig.NOGUARDS_COMPILE_ENABLED) {
            this.method.go_to(after);
            this.method.label(slow);
            this.invokeDynamic(name, null, null, CallType.FUNCTIONAL, null, false);
            this.method.label(after);
        }
    }
    
    public void invokeDynamic(final String name, final CompilerCallback receiverCallback, final ArgumentsCallback argsCallback, final CallType callType, CompilerCallback closureArg, final boolean iterator) {
        if (RubyInstanceConfig.INLINE_DYNCALL_ENABLED && closureArg == null && (callType == CallType.FUNCTIONAL || callType == CallType.VARIABLE)) {
            if (argsCallback == null) {
                this.invokeDynamicSelfNoBlockZero(name);
                return;
            }
            if (argsCallback.getArity() >= 1 && argsCallback.getArity() <= 3) {
                this.invokeDynamicSelfNoBlockSpecificArity(name, argsCallback);
                return;
            }
        }
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheCallSite(this.methodCompiler, name, callType);
        this.methodCompiler.loadThreadContext();
        this.methodCompiler.loadSelf();
        if (receiverCallback != null) {
            receiverCallback.call(this.methodCompiler);
        }
        else {
            this.methodCompiler.loadSelf();
        }
        if (callType == CallType.SUPER && closureArg == null) {
            closureArg = new CompilerCallback() {
                public void call(final BodyCompiler context) {
                    StandardInvocationCompiler.this.methodCompiler.loadBlock();
                }
            };
        }
        String callSiteMethod = "call";
        String signature = null;
        if (argsCallback == null) {
            if (closureArg == null) {
                signature = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, IRubyObject.class));
            }
            else {
                if (iterator) {
                    callSiteMethod = "callIter";
                }
                closureArg.call(this.methodCompiler);
                signature = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, IRubyObject.class, Block.class));
            }
        }
        else {
            argsCallback.call(this.methodCompiler);
            if (closureArg == null) {
                switch (argsCallback.getArity()) {
                    case 1: {
                        signature = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class));
                        break;
                    }
                    case 2: {
                        signature = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, IRubyObject.class));
                        break;
                    }
                    case 3: {
                        signature = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, IRubyObject.class));
                        break;
                    }
                    default: {
                        signature = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject[].class));
                        break;
                    }
                }
            }
            else {
                if (iterator) {
                    callSiteMethod = "callIter";
                }
                closureArg.call(this.methodCompiler);
                switch (argsCallback.getArity()) {
                    case 1: {
                        signature = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, Block.class));
                        break;
                    }
                    case 2: {
                        signature = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, Block.class));
                        break;
                    }
                    case 3: {
                        signature = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, Block.class));
                        break;
                    }
                    default: {
                        signature = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject[].class, Block.class));
                        break;
                    }
                }
            }
        }
        this.method.invokevirtual(CodegenUtils.p(CallSite.class), callSiteMethod, signature);
    }
    
    public void invokeDynamicSelfNoBlockZero(final String name) {
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheMethod(this.methodCompiler, name);
        this.methodCompiler.loadThreadContext();
        this.methodCompiler.loadSelf();
        this.method.dup();
        this.method.invokeinterface(CodegenUtils.p(IRubyObject.class), "getMetaClass", CodegenUtils.sig(RubyClass.class, new Class[0]));
        this.method.ldc(name);
        this.method.invokevirtual(CodegenUtils.p(DynamicMethod.class), "call", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, RubyModule.class, String.class));
    }
    
    public void invokeDynamicSelfNoBlockSpecificArity(final String name, final ArgumentsCallback argsCallback) {
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheMethod(this.methodCompiler, name);
        this.methodCompiler.loadThreadContext();
        this.methodCompiler.loadSelf();
        this.method.dup();
        this.method.invokeinterface(CodegenUtils.p(IRubyObject.class), "getMetaClass", CodegenUtils.sig(RubyClass.class, new Class[0]));
        this.method.ldc(name);
        argsCallback.call(this.methodCompiler);
        switch (argsCallback.getArity()) {
            case 1: {
                this.method.invokevirtual(CodegenUtils.p(DynamicMethod.class), "call", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, RubyModule.class, String.class, IRubyObject.class));
                break;
            }
            case 2: {
                this.method.invokevirtual(CodegenUtils.p(DynamicMethod.class), "call", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, RubyModule.class, String.class, IRubyObject.class, IRubyObject.class));
                break;
            }
            case 3: {
                this.method.invokevirtual(CodegenUtils.p(DynamicMethod.class), "call", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, RubyModule.class, String.class, IRubyObject.class, IRubyObject.class, IRubyObject.class));
                break;
            }
        }
    }
    
    public void invokeDynamicNoBlockZero(final String name, final CompilerCallback receiverCallback) {
        receiverCallback.call(this.methodCompiler);
        final int recv = this.methodCompiler.getVariableCompiler().grabTempLocal();
        this.method.astore(recv);
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheMethod(this.methodCompiler, name, recv);
        this.methodCompiler.loadThreadContext();
        this.method.aload(recv);
        this.methodCompiler.getVariableCompiler().releaseTempLocal();
        this.method.dup();
        this.method.invokeinterface(CodegenUtils.p(IRubyObject.class), "getMetaClass", CodegenUtils.sig(RubyClass.class, new Class[0]));
        this.method.ldc(name);
        this.method.invokevirtual(CodegenUtils.p(DynamicMethod.class), "call", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, RubyModule.class, String.class));
    }
    
    public void invokeDynamicNoBlockSpecificArity(final String name, final CompilerCallback receiverCallback, final ArgumentsCallback argsCallback) {
        receiverCallback.call(this.methodCompiler);
        final int recv = this.methodCompiler.getVariableCompiler().grabTempLocal();
        this.method.astore(recv);
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheMethod(this.methodCompiler, name, recv);
        this.methodCompiler.loadThreadContext();
        this.method.aload(recv);
        this.methodCompiler.getVariableCompiler().releaseTempLocal();
        this.method.dup();
        this.method.invokeinterface(CodegenUtils.p(IRubyObject.class), "getMetaClass", CodegenUtils.sig(RubyClass.class, new Class[0]));
        this.method.ldc(name);
        argsCallback.call(this.methodCompiler);
        switch (argsCallback.getArity()) {
            case 1: {
                this.method.invokevirtual(CodegenUtils.p(DynamicMethod.class), "call", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, RubyModule.class, String.class, IRubyObject.class));
                break;
            }
            case 2: {
                this.method.invokevirtual(CodegenUtils.p(DynamicMethod.class), "call", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, RubyModule.class, String.class, IRubyObject.class, IRubyObject.class));
                break;
            }
            case 3: {
                this.method.invokevirtual(CodegenUtils.p(DynamicMethod.class), "call", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, RubyModule.class, String.class, IRubyObject.class, IRubyObject.class, IRubyObject.class));
                break;
            }
        }
    }
    
    public void invokeOpAsgnWithOr(final String attrName, final String attrAsgnName, final CompilerCallback receiverCallback, final ArgumentsCallback argsCallback) {
        receiverCallback.call(this.methodCompiler);
        this.method.dup();
        this.methodCompiler.loadThreadContext();
        this.methodCompiler.loadSelf();
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheCallSite(this.methodCompiler, attrName, CallType.FUNCTIONAL);
        this.methodCompiler.invokeUtilityMethod("preOpAsgnWithOrAnd", CodegenUtils.sig(IRubyObject.class, IRubyObject.class, ThreadContext.class, IRubyObject.class, CallSite.class));
        final Label done = new Label();
        final Label isTrue = new Label();
        this.method.dup();
        this.methodCompiler.invokeIRubyObject("isTrue", CodegenUtils.sig(Boolean.TYPE, new Class[0]));
        this.method.ifne(isTrue);
        this.method.pop();
        argsCallback.call(this.methodCompiler);
        this.methodCompiler.loadThreadContext();
        this.methodCompiler.loadSelf();
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheCallSite(this.methodCompiler, attrAsgnName, CallType.NORMAL);
        this.methodCompiler.invokeUtilityMethod("postOpAsgnWithOrAnd", CodegenUtils.sig(IRubyObject.class, IRubyObject.class, IRubyObject.class, ThreadContext.class, IRubyObject.class, CallSite.class));
        this.method.go_to(done);
        this.method.label(isTrue);
        this.method.swap();
        this.method.pop();
        this.method.label(done);
    }
    
    public void invokeOpAsgnWithAnd(final String attrName, final String attrAsgnName, final CompilerCallback receiverCallback, final ArgumentsCallback argsCallback) {
        receiverCallback.call(this.methodCompiler);
        this.method.dup();
        this.methodCompiler.loadThreadContext();
        this.methodCompiler.loadSelf();
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheCallSite(this.methodCompiler, attrName, CallType.FUNCTIONAL);
        this.methodCompiler.invokeUtilityMethod("preOpAsgnWithOrAnd", CodegenUtils.sig(IRubyObject.class, IRubyObject.class, ThreadContext.class, IRubyObject.class, CallSite.class));
        final Label done = new Label();
        final Label isFalse = new Label();
        this.method.dup();
        this.methodCompiler.invokeIRubyObject("isTrue", CodegenUtils.sig(Boolean.TYPE, new Class[0]));
        this.method.ifeq(isFalse);
        this.method.pop();
        argsCallback.call(this.methodCompiler);
        this.methodCompiler.loadThreadContext();
        this.methodCompiler.loadSelf();
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheCallSite(this.methodCompiler, attrAsgnName, CallType.NORMAL);
        this.methodCompiler.invokeUtilityMethod("postOpAsgnWithOrAnd", CodegenUtils.sig(IRubyObject.class, IRubyObject.class, IRubyObject.class, ThreadContext.class, IRubyObject.class, CallSite.class));
        this.method.go_to(done);
        this.method.label(isFalse);
        this.method.swap();
        this.method.pop();
        this.method.label(done);
    }
    
    public void invokeOpAsgnWithMethod(final String operatorName, final String attrName, final String attrAsgnName, final CompilerCallback receiverCallback, final ArgumentsCallback argsCallback) {
        this.methodCompiler.loadThreadContext();
        this.methodCompiler.loadSelf();
        receiverCallback.call(this.methodCompiler);
        argsCallback.call(this.methodCompiler);
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheCallSite(this.methodCompiler, attrName, CallType.FUNCTIONAL);
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheCallSite(this.methodCompiler, operatorName, CallType.FUNCTIONAL);
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheCallSite(this.methodCompiler, attrAsgnName, CallType.NORMAL);
        this.methodCompiler.invokeUtilityMethod("opAsgnWithMethod", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, CallSite.class, CallSite.class, CallSite.class));
    }
    
    public void invokeOpElementAsgnWithMethod(final String operatorName, final CompilerCallback receiverCallback, final ArgumentsCallback argsCallback) {
        this.methodCompiler.loadThreadContext();
        this.methodCompiler.loadSelf();
        receiverCallback.call(this.methodCompiler);
        argsCallback.call(this.methodCompiler);
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheCallSite(this.methodCompiler, "[]", CallType.FUNCTIONAL);
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheCallSite(this.methodCompiler, operatorName, CallType.FUNCTIONAL);
        this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheCallSite(this.methodCompiler, "[]=", CallType.NORMAL);
        this.methodCompiler.invokeUtilityMethod("opElementAsgnWithMethod", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, CallSite.class, CallSite.class, CallSite.class));
    }
    
    public void yield(final CompilerCallback argsCallback, final boolean unwrap) {
        this.methodCompiler.loadBlock();
        this.methodCompiler.loadThreadContext();
        if (argsCallback != null) {
            argsCallback.call(this.methodCompiler);
        }
        else {
            this.method.aconst_null();
        }
        if (unwrap) {
            this.method.aconst_null();
            this.method.aconst_null();
            this.method.invokevirtual(CodegenUtils.p(Block.class), "yieldArray", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, RubyModule.class));
        }
        else {
            this.method.invokevirtual(CodegenUtils.p(Block.class), "yield", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class));
        }
    }
    
    public void yieldSpecific(final ArgumentsCallback argsCallback) {
        this.methodCompiler.loadBlock();
        this.methodCompiler.loadThreadContext();
        String signature = null;
        if (argsCallback == null) {
            signature = CodegenUtils.sig(IRubyObject.class, ThreadContext.class);
        }
        else {
            argsCallback.call(this.methodCompiler);
            switch (argsCallback.getArity()) {
                case 1: {
                    signature = CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class);
                    break;
                }
                case 2: {
                    signature = CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class);
                    break;
                }
                case 3: {
                    signature = CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class);
                    break;
                }
                default: {
                    throw new NotCompilableException("Can't do specific-arity call for > 3 args yet");
                }
            }
        }
        this.method.invokevirtual(CodegenUtils.p(Block.class), "yieldSpecific", signature);
    }
    
    public void invokeEqq(final ArgumentsCallback receivers, final CompilerCallback argument) {
        if (argument == null) {
            receivers.call(this.methodCompiler);
            switch (receivers.getArity()) {
                case 1: {
                    this.methodCompiler.invokeUtilityMethod("invokeEqqForCaselessWhen", CodegenUtils.sig(Boolean.TYPE, IRubyObject.class));
                    break;
                }
                case 2: {
                    this.methodCompiler.invokeUtilityMethod("invokeEqqForCaselessWhen", CodegenUtils.sig(Boolean.TYPE, IRubyObject.class, IRubyObject.class));
                    break;
                }
                case 3: {
                    this.methodCompiler.invokeUtilityMethod("invokeEqqForCaselessWhen", CodegenUtils.sig(Boolean.TYPE, IRubyObject.class, IRubyObject.class, IRubyObject.class));
                    break;
                }
                default: {
                    this.methodCompiler.invokeUtilityMethod("invokeEqqForCaselessWhen", CodegenUtils.sig(Boolean.TYPE, IRubyObject[].class));
                    break;
                }
            }
        }
        else {
            this.methodCompiler.getScriptCompiler().getCacheCompiler().cacheCallSite(this.methodCompiler, "===", CallType.NORMAL);
            this.methodCompiler.loadThreadContext();
            this.methodCompiler.loadSelf();
            argument.call(this.methodCompiler);
            receivers.call(this.methodCompiler);
            switch (receivers.getArity()) {
                case 1: {
                    this.methodCompiler.invokeUtilityMethod("invokeEqqForCaseWhen", CodegenUtils.sig(Boolean.TYPE, CallSite.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class));
                    break;
                }
                case 2: {
                    this.methodCompiler.invokeUtilityMethod("invokeEqqForCaseWhen", CodegenUtils.sig(Boolean.TYPE, CallSite.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, IRubyObject.class));
                    break;
                }
                case 3: {
                    this.methodCompiler.invokeUtilityMethod("invokeEqqForCaseWhen", CodegenUtils.sig(Boolean.TYPE, CallSite.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, IRubyObject.class));
                    break;
                }
                default: {
                    this.methodCompiler.invokeUtilityMethod("invokeEqqForCaseWhen", CodegenUtils.sig(Boolean.TYPE, CallSite.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, IRubyObject[].class));
                    break;
                }
            }
        }
    }
}
