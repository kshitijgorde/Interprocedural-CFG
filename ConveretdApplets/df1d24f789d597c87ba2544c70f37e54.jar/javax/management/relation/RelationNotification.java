// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.relation;

import java.io.ObjectOutputStream;
import java.io.IOException;
import org.jboss.mx.util.Serialization;
import java.io.ObjectInputStream;
import java.util.Collection;
import java.util.ArrayList;
import java.io.ObjectStreamField;
import javax.management.ObjectName;
import java.util.List;
import javax.management.Notification;

public class RelationNotification extends Notification
{
    public static final String RELATION_BASIC_CREATION = "jmx.relation.creation.basic";
    public static final String RELATION_BASIC_REMOVAL = "jmx.relation.removal.basic";
    public static final String RELATION_BASIC_UPDATE = "jmx.relation.update.basic";
    public static final String RELATION_MBEAN_CREATION = "jmx.relation.creation.mbean";
    public static final String RELATION_MBEAN_REMOVAL = "jmx.relation.removal.mbean";
    public static final String RELATION_MBEAN_UPDATE = "jmx.relation.update.mbean";
    private static int CREATION_REMOVAL;
    private static int UPDATE;
    private List unregisterMBeanList;
    private List newRoleValue;
    private ObjectName relationObjName;
    private List oldRoleValue;
    private String relationId;
    private String relationTypeName;
    private String roleName;
    private static final long serialVersionUID;
    private static final ObjectStreamField[] serialPersistentFields;
    
    public RelationNotification(final String type, final Object source, final long sequenceNumber, final long timeStamp, final String message, final String relationId, final String relTypeName, final ObjectName relObjName, final List unregMBeans) throws IllegalArgumentException {
        super(type, source, sequenceNumber, timeStamp, message);
        this.init(RelationNotification.CREATION_REMOVAL, type, source, sequenceNumber, timeStamp, message, relationId, relTypeName, relObjName, unregMBeans, null, null, null);
    }
    
    public RelationNotification(final String type, final Object source, final long sequenceNumber, final long timeStamp, final String message, final String relationId, final String relTypeName, final ObjectName relObjName, final String roleName, final List newRoleValue, final List oldRoleValue) throws IllegalArgumentException {
        super(type, source, sequenceNumber, timeStamp, message);
        this.init(RelationNotification.UPDATE, type, source, sequenceNumber, timeStamp, message, relationId, relTypeName, relObjName, null, roleName, newRoleValue, oldRoleValue);
    }
    
    public List getMBeansToUnregister() {
        if (this.unregisterMBeanList == null) {
            return new ArrayList();
        }
        return new ArrayList(this.unregisterMBeanList);
    }
    
    public List getNewRoleValue() {
        if (this.newRoleValue == null) {
            return new ArrayList();
        }
        return new ArrayList(this.newRoleValue);
    }
    
    public ObjectName getObjectName() {
        return this.relationObjName;
    }
    
    public List getOldRoleValue() {
        if (this.oldRoleValue == null) {
            return new ArrayList();
        }
        return new ArrayList(this.oldRoleValue);
    }
    
    public String getRelationId() {
        return this.relationId;
    }
    
    public String getRelationTypeName() {
        return this.relationTypeName;
    }
    
    public String getRoleName() {
        return this.roleName;
    }
    
