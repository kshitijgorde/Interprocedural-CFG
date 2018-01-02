// 
// Decompiled by Procyon v0.5.30
// 

package org.jnp.interfaces;

import java.rmi.NoSuchObjectException;
import javax.naming.ConfigurationException;
import java.util.Arrays;
import java.net.DatagramPacket;
import java.net.SocketAddress;
import java.net.MulticastSocket;
import java.net.InetSocketAddress;
import javax.naming.InitialContext;
import javax.naming.OperationNotSupportedException;
import java.util.Iterator;
import javax.naming.Binding;
import java.util.ArrayList;
import java.util.Collection;
import javax.naming.NamingEnumeration;
import java.util.Enumeration;
import javax.naming.NotContextException;
import javax.naming.LinkRef;
import javax.naming.spi.ResolveResult;
import java.rmi.ConnectException;
import javax.naming.CannotProceedException;
import javax.naming.spi.NamingManager;
import java.rmi.RemoteException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.InvalidNameException;
import java.util.StringTokenizer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;
import javax.naming.NamingException;
import java.net.Socket;
import javax.net.SocketFactory;
import javax.naming.CommunicationException;
import java.rmi.MarshalledObject;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import javax.naming.ServiceUnavailableException;
import java.net.InetAddress;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import javax.naming.NameParser;
import javax.naming.Name;
import java.util.Hashtable;
import org.jboss.logging.Logger;
import java.io.Serializable;
import javax.naming.Context;

public class NamingContext implements Context, Serializable
{
    static final long serialVersionUID = 8906455608484282128L;
    public static final String JNP_SOCKET_FACTORY = "jnp.socketFactory";
    public static final String JNP_LOCAL_ADDRESS = "jnp.localAddress";
    public static final String JNP_LOCAL_PORT = "jnp.localPort";
    public static final String JNP_DISABLE_DISCOVERY = "jnp.disableDiscovery";
    public static final String JNP_PARTITION_NAME = "jnp.partitionName";
    public static final String JNP_DISCOVERY_GROUP = "jnp.discoveryGroup";
    public static final String JNP_DISCOVERY_PORT = "jnp.discoveryPort";
    public static final String JNP_DISCOVERY_TTL = "jnp.discoveryTTL";
    public static final String JNP_DISCOVERY_TIMEOUT = "jnp.discoveryTimeout";
    public static final String JNP_PARSED_NAME = "jnp.parsedName";
    public static final String JNP_USE_RELATIVE_NAME = "jnp.useRelativeName";
    public static final String JNP_MAX_RETRIES = "jnp.maxRetries";
    public static final String DEFAULT_DISCOVERY_GROUP_ADDRESS = "230.0.0.4";
    public static final int DEFAULT_DISCOVERY_GROUP_PORT = 1102;
    public static final int DEFAULT_DISCOVERY_TIMEOUT = 5000;
    public static int MAX_RETRIES;
    private static Logger log;
    public static Hashtable haServers;
    public static Naming localServer;
    Naming naming;
    Hashtable env;
    Name prefix;
    NameParser parser;
    static HashMap cachedServers;
    
    public static void setHANamingServerForPartition(final String partitionName, final Naming haServer) {
        NamingContext.haServers.put(partitionName, haServer);
    }
    
    public static void removeHANamingServerForPartition(final String partitionName) {
        NamingContext.haServers.remove(partitionName);
    }
    
    public static Naming getHANamingServerForPartition(final String partitionName) {
        return NamingContext.haServers.get(partitionName);
    }
    
    static void addServer(final String name, final Naming server) {
        synchronized (NamingContext.class) {
            NamingContext.cachedServers.put(name, new WeakReference<Naming>(server));
        }
    }
    
