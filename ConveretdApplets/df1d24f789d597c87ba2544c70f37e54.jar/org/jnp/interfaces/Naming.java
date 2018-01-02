// 
// Decompiled by Procyon v0.5.30
// 

package org.jnp.interfaces;

import javax.naming.Context;
import java.util.Collection;
import java.rmi.RemoteException;
import javax.naming.NamingException;
import javax.naming.Name;
import java.rmi.Remote;

public interface Naming extends Remote
{
    void bind(final Name p0, final Object p1, final String p2) throws NamingException, RemoteException;
    
    void rebind(final Name p0, final Object p1, final String p2) throws NamingException, RemoteException;
    
    void unbind(final Name p0) throws NamingException, RemoteException;
    
    Object lookup(final Name p0) throws NamingException, RemoteException;
    
    Collection list(final Name p0) throws NamingException, RemoteException;
    
    Collection listBindings(final Name p0) throws NamingException, RemoteException;
    
    Context createSubcontext(final Name p0) throws NamingException, RemoteException;
}
