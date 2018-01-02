// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import java.util.Vector;
import org.apache.xerces.xs.XSTypeDefinition;
import org.apache.xerces.xni.QName;
import java.util.Hashtable;

public class SubstitutionGroupHandler
{
    private static final XSElementDecl[] EMPTY_GROUP;
    XSGrammarBucket fGrammarBucket;
    Hashtable fSubGroupsB;
    private static final OneSubGroup[] EMPTY_VECTOR;
    Hashtable fSubGroups;
    
    public SubstitutionGroupHandler(final XSGrammarBucket fGrammarBucket) {
        this.fSubGroupsB = new Hashtable();
        this.fSubGroups = new Hashtable();
        this.fGrammarBucket = fGrammarBucket;
    }
    
    public XSElementDecl getMatchingElemDecl(final QName qName, final XSElementDecl xsElementDecl) {
        if (qName.localpart == xsElementDecl.fName && qName.uri == xsElementDecl.fTargetNamespace) {
            return xsElementDecl;
        }
        if (xsElementDecl.fScope != 1) {
            return null;
        }
        if ((xsElementDecl.fBlock & 0x4) != 0x0) {
            return null;
        }
        final SchemaGrammar grammar = this.fGrammarBucket.getGrammar(qName.uri);
        if (grammar == null) {
            return null;
        }
        final XSElementDecl globalElementDecl = grammar.getGlobalElementDecl(qName.localpart);
        if (globalElementDecl == null) {
            return null;
        }
        if (this.substitutionGroupOK(globalElementDecl, xsElementDecl, xsElementDecl.fBlock)) {
            return globalElementDecl;
        }
        return null;
    }
    
    protected boolean substitutionGroupOK(final XSElementDecl xsElementDecl, final XSElementDecl xsElementDecl2, final short n) {
        if (xsElementDecl == xsElementDecl2) {
            return true;
        }
        if ((n & 0x4) != 0x0) {
            return false;
        }
        XSElementDecl xsElementDecl3;
        for (xsElementDecl3 = xsElementDecl.fSubGroup; xsElementDecl3 != null && xsElementDecl3 != xsElementDecl2; xsElementDecl3 = xsElementDecl3.fSubGroup) {}
        if (xsElementDecl3 == null) {
            return false;
        }
        short n2 = 0;
        short n3 = n;
        XSTypeDefinition xsTypeDefinition = xsElementDecl.fType;
        while (xsTypeDefinition != xsElementDecl2.fType && xsTypeDefinition != SchemaGrammar.fAnyType) {
            if (xsTypeDefinition.getTypeCategory() == 15) {
                n2 |= ((XSComplexTypeDecl)xsTypeDefinition).fDerivedBy;
            }
            else {
                n2 |= 0x2;
            }
            xsTypeDefinition = xsTypeDefinition.getBaseType();
            if (xsTypeDefinition == null) {
                xsTypeDefinition = SchemaGrammar.fAnyType;
            }
            if (xsTypeDefinition.getTypeCategory() == 15) {
                n3 |= ((XSComplexTypeDecl)xsTypeDefinition).fBlock;
            }
        }
        return xsTypeDefinition == xsElementDecl2.fType && (n2 & n3) == 0x0;
    }
    
    public boolean inSubstitutionGroup(final XSElementDecl xsElementDecl, final XSElementDecl xsElementDecl2) {
        return this.substitutionGroupOK(xsElementDecl, xsElementDecl2, xsElementDecl2.fBlock);
    }
    
    public void reset() {
        this.fSubGroupsB.clear();
        this.fSubGroups.clear();
    }
    
    public void addSubstitutionGroup(final XSElementDecl[] array) {
        for (int i = array.length - 1; i >= 0; --i) {
            final XSElementDecl xsElementDecl = array[i];
            final XSElementDecl fSubGroup = xsElementDecl.fSubGroup;
            Vector<XSElementDecl> vector = this.fSubGroupsB.get(fSubGroup);
            if (vector == null) {
                vector = new Vector<XSElementDecl>();
                this.fSubGroupsB.put(fSubGroup, vector);
            }
            vector.addElement(xsElementDecl);
        }
    }
    
