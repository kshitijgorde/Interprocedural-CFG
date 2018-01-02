// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast.executable;

import org.jruby.internal.runtime.methods.UndefinedMethod;
import org.jruby.runtime.CallType;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.RubyModule;
import java.util.Arrays;
import org.jruby.runtime.MethodIndex;
import org.jruby.common.IRubyWarnings;
import org.jruby.util.RegexpOptions;
import org.jruby.RubyString;
import org.jruby.Ruby;
import org.jruby.parser.LocalStaticScope;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyClass;
import java.math.BigInteger;
import org.jruby.RubyRegexp;
import org.jruby.RubyFloat;
import org.jruby.RubyFixnum;
import org.jcodings.Encoding;
import org.jruby.util.ByteList;
import org.jruby.RubySymbol;
import org.jruby.runtime.CompiledBlockCallback;
import org.jruby.runtime.BlockBody;
import org.jruby.runtime.callsite.CacheEntry;
import org.jruby.runtime.CallSite;
import org.jruby.parser.StaticScope;

public class RuntimeCache
{
    private static final int SCOPE = 0;
    private static final int SYMBOL = 1;
    private static final int FIXNUM = 2;
    private static final int FLOAT = 3;
    private static final int CONSTANT = 4;
    private static final int REGEXP = 5;
    private static final int BIGINTEGER = 6;
    private static final int VARIABLEREADER = 7;
    private static final int VARIABLEWRITER = 8;
    private static final int BLOCKBODY = 9;
    private static final int BLOCKCALLBACK = 10;
    private static final int METHOD = 11;
    private static final int STRING = 12;
    private static final int ENCODING = 13;
    private static final StaticScope[] EMPTY_SCOPES;
    public StaticScope[] scopes;
    private static final CallSite[] EMPTY_CALLSITES;
    public CallSite[] callSites;
    private static final CacheEntry[] EMPTY_CACHEENTRIES;
    public CacheEntry[] methodCache;
    private static final BlockBody[] EMPTY_BLOCKBODIES;
    public BlockBody[] blockBodies;
    private static final CompiledBlockCallback[] EMPTY_COMPILEDBLOCKCALLBACKS;
    public CompiledBlockCallback[] blockCallbacks;
    private static final RubySymbol[] EMPTY_RUBYSYMBOLS;
    public RubySymbol[] symbols;
    private static final ByteList[] EMPTY_BYTELISTS;
    public ByteList[] byteLists;
    private static final Encoding[] EMPTY_ENCODINGS;
    public Encoding[] encodings;
    private static final RubyFixnum[] EMPTY_FIXNUMS;
    public RubyFixnum[] fixnums;
    private static final RubyFloat[] EMPTY_FLOATS;
    public RubyFloat[] floats;
    private static final RubyRegexp[] EMPTY_RUBYREGEXPS;
    public RubyRegexp[] regexps;
    private static final BigInteger[] EMPTY_BIGINTEGERS;
    public BigInteger[] bigIntegers;
    private static final RubyClass.VariableAccessor[] EMPTY_VARIABLE_ACCESSORS;
    public RubyClass.VariableAccessor[] variableReaders;
    public RubyClass.VariableAccessor[] variableWriters;
    public IRubyObject[] constants;
    private static final int[] EMPTY_INTS;
    public int[] constantGenerations;
    public int[] constantTargetHashes;
    
    public RuntimeCache() {
        this.scopes = RuntimeCache.EMPTY_SCOPES;
        this.callSites = RuntimeCache.EMPTY_CALLSITES;
        this.methodCache = RuntimeCache.EMPTY_CACHEENTRIES;
        this.blockBodies = RuntimeCache.EMPTY_BLOCKBODIES;
        this.blockCallbacks = RuntimeCache.EMPTY_COMPILEDBLOCKCALLBACKS;
        this.symbols = RuntimeCache.EMPTY_RUBYSYMBOLS;
        this.byteLists = RuntimeCache.EMPTY_BYTELISTS;
        this.encodings = RuntimeCache.EMPTY_ENCODINGS;
        this.fixnums = RuntimeCache.EMPTY_FIXNUMS;
        this.floats = RuntimeCache.EMPTY_FLOATS;
        this.regexps = RuntimeCache.EMPTY_RUBYREGEXPS;
        this.bigIntegers = RuntimeCache.EMPTY_BIGINTEGERS;
        this.variableReaders = RuntimeCache.EMPTY_VARIABLE_ACCESSORS;
        this.variableWriters = RuntimeCache.EMPTY_VARIABLE_ACCESSORS;
        this.constants = IRubyObject.NULL_ARRAY;
        this.constantGenerations = RuntimeCache.EMPTY_INTS;
        this.constantTargetHashes = RuntimeCache.EMPTY_INTS;
    }
    
