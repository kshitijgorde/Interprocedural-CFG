// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.java.dispatch;

import org.jruby.util.CodegenUtils;
import org.jruby.javasupport.JavaMethod;
import org.jruby.javasupport.proxy.JavaProxyConstructor;
import org.jruby.javasupport.JavaConstructor;
import org.jruby.runtime.ThreadContext;
import org.jruby.java.proxies.ConcreteJavaProxy;
import org.jruby.exceptions.RaiseException;
import org.jruby.Ruby;
import org.jruby.javasupport.JavaUtil;
import org.jruby.javasupport.JavaClass;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import org.jruby.RubyClass;
import java.util.List;
import org.jruby.javasupport.JavaCallable;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.ParameterTypes;
import java.util.Map;

public class CallableSelector
{
    private static final CallableAcceptor Exact;
    private static final CallableAcceptor AssignableAndPrimitivable;
    private static final CallableAcceptor AssignableOrDuckable;
    private static final CallableAcceptor AssignableAndPrimitivableWithVarargs;
    private static Matcher EXACT;
    private static Matcher ASSIGNABLE;
    private static Matcher PRIMITIVABLE;
    private static Matcher DUCKABLE;
    private static final Matcher[] MATCH_SEQUENCE;
    
    public static ParameterTypes matchingCallableArityN(final Map cache, final ParameterTypes[] methods, final IRubyObject[] args, final int argsLength) {
        final int signatureCode = argsHashCode(args);
        ParameterTypes method = cache.get(signatureCode);
        if (method == null) {
            method = findMatchingCallableForArgs(cache, signatureCode, methods, args);
        }
        return method;
    }
    
    public static JavaCallable matchingCallableArityN(final Map cache, final JavaCallable[] methods, final IRubyObject[] args, final int argsLength) {
        final int signatureCode = argsHashCode(args);
        JavaCallable method = cache.get(signatureCode);
        if (method == null) {
            method = (JavaCallable)findMatchingCallableForArgs(cache, signatureCode, methods, args);
        }
        return method;
    }
    
    public static JavaCallable matchingCallableArityOne(final Map cache, final JavaCallable[] methods, final IRubyObject arg0) {
        final int signatureCode = argsHashCode(arg0);
        JavaCallable method = cache.get(signatureCode);
        if (method == null) {
            method = (JavaCallable)findMatchingCallableForArgs(cache, signatureCode, methods, arg0);
        }
        return method;
    }
    
    public static JavaCallable matchingCallableArityTwo(final Map cache, final JavaCallable[] methods, final IRubyObject arg0, final IRubyObject arg1) {
        final int signatureCode = argsHashCode(arg0, arg1);
        JavaCallable method = cache.get(signatureCode);
        if (method == null) {
            method = (JavaCallable)findMatchingCallableForArgs(cache, signatureCode, methods, arg0, arg1);
        }
        return method;
    }
    
    public static JavaCallable matchingCallableArityThree(final Map cache, final JavaCallable[] methods, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        final int signatureCode = argsHashCode(arg0, arg1, arg2);
        JavaCallable method = cache.get(signatureCode);
        if (method == null) {
            method = (JavaCallable)findMatchingCallableForArgs(cache, signatureCode, methods, arg0, arg1, arg2);
        }
        return method;
    }
    
    public static JavaCallable matchingCallableArityFour(final Map cache, final JavaCallable[] methods, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final IRubyObject arg3) {
        final int signatureCode = argsHashCode(arg0, arg1, arg2, arg3);
        JavaCallable method = cache.get(signatureCode);
        if (method == null) {
            method = (JavaCallable)findMatchingCallableForArgs(cache, signatureCode, methods, arg0, arg1, arg2, arg3);
        }
        return method;
    }
    
    private static ParameterTypes findMatchingCallableForArgs(final Map cache, final int signatureCode, final ParameterTypes[] methods, final IRubyObject... args) {
        ParameterTypes method = null;
        final List<ParameterTypes> newFinds = findCallable(methods, args);
        if (newFinds.size() > 0) {
            if (newFinds.size() > 1 && args[0].getRuntime().isDebug()) {
                warnMultipleMatches(args, newFinds);
            }
            method = newFinds.get(0);
            return method;
        }
        if (method == null) {
            method = findCallable(methods, CallableSelector.Exact, args);
        }
        if (method == null) {
            method = findCallable(methods, CallableSelector.AssignableAndPrimitivable, args);
        }
        if (method == null) {
            method = findCallable(methods, CallableSelector.AssignableOrDuckable, args);
        }
        if (method == null) {
            method = findCallable(methods, CallableSelector.AssignableAndPrimitivableWithVarargs, args);
        }
        if (method != null) {
            cache.put(signatureCode, method);
        }
        return method;
    }
    
