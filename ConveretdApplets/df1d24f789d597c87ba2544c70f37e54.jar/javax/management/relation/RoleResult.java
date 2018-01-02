// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.relation;

import java.io.ObjectOutputStream;
import java.io.IOException;
import org.jboss.mx.util.Serialization;
import java.io.ObjectInputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;

public class RoleResult implements Serializable
{
    private RoleList roleList;
    private RoleUnresolvedList unresolvedRoleList;
    private static final long serialVersionUID;
    private static final ObjectStreamField[] serialPersistentFields;
    
    public RoleResult(final RoleList roleList, final RoleUnresolvedList roleUnresolvedList) {
        this.roleList = roleList;
        this.unresolvedRoleList = roleUnresolvedList;
    }
    
    public RoleList getRoles() {
        return this.roleList;
    }
    
    public RoleUnresolvedList getRolesUnresolved() {
        return this.unresolvedRoleList;
    }
    
    public void setRoles(final RoleList roleList) {
        this.roleList = roleList;
    }
    
    public void setRolesUnresolved(final RoleUnresolvedList roleUnresolvedList) {
        this.unresolvedRoleList = roleUnresolvedList;
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer("Resolved Roles:\n");
        buffer.append(this.roleList);
        buffer.append("\nUnresolved Roles\n");
        buffer.append(this.unresolvedRoleList);
        return buffer.toString();
    }
    
    private void readObject(final ObjectInputStream ois) throws IOException, ClassNotFoundException {
        switch (Serialization.version) {
            case 10: {
                final ObjectInputStream.GetField getField = ois.readFields();
                this.roleList = (RoleList)getField.get("myRoleList", null);
                this.unresolvedRoleList = (RoleUnresolvedList)getField.get("myRoleUnresList", null);
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
                putField.put("myRoleList", this.roleList);
                putField.put("myRoleUnresList", this.unresolvedRoleList);
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
        switch (Serialization.version) {
            case 10: {
                serialVersionUID = 3786616013762091099L;
                serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("myRoleList", RoleList.class), new ObjectStreamField("myRoleUnresList", RoleUnresolvedList.class) };
                break;
            }
            default: {
                serialVersionUID = -6304063118040985512L;
                serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("roleList", RoleList.class), new ObjectStreamField("unresolvedRoleList", RoleUnresolvedList.class) };
                break;
            }
        }
    }
}
