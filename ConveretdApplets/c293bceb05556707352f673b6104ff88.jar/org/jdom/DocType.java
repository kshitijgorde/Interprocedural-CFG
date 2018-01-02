// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

import org.jdom.output.XMLOutputter;
import java.io.Serializable;

public class DocType implements Serializable, Cloneable
{
    private static final String CVS_ID = "@(#) $RCSfile: DocType.java,v $ $Revision: 1.18 $ $Date: 2002/02/05 08:03:18 $ $Name: jdom_1_0_b8 $";
    protected String elementName;
    protected String publicID;
    protected String systemID;
    protected Document document;
    protected String internalSubset;
    
    protected DocType() {
    }
    
    public DocType(final String elementName, final String publicID, final String systemID) {
        this.setElementName(elementName);
        this.setPublicID(publicID);
        this.setSystemID(systemID);
    }
    
    public DocType(final String elementName, final String systemID) {
        this(elementName, null, systemID);
    }
    
    public DocType(final String elementName) {
        this(elementName, null, null);
    }
    
    public String getElementName() {
        return this.elementName;
    }
    
    public DocType setElementName(final String elementName) {
        final String reason = Verifier.checkXMLName(elementName);
        if (reason != null) {
            throw new IllegalNameException(elementName, "DocType", reason);
        }
        this.elementName = elementName;
        return this;
    }
    
    public String getPublicID() {
        return this.publicID;
    }
    
    public DocType setPublicID(final String publicID) {
        final String reason = Verifier.checkPublicID(publicID);
        if (reason != null) {
            throw new IllegalDataException(publicID, "DocType", reason);
        }
        this.publicID = publicID;
        return this;
    }
    
    public String getSystemID() {
        return this.systemID;
    }
    
    public DocType setSystemID(final String systemID) {
        final String reason = Verifier.checkSystemLiteral(systemID);
        if (reason != null) {
            throw new IllegalDataException(systemID, "DocType", reason);
        }
        this.systemID = systemID;
        return this;
    }
    
    public Document getDocument() {
        return this.document;
    }
    
    protected DocType setDocument(final Document document) {
        this.document = document;
        return this;
    }
    
    public void setInternalSubset(final String newData) {
        this.internalSubset = newData;
    }
    
    public String getInternalSubset() {
        return this.internalSubset;
    }
    
    public String toString() {
        return "[DocType: " + new XMLOutputter().outputString(this) + "]";
    }
    
    public final boolean equals(final Object ob) {
        if (ob instanceof DocType) {
            final DocType dt = (DocType)ob;
            return this.stringEquals(dt.elementName, this.elementName) && this.stringEquals(dt.publicID, this.publicID) && this.stringEquals(dt.systemID, this.systemID);
        }
        return false;
    }
    
    private boolean stringEquals(final String s1, final String s2) {
        return (s1 == null && s2 == null) || ((s1 != null || s2 == null) && s1.equals(s2));
    }
    
    public final int hashCode() {
        return super.hashCode();
    }
    
    public Object clone() {
        DocType docType = null;
        try {
            docType = (DocType)super.clone();
        }
        catch (CloneNotSupportedException ex) {}
        docType.document = null;
        return docType;
    }
}
