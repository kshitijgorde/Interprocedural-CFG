// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.impl;

import org.jcodings.Encoding;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.List;
import org.jruby.compiler.FastSwitchType;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import org.jruby.RubyFixnum;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.util.SafePropertyAccessor;
import org.jruby.RubyException;
import org.jruby.runtime.CallType;
import org.jruby.compiler.ArgumentsCallback;
import org.jruby.ast.util.ArgsUtil;
import org.jruby.MetaClass;
import org.jruby.runtime.Visibility;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.InstanceVariables;
import org.jruby.internal.runtime.GlobalVariables;
import org.jruby.javasupport.JavaUtil;
import org.jruby.RubyMatchData;
import org.jruby.RubyInstanceConfig;
import org.jruby.RubyRegexp;
import org.jruby.RubyBoolean;
import java.io.PrintStream;
import org.jruby.org.objectweb.asm.Type;
import org.jruby.runtime.CompiledBlockCallback;
import org.jruby.runtime.BlockBody;
import org.jruby.util.JavaNameMangler;
import org.jruby.ast.NodeType;
import org.jruby.exceptions.RaiseException;
import org.jruby.exceptions.JumpException;
import org.jruby.compiler.BranchCallback;
import org.jruby.runtime.Block;
import org.jruby.RubyProc;
import org.jruby.RubyRange;
import org.jruby.RubyHash;
import java.util.Arrays;
import org.jruby.RubyArray;
import org.jruby.util.ByteList;
import org.jruby.RubySymbol;
import org.jruby.RubyString;
import org.jruby.compiler.ArrayCallback;
import org.jruby.RubyBignum;
import java.math.BigInteger;
import org.jruby.RubyModule;
import org.jruby.compiler.NotCompilableException;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.RubyClass;
import org.jruby.Ruby;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.DynamicScope;
import org.jruby.util.CodegenUtils;
import org.jruby.runtime.ThreadContext;
import org.jruby.compiler.CompilerCallback;
import java.lang.reflect.InvocationTargetException;
import org.jruby.compiler.ASTInspector;
import org.jruby.parser.StaticScope;
import org.jruby.org.objectweb.asm.Label;
import org.jruby.compiler.InvocationCompiler;
import org.jruby.compiler.VariableCompiler;
import org.jruby.compiler.BodyCompiler;

public abstract class BaseBodyCompiler implements BodyCompiler
{
    protected SkinnyMethodAdapter method;
    protected VariableCompiler variableCompiler;
    protected InvocationCompiler invocationCompiler;
    protected int argParamCount;
    protected Label[] currentLoopLabels;
    protected Label scopeStart;
    protected Label scopeEnd;
    protected Label redoJump;
    protected boolean inNestedMethod;
    private int lastLine;
    private int lastPositionLine;
    protected StaticScope scope;
    protected ASTInspector inspector;
    protected String methodName;
    protected String rubyName;
    protected StandardASMCompiler script;
    
    public BaseBodyCompiler(final StandardASMCompiler scriptCompiler, final String methodName, final String rubyName, final ASTInspector inspector, final StaticScope scope) {
        this.scopeStart = new Label();
        this.scopeEnd = new Label();
        this.inNestedMethod = false;
        this.lastLine = -1;
        this.lastPositionLine = -1;
        this.script = scriptCompiler;
        this.scope = scope;
        this.inspector = inspector;
        this.methodName = methodName;
        this.rubyName = rubyName;
        this.argParamCount = this.getActualArgsCount(scope);
        this.method = new SkinnyMethodAdapter(this.script.getClassVisitor(), 9, methodName, this.getSignature(), null, null);
        this.createVariableCompiler();
        if (StandardASMCompiler.invDynInvCompilerConstructor != null) {
            try {
                this.invocationCompiler = StandardASMCompiler.invDynInvCompilerConstructor.newInstance(this, this.method);
            }
            catch (InstantiationException ie) {}
            catch (IllegalAccessException ie2) {}
            catch (InvocationTargetException ex) {}
        }
        if (this.invocationCompiler == null) {
            this.invocationCompiler = new StandardInvocationCompiler(this, this.method);
        }
    }
    
    public String getNativeMethodName() {
        return this.methodName;
    }
    
    public String getRubyName() {
        return this.rubyName;
    }
    
    protected boolean shouldUseBoxedArgs(final StaticScope scope) {
        return scope.getRestArg() >= 0 || scope.getRestArg() == -2 || scope.getOptionalArgs() > 0 || scope.getRequiredArgs() > 3;
    }
    
    protected int getActualArgsCount(final StaticScope scope) {
        if (this.shouldUseBoxedArgs(scope)) {
            return 1;
        }
        return scope.getRequiredArgs();
    }
    
    protected abstract String getSignature();
    
    protected abstract void createVariableCompiler();
    
    public abstract void beginMethod(final CompilerCallback p0, final StaticScope p1);
    
    public abstract void endBody();
    
    public BodyCompiler chainToMethod(final String methodName) {
        final BodyCompiler compiler = this.outline(methodName);
        this.endBody();
        return compiler;
    }
    