    static Naming getServer(final String host, final int port, final Hashtable serverEnv) throws NamingException {
        final String hostKey = host + ":" + port;
        final WeakReference ref = NamingContext.cachedServers.get(hostKey);
        if (ref != null) {
            final Naming server = (Naming)ref.get();
            if (server != null) {
                serverEnv.put("hostKey", hostKey);
                return server;
            }
        }
        try {
            final SocketFactory factory = loadSocketFactory(serverEnv);
            Socket s;
            try {
                InetAddress localAddr = null;
                int localPort = 0;
                final String localAddrStr = serverEnv.get("jnp.localAddress");
                final String localPortStr = serverEnv.get("jnp.localPort");
                if (localAddrStr != null) {
                    localAddr = InetAddress.getByName(localAddrStr);
                }
                if (localPortStr != null) {
                    localPort = Integer.parseInt(localPortStr);
                }
                s = factory.createSocket(host, port, localAddr, localPort);
            }
            catch (IOException e) {
                final NamingException ex = new ServiceUnavailableException("Failed to connect to server " + hostKey);
                ex.setRootCause(e);
                throw ex;
            }
            final BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
            final ObjectInputStream in = new ObjectInputStream(bis);
            final MarshalledObject stub = (MarshalledObject)in.readObject();
            final Naming server = stub.get();
            s.close();
            addServer(hostKey, server);
            serverEnv.put("hostKey", hostKey);
            return server;
        }
        catch (IOException e2) {
            final NamingException ex2 = new CommunicationException("Failed to retrieve stub from server " + hostKey);
            ex2.setRootCause(e2);
            throw ex2;
        }
        catch (Exception e3) {
            final NamingException ex2 = new CommunicationException("Failed to connect to server " + hostKey);
            ex2.setRootCause(e3);
            throw ex2;
        }
    }
    
