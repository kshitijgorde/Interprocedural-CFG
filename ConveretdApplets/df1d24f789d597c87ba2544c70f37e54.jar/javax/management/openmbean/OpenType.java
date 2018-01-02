// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.openmbean;

import javax.management.ObjectName;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.Date;
import org.jboss.mx.util.MetaDataUtil;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;

public abstract class OpenType implements Serializable
{
    private static final long serialVersionUID = -9195195325186646468L;
    private static final ObjectStreamField[] serialPersistentFields;
    private String className;
    private String description;
    private String typeName;
    private transient boolean array;
    public static final String[] ALLOWED_CLASSNAMES;
    
    protected OpenType(final String className, final String typeName, final String description) throws OpenDataException {
        this.array = false;
        this.init(className, typeName, description);
    }
    
    public String getClassName() {
        return this.className;
    }
    
    public String getTypeName() {
        return this.typeName;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public boolean isArray() {
        return this.array;
    }
    
    public abstract boolean isValue(final Object p0);
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        final ObjectInputStream.GetField getField = in.readFields();
        final String className = (String)getField.get("className", null);
        final String typeName = (String)getField.get("typeName", null);
        final String description = (String)getField.get("description", null);
        try {
            this.init(className, typeName, description);
        }
        catch (Exception e) {
            throw new StreamCorruptedException(e.toString());
        }
    }
    
    public abstract boolean equals(final Object p0);
    
    public abstract int hashCode();
    
    public abstract String toString();
    
    private void init(final String className, final String typeName, final String description) throws OpenDataException {
        if (className == null || className.trim().equals("")) {
            throw new IllegalArgumentException("null or empty class name");
        }
        if (typeName == null || typeName.trim().equals("")) {
            throw new IllegalArgumentException("null or empty type name");
        }
        if (description == null || description.trim().equals("")) {
            throw new IllegalArgumentException("null or empty description");
        }
        final String testClassName = MetaDataUtil.getBaseClassName(className);
        if (testClassName == null) {
            throw new OpenDataException("Invalid array declaration (see the javadocs for java.lang.Class): " + className);
        }
        if (!testClassName.equals(className)) {
            this.array = true;
        }
        boolean ok = false;
        for (int i = 0; i < OpenType.ALLOWED_CLASSNAMES.length; ++i) {
            if (testClassName.equals(OpenType.ALLOWED_CLASSNAMES[i])) {
                ok = true;
                break;
            }
        }
        if (!ok) {
            throw new OpenDataException("Not an OpenType allowed class name: " + className);
        }
        this.className = className;
        this.typeName = typeName;
        this.description = description;
    }
    
    static {
        serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("className", String.class), new ObjectStreamField("description", String.class), new ObjectStreamField("typeName", String.class) };
        ALLOWED_CLASSNAMES = new String[] { Void.class.getName(), Boolean.class.getName(), Character.class.getName(), Byte.class.getName(), Short.class.getName(), Integer.class.getName(), Long.class.getName(), Float.class.getName(), Double.class.getName(), String.class.getName(), Date.class.getName(), BigDecimal.class.getName(), BigInteger.class.getName(), ObjectName.class.getName(), CompositeData.class.getName(), TabularData.class.getName() };
    }
}
