// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.impl;

import java.util.Iterator;
import org.jruby.ast.executable.AbstractScript;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.CompiledBlockCallback;
import org.jruby.runtime.BlockBody;
import org.jruby.compiler.ASTInspector;
import org.jruby.ast.NodeType;
import java.math.BigInteger;
import org.jruby.RubyEncoding;
import org.jruby.runtime.encoding.EncodingService;
import org.jcodings.Encoding;
import org.jruby.RubyModule;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyFloat;
import org.jruby.RubyFixnum;
import org.jruby.RubyString;
import org.jruby.compiler.BodyCompiler;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.org.objectweb.asm.Label;
import org.jruby.compiler.CompilerCallback;
import org.jruby.RubyRegexp;
import org.jruby.util.ByteList;
import org.jruby.Ruby;
import org.jruby.RubySymbol;
import org.jruby.runtime.CallSite;
import org.jruby.util.CodegenUtils;
import org.jruby.runtime.ThreadContext;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.parser.StaticScope;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import org.jruby.runtime.CallType;
import java.util.List;
import org.jruby.compiler.CacheCompiler;

public class InheritedCacheCompiler implements CacheCompiler
{
    protected StandardASMCompiler scriptCompiler;
    int scopeCount;
    int callSiteCount;
    List<String> callSiteList;
    List<CallType> callTypeList;
    Map<String, Integer> stringIndices;
    Map<String, Integer> encodingIndices;
    Map<String, Integer> stringEncodings;
    Map<String, Integer> symbolIndices;
    Map<Long, Integer> fixnumIndices;
    Map<Double, Integer> floatIndices;
    int inheritedSymbolCount;
    int inheritedStringCount;
    int inheritedEncodingCount;
    int inheritedRegexpCount;
    int inheritedBigIntegerCount;
    int inheritedVariableReaderCount;
    int inheritedVariableWriterCount;
    int inheritedFixnumCount;
    int inheritedFloatCount;
    int inheritedConstantCount;
    int inheritedBlockBodyCount;
    int inheritedBlockCallbackCount;
    int inheritedMethodCount;
    boolean runtimeCacheInited;
    
    public InheritedCacheCompiler(final StandardASMCompiler scriptCompiler) {
        this.scopeCount = 0;
        this.callSiteCount = 0;
        this.callSiteList = new ArrayList<String>();
        this.callTypeList = new ArrayList<CallType>();
        this.stringIndices = new HashMap<String, Integer>();
        this.encodingIndices = new HashMap<String, Integer>();
        this.stringEncodings = new HashMap<String, Integer>();
        this.symbolIndices = new HashMap<String, Integer>();
        this.fixnumIndices = new HashMap<Long, Integer>();
        this.floatIndices = new HashMap<Double, Integer>();
        this.inheritedSymbolCount = 0;
        this.inheritedStringCount = 0;
        this.inheritedEncodingCount = 0;
        this.inheritedRegexpCount = 0;
        this.inheritedBigIntegerCount = 0;
        this.inheritedVariableReaderCount = 0;
        this.inheritedVariableWriterCount = 0;
        this.inheritedFixnumCount = 0;
        this.inheritedFloatCount = 0;
        this.inheritedConstantCount = 0;
        this.inheritedBlockBodyCount = 0;
        this.inheritedBlockCallbackCount = 0;
        this.inheritedMethodCount = 0;
        this.runtimeCacheInited = false;
        this.scriptCompiler = scriptCompiler;
    }
    
    public void cacheStaticScope(final BaseBodyCompiler method, final StaticScope scope) {
        final String scopeString = RuntimeHelpers.encodeScope(scope);
        method.loadThis();
        method.loadThreadContext();
        method.method.ldc(scopeString);
        if (this.scopeCount < 10) {
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getScope" + this.scopeCount, CodegenUtils.sig(StaticScope.class, ThreadContext.class, String.class));
        }
        else {
            method.method.pushInt(this.scopeCount);
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getScope", CodegenUtils.sig(StaticScope.class, ThreadContext.class, String.class, Integer.TYPE));
        }
        ++this.scopeCount;
    }
    
