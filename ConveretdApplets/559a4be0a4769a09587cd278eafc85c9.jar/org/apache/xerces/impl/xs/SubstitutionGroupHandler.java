// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import java.util.Vector;
import org.apache.xerces.xni.QName;
import java.util.Hashtable;

public class SubstitutionGroupHandler
{
    XSGrammarBucket fGrammarBucket;
    Hashtable fSubGroups;
    private static final XSElementDecl[] EMPTY_VECTOR;
    
    public SubstitutionGroupHandler(final XSGrammarBucket grammarBucket) {
        this.fSubGroups = new Hashtable();
        this.fGrammarBucket = grammarBucket;
    }
    
    public XSElementDecl getMatchingElemDecl(final QName element, final XSElementDecl exemplar) {
        if (element.localpart == exemplar.fName && element.uri == exemplar.fTargetNamespace) {
            return exemplar;
        }
        if (exemplar.fScope != 1) {
            return null;
        }
        if ((exemplar.fBlock & 0x4) != 0x0) {
            return null;
        }
        final SchemaGrammar sGrammar = this.fGrammarBucket.getGrammar(element.uri);
        if (sGrammar == null) {
            return null;
        }
        final XSElementDecl eDecl = sGrammar.getGlobalElementDecl(element.localpart);
        if (eDecl == null) {
            return null;
        }
        if (this.substitutionGroupOK(eDecl, exemplar, exemplar.fBlock)) {
            return eDecl;
        }
        return null;
    }
    
    protected boolean substitutionGroupOK(final XSElementDecl element, final XSElementDecl exemplar, final short blockingConstraint) {
        if ((blockingConstraint & 0x4) != 0x0) {
            return false;
        }
        short devMethod = 0;
        short blockConstraint = blockingConstraint;
        XSTypeDecl type = element.fType;
        if (type.getTypeCategory() == 13) {
            devMethod = ((XSComplexTypeDecl)type).fDerivedBy;
        }
        else {
            devMethod = 2;
        }
        type = exemplar.fType;
        if (type.getTypeCategory() == 13) {
            blockConstraint |= ((XSComplexTypeDecl)type).fBlock;
        }
        XSElementDecl subGroup;
        for (subGroup = element.fSubGroup; subGroup != null && subGroup != exemplar; subGroup = subGroup.fSubGroup) {
            type = subGroup.fType;
            if (type.getTypeCategory() == 13) {
                devMethod |= ((XSComplexTypeDecl)type).fDerivedBy;
                blockConstraint |= ((XSComplexTypeDecl)type).fBlock;
            }
            else {
                devMethod |= 0x2;
            }
        }
        return subGroup != null && (devMethod & blockConstraint) == 0x0;
    }
    
    public boolean inSubstitutionGroup(XSElementDecl element, final XSElementDecl exemplar) {
        while (element != null && element != exemplar) {
            element = element.fSubGroup;
        }
        return element != null;
    }
    
    public void reset() {
        this.fSubGroups.clear();
    }
    
    public void addSubstitutionGroup(final XSElementDecl[] elements) {
        for (int i = elements.length - 1; i >= 0; --i) {
            final XSElementDecl element = elements[i];
            final XSElementDecl subHead = element.fSubGroup;
            Vector subGroup = this.fSubGroups.get(subHead);
            if (subGroup == null) {
                subGroup = new Vector();
                this.fSubGroups.put(subHead, subGroup);
            }
            subGroup.addElement(element);
        }
    }
    
    public XSElementDecl[] getSubstitutionGroup(final XSElementDecl element) {
        final Object subGroup = this.fSubGroups.get(element);
        XSElementDecl[] ret;
        if (subGroup == null) {
            ret = SubstitutionGroupHandler.EMPTY_VECTOR;
            this.fSubGroups.put(element, ret);
        }
        else if (subGroup instanceof XSElementDecl[]) {
            ret = (XSElementDecl[])subGroup;
        }
        else {
            final Vector group = (Vector)subGroup;
            for (int i = group.size() - 1; i >= 0; --i) {
                final XSElementDecl[] group2 = this.getSubstitutionGroup(group.elementAt(i));
                for (int j = group2.length - 1; j >= 0; --j) {
                    group.addElement(group2[j]);
                }
            }
            ret = new XSElementDecl[group.size()];
            for (int k = group.size() - 1; k >= 0; --k) {
                ret[k] = group.elementAt(k);
            }
            this.fSubGroups.put(element, ret);
        }
        return ret;
    }
    
    static {
        EMPTY_VECTOR = new XSElementDecl[0];
    }
}
