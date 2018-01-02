// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.manager.interfaces.impl;

import javax.management.ObjectName;
import org.jboss.console.manager.interfaces.ManageableResource;

public class MBeanResource implements ManageableResource
{
    String className;
    ObjectName oj;
    transient Object mbean;
    
    public MBeanResource() {
        this.className = null;
        this.oj = null;
        this.mbean = null;
    }
    
    public MBeanResource(final ObjectName oj, final String clazz) {
        this.className = null;
        this.oj = null;
        this.mbean = null;
        this.oj = oj;
        this.className = clazz;
    }
    
    public MBeanResource(final ObjectName oj, final String clazz, final Object proxy) {
        this.className = null;
        this.oj = null;
        this.mbean = null;
        this.oj = oj;
        this.className = clazz;
        this.mbean = proxy;
    }
    
    public String getClassName() {
        return this.className;
    }
    
    public ObjectName getObjectName() {
        return this.oj;
    }
    
    public Object getMBeanProxy() {
        return this.mbean;
    }
    
    public String getId() {
        return this.oj.toString();
    }
    
    public boolean equals(final Object other) {
        return other instanceof MBeanResource && this.oj.equals(((MBeanResource)other).oj);
    }
}
