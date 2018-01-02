// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.util.CodegenUtils;
import org.jruby.exceptions.JumpException;
import org.jruby.exceptions.RaiseException;
import org.jruby.RubyLocalJumpError;
import org.jruby.Ruby;
import org.jruby.runtime.Arity;
import org.jruby.MetaClass;
import org.jruby.runtime.CallType;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;

public abstract class DynamicMethod
{
    protected RubyModule implementationClass;
    protected RubyModule protectedClass;
    protected Visibility visibility;
    protected CallConfiguration callConfig;
    protected long serialNumber;
    protected boolean builtin;
    protected NativeCall nativeCall;
    protected String name;
    protected boolean notImplemented;
    
    protected DynamicMethod(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
        this.builtin = false;
        this.notImplemented = false;
        assert implementationClass != null;
        this.init(implementationClass, visibility, callConfig);
    }
    
    protected DynamicMethod(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig, final String name) {
        this(implementationClass, visibility, callConfig);
        this.name = name;
    }
    
    protected DynamicMethod() {
        this.builtin = false;
        this.notImplemented = false;
    }
    
    protected void init(final RubyModule implementationClass, final Visibility visibility, final CallConfiguration callConfig) {
        this.visibility = visibility;
        this.implementationClass = implementationClass;
        this.protectedClass = calculateProtectedClass(implementationClass);
        this.callConfig = callConfig;
        this.serialNumber = implementationClass.getRuntime().getNextDynamicMethodSerial();
    }
    
    public long getSerialNumber() {
        return this.serialNumber;
    }
    
    public boolean isBuiltin() {
        return this.builtin;
    }
    
    public void setIsBuiltin(final boolean isBuiltin) {
        this.builtin = isBuiltin;
    }
    
