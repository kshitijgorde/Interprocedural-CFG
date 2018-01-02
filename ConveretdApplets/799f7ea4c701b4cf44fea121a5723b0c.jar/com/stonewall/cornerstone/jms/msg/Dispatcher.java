// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms.msg;

import org.xmodel.IModelObjectFactory;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import org.xmodel.IModelObject;
import javax.jms.Message;
import java.util.HashMap;
import org.xmodel.log.Log;
import java.lang.reflect.Method;
import com.stonewall.cornerstone.thread.ThreadPool;
import java.util.Map;
import javax.jms.MessageListener;

public class Dispatcher implements MessageListener
{
    private Map<RegistrySelector, Object> registry;
    private ThreadPool threadPool;
    private Method invalidMethod;
    private MessageBuilder builder;
    static final Log log;
    
    static {
        log = Log.getLog(Dispatcher.class);
    }
    
    public Dispatcher() {
        this(0);
    }
    
    public Dispatcher(final String name) {
        this(0, name);
    }
    
    public Dispatcher(final int threadCount) {
        this(threadCount, "dispatcher");
    }
    
    public Dispatcher(final int threadCount, final String name) {
        this(new ThreadPool(name, threadCount));
    }
    
    public Dispatcher(final ThreadPool pool) {
        this.registry = new HashMap<RegistrySelector, Object>();
        this.threadPool = pool;
        this.builder = new MessageBuilder();
        try {
            final Class[] classes = { Request.class };
            this.invalidMethod = Dispatcher.class.getMethod("processInvalidRequest", (Class<?>[])classes);
        }
        catch (Exception e) {
            Dispatcher.log.error(pool.getName(), e);
        }
    }
    
    @Override
    public void onMessage(final Message message) {
        final DispatchMessage job = new DispatchMessage(this.threadPool);
        final JmsMessage jmsMessage = new JmsMessage(message);
        job.submit(this, jmsMessage);
    }
    
    public Object dispatchMessage(final JmsMessage message) {
        try {
            RegistrySelector selector = null;
            final IModelObject content = this.builder.buildModel(message);
            for (final RegistrySelector s : this.registry.keySet()) {
                if (s.select(message, content)) {
                    selector = s;
                    break;
                }
            }
            if (selector != null) {
                Object[] args = { message, content };
                final com.stonewall.cornerstone.jms.msg.Message msg = selector.constructor.newInstance(args);
                Object target = this.registry.get(selector);
                args = new Object[] { msg };
                Method method = selector.method;
                if (message.getReplyTo() != null && !msg.isValid()) {
                    method = this.invalidMethod;
                    target = this;
                }
                return method.invoke(target, args);
            }
        }
        catch (Exception e) {
            Dispatcher.log.error(message, e);
        }
        return null;
    }
    
    public void register(final RegistrySelector key, final Object handler) throws Exception {
        final Method method = this.locateMethod(handler.getClass(), key.methodName);
        if (method != null) {
            final Class[] params = method.getParameterTypes();
            Class[] array;
            for (int length = (array = params).length, i = 0; i < length; ++i) {
                final Class param = array[i];
                final Class[] classes = { JmsMessage.class, IModelObject.class };
                final Constructor con = param.getConstructor((Class[])classes);
                if (con != null) {
                    key.constructor = con;
                    key.messageClass = param;
                    key.method = method;
                }
            }
            this.registry.put(key, handler);
            return;
        }
        Dispatcher.log.debug("Cannot locate method:" + key.methodName + "for class:" + handler.getClass());
        throw new NoSuchMethodException("cannot locate method " + key.methodName + " for class:" + handler.getClass());
    }
    
    public Object unregister(final RegistrySelector key) {
        return this.registry.remove(key);
    }
    
    private Method locateMethod(final Class clazz, final String methodName) throws Exception {
        final Method[] methods = clazz.getDeclaredMethods();
        Method[] array;
        for (int length = (array = methods).length, i = 0; i < length; ++i) {
            final Method method = array[i];
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        final Class superClass = clazz.getSuperclass();
        if (superClass == null) {
            return null;
        }
        final Method method2 = this.locateMethod(superClass, methodName);
        if (method2 != null) {
            return method2;
        }
        return null;
    }
    
    public Reply processInvalidRequest(final Request request) {
        final Reply reply = request.createReply();
        reply.setStatus(Reply.Status.failed);
        return reply;
    }
    
    public void setFactory(final IModelObjectFactory factory) {
        this.builder.setFactory(factory);
    }
    
    @Override
    public String toString() {
        return "Dispatcher:\n" + this.threadPool;
    }
}
