// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms.msg;

import com.stonewall.cornerstone.thread.ThreadPool;
import org.xmodel.log.Log;
import java.lang.reflect.Method;
import com.stonewall.cornerstone.thread.ThreadDispatch;

public class DispatchMessage extends ThreadDispatch
{
    private static Method method;
    static final Log log;
    
    static {
        log = Dispatcher.log;
    }
    
    public DispatchMessage(final ThreadPool pool) {
        super(pool, getMethod());
    }
    
    @Override
    protected Object invoke(final Object object, final Object[] args) throws Exception {
        return DispatchMessage.method.invoke(object, args);
    }
    
    @Override
    protected void afterRun(final Object result) {
        if (result == null) {
            return;
        }
        ((Reply)result).send();
    }
    
    static Method getMethod() {
        if (DispatchMessage.method == null) {
            try {
                final Class[] classes = { JmsMessage.class };
                DispatchMessage.method = Dispatcher.class.getMethod("dispatchMessage", (Class<?>[])classes);
            }
            catch (NoSuchMethodException e) {
                DispatchMessage.log.error("invoke", e);
            }
        }
        return DispatchMessage.method;
    }
}
