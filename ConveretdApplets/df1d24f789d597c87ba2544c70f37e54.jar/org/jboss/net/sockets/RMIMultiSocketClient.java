// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.net.sockets;

import java.lang.reflect.Method;
import java.util.Random;
import java.rmi.Remote;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;

public class RMIMultiSocketClient implements InvocationHandler, Serializable
{
    static final long serialVersionUID = -945837789475428529L;
    protected Remote[] stubs;
    protected Random random;
    
    public RMIMultiSocketClient(final Remote[] stubs) {
        this.stubs = stubs;
        this.random = new Random();
    }
    
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        if (method.getName().equals("hashCode")) {
            return new Integer(this.stubs[0].hashCode());
        }
        if (method.getName().equals("equals")) {
            return new Boolean(this.stubs[0].equals(args[0]));
        }
        final int i = this.random.nextInt(this.stubs.length);
        final long hash = MethodHash.calculateHash(method);
        final RMIMultiSocket target = (RMIMultiSocket)this.stubs[i];
        return target.invoke(hash, args);
    }
}