    public void cacheCallSite(final BaseBodyCompiler method, final String name, final CallType callType) {
        method.loadThis();
        if (this.callSiteCount < 10) {
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getCallSite" + this.callSiteCount, CodegenUtils.sig(CallSite.class, new Class[0]));
        }
        else {
            method.method.pushInt(this.callSiteCount);
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getCallSite", CodegenUtils.sig(CallSite.class, Integer.TYPE));
        }
        this.callSiteList.add(name);
        this.callTypeList.add(callType);
        ++this.callSiteCount;
    }
    
    public void cacheSymbol(final BaseBodyCompiler method, final String symbol) {
        Integer index = this.symbolIndices.get(symbol);
        if (index == null) {
            index = this.inheritedSymbolCount++;
            this.symbolIndices.put(symbol, index);
        }
        method.loadThis();
        method.loadRuntime();
        if (index < 10) {
            method.method.ldc(symbol);
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getSymbol" + index, CodegenUtils.sig(RubySymbol.class, Ruby.class, String.class));
        }
        else {
            method.method.ldc((int)index);
            method.method.ldc(symbol);
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getSymbol", CodegenUtils.sig(RubySymbol.class, Ruby.class, Integer.TYPE, String.class));
        }
    }
    
    public void cacheRegexp(final BaseBodyCompiler method, final ByteList pattern, final int options) {
        method.loadThis();
        method.loadRuntime();
        final int index = this.inheritedRegexpCount++;
        if (index < 10) {
            this.cacheByteList(method, pattern);
            method.method.ldc(options);
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getRegexp" + index, CodegenUtils.sig(RubyRegexp.class, Ruby.class, ByteList.class, Integer.TYPE));
        }
        else {
            method.method.pushInt(index);
            this.cacheByteList(method, pattern);
            method.method.ldc(options);
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getRegexp", CodegenUtils.sig(RubyRegexp.class, Ruby.class, Integer.TYPE, ByteList.class, Integer.TYPE));
        }
    }
    
    public void cacheDRegexp(final BaseBodyCompiler method, final CompilerCallback createStringCallback, final int options) {
        final int index = this.inheritedRegexpCount++;
        final Label alreadyCompiled = new Label();
        method.loadThis();
        method.method.getfield(this.scriptCompiler.getClassname(), "runtimeCache", CodegenUtils.ci(RuntimeCache.class));
        method.method.pushInt(index);
        method.method.invokevirtual(CodegenUtils.p(RuntimeCache.class), "getRegexp", CodegenUtils.sig(RubyRegexp.class, Integer.TYPE));
        method.method.dup();
        method.ifNotNull(alreadyCompiled);
        method.method.pop();
        method.loadThis();
        method.method.getfield(this.scriptCompiler.getClassname(), "runtimeCache", CodegenUtils.ci(RuntimeCache.class));
        method.method.pushInt(index);
        createStringCallback.call(method);
        method.method.ldc(options);
        method.method.invokevirtual(CodegenUtils.p(RuntimeCache.class), "cacheRegexp", CodegenUtils.sig(RubyRegexp.class, Integer.TYPE, RubyString.class, Integer.TYPE));
        method.method.label(alreadyCompiled);
    }
    