    public XSElementDecl[] getSubstitutionGroup(final XSElementDecl xsElementDecl) {
        final XSElementDecl[] value = this.fSubGroups.get(xsElementDecl);
        if (value != null) {
            return value;
        }
        if ((xsElementDecl.fBlock & 0x4) != 0x0) {
            this.fSubGroups.put(xsElementDecl, SubstitutionGroupHandler.EMPTY_GROUP);
            return SubstitutionGroupHandler.EMPTY_GROUP;
        }
        final OneSubGroup[] subGroupB = this.getSubGroupB(xsElementDecl, new OneSubGroup());
        final int length = subGroupB.length;
        int n = 0;
        XSElementDecl[] array = new XSElementDecl[length];
        for (int i = 0; i < length; ++i) {
            if ((xsElementDecl.fBlock & subGroupB[i].dMethod) == 0x0) {
                array[n++] = subGroupB[i].sub;
            }
        }
        if (n < length) {
            final XSElementDecl[] array2 = new XSElementDecl[n];
            System.arraycopy(array, 0, array2, 0, n);
            array = array2;
        }
        this.fSubGroups.put(xsElementDecl, array);
        return array;
    }
    
    private OneSubGroup[] getSubGroupB(final XSElementDecl xsElementDecl, final OneSubGroup oneSubGroup) {
        final Vector<XSElementDecl> value = this.fSubGroupsB.get(xsElementDecl);
        if (value == null) {
            this.fSubGroupsB.put(xsElementDecl, SubstitutionGroupHandler.EMPTY_VECTOR);
            return SubstitutionGroupHandler.EMPTY_VECTOR;
        }
        if (value instanceof OneSubGroup[]) {
            return (OneSubGroup[])(Object)value;
        }
        final Vector<XSElementDecl> vector = value;
        final Vector vector2 = new Vector<OneSubGroup>();
        for (int i = vector.size() - 1; i >= 0; --i) {
            final XSElementDecl xsElementDecl2 = vector.elementAt(i);
            if (this.getDBMethods(xsElementDecl2.fType, xsElementDecl.fType, oneSubGroup)) {
                final short dMethod = oneSubGroup.dMethod;
                final short bMethod = oneSubGroup.bMethod;
                vector2.addElement(new OneSubGroup(xsElementDecl2, oneSubGroup.dMethod, oneSubGroup.bMethod));
                final OneSubGroup[] subGroupB = this.getSubGroupB(xsElementDecl2, oneSubGroup);
                for (int j = subGroupB.length - 1; j >= 0; --j) {
                    final short n = (short)(dMethod | subGroupB[j].dMethod);
                    final short n2 = (short)(bMethod | subGroupB[j].bMethod);
                    if ((n & n2) == 0x0) {
                        vector2.addElement(new OneSubGroup(subGroupB[j].sub, n, n2));
                    }
                }
            }
        }
        final OneSubGroup[] array = new OneSubGroup[vector2.size()];
        for (int k = vector2.size() - 1; k >= 0; --k) {
            array[k] = vector2.elementAt(k);
        }
        this.fSubGroupsB.put(xsElementDecl, array);
        return array;
    }
    
    private boolean getDBMethods(XSTypeDefinition xsTypeDefinition, final XSTypeDefinition xsTypeDefinition2, final OneSubGroup oneSubGroup) {
        short dMethod = 0;
        short bMethod = 0;
        while (xsTypeDefinition != xsTypeDefinition2 && xsTypeDefinition != SchemaGrammar.fAnyType) {
            if (xsTypeDefinition.getTypeCategory() == 15) {
                dMethod |= ((XSComplexTypeDecl)xsTypeDefinition).fDerivedBy;
            }
            else {
                dMethod |= 0x2;
            }
            xsTypeDefinition = xsTypeDefinition.getBaseType();
            if (xsTypeDefinition == null) {
                xsTypeDefinition = SchemaGrammar.fAnyType;
            }
            if (xsTypeDefinition.getTypeCategory() == 15) {
                bMethod |= ((XSComplexTypeDecl)xsTypeDefinition).fBlock;
            }
        }
        if (xsTypeDefinition != xsTypeDefinition2 || (dMethod & bMethod) != 0x0) {
            return false;
        }
        oneSubGroup.dMethod = dMethod;
        oneSubGroup.bMethod = bMethod;
        return true;
    }
    
    static {
        EMPTY_GROUP = new XSElementDecl[0];
        EMPTY_VECTOR = new OneSubGroup[0];
    }
    
    private static final class OneSubGroup
    {
        XSElementDecl sub;
        short dMethod;
        short bMethod;
        
        OneSubGroup() {
        }
        
        OneSubGroup(final XSElementDecl sub, final short dMethod, final short bMethod) {
            this.sub = sub;
            this.dMethod = dMethod;
            this.bMethod = bMethod;
        }
    }
}
