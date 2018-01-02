// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.invokers;

import org.jruby.java.dispatch.CallableSelector;
import java.util.Arrays;
import org.jruby.java.proxies.JavaProxy;
import java.lang.reflect.Array;
import org.jruby.java.proxies.ArrayJavaProxy;
import org.jruby.runtime.builtin.IRubyObject;
import java.lang.reflect.AccessibleObject;
import java.util.Iterator;
import org.jruby.Ruby;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import org.jruby.runtime.Arity;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import java.lang.reflect.Member;
import java.util.Map;
import org.jruby.javasupport.JavaCallable;
import org.jruby.internal.runtime.methods.JavaMethod;

public abstract class RubyToJavaInvoker extends JavaMethod
{
    protected static final Object[] EMPTY_OBJECT_ARRAY;
    protected final JavaCallable javaCallable;
    protected final JavaCallable[][] javaCallables;
    protected final JavaCallable[] javaVarargsCallables;
    protected final int minVarargsArity;
    protected final Map cache;
    private Member[] members;
    
    RubyToJavaInvoker(final RubyModule host, Member[] members) {
        super(host, Visibility.PUBLIC);
        this.members = members;
        this.setArity(Arity.OPTIONAL);
        final Ruby runtime = host.getRuntime();
        JavaCallable callable = null;
        JavaCallable[][] callables = null;
        JavaCallable[] varargsCallables = null;
        int varargsArity = Integer.MAX_VALUE;
        if (members.length == 1) {
            callable = this.createCallable(runtime, members[0]);
            if (callable.isVarArgs()) {
                varargsCallables = this.createCallableArray(callable);
            }
        }
        else {
            final Map<Integer, List<JavaCallable>> methodsMap = new HashMap<Integer, List<JavaCallable>>();
            final List<JavaCallable> varargsMethods = new ArrayList<JavaCallable>();
            int maxArity = 0;
            for (final Member method : members) {
                final int currentArity = this.getMemberParameterTypes(method).length;
                maxArity = Math.max(currentArity, maxArity);
                List<JavaCallable> methodsForArity = methodsMap.get(currentArity);
                if (methodsForArity == null) {
                    methodsForArity = new ArrayList<JavaCallable>();
                    methodsMap.put(currentArity, methodsForArity);
                }
                final JavaCallable javaMethod = this.createCallable(runtime, method);
                methodsForArity.add(javaMethod);
                if (this.isMemberVarArgs(method)) {
                    varargsArity = Math.min(currentArity - 1, varargsArity);
                    varargsMethods.add(javaMethod);
                }
            }
            callables = this.createCallableArrayArray(maxArity + 1);
            for (final Map.Entry<Integer, List<JavaCallable>> entry : methodsMap.entrySet()) {
                final List<JavaCallable> methodsForArity2 = entry.getValue();
                final JavaCallable[] methodsArray = methodsForArity2.toArray(this.createCallableArray(methodsForArity2.size()));
                callables[entry.getKey()] = methodsArray;
            }
            if (varargsMethods.size() > 0) {
                varargsCallables = this.createCallableArray(varargsMethods.size());
                varargsMethods.toArray(varargsCallables);
            }
        }
        members = null;
        this.cache = new ConcurrentHashMap(0, 0.75f, 1);
        this.javaCallable = callable;
        this.javaCallables = callables;
        this.javaVarargsCallables = varargsCallables;
        this.minVarargsArity = varargsArity;
    }
    
    protected Member[] getMembers() {
        return this.members;
    }
    
    protected AccessibleObject[] getAccessibleObjects() {
        return (AccessibleObject[])this.getMembers();
    }
    
    protected abstract JavaCallable createCallable(final Ruby p0, final Member p1);
    
    protected abstract JavaCallable[] createCallableArray(final JavaCallable p0);
    
    protected abstract JavaCallable[] createCallableArray(final int p0);
    
    protected abstract JavaCallable[][] createCallableArrayArray(final int p0);
    