    public void cacheFixnum(final BaseBodyCompiler method, final long value) {
        if (value <= 5L && value >= -1L) {
            method.loadRuntime();
            switch ((int)value) {
                case -1: {
                    method.method.invokestatic(CodegenUtils.p(RubyFixnum.class), "minus_one", CodegenUtils.sig(RubyFixnum.class, Ruby.class));
                    break;
                }
                case 0: {
                    method.method.invokestatic(CodegenUtils.p(RubyFixnum.class), "zero", CodegenUtils.sig(RubyFixnum.class, Ruby.class));
                    break;
                }
                case 1: {
                    method.method.invokestatic(CodegenUtils.p(RubyFixnum.class), "one", CodegenUtils.sig(RubyFixnum.class, Ruby.class));
                    break;
                }
                case 2: {
                    method.method.invokestatic(CodegenUtils.p(RubyFixnum.class), "two", CodegenUtils.sig(RubyFixnum.class, Ruby.class));
                    break;
                }
                case 3: {
                    method.method.invokestatic(CodegenUtils.p(RubyFixnum.class), "three", CodegenUtils.sig(RubyFixnum.class, Ruby.class));
                    break;
                }
                case 4: {
                    method.method.invokestatic(CodegenUtils.p(RubyFixnum.class), "four", CodegenUtils.sig(RubyFixnum.class, Ruby.class));
                    break;
                }
                case 5: {
                    method.method.invokestatic(CodegenUtils.p(RubyFixnum.class), "five", CodegenUtils.sig(RubyFixnum.class, Ruby.class));
                    break;
                }
                default: {
                    throw new RuntimeException("wtf?");
                }
            }
        }
        else {
            Integer index = this.fixnumIndices.get(value);
            if (index == null) {
                index = this.inheritedFixnumCount++;
                this.fixnumIndices.put(value, index);
            }
            method.loadThis();
            method.loadRuntime();
            if (value <= 2147483647L && value >= -2147483648L) {
                if (index < 10) {
                    method.method.pushInt((int)value);
                    method.method.invokevirtual(this.scriptCompiler.getClassname(), "getFixnum" + index, CodegenUtils.sig(RubyFixnum.class, Ruby.class, Integer.TYPE));
                }
                else {
                    method.method.pushInt(index);
                    method.method.pushInt((int)value);
                    method.method.invokevirtual(this.scriptCompiler.getClassname(), "getFixnum", CodegenUtils.sig(RubyFixnum.class, Ruby.class, Integer.TYPE, Integer.TYPE));
                }
            }
            else {
                method.method.pushInt(index);
                method.method.ldc(value);
                method.method.invokevirtual(this.scriptCompiler.getClassname(), "getFixnum", CodegenUtils.sig(RubyFixnum.class, Ruby.class, Integer.TYPE, Long.TYPE));
            }
        }
    }
    
    public void cacheFloat(final BaseBodyCompiler method, final double value) {
        final Integer index = this.inheritedFloatCount++;
        this.floatIndices.put(value, index);
        method.loadThis();
        method.loadRuntime();
        if (index < 10) {
            method.method.ldc(value);
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getFloat" + index, CodegenUtils.sig(RubyFloat.class, Ruby.class, Double.TYPE));
        }
        else {
            method.method.pushInt(index);
            method.method.ldc(value);
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getFloat", CodegenUtils.sig(RubyFloat.class, Ruby.class, Integer.TYPE, Double.TYPE));
        }
    }
    
    public void cacheConstant(final BaseBodyCompiler method, final String constantName) {
        method.loadThis();
        method.loadThreadContext();
        method.method.ldc(constantName);
        if (this.inheritedConstantCount < 10) {
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getConstant" + this.inheritedConstantCount, CodegenUtils.sig(IRubyObject.class, ThreadContext.class, String.class));
        }
        else {
            method.method.pushInt(this.inheritedConstantCount);
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getConstant", CodegenUtils.sig(IRubyObject.class, ThreadContext.class, String.class, Integer.TYPE));
        }
        ++this.inheritedConstantCount;
    }
    
    public void cacheConstantFrom(final BaseBodyCompiler method, final String constantName) {
        method.loadThis();
        method.method.swap();
        method.loadThreadContext();
        method.method.ldc(constantName);
        if (this.inheritedConstantCount < 10) {
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getConstantFrom" + this.inheritedConstantCount, CodegenUtils.sig(IRubyObject.class, RubyModule.class, ThreadContext.class, String.class));
        }
        else {
            method.method.pushInt(this.inheritedConstantCount);
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getConstantFrom", CodegenUtils.sig(IRubyObject.class, RubyModule.class, ThreadContext.class, String.class, Integer.TYPE));
        }
        ++this.inheritedConstantCount;
    }
    
    public void cacheString(final BaseBodyCompiler method, final ByteList contents, final int codeRange) {
        final String asString = RuntimeHelpers.rawBytesToString(contents.bytes());
        Integer index = this.stringIndices.get(asString);
        if (index == null) {
            index = this.inheritedStringCount++;
            this.stringIndices.put(asString, index);
            this.stringEncodings.put(asString, this.cacheEncoding(contents.getEncoding()));
        }
        method.loadThis();
        method.loadRuntime();
        if (index < 10) {
            method.method.pushInt(codeRange);
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getString" + index, CodegenUtils.sig(RubyString.class, Ruby.class, Integer.TYPE));
        }
        else {
            method.method.pushInt(index);
            method.method.pushInt(codeRange);
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getString", CodegenUtils.sig(RubyString.class, Ruby.class, Integer.TYPE, Integer.TYPE));
        }
    }
    
