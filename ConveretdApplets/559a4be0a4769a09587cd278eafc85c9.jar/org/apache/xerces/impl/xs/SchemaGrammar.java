// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.impl.xs.psvi.XSParticle;
import org.apache.xerces.impl.xs.psvi.XSWildcard;
import org.apache.xerces.impl.xs.psvi.XSObject;
import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import org.apache.xerces.impl.xs.psvi.XSObjectList;
import org.apache.xerces.impl.dv.SchemaDVFactory;
import org.apache.xerces.impl.xs.psvi.XSModel;
import org.apache.xerces.impl.xs.util.StringListImpl;
import org.apache.xerces.impl.xs.psvi.StringList;
import org.apache.xerces.impl.xs.util.ObjectListImpl;
import org.apache.xerces.impl.xs.psvi.ObjectList;
import org.apache.xerces.impl.xs.psvi.XSNotationDeclaration;
import org.apache.xerces.impl.xs.psvi.XSModelGroupDefinition;
import org.apache.xerces.impl.xs.psvi.XSAttributeGroupDefinition;
import org.apache.xerces.impl.xs.psvi.XSElementDeclaration;
import org.apache.xerces.impl.xs.psvi.XSAttributeDeclaration;
import org.apache.xerces.impl.xs.psvi.XSTypeDefinition;
import org.apache.xerces.impl.xs.util.XSNamedMapImpl;
import org.apache.xerces.impl.xs.util.XSNamedMap4Types;
import org.apache.xerces.impl.xs.identity.IdentityConstraint;
import org.apache.xerces.xni.grammars.XMLGrammarDescription;
import org.apache.xerces.impl.xs.psvi.XSNamedMap;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.impl.xs.util.SimpleLocator;
import java.util.Vector;
import org.apache.xerces.util.SymbolHash;
import org.apache.xerces.impl.xs.psvi.XSNamespaceItem;
import org.apache.xerces.xni.grammars.XSGrammar;
import org.apache.xerces.xni.grammars.Grammar;

public class SchemaGrammar implements Grammar, XSGrammar, XSNamespaceItem
{
    String fTargetNamespace;
    SymbolHash fGlobalAttrDecls;
    SymbolHash fGlobalAttrGrpDecls;
    SymbolHash fGlobalElemDecls;
    SymbolHash fGlobalGroupDecls;
    SymbolHash fGlobalNotationDecls;
    SymbolHash fGlobalIDConstraintDecls;
    SymbolHash fGlobalTypeDecls;
    XSDDescription fGrammarDescription;
    private static final int BASICSET_COUNT = 29;
    private static final int FULLSET_COUNT = 46;
    private static final int GRAMMAR_XS = 1;
    private static final int GRAMMAR_XSI = 2;
    Vector fImported;
    private static final int INITIAL_SIZE = 16;
    private static final int INC_SIZE = 16;
    private int fCTCount;
    private XSComplexTypeDecl[] fComplexTypeDecls;
    private SimpleLocator[] fCTLocators;
    private static final int REDEFINED_GROUP_INIT_SIZE = 2;
    private int fRGCount;
    private XSGroupDecl[] fRedefinedGroupDecls;
    private SimpleLocator[] fRGLocators;
    boolean fFullChecked;
    private int fSubGroupCount;
    private XSElementDecl[] fSubGroups;
    public static final XSComplexTypeDecl fAnyType;
    public static final BuiltinSchemaGrammar SG_SchemaNS;
    public static final XSSimpleType fAnySimpleType;
    public static final BuiltinSchemaGrammar SG_XSI;
    private static final short MAX_COMP_IDX = 14;
    private static final boolean[] GLOBAL_COMP;
    private XSNamedMap[] fComponents;
    private Vector fDocuments;
    private Vector fLocations;
    