    public final StaticScope getScope(final ThreadContext context, final String varNamesDescriptor, final int index) {
        StaticScope scope = this.scopes[index];
        if (scope == null) {
            final String[] scopeData = varNamesDescriptor.split(",");
            final String[] varNames = scopeData[0].split(";");
            for (int i = 0; i < varNames.length; ++i) {
                varNames[i] = varNames[i].intern();
            }
            final StaticScope[] scopes = this.scopes;
            final StaticScope staticScope = new LocalStaticScope(context.getCurrentScope().getStaticScope(), varNames);
            scopes[index] = staticScope;
            scope = staticScope;
        }
        return scope;
    }
    
    public final CallSite getCallSite(final int index) {
        return this.callSites[index];
    }
    
    public final BlockBody getBlockBody(final Object scriptObject, final ThreadContext context, final int index, final String descriptor) {
        final BlockBody body = this.blockBodies[index];
        if (body == null) {
            return this.createBlockBody(scriptObject, context, index, descriptor);
        }
        return body;
    }
    
    public final BlockBody getBlockBody19(final Object scriptObject, final ThreadContext context, final int index, final String descriptor) {
        final BlockBody body = this.blockBodies[index];
        if (body == null) {
            return this.createBlockBody19(scriptObject, context, index, descriptor);
        }
        return body;
    }
    
    public final CompiledBlockCallback getBlockCallback(final Object scriptObject, final Ruby runtime, final int index, final String method) {
        final CompiledBlockCallback callback = this.blockCallbacks[index];
        if (callback == null) {
            return this.createCompiledBlockCallback(scriptObject, runtime, index, method);
        }
        return callback;
    }
    
    public final RubySymbol getSymbol(final Ruby runtime, final int index, final String name) {
        final RubySymbol symbol = this.symbols[index];
        if (symbol == null) {
            return this.symbols[index] = runtime.newSymbol(name);
        }
        return symbol;
    }
    
    public final RubyString getString(final Ruby runtime, final int index, final int codeRange) {
        return RubyString.newStringShared(runtime, this.getByteList(index), codeRange);
    }
    
    public final ByteList getByteList(final int index) {
        return this.byteLists[index];
    }
    
    public final Encoding getEncoding(final int index) {
        return this.encodings[index];
    }
    
    public final RubyFixnum getFixnum(final Ruby runtime, final int index, final int value) {
        final RubyFixnum fixnum = this.fixnums[index];
        if (fixnum == null) {
            return this.fixnums[index] = RubyFixnum.newFixnum(runtime, value);
        }
        return fixnum;
    }
    
    public final RubyFixnum getFixnum(final Ruby runtime, final int index, final long value) {
        final RubyFixnum fixnum = this.fixnums[index];
        if (fixnum == null) {
            return this.fixnums[index] = RubyFixnum.newFixnum(runtime, value);
        }
        return fixnum;
    }
    
    public final RubyFloat getFloat(final Ruby runtime, final int index, final double value) {
        final RubyFloat flote = this.floats[index];
        if (flote == null) {
            return this.floats[index] = RubyFloat.newFloat(runtime, value);
        }
        return flote;
    }
    
    public final RubyRegexp getRegexp(final Ruby runtime, final int index, final ByteList pattern, final int options) {
        RubyRegexp regexp = this.regexps[index];
        if (regexp == null || runtime.getKCode() != regexp.getKCode()) {
            regexp = RubyRegexp.newRegexp(runtime, pattern, RegexpOptions.fromEmbeddedOptions(options));
            regexp.setLiteral();
            this.regexps[index] = regexp;
        }
        return regexp;
    }
    
