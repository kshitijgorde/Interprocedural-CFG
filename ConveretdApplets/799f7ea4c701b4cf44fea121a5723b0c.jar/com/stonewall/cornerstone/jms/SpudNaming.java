// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms;

import java.util.Hashtable;
import java.util.Properties;
import javax.naming.NamingException;
import javax.naming.InitialContext;

public class SpudNaming implements JndiProvider
{
    protected static InitialContext context;
    
    @Override
    public InitialContext getInitialContext() throws NamingException {
        if (SpudNaming.context == null) {
            this.createInitialContext();
        }
        return SpudNaming.context;
    }
    
    @Override
    public InitialContext createInitialContext() throws NamingException {
        if (SpudNaming.context != null) {
            SpudNaming.context.close();
            SpudNaming.context = null;
        }
        final Properties props = new Properties();
        props.setProperty("java.naming.factory.initial", "com.stonewall.naming.SpudContextFactory");
        props.setProperty("com.stonewall.naming.unique.context", "false");
        return SpudNaming.context = new InitialContext(props);
    }
}