    private SchemaGrammar() {
        this.fGrammarDescription = null;
        this.fImported = null;
        this.fCTCount = 0;
        this.fComplexTypeDecls = new XSComplexTypeDecl[16];
        this.fCTLocators = new SimpleLocator[16];
        this.fRGCount = 0;
        this.fRedefinedGroupDecls = new XSGroupDecl[2];
        this.fRGLocators = new SimpleLocator[1];
        this.fFullChecked = false;
        this.fSubGroupCount = 0;
        this.fSubGroups = new XSElementDecl[16];
        this.fComponents = null;
        this.fDocuments = null;
        this.fLocations = null;
    }
    
    public SchemaGrammar(final String targetNamespace, final XSDDescription grammarDesc) {
        this.fGrammarDescription = null;
        this.fImported = null;
        this.fCTCount = 0;
        this.fComplexTypeDecls = new XSComplexTypeDecl[16];
        this.fCTLocators = new SimpleLocator[16];
        this.fRGCount = 0;
        this.fRedefinedGroupDecls = new XSGroupDecl[2];
        this.fRGLocators = new SimpleLocator[1];
        this.fFullChecked = false;
        this.fSubGroupCount = 0;
        this.fSubGroups = new XSElementDecl[16];
        this.fComponents = null;
        this.fDocuments = null;
        this.fLocations = null;
        this.fTargetNamespace = targetNamespace;
        this.fGrammarDescription = grammarDesc;
        this.fGlobalAttrDecls = new SymbolHash();
        this.fGlobalAttrGrpDecls = new SymbolHash();
        this.fGlobalElemDecls = new SymbolHash();
        this.fGlobalGroupDecls = new SymbolHash();
        this.fGlobalNotationDecls = new SymbolHash();
        this.fGlobalTypeDecls = new SymbolHash();
        this.fGlobalIDConstraintDecls = new SymbolHash();
    }
    
    public XMLGrammarDescription getGrammarDescription() {
        return this.fGrammarDescription;
    }
    
    public boolean isNamespaceAware() {
        return true;
    }
    
    public void setImportedGrammars(final Vector importedGrammars) {
        this.fImported = importedGrammars;
    }
    
    public Vector getImportedGrammars() {
        return this.fImported;
    }
    
    public final String getTargetNamespace() {
        return this.fTargetNamespace;
    }
    
    public void addGlobalAttributeDecl(final XSAttributeDecl decl) {
        this.fGlobalAttrDecls.put(decl.fName, decl);
    }
    
    public void addGlobalAttributeGroupDecl(final XSAttributeGroupDecl decl) {
        this.fGlobalAttrGrpDecls.put(decl.fName, decl);
    }
    
    public void addGlobalElementDecl(final XSElementDecl decl) {
        this.fGlobalElemDecls.put(decl.fName, decl);
        if (decl.fSubGroup != null) {
            if (this.fSubGroupCount == this.fSubGroups.length) {
                this.fSubGroups = resize(this.fSubGroups, this.fSubGroupCount + 16);
            }
            this.fSubGroups[this.fSubGroupCount++] = decl;
        }
    }
    
    public void addGlobalGroupDecl(final XSGroupDecl decl) {
        this.fGlobalGroupDecls.put(decl.fName, decl);
    }
    
    public void addGlobalNotationDecl(final XSNotationDecl decl) {
        this.fGlobalNotationDecls.put(decl.fName, decl);
    }
    
    public void addGlobalTypeDecl(final XSTypeDecl decl) {
        this.fGlobalTypeDecls.put(decl.getName(), decl);
    }
    
    public final void addIDConstraintDecl(final XSElementDecl elmDecl, final IdentityConstraint decl) {
        elmDecl.addIDConstaint(decl);
        this.fGlobalIDConstraintDecls.put(decl.getIdentityConstraintName(), decl);
    }
    
    public final XSAttributeDecl getGlobalAttributeDecl(final String declName) {
        return (XSAttributeDecl)this.fGlobalAttrDecls.get(declName);
    }
    
    public final XSAttributeGroupDecl getGlobalAttributeGroupDecl(final String declName) {
        return (XSAttributeGroupDecl)this.fGlobalAttrGrpDecls.get(declName);
    }
    