    public final RubyRegexp getRegexp(final int index) {
        return this.regexps[index];
    }
    
    public final RubyRegexp cacheRegexp(final int index, final RubyString pattern, final int options) {
        RubyRegexp regexp = this.regexps[index];
        final Ruby runtime = pattern.getRuntime();
        if (regexp == null || runtime.getKCode() != regexp.getKCode()) {
            regexp = RubyRegexp.newRegexp(runtime, pattern.getByteList(), RegexpOptions.fromEmbeddedOptions(options));
            this.regexps[index] = regexp;
        }
        return regexp;
    }
    
    public final BigInteger getBigInteger(final Ruby runtime, final int index, final String pattern) {
        final BigInteger bigint = this.bigIntegers[index];
        if (bigint == null) {
            return this.bigIntegers[index] = new BigInteger(pattern, 16);
        }
        return bigint;
    }
    
    public final IRubyObject getVariable(final Ruby runtime, final int index, final String name, final IRubyObject object) {
        RubyClass.VariableAccessor variableAccessor = this.variableReaders[index];
        final RubyClass cls = object.getMetaClass().getRealClass();
        if (variableAccessor.getClassId() != cls.hashCode()) {
            variableAccessor = (this.variableReaders[index] = cls.getVariableAccessorForRead(name));
        }
        final IRubyObject value = (IRubyObject)variableAccessor.get(object);
        if (value != null) {
            return value;
        }
        if (runtime.isVerbose()) {
            this.warnAboutUninitializedIvar(runtime, name);
        }
        return runtime.getNil();
    }
    
    private void warnAboutUninitializedIvar(final Ruby runtime, final String name) {
        runtime.getWarnings().warning(IRubyWarnings.ID.IVAR_NOT_INITIALIZED, "instance variable " + name + " not initialized", new Object[0]);
    }
    
    public final IRubyObject setVariable(final Ruby runtime, final int index, final String name, final IRubyObject object, final IRubyObject value) {
        RubyClass.VariableAccessor variableAccessor = this.variableWriters[index];
        final RubyClass cls = object.getMetaClass().getRealClass();
        if (variableAccessor.getClassId() != cls.hashCode()) {
            variableAccessor = (this.variableWriters[index] = cls.getVariableAccessorForWrite(name));
        }
        variableAccessor.set(object, value);
        return value;
    }
    
    public final void initScopes(final int size) {
        this.scopes = new StaticScope[size];
    }
    
    public final void initCallSites(final int size) {
        this.callSites = new CallSite[size];
    }
    
    public final void initFromDescriptor(final String descriptor) {
        final String[] pieces = descriptor.split("\uffff");
        final CallSite[] sites = new CallSite[pieces.length - 0];
        if (pieces[0].length() != 0) {
            for (int i = 0; i < pieces.length - 1; i += 2) {
                switch (pieces[i + 1].charAt(0)) {
                    case 'N': {
                        sites[i / 2] = MethodIndex.getCallSite(pieces[i]);
                        break;
                    }
                    case 'F': {
                        sites[i / 2] = MethodIndex.getFunctionalCallSite(pieces[i]);
                        break;
                    }
                    case 'V': {
                        sites[i / 2] = MethodIndex.getVariableCallSite(pieces[i]);
                        break;
                    }
                    case 'S': {
                        sites[i / 2] = MethodIndex.getSuperCallSite();
                        break;
                    }
                    default: {
                        throw new RuntimeException("Unknown call type: " + pieces[i + 1] + " for method " + pieces[i]);
                    }
                }
            }
            this.callSites = sites;
        }
        this.initOthers(pieces[pieces.length - 1]);
    }
    
