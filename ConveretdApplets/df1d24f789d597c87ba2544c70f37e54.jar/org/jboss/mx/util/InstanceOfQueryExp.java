// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import javax.management.BadAttributeValueExpException;
import javax.management.BadBinaryOpValueExpException;
import javax.management.BadStringOperationException;
import javax.management.InstanceNotFoundException;
import javax.management.InvalidApplicationException;
import javax.management.ObjectName;
import javax.management.MBeanServer;
import javax.management.QueryExp;

public class InstanceOfQueryExp implements QueryExp
{
    private static final long serialVersionUID = -5765353862268810105L;
    MBeanServer server;
    String classname;
    
    public InstanceOfQueryExp(final String classname) {
        this.classname = classname;
    }
    
    public boolean apply(final ObjectName name) throws BadStringOperationException, BadBinaryOpValueExpException, BadAttributeValueExpException, InvalidApplicationException {
        try {
            return this.server.isInstanceOf(name, this.classname);
        }
        catch (InstanceNotFoundException e) {
            throw new InvalidApplicationException(name);
        }
    }
    
    public void setMBeanServer(final MBeanServer server) {
        this.server = server;
    }
}
