// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.remote;

import javax.management.ObjectName;
import java.io.Serializable;

public class RemoteMBeanInvocation implements Serializable
{
    static final long serialVersionUID = -2283426739714617862L;
    public ObjectName targetObjectName;
    public String actionName;
    public Object[] params;
    public String[] signature;
    
    public RemoteMBeanInvocation(final ObjectName pName, final String pActionName, final Object[] pParams, final String[] pSignature) {
        this.targetObjectName = null;
        this.actionName = null;
        this.params = null;
        this.signature = null;
        this.targetObjectName = pName;
        this.actionName = pActionName;
        this.params = pParams;
        this.signature = pSignature;
    }
}
