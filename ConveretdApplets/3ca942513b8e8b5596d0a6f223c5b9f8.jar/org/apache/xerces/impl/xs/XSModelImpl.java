// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xs.XSObjectList;
import org.apache.xerces.xs.XSNotationDeclaration;
import org.apache.xerces.xs.XSModelGroupDefinition;
import org.apache.xerces.xs.XSAttributeGroupDefinition;
import org.apache.xerces.xs.XSElementDeclaration;
import org.apache.xerces.xs.XSAttributeDeclaration;
import org.apache.xerces.xs.XSTypeDefinition;
import org.apache.xerces.impl.xs.util.XSNamedMap4Types;
import org.apache.xerces.impl.xs.util.XSNamedMapImpl;
import org.apache.xerces.xs.XSNamespaceItem;
import org.apache.xerces.impl.xs.util.NSItemListImpl;
import org.apache.xerces.xs.XSNamespaceItemList;
import org.apache.xerces.impl.xs.util.StringListImpl;
import org.apache.xerces.xs.StringList;
import org.apache.xerces.xs.XSObject;
import java.util.Vector;
import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import org.apache.xerces.xs.XSNamedMap;
import org.apache.xerces.util.SymbolHash;
import org.apache.xerces.xs.XSModel;

public class XSModelImpl implements XSModel
{
    private static final short MAX_COMP_IDX = 16;
    private static final boolean[] GLOBAL_COMP;
    private int fGrammarCount;
    private String[] fNamespaces;
    private SchemaGrammar[] fGrammarList;
    private SymbolHash fGrammarMap;
    private SymbolHash fSubGroupMap;
    private XSNamedMap[] fGlobalComponents;
    private XSNamedMap[][] fNSComponents;
    private XSObjectListImpl fAnnotations;
    private boolean fHasIDC;
    
    public XSModelImpl(final SchemaGrammar[] array) {
        this.fAnnotations = null;
        this.fHasIDC = false;
        int length = array.length;
        this.fNamespaces = new String[Math.max(length + 1, 5)];
        this.fGrammarList = new SchemaGrammar[Math.max(length + 1, 5)];
        boolean b = false;
        for (int i = 0; i < length; ++i) {
            this.fNamespaces[i] = array[i].getTargetNamespace();
            this.fGrammarList[i] = array[i];
            if (this.fNamespaces[i] == SchemaSymbols.URI_SCHEMAFORSCHEMA) {
                b = true;
            }
        }
        if (!b) {
            this.fNamespaces[length] = SchemaSymbols.URI_SCHEMAFORSCHEMA;
            this.fGrammarList[length++] = SchemaGrammar.SG_SchemaNS;
        }
        for (int j = 0; j < length; ++j) {
            final Vector importedGrammars = this.fGrammarList[j].getImportedGrammars();
            for (int k = (importedGrammars == null) ? -1 : (importedGrammars.size() - 1); k >= 0; --k) {
                SchemaGrammar schemaGrammar;
                int n;
                for (schemaGrammar = importedGrammars.elementAt(k), n = 0; n < length && schemaGrammar != this.fGrammarList[n]; ++n) {}
                if (n == length) {
                    if (length == this.fGrammarList.length) {
                        final String[] fNamespaces = new String[length * 2];
                        System.arraycopy(this.fNamespaces, 0, fNamespaces, 0, length);
                        this.fNamespaces = fNamespaces;
                        final SchemaGrammar[] fGrammarList = new SchemaGrammar[length * 2];
                        System.arraycopy(this.fGrammarList, 0, fGrammarList, 0, length);
                        this.fGrammarList = fGrammarList;
                    }
                    this.fNamespaces[length] = schemaGrammar.getTargetNamespace();
                    this.fGrammarList[length] = schemaGrammar;
                    ++length;
                }
            }
        }
        this.fGrammarMap = new SymbolHash(length * 2);
        for (int l = 0; l < length; ++l) {
            this.fGrammarMap.put(null2EmptyString(this.fNamespaces[l]), this.fGrammarList[l]);
            if (this.fGrammarList[l].hasIDConstraints()) {
                this.fHasIDC = true;
            }
        }
        this.fGrammarCount = length;
        this.fGlobalComponents = new XSNamedMap[17];
        this.fNSComponents = new XSNamedMap[length][17];
        this.buildSubGroups();
    }
    
