// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.relation;

import java.io.ObjectOutputStream;
import java.io.IOException;
import org.jboss.mx.util.Serialization;
import java.io.ObjectInputStream;
import javax.management.NotCompliantMBeanException;
import java.io.ObjectStreamField;
import java.io.Serializable;

public class RoleInfo implements Serializable
{
    public static int ROLE_CARDINALITY_INFINITY;
    private String name;
    private String referencedMBeanClassName;
    boolean isReadable;
    boolean isWritable;
    int minDegree;
    int maxDegree;
    String description;
    private static final long serialVersionUID;
    private static final ObjectStreamField[] serialPersistentFields;
    
    public RoleInfo(final RoleInfo other) throws IllegalArgumentException {
        if (other == null) {
            throw new IllegalArgumentException("Null role info");
        }
        this.name = other.name;
        this.referencedMBeanClassName = other.referencedMBeanClassName;
        this.isReadable = other.isReadable;
        this.isWritable = other.isWritable;
        this.minDegree = other.minDegree;
        this.maxDegree = other.maxDegree;
        this.description = other.description;
    }
    
    public RoleInfo(final String name, final String className) throws IllegalArgumentException, ClassNotFoundException, NotCompliantMBeanException {
        this(name, className, true, true);
    }
    
    public RoleInfo(final String name, final String className, final boolean readable, final boolean writable) throws IllegalArgumentException, ClassNotFoundException, NotCompliantMBeanException {
        if (name == null) {
            throw new IllegalArgumentException("Null name");
        }
        if (className == null) {
            throw new IllegalArgumentException("Null class name");
        }
        this.name = name;
        this.referencedMBeanClassName = className;
        this.isReadable = readable;
        this.isWritable = writable;
        this.minDegree = 1;
        this.maxDegree = 1;
    }
    
    public RoleInfo(final String name, final String className, final boolean readable, final boolean writable, final int minDegree, final int maxDegree, final String description) throws IllegalArgumentException, ClassNotFoundException, NotCompliantMBeanException, InvalidRoleInfoException {
        if (name == null) {
            throw new IllegalArgumentException("Null name");
        }
        if (className == null) {
            throw new IllegalArgumentException("Null class name");
        }
        if (minDegree < RoleInfo.ROLE_CARDINALITY_INFINITY) {
            throw new InvalidRoleInfoException("invalid minimum");
        }
        if (maxDegree < RoleInfo.ROLE_CARDINALITY_INFINITY) {
            throw new InvalidRoleInfoException("invalid maximum");
        }
        if (maxDegree < minDegree && maxDegree != RoleInfo.ROLE_CARDINALITY_INFINITY) {
            throw new InvalidRoleInfoException("maximum less than minimum");
        }
        if (minDegree == RoleInfo.ROLE_CARDINALITY_INFINITY && maxDegree != RoleInfo.ROLE_CARDINALITY_INFINITY) {
            throw new InvalidRoleInfoException("maximum less than minimum");
        }
        this.name = name;
        this.referencedMBeanClassName = className;
        this.minDegree = minDegree;
        this.maxDegree = maxDegree;
        this.isReadable = readable;
        this.isWritable = writable;
        this.description = description;
    }
    
    public boolean checkMinDegree(final int value) {
        if (this.minDegree == RoleInfo.ROLE_CARDINALITY_INFINITY) {
            return value >= RoleInfo.ROLE_CARDINALITY_INFINITY;
        }
        return value >= this.minDegree;
    }
    
    public boolean checkMaxDegree(final int value) {
        if (this.maxDegree == RoleInfo.ROLE_CARDINALITY_INFINITY) {
            return this.maxDegree >= RoleInfo.ROLE_CARDINALITY_INFINITY;
        }
        return value <= this.maxDegree;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public int getMinDegree() {
        return this.minDegree;
    }
    
    public int getMaxDegree() {
        return this.maxDegree;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getRefMBeanClassName() {
        return this.referencedMBeanClassName;
    }
    
    public boolean isReadable() {
        return this.isReadable;
    }
    
    public boolean isWritable() {
        return this.isWritable;
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer("RoleInfo for name: (");
        buffer.append(this.name);
        buffer.append(") class name: (");
        buffer.append(this.referencedMBeanClassName);
        buffer.append(") description: (");
        buffer.append(this.description);
        buffer.append(") readable: (");
        buffer.append(this.isReadable);
        buffer.append(") writable: (");
        buffer.append(this.isWritable);
        buffer.append(") minimum degree: (");
        buffer.append(this.minDegree);
        buffer.append(") maximum degree: (");
        buffer.append(this.maxDegree);
        buffer.append(")");
        return buffer.toString();
    }
    
    private void readObject(final ObjectInputStream ois) throws IOException, ClassNotFoundException {
        switch (Serialization.version) {
            case 10: {
                final ObjectInputStream.GetField getField = ois.readFields();
                this.description = (String)getField.get("myDescription", null);
                this.isReadable = getField.get("myIsReadableFlg", false);
                this.isWritable = getField.get("myIsWritableFlg", false);
                this.maxDegree = getField.get("myMaxDegree", 1);
                this.minDegree = getField.get("myMinDegree", 1);
                this.name = (String)getField.get("myName", null);
                this.referencedMBeanClassName = (String)getField.get("myRefMBeanClassName", null);
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
                putField.put("myDescription", this.description);
                putField.put("myIsReadableFlg", this.isReadable);
                putField.put("myIsWritableFlg", this.isWritable);
                putField.put("myMaxDegree", this.maxDegree);
                putField.put("myMinDegree", this.minDegree);
                putField.put("myName", this.name);
                putField.put("myRefMBeanClassName", this.referencedMBeanClassName);
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
        RoleInfo.ROLE_CARDINALITY_INFINITY = -1;
        switch (Serialization.version) {
            case 10: {
                serialVersionUID = 7227256952085334351L;
                serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("myDescription", String.class), new ObjectStreamField("myIsReadableFlg", Boolean.TYPE), new ObjectStreamField("myIsWritableFlg", Boolean.TYPE), new ObjectStreamField("myMaxDegree", Integer.TYPE), new ObjectStreamField("myMinDegree", Integer.TYPE), new ObjectStreamField("myName", String.class), new ObjectStreamField("myRefMBeanClassName", String.class) };
                break;
            }
            default: {
                serialVersionUID = 2504952983494636987L;
                serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("description", String.class), new ObjectStreamField("isReadable", Boolean.TYPE), new ObjectStreamField("isWritable", Boolean.TYPE), new ObjectStreamField("maxDegree", Integer.TYPE), new ObjectStreamField("minDegree", Integer.TYPE), new ObjectStreamField("name", String.class), new ObjectStreamField("referencedMBeanClassName", String.class) };
                break;
            }
        }
    }
}
