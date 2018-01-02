// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.impl.xs.psvi.XSObjectList;
import org.apache.xerces.impl.xs.psvi.XSNotationDeclaration;
import org.apache.xerces.impl.xs.psvi.XSModelGroupDefinition;
import org.apache.xerces.impl.xs.psvi.XSAttributeGroupDefinition;
import org.apache.xerces.impl.xs.psvi.XSElementDeclaration;
import org.apache.xerces.impl.xs.psvi.XSAttributeDeclaration;
import org.apache.xerces.impl.xs.psvi.XSTypeDefinition;
import org.apache.xerces.impl.xs.util.XSNamedMapImpl;
import org.apache.xerces.impl.xs.util.XSNamedMap4Types;
import org.apache.xerces.impl.xs.util.ObjectListImpl;
import org.apache.xerces.impl.xs.psvi.ObjectList;
import org.apache.xerces.impl.xs.util.StringListImpl;
import org.apache.xerces.impl.xs.psvi.StringList;
import java.util.Vector;
import org.apache.xerces.impl.xs.psvi.XSNamedMap;
import org.apache.xerces.util.SymbolHash;
import org.apache.xerces.impl.xs.psvi.XSModel;

public class XSModelImpl implements XSModel
{
    private static final short MAX_COMP_IDX = 14;
    private static final boolean[] GLOBAL_COMP;
    private int fGrammarCount;
    private String[] fNamespaces;
    private SchemaGrammar[] fGrammarList;
    private SymbolHash fGrammarMap;
    private XSNamedMap[] fGlobalComponents;
    private XSNamedMap[][] fNSComponents;
    private SymbolHash[] fTables;
    
    public XSModelImpl(final SchemaGrammar[] grammars) {
        int len = grammars.length;
        this.fNamespaces = new String[Math.max(len, 5)];
        this.fGrammarList = new SchemaGrammar[Math.max(len, 5)];
        for (int i = 0; i < len; ++i) {
            this.fNamespaces[i] = grammars[i].getTargetNamespace();
            this.fGrammarList[i] = grammars[i];
        }
        for (int j = 0; j < len; ++j) {
            final SchemaGrammar sg1 = this.fGrammarList[j];
            final Vector gs = sg1.getImportedGrammars();
            for (int k = (gs == null) ? -1 : (gs.size() - 1); k >= 0; --k) {
                SchemaGrammar sg2;
                int l;
                for (sg2 = gs.elementAt(k), l = 0; l < len && sg2 != this.fGrammarList[l]; ++l) {}
                if (l == len) {
                    if (len == this.fGrammarList.length) {
                        final String[] newSA = new String[len * 2];
                        System.arraycopy(this.fNamespaces, 0, newSA, 0, len);
                        this.fNamespaces = newSA;
                        final SchemaGrammar[] newGA = new SchemaGrammar[len * 2];
                        System.arraycopy(this.fGrammarList, 0, newGA, 0, len);
                        this.fGrammarList = newGA;
                    }
                    this.fNamespaces[len] = sg2.getTargetNamespace();
                    this.fGrammarList[len] = sg2;
                    ++len;
                }
            }
        }
        this.fGrammarMap = new SymbolHash(len * 2);
        for (int j = 0; j < len; ++j) {
            this.fGrammarMap.put(null2EmptyString(this.fNamespaces[j]), this.fGrammarList[j]);
        }
        this.fGrammarCount = len;
        this.fGlobalComponents = new XSNamedMap[15];
        this.fNSComponents = new XSNamedMap[len][15];
        this.fTables = new SymbolHash[this.fGrammarCount];
    }
    
    public StringList getNamespaces() {
        return new StringListImpl(this.fNamespaces, this.fGrammarCount);
    }
    
    public ObjectList getNamespaceItems() {
        return new ObjectListImpl(this.fGrammarList, this.fGrammarCount);
    }
    