    private static void warnMultipleMatches(final IRubyObject[] args, final List<ParameterTypes> newFinds) {
        final RubyClass[] argTypes = new RubyClass[args.length];
        for (int i = 0; i < argTypes.length; ++i) {
            argTypes[i] = args[i].getMetaClass();
        }
        final StringBuilder builder = new StringBuilder("multiple Java methods for arguments (");
        boolean first = true;
        for (final RubyClass argType : argTypes) {
            if (!first) {
                builder.append(",");
            }
            first = false;
            builder.append(argType);
        }
        builder.append("), using first:");
        for (final ParameterTypes types : newFinds) {
            builder.append("\n  ").append(types);
        }
        args[0].getRuntime().getWarnings().warn(builder.toString());
    }
    
    private static ParameterTypes findCallable(final ParameterTypes[] callables, final CallableAcceptor acceptor, final IRubyObject... args) {
        ParameterTypes bestCallable = null;
        int bestScore = -1;
        for (int k = 0; k < callables.length; ++k) {
            final ParameterTypes callable = callables[k];
            if (acceptor.accept(callable, args)) {
                final int currentScore = getExactnessScore(callable, args);
                if (currentScore > bestScore) {
                    bestCallable = callable;
                    bestScore = currentScore;
                }
            }
        }
        return bestCallable;
    }
    
    private static List<ParameterTypes> findCallable(final ParameterTypes[] callables, final IRubyObject... args) {
        final List<ParameterTypes> retainedCallables = new ArrayList<ParameterTypes>(callables.length);
        final List<ParameterTypes> incomingCallables = new ArrayList<ParameterTypes>(Arrays.asList(callables));
        for (int currentArg = 0; currentArg < args.length; ++currentArg) {
            retainedCallables.clear();
            for (final Matcher matcher : CallableSelector.MATCH_SEQUENCE) {
                final Iterator<ParameterTypes> callableIter = incomingCallables.iterator();
                while (callableIter.hasNext()) {
                    final ParameterTypes callable = callableIter.next();
                    final Class[] types = callable.getParameterTypes();
                    if (matcher.match(types[currentArg], args[currentArg])) {
                        callableIter.remove();
                        retainedCallables.add(callable);
                    }
                }
            }
            incomingCallables.clear();
            incomingCallables.addAll(retainedCallables);
        }
        return retainedCallables;
    }
    
    private static int getExactnessScore(final ParameterTypes paramTypes, final IRubyObject[] args) {
        final Class[] types = paramTypes.getParameterTypes();
        int count = 0;
        if (paramTypes.isVarArgs()) {
            final int nonVarargs = types.length - 1;
            ++count;
            for (int i = 0; i < nonVarargs; ++i) {
                if (types[i].equals(argClass(args[i]))) {
                    ++count;
                }
            }
        }
        else {
            for (int j = 0; j < args.length; ++j) {
                if (types[j].equals(argClass(args[j]))) {
                    ++count;
                }
            }
        }
        return count;
    }
    
