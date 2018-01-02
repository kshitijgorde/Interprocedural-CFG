// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.proxies;

import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.MethodIndex;
import org.jruby.RubyModule;
import org.jruby.runtime.CallSite;
import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.runtime.Visibility;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyClass;
import org.jruby.Ruby;

public class ConcreteJavaProxy extends JavaProxy
{
    public ConcreteJavaProxy(final Ruby runtime, final RubyClass klazz) {
        super(runtime, klazz);
    }
    
    public static RubyClass createConcreteJavaProxy(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final RubyClass concreteJavaProxy = runtime.defineClass("ConcreteJavaProxy", runtime.getJavaSupport().getJavaProxyClass(), new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klazz) {
                return new ConcreteJavaProxy(runtime, klazz);
            }
        });
        initialize(concreteJavaProxy);
        return concreteJavaProxy;
    }
    
    protected static void initialize(final RubyClass concreteJavaProxy) {
        concreteJavaProxy.addMethod("initialize", new JavaMethod(concreteJavaProxy, Visibility.PUBLIC) {
            private final CallSite jcreateSite = MethodIndex.getFunctionalCallSite("__jcreate!");
            
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
                return this.jcreateSite.call(context, self, self, args, block);
            }
            
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final Block block) {
                return this.jcreateSite.call(context, self, self, block);
            }
            
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final Block block) {
                return this.jcreateSite.call(context, self, self, arg0, block);
            }
            
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
                return this.jcreateSite.call(context, self, self, arg0, arg1, block);
            }
            
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
                return this.jcreateSite.call(context, self, self, arg0, arg1, arg2, block);
            }
            
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args) {
                return this.jcreateSite.call(context, self, self, args);
            }
            
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name) {
                return this.jcreateSite.call(context, self, self);
            }
            
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0) {
                return this.jcreateSite.call(context, self, self, arg0);
            }
            
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1) {
                return this.jcreateSite.call(context, self, self, arg0, arg1);
            }
            
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
                return this.jcreateSite.call(context, self, self, arg0, arg1, arg2);
            }
        });
        final RubyClass singleton = concreteJavaProxy.getSingletonClass();
        final DynamicMethod oldNew = singleton.searchMethod("new");
        singleton.addMethod("new", new JavaMethod(singleton, Visibility.PUBLIC) {
            private final CallSite jcreateSite = MethodIndex.getFunctionalCallSite("__jcreate!");
            
            private boolean needsCreate(final IRubyObject recv) {
                return ((JavaProxy)recv).object == null;
            }
            
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
                final IRubyObject proxy = oldNew.call(context, self, clazz, "new_proxy", args, block);
                if (this.needsCreate(proxy)) {
                    this.jcreateSite.call(context, proxy, proxy, args, block);
                }
                return proxy;
            }
            
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final Block block) {
                final IRubyObject proxy = oldNew.call(context, self, clazz, "new_proxy", block);
                if (this.needsCreate(proxy)) {
                    this.jcreateSite.call(context, proxy, proxy, block);
                }
                return proxy;
            }
            
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final Block block) {
                final IRubyObject proxy = oldNew.call(context, self, clazz, "new_proxy", arg0, block);
                if (this.needsCreate(proxy)) {
                    this.jcreateSite.call(context, proxy, proxy, arg0, block);
                }
                return proxy;
            }
            
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
                final IRubyObject proxy = oldNew.call(context, self, clazz, "new_proxy", arg0, arg1, block);
                if (this.needsCreate(proxy)) {
                    this.jcreateSite.call(context, proxy, proxy, arg0, arg1, block);
                }
                return proxy;
            }
            
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
                final IRubyObject proxy = oldNew.call(context, self, clazz, "new_proxy", arg0, arg1, arg2, block);
                if (this.needsCreate(proxy)) {
                    this.jcreateSite.call(context, proxy, proxy, arg0, arg1, arg2, block);
                }
                return proxy;
            }
            
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args) {
                final IRubyObject proxy = oldNew.call(context, self, clazz, "new_proxy", args);
                if (this.needsCreate(proxy)) {
                    this.jcreateSite.call(context, proxy, proxy, args);
                }
                return proxy;
            }
            
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name) {
                final IRubyObject proxy = oldNew.call(context, self, clazz, "new_proxy");
                if (this.needsCreate(proxy)) {
                    this.jcreateSite.call(context, proxy, proxy);
                }
                return proxy;
            }
            
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0) {
                final IRubyObject proxy = oldNew.call(context, self, clazz, "new_proxy", arg0);
                if (this.needsCreate(proxy)) {
                    this.jcreateSite.call(context, proxy, proxy, arg0);
                }
                return proxy;
            }
            
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1) {
                final IRubyObject proxy = oldNew.call(context, self, clazz, "new_proxy", arg0, arg1);
                if (this.needsCreate(proxy)) {
                    this.jcreateSite.call(context, proxy, proxy, arg0, arg1);
                }
                return proxy;
            }
            
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
                final IRubyObject proxy = oldNew.call(context, self, clazz, "new_proxy", arg0, arg1, arg2);
                if (this.needsCreate(proxy)) {
                    this.jcreateSite.call(context, proxy, proxy, arg0, arg1, arg2);
                }
                return proxy;
            }
        });
    }
    
    public IRubyObject id() {
        return this.getRuntime().newFixnum(System.identityHashCode(this.getObject()));
    }
}
