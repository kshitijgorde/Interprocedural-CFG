// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import java.lang.reflect.Proxy;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.lang.reflect.InvocationHandler;

public class MBeanServerInvocationHandler implements InvocationHandler
{
    private MBeanServerConnection connection;
    private ObjectName name;
    private HashMap mappings;
    private static final String[] EMPTY_SIGNATURE;
    private static final String[] EQUALS_SIGNATURE;
    private static final Class[] LISTENER;
    private static final Class[] TRIPLET;
    private static final Method EQUALS;
    private static final Method HASH_CODE;
    private static final Method TO_STRING;
    private static final Method ADD_NOTIFICATION_LISTENER;
    private static final Method GET_NOTIFICATION_INFO;
    private static final Method REMOVE_NOTIFICATION_LISTENER;
    private static final Method REMOVE_NOTIFICATION_LISTENER_TRIPLET;
    
    public MBeanServerInvocationHandler(final MBeanServerConnection connection, final ObjectName name) {
        if (connection == null) {
            throw new IllegalArgumentException("Null connection");
        }
        if (name == null) {
            throw new IllegalArgumentException("Null name");
        }
        if (name.isPattern()) {
            throw new IllegalArgumentException("Name is a pattern");
        }
        this.connection = connection;
        this.name = name;
    }
    
