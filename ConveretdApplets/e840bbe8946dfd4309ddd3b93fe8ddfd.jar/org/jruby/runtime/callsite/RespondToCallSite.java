// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.callsite;

import org.jruby.runtime.Visibility;
import org.jruby.Ruby;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.RubyModule;
import org.jruby.RubyClass;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;

public class RespondToCallSite extends NormalCachingCallSite
{
    private volatile RespondToTuple respondToTuple;
    
    public RespondToCallSite() {
        super("respond_to?");
        this.respondToTuple = RespondToTuple.NULL_CACHE;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject name) {
        final RubyClass klass = self.getMetaClass();
        final RespondToTuple tuple = this.respondToTuple;
        if (tuple.cacheOk(klass)) {
            final String strName = name.asJavaString();
            if (strName.equals(tuple.name) && tuple.checkVisibility) {
                return tuple.respondsTo;
            }
        }
        IRubyObject respond = super.call(context, caller, self, name);
        if (!respond.isTrue() && context.getRuntime().is1_9()) {
            respond = self.callMethod(context, "respond_to_missing?", new IRubyObject[] { name, context.getRuntime().getFalse() });
            respond = context.getRuntime().newBoolean(respond.isTrue());
        }
        return respond;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject caller, final IRubyObject self, final IRubyObject name, final IRubyObject bool) {
        final RubyClass klass = self.getMetaClass();
        final RespondToTuple tuple = this.respondToTuple;
        if (tuple.cacheOk(klass)) {
            final String strName = name.asJavaString();
            if (strName.equals(tuple.name) && !bool.isTrue() == tuple.checkVisibility) {
                return tuple.respondsTo;
            }
        }
        IRubyObject respond = super.call(context, caller, self, name, bool);
        if (!respond.isTrue() && context.getRuntime().is1_9()) {
            respond = self.callMethod(context, "respond_to_missing?", new IRubyObject[] { name, bool });
            respond = context.getRuntime().newBoolean(respond.isTrue());
        }
        return respond;
    }
    
    protected IRubyObject cacheAndCall(final IRubyObject caller, final RubyClass selfType, final ThreadContext context, final IRubyObject self, final IRubyObject arg) {
        final CacheEntry entry = selfType.searchWithCache(this.methodName);
        final DynamicMethod method = entry.method;
        if (this.methodMissing(method, caller)) {
            return this.callMethodMissing(context, self, method, arg);
        }
        if (entry.method == context.getRuntime().getRespondToMethod()) {
            final String name = arg.asJavaString();
            final RespondToTuple tuple = recacheRespondsTo(entry, name, selfType, true, context);
            this.respondToTuple = tuple;
            return tuple.respondsTo;
        }
        this.cache = entry;
        return method.call(context, self, selfType, this.methodName, arg);
    }
    
    protected IRubyObject cacheAndCall(final IRubyObject caller, final RubyClass selfType, final ThreadContext context, final IRubyObject self, final IRubyObject arg0, final IRubyObject arg1) {
        final CacheEntry entry = selfType.searchWithCache(this.methodName);
        final DynamicMethod method = entry.method;
        if (this.methodMissing(method, caller)) {
            return this.callMethodMissing(context, self, method, arg0, arg1);
        }
        if (entry.method == context.getRuntime().getRespondToMethod()) {
            final String name = arg0.asJavaString();
            final RespondToTuple tuple = recacheRespondsTo(entry, name, selfType, !arg1.isTrue(), context);
            this.respondToTuple = tuple;
            return tuple.respondsTo;
        }
        this.cache = entry;
        return method.call(context, self, selfType, this.methodName, arg0, arg1);
    }
    
    private static RespondToTuple recacheRespondsTo(final CacheEntry respondToMethod, final String newString, final RubyClass klass, final boolean checkVisibility, final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final CacheEntry respondToLookupResult = klass.searchWithCache(newString);
        IRubyObject respondsTo;
        if (!respondToLookupResult.method.isUndefined() && !respondToLookupResult.method.isNotImplemented()) {
            respondsTo = checkVisibilityAndCache(respondToLookupResult, checkVisibility, runtime);
        }
        else {
            respondsTo = runtime.getFalse();
        }
        return new RespondToTuple(newString, checkVisibility, respondToMethod, respondToLookupResult, respondsTo);
    }
    
    private static IRubyObject checkVisibilityAndCache(final CacheEntry respondEntry, final boolean checkVisibility, final Ruby runtime) {
        if (!checkVisibility || respondEntry.method.getVisibility() != Visibility.PRIVATE) {
            return runtime.getTrue();
        }
        return runtime.getFalse();
    }
    
    private static class RespondToTuple
    {
        static final RespondToTuple NULL_CACHE;
        public final String name;
        public final boolean checkVisibility;
        public final CacheEntry respondToMethod;
        public final CacheEntry entry;
        public final IRubyObject respondsTo;
        
        public RespondToTuple(final String name, final boolean checkVisibility, final CacheEntry respondToMethod, final CacheEntry entry, final IRubyObject respondsTo) {
            this.name = name;
            this.checkVisibility = checkVisibility;
            this.respondToMethod = respondToMethod;
            this.entry = entry;
            this.respondsTo = respondsTo;
        }
        
        public boolean cacheOk(final RubyClass klass) {
            return this.respondToMethod.typeOk(klass) && this.entry.typeOk(klass);
        }
        
        static {
            NULL_CACHE = new RespondToTuple("", true, CacheEntry.NULL_CACHE, CacheEntry.NULL_CACHE, null);
        }
    }
}
