// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms;

import javax.naming.NamingException;
import javax.naming.InitialContext;

public interface JndiProvider
{
    InitialContext getInitialContext() throws NamingException;
    
    InitialContext createInitialContext() throws NamingException;
}
