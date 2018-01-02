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

public class SuperCallSite extends CallSite
{
    protected volatile SuperTuple cache;
    
    public SuperCallSite() {
        super("super", CallType.SUPER);
        this.cache = SuperTuple.NULL_CACHE;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final long fixnum) {
        return this.call(context, caller, self, RubyFixnum.newFixnum(context.getRuntime(), fixnum));
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final double flote) {
        return this.call(context, caller, self, RubyFloat.newFloat(context.getRuntime(), flote));
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject... args) {
        final RubyModule klazz = context.getFrameKlazz();
        final String name = context.getFrameName();
        final RubyClass selfType = pollAndGetClass(context, self, klazz, name);
        final SuperTuple myCache = this.cache;
        if (selfType != null && myCache.cacheOk(name, selfType)) {
            return myCache.cache.method.call(context, self, selfType, name, args);
        }
        return this.cacheAndCall(caller, selfType, args, context, self, name);
    }
    
    private IRubyObject callBlock(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject[] args, final Block block) {
        final RubyModule klazz = context.getFrameKlazz();
        final String name = context.getFrameName();
        final RubyClass selfType = pollAndGetClass(context, self, klazz, name);
        final SuperTuple myCache = this.cache;
        if (selfType != null && myCache.cacheOk(name, selfType)) {
            return myCache.cache.method.call(context, self, selfType, name, args, block);
        }
        return this.cacheAndCall(caller, selfType, block, args, context, self, name);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject[] args, final Block block) {
        try {
            return this.callBlock(context, caller, self, args, block);
        }
        catch (JumpException.BreakJump bj) {
            return handleBreakJump(context, bj);
        }
        catch (JumpException.RetryJump rj) {
            throw retryJumpError(context);
        }
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
        final RubyModule klazz = context.getFrameKlazz();
        final String name = context.getFrameName();
        final RubyClass selfType = pollAndGetClass(context, self, klazz, name);
        final SuperTuple myCache = this.cache;
        if (selfType != null && myCache.cacheOk(name, selfType)) {
            return myCache.cache.method.call(context, self, selfType, name);
        }
        return this.cacheAndCall(caller, selfType, context, self, name);
    }
    
    private IRubyObject callBlock(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final Block block) {
        final RubyModule klazz = context.getFrameKlazz();
        final String name = context.getFrameName();
        final RubyClass selfType = pollAndGetClass(context, self, klazz, name);
        final SuperTuple myCache = this.cache;
        if (selfType != null && myCache.cacheOk(name, selfType)) {
            return myCache.cache.method.call(context, self, selfType, name, block);
        }
        return this.cacheAndCall(caller, selfType, block, context, self, name);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final Block block) {
        try {
            return this.callBlock(context, caller, self, block);
        }
        catch (JumpException.BreakJump bj) {
            return handleBreakJump(context, bj);
        }
        catch (JumpException.RetryJump rj) {
            throw retryJumpError(context);
        }
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
        final RubyModule klazz = context.getFrameKlazz();
        final String name = context.getFrameName();
        final RubyClass selfType = pollAndGetClass(context, self, klazz, name);
        final SuperTuple myCache = this.cache;
        if (selfType != null && myCache.cacheOk(name, selfType)) {
            return myCache.cache.method.call(context, self, selfType, name, arg1);
        }
        return this.cacheAndCall(caller, selfType, context, self, name, arg1);
    }
    
    private IRubyObject callBlock(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject arg1, final Block block) {
        final RubyModule klazz = context.getFrameKlazz();
        final String name = context.getFrameName();
        final RubyClass selfType = pollAndGetClass(context, self, klazz, name);
        final SuperTuple myCache = this.cache;
        if (selfType != null && myCache.cacheOk(name, selfType)) {
            return myCache.cache.method.call(context, self, selfType, name, arg1, block);
        }
        return this.cacheAndCall(caller, selfType, block, context, self, name, arg1);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject arg1, final Block block) {
        try {
            return this.callBlock(context, caller, self, arg1, block);
        }
        catch (JumpException.BreakJump bj) {
            return handleBreakJump(context, bj);
        }
        catch (JumpException.RetryJump rj) {
            throw retryJumpError(context);
        }
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
        final RubyModule klazz = context.getFrameKlazz();
        final String name = context.getFrameName();
        final RubyClass selfType = pollAndGetClass(context, self, klazz, name);
        final SuperTuple myCache = this.cache;
        if (selfType != null && myCache.cacheOk(name, selfType)) {
            return myCache.cache.method.call(context, self, selfType, name, arg1, arg2);
        }
        return this.cacheAndCall(caller, selfType, context, self, name, arg1, arg2);
    }
    