    public final XSElementDecl getGlobalElementDecl(final String declName) {
        return (XSElementDecl)this.fGlobalElemDecls.get(declName);
    }
    
    public final XSGroupDecl getGlobalGroupDecl(final String declName) {
        return (XSGroupDecl)this.fGlobalGroupDecls.get(declName);
    }
    
    public final XSNotationDecl getGlobalNotationDecl(final String declName) {
        return (XSNotationDecl)this.fGlobalNotationDecls.get(declName);
    }
    
    public final XSTypeDecl getGlobalTypeDecl(final String declName) {
        return (XSTypeDecl)this.fGlobalTypeDecls.get(declName);
    }
    
    public final IdentityConstraint getIDConstraintDecl(final String declName) {
        return (IdentityConstraint)this.fGlobalIDConstraintDecls.get(declName);
    }
    
    public void addComplexTypeDecl(final XSComplexTypeDecl decl, final SimpleLocator locator) {
        if (this.fCTCount == this.fComplexTypeDecls.length) {
            this.fComplexTypeDecls = resize(this.fComplexTypeDecls, this.fCTCount + 16);
            this.fCTLocators = resize(this.fCTLocators, this.fCTCount + 16);
        }
        this.fCTLocators[this.fCTCount] = locator;
        this.fComplexTypeDecls[this.fCTCount++] = decl;
    }
    
    public void addRedefinedGroupDecl(final XSGroupDecl derived, final XSGroupDecl base, final SimpleLocator locator) {
        if (this.fRGCount == this.fRedefinedGroupDecls.length) {
            this.fRedefinedGroupDecls = resize(this.fRedefinedGroupDecls, this.fRGCount << 1);
            this.fRGLocators = resize(this.fRGLocators, this.fRGCount);
        }
        this.fRGLocators[this.fCTCount / 2] = locator;
        this.fRedefinedGroupDecls[this.fRGCount++] = derived;
        this.fRedefinedGroupDecls[this.fRGCount++] = base;
    }
    
    final XSComplexTypeDecl[] getUncheckedComplexTypeDecls() {
        if (this.fCTCount < this.fComplexTypeDecls.length) {
            this.fComplexTypeDecls = resize(this.fComplexTypeDecls, this.fCTCount);
            this.fCTLocators = resize(this.fCTLocators, this.fCTCount);
        }
        return this.fComplexTypeDecls;
    }
    
    final SimpleLocator[] getUncheckedCTLocators() {
        if (this.fCTCount < this.fCTLocators.length) {
            this.fComplexTypeDecls = resize(this.fComplexTypeDecls, this.fCTCount);
            this.fCTLocators = resize(this.fCTLocators, this.fCTCount);
        }
        return this.fCTLocators;
    }
    
    final XSGroupDecl[] getRedefinedGroupDecls() {
        if (this.fRGCount < this.fRedefinedGroupDecls.length) {
            this.fRedefinedGroupDecls = resize(this.fRedefinedGroupDecls, this.fRGCount);
            this.fRGLocators = resize(this.fRGLocators, this.fRGCount / 2);
        }
        return this.fRedefinedGroupDecls;
    }
    
    final SimpleLocator[] getRGLocators() {
        if (this.fRGCount < this.fRedefinedGroupDecls.length) {
            this.fRedefinedGroupDecls = resize(this.fRedefinedGroupDecls, this.fRGCount);
            this.fRGLocators = resize(this.fRGLocators, this.fRGCount / 2);
        }
        return this.fRGLocators;
    }
    
    final void setUncheckedTypeNum(final int newSize) {
        this.fCTCount = newSize;
        this.fComplexTypeDecls = resize(this.fComplexTypeDecls, this.fCTCount);
        this.fCTLocators = resize(this.fCTLocators, this.fCTCount);
    }
    