    public final void initOthers(final String descriptor) {
        final int scopeCount = getDescriptorValue(descriptor, 0);
        if (scopeCount > 0) {
            this.initScopes(scopeCount);
        }
        final int symbolCount = getDescriptorValue(descriptor, 1);
        if (symbolCount > 0) {
            this.initSymbols(symbolCount);
        }
        final int fixnumCount = getDescriptorValue(descriptor, 2);
        if (fixnumCount > 0) {
            this.initFixnums(fixnumCount);
        }
        final int floatCount = getDescriptorValue(descriptor, 3);
        if (floatCount > 0) {
            this.initFloats(floatCount);
        }
        final int constantCount = getDescriptorValue(descriptor, 4);
        if (constantCount > 0) {
            this.initConstants(constantCount);
        }
        final int regexpCount = getDescriptorValue(descriptor, 5);
        if (regexpCount > 0) {
            this.initRegexps(regexpCount);
        }
        final int bigIntegerCount = getDescriptorValue(descriptor, 6);
        if (bigIntegerCount > 0) {
            this.initBigIntegers(bigIntegerCount);
        }
        final int variableReaderCount = getDescriptorValue(descriptor, 7);
        if (variableReaderCount > 0) {
            this.initVariableReaders(variableReaderCount);
        }
        final int variableWriterCount = getDescriptorValue(descriptor, 8);
        if (variableWriterCount > 0) {
            this.initVariableWriters(variableWriterCount);
        }
        final int blockBodyCount = getDescriptorValue(descriptor, 9);
        if (blockBodyCount > 0) {
            this.initBlockBodies(blockBodyCount);
        }
        final int blockCallbackCount = getDescriptorValue(descriptor, 10);
        if (blockCallbackCount > 0) {
            this.initBlockCallbacks(blockCallbackCount);
        }
        final int methodCount = getDescriptorValue(descriptor, 11);
        if (methodCount > 0) {
            this.initMethodCache(methodCount);
        }
        final int stringCount = getDescriptorValue(descriptor, 12);
        if (stringCount > 0) {
            this.initStrings(stringCount);
        }
        final int encodingCount = getDescriptorValue(descriptor, 13);
        if (encodingCount > 0) {
            this.initEncodings(encodingCount);
        }
    }
    
    private static int getDescriptorValue(final String descriptor, final int type) {
        return descriptor.charAt(type);
    }
    
    public final void initBlockBodies(final int size) {
        this.blockBodies = new BlockBody[size];
    }
    
    public final void initBlockCallbacks(final int size) {
        this.blockCallbacks = new CompiledBlockCallback[size];
    }
    
    public final void initSymbols(final int size) {
        this.symbols = new RubySymbol[size];
    }
    
    public final ByteList[] initStrings(final int size) {
        return this.byteLists = new ByteList[size];
    }
    
    public final Encoding[] initEncodings(final int size) {
        return this.encodings = new Encoding[size];
    }
    
    public final void initFixnums(final int size) {
        this.fixnums = new RubyFixnum[size];
    }
    
    public final void initFloats(final int size) {
        this.floats = new RubyFloat[size];
    }
    
    public final void initRegexps(final int size) {
        this.regexps = new RubyRegexp[size];
    }
    
    public final void initBigIntegers(final int size) {
        this.bigIntegers = new BigInteger[size];
    }
    
    public final void initConstants(final int size) {
        this.constants = new IRubyObject[size];
        this.constantTargetHashes = new int[size];
        Arrays.fill(this.constantGenerations = new int[size], -1);
        Arrays.fill(this.constantTargetHashes, -1);
    }
    
    public final void initVariableReaders(final int size) {
        Arrays.fill(this.variableReaders = new RubyClass.VariableAccessor[size], RubyClass.VariableAccessor.DUMMY_ACCESSOR);
    }
    
    public final void initVariableWriters(final int size) {
        Arrays.fill(this.variableWriters = new RubyClass.VariableAccessor[size], RubyClass.VariableAccessor.DUMMY_ACCESSOR);
    }
    
    public final void initMethodCache(final int size) {
        Arrays.fill(this.methodCache = new CacheEntry[size], CacheEntry.NULL_CACHE);
    }
    
    public final IRubyObject getConstant(final ThreadContext context, final String name, final int index) {
        final IRubyObject value = this.getValue(context, name, index);
        return (value != null) ? value : context.getCurrentScope().getStaticScope().getModule().callMethod(context, "const_missing", context.getRuntime().fastNewSymbol(name));
    }
    
