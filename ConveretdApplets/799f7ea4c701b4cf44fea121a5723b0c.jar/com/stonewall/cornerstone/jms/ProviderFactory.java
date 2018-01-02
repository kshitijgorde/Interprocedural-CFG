// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms;

public class ProviderFactory
{
    protected static ActiveMQ jms;
    protected static JndiProvider jndi;
    
    static {
        ProviderFactory.jms = new ActiveMQ();
        ProviderFactory.jndi = new SpudNaming();
    }
    
    public static JmsProvider getJmsProvider() {
        return ProviderFactory.jms;
    }
    
    public static JndiProvider getJndiProvider() {
        return ProviderFactory.jndi;
    }
}
