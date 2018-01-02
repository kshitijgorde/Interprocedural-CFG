// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import java.io.Serializable;

public class ObjectInstance implements Serializable
{
    private ObjectName name;
    private String className;
    private static final long serialVersionUID = -4099952623687795850L;
    
    public ObjectInstance(final String name, final String className) throws MalformedObjectNameException {
        this.name = null;
        this.className = null;
        if (name == null) {
            throw new MalformedObjectNameException("Null name");
        }
        this.name = new ObjectName(name);
        this.className = className;
    }
    
    public ObjectInstance(final ObjectName name, final String className) {
        this.name = null;
        this.className = null;
        this.name = name;
        this.className = className;
    }
    
    public ObjectName getObjectName() {
        return this.name;
    }
    
    public String getClassName() {
        return this.className;
    }
    
    public boolean equals(final Object object) {
        if (!(object instanceof ObjectInstance)) {
            return false;
        }
        final ObjectInstance oi = (ObjectInstance)object;
        return this.name.equals(oi.getObjectName()) && this.className.equals(oi.getClassName());
    }
    
    public int hashCode() {
        return this.name.hashCode() + this.className.hashCode();
    }
    
    public String toString() {
        return "ObjectInstance[" + this.name + ", " + this.className + "]";
    }
}
