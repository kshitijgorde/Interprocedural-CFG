// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.remote;

import javax.management.ObjectName;
import java.io.Serializable;

public class RemoteMBeanAttributeInvocation implements Serializable
{
    static final long serialVersionUID = 6592113867540285376L;
    public ObjectName targetObjectName;
    public String attributeName;
    
    public RemoteMBeanAttributeInvocation(final ObjectName pName, final String attribute) {
        this.targetObjectName = null;
        this.attributeName = null;
        this.targetObjectName = pName;
        this.attributeName = attribute;
    }
}
