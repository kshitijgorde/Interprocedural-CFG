// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.invocation;

import java.io.ObjectStreamException;
import java.io.Serializable;

public final class InvocationKey implements Serializable
{
    private static final long serialVersionUID = -5117370636698417671L;
    private static final int MAX_KEY_ID = 18;
    private static final InvocationKey[] values;
    public static final InvocationKey TRANSACTION;
    public static final InvocationKey PRINCIPAL;
    public static final InvocationKey CREDENTIAL;
    public static final InvocationKey SUBJECT;
    public static final InvocationKey OBJECT_NAME;
    public static final InvocationKey TYPE;
    public static final InvocationKey CACHE_ID;
    public static final InvocationKey METHOD;
    public static final InvocationKey ARGUMENTS;
    public static final InvocationKey INVOCATION_CONTEXT;
    public static final InvocationKey ENTERPRISE_CONTEXT;
    public static final InvocationKey INVOKER_PROXY_BINDING;
    public static final InvocationKey INVOKER;
    public static final InvocationKey JNDI_NAME;
    public static final InvocationKey EJB_METADATA;
    public static final InvocationKey EJB_HOME;
    public static final InvocationKey SOAP_MESSAGE_CONTEXT;
    public static final InvocationKey SOAP_MESSAGE;
    public static final InvocationKey JACC_CONTEXT_ID;
    private final transient String name;
    private final int ordinal;
    
    private InvocationKey(final String name, final int ordinal) {
        this.name = name;
        this.ordinal = ordinal;
        InvocationKey.values[ordinal] = this;
    }
    
    public String toString() {
        return this.name;
    }
    
    Object readResolve() throws ObjectStreamException {
        return InvocationKey.values[this.ordinal];
    }
    
    static {
        values = new InvocationKey[19];
        TRANSACTION = new InvocationKey("TRANSACTION", 0);
        PRINCIPAL = new InvocationKey("PRINCIPAL", 1);
        CREDENTIAL = new InvocationKey("CREDENTIAL", 2);
        SUBJECT = new InvocationKey("SUBJECT", 14);
        OBJECT_NAME = new InvocationKey("CONTAINER", 3);
        TYPE = new InvocationKey("TYPE", 4);
        CACHE_ID = new InvocationKey("CACHE_ID", 5);
        METHOD = new InvocationKey("METHOD", 6);
        ARGUMENTS = new InvocationKey("ARGUMENTS", 7);
        INVOCATION_CONTEXT = new InvocationKey("INVOCATION_CONTEXT", 8);
        ENTERPRISE_CONTEXT = new InvocationKey("ENTERPRISE_CONTEXT", 9);
        INVOKER_PROXY_BINDING = new InvocationKey("INVOKER_PROXY_BINDING", 10);
        INVOKER = new InvocationKey("INVOKER", 11);
        JNDI_NAME = new InvocationKey("JNDI_NAME", 12);
        EJB_METADATA = new InvocationKey("EJB_METADATA", 13);
        EJB_HOME = new InvocationKey("EJB_HOME", 14);
        SOAP_MESSAGE_CONTEXT = new InvocationKey("SOAP_MESSAGE_CONTEXT", 15);
        SOAP_MESSAGE = new InvocationKey("SOAP_MESSAGE", 16);
        JACC_CONTEXT_ID = new InvocationKey("JACC_CONTEXT_ID", 17);
    }
}
