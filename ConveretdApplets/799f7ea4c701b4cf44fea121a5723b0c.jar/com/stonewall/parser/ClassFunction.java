// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import java.util.Arrays;
import java.lang.reflect.Modifier;
import java.util.Set;
import java.security.AccessController;
import java.security.PrivilegedAction;
import org.xmodel.log.Log;
import java.lang.reflect.Method;

public class ClassFunction implements Function
{
    static Class<?>[] signature;
    private Dictionary<Method> methods;
    protected Dictionary<ClassFunction> classes;
    protected static Log log;
    
    static {
        ClassFunction.signature = (Class<?>[])new Class[] { Context.class, String[].class };
        ClassFunction.log = Log.getLog(ClassFunction.class);
    }
    
    public ClassFunction() {
        this.methods = new Dictionary<Method>();
        this.classes = new Dictionary<ClassFunction>();
        this.addMethods();
    }
    
    @Override
    public String execute(final Context ctx, final String[] args) throws Exception {
        final Call call = this.getCall(args[0]);
        AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                call.m.setAccessible(true);
                return null;
            }
        });
        return (String)call.m.invoke(call.fn, ctx, args);
    }
    
    public Set<String> methods() {
        return this.methods.keySet();
    }
    
    private void addMethods() {
        Method[] declaredMethods;
        for (int length = (declaredMethods = this.getClass().getDeclaredMethods()).length, i = 0; i < length; ++i) {
            final Method m = declaredMethods[i];
            final Class[] params = m.getParameterTypes();
            final int modifier = m.getModifiers();
            if (Modifier.isPublic(modifier) && Arrays.equals(ClassFunction.signature, params)) {
                this.methods.put(m.getName(), m);
            }
        }
    }
    
    private Call getCall(final String path) throws UnsupportedOperationException {
        final String[] parts = path.split("\\.");
        final Call call = new Call();
        call.fn = this;
        final int last = parts.length - 1;
        for (int i = 1; i < last; ++i) {
            call.fn = call.fn.classes.get(parts[i]);
            if (call.fn == null) {
                throw new UnsupportedOperationException(path);
            }
        }
        call.m = call.fn.methods.get(parts[last]);
        if (call.m == null) {
            throw new UnsupportedOperationException(path);
        }
        return call;
    }
    
    class Call
    {
        ClassFunction fn;
        Method m;
    }
}
