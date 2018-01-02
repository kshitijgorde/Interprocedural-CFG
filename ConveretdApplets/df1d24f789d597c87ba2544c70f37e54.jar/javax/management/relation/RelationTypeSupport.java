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
import java.util.List;
import java.io.ObjectStreamField;
import java.util.HashMap;

public class RelationTypeSupport implements RelationType
{
    private String name;
    private HashMap roleInfos;
    private static final long serialVersionUID;
    private static final ObjectStreamField[] serialPersistentFields;
    
    protected RelationTypeSupport(final String name) {
        this.name = name;
        this.roleInfos = new HashMap();
    }
    
    public RelationTypeSupport(final String name, final RoleInfo[] infos) throws IllegalArgumentException, InvalidRelationTypeException {
        if (name == null) {
            throw new IllegalArgumentException("Null name");
        }
        if (infos == null) {
            throw new IllegalArgumentException("No role information");
        }
        if (infos.length == 0) {
            throw new InvalidRelationTypeException("No role information");
        }
        this.name = name;
        this.roleInfos = new HashMap();
        for (int i = 0; i < infos.length; ++i) {
            if (infos[i] == null) {
                throw new InvalidRelationTypeException("Null role");
            }
            if (this.roleInfos.containsKey(infos[i].getName())) {
                throw new InvalidRelationTypeException("Duplicate role name" + infos[i].getName());
            }
            this.roleInfos.put(infos[i].getName(), infos[i]);
        }
    }
    
    public String getRelationTypeName() {
        return this.name;
    }
    
    public List getRoleInfos() {
        return new ArrayList(this.roleInfos.values());
    }
    
    public RoleInfo getRoleInfo(final String roleInfoName) throws IllegalArgumentException, RoleInfoNotFoundException {
        if (roleInfoName == null) {
            throw new IllegalArgumentException("Null role info name");
        }
        final RoleInfo result = this.roleInfos.get(roleInfoName);
        if (result == null) {
            throw new RoleInfoNotFoundException(roleInfoName);
        }
        return result;
    }
    
    protected void addRoleInfo(final RoleInfo roleInfo) throws IllegalArgumentException, InvalidRelationTypeException {
        if (roleInfo == null) {
            throw new IllegalArgumentException("No role information");
        }
        final String newName = roleInfo.getName();
        if (this.roleInfos.containsKey(newName)) {
            throw new InvalidRelationTypeException("Duplicate role name");
        }
        this.roleInfos.put(newName, roleInfo);
    }
    
    private void readObject(final ObjectInputStream ois) throws IOException, ClassNotFoundException {
        final ObjectInputStream.GetField getField = ois.readFields();
        switch (Serialization.version) {
            case 10: {
                this.roleInfos = (HashMap)getField.get("myRoleName2InfoMap", null);
                this.name = (String)getField.get("myTypeName", null);
                break;
            }
            default: {
                this.roleInfos = (HashMap)getField.get("roleName2InfoMap", null);
                this.name = (String)getField.get("typeName", null);
                break;
            }
        }
    }
    
    private void writeObject(final ObjectOutputStream oos) throws IOException {
        final ObjectOutputStream.PutField putField = oos.putFields();
        switch (Serialization.version) {
            case 10: {
                putField.put("myTypeName", this.name);
                putField.put("myRoleName2InfoMap", this.roleInfos);
                break;
            }
            default: {
                putField.put("typeName", this.name);
                putField.put("roleName2InfoMap", this.roleInfos);
                break;
            }
        }
        oos.writeFields();
    }
    
    static {
        switch (Serialization.version) {
            case 10: {
                serialVersionUID = -8179019472410837190L;
                serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("myIsInRelServFlg", Boolean.TYPE), new ObjectStreamField("myRoleName2InfoMap", HashMap.class), new ObjectStreamField("myTypeName", String.class) };
                break;
            }
            default: {
                serialVersionUID = 4611072955724144607L;
                serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("isInRelationService", Boolean.TYPE), new ObjectStreamField("roleName2InfoMap", HashMap.class), new ObjectStreamField("typeName", String.class) };
                break;
            }
        }
    }
}