    private static boolean exactMatch(final ParameterTypes paramTypes, final IRubyObject... args) {
        final Class[] types = paramTypes.getParameterTypes();
        for (int i = 0; i < types.length; ++i) {
            if (!CallableSelector.EXACT.match(types[i], args[i])) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean assignableAndPrimitivable(final ParameterTypes paramTypes, final IRubyObject... args) {
        final Class[] types = paramTypes.getParameterTypes();
        for (int i = 0; i < types.length; ++i) {
            if (!CallableSelector.ASSIGNABLE.match(types[i], args[i]) || !CallableSelector.PRIMITIVABLE.match(types[i], args[i])) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean assignableOrDuckable(final ParameterTypes paramTypes, final IRubyObject... args) {
        final Class[] types = paramTypes.getParameterTypes();
        for (int i = 0; i < types.length; ++i) {
            if (!CallableSelector.ASSIGNABLE.match(types[i], args[i]) && !CallableSelector.DUCKABLE.match(types[i], args[i])) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean assignableAndPrimitivableWithVarargs(final ParameterTypes paramTypes, final IRubyObject... args) {
        if (!paramTypes.isVarArgs()) {
            return false;
        }
        final Class[] types = paramTypes.getParameterTypes();
        final Class varArgArrayType = types[types.length - 1];
        final Class varArgType = varArgArrayType.getComponentType();
        final int nonVarargs = types.length - 1;
        for (int i = args.length - 1; i >= nonVarargs; --i) {
            if (!CallableSelector.ASSIGNABLE.match(varArgType, args[i]) && !CallableSelector.PRIMITIVABLE.match(varArgType, args[i])) {
                return false;
            }
        }
        for (int i = 0; i < nonVarargs; ++i) {
            if (!CallableSelector.ASSIGNABLE.match(types[i], args[i]) && !CallableSelector.PRIMITIVABLE.match(types[i], args[i])) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean assignable(final Class type, final IRubyObject arg) {
        return JavaClass.assignable(type, argClass(arg));
    }
    
    private static boolean primitivable(final Class type, final IRubyObject arg) {
        final Class argClass = argClass(arg);
        if (type.isPrimitive()) {
            if (type == Integer.TYPE || type == Long.TYPE || type == Short.TYPE || type == Character.TYPE) {
                return argClass == Long.TYPE || argClass == Byte.TYPE || argClass == Short.TYPE || argClass == Character.TYPE || argClass == Integer.TYPE || argClass == Long.class || argClass == Byte.class || argClass == Short.class || argClass == Character.class || argClass == Integer.class;
            }
            if (type == Float.TYPE || type == Double.TYPE) {
                return argClass == Double.TYPE || argClass == Float.TYPE || argClass == Float.class || argClass == Double.class;
            }
            if (type == Boolean.TYPE) {
                return argClass == Boolean.TYPE || argClass == Boolean.class;
            }
        }
        return false;
    }
    
    private static boolean duckable(final Class type, final IRubyObject arg) {
        return JavaUtil.isDuckTypeConvertable(argClass(arg), type);
    }
    
    private static int argsHashCode(final IRubyObject a0) {
        return 31 + classHashCode(a0);
    }
    
    private static int argsHashCode(final IRubyObject a0, final IRubyObject a1) {
        return 31 * argsHashCode(a0) + classHashCode(a1);
    }
    
    private static int argsHashCode(final IRubyObject a0, final IRubyObject a1, final IRubyObject a2) {
        return 31 * argsHashCode(a0, a1) + classHashCode(a2);
    }
    
    private static int argsHashCode(final IRubyObject a0, final IRubyObject a1, final IRubyObject a2, final IRubyObject a3) {
        return 31 * argsHashCode(a0, a1, a2) + classHashCode(a3);
    }
    
    private static int argsHashCode(final IRubyObject[] a) {
        if (a == null) {
            return 0;
        }
        int result = 1;
        for (final IRubyObject element : a) {
            result = 31 * result + classHashCode(element);
        }
        return result;
    }
    
    private static int classHashCode(final IRubyObject o) {
        return (o == null) ? 0 : o.getJavaClass().hashCode();
    }
    
    private static Class argClass(final IRubyObject a) {
        if (a == null) {
            return Void.TYPE;
        }
        return a.getJavaClass();
    }
    
    public static RaiseException argTypesDoNotMatch(final Ruby runtime, final IRubyObject receiver, final Object[] methods, final Object... args) {
        final ArrayList<Class<?>> argTypes = new ArrayList<Class<?>>(args.length);
        for (final Object o : args) {
            argTypes.add(argClassTypeError(o));
        }
        return argumentError(runtime.getCurrentContext(), methods[0], receiver, argTypes);
    }
    
    private static Class argClassTypeError(final Object object) {
        if (object == null) {
            return Void.TYPE;
        }
        if (object instanceof ConcreteJavaProxy) {
            return ((ConcreteJavaProxy)object).getJavaClass();
        }
        return object.getClass();
    }
    
    private static RaiseException argumentError(final ThreadContext context, final Object method, final IRubyObject receiver, final List<Class<?>> argTypes) {
        final String methodName = (method instanceof JavaConstructor || method instanceof JavaProxyConstructor) ? "constructor" : ((JavaMethod)method).name().toString();
        return context.getRuntime().newNameError("no " + methodName + " with arguments matching " + argTypes + " on object " + receiver.callMethod(context, "inspect"), null);
    }
    
    static {
        Exact = new CallableAcceptor() {
            public boolean accept(final ParameterTypes types, final IRubyObject[] args) {
                return exactMatch(types, args);
            }
        };
        AssignableAndPrimitivable = new CallableAcceptor() {
            public boolean accept(final ParameterTypes types, final IRubyObject[] args) {
                return assignableAndPrimitivable(types, args);
            }
        };
        AssignableOrDuckable = new CallableAcceptor() {
            public boolean accept(final ParameterTypes types, final IRubyObject[] args) {
                return assignableOrDuckable(types, args);
            }
        };
        AssignableAndPrimitivableWithVarargs = new CallableAcceptor() {
            public boolean accept(final ParameterTypes types, final IRubyObject[] args) {
                return assignableAndPrimitivableWithVarargs(types, args);
            }
        };
        CallableSelector.EXACT = new Matcher() {
            public boolean match(final Class type, final IRubyObject arg) {
                return type.equals(argClass(arg)) || (type.isPrimitive() && CodegenUtils.getBoxType(type) == argClass(arg));
            }
        };
        CallableSelector.ASSIGNABLE = new Matcher() {
            public boolean match(final Class type, final IRubyObject arg) {
                return assignable(type, arg);
            }
        };
        CallableSelector.PRIMITIVABLE = new Matcher() {
            public boolean match(final Class type, final IRubyObject arg) {
                return primitivable(type, arg);
            }
        };
        CallableSelector.DUCKABLE = new Matcher() {
            public boolean match(final Class type, final IRubyObject arg) {
                return duckable(type, arg);
            }
        };
        MATCH_SEQUENCE = new Matcher[] { CallableSelector.EXACT, CallableSelector.PRIMITIVABLE, CallableSelector.ASSIGNABLE, CallableSelector.DUCKABLE };
    }
    
    private interface Matcher
    {
        boolean match(final Class p0, final IRubyObject p1);
    }
    
    private interface CallableAcceptor
    {
        boolean accept(final ParameterTypes p0, final IRubyObject[] p1);
    }
}
