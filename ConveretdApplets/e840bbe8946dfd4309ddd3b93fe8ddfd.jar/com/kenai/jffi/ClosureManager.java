// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

import java.lang.ref.SoftReference;
import java.util.WeakHashMap;
import java.lang.ref.Reference;
import java.util.Map;

public class ClosureManager
{
    private final Map<CallContext, Reference<ClosurePool>> poolMap;
    
    public static final ClosureManager getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    private ClosureManager() {
        this.poolMap = new WeakHashMap<CallContext, Reference<ClosurePool>>();
    }
    
    public final Closure.Handle newClosure(final Closure closure, final Type returnType, final Type[] parameterTypes, final CallingConvention convention) {
        return this.newClosure(closure, CallContextCache.getInstance().getCallContext(returnType, parameterTypes, convention));
    }
    
    public final Closure.Handle newClosure(final Closure closure, final CallContext callContext) {
        final ClosurePool pool = this.getClosurePool(callContext);
        return pool.newClosureHandle(closure);
    }
    
    private final synchronized ClosurePool getClosurePool(final CallContext callContext) {
        final Reference<ClosurePool> ref = this.poolMap.get(callContext);
        ClosurePool pool;
        if (ref != null && (pool = ref.get()) != null) {
            return pool;
        }
        this.poolMap.put(callContext, new SoftReference<ClosurePool>(pool = new ClosurePool(callContext)));
        return pool;
    }
    
    private static final class SingletonHolder
    {
        static final ClosureManager INSTANCE;
        
        static {
            INSTANCE = new ClosureManager(null);
        }
    }
}
