// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

import org.jdom.output.XMLOutputter;

public class DocType extends Content
{
    private static final String CVS_ID = "@(#) $RCSfile: DocType.java,v $ $Revision: 1.32 $ $Date: 2007/11/10 05:28:58 $ $Name: jdom_1_1_1 $";
    protected String elementName;
    protected String publicID;
    protected String systemID;
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
    
    public String getValue() {
        return "";
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
}
