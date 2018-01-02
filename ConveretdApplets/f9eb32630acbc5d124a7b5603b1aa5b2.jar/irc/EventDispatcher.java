// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;

public class EventDispatcher
{
    private static final int USER = 0;
    private static final int SECURITY = 1;
    private static final String[] _names;
    private static Hashtable _cache;
    private static DispatchThread[] _thread;
    private static boolean _warning;
    
    private static void ensureAlive(final int n) {
        if (EventDispatcher._thread[n] == null || !EventDispatcher._thread[n].isAlive()) {
            EventDispatcher._thread[n] = new DispatchThread(EventDispatcher._names[n]);
        }
    }
    
    private static boolean match(final Class[] array, final Class[] array2) {
        if (array.length != array2.length) {
            return false;
        }
        for (int i = 0; i < array.length; ++i) {
            if (array2[i] != null && !array[i].isAssignableFrom(array2[i])) {
                return false;
            }
        }
        return true;
    }
    
    public static void clearCache() {
        synchronized (EventDispatcher._cache) {
            EventDispatcher._cache.clear();
        }
    }
    
    public static void disableBadThreadWarning() {
        EventDispatcher._warning = false;
    }
    
    public static void enableBadThreadWarning() {
        EventDispatcher._warning = true;
    }
    
    public static Object dispatchEventSync(final Object o, final String s, final Object[] array) {
        try {
            return dispatchEventSyncEx(o, s, array);
        }
        catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }
    
    public static Object dispatchEventSyncEx(final Object o, final String s, final Object[] array) throws Throwable {
        ensureAlive(0);
        if (EventDispatcher._warning && !isEventThread()) {
            System.err.println("Event dispatch in wrong thread");
            System.err.println("expected thread was " + EventDispatcher._thread);
            System.err.println("current thread is " + Thread.currentThread());
            System.err.println("please submit a bug report to plouf@pjirc.com with the following information :");
            Thread.dumpStack();
        }
        try {
            final Class<?> class1 = o.getClass();
            Method[] methods;
            synchronized (EventDispatcher._cache) {
                methods = EventDispatcher._cache.get(class1);
                if (methods == null) {
                    methods = class1.getMethods();
                    EventDispatcher._cache.put(class1, methods);
                }
            }
            final Class[] array2 = new Class[array.length];
            for (int i = 0; i < array.length; ++i) {
                if (array[i] != null) {
                    array2[i] = array[i].getClass();
                }
                else {
                    array2[i] = null;
                }
            }
            for (int j = 0; j < methods.length; ++j) {
                if (methods[j].getName().equals(s) && match(methods[j].getParameterTypes(), array2)) {
                    return methods[j].invoke(o, array);
                }
            }
            throw new NoSuchMethodException(s);
        }
        catch (InvocationTargetException ex) {
            throw ex.getTargetException();
        }
        catch (Throwable t) {
            System.err.println("internal error");
            System.err.println("please submit a bug report to plouf@pjirc.com with the following information :");
            t.printStackTrace();
            return null;
        }
    }
    
    public static void dispatchEventAsync(final Object o, final String s, final Object[] array) {
        ensureAlive(0);
        EventDispatcher._thread[0].addEvent(o, s, array);
    }
    
    private static void checkStack() {
    }
    
    private static void checkDeadLock(final int n) {
        ensureAlive(n);
        if (Thread.currentThread() == EventDispatcher._thread[n]) {
            try {
                throw new RuntimeException("Deadlock protection");
            }
            catch (RuntimeException ex) {
                ex.printStackTrace();
                throw ex;
            }
        }
    }
    
    public static void dispatchEventAsyncSecurity(final Object o, final String s, final Object[] array) {
        checkStack();
        ensureAlive(1);
        EventDispatcher._thread[1].addEvent(o, s, array);
    }
    
    private static Object dispatchEventAsyncAndWaitExImp(final Object o, final String s, final Object[] array, final int n) throws InterruptedException, Throwable {
        checkDeadLock(n);
        ensureAlive(n);
        final EventItem addEvent = EventDispatcher._thread[n].addEvent(o, s, array);
        synchronized (addEvent.endLock) {
            if (addEvent.resultAvailable) {
                if (addEvent.resultException != null) {
                    throw addEvent.resultException;
                }
                return addEvent.result;
            }
            else {
                addEvent.endLock.wait();
                if (addEvent.resultException != null) {
                    throw addEvent.resultException;
                }
                return addEvent.result;
            }
        }
    }
    
    public static Object dispatchEventAsyncAndWaitEx(final Object o, final String s, final Object[] array) throws InterruptedException, Throwable {
        return dispatchEventAsyncAndWaitExImp(o, s, array, 0);
    }
    
    public static Object dispatchEventAsyncAndWaitExSecurity(final Object o, final String s, final Object[] array) throws InterruptedException, Throwable {
        checkStack();
        return dispatchEventAsyncAndWaitExImp(o, s, array, 1);
    }
    
    public static Object dispatchEventAsyncAndWait(final Object o, final String s, final Object[] array) throws InterruptedException {
        checkDeadLock(0);
        ensureAlive(0);
        final EventItem addEvent = EventDispatcher._thread[0].addEvent(o, s, array);
        synchronized (addEvent.endLock) {
            if (addEvent.resultAvailable) {
                return addEvent.result;
            }
            addEvent.endLock.wait();
            return addEvent.result;
        }
    }
    
    public static boolean isEventThread() {
        ensureAlive(0);
        return Thread.currentThread() == EventDispatcher._thread[0] || Thread.currentThread() == EventDispatcher._thread[1];
    }
    
    static {
        _names = new String[] { "User", "Security" };
        EventDispatcher._cache = new Hashtable();
        EventDispatcher._thread = new DispatchThread[2];
        EventDispatcher._warning = true;
    }
}