    public synchronized XSNamedMap getComponents(final short objectType) {
        if (objectType <= 0 || objectType > 14 || !XSModelImpl.GLOBAL_COMP[objectType]) {
            return null;
        }
        if (this.fGlobalComponents[objectType] == null) {
            for (int i = 0; i < this.fGrammarCount; ++i) {
                switch (objectType) {
                    case 3:
                    case 13:
                    case 14: {
                        this.fTables[i] = this.fGrammarList[i].fGlobalTypeDecls;
                        break;
                    }
                    case 1: {
                        this.fTables[i] = this.fGrammarList[i].fGlobalAttrDecls;
                        break;
                    }
                    case 2: {
                        this.fTables[i] = this.fGrammarList[i].fGlobalElemDecls;
                        break;
                    }
                    case 5: {
                        this.fTables[i] = this.fGrammarList[i].fGlobalAttrGrpDecls;
                        break;
                    }
                    case 6: {
                        this.fTables[i] = this.fGrammarList[i].fGlobalGroupDecls;
                        break;
                    }
                    case 11: {
                        this.fTables[i] = this.fGrammarList[i].fGlobalNotationDecls;
                        break;
                    }
                }
            }
            if (objectType == 13 || objectType == 14) {
                this.fGlobalComponents[objectType] = new XSNamedMap4Types(this.fNamespaces, this.fTables, this.fGrammarCount, objectType);
            }
            else {
                this.fGlobalComponents[objectType] = new XSNamedMapImpl(this.fNamespaces, this.fTables, this.fGrammarCount);
            }
        }
        return this.fGlobalComponents[objectType];
    }
    
    public synchronized XSNamedMap getComponentsByNamespace(final short objectType, final String namespace) {
        if (objectType <= 0 || objectType > 14 || !XSModelImpl.GLOBAL_COMP[objectType]) {
            return null;
        }
        int i;
        for (i = 0; i < this.fGrammarCount && this.fNamespaces[i] != namespace; ++i) {}
        if (i == this.fGrammarCount) {
            return null;
        }
        if (this.fNSComponents[i][objectType] == null) {
            SymbolHash table = null;
            switch (objectType) {
                case 3:
                case 13:
                case 14: {
                    table = this.fGrammarList[i].fGlobalTypeDecls;
                    break;
                }
                case 1: {
                    table = this.fGrammarList[i].fGlobalAttrDecls;
                    break;
                }
                case 2: {
                    table = this.fGrammarList[i].fGlobalElemDecls;
                    break;
                }
                case 5: {
                    table = this.fGrammarList[i].fGlobalAttrGrpDecls;
                    break;
                }
                case 6: {
                    table = this.fGrammarList[i].fGlobalGroupDecls;
                    break;
                }
                case 11: {
                    table = this.fGrammarList[i].fGlobalNotationDecls;
                    break;
                }
            }
            if (objectType == 13 || objectType == 14) {
                this.fNSComponents[i][objectType] = new XSNamedMap4Types(namespace, table, objectType);
            }
            else {
                this.fNSComponents[i][objectType] = new XSNamedMapImpl(namespace, table);
            }
        }
        return this.fNSComponents[i][objectType];
    }
    
    public XSTypeDefinition getTypeDefinition(final String name, final String namespace) {
        final SchemaGrammar sg = (SchemaGrammar)this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return (XSTypeDefinition)sg.fGlobalTypeDecls.get(name);
    }
    
    public XSAttributeDeclaration getAttributeDecl(final String name, final String namespace) {
        final SchemaGrammar sg = (SchemaGrammar)this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return (XSAttributeDeclaration)sg.fGlobalAttrDecls.get(name);
    }
    
    public XSElementDeclaration getElementDecl(final String name, final String namespace) {
        final SchemaGrammar sg = (SchemaGrammar)this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return (XSElementDeclaration)sg.fGlobalElemDecls.get(name);
    }
    
    public XSAttributeGroupDefinition getAttributeGroup(final String name, final String namespace) {
        final SchemaGrammar sg = (SchemaGrammar)this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return (XSAttributeGroupDefinition)sg.fGlobalAttrGrpDecls.get(name);
    }
    
    public XSModelGroupDefinition getModelGroupDefinition(final String name, final String namespace) {
        final SchemaGrammar sg = (SchemaGrammar)this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return (XSModelGroupDefinition)sg.fGlobalGroupDecls.get(name);
    }
    
    public XSNotationDeclaration getNotationDecl(final String name, final String namespace) {
        final SchemaGrammar sg = (SchemaGrammar)this.fGrammarMap.get(null2EmptyString(namespace));
        if (sg == null) {
            return null;
        }
        return (XSNotationDeclaration)sg.fGlobalNotationDecls.get(name);
    }
    
    public XSObjectList getAnnotations() {
        return null;
    }
    
    private static final String null2EmptyString(final String str) {
        return (str == null) ? XMLSymbols.EMPTY_STRING : str;
    }
    
    static {
        GLOBAL_COMP = new boolean[] { false, true, true, true, false, true, true, false, false, false, false, true, false, true, true };
    }
}
