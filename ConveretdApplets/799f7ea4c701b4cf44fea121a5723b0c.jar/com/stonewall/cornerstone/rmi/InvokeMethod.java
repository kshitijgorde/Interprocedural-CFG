// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.rmi;

import com.stonewall.cornerstone.thread.ThreadPool;
import org.xmodel.log.Log;
import java.lang.reflect.Method;
import com.stonewall.cornerstone.thread.ThreadDispatch;

public class InvokeMethod extends ThreadDispatch
{
    private static Method method;
    static final Log log;
    
    static {
        log = RMIComponent.log;
    }
    
    InvokeMethod(final ThreadPool pool) {
        super(pool, getMethod());
    }
    
    @Override
    protected Object invoke(final Object object, final Object[] args) throws Exception {
        return InvokeMethod.method.invoke(object, args);
    }
    
    public void invoke(final RMIComponent component, final Target target, final com.stonewall.cornerstone.rmi.Method method) {
        this.submit(component, target, method);
    }
    
    static Method getMethod() {
        if (InvokeMethod.method == null) {
            try {
                final Class[] classes = { Target.class, com.stonewall.cornerstone.rmi.Method.class };
                InvokeMethod.method = RMIComponent.class.getMethod("invokeDispatched", (Class<?>[])classes);
            }
            catch (NoSuchMethodException e) {
                InvokeMethod.log.error("invokeDispatched", e);
            }
        }
        return InvokeMethod.method;
    }
}