    protected abstract Class[] getMemberParameterTypes(final Member p0);
    
    protected abstract boolean isMemberVarArgs(final Member p0);
    
    static Object convertArg(final IRubyObject arg, final JavaCallable method, final int index) {
        return arg.toJava(method.getParameterTypes()[index]);
    }
    
    static Object convertVarargs(final IRubyObject[] args, final JavaCallable method) {
        final Class[] types = method.getParameterTypes();
        final Class varargArrayType = types[types.length - 1];
        final Class varargType = varargArrayType.getComponentType();
        final int varargsStart = types.length - 1;
        final int varargsCount = args.length - varargsStart;
        Object varargs;
        if (varargsCount == 1 && args[varargsStart] instanceof ArrayJavaProxy) {
            varargs = args[varargsStart].toJava(varargArrayType);
        }
        else {
            varargs = Array.newInstance(varargType, varargsCount);
            for (int i = 0; i < varargsCount; ++i) {
                Array.set(varargs, i, args[varargsStart + i].toJava(varargType));
            }
        }
        return varargs;
    }
    
    static JavaProxy castJavaProxy(final IRubyObject self) {
        assert self instanceof JavaProxy : "Java methods can only be invoked on Java objects";
        return (JavaProxy)self;
    }
    
    static void trySetAccessible(final AccessibleObject[] accObjs) {
        if (!Ruby.isSecurityRestricted()) {
            try {
                AccessibleObject.setAccessible(accObjs, true);
            }
            catch (SecurityException ex) {}
        }
    }
    
    void raiseNoMatchingCallableError(final String name, final IRubyObject proxy, final Object... args) {
        final int len = args.length;
        final Class[] argTypes = new Class[args.length];
        for (int i = 0; i < len; ++i) {
            argTypes[i] = args[i].getClass();
        }
        throw proxy.getRuntime().newArgumentError("no " + name + " with arguments matching " + Arrays.toString(argTypes) + " on object " + proxy.getMetaClass());
    }
    
    protected JavaCallable findCallable(final IRubyObject self, final String name, final IRubyObject[] args, final int arity) {
        JavaCallable callable;
        if ((callable = this.javaCallable) == null) {
            JavaCallable[] callablesForArity = null;
            if (arity >= this.javaCallables.length || (callablesForArity = this.javaCallables[arity]) == null) {
                if (this.javaVarargsCallables == null) {
                    throw self.getRuntime().newArgumentError(args.length, this.javaCallables.length - 1);
                }
                callable = CallableSelector.matchingCallableArityN(this.cache, this.javaVarargsCallables, args, arity);
                if (callable == null) {
                    throw CallableSelector.argTypesDoNotMatch(self.getRuntime(), self, this.javaVarargsCallables, (Object[])args);
                }
                return callable;
            }
            else {
                callable = CallableSelector.matchingCallableArityN(this.cache, callablesForArity, args, arity);
                if (callable == null && this.javaVarargsCallables != null) {
                    callable = CallableSelector.matchingCallableArityN(this.cache, this.javaVarargsCallables, args, arity);
                    if (callable == null) {
                        throw CallableSelector.argTypesDoNotMatch(self.getRuntime(), self, this.javaVarargsCallables, (Object[])args);
                    }
                    return callable;
                }
                else if (callable == null) {
                    throw CallableSelector.argTypesDoNotMatch(self.getRuntime(), self, callablesForArity, (Object[])args);
                }
            }
        }
        else if (!callable.isVarArgs() && callable.getParameterTypes().length != args.length) {
            throw self.getRuntime().newArgumentError(args.length, callable.getParameterTypes().length);
        }
        return callable;
    }
    
