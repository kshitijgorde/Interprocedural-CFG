// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport.util;

import org.jruby.runtime.MethodIndex;
import java.util.Iterator;
import org.jruby.ast.UnnamedRestArgNode;
import org.jruby.ast.DAsgnNode;
import org.jruby.ast.LocalAsgnNode;
import org.jruby.ast.OptArgNode;
import org.jruby.ast.ArgumentNode;
import org.jruby.ast.MultipleAsgn19Node;
import org.jruby.ast.ArgsNode;
import java.util.ArrayList;
import java.util.StringTokenizer;
import org.jruby.RubyBasicObject;
import org.jruby.ast.DSymbolNode;
import org.jruby.ast.LiteralNode;
import org.jruby.runtime.RubyEvent;
import org.jruby.runtime.Interpreted19Block;
import org.jruby.runtime.InterpretedBlock;
import org.jruby.ast.IterNode;
import org.jruby.ast.Node;
import org.jruby.common.IRubyWarnings;
import org.jruby.RubyInstanceConfig;
import org.jruby.MetaClass;
import org.jruby.internal.runtime.methods.WrapperMethod;
import org.jruby.parser.LocalStaticScope;
import org.jruby.RubyString;
import org.jruby.util.ByteList;
import org.jruby.RubyHash;
import org.jruby.RubyBoolean;
import org.jruby.util.unsafe.UnsafeFactory;
import org.jruby.exceptions.Unrescuable;
import org.jruby.javasupport.JavaUtil;
import org.jruby.javasupport.JavaClass;
import org.jruby.RubyRegexp;
import org.jruby.RubyMatchData;
import org.jruby.util.TypeConverter;
import org.jruby.RubyProc;
import org.jruby.RubyException;
import org.jruby.exceptions.JumpException;
import org.jruby.RubyLocalJumpError;
import org.jruby.exceptions.RaiseException;
import org.jruby.evaluator.ASTInterpreter;
import org.jruby.ast.util.ArgsUtil;
import org.jruby.RubyArray;
import org.jruby.internal.runtime.methods.UndefinedMethod;
import org.jruby.RubyKernel;
import org.jruby.runtime.CallType;
import org.jruby.RubySymbol;
import org.jruby.RubyFixnum;
import org.jruby.RubyClass;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.lexer.yacc.SimpleSourcePosition;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.runtime.CompiledSharedScopeBlock;
import org.jruby.runtime.DynamicScope;
import org.jruby.runtime.Block;
import org.jruby.runtime.CompiledBlock19;
import org.jruby.runtime.CompiledBlockLight19;
import org.jruby.runtime.CompiledBlock;
import org.jruby.runtime.CompiledBlockLight;
import org.jruby.runtime.Arity;
import org.jruby.parser.BlockStaticScope;
import org.jruby.runtime.BlockBody;
import org.jruby.compiler.ASTInspector;
import org.jruby.ast.NodeType;
import org.jruby.parser.StaticScope;
import org.jruby.runtime.CompiledBlockCallback19;
import org.jruby.runtime.MethodFactory;
import org.jruby.runtime.CompiledBlockCallback;
import org.jruby.Ruby;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.CallSite;
import org.jruby.runtime.builtin.IRubyObject;

public class RuntimeHelpers
{
    public static final int MAX_SPECIFIC_ARITY_OBJECT_ARRAY = 5;
    public static final int MAX_SPECIFIC_ARITY_HASH = 3;
    
    public static CallSite selectAttrAsgnCallSite(final IRubyObject receiver, final IRubyObject self, final CallSite normalSite, final CallSite variableSite) {
        if (receiver == self) {
            return variableSite;
        }
        return normalSite;
    }
    
    public static IRubyObject doAttrAsgn(final IRubyObject receiver, final CallSite callSite, final IRubyObject value, final ThreadContext context, final IRubyObject caller) {
        callSite.call(context, caller, receiver, value);
        return value;
    }
    
    public static IRubyObject doAttrAsgn(final IRubyObject receiver, final CallSite callSite, final IRubyObject arg0, final IRubyObject value, final ThreadContext context, final IRubyObject caller) {
        callSite.call(context, caller, receiver, arg0, value);
        return value;
    }
    
    public static IRubyObject doAttrAsgn(final IRubyObject receiver, final CallSite callSite, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject value, final ThreadContext context, final IRubyObject caller) {
        callSite.call(context, caller, receiver, arg0, arg1, value);
        return value;
    }
    
    public static IRubyObject doAttrAsgn(final IRubyObject receiver, final CallSite callSite, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject value, final ThreadContext context, final IRubyObject caller) {
        callSite.call(context, caller, receiver, arg0, arg1, arg2, value);
        return value;
    }
    
    public static IRubyObject doAttrAsgn(final IRubyObject receiver, final CallSite callSite, final IRubyObject[] args, final ThreadContext context, final IRubyObject caller) {
        callSite.call(context, caller, receiver, args);
        return args[args.length - 1];
    }
    
    public static IRubyObject doAttrAsgn(final IRubyObject receiver, final CallSite callSite, final IRubyObject[] args, final IRubyObject value, final ThreadContext context, final IRubyObject caller) {
        final IRubyObject[] newArgs = new IRubyObject[args.length + 1];
        System.arraycopy(args, 0, newArgs, 0, args.length);
        newArgs[args.length] = value;
        callSite.call(context, caller, receiver, newArgs);
        return value;
    }
    
