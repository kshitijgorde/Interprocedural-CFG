// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collection;
import java.util.Vector;
import java.util.HashSet;
import java.util.Set;
import java.io.ObjectStreamField;
import java.io.Serializable;

public class AttributeChangeNotificationFilter implements NotificationFilter, Serializable
{
    private static final long serialVersionUID = -6347317584796410029L;
    private static final ObjectStreamField[] serialPersistentFields;
    private Set attributes;
    
    public AttributeChangeNotificationFilter() {
        this.attributes = new HashSet();
    }
    
    public synchronized boolean isNotificationEnabled(final Notification notification) {
        if (notification != null && notification instanceof AttributeChangeNotification) {
            final AttributeChangeNotification notif = (AttributeChangeNotification)notification;
            if (this.attributes.contains(notif.getAttributeName())) {
                return true;
            }
        }
        return false;
    }
    
    public synchronized void enableAttribute(final String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Null attribute name");
        }
        this.attributes.add(name);
    }
    
    public synchronized void disableAttribute(final String name) {
        this.attributes.remove(name);
    }
    
    public synchronized void disableAllAttributes() {
        this.attributes.clear();
    }
    
    public synchronized Vector getEnabledAttributes() {
        return new Vector(this.attributes);
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer(100);
        buffer.append(this.getClass().getName()).append(":");
        buffer.append(" enabledAttributes=").append(this.getEnabledAttributes());
        return buffer.toString();
    }
    
    private void readObject(final ObjectInputStream ois) throws IOException, ClassNotFoundException {
        final ObjectInputStream.GetField getField = ois.readFields();
        final Vector enabled = (Vector)getField.get("enabledAttributes", null);
        this.attributes = new HashSet(enabled);
    }
    
    private void writeObject(final ObjectOutputStream oos) throws IOException {
        final ObjectOutputStream.PutField putField = oos.putFields();
        putField.put("enabledAttributes", new Vector(this.attributes));
        oos.writeFields();
    }
    
    static {
        serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("enabledAttributes", Vector.class) };
    }
}
