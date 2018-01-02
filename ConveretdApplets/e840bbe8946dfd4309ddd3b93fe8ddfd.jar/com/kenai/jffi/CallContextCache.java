// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.ref.ReferenceQueue;
import java.util.Map;

public class CallContextCache
{
    private final Map<Signature, CallContextRef> contextCache;
    private final ReferenceQueue<CallContext> contextReferenceQueue;
    
    public static final CallContextCache getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    private CallContextCache() {
        this.contextCache = new ConcurrentHashMap<Signature, CallContextRef>();
        this.contextReferenceQueue = new ReferenceQueue<CallContext>();
    }
    
    public final CallContext getCallContext(final Type returnType, final Type[] parameterTypes, final CallingConvention convention) {
        final Signature signature = new Signature(returnType, parameterTypes, convention);
        CallContextRef ref = this.contextCache.get(signature);
        CallContext ctx;
        if (ref != null && (ctx = ref.get()) != null) {
            return ctx;
        }
        while ((ref = (CallContextRef)this.contextReferenceQueue.poll()) != null) {
            this.contextCache.remove(ref.signature);
        }
        ctx = new CallContext(returnType, parameterTypes.clone(), convention);
        this.contextCache.put(signature, new CallContextRef(signature, ctx, this.contextReferenceQueue));
        return ctx;
    }
    
    private static final class SingletonHolder
    {
        static final CallContextCache INSTANCE;
        
        static {
            INSTANCE = new CallContextCache(null);
        }
    }
    
    private static final class CallContextRef extends SoftReference<CallContext>
    {
        final Signature signature;
        
        public CallContextRef(final Signature signature, final CallContext ctx, final ReferenceQueue<CallContext> queue) {
            super(ctx, queue);
            this.signature = signature;
        }
    }
    
    private static final class Signature
    {
        private final Type returnType;
        private final Type[] parameterTypes;
        private final CallingConvention convention;
        private int hashCode;
        
        public Signature(final Type returnType, final Type[] parameterTypes, final CallingConvention convention) {
            this.hashCode = 0;
            if (returnType == null || parameterTypes == null) {
                throw new NullPointerException("null return type or parameter types array");
            }
            this.returnType = returnType;
            this.parameterTypes = parameterTypes;
            this.convention = convention;
        }
        
        public boolean equals(final Object obj) {
            if (obj == null || this.getClass() != obj.getClass()) {
                return false;
            }
            final Signature other = (Signature)obj;
            if (this.convention != other.convention) {
                return false;
            }
            if (this.returnType != other.returnType && !this.returnType.equals(other.returnType)) {
                return false;
            }
            if (this.parameterTypes.length == other.parameterTypes.length) {
                for (int i = 0; i < this.parameterTypes.length; ++i) {
                    if (this.parameterTypes[i] != other.parameterTypes[i] && (this.parameterTypes[i] == null || !this.parameterTypes[i].equals(other.parameterTypes[i]))) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        
        private final int calculateHashCode() {
            int hash = 7;
            hash = 53 * hash + ((this.returnType != null) ? this.returnType.hashCode() : 0);
            int paramHash = 1;
            for (int i = 0; i < this.parameterTypes.length; ++i) {
                paramHash = 31 * paramHash + this.parameterTypes[i].hashCode();
            }
            hash = 53 * hash + paramHash;
            hash = 53 * hash + this.convention.hashCode();
            return hash;
        }
        
        public int hashCode() {
            return (this.hashCode != 0) ? this.hashCode : (this.hashCode = this.calculateHashCode());
        }
    }
}