    final XSElementDecl[] getSubstitutionGroups() {
        if (this.fSubGroupCount < this.fSubGroups.length) {
            this.fSubGroups = resize(this.fSubGroups, this.fSubGroupCount);
        }
        return this.fSubGroups;
    }
    
    static final XSComplexTypeDecl[] resize(final XSComplexTypeDecl[] oldArray, final int newSize) {
        final XSComplexTypeDecl[] newArray = new XSComplexTypeDecl[newSize];
        System.arraycopy(oldArray, 0, newArray, 0, Math.min(oldArray.length, newSize));
        return newArray;
    }
    
    static final XSGroupDecl[] resize(final XSGroupDecl[] oldArray, final int newSize) {
        final XSGroupDecl[] newArray = new XSGroupDecl[newSize];
        System.arraycopy(oldArray, 0, newArray, 0, Math.min(oldArray.length, newSize));
        return newArray;
    }
    
    static final XSElementDecl[] resize(final XSElementDecl[] oldArray, final int newSize) {
        final XSElementDecl[] newArray = new XSElementDecl[newSize];
        System.arraycopy(oldArray, 0, newArray, 0, Math.min(oldArray.length, newSize));
        return newArray;
    }
    
    static final SimpleLocator[] resize(final SimpleLocator[] oldArray, final int newSize) {
        final SimpleLocator[] newArray = new SimpleLocator[newSize];
        System.arraycopy(oldArray, 0, newArray, 0, Math.min(oldArray.length, newSize));
        return newArray;
    }
    
    public synchronized void addDocument(final Object document, final String location) {
        if (this.fDocuments == null) {
            this.fDocuments = new Vector();
            this.fLocations = new Vector();
        }
        this.fDocuments.addElement(document);
        this.fLocations.addElement(location);
    }
    
    public String getSchemaNamespace() {
        return this.fTargetNamespace;
    }
    
    public synchronized XSNamedMap getComponents(final short objectType) {
        if (objectType <= 0 || objectType > 14 || !SchemaGrammar.GLOBAL_COMP[objectType]) {
            return null;
        }
        if (this.fComponents == null) {
            this.fComponents = new XSNamedMap[15];
        }
        if (this.fComponents[objectType] == null) {
            SymbolHash table = null;
            switch (objectType) {
                case 3:
                case 13:
                case 14: {
                    table = this.fGlobalTypeDecls;
                    break;
                }
                case 1: {
                    table = this.fGlobalAttrDecls;
                    break;
                }
                case 2: {
                    table = this.fGlobalElemDecls;
                    break;
                }
                case 5: {
                    table = this.fGlobalAttrGrpDecls;
                    break;
                }
                case 6: {
                    table = this.fGlobalGroupDecls;
                    break;
                }
                case 11: {
                    table = this.fGlobalNotationDecls;
                    break;
                }
            }
            if (objectType == 13 || objectType == 14) {
                this.fComponents[objectType] = new XSNamedMap4Types(this.fTargetNamespace, table, objectType);
            }
            else {
                this.fComponents[objectType] = new XSNamedMapImpl(this.fTargetNamespace, table);
            }
        }
        return this.fComponents[objectType];
    }
    
    public XSTypeDefinition getTypeDefinition(final String name) {
        return this.getGlobalTypeDecl(name);
    }
    
    public XSAttributeDeclaration getAttributeDecl(final String name) {
        return this.getGlobalAttributeDecl(name);
    }
    
    public XSElementDeclaration getElementDecl(final String name) {
        return this.getGlobalElementDecl(name);
    }
    
    public XSAttributeGroupDefinition getAttributeGroup(final String name) {
        return this.getGlobalAttributeGroupDecl(name);
    }
    
    public XSModelGroupDefinition getModelGroupDefinition(final String name) {
        return this.getGlobalGroupDecl(name);
    }
    
    public XSNotationDeclaration getNotationDecl(final String name) {
        return this.getGlobalNotationDecl(name);
    }
    
    public ObjectList getDocuments() {
        return new ObjectListImpl(this.fDocuments);
    }
    
