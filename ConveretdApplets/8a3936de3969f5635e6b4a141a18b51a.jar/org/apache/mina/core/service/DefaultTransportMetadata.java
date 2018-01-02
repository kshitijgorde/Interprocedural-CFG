// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.service;

import java.util.Set;
import java.util.Collections;
import org.apache.mina.util.IdentityHashSet;
import org.apache.mina.core.session.IoSessionConfig;
import java.net.SocketAddress;

public final class DefaultTransportMetadata implements TransportMetadata
{
    private final String providerName;
    private final String name;
    private final boolean connectionless;
    private final boolean fragmentation;
    private final Class<? extends SocketAddress> addressType;
    private final Class<? extends IoSessionConfig> sessionConfigType;
    
    public DefaultTransportMetadata(String lowerCase, String lowerCase2, final boolean b, final boolean b2, final Class<? extends SocketAddress> addressType, final Class<? extends IoSessionConfig> sessionConfigType, final Class<?>... array) {
        if (lowerCase == null) {
            throw new IllegalArgumentException("providerName");
        }
        if (lowerCase2 == null) {
            throw new IllegalArgumentException("name");
        }
        if ((lowerCase = lowerCase.trim().toLowerCase()).length() == 0) {
            throw new IllegalArgumentException("providerName is empty.");
        }
        if ((lowerCase2 = lowerCase2.trim().toLowerCase()).length() == 0) {
            throw new IllegalArgumentException("name is empty.");
        }
        if (addressType == null) {
            throw new IllegalArgumentException("addressType");
        }
        if (array == null) {
            throw new IllegalArgumentException("envelopeTypes");
        }
        if (array.length == 0) {
            throw new IllegalArgumentException("envelopeTypes is empty.");
        }
        if (sessionConfigType == null) {
            throw new IllegalArgumentException("sessionConfigType");
        }
        this.providerName = lowerCase;
        this.name = lowerCase2;
        this.connectionless = false;
        this.fragmentation = true;
        this.addressType = addressType;
        this.sessionConfigType = sessionConfigType;
        final IdentityHashSet<Class<?>> set = new IdentityHashSet<Class<?>>();
        for (int length = array.length, i = 0; i < length; ++i) {
            set.add(array[i]);
        }
        Collections.unmodifiableSet((Set<?>)set);
    }
    
    @Override
    public final Class<? extends SocketAddress> getAddressType() {
        return this.addressType;
    }
    
    @Override
    public final Class<? extends IoSessionConfig> getSessionConfigType() {
        return this.sessionConfigType;
    }
    
    @Override
    public final String getProviderName() {
        return this.providerName;
    }
    
    @Override
    public final String getName() {
        return this.name;
    }
    
    @Override
    public final boolean isConnectionless() {
        return this.connectionless;
    }
    
    @Override
    public final boolean hasFragmentation() {
        return this.fragmentation;
    }
    
    @Override
    public final String toString() {
        return this.name;
    }
}
