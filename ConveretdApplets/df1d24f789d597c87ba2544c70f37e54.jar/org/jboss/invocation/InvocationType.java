// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.invocation;

import java.io.ObjectStreamException;
import java.io.Serializable;

public final class InvocationType implements Serializable
{
    private static final long serialVersionUID = 6460196085190851775L;
    private static final String[] INTERFACE_NAMES;
    private static final int MAX_TYPE_ID = 4;
    private static final InvocationType[] values;
    public static final InvocationType REMOTE;
    public static final InvocationType LOCAL;
    public static final InvocationType HOME;
    public static final InvocationType LOCALHOME;
    public static final InvocationType SERVICE_ENDPOINT;
    private final transient String name;
    private final int ordinal;
    
    private InvocationType(final String name, final int ordinal) {
        this.name = name;
        this.ordinal = ordinal;
        InvocationType.values[ordinal] = this;
    }
    
    public String toString() {
        return this.name;
    }
    
    public String toInterfaceString() {
        return InvocationType.INTERFACE_NAMES[this.ordinal];
    }
    
    Object readResolve() throws ObjectStreamException {
        return InvocationType.values[this.ordinal];
    }
    
    static {
        INTERFACE_NAMES = new String[] { "Remote", "Local", "Home", "LocalHome", "ServiceEndpoint" };
        values = new InvocationType[5];
        REMOTE = new InvocationType("REMOTE", 0);
        LOCAL = new InvocationType("LOCAL", 1);
        HOME = new InvocationType("HOME", 2);
        LOCALHOME = new InvocationType("LOCALHOME", 3);
        SERVICE_ENDPOINT = new InvocationType("SERVICE_ENDPOINT", 4);
    }
}