    public StringList getDocumentLocations() {
        return new StringListImpl(this.fLocations);
    }
    
    public XSModel toXSModel() {
        return new XSModelImpl(new SchemaGrammar[] { this });
    }
    
    static {
        fAnyType = new XSAnyType();
        SG_SchemaNS = new BuiltinSchemaGrammar(1);
        fAnySimpleType = (XSSimpleType)SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl("anySimpleType");
        SG_XSI = new BuiltinSchemaGrammar(2);
        GLOBAL_COMP = new boolean[] { false, true, true, true, false, true, true, false, false, false, false, true, false, true, true };
    }
    
    public static class BuiltinSchemaGrammar extends SchemaGrammar
    {
        public BuiltinSchemaGrammar(final int grammar) {
            super(null);
            final SchemaDVFactory schemaFactory = SchemaDVFactory.getInstance();
            if (grammar == 1) {
                super.fTargetNamespace = SchemaSymbols.URI_SCHEMAFORSCHEMA;
                super.fGrammarDescription = new XSDDescription();
                super.fGrammarDescription.fContextType = 3;
                super.fGrammarDescription.fTargetNamespace = SchemaSymbols.URI_SCHEMAFORSCHEMA;
                super.fGlobalAttrDecls = new SymbolHash(1);
                super.fGlobalAttrGrpDecls = new SymbolHash(1);
                super.fGlobalElemDecls = new SymbolHash(1);
                super.fGlobalGroupDecls = new SymbolHash(1);
                super.fGlobalNotationDecls = new SymbolHash(1);
                super.fGlobalIDConstraintDecls = new SymbolHash(1);
                (super.fGlobalTypeDecls = schemaFactory.getBuiltInTypes()).put(SchemaGrammar.fAnyType.getName(), SchemaGrammar.fAnyType);
            }
            else if (grammar == 2) {
                super.fTargetNamespace = SchemaSymbols.URI_XSI;
                super.fGrammarDescription = new XSDDescription();
                super.fGrammarDescription.fContextType = 3;
                super.fGrammarDescription.fTargetNamespace = SchemaSymbols.URI_XSI;
                super.fGlobalAttrGrpDecls = new SymbolHash(1);
                super.fGlobalElemDecls = new SymbolHash(1);
                super.fGlobalGroupDecls = new SymbolHash(1);
                super.fGlobalNotationDecls = new SymbolHash(1);
                super.fGlobalIDConstraintDecls = new SymbolHash(1);
                super.fGlobalTypeDecls = new SymbolHash(1);
                super.fGlobalAttrDecls = new SymbolHash(8);
                String name = null;
                String tns = null;
                XSSimpleType type = null;
                final short constraint = 0;
                final short scope = 1;
                name = SchemaSymbols.XSI_TYPE;
                tns = SchemaSymbols.URI_XSI;
                type = schemaFactory.getBuiltInType("QName");
                super.fGlobalAttrDecls.put(name, new BuiltinAttrDecl(name, tns, type, scope));
                name = SchemaSymbols.XSI_NIL;
                tns = SchemaSymbols.URI_XSI;
                type = schemaFactory.getBuiltInType("boolean");
                super.fGlobalAttrDecls.put(name, new BuiltinAttrDecl(name, tns, type, scope));
                final XSSimpleType anyURI = schemaFactory.getBuiltInType("anyURI");
                name = SchemaSymbols.XSI_SCHEMALOCATION;
                tns = SchemaSymbols.URI_XSI;
                type = schemaFactory.createTypeList(null, SchemaSymbols.URI_XSI, (short)0, anyURI);
                super.fGlobalAttrDecls.put(name, new BuiltinAttrDecl(name, tns, type, scope));
                name = SchemaSymbols.XSI_NONAMESPACESCHEMALOCATION;
                tns = SchemaSymbols.URI_XSI;
                type = anyURI;
                super.fGlobalAttrDecls.put(name, new BuiltinAttrDecl(name, tns, type, scope));
            }
        }
        