    public static Object newProxyInstance(final MBeanServerConnection connection, final ObjectName name, final Class interfaceClass, final boolean broadcaster) {
        final MBeanServerInvocationHandler handler = new MBeanServerInvocationHandler(connection, name);
        if (interfaceClass == null) {
            throw new IllegalArgumentException("Null interface");
        }
        Class[] interfaces;
        if (broadcaster) {
            interfaces = new Class[] { interfaceClass, NotificationEmitter.class };
        }
        else {
            interfaces = new Class[] { interfaceClass };
        }
        return Proxy.newProxyInstance(interfaceClass.getClassLoader(), interfaces, handler);
    }
    
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        return this.getAction(proxy, method).perform(args);
    }
    
    private Action getAction(final Object proxy, final Method method) throws Throwable {
        if (this.mappings == null) {
            this.mappings = this.getMappings(proxy);
        }
        final Action result = this.mappings.get(method);
        if (result == null) {
            throw new IllegalArgumentException("Unknown method " + method);
        }
        return result;
    }
    
    private HashMap getMappings(final Object proxy) throws Throwable {
        final HashMap result = new HashMap();
        final Class[] interfaces = proxy.getClass().getInterfaces();
        for (int i = 0; i < interfaces.length; ++i) {
            if (interfaces[i].equals(NotificationEmitter.class)) {
                result.put(MBeanServerInvocationHandler.ADD_NOTIFICATION_LISTENER, new AddNotificationListenerAction());
                result.put(MBeanServerInvocationHandler.GET_NOTIFICATION_INFO, new GetNotificationInfoAction());
                result.put(MBeanServerInvocationHandler.REMOVE_NOTIFICATION_LISTENER, new RemoveNotificationListenerAction());
                result.put(MBeanServerInvocationHandler.REMOVE_NOTIFICATION_LISTENER_TRIPLET, new RemoveNotificationListenerTripletAction());
            }
            else {
                final Method[] methods = interfaces[i].getMethods();
                for (int m = 0; m < methods.length; ++m) {
                    final String methodName = methods[m].getName();
                    final Class returnType = methods[m].getReturnType();
                    final Class[] parameterTypes = methods[m].getParameterTypes();
                    if (methodName.startsWith("get") && methodName.length() > 3 && !Void.TYPE.equals(returnType) && parameterTypes.length == 0) {
                        result.put(methods[m], new GetAction(methodName.substring(3)));
                    }
                    else if (methodName.startsWith("is") && methodName.length() > 2 && (Boolean.TYPE.equals(returnType) || Boolean.class.equals(returnType)) && parameterTypes.length == 0) {
                        result.put(methods[m], new GetAction(methodName.substring(2)));
                    }
                    else if (methodName.startsWith("set") && methodName.length() > 3 && Void.TYPE.equals(returnType) && parameterTypes.length == 1) {
                        result.put(methods[m], new SetAction(methodName.substring(3)));
                    }
                    else {
                        result.put(methods[m], new InvokeAction(methodName, getSignature(parameterTypes)));
                    }
                }
            }
        }
        result.put(MBeanServerInvocationHandler.EQUALS, new InvokeAction(MBeanServerInvocationHandler.EQUALS.getName(), MBeanServerInvocationHandler.EQUALS_SIGNATURE));
        result.put(MBeanServerInvocationHandler.HASH_CODE, new InvokeAction(MBeanServerInvocationHandler.HASH_CODE.getName(), MBeanServerInvocationHandler.EMPTY_SIGNATURE));
        result.put(MBeanServerInvocationHandler.TO_STRING, new InvokeAction(MBeanServerInvocationHandler.TO_STRING.getName(), MBeanServerInvocationHandler.EMPTY_SIGNATURE));
        return result;
    }
    
    private static String[] getSignature(final Class[] parameterTypes) {
        final String[] signature = new String[parameterTypes.length];
        for (int p = 0; p < parameterTypes.length; ++p) {
            signature[p] = parameterTypes[p].getName();
        }
        return signature;
    }
    
    static {
        EMPTY_SIGNATURE = new String[0];
        EQUALS_SIGNATURE = new String[] { Object.class.getName() };
        LISTENER = new Class[] { NotificationListener.class };
        TRIPLET = new Class[] { NotificationListener.class, NotificationFilter.class, Object.class };
        try {
            ADD_NOTIFICATION_LISTENER = NotificationBroadcaster.class.getDeclaredMethod("addNotificationListener", (Class<?>[])MBeanServerInvocationHandler.TRIPLET);
            GET_NOTIFICATION_INFO = NotificationBroadcaster.class.getDeclaredMethod("getNotificationInfo", (Class<?>[])new Class[0]);
            REMOVE_NOTIFICATION_LISTENER = NotificationBroadcaster.class.getDeclaredMethod("removeNotificationListener", (Class<?>[])MBeanServerInvocationHandler.LISTENER);
            REMOVE_NOTIFICATION_LISTENER_TRIPLET = NotificationEmitter.class.getDeclaredMethod("removeNotificationListener", (Class<?>[])MBeanServerInvocationHandler.TRIPLET);
            EQUALS = Object.class.getDeclaredMethod("equals", Object.class);
            HASH_CODE = Object.class.getDeclaredMethod("hashCode", (Class<?>[])new Class[0]);
            TO_STRING = Object.class.getDeclaredMethod("toString", (Class<?>[])new Class[0]);
        }
        catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }
    
    private class GetAction implements Action
    {
        private String attribute;
        
        public GetAction(final String attribute) {
            this.attribute = attribute;
        }
        
        public Object perform(final Object[] args) throws Throwable {
            try {
                return MBeanServerInvocationHandler.this.connection.getAttribute(MBeanServerInvocationHandler.this.name, this.attribute);
            }
            catch (MBeanException exception) {
                throw exception.getTargetException();
            }
        }
    }
    
    private class SetAction implements Action
    {
        private String attribute;
        
        public SetAction(final String attribute) {
            this.attribute = attribute;
        }
        
        public Object perform(final Object[] args) throws Throwable {
            try {
                MBeanServerInvocationHandler.this.connection.setAttribute(MBeanServerInvocationHandler.this.name, new Attribute(this.attribute, args[0]));
                return null;
            }
            catch (MBeanException exception) {
                throw exception.getTargetException();
            }
        }
    }
    
    private class InvokeAction implements Action
    {
        private String operation;
        private String[] signature;
        
        public InvokeAction(final String operation, final String[] signature) {
            this.operation = operation;
            this.signature = signature;
        }
        
        public Object perform(final Object[] args) throws Throwable {
            try {
                return MBeanServerInvocationHandler.this.connection.invoke(MBeanServerInvocationHandler.this.name, this.operation, args, this.signature);
            }
            catch (MBeanException exception) {
                throw exception.getTargetException();
            }
        }
    }
    
    private class AddNotificationListenerAction implements Action
    {
        public Object perform(final Object[] args) throws Throwable {
            MBeanServerInvocationHandler.this.connection.addNotificationListener(MBeanServerInvocationHandler.this.name, (NotificationListener)args[0], (NotificationFilter)args[1], args[2]);
            return null;
        }
    }
    
    private class GetNotificationInfoAction implements Action
    {
        public Object perform(final Object[] args) throws Throwable {
            return MBeanServerInvocationHandler.this.connection.getMBeanInfo(MBeanServerInvocationHandler.this.name).getNotifications();
        }
    }
    
    private class RemoveNotificationListenerAction implements Action
    {
        public Object perform(final Object[] args) throws Throwable {
            MBeanServerInvocationHandler.this.connection.removeNotificationListener(MBeanServerInvocationHandler.this.name, (NotificationListener)args[0]);
            return null;
        }
    }
    
    private class RemoveNotificationListenerTripletAction implements Action
    {
        public Object perform(final Object[] args) throws Throwable {
            MBeanServerInvocationHandler.this.connection.removeNotificationListener(MBeanServerInvocationHandler.this.name, (NotificationListener)args[0], (NotificationFilter)args[1], args[2]);
            return null;
        }
    }
    
    private interface Action
    {
        Object perform(final Object[] p0) throws Throwable;
    }
}