    public void beginChainedMethod() {
        this.method.start();
        this.method.aload(1);
        this.method.invokevirtual(CodegenUtils.p(ThreadContext.class), "getCurrentScope", CodegenUtils.sig(DynamicScope.class, new Class[0]));
        this.method.astore(this.getDynamicScopeIndex());
        if (this.scope.getNumberOfVariables() > 4) {
            this.method.aload(this.getDynamicScopeIndex());
            this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "getValues", CodegenUtils.sig(IRubyObject[].class, new Class[0]));
            this.method.astore(this.getVarsArrayIndex());
        }
        this.method.label(this.scopeStart);
    }
    
    public abstract BaseBodyCompiler outline(final String p0);
    
    public StandardASMCompiler getScriptCompiler() {
        return this.script;
    }
    
    public void lineNumber(final ISourcePosition position) {
        final int thisLine = position.getStartLine();
        if (thisLine != this.lastLine) {
            this.lastLine = thisLine;
            final Label line = new Label();
            this.method.label(line);
            this.method.visitLineNumber(thisLine + 1, line);
        }
    }
    
    public void loadThreadContext() {
        this.method.aload(1);
    }
    
    public void loadSelf() {
        this.method.aload(2);
    }
    
    protected int getClosureIndex() {
        return 3 + this.argParamCount + 0;
    }
    
    protected int getPreviousExceptionIndex() {
        return 3 + this.argParamCount + 4;
    }
    
    protected int getDynamicScopeIndex() {
        return 3 + this.argParamCount + 1;
    }
    
    protected int getVarsArrayIndex() {
        return 3 + this.argParamCount + 2;
    }
    
    protected int getFirstTempIndex() {
        return 3 + this.argParamCount + 5;
    }
    
    protected int getExceptionIndex() {
        return 3 + this.argParamCount + 3;
    }
    
    public void loadThis() {
        this.method.aload(0);
    }
    
    public void loadRuntime() {
        this.loadThreadContext();
        this.method.getfield(CodegenUtils.p(ThreadContext.class), "runtime", CodegenUtils.ci(Ruby.class));
    }
    
    public void loadBlock() {
        this.method.aload(this.getClosureIndex());
    }
    
    public void loadNil() {
        this.loadThreadContext();
        this.method.getfield(CodegenUtils.p(ThreadContext.class), "nil", CodegenUtils.ci(IRubyObject.class));
    }
    
    public void loadNull() {
        this.method.aconst_null();
    }
    
    public void loadObject() {
        this.loadRuntime();
        this.invokeRuby("getObject", CodegenUtils.sig(RubyClass.class, CodegenUtils.params(new Class[0])));
    }
    
    public void invokeUtilityMethod(final String methodName, final String signature) {
        this.method.invokestatic(CodegenUtils.p(RuntimeHelpers.class), methodName, signature);
    }
    
    public void invokeThreadContext(final String methodName, final String signature) {
        this.method.invokevirtual(StandardASMCompiler.THREADCONTEXT, methodName, signature);
    }
    
    public void invokeRuby(final String methodName, final String signature) {
        this.method.invokevirtual(StandardASMCompiler.RUBY, methodName, signature);
    }
    
    public void invokeIRubyObject(final String methodName, final String signature) {
        this.method.invokeinterface(StandardASMCompiler.IRUBYOBJECT, methodName, signature);
    }
    
    public void consumeCurrentValue() {
        this.method.pop();
    }
    
    public void duplicateCurrentValue() {
        this.method.dup();
    }
    
    public void swapValues() {
        this.method.swap();
    }
    
    public void reverseValues(final int count) {
        switch (count) {
            case 2: {
                this.method.swap();
                break;
            }
            case 3: {
                this.method.dup_x2();
                this.method.pop();
                this.method.swap();
                break;
            }
            case 4: {
                this.method.swap();
                this.method.dup2_x2();
                this.method.pop2();
                this.method.swap();
                break;
            }
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10: {
                final int[] tmpLocals = new int[count];
                for (int i = 0; i < count; ++i) {
                    tmpLocals[i] = this.getVariableCompiler().grabTempLocal();
                    this.getVariableCompiler().setTempLocal(tmpLocals[i]);
                }
                for (int i = 0; i < count; ++i) {
                    this.getVariableCompiler().getTempLocal(tmpLocals[i]);
                    this.getVariableCompiler().releaseTempLocal();
                }
                break;
            }
            default: {
                throw new NotCompilableException("can't reverse more than ten values on the stack");
            }
        }
    }
    
    public void retrieveSelf() {
        this.loadSelf();
    }
    
    public void retrieveSelfClass() {
        this.loadSelf();
        this.metaclass();
    }
    
    public VariableCompiler getVariableCompiler() {
        return this.variableCompiler;
    }
    
    public InvocationCompiler getInvocationCompiler() {
        return this.invocationCompiler;
    }
    
    public void assignConstantInCurrent(final String name) {
        this.loadThreadContext();
        this.method.ldc(name);
        this.invokeUtilityMethod("setConstantInCurrent", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(IRubyObject.class, ThreadContext.class, String.class)));
    }
    
    public void assignConstantInModule(final String name) {
        this.method.ldc(name);
        this.loadThreadContext();
        this.invokeUtilityMethod("setConstantInModule", CodegenUtils.sig(IRubyObject.class, IRubyObject.class, IRubyObject.class, String.class, ThreadContext.class));
    }
    
    public void assignConstantInObject(final String name) {
        this.loadObject();
        this.method.swap();
        this.assignConstantInModule(name);
    }
    
    public void retrieveConstant(final String name) {
        this.script.getCacheCompiler().cacheConstant(this, name);
    }
    
    public void retrieveConstantFromModule(final String name) {
        this.invokeUtilityMethod("checkIsModule", CodegenUtils.sig(RubyModule.class, IRubyObject.class));
        this.script.getCacheCompiler().cacheConstantFrom(this, name);
    }
    
    public void retrieveConstantFromObject(final String name) {
        this.loadObject();
        this.script.getCacheCompiler().cacheConstantFrom(this, name);
    }
    
    public void retrieveClassVariable(final String name) {
        this.loadThreadContext();
        this.loadRuntime();
        this.loadSelf();
        this.method.ldc(name);
        this.invokeUtilityMethod("fastFetchClassVariable", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, Ruby.class, IRubyObject.class, String.class)));
    }
    
    public void assignClassVariable(final String name) {
        this.loadThreadContext();
        this.method.swap();
        this.loadRuntime();
        this.method.swap();
        this.loadSelf();
        this.method.swap();
        this.method.ldc(name);
        this.method.swap();
        this.invokeUtilityMethod("fastSetClassVariable", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, Ruby.class, IRubyObject.class, String.class, IRubyObject.class)));
    }
    
    public void assignClassVariable(final String name, final CompilerCallback value) {
        this.loadThreadContext();
        this.loadRuntime();
        this.loadSelf();
        this.method.ldc(name);
        value.call(this);
        this.invokeUtilityMethod("fastSetClassVariable", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, Ruby.class, IRubyObject.class, String.class, IRubyObject.class)));
    }
    
    public void declareClassVariable(final String name) {
        this.loadThreadContext();
        this.method.swap();
        this.loadRuntime();
        this.method.swap();
        this.loadSelf();
        this.method.swap();
        this.method.ldc(name);
        this.method.swap();
        this.invokeUtilityMethod("fastDeclareClassVariable", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, Ruby.class, IRubyObject.class, String.class, IRubyObject.class)));
    }
    
    public void declareClassVariable(final String name, final CompilerCallback value) {
        this.loadThreadContext();
        this.loadRuntime();
        this.loadSelf();
        this.method.ldc(name);
        value.call(this);
        this.invokeUtilityMethod("fastDeclareClassVariable", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, Ruby.class, IRubyObject.class, String.class, IRubyObject.class)));
    }
    
    public void createNewFloat(final double value) {
        this.script.getCacheCompiler().cacheFloat(this, value);
    }
    
    public void createNewFixnum(final long value) {
        this.script.getCacheCompiler().cacheFixnum(this, value);
    }
    
    public void createNewBignum(final BigInteger value) {
        this.loadRuntime();
        this.script.getCacheCompiler().cacheBigInteger(this, value);
        this.method.invokestatic(CodegenUtils.p(RubyBignum.class), "newBignum", CodegenUtils.sig(RubyBignum.class, CodegenUtils.params(Ruby.class, BigInteger.class)));
    }
    
    public void createNewString(final ArrayCallback callback, final int count) {
        this.loadRuntime();
        this.method.ldc(20);
        this.method.invokestatic(CodegenUtils.p(RubyString.class), "newStringLight", CodegenUtils.sig(RubyString.class, Ruby.class, Integer.TYPE));
        for (int i = 0; i < count; ++i) {
            callback.nextValue(this, null, i);
            this.method.invokevirtual(CodegenUtils.p(RubyString.class), "append", CodegenUtils.sig(RubyString.class, CodegenUtils.params(IRubyObject.class)));
        }
    }
    
    public void createNewSymbol(final ArrayCallback callback, final int count) {
        this.loadRuntime();
        this.createNewString(callback, count);
        this.toJavaString();
        this.invokeRuby("newSymbol", CodegenUtils.sig(RubySymbol.class, CodegenUtils.params(String.class)));
    }
    
    public void createNewString(final ByteList value, final int codeRange) {
        this.script.getCacheCompiler().cacheString(this, value, codeRange);
    }
    
    public void createNewSymbol(final String name) {
        this.script.getCacheCompiler().cacheSymbol(this, name);
    }
    
    public void createNewArray(final boolean lightweight) {
        this.loadRuntime();
        this.method.swap();
        if (lightweight) {
            this.method.invokestatic(CodegenUtils.p(RubyArray.class), "newArrayNoCopyLight", CodegenUtils.sig(RubyArray.class, CodegenUtils.params(Ruby.class, IRubyObject[].class)));
        }
        else {
            this.method.invokestatic(CodegenUtils.p(RubyArray.class), "newArrayNoCopy", CodegenUtils.sig(RubyArray.class, CodegenUtils.params(Ruby.class, IRubyObject[].class)));
        }
    }
    
    public void createNewArray(final Object[] sourceArray, final ArrayCallback callback, final boolean lightweight) {
        this.loadRuntime();
        this.buildRubyArray(sourceArray, callback, lightweight);
    }
    
    public void createNewLiteralArray(final Object[] sourceArray, final ArrayCallback callback, final boolean lightweight) {
        this.buildRubyLiteralArray(sourceArray, callback, lightweight);
    }
    
    public void createEmptyArray() {
        this.loadRuntime();
        this.invokeRuby("newArray", CodegenUtils.sig(RubyArray.class, new Class[0]));
    }
    
    public void createObjectArray(final Object[] sourceArray, final ArrayCallback callback) {
        this.buildObjectArray(StandardASMCompiler.IRUBYOBJECT, sourceArray, callback);
    }
    
    public void createObjectArray(final int elementCount) {
        if (elementCount < 6) {
            final Class[] params = new Class[elementCount];
            Arrays.fill(params, IRubyObject.class);
            this.invokeUtilityMethod("constructObjectArray", CodegenUtils.sig(IRubyObject[].class, params));
            return;
        }
        throw new NotCompilableException("Don't use createObjectArray(int) for more than 5 elements");
    }
    
    private void buildObjectArray(final String type, final Object[] sourceArray, final ArrayCallback callback) {
        if (sourceArray.length == 0) {
            this.method.getstatic(CodegenUtils.p(IRubyObject.class), "NULL_ARRAY", CodegenUtils.ci(IRubyObject[].class));
        }
        else if (sourceArray.length <= 5) {
            for (int i = 0; i < sourceArray.length; ++i) {
                callback.nextValue(this, sourceArray, i);
            }
            this.invokeUtilityMethod("constructObjectArray", CodegenUtils.sig(IRubyObject[].class, CodegenUtils.params(IRubyObject.class, sourceArray.length)));
        }
        else {
            this.method.pushInt(sourceArray.length);
            this.method.anewarray(type);
            for (int i = 0; i < sourceArray.length; ++i) {
                this.method.dup();
                this.method.pushInt(i);
                callback.nextValue(this, sourceArray, i);
                this.method.arraystore();
            }
        }
    }
    
    private void buildRubyArray(final Object[] sourceArray, final ArrayCallback callback, final boolean light) {
        if (sourceArray.length == 0) {
            this.method.invokestatic(CodegenUtils.p(RubyArray.class), "newEmptyArray", CodegenUtils.sig(RubyArray.class, Ruby.class));
        }
        else if (sourceArray.length <= 5) {
            for (int i = 0; i < sourceArray.length; ++i) {
                callback.nextValue(this, sourceArray, i);
            }
            this.invokeUtilityMethod("constructRubyArray", CodegenUtils.sig(RubyArray.class, CodegenUtils.params(Ruby.class, IRubyObject.class, sourceArray.length)));
        }
        else {
            this.method.pushInt(sourceArray.length);
            this.method.anewarray(CodegenUtils.p(IRubyObject.class));
            for (int i = 0; i < sourceArray.length; ++i) {
                this.method.dup();
                this.method.pushInt(i);
                callback.nextValue(this, sourceArray, i);
                this.method.arraystore();
            }
            if (light) {
                this.method.invokestatic(CodegenUtils.p(RubyArray.class), "newArrayNoCopyLight", CodegenUtils.sig(RubyArray.class, Ruby.class, IRubyObject[].class));
            }
            else {
                this.method.invokestatic(CodegenUtils.p(RubyArray.class), "newArrayNoCopy", CodegenUtils.sig(RubyArray.class, Ruby.class, IRubyObject[].class));
            }
        }
    }
    
    private void buildRubyLiteralArray(final Object[] sourceArray, final ArrayCallback callback, final boolean light) {
        if (sourceArray.length < 100) {
            this.loadRuntime();
            this.buildRubyArray(sourceArray, callback, light);
        }
        else {
            final SkinnyMethodAdapter oldMethod = this.method;
            final String newMethodName = "array_builder_" + this.script.getAndIncrementMethodIndex() + "";
            (this.method = new SkinnyMethodAdapter(this.script.getClassVisitor(), 4106, newMethodName, CodegenUtils.sig(IRubyObject[].class, "L" + this.script.getClassname() + ";", ThreadContext.class, IRubyObject[].class), null, null)).start();
            for (int i = 0; i < sourceArray.length; ++i) {
                if ((i + 1) % 100 == 0) {
                    final String nextName = "array_builder_" + this.script.getAndIncrementMethodIndex() + "";
                    this.method.aloadMany(0, 1, 2);
                    this.method.invokestatic(this.script.getClassname(), nextName, CodegenUtils.sig(IRubyObject[].class, "L" + this.script.getClassname() + ";", ThreadContext.class, IRubyObject[].class));
                    this.method.areturn();
                    this.method.end();
                    (this.method = new SkinnyMethodAdapter(this.script.getClassVisitor(), 4106, nextName, CodegenUtils.sig(IRubyObject[].class, "L" + this.script.getClassname() + ";", ThreadContext.class, IRubyObject[].class), null, null)).start();
                }
                this.method.aload(2);
                this.method.pushInt(i);
                callback.nextValue(this, sourceArray, i);
                this.method.arraystore();
            }
            this.method.aload(2);
            this.method.areturn();
            this.method.end();
            this.method = oldMethod;
            this.loadRuntime();
            this.method.aload(0);
            this.method.aload(1);
            this.method.pushInt(sourceArray.length);
            this.method.anewarray(CodegenUtils.p(IRubyObject.class));
            this.method.invokestatic(this.script.getClassname(), newMethodName, CodegenUtils.sig(IRubyObject[].class, "L" + this.script.getClassname() + ";", ThreadContext.class, IRubyObject[].class));
            if (light) {
                this.method.invokestatic(CodegenUtils.p(RubyArray.class), "newArrayNoCopyLight", CodegenUtils.sig(RubyArray.class, Ruby.class, IRubyObject[].class));
            }
            else {
                this.method.invokestatic(CodegenUtils.p(RubyArray.class), "newArrayNoCopy", CodegenUtils.sig(RubyArray.class, Ruby.class, IRubyObject[].class));
            }
        }
    }
    
    public void createEmptyHash() {
        this.loadRuntime();
        this.method.invokestatic(CodegenUtils.p(RubyHash.class), "newHash", CodegenUtils.sig(RubyHash.class, CodegenUtils.params(Ruby.class)));
    }
    
    public void createNewHash(final Object elements, final ArrayCallback callback, final int keyCount) {
        this.createNewHashCommon(elements, callback, keyCount, "constructHash", "fastASetCheckString");
    }
    
    public void createNewLiteralHash(final Object elements, final ArrayCallback callback, final int keyCount) {
        this.createNewLiteralHashCommon(elements, callback, keyCount, "constructHash", "fastASetCheckString");
    }
    
    public void createNewHash19(final Object elements, final ArrayCallback callback, final int keyCount) {
        this.createNewHashCommon(elements, callback, keyCount, "constructHash19", "fastASetCheckString19");
    }
    
    private void createNewHashCommon(final Object elements, final ArrayCallback callback, final int keyCount, final String constructorName, final String methodName) {
        this.loadRuntime();
        int i;
        for (i = 0; i < keyCount && i < 3; ++i) {
            callback.nextValue(this, elements, i);
        }
        this.invokeUtilityMethod(constructorName, CodegenUtils.sig(RubyHash.class, CodegenUtils.params(Ruby.class, IRubyObject.class, i * 2)));
        while (i < keyCount) {
            this.method.dup();
            this.loadRuntime();
            callback.nextValue(this, elements, i);
            this.method.invokevirtual(CodegenUtils.p(RubyHash.class), methodName, CodegenUtils.sig(Void.TYPE, CodegenUtils.params(Ruby.class, IRubyObject.class, IRubyObject.class)));
            ++i;
        }
    }
    
    private void createNewLiteralHashCommon(final Object elements, final ArrayCallback callback, final int keyCount, final String constructorName, final String methodName) {
        if (keyCount < 50) {
            this.createNewHashCommon(elements, callback, keyCount, constructorName, methodName);
        }
        else {
            final SkinnyMethodAdapter oldMethod = this.method;
            final String builderMethod = "hash_builder_" + this.script.getAndIncrementMethodIndex() + "";
            (this.method = new SkinnyMethodAdapter(this.script.getClassVisitor(), 4106, builderMethod, CodegenUtils.sig(RubyHash.class, "L" + this.script.getClassname() + ";", ThreadContext.class, RubyHash.class), null, null)).start();
            for (int i = 0; i < keyCount; ++i) {
                if ((i + 1) % 100 == 0) {
                    final String nextName = "hash_builder_" + this.script.getAndIncrementMethodIndex() + "";
                    this.method.aloadMany(0, 1, 2);
                    this.method.invokestatic(this.script.getClassname(), nextName, CodegenUtils.sig(RubyHash.class, "L" + this.script.getClassname() + ";", ThreadContext.class, RubyHash.class));
                    this.method.areturn();
                    this.method.end();
                    (this.method = new SkinnyMethodAdapter(this.script.getClassVisitor(), 4106, nextName, CodegenUtils.sig(RubyHash.class, "L" + this.script.getClassname() + ";", ThreadContext.class, RubyHash.class), null, null)).start();
                }
                this.method.aload(2);
                this.loadRuntime();
                callback.nextValue(this, elements, i);
                this.method.invokevirtual(CodegenUtils.p(RubyHash.class), methodName, CodegenUtils.sig(Void.TYPE, CodegenUtils.params(Ruby.class, IRubyObject.class, IRubyObject.class)));
            }
            this.method.aload(2);
            this.method.areturn();
            this.method.end();
            (this.method = oldMethod).aload(0);
            this.method.aload(1);
            this.loadRuntime();
            this.method.invokestatic(CodegenUtils.p(RubyHash.class), "newHash", CodegenUtils.sig(RubyHash.class, Ruby.class));
            this.method.invokestatic(this.script.getClassname(), builderMethod, CodegenUtils.sig(RubyHash.class, "L" + this.script.getClassname() + ";", ThreadContext.class, RubyHash.class));
        }
    }
    
    public void createNewRange(final CompilerCallback beginEndCallback, final boolean isExclusive) {
        this.loadRuntime();
        this.loadThreadContext();
        beginEndCallback.call(this);
        if (isExclusive) {
            this.method.invokestatic(CodegenUtils.p(RubyRange.class), "newExclusiveRange", CodegenUtils.sig(RubyRange.class, CodegenUtils.params(Ruby.class, ThreadContext.class, IRubyObject.class, IRubyObject.class)));
        }
        else {
            this.method.invokestatic(CodegenUtils.p(RubyRange.class), "newInclusiveRange", CodegenUtils.sig(RubyRange.class, CodegenUtils.params(Ruby.class, ThreadContext.class, IRubyObject.class, IRubyObject.class)));
        }
    }
    
    public void createNewLambda(final CompilerCallback closure) {
        this.loadThreadContext();
        closure.call(this);
        this.loadSelf();
        this.invokeUtilityMethod("newLiteralLambda", CodegenUtils.sig(RubyProc.class, ThreadContext.class, Block.class, IRubyObject.class));
    }
    
    private void isTrue() {
        this.invokeIRubyObject("isTrue", CodegenUtils.sig(Boolean.TYPE, new Class[0]));
    }
    
    public void performBooleanBranch(final BranchCallback trueBranch, final BranchCallback falseBranch) {
        final Label afterJmp = new Label();
        final Label falseJmp = new Label();
        this.isTrue();
        this.method.ifeq(falseJmp);
        trueBranch.branch(this);
        this.method.go_to(afterJmp);
        this.method.label(falseJmp);
        falseBranch.branch(this);
        this.method.label(afterJmp);
    }
    
    public void performLogicalAnd(final BranchCallback longBranch) {
        final Label falseJmp = new Label();
        this.method.dup();
        this.isTrue();
        this.method.ifeq(falseJmp);
        this.method.pop();
        longBranch.branch(this);
        this.method.label(falseJmp);
    }
    
    public void performLogicalOr(final BranchCallback longBranch) {
        final Label falseJmp = new Label();
        this.method.dup();
        this.isTrue();
        this.method.ifne(falseJmp);
        this.method.pop();
        longBranch.branch(this);
        this.method.label(falseJmp);
    }
    
    public void performBooleanLoopSafe(final BranchCallback condition, final BranchCallback body, final boolean checkFirst) {
        final String mname = this.getNewRescueName();
        final BaseBodyCompiler nested = this.outline(mname);
        nested.performBooleanLoopSafeInner(condition, body, checkFirst);
    }
    
    private void performBooleanLoopSafeInner(final BranchCallback condition, final BranchCallback body, final boolean checkFirst) {
        this.performBooleanLoop(condition, body, checkFirst);
        this.endBody();
    }
    
    public void performBooleanLoop(final BranchCallback condition, final BranchCallback body, final boolean checkFirst) {
        final Label tryBegin = new Label();
        final Label tryEnd = new Label();
        final Label catchNext = new Label();
        final Label catchBreak = new Label();
        final Label endOfBody = new Label();
        final Label conditionCheck = new Label();
        final Label topOfBody = new Label();
        final Label done = new Label();
        final Label normalLoopEnd = new Label();
        this.method.trycatch(tryBegin, tryEnd, catchNext, CodegenUtils.p(JumpException.NextJump.class));
        this.method.trycatch(tryBegin, tryEnd, catchBreak, CodegenUtils.p(JumpException.BreakJump.class));
        this.method.label(tryBegin);
        final Label[] oldLoopLabels = this.currentLoopLabels;
        this.currentLoopLabels = new Label[] { endOfBody, topOfBody, done };
        if (checkFirst) {
            this.method.go_to(conditionCheck);
        }
        this.method.label(topOfBody);
        final Runnable redoBody = new Runnable() {
            public void run() {
                final Runnable raiseBody = new Runnable() {
                    public void run() {
                        body.branch(BaseBodyCompiler.this);
                    }
                };
                final Runnable raiseCatch = new Runnable() {
                    public void run() {
                        BaseBodyCompiler.this.loadThreadContext();
                        BaseBodyCompiler.this.invokeUtilityMethod("unwrapRedoNextBreakOrJustLocalJump", CodegenUtils.sig(Throwable.class, RaiseException.class, ThreadContext.class));
                        BaseBodyCompiler.this.method.athrow();
                    }
                };
                BaseBodyCompiler.this.method.trycatch(CodegenUtils.p(RaiseException.class), raiseBody, raiseCatch);
            }
        };
        final Runnable redoCatch = new Runnable() {
            public void run() {
                BaseBodyCompiler.this.method.pop();
                BaseBodyCompiler.this.method.go_to(topOfBody);
            }
        };
        this.method.trycatch(CodegenUtils.p(JumpException.RedoJump.class), redoBody, redoCatch);
        this.method.label(endOfBody);
        this.method.pop();
        this.method.label(conditionCheck);
        condition.branch(this);
        this.isTrue();
        this.method.ifne(topOfBody);
        this.currentLoopLabels = oldLoopLabels;
        this.method.label(tryEnd);
        this.method.go_to(normalLoopEnd);
        this.method.label(catchNext);
        this.method.pop();
        this.method.go_to(conditionCheck);
        this.method.label(catchBreak);
        this.loadThreadContext();
        this.invokeUtilityMethod("breakJumpInWhile", CodegenUtils.sig(IRubyObject.class, JumpException.BreakJump.class, ThreadContext.class));
        this.method.go_to(done);
        this.method.label(normalLoopEnd);
        this.loadNil();
        this.method.label(done);
    }
    
    public void performBooleanLoopLight(final BranchCallback condition, final BranchCallback body, final boolean checkFirst) {
        final Label endOfBody = new Label();
        final Label conditionCheck = new Label();
        final Label topOfBody = new Label();
        final Label done = new Label();
        final Label[] oldLoopLabels = this.currentLoopLabels;
        this.currentLoopLabels = new Label[] { endOfBody, topOfBody, done };
        if (checkFirst) {
            this.method.go_to(conditionCheck);
        }
        this.method.label(topOfBody);
        body.branch(this);
        this.method.label(endOfBody);
        this.method.pop();
        this.method.label(conditionCheck);
        condition.branch(this);
        this.isTrue();
        this.method.ifne(topOfBody);
        this.currentLoopLabels = oldLoopLabels;
        this.loadNil();
        this.method.label(done);
    }
    
    public void createNewClosure(final String file, final int line, final StaticScope scope, final int arity, final CompilerCallback body, final CompilerCallback args, final boolean hasMultipleArgsHead, final NodeType argsNodeId, final ASTInspector inspector) {
        String blockInMethod = JavaNameMangler.mangleMethodName(this.rubyName);
        if (this.rubyName == null || this.rubyName.length() == 0) {
            blockInMethod = "__block__";
        }
        final String closureMethodName = "block_" + this.script.getAndIncrementInnerIndex() + "$RUBY$" + blockInMethod;
        final ChildScopedBodyCompiler closureCompiler = new ChildScopedBodyCompiler(this.script, closureMethodName, this.rubyName, inspector, scope);
        closureCompiler.beginMethod(args, scope);
        body.call(closureCompiler);
        closureCompiler.endBody();
        this.loadThreadContext();
        this.loadSelf();
        this.script.getCacheCompiler().cacheClosure(this, closureMethodName, arity, scope, file, line, hasMultipleArgsHead, argsNodeId, inspector);
        this.script.addBlockCallbackDescriptor(closureMethodName, file, line);
        this.invokeUtilityMethod("createBlock", CodegenUtils.sig(Block.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, BlockBody.class)));
    }
    
    public void createNewClosure19(final String file, final int line, final StaticScope scope, final int arity, final CompilerCallback body, final CompilerCallback args, final boolean hasMultipleArgsHead, final NodeType argsNodeId, final String parameterList, final ASTInspector inspector) {
        String blockInMethod = JavaNameMangler.mangleMethodName(this.rubyName);
        if (this.rubyName == null || this.rubyName.length() == 0) {
            blockInMethod = "__block__";
        }
        final String closureMethodName = "block_" + this.script.getAndIncrementInnerIndex() + "$RUBY$" + blockInMethod;
        final ChildScopedBodyCompiler19 closureCompiler = new ChildScopedBodyCompiler19(this.script, closureMethodName, this.rubyName, inspector, scope);
        closureCompiler.beginMethod(args, scope);
        body.call(closureCompiler);
        closureCompiler.endBody();
        this.loadThreadContext();
        this.loadSelf();
        this.script.getCacheCompiler().cacheClosure19(this, closureMethodName, arity, scope, file, line, hasMultipleArgsHead, argsNodeId, parameterList, inspector);
        this.script.addBlockCallback19Descriptor(closureMethodName, file, line);
        this.invokeUtilityMethod("createBlock19", CodegenUtils.sig(Block.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, BlockBody.class)));
    }
    
    public void runBeginBlock(final StaticScope scope, final CompilerCallback body) {
        final String closureMethodName = "block_" + this.script.getAndIncrementInnerIndex() + "$RUBY$__begin__";
        final ChildScopedBodyCompiler closureCompiler = new ChildScopedBodyCompiler(this.script, closureMethodName, this.rubyName, null, scope);
        closureCompiler.beginMethod(null, scope);
        body.call(closureCompiler);
        closureCompiler.endBody();
        this.loadThreadContext();
        this.loadSelf();
        final String scopeNames = RuntimeHelpers.encodeScope(scope);
        this.method.ldc(scopeNames);
        this.script.getCacheCompiler().cacheSpecialClosure(this, closureMethodName);
        this.invokeUtilityMethod("runBeginBlock", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, String.class, CompiledBlockCallback.class)));
    }
    
    public void createNewForLoop(final int arity, final CompilerCallback body, final CompilerCallback args, final boolean hasMultipleArgsHead, final NodeType argsNodeId, final ASTInspector inspector) {
        final String closureMethodName = "block_" + this.script.getAndIncrementInnerIndex() + "$RUBY$__for__";
        final ChildScopedBodyCompiler closureCompiler = new ChildScopedBodyCompiler(this.script, closureMethodName, this.rubyName, inspector, this.scope);
        closureCompiler.beginMethod(args, null);
        body.call(closureCompiler);
        closureCompiler.endBody();
        this.loadThreadContext();
        this.loadSelf();
        this.method.pushInt(arity);
        this.script.getCacheCompiler().cacheSpecialClosure(this, closureMethodName);
        this.method.ldc(hasMultipleArgsHead);
        this.method.ldc(BlockBody.asArgumentType(argsNodeId));
        this.invokeUtilityMethod("createSharedScopeBlock", CodegenUtils.sig(Block.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, Integer.TYPE, CompiledBlockCallback.class, Boolean.TYPE, Integer.TYPE)));
    }
    
    public void createNewEndBlock(final CompilerCallback body) {
        final String closureMethodName = "block_" + this.script.getAndIncrementInnerIndex() + "$RUBY$__end__";
        final ChildScopedBodyCompiler closureCompiler = new ChildScopedBodyCompiler(this.script, closureMethodName, this.rubyName, null, this.scope);
        closureCompiler.beginMethod(null, null);
        body.call(closureCompiler);
        closureCompiler.endBody();
        this.loadThreadContext();
        this.loadSelf();
        this.method.iconst_0();
        this.script.getCacheCompiler().cacheSpecialClosure(this, closureMethodName);
        this.method.iconst_0();
        this.method.iconst_0();
        this.invokeUtilityMethod("createSharedScopeBlock", CodegenUtils.sig(Block.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, Integer.TYPE, CompiledBlockCallback.class, Boolean.TYPE, Integer.TYPE)));
        this.loadRuntime();
        this.invokeUtilityMethod("registerEndBlock", CodegenUtils.sig(Void.TYPE, Block.class, Ruby.class));
        this.loadNil();
    }
    
    public void getCompiledClass() {
        this.method.ldc(Type.getType(this.script.getClassname()));
    }
    
    public void println() {
        this.method.dup();
        this.method.getstatic(CodegenUtils.p(System.class), "out", CodegenUtils.ci(PrintStream.class));
        this.method.swap();
        this.method.invokevirtual(CodegenUtils.p(PrintStream.class), "println", CodegenUtils.sig(Void.TYPE, CodegenUtils.params(Object.class)));
    }
    
    public void defineAlias(final CompilerCallback args) {
        this.loadThreadContext();
        this.loadSelf();
        args.call(this);
        this.invokeUtilityMethod("defineAlias", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, IRubyObject.class, Object.class, Object.class));
    }
    
    public void literal(final String value) {
        this.method.ldc(value);
    }
    
    public void loadFalse() {
        this.loadRuntime();
        this.invokeRuby("getFalse", CodegenUtils.sig(RubyBoolean.class, new Class[0]));
    }
    
    public void loadTrue() {
        this.loadRuntime();
        this.invokeRuby("getTrue", CodegenUtils.sig(RubyBoolean.class, new Class[0]));
    }
    
    public void loadCurrentModule() {
        this.loadThreadContext();
        this.invokeThreadContext("getCurrentScope", CodegenUtils.sig(DynamicScope.class, new Class[0]));
        this.method.invokevirtual(CodegenUtils.p(DynamicScope.class), "getStaticScope", CodegenUtils.sig(StaticScope.class, new Class[0]));
        this.method.invokevirtual(CodegenUtils.p(StaticScope.class), "getModule", CodegenUtils.sig(RubyModule.class, new Class[0]));
    }
    
    public void retrieveInstanceVariable(final String name) {
        this.script.getCacheCompiler().cachedGetVariable(this, name);
    }
    
    public void assignInstanceVariable(final String name) {
        final int tmp = this.getVariableCompiler().grabTempLocal();
        this.getVariableCompiler().setTempLocal(tmp);
        final CompilerCallback callback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                context.getVariableCompiler().getTempLocal(tmp);
            }
        };
        this.script.getCacheCompiler().cachedSetVariable(this, name, callback);
    }
    
    public void assignInstanceVariable(final String name, final CompilerCallback value) {
        this.script.getCacheCompiler().cachedSetVariable(this, name, value);
    }
    
    public void retrieveGlobalVariable(final String name) {
        this.loadRuntime();
        this.method.ldc(name);
        this.invokeUtilityMethod("getGlobalVariable", CodegenUtils.sig(IRubyObject.class, Ruby.class, String.class));
    }
    
    public void assignGlobalVariable(final String name) {
        this.loadRuntime();
        this.method.ldc(name);
        this.invokeUtilityMethod("setGlobalVariable", CodegenUtils.sig(IRubyObject.class, IRubyObject.class, Ruby.class, String.class));
    }
    
    public void assignGlobalVariable(final String name, final CompilerCallback value) {
        value.call(this);
        this.loadRuntime();
        this.method.ldc(name);
        this.invokeUtilityMethod("setGlobalVariable", CodegenUtils.sig(IRubyObject.class, IRubyObject.class, Ruby.class, String.class));
    }
    
    public void negateCurrentValue() {
        this.loadRuntime();
        this.invokeUtilityMethod("negate", CodegenUtils.sig(IRubyObject.class, IRubyObject.class, Ruby.class));
    }
    
    public void splatCurrentValue(final String methodName) {
        this.method.invokestatic(CodegenUtils.p(RuntimeHelpers.class), methodName, CodegenUtils.sig(RubyArray.class, CodegenUtils.params(IRubyObject.class)));
    }
    
    public void singlifySplattedValue() {
        this.method.invokestatic(CodegenUtils.p(RuntimeHelpers.class), "aValueSplat", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(IRubyObject.class)));
    }
    
    public void singlifySplattedValue19() {
        this.method.invokestatic(CodegenUtils.p(RuntimeHelpers.class), "aValueSplat19", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(IRubyObject.class)));
    }
    
    public void aryToAry() {
        this.method.invokestatic(CodegenUtils.p(RuntimeHelpers.class), "aryToAry", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(IRubyObject.class)));
    }
    
    public void ensureRubyArray() {
        this.invokeUtilityMethod("ensureRubyArray", CodegenUtils.sig(RubyArray.class, CodegenUtils.params(IRubyObject.class)));
    }
    
    public void ensureMultipleAssignableRubyArray(final boolean masgnHasHead) {
        this.loadRuntime();
        this.method.pushBoolean(masgnHasHead);
        this.invokeUtilityMethod("ensureMultipleAssignableRubyArray", CodegenUtils.sig(RubyArray.class, CodegenUtils.params(IRubyObject.class, Ruby.class, Boolean.TYPE)));
    }
    
    public void forEachInValueArray(int start, final int count, final Object source, final ArrayCallback callback, final CompilerCallback argsCallback) {
        if (start < count || argsCallback != null) {
            final int tempLocal = this.getVariableCompiler().grabTempLocal();
            this.getVariableCompiler().setTempLocal(tempLocal);
            while (start < count) {
                this.getVariableCompiler().getTempLocal(tempLocal);
                switch (start) {
                    case 0: {
                        this.invokeUtilityMethod("arrayEntryOrNilZero", CodegenUtils.sig(IRubyObject.class, RubyArray.class));
                        break;
                    }
                    case 1: {
                        this.invokeUtilityMethod("arrayEntryOrNilOne", CodegenUtils.sig(IRubyObject.class, RubyArray.class));
                        break;
                    }
                    case 2: {
                        this.invokeUtilityMethod("arrayEntryOrNilTwo", CodegenUtils.sig(IRubyObject.class, RubyArray.class));
                        break;
                    }
                    default: {
                        this.method.pushInt(start);
                        this.invokeUtilityMethod("arrayEntryOrNil", CodegenUtils.sig(IRubyObject.class, RubyArray.class, Integer.TYPE));
                        break;
                    }
                }
                callback.nextValue(this, source, start);
                ++start;
            }
            if (argsCallback != null) {
                this.getVariableCompiler().getTempLocal(tempLocal);
                this.loadRuntime();
                this.method.pushInt(start);
                this.invokeUtilityMethod("subarrayOrEmpty", CodegenUtils.sig(RubyArray.class, RubyArray.class, Ruby.class, Integer.TYPE));
                argsCallback.call(this);
            }
            this.getVariableCompiler().getTempLocal(tempLocal);
            this.getVariableCompiler().releaseTempLocal();
        }
    }
    
    public void forEachInValueArray(int start, final int preCount, final Object preSource, final int postCount, final Object postSource, final ArrayCallback callback, final CompilerCallback argsCallback) {
        if (start < preCount || argsCallback != null) {
            final int tempLocal = this.getVariableCompiler().grabTempLocal();
            this.getVariableCompiler().setTempLocal(tempLocal);
            while (start < preCount) {
                this.getVariableCompiler().getTempLocal(tempLocal);
                switch (start) {
                    case 0: {
                        this.invokeUtilityMethod("arrayEntryOrNilZero", CodegenUtils.sig(IRubyObject.class, RubyArray.class));
                        break;
                    }
                    case 1: {
                        this.invokeUtilityMethod("arrayEntryOrNilOne", CodegenUtils.sig(IRubyObject.class, RubyArray.class));
                        break;
                    }
                    case 2: {
                        this.invokeUtilityMethod("arrayEntryOrNilTwo", CodegenUtils.sig(IRubyObject.class, RubyArray.class));
                        break;
                    }
                    default: {
                        this.method.pushInt(start);
                        this.invokeUtilityMethod("arrayEntryOrNil", CodegenUtils.sig(IRubyObject.class, RubyArray.class, Integer.TYPE));
                        break;
                    }
                }
                callback.nextValue(this, preSource, start);
                ++start;
            }
            if (argsCallback != null) {
                this.getVariableCompiler().getTempLocal(tempLocal);
                this.loadRuntime();
                this.method.pushInt(start);
                this.method.pushInt(postCount);
                this.invokeUtilityMethod("subarrayOrEmpty", CodegenUtils.sig(RubyArray.class, RubyArray.class, Ruby.class, Integer.TYPE, Integer.TYPE));
                argsCallback.call(this);
            }
            for (int postStart = 0; postStart < postCount; ++postStart) {
                this.getVariableCompiler().getTempLocal(tempLocal);
                this.method.pushInt(preCount);
                this.method.pushInt(postCount);
                switch (postStart) {
                    case 0: {
                        this.invokeUtilityMethod("arrayPostOrNilZero", CodegenUtils.sig(IRubyObject.class, RubyArray.class, Integer.TYPE, Integer.TYPE));
                        break;
                    }
                    case 1: {
                        this.invokeUtilityMethod("arrayPostOrNilOne", CodegenUtils.sig(IRubyObject.class, RubyArray.class, Integer.TYPE, Integer.TYPE));
                        break;
                    }
                    case 2: {
                        this.invokeUtilityMethod("arrayPostOrNilTwo", CodegenUtils.sig(IRubyObject.class, RubyArray.class, Integer.TYPE, Integer.TYPE));
                        break;
                    }
                    default: {
                        this.method.pushInt(postStart);
                        this.invokeUtilityMethod("arrayPostOrNil", CodegenUtils.sig(IRubyObject.class, RubyArray.class, Integer.TYPE, Integer.TYPE, Integer.TYPE));
                        break;
                    }
                }
                callback.nextValue(this, postSource, postStart);
            }
            this.getVariableCompiler().getTempLocal(tempLocal);
            this.getVariableCompiler().releaseTempLocal();
        }
    }
    
    public void asString() {
        this.method.invokeinterface(CodegenUtils.p(IRubyObject.class), "asString", CodegenUtils.sig(RubyString.class, new Class[0]));
    }
    
    public void toJavaString() {
        this.method.invokevirtual(CodegenUtils.p(Object.class), "toString", CodegenUtils.sig(String.class, new Class[0]));
    }
    
    public void nthRef(final int match) {
        this.method.pushInt(match);
        this.backref();
        this.method.invokestatic(CodegenUtils.p(RubyRegexp.class), "nth_match", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(Integer.TYPE, IRubyObject.class)));
    }
    
    public void match() {
        this.loadThreadContext();
        this.method.invokevirtual(CodegenUtils.p(RubyRegexp.class), "op_match2", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class)));
    }
    
    public void match2(final CompilerCallback value) {
        this.loadThreadContext();
        value.call(this);
        this.method.invokevirtual(CodegenUtils.p(RubyRegexp.class), "op_match", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class)));
    }
    
    public void match2Capture(final CompilerCallback value, final int[] scopeOffsets) {
        this.loadThreadContext();
        value.call(this);
        this.method.ldc(RuntimeHelpers.encodeCaptureOffsets(scopeOffsets));
        this.invokeUtilityMethod("match2AndUpdateScope", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(IRubyObject.class, ThreadContext.class, IRubyObject.class, String.class)));
    }
    
    public void match3() {
        this.loadThreadContext();
        this.invokeUtilityMethod("match3", CodegenUtils.sig(IRubyObject.class, RubyRegexp.class, IRubyObject.class, ThreadContext.class));
    }
    
    public void createNewRegexp(final ByteList value, final int options) {
        this.script.getCacheCompiler().cacheRegexp(this, value, options);
    }
    
    public void createNewRegexp(final CompilerCallback createStringCallback, final int options) {
        final boolean onceOnly = (options & 0x80) != 0x0;
        if (onceOnly) {
            this.script.getCacheCompiler().cacheDRegexp(this, createStringCallback, options);
        }
        else {
            this.loadRuntime();
            createStringCallback.call(this);
            this.method.pushInt(options);
            this.method.invokestatic(CodegenUtils.p(RubyRegexp.class), "newDRegexp", CodegenUtils.sig(RubyRegexp.class, CodegenUtils.params(Ruby.class, RubyString.class, Integer.TYPE)));
        }
    }
    
    public void pollThreadEvents() {
        if (!RubyInstanceConfig.THREADLESS_COMPILE_ENABLED) {
            this.loadThreadContext();
            this.invokeThreadContext("pollThreadEvents", CodegenUtils.sig(Void.TYPE, new Class[0]));
        }
    }
    
    public void nullToNil() {
        final Label notNull = new Label();
        this.method.dup();
        this.method.ifnonnull(notNull);
        this.method.pop();
        this.loadNil();
        this.method.label(notNull);
    }
    
    public void isInstanceOf(final Class clazz, final BranchCallback trueBranch, final BranchCallback falseBranch) {
        this.method.instance_of(CodegenUtils.p(clazz));
        final Label falseJmp = new Label();
        final Label afterJmp = new Label();
        this.method.ifeq(falseJmp);
        trueBranch.branch(this);
        this.method.go_to(afterJmp);
        this.method.label(falseJmp);
        falseBranch.branch(this);
        this.method.label(afterJmp);
    }
    
    public void isCaptured(final int number, final BranchCallback trueBranch, final BranchCallback falseBranch) {
        this.backref();
        this.method.dup();
        this.isInstanceOf(RubyMatchData.class, new BranchCallback() {
            public void branch(final BodyCompiler context) {
                BaseBodyCompiler.this.method.visitTypeInsn(192, CodegenUtils.p(RubyMatchData.class));
                BaseBodyCompiler.this.method.pushInt(number);
                BaseBodyCompiler.this.method.invokevirtual(CodegenUtils.p(RubyMatchData.class), "group", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(Integer.TYPE)));
                BaseBodyCompiler.this.method.invokeinterface(CodegenUtils.p(IRubyObject.class), "isNil", CodegenUtils.sig(Boolean.TYPE, new Class[0]));
                final Label isNil = new Label();
                final Label after = new Label();
                BaseBodyCompiler.this.method.ifne(isNil);
                trueBranch.branch(context);
                BaseBodyCompiler.this.method.go_to(after);
                BaseBodyCompiler.this.method.label(isNil);
                falseBranch.branch(context);
                BaseBodyCompiler.this.method.label(after);
            }
        }, new BranchCallback() {
            public void branch(final BodyCompiler context) {
                BaseBodyCompiler.this.method.pop();
                falseBranch.branch(context);
            }
        });
    }
    
    public void backref() {
        this.loadRuntime();
        this.loadThreadContext();
        this.invokeUtilityMethod("getBackref", CodegenUtils.sig(IRubyObject.class, Ruby.class, ThreadContext.class));
    }
    
    public void backrefMethod(final String methodName) {
        this.backref();
        this.method.invokestatic(CodegenUtils.p(RubyRegexp.class), methodName, CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(IRubyObject.class)));
    }
    
    public void issueLoopBreak() {
        this.method.go_to(this.currentLoopLabels[2]);
    }
    
    public void issueLoopNext() {
        this.method.go_to(this.currentLoopLabels[0]);
    }
    
    public void issueLoopRedo() {
        this.method.go_to(this.currentLoopLabels[1]);
    }
    
    protected String getNewEnsureName() {
        return "ensure_" + this.script.getAndIncrementEnsureNumber() + "$RUBY$__ensure__";
    }
    
    public void protect(final BranchCallback regularCode, final BranchCallback protectedCode, final Class ret) {
        final String mname = this.getNewEnsureName();
        final SkinnyMethodAdapter mv = new SkinnyMethodAdapter(this.script.getClassVisitor(), 4105, mname, CodegenUtils.sig(ret, "L" + this.script.getClassname() + ";", ThreadContext.class, IRubyObject.class, Block.class), null, null);
        SkinnyMethodAdapter old_method = null;
        SkinnyMethodAdapter var_old_method = null;
        SkinnyMethodAdapter inv_old_method = null;
        final boolean oldInNestedMethod = this.inNestedMethod;
        this.inNestedMethod = true;
        final Label[] oldLoopLabels = this.currentLoopLabels;
        this.currentLoopLabels = null;
        final int oldArgCount = this.argParamCount;
        this.argParamCount = 0;
        try {
            old_method = this.method;
            var_old_method = this.getVariableCompiler().getMethodAdapter();
            inv_old_method = this.getInvocationCompiler().getMethodAdapter();
            this.method = mv;
            this.getVariableCompiler().setMethodAdapter(mv);
            this.getInvocationCompiler().setMethodAdapter(mv);
            mv.visitCode();
            mv.aload(1);
            mv.invokevirtual(CodegenUtils.p(ThreadContext.class), "getCurrentScope", CodegenUtils.sig(DynamicScope.class, new Class[0]));
            mv.dup();
            mv.astore(this.getDynamicScopeIndex());
            mv.invokevirtual(CodegenUtils.p(DynamicScope.class), "getValues", CodegenUtils.sig(IRubyObject[].class, new Class[0]));
            mv.astore(this.getVarsArrayIndex());
            final Label codeBegin = new Label();
            final Label codeEnd = new Label();
            final Label ensureBegin = new Label();
            final Label ensureEnd = new Label();
            this.method.label(codeBegin);
            regularCode.branch(this);
            this.method.label(codeEnd);
            protectedCode.branch(this);
            mv.areturn();
            this.method.label(ensureBegin);
            this.method.astore(this.getExceptionIndex());
            this.method.label(ensureEnd);
            protectedCode.branch(this);
            this.method.aload(this.getExceptionIndex());
            this.method.athrow();
            this.method.trycatch(codeBegin, codeEnd, ensureBegin, null);
            this.method.trycatch(ensureBegin, ensureEnd, ensureBegin, null);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        finally {
            this.method = old_method;
            this.getVariableCompiler().setMethodAdapter(var_old_method);
            this.getInvocationCompiler().setMethodAdapter(inv_old_method);
            this.inNestedMethod = oldInNestedMethod;
            this.currentLoopLabels = oldLoopLabels;
            this.argParamCount = oldArgCount;
        }
        this.method.aload(0);
        this.loadThreadContext();
        this.loadSelf();
        if (this instanceof ChildScopedBodyCompiler) {
            this.pushNull();
        }
        else {
            this.loadBlock();
        }
        this.method.invokestatic(this.script.getClassname(), mname, CodegenUtils.sig(ret, "L" + this.script.getClassname() + ";", ThreadContext.class, IRubyObject.class, Block.class));
    }
    
    public void performEnsure(final BranchCallback regularCode, final BranchCallback protectedCode) {
        final String mname = this.getNewEnsureName();
        final BaseBodyCompiler ensure = this.outline(mname);
        ensure.performEnsureInner(regularCode, protectedCode);
    }
    
    private void performEnsureInner(final BranchCallback regularCode, final BranchCallback protectedCode) {
        final Label codeBegin = new Label();
        final Label codeEnd = new Label();
        final Label ensureBegin = new Label();
        final Label ensureEnd = new Label();
        this.method.label(codeBegin);
        regularCode.branch(this);
        this.method.label(codeEnd);
        protectedCode.branch(this);
        this.method.areturn();
        this.method.label(ensureBegin);
        this.method.astore(this.getExceptionIndex());
        this.method.label(ensureEnd);
        protectedCode.branch(this);
        this.method.aload(this.getExceptionIndex());
        this.method.athrow();
        this.method.trycatch(codeBegin, codeEnd, ensureBegin, null);
        this.method.trycatch(ensureBegin, ensureEnd, ensureBegin, null);
        this.loadNil();
        this.endBody();
    }
    
    protected String getNewRescueName() {
        return "rescue_" + this.script.getAndIncrementRescueNumber() + "$RUBY$SYNTHETIC" + JavaNameMangler.mangleStringForCleanJavaIdentifier(this.getRubyName());
    }
    
    public void storeExceptionInErrorInfo() {
        this.loadException();
        this.loadThreadContext();
        this.invokeUtilityMethod("storeExceptionInErrorInfo", CodegenUtils.sig(Void.TYPE, Throwable.class, ThreadContext.class));
    }
    
    public void clearErrorInfo() {
        this.loadThreadContext();
        this.invokeUtilityMethod("clearErrorInfo", CodegenUtils.sig(Void.TYPE, ThreadContext.class));
    }
    
    public void rescue(final BranchCallback regularCode, final Class exception, final BranchCallback catchCode, final Class ret) {
        final String mname = this.getNewRescueName();
        final SkinnyMethodAdapter mv = new SkinnyMethodAdapter(this.script.getClassVisitor(), 4105, mname, CodegenUtils.sig(ret, "L" + this.script.getClassname() + ";", ThreadContext.class, IRubyObject.class, Block.class), null, null);
        SkinnyMethodAdapter old_method = null;
        SkinnyMethodAdapter var_old_method = null;
        SkinnyMethodAdapter inv_old_method = null;
        final Label afterMethodBody = new Label();
        final Label catchRetry = new Label();
        final Label catchRaised = new Label();
        final Label catchJumps = new Label();
        final Label exitRescue = new Label();
        final boolean oldWithinProtection = this.inNestedMethod;
        this.inNestedMethod = true;
        final Label[] oldLoopLabels = this.currentLoopLabels;
        this.currentLoopLabels = null;
        final int oldArgCount = this.argParamCount;
        this.argParamCount = 0;
        try {
            old_method = this.method;
            var_old_method = this.getVariableCompiler().getMethodAdapter();
            inv_old_method = this.getInvocationCompiler().getMethodAdapter();
            this.method = mv;
            this.getVariableCompiler().setMethodAdapter(mv);
            this.getInvocationCompiler().setMethodAdapter(mv);
            mv.start();
            this.loadThreadContext();
            this.invokeThreadContext("getErrorInfo", CodegenUtils.sig(IRubyObject.class, new Class[0]));
            mv.astore(this.getPreviousExceptionIndex());
            mv.aload(1);
            mv.invokevirtual(CodegenUtils.p(ThreadContext.class), "getCurrentScope", CodegenUtils.sig(DynamicScope.class, new Class[0]));
            mv.astore(this.getDynamicScopeIndex());
            if (this.scope.getNumberOfVariables() > 4) {
                mv.aload(this.getDynamicScopeIndex());
                mv.invokevirtual(CodegenUtils.p(DynamicScope.class), "getValues", CodegenUtils.sig(IRubyObject[].class, new Class[0]));
                mv.astore(this.getVarsArrayIndex());
            }
            final Label beforeBody = new Label();
            final Label afterBody = new Label();
            final Label catchBlock = new Label();
            mv.trycatch(beforeBody, afterBody, catchBlock, CodegenUtils.p(exception));
            mv.label(beforeBody);
            regularCode.branch(this);
            mv.label(afterBody);
            mv.go_to(exitRescue);
            mv.label(catchBlock);
            mv.astore(this.getExceptionIndex());
            catchCode.branch(this);
            mv.label(afterMethodBody);
            mv.go_to(exitRescue);
            mv.trycatch(catchBlock, afterMethodBody, catchRetry, CodegenUtils.p(JumpException.RetryJump.class));
            mv.label(catchRetry);
            mv.pop();
            mv.go_to(beforeBody);
            mv.trycatch(beforeBody, afterMethodBody, catchRaised, CodegenUtils.p(RaiseException.class));
            mv.label(catchRaised);
            mv.athrow();
            mv.trycatch(beforeBody, afterMethodBody, catchJumps, CodegenUtils.p(JumpException.class));
            mv.label(catchJumps);
            this.loadThreadContext();
            this.method.aload(this.getPreviousExceptionIndex());
            this.invokeThreadContext("setErrorInfo", CodegenUtils.sig(IRubyObject.class, IRubyObject.class));
            this.method.pop();
            mv.athrow();
            mv.label(exitRescue);
            this.loadThreadContext();
            this.method.aload(this.getPreviousExceptionIndex());
            this.invokeThreadContext("setErrorInfo", CodegenUtils.sig(IRubyObject.class, IRubyObject.class));
            this.method.pop();
            mv.areturn();
            mv.end();
        }
        finally {
            this.inNestedMethod = oldWithinProtection;
            this.method = old_method;
            this.getVariableCompiler().setMethodAdapter(var_old_method);
            this.getInvocationCompiler().setMethodAdapter(inv_old_method);
            this.currentLoopLabels = oldLoopLabels;
            this.argParamCount = oldArgCount;
        }
        this.method.aload(0);
        this.loadThreadContext();
        this.loadSelf();
        if (this instanceof ChildScopedBodyCompiler) {
            this.pushNull();
        }
        else {
            this.loadBlock();
        }
        this.method.invokestatic(this.script.getClassname(), mname, CodegenUtils.sig(ret, "L" + this.script.getClassname() + ";", ThreadContext.class, IRubyObject.class, Block.class));
    }
    
    public void performRescue(final BranchCallback regularCode, final BranchCallback rubyCatchCode, final BranchCallback rubyElseCode, final boolean needsRetry) {
        final String mname = this.getNewRescueName();
        final BaseBodyCompiler rescueMethod = this.outline(mname);
        rescueMethod.performRescueLight(regularCode, rubyCatchCode, rubyElseCode, needsRetry);
        rescueMethod.endBody();
    }
    
    public void performRescueLight(final BranchCallback regularCode, final BranchCallback rubyCatchCode, final BranchCallback rubyElseCode, final boolean needsRetry) {
        final Label afterRubyCatchBody = new Label();
        final Label catchRetry = new Label();
        final Label catchJumps = new Label();
        final Label exitRescue = new Label();
        this.loadThreadContext();
        this.invokeThreadContext("getErrorInfo", CodegenUtils.sig(IRubyObject.class, new Class[0]));
        this.method.astore(this.getPreviousExceptionIndex());
        final Label beforeBody = new Label();
        final Label afterBody = new Label();
        final Label rubyCatchBlock = new Label();
        final Label flowCatchBlock = new Label();
        final Label elseLabel = new Label();
        this.method.visitTryCatchBlock(beforeBody, afterBody, flowCatchBlock, CodegenUtils.p(JumpException.FlowControlException.class));
        this.method.visitTryCatchBlock(beforeBody, afterBody, rubyCatchBlock, CodegenUtils.p(Throwable.class));
        this.method.visitLabel(beforeBody);
        regularCode.branch(this);
        this.method.label(afterBody);
        if (rubyElseCode != null) {
            this.method.go_to(elseLabel);
        }
        else {
            this.method.go_to(exitRescue);
        }
        this.method.label(flowCatchBlock);
        this.method.athrow();
        this.method.label(rubyCatchBlock);
        this.method.astore(this.getExceptionIndex());
        rubyCatchCode.branch(this);
        this.method.label(afterRubyCatchBody);
        this.method.go_to(exitRescue);
        if (needsRetry) {
            this.method.trycatch(rubyCatchBlock, afterRubyCatchBody, catchRetry, CodegenUtils.p(JumpException.RetryJump.class));
            this.method.label(catchRetry);
            this.method.pop();
            this.method.go_to(beforeBody);
        }
        this.method.trycatch(beforeBody, afterRubyCatchBody, catchJumps, CodegenUtils.p(JumpException.FlowControlException.class));
        this.method.label(catchJumps);
        this.loadThreadContext();
        this.method.aload(this.getPreviousExceptionIndex());
        this.invokeThreadContext("setErrorInfo", CodegenUtils.sig(IRubyObject.class, IRubyObject.class));
        this.method.pop();
        this.method.athrow();
        if (rubyElseCode != null) {
            this.method.label(elseLabel);
            rubyElseCode.branch(this);
        }
        this.method.label(exitRescue);
        this.loadThreadContext();
        this.method.aload(this.getPreviousExceptionIndex());
        this.invokeThreadContext("setErrorInfo", CodegenUtils.sig(IRubyObject.class, IRubyObject.class));
        this.method.pop();
    }
    
    public void wrapJavaException() {
        this.loadRuntime();
        this.loadException();
        this.wrapJavaObject();
    }
    
    public void wrapJavaObject() {
        this.method.invokestatic(CodegenUtils.p(JavaUtil.class), "convertJavaToUsableRubyObject", CodegenUtils.sig(IRubyObject.class, Ruby.class, Object.class));
    }
    
    public void inDefined() {
        this.method.aload(1);
        this.method.iconst_1();
        this.invokeThreadContext("setWithinDefined", CodegenUtils.sig(Void.TYPE, CodegenUtils.params(Boolean.TYPE)));
    }
    
    public void outDefined() {
        this.method.aload(1);
        this.method.iconst_0();
        this.invokeThreadContext("setWithinDefined", CodegenUtils.sig(Void.TYPE, CodegenUtils.params(Boolean.TYPE)));
    }
    
    public void stringOrNil() {
        this.loadThreadContext();
        this.invokeUtilityMethod("stringOrNil", CodegenUtils.sig(IRubyObject.class, ByteList.class, ThreadContext.class));
    }
    
    public void pushNull() {
        this.method.aconst_null();
    }
    
    public void pushString(final String str) {
        this.method.ldc(str);
    }
    
    public void pushByteList(final ByteList byteList) {
        this.script.getCacheCompiler().cacheByteList(this, byteList);
    }
    
    public void isMethodBound(final String name, final BranchCallback trueBranch, final BranchCallback falseBranch) {
        this.metaclass();
        this.method.ldc(name);
        this.method.iconst_0();
        this.method.invokevirtual(CodegenUtils.p(RubyClass.class), "isMethodBound", CodegenUtils.sig(Boolean.TYPE, CodegenUtils.params(String.class, Boolean.TYPE)));
        final Label falseLabel = new Label();
        final Label exitLabel = new Label();
        this.method.ifeq(falseLabel);
        trueBranch.branch(this);
        this.method.go_to(exitLabel);
        this.method.label(falseLabel);
        falseBranch.branch(this);
        this.method.label(exitLabel);
    }
    
    public void hasBlock(final BranchCallback trueBranch, final BranchCallback falseBranch) {
        this.loadBlock();
        this.method.invokevirtual(CodegenUtils.p(Block.class), "isGiven", CodegenUtils.sig(Boolean.TYPE, new Class[0]));
        final Label falseLabel = new Label();
        final Label exitLabel = new Label();
        this.method.ifeq(falseLabel);
        trueBranch.branch(this);
        this.method.go_to(exitLabel);
        this.method.label(falseLabel);
        falseBranch.branch(this);
        this.method.label(exitLabel);
    }
    
    public void isGlobalDefined(final String name, final BranchCallback trueBranch, final BranchCallback falseBranch) {
        this.loadRuntime();
        this.invokeRuby("getGlobalVariables", CodegenUtils.sig(GlobalVariables.class, new Class[0]));
        this.method.ldc(name);
        this.method.invokevirtual(CodegenUtils.p(GlobalVariables.class), "isDefined", CodegenUtils.sig(Boolean.TYPE, CodegenUtils.params(String.class)));
        final Label falseLabel = new Label();
        final Label exitLabel = new Label();
        this.method.ifeq(falseLabel);
        trueBranch.branch(this);
        this.method.go_to(exitLabel);
        this.method.label(falseLabel);
        falseBranch.branch(this);
        this.method.label(exitLabel);
    }
    
    public void isConstantDefined(final String name, final BranchCallback trueBranch, final BranchCallback falseBranch) {
        this.loadThreadContext();
        this.method.ldc(name);
        this.invokeThreadContext("getConstantDefined", CodegenUtils.sig(Boolean.TYPE, CodegenUtils.params(String.class)));
        final Label falseLabel = new Label();
        final Label exitLabel = new Label();
        this.method.ifeq(falseLabel);
        trueBranch.branch(this);
        this.method.go_to(exitLabel);
        this.method.label(falseLabel);
        falseBranch.branch(this);
        this.method.label(exitLabel);
    }
    
    public void isInstanceVariableDefined(final String name, final BranchCallback trueBranch, final BranchCallback falseBranch) {
        this.loadSelf();
        this.invokeIRubyObject("getInstanceVariables", CodegenUtils.sig(InstanceVariables.class, new Class[0]));
        this.method.ldc(name);
        this.method.invokeinterface(CodegenUtils.p(InstanceVariables.class), "fastHasInstanceVariable", CodegenUtils.sig(Boolean.TYPE, CodegenUtils.params(String.class)));
        final Label trueLabel = new Label();
        final Label exitLabel = new Label();
        this.method.ifne(trueLabel);
        falseBranch.branch(this);
        this.method.go_to(exitLabel);
        this.method.label(trueLabel);
        trueBranch.branch(this);
        this.method.label(exitLabel);
    }
    
    public void isClassVarDefined(final String name, final BranchCallback trueBranch, final BranchCallback falseBranch) {
        this.method.ldc(name);
        this.method.invokevirtual(CodegenUtils.p(RubyModule.class), "fastIsClassVarDefined", CodegenUtils.sig(Boolean.TYPE, CodegenUtils.params(String.class)));
        final Label trueLabel = new Label();
        final Label exitLabel = new Label();
        this.method.ifne(trueLabel);
        falseBranch.branch(this);
        this.method.go_to(exitLabel);
        this.method.label(trueLabel);
        trueBranch.branch(this);
        this.method.label(exitLabel);
    }
    
    public Object getNewEnding() {
        return new Label();
    }
    
    public void isNil(final BranchCallback trueBranch, final BranchCallback falseBranch) {
        this.method.invokeinterface(CodegenUtils.p(IRubyObject.class), "isNil", CodegenUtils.sig(Boolean.TYPE, new Class[0]));
        final Label falseLabel = new Label();
        final Label exitLabel = new Label();
        this.method.ifeq(falseLabel);
        trueBranch.branch(this);
        this.method.go_to(exitLabel);
        this.method.label(falseLabel);
        falseBranch.branch(this);
        this.method.label(exitLabel);
    }
    
    public void isNull(final BranchCallback trueBranch, final BranchCallback falseBranch) {
        final Label falseLabel = new Label();
        final Label exitLabel = new Label();
        this.method.ifnonnull(falseLabel);
        trueBranch.branch(this);
        this.method.go_to(exitLabel);
        this.method.label(falseLabel);
        falseBranch.branch(this);
        this.method.label(exitLabel);
    }
    
    public void ifNull(final Object gotoToken) {
        this.method.ifnull((Label)gotoToken);
    }
    
    public void ifNotNull(final Object gotoToken) {
        this.method.ifnonnull((Label)gotoToken);
    }
    
    public void setEnding(final Object endingToken) {
        this.method.label((Label)endingToken);
    }
    
    public void go(final Object gotoToken) {
        this.method.go_to((Label)gotoToken);
    }
    
    public void isConstantBranch(final BranchCallback setup, final String name) {
        final BranchCallback catchCode = new BranchCallback() {
            public void branch(final BodyCompiler context) {
                BaseBodyCompiler.this.loadThreadContext();
                BaseBodyCompiler.this.method.aload(BaseBodyCompiler.this.getPreviousExceptionIndex());
                BaseBodyCompiler.this.invokeThreadContext("setErrorInfo", CodegenUtils.sig(IRubyObject.class, IRubyObject.class));
                BaseBodyCompiler.this.method.pop();
                BaseBodyCompiler.this.pushNull();
            }
        };
        final BranchCallback regularCode = new BranchCallback() {
            public void branch(final BodyCompiler context) {
                setup.branch(BaseBodyCompiler.this);
                BaseBodyCompiler.this.method.ldc(name);
                BaseBodyCompiler.this.invokeUtilityMethod("getDefinedConstantOrBoundMethod", CodegenUtils.sig(ByteList.class, IRubyObject.class, String.class));
            }
        };
        final String mname = this.getNewRescueName();
        final SkinnyMethodAdapter mv = new SkinnyMethodAdapter(this.script.getClassVisitor(), 4105, mname, CodegenUtils.sig(ByteList.class, "L" + this.script.getClassname() + ";", ThreadContext.class, IRubyObject.class, Block.class), null, null);
        SkinnyMethodAdapter old_method = null;
        SkinnyMethodAdapter var_old_method = null;
        SkinnyMethodAdapter inv_old_method = null;
        final Label exitRescue = new Label();
        final boolean oldWithinProtection = this.inNestedMethod;
        this.inNestedMethod = true;
        final Label[] oldLoopLabels = this.currentLoopLabels;
        this.currentLoopLabels = null;
        final int oldArgCount = this.argParamCount;
        this.argParamCount = 0;
        try {
            old_method = this.method;
            var_old_method = this.getVariableCompiler().getMethodAdapter();
            inv_old_method = this.getInvocationCompiler().getMethodAdapter();
            this.method = mv;
            this.getVariableCompiler().setMethodAdapter(mv);
            this.getInvocationCompiler().setMethodAdapter(mv);
            mv.start();
            this.loadThreadContext();
            this.invokeThreadContext("getErrorInfo", CodegenUtils.sig(IRubyObject.class, new Class[0]));
            mv.astore(this.getPreviousExceptionIndex());
            mv.aload(1);
            mv.invokevirtual(CodegenUtils.p(ThreadContext.class), "getCurrentScope", CodegenUtils.sig(DynamicScope.class, new Class[0]));
            mv.astore(this.getDynamicScopeIndex());
            if (this.scope.getNumberOfVariables() > 4) {
                mv.aload(this.getDynamicScopeIndex());
                mv.invokevirtual(CodegenUtils.p(DynamicScope.class), "getValues", CodegenUtils.sig(IRubyObject[].class, new Class[0]));
                mv.astore(this.getVarsArrayIndex());
            }
            final Label beforeBody = new Label();
            final Label afterBody = new Label();
            final Label catchBlock = new Label();
            mv.trycatch(beforeBody, afterBody, catchBlock, CodegenUtils.p(JumpException.class));
            mv.label(beforeBody);
            regularCode.branch(this);
            mv.label(afterBody);
            mv.go_to(exitRescue);
            mv.label(catchBlock);
            mv.astore(this.getExceptionIndex());
            catchCode.branch(this);
            mv.label(exitRescue);
            mv.areturn();
            mv.end();
        }
        finally {
            this.inNestedMethod = oldWithinProtection;
            this.method = old_method;
            this.getVariableCompiler().setMethodAdapter(var_old_method);
            this.getInvocationCompiler().setMethodAdapter(inv_old_method);
            this.currentLoopLabels = oldLoopLabels;
            this.argParamCount = oldArgCount;
        }
        this.method.aload(0);
        this.loadThreadContext();
        this.loadSelf();
        if (this instanceof ChildScopedBodyCompiler) {
            this.pushNull();
        }
        else {
            this.loadBlock();
        }
        this.method.invokestatic(this.script.getClassname(), mname, CodegenUtils.sig(ByteList.class, "L" + this.script.getClassname() + ";", ThreadContext.class, IRubyObject.class, Block.class));
    }
    
    public void metaclass() {
        this.invokeIRubyObject("getMetaClass", CodegenUtils.sig(RubyClass.class, new Class[0]));
    }
    
    public void aprintln() {
        this.method.aprintln();
    }
    
    public void getVisibilityFor(final String name) {
        this.method.ldc(name);
        this.method.invokevirtual(CodegenUtils.p(RubyClass.class), "searchMethod", CodegenUtils.sig(DynamicMethod.class, CodegenUtils.params(String.class)));
        this.method.invokevirtual(CodegenUtils.p(DynamicMethod.class), "getVisibility", CodegenUtils.sig(Visibility.class, new Class[0]));
    }
    
    public void isPrivate(final Object gotoToken, int toConsume) {
        this.method.getstatic(CodegenUtils.p(Visibility.class), "PRIVATE", CodegenUtils.ci(Visibility.class));
        final Label temp = new Label();
        this.method.if_acmpne(temp);
        while (toConsume-- > 0) {
            this.method.pop();
        }
        this.method.go_to((Label)gotoToken);
        this.method.label(temp);
    }
    
    public void isNotProtected(final Object gotoToken, int toConsume) {
        this.method.getstatic(CodegenUtils.p(Visibility.class), "PROTECTED", CodegenUtils.ci(Visibility.class));
        final Label temp = new Label();
        this.method.if_acmpeq(temp);
        while (toConsume-- > 0) {
            this.method.pop();
        }
        this.method.go_to((Label)gotoToken);
        this.method.label(temp);
    }
    
    public void selfIsKindOf(final Object gotoToken) {
        this.method.invokevirtual(CodegenUtils.p(RubyClass.class), "getRealClass", CodegenUtils.sig(RubyClass.class, new Class[0]));
        this.loadSelf();
        this.method.invokevirtual(CodegenUtils.p(RubyModule.class), "isInstance", CodegenUtils.sig(Boolean.TYPE, CodegenUtils.params(IRubyObject.class)));
        this.method.ifne((Label)gotoToken);
    }
    
    public void notIsModuleAndClassVarDefined(final String name, final Object gotoToken) {
        this.method.dup();
        this.method.instance_of(CodegenUtils.p(RubyModule.class));
        final Label falsePopJmp = new Label();
        final Label successJmp = new Label();
        this.method.ifeq(falsePopJmp);
        this.method.visitTypeInsn(192, CodegenUtils.p(RubyModule.class));
        this.method.ldc(name);
        this.method.invokevirtual(CodegenUtils.p(RubyModule.class), "fastIsClassVarDefined", CodegenUtils.sig(Boolean.TYPE, CodegenUtils.params(String.class)));
        this.method.ifeq((Label)gotoToken);
        this.method.go_to(successJmp);
        this.method.label(falsePopJmp);
        this.method.pop();
        this.method.go_to((Label)gotoToken);
        this.method.label(successJmp);
    }
    
    public void ifSingleton(final Object gotoToken) {
        this.method.invokevirtual(CodegenUtils.p(RubyModule.class), "isSingleton", CodegenUtils.sig(Boolean.TYPE, new Class[0]));
        this.method.ifne((Label)gotoToken);
    }
    
    public void getInstanceVariable(final String name) {
        this.method.ldc(name);
        this.invokeIRubyObject("getInstanceVariables", CodegenUtils.sig(InstanceVariables.class, new Class[0]));
        this.method.invokeinterface(CodegenUtils.p(InstanceVariables.class), "fastGetInstanceVariable", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(String.class)));
    }
    
    public void getFrameName() {
        this.loadThreadContext();
        this.invokeThreadContext("getFrameName", CodegenUtils.sig(String.class, new Class[0]));
    }
    
    public void getFrameKlazz() {
        this.loadThreadContext();
        this.invokeThreadContext("getFrameKlazz", CodegenUtils.sig(RubyModule.class, new Class[0]));
    }
    
    public void superClass() {
        this.loadSelf();
        this.metaclass();
        this.method.swap();
        this.invokeUtilityMethod("findImplementerIfNecessary", CodegenUtils.sig(RubyModule.class, CodegenUtils.params(RubyModule.class, RubyModule.class)));
        this.method.invokevirtual(CodegenUtils.p(RubyModule.class), "getSuperClass", CodegenUtils.sig(RubyClass.class, new Class[0]));
    }
    
    public void attached() {
        this.method.visitTypeInsn(192, CodegenUtils.p(MetaClass.class));
        this.method.invokevirtual(CodegenUtils.p(MetaClass.class), "getAttached", CodegenUtils.sig(IRubyObject.class, new Class[0]));
    }
    
    public void ifNotSuperMethodBound(final Object token) {
        this.method.swap();
        this.method.iconst_0();
        this.method.invokevirtual(CodegenUtils.p(RubyModule.class), "isMethodBound", CodegenUtils.sig(Boolean.TYPE, CodegenUtils.params(String.class, Boolean.TYPE)));
        this.method.ifeq((Label)token);
    }
    
    public void concatArrays() {
        this.method.invokevirtual(CodegenUtils.p(RubyArray.class), "concat", CodegenUtils.sig(RubyArray.class, CodegenUtils.params(IRubyObject.class)));
    }
    
    public void concatObjectArrays() {
        this.invokeUtilityMethod("concatObjectArrays", CodegenUtils.sig(IRubyObject[].class, CodegenUtils.params(IRubyObject[].class, IRubyObject[].class)));
    }
    
    public void appendToArray() {
        this.method.invokevirtual(CodegenUtils.p(RubyArray.class), "append", CodegenUtils.sig(RubyArray.class, CodegenUtils.params(IRubyObject.class)));
    }
    
    public void appendToObjectArray() {
        this.invokeUtilityMethod("appendToObjectArray", CodegenUtils.sig(IRubyObject[].class, CodegenUtils.params(IRubyObject[].class, IRubyObject.class)));
    }
    
    public void convertToJavaArray() {
        this.method.invokestatic(CodegenUtils.p(ArgsUtil.class), "convertToJavaArray", CodegenUtils.sig(IRubyObject[].class, CodegenUtils.params(IRubyObject.class)));
    }
    
    public void aliasGlobal(final String newName, final String oldName) {
        this.loadRuntime();
        this.invokeRuby("getGlobalVariables", CodegenUtils.sig(GlobalVariables.class, new Class[0]));
        this.method.ldc(newName);
        this.method.ldc(oldName);
        this.method.invokevirtual(CodegenUtils.p(GlobalVariables.class), "alias", CodegenUtils.sig(Void.TYPE, CodegenUtils.params(String.class, String.class)));
        this.loadNil();
    }
    
    public void raiseTypeError(final String msg) {
        this.loadRuntime();
        this.method.ldc(msg);
        this.invokeRuby("newTypeError", CodegenUtils.sig(RaiseException.class, CodegenUtils.params(String.class)));
        this.method.athrow();
    }
    
    public void undefMethod(final CompilerCallback nameArg) {
        this.loadThreadContext();
        nameArg.call(this);
        this.invokeUtilityMethod("undefMethod", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, Object.class));
    }
    
    public void defineClass(final String name, final StaticScope staticScope, final CompilerCallback superCallback, final CompilerCallback pathCallback, final CompilerCallback bodyCallback, final CompilerCallback receiverCallback, final ASTInspector inspector) {
        String classMethodName = null;
        String rubyName;
        if (receiverCallback == null) {
            final String mangledName = JavaNameMangler.mangleStringForCleanJavaIdentifier(name);
            classMethodName = "class_" + this.script.getAndIncrementMethodIndex() + "$RUBY$" + mangledName;
            rubyName = mangledName;
        }
        else {
            classMethodName = "sclass_" + this.script.getAndIncrementMethodIndex() + "$RUBY$__singleton__";
            rubyName = "__singleton__";
        }
        final RootScopedBodyCompiler classBody = new ClassBodyCompiler(this.script, classMethodName, rubyName, inspector, staticScope);
        final CompilerCallback bodyPrep = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                if (receiverCallback == null) {
                    if (superCallback != null) {
                        classBody.loadRuntime();
                        classBody.method.aload(2);
                        classBody.invokeUtilityMethod("prepareSuperClass", CodegenUtils.sig(RubyClass.class, CodegenUtils.params(Ruby.class, IRubyObject.class)));
                    }
                    else {
                        classBody.method.aconst_null();
                    }
                    classBody.loadThreadContext();
                    pathCallback.call(classBody);
                    classBody.invokeUtilityMethod("prepareClassNamespace", CodegenUtils.sig(RubyModule.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class)));
                    classBody.method.swap();
                    classBody.method.ldc(name);
                    classBody.method.swap();
                    classBody.method.invokevirtual(CodegenUtils.p(RubyModule.class), "defineOrGetClassUnder", CodegenUtils.sig(RubyClass.class, CodegenUtils.params(String.class, RubyClass.class)));
                }
                else {
                    classBody.loadRuntime();
                    classBody.method.aload(2);
                    final int selfTemp = classBody.getVariableCompiler().grabTempLocal();
                    classBody.getVariableCompiler().setTempLocal(selfTemp);
                    classBody.method.aload(2);
                    classBody.invokeUtilityMethod("getSingletonClass", CodegenUtils.sig(RubyClass.class, CodegenUtils.params(Ruby.class, IRubyObject.class)));
                }
                classBody.method.dup();
                classBody.method.astore(2);
                classBody.loadThreadContext();
                classBody.method.swap();
                BaseBodyCompiler.this.script.getCacheCompiler().cacheStaticScope(classBody, staticScope);
                if (inspector.hasClosure() || inspector.hasScopeAwareMethods()) {
                    classBody.invokeThreadContext("preCompiledClass", CodegenUtils.sig(Void.TYPE, CodegenUtils.params(RubyModule.class, StaticScope.class)));
                }
                else {
                    classBody.invokeThreadContext("preCompiledClassDummyScope", CodegenUtils.sig(Void.TYPE, CodegenUtils.params(RubyModule.class, StaticScope.class)));
                }
                if (RubyInstanceConfig.FULL_TRACE_ENABLED) {
                    classBody.traceClass();
                }
            }
        };
        final Label start = new Label();
        final Label end = new Label();
        final Label after = new Label();
        final Label noException = new Label();
        classBody.method.trycatch(start, end, after, null);
        classBody.beginMethod(bodyPrep, staticScope);
        classBody.method.label(start);
        bodyCallback.call(classBody);
        classBody.method.label(end);
        if (RubyInstanceConfig.FULL_TRACE_ENABLED) {
            classBody.traceEnd();
        }
        classBody.loadThreadContext();
        classBody.invokeThreadContext("postCompiledClass", CodegenUtils.sig(Void.TYPE, CodegenUtils.params(new Class[0])));
        classBody.method.go_to(noException);
        classBody.method.label(after);
        if (RubyInstanceConfig.FULL_TRACE_ENABLED) {
            classBody.traceEnd();
        }
        classBody.loadThreadContext();
        classBody.invokeThreadContext("postCompiledClass", CodegenUtils.sig(Void.TYPE, CodegenUtils.params(new Class[0])));
        classBody.method.athrow();
        classBody.method.label(noException);
        classBody.endBody();
        this.method.aload(0);
        this.loadThreadContext();
        if (receiverCallback == null) {
            if (superCallback != null) {
                superCallback.call(this);
            }
            else {
                this.method.aload(2);
            }
        }
        else {
            receiverCallback.call(this);
        }
        this.method.getstatic(CodegenUtils.p(Block.class), "NULL_BLOCK", CodegenUtils.ci(Block.class));
        this.method.invokestatic(this.script.getClassname(), classMethodName, StandardASMCompiler.getStaticMethodSignature(this.script.getClassname(), 0));
    }
    
    public void defineModule(final String name, final StaticScope staticScope, final CompilerCallback pathCallback, final CompilerCallback bodyCallback, final ASTInspector inspector) {
        final String mangledName = JavaNameMangler.mangleStringForCleanJavaIdentifier(name);
        final String moduleMethodName = "module__" + this.script.getAndIncrementMethodIndex() + "$RUBY$" + mangledName;
        final RootScopedBodyCompiler classBody = new ClassBodyCompiler(this.script, moduleMethodName, mangledName, inspector, staticScope);
        final CompilerCallback bodyPrep = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                classBody.loadThreadContext();
                pathCallback.call(classBody);
                classBody.invokeUtilityMethod("prepareClassNamespace", CodegenUtils.sig(RubyModule.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class)));
                classBody.method.ldc(name);
                classBody.method.invokevirtual(CodegenUtils.p(RubyModule.class), "defineOrGetModuleUnder", CodegenUtils.sig(RubyModule.class, CodegenUtils.params(String.class)));
                classBody.method.dup();
                classBody.method.astore(2);
                classBody.loadThreadContext();
                classBody.method.swap();
                BaseBodyCompiler.this.script.getCacheCompiler().cacheStaticScope(classBody, staticScope);
                if (inspector.hasClosure() || inspector.hasScopeAwareMethods()) {
                    classBody.invokeThreadContext("preCompiledClass", CodegenUtils.sig(Void.TYPE, CodegenUtils.params(RubyModule.class, StaticScope.class)));
                }
                else {
                    classBody.invokeThreadContext("preCompiledClassDummyScope", CodegenUtils.sig(Void.TYPE, CodegenUtils.params(RubyModule.class, StaticScope.class)));
                }
                if (RubyInstanceConfig.FULL_TRACE_ENABLED) {
                    classBody.traceClass();
                }
            }
        };
        final Label start = new Label();
        final Label end = new Label();
        final Label after = new Label();
        final Label noException = new Label();
        classBody.method.trycatch(start, end, after, null);
        classBody.beginMethod(bodyPrep, staticScope);
        classBody.method.label(start);
        bodyCallback.call(classBody);
        classBody.method.label(end);
        classBody.method.go_to(noException);
        classBody.method.label(after);
        if (RubyInstanceConfig.FULL_TRACE_ENABLED) {
            classBody.traceEnd();
        }
        classBody.loadThreadContext();
        classBody.invokeThreadContext("postCompiledClass", CodegenUtils.sig(Void.TYPE, CodegenUtils.params(new Class[0])));
        classBody.method.athrow();
        classBody.method.label(noException);
        if (RubyInstanceConfig.FULL_TRACE_ENABLED) {
            classBody.traceEnd();
        }
        classBody.loadThreadContext();
        classBody.invokeThreadContext("postCompiledClass", CodegenUtils.sig(Void.TYPE, CodegenUtils.params(new Class[0])));
        classBody.endBody();
        this.method.aload(0);
        this.loadThreadContext();
        this.loadSelf();
        this.method.getstatic(CodegenUtils.p(IRubyObject.class), "NULL_ARRAY", CodegenUtils.ci(IRubyObject[].class));
        this.method.getstatic(CodegenUtils.p(Block.class), "NULL_BLOCK", CodegenUtils.ci(Block.class));
        this.method.invokestatic(this.script.getClassname(), moduleMethodName, StandardASMCompiler.getStaticMethodSignature(this.script.getClassname(), 4));
    }
    
    public void unwrapPassedBlock() {
        this.loadBlock();
        this.invokeUtilityMethod("getBlockFromBlockPassBody", CodegenUtils.sig(Block.class, CodegenUtils.params(IRubyObject.class, Block.class)));
    }
    
    public void performBackref(final char type) {
        this.loadThreadContext();
        switch (type) {
            case '~': {
                this.invokeUtilityMethod("backref", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class)));
                break;
            }
            case '&': {
                this.invokeUtilityMethod("backrefLastMatch", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class)));
                break;
            }
            case '`': {
                this.invokeUtilityMethod("backrefMatchPre", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class)));
                break;
            }
            case '\'': {
                this.invokeUtilityMethod("backrefMatchPost", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class)));
                break;
            }
            case '+': {
                this.invokeUtilityMethod("backrefMatchLast", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class)));
                break;
            }
            default: {
                throw new NotCompilableException("ERROR: backref with invalid type");
            }
        }
    }
    
    public void callZSuper(final CompilerCallback closure) {
        final ArgumentsCallback argsCallback = new ArgumentsCallback() {
            public int getArity() {
                return -1;
            }
            
            public void call(final BodyCompiler context) {
                BaseBodyCompiler.this.loadThreadContext();
                BaseBodyCompiler.this.invokeUtilityMethod("getArgValues", CodegenUtils.sig(IRubyObject[].class, ThreadContext.class));
            }
        };
        this.getInvocationCompiler().invokeDynamic(null, null, argsCallback, CallType.SUPER, closure, false);
    }
    
    public void checkIsExceptionHandled(final ArgumentsCallback rescueArgs) {
        rescueArgs.call(this);
        this.loadThreadContext();
        switch (rescueArgs.getArity()) {
            case 1: {
                this.invokeUtilityMethod("isJavaExceptionHandled", CodegenUtils.sig(IRubyObject.class, Throwable.class, IRubyObject.class, ThreadContext.class));
                break;
            }
            case 2: {
                this.invokeUtilityMethod("isJavaExceptionHandled", CodegenUtils.sig(IRubyObject.class, Throwable.class, IRubyObject.class, IRubyObject.class, ThreadContext.class));
                break;
            }
            case 3: {
                this.invokeUtilityMethod("isJavaExceptionHandled", CodegenUtils.sig(IRubyObject.class, Throwable.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, ThreadContext.class));
                break;
            }
            default: {
                this.invokeUtilityMethod("isJavaExceptionHandled", CodegenUtils.sig(IRubyObject.class, Throwable.class, IRubyObject[].class, ThreadContext.class));
                break;
            }
        }
    }
    
    public void rethrowException() {
        this.loadException();
        this.method.athrow();
    }
    
    public void loadClass(final String name) {
        this.loadRuntime();
        this.method.ldc(name);
        this.invokeRuby("getClass", CodegenUtils.sig(RubyClass.class, String.class));
    }
    
    public void loadStandardError() {
        this.loadRuntime();
        this.invokeRuby("getStandardError", CodegenUtils.sig(RubyClass.class, new Class[0]));
    }
    
    public void unwrapRaiseException() {
        this.method.invokevirtual(CodegenUtils.p(RaiseException.class), "getException", CodegenUtils.sig(RubyException.class, new Class[0]));
    }
    
    public void loadException() {
        this.method.aload(this.getExceptionIndex());
    }
    
    public void setFilePosition(final ISourcePosition position) {
        if (RubyInstanceConfig.FULL_TRACE_ENABLED) {
            this.loadThreadContext();
            this.method.ldc(position.getFile());
            this.invokeThreadContext("setFile", CodegenUtils.sig(Void.TYPE, CodegenUtils.params(String.class)));
        }
    }
    
    public void setLinePosition(final ISourcePosition position) {
        if (RubyInstanceConfig.FULL_TRACE_ENABLED) {
            if (this.lastPositionLine == position.getStartLine()) {
                return;
            }
            this.lastPositionLine = position.getStartLine();
            this.loadThreadContext();
            this.method.pushInt(position.getStartLine());
            this.method.invokestatic(this.script.getClassname(), "setPosition", CodegenUtils.sig(Void.TYPE, CodegenUtils.params(ThreadContext.class, Integer.TYPE)));
        }
    }
    
    public void checkWhenWithSplat() {
        this.loadThreadContext();
        this.invokeUtilityMethod("isWhenTriggered", CodegenUtils.sig(RubyBoolean.class, IRubyObject.class, IRubyObject.class, ThreadContext.class));
    }
    
    public void issueRetryEvent() {
        this.invokeUtilityMethod("retryJump", CodegenUtils.sig(IRubyObject.class, new Class[0]));
    }
    
    public void defineNewMethod(final String name, final int methodArity, final StaticScope scope, final CompilerCallback body, final CompilerCallback args, final CompilerCallback receiver, final ASTInspector inspector, final boolean root, final String filename, final int line, final String parameterDesc) {
        String newMethodName;
        if (root && SafePropertyAccessor.getBoolean("jruby.compile.toplevel", false)) {
            newMethodName = name;
        }
        else {
            final String mangledName = JavaNameMangler.mangleStringForCleanJavaIdentifier(name);
            newMethodName = "method__" + this.script.getAndIncrementMethodIndex() + "$RUBY$" + mangledName;
        }
        final BodyCompiler methodCompiler = this.script.startMethod(name, newMethodName, args, scope, inspector);
        body.call(methodCompiler);
        methodCompiler.endBody();
        this.loadThreadContext();
        this.loadSelf();
        if (receiver != null) {
            receiver.call(this);
        }
        this.method.aload(0);
        this.method.ldc(name);
        this.method.ldc(newMethodName);
        final String scopeNames = RuntimeHelpers.encodeScope(scope);
        this.method.ldc(scopeNames);
        this.method.pushInt(methodArity);
        this.method.ldc(filename);
        this.method.ldc(line);
        this.method.getstatic(CodegenUtils.p(CallConfiguration.class), inspector.getCallConfig().name(), CodegenUtils.ci(CallConfiguration.class));
        this.method.ldc(parameterDesc);
        if (receiver != null) {
            this.invokeUtilityMethod("defs", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, IRubyObject.class, Object.class, String.class, String.class, String.class, Integer.TYPE, String.class, Integer.TYPE, CallConfiguration.class, String.class)));
        }
        else {
            this.invokeUtilityMethod("def", CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, Object.class, String.class, String.class, String.class, Integer.TYPE, String.class, Integer.TYPE, CallConfiguration.class, String.class)));
        }
        this.script.addInvokerDescriptor(newMethodName, methodArity, scope, inspector.getCallConfig(), filename, line);
    }
    
    public void rethrowIfSystemExit() {
        this.loadRuntime();
        this.method.ldc("SystemExit");
        this.method.invokevirtual(CodegenUtils.p(Ruby.class), "fastGetClass", CodegenUtils.sig(RubyClass.class, String.class));
        this.method.swap();
        this.method.invokevirtual(CodegenUtils.p(RubyModule.class), "isInstance", CodegenUtils.sig(Boolean.TYPE, CodegenUtils.params(IRubyObject.class)));
        this.method.iconst_0();
        final Label ifEnd = new Label();
        this.method.if_icmpeq(ifEnd);
        this.loadException();
        this.method.athrow();
        this.method.label(ifEnd);
    }
    
    public void literalSwitch(final int[] cases, final Object[] bodies, final ArrayCallback arrayCallback, final CompilerCallback defaultCallback) {
        this.method.checkcast(CodegenUtils.p(RubyFixnum.class));
        this.method.invokevirtual(CodegenUtils.p(RubyFixnum.class), "getLongValue", CodegenUtils.sig(Long.TYPE, new Class[0]));
        this.method.l2i();
        final Map<Object, Label> labelMap = new HashMap<Object, Label>();
        final Label[] labels = new Label[cases.length];
        for (int i = 0; i < labels.length; ++i) {
            final Object body = bodies[i];
            Label label = labelMap.get(body);
            if (label == null) {
                label = new Label();
                labelMap.put(body, label);
            }
            labels[i] = label;
        }
        final Label defaultLabel = new Label();
        final Label endLabel = new Label();
        this.method.lookupswitch(defaultLabel, cases, labels);
        final Set<Label> labelDone = new HashSet<Label>();
        for (int j = 0; j < cases.length; ++j) {
            if (!labelDone.contains(labels[j])) {
                labelDone.add(labels[j]);
                this.method.label(labels[j]);
                arrayCallback.nextValue(this, bodies, j);
                this.method.go_to(endLabel);
            }
        }
        this.method.label(defaultLabel);
        defaultCallback.call(this);
        this.method.label(endLabel);
    }
    
    public void typeCheckBranch(final Class type, final BranchCallback trueCallback, final BranchCallback falseCallback) {
        final Label elseLabel = new Label();
        final Label done = new Label();
        this.method.dup();
        this.method.instance_of(CodegenUtils.p(type));
        this.method.ifeq(elseLabel);
        trueCallback.branch(this);
        this.method.go_to(done);
        this.method.label(elseLabel);
        falseCallback.branch(this);
        this.method.label(done);
    }
    
    public void loadFilename() {
        this.loadRuntime();
        this.loadThis();
        this.method.getfield(this.getScriptCompiler().getClassname(), "filename", CodegenUtils.ci(String.class));
        this.method.invokestatic(CodegenUtils.p(RubyString.class), "newString", CodegenUtils.sig(RubyString.class, Ruby.class, CharSequence.class));
    }
    
    public void compileSequencedConditional(final CompilerCallback inputValue, final FastSwitchType fastSwitchType, final Map<CompilerCallback, int[]> switchCases, final List<ArgumentsCallback> conditionals, final List<CompilerCallback> bodies, final CompilerCallback fallback) {
        final Map<CompilerCallback, Label> bodyLabels = new HashMap<CompilerCallback, Label>();
        final Label defaultCase = new Label();
        final Label slowPath = new Label();
        CompilerCallback getCaseValue = null;
        final int tmp = this.getVariableCompiler().grabTempLocal();
        if (inputValue != null) {
            inputValue.call(this);
            this.getVariableCompiler().setTempLocal(tmp);
            getCaseValue = new CompilerCallback() {
                public void call(final BodyCompiler context) {
                    BaseBodyCompiler.this.getVariableCompiler().getTempLocal(tmp);
                }
            };
            if (switchCases != null) {
                final SortedMap<Integer, Label> optimizedLabels = new TreeMap<Integer, Label>();
                for (final Map.Entry<CompilerCallback, int[]> entry : switchCases.entrySet()) {
                    final Label lbl = new Label();
                    bodyLabels.put(entry.getKey(), lbl);
                    for (final int i : entry.getValue()) {
                        optimizedLabels.put(i, lbl);
                    }
                }
                final int[] caseValues = new int[optimizedLabels.size()];
                final Label[] caseLabels = new Label[optimizedLabels.size()];
                final Set<Map.Entry<Integer, Label>> entrySet = optimizedLabels.entrySet();
                final Iterator<Map.Entry<Integer, Label>> iterator = entrySet.iterator();
                for (int j = 0; j < entrySet.size(); ++j) {
                    final Map.Entry<Integer, Label> entry2 = iterator.next();
                    caseValues[j] = entry2.getKey();
                    caseLabels[j] = entry2.getValue();
                }
                getCaseValue.call(this);
                this.method.instance_of(CodegenUtils.p(fastSwitchType.getAssociatedClass()));
                this.method.ifeq(slowPath);
                switch (fastSwitchType) {
                    case FIXNUM: {
                        getCaseValue.call(this);
                        this.method.checkcast(CodegenUtils.p(RubyFixnum.class));
                        this.method.invokevirtual(CodegenUtils.p(RubyFixnum.class), "getLongValue", CodegenUtils.sig(Long.TYPE, new Class[0]));
                        this.method.l2i();
                        break;
                    }
                    case SINGLE_CHAR_STRING: {
                        getCaseValue.call(this);
                        this.invokeUtilityMethod("isFastSwitchableSingleCharString", CodegenUtils.sig(Boolean.TYPE, IRubyObject.class));
                        this.method.ifeq(slowPath);
                        getCaseValue.call(this);
                        this.invokeUtilityMethod("getFastSwitchSingleCharString", CodegenUtils.sig(Integer.TYPE, IRubyObject.class));
                        break;
                    }
                    case STRING: {
                        getCaseValue.call(this);
                        this.invokeUtilityMethod("isFastSwitchableString", CodegenUtils.sig(Boolean.TYPE, IRubyObject.class));
                        this.method.ifeq(slowPath);
                        getCaseValue.call(this);
                        this.invokeUtilityMethod("getFastSwitchString", CodegenUtils.sig(Integer.TYPE, IRubyObject.class));
                        break;
                    }
                    case SINGLE_CHAR_SYMBOL: {
                        getCaseValue.call(this);
                        this.invokeUtilityMethod("isFastSwitchableSingleCharSymbol", CodegenUtils.sig(Boolean.TYPE, IRubyObject.class));
                        this.method.ifeq(slowPath);
                        getCaseValue.call(this);
                        this.invokeUtilityMethod("getFastSwitchSingleCharSymbol", CodegenUtils.sig(Integer.TYPE, IRubyObject.class));
                        break;
                    }
                    case SYMBOL: {
                        getCaseValue.call(this);
                        this.invokeUtilityMethod("isFastSwitchableSymbol", CodegenUtils.sig(Boolean.TYPE, IRubyObject.class));
                        this.method.ifeq(slowPath);
                        getCaseValue.call(this);
                        this.invokeUtilityMethod("getFastSwitchSymbol", CodegenUtils.sig(Integer.TYPE, IRubyObject.class));
                        break;
                    }
                }
                this.method.lookupswitch(defaultCase, caseValues, caseLabels);
            }
        }
        final Label done = new Label();
        Label currentLabel = slowPath;
        for (int k = 0; k < conditionals.size(); ++k) {
            final ArgumentsCallback conditional = conditionals.get(k);
            final CompilerCallback body = bodies.get(k);
            this.method.label(currentLabel);
            this.getInvocationCompiler().invokeEqq(conditional, getCaseValue);
            if (k + 1 < conditionals.size()) {
                currentLabel = new Label();
            }
            else {
                currentLabel = defaultCase;
            }
            this.method.ifeq(currentLabel);
            final Label bodyLabel = bodyLabels.get(body);
            if (bodyLabel != null) {
                this.method.label(bodyLabel);
            }
            body.call(this);
            this.method.go_to(done);
        }
        this.method.label(currentLabel);
        fallback.call(this);
        this.method.label(done);
        this.getVariableCompiler().releaseTempLocal();
    }
    
    public void traceLine() {
        this.loadThreadContext();
        this.invokeUtilityMethod("traceLine", CodegenUtils.sig(Void.TYPE, ThreadContext.class));
    }
    
    public void traceClass() {
        this.loadThreadContext();
        this.invokeUtilityMethod("traceClass", CodegenUtils.sig(Void.TYPE, ThreadContext.class));
    }
    
    public void traceEnd() {
        this.loadThreadContext();
        this.invokeUtilityMethod("traceEnd", CodegenUtils.sig(Void.TYPE, ThreadContext.class));
    }
    
    public void preMultiAssign(final int head, final boolean args) {
        if (head == 1 && args) {
            this.invokeUtilityMethod("arraySlice1N", CodegenUtils.sig(IRubyObject[].class, IRubyObject.class));
            this.method.dup();
            this.method.pushInt(1);
            this.method.aaload();
            this.method.swap();
            this.method.pushInt(0);
            this.method.aaload();
        }
        else {
            if (head != 1 || args) {
                throw new RuntimeException("invalid preMultiAssign args: " + head + ", " + args);
            }
            this.invokeUtilityMethod("arraySlice1", CodegenUtils.sig(IRubyObject.class, IRubyObject.class));
        }
    }
    
    public void argsPush() {
        this.invokeUtilityMethod("argsPush", CodegenUtils.sig(RubyArray.class, RubyArray.class, IRubyObject.class));
    }
    
    public void argsCat() {
        this.invokeUtilityMethod("argsCat", CodegenUtils.sig(RubyArray.class, IRubyObject.class, IRubyObject.class));
    }
    
    public void loadEncoding(final Encoding encoding) {
        this.script.getCacheCompiler().cacheEncoding(this, encoding);
    }
    
    public void definedCall(final String name) {
        this.loadThreadContext();
        this.loadSelf();
        this.method.dup2_x1();
        this.method.pop2();
        this.method.ldc(name);
        this.invokeUtilityMethod("getDefinedCall", CodegenUtils.sig(ByteList.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class));
    }
    
    public void definedNot() {
        this.loadRuntime();
        this.method.swap();
        this.invokeUtilityMethod("getDefinedNot", CodegenUtils.sig(ByteList.class, Ruby.class, ByteList.class));
    }
}
