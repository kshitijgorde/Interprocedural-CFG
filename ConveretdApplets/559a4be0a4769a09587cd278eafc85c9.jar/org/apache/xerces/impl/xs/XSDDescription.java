// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.grammars.XMLGrammarDescription;
import org.apache.xerces.util.XMLResourceIdentifierImpl;

public class XSDDescription extends XMLResourceIdentifierImpl implements XMLGrammarDescription
{
    public static final short CONTEXT_INITIALIZE = -1;
    public static final short CONTEXT_INCLUDE = 0;
    public static final short CONTEXT_REDEFINE = 1;
    public static final short CONTEXT_IMPORT = 2;
    public static final short CONTEXT_PREPARSE = 3;
    public static final short CONTEXT_INSTANCE = 4;
    public static final short CONTEXT_ELEMENT = 5;
    public static final short CONTEXT_ATTRIBUTE = 6;
    public static final short CONTEXT_XSITYPE = 7;
    protected short fContextType;
    protected String fTargetNamespace;
    protected String[] fLocationHints;
    protected QName fTriggeringComponent;
    protected QName fEnclosedElementName;
    protected XMLAttributes fAttributes;
    
    public String getGrammarType() {
        return "http://www.w3.org/2001/XMLSchema";
    }
    
    public short getContextType() {
        return this.fContextType;
    }
    
    public String getTargetNamespace() {
        return this.fTargetNamespace;
    }
    
    public String[] getLocationHints() {
        return this.fLocationHints;
    }
    
    public QName getTriggeringComponent() {
        return this.fTriggeringComponent;
    }
    
    public QName getEnclosingElementName() {
        return this.fEnclosedElementName;
    }
    
    public XMLAttributes getAttributes() {
        return this.fAttributes;
    }
    
    public boolean fromInstance() {
        return this.fContextType == 6 || this.fContextType == 5 || this.fContextType == 4 || this.fContextType == 7;
    }
    
    public boolean equals(final Object descObj) {
        if (!(descObj instanceof XMLGrammarDescription)) {
            return false;
        }
        final XMLGrammarDescription desc = (XMLGrammarDescription)descObj;
        return this.getGrammarType().equals(desc.getGrammarType()) && ((this.fTargetNamespace != null && this.fTargetNamespace.equals(((XSDDescription)desc).getTargetNamespace())) || (this.fTargetNamespace == null && ((XSDDescription)desc).getTargetNamespace() == null));
    }
    
    public int hashCode() {
        return (this.fTargetNamespace == null) ? 0 : this.fTargetNamespace.hashCode();
    }
    
    public void setContextType(final short contextType) {
        this.fContextType = contextType;
    }
    
    public void setTargetNamespace(final String targetNamespace) {
        this.fTargetNamespace = targetNamespace;
    }
    
    public void setLocationHints(final String[] locationHints) {
        final int length = locationHints.length;
        System.arraycopy(locationHints, 0, this.fLocationHints = new String[length], 0, length);
    }
    
    public void setTriggeringComponent(final QName triggeringComponent) {
        this.fTriggeringComponent = triggeringComponent;
    }
    
    public void setEnclosingElementName(final QName enclosedElementName) {
        this.fEnclosedElementName = enclosedElementName;
    }
    
    public void setAttributes(final XMLAttributes attributes) {
        this.fAttributes = attributes;
    }
    
    public void reset() {
        this.fContextType = -1;
        this.fTargetNamespace = null;
        this.fLocationHints = null;
        this.fTriggeringComponent = null;
        this.fEnclosedElementName = null;
        this.fAttributes = null;
    }
    
    public XSDDescription makeClone() {
        final XSDDescription desc = new XSDDescription();
        desc.fAttributes = this.fAttributes;
        desc.fBaseSystemId = super.fBaseSystemId;
        desc.fContextType = this.fContextType;
        desc.fEnclosedElementName = this.fEnclosedElementName;
        desc.fExpandedSystemId = super.fExpandedSystemId;
        desc.fLiteralSystemId = super.fLiteralSystemId;
        desc.fLocationHints = this.fLocationHints;
        desc.fPublicId = super.fPublicId;
        desc.fTargetNamespace = this.fTargetNamespace;
        desc.fTriggeringComponent = this.fTriggeringComponent;
        return desc;
    }
}