    static SocketFactory loadSocketFactory(final Hashtable serverEnv) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        SocketFactory factory = null;
        final String socketFactoryName = serverEnv.get("jnp.socketFactory");
        if (socketFactoryName == null || socketFactoryName.equals(TimedSocketFactory.class.getName())) {
            factory = new TimedSocketFactory(serverEnv);
            return factory;
        }
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        final Class factoryClass = loader.loadClass(socketFactoryName);
        try {
            final Class[] ctorSig = { Hashtable.class };
            final Constructor ctor = factoryClass.getConstructor((Class[])ctorSig);
            final Object[] ctorArgs = { serverEnv };
            factory = ctor.newInstance(ctorArgs);
        }
        catch (NoSuchMethodException e) {
            factory = factoryClass.newInstance();
        }
        return factory;
    }
    
    static void removeServer(final Hashtable serverEnv) {
        String host = "localhost";
        int port = 1099;
        if (serverEnv.get("java.naming.provider.url") != null) {
            final String providerURL = serverEnv.get("java.naming.provider.url");
            final StringTokenizer tokenizer = new StringTokenizer(providerURL, ", ");
            while (tokenizer.hasMoreElements()) {
                String url = tokenizer.nextToken();
                try {
                    final Name urlAsName = new NamingParser().parse(url);
                    final String server = parseNameForScheme(urlAsName, null);
                    if (server != null) {
                        url = server;
                    }
                    final int colon = url.indexOf(58);
                    if (colon < 0) {
                        host = url.trim();
                    }
                    else {
                        host = url.substring(0, colon).trim();
                        try {
                            port = Integer.parseInt(url.substring(colon + 1).trim());
                        }
                        catch (Exception ex) {}
                    }
                    synchronized (NamingContext.class) {
                        NamingContext.cachedServers.remove(host + ":" + port);
                    }
                }
                catch (NamingException ex2) {}
            }
        }
        final Object hostKey = serverEnv.remove("hostKey");
        if (hostKey != null) {
            synchronized (NamingContext.class) {
                NamingContext.cachedServers.remove(hostKey);
            }
        }
    }
    
    static String parseNameForScheme(Name n, final Hashtable nameEnv) throws InvalidNameException {
        String serverInfo = null;
        if (n.size() > 0) {
            final String scheme = n.get(0);
            int schemeLength = 0;
            if (scheme.startsWith("java:")) {
                schemeLength = 5;
            }
            else if (scheme.startsWith("jnp:")) {
                schemeLength = 4;
            }
            else if (scheme.startsWith("jnps:")) {
                schemeLength = 5;
            }
            else if (scheme.startsWith("jnp-http:")) {
                schemeLength = 9;
            }
            else if (scheme.startsWith("jnp-https:")) {
                schemeLength = 10;
            }
            if (schemeLength > 0) {
                n = (Name)n.clone();
                final String suffix = scheme.substring(schemeLength);
                if (suffix.length() == 0) {
                    n.remove(0);
                    if (n.size() > 1 && n.get(0).equals("")) {
                        serverInfo = n.get(1);
                        n.remove(0);
                        n.remove(0);
                        if (n.size() == 1 && n.get(0).length() == 0) {
                            n.remove(0);
                        }
                    }
                }
                else {
                    n.remove(0);
                    n.add(0, suffix);
                }
                if (nameEnv != null) {
                    nameEnv.put("jnp.parsedName", n);
                }
            }
        }
        return serverInfo;
    }
    
    public static void setLocal(final Naming server) {
        NamingContext.localServer = server;
    }
    
    public NamingContext(final Hashtable e, final Name baseName, final Naming server) throws NamingException {
        this.parser = new NamingParser();
        if (baseName == null) {
            this.prefix = this.parser.parse("");
        }
        else {
            this.prefix = baseName;
        }
        if (e != null) {
            this.env = (Hashtable)e.clone();
        }
        else {
            this.env = new Hashtable();
        }
        this.naming = server;
    }
    
    public Naming getNaming() {
        return this.naming;
    }
    
    public void setNaming(final Naming server) {
        this.naming = server;
    }
    
    public void rebind(final String name, final Object obj) throws NamingException {
        this.rebind(this.getNameParser(name).parse(name), obj);
    }
    
    public void rebind(Name name, Object obj) throws NamingException {
        final Hashtable refEnv = this.getEnv(name);
        this.checkRef(refEnv);
        final Name parsedName = refEnv.get("jnp.parsedName");
        if (parsedName != null) {
            name = parsedName;
        }
        obj = this.getStateToBind(obj, name, refEnv);
        try {
            String className = null;
            if (obj instanceof Referenceable) {
                obj = ((Referenceable)obj).getReference();
            }
            if (!(obj instanceof Reference)) {
                if (obj != null) {
                    className = obj.getClass().getName();
                }
                obj = new MarshalledValuePair(obj);
            }
            else {
                className = ((Reference)obj).getClassName();
            }
            try {
                this.naming.rebind(this.getAbsoluteName(name), obj, className);
            }
            catch (RemoteException re) {
                if (!this.handleStaleNamingStub(re, refEnv)) {
                    throw re;
                }
                this.naming.rebind(this.getAbsoluteName(name), obj, className);
            }
        }
        catch (CannotProceedException cpe) {
            cpe.setEnvironment(refEnv);
            final Context cctx = NamingManager.getContinuationContext(cpe);
            cctx.rebind(cpe.getRemainingName(), obj);
        }
        catch (IOException e) {
            this.naming = null;
            removeServer(refEnv);
            final NamingException ex = new CommunicationException();
            ex.setRootCause(e);
            throw ex;
        }
    }
    
    public void bind(final String name, final Object obj) throws NamingException {
        this.bind(this.getNameParser(name).parse(name), obj);
    }
    
    public void bind(Name name, Object obj) throws NamingException {
        final Hashtable refEnv = this.getEnv(name);
        this.checkRef(refEnv);
        final Name parsedName = refEnv.get("jnp.parsedName");
        if (parsedName != null) {
            name = parsedName;
        }
        obj = this.getStateToBind(obj, name, refEnv);
        try {
            String className = null;
            if (obj instanceof Referenceable) {
                obj = ((Referenceable)obj).getReference();
            }
            if (!(obj instanceof Reference)) {
                if (obj != null) {
                    className = obj.getClass().getName();
                }
                obj = new MarshalledValuePair(obj);
            }
            else {
                className = ((Reference)obj).getClassName();
            }
            name = this.getAbsoluteName(name);
            try {
                this.naming.bind(name, obj, className);
            }
            catch (RemoteException re) {
                if (!this.handleStaleNamingStub(re, refEnv)) {
                    throw re;
                }
                this.naming.bind(name, obj, className);
            }
        }
        catch (CannotProceedException cpe) {
            cpe.setEnvironment(refEnv);
            final Context cctx = NamingManager.getContinuationContext(cpe);
            cctx.bind(cpe.getRemainingName(), obj);
        }
        catch (IOException e) {
            this.naming = null;
            removeServer(refEnv);
            final NamingException ex = new CommunicationException();
            ex.setRootCause(e);
            throw ex;
        }
    }
    
    public Object lookup(final String name) throws NamingException {
        return this.lookup(this.getNameParser(name).parse(name));
    }
    
    public Object lookup(Name name) throws NamingException {
        final Hashtable refEnv = this.getEnv(name);
        this.checkRef(refEnv);
        final Name parsedName = refEnv.get("jnp.parsedName");
        if (parsedName != null) {
            name = parsedName;
        }
        if (name.isEmpty()) {
            return new NamingContext(refEnv, this.prefix, this.naming);
        }
        try {
            int maxTries = 1;
            try {
                final String n = refEnv.get("jnp.maxRetries");
                if (n != null) {
                    maxTries = Integer.parseInt(n);
                }
                if (maxTries <= 0) {
                    maxTries = 1;
                }
            }
            catch (Exception e) {
                NamingContext.log.debug("Failed to get JNP_MAX_RETRIES, using 1", e);
            }
            final Name n2 = this.getAbsoluteName(name);
            Object res = null;
            final boolean trace = NamingContext.log.isTraceEnabled();
            int i = 0;
            while (i < maxTries) {
                try {
                    try {
                        res = this.naming.lookup(n2);
                    }
                    catch (RemoteException re) {
                        if (!this.handleStaleNamingStub(re, refEnv)) {
                            throw re;
                        }
                        res = this.naming.lookup(n2);
                    }
                }
                catch (ConnectException ce) {
                    final int retries = maxTries - i - 1;
                    if (trace) {
                        NamingContext.log.trace("Connect failed, retry count: " + retries, ce);
                    }
                    if (retries > 0) {
                        try {
                            Thread.sleep(1L);
                        }
                        catch (InterruptedException ex2) {}
                        ++i;
                        continue;
                    }
                    throw ce;
                }
                break;
            }
            if (res instanceof MarshalledValuePair) {
                final MarshalledValuePair mvp = (MarshalledValuePair)res;
                final Object storedObj = mvp.get();
                return this.getObjectInstanceWrapFailure(storedObj, name, refEnv);
            }
            if (res instanceof MarshalledObject) {
                final MarshalledObject mo = (MarshalledObject)res;
                return mo.get();
            }
            if (res instanceof Context) {
                final Enumeration keys = refEnv.keys();
                while (keys.hasMoreElements()) {
                    final String key = keys.nextElement();
                    ((Context)res).addToEnvironment(key, refEnv.get(key));
                }
                return res;
            }
            if (!(res instanceof ResolveResult)) {
                if (res instanceof LinkRef) {
                    res = this.resolveLink(res, refEnv);
                }
                else if (res instanceof Reference) {
                    res = this.getObjectInstanceWrapFailure(res, name, refEnv);
                    if (res instanceof LinkRef) {
                        res = this.resolveLink(res, refEnv);
                    }
                }
                return res;
            }
            final ResolveResult rr = (ResolveResult)res;
            final Object resolveRes = rr.getResolvedObj();
            Object context;
            Object instanceID;
            if (resolveRes instanceof LinkRef) {
                context = this.resolveLink(resolveRes, null);
                instanceID = ((LinkRef)resolveRes).getLinkName();
            }
            else {
                context = (instanceID = this.getObjectInstanceWrapFailure(resolveRes, name, refEnv));
            }
            if (!(context instanceof Context)) {
                throw new NotContextException(instanceID + " is not a Context");
            }
            final Context ncontext = (Context)context;
            return ncontext.lookup(rr.getRemainingName());
        }
        catch (CannotProceedException cpe) {
            cpe.setEnvironment(refEnv);
            final Context cctx = NamingManager.getContinuationContext(cpe);
            return cctx.lookup(cpe.getRemainingName());
        }
        catch (IOException e2) {
            this.naming = null;
            removeServer(refEnv);
            final NamingException ex = new CommunicationException();
            ex.setRootCause(e2);
            throw ex;
        }
        catch (ClassNotFoundException e3) {
            final NamingException ex = new CommunicationException();
            ex.setRootCause(e3);
            throw ex;
        }
    }
    
    public void unbind(final String name) throws NamingException {
        this.unbind(this.getNameParser(name).parse(name));
    }
    
    public void unbind(Name name) throws NamingException {
        final Hashtable refEnv = this.getEnv(name);
        this.checkRef(refEnv);
        final Name parsedName = refEnv.get("jnp.parsedName");
        while (true) {
            if (parsedName != null) {
                name = parsedName;
                try {
                    try {
                        this.naming.unbind(this.getAbsoluteName(name));
                    }
                    catch (RemoteException re) {
                        if (!this.handleStaleNamingStub(re, refEnv)) {
                            throw re;
                        }
                        this.naming.unbind(this.getAbsoluteName(name));
                    }
                }
                catch (CannotProceedException cpe) {
                    cpe.setEnvironment(refEnv);
                    final Context cctx = NamingManager.getContinuationContext(cpe);
                    cctx.unbind(cpe.getRemainingName());
                }
                catch (IOException e) {
                    this.naming = null;
                    removeServer(refEnv);
                    final NamingException ex = new CommunicationException();
                    ex.setRootCause(e);
                    throw ex;
                }
                return;
            }
            continue;
        }
    }
    
    public void rename(final String oldname, final String newname) throws NamingException {
        this.rename(this.getNameParser(oldname).parse(oldname), this.getNameParser(newname).parse(newname));
    }
    
    public void rename(final Name oldName, final Name newName) throws NamingException {
        this.bind(newName, this.lookup(oldName));
        this.unbind(oldName);
    }
    
    public NamingEnumeration list(final String name) throws NamingException {
        return this.list(this.getNameParser(name).parse(name));
    }
    
    public NamingEnumeration list(Name name) throws NamingException {
        final Hashtable refEnv = this.getEnv(name);
        this.checkRef(refEnv);
        final Name parsedName = refEnv.get("jnp.parsedName");
        if (parsedName != null) {
            name = parsedName;
        }
        try {
            Collection c = null;
            try {
                c = this.naming.list(this.getAbsoluteName(name));
            }
            catch (RemoteException re) {
                if (!this.handleStaleNamingStub(re, refEnv)) {
                    throw re;
                }
                c = this.naming.list(this.getAbsoluteName(name));
            }
            return new NamingEnumerationImpl(c);
        }
        catch (CannotProceedException cpe) {
            cpe.setEnvironment(refEnv);
            final Context cctx = NamingManager.getContinuationContext(cpe);
            return cctx.list(cpe.getRemainingName());
        }
        catch (IOException e) {
            this.naming = null;
            removeServer(refEnv);
            final NamingException ex = new CommunicationException();
            ex.setRootCause(e);
            throw ex;
        }
    }
    
    public NamingEnumeration listBindings(final String name) throws NamingException {
        return this.listBindings(this.getNameParser(name).parse(name));
    }
    
    public NamingEnumeration listBindings(Name name) throws NamingException {
        final Hashtable refEnv = this.getEnv(name);
        this.checkRef(refEnv);
        final Name parsedName = refEnv.get("jnp.parsedName");
        if (parsedName != null) {
            name = parsedName;
        }
        try {
            Collection bindings = null;
            try {
                bindings = this.naming.listBindings(this.getAbsoluteName(name));
            }
            catch (RemoteException re) {
                if (!this.handleStaleNamingStub(re, refEnv)) {
                    throw re;
                }
                bindings = this.naming.listBindings(this.getAbsoluteName(name));
            }
            final Collection realBindings = new ArrayList(bindings.size());
            for (final Binding binding : bindings) {
                Object obj = binding.getObject();
                Label_0221: {
                    if (obj instanceof MarshalledValuePair) {
                        try {
                            obj = ((MarshalledValuePair)obj).get();
                            break Label_0221;
                        }
                        catch (ClassNotFoundException e) {
                            final NamingException ex = new CommunicationException();
                            ex.setRootCause(e);
                            throw ex;
                        }
                    }
                    if (obj instanceof MarshalledObject) {
                        try {
                            obj = ((MarshalledObject)obj).get();
                        }
                        catch (ClassNotFoundException e) {
                            final NamingException ex = new CommunicationException();
                            ex.setRootCause(e);
                            throw ex;
                        }
                    }
                }
                realBindings.add(new Binding(binding.getName(), binding.getClassName(), obj));
            }
            return new NamingEnumerationImpl(realBindings);
        }
        catch (CannotProceedException cpe) {
            cpe.setEnvironment(refEnv);
            final Context cctx = NamingManager.getContinuationContext(cpe);
            return cctx.listBindings(cpe.getRemainingName());
        }
        catch (IOException e2) {
            this.naming = null;
            removeServer(refEnv);
            final NamingException ex2 = new CommunicationException();
            ex2.setRootCause(e2);
            throw ex2;
        }
    }
    
    public String composeName(final String name, final String prefix) throws NamingException {
        final Name result = this.composeName(this.parser.parse(name), this.parser.parse(prefix));
        return result.toString();
    }
    
    public Name composeName(final Name name, final Name prefix) throws NamingException {
        final Name result = (Name)prefix.clone();
        result.addAll(name);
        return result;
    }
    
    public NameParser getNameParser(final String name) throws NamingException {
        return this.parser;
    }
    
    public NameParser getNameParser(final Name name) throws NamingException {
        return this.getNameParser(name.toString());
    }
    
    public Context createSubcontext(final String name) throws NamingException {
        return this.createSubcontext(this.getNameParser(name).parse(name));
    }
    
    public Context createSubcontext(Name name) throws NamingException {
        if (name.size() == 0) {
            throw new InvalidNameException("Cannot pass an empty name to createSubcontext");
        }
        final Hashtable refEnv = this.getEnv(name);
        this.checkRef(refEnv);
        final Name parsedName = refEnv.get("jnp.parsedName");
        if (parsedName != null) {
            name = parsedName;
        }
        try {
            name = this.getAbsoluteName(name);
            try {
                return this.naming.createSubcontext(name);
            }
            catch (RemoteException re) {
                if (this.handleStaleNamingStub(re, refEnv)) {
                    return this.naming.createSubcontext(name);
                }
                throw re;
            }
        }
        catch (CannotProceedException cpe) {
            cpe.setEnvironment(refEnv);
            final Context cctx = NamingManager.getContinuationContext(cpe);
            return cctx.createSubcontext(cpe.getRemainingName());
        }
        catch (IOException e) {
            this.naming = null;
            removeServer(refEnv);
            final NamingException ex = new CommunicationException();
            ex.setRootCause(e);
            throw ex;
        }
    }
    
    public Object addToEnvironment(final String propName, final Object propVal) throws NamingException {
        final Object old = this.env.get(propName);
        this.env.put(propName, propVal);
        return old;
    }
    
    public Object removeFromEnvironment(final String propName) throws NamingException {
        return this.env.remove(propName);
    }
    
    public Hashtable getEnvironment() throws NamingException {
        return this.env;
    }
    
    public void close() throws NamingException {
        this.env = null;
        this.naming = null;
    }
    
    public String getNameInNamespace() throws NamingException {
        return this.prefix.toString();
    }
    
    public void destroySubcontext(final String name) throws NamingException {
        throw new OperationNotSupportedException();
    }
    
    public void destroySubcontext(final Name name) throws NamingException {
        throw new OperationNotSupportedException();
    }
    
    public Object lookupLink(final String name) throws NamingException {
        return this.lookupLink(this.getNameParser(name).parse(name));
    }
    
    public Object lookupLink(final Name name) throws NamingException {
        if (name.isEmpty()) {
            return this.lookup(name);
        }
        Object link = null;
        try {
            final Name n = this.getAbsoluteName(name);
            try {
                link = this.naming.lookup(n);
            }
            catch (RemoteException re) {
                if (!this.handleStaleNamingStub(re, this.env)) {
                    throw re;
                }
                link = this.naming.lookup(n);
            }
            if (!(link instanceof LinkRef) && link instanceof Reference) {
                link = this.getObjectInstance(link, name, null);
            }
        }
        catch (IOException e) {
            this.naming = null;
            removeServer(this.env);
            final NamingException ex = new CommunicationException();
            ex.setRootCause(e);
            throw ex;
        }
        catch (Exception e2) {
            final NamingException ex = new NamingException("Could not lookup link");
            ex.setRemainingName(name);
            ex.setRootCause(e2);
            throw ex;
        }
        return link;
    }
    
    protected Object resolveLink(final Object res, final Hashtable refEnv) throws NamingException {
        Object linkResult = null;
        try {
            final LinkRef link = (LinkRef)res;
            final String ref = link.getLinkName();
            if (ref.startsWith("./")) {
                linkResult = this.lookup(ref.substring(2));
            }
            else if (refEnv != null) {
                linkResult = new InitialContext(refEnv).lookup(ref);
            }
            else {
                linkResult = new InitialContext().lookup(ref);
            }
        }
        catch (Exception e) {
            final NamingException ex = new NamingException("Could not dereference object");
            ex.setRootCause(e);
            throw ex;
        }
        return linkResult;
    }
    
    private boolean useAbsoluteName(final Hashtable env) {
        if (env == null) {
            return true;
        }
        final String useRelativeName = env.get("jnp.useRelativeName");
        return Boolean.valueOf(useRelativeName) == Boolean.FALSE;
    }
    
    private Object getStateToBind(final Object obj, Name name, final Hashtable env) throws NamingException {
        if (this.useAbsoluteName(env)) {
            name = this.getAbsoluteName(name);
        }
        return NamingManager.getStateToBind(obj, name, this, env);
    }
    
    private Object getObjectInstance(final Object obj, Name name, final Hashtable env) throws Exception {
        if (this.useAbsoluteName(env)) {
            name = this.getAbsoluteName(name);
        }
        return NamingManager.getObjectInstance(obj, name, this, env);
    }
    
    private Object getObjectInstanceWrapFailure(final Object obj, final Name name, final Hashtable env) throws NamingException {
        try {
            return this.getObjectInstance(obj, name, env);
        }
        catch (NamingException e) {
            throw e;
        }
        catch (Exception e2) {
            final NamingException ex = new NamingException("Could not dereference object");
            ex.setRootCause(e2);
            throw ex;
        }
    }
    
    private Naming discoverServer(final Hashtable serverEnv) throws NamingException {
        final boolean trace = NamingContext.log.isTraceEnabled();
        final String disableDiscovery = serverEnv.get("jnp.disableDiscovery");
        if (Boolean.valueOf(disableDiscovery) == Boolean.TRUE) {
            if (trace) {
                NamingContext.log.trace("Skipping discovery due to disable flag");
            }
            return null;
        }
        final String partitionName = serverEnv.get("jnp.partitionName");
        Naming server = null;
        if (partitionName != null) {
            server = getHANamingServerForPartition(partitionName);
            if (server != null) {
                return server;
            }
        }
        MulticastSocket s = null;
        InetAddress iaGroup = null;
        try {
            String group = "230.0.0.4";
            int port = 1102;
            int timeout = 5000;
            int ttl = 16;
            final String discoveryTTL = serverEnv.get("jnp.discoveryTTL");
            if (discoveryTTL != null) {
                ttl = Integer.parseInt(discoveryTTL);
            }
            final String discoveryGroup = serverEnv.get("jnp.discoveryGroup");
            if (discoveryGroup != null) {
                group = discoveryGroup;
            }
            String discoveryTimeout = serverEnv.get("jnp.discoveryTimeout");
            if (discoveryTimeout == null) {
                discoveryTimeout = serverEnv.get("DISCOVERY_TIMEOUT");
            }
            if (discoveryTimeout != null && !discoveryTimeout.equals("")) {
                timeout = Integer.parseInt(discoveryTimeout);
            }
            String discoveryGroupPort = serverEnv.get("jnp.discoveryPort");
            if (discoveryGroupPort == null) {
                discoveryGroupPort = serverEnv.get("DISCOVERY_GROUP");
            }
            if (discoveryGroupPort != null && !discoveryGroupPort.equals("")) {
                final int colon = discoveryGroupPort.indexOf(58);
                if (colon < 0) {
                    try {
                        port = Integer.parseInt(discoveryGroupPort);
                    }
                    catch (Exception ex) {
                        NamingContext.log.warn("Failed to parse port: " + discoveryGroupPort, ex);
                    }
                }
                else {
                    group = discoveryGroupPort.substring(0, colon);
                    final String portStr = discoveryGroupPort.substring(colon + 1);
                    try {
                        port = Integer.parseInt(portStr);
                    }
                    catch (Exception ex2) {
                        NamingContext.log.warn("Failed to parse port: " + portStr, ex2);
                    }
                }
            }
            iaGroup = InetAddress.getByName(group);
            final String localAddrStr = serverEnv.get("jnp.localAddress");
            final String localPortStr = serverEnv.get("jnp.localPort");
            int localPort = 0;
            if (localPortStr != null) {
                localPort = Integer.parseInt(localPortStr);
            }
            if (localAddrStr != null) {
                final InetSocketAddress localAddr = new InetSocketAddress(localAddrStr, localPort);
                s = new MulticastSocket(localAddr);
            }
            else {
                s = new MulticastSocket(localPort);
            }
            s.setSoTimeout(timeout);
            s.setTimeToLive(ttl);
            if (NamingContext.log.isTraceEnabled()) {
                NamingContext.log.trace("TTL on multicast discovery socket is " + ttl);
            }
            s.joinGroup(iaGroup);
            if (trace) {
                NamingContext.log.trace("MulticastSocket: " + s);
            }
            final StringBuffer data = new StringBuffer("GET_ADDRESS");
            if (partitionName != null) {
                data.append(":" + partitionName);
            }
            byte[] buf = data.toString().getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, iaGroup, port);
            if (trace) {
                NamingContext.log.trace("Sending discovery packet(" + (Object)data + ") to: " + iaGroup + ":" + port);
            }
            s.send(packet);
            buf = new byte[50];
            packet = new DatagramPacket(buf, buf.length);
            s.receive(packet);
            String myServer = new String(packet.getData()).trim();
            if (trace) {
                NamingContext.log.trace("Received answer packet: " + myServer);
            }
            while (myServer != null && myServer.startsWith("GET_ADDRESS")) {
                Arrays.fill(buf, (byte)0);
                packet.setLength(buf.length);
                s.receive(packet);
                final byte[] reply = packet.getData();
                myServer = new String(reply).trim();
                if (trace) {
                    NamingContext.log.trace("Received answer packet: " + myServer);
                }
            }
            final int colon2 = myServer.indexOf(58);
            if (colon2 >= 0) {
                final String serverHost = myServer.substring(0, colon2);
                final int serverPort = Integer.valueOf(myServer.substring(colon2 + 1));
                server = getServer(serverHost, serverPort, serverEnv);
            }
            return server;
        }
        catch (IOException e) {
            if (trace) {
                NamingContext.log.trace("Discovery failed", e);
            }
            final NamingException ex3 = new CommunicationException(e.getMessage());
            ex3.setRootCause(e);
            throw ex3;
        }
        finally {
            try {
                if (s != null) {
                    s.leaveGroup(iaGroup);
                }
            }
            catch (Exception ex4) {}
            try {
                if (s != null) {
                    s.close();
                }
            }
            catch (Exception ex5) {}
        }
    }
    
    private void checkRef(final Hashtable refEnv) throws NamingException {
        if (this.naming == null) {
            String host = "localhost";
            int port = 1099;
            Exception serverEx = null;
            final String urls = refEnv.get("java.naming.provider.url");
            if (urls != null && urls.length() > 0) {
                final StringTokenizer tokenizer = new StringTokenizer(urls, ",");
                while (this.naming == null && tokenizer.hasMoreElements()) {
                    String url = tokenizer.nextToken();
                    final Name urlAsName = this.getNameParser("").parse(url);
                    final String server = parseNameForScheme(urlAsName, null);
                    if (server != null) {
                        url = server;
                    }
                    final int colon = url.indexOf(58);
                    if (colon < 0) {
                        host = url;
                    }
                    else {
                        host = url.substring(0, colon).trim();
                        try {
                            port = Integer.parseInt(url.substring(colon + 1).trim());
                        }
                        catch (Exception ex) {}
                    }
                    try {
                        this.naming = getServer(host, port, refEnv);
                    }
                    catch (Exception e) {
                        serverEx = e;
                        NamingContext.log.debug("Failed to connect to " + host + ":" + port, e);
                    }
                }
                Exception discoveryFailure = null;
                if (this.naming == null) {
                    try {
                        this.naming = this.discoverServer(refEnv);
                    }
                    catch (Exception e2) {
                        discoveryFailure = e2;
                        if (serverEx == null) {
                            serverEx = e2;
                        }
                    }
                    if (this.naming == null) {
                        final StringBuffer buffer = new StringBuffer(50);
                        buffer.append("Could not obtain connection to any of these urls: ").append(urls);
                        if (discoveryFailure != null) {
                            buffer.append(" and discovery failed with error: ").append(discoveryFailure);
                        }
                        final CommunicationException ce = new CommunicationException(buffer.toString());
                        ce.setRootCause(serverEx);
                        throw ce;
                    }
                }
            }
            else {
                final String jnpPartitionName = refEnv.get("jnp.partitionName");
                if (jnpPartitionName != null) {
                    this.naming = this.discoverServer(refEnv);
                    if (this.naming == null) {
                        throw new ConfigurationException("No valid context could be build for jnp.partitionName=" + jnpPartitionName);
                    }
                }
                else {
                    this.naming = NamingContext.localServer;
                    if (this.naming == null) {
                        this.naming = this.discoverServer(refEnv);
                        if (this.naming == null) {
                            throw new ConfigurationException("No valid Context.PROVIDER_URL was found");
                        }
                    }
                }
            }
        }
    }
    
    private Name getAbsoluteName(final Name n) throws NamingException {
        if (n.isEmpty()) {
            return this.composeName(n, this.prefix);
        }
        if (n.get(0).toString().equals("")) {
            return n.getSuffix(1);
        }
        return this.composeName(n, this.prefix);
    }
    
    private Hashtable getEnv(final Name n) throws InvalidNameException {
        Hashtable nameEnv = this.env;
        this.env.remove("jnp.parsedName");
        final String serverInfo = parseNameForScheme(n, nameEnv);
        if (serverInfo != null) {
            nameEnv = (Hashtable)this.env.clone();
            nameEnv.put("java.naming.provider.url", serverInfo);
        }
        return nameEnv;
    }
    
    private boolean handleStaleNamingStub(final RemoteException e, final Hashtable refEnv) {
        if (!(e instanceof NoSuchObjectException)) {
            if (!(e.getCause() instanceof NoSuchObjectException)) {
                return false;
            }
        }
        try {
            if (NamingContext.log.isTraceEnabled()) {
                NamingContext.log.trace("Call failed with recoverable RMI failure, flushing server cache and reaquiring Naming ref", e);
            }
            this.naming = null;
            removeServer(refEnv);
            this.checkRef(refEnv);
            return true;
        }
        catch (Exception e2) {
            NamingContext.log.error("Caught exception flushing server cache and re-establish naming after exception " + e.getLocalizedMessage(), e2);
        }
        return false;
    }
    
    static {
        NamingContext.MAX_RETRIES = 1;
        NamingContext.log = Logger.getLogger(NamingContext.class);
        NamingContext.haServers = new Hashtable();
        NamingContext.cachedServers = new HashMap();
    }
}