    private void init(final int which, final String type, final Object source, final long sequenceNumber, final long timeStamp, final String message, final String relationId, final String relTypeName, final ObjectName relObjName, final List unregMBeans, final String roleName, final List newRoleValue, final List oldRoleValue) throws IllegalArgumentException {
        if (type == null) {
            throw new IllegalArgumentException("null notification type");
        }
        if (which == RelationNotification.CREATION_REMOVAL && type != "jmx.relation.creation.basic" && type != "jmx.relation.removal.basic" && type != "jmx.relation.creation.mbean" && type != "jmx.relation.removal.mbean") {
            throw new IllegalArgumentException("Invalid creation/removal notifcation");
        }
        if (which == RelationNotification.UPDATE && type != "jmx.relation.update.basic" && type != "jmx.relation.update.mbean") {
            throw new IllegalArgumentException("Invalid update notifcation");
        }
        if (type == null) {
            throw new IllegalArgumentException("null source");
        }
        if (relationId == null) {
            throw new IllegalArgumentException("null relation id");
        }
        if (relTypeName == null) {
            throw new IllegalArgumentException("null relation type name");
        }
        if (which == RelationNotification.UPDATE && roleName == null) {
            throw new IllegalArgumentException("null role name");
        }
        if (which == RelationNotification.UPDATE && newRoleValue == null) {
            throw new IllegalArgumentException("null new role value");
        }
        if (which == RelationNotification.UPDATE && oldRoleValue == null) {
            throw new IllegalArgumentException("null old role value");
        }
        this.relationId = relationId;
        this.relationTypeName = relTypeName;
        this.relationObjName = relObjName;
        if (unregMBeans != null) {
            this.unregisterMBeanList = new ArrayList(unregMBeans);
        }
        if (roleName != null) {
            this.roleName = roleName;
        }
        if (newRoleValue != null) {
            this.newRoleValue = new ArrayList(newRoleValue);
        }
        if (oldRoleValue != null) {
            this.oldRoleValue = new ArrayList(oldRoleValue);
        }
    }
    
    private void readObject(final ObjectInputStream ois) throws IOException, ClassNotFoundException {
        switch (Serialization.version) {
            case 10: {
                final ObjectInputStream.GetField getField = ois.readFields();
                this.newRoleValue = (ArrayList)getField.get("myNewRoleValue", null);
                this.oldRoleValue = (ArrayList)getField.get("myOldRoleValue", null);
                this.relationId = (String)getField.get("myRelId", null);
                this.relationObjName = (ObjectName)getField.get("myRelObjName", null);
                this.relationTypeName = (String)getField.get("myRelTypeName", null);
                this.roleName = (String)getField.get("myRoleName", null);
                this.unregisterMBeanList = (ArrayList)getField.get("myUnregMBeanList", null);
                break;
            }
            default: {
                ois.defaultReadObject();
                break;
            }
        }
    }
    
    private void writeObject(final ObjectOutputStream oos) throws IOException {
        switch (Serialization.version) {
            case 10: {
                final ObjectOutputStream.PutField putField = oos.putFields();
                putField.put("myNewRoleValue", this.newRoleValue);
                putField.put("myOldRoleValue", this.oldRoleValue);
                putField.put("myRelId", this.relationId);
                putField.put("myRelObjName", this.relationObjName);
                putField.put("myRelTypeName", this.relationTypeName);
                putField.put("myRoleName", this.roleName);
                putField.put("myUnregMBeanList", this.unregisterMBeanList);
                oos.writeFields();
                break;
            }
            default: {
                oos.defaultWriteObject();
                break;
            }
        }
    }
    
    static {
        RelationNotification.CREATION_REMOVAL = 0;
        RelationNotification.UPDATE = 1;
        switch (Serialization.version) {
            case 10: {
                serialVersionUID = -2126464566505527147L;
                serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("myNewRoleValue", ArrayList.class), new ObjectStreamField("myOldRoleValue", ArrayList.class), new ObjectStreamField("myRelId", String.class), new ObjectStreamField("myRelObjName", ObjectName.class), new ObjectStreamField("myRelTypeName", String.class), new ObjectStreamField("myRoleName", String.class), new ObjectStreamField("myUnregMBeanList", ArrayList.class) };
                break;
            }
            default: {
                serialVersionUID = -6871117877523310399L;
                serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("newRoleValue", List.class), new ObjectStreamField("oldRoleValue", List.class), new ObjectStreamField("relationId", String.class), new ObjectStreamField("relationObjName", ObjectName.class), new ObjectStreamField("relationTypeName", String.class), new ObjectStreamField("roleName", String.class), new ObjectStreamField("unregisterMBeanList", List.class) };
                break;
            }
        }
    }
}
