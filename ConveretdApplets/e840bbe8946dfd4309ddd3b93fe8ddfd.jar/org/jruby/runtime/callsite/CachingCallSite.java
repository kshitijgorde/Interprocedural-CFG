// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.callsite;

import org.jruby.RubyLocalJumpError;
import org.jruby.exceptions.RaiseException;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.exceptions.JumpException;
import org.jruby.runtime.Block;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.RubyFloat;
import org.jruby.RubyFixnum;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.CallType;
import org.jruby.runtime.CallSite;

public abstract class CachingCallSite extends CallSite
{
    protected CacheEntry cache;
    public static volatile int totalCallSites;
    
    public CachingCallSite(final String methodName, final CallType callType) {
        super(methodName, callType);
        this.cache = CacheEntry.NULL_CACHE;
        ++CachingCallSite.totalCallSites;
    }
    
    public CacheEntry getCache() {
        return this.cache;
    }
    
    public boolean isOptimizable() {
        return this.getCache() != CacheEntry.NULL_CACHE;
    }
    
    public int getCachedClassIndex() {
        final CacheEntry cacheEntry = this.getCache();
        if (cacheEntry != CacheEntry.NULL_CACHE) {
            return cacheEntry.method.getImplementationClass().index;
        }
        return 0;
    }
    
    public String getMethodName() {
        return this.methodName;
    }
    
    public long getCachedMethodSerial() {
        final CacheEntry cacheEntry = this.getCache();
        if (cacheEntry != CacheEntry.NULL_CACHE) {
            return cacheEntry.method.getSerialNumber();
        }
        return -1L;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final long fixnum) {
        return this.call(context, caller, self, RubyFixnum.newFixnum(context.getRuntime(), fixnum));
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final double flote) {
        return this.call(context, caller, self, RubyFloat.newFloat(context.getRuntime(), flote));
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject... args) {
        final RubyClass selfType = pollAndGetClass(context, self);
        final CacheEntry myCache = this.cache;
        if (CacheEntry.typeOk(myCache, selfType)) {
            return myCache.method.call(context, self, selfType, this.methodName, args);
        }
        return this.cacheAndCall(caller, selfType, args, context, self);
    }
    
    private IRubyObject callBlock(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject[] args, final Block block) {
        final RubyClass selfType = pollAndGetClass(context, self);
        final CacheEntry myCache = this.cache;
        if (CacheEntry.typeOk(myCache, selfType)) {
            return myCache.method.call(context, self, selfType, this.methodName, args, block);
        }
        return this.cacheAndCall(caller, selfType, block, args, context, self);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject[] args, final Block block) {
        return this.callBlock(context, caller, self, args, block);
    }
    
