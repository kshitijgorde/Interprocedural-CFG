// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.invokedynamic;

import java.dyn.MutableCallSite;
import java.dyn.NoAccessException;
import org.jruby.RubyLocalJumpError;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.exceptions.JumpException;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.RubyBasicObject;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.internal.runtime.methods.Framing;
import org.jruby.internal.runtime.methods.CompiledMethod;
import org.jruby.ast.executable.AbstractScript;
import org.jruby.util.SafePropertyAccessor;
import org.jruby.runtime.callsite.CacheEntry;
import java.dyn.Linkage;
import org.jruby.util.CodegenUtils;
import org.jruby.compiler.impl.SkinnyMethodAdapter;
import org.jruby.org.objectweb.asm.MethodVisitor;
import java.dyn.MethodHandles;
import org.jruby.runtime.CallType;
import java.dyn.CallSite;
import java.dyn.MethodHandle;
import java.dyn.MethodType;

public class InvokeDynamicSupport
{
    private static final MethodType BOOTSTRAP_TYPE;
    public static final MethodHandle BOOTSTRAP;
    private static final MethodHandle GETMETHOD;
    private static final MethodHandle PGC;
    private static final MethodHandle TEST;
    private static final MethodHandle PGC_0;
    private static final MethodHandle GETMETHOD_0;
    private static final MethodHandle TEST_0;
    private static final MethodHandle TARGET_0;
    private static final MethodHandle FALLBACK_0;
    private static final MethodHandle PGC_1;
    private static final MethodHandle GETMETHOD_1;
    private static final MethodHandle TEST_1;
    private static final MethodHandle TARGET_1;
    private static final MethodHandle FALLBACK_1;
    private static final MethodHandle PGC_2;
    private static final MethodHandle GETMETHOD_2;
    private static final MethodHandle TEST_2;
    private static final MethodHandle TARGET_2;
    private static final MethodHandle FALLBACK_2;
    private static final MethodHandle PGC_3;
    private static final MethodHandle GETMETHOD_3;
    private static final MethodHandle TEST_3;
    private static final MethodHandle TARGET_3;
    private static final MethodHandle FALLBACK_3;
    private static final MethodHandle PGC_N;
    private static final MethodHandle GETMETHOD_N;
    private static final MethodHandle TEST_N;
    private static final MethodHandle TARGET_N;
    private static final MethodHandle FALLBACK_N;
    private static final MethodHandle BREAKJUMP;
    private static final MethodHandle RETRYJUMP;
    private static final MethodHandle PGC_0_B;
    private static final MethodHandle GETMETHOD_0_B;
    private static final MethodHandle TEST_0_B;
    private static final MethodHandle TARGET_0_B;
    private static final MethodHandle FALLBACK_0_B;
    private static final MethodHandle PGC_1_B;
    private static final MethodHandle GETMETHOD_1_B;
    private static final MethodHandle TEST_1_B;
    private static final MethodHandle TARGET_1_B;
    private static final MethodHandle FALLBACK_1_B;
    private static final MethodHandle PGC_2_B;
    private static final MethodHandle GETMETHOD_2_B;
    private static final MethodHandle TEST_2_B;
    private static final MethodHandle TARGET_2_B;
    private static final MethodHandle FALLBACK_2_B;
    private static final MethodHandle PGC_3_B;
    private static final MethodHandle GETMETHOD_3_B;
    private static final MethodHandle TEST_3_B;
    private static final MethodHandle TARGET_3_B;
    private static final MethodHandle FALLBACK_3_B;
    private static final MethodHandle PGC_N_B;
    private static final MethodHandle GETMETHOD_N_B;
    private static final MethodHandle TEST_N_B;
    private static final MethodHandle TARGET_N_B;
    private static final MethodHandle FALLBACK_N_B;
    
    public static CallSite bootstrap(final Class caller, final String name, final MethodType type) {
        JRubyCallSite site;
        if (name == "call") {
            site = new JRubyCallSite(type, CallType.NORMAL);
        }
        else {
            site = new JRubyCallSite(type, CallType.FUNCTIONAL);
        }
        final MethodType fallbackType = type.insertParameterTypes(0, new Class[] { JRubyCallSite.class });
        final MethodHandle myFallback = MethodHandles.insertArguments(findStatic(InvokeDynamicSupport.class, "fallback", fallbackType), 0, new Object[] { site });
        site.setTarget(myFallback);
        return (CallSite)site;
    }
    
    public static void installBytecode(final MethodVisitor method, final String classname) {
        final SkinnyMethodAdapter mv = new SkinnyMethodAdapter(method);
        mv.ldc(CodegenUtils.c(classname));
        mv.invokestatic(CodegenUtils.p(Class.class), "forName", CodegenUtils.sig(Class.class, CodegenUtils.params(String.class)));
        mv.getstatic(CodegenUtils.p(InvokeDynamicSupport.class), "BOOTSTRAP", CodegenUtils.ci(MethodHandle.class));
        mv.invokestatic(CodegenUtils.p(Linkage.class), "registerBootstrapMethod", CodegenUtils.sig(Void.TYPE, Class.class, MethodHandle.class));
    }
    
    private static MethodHandle createGWT(final MethodHandle test, final MethodHandle target, final MethodHandle fallback, final CacheEntry entry, final JRubyCallSite site) {
        if (entry.method.getNativeCall() != null) {
            final DynamicMethod.NativeCall nativeCall = entry.method.getNativeCall();
            final Class[] nativeSig = nativeCall.getNativeSignature();
            if (SafePropertyAccessor.getBoolean("jruby.compile.invokedynamic.rubyDirect", true) && nativeSig.length > 0 && AbstractScript.class.isAssignableFrom(nativeSig[0]) && entry.method instanceof CompiledMethod) {
                if (entry.method.getCallConfig().framing() == Framing.None) {
                    return createRubyGWT(nativeCall, test, fallback, entry, site);
                }
            }
            else if (SafePropertyAccessor.getBoolean("jruby.compile.invokedynamic.nativeDirect", true) && getArgCount(nativeSig, nativeCall.isStatic()) != -1 && nativeSig.length > 0 && nativeSig[0] == ThreadContext.class && nativeSig[nativeSig.length - 1] != Block.class) {
                return createNativeGWT(nativeCall, test, fallback, entry, site);
            }
        }
        final MethodHandle myTest = MethodHandles.insertArguments(test, 0, new Object[] { entry.token });
        final MethodHandle myTarget = MethodHandles.insertArguments(target, 0, new Object[] { entry });
        final MethodHandle myFallback = MethodHandles.insertArguments(fallback, 0, new Object[] { site });
        final MethodHandle guardWithTest = MethodHandles.guardWithTest(myTest, myTarget, myFallback);
        return MethodHandles.convertArguments(guardWithTest, site.type());
    }
    