    public IRubyObject getValue(final ThreadContext context, final String name, final int index) {
        final IRubyObject value = this.constants[index];
        return this.isCached(context, value, index) ? value : this.reCache(context, name, index);
    }
    
    private boolean isCached(final ThreadContext context, final IRubyObject value, final int index) {
        return value != null && this.constantGenerations[index] == context.getRuntime().getConstantGeneration();
    }
    
    public IRubyObject reCache(final ThreadContext context, final String name, final int index) {
        final int newGeneration = context.getRuntime().getConstantGeneration();
        final IRubyObject value = context.getConstant(name);
        this.constants[index] = value;
        if (value != null) {
            this.constantGenerations[index] = newGeneration;
        }
        return value;
    }
    
    public final IRubyObject getConstantFrom(final RubyModule target, final ThreadContext context, final String name, final int index) {
        final IRubyObject value = this.getValueFrom(target, context, name, index);
        return (value != null) ? value : target.fastGetConstantFromConstMissing(name);
    }
    
    public IRubyObject getValueFrom(final RubyModule target, final ThreadContext context, final String name, final int index) {
        final IRubyObject value = this.constants[index];
        return this.isCachedFrom(target, context, value, index) ? value : this.reCacheFrom(target, context, name, index);
    }
    
    private boolean isCachedFrom(final RubyModule target, final ThreadContext context, final IRubyObject value, final int index) {
        return value != null && this.constantGenerations[index] == context.getRuntime().getConstantGeneration() && this.constantTargetHashes[index] == target.hashCode();
    }
    
    public IRubyObject reCacheFrom(final RubyModule target, final ThreadContext context, final String name, final int index) {
        final int newGeneration = context.getRuntime().getConstantGeneration();
        final IRubyObject value = target.fastGetConstantFromNoConstMissing(name);
        this.constants[index] = value;
        if (value != null) {
            this.constantGenerations[index] = newGeneration;
            this.constantTargetHashes[index] = target.hashCode();
        }
        return value;
    }
    
    private BlockBody createBlockBody(final Object scriptObject, final ThreadContext context, final int index, final String descriptor) throws NumberFormatException {
        final BlockBody body = RuntimeHelpers.createCompiledBlockBody(context, scriptObject, descriptor);
        return this.blockBodies[index] = body;
    }
    
    private BlockBody createBlockBody19(final Object scriptObject, final ThreadContext context, final int index, final String descriptor) throws NumberFormatException {
        final BlockBody body = RuntimeHelpers.createCompiledBlockBody19(context, scriptObject, descriptor);
        return this.blockBodies[index] = body;
    }
    
    private CompiledBlockCallback createCompiledBlockCallback(final Object scriptObject, final Ruby runtime, final int index, final String method) {
        final CompiledBlockCallback callback = RuntimeHelpers.createBlockCallback(runtime, scriptObject, method, "(internal)", -1);
        return this.blockCallbacks[index] = callback;
    }
    
    public DynamicMethod getMethod(final ThreadContext context, final RubyClass selfType, final int index, final String methodName) {
        final CacheEntry myCache = this.getCacheEntry(index);
        if (CacheEntry.typeOk(myCache, selfType)) {
            return myCache.method;
        }
        return this.cacheAndGet(context, selfType, index, methodName);
    }
    
    public DynamicMethod getMethod(final ThreadContext context, final IRubyObject self, final int index, final String methodName) {
        return this.getMethod(context, pollAndGetClass(context, self), index, methodName);
    }
    
    private DynamicMethod cacheAndGet(final ThreadContext context, final RubyClass selfType, final int index, final String methodName) {
        final CacheEntry entry = selfType.searchWithCache(methodName);
        final DynamicMethod method = entry.method;
        if (method.isUndefined()) {
            return RuntimeHelpers.selectMethodMissing(context, selfType, method.getVisibility(), methodName, CallType.FUNCTIONAL);
        }
        this.methodCache[index] = entry;
        return method;
    }
    
