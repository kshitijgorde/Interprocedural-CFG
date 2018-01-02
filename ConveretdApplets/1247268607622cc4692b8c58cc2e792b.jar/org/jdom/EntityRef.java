// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

public class EntityRef extends Content
{
    private static final String CVS_ID = "@(#) $RCSfile: EntityRef.java,v $ $Revision: 1.22 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";
    protected String name;
    protected String publicID;
    protected String systemID;
    
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
    
    public String getName() {
        return this.name;
    }
    
    public String getValue() {
        return "";
    }
    
    public String getPublicID() {
        return this.publicID;
    }
    
    public String getSystemID() {
        return this.systemID;
    }
    
    public EntityRef setName(final String name) {
        final String reason = Verifier.checkXMLName(name);
        if (reason != null) {
            throw new IllegalNameException(name, "EntityRef", reason);
        }
        this.name = name;
        return this;
    }
    
    public EntityRef setPublicID(final String publicID) {
        final String reason = Verifier.checkPublicID(publicID);
        if (reason != null) {
            throw new IllegalDataException(publicID, "EntityRef", reason);
        }
        this.publicID = publicID;
        return this;
    }
    
    public EntityRef setSystemID(final String systemID) {
        final String reason = Verifier.checkSystemLiteral(systemID);
        if (reason != null) {
            throw new IllegalDataException(systemID, "EntityRef", reason);
        }
        this.systemID = systemID;
        return this;
    }
    
    public String toString() {
        return "[EntityRef: " + "&" + this.name + ";" + "]";
    }
}
