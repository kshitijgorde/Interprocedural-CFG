// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.runtime.CallType;
import org.jruby.runtime.Visibility;
import org.jruby.runtime.Block;
import org.jruby.RubyModule;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;

public class UndefinedMethod extends DynamicMethod
{
    public static final UndefinedMethod INSTANCE;
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule klazz, final String name, final IRubyObject[] args, final Block block) {
        throw new UnsupportedOperationException("BUG: invoking UndefinedMethod.call; report at http://bugs.jruby.org");
    }
    
    public DynamicMethod dup() {
        return UndefinedMethod.INSTANCE;
    }
    
    public static UndefinedMethod getInstance() {
        return UndefinedMethod.INSTANCE;
    }
    
    public void setImplementationClass(final RubyModule implClass) {
    }
    
    public void setVisibility(final Visibility visibility) {
    }
    
    public void setCallConfig(final CallConfiguration callConfig) {
    }
    
    public boolean isCallableFrom(final IRubyObject caller, final CallType callType) {
        return true;
    }
    
    static {
        INSTANCE = new UndefinedMethod();
    }
}
