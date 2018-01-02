// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.proxies;

import org.jruby.runtime.Arity;
import java.lang.reflect.Constructor;
import org.jruby.javasupport.JavaUtilities;
import org.jruby.javasupport.JavaObject;
import org.jruby.RubyInstanceConfig;
import org.jruby.exceptions.RaiseException;
import org.jruby.javasupport.Java;
import org.jruby.RubyArray;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.internal.runtime.methods.DynamicMethod;
import java.lang.reflect.Method;
import org.jruby.internal.runtime.methods.UndefinedMethod;
import org.jruby.runtime.Block;
import org.jruby.internal.runtime.methods.JavaMethod;
import org.jruby.runtime.Visibility;
import org.jruby.javasupport.JavaClass;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyClass;
import org.jruby.Ruby;
import org.jruby.RubyModule;
import org.jruby.runtime.ThreadContext;

public class JavaInterfaceTemplate
{
    public static RubyModule createJavaInterfaceTemplateModule(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final RubyModule javaInterfaceTemplate = runtime.defineModule("JavaInterfaceTemplate");
        final RubyClass singleton = javaInterfaceTemplate.getSingletonClass();
        singleton.addReadAttribute(context, "java_class");
        singleton.defineAnnotatedMethods(JavaInterfaceTemplate.class);
        return javaInterfaceTemplate;
    }
    
