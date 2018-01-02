// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.jmdns;

import java.util.logging.Logger;
import java.util.EventObject;

public class ServiceEvent extends EventObject
{
    private static Logger logger;
    private String type;
    private String name;
    private ServiceInfo info;
    
    public ServiceEvent(final JmDNS source, final String type, final String name, final ServiceInfo info) {
        super(source);
        this.type = type;
        this.name = name;
        this.info = info;
    }
    
    public JmDNS getDNS() {
        return (JmDNS)this.getSource();
    }
    
    public String getType() {
        return this.type;
    }
    
    public String getName() {
        return this.name;
    }
    
    public ServiceInfo getInfo() {
        return this.info;
    }
    
    @Override
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("<" + this.getClass().getName() + "> ");
        buf.append(super.toString());
        buf.append(" name ");
        buf.append(this.getName());
        buf.append(" type ");
        buf.append(this.getType());
        buf.append(" info ");
        buf.append(this.getInfo());
        return buf.toString();
    }
    
    static {
        ServiceEvent.logger = Logger.getLogger(ServiceEvent.class.toString());
    }
}
