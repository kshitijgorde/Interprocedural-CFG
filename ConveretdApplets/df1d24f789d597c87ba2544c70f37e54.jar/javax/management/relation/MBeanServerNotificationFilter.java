// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.relation;

import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.List;
import org.jboss.mx.util.Serialization;
import java.io.ObjectInputStream;
import javax.management.MBeanServerNotification;
import javax.management.Notification;
import java.util.Collection;
import java.util.Vector;
import javax.management.ObjectName;
import java.io.ObjectStreamField;
import java.util.HashSet;
import javax.management.NotificationFilterSupport;

public class MBeanServerNotificationFilter extends NotificationFilterSupport
{
    private HashSet enabled;
    private HashSet disabled;
    private static final long serialVersionUID;
    private static final ObjectStreamField[] serialPersistentFields;
    
    public MBeanServerNotificationFilter() {
        this.enabled = new HashSet();
        this.disabled = null;
    }
    
    public synchronized void disableAllObjectNames() {
        this.enabled = new HashSet();
        this.disabled = null;
    }
    
    public synchronized void disableObjectName(final ObjectName objectName) throws IllegalArgumentException {
        if (objectName == null) {
            throw new IllegalArgumentException("null object name");
        }
        if (this.enabled != null) {
            this.enabled.remove(objectName);
        }
        if (this.disabled != null && !this.disabled.contains(objectName)) {
            this.disabled.add(objectName);
        }
    }
    
    public synchronized void enableAllObjectNames() {
        this.enabled = null;
        this.disabled = new HashSet();
    }
    
    public synchronized void enableObjectName(final ObjectName objectName) throws IllegalArgumentException {
        if (objectName == null) {
            throw new IllegalArgumentException("null object name");
        }
        if (this.disabled != null) {
            this.disabled.remove(objectName);
        }
        if (this.enabled != null && !this.enabled.contains(objectName)) {
            this.enabled.add(objectName);
        }
    }
    
    public synchronized Vector getDisabledObjectNames() {
        if (this.disabled == null) {
            return null;
        }
        return new Vector(this.disabled);
    }
    
    public synchronized Vector getEnabledObjectNames() {
        if (this.enabled == null) {
            return null;
        }
        return new Vector(this.enabled);
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer(100);
        buffer.append(this.getClass().getName()).append(":");
        buffer.append(" enabledTypes=").append(this.getEnabledTypes());
        buffer.append(" enabledObjectNames=").append(this.getEnabledObjectNames());
        buffer.append(" disabledObjectNames=").append(this.getDisabledObjectNames());
        return buffer.toString();
    }
    
    public synchronized boolean isNotificationEnabled(final Notification notification) throws IllegalArgumentException {
        if (notification == null) {
            throw new IllegalArgumentException("null notification");
        }
        if (!super.isNotificationEnabled(notification)) {
            return false;
        }
        final MBeanServerNotification mbsNotification = (MBeanServerNotification)notification;
        final ObjectName objectName = mbsNotification.getMBeanName();
        if (this.enabled != null) {
            return this.enabled.contains(objectName);
        }
        return !this.disabled.contains(objectName);
    }
    
    private void readObject(final ObjectInputStream ois) throws IOException, ClassNotFoundException {
        final ObjectInputStream.GetField getField = ois.readFields();
        List deselectedNames = null;
        List selectedNames = null;
        switch (Serialization.version) {
            case 10: {
                deselectedNames = (List)getField.get("myDeselectObjNameList", null);
                selectedNames = (List)getField.get("mySelectObjNameList", null);
                break;
            }
            default: {
                deselectedNames = (List)getField.get("deselectedNames", null);
                selectedNames = (List)getField.get("selectedNames", null);
                break;
            }
        }
        if (deselectedNames == null && selectedNames == null) {
            throw new StreamCorruptedException("Nothing enabled or disabled?");
        }
        if (deselectedNames == null) {
            this.disabled = null;
        }
        else {
            this.disabled = new HashSet(deselectedNames);
        }
        if (selectedNames == null) {
            this.enabled = null;
        }
        else {
            this.enabled = new HashSet(selectedNames);
        }
    }
    
    private void writeObject(final ObjectOutputStream oos) throws IOException {
        final ObjectOutputStream.PutField putField = oos.putFields();
        switch (Serialization.version) {
            case 10: {
                putField.put("myDeselectObjNameList", this.getDisabledObjectNames());
                putField.put("mySelectObjNameList", this.getEnabledObjectNames());
                break;
            }
            default: {
                putField.put("deselectedNames", this.getDisabledObjectNames());
                putField.put("selectedNames", this.getEnabledObjectNames());
                break;
            }
        }
        oos.writeFields();
    }
    
    static {
        switch (Serialization.version) {
            case 10: {
                serialVersionUID = 6001782699077323605L;
                serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("myDeselectObjNameList", Vector.class), new ObjectStreamField("mySelectObjNameList", Vector.class) };
                break;
            }
            default: {
                serialVersionUID = 2605900539589789736L;
                serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("deselectedNames", List.class), new ObjectStreamField("selectedNames", List.class) };
                break;
            }
        }
    }
}