    private IRubyObject callBlock(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        final RubyModule klazz = context.getFrameKlazz();
        final String name = context.getFrameName();
        final RubyClass selfType = pollAndGetClass(context, self, klazz, name);
        final SuperTuple myCache = this.cache;
        if (selfType != null && myCache.cacheOk(name, selfType)) {
            return myCache.cache.method.call(context, self, selfType, name, arg1, arg2, block);
        }
        return this.cacheAndCall(caller, selfType, block, context, self, name, arg1, arg2);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        try {
            return this.callBlock(context, caller, self, arg1, arg2, block);
        }
        catch (JumpException.BreakJump bj) {
            return handleBreakJump(context, bj);
        }
        catch (JumpException.RetryJump rj) {
            throw retryJumpError(context);
        }
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
        final RubyModule klazz = context.getFrameKlazz();
        final String name = context.getFrameName();
        final RubyClass selfType = pollAndGetClass(context, self, klazz, name);
        final SuperTuple myCache = this.cache;
        if (selfType != null && myCache.cacheOk(name, selfType)) {
            return myCache.cache.method.call(context, self, selfType, name, arg1, arg2, arg3);
        }
        return this.cacheAndCall(caller, selfType, context, self, name, arg1, arg2, arg3);
    }
    
    private IRubyObject callBlock(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final Block block) {
        final RubyModule klazz = context.getFrameKlazz();
        final String name = context.getFrameName();
        final RubyClass selfType = pollAndGetClass(context, self, klazz, name);
        final SuperTuple myCache = this.cache;
        if (selfType != null && myCache.cacheOk(name, selfType)) {
            return myCache.cache.method.call(context, self, selfType, name, arg1, arg2, arg3, block);
        }
        return this.cacheAndCall(caller, selfType, block, context, self, name, arg1, arg2, arg3);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3, final Block block) {
        try {
            return this.callBlock(context, caller, self, arg1, arg2, arg3, block);
        }
        catch (JumpException.BreakJump bj) {
            return handleBreakJump(context, bj);
        }
        catch (JumpException.RetryJump rj) {
            throw retryJumpError(context);
        }
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
    
    protected IRubyObject cacheAndCall(final IRubyObject caller, final RubyClass selfType, final Block block, final IRubyObject[] args, final ThreadContext context, final IRubyObject self, final String name) {
        final CacheEntry entry = (selfType != null) ? selfType.searchWithCache(name) : CacheEntry.NULL_CACHE;
        final DynamicMethod method = entry.method;
        if (this.methodMissing(method, caller)) {
            return this.callMethodMissing(context, self, name, method, args, block);
        }
        this.cache = new SuperTuple(name, entry);
        return method.call(context, self, selfType, name, args, block);
    }
    
    protected IRubyObject cacheAndCall(final IRubyObject caller, final RubyClass selfType, final IRubyObject[] args, final ThreadContext context, final IRubyObject self, final String name) {
        final CacheEntry entry = (selfType != null) ? selfType.searchWithCache(name) : CacheEntry.NULL_CACHE;
        final DynamicMethod method = entry.method;
        if (this.methodMissing(method, caller)) {
            return this.callMethodMissing(context, self, name, method, args);
        }
        this.cache = new SuperTuple(name, entry);
        return method.call(context, self, selfType, name, args);
    }
    
    protected IRubyObject cacheAndCall(final IRubyObject caller, final RubyClass selfType, final ThreadContext context, final IRubyObject self, final String name) {
        final CacheEntry entry = (selfType != null) ? selfType.searchWithCache(name) : CacheEntry.NULL_CACHE;
        final DynamicMethod method = entry.method;
        if (this.methodMissing(method, caller)) {
            return this.callMethodMissing(context, self, name, method);
        }
        this.cache = new SuperTuple(name, entry);
        return method.call(context, self, selfType, name);
    }
    
    protected IRubyObject cacheAndCall(final IRubyObject caller, final RubyClass selfType, final Block block, final ThreadContext context, final IRubyObject self, final String name) {
        final CacheEntry entry = (selfType != null) ? selfType.searchWithCache(name) : CacheEntry.NULL_CACHE;
        final DynamicMethod method = entry.method;
        if (this.methodMissing(method, caller)) {
            return this.callMethodMissing(context, self, name, method, block);
        }
        this.cache = new SuperTuple(name, entry);
        return method.call(context, self, selfType, name, block);
    }
    
    protected IRubyObject cacheAndCall(final IRubyObject caller, final RubyClass selfType, final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg) {
        final CacheEntry entry = (selfType != null) ? selfType.searchWithCache(name) : CacheEntry.NULL_CACHE;
        final DynamicMethod method = entry.method;
        if (this.methodMissing(method, caller)) {
            return this.callMethodMissing(context, self, name, method, arg);
        }
        this.cache = new SuperTuple(name, entry);
        return method.call(context, self, selfType, name, arg);
    }
    
    protected IRubyObject cacheAndCall(final IRubyObject caller, final RubyClass selfType, final Block block, final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg) {
        final CacheEntry entry = (selfType != null) ? selfType.searchWithCache(name) : CacheEntry.NULL_CACHE;
        final DynamicMethod method = entry.method;
        if (this.methodMissing(method, caller)) {
            return this.callMethodMissing(context, self, name, method, arg, block);
        }
        this.cache = new SuperTuple(name, entry);
        return method.call(context, self, selfType, name, arg, block);
    }
    
    protected IRubyObject cacheAndCall(final IRubyObject caller, final RubyClass selfType, final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg1, final IRubyObject arg2) {
        final CacheEntry entry = (selfType != null) ? selfType.searchWithCache(name) : CacheEntry.NULL_CACHE;
        final DynamicMethod method = entry.method;
        if (this.methodMissing(method, caller)) {
            return this.callMethodMissing(context, self, name, method, arg1, arg2);
        }
        this.cache = new SuperTuple(name, entry);
        return method.call(context, self, selfType, name, arg1, arg2);
    }
    
    protected IRubyObject cacheAndCall(final IRubyObject caller, final RubyClass selfType, final Block block, final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg1, final IRubyObject arg2) {
        final CacheEntry entry = (selfType != null) ? selfType.searchWithCache(name) : CacheEntry.NULL_CACHE;
        final DynamicMethod method = entry.method;
        if (this.methodMissing(method, caller)) {
            return this.callMethodMissing(context, self, name, method, arg1, arg2, block);
        }
        this.cache = new SuperTuple(name, entry);
        return method.call(context, self, selfType, name, arg1, arg2, block);
    }
    
    protected IRubyObject cacheAndCall(final IRubyObject caller, final RubyClass selfType, final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3) {
        final CacheEntry entry = (selfType != null) ? selfType.searchWithCache(name) : CacheEntry.NULL_CACHE;
        final DynamicMethod method = entry.method;
        if (this.methodMissing(method, caller)) {
            return this.callMethodMissing(context, self, name, method, arg1, arg2, arg3);
        }
        this.cache = new SuperTuple(name, entry);
        return method.call(context, self, selfType, name, arg1, arg2, arg3);
    }
    
    protected IRubyObject cacheAndCall(final IRubyObject caller, final RubyClass selfType, final Block block, final ThreadContext context, final IRubyObject self, final String name, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3) {
        final CacheEntry entry = (selfType != null) ? selfType.searchWithCache(name) : CacheEntry.NULL_CACHE;
        final DynamicMethod method = entry.method;
        if (this.methodMissing(method, caller)) {
            return this.callMethodMissing(context, self, name, method, arg1, arg2, arg3, block);
        }
        this.cache = new SuperTuple(name, entry);
        return method.call(context, self, selfType, name, arg1, arg2, arg3, block);
    }
    
    protected IRubyObject callMethodMissing(final ThreadContext context, final IRubyObject self, final String name, final DynamicMethod method, final IRubyObject[] args) {
        return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, this.callType, args, Block.NULL_BLOCK);
    }
    
