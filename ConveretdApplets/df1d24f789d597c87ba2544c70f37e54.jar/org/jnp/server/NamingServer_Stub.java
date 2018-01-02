// 
// Decompiled by Procyon v0.5.30
// 

package org.jnp.server;

import java.util.Collection;
import javax.naming.Context;
import java.rmi.UnexpectedException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.Remote;
import javax.naming.Name;
import java.rmi.server.RemoteRef;
import java.lang.reflect.Method;
import org.jnp.interfaces.Naming;
import java.rmi.server.RemoteStub;

public final class NamingServer_Stub extends RemoteStub implements Naming
{
    private static final long serialVersionUID = 2L;
    private static Method $method_bind_0;
    private static Method $method_createSubcontext_1;
    private static Method $method_list_2;
    private static Method $method_listBindings_3;
    private static Method $method_lookup_4;
    private static Method $method_rebind_5;
    private static Method $method_unbind_6;
    static /* synthetic */ Class class$org$jnp$interfaces$Naming;
    static /* synthetic */ Class class$javax$naming$Name;
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$java$lang$String;
    
    static {
        try {
            NamingServer_Stub.$method_bind_0 = ((NamingServer_Stub.class$org$jnp$interfaces$Naming != null) ? NamingServer_Stub.class$org$jnp$interfaces$Naming : (NamingServer_Stub.class$org$jnp$interfaces$Naming = class$("org.jnp.interfaces.Naming"))).getMethod("bind", (NamingServer_Stub.class$javax$naming$Name != null) ? NamingServer_Stub.class$javax$naming$Name : (NamingServer_Stub.class$javax$naming$Name = class$("javax.naming.Name")), (NamingServer_Stub.class$java$lang$Object != null) ? NamingServer_Stub.class$java$lang$Object : (NamingServer_Stub.class$java$lang$Object = class$("java.lang.Object")), (NamingServer_Stub.class$java$lang$String != null) ? NamingServer_Stub.class$java$lang$String : (NamingServer_Stub.class$java$lang$String = class$("java.lang.String")));
            NamingServer_Stub.$method_createSubcontext_1 = ((NamingServer_Stub.class$org$jnp$interfaces$Naming != null) ? NamingServer_Stub.class$org$jnp$interfaces$Naming : (NamingServer_Stub.class$org$jnp$interfaces$Naming = class$("org.jnp.interfaces.Naming"))).getMethod("createSubcontext", (NamingServer_Stub.class$javax$naming$Name != null) ? NamingServer_Stub.class$javax$naming$Name : (NamingServer_Stub.class$javax$naming$Name = class$("javax.naming.Name")));
            NamingServer_Stub.$method_list_2 = ((NamingServer_Stub.class$org$jnp$interfaces$Naming != null) ? NamingServer_Stub.class$org$jnp$interfaces$Naming : (NamingServer_Stub.class$org$jnp$interfaces$Naming = class$("org.jnp.interfaces.Naming"))).getMethod("list", (NamingServer_Stub.class$javax$naming$Name != null) ? NamingServer_Stub.class$javax$naming$Name : (NamingServer_Stub.class$javax$naming$Name = class$("javax.naming.Name")));
            NamingServer_Stub.$method_listBindings_3 = ((NamingServer_Stub.class$org$jnp$interfaces$Naming != null) ? NamingServer_Stub.class$org$jnp$interfaces$Naming : (NamingServer_Stub.class$org$jnp$interfaces$Naming = class$("org.jnp.interfaces.Naming"))).getMethod("listBindings", (NamingServer_Stub.class$javax$naming$Name != null) ? NamingServer_Stub.class$javax$naming$Name : (NamingServer_Stub.class$javax$naming$Name = class$("javax.naming.Name")));
            NamingServer_Stub.$method_lookup_4 = ((NamingServer_Stub.class$org$jnp$interfaces$Naming != null) ? NamingServer_Stub.class$org$jnp$interfaces$Naming : (NamingServer_Stub.class$org$jnp$interfaces$Naming = class$("org.jnp.interfaces.Naming"))).getMethod("lookup", (NamingServer_Stub.class$javax$naming$Name != null) ? NamingServer_Stub.class$javax$naming$Name : (NamingServer_Stub.class$javax$naming$Name = class$("javax.naming.Name")));
            NamingServer_Stub.$method_rebind_5 = ((NamingServer_Stub.class$org$jnp$interfaces$Naming != null) ? NamingServer_Stub.class$org$jnp$interfaces$Naming : (NamingServer_Stub.class$org$jnp$interfaces$Naming = class$("org.jnp.interfaces.Naming"))).getMethod("rebind", (NamingServer_Stub.class$javax$naming$Name != null) ? NamingServer_Stub.class$javax$naming$Name : (NamingServer_Stub.class$javax$naming$Name = class$("javax.naming.Name")), (NamingServer_Stub.class$java$lang$Object != null) ? NamingServer_Stub.class$java$lang$Object : (NamingServer_Stub.class$java$lang$Object = class$("java.lang.Object")), (NamingServer_Stub.class$java$lang$String != null) ? NamingServer_Stub.class$java$lang$String : (NamingServer_Stub.class$java$lang$String = class$("java.lang.String")));
            NamingServer_Stub.$method_unbind_6 = ((NamingServer_Stub.class$org$jnp$interfaces$Naming != null) ? NamingServer_Stub.class$org$jnp$interfaces$Naming : (NamingServer_Stub.class$org$jnp$interfaces$Naming = class$("org.jnp.interfaces.Naming"))).getMethod("unbind", (NamingServer_Stub.class$javax$naming$Name != null) ? NamingServer_Stub.class$javax$naming$Name : (NamingServer_Stub.class$javax$naming$Name = class$("javax.naming.Name")));
        }
        catch (NoSuchMethodException ex) {
            throw new NoSuchMethodError("stub class initialization failed");
        }
    }
    