    public IRubyObject callIter(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject[] args, final Block block) {
        try {
            return this.callBlock(context, caller, self, args, block);
        }
        catch (JumpException.BreakJump bj) {
            return handleBreakJump(context, bj);
        }
        catch (JumpException.RetryJump rj) {
            throw retryJumpError(context);
        }
        finally {
            block.escape();
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self) {
        final RubyClass selfType = pollAndGetClass(context, self);
        final CacheEntry myCache = this.cache;
        if (CacheEntry.typeOk(myCache, selfType)) {
            return myCache.method.call(context, self, selfType, this.methodName);
        }
        return this.cacheAndCall(caller, selfType, context, self);
    }
    
    private IRubyObject callBlock(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final Block block) {
        final RubyClass selfType = pollAndGetClass(context, self);
        final CacheEntry myCache = this.cache;
        if (CacheEntry.typeOk(myCache, selfType)) {
            return myCache.method.call(context, self, selfType, this.methodName, block);
        }
        return this.cacheAndCall(caller, selfType, block, context, self);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final Block block) {
        return this.callBlock(context, caller, self, block);
    }
    
    public IRubyObject callIter(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final Block block) {
        try {
            return this.callBlock(context, caller, self, block);
        }
        catch (JumpException.BreakJump bj) {
            return handleBreakJump(context, bj);
        }
        catch (JumpException.RetryJump rj) {
            throw retryJumpError(context);
        }
        finally {
            block.escape();
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject arg1) {
        final RubyClass selfType = pollAndGetClass(context, self);
        final CacheEntry myCache = this.cache;
        if (CacheEntry.typeOk(myCache, selfType)) {
            return myCache.method.call(context, self, selfType, this.methodName, arg1);
        }
        return this.cacheAndCall(caller, selfType, context, self, arg1);
    }
    
    private IRubyObject callBlock(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject arg1, final Block block) {
        final RubyClass selfType = pollAndGetClass(context, self);
        final CacheEntry myCache = this.cache;
        if (CacheEntry.typeOk(myCache, selfType)) {
            return myCache.method.call(context, self, selfType, this.methodName, arg1, block);
        }
        return this.cacheAndCall(caller, selfType, block, context, self, arg1);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject arg1, final Block block) {
        return this.callBlock(context, caller, self, arg1, block);
    }
    
    public IRubyObject callIter(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject arg1, final Block block) {
        try {
            return this.callBlock(context, caller, self, arg1, block);
        }
        catch (JumpException.BreakJump bj) {
            return handleBreakJump(context, bj);
        }
        catch (JumpException.RetryJump rj) {
            throw retryJumpError(context);
        }
        finally {
            block.escape();
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject arg1, final IRubyObject arg2) {
        final RubyClass selfType = pollAndGetClass(context, self);
        final CacheEntry myCache = this.cache;
        if (CacheEntry.typeOk(myCache, selfType)) {
            return myCache.method.call(context, self, selfType, this.methodName, arg1, arg2);
        }
        return this.cacheAndCall(caller, selfType, context, self, arg1, arg2);
    }
    
    private IRubyObject callBlock(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        final RubyClass selfType = pollAndGetClass(context, self);
        final CacheEntry myCache = this.cache;
        if (CacheEntry.typeOk(myCache, selfType)) {
            return myCache.method.call(context, self, selfType, this.methodName, arg1, arg2, block);
        }
        return this.cacheAndCall(caller, selfType, block, context, self, arg1, arg2);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        return this.callBlock(context, caller, self, arg1, arg2, block);
    }
    
    public IRubyObject callIter(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        try {
            return this.callBlock(context, caller, self, arg1, arg2, block);
        }
        catch (JumpException.BreakJump bj) {
            return handleBreakJump(context, bj);
        }
        catch (JumpException.RetryJump rj) {
            throw retryJumpError(context);
        }
        finally {
            block.escape();
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3) {
        final RubyClass selfType = pollAndGetClass(context, self);
        final CacheEntry myCache = this.cache;
        if (CacheEntry.typeOk(myCache, selfType)) {
            return myCache.method.call(context, self, selfType, this.methodName, arg1, arg2, arg3);
        }
        return this.cacheAndCall(caller, selfType, context, self, arg1, arg2, arg3);
    }
    
    private IRubyObject callBlock(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final Block block) {
        final RubyClass selfType = pollAndGetClass(context, self);
        final CacheEntry myCache = this.cache;
        if (CacheEntry.typeOk(myCache, selfType)) {
            return myCache.method.call(context, self, selfType, this.methodName, arg1, arg2, arg3, block);
        }
        return this.cacheAndCall(caller, selfType, block, context, self, arg1, arg2, arg3);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final Block block) {
        return this.callBlock(context, caller, self, arg1, arg2, arg3, block);
    }
    
    public IRubyObject callIter(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final Block block) {
        try {
            return this.callBlock(context, caller, self, arg1, arg2, arg3, block);
        }
        catch (JumpException.BreakJump bj) {
            return handleBreakJump(context, bj);
        }
        catch (JumpException.RetryJump rj) {
            throw retryJumpError(context);
        }
        finally {
            block.escape();
        }
    }
    
    protected IRubyObject cacheAndCall(final IRubyObject caller, final RubyClass selfType, final Block block, final IRubyObject[] args, final ThreadContext context, final IRubyObject self) {
        final CacheEntry entry = selfType.searchWithCache(this.methodName);
        final DynamicMethod method = entry.method;
        if (this.methodMissing(method, caller)) {
            return this.callMethodMissing(context, self, method, args, block);
        }
        this.updateCache(entry);
        return method.call(context, self, selfType, this.methodName, args, block);
    }
    
    protected IRubyObject cacheAndCall(final IRubyObject caller, final RubyClass selfType, final IRubyObject[] args, final ThreadContext context, final IRubyObject self) {
        final CacheEntry entry = selfType.searchWithCache(this.methodName);
        final DynamicMethod method = entry.method;
        if (this.methodMissing(method, caller)) {
            return this.callMethodMissing(context, self, method, args);
        }
        this.updateCache(entry);
        return method.call(context, self, selfType, this.methodName, args);
    }
    
    protected IRubyObject cacheAndCall(final IRubyObject caller, final RubyClass selfType, final ThreadContext context, final IRubyObject self) {
        final CacheEntry entry = selfType.searchWithCache(this.methodName);
        final DynamicMethod method = entry.method;
        if (this.methodMissing(method, caller)) {
            return this.callMethodMissing(context, self, method);
        }
        this.updateCache(entry);
        return method.call(context, self, selfType, this.methodName);
    }
    
    protected IRubyObject cacheAndCall(final IRubyObject caller, final RubyClass selfType, final Block block, final ThreadContext context, final IRubyObject self) {
        final CacheEntry entry = selfType.searchWithCache(this.methodName);
        final DynamicMethod method = entry.method;
        if (this.methodMissing(method, caller)) {
            return this.callMethodMissing(context, self, method, block);
        }
        this.updateCache(entry);
        return method.call(context, self, selfType, this.methodName, block);
    }
    
    protected IRubyObject cacheAndCall(final IRubyObject caller, final RubyClass selfType, final ThreadContext context, final IRubyObject self, final IRubyObject arg) {
        final CacheEntry entry = selfType.searchWithCache(this.methodName);
        final DynamicMethod method = entry.method;
        if (this.methodMissing(method, caller)) {
            return this.callMethodMissing(context, self, method, arg);
        }
        this.updateCache(entry);
        return method.call(context, self, selfType, this.methodName, arg);
    }
    
    protected IRubyObject cacheAndCall(final IRubyObject caller, final RubyClass selfType, final Block block, final ThreadContext context, final IRubyObject self, final IRubyObject arg) {
        final CacheEntry entry = selfType.searchWithCache(this.methodName);
        final DynamicMethod method = entry.method;
        if (this.methodMissing(method, caller)) {
            return this.callMethodMissing(context, self, method, arg, block);
        }
        this.updateCache(entry);
        return method.call(context, self, selfType, this.methodName, arg, block);
    }
    
    protected IRubyObject cacheAndCall(final IRubyObject caller, final RubyClass selfType, final ThreadContext context, final IRubyObject self, final IRubyObject arg1, final IRubyObject arg2) {
        final CacheEntry entry = selfType.searchWithCache(this.methodName);
        final DynamicMethod method = entry.method;
        if (this.methodMissing(method, caller)) {
            return this.callMethodMissing(context, self, method, arg1, arg2);
        }
        this.updateCache(entry);
        return method.call(context, self, selfType, this.methodName, arg1, arg2);
    }
    
    protected IRubyObject cacheAndCall(final IRubyObject caller, final RubyClass selfType, final Block block, final ThreadContext context, final IRubyObject self, final IRubyObject arg1, final IRubyObject arg2) {
        final CacheEntry entry = selfType.searchWithCache(this.methodName);
        final DynamicMethod method = entry.method;
        if (this.methodMissing(method, caller)) {
            return this.callMethodMissing(context, self, method, arg1, arg2, block);
        }
        this.updateCache(entry);
        return method.call(context, self, selfType, this.methodName, arg1, arg2, block);
    }
    
    protected IRubyObject cacheAndCall(final IRubyObject caller, final RubyClass selfType, final ThreadContext context, final IRubyObject self, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3) {
        final CacheEntry entry = selfType.searchWithCache(this.methodName);
        final DynamicMethod method = entry.method;
        if (this.methodMissing(method, caller)) {
            return this.callMethodMissing(context, self, method, arg1, arg2, arg3);
        }
        this.updateCache(entry);
        return method.call(context, self, selfType, this.methodName, arg1, arg2, arg3);
    }
    
    protected IRubyObject cacheAndCall(final IRubyObject caller, final RubyClass selfType, final Block block, final ThreadContext context, final IRubyObject self, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3) {
        final CacheEntry entry = selfType.searchWithCache(this.methodName);
        final DynamicMethod method = entry.method;
        if (this.methodMissing(method, caller)) {
            return this.callMethodMissing(context, self, method, arg1, arg2, arg3, block);
        }
        this.updateCache(entry);
        return method.call(context, self, selfType, this.methodName, arg1, arg2, arg3, block);
    }
    
    protected void updateCache(final CacheEntry newEntry) {
        this.cache = newEntry;
    }
    
    protected IRubyObject callMethodMissing(final ThreadContext context, final IRubyObject self, final DynamicMethod method, final IRubyObject[] args) {
        return RuntimeHelpers.selectMethodMissing(context, self, method.getVisibility(), this.methodName, this.callType).call(context, self, self.getMetaClass(), this.methodName, args, Block.NULL_BLOCK);
    }
    
    protected IRubyObject callMethodMissing(final ThreadContext context, final IRubyObject self, final DynamicMethod method) {
        return RuntimeHelpers.selectMethodMissing(context, self, method.getVisibility(), this.methodName, this.callType).call(context, self, self.getMetaClass(), this.methodName, Block.NULL_BLOCK);
    }
    
    protected IRubyObject callMethodMissing(final ThreadContext context, final IRubyObject self, final DynamicMethod method, final Block block) {
        return RuntimeHelpers.selectMethodMissing(context, self, method.getVisibility(), this.methodName, this.callType).call(context, self, self.getMetaClass(), this.methodName, block);
    }
    
    protected IRubyObject callMethodMissing(final ThreadContext context, final IRubyObject self, final DynamicMethod method, final IRubyObject arg) {
        return RuntimeHelpers.selectMethodMissing(context, self, method.getVisibility(), this.methodName, this.callType).call(context, self, self.getMetaClass(), this.methodName, arg, Block.NULL_BLOCK);
    }
    
    protected IRubyObject callMethodMissing(final ThreadContext context, final IRubyObject self, final DynamicMethod method, final IRubyObject[] args, final Block block) {
        return RuntimeHelpers.selectMethodMissing(context, self, method.getVisibility(), this.methodName, this.callType).call(context, self, self.getMetaClass(), this.methodName, args, block);
    }
    
    protected IRubyObject callMethodMissing(final ThreadContext context, final IRubyObject self, final DynamicMethod method, final IRubyObject arg0, final Block block) {
        return RuntimeHelpers.selectMethodMissing(context, self, method.getVisibility(), this.methodName, this.callType).call(context, self, self.getMetaClass(), this.methodName, arg0, block);
    }
    
    protected IRubyObject callMethodMissing(final ThreadContext context, final IRubyObject self, final DynamicMethod method, final IRubyObject arg0, final IRubyObject arg1) {
        return RuntimeHelpers.selectMethodMissing(context, self, method.getVisibility(), this.methodName, this.callType).call(context, self, self.getMetaClass(), this.methodName, arg0, arg1, Block.NULL_BLOCK);
    }
    
    protected IRubyObject callMethodMissing(final ThreadContext context, final IRubyObject self, final DynamicMethod method, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        return RuntimeHelpers.selectMethodMissing(context, self, method.getVisibility(), this.methodName, this.callType).call(context, self, self.getMetaClass(), this.methodName, arg0, arg1, block);
    }
    
    protected IRubyObject callMethodMissing(final ThreadContext context, final IRubyObject self, final DynamicMethod method, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        return RuntimeHelpers.selectMethodMissing(context, self, method.getVisibility(), this.methodName, this.callType).call(context, self, self.getMetaClass(), this.methodName, arg0, arg1, arg2, Block.NULL_BLOCK);
    }
    
    protected IRubyObject callMethodMissing(final ThreadContext context, final IRubyObject self, final DynamicMethod method, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        return RuntimeHelpers.selectMethodMissing(context, self, method.getVisibility(), this.methodName, this.callType).call(context, self, self.getMetaClass(), this.methodName, arg0, arg1, arg2, block);
    }
    
    protected abstract boolean methodMissing(final DynamicMethod p0, final IRubyObject p1);
    
    private static RubyClass pollAndGetClass(final ThreadContext context, final IRubyObject self) {
        ThreadContext.callThreadPoll(context);
        final RubyClass selfType = self.getMetaClass();
        return selfType;
    }
    
    private static IRubyObject handleBreakJump(final ThreadContext context, final JumpException.BreakJump bj) throws JumpException.BreakJump {
        if (context.getFrameJumpTarget() == bj.getTarget()) {
            return (IRubyObject)bj.getValue();
        }
        throw bj;
    }
    
    private static RaiseException retryJumpError(final ThreadContext context) {
        return context.getRuntime().newLocalJumpError(RubyLocalJumpError.Reason.RETRY, context.getRuntime().getNil(), "retry outside of rescue not supported");
    }
}