    protected IRubyObject callMethodMissing(final ThreadContext context, final IRubyObject self, final String name, final DynamicMethod method) {
        return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, this.callType, Block.NULL_BLOCK);
    }
    
    protected IRubyObject callMethodMissing(final ThreadContext context, final IRubyObject self, final String name, final DynamicMethod method, final Block block) {
        return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, this.callType, block);
    }
    
    protected IRubyObject callMethodMissing(final ThreadContext context, final IRubyObject self, final String name, final DynamicMethod method, final IRubyObject arg) {
        return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, this.callType, arg, Block.NULL_BLOCK);
    }
    
    protected IRubyObject callMethodMissing(final ThreadContext context, final IRubyObject self, final String name, final DynamicMethod method, final IRubyObject[] args, final Block block) {
        return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, this.callType, args, block);
    }
    
    protected IRubyObject callMethodMissing(final ThreadContext context, final IRubyObject self, final String name, final DynamicMethod method, final IRubyObject arg0, final Block block) {
        return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, this.callType, arg0, block);
    }
    
    protected IRubyObject callMethodMissing(final ThreadContext context, final IRubyObject self, final String name, final DynamicMethod method, final IRubyObject arg0, final IRubyObject arg1) {
        return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, this.callType, arg0, arg1, Block.NULL_BLOCK);
    }
    
    protected IRubyObject callMethodMissing(final ThreadContext context, final IRubyObject self, final String name, final DynamicMethod method, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, this.callType, arg0, arg1, block);
    }
    
    protected IRubyObject callMethodMissing(final ThreadContext context, final IRubyObject self, final String name, final DynamicMethod method, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg3) {
        return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, this.callType, arg0, arg1, arg3, Block.NULL_BLOCK);
    }
    
    protected IRubyObject callMethodMissing(final ThreadContext context, final IRubyObject self, final String name, final DynamicMethod method, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        return RuntimeHelpers.callMethodMissing(context, self, method.getVisibility(), name, this.callType, arg0, arg1, arg2, block);
    }
    
    protected boolean methodMissing(final DynamicMethod method, final IRubyObject caller) {
        return method.isUndefined();
    }
    
    protected static RubyClass pollAndGetClass(final ThreadContext context, final IRubyObject self, final RubyModule frameClass, final String frameName) {
        checkSuperDisabledOrOutOfMethod(context, frameClass, frameName);
        final RubyClass superClass = RuntimeHelpers.findImplementerIfNecessary(self.getMetaClass(), frameClass).getSuperClass();
        return superClass;
    }
    
    protected static void checkSuperDisabledOrOutOfMethod(final ThreadContext context, final RubyModule frameClass, final String frameName) {
        if (frameClass != null) {
            return;
        }
        if (frameName != null) {
            throw context.getRuntime().newNameError("superclass method '" + frameName + "' disabled", frameName);
        }
        throw context.getRuntime().newNoMethodError("super called outside of method", null, context.getRuntime().getNil());
    }
    
    protected static IRubyObject handleBreakJump(final ThreadContext context, final JumpException.BreakJump bj) throws JumpException.BreakJump {
        if (context.getFrameJumpTarget() == bj.getTarget()) {
            return (IRubyObject)bj.getValue();
        }
        throw bj;
    }
    
    protected static RaiseException retryJumpError(final ThreadContext context) {
        return context.getRuntime().newLocalJumpError(RubyLocalJumpError.Reason.RETRY, context.getRuntime().getNil(), "retry outside of rescue not supported");
    }
    
    private static class SuperTuple
    {
        static final SuperTuple NULL_CACHE;
        public final String name;
        public final CacheEntry cache;
        
        public SuperTuple(final String name, final CacheEntry cache) {
            this.name = name;
            this.cache = cache;
        }
        
        public boolean cacheOk(final String name, final RubyClass klass) {
            return this.name.equals(name) && this.cache.typeOk(klass);
        }
        
        static {
            NULL_CACHE = new SuperTuple("", CacheEntry.NULL_CACHE);
        }
    }
}
