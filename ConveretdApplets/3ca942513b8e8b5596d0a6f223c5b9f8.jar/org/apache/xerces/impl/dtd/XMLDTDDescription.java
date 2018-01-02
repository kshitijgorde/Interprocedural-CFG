// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dtd;

import org.apache.xerces.xni.grammars.XMLGrammarDescription;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.XMLResourceIdentifier;
import java.util.Vector;
import org.apache.xerces.util.XMLResourceIdentifierImpl;

public class XMLDTDDescription extends XMLResourceIdentifierImpl implements org.apache.xerces.xni.grammars.XMLDTDDescription
{
    protected String fRootName;
    protected Vector fPossibleRoots;
    
    public XMLDTDDescription(final XMLResourceIdentifier xmlResourceIdentifier, final String fRootName) {
        this.fRootName = null;
        this.fPossibleRoots = null;
        this.setValues(xmlResourceIdentifier.getPublicId(), xmlResourceIdentifier.getLiteralSystemId(), xmlResourceIdentifier.getBaseSystemId(), xmlResourceIdentifier.getExpandedSystemId());
        this.fRootName = fRootName;
        this.fPossibleRoots = null;
    }
    
    public XMLDTDDescription(final String s, final String s2, final String s3, final String s4, final String fRootName) {
        this.fRootName = null;
        this.fPossibleRoots = null;
        this.setValues(s, s2, s3, s4);
        this.fRootName = fRootName;
        this.fPossibleRoots = null;
    }
    
    public XMLDTDDescription(final XMLInputSource xmlInputSource) {
        this.fRootName = null;
        this.fPossibleRoots = null;
        this.setValues(xmlInputSource.getPublicId(), null, xmlInputSource.getBaseSystemId(), xmlInputSource.getSystemId());
        this.fRootName = null;
        this.fPossibleRoots = null;
    }
    
    public String getGrammarType() {
        return "http://www.w3.org/TR/REC-xml";
    }
    
    public String getRootName() {
        return this.fRootName;
    }
    
    public void setRootName(final String fRootName) {
        this.fRootName = fRootName;
        this.fPossibleRoots = null;
    }
    
    public void setPossibleRoots(final Vector fPossibleRoots) {
        this.fPossibleRoots = fPossibleRoots;
    }
    
    public boolean equals(final Object o) {
        if (!(o instanceof XMLGrammarDescription)) {
            return false;
        }
        if (!this.getGrammarType().equals(((XMLGrammarDescription)o).getGrammarType())) {
            return false;
        }
        final XMLDTDDescription xmldtdDescription = (XMLDTDDescription)o;
        if (this.fRootName != null) {
            if (xmldtdDescription.fRootName != null && !xmldtdDescription.fRootName.equals(this.fRootName)) {
                return false;
            }
            if (xmldtdDescription.fPossibleRoots != null && !xmldtdDescription.fPossibleRoots.contains(this.fRootName)) {
                return false;
            }
        }
        else if (this.fPossibleRoots != null) {
            if (xmldtdDescription.fRootName != null) {
                if (!this.fPossibleRoots.contains(xmldtdDescription.fRootName)) {
                    return false;
                }
            }
            else {
                if (xmldtdDescription.fPossibleRoots == null) {
                    return false;
                }
                boolean contains = false;
                for (int i = 0; i < this.fPossibleRoots.size(); ++i) {
                    contains = xmldtdDescription.fPossibleRoots.contains(this.fPossibleRoots.elementAt(i));
                    if (contains) {
                        break;
                    }
                }
                if (!contains) {
                    return false;
                }
            }
        }
        if (super.fExpandedSystemId != null) {
            if (!super.fExpandedSystemId.equals(xmldtdDescription.fExpandedSystemId)) {
                return false;
            }
        }
        else if (xmldtdDescription.fExpandedSystemId != null) {
            return false;
        }
        if (super.fPublicId != null) {
            if (!super.fPublicId.equals(xmldtdDescription.fPublicId)) {
                return false;
            }
        }
        else if (xmldtdDescription.fPublicId != null) {
            return false;
        }
        return true;
    }
    
    public int hashCode() {
        if (super.fExpandedSystemId != null) {
            return super.fExpandedSystemId.hashCode();
        }
        if (super.fPublicId != null) {
            return super.fPublicId.hashCode();
        }
        return 0;
    }
}