        public XMLGrammarDescription getGrammarDescription() {
            return super.fGrammarDescription.makeClone();
        }
        
        public void setImportedGrammars(final Vector importedGrammars) {
        }
        
        public void addGlobalAttributeDecl(final XSAttributeDecl decl) {
        }
        
        public void addGlobalAttributeGroupDecl(final XSAttributeGroupDecl decl) {
        }
        
        public void addGlobalElementDecl(final XSElementDecl decl) {
        }
        
        public void addGlobalGroupDecl(final XSGroupDecl decl) {
        }
        
        public void addGlobalNotationDecl(final XSNotationDecl decl) {
        }
        
        public void addGlobalTypeDecl(final XSTypeDecl decl) {
        }
        
        public void addComplexTypeDecl(final XSComplexTypeDecl decl, final SimpleLocator locator) {
        }
        
        public void addRedefinedGroupDecl(final XSGroupDecl derived, final XSGroupDecl base, final SimpleLocator locator) {
        }
        
        public synchronized void addDocument(final Object document, final String location) {
        }
    }
    
    private static class XSAnyType extends XSComplexTypeDecl
    {
        public XSAnyType() {
            super.fName = "anyType";
            super.fTargetNamespace = SchemaSymbols.URI_SCHEMAFORSCHEMA;
            super.fBaseType = this;
            super.fDerivedBy = 2;
            super.fContentType = 3;
            super.fParticle = null;
            super.fAttrGrp = null;
        }
        
        public void setValues(final String name, final String targetNamespace, final XSTypeDecl baseType, final short derivedBy, final short schemaFinal, final short block, final short contentType, final boolean isAbstract, final XSAttributeGroupDecl attrGrp, final XSSimpleType simpleType, final XSParticleDecl particle) {
        }
        
        public void setName(final String name) {
        }
        
        public void setIsAbstractType() {
        }
        
        public void setContainsTypeID() {
        }
        
        public void setIsAnonymous() {
        }
        
        public void reset() {
        }
        
        public XSObjectList getAttributeUses() {
            return new XSObjectListImpl(null, 0);
        }
        
        public XSAttributeGroupDecl getAttrGrp() {
            final XSWildcardDecl wildcard = new XSWildcardDecl();
            wildcard.fProcessContents = 3;
            final XSAttributeGroupDecl attrGrp = new XSAttributeGroupDecl();
            attrGrp.fAttributeWC = wildcard;
            return attrGrp;
        }
        
        public XSWildcard getAttributeWildcard() {
            final XSWildcardDecl wildcard = new XSWildcardDecl();
            wildcard.fProcessContents = 3;
            return wildcard;
        }
        
        public XSParticle getParticle() {
            final XSWildcardDecl wildcard = new XSWildcardDecl();
            wildcard.fProcessContents = 3;
            final XSParticleDecl particleW = new XSParticleDecl();
            particleW.fMinOccurs = 0;
            particleW.fMaxOccurs = -1;
            particleW.fType = 2;
            particleW.fValue = wildcard;
            final XSModelGroupImpl group = new XSModelGroupImpl();
            group.fCompositor = 102;
            group.fParticleCount = 1;
            (group.fParticles = new XSParticleDecl[1])[0] = particleW;
            final XSParticleDecl particleG = new XSParticleDecl();
            particleG.fType = 3;
            particleG.fValue = group;
            return particleG;
        }
    }
    
    private static class BuiltinAttrDecl extends XSAttributeDecl
    {
        public BuiltinAttrDecl(final String name, final String tns, final XSSimpleType type, final short scope) {
            super.fName = name;
            super.fTargetNamespace = tns;
            super.fType = type;
            super.fScope = scope;
        }
        
        public void setValues(final String name, final String targetNamespace, final XSSimpleType simpleType, final short constraintType, final short scope, final ValidatedInfo valInfo, final XSComplexTypeDecl enclosingCT) {
        }
        
        public void reset() {
        }
    }
}
