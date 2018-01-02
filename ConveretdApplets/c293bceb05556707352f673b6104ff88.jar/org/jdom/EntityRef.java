// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

import java.io.Serializable;

public class EntityRef implements Serializable, Cloneable
{
    private static final String CVS_ID = "@(#) $RCSfile: EntityRef.java,v $ $Revision: 1.8 $ $Date: 2002/03/12 07:11:39 $ $Name: jdom_1_0_b8 $";
    protected String name;
    protected String publicID;
    protected String systemID;
    protected Object parent;
    
    protected EntityRef() {
    }
    
    public EntityRef(final String name) {
        this(name, null, null);
    }
    
    public EntityRef(final String name, final String systemID) {
        this(name, null, systemID);
    }
    
    public EntityRef(final String name, final String publicID, final String systemID) {
        this.setName(name);
        this.setPublicID(publicID);
        this.setSystemID(systemID);
    }
    
    public Object clone() {
        EntityRef entity = null;
        try {
            entity = (EntityRef)super.clone();
        }
        catch (CloneNotSupportedException ex) {}
        entity.parent = null;
        return entity;
    }
    
    public EntityRef detach() {
        final Element p = this.getParent();
        if (p != null) {
            p.removeContent(this);
        }
        return this;
    }
    
    public final boolean equals(final Object ob) {
        return ob == this;
    }
    
    public Document getDocument() {
        if (this.parent != null) {
            return ((Element)this.parent).getDocument();
        }
        return null;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Element getParent() {
        return (Element)this.parent;
    }
    
    public String getPublicID() {
        return this.publicID;
    }
    
    public String getSystemID() {
        return this.systemID;
    }
    
    public final int hashCode() {
        return super.hashCode();
    }
    
    protected EntityRef setParent(final Element parent) {
        this.parent = parent;
        return this;
    }
    
    public EntityRef setName(final String name) {
        final String reason = Verifier.checkXMLName(name);
        if (reason != null) {
            throw new IllegalNameException(name, "EntityRef", reason);
        }
        this.name = name;
        return this;
    }
    
    public EntityRef setPublicID(final String newPublicID) {
        final String reason = Verifier.checkPublicID(this.publicID);
        if (reason != null) {
            throw new IllegalDataException(this.publicID, "EntityRef", reason);
        }
        this.publicID = newPublicID;
        return this;
    }
    
    public EntityRef setSystemID(final String newSystemID) {
        final String reason = Verifier.checkSystemLiteral(this.systemID);
        if (reason != null) {
            throw new IllegalDataException(this.systemID, "EntityRef", reason);
        }
        this.systemID = newSystemID;
        return this;
    }
    
    public String toString() {
        return "[EntityRef: " + "&" + this.name + ";" + "]";
    }
}