    public static boolean invokeEqqForCaseWhen(final CallSite callSite, final ThreadContext context, final IRubyObject caller, final IRubyObject arg, final IRubyObject[] receivers) {
        for (int i = 0; i < receivers.length; ++i) {
            final IRubyObject receiver = receivers[i];
            if (invokeEqqForCaseWhen(callSite, context, caller, arg, receiver)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean invokeEqqForCaseWhen(final CallSite callSite, final ThreadContext context, final IRubyObject caller, final IRubyObject arg, final IRubyObject receiver) {
        final IRubyObject result = callSite.call(context, caller, receiver, arg);
        return result.isTrue();
    }
    
    public static boolean invokeEqqForCaseWhen(final CallSite callSite, final ThreadContext context, final IRubyObject caller, final IRubyObject arg, final IRubyObject receiver0, final IRubyObject receiver1) {
        final IRubyObject result = callSite.call(context, caller, receiver0, arg);
        return result.isTrue() || invokeEqqForCaseWhen(callSite, context, caller, arg, receiver1);
    }
    
    public static boolean invokeEqqForCaseWhen(final CallSite callSite, final ThreadContext context, final IRubyObject caller, final IRubyObject arg, final IRubyObject receiver0, final IRubyObject receiver1, final IRubyObject receiver2) {
        final IRubyObject result = callSite.call(context, caller, receiver0, arg);
        return result.isTrue() || invokeEqqForCaseWhen(callSite, context, caller, arg, receiver1, receiver2);
    }
    
    public static boolean areAnyTrueForCaselessWhen(final IRubyObject[] receivers) {
        for (int i = 0; i < receivers.length; ++i) {
            if (receivers[i].isTrue()) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean invokeEqqForCaselessWhen(final IRubyObject receiver) {
        return receiver.isTrue();
    }
    
    public static boolean invokeEqqForCaselessWhen(final IRubyObject receiver0, final IRubyObject receiver1) {
        return receiver0.isTrue() || receiver1.isTrue();
    }
    
    public static boolean invokeEqqForCaselessWhen(final IRubyObject receiver0, final IRubyObject receiver1, final IRubyObject receiver2) {
        return receiver0.isTrue() || receiver1.isTrue() || receiver2.isTrue();
    }
    
    public static CompiledBlockCallback createBlockCallback(final Ruby runtime, final Object scriptObject, final String closureMethod, final String file, final int line) {
        final Class scriptClass = scriptObject.getClass();
        final ClassLoader scriptClassLoader = scriptClass.getClassLoader();
        final MethodFactory factory = MethodFactory.createFactory(scriptClassLoader);
        return factory.getBlockCallback(closureMethod, file, line, scriptObject);
    }
    
    public static CompiledBlockCallback19 createBlockCallback19(final Ruby runtime, final Object scriptObject, final String closureMethod, final String file, final int line) {
        final Class scriptClass = scriptObject.getClass();
        final ClassLoader scriptClassLoader = scriptClass.getClassLoader();
        final MethodFactory factory = MethodFactory.createFactory(scriptClassLoader);
        return factory.getBlockCallback19(closureMethod, file, line, scriptObject);
    }
    
    public static byte[] createBlockCallbackOffline(final String classPath, final String closureMethod, final String file, final int line) {
        final MethodFactory factory = MethodFactory.createFactory(RuntimeHelpers.class.getClassLoader());
        return factory.getBlockCallbackOffline(closureMethod, file, line, classPath);
    }
    
    public static byte[] createBlockCallback19Offline(final String classPath, final String closureMethod, final String file, final int line) {
        final MethodFactory factory = MethodFactory.createFactory(RuntimeHelpers.class.getClassLoader());
        return factory.getBlockCallback19Offline(closureMethod, file, line, classPath);
    }
    
    public static String buildBlockDescriptor19(final String closureMethod, final int arity, final StaticScope scope, final String file, final int line, final boolean hasMultipleArgsHead, final NodeType argsNodeId, final String parameterList, final ASTInspector inspector) {
        return buildBlockDescriptor(closureMethod, arity, scope, file, line, hasMultipleArgsHead, argsNodeId, inspector) + "," + parameterList;
    }
    
    public static String buildBlockDescriptor(final String closureMethod, final int arity, final StaticScope scope, final String file, final int line, final boolean hasMultipleArgsHead, final NodeType argsNodeId, final ASTInspector inspector) {
        final StringBuffer scopeNames = new StringBuffer();
        for (int i = 0; i < scope.getVariables().length; ++i) {
            if (i != 0) {
                scopeNames.append(';');
            }
            scopeNames.append(scope.getVariables()[i]);
        }
        final String descriptor = closureMethod + ',' + arity + ',' + (Object)scopeNames + ',' + hasMultipleArgsHead + ',' + BlockBody.asArgumentType(argsNodeId) + ',' + file + ',' + line + ',' + (!inspector.hasClosure() && !inspector.hasScopeAwareMethods());
        return descriptor;
    }
    
    public static String[][] parseBlockDescriptor(final String descriptor) {
        final String[] firstSplit = descriptor.split(",");
        String[] secondSplit;
        if (firstSplit[2].length() == 0) {
            secondSplit = new String[0];
        }
        else {
            secondSplit = firstSplit[2].split(";");
            for (int i = 0; i < secondSplit.length; ++i) {
                secondSplit[i] = secondSplit[i].intern();
            }
        }
        return new String[][] { firstSplit, secondSplit };
    }
    
    public static BlockBody createCompiledBlockBody(final ThreadContext context, final Object scriptObject, final String descriptor) {
        final String[][] splitDesc = parseBlockDescriptor(descriptor);
        final String[] firstSplit = splitDesc[0];
        final String[] secondSplit = splitDesc[1];
        return createCompiledBlockBody(context, scriptObject, firstSplit[0], Integer.parseInt(firstSplit[1]), secondSplit, Boolean.valueOf(firstSplit[3]), Integer.parseInt(firstSplit[4]), firstSplit[5], Integer.parseInt(firstSplit[6]), Boolean.valueOf(firstSplit[7]));
    }
    
    public static BlockBody createCompiledBlockBody(final ThreadContext context, final Object scriptObject, final String closureMethod, final int arity, final String[] staticScopeNames, final boolean hasMultipleArgsHead, final int argsNodeType, final String file, final int line, final boolean light) {
        final StaticScope staticScope = new BlockStaticScope(context.getCurrentScope().getStaticScope(), staticScopeNames);
        staticScope.determineModule();
        if (light) {
            return CompiledBlockLight.newCompiledBlockLight(Arity.createArity(arity), staticScope, createBlockCallback(context.getRuntime(), scriptObject, closureMethod, file, line), hasMultipleArgsHead, argsNodeType);
        }
        return CompiledBlock.newCompiledBlock(Arity.createArity(arity), staticScope, createBlockCallback(context.getRuntime(), scriptObject, closureMethod, file, line), hasMultipleArgsHead, argsNodeType);
    }
    
    public static BlockBody createCompiledBlockBody19(final ThreadContext context, final Object scriptObject, final String descriptor) {
        final String[][] splitDesc = parseBlockDescriptor(descriptor);
        final String[] firstSplit = splitDesc[0];
        final String[] secondSplit = splitDesc[1];
        return createCompiledBlockBody19(context, scriptObject, firstSplit[0], Integer.parseInt(firstSplit[1]), secondSplit, Boolean.valueOf(firstSplit[3]), Integer.parseInt(firstSplit[4]), firstSplit[5], Integer.parseInt(firstSplit[6]), Boolean.valueOf(firstSplit[7]), firstSplit[8]);
    }
    
    public static BlockBody createCompiledBlockBody19(final ThreadContext context, final Object scriptObject, final String closureMethod, final int arity, final String[] staticScopeNames, final boolean hasMultipleArgsHead, final int argsNodeType, final String file, final int line, final boolean light, final String parameterList) {
        final StaticScope staticScope = new BlockStaticScope(context.getCurrentScope().getStaticScope(), staticScopeNames);
        staticScope.determineModule();
        if (light) {
            return CompiledBlockLight19.newCompiledBlockLight(Arity.createArity(arity), staticScope, createBlockCallback19(context.getRuntime(), scriptObject, closureMethod, file, line), hasMultipleArgsHead, argsNodeType, parameterList.split(";"));
        }
        return CompiledBlock19.newCompiledBlock(Arity.createArity(arity), staticScope, createBlockCallback19(context.getRuntime(), scriptObject, closureMethod, file, line), hasMultipleArgsHead, argsNodeType, parameterList.split(";"));
    }
    
    public static Block createBlock(final ThreadContext context, final IRubyObject self, final BlockBody body) {
        return CompiledBlock.newCompiledClosure(context, self, body);
    }
    
    public static Block createBlock19(final ThreadContext context, final IRubyObject self, final BlockBody body) {
        return CompiledBlock19.newCompiledClosure(context, self, body);
    }
    
    public static IRubyObject runBeginBlock(final ThreadContext context, final IRubyObject self, final String scopeString, final CompiledBlockCallback callback) {
        final StaticScope staticScope = decodeBlockScope(context, scopeString);
        staticScope.determineModule();
        context.preScopedBody(DynamicScope.newDynamicScope(staticScope, context.getCurrentScope()));
        final Block block = CompiledBlock.newCompiledClosure(context, self, Arity.createArity(0), staticScope, callback, false, 0);
        try {
            block.yield(context, null);
        }
        finally {
            context.postScopedBody();
        }
        return context.getRuntime().getNil();
    }
    
    public static Block createSharedScopeBlock(final ThreadContext context, final IRubyObject self, final int arity, final CompiledBlockCallback callback, final boolean hasMultipleArgsHead, final int argsNodeType) {
        return CompiledSharedScopeBlock.newCompiledSharedScopeClosure(context, self, Arity.createArity(arity), context.getCurrentScope(), callback, hasMultipleArgsHead, argsNodeType);
    }
    
    public static IRubyObject def(final ThreadContext context, final IRubyObject self, final Object scriptObject, final String name, final String javaName, final String scopeString, final int arity, final String filename, final int line, final CallConfiguration callConfig, final String parameterDesc) {
        final Class compiledClass = scriptObject.getClass();
        final Ruby runtime = context.getRuntime();
        final RubyModule containingClass = context.getRubyClass();
        final Visibility visibility = context.getCurrentVisibility();
        performNormalMethodChecks(containingClass, runtime, name);
        final StaticScope scope = createScopeForClass(context, scopeString);
        final MethodFactory factory = MethodFactory.createFactory(compiledClass.getClassLoader());
        final DynamicMethod method = constructNormalMethod(factory, javaName, name, containingClass, new SimpleSourcePosition(filename, line), arity, scope, visibility, scriptObject, callConfig, parameterDesc);
        addInstanceMethod(containingClass, name, method, visibility, context, runtime);
        return runtime.getNil();
    }
    
    public static IRubyObject defs(final ThreadContext context, final IRubyObject self, final IRubyObject receiver, final Object scriptObject, final String name, final String javaName, final String scopeString, final int arity, final String filename, final int line, final CallConfiguration callConfig, final String parameterDesc) {
        final Class compiledClass = scriptObject.getClass();
        final Ruby runtime = context.getRuntime();
        final RubyClass rubyClass = performSingletonMethodChecks(runtime, receiver, name);
        final StaticScope scope = createScopeForClass(context, scopeString);
        final MethodFactory factory = MethodFactory.createFactory(compiledClass.getClassLoader());
        final DynamicMethod method = constructSingletonMethod(factory, javaName, rubyClass, new SimpleSourcePosition(filename, line), arity, scope, scriptObject, callConfig, parameterDesc);
        rubyClass.addMethod(name, method);
        callSingletonMethodHook(receiver, context, runtime.fastNewSymbol(name));
        return runtime.getNil();
    }
    
    public static byte[] defOffline(final String name, final String classPath, final String invokerName, final Arity arity, final StaticScope scope, final CallConfiguration callConfig, final String filename, final int line) {
        final MethodFactory factory = MethodFactory.createFactory(RuntimeHelpers.class.getClassLoader());
        final byte[] methodBytes = factory.getCompiledMethodOffline(name, classPath, invokerName, arity, scope, callConfig, filename, line);
        return methodBytes;
    }
    
    public static RubyClass getSingletonClass(final Ruby runtime, final IRubyObject receiver) {
        if (receiver instanceof RubyFixnum || receiver instanceof RubySymbol) {
            throw runtime.newTypeError("no virtual class for " + receiver.getMetaClass().getBaseName());
        }
        if (runtime.getSafeLevel() >= 4 && !receiver.isTaint()) {
            throw runtime.newSecurityError("Insecure: can't extend object.");
        }
        return receiver.getSingletonClass();
    }
    
    public static IRubyObject invokeMethodMissing(final IRubyObject receiver, final String name, final IRubyObject[] args) {
        final ThreadContext context = receiver.getRuntime().getCurrentContext();
        context.setLastCallStatusAndVisibility(CallType.FUNCTIONAL, Visibility.PUBLIC);
        if (name.equals("method_missing")) {
            return RubyKernel.method_missing(context, receiver, args, Block.NULL_BLOCK);
        }
        final IRubyObject[] newArgs = prepareMethodMissingArgs(args, context, name);
        return invoke(context, receiver, "method_missing", newArgs, Block.NULL_BLOCK);
    }
    
    public static IRubyObject callMethodMissing(final ThreadContext context, final IRubyObject receiver, final Visibility visibility, final String name, final CallType callType, final IRubyObject[] args, final Block block) {
        return selectMethodMissing(context, receiver, visibility, name, callType).call(context, receiver, receiver.getMetaClass(), name, args, block);
    }
    
    public static IRubyObject callMethodMissing(final ThreadContext context, final IRubyObject receiver, final Visibility visibility, final String name, final CallType callType, final IRubyObject arg0, final Block block) {
        return selectMethodMissing(context, receiver, visibility, name, callType).call(context, receiver, receiver.getMetaClass(), name, arg0, block);
    }
    
    public static IRubyObject callMethodMissing(final ThreadContext context, final IRubyObject receiver, final Visibility visibility, final String name, final CallType callType, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        return selectMethodMissing(context, receiver, visibility, name, callType).call(context, receiver, receiver.getMetaClass(), name, arg0, arg1, block);
    }
    
    public static IRubyObject callMethodMissing(final ThreadContext context, final IRubyObject receiver, final Visibility visibility, final String name, final CallType callType, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        return selectMethodMissing(context, receiver, visibility, name, callType).call(context, receiver, receiver.getMetaClass(), name, arg0, arg1, arg2, block);
    }
    
    public static IRubyObject callMethodMissing(final ThreadContext context, final IRubyObject receiver, final Visibility visibility, final String name, final CallType callType, final Block block) {
        return selectMethodMissing(context, receiver, visibility, name, callType).call(context, receiver, receiver.getMetaClass(), name, block);
    }
    
    public static DynamicMethod selectMethodMissing(final ThreadContext context, final IRubyObject receiver, final Visibility visibility, final String name, final CallType callType) {
        final Ruby runtime = context.getRuntime();
        if (name.equals("method_missing")) {
            return selectInternalMM(runtime, visibility, callType);
        }
        final DynamicMethod methodMissing = receiver.getMetaClass().searchMethod("method_missing");
        if (methodMissing.isUndefined() || methodMissing == runtime.getDefaultMethodMissing()) {
            return selectInternalMM(runtime, visibility, callType);
        }
        return new MethodMissingMethod(methodMissing, callType);
    }
    
    public static DynamicMethod selectMethodMissing(final ThreadContext context, final RubyClass selfClass, final Visibility visibility, final String name, final CallType callType) {
        final Ruby runtime = context.getRuntime();
        if (name.equals("method_missing")) {
            return selectInternalMM(runtime, visibility, callType);
        }
        final DynamicMethod methodMissing = selfClass.searchMethod("method_missing");
        if (methodMissing.isUndefined() || methodMissing == runtime.getDefaultMethodMissing()) {
            return selectInternalMM(runtime, visibility, callType);
        }
        return new MethodMissingMethod(methodMissing, callType);
    }
    
    public static DynamicMethod selectMethodMissing(final RubyClass selfClass, final Visibility visibility, final String name, final CallType callType) {
        final Ruby runtime = selfClass.getClassRuntime();
        if (name.equals("method_missing")) {
            return selectInternalMM(runtime, visibility, callType);
        }
        final DynamicMethod methodMissing = selfClass.searchMethod("method_missing");
        if (methodMissing.isUndefined() || methodMissing == runtime.getDefaultMethodMissing()) {
            return selectInternalMM(runtime, visibility, callType);
        }
        return new MethodMissingMethod(methodMissing, callType);
    }
    
    private static DynamicMethod selectInternalMM(final Ruby runtime, final Visibility visibility, final CallType callType) {
        if (visibility == Visibility.PRIVATE) {
            return runtime.getPrivateMethodMissing();
        }
        if (visibility == Visibility.PROTECTED) {
            return runtime.getProtectedMethodMissing();
        }
        if (callType == CallType.VARIABLE) {
            return runtime.getVariableMethodMissing();
        }
        if (callType == CallType.SUPER) {
            return runtime.getSuperMethodMissing();
        }
        return runtime.getNormalMethodMissing();
    }
    
    private static IRubyObject[] prepareMethodMissingArgs(final IRubyObject[] args, final ThreadContext context, final String name) {
        final IRubyObject[] newArgs = new IRubyObject[args.length + 1];
        System.arraycopy(args, 0, newArgs, 1, args.length);
        newArgs[0] = context.getRuntime().newSymbol(name);
        return newArgs;
    }
    
    public static IRubyObject invoke(final ThreadContext context, final IRubyObject self, final String name, final Block block) {
        return self.getMetaClass().finvoke(context, self, name, block);
    }
    
    public static IRubyObject invoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg0, final Block block) {
        return self.getMetaClass().finvoke(context, self, name, arg0, block);
    }
    
    public static IRubyObject invoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        return self.getMetaClass().finvoke(context, self, name, arg0, arg1, block);
    }
    
    public static IRubyObject invoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        return self.getMetaClass().finvoke(context, self, name, arg0, arg1, arg2, block);
    }
    
    public static IRubyObject invoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject[] args, final Block block) {
        return self.getMetaClass().finvoke(context, self, name, args, block);
    }
    
    public static IRubyObject invoke(final ThreadContext context, final IRubyObject self, final String name) {
        return self.getMetaClass().finvoke(context, self, name);
    }
    
    public static IRubyObject invoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg0) {
        return self.getMetaClass().finvoke(context, self, name, arg0);
    }
    