    protected JavaCallable findCallableArityZero(final IRubyObject self, final String name) {
        JavaCallable callable;
        if ((callable = this.javaCallable) == null) {
            JavaCallable[] callablesForArity = null;
            if (this.javaCallables.length == 0 || (callablesForArity = this.javaCallables[0]) == null) {
                this.raiseNoMatchingCallableError(name, self, RubyToJavaInvoker.EMPTY_OBJECT_ARRAY);
            }
            callable = callablesForArity[0];
        }
        else if (callable.getParameterTypes().length != 0) {
            throw self.getRuntime().newArgumentError(0, callable.getParameterTypes().length);
        }
        return callable;
    }
    
    protected JavaCallable findCallableArityOne(final IRubyObject self, final String name, final IRubyObject arg0) {
        JavaCallable callable;
        if ((callable = this.javaCallable) == null) {
            JavaCallable[] callablesForArity = null;
            if (this.javaCallables.length <= 1 || (callablesForArity = this.javaCallables[1]) == null) {
                throw self.getRuntime().newArgumentError(1, this.javaCallables.length - 1);
            }
            callable = CallableSelector.matchingCallableArityOne(this.cache, callablesForArity, arg0);
            if (callable == null) {
                throw CallableSelector.argTypesDoNotMatch(self.getRuntime(), self, callablesForArity, arg0);
            }
        }
        else if (callable.getParameterTypes().length != 1) {
            throw self.getRuntime().newArgumentError(1, callable.getParameterTypes().length);
        }
        return callable;
    }
    
    protected JavaCallable findCallableArityTwo(final IRubyObject self, final String name, final IRubyObject arg0, final IRubyObject arg1) {
        JavaCallable callable;
        if ((callable = this.javaCallable) == null) {
            JavaCallable[] callablesForArity = null;
            if (this.javaCallables.length <= 2 || (callablesForArity = this.javaCallables[2]) == null) {
                throw self.getRuntime().newArgumentError(2, this.javaCallables.length - 1);
            }
            callable = CallableSelector.matchingCallableArityTwo(this.cache, callablesForArity, arg0, arg1);
            if (callable == null) {
                throw CallableSelector.argTypesDoNotMatch(self.getRuntime(), self, callablesForArity, arg0, arg1);
            }
        }
        else if (callable.getParameterTypes().length != 2) {
            throw self.getRuntime().newArgumentError(2, callable.getParameterTypes().length);
        }
        return callable;
    }
    
    protected JavaCallable findCallableArityThree(final IRubyObject self, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        JavaCallable callable;
        if ((callable = this.javaCallable) == null) {
            JavaCallable[] callablesForArity = null;
            if (this.javaCallables.length <= 3 || (callablesForArity = this.javaCallables[3]) == null) {
                throw self.getRuntime().newArgumentError(3, this.javaCallables.length - 1);
            }
            callable = CallableSelector.matchingCallableArityThree(this.cache, callablesForArity, arg0, arg1, arg2);
            if (callable == null) {
                throw CallableSelector.argTypesDoNotMatch(self.getRuntime(), self, callablesForArity, arg0, arg1, arg2);
            }
        }
        else if (callable.getParameterTypes().length != 3) {
            throw self.getRuntime().newArgumentError(3, callable.getParameterTypes().length);
        }
        return callable;
    }
    
    protected JavaCallable findCallableArityFour(final IRubyObject self, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3) {
        JavaCallable callable;
        if ((callable = this.javaCallable) == null) {
            JavaCallable[] callablesForArity = null;
            if (this.javaCallables.length <= 4 || (callablesForArity = this.javaCallables[4]) == null) {
                throw self.getRuntime().newArgumentError(4, this.javaCallables.length - 1);
            }
            callable = CallableSelector.matchingCallableArityFour(this.cache, callablesForArity, arg0, arg1, arg2, arg3);
            if (callable == null) {
                throw CallableSelector.argTypesDoNotMatch(self.getRuntime(), self, callablesForArity, arg0, arg1, arg2, arg3);
            }
        }
        else if (callable.getParameterTypes().length != 4) {
            throw self.getRuntime().newArgumentError(4, callable.getParameterTypes().length);
        }
        return callable;
    }
    
    static {
        EMPTY_OBJECT_ARRAY = new Object[0];
    }
}
