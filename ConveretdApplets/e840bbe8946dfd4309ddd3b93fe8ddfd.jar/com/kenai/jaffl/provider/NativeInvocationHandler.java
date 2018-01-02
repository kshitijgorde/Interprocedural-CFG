// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider;

import com.kenai.jaffl.Platform;
import com.kenai.jaffl.annotations.Synchronized;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import com.kenai.jaffl.CallingConvention;
import com.kenai.jaffl.annotations.StdCall;
import java.util.HashMap;
import com.kenai.jaffl.LibraryOption;
import java.util.Map;
import java.lang.reflect.InvocationHandler;

public class NativeInvocationHandler implements InvocationHandler
{
    private final InvokerMap invokers;
    private final Library library;
    private final Map<LibraryOption, Object> optionsMap;
    private final Class<?> interfaceClass;
    
    public NativeInvocationHandler(final Library library, final Class<?> interfaceClass, final Map<LibraryOption, ?> optionsMap) {
        this.library = library;
        this.interfaceClass = interfaceClass;
        this.optionsMap = new HashMap<LibraryOption, Object>(optionsMap);
        if (interfaceClass.getAnnotation(StdCall.class) != null) {
            this.optionsMap.put(LibraryOption.CallingConvention, CallingConvention.STDCALL);
        }
        this.invokers = new InvokerMap(interfaceClass.getDeclaredMethods().length);
    }
    
    public static <T> T wrapInterface(final Library library, final Class<T> interfaceClass, final Map<LibraryOption, ?> optionsMap) {
        return interfaceClass.cast(Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[] { interfaceClass }, new NativeInvocationHandler(library, interfaceClass, optionsMap)));
    }
    
    private Invoker getInvoker(final Method method) {
        final Invoker invoker = this.invokers.get(method);
        if (invoker != null) {
            return invoker;
        }
        return this.createInvoker(method);
    }
    
    private synchronized Invoker createInvoker(final Method method) {
        Invoker invoker = this.invokers.get(method);
        if (invoker != null) {
            return invoker;
        }
        invoker = this.library.getInvoker(method, this.optionsMap);
        if (method.getAnnotation(Synchronized.class) != null || this.interfaceClass.getAnnotation(Synchronized.class) != null) {
            invoker = new SynchronizedInvoker(invoker, this.library.libraryLock());
        }
        this.invokers.put(method, invoker);
        return invoker;
    }
    
    public Object invoke(final Object self, final Method method, final Object[] argArray) throws Throwable {
        return this.getInvoker(method).invoke(argArray);
    }
    
    private static final class SynchronizedInvoker implements Invoker
    {
        private final Object lock;
        private final Invoker invoker;
        
        public SynchronizedInvoker(final Invoker invoker, final Object lock) {
            this.invoker = invoker;
            this.lock = lock;
        }
        
        public Object invoke(final Object[] parameters) {
            synchronized (this.lock) {
                return this.invoker.invoke(parameters);
            }
        }
    }
    
    private static final class InvokerMap
    {
        private volatile Object[] entries;
        private static final float loadFactor = 0.5f;
        private static final Hasher hasher;
        
        public InvokerMap(final int maxEntries) {
            int capacity;
            int size;
            for (capacity = (int)(maxEntries / 0.5f) + 1, size = 1; size < capacity; size <<= 1) {}
            this.entries = new Object[size * 2];
        }
        
        public final synchronized void put(final Method method, final Invoker invoker) {
            final Object[] tmp = new Object[this.entries.length];
            System.arraycopy(this.entries, 0, tmp, 0, tmp.length);
            int start = indexFor(method, tmp.length);
        Label_0088:
            for (int loop = 0; loop < 2; ++loop) {
                for (int i = start; i < tmp.length - 1; i += 2) {
                    if (tmp[i] == null) {
                        tmp[i] = method;
                        tmp[i + 1] = invoker;
                        break Label_0088;
                    }
                }
                start = 0;
            }
            this.entries = tmp;
        }
        
        public final Invoker get(final Method method) {
            final Object[] tmp = this.entries;
            int start = indexFor(method, tmp.length);
        Label_0075:
            for (int loop = 0; loop < 2; ++loop) {
                for (int i = start; i < tmp.length - 1; i += 2) {
                    if (tmp[i] == method) {
                        return (Invoker)tmp[i + 1];
                    }
                    if (tmp[i] == null) {
                        break Label_0075;
                    }
                }
                start = 0;
            }
            return null;
        }
        
        private static final int indexFor(final Method key, final int length) {
            return InvokerMap.hasher.hash(key) & length - 1;
        }
        
        static {
            hasher = ((Platform.getPlatform().getJavaMajorVersion() >= 6) ? IdentityHasherSingleton.getInstance() : NameHasherSingleton.getInstance());
        }
        
        private static final class IdentityHasherSingleton
        {
            public static final Hasher getInstance() {
                return new IdentityHasher();
            }
            
            private static final class IdentityHasher implements Hasher
            {
                public final int hash(final Method key) {
                    final int h = System.identityHashCode(key);
                    return (h << 1) - (h << 8);
                }
            }
        }
        
        private static final class NameHasherSingleton
        {
            public static final Hasher getInstance() {
                return new NameHasher();
            }
            
            private static final class NameHasher implements Hasher
            {
                public final int hash(final Method key) {
                    int h = key.hashCode();
                    h += (h << 15 ^ 0xFFFFCD7D);
                    h ^= h >>> 10;
                    h += h << 3;
                    h ^= h >>> 6;
                    h += (h << 2) + (h << 14);
                    return h ^ h >>> 16;
                }
            }
        }
        
        private interface Hasher
        {
            int hash(final Method p0);
        }
    }
}