    public NamingServer_Stub(final RemoteRef remoteRef) {
        super(remoteRef);
    }
    
    public void bind(final Name name, final Object o, final String s) throws RemoteException, NamingException {
        try {
            super.ref.invoke(this, NamingServer_Stub.$method_bind_0, new Object[] { name, o, s }, 8662829417543390098L);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (NamingException ex3) {
            throw ex3;
        }
        catch (Exception ex4) {
            throw new UnexpectedException("undeclared checked exception", ex4);
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public Context createSubcontext(final Name name) throws RemoteException, NamingException {
        try {
            return (Context)super.ref.invoke(this, NamingServer_Stub.$method_createSubcontext_1, new Object[] { name }, 8857620196654006348L);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (NamingException ex3) {
            throw ex3;
        }
        catch (Exception ex4) {
            throw new UnexpectedException("undeclared checked exception", ex4);
        }
    }
    
    public Collection list(final Name name) throws RemoteException, NamingException {
        try {
            return (Collection)super.ref.invoke(this, NamingServer_Stub.$method_list_2, new Object[] { name }, -3150771248161076586L);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (NamingException ex3) {
            throw ex3;
        }
        catch (Exception ex4) {
            throw new UnexpectedException("undeclared checked exception", ex4);
        }
    }
    
    public Collection listBindings(final Name name) throws RemoteException, NamingException {
        try {
            return (Collection)super.ref.invoke(this, NamingServer_Stub.$method_listBindings_3, new Object[] { name }, -5496843691089219130L);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (NamingException ex3) {
            throw ex3;
        }
        catch (Exception ex4) {
            throw new UnexpectedException("undeclared checked exception", ex4);
        }
    }
    
    public Object lookup(final Name name) throws RemoteException, NamingException {
        try {
            return super.ref.invoke(this, NamingServer_Stub.$method_lookup_4, new Object[] { name }, -790108502850547550L);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (NamingException ex3) {
            throw ex3;
        }
        catch (Exception ex4) {
            throw new UnexpectedException("undeclared checked exception", ex4);
        }
    }
    
    public void rebind(final Name name, final Object o, final String s) throws RemoteException, NamingException {
        try {
            super.ref.invoke(this, NamingServer_Stub.$method_rebind_5, new Object[] { name, o, s }, 8519187059293746991L);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (NamingException ex3) {
            throw ex3;
        }
        catch (Exception ex4) {
            throw new UnexpectedException("undeclared checked exception", ex4);
        }
    }
    
    public void unbind(final Name name) throws RemoteException, NamingException {
        try {
            super.ref.invoke(this, NamingServer_Stub.$method_unbind_6, new Object[] { name }, 8176650517119097487L);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (RemoteException ex2) {
            throw ex2;
        }
        catch (NamingException ex3) {
            throw ex3;
        }
        catch (Exception ex4) {
            throw new UnexpectedException("undeclared checked exception", ex4);
        }
    }
}
