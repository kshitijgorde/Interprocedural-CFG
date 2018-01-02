// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.runtime.Arity;
import org.jruby.runtime.CallType;
import org.jruby.runtime.Visibility;
import org.jruby.runtime.Block;
import org.jruby.RubyModule;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;

public abstract class DelegatingDynamicMethod extends DynamicMethod
{
    protected final DynamicMethod delegate;
    
    public DelegatingDynamicMethod(final DynamicMethod delegate) {
        super(delegate.getImplementationClass(), delegate.getVisibility(), delegate.getCallConfig());
        this.delegate = delegate;
    }
    
    public DynamicMethod getDelegate() {
        return this.delegate;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name) {
        return this.delegate.call(context, self, klazz, name);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg) {
        return this.delegate.call(context, self, klazz, name, arg);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg1, final IRubyObject arg2) {
        return this.delegate.call(context, self, klazz, name, arg1, arg2);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3) {
        return this.delegate.call(context, self, klazz, name, arg1, arg2, arg3);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject[] args) {
        return this.delegate.call(context, self, klazz, name, args);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final Block block) {
        return this.delegate.call(context, self, klazz, name, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg1, final Block block) {
        return this.delegate.call(context, self, klazz, name, arg1, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        return this.delegate.call(context, self, klazz, name, arg1, arg2, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final Block block) {
        return this.delegate.call(context, self, klazz, name, arg1, arg2, arg3, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject[] args, final Block block) {
        return this.delegate.call(context, self, klazz, name, args, block);
    }
    
    public void setVisibility(final Visibility visibility) {
        this.delegate.setVisibility(visibility);
    }
    
    public void setNativeCall(final Class nativeTarget, final String nativeName, final Class nativeReturn, final Class[] nativeSignature, final boolean statik) {
        this.delegate.setNativeCall(nativeTarget, nativeName, nativeReturn, nativeSignature, statik);
    }
    
    public void setIsBuiltin(final boolean isBuiltin) {
        this.delegate.setIsBuiltin(isBuiltin);
    }
    
    public void setImplementationClass(final RubyModule implClass) {
        this.delegate.setImplementationClass(implClass);
    }
    
    public void setCallConfig(final CallConfiguration callConfig) {
        this.delegate.setCallConfig(callConfig);
    }
    
    public boolean isNative() {
        return this.delegate.isNative();
    }
    
    public boolean isCallableFrom(final IRubyObject caller, final CallType callType) {
        return this.delegate.isCallableFrom(caller, callType);
    }
    
    public boolean isBuiltin() {
        return this.delegate.isBuiltin();
    }
    
    public Visibility getVisibility() {
        return this.delegate.getVisibility();
    }
    
    public long getSerialNumber() {
        return this.delegate.getSerialNumber();
    }
    
    public DynamicMethod getRealMethod() {
        return this.delegate.getRealMethod();
    }
    
    protected RubyModule getProtectedClass() {
        return this.delegate.getProtectedClass();
    }
    
    public NativeCall getNativeCall() {
        return this.delegate.getNativeCall();
    }
    
    public RubyModule getImplementationClass() {
        return this.delegate.getImplementationClass();
    }
    
    public CallConfiguration getCallConfig() {
        return this.delegate.getCallConfig();
    }
    
    public Arity getArity() {
        return this.delegate.getArity();
    }
}