    private static MethodHandle createNativeGWT(final DynamicMethod.NativeCall nativeCall, final MethodHandle test, final MethodHandle fallback, final CacheEntry entry, final JRubyCallSite site) {
        try {
            final boolean isStatic = nativeCall.isStatic();
            MethodHandle nativeTarget;
            if (isStatic) {
                nativeTarget = MethodHandles.lookup().findStatic(nativeCall.getNativeTarget(), nativeCall.getNativeName(), MethodType.methodType(nativeCall.getNativeReturn(), nativeCall.getNativeSignature()));
            }
            else {
                nativeTarget = MethodHandles.lookup().findVirtual(nativeCall.getNativeTarget(), nativeCall.getNativeName(), MethodType.methodType(nativeCall.getNativeReturn(), nativeCall.getNativeSignature()));
            }
            final int argCount = getArgCount(nativeCall.getNativeSignature(), nativeCall.isStatic());
            switch (argCount) {
                case 0: {
                    final MethodHandle methodHandle = nativeTarget;
                    final MethodType type = site.type();
                    int[] array2;
                    if (isStatic) {
                        final int[] array = array2 = new int[2];
                        array[0] = 0;
                        array[1] = 2;
                    }
                    else {
                        final int[] array3 = array2 = new int[2];
                        array3[0] = 2;
                        array3[1] = 0;
                    }
                    nativeTarget = MethodHandles.permuteArguments(methodHandle, type, array2);
                    break;
                }
                case -1:
                case 1: {
                    final MethodHandle methodHandle2 = nativeTarget;
                    final MethodType type2 = site.type();
                    int[] array5;
                    if (isStatic) {
                        final int[] array4 = array5 = new int[3];
                        array4[0] = 0;
                        array4[array4[1] = 2] = 4;
                    }
                    else {
                        final int[] array6 = array5 = new int[3];
                        array6[0] = 2;
                        array6[1] = 0;
                        array6[2] = 4;
                    }
                    nativeTarget = MethodHandles.permuteArguments(methodHandle2, type2, array5);
                    break;
                }
                case 2: {
                    final MethodHandle methodHandle3 = nativeTarget;
                    final MethodType type3 = site.type();
                    int[] array8;
                    if (isStatic) {
                        final int[] array7 = array8 = new int[4];
                        array7[0] = 0;
                        array7[array7[1] = 2] = 4;
                        array7[3] = 5;
                    }
                    else {
                        final int[] array9 = array8 = new int[4];
                        array9[0] = 2;
                        array9[1] = 0;
                        array9[2] = 4;
                        array9[3] = 5;
                    }
                    nativeTarget = MethodHandles.permuteArguments(methodHandle3, type3, array8);
                    break;
                }
                case 3: {
                    final MethodHandle methodHandle4 = nativeTarget;
                    final MethodType type4 = site.type();
                    int[] array11;
                    if (isStatic) {
                        final int[] array10 = array11 = new int[5];
                        array10[0] = 0;
                        array10[array10[1] = 2] = 4;
                        array10[3] = 5;
                        array10[4] = 6;
                    }
                    else {
                        final int[] array12 = array11 = new int[5];
                        array12[0] = 2;
                        array12[1] = 0;
                        array12[2] = 4;
                        array12[3] = 5;
                        array12[4] = 6;
                    }
                    nativeTarget = MethodHandles.permuteArguments(methodHandle4, type4, array11);
                    break;
                }
                default: {
                    throw new RuntimeException("unknown arg count: " + argCount);
                }
            }
            final MethodHandle myFallback = MethodHandles.insertArguments(fallback, 0, new Object[] { site });
            final MethodHandle myTest = MethodHandles.insertArguments(test, 0, new Object[] { entry.token });
            final MethodHandle gwt = MethodHandles.guardWithTest(myTest, nativeTarget, myFallback);
            return MethodHandles.convertArguments(gwt, site.type());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private static MethodHandle createRubyGWT(final DynamicMethod.NativeCall nativeCall, final MethodHandle test, final MethodHandle fallback, final CacheEntry entry, final JRubyCallSite site) {
        try {
            MethodHandle nativeTarget = MethodHandles.lookup().findStatic(nativeCall.getNativeTarget(), nativeCall.getNativeName(), MethodType.methodType(nativeCall.getNativeReturn(), nativeCall.getNativeSignature()));
            final CompiledMethod cm = (CompiledMethod)entry.method;
            nativeTarget = MethodHandles.insertArguments(nativeTarget, 0, new Object[] { cm.getScriptObject() });
            nativeTarget = MethodHandles.insertArguments(nativeTarget, nativeTarget.type().parameterCount() - 1, new Object[] { Block.NULL_BLOCK });
            final int argCount = getRubyArgCount(nativeCall.getNativeSignature());
            switch (argCount) {
                case 0: {
                    nativeTarget = MethodHandles.permuteArguments(nativeTarget, site.type(), new int[] { 0, 2 });
                    break;
                }
                case -1:
                case 1: {
                    nativeTarget = MethodHandles.permuteArguments(nativeTarget, site.type(), new int[] { 0, 2, 4 });
                    break;
                }
                case 2: {
                    nativeTarget = MethodHandles.permuteArguments(nativeTarget, site.type(), new int[] { 0, 2, 4, 5 });
                    break;
                }
                case 3: {
                    nativeTarget = MethodHandles.permuteArguments(nativeTarget, site.type(), new int[] { 0, 2, 4, 5, 6 });
                    break;
                }
                default: {
                    throw new RuntimeException("unknown arg count: " + argCount);
                }
            }
            final MethodHandle myFallback = MethodHandles.insertArguments(fallback, 0, new Object[] { site });
            final MethodHandle myTest = MethodHandles.insertArguments(test, 0, new Object[] { entry.token });
            final MethodHandle gwt = MethodHandles.guardWithTest(myTest, nativeTarget, myFallback);
            return MethodHandles.convertArguments(gwt, site.type());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private static int getArgCount(final Class[] args, final boolean isStatic) {
        int length = args.length;
        boolean hasContext = false;
        if (isStatic) {
            if (args.length > 1 && args[0] == ThreadContext.class) {
                --length;
                hasContext = true;
            }
            if (args.length > 1 && args[args.length - 1] == Block.class) {
                --length;
            }
            if (--length == 1) {
                if (hasContext && args[2] == IRubyObject[].class) {
                    length = -1;
                }
                else if (args[1] == IRubyObject[].class) {
                    length = -1;
                }
            }
        }
        else {
            if (args.length > 0 && args[0] == ThreadContext.class) {
                --length;
                hasContext = true;
            }
            if (args.length > 0 && args[args.length - 1] == Block.class) {
                --length;
            }
            if (length == 1) {
                if (hasContext && args[1] == IRubyObject[].class) {
                    length = -1;
                }
                else if (args[0] == IRubyObject[].class) {
                    length = -1;
                }
            }
        }
        return length;
    }
    
    private static int getRubyArgCount(final Class[] args) {
        return args.length - 4;
    }
    
    public static boolean test(final int token, final IRubyObject self) {
        return token == ((RubyBasicObject)self).getMetaClass().getCacheToken();
    }
    
    public static IRubyObject fallback(final JRubyCallSite site, final ThreadContext context, final IRubyObject caller, final IRubyObject self, final String name) {
        final RubyClass selfClass = pollAndGetClass(context, self);
        final CacheEntry entry = selfClass.searchWithCache(name);
        if (methodMissing(entry, site.callType(), name, caller)) {
            return callMethodMissing(entry, site.callType(), context, self, name);
        }
        site.setTarget(createGWT(InvokeDynamicSupport.TEST_0, InvokeDynamicSupport.TARGET_0, InvokeDynamicSupport.FALLBACK_0, entry, site));
        return entry.method.call(context, self, selfClass, name);
    }
    
    public static IRubyObject fallback(final JRubyCallSite site, final ThreadContext context, final IRubyObject caller, final IRubyObject self, final String name, final IRubyObject arg0) {
        final RubyClass selfClass = pollAndGetClass(context, self);
        final CacheEntry entry = selfClass.searchWithCache(name);
        if (methodMissing(entry, site.callType(), name, caller)) {
            return callMethodMissing(entry, site.callType(), context, self, name, arg0);
        }
        site.setTarget(createGWT(InvokeDynamicSupport.TEST_1, InvokeDynamicSupport.TARGET_1, InvokeDynamicSupport.FALLBACK_1, entry, site));
        return entry.method.call(context, self, selfClass, name, arg0);
    }
    
    public static IRubyObject fallback(final JRubyCallSite site, final ThreadContext context, final IRubyObject caller, final IRubyObject self, final String name, final IRubyObject arg0, final IRubyObject arg1) {
        final RubyClass selfClass = pollAndGetClass(context, self);
        final CacheEntry entry = selfClass.searchWithCache(name);
        if (methodMissing(entry, site.callType(), name, caller)) {
            return callMethodMissing(entry, site.callType(), context, self, name, arg0, arg1);
        }
        site.setTarget(createGWT(InvokeDynamicSupport.TEST_2, InvokeDynamicSupport.TARGET_2, InvokeDynamicSupport.FALLBACK_2, entry, site));
        return entry.method.call(context, self, selfClass, name, arg0, arg1);
    }
    
    public static IRubyObject fallback(final JRubyCallSite site, final ThreadContext context, final IRubyObject caller, final IRubyObject self, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        final RubyClass selfClass = pollAndGetClass(context, self);
        final CacheEntry entry = selfClass.searchWithCache(name);
        if (methodMissing(entry, site.callType(), name, caller)) {
            return callMethodMissing(entry, site.callType(), context, self, name, arg0, arg1, arg2);
        }
        site.setTarget(createGWT(InvokeDynamicSupport.TEST_3, InvokeDynamicSupport.TARGET_3, InvokeDynamicSupport.FALLBACK_3, entry, site));
        return entry.method.call(context, self, selfClass, name, arg0, arg1, arg2);
    }
    
    public static IRubyObject fallback(final JRubyCallSite site, final ThreadContext context, final IRubyObject caller, final IRubyObject self, final String name, final IRubyObject[] args) {
        final RubyClass selfClass = pollAndGetClass(context, self);
        final CacheEntry entry = selfClass.searchWithCache(name);
        if (methodMissing(entry, site.callType(), name, caller)) {
            return callMethodMissing(entry, site.callType(), context, self, name, args);
        }
        site.setTarget(createGWT(InvokeDynamicSupport.TEST_N, InvokeDynamicSupport.TARGET_N, InvokeDynamicSupport.FALLBACK_N, entry, site));
        return entry.method.call(context, self, selfClass, name, args);
    }
    
    public static IRubyObject fallback(final JRubyCallSite site, final ThreadContext context, final IRubyObject caller, final IRubyObject self, final String name, final Block block) {
        final RubyClass selfClass = pollAndGetClass(context, self);
        final CacheEntry entry = selfClass.searchWithCache(name);
        try {
            if (methodMissing(entry, site.callType(), name, caller)) {
                return callMethodMissing(entry, site.callType(), context, self, name, block);
            }
            site.setTarget(createGWT(InvokeDynamicSupport.TEST_0_B, InvokeDynamicSupport.TARGET_0_B, InvokeDynamicSupport.FALLBACK_0_B, entry, site));
            return entry.method.call(context, self, selfClass, name, block);
        }
        catch (JumpException.BreakJump bj) {
            return handleBreakJump(context, bj);
        }
        catch (JumpException.RetryJump rj) {
            return retryJumpError(context);
        }
        finally {
            block.escape();
        }
    }
    
    public static IRubyObject fallback(final JRubyCallSite site, final ThreadContext context, final IRubyObject caller, final IRubyObject self, final String name, final IRubyObject arg0, final Block block) {
        final RubyClass selfClass = pollAndGetClass(context, self);
        final CacheEntry entry = selfClass.searchWithCache(name);
        try {
            if (methodMissing(entry, site.callType(), name, caller)) {
                return callMethodMissing(entry, site.callType(), context, self, name, arg0, block);
            }
            site.setTarget(createGWT(InvokeDynamicSupport.TEST_1_B, InvokeDynamicSupport.TARGET_1_B, InvokeDynamicSupport.FALLBACK_1_B, entry, site));
            return entry.method.call(context, self, selfClass, name, arg0, block);
        }
        catch (JumpException.BreakJump bj) {
            return handleBreakJump(context, bj);
        }
        catch (JumpException.RetryJump rj) {
            return retryJumpError(context);
        }
        finally {
            block.escape();
        }
    }
    
    public static IRubyObject fallback(final JRubyCallSite site, final ThreadContext context, final IRubyObject caller, final IRubyObject self, final String name, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        final RubyClass selfClass = pollAndGetClass(context, self);
        final CacheEntry entry = selfClass.searchWithCache(name);
        try {
            if (methodMissing(entry, site.callType(), name, caller)) {
                return callMethodMissing(entry, site.callType(), context, self, name, arg0, arg1, block);
            }
            site.setTarget(createGWT(InvokeDynamicSupport.TEST_2_B, InvokeDynamicSupport.TARGET_2_B, InvokeDynamicSupport.FALLBACK_2_B, entry, site));
            return entry.method.call(context, self, selfClass, name, arg0, arg1, block);
        }
        catch (JumpException.BreakJump bj) {
            return handleBreakJump(context, bj);
        }
        catch (JumpException.RetryJump rj) {
            return retryJumpError(context);
        }
        finally {
            block.escape();
        }
    }
    
    public static IRubyObject fallback(final JRubyCallSite site, final ThreadContext context, final IRubyObject caller, final IRubyObject self, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        final RubyClass selfClass = pollAndGetClass(context, self);
        final CacheEntry entry = selfClass.searchWithCache(name);
        try {
            if (methodMissing(entry, site.callType(), name, caller)) {
                return callMethodMissing(entry, site.callType(), context, self, name, arg0, arg1, arg2, block);
            }
            site.setTarget(createGWT(InvokeDynamicSupport.TEST_3_B, InvokeDynamicSupport.TARGET_3_B, InvokeDynamicSupport.FALLBACK_3_B, entry, site));
            return entry.method.call(context, self, selfClass, name, arg0, arg1, arg2, block);
        }
        catch (JumpException.BreakJump bj) {
            return handleBreakJump(context, bj);
        }
        catch (JumpException.RetryJump rj) {
            return retryJumpError(context);
        }
        finally {
            block.escape();
        }
    }
    
    public static IRubyObject fallback(final JRubyCallSite site, final ThreadContext context, final IRubyObject caller, final IRubyObject self, final String name, final IRubyObject[] args, final Block block) {
        final RubyClass selfClass = pollAndGetClass(context, self);
        final CacheEntry entry = selfClass.searchWithCache(name);
        try {
            if (methodMissing(entry, site.callType(), name, caller)) {
                return callMethodMissing(entry, site.callType(), context, self, name, args, block);
            }
            site.setTarget(createGWT(InvokeDynamicSupport.TEST_N_B, InvokeDynamicSupport.TARGET_N_B, InvokeDynamicSupport.FALLBACK_N_B, entry, site));
            return entry.method.call(context, self, selfClass, name, args, block);
        }
        catch (JumpException.BreakJump bj) {
            return handleBreakJump(context, bj);
        }
        catch (JumpException.RetryJump rj) {
            return retryJumpError(context);
        }
        finally {
            block.escape();
        }
    }
    
    protected static boolean methodMissing(final CacheEntry entry, final CallType callType, final String name, final IRubyObject caller) {
        final DynamicMethod method = entry.method;
        return method.isUndefined() || (callType == CallType.NORMAL && !name.equals("method_missing") && !method.isCallableFrom(caller, callType));
    }
    
    private static IRubyObject callMethodMissing(final CacheEntry entry, final CallType callType, final ThreadContext context, final IRubyObject self, final String name, final IRubyObject[] args) {
        return RuntimeHelpers.selectMethodMissing(context, self, entry.method.getVisibility(), name, callType).call(context, self, self.getMetaClass(), name, args, Block.NULL_BLOCK);
    }
    
    private static IRubyObject callMethodMissing(final CacheEntry entry, final CallType callType, final ThreadContext context, final IRubyObject self, final String name) {
        return RuntimeHelpers.selectMethodMissing(context, self, entry.method.getVisibility(), name, callType).call(context, self, self.getMetaClass(), name, Block.NULL_BLOCK);
    }
    
    private static IRubyObject callMethodMissing(final CacheEntry entry, final CallType callType, final ThreadContext context, final IRubyObject self, final String name, final Block block) {
        return RuntimeHelpers.selectMethodMissing(context, self, entry.method.getVisibility(), name, callType).call(context, self, self.getMetaClass(), name, block);
    }
    
    private static IRubyObject callMethodMissing(final CacheEntry entry, final CallType callType, final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg) {
        return RuntimeHelpers.selectMethodMissing(context, self, entry.method.getVisibility(), name, callType).call(context, self, self.getMetaClass(), name, arg, Block.NULL_BLOCK);
    }
    
    private static IRubyObject callMethodMissing(final CacheEntry entry, final CallType callType, final ThreadContext context, final IRubyObject self, final String name, final IRubyObject[] args, final Block block) {
        return RuntimeHelpers.selectMethodMissing(context, self, entry.method.getVisibility(), name, callType).call(context, self, self.getMetaClass(), name, args, block);
    }
    
    private static IRubyObject callMethodMissing(final CacheEntry entry, final CallType callType, final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg0, final Block block) {
        return RuntimeHelpers.selectMethodMissing(context, self, entry.method.getVisibility(), name, callType).call(context, self, self.getMetaClass(), name, arg0, block);
    }
    
    private static IRubyObject callMethodMissing(final CacheEntry entry, final CallType callType, final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg0, final IRubyObject arg1) {
        return RuntimeHelpers.selectMethodMissing(context, self, entry.method.getVisibility(), name, callType).call(context, self, self.getMetaClass(), name, arg0, arg1, Block.NULL_BLOCK);
    }
    
    private static IRubyObject callMethodMissing(final CacheEntry entry, final CallType callType, final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        return RuntimeHelpers.selectMethodMissing(context, self, entry.method.getVisibility(), name, callType).call(context, self, self.getMetaClass(), name, arg0, arg1, block);
    }
    
    private static IRubyObject callMethodMissing(final CacheEntry entry, final CallType callType, final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        return RuntimeHelpers.selectMethodMissing(context, self, entry.method.getVisibility(), name, callType).call(context, self, self.getMetaClass(), name, arg0, arg1, arg2, Block.NULL_BLOCK);
    }
    
    private static IRubyObject callMethodMissing(final CacheEntry entry, final CallType callType, final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        return RuntimeHelpers.selectMethodMissing(context, self, entry.method.getVisibility(), name, callType).call(context, self, self.getMetaClass(), name, arg0, arg1, arg2, block);
    }
    
    public static RubyClass pollAndGetClass(final ThreadContext context, final IRubyObject self) {
        context.callThreadPoll();
        final RubyClass selfType = self.getMetaClass();
        return selfType;
    }
    
    public static IRubyObject handleBreakJump(final JumpException.BreakJump bj, final ThreadContext context) throws JumpException.BreakJump {
        if (context.getFrameJumpTarget() == bj.getTarget()) {
            return (IRubyObject)bj.getValue();
        }
        throw bj;
    }
    
    private static IRubyObject handleBreakJump(final ThreadContext context, final JumpException.BreakJump bj) throws JumpException.BreakJump {
        if (context.getFrameJumpTarget() == bj.getTarget()) {
            return (IRubyObject)bj.getValue();
        }
        throw bj;
    }
    
    public static IRubyObject retryJumpError(final ThreadContext context) {
        throw context.getRuntime().newLocalJumpError(RubyLocalJumpError.Reason.RETRY, context.getRuntime().getNil(), "retry outside of rescue not supported");
    }
    
    public static final DynamicMethod getMethod(final CacheEntry entry) {
        return entry.method;
    }
    
    private static MethodHandle dropNameAndArgs(final MethodHandle original, final int index, final int count, final boolean block) {
        switch (count) {
            case -1: {
                if (block) {
                    return MethodHandles.dropArguments(original, index, new Class[] { String.class, IRubyObject[].class, Block.class });
                }
                return MethodHandles.dropArguments(original, index, new Class[] { String.class, IRubyObject[].class });
            }
            case 0: {
                if (block) {
                    return MethodHandles.dropArguments(original, index, new Class[] { String.class, Block.class });
                }
                return MethodHandles.dropArguments(original, index, new Class[] { String.class });
            }
            case 1: {
                if (block) {
                    return MethodHandles.dropArguments(original, index, new Class[] { String.class, IRubyObject.class, Block.class });
                }
                return MethodHandles.dropArguments(original, index, new Class[] { String.class, IRubyObject.class });
            }
            case 2: {
                if (block) {
                    return MethodHandles.dropArguments(original, index, new Class[] { String.class, IRubyObject.class, IRubyObject.class, Block.class });
                }
                return MethodHandles.dropArguments(original, index, new Class[] { String.class, IRubyObject.class, IRubyObject.class });
            }
            case 3: {
                if (block) {
                    return MethodHandles.dropArguments(original, index, new Class[] { String.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, Block.class });
                }
                return MethodHandles.dropArguments(original, index, new Class[] { String.class, IRubyObject.class, IRubyObject.class, IRubyObject.class });
            }
            default: {
                throw new RuntimeException("Invalid arg count (" + count + ") while preparing method handle:\n\t" + original);
            }
        }
    }
    
    private static MethodHandle findStatic(final Class target, final String name, final MethodType type) {
        try {
            return MethodHandles.lookup().findStatic(target, name, type);
        }
        catch (NoAccessException nae) {
            throw new RuntimeException((Throwable)nae);
        }
    }
    
    private static MethodHandle findVirtual(final Class target, final String name, final MethodType type) {
        try {
            return MethodHandles.lookup().findVirtual(target, name, type);
        }
        catch (NoAccessException nae) {
            throw new RuntimeException((Throwable)nae);
        }
    }
    
    static {
        BOOTSTRAP_TYPE = MethodType.methodType((Class)CallSite.class, (Class)Class.class, new Class[] { String.class, MethodType.class });
        BOOTSTRAP = findStatic(InvokeDynamicSupport.class, "bootstrap", InvokeDynamicSupport.BOOTSTRAP_TYPE);
        MethodHandle getMethod = findStatic(InvokeDynamicSupport.class, "getMethod", MethodType.methodType((Class)DynamicMethod.class, (Class)CacheEntry.class));
        getMethod = MethodHandles.dropArguments(getMethod, 0, new Class[] { RubyClass.class });
        getMethod = (GETMETHOD = MethodHandles.dropArguments(getMethod, 2, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class }));
        PGC = MethodHandles.dropArguments(MethodHandles.dropArguments(findStatic(InvokeDynamicSupport.class, "pollAndGetClass", MethodType.methodType((Class)RubyClass.class, (Class)ThreadContext.class, new Class[] { IRubyObject.class })), 1, new Class[] { IRubyObject.class }), 0, new Class[] { CacheEntry.class });
        TEST = MethodHandles.dropArguments(findStatic(InvokeDynamicSupport.class, "test", MethodType.methodType((Class)Boolean.TYPE, (Class)Integer.TYPE, new Class[] { IRubyObject.class })), 1, new Class[] { ThreadContext.class, IRubyObject.class });
        PGC_0 = dropNameAndArgs(InvokeDynamicSupport.PGC, 4, 0, false);
        GETMETHOD_0 = dropNameAndArgs(InvokeDynamicSupport.GETMETHOD, 5, 0, false);
        TEST_0 = dropNameAndArgs(InvokeDynamicSupport.TEST, 4, 0, false);
        MethodHandle target = findVirtual(DynamicMethod.class, "call", MethodType.methodType((Class)IRubyObject.class, (Class)ThreadContext.class, new Class[] { IRubyObject.class, RubyModule.class, String.class }));
        target = MethodHandles.convertArguments(target, MethodType.methodType((Class)IRubyObject.class, (Class)DynamicMethod.class, new Class[] { ThreadContext.class, IRubyObject.class, RubyClass.class, String.class }));
        target = MethodHandles.permuteArguments(target, MethodType.methodType((Class)IRubyObject.class, (Class)DynamicMethod.class, new Class[] { RubyClass.class, CacheEntry.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class }), new int[] { 0, 3, 5, 1, 6 });
        target = MethodHandles.foldArguments(target, InvokeDynamicSupport.GETMETHOD_0);
        target = (TARGET_0 = MethodHandles.foldArguments(target, InvokeDynamicSupport.PGC_0));
        FALLBACK_0 = findStatic(InvokeDynamicSupport.class, "fallback", MethodType.methodType((Class)IRubyObject.class, (Class)JRubyCallSite.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class }));
        PGC_1 = dropNameAndArgs(InvokeDynamicSupport.PGC, 4, 1, false);
        GETMETHOD_1 = dropNameAndArgs(InvokeDynamicSupport.GETMETHOD, 5, 1, false);
        TEST_1 = dropNameAndArgs(InvokeDynamicSupport.TEST, 4, 1, false);
        target = findVirtual(DynamicMethod.class, "call", MethodType.methodType((Class)IRubyObject.class, (Class)ThreadContext.class, new Class[] { IRubyObject.class, RubyModule.class, String.class, IRubyObject.class }));
        target = MethodHandles.convertArguments(target, MethodType.methodType((Class)IRubyObject.class, (Class)DynamicMethod.class, new Class[] { ThreadContext.class, IRubyObject.class, RubyClass.class, String.class, IRubyObject.class }));
        target = MethodHandles.permuteArguments(target, MethodType.methodType((Class)IRubyObject.class, (Class)DynamicMethod.class, new Class[] { RubyClass.class, CacheEntry.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, IRubyObject.class }), new int[] { 0, 3, 5, 1, 6, 7 });
        target = MethodHandles.foldArguments(target, InvokeDynamicSupport.GETMETHOD_1);
        target = (TARGET_1 = MethodHandles.foldArguments(target, InvokeDynamicSupport.PGC_1));
        FALLBACK_1 = findStatic(InvokeDynamicSupport.class, "fallback", MethodType.methodType((Class)IRubyObject.class, (Class)JRubyCallSite.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, IRubyObject.class }));
        PGC_2 = dropNameAndArgs(InvokeDynamicSupport.PGC, 4, 2, false);
        GETMETHOD_2 = dropNameAndArgs(InvokeDynamicSupport.GETMETHOD, 5, 2, false);
        TEST_2 = dropNameAndArgs(InvokeDynamicSupport.TEST, 4, 2, false);
        target = findVirtual(DynamicMethod.class, "call", MethodType.methodType((Class)IRubyObject.class, (Class)ThreadContext.class, new Class[] { IRubyObject.class, RubyModule.class, String.class, IRubyObject.class, IRubyObject.class }));
        target = MethodHandles.convertArguments(target, MethodType.methodType((Class)IRubyObject.class, (Class)DynamicMethod.class, new Class[] { ThreadContext.class, IRubyObject.class, RubyClass.class, String.class, IRubyObject.class, IRubyObject.class }));
        target = MethodHandles.permuteArguments(target, MethodType.methodType((Class)IRubyObject.class, (Class)DynamicMethod.class, new Class[] { RubyClass.class, CacheEntry.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, IRubyObject.class, IRubyObject.class }), new int[] { 0, 3, 5, 1, 6, 7, 8 });
        target = MethodHandles.foldArguments(target, InvokeDynamicSupport.GETMETHOD_2);
        target = (TARGET_2 = MethodHandles.foldArguments(target, InvokeDynamicSupport.PGC_2));
        FALLBACK_2 = findStatic(InvokeDynamicSupport.class, "fallback", MethodType.methodType((Class)IRubyObject.class, (Class)JRubyCallSite.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, IRubyObject.class, IRubyObject.class }));
        PGC_3 = dropNameAndArgs(InvokeDynamicSupport.PGC, 4, 3, false);
        GETMETHOD_3 = dropNameAndArgs(InvokeDynamicSupport.GETMETHOD, 5, 3, false);
        TEST_3 = dropNameAndArgs(InvokeDynamicSupport.TEST, 4, 3, false);
        target = findVirtual(DynamicMethod.class, "call", MethodType.methodType((Class)IRubyObject.class, (Class)ThreadContext.class, new Class[] { IRubyObject.class, RubyModule.class, String.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }));
        target = MethodHandles.convertArguments(target, MethodType.methodType((Class)IRubyObject.class, (Class)DynamicMethod.class, new Class[] { ThreadContext.class, IRubyObject.class, RubyClass.class, String.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }));
        target = MethodHandles.permuteArguments(target, MethodType.methodType((Class)IRubyObject.class, (Class)DynamicMethod.class, new Class[] { RubyClass.class, CacheEntry.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }), new int[] { 0, 3, 5, 1, 6, 7, 8, 9 });
        target = MethodHandles.foldArguments(target, InvokeDynamicSupport.GETMETHOD_3);
        target = (TARGET_3 = MethodHandles.foldArguments(target, InvokeDynamicSupport.PGC_3));
        FALLBACK_3 = findStatic(InvokeDynamicSupport.class, "fallback", MethodType.methodType((Class)IRubyObject.class, (Class)JRubyCallSite.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, IRubyObject.class, IRubyObject.class, IRubyObject.class }));
        PGC_N = dropNameAndArgs(InvokeDynamicSupport.PGC, 4, -1, false);
        GETMETHOD_N = dropNameAndArgs(InvokeDynamicSupport.GETMETHOD, 5, -1, false);
        TEST_N = dropNameAndArgs(InvokeDynamicSupport.TEST, 4, -1, false);
        target = findVirtual(DynamicMethod.class, "call", MethodType.methodType((Class)IRubyObject.class, (Class)ThreadContext.class, new Class[] { IRubyObject.class, RubyModule.class, String.class, IRubyObject[].class }));
        target = MethodHandles.convertArguments(target, MethodType.methodType((Class)IRubyObject.class, (Class)DynamicMethod.class, new Class[] { ThreadContext.class, IRubyObject.class, RubyClass.class, String.class, IRubyObject[].class }));
        target = MethodHandles.permuteArguments(target, MethodType.methodType((Class)IRubyObject.class, (Class)DynamicMethod.class, new Class[] { RubyClass.class, CacheEntry.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, IRubyObject[].class }), new int[] { 0, 3, 5, 1, 6, 7 });
        target = MethodHandles.foldArguments(target, InvokeDynamicSupport.GETMETHOD_N);
        target = (TARGET_N = MethodHandles.foldArguments(target, InvokeDynamicSupport.PGC_N));
        FALLBACK_N = findStatic(InvokeDynamicSupport.class, "fallback", MethodType.methodType((Class)IRubyObject.class, (Class)JRubyCallSite.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, IRubyObject[].class }));
        MethodHandle breakJump = findStatic(InvokeDynamicSupport.class, "handleBreakJump", MethodType.methodType((Class)IRubyObject.class, (Class)JumpException.BreakJump.class, new Class[] { ThreadContext.class }));
        breakJump = (BREAKJUMP = MethodHandles.permuteArguments(breakJump, MethodType.methodType((Class)IRubyObject.class, (Class)JumpException.BreakJump.class, new Class[] { CacheEntry.class, ThreadContext.class, IRubyObject.class, IRubyObject.class }), new int[] { 0, 2 }));
        MethodHandle retryJump = findStatic(InvokeDynamicSupport.class, "retryJumpError", MethodType.methodType((Class)IRubyObject.class, (Class)ThreadContext.class));
        retryJump = (RETRYJUMP = MethodHandles.permuteArguments(retryJump, MethodType.methodType((Class)IRubyObject.class, (Class)JumpException.RetryJump.class, new Class[] { CacheEntry.class, ThreadContext.class, IRubyObject.class, IRubyObject.class }), new int[] { 2 }));
        PGC_0_B = dropNameAndArgs(InvokeDynamicSupport.PGC, 4, 0, true);
        GETMETHOD_0_B = dropNameAndArgs(InvokeDynamicSupport.GETMETHOD, 5, 0, true);
        TEST_0_B = dropNameAndArgs(InvokeDynamicSupport.TEST, 4, 0, true);
        target = findVirtual(DynamicMethod.class, "call", MethodType.methodType((Class)IRubyObject.class, (Class)ThreadContext.class, new Class[] { IRubyObject.class, RubyModule.class, String.class, Block.class }));
        target = MethodHandles.convertArguments(target, MethodType.methodType((Class)IRubyObject.class, (Class)DynamicMethod.class, new Class[] { ThreadContext.class, IRubyObject.class, RubyClass.class, String.class, Block.class }));
        target = MethodHandles.permuteArguments(target, MethodType.methodType((Class)IRubyObject.class, (Class)DynamicMethod.class, new Class[] { RubyClass.class, CacheEntry.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, Block.class }), new int[] { 0, 3, 5, 1, 6, 7 });
        target = MethodHandles.foldArguments(target, InvokeDynamicSupport.GETMETHOD_0_B);
        target = MethodHandles.foldArguments(target, InvokeDynamicSupport.PGC_0_B);
        MethodHandle breakJump2 = dropNameAndArgs(InvokeDynamicSupport.BREAKJUMP, 5, 0, true);
        MethodHandle retryJump2 = dropNameAndArgs(InvokeDynamicSupport.RETRYJUMP, 5, 0, true);
        target = MethodHandles.catchException(target, (Class)JumpException.BreakJump.class, breakJump2);
        target = (TARGET_0_B = MethodHandles.catchException(target, (Class)JumpException.RetryJump.class, retryJump2));
        FALLBACK_0_B = findStatic(InvokeDynamicSupport.class, "fallback", MethodType.methodType((Class)IRubyObject.class, (Class)JRubyCallSite.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, Block.class }));
        PGC_1_B = dropNameAndArgs(InvokeDynamicSupport.PGC, 4, 1, true);
        GETMETHOD_1_B = dropNameAndArgs(InvokeDynamicSupport.GETMETHOD, 5, 1, true);
        TEST_1_B = dropNameAndArgs(InvokeDynamicSupport.TEST, 4, 1, true);
        target = findVirtual(DynamicMethod.class, "call", MethodType.methodType((Class)IRubyObject.class, (Class)ThreadContext.class, new Class[] { IRubyObject.class, RubyModule.class, String.class, IRubyObject.class, Block.class }));
        target = MethodHandles.convertArguments(target, MethodType.methodType((Class)IRubyObject.class, (Class)DynamicMethod.class, new Class[] { ThreadContext.class, IRubyObject.class, RubyClass.class, String.class, IRubyObject.class, Block.class }));
        target = MethodHandles.permuteArguments(target, MethodType.methodType((Class)IRubyObject.class, (Class)DynamicMethod.class, new Class[] { RubyClass.class, CacheEntry.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, IRubyObject.class, Block.class }), new int[] { 0, 3, 5, 1, 6, 7, 8 });
        target = MethodHandles.foldArguments(target, InvokeDynamicSupport.GETMETHOD_1_B);
        target = MethodHandles.foldArguments(target, InvokeDynamicSupport.PGC_1_B);
        breakJump2 = dropNameAndArgs(InvokeDynamicSupport.BREAKJUMP, 5, 1, true);
        retryJump2 = dropNameAndArgs(InvokeDynamicSupport.RETRYJUMP, 5, 1, true);
        target = MethodHandles.catchException(target, (Class)JumpException.BreakJump.class, breakJump2);
        target = (TARGET_1_B = MethodHandles.catchException(target, (Class)JumpException.RetryJump.class, retryJump2));
        FALLBACK_1_B = findStatic(InvokeDynamicSupport.class, "fallback", MethodType.methodType((Class)IRubyObject.class, (Class)JRubyCallSite.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, IRubyObject.class, Block.class }));
        PGC_2_B = dropNameAndArgs(InvokeDynamicSupport.PGC, 4, 2, true);
        GETMETHOD_2_B = dropNameAndArgs(InvokeDynamicSupport.GETMETHOD, 5, 2, true);
        TEST_2_B = dropNameAndArgs(InvokeDynamicSupport.TEST, 4, 2, true);
        target = findVirtual(DynamicMethod.class, "call", MethodType.methodType((Class)IRubyObject.class, (Class)ThreadContext.class, new Class[] { IRubyObject.class, RubyModule.class, String.class, IRubyObject.class, IRubyObject.class, Block.class }));
        target = MethodHandles.convertArguments(target, MethodType.methodType((Class)IRubyObject.class, (Class)DynamicMethod.class, new Class[] { ThreadContext.class, IRubyObject.class, RubyClass.class, String.class, IRubyObject.class, IRubyObject.class, Block.class }));
        target = MethodHandles.permuteArguments(target, MethodType.methodType((Class)IRubyObject.class, (Class)DynamicMethod.class, new Class[] { RubyClass.class, CacheEntry.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, IRubyObject.class, IRubyObject.class, Block.class }), new int[] { 0, 3, 5, 1, 6, 7, 8, 9 });
        target = MethodHandles.foldArguments(target, InvokeDynamicSupport.GETMETHOD_2_B);
        target = MethodHandles.foldArguments(target, InvokeDynamicSupport.PGC_2_B);
        breakJump2 = dropNameAndArgs(InvokeDynamicSupport.BREAKJUMP, 5, 2, true);
        retryJump2 = dropNameAndArgs(InvokeDynamicSupport.RETRYJUMP, 5, 2, true);
        target = MethodHandles.catchException(target, (Class)JumpException.BreakJump.class, breakJump2);
        target = (TARGET_2_B = MethodHandles.catchException(target, (Class)JumpException.RetryJump.class, retryJump2));
        FALLBACK_2_B = findStatic(InvokeDynamicSupport.class, "fallback", MethodType.methodType((Class)IRubyObject.class, (Class)JRubyCallSite.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, IRubyObject.class, IRubyObject.class, Block.class }));
        PGC_3_B = dropNameAndArgs(InvokeDynamicSupport.PGC, 4, 3, true);
        GETMETHOD_3_B = dropNameAndArgs(InvokeDynamicSupport.GETMETHOD, 5, 3, true);
        TEST_3_B = dropNameAndArgs(InvokeDynamicSupport.TEST, 4, 3, true);
        target = findVirtual(DynamicMethod.class, "call", MethodType.methodType((Class)IRubyObject.class, (Class)ThreadContext.class, new Class[] { IRubyObject.class, RubyModule.class, String.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, Block.class }));
        target = MethodHandles.convertArguments(target, MethodType.methodType((Class)IRubyObject.class, (Class)DynamicMethod.class, new Class[] { ThreadContext.class, IRubyObject.class, RubyClass.class, String.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, Block.class }));
        target = MethodHandles.permuteArguments(target, MethodType.methodType((Class)IRubyObject.class, (Class)DynamicMethod.class, new Class[] { RubyClass.class, CacheEntry.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, Block.class }), new int[] { 0, 3, 5, 1, 6, 7, 8, 9, 10 });
        target = MethodHandles.foldArguments(target, InvokeDynamicSupport.GETMETHOD_3_B);
        target = MethodHandles.foldArguments(target, InvokeDynamicSupport.PGC_3_B);
        breakJump2 = dropNameAndArgs(InvokeDynamicSupport.BREAKJUMP, 5, 3, true);
        retryJump2 = dropNameAndArgs(InvokeDynamicSupport.RETRYJUMP, 5, 3, true);
        target = MethodHandles.catchException(target, (Class)JumpException.BreakJump.class, breakJump2);
        target = (TARGET_3_B = MethodHandles.catchException(target, (Class)JumpException.RetryJump.class, retryJump2));
        FALLBACK_3_B = findStatic(InvokeDynamicSupport.class, "fallback", MethodType.methodType((Class)IRubyObject.class, (Class)JRubyCallSite.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, Block.class }));
        PGC_N_B = dropNameAndArgs(InvokeDynamicSupport.PGC, 4, -1, true);
        GETMETHOD_N_B = dropNameAndArgs(InvokeDynamicSupport.GETMETHOD, 5, -1, true);
        TEST_N_B = dropNameAndArgs(InvokeDynamicSupport.TEST, 4, -1, true);
        target = findVirtual(DynamicMethod.class, "call", MethodType.methodType((Class)IRubyObject.class, (Class)ThreadContext.class, new Class[] { IRubyObject.class, RubyModule.class, String.class, IRubyObject[].class, Block.class }));
        target = MethodHandles.convertArguments(target, MethodType.methodType((Class)IRubyObject.class, (Class)DynamicMethod.class, new Class[] { ThreadContext.class, IRubyObject.class, RubyClass.class, String.class, IRubyObject[].class, Block.class }));
        target = MethodHandles.permuteArguments(target, MethodType.methodType((Class)IRubyObject.class, (Class)DynamicMethod.class, new Class[] { RubyClass.class, CacheEntry.class, ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, IRubyObject[].class, Block.class }), new int[] { 0, 3, 5, 1, 6, 7, 8 });
        target = MethodHandles.foldArguments(target, InvokeDynamicSupport.GETMETHOD_N_B);
        target = MethodHandles.foldArguments(target, InvokeDynamicSupport.PGC_N_B);
        breakJump2 = dropNameAndArgs(InvokeDynamicSupport.BREAKJUMP, 5, -1, true);
        retryJump2 = dropNameAndArgs(InvokeDynamicSupport.RETRYJUMP, 5, -1, true);
        target = MethodHandles.catchException(target, (Class)JumpException.BreakJump.class, breakJump2);
        target = (TARGET_N_B = MethodHandles.catchException(target, (Class)JumpException.RetryJump.class, retryJump2));
        FALLBACK_N_B = findStatic(InvokeDynamicSupport.class, "fallback", MethodType.methodType((Class)IRubyObject.class, (Class)JRubyCallSite.class, new Class[] { ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, IRubyObject[].class, Block.class }));
    }
    
    public static class JRubyCallSite extends MutableCallSite
    {
        private final CallType callType;
        private final MethodType type;
        
        public JRubyCallSite(final MethodType type, final CallType callType) {
            super(type);
            this.type = type;
            this.callType = callType;
        }
        
        public CallType callType() {
            return this.callType;
        }
        
        public MethodType type() {
            return this.type;
        }
    }
}