    public void cacheByteList(final BaseBodyCompiler method, final ByteList contents) {
        final String asString = RuntimeHelpers.rawBytesToString(contents.bytes());
        Integer index = this.stringIndices.get(asString);
        if (index == null) {
            index = this.inheritedStringCount++;
            this.stringIndices.put(asString, index);
            this.stringEncodings.put(asString, this.cacheEncoding(contents.getEncoding()));
        }
        method.loadThis();
        if (index < 10) {
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getByteList" + index, CodegenUtils.sig(ByteList.class, new Class[0]));
        }
        else {
            method.method.pushInt(index);
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getByteList", CodegenUtils.sig(ByteList.class, Integer.TYPE));
        }
    }
    
    public void cacheEncoding(final BaseBodyCompiler method, final Encoding encoding) {
        final int encodingIndex = this.cacheEncoding(encoding);
        this.loadEncoding(method.method, encodingIndex);
        this.createRubyEncoding(method);
    }
    
    private int cacheEncoding(final Encoding encoding) {
        final String encodingName = new String(encoding.getName());
        Integer index = this.encodingIndices.get(encodingName);
        if (index == null) {
            index = this.inheritedEncodingCount++;
            this.encodingIndices.put(encodingName, index);
        }
        return index;
    }
    
    private void loadEncoding(final SkinnyMethodAdapter method, final int encodingIndex) {
        method.aload(0);
        if (encodingIndex < 10) {
            method.invokevirtual(this.scriptCompiler.getClassname(), "getEncoding" + encodingIndex, CodegenUtils.sig(Encoding.class, new Class[0]));
        }
        else {
            method.pushInt(encodingIndex);
            method.invokevirtual(this.scriptCompiler.getClassname(), "getEncoding", CodegenUtils.sig(Encoding.class, Integer.TYPE));
        }
    }
    
    private void createRubyEncoding(final BaseBodyCompiler method) {
        method.loadRuntime();
        method.invokeRuby("getEncodingService", CodegenUtils.sig(EncodingService.class, new Class[0]));
        method.method.swap();
        method.method.invokevirtual(CodegenUtils.p(EncodingService.class), "getEncoding", CodegenUtils.sig(RubyEncoding.class, Encoding.class));
    }
    
    public void cacheBigInteger(final BaseBodyCompiler method, final BigInteger bigint) {
        method.loadThis();
        method.loadRuntime();
        final int index = this.inheritedBigIntegerCount++;
        if (index < 10) {
            method.method.ldc(bigint.toString(16));
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getBigInteger" + index, CodegenUtils.sig(BigInteger.class, Ruby.class, String.class));
        }
        else {
            method.method.pushInt(index);
            method.method.ldc(bigint.toString(16));
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getBigInteger", CodegenUtils.sig(BigInteger.class, Ruby.class, Integer.TYPE, String.class));
        }
    }
    
    public void cachedGetVariable(final BaseBodyCompiler method, final String name) {
        method.loadThis();
        method.loadRuntime();
        final int index = this.inheritedVariableReaderCount++;
        if (index < 10) {
            method.method.ldc(name);
            method.loadSelf();
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getVariable" + index, CodegenUtils.sig(IRubyObject.class, Ruby.class, String.class, IRubyObject.class));
        }
        else {
            method.method.pushInt(index);
            method.method.ldc(name);
            method.loadSelf();
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getVariable", CodegenUtils.sig(IRubyObject.class, Ruby.class, Integer.TYPE, String.class, IRubyObject.class));
        }
    }
    