    public DynamicMethod searchWithCache(final RubyClass clazz, final int index, final String name1) {
        final CacheEntry entry = clazz.searchWithCache(name1);
        final DynamicMethod method = entry.method;
        if (entry.method == UndefinedMethod.INSTANCE) {
            return RuntimeHelpers.selectMethodMissing(clazz, method.getVisibility(), name1, CallType.FUNCTIONAL);
        }
        this.methodCache[index] = entry;
        return method;
    }
    
    public DynamicMethod searchWithCache(final RubyClass clazz, final int index, final String name1, final String name2) {
        final CacheEntry entry = clazz.searchWithCache(name1);
        final DynamicMethod method = entry.method;
        if (entry.method == UndefinedMethod.INSTANCE) {
            return this.searchWithCache(clazz, index, name2);
        }
        this.methodCache[index] = entry;
        return method;
    }
    
    public DynamicMethod searchWithCache(final RubyClass clazz, final int index, final String name1, final String name2, final String name3) {
        final CacheEntry entry = clazz.searchWithCache(name1);
        final DynamicMethod method = entry.method;
        if (entry.method == UndefinedMethod.INSTANCE) {
            return this.searchWithCache(clazz, index, name2, name3);
        }
        this.methodCache[index] = entry;
        return method;
    }
    
    public DynamicMethod searchWithCache(final RubyClass clazz, final int index, final String name1, final String name2, final String name3, final String name4) {
        final CacheEntry entry = clazz.searchWithCache(name1);
        final DynamicMethod method = entry.method;
        if (entry.method == UndefinedMethod.INSTANCE) {
            return this.searchWithCache(clazz, index, name2, name3, name4);
        }
        this.methodCache[index] = entry;
        return method;
    }
    
    public DynamicMethod searchWithCache(final RubyClass clazz, final int index, final String name1, final String name2, final String name3, final String name4, final String name5) {
        final CacheEntry entry = clazz.searchWithCache(name1);
        final DynamicMethod method = entry.method;
        if (entry.method == UndefinedMethod.INSTANCE) {
            return this.searchWithCache(clazz, index, name2, name3, name4, name5);
        }
        this.methodCache[index] = entry;
        return method;
    }
    
    public DynamicMethod searchWithCache(final RubyClass clazz, final int index, final String name1, final String name2, final String name3, final String name4, final String name5, final String name6) {
        final CacheEntry entry = clazz.searchWithCache(name1);
        final DynamicMethod method = entry.method;
        if (entry.method == UndefinedMethod.INSTANCE) {
            return this.searchWithCache(clazz, index, name2, name3, name4, name5, name6);
        }
        this.methodCache[index] = entry;
        return method;
    }
    
    public DynamicMethod searchWithCache(final RubyClass clazz, final int index, final String name1, final String name2, final String name3, final String name4, final String name5, final String name6, final String name7) {
        final CacheEntry entry = clazz.searchWithCache(name1);
        final DynamicMethod method = entry.method;
        if (entry.method == UndefinedMethod.INSTANCE) {
            return this.searchWithCache(clazz, index, name2, name3, name4, name5, name6, name7);
        }
        this.methodCache[index] = entry;
        return method;
    }
    
    public DynamicMethod searchWithCache(final RubyClass clazz, final int index, final String name1, final String name2, final String name3, final String name4, final String name5, final String name6, final String name7, final String name8) {
        final CacheEntry entry = clazz.searchWithCache(name1);
        final DynamicMethod method = entry.method;
        if (entry.method == UndefinedMethod.INSTANCE) {
            return this.searchWithCache(clazz, index, name2, name3, name4, name5, name6, name7, name8);
        }
        this.methodCache[index] = entry;
        return method;
    }
    
    public DynamicMethod searchWithCache(final IRubyObject obj, final int index, final String name1) {
        final CacheEntry myCache = this.getCacheEntry(index);
        if (CacheEntry.typeOk(myCache, obj.getMetaClass())) {
            return myCache.method;
        }
        return this.searchWithCache(obj.getMetaClass(), index, name1);
    }
    