    public abstract IRubyObject call(final ThreadContext p0, final IRubyObject p1, final RubyModule p2, final String p3, final IRubyObject[] p4, final Block p5);
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args) {
        return this.call(context, self, clazz, name, args, Block.NULL_BLOCK);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name) {
        return this.call(context, self, klazz, name, Block.NULL_BLOCK);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final Block block) {
        return this.call(context, self, klazz, name, IRubyObject.NULL_ARRAY, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg0) {
        return this.call(context, self, klazz, name, arg0, Block.NULL_BLOCK);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg0, final Block block) {
        return this.call(context, self, klazz, name, new IRubyObject[] { arg0 }, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg0, final IRubyObject arg1) {
        return this.call(context, self, klazz, name, arg0, arg1, Block.NULL_BLOCK);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        return this.call(context, self, klazz, name, new IRubyObject[] { arg0, arg1 }, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        return this.call(context, self, klazz, name, arg0, arg1, arg2, Block.NULL_BLOCK);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        return this.call(context, self, klazz, name, new IRubyObject[] { arg0, arg1, arg2 }, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3) {
        return this.call(context, self, klazz, name, arg0, arg1, arg2, arg3, Block.NULL_BLOCK);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final Block block) {
        return this.call(context, self, klazz, name, new IRubyObject[] { arg0, arg1, arg2, arg3 }, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4) {
        return this.call(context, self, klazz, name, arg0, arg1, arg2, arg3, arg4, Block.NULL_BLOCK);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final Block block) {
        return this.call(context, self, klazz, name, new IRubyObject[] { arg0, arg1, arg2, arg3, arg4 }, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5) {
        return this.call(context, self, klazz, name, arg0, arg1, arg2, arg3, arg4, arg5, Block.NULL_BLOCK);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final Block block) {
        return this.call(context, self, klazz, name, new IRubyObject[] { arg0, arg1, arg2, arg3, arg4, arg5 }, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6) {
        return this.call(context, self, klazz, name, arg0, arg1, arg2, arg3, arg4, arg5, arg6, Block.NULL_BLOCK);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final Block block) {
        return this.call(context, self, klazz, name, new IRubyObject[] { arg0, arg1, arg2, arg3, arg4, arg5, arg6 }, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final IRubyObject arg7) {
        return this.call(context, self, klazz, name, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, Block.NULL_BLOCK);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final IRubyObject arg7, final Block block) {
        return this.call(context, self, klazz, name, new IRubyObject[] { arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7 }, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final IRubyObject arg7, final IRubyObject arg8) {
        return this.call(context, self, klazz, name, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, Block.NULL_BLOCK);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final IRubyObject arg7, final IRubyObject arg8, final Block block) {
        return this.call(context, self, klazz, name, new IRubyObject[] { arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8 }, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final IRubyObject arg7, final IRubyObject arg8, final IRubyObject arg9) {
        return this.call(context, self, klazz, name, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, Block.NULL_BLOCK);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final IRubyObject arg4, final IRubyObject arg5, final IRubyObject arg6, final IRubyObject arg7, final IRubyObject arg8, final IRubyObject arg9, final Block block) {
        return this.call(context, self, klazz, name, new IRubyObject[] { arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9 }, block);
    }
    
    public abstract DynamicMethod dup();
    
    public boolean isCallableFrom(final IRubyObject caller, final CallType callType) {
        switch (this.visibility) {
            case PUBLIC: {
                return true;
            }
            case PRIVATE: {
                return callType != CallType.NORMAL;
            }
            case PROTECTED: {
                return this.protectedAccessOk(caller);
            }
            default: {
                return true;
            }
        }
    }
    
    private boolean protectedAccessOk(final IRubyObject caller) {
        return this.getProtectedClass().isInstance(caller);
    }
    
    protected static RubyModule calculateProtectedClass(RubyModule cls) {
        if (cls.isSingleton()) {
            cls = cls.getSuperClass();
        }
        while (cls.isIncluded()) {
            cls = cls.getMetaClass();
        }
        if (cls instanceof MetaClass) {
            cls = ((MetaClass)cls).getRealClass();
        }
        return cls;
    }
    
    protected RubyModule getProtectedClass() {
        return this.protectedClass;
    }
    
    public RubyModule getImplementationClass() {
        return this.implementationClass;
    }
    
    public void setImplementationClass(final RubyModule implClass) {
        this.implementationClass = implClass;
        this.protectedClass = calculateProtectedClass(implClass);
    }
    
    public Visibility getVisibility() {
        return this.visibility;
    }
    
    public void setVisibility(final Visibility visibility) {
        this.visibility = visibility;
    }
    
    public final boolean isUndefined() {
        return this == UndefinedMethod.INSTANCE;
    }
    
    public Arity getArity() {
        return Arity.optional();
    }
    
    public DynamicMethod getRealMethod() {
        return this;
    }
    
    public CallConfiguration getCallConfig() {
        return this.callConfig;
    }
    
    public void setCallConfig(final CallConfiguration callConfig) {
        this.callConfig = callConfig;
    }
    
    public void setNativeCall(final Class nativeTarget, final String nativeName, final Class nativeReturn, final Class[] nativeSignature, final boolean statik) {
        this.nativeCall = new NativeCall(nativeTarget, nativeName, nativeReturn, nativeSignature, statik);
    }
    
    public NativeCall getNativeCall() {
        return this.nativeCall;
    }
    
    public boolean isNative() {
        return false;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public boolean isNotImplemented() {
        return this.notImplemented;
    }
    
    public void setNotImplemented(final boolean setNotImplemented) {
        this.notImplemented = setNotImplemented;
    }
    
    protected IRubyObject handleRedo(final Ruby runtime) throws RaiseException {
        throw runtime.newLocalJumpError(RubyLocalJumpError.Reason.REDO, runtime.getNil(), "unexpected redo");
    }
    
    protected IRubyObject handleReturn(final ThreadContext context, final JumpException.ReturnJump rj, final int callNumber) {
        if (rj.getTarget() == callNumber) {
            return (IRubyObject)rj.getValue();
        }
        throw rj;
    }
    
    protected IRubyObject handleBreak(final ThreadContext context, final Ruby runtime, final JumpException.BreakJump bj, final int callNumber) {
        if (bj.getTarget() == callNumber) {
            throw runtime.newLocalJumpError(RubyLocalJumpError.Reason.BREAK, runtime.getNil(), "unexpected break");
        }
        throw bj;
    }
    
    public static class NativeCall
    {
        private final Class nativeTarget;
        private final String nativeName;
        private final Class nativeReturn;
        private final Class[] nativeSignature;
        private final boolean statik;
        
        public NativeCall(final Class nativeTarget, final String nativeName, final Class nativeReturn, final Class[] nativeSignature, final boolean statik) {
            this.nativeTarget = nativeTarget;
            this.nativeName = nativeName;
            this.nativeReturn = nativeReturn;
            this.nativeSignature = nativeSignature;
            this.statik = statik;
        }
        
        public Class getNativeTarget() {
            return this.nativeTarget;
        }
        
        public String getNativeName() {
            return this.nativeName;
        }
        
        public Class getNativeReturn() {
            return this.nativeReturn;
        }
        
        public Class[] getNativeSignature() {
            return this.nativeSignature;
        }
        
        public boolean isStatic() {
            return this.statik;
        }
        
        public String toString() {
            return "" + (this.statik ? "static " : "") + this.nativeReturn.getName() + " " + this.nativeTarget.getName() + "." + this.nativeName + CodegenUtils.prettyParams(this.nativeSignature);
        }
    }
}