    public static IRubyObject invoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg0, final IRubyObject arg1) {
        return self.getMetaClass().finvoke(context, self, name, arg0, arg1);
    }
    
    public static IRubyObject invoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        return self.getMetaClass().finvoke(context, self, name, arg0, arg1, arg2);
    }
    
    public static IRubyObject invoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject... args) {
        return self.getMetaClass().finvoke(context, self, name, args);
    }
    
    public static IRubyObject invoke(final ThreadContext context, final IRubyObject self, final String name, final CallType callType) {
        return invoke(context, self, name, IRubyObject.NULL_ARRAY, callType, Block.NULL_BLOCK);
    }
    
    public static IRubyObject invoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject[] args, final CallType callType, final Block block) {
        return self.getMetaClass().invoke(context, self, name, args, callType, block);
    }
    
    public static IRubyObject invoke(final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg, final CallType callType, final Block block) {
        return self.getMetaClass().invoke(context, self, name, arg, callType, block);
    }
    
    public static IRubyObject invokeAs(final ThreadContext context, final RubyClass asClass, final IRubyObject self, final String name, final IRubyObject[] args, final Block block) {
        return asClass.finvoke(context, self, name, args, block);
    }
    
    public static IRubyObject invokeAs(final ThreadContext context, final RubyClass asClass, final IRubyObject self, final String name, final Block block) {
        return asClass.finvoke(context, self, name, block);
    }
    
    public static IRubyObject invokeAs(final ThreadContext context, final RubyClass asClass, final IRubyObject self, final String name, final IRubyObject arg0, final Block block) {
        return asClass.finvoke(context, self, name, arg0, block);
    }
    
    public static IRubyObject invokeAs(final ThreadContext context, final RubyClass asClass, final IRubyObject self, final String name, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        return asClass.finvoke(context, self, name, arg0, arg1, block);
    }
    
    public static IRubyObject invokeAs(final ThreadContext context, final RubyClass asClass, final IRubyObject self, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        return asClass.finvoke(context, self, name, arg0, arg1, arg2, block);
    }
    
    public static IRubyObject invokeChecked(final ThreadContext context, final IRubyObject self, final String name) {
        return self.getMetaClass().finvokeChecked(context, self, name);
    }
    
    public static IRubyObject invokeSuper(final ThreadContext context, final IRubyObject self, final IRubyObject[] args, final Block block) {
        checkSuperDisabledOrOutOfMethod(context);
        final RubyModule klazz = context.getFrameKlazz();
        final String name = context.getFrameName();
        final RubyClass superClass = findImplementerIfNecessary(self.getMetaClass(), klazz).getSuperClass();
        final DynamicMethod method = (superClass != null) ? superClass.searchMethod(name) : UndefinedMethod.INSTANCE;
        if (method.isUndefined()) {
            return callMethodMissing(context, self, method.getVisibility(), name, CallType.SUPER, args, block);
        }
        return method.call(context, self, superClass, name, args, block);
    }
    
    public static IRubyObject invokeSuper(final ThreadContext context, final IRubyObject self, final Block block) {
        checkSuperDisabledOrOutOfMethod(context);
        final RubyModule klazz = context.getFrameKlazz();
        final String name = context.getFrameName();
        final RubyClass superClass = findImplementerIfNecessary(self.getMetaClass(), klazz).getSuperClass();
        final DynamicMethod method = (superClass != null) ? superClass.searchMethod(name) : UndefinedMethod.INSTANCE;
        if (method.isUndefined()) {
            return callMethodMissing(context, self, method.getVisibility(), name, CallType.SUPER, block);
        }
        return method.call(context, self, superClass, name, block);
    }
    
    public static IRubyObject invokeSuper(final ThreadContext context, final IRubyObject self, final IRubyObject arg0, final Block block) {
        checkSuperDisabledOrOutOfMethod(context);
        final RubyModule klazz = context.getFrameKlazz();
        final String name = context.getFrameName();
        final RubyClass superClass = findImplementerIfNecessary(self.getMetaClass(), klazz).getSuperClass();
        final DynamicMethod method = (superClass != null) ? superClass.searchMethod(name) : UndefinedMethod.INSTANCE;
        if (method.isUndefined()) {
            return callMethodMissing(context, self, method.getVisibility(), name, CallType.SUPER, arg0, block);
        }
        return method.call(context, self, superClass, name, arg0, block);
    }
    
    public static IRubyObject invokeSuper(final ThreadContext context, final IRubyObject self, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        checkSuperDisabledOrOutOfMethod(context);
        final RubyModule klazz = context.getFrameKlazz();
        final String name = context.getFrameName();
        final RubyClass superClass = findImplementerIfNecessary(self.getMetaClass(), klazz).getSuperClass();
        final DynamicMethod method = (superClass != null) ? superClass.searchMethod(name) : UndefinedMethod.INSTANCE;
        if (method.isUndefined()) {
            return callMethodMissing(context, self, method.getVisibility(), name, CallType.SUPER, arg0, arg1, block);
        }
        return method.call(context, self, superClass, name, arg0, arg1, block);
    }
    
    public static IRubyObject invokeSuper(final ThreadContext context, final IRubyObject self, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        checkSuperDisabledOrOutOfMethod(context);
        final RubyModule klazz = context.getFrameKlazz();
        final String name = context.getFrameName();
        final RubyClass superClass = findImplementerIfNecessary(self.getMetaClass(), klazz).getSuperClass();
        final DynamicMethod method = (superClass != null) ? superClass.searchMethod(name) : UndefinedMethod.INSTANCE;
        if (method.isUndefined()) {
            return callMethodMissing(context, self, method.getVisibility(), name, CallType.SUPER, arg0, arg1, arg2, block);
        }
        return method.call(context, self, superClass, name, arg0, arg1, arg2, block);
    }
    
    public static RubyArray ensureRubyArray(final IRubyObject value) {
        return ensureRubyArray(value.getRuntime(), value);
    }
    
    public static RubyArray ensureRubyArray(final Ruby runtime, final IRubyObject value) {
        return (RubyArray)((value instanceof RubyArray) ? value : RubyArray.newArray(runtime, value));
    }
    
    public static RubyArray ensureMultipleAssignableRubyArray(IRubyObject value, final Ruby runtime, final boolean masgnHasHead) {
        if (!(value instanceof RubyArray)) {
            value = ArgsUtil.convertToRubyArray(runtime, value, masgnHasHead);
        }
        return (RubyArray)value;
    }
    
    public static IRubyObject fetchClassVariable(final ThreadContext context, final Ruby runtime, final IRubyObject self, final String name) {
        RubyModule rubyClass = ASTInterpreter.getClassVariableBase(context, runtime);
        if (rubyClass == null) {
            rubyClass = self.getMetaClass();
        }
        return rubyClass.getClassVar(name);
    }
    
    public static IRubyObject fastFetchClassVariable(final ThreadContext context, final Ruby runtime, final IRubyObject self, final String internedName) {
        RubyModule rubyClass = ASTInterpreter.getClassVariableBase(context, runtime);
        if (rubyClass == null) {
            rubyClass = self.getMetaClass();
        }
        return rubyClass.fastGetClassVar(internedName);
    }
    
    public static IRubyObject getConstant(final ThreadContext context, final String internedName) {
        final Ruby runtime = context.getRuntime();
        return context.getCurrentScope().getStaticScope().getConstantWithConstMissing(runtime, internedName, runtime.getObject());
    }
    
    public static IRubyObject nullToNil(final IRubyObject value, final Ruby runtime) {
        return (value != null) ? value : runtime.getNil();
    }
    
    public static RubyClass prepareSuperClass(final Ruby runtime, final IRubyObject rubyClass) {
        RubyClass.checkInheritable(rubyClass);
        return (RubyClass)rubyClass;
    }
    
    public static RubyModule prepareClassNamespace(final ThreadContext context, IRubyObject rubyModule) {
        if (rubyModule == null || rubyModule.isNil()) {
            rubyModule = context.getCurrentScope().getStaticScope().getModule();
            if (rubyModule == null) {
                throw context.getRuntime().newTypeError("no outer class/module");
            }
        }
        if (rubyModule instanceof RubyModule) {
            return (RubyModule)rubyModule;
        }
        throw context.getRuntime().newTypeError(rubyModule + " is not a class/module");
    }
    
    public static IRubyObject setClassVariable(final ThreadContext context, final Ruby runtime, final IRubyObject self, final String name, final IRubyObject value) {
        RubyModule rubyClass = ASTInterpreter.getClassVariableBase(context, runtime);
        if (rubyClass == null) {
            rubyClass = self.getMetaClass();
        }
        rubyClass.setClassVar(name, value);
        return value;
    }
    
    public static IRubyObject fastSetClassVariable(final ThreadContext context, final Ruby runtime, final IRubyObject self, final String internedName, final IRubyObject value) {
        RubyModule rubyClass = ASTInterpreter.getClassVariableBase(context, runtime);
        if (rubyClass == null) {
            rubyClass = self.getMetaClass();
        }
        rubyClass.fastSetClassVar(internedName, value);
        return value;
    }
    
    public static IRubyObject declareClassVariable(final ThreadContext context, final Ruby runtime, final IRubyObject self, final String name, final IRubyObject value) {
        final RubyModule rubyClass = ASTInterpreter.getClassVariableBase(context, runtime);
        if (rubyClass == null) {
            throw runtime.newTypeError("no class/module to define class variable");
        }
        rubyClass.setClassVar(name, value);
        return value;
    }
    
    public static IRubyObject fastDeclareClassVariable(final ThreadContext context, final Ruby runtime, final IRubyObject self, final String internedName, final IRubyObject value) {
        final RubyModule rubyClass = ASTInterpreter.getClassVariableBase(context, runtime);
        if (rubyClass == null) {
            throw runtime.newTypeError("no class/module to define class variable");
        }
        rubyClass.fastSetClassVar(internedName, value);
        return value;
    }
    
    public static void handleArgumentSizes(final ThreadContext context, final Ruby runtime, final int given, final int required, final int opt, final int rest) {
        if (opt == 0) {
            if (rest < 0) {
                if (given != required) {
                    throw runtime.newArgumentError("wrong number of arguments (" + given + " for " + required + ")");
                }
            }
            else if (given < required) {
                throw runtime.newArgumentError("wrong number of arguments (" + given + " for " + required + ")");
            }
        }
        else if (rest < 0) {
            if (given < required) {
                throw runtime.newArgumentError("wrong number of arguments (" + given + " for " + required + ")");
            }
            if (given > required + opt) {
                throw runtime.newArgumentError("wrong number of arguments (" + given + " for " + (required + opt) + ")");
            }
        }
        else if (given < required) {
            throw runtime.newArgumentError("wrong number of arguments (" + given + " for " + required + ")");
        }
    }
    
    public static Throwable unwrapRedoNextBreakOrJustLocalJump(final RaiseException re, final ThreadContext context) {
        final RubyException exception = re.getException();
        if (context.getRuntime().getLocalJumpError().isInstance(exception)) {
            final RubyLocalJumpError jumpError = (RubyLocalJumpError)re.getException();
            switch (jumpError.getReason()) {
                case REDO: {
                    return JumpException.REDO_JUMP;
                }
                case NEXT: {
                    return new JumpException.NextJump(jumpError.exit_value());
                }
                case BREAK: {
                    return new JumpException.BreakJump(context.getFrameJumpTarget(), jumpError.exit_value());
                }
            }
        }
        return re;
    }
    
    public static String getLocalJumpTypeOrRethrow(final RaiseException re) {
        final RubyException exception = re.getException();
        final Ruby runtime = exception.getRuntime();
        if (runtime.getLocalJumpError().isInstance(exception)) {
            final RubyLocalJumpError jumpError = (RubyLocalJumpError)re.getException();
            final IRubyObject reason = jumpError.reason();
            return reason.asJavaString();
        }
        throw re;
    }
    
    public static IRubyObject unwrapLocalJumpErrorValue(final RaiseException re) {
        return ((RubyLocalJumpError)re.getException()).exit_value();
    }
    
    public static IRubyObject processBlockArgument(final Ruby runtime, final Block block) {
        if (!block.isGiven()) {
            return runtime.getNil();
        }
        return processGivenBlock(block, runtime);
    }
    
    private static IRubyObject processGivenBlock(final Block block, final Ruby runtime) {
        RubyProc blockArg = block.getProcObject();
        if (blockArg == null) {
            blockArg = runtime.newBlockPassProc(Block.Type.PROC, block);
            blockArg.getBlock().type = Block.Type.PROC;
        }
        return blockArg;
    }
    
    public static Block getBlockFromBlockPassBody(final Ruby runtime, IRubyObject proc, final Block currentBlock) {
        if (proc.isNil()) {
            return Block.NULL_BLOCK;
        }
        if (!(proc instanceof RubyProc)) {
            proc = coerceProc(proc, runtime);
        }
        return getBlockFromProc(currentBlock, proc);
    }
    
    private static IRubyObject coerceProc(IRubyObject proc, final Ruby runtime) throws RaiseException {
        proc = TypeConverter.convertToType(proc, runtime.getProc(), "to_proc", false);
        if (!(proc instanceof RubyProc)) {
            throw runtime.newTypeError("wrong argument type " + proc.getMetaClass().getName() + " (expected Proc)");
        }
        return proc;
    }
    
    private static Block getBlockFromProc(final Block currentBlock, final IRubyObject proc) {
        if (currentBlock != null && currentBlock.isGiven()) {
            final RubyProc procObject = currentBlock.getProcObject();
            if (procObject != null && procObject == proc) {
                return currentBlock;
            }
        }
        return ((RubyProc)proc).getBlock();
    }
    
    public static Block getBlockFromBlockPassBody(final IRubyObject proc, final Block currentBlock) {
        return getBlockFromBlockPassBody(proc.getRuntime(), proc, currentBlock);
    }
    
    public static IRubyObject backref(final ThreadContext context) {
        final IRubyObject backref = context.getCurrentScope().getBackRef(context.getRuntime());
        if (backref instanceof RubyMatchData) {
            ((RubyMatchData)backref).use();
        }
        return backref;
    }
    
    public static IRubyObject backrefLastMatch(final ThreadContext context) {
        final IRubyObject backref = context.getCurrentScope().getBackRef(context.getRuntime());
        return RubyRegexp.last_match(backref);
    }
    
    public static IRubyObject backrefMatchPre(final ThreadContext context) {
        final IRubyObject backref = context.getCurrentScope().getBackRef(context.getRuntime());
        return RubyRegexp.match_pre(backref);
    }
    
    public static IRubyObject backrefMatchPost(final ThreadContext context) {
        final IRubyObject backref = context.getCurrentScope().getBackRef(context.getRuntime());
        return RubyRegexp.match_post(backref);
    }
    
    public static IRubyObject backrefMatchLast(final ThreadContext context) {
        final IRubyObject backref = context.getCurrentScope().getBackRef(context.getRuntime());
        return RubyRegexp.match_last(backref);
    }
    
    public static IRubyObject[] getArgValues(final ThreadContext context) {
        return context.getCurrentScope().getArgValues();
    }
    
    public static IRubyObject callZSuper(final Ruby runtime, final ThreadContext context, Block block, final IRubyObject self) {
        if (!block.isGiven()) {
            block = context.getCurrentFrame().getBlock();
        }
        return invokeSuper(context, self, context.getCurrentScope().getArgValues(), block);
    }
    
    public static IRubyObject[] appendToObjectArray(final IRubyObject[] array, final IRubyObject add) {
        final IRubyObject[] newArray = new IRubyObject[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = add;
        return newArray;
    }
    
    public static JumpException.ReturnJump returnJump(final IRubyObject result, final ThreadContext context) {
        return context.returnJump(result);
    }
    
    public static IRubyObject breakJumpInWhile(final JumpException.BreakJump bj, final ThreadContext context) {
        if (bj.getTarget() == context.getFrameJumpTarget()) {
            return (IRubyObject)bj.getValue();
        }
        throw bj;
    }
    
    public static IRubyObject breakJump(final ThreadContext context, final IRubyObject value) {
        throw new JumpException.BreakJump(context.getFrameJumpTarget(), value);
    }
    
    public static IRubyObject breakLocalJumpError(final Ruby runtime, final IRubyObject value) {
        throw runtime.newLocalJumpError(RubyLocalJumpError.Reason.BREAK, value, "unexpected break");
    }
    
    public static IRubyObject[] concatObjectArrays(final IRubyObject[] array, final IRubyObject[] add) {
        final IRubyObject[] newArray = new IRubyObject[array.length + add.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        System.arraycopy(add, 0, newArray, array.length, add.length);
        return newArray;
    }
    
    public static IRubyObject isExceptionHandled(final RubyException currentException, final IRubyObject[] exceptions, final ThreadContext context) {
        for (int i = 0; i < exceptions.length; ++i) {
            final IRubyObject result = isExceptionHandled(currentException, exceptions[i], context);
            if (result.isTrue()) {
                return result;
            }
        }
        return context.getRuntime().getFalse();
    }
    
    public static IRubyObject isExceptionHandled(final RubyException currentException, final IRubyObject exception, final ThreadContext context) {
        return isExceptionHandled((IRubyObject)currentException, exception, context);
    }
    
    public static IRubyObject isExceptionHandled(final IRubyObject currentException, final IRubyObject exception, final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (!runtime.getModule().isInstance(exception)) {
            throw runtime.newTypeError("class or module required for rescue clause");
        }
        final IRubyObject result = invoke(context, exception, "===", currentException);
        if (result.isTrue()) {
            return result;
        }
        return runtime.getFalse();
    }
    
    public static IRubyObject isExceptionHandled(final RubyException currentException, final IRubyObject exception0, final IRubyObject exception1, final ThreadContext context) {
        final IRubyObject result = isExceptionHandled(currentException, exception0, context);
        if (result.isTrue()) {
            return result;
        }
        return isExceptionHandled(currentException, exception1, context);
    }
    
    public static IRubyObject isExceptionHandled(final RubyException currentException, final IRubyObject exception0, final IRubyObject exception1, final IRubyObject exception2, final ThreadContext context) {
        final IRubyObject result = isExceptionHandled(currentException, exception0, context);
        if (result.isTrue()) {
            return result;
        }
        return isExceptionHandled(currentException, exception1, exception2, context);
    }
    
    private static boolean checkJavaException(final Throwable throwable, final IRubyObject catchable, final ThreadContext context) {
        if (!context.getRuntime().getException().op_ge(catchable).isTrue() && context.getRuntime().getObject() != catchable) {
            if (catchable instanceof RubyClass && catchable.getInstanceVariables().hasInstanceVariable("@java_class")) {
                final RubyClass rubyClass = (RubyClass)catchable;
                final JavaClass javaClass = (JavaClass)rubyClass.fastGetInstanceVariable("@java_class");
                if (javaClass != null) {
                    final Class cls = javaClass.javaClass();
                    if (cls.isInstance(throwable)) {
                        return true;
                    }
                }
            }
            return false;
        }
        if (throwable instanceof RaiseException) {
            return isExceptionHandled(((RaiseException)throwable).getException(), catchable, context).isTrue();
        }
        return isExceptionHandled(JavaUtil.convertJavaToUsableRubyObject(context.getRuntime(), throwable), catchable, context).isTrue();
    }
    
    public static IRubyObject isJavaExceptionHandled(final Throwable currentThrowable, final IRubyObject[] throwables, final ThreadContext context) {
        if (currentThrowable instanceof Unrescuable) {
            UnsafeFactory.getUnsafe().throwException(currentThrowable);
        }
        if (currentThrowable instanceof RaiseException) {
            return isExceptionHandled(((RaiseException)currentThrowable).getException(), throwables, context);
        }
        for (int i = 0; i < throwables.length; ++i) {
            if (checkJavaException(currentThrowable, throwables[0], context)) {
                return context.getRuntime().getTrue();
            }
        }
        return context.getRuntime().getFalse();
    }
    
    public static IRubyObject isJavaExceptionHandled(final Throwable currentThrowable, final IRubyObject throwable, final ThreadContext context) {
        if (currentThrowable instanceof Unrescuable) {
            UnsafeFactory.getUnsafe().throwException(currentThrowable);
        }
        if (currentThrowable instanceof RaiseException) {
            return isExceptionHandled(((RaiseException)currentThrowable).getException(), throwable, context);
        }
        if (checkJavaException(currentThrowable, throwable, context)) {
            return context.getRuntime().getTrue();
        }
        return context.getRuntime().getFalse();
    }
    
    public static IRubyObject isJavaExceptionHandled(final Throwable currentThrowable, final IRubyObject throwable0, final IRubyObject throwable1, final ThreadContext context) {
        if (currentThrowable instanceof Unrescuable) {
            UnsafeFactory.getUnsafe().throwException(currentThrowable);
        }
        if (currentThrowable instanceof RaiseException) {
            return isExceptionHandled(((RaiseException)currentThrowable).getException(), throwable0, throwable1, context);
        }
        if (checkJavaException(currentThrowable, throwable0, context)) {
            return context.getRuntime().getTrue();
        }
        if (checkJavaException(currentThrowable, throwable1, context)) {
            return context.getRuntime().getTrue();
        }
        return context.getRuntime().getFalse();
    }
    
    public static IRubyObject isJavaExceptionHandled(final Throwable currentThrowable, final IRubyObject throwable0, final IRubyObject throwable1, final IRubyObject throwable2, final ThreadContext context) {
        if (currentThrowable instanceof Unrescuable) {
            UnsafeFactory.getUnsafe().throwException(currentThrowable);
        }
        if (currentThrowable instanceof RaiseException) {
            return isExceptionHandled(((RaiseException)currentThrowable).getException(), throwable0, throwable1, throwable2, context);
        }
        if (checkJavaException(currentThrowable, throwable0, context)) {
            return context.getRuntime().getTrue();
        }
        if (checkJavaException(currentThrowable, throwable1, context)) {
            return context.getRuntime().getTrue();
        }
        if (checkJavaException(currentThrowable, throwable2, context)) {
            return context.getRuntime().getTrue();
        }
        return context.getRuntime().getFalse();
    }
    
    public static void storeExceptionInErrorInfo(final Throwable currentThrowable, final ThreadContext context) {
        IRubyObject exception = null;
        if (currentThrowable instanceof RaiseException) {
            exception = ((RaiseException)currentThrowable).getException();
        }
        else {
            exception = JavaUtil.convertJavaToUsableRubyObject(context.getRuntime(), currentThrowable);
        }
        context.setErrorInfo(exception);
    }
    
    public static void clearErrorInfo(final ThreadContext context) {
        context.setErrorInfo(context.getRuntime().getNil());
    }
    
    public static void checkSuperDisabledOrOutOfMethod(final ThreadContext context) {
        if (context.getFrameKlazz() != null) {
            return;
        }
        final String name = context.getFrameName();
        if (name != null) {
            throw context.getRuntime().newNameError("superclass method '" + name + "' disabled", name);
        }
        throw context.getRuntime().newNoMethodError("super called outside of method", null, context.getRuntime().getNil());
    }
    
    public static Block ensureSuperBlock(final Block given, final Block parent) {
        if (!given.isGiven()) {
            return parent;
        }
        return given;
    }
    
    public static RubyModule findImplementerIfNecessary(final RubyModule clazz, final RubyModule implementationClass) {
        if (implementationClass != null && implementationClass.needsImplementer()) {
            return clazz.findImplementer(implementationClass);
        }
        return implementationClass;
    }
    
    public static RubyArray createSubarray(final RubyArray input, final int start) {
        return (RubyArray)input.subseqLight(start, input.size() - start);
    }
    
    public static RubyArray createSubarray(final RubyArray input, final int start, final int post) {
        return (RubyArray)input.subseqLight(start, input.size() - post - start);
    }
    
    public static RubyArray createSubarray(final IRubyObject[] input, final Ruby runtime, final int start) {
        if (start >= input.length) {
            return RubyArray.newEmptyArray(runtime);
        }
        return RubyArray.newArrayNoCopy(runtime, input, start);
    }
    
    public static RubyArray createSubarray(final IRubyObject[] input, final Ruby runtime, final int start, final int exclude) {
        final int length = input.length - exclude - start;
        if (length <= 0) {
            return RubyArray.newEmptyArray(runtime);
        }
        return RubyArray.newArrayNoCopy(runtime, input, start, length);
    }
    
    public static IRubyObject elementOrNull(final IRubyObject[] input, final int element) {
        if (element >= input.length) {
            return null;
        }
        return input[element];
    }
    
    public static IRubyObject optElementOrNull(final IRubyObject[] input, final int element, final int postCount) {
        if (element + postCount >= input.length) {
            return null;
        }
        return input[element];
    }
    
    public static IRubyObject elementOrNil(final IRubyObject[] input, final int element, final IRubyObject nil) {
        if (element >= input.length) {
            return nil;
        }
        return input[element];
    }
    
    public static IRubyObject postElementOrNil(final IRubyObject[] input, final int postCount, final int postIndex, final IRubyObject nil) {
        final int aryIndex = input.length - postCount + postIndex;
        if (aryIndex >= input.length || aryIndex < 0) {
            return nil;
        }
        return input[aryIndex];
    }
    
    public static RubyBoolean isWhenTriggered(final IRubyObject expression, final IRubyObject expressionsObject, final ThreadContext context) {
        final RubyArray expressions = splatValue(expressionsObject);
        for (int j = 0, k = expressions.getLength(); j < k; ++j) {
            final IRubyObject condition = expressions.eltInternal(j);
            if ((expression != null && condition.callMethod(context, "===", expression).isTrue()) || (expression == null && condition.isTrue())) {
                return context.getRuntime().getTrue();
            }
        }
        return context.getRuntime().getFalse();
    }
    
    public static IRubyObject setConstantInModule(final IRubyObject module, final IRubyObject value, final String name, final ThreadContext context) {
        return context.setConstantInModule(name, module, value);
    }
    
    public static IRubyObject setConstantInCurrent(final IRubyObject value, final ThreadContext context, final String name) {
        return context.setConstantInCurrent(name, value);
    }
    
    public static IRubyObject retryJump() {
        throw JumpException.RETRY_JUMP;
    }
    
    public static IRubyObject redoJump() {
        throw JumpException.REDO_JUMP;
    }
    
    public static IRubyObject redoLocalJumpError(final Ruby runtime) {
        throw runtime.newLocalJumpError(RubyLocalJumpError.Reason.REDO, runtime.getNil(), "unexpected redo");
    }
    
    public static IRubyObject nextJump(final IRubyObject value) {
        throw new JumpException.NextJump(value);
    }
    
    public static IRubyObject nextLocalJumpError(final Ruby runtime, final IRubyObject value) {
        throw runtime.newLocalJumpError(RubyLocalJumpError.Reason.NEXT, value, "unexpected next");
    }
    
    public static IRubyObject[] constructObjectArray(final IRubyObject one) {
        return new IRubyObject[] { one };
    }
    
    public static IRubyObject[] constructObjectArray(final IRubyObject one, final IRubyObject two) {
        return new IRubyObject[] { one, two };
    }
    
    public static IRubyObject[] constructObjectArray(final IRubyObject one, final IRubyObject two, final IRubyObject three) {
        return new IRubyObject[] { one, two, three };
    }
    
    public static IRubyObject[] constructObjectArray(final IRubyObject one, final IRubyObject two, final IRubyObject three, final IRubyObject four) {
        return new IRubyObject[] { one, two, three, four };
    }
    
    public static IRubyObject[] constructObjectArray(final IRubyObject one, final IRubyObject two, final IRubyObject three, final IRubyObject four, final IRubyObject five) {
        return new IRubyObject[] { one, two, three, four, five };
    }
    
    public static RubyArray constructRubyArray(final Ruby runtime, final IRubyObject one) {
        return RubyArray.newArrayLight(runtime, one);
    }
    
    public static RubyArray constructRubyArray(final Ruby runtime, final IRubyObject one, final IRubyObject two) {
        return RubyArray.newArrayLight(runtime, one, two);
    }
    
    public static RubyArray constructRubyArray(final Ruby runtime, final IRubyObject one, final IRubyObject two, final IRubyObject three) {
        return RubyArray.newArrayLight(runtime, one, two, three);
    }
    
    public static RubyArray constructRubyArray(final Ruby runtime, final IRubyObject one, final IRubyObject two, final IRubyObject three, final IRubyObject four) {
        return RubyArray.newArrayLight(runtime, one, two, three, four);
    }
    
    public static RubyArray constructRubyArray(final Ruby runtime, final IRubyObject one, final IRubyObject two, final IRubyObject three, final IRubyObject four, final IRubyObject five) {
        return RubyArray.newArrayLight(runtime, one, two, three, four, five);
    }
    
    public static String[] constructStringArray(final String one) {
        return new String[] { one };
    }
    
    public static String[] constructStringArray(final String one, final String two) {
        return new String[] { one, two };
    }
    
    public static String[] constructStringArray(final String one, final String two, final String three) {
        return new String[] { one, two, three };
    }
    
    public static String[] constructStringArray(final String one, final String two, final String three, final String four) {
        return new String[] { one, two, three, four };
    }
    
    public static String[] constructStringArray(final String one, final String two, final String three, final String four, final String five) {
        return new String[] { one, two, three, four, five };
    }
    
    public static String[] constructStringArray(final String one, final String two, final String three, final String four, final String five, final String six) {
        return new String[] { one, two, three, four, five, six };
    }
    
    public static String[] constructStringArray(final String one, final String two, final String three, final String four, final String five, final String six, final String seven) {
        return new String[] { one, two, three, four, five, six, seven };
    }
    
    public static String[] constructStringArray(final String one, final String two, final String three, final String four, final String five, final String six, final String seven, final String eight) {
        return new String[] { one, two, three, four, five, six, seven, eight };
    }
    
    public static String[] constructStringArray(final String one, final String two, final String three, final String four, final String five, final String six, final String seven, final String eight, final String nine) {
        return new String[] { one, two, three, four, five, six, seven, eight, nine };
    }
    
    public static String[] constructStringArray(final String one, final String two, final String three, final String four, final String five, final String six, final String seven, final String eight, final String nine, final String ten) {
        return new String[] { one, two, three, four, five, six, seven, eight, nine, ten };
    }
    
    public static RubyHash constructHash(final Ruby runtime, final IRubyObject key1, final IRubyObject value1) {
        final RubyHash hash = RubyHash.newHash(runtime);
        hash.fastASetCheckString(runtime, key1, value1);
        return hash;
    }
    
    public static RubyHash constructHash(final Ruby runtime, final IRubyObject key1, final IRubyObject value1, final IRubyObject key2, final IRubyObject value2) {
        final RubyHash hash = RubyHash.newHash(runtime);
        hash.fastASetCheckString(runtime, key1, value1);
        hash.fastASetCheckString(runtime, key2, value2);
        return hash;
    }
    
    public static RubyHash constructHash(final Ruby runtime, final IRubyObject key1, final IRubyObject value1, final IRubyObject key2, final IRubyObject value2, final IRubyObject key3, final IRubyObject value3) {
        final RubyHash hash = RubyHash.newHash(runtime);
        hash.fastASetCheckString(runtime, key1, value1);
        hash.fastASetCheckString(runtime, key2, value2);
        hash.fastASetCheckString(runtime, key3, value3);
        return hash;
    }
    
    public static RubyHash constructHash19(final Ruby runtime, final IRubyObject key1, final IRubyObject value1) {
        final RubyHash hash = RubyHash.newHash(runtime);
        hash.fastASetCheckString19(runtime, key1, value1);
        return hash;
    }
    
    public static RubyHash constructHash19(final Ruby runtime, final IRubyObject key1, final IRubyObject value1, final IRubyObject key2, final IRubyObject value2) {
        final RubyHash hash = RubyHash.newHash(runtime);
        hash.fastASetCheckString19(runtime, key1, value1);
        hash.fastASetCheckString19(runtime, key2, value2);
        return hash;
    }
    
    public static RubyHash constructHash19(final Ruby runtime, final IRubyObject key1, final IRubyObject value1, final IRubyObject key2, final IRubyObject value2, final IRubyObject key3, final IRubyObject value3) {
        final RubyHash hash = RubyHash.newHash(runtime);
        hash.fastASetCheckString19(runtime, key1, value1);
        hash.fastASetCheckString19(runtime, key2, value2);
        hash.fastASetCheckString19(runtime, key3, value3);
        return hash;
    }
    
    public static IRubyObject undefMethod(final ThreadContext context, final Object nameArg) {
        final RubyModule module = context.getRubyClass();
        final String name = (String)((nameArg instanceof String) ? nameArg : nameArg.toString());
        if (module == null) {
            throw context.getRuntime().newTypeError("No class to undef method '" + name + "'.");
        }
        module.undef(context, name);
        return context.getRuntime().getNil();
    }
    
    public static IRubyObject defineAlias(final ThreadContext context, final IRubyObject self, final Object newNameArg, final Object oldNameArg) {
        final Ruby runtime = context.getRuntime();
        final RubyModule module = context.getRubyClass();
        if (module == null || self instanceof RubyFixnum || self instanceof RubySymbol) {
            throw runtime.newTypeError("no class to make alias");
        }
        final String newName = (String)((newNameArg instanceof String) ? newNameArg : newNameArg.toString());
        final String oldName = (String)((oldNameArg instanceof String) ? oldNameArg : oldNameArg.toString());
        module.defineAlias(newName, oldName);
        module.callMethod(context, "method_added", runtime.newSymbol(newName));
        return runtime.getNil();
    }
    
    public static IRubyObject negate(final IRubyObject value, final Ruby runtime) {
        if (value.isTrue()) {
            return runtime.getFalse();
        }
        return runtime.getTrue();
    }
    
    public static IRubyObject stringOrNil(final ByteList value, final ThreadContext context) {
        if (value == null) {
            return context.nil;
        }
        return RubyString.newStringShared(context.runtime, value);
    }
    
    public static void preLoad(final ThreadContext context, final String[] varNames) {
        final StaticScope staticScope = new LocalStaticScope(null, varNames);
        preLoadCommon(context, staticScope);
    }
    
    public static void preLoad(final ThreadContext context, final String scopeString) {
        final StaticScope staticScope = decodeRootScope(context, scopeString);
        preLoadCommon(context, staticScope);
    }
    
    private static void preLoadCommon(final ThreadContext context, final StaticScope staticScope) {
        final RubyClass objectClass = context.getRuntime().getObject();
        final IRubyObject topLevel = context.getRuntime().getTopSelf();
        staticScope.setModule(objectClass);
        final DynamicScope scope = DynamicScope.newDynamicScope(staticScope);
        context.preScopedBody(scope);
        context.preNodeEval(objectClass, topLevel);
    }
    
    public static void postLoad(final ThreadContext context) {
        context.postNodeEval();
        context.postScopedBody();
    }
    
    public static void registerEndBlock(final Block block, final Ruby runtime) {
        runtime.pushExitBlock(runtime.newProc(Block.Type.LAMBDA, block));
    }
    
    public static IRubyObject match3(final RubyRegexp regexp, final IRubyObject value, final ThreadContext context) {
        if (value instanceof RubyString) {
            return regexp.op_match(context, value);
        }
        return value.callMethod(context, "=~", regexp);
    }
    
    public static IRubyObject getErrorInfo(final Ruby runtime) {
        return runtime.getGlobalVariables().get("$!");
    }
    
    public static void setErrorInfo(final Ruby runtime, final IRubyObject error) {
        runtime.getGlobalVariables().set("$!", error);
    }
    
    public static IRubyObject setLastLine(final Ruby runtime, final ThreadContext context, final IRubyObject value) {
        return context.getCurrentScope().setLastLine(value);
    }
    
    public static IRubyObject getLastLine(final Ruby runtime, final ThreadContext context) {
        return context.getCurrentScope().getLastLine(runtime);
    }
    
    public static IRubyObject setBackref(final Ruby runtime, final ThreadContext context, final IRubyObject value) {
        if (!value.isNil() && !(value instanceof RubyMatchData)) {
            throw runtime.newTypeError(value, runtime.getMatchData());
        }
        return context.getCurrentScope().setBackRef(value);
    }
    
    public static IRubyObject getBackref(final Ruby runtime, final ThreadContext context) {
        final IRubyObject backref = context.getCurrentScope().getBackRef(runtime);
        if (backref instanceof RubyMatchData) {
            ((RubyMatchData)backref).use();
        }
        return backref;
    }
    
    public static IRubyObject preOpAsgnWithOrAnd(final IRubyObject receiver, final ThreadContext context, final IRubyObject self, final CallSite varSite) {
        return varSite.call(context, self, receiver);
    }
    
    public static IRubyObject postOpAsgnWithOrAnd(final IRubyObject receiver, final IRubyObject value, final ThreadContext context, final IRubyObject self, final CallSite varAsgnSite) {
        varAsgnSite.call(context, self, receiver, value);
        return value;
    }
    
    public static IRubyObject opAsgnWithMethod(final ThreadContext context, final IRubyObject self, final IRubyObject receiver, final IRubyObject arg, final CallSite varSite, final CallSite opSite, final CallSite opAsgnSite) {
        final IRubyObject var = varSite.call(context, self, receiver);
        final IRubyObject result = opSite.call(context, self, var, arg);
        opAsgnSite.call(context, self, receiver, result);
        return result;
    }
    
    public static IRubyObject opElementAsgnWithMethod(final ThreadContext context, final IRubyObject self, final IRubyObject receiver, final IRubyObject value, final CallSite elementSite, final CallSite opSite, final CallSite elementAsgnSite) {
        final IRubyObject var = elementSite.call(context, self, receiver);
        final IRubyObject result = opSite.call(context, self, var, value);
        elementAsgnSite.call(context, self, receiver, result);
        return result;
    }
    
    public static IRubyObject opElementAsgnWithMethod(final ThreadContext context, final IRubyObject self, final IRubyObject receiver, final IRubyObject arg, final IRubyObject value, final CallSite elementSite, final CallSite opSite, final CallSite elementAsgnSite) {
        final IRubyObject var = elementSite.call(context, self, receiver, arg);
        final IRubyObject result = opSite.call(context, self, var, value);
        elementAsgnSite.call(context, self, receiver, arg, result);
        return result;
    }
    
    public static IRubyObject opElementAsgnWithMethod(final ThreadContext context, final IRubyObject self, final IRubyObject receiver, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject value, final CallSite elementSite, final CallSite opSite, final CallSite elementAsgnSite) {
        final IRubyObject var = elementSite.call(context, self, receiver, arg1, arg2);
        final IRubyObject result = opSite.call(context, self, var, value);
        elementAsgnSite.call(context, self, receiver, arg1, arg2, result);
        return result;
    }
    
    public static IRubyObject opElementAsgnWithMethod(final ThreadContext context, final IRubyObject self, final IRubyObject receiver, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject value, final CallSite elementSite, final CallSite opSite, final CallSite elementAsgnSite) {
        final IRubyObject var = elementSite.call(context, self, receiver, arg1, arg2, arg3);
        final IRubyObject result = opSite.call(context, self, var, value);
        elementAsgnSite.call(context, self, receiver, arg1, arg2, arg3, result);
        return result;
    }
    
    public static IRubyObject opElementAsgnWithMethod(final ThreadContext context, final IRubyObject self, final IRubyObject receiver, final IRubyObject[] args, final IRubyObject value, final CallSite elementSite, final CallSite opSite, final CallSite elementAsgnSite) {
        final IRubyObject var = elementSite.call(context, self, receiver);
        final IRubyObject result = opSite.call(context, self, var, value);
        elementAsgnSite.call(context, self, receiver, appendToObjectArray(args, result));
        return result;
    }
    
    public static IRubyObject opElementAsgnWithOrPartTwoOneArg(final ThreadContext context, final IRubyObject self, final IRubyObject receiver, final IRubyObject arg, final IRubyObject value, final CallSite asetSite) {
        asetSite.call(context, self, receiver, arg, value);
        return value;
    }
    
    public static IRubyObject opElementAsgnWithOrPartTwoTwoArgs(final ThreadContext context, final IRubyObject self, final IRubyObject receiver, final IRubyObject[] args, final IRubyObject value, final CallSite asetSite) {
        asetSite.call(context, self, receiver, args[0], args[1], value);
        return value;
    }
    
    public static IRubyObject opElementAsgnWithOrPartTwoThreeArgs(final ThreadContext context, final IRubyObject self, final IRubyObject receiver, final IRubyObject[] args, final IRubyObject value, final CallSite asetSite) {
        asetSite.call(context, self, receiver, args[0], args[1], args[2], value);
        return value;
    }
    
    public static IRubyObject opElementAsgnWithOrPartTwoNArgs(final ThreadContext context, final IRubyObject self, final IRubyObject receiver, final IRubyObject[] args, final IRubyObject value, final CallSite asetSite) {
        final IRubyObject[] newArgs = new IRubyObject[args.length + 1];
        System.arraycopy(args, 0, newArgs, 0, args.length);
        newArgs[args.length] = value;
        asetSite.call(context, self, receiver, newArgs);
        return value;
    }
    
    public static RubyArray arrayValue(final IRubyObject value) {
        final Ruby runtime = value.getRuntime();
        return arrayValue(runtime.getCurrentContext(), runtime, value);
    }
    
    public static RubyArray arrayValue(final ThreadContext context, final Ruby runtime, final IRubyObject value) {
        final IRubyObject tmp = value.checkArrayType();
        if (!tmp.isNil()) {
            return (RubyArray)tmp;
        }
        if (!value.respondsTo("to_a") || value.getMetaClass().searchMethod("to_a").getImplementationClass() == runtime.getKernel()) {
            return runtime.newArray(value);
        }
        final IRubyObject avalue = value.callMethod(context, "to_a");
        if (avalue instanceof RubyArray) {
            return (RubyArray)avalue;
        }
        if (runtime.is1_9() && avalue.isNil()) {
            return runtime.newArray(value);
        }
        throw runtime.newTypeError("`to_a' did not return Array");
    }
    
    public static IRubyObject aryToAry(final IRubyObject value) {
        if (value instanceof RubyArray) {
            return value;
        }
        if (value.respondsTo("to_ary")) {
            return TypeConverter.convertToType(value, value.getRuntime().getArray(), "to_ary", false);
        }
        return value.getRuntime().newArray(value);
    }
    
    public static IRubyObject aValueSplat(final IRubyObject value) {
        if (!(value instanceof RubyArray) || ((RubyArray)value).length().getLongValue() == 0L) {
            return value.getRuntime().getNil();
        }
        final RubyArray array = (RubyArray)value;
        return (array.getLength() == 1) ? array.first() : array;
    }
    
    public static IRubyObject aValueSplat19(final IRubyObject value) {
        if (!(value instanceof RubyArray)) {
            return value.getRuntime().getNil();
        }
        return value;
    }
    
    public static RubyArray splatValue(final IRubyObject value) {
        if (value.isNil()) {
            return value.getRuntime().newArray(value);
        }
        return arrayValue(value);
    }
    
    public static RubyArray splatValue19(final IRubyObject value) {
        if (value.isNil()) {
            return value.getRuntime().newEmptyArray();
        }
        return arrayValue(value);
    }
    
    public static void addInstanceMethod(final RubyModule containingClass, final String name, final DynamicMethod method, final Visibility visibility, final ThreadContext context, final Ruby runtime) {
        containingClass.addMethod(name, method);
        final RubySymbol sym = runtime.fastNewSymbol(name);
        if (visibility == Visibility.MODULE_FUNCTION) {
            addModuleMethod(containingClass, name, method, context, sym);
        }
        callNormalMethodHook(containingClass, context, sym);
    }
    
    private static void addModuleMethod(final RubyModule containingClass, final String name, final DynamicMethod method, final ThreadContext context, final RubySymbol sym) {
        containingClass.getSingletonClass().addMethod(name, new WrapperMethod(containingClass.getSingletonClass(), method, Visibility.PUBLIC));
        containingClass.callMethod(context, "singleton_method_added", sym);
    }
    
    private static void callNormalMethodHook(final RubyModule containingClass, final ThreadContext context, final RubySymbol name) {
        if (containingClass.isSingleton()) {
            callSingletonMethodHook(((MetaClass)containingClass).getAttached(), context, name);
        }
        else {
            containingClass.callMethod(context, "method_added", name);
        }
    }
    
    private static void callSingletonMethodHook(final IRubyObject receiver, final ThreadContext context, final RubySymbol name) {
        receiver.callMethod(context, "singleton_method_added", name);
    }
    
    private static DynamicMethod constructNormalMethod(final MethodFactory factory, final String javaName, final String name, final RubyModule containingClass, final ISourcePosition position, final int arity, final StaticScope scope, Visibility visibility, final Object scriptObject, final CallConfiguration callConfig, final String parameterDesc) {
        if (name.equals("initialize") || name.equals("initialize_copy") || visibility == Visibility.MODULE_FUNCTION) {
            visibility = Visibility.PRIVATE;
        }
        DynamicMethod method;
        if (RubyInstanceConfig.LAZYHANDLES_COMPILE) {
            method = factory.getCompiledMethodLazily(containingClass, javaName, Arity.createArity(arity), visibility, scope, scriptObject, callConfig, position, parameterDesc);
        }
        else {
            method = factory.getCompiledMethod(containingClass, javaName, Arity.createArity(arity), visibility, scope, scriptObject, callConfig, position, parameterDesc);
        }
        return method;
    }
    
    private static DynamicMethod constructSingletonMethod(final MethodFactory factory, final String javaName, final RubyClass rubyClass, final ISourcePosition position, final int arity, final StaticScope scope, final Object scriptObject, final CallConfiguration callConfig, final String parameterDesc) {
        if (RubyInstanceConfig.LAZYHANDLES_COMPILE) {
            return factory.getCompiledMethodLazily(rubyClass, javaName, Arity.createArity(arity), Visibility.PUBLIC, scope, scriptObject, callConfig, position, parameterDesc);
        }
        return factory.getCompiledMethod(rubyClass, javaName, Arity.createArity(arity), Visibility.PUBLIC, scope, scriptObject, callConfig, position, parameterDesc);
    }
    
    public static String encodeScope(final StaticScope scope) {
        final StringBuilder namesBuilder = new StringBuilder();
        boolean first = true;
        for (final String name : scope.getVariables()) {
            if (!first) {
                namesBuilder.append(';');
            }
            first = false;
            namesBuilder.append(name);
        }
        namesBuilder.append(',').append(scope.getRequiredArgs()).append(',').append(scope.getOptionalArgs()).append(',').append(scope.getRestArg());
        return namesBuilder.toString();
    }
    
    public static LocalStaticScope decodeRootScope(final ThreadContext context, final String scopeString) {
        final String[][] decodedScope = decodeScopeDescriptor(scopeString);
        final LocalStaticScope scope = new LocalStaticScope(null, decodedScope[1]);
        setAritiesFromDecodedScope(scope, decodedScope[0]);
        return scope;
    }
    
    public static LocalStaticScope decodeLocalScope(final ThreadContext context, final String scopeString) {
        final String[][] decodedScope = decodeScopeDescriptor(scopeString);
        final LocalStaticScope scope = new LocalStaticScope(context.getCurrentScope().getStaticScope(), decodedScope[1]);
        setAritiesFromDecodedScope(scope, decodedScope[0]);
        return scope;
    }
    
    public static BlockStaticScope decodeBlockScope(final ThreadContext context, final String scopeString) {
        final String[][] decodedScope = decodeScopeDescriptor(scopeString);
        final BlockStaticScope scope = new BlockStaticScope(context.getCurrentScope().getStaticScope(), decodedScope[1]);
        setAritiesFromDecodedScope(scope, decodedScope[0]);
        return scope;
    }
    
    private static String[][] decodeScopeDescriptor(final String scopeString) {
        final String[] scopeElements = scopeString.split(",");
        final String[] scopeNames = (scopeElements[0].length() == 0) ? new String[0] : getScopeNames(scopeElements[0]);
        return new String[][] { scopeElements, scopeNames };
    }
    
    private static void setAritiesFromDecodedScope(final StaticScope scope, final String[] scopeElements) {
        scope.setArities(Integer.parseInt(scopeElements[1]), Integer.parseInt(scopeElements[2]), Integer.parseInt(scopeElements[3]));
    }
    
    private static StaticScope createScopeForClass(final ThreadContext context, final String scopeString) {
        final StaticScope scope = decodeLocalScope(context, scopeString);
        scope.determineModule();
        return scope;
    }
    
    private static void performNormalMethodChecks(final RubyModule containingClass, final Ruby runtime, final String name) throws RaiseException {
        if (containingClass == runtime.getDummy()) {
            throw runtime.newTypeError("no class/module to add method");
        }
        if (containingClass == runtime.getObject() && name.equals("initialize")) {
            runtime.getWarnings().warn(IRubyWarnings.ID.REDEFINING_DANGEROUS, "redefining Object#initialize may cause infinite loop", "Object#initialize");
        }
        if (name.equals("__id__") || name.equals("__send__")) {
            runtime.getWarnings().warn(IRubyWarnings.ID.REDEFINING_DANGEROUS, "redefining `" + name + "' may cause serious problem", name);
        }
    }
    
    private static RubyClass performSingletonMethodChecks(final Ruby runtime, final IRubyObject receiver, final String name) throws RaiseException {
        if (runtime.getSafeLevel() >= 4 && !receiver.isTaint()) {
            throw runtime.newSecurityError("Insecure; can't define singleton method.");
        }
        if (receiver instanceof RubyFixnum || receiver instanceof RubySymbol) {
            throw runtime.newTypeError("can't define singleton method \"" + name + "\" for " + receiver.getMetaClass().getBaseName());
        }
        if (receiver.isFrozen()) {
            throw runtime.newFrozenError("object");
        }
        final RubyClass rubyClass = receiver.getSingletonClass();
        if (runtime.getSafeLevel() >= 4 && rubyClass.getMethods().get(name) != null) {
            throw runtime.newSecurityError("redefining method prohibited.");
        }
        return rubyClass;
    }
    
    public static IRubyObject arrayEntryOrNil(final RubyArray array, final int index) {
        if (index < array.getLength()) {
            return array.eltInternal(index);
        }
        return array.getRuntime().getNil();
    }
    
    public static IRubyObject arrayEntryOrNilZero(final RubyArray array) {
        if (0 < array.getLength()) {
            return array.eltInternal(0);
        }
        return array.getRuntime().getNil();
    }
    
    public static IRubyObject arrayEntryOrNilOne(final RubyArray array) {
        if (1 < array.getLength()) {
            return array.eltInternal(1);
        }
        return array.getRuntime().getNil();
    }
    
    public static IRubyObject arrayEntryOrNilTwo(final RubyArray array) {
        if (2 < array.getLength()) {
            return array.eltInternal(2);
        }
        return array.getRuntime().getNil();
    }
    
    public static IRubyObject arrayPostOrNil(final RubyArray array, final int pre, final int post, final int index) {
        if (pre + post < array.getLength()) {
            return array.eltInternal(array.getLength() - post + index);
        }
        if (pre + index < array.getLength()) {
            return array.eltInternal(pre + index);
        }
        return array.getRuntime().getNil();
    }
    
    public static IRubyObject arrayPostOrNilZero(final RubyArray array, final int pre, final int post) {
        if (pre + post < array.getLength()) {
            return array.eltInternal(array.getLength() - post + 0);
        }
        if (pre + 0 < array.getLength()) {
            return array.eltInternal(pre + 0);
        }
        return array.getRuntime().getNil();
    }
    
    public static IRubyObject arrayPostOrNilOne(final RubyArray array, final int pre, final int post) {
        if (pre + post < array.getLength()) {
            return array.eltInternal(array.getLength() - post + 1);
        }
        if (pre + 1 < array.getLength()) {
            return array.eltInternal(pre + 1);
        }
        return array.getRuntime().getNil();
    }
    
    public static IRubyObject arrayPostOrNilTwo(final RubyArray array, final int pre, final int post) {
        if (pre + post < array.getLength()) {
            return array.eltInternal(array.getLength() - post + 2);
        }
        if (pre + 2 < array.getLength()) {
            return array.eltInternal(pre + 2);
        }
        return array.getRuntime().getNil();
    }
    
    public static RubyArray subarrayOrEmpty(final RubyArray array, final Ruby runtime, final int index) {
        if (index < array.getLength()) {
            return createSubarray(array, index);
        }
        return RubyArray.newEmptyArray(runtime);
    }
    
    public static RubyArray subarrayOrEmpty(final RubyArray array, final Ruby runtime, final int index, final int post) {
        if (index + post < array.getLength()) {
            return createSubarray(array, index, post);
        }
        return RubyArray.newEmptyArray(runtime);
    }
    
    public static RubyModule checkIsModule(final IRubyObject maybeModule) {
        if (maybeModule instanceof RubyModule) {
            return (RubyModule)maybeModule;
        }
        throw maybeModule.getRuntime().newTypeError(maybeModule + " is not a class/module");
    }
    
    public static IRubyObject getGlobalVariable(final Ruby runtime, final String name) {
        return runtime.getGlobalVariables().get(name);
    }
    
    public static IRubyObject setGlobalVariable(final IRubyObject value, final Ruby runtime, final String name) {
        return runtime.getGlobalVariables().set(name, value);
    }
    
    public static IRubyObject getInstanceVariable(final IRubyObject self, final Ruby runtime, final String internedName) {
        final IRubyObject result = self.getInstanceVariables().fastGetInstanceVariable(internedName);
        if (result != null) {
            return result;
        }
        if (runtime.isVerbose()) {
            warnAboutUninitializedIvar(runtime, internedName);
        }
        return runtime.getNil();
    }
    
    private static void warnAboutUninitializedIvar(final Ruby runtime, final String internedName) {
        runtime.getWarnings().warning(IRubyWarnings.ID.IVAR_NOT_INITIALIZED, "instance variable " + internedName + " not initialized", new Object[0]);
    }
    
    public static IRubyObject setInstanceVariable(final IRubyObject value, final IRubyObject self, final String name) {
        return self.getInstanceVariables().fastSetInstanceVariable(name, value);
    }
    
    public static RubyProc newLiteralLambda(final ThreadContext context, final Block block, final IRubyObject self) {
        return RubyProc.newProc(context.getRuntime(), block, Block.Type.LAMBDA);
    }
    
    public static void fillNil(final IRubyObject[] arr, final int from, final int to, final Ruby runtime) {
        final IRubyObject[] nils = runtime.getNilPrefilledArray();
        int i;
        for (i = from; i + 128 < to; i += 128) {
            System.arraycopy(nils, 0, arr, i, 128);
        }
        System.arraycopy(nils, 0, arr, i, to - i);
    }
    
    public static void fillNil(final IRubyObject[] arr, final Ruby runtime) {
        fillNil(arr, 0, arr.length, runtime);
    }
    
    public static boolean isFastSwitchableString(final IRubyObject str) {
        return str instanceof RubyString;
    }
    
    public static boolean isFastSwitchableSingleCharString(final IRubyObject str) {
        return str instanceof RubyString && ((RubyString)str).getByteList().length() == 1;
    }
    
    public static int getFastSwitchString(final IRubyObject str) {
        final ByteList byteList = ((RubyString)str).getByteList();
        return byteList.hashCode();
    }
    
    public static int getFastSwitchSingleCharString(final IRubyObject str) {
        final ByteList byteList = ((RubyString)str).getByteList();
        return byteList.get(0);
    }
    
    public static boolean isFastSwitchableSymbol(final IRubyObject sym) {
        return sym instanceof RubySymbol;
    }
    
    public static boolean isFastSwitchableSingleCharSymbol(final IRubyObject sym) {
        return sym instanceof RubySymbol && ((RubySymbol)sym).asJavaString().length() == 1;
    }
    
    public static int getFastSwitchSymbol(final IRubyObject sym) {
        final String str = ((RubySymbol)sym).asJavaString();
        return str.hashCode();
    }
    
    public static int getFastSwitchSingleCharSymbol(final IRubyObject sym) {
        final String str = ((RubySymbol)sym).asJavaString();
        return str.charAt(0);
    }
    
    public static Block getBlock(final ThreadContext context, final IRubyObject self, final Node node) {
        final IterNode iter = (IterNode)node;
        iter.getScope().determineModule();
        if (iter.getBlockBody() instanceof InterpretedBlock) {
            return InterpretedBlock.newInterpretedClosure(context, iter.getBlockBody(), self);
        }
        return Interpreted19Block.newInterpretedClosure(context, iter.getBlockBody(), self);
    }
    
    public static Block getBlock(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Node node, final Block aBlock) {
        return getBlockFromBlockPassBody(runtime, node.interpret(runtime, context, self, aBlock), aBlock);
    }
    
    public static RubyBoolean rbEqual(final ThreadContext context, final IRubyObject a, final IRubyObject b) {
        final Ruby runtime = context.getRuntime();
        if (a == b) {
            return runtime.getTrue();
        }
        final IRubyObject res = invokedynamic(context, a, 1, b);
        return runtime.newBoolean(res.isTrue());
    }
    
    public static void traceLine(final ThreadContext context) {
        final String name = context.getFrameName();
        final RubyModule type = context.getFrameKlazz();
        context.getRuntime().callEventHooks(context, RubyEvent.LINE, context.getFile(), context.getLine(), name, type);
    }
    
    public static void traceClass(final ThreadContext context) {
        final String name = context.getFrameName();
        final RubyModule type = context.getFrameKlazz();
        context.getRuntime().callEventHooks(context, RubyEvent.CLASS, context.getFile(), context.getLine(), name, type);
    }
    
    public static void traceEnd(final ThreadContext context) {
        final String name = context.getFrameName();
        final RubyModule type = context.getFrameKlazz();
        context.getRuntime().callEventHooks(context, RubyEvent.END, context.getFile(), context.getLine(), name, type);
    }
    
    public static String interpretAliasUndefName(final Node nameNode, final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        String name;
        if (nameNode instanceof LiteralNode) {
            name = ((LiteralNode)nameNode).getName();
        }
        else {
            assert nameNode instanceof DSymbolNode : "Alias or Undef not literal or dsym";
            name = ((RubySymbol)nameNode.interpret(runtime, context, self, aBlock)).asJavaString();
        }
        return name;
    }
    
    public static void checkArgumentCount(final ThreadContext context, final IRubyObject[] args, final int min, final int max) {
        checkArgumentCount(context, args.length, min, max);
    }
    
    public static void checkArgumentCount(final ThreadContext context, final IRubyObject[] args, final int req) {
        checkArgumentCount(context, args.length, req, req);
    }
    
    public static void checkArgumentCount(final ThreadContext context, final int length, final int min, final int max) {
        int expected = 0;
        if (length < min) {
            expected = min;
        }
        else {
            if (max <= -1 || length <= max) {
                return;
            }
            expected = max;
        }
        throw context.getRuntime().newArgumentError(length, expected);
    }
    
    public static boolean isModuleAndHasConstant(final IRubyObject left, final String name) {
        return left instanceof RubyModule && ((RubyModule)left).fastGetConstantFromNoConstMissing(name) != null;
    }
    
    public static ByteList getDefinedConstantOrBoundMethod(final IRubyObject left, final String name) {
        if (isModuleAndHasConstant(left, name)) {
            return Node.CONSTANT_BYTELIST;
        }
        if (left.getMetaClass().isMethodBound(name, true)) {
            return Node.METHOD_BYTELIST;
        }
        return null;
    }
    
    public static RubyModule getSuperClassForDefined(final Ruby runtime, final RubyModule klazz) {
        RubyModule superklazz = klazz.getSuperClass();
        if (superklazz == null && klazz.isModule()) {
            superklazz = runtime.getObject();
        }
        return superklazz;
    }
    
    public static boolean isGenerationEqual(final IRubyObject object, final int generation) {
        RubyClass metaClass;
        if (object instanceof RubyBasicObject) {
            metaClass = ((RubyBasicObject)object).getMetaClass();
        }
        else {
            metaClass = object.getMetaClass();
        }
        return metaClass.getCacheToken() == generation;
    }
    
    public static String[] getScopeNames(final String scopeNames) {
        final StringTokenizer toker = new StringTokenizer(scopeNames, ";");
        final ArrayList list = new ArrayList(10);
        while (toker.hasMoreTokens()) {
            list.add(toker.nextToken().intern());
        }
        return list.toArray(new String[list.size()]);
    }
    
    public static IRubyObject[] arraySlice1N(IRubyObject arrayish) {
        arrayish = aryToAry(arrayish);
        final RubyArray arrayish2 = ensureMultipleAssignableRubyArray(arrayish, arrayish.getRuntime(), true);
        return new IRubyObject[] { arrayEntryOrNilZero(arrayish2), subarrayOrEmpty(arrayish2, arrayish2.getRuntime(), 1) };
    }
    
    public static IRubyObject arraySlice1(IRubyObject arrayish) {
        arrayish = aryToAry(arrayish);
        final RubyArray arrayish2 = ensureMultipleAssignableRubyArray(arrayish, arrayish.getRuntime(), true);
        return arrayEntryOrNilZero(arrayish2);
    }
    
    public static RubyClass metaclass(final IRubyObject object) {
        return (object instanceof RubyBasicObject) ? ((RubyBasicObject)object).getMetaClass() : object.getMetaClass();
    }
    
    public static String rawBytesToString(final byte[] bytes) {
        final char[] chars = new char[bytes.length];
        for (int i = 0; i < bytes.length; ++i) {
            chars[i] = (char)bytes[i];
        }
        return new String(chars);
    }
    
    public static byte[] stringToRawBytes(final String string) {
        final char[] chars = string.toCharArray();
        final byte[] bytes = new byte[chars.length];
        for (int i = 0; i < chars.length; ++i) {
            bytes[i] = (byte)chars[i];
        }
        return bytes;
    }
    
    public static String encodeCaptureOffsets(final int[] scopeOffsets) {
        final char[] encoded = new char[scopeOffsets.length * 2];
        for (int i = 0; i < scopeOffsets.length; ++i) {
            final int offDepth = scopeOffsets[i];
            final char off = (char)(offDepth & 0xFFFF);
            final char depth = (char)(offDepth >> 16);
            encoded[2 * i] = off;
            encoded[2 * i + 1] = depth;
        }
        return new String(encoded);
    }
    
    public static int[] decodeCaptureOffsets(final String encoded) {
        final char[] chars = encoded.toCharArray();
        final int[] scopeOffsets = new int[chars.length / 2];
        for (int i = 0; i < scopeOffsets.length; ++i) {
            final char off = chars[2 * i];
            final char depth = chars[2 * i + 1];
            scopeOffsets[i] = (depth << 16 | off);
        }
        return scopeOffsets;
    }
    
    public static IRubyObject match2AndUpdateScope(final IRubyObject receiver, final ThreadContext context, final IRubyObject value, final String scopeOffsets) {
        final DynamicScope scope = context.getCurrentScope();
        final IRubyObject match = ((RubyRegexp)receiver).op_match(context, value);
        updateScopeWithCaptures(context, scope, decodeCaptureOffsets(scopeOffsets), value);
        return match;
    }
    
    public static void updateScopeWithCaptures(final ThreadContext context, final DynamicScope scope, final int[] scopeOffsets, final IRubyObject result) {
        final Ruby runtime = context.runtime;
        if (result.isNil()) {
            final IRubyObject nil = runtime.getNil();
            for (int i = 0; i < scopeOffsets.length; ++i) {
                scope.setValue(nil, scopeOffsets[i], 0);
            }
        }
        else {
            final RubyMatchData matchData = (RubyMatchData)scope.getBackRef(runtime);
            final IRubyObject[] namedValues = matchData.getNamedBackrefValues(runtime);
            for (int j = 0; j < scopeOffsets.length; ++j) {
                scope.setValue(namedValues[j], scopeOffsets[j] & 0xFFFF, scopeOffsets[j] >> 16);
            }
        }
    }
    
    public static RubyArray argsPush(final RubyArray first, final IRubyObject second) {
        return ((RubyArray)first.dup()).append(second);
    }
    
    public static RubyArray argsCat(final IRubyObject first, final IRubyObject second) {
        final Ruby runtime = first.getRuntime();
        IRubyObject secondArgs;
        if (runtime.is1_9()) {
            secondArgs = splatValue19(second);
        }
        else {
            secondArgs = splatValue(second);
        }
        return ((RubyArray)ensureRubyArray(runtime, first).dup()).concat(secondArgs);
    }
    
    public static String encodeParameterList(final ArgsNode argsNode) {
        final StringBuilder builder = new StringBuilder();
        boolean added = false;
        if (argsNode.getPre() != null) {
            for (final Node preNode : argsNode.getPre().childNodes()) {
                if (added) {
                    builder.append(';');
                }
                added = true;
                if (preNode instanceof MultipleAsgn19Node) {
                    builder.append("nil");
                }
                else {
                    builder.append("q").append(((ArgumentNode)preNode).getName());
                }
            }
        }
        if (argsNode.getOptArgs() != null) {
            for (final Node optNode : argsNode.getOptArgs().childNodes()) {
                if (added) {
                    builder.append(';');
                }
                added = true;
                builder.append("o");
                if (optNode instanceof OptArgNode) {
                    builder.append(((OptArgNode)optNode).getName());
                }
                else if (optNode instanceof LocalAsgnNode) {
                    builder.append(((LocalAsgnNode)optNode).getName());
                }
                else {
                    if (!(optNode instanceof DAsgnNode)) {
                        continue;
                    }
                    builder.append(((DAsgnNode)optNode).getName());
                }
            }
        }
        if (argsNode.getRestArg() >= 0) {
            if (added) {
                builder.append(';');
            }
            added = true;
            if (argsNode.getRestArgNode() instanceof UnnamedRestArgNode) {
                if (((UnnamedRestArgNode)argsNode.getRestArgNode()).isStar()) {
                    builder.append("R");
                }
            }
            else {
                builder.append("r").append(argsNode.getRestArgNode().getName());
            }
        }
        if (argsNode.getPost() != null) {
            for (final Node postNode : argsNode.getPost().childNodes()) {
                if (added) {
                    builder.append(';');
                }
                added = true;
                if (postNode instanceof MultipleAsgn19Node) {
                    builder.append("nil");
                }
                else {
                    builder.append("q").append(((ArgumentNode)postNode).getName());
                }
            }
        }
        if (argsNode.getBlock() != null) {
            if (added) {
                builder.append(';');
            }
            added = true;
            builder.append("b").append(argsNode.getBlock().getName());
        }
        if (!added) {
            builder.append("NONE");
        }
        return builder.toString();
    }
    
    public static RubyArray parameterListToParameters(final Ruby runtime, final String[] parameterList, final boolean isLambda) {
        final RubyArray parms = RubyArray.newEmptyArray(runtime);
        for (final String param : parameterList) {
            if (param.equals("NONE")) {
                break;
            }
            final RubyArray elem = RubyArray.newEmptyArray(runtime);
            Label_0282: {
                if (param.equals("nil")) {
                    elem.add(RubySymbol.newSymbol(runtime, isLambda ? "req" : "opt"));
                    parms.add(elem);
                }
                else {
                    if (param.charAt(0) == 'q') {
                        elem.add(RubySymbol.newSymbol(runtime, isLambda ? "req" : "opt"));
                    }
                    else if (param.charAt(0) == 'r') {
                        elem.add(RubySymbol.newSymbol(runtime, "rest"));
                    }
                    else {
                        if (param.charAt(0) == 'R') {
                            elem.add(RubySymbol.newSymbol(runtime, "rest"));
                            parms.add(elem);
                            break Label_0282;
                        }
                        if (param.charAt(0) == 'o') {
                            elem.add(RubySymbol.newSymbol(runtime, "opt"));
                            if (param.length() == 1) {
                                parms.add(elem);
                                break Label_0282;
                            }
                        }
                        else if (param.charAt(0) == 'b') {
                            elem.add(RubySymbol.newSymbol(runtime, "block"));
                        }
                    }
                    elem.add(RubySymbol.newSymbol(runtime, param.substring(1)));
                    parms.add(elem);
                }
            }
        }
        return parms;
    }
    
    public static ByteList getDefinedCall(final ThreadContext context, final IRubyObject self, final IRubyObject receiver, final String name) {
        final RubyClass metaClass = receiver.getMetaClass();
        final DynamicMethod method = metaClass.searchMethod(name);
        final Visibility visibility = method.getVisibility();
        if (visibility != Visibility.PRIVATE && (visibility != Visibility.PROTECTED || metaClass.getRealClass().isInstance(self)) && !method.isUndefined()) {
            return Node.METHOD_BYTELIST;
        }
        if (context.getRuntime().is1_9() && receiver.callMethod(context, "respond_to_missing?", new IRubyObject[] { context.getRuntime().newSymbol(name), context.getRuntime().getFalse() }).isTrue()) {
            return Node.METHOD_BYTELIST;
        }
        return null;
    }
    
    public static ByteList getDefinedNot(final Ruby runtime, ByteList definition) {
        if (definition != null && runtime.is1_9()) {
            definition = Node.METHOD_BYTELIST;
        }
        return definition;
    }
    
    public static IRubyObject invokedynamic(final ThreadContext context, final IRubyObject self, final int index) {
        final RubyClass metaclass = self.getMetaClass();
        final String name = MethodIndex.METHOD_NAMES[index];
        return getMethodCached(context, metaclass, index, name).call(context, self, metaclass, name);
    }
    
    public static IRubyObject invokedynamic(final ThreadContext context, final IRubyObject self, final int index, final IRubyObject arg0) {
        final RubyClass metaclass = self.getMetaClass();
        final String name = MethodIndex.METHOD_NAMES[index];
        return getMethodCached(context, metaclass, index, name).call(context, self, metaclass, name, arg0);
    }
    
    private static DynamicMethod getMethodCached(final ThreadContext context, final RubyClass metaclass, final int index, final String name) {
        if (metaclass.index >= 39) {
            return metaclass.searchMethod(name);
        }
        return context.runtimeCache.getMethod(context, metaclass, metaclass.index * (index + 1), name);
    }
    
    private static class MethodMissingMethod extends DynamicMethod
    {
        private final DynamicMethod delegate;
        private final CallType lastCallStatus;
        
        public MethodMissingMethod(final DynamicMethod delegate, final CallType lastCallStatus) {
            this.delegate = delegate;
            this.lastCallStatus = lastCallStatus;
        }
        
        public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
            context.setLastCallStatus(this.lastCallStatus);
            return this.delegate.call(context, self, clazz, "method_missing", prepareMethodMissingArgs(args, context, name), block);
        }
        
        public DynamicMethod dup() {
            return this;
        }
    }
}