    public void cachedSetVariable(final BaseBodyCompiler method, final String name, final CompilerCallback valueCallback) {
        method.loadThis();
        method.loadRuntime();
        final int index = this.inheritedVariableWriterCount++;
        if (index < 10) {
            method.method.ldc(name);
            method.loadSelf();
            valueCallback.call(method);
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "setVariable" + index, CodegenUtils.sig(IRubyObject.class, Ruby.class, String.class, IRubyObject.class, IRubyObject.class));
        }
        else {
            method.method.pushInt(index);
            method.method.ldc(name);
            method.loadSelf();
            valueCallback.call(method);
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "setVariable", CodegenUtils.sig(IRubyObject.class, Ruby.class, Integer.TYPE, String.class, IRubyObject.class, IRubyObject.class));
        }
    }
    
    public void cacheClosure(final BaseBodyCompiler method, final String closureMethod, final int arity, final StaticScope scope, final String file, final int line, final boolean hasMultipleArgsHead, final NodeType argsNodeId, final ASTInspector inspector) {
        final String descriptor = RuntimeHelpers.buildBlockDescriptor(closureMethod, arity, scope, file, line, hasMultipleArgsHead, argsNodeId, inspector);
        method.loadThis();
        method.loadThreadContext();
        if (this.inheritedBlockBodyCount < 10) {
            method.method.ldc(descriptor);
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getBlockBody" + this.inheritedBlockBodyCount, CodegenUtils.sig(BlockBody.class, ThreadContext.class, String.class));
        }
        else {
            method.method.pushInt(this.inheritedBlockBodyCount);
            method.method.ldc(descriptor);
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getBlockBody", CodegenUtils.sig(BlockBody.class, ThreadContext.class, Integer.TYPE, String.class));
        }
        ++this.inheritedBlockBodyCount;
    }
    
    public void cacheClosure19(final BaseBodyCompiler method, final String closureMethod, final int arity, final StaticScope scope, final String file, final int line, final boolean hasMultipleArgsHead, final NodeType argsNodeId, final String parameterList, final ASTInspector inspector) {
        final String descriptor = RuntimeHelpers.buildBlockDescriptor19(closureMethod, arity, scope, file, line, hasMultipleArgsHead, argsNodeId, parameterList, inspector);
        method.loadThis();
        method.loadThreadContext();
        if (this.inheritedBlockBodyCount < 10) {
            method.method.ldc(descriptor);
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getBlockBody19" + this.inheritedBlockBodyCount, CodegenUtils.sig(BlockBody.class, ThreadContext.class, String.class));
        }
        else {
            method.method.pushInt(this.inheritedBlockBodyCount);
            method.method.ldc(descriptor);
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getBlockBody19", CodegenUtils.sig(BlockBody.class, ThreadContext.class, Integer.TYPE, String.class));
        }
        ++this.inheritedBlockBodyCount;
    }
    
    public void cacheSpecialClosure(final BaseBodyCompiler method, final String closureMethod) {
        method.loadThis();
        method.loadRuntime();
        if (this.inheritedBlockCallbackCount < 10) {
            method.method.ldc(closureMethod);
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getBlockCallback" + this.inheritedBlockCallbackCount, CodegenUtils.sig(CompiledBlockCallback.class, Ruby.class, String.class));
        }
        else {
            method.method.pushInt(this.inheritedBlockCallbackCount);
            method.method.ldc(closureMethod);
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getBlockCallback", CodegenUtils.sig(CompiledBlockCallback.class, Ruby.class, Integer.TYPE, String.class));
        }
        ++this.inheritedBlockCallbackCount;
    }
    
    public void cacheMethod(final BaseBodyCompiler method, final String methodName) {
        method.loadThis();
        method.loadThreadContext();
        method.loadSelf();
        if (this.inheritedMethodCount < 10) {
            method.method.ldc(methodName);
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getMethod" + this.inheritedMethodCount, CodegenUtils.sig(DynamicMethod.class, ThreadContext.class, IRubyObject.class, String.class));
        }
        else {
            method.method.pushInt(this.inheritedMethodCount);
            method.method.ldc(methodName);
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getMethod", CodegenUtils.sig(DynamicMethod.class, ThreadContext.class, IRubyObject.class, Integer.TYPE, String.class));
        }
        ++this.inheritedMethodCount;
    }
    
    public void cacheMethod(final BaseBodyCompiler method, final String methodName, final int receiverLocal) {
        method.loadThis();
        method.loadThreadContext();
        method.method.aload(receiverLocal);
        if (this.inheritedMethodCount < 10) {
            method.method.ldc(methodName);
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getMethod" + this.inheritedMethodCount, CodegenUtils.sig(DynamicMethod.class, ThreadContext.class, IRubyObject.class, String.class));
        }
        else {
            method.method.pushInt(this.inheritedMethodCount);
            method.method.ldc(methodName);
            method.method.invokevirtual(this.scriptCompiler.getClassname(), "getMethod", CodegenUtils.sig(DynamicMethod.class, ThreadContext.class, IRubyObject.class, Integer.TYPE, String.class));
        }
        ++this.inheritedMethodCount;
    }
    
    public void finish() {
        final SkinnyMethodAdapter initMethod = this.scriptCompiler.getInitMethod();
        final int callSiteListSize = this.callSiteList.size();
        final int otherCount = this.scopeCount + this.inheritedSymbolCount + this.inheritedFixnumCount + this.inheritedFloatCount + this.inheritedConstantCount + this.inheritedRegexpCount + this.inheritedBigIntegerCount + this.inheritedVariableReaderCount + this.inheritedVariableWriterCount + this.inheritedBlockBodyCount + this.inheritedBlockCallbackCount + this.inheritedMethodCount + this.inheritedStringCount + this.inheritedEncodingCount;
        if (callSiteListSize + otherCount != 0) {
            this.ensureRuntimeCacheInited(initMethod);
            final StringBuffer descriptor = new StringBuffer(callSiteListSize * 5 + 12);
            for (int i = 0; i < callSiteListSize; ++i) {
                final String name = this.callSiteList.get(i);
                final CallType callType = this.callTypeList.get(i);
                if (i > 0) {
                    descriptor.append('\uffff');
                }
                if (callType.equals(CallType.NORMAL)) {
                    descriptor.append(name).append("\uffffN");
                }
                else if (callType.equals(CallType.FUNCTIONAL)) {
                    descriptor.append(name).append("\uffffF");
                }
                else if (callType.equals(CallType.VARIABLE)) {
                    descriptor.append(name).append("\uffffV");
                }
                else if (callType.equals(CallType.SUPER)) {
                    descriptor.append("super").append("\uffffS");
                }
            }
            descriptor.append('\uffff');
            descriptor.append((char)this.scopeCount);
            descriptor.append((char)this.inheritedSymbolCount);
            descriptor.append((char)this.inheritedFixnumCount);
            descriptor.append((char)this.inheritedFloatCount);
            descriptor.append((char)this.inheritedConstantCount);
            descriptor.append((char)this.inheritedRegexpCount);
            descriptor.append((char)this.inheritedBigIntegerCount);
            descriptor.append((char)this.inheritedVariableReaderCount);
            descriptor.append((char)this.inheritedVariableWriterCount);
            descriptor.append((char)this.inheritedBlockBodyCount);
            descriptor.append((char)this.inheritedBlockCallbackCount);
            descriptor.append((char)this.inheritedMethodCount);
            descriptor.append((char)this.inheritedStringCount);
            descriptor.append((char)this.inheritedEncodingCount);
            initMethod.aload(0);
            initMethod.ldc(descriptor.toString());
            initMethod.invokevirtual(CodegenUtils.p(AbstractScript.class), "initFromDescriptor", CodegenUtils.sig(Void.TYPE, String.class));
            if (this.inheritedEncodingCount > 0) {
                for (final Map.Entry<String, Integer> entry : this.encodingIndices.entrySet()) {
                    initMethod.aload(0);
                    initMethod.ldc(entry.getValue());
                    initMethod.ldc(entry.getKey());
                    initMethod.invokevirtual(CodegenUtils.p(AbstractScript.class), "setEncoding", CodegenUtils.sig(Void.TYPE, Integer.TYPE, String.class));
                }
            }
            if (this.inheritedStringCount > 0) {
                for (final Map.Entry<String, Integer> entry : this.stringIndices.entrySet()) {
                    initMethod.aload(0);
                    initMethod.ldc(entry.getValue());
                    initMethod.ldc(entry.getKey());
                    this.loadEncoding(initMethod, this.stringEncodings.get(entry.getKey()));
                    initMethod.invokevirtual(CodegenUtils.p(AbstractScript.class), "setByteList", CodegenUtils.sig(Void.TYPE, Integer.TYPE, String.class, Encoding.class));
                }
            }
        }
    }
    
    private void ensureRuntimeCacheInited(final SkinnyMethodAdapter initMethod) {
        if (!this.runtimeCacheInited) {
            initMethod.aload(0);
            initMethod.newobj(CodegenUtils.p(RuntimeCache.class));
            initMethod.dup();
            initMethod.invokespecial(CodegenUtils.p(RuntimeCache.class), "<init>", CodegenUtils.sig(Void.TYPE, new Class[0]));
            initMethod.putfield(CodegenUtils.p(AbstractScript.class), "runtimeCache", CodegenUtils.ci(RuntimeCache.class));
            this.runtimeCacheInited = true;
        }
    }
}
