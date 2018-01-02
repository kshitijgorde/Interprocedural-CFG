// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dtd;

import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.XMLResourceIdentifier;
import java.util.Vector;
import org.apache.xerces.xni.grammars.XMLGrammarDescription;
import org.apache.xerces.util.XMLResourceIdentifierImpl;

public class XMLDTDDescription extends XMLResourceIdentifierImpl implements XMLGrammarDescription
{
    protected String fRootName;
    protected Vector fPossibleRoots;
    
    public XMLDTDDescription(final XMLResourceIdentifier id, final String rootName) {
        this.fRootName = null;
        this.fPossibleRoots = null;
        this.setValues(id.getPublicId(), id.getLiteralSystemId(), id.getBaseSystemId(), id.getExpandedSystemId());
        this.fRootName = rootName;
        this.fPossibleRoots = null;
    }
    
    public XMLDTDDescription(final String publicId, final String literalId, final String baseId, final String expandedId, final String rootName) {
        this.fRootName = null;
        this.fPossibleRoots = null;
        this.setValues(publicId, literalId, baseId, expandedId);
        this.fRootName = rootName;
        this.fPossibleRoots = null;
    }
    
    public XMLDTDDescription(final XMLInputSource source) {
        this.fRootName = null;
        this.fPossibleRoots = null;
        this.setValues(source.getPublicId(), null, source.getBaseSystemId(), source.getSystemId());
        this.fRootName = null;
        this.fPossibleRoots = null;
    }
    
    public String getGrammarType() {
        return "http://www.w3.org/TR/REC-xml";
    }
    
    public String getRootName() {
        return this.fRootName;
    }
    
    public void setRootName(final String rootName) {
        this.fRootName = rootName;
        this.fPossibleRoots = null;
    }
    
    public void setPossibleRoots(final Vector possibleRoots) {
        this.fPossibleRoots = possibleRoots;
    }
    
    public boolean equals(final Object desc) {
        if (!(desc instanceof XMLGrammarDescription)) {
            return false;
        }
        if (!this.getGrammarType().equals(((XMLGrammarDescription)desc).getGrammarType())) {
            return false;
        }
        final XMLDTDDescription dtdDesc = (XMLDTDDescription)desc;
        if (this.fRootName != null) {
            if (dtdDesc.fRootName != null && !dtdDesc.fRootName.equals(this.fRootName)) {
                return false;
            }
            if (dtdDesc.fPossibleRoots != null && !dtdDesc.fPossibleRoots.contains(this.fRootName)) {
                return false;
            }
        }
        else if (this.fPossibleRoots != null) {
            if (dtdDesc.fRootName != null) {
                if (!this.fPossibleRoots.contains(dtdDesc.fRootName)) {
                    return false;
                }
            }
            else {
                if (dtdDesc.fPossibleRoots == null) {
                    return false;
                }
                boolean found = false;
                for (int i = 0; i < this.fPossibleRoots.size(); ++i) {
                    final String root = this.fPossibleRoots.elementAt(i);
                    found = dtdDesc.fPossibleRoots.contains(root);
                    if (found) {
                        break;
                    }
                }
                if (!found) {
                    return false;
                }
            }
        }
        if (super.fExpandedSystemId != null) {
            if (!super.fExpandedSystemId.equals(dtdDesc.fExpandedSystemId)) {
                return false;
            }
        }
        else if (dtdDesc.fExpandedSystemId != null) {
            return false;
        }
        if (super.fPublicId != null) {
            if (!super.fPublicId.equals(dtdDesc.fPublicId)) {
                return false;
            }
        }
        else if (dtdDesc.fPublicId != null) {
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
