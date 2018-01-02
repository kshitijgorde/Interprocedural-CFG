// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import javax.management.JMRuntimeException;

public class RuntimeProxyException extends JMRuntimeException
{
    private static final long serialVersionUID = -1166909485463779459L;
    
    public RuntimeProxyException() {
    }
    
    public RuntimeProxyException(final String msg) {
        super(msg);
    }
}
