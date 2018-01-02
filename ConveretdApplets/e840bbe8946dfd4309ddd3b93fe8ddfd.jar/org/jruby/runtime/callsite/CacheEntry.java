// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.callsite;

import org.jruby.internal.runtime.methods.UndefinedMethod;
import org.jruby.RubyClass;
import org.jruby.internal.runtime.methods.DynamicMethod;

public class CacheEntry
{
    public static final CacheEntry NULL_CACHE;
    public final DynamicMethod method;
    public final int token;
    
    public CacheEntry(final DynamicMethod method, final int token) {
        this.method = method;
        this.token = token;
    }
    
    public final boolean typeOk(final RubyClass incomingType) {
        return typeOk(this, incomingType);
    }
    
    public static final boolean typeOk(final CacheEntry entry, final RubyClass incomingType) {
        return entry.token == incomingType.getCacheToken();
    }
    
    static {
        NULL_CACHE = new CacheEntry(UndefinedMethod.INSTANCE, 0);
    }
}
