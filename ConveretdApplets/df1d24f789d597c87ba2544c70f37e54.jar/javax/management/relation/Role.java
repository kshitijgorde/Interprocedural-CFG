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
import java.util.Iterator;
import java.io.ObjectStreamField;
import java.util.List;
import java.io.Serializable;

public class Role implements Serializable
{
    private String name;
    private List objectNameList;
    private static final long serialVersionUID;
    private static final ObjectStreamField[] serialPersistentFields;
    
    public static String roleValueToString(final List roleValue) throws IllegalArgumentException {
        if (roleValue == null) {
            throw new IllegalArgumentException("null roleValue");
        }
        final StringBuffer buffer = new StringBuffer();
        final Iterator iterator = roleValue.iterator();
        while (iterator.hasNext()) {
            buffer.append(iterator.next());
            if (iterator.hasNext()) {
                buffer.append("\n");
            }
        }
        return buffer.toString();
    }
    
    public Role(final String roleName, final List roleValue) throws IllegalArgumentException {
        this.setRoleName(roleName);
        this.setRoleValue(roleValue);
    }
    
    public String getRoleName() {
        return this.name;
    }
    
    public List getRoleValue() {
        return new ArrayList(this.objectNameList);
    }
    
    public void setRoleName(final String roleName) throws IllegalArgumentException {
        if (roleName == null) {
            throw new IllegalArgumentException("Null roleName");
        }
        this.name = roleName;
    }
    
    public void setRoleValue(final List roleValue) throws IllegalArgumentException {
        if (roleValue == null) {
            throw new IllegalArgumentException("Null roleValue");
        }
        this.objectNameList = new ArrayList(roleValue);
    }
    
    public synchronized Object clone() {
        return new Role(this.name, this.objectNameList);
    }
    
    public synchronized String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("Role@");
        buffer.append(System.identityHashCode(this));
        buffer.append(" RoleName(");
        buffer.append(this.name);
        buffer.append(") ObjectNames (");
        final Iterator iterator = this.objectNameList.iterator();
        while (iterator.hasNext()) {
            buffer.append(iterator.next());
            if (iterator.hasNext()) {
                buffer.append(" & ");
            }
        }
        buffer.append(")");
        return buffer.toString();
    }
    
    private void readObject(final ObjectInputStream ois) throws IOException, ClassNotFoundException {
        switch (Serialization.version) {
            case 10: {
                final ObjectInputStream.GetField getField = ois.readFields();
                this.name = (String)getField.get("myName", null);
                this.objectNameList = (List)getField.get("myObjNameList", null);
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
                putField.put("myName", this.name);
                putField.put("myObjNameList", this.objectNameList);
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
                serialVersionUID = -1959486389343113026L;
                serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("myName", String.class), new ObjectStreamField("myObjNameList", List.class) };
                break;
            }
            default: {
                serialVersionUID = -279985518429862552L;
                serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("name", String.class), new ObjectStreamField("objectNameList", List.class) };
                break;
            }
        }
    }
}
