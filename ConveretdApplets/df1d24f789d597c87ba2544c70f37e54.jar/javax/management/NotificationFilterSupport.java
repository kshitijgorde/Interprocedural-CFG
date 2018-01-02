// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import java.util.Enumeration;
import java.util.Vector;
import java.io.Serializable;

public class NotificationFilterSupport implements NotificationFilter, Serializable
{
    private static final long serialVersionUID = 6579080007561786969L;
    private Vector enabledTypes;
    
    public NotificationFilterSupport() {
        this.enabledTypes = new Vector();
    }
    
    public synchronized void disableAllTypes() {
        this.enabledTypes = new Vector();
    }
    
    public synchronized void disableType(final String type) {
        this.enabledTypes.removeElement(type);
    }
    
    public synchronized void enableType(final String type) throws IllegalArgumentException {
        if (type == null) {
            throw new IllegalArgumentException("null notification type");
        }
        if (!this.enabledTypes.contains(type)) {
            this.enabledTypes.addElement(type);
        }
    }
    
    public synchronized Vector getEnabledTypes() {
        return (Vector)this.enabledTypes.clone();
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(100);
        sb.append(this.getClass().getName()).append(':');
        sb.append(" enabledTypes=").append(this.getEnabledTypes());
        return sb.toString();
    }
    
    public synchronized boolean isNotificationEnabled(final Notification notification) {
        if (notification == null) {
            throw new IllegalArgumentException("null notification");
        }
        final String notificationType = notification.getType();
        final Enumeration e = this.enabledTypes.elements();
        while (e.hasMoreElements()) {
            final String type = e.nextElement();
            if (notificationType.startsWith(type)) {
                return true;
            }
        }
        return false;
    }
}