    private void buildSubGroups() {
        final SubstitutionGroupHandler substitutionGroupHandler = new SubstitutionGroupHandler(null);
        for (int i = 0; i < this.fGrammarCount; ++i) {
            substitutionGroupHandler.addSubstitutionGroup(this.fGrammarList[i].getSubstitutionGroups());
        }
        final XSNamedMap components = this.getComponents((short)2);
        final int length = components.getLength();
        this.fSubGroupMap = new SymbolHash(length * 2);
        for (int j = 0; j < length; ++j) {
            final XSElementDecl xsElementDecl = (XSElementDecl)components.item(j);
            final XSElementDecl[] substitutionGroup = substitutionGroupHandler.getSubstitutionGroup(xsElementDecl);
            this.fSubGroupMap.put(xsElementDecl, (substitutionGroup.length > 0) ? new XSObjectListImpl(substitutionGroup, substitutionGroup.length) : XSObjectListImpl.EMPTY_LIST);
        }
    }
    
    public StringList getNamespaces() {
        return new StringListImpl(this.fNamespaces, this.fGrammarCount);
    }
    
    public XSNamespaceItemList getNamespaceItems() {
        return new NSItemListImpl(this.fGrammarList, this.fGrammarCount);
    }
    
    public synchronized XSNamedMap getComponents(final short n) {
        if (n <= 0 || n > 16 || !XSModelImpl.GLOBAL_COMP[n]) {
            return XSNamedMapImpl.EMPTY_MAP;
        }
        final SymbolHash[] array = new SymbolHash[this.fGrammarCount];
        if (this.fGlobalComponents[n] == null) {
            for (int i = 0; i < this.fGrammarCount; ++i) {
                switch (n) {
                    case 3:
                    case 15:
                    case 16: {
                        array[i] = this.fGrammarList[i].fGlobalTypeDecls;
                        break;
                    }
                    case 1: {
                        array[i] = this.fGrammarList[i].fGlobalAttrDecls;
                        break;
                    }
                    case 2: {
                        array[i] = this.fGrammarList[i].fGlobalElemDecls;
                        break;
                    }
                    case 5: {
                        array[i] = this.fGrammarList[i].fGlobalAttrGrpDecls;
                        break;
                    }
                    case 6: {
                        array[i] = this.fGrammarList[i].fGlobalGroupDecls;
                        break;
                    }
                    case 11: {
                        array[i] = this.fGrammarList[i].fGlobalNotationDecls;
                        break;
                    }
                }
            }
            if (n == 15 || n == 16) {
                this.fGlobalComponents[n] = new XSNamedMap4Types(this.fNamespaces, array, this.fGrammarCount, n);
            }
            else {
                this.fGlobalComponents[n] = new XSNamedMapImpl(this.fNamespaces, array, this.fGrammarCount);
            }
        }
        return this.fGlobalComponents[n];
    }
    
    public synchronized XSNamedMap getComponentsByNamespace(final short n, final String s) {
        if (n <= 0 || n > 16 || !XSModelImpl.GLOBAL_COMP[n]) {
            return XSNamedMapImpl.EMPTY_MAP;
        }
        int i = 0;
        if (s != null) {
            while (i < this.fGrammarCount) {
                if (s.equals(this.fNamespaces[i])) {
                    break;
                }
                ++i;
            }
        }
        else {
            while (i < this.fGrammarCount) {
                if (this.fNamespaces[i] == null) {
                    break;
                }
                ++i;
            }
        }
        if (i == this.fGrammarCount) {
            return XSNamedMapImpl.EMPTY_MAP;
        }
        if (this.fNSComponents[i][n] == null) {
            SymbolHash symbolHash = null;
            switch (n) {
                case 3:
                case 15:
                case 16: {
                    symbolHash = this.fGrammarList[i].fGlobalTypeDecls;
                    break;
                }
                case 1: {
                    symbolHash = this.fGrammarList[i].fGlobalAttrDecls;
                    break;
                }
                case 2: {
                    symbolHash = this.fGrammarList[i].fGlobalElemDecls;
                    break;
                }
                case 5: {
                    symbolHash = this.fGrammarList[i].fGlobalAttrGrpDecls;
                    break;
                }
                case 6: {
                    symbolHash = this.fGrammarList[i].fGlobalGroupDecls;
                    break;
                }
                case 11: {
                    symbolHash = this.fGrammarList[i].fGlobalNotationDecls;
                    break;
                }
            }
            if (n == 15 || n == 16) {
                this.fNSComponents[i][n] = new XSNamedMap4Types(s, symbolHash, n);
            }
            else {
                this.fNSComponents[i][n] = new XSNamedMapImpl(s, symbolHash);
            }
        }
        return this.fNSComponents[i][n];
    }
    
