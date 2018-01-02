// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.net.sockets;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.Remote;
import java.util.HashMap;

public class RMIMultiSocketServer
{
    private static HashMap handlermap;
    private static HashMap stubmap;
    
    public static Remote exportObject(final Remote obj, final int port, final RMIClientSocketFactory csf, final RMIServerSocketFactory ssf, final Class[] interfaces, final int numSockets) throws RemoteException {
        final Remote[] stubs = new Remote[numSockets];
        final Method[] methods = obj.getClass().getMethods();
        final HashMap invokerMap = new HashMap();
        for (int i = 0; i < methods.length; ++i) {
            final Long methodkey = new Long(MethodHash.calculateHash(methods[i]));
            invokerMap.put(methodkey, methods[i]);
        }
        final RMIMultiSocketHandler[] handlers = new RMIMultiSocketHandler[numSockets];
        for (int j = 0; j < numSockets; ++j) {
            final int theport = (port == 0) ? 0 : (port + j);
            handlers[j] = new RMIMultiSocketHandler(obj, invokerMap);
            stubs[j] = UnicastRemoteObject.exportObject(handlers[j], theport, csf, ssf);
        }
        final Remote remote = (Remote)Proxy.newProxyInstance(obj.getClass().getClassLoader(), interfaces, new RMIMultiSocketClient(stubs));
        RMIMultiSocketServer.stubmap.put(remote, stubs);
        RMIMultiSocketServer.handlermap.put(remote, handlers);
        return remote;
    }
    
    public static Remote exportObject(final Remote obj, final int port, final RMIClientSocketFactory csf, final RMIServerSocketFactory ssf, final int numSockets) throws RemoteException {
        return exportObject(obj, port, csf, ssf, obj.getClass().getInterfaces(), numSockets);
    }
    
    public static boolean unexportObject(final Remote obj, final boolean force) throws NoSuchObjectException {
        RMIMultiSocketServer.handlermap.remove(obj);
        final Remote[] stubs = RMIMultiSocketServer.stubmap.remove(obj);
        for (int i = 0; i < stubs.length; ++i) {
            UnicastRemoteObject.unexportObject(stubs[i], force);
        }
        return true;
    }
    
    static {
        RMIMultiSocketServer.handlermap = new HashMap();
        RMIMultiSocketServer.stubmap = new HashMap();
    }
}