    @JRubyMethod(backtrace = true, visibility = Visibility.PRIVATE)
    public static IRubyObject implement(final ThreadContext context, final IRubyObject self, final IRubyObject clazz) {
        final Ruby runtime = context.getRuntime();
        if (!(clazz instanceof RubyModule)) {
            throw runtime.newTypeError(clazz, runtime.getModule());
        }
        final RubyModule targetModule = (RubyModule)clazz;
        final JavaClass javaClass = (JavaClass)self.getInstanceVariables().fastGetInstanceVariable("@java_class");
        final Method[] javaInstanceMethods = javaClass.javaClass().getMethods();
        final DynamicMethod dummyMethod = new JavaMethod(targetModule, Visibility.PUBLIC) {
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
                return context.getRuntime().getNil();
            }
        };
        for (int i = 0; i < javaInstanceMethods.length; ++i) {
            final Method method = javaInstanceMethods[i];
            final String name = method.getName();
            if (targetModule.searchMethod(name) == UndefinedMethod.INSTANCE) {
                targetModule.addMethod(name, dummyMethod);
            }
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(frame = true)
    public static IRubyObject append_features(final ThreadContext context, final IRubyObject self, final IRubyObject clazz, final Block block) {
        if (clazz instanceof RubyClass) {
            appendFeaturesToClass(context, self, (RubyClass)clazz);
        }
        else {
            if (!(clazz instanceof RubyModule)) {
                throw context.getRuntime().newTypeError("received " + clazz + ", expected Class/Module");
            }
            appendFeaturesToModule(context, self, (RubyModule)clazz);
        }
        return RuntimeHelpers.invokeSuper(context, self, clazz, block);
    }
    
    private static void appendFeaturesToClass(final ThreadContext context, final IRubyObject self, final RubyClass clazz) {
        final Ruby runtime = context.getRuntime();
        checkAlreadyReified(clazz, runtime);
        final IRubyObject javaClassObj = RuntimeHelpers.getInstanceVariable(self, runtime, "@java_class");
        if (!clazz.hasInstanceVariable("@java_interfaces")) {
            final IRubyObject javaInterfaces = RubyArray.newArray(runtime, javaClassObj);
            RuntimeHelpers.setInstanceVariable(javaInterfaces, clazz, "@java_interfaces");
            initInterfaceImplMethods(context, clazz);
        }
        else {
            final IRubyObject javaInterfaces = RuntimeHelpers.getInstanceVariable(clazz, runtime, "@java_interfaces");
            if (!javaInterfaces.isFrozen() && !((RubyArray)javaInterfaces).includes(context, javaClassObj)) {
                ((RubyArray)javaInterfaces).append(javaClassObj);
            }
        }
    }
    
    private static void checkAlreadyReified(final RubyClass clazz, final Ruby runtime) throws RaiseException {
        if ((Java.NEW_STYLE_EXTENSION && clazz.getReifiedClass() != null) || (clazz.hasInstanceVariable("@java_class") && clazz.getInstanceVariable("@java_class").isTrue() && !clazz.getSingletonClass().isMethodBound("java_proxy_class", false)) || (clazz.hasInstanceVariable("@java_proxy_class") && clazz.getInstanceVariable("@java_proxy_class").isTrue())) {
            throw runtime.newArgumentError("can not add Java interface to existing Java class");
        }
    }
    
    private static void initInterfaceImplMethods(final ThreadContext context, final RubyClass clazz) {
        if (!clazz.isMethodBound("__jcreate!", false) && !clazz.isMethodBound("__jcreate_meta!", false)) {
            final RubyClass singleton = clazz.getSingletonClass();
            singleton.addReadAttribute(context, "java_interfaces");
            if ((!Java.NEW_STYLE_EXTENSION && clazz.getSuperClass().getRealClass().hasInstanceVariable("@java_class")) || RubyInstanceConfig.INTERFACES_USE_PROXY) {
                singleton.addMethod("new", new JavaMethod(singleton, Visibility.PUBLIC) {
                    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
                        assert self instanceof RubyClass : "new defined on non-class";
                        final RubyClass clazzSelf = (RubyClass)self;
                        final IRubyObject newObj = clazzSelf.allocate();
                        RuntimeHelpers.invoke(context, newObj, "__jcreate!", args, block);
                        RuntimeHelpers.invoke(context, newObj, "initialize", args, block);
                        return newObj;
                    }
                });
                clazz.addMethod("__jcreate!", new JavaMethod.JavaMethodN(clazz, Visibility.PRIVATE) {
                    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args) {
                        return jcreateProxy(self, args);
                    }
                });
            }
            else {
                addRealImplClassNew(clazz);
            }
            clazz.addMethod("__jcreate_meta!", new JavaMethod.JavaMethodN(clazz, Visibility.PRIVATE) {
                public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args) {
                    final IRubyObject result = jcreateProxy(self, args);
                    return result;
                }
            });
            clazz.addMethod("java_class", new JavaMethod.JavaMethodZero(clazz, Visibility.PUBLIC) {
                public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name) {
                    return ((JavaObject)self.dataGetStruct()).java_class();
                }
            });
            clazz.defineAlias("old_eqq", "===");
            clazz.addMethod("===", new JavaMethod.JavaMethodOne(clazz, Visibility.PUBLIC) {
                public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg) {
                    if (!arg.respondsTo("java_object")) {
                        return RuntimeHelpers.invoke(context, self, "old_eqq", arg);
                    }
                    final IRubyObject interfaces = self.getMetaClass().getInstanceVariables().fastGetInstanceVariable("@java_interfaces");
                    assert interfaces instanceof RubyArray : "interface list was not an array";
                    return context.getRuntime().newBoolean(((RubyArray)interfaces).op_diff(((JavaClass)((JavaObject)arg.dataGetStruct()).java_class()).interfaces()).equals(RubyArray.newArray(context.getRuntime())));
                }
            });
        }
        if (!clazz.isMethodBound("implement", false)) {
            final RubyClass singleton = clazz.getSingletonClass();
            singleton.addMethod("implement", new JavaMethod.JavaMethodOne(clazz, Visibility.PRIVATE) {
                public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg) {
                    final IRubyObject javaInterfaces = self.getInstanceVariables().fastGetInstanceVariable("@java_interfaces");
                    if (javaInterfaces != null && ((RubyArray)javaInterfaces).includes(context, arg)) {
                        return RuntimeHelpers.invoke(context, arg, "implement", self);
                    }
                    return context.getRuntime().getNil();
                }
            });
            singleton.addMethod("implement_all", new JavaMethod.JavaMethodOne(clazz, Visibility.PRIVATE) {
                public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg) {
                    final RubyArray javaInterfaces = (RubyArray)self.getInstanceVariables().fastGetInstanceVariable("@java_interfaces");
                    for (int i = 0; i < javaInterfaces.size(); ++i) {
                        RuntimeHelpers.invoke(context, JavaUtilities.get_interface_module(self, javaInterfaces.eltInternal(i)), "implement", self);
                    }
                    return javaInterfaces;
                }
            });
        }
    }
    
    public static void addRealImplClassNew(final RubyClass clazz) {
        final RubyClass singleton = clazz.getSingletonClass();
        singleton.addMethod("new", new JavaMethod(singleton, Visibility.PUBLIC) {
            private Constructor proxyConstructor;
            
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
                assert self instanceof RubyClass : "new defined on non-class";
                final RubyClass clazzSelf = (RubyClass)self;
                Class reifiedClass = clazzSelf.getReifiedClass();
                if (this.proxyConstructor == null || this.proxyConstructor.getDeclaringClass() != reifiedClass) {
                    if (reifiedClass == null) {
                        reifiedClass = Java.generateRealClass(clazzSelf);
                    }
                    this.proxyConstructor = Java.getRealClassConstructor(context.getRuntime(), reifiedClass);
                }
                final IRubyObject newObj = Java.constructProxy(context.getRuntime(), this.proxyConstructor, clazzSelf);
                RuntimeHelpers.invoke(context, newObj, "initialize", args, block);
                return newObj;
            }
        });
    }
    
    private static IRubyObject jcreateProxy(final IRubyObject self, final IRubyObject[] args) {
        final RubyClass current = self.getMetaClass();
        final IRubyObject newObject = Java.newInterfaceImpl(self, Java.getInterfacesFromRubyClass(current));
        return JavaUtilities.set_java_object(self, self, newObject);
    }
    
    private static void appendFeaturesToModule(final ThreadContext context, final IRubyObject self, final RubyModule module) {
        final Ruby runtime = context.getRuntime();
        if (module.getInstanceVariables().fastHasInstanceVariable("@java_class") && module.getInstanceVariables().fastGetInstanceVariable("@java_class").isTrue()) {
            throw runtime.newTypeError("can not add Java interface to existing Java interface");
        }
        synchronized (module) {
            if (!module.getInstanceVariables().fastHasInstanceVariable("@java_interface_mods")) {
                final RubyArray javaInterfaceMods = RubyArray.newArray(runtime, self);
                module.getInstanceVariables().fastSetInstanceVariable("@java_interface_mods", javaInterfaceMods);
                final RubyClass singleton = module.getSingletonClass();
                singleton.addMethod("append_features", new JavaMethod.JavaMethodOneBlock(singleton, Visibility.PUBLIC) {
                    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg, final Block block) {
                        if (!(arg instanceof RubyClass)) {
                            throw context.getRuntime().newTypeError("append_features called with non-class");
                        }
                        final RubyClass target = (RubyClass)arg;
                        final RubyArray javaInterfaceMods = (RubyArray)self.getInstanceVariables().fastGetInstanceVariable("@java_interface_mods");
                        target.include(javaInterfaceMods.toJavaArray());
                        return RuntimeHelpers.invokeAs(context, clazz.getSuperClass(), self, name, arg, block);
                    }
                });
            }
            else {
                final RubyArray javaInterfaceMods = (RubyArray)module.getInstanceVariables().fastGetInstanceVariable("@java_interface_mods");
                if (!javaInterfaceMods.includes(context, self)) {
                    javaInterfaceMods.append(self);
                }
            }
        }
    }
    
    @JRubyMethod
    public static IRubyObject extended(final ThreadContext context, final IRubyObject self, final IRubyObject object) {
        if (!(self instanceof RubyModule)) {
            throw context.getRuntime().newTypeError(self, context.getRuntime().getModule());
        }
        final RubyClass singleton = object.getSingletonClass();
        singleton.include(new IRubyObject[] { self });
        return singleton;
    }
    
    @JRubyMethod(name = { "[]" }, rest = true, backtrace = true)
    public static IRubyObject op_aref(final ThreadContext context, final IRubyObject self, final IRubyObject[] args) {
        return JavaProxy.op_aref(context, self, args);
    }
    
    @JRubyMethod(rest = true, backtrace = true)
    public static IRubyObject impl(final ThreadContext context, final IRubyObject self, final IRubyObject[] args, final Block implBlock) {
        final Ruby runtime = context.getRuntime();
        if (!implBlock.isGiven()) {
            throw runtime.newArgumentError("block required to call #impl on a Java interface");
        }
        final RubyArray methodNames = (args.length > 0) ? runtime.newArray(args) : null;
        final RubyClass implClass = RubyClass.newClass(runtime, runtime.getObject());
        implClass.include(new IRubyObject[] { self });
        final IRubyObject implObject = implClass.callMethod(context, "new");
        implClass.addMethod("method_missing", new JavaMethod(implClass, Visibility.PUBLIC) {
            public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
                Arity.checkArgumentCount(context.getRuntime(), args.length, 1, -1);
                if (methodNames == null || methodNames.include_p(context, args[0]).isTrue()) {
                    return implBlock.call(context, args);
                }
                return clazz.getSuperClass().callMethod(context, "method_missing", args, block);
            }
        });
        return implObject;
    }
    
    @JRubyMethod(name = { "new" }, rest = true, backtrace = true)
    public static IRubyObject rbNew(final ThreadContext context, final IRubyObject self, final IRubyObject[] args, final Block block) {
        final Ruby runtime = context.getRuntime();
        RubyClass implClass = (RubyClass)self.getInstanceVariables().getInstanceVariable("@__implementation");
        if (implClass == null) {
            implClass = RubyClass.newClass(runtime, runtime.getClass("InterfaceJavaProxy"));
            implClass.include(new IRubyObject[] { self });
            RuntimeHelpers.setInstanceVariable(implClass, self, "@__implementation");
        }
        return RuntimeHelpers.invoke(context, implClass, "new", args, block);
    }
}
