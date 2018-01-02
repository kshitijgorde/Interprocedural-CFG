// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.relation;

import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.StreamCorruptedException;
import org.jboss.mx.util.Serialization;
import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.io.ObjectStreamField;
import java.util.List;
import java.io.Serializable;

public class RoleUnresolved implements Serializable
{
    private String roleName;
    private List roleValue;
    private int problemType;
    private static final long serialVersionUID;
    private static final ObjectStreamField[] serialPersistentFields;
    
    public RoleUnresolved(final String roleName, final List roleValue, final int problemType) throws IllegalArgumentException {
        if (roleName == null) {
            throw new IllegalArgumentException("Null roleName");
        }
        if (roleValue == null) {
            throw new IllegalArgumentException("Null roleValue");
        }
        if (!RoleStatus.isRoleStatus(problemType)) {
            throw new IllegalArgumentException("Invalid problem type.");
        }
        this.roleName = roleName;
        this.roleValue = new ArrayList(roleValue);
        this.problemType = problemType;
    }
    
    public int getProblemType() {
        return this.problemType;
    }
    
    public String getRoleName() {
        return this.roleName;
    }
    
    public List getRoleValue() {
        return this.roleValue;
    }
    
    public void setProblemType(final int problemType) throws IllegalArgumentException {
        if (!RoleStatus.isRoleStatus(problemType)) {
            throw new IllegalArgumentException("Invalid problem type.");
        }
        this.problemType = problemType;
    }
    
    public void setRoleName(final String roleName) throws IllegalArgumentException {
        if (roleName == null) {
            throw new IllegalArgumentException("Null roleName");
        }
        this.roleName = roleName;
    }
    
    public void setRoleValue(final List roleValue) {
        this.roleValue = new ArrayList(roleValue);
    }
    
    public synchronized Object clone() {
        return new RoleUnresolved(this.roleName, this.roleValue, this.problemType);
    }
    
    public synchronized String toString() {
        final StringBuffer buffer = new StringBuffer("Problem (");
        buffer.append(this.problemType);
        buffer.append(") Role Name (");
        buffer.append(this.roleName);
        buffer.append(") ObjectNames (");
        final Iterator iterator = this.roleValue.iterator();
        while (iterator.hasNext()) {
            buffer.append(iterator.next());
            if (iterator.hasNext()) {
                buffer.append(" ");
            }
        }
        buffer.append(")");
        return buffer.toString();
    }
    
    private void readObject(final ObjectInputStream ois) throws IOException, ClassNotFoundException {
        switch (Serialization.version) {
            case 10: {
                final ObjectInputStream.GetField getField = ois.readFields();
                this.roleName = (String)getField.get("myRoleName", null);
                this.roleValue = (List)getField.get("myRoleValue", null);
                this.problemType = getField.get("myPbType", -1);
                if (this.problemType == -1) {
                    throw new StreamCorruptedException("No problem type?");
                }
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
                putField.put("myRoleName", this.roleName);
                putField.put("myRoleValue", this.roleValue);
                putField.put("myPbType", this.problemType);
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
                serialVersionUID = -9026457686611660144L;
                serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("myRoleName", String.class), new ObjectStreamField("myRoleValue", ArrayList.class), new ObjectStreamField("myPbType", Integer.TYPE) };
                break;
            }
            default: {
                serialVersionUID = -48350262537070138L;
                serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("roleName", String.class), new ObjectStreamField("roleValue", List.class), new ObjectStreamField("problemType", Integer.TYPE) };
                break;
            }
        }
    }
}