    public XSTypeDefinition getTypeDefinition(final String s, final String s2) {
        final SchemaGrammar schemaGrammar = (SchemaGrammar)this.fGrammarMap.get(null2EmptyString(s2));
        if (schemaGrammar == null) {
            return null;
        }
        return (XSTypeDefinition)schemaGrammar.fGlobalTypeDecls.get(s);
    }
    
    public XSAttributeDeclaration getAttributeDeclaration(final String s, final String s2) {
        final SchemaGrammar schemaGrammar = (SchemaGrammar)this.fGrammarMap.get(null2EmptyString(s2));
        if (schemaGrammar == null) {
            return null;
        }
        return (XSAttributeDeclaration)schemaGrammar.fGlobalAttrDecls.get(s);
    }
    
    public XSElementDeclaration getElementDeclaration(final String s, final String s2) {
        final SchemaGrammar schemaGrammar = (SchemaGrammar)this.fGrammarMap.get(null2EmptyString(s2));
        if (schemaGrammar == null) {
            return null;
        }
        return (XSElementDeclaration)schemaGrammar.fGlobalElemDecls.get(s);
    }
    
    public XSAttributeGroupDefinition getAttributeGroup(final String s, final String s2) {
        final SchemaGrammar schemaGrammar = (SchemaGrammar)this.fGrammarMap.get(null2EmptyString(s2));
        if (schemaGrammar == null) {
            return null;
        }
        return (XSAttributeGroupDefinition)schemaGrammar.fGlobalAttrGrpDecls.get(s);
    }
    
    public XSModelGroupDefinition getModelGroupDefinition(final String s, final String s2) {
        final SchemaGrammar schemaGrammar = (SchemaGrammar)this.fGrammarMap.get(null2EmptyString(s2));
        if (schemaGrammar == null) {
            return null;
        }
        return (XSModelGroupDefinition)schemaGrammar.fGlobalGroupDecls.get(s);
    }
    
    public XSNotationDeclaration getNotationDeclaration(final String s, final String s2) {
        final SchemaGrammar schemaGrammar = (SchemaGrammar)this.fGrammarMap.get(null2EmptyString(s2));
        if (schemaGrammar == null) {
            return null;
        }
        return (XSNotationDeclaration)schemaGrammar.fGlobalNotationDecls.get(s);
    }
    
    public synchronized XSObjectList getAnnotations() {
        if (this.fAnnotations != null) {
            return this.fAnnotations;
        }
        int n = 0;
        for (int i = 0; i < this.fGrammarCount; ++i) {
            n += this.fGrammarList[i].fNumAnnotations;
        }
        final XSAnnotationImpl[] array = new XSAnnotationImpl[n];
        int n2 = 0;
        for (int j = 0; j < this.fGrammarCount; ++j) {
            final SchemaGrammar schemaGrammar = this.fGrammarList[j];
            if (schemaGrammar.fNumAnnotations > 0) {
                System.arraycopy(schemaGrammar.fAnnotations, 0, array, n2, schemaGrammar.fNumAnnotations);
                n2 += schemaGrammar.fNumAnnotations;
            }
        }
        return this.fAnnotations = new XSObjectListImpl(array, array.length);
    }
    
    private static final String null2EmptyString(final String s) {
        return (s == null) ? XMLSymbols.EMPTY_STRING : s;
    }
    
    public boolean hasIDConstraints() {
        return this.fHasIDC;
    }
    
    public XSObjectList getSubstitutionGroup(final XSElementDeclaration xsElementDeclaration) {
        return (XSObjectList)this.fSubGroupMap.get(xsElementDeclaration);
    }
    
    static {
        GLOBAL_COMP = new boolean[] { false, true, true, true, false, true, true, false, false, false, false, true, false, false, false, true, true };
    }
}