    public DynamicMethod searchWithCache(final IRubyObject obj, final int index, final String name1, final String name2) {
        final CacheEntry myCache = this.getCacheEntry(index);
        if (CacheEntry.typeOk(myCache, obj.getMetaClass())) {
            return myCache.method;
        }
        return this.searchWithCache(obj.getMetaClass(), index, name1, name2);
    }
    
    public DynamicMethod searchWithCache(final IRubyObject obj, final int index, final String name1, final String name2, final String name3) {
        final CacheEntry myCache = this.getCacheEntry(index);
        if (CacheEntry.typeOk(myCache, obj.getMetaClass())) {
            return myCache.method;
        }
        return this.searchWithCache(obj.getMetaClass(), index, name1, name2, name3);
    }
    
    public DynamicMethod searchWithCache(final IRubyObject obj, final int index, final String name1, final String name2, final String name3, final String name4) {
        final CacheEntry myCache = this.getCacheEntry(index);
        if (CacheEntry.typeOk(myCache, obj.getMetaClass())) {
            return myCache.method;
        }
        return this.searchWithCache(obj.getMetaClass(), index, name1, name2, name3, name4);
    }
    
    public DynamicMethod searchWithCache(final IRubyObject obj, final int index, final String name1, final String name2, final String name3, final String name4, final String name5) {
        final CacheEntry myCache = this.getCacheEntry(index);
        if (CacheEntry.typeOk(myCache, obj.getMetaClass())) {
            return myCache.method;
        }
        return this.searchWithCache(obj.getMetaClass(), index, name1, name2, name3, name4, name5);
    }
    
    public DynamicMethod searchWithCache(final IRubyObject obj, final int index, final String name1, final String name2, final String name3, final String name4, final String name5, final String name6) {
        final CacheEntry myCache = this.getCacheEntry(index);
        if (CacheEntry.typeOk(myCache, obj.getMetaClass())) {
            return myCache.method;
        }
        return this.searchWithCache(obj.getMetaClass(), index, name1, name2, name3, name4, name5, name6);
    }
    
    public DynamicMethod searchWithCache(final IRubyObject obj, final int index, final String name1, final String name2, final String name3, final String name4, final String name5, final String name6, final String name7) {
        final CacheEntry myCache = this.getCacheEntry(index);
        if (CacheEntry.typeOk(myCache, obj.getMetaClass())) {
            return myCache.method;
        }
        return this.searchWithCache(obj.getMetaClass(), index, name1, name2, name3, name4, name5, name6, name7);
    }
    
    public DynamicMethod searchWithCache(final IRubyObject obj, final int index, final String name1, final String name2, final String name3, final String name4, final String name5, final String name6, final String name7, final String name8) {
        final CacheEntry myCache = this.getCacheEntry(index);
        if (CacheEntry.typeOk(myCache, obj.getMetaClass())) {
            return myCache.method;
        }
        return this.searchWithCache(obj.getMetaClass(), index, name1, name2, name3, name4, name5, name6, name7, name8);
    }
    
    private static RubyClass pollAndGetClass(final ThreadContext context, final IRubyObject self) {
        context.callThreadPoll();
        final RubyClass selfType = self.getMetaClass();
        return selfType;
    }
    
    private CacheEntry getCacheEntry(final int index) {
        return this.methodCache[index];
    }
    
    static {
        EMPTY_SCOPES = new StaticScope[0];
        EMPTY_CALLSITES = new CallSite[0];
        EMPTY_CACHEENTRIES = new CacheEntry[0];
        EMPTY_BLOCKBODIES = new BlockBody[0];
        EMPTY_COMPILEDBLOCKCALLBACKS = new CompiledBlockCallback[0];
        EMPTY_RUBYSYMBOLS = new RubySymbol[0];
        EMPTY_BYTELISTS = new ByteList[0];
        EMPTY_ENCODINGS = new Encoding[0];
        EMPTY_FIXNUMS = new RubyFixnum[0];
        EMPTY_FLOATS = new RubyFloat[0];
        EMPTY_RUBYREGEXPS = new RubyRegexp[0];
        EMPTY_BIGINTEGERS = new BigInteger[0];
        EMPTY_VARIABLE_ACCESSORS = new RubyClass.VariableAccessor[0];
        EMPTY_INTS = new int[0];
    }
}
