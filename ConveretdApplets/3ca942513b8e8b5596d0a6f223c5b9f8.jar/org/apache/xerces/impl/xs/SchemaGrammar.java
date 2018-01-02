// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.impl.dv.SchemaDVFactory;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xs.XSParticle;
import org.apache.xerces.xs.XSWildcard;
import org.apache.xerces.xs.XSAnnotation;
import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.xs.XSObject;
import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import org.apache.xerces.xs.XSObjectList;
import org.apache.xerces.xs.XSModel;
import org.apache.xerces.impl.xs.util.StringListImpl;
import org.apache.xerces.xs.StringList;
import org.apache.xerces.xs.XSNotationDeclaration;
import org.apache.xerces.xs.XSModelGroupDefinition;
import org.apache.xerces.xs.XSAttributeGroupDefinition;
import org.apache.xerces.xs.XSElementDeclaration;
import org.apache.xerces.xs.XSAttributeDeclaration;
import org.apache.xerces.impl.xs.util.XSNamedMap4Types;
import org.apache.xerces.impl.xs.util.XSNamedMapImpl;
import org.apache.xerces.parsers.SAXParser;
import org.xml.sax.SAXException;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.apache.xerces.parsers.XML11Configuration;
import org.apache.xerces.parsers.DOMParser;
import org.apache.xerces.impl.xs.identity.IdentityConstraint;
import org.apache.xerces.xs.XSTypeDefinition;
import org.apache.xerces.xni.grammars.XMLGrammarDescription;
import org.apache.xerces.xs.XSNamedMap;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.impl.xs.util.SimpleLocator;
import java.util.Vector;
import java.lang.ref.SoftReference;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.SymbolHash;
import org.apache.xerces.xs.XSNamespaceItem;
import org.apache.xerces.xni.grammars.XSGrammar;

public class SchemaGrammar implements XSGrammar, XSNamespaceItem
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
    XSAnnotationImpl[] fAnnotations;
    int fNumAnnotations;
    private SymbolTable fSymbolTable;
    private SoftReference fSAXParser;
    private SoftReference fDOMParser;
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
    public static final Schema4Annotations SG_Schema4Annotations;
    public static final XSSimpleType fAnySimpleType;
    public static final BuiltinSchemaGrammar SG_XSI;
    private static final short MAX_COMP_IDX = 16;
    private static final boolean[] GLOBAL_COMP;
    private XSNamedMap[] fComponents;
    private Vector fDocuments;
    private Vector fLocations;
    
    protected SchemaGrammar() {
        this.fGrammarDescription = null;
        this.fAnnotations = null;
        this.fSymbolTable = null;
        this.fSAXParser = null;
        this.fDOMParser = null;
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
    
    public SchemaGrammar(final String fTargetNamespace, final XSDDescription fGrammarDescription, final SymbolTable fSymbolTable) {
        this.fGrammarDescription = null;
        this.fAnnotations = null;
        this.fSymbolTable = null;
        this.fSAXParser = null;
        this.fDOMParser = null;
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
        this.fTargetNamespace = fTargetNamespace;
        this.fGrammarDescription = fGrammarDescription;
        this.fSymbolTable = fSymbolTable;
        this.fGlobalAttrDecls = new SymbolHash();
        this.fGlobalAttrGrpDecls = new SymbolHash();
        this.fGlobalElemDecls = new SymbolHash();
        this.fGlobalGroupDecls = new SymbolHash();
        this.fGlobalNotationDecls = new SymbolHash();
        this.fGlobalIDConstraintDecls = new SymbolHash();
        if (this.fTargetNamespace == SchemaSymbols.URI_SCHEMAFORSCHEMA) {
            this.fGlobalTypeDecls = SchemaGrammar.SG_SchemaNS.fGlobalTypeDecls.makeClone();
        }
        else {
            this.fGlobalTypeDecls = new SymbolHash();
        }
    }
    
    public XMLGrammarDescription getGrammarDescription() {
        return this.fGrammarDescription;
    }
    
    public boolean isNamespaceAware() {
        return true;
    }
    
    public void setImportedGrammars(final Vector fImported) {
        this.fImported = fImported;
    }
    
    public Vector getImportedGrammars() {
        return this.fImported;
    }
    
    public final String getTargetNamespace() {
        return this.fTargetNamespace;
    }
    
    public void addGlobalAttributeDecl(final XSAttributeDecl xsAttributeDecl) {
        this.fGlobalAttrDecls.put(xsAttributeDecl.fName, xsAttributeDecl);
    }
    
    public void addGlobalAttributeGroupDecl(final XSAttributeGroupDecl xsAttributeGroupDecl) {
        this.fGlobalAttrGrpDecls.put(xsAttributeGroupDecl.fName, xsAttributeGroupDecl);
    }
    
    public void addGlobalElementDecl(final XSElementDecl xsElementDecl) {
        this.fGlobalElemDecls.put(xsElementDecl.fName, xsElementDecl);
        if (xsElementDecl.fSubGroup != null) {
            if (this.fSubGroupCount == this.fSubGroups.length) {
                this.fSubGroups = resize(this.fSubGroups, this.fSubGroupCount + 16);
            }
            this.fSubGroups[this.fSubGroupCount++] = xsElementDecl;
        }
    }
    
    public void addGlobalGroupDecl(final XSGroupDecl xsGroupDecl) {
        this.fGlobalGroupDecls.put(xsGroupDecl.fName, xsGroupDecl);
    }
    
    public void addGlobalNotationDecl(final XSNotationDecl xsNotationDecl) {
        this.fGlobalNotationDecls.put(xsNotationDecl.fName, xsNotationDecl);
    }
    
    public void addGlobalTypeDecl(final XSTypeDefinition xsTypeDefinition) {
        this.fGlobalTypeDecls.put(xsTypeDefinition.getName(), xsTypeDefinition);
    }
    
    public final void addIDConstraintDecl(final XSElementDecl xsElementDecl, final IdentityConstraint identityConstraint) {
        xsElementDecl.addIDConstraint(identityConstraint);
        this.fGlobalIDConstraintDecls.put(identityConstraint.getIdentityConstraintName(), identityConstraint);
    }
    
    public final XSAttributeDecl getGlobalAttributeDecl(final String s) {
        return (XSAttributeDecl)this.fGlobalAttrDecls.get(s);
    }
    
    public final XSAttributeGroupDecl getGlobalAttributeGroupDecl(final String s) {
        return (XSAttributeGroupDecl)this.fGlobalAttrGrpDecls.get(s);
    }
    
    public final XSElementDecl getGlobalElementDecl(final String s) {
        return (XSElementDecl)this.fGlobalElemDecls.get(s);
    }
    
    public final XSGroupDecl getGlobalGroupDecl(final String s) {
        return (XSGroupDecl)this.fGlobalGroupDecls.get(s);
    }
    
    public final XSNotationDecl getGlobalNotationDecl(final String s) {
        return (XSNotationDecl)this.fGlobalNotationDecls.get(s);
    }
    
    public final XSTypeDefinition getGlobalTypeDecl(final String s) {
        return (XSTypeDefinition)this.fGlobalTypeDecls.get(s);
    }
    
    public final IdentityConstraint getIDConstraintDecl(final String s) {
        return (IdentityConstraint)this.fGlobalIDConstraintDecls.get(s);
    }
    
    public final boolean hasIDConstraints() {
        return this.fGlobalIDConstraintDecls.getLength() > 0;
    }
    
    public void addComplexTypeDecl(final XSComplexTypeDecl xsComplexTypeDecl, final SimpleLocator simpleLocator) {
        if (this.fCTCount == this.fComplexTypeDecls.length) {
            this.fComplexTypeDecls = resize(this.fComplexTypeDecls, this.fCTCount + 16);
            this.fCTLocators = resize(this.fCTLocators, this.fCTCount + 16);
        }
        this.fCTLocators[this.fCTCount] = simpleLocator;
        this.fComplexTypeDecls[this.fCTCount++] = xsComplexTypeDecl;
    }
    
    public void addRedefinedGroupDecl(final XSGroupDecl xsGroupDecl, final XSGroupDecl xsGroupDecl2, final SimpleLocator simpleLocator) {
        if (this.fRGCount == this.fRedefinedGroupDecls.length) {
            this.fRedefinedGroupDecls = resize(this.fRedefinedGroupDecls, this.fRGCount << 1);
            this.fRGLocators = resize(this.fRGLocators, this.fRGCount);
        }
        this.fRGLocators[this.fRGCount / 2] = simpleLocator;
        this.fRedefinedGroupDecls[this.fRGCount++] = xsGroupDecl;
        this.fRedefinedGroupDecls[this.fRGCount++] = xsGroupDecl2;
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
    
    final void setUncheckedTypeNum(final int fctCount) {
        this.fCTCount = fctCount;
        this.fComplexTypeDecls = resize(this.fComplexTypeDecls, this.fCTCount);
        this.fCTLocators = resize(this.fCTLocators, this.fCTCount);
    }
    
    final XSElementDecl[] getSubstitutionGroups() {
        if (this.fSubGroupCount < this.fSubGroups.length) {
            this.fSubGroups = resize(this.fSubGroups, this.fSubGroupCount);
        }
        return this.fSubGroups;
    }
    
    static final XSComplexTypeDecl[] resize(final XSComplexTypeDecl[] array, final int n) {
        final XSComplexTypeDecl[] array2 = new XSComplexTypeDecl[n];
        System.arraycopy(array, 0, array2, 0, Math.min(array.length, n));
        return array2;
    }
    
    static final XSGroupDecl[] resize(final XSGroupDecl[] array, final int n) {
        final XSGroupDecl[] array2 = new XSGroupDecl[n];
        System.arraycopy(array, 0, array2, 0, Math.min(array.length, n));
        return array2;
    }
    
    static final XSElementDecl[] resize(final XSElementDecl[] array, final int n) {
        final XSElementDecl[] array2 = new XSElementDecl[n];
        System.arraycopy(array, 0, array2, 0, Math.min(array.length, n));
        return array2;
    }
    
    static final SimpleLocator[] resize(final SimpleLocator[] array, final int n) {
        final SimpleLocator[] array2 = new SimpleLocator[n];
        System.arraycopy(array, 0, array2, 0, Math.min(array.length, n));
        return array2;
    }
    
    public synchronized void addDocument(final Object o, final String s) {
        if (this.fDocuments == null) {
            this.fDocuments = new Vector();
            this.fLocations = new Vector();
        }
        this.fDocuments.addElement(o);
        this.fLocations.addElement(s);
    }
    
    public String getSchemaNamespace() {
        return this.fTargetNamespace;
    }
    
    synchronized DOMParser getDOMParser() {
        if (this.fDOMParser != null) {
            final DOMParser domParser = this.fDOMParser.get();
            if (domParser != null) {
                return domParser;
            }
        }
        final XML11Configuration xml11Configuration = new XML11Configuration(this.fSymbolTable);
        xml11Configuration.setFeature("http://xml.org/sax/features/namespaces", true);
        xml11Configuration.setFeature("http://xml.org/sax/features/validation", false);
        final DOMParser domParser2 = new DOMParser(xml11Configuration);
        try {
            domParser2.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", false);
        }
        catch (SAXException ex) {}
        this.fDOMParser = new SoftReference(domParser2);
        return domParser2;
    }
    
    synchronized SAXParser getSAXParser() {
        if (this.fSAXParser != null) {
            final SAXParser saxParser = this.fSAXParser.get();
            if (saxParser != null) {
                return saxParser;
            }
        }
        final XML11Configuration xml11Configuration = new XML11Configuration(this.fSymbolTable);
        xml11Configuration.setFeature("http://xml.org/sax/features/namespaces", true);
        xml11Configuration.setFeature("http://xml.org/sax/features/validation", false);
        final SAXParser saxParser2 = new SAXParser(xml11Configuration);
        this.fSAXParser = new SoftReference(saxParser2);
        return saxParser2;
    }
    
    public synchronized XSNamedMap getComponents(final short n) {
        if (n <= 0 || n > 16 || !SchemaGrammar.GLOBAL_COMP[n]) {
            return XSNamedMapImpl.EMPTY_MAP;
        }
        if (this.fComponents == null) {
            this.fComponents = new XSNamedMap[17];
        }
        if (this.fComponents[n] == null) {
            SymbolHash symbolHash = null;
            switch (n) {
                case 3:
                case 15:
                case 16: {
                    symbolHash = this.fGlobalTypeDecls;
                    break;
                }
                case 1: {
                    symbolHash = this.fGlobalAttrDecls;
                    break;
                }
                case 2: {
                    symbolHash = this.fGlobalElemDecls;
                    break;
                }
                case 5: {
                    symbolHash = this.fGlobalAttrGrpDecls;
                    break;
                }
                case 6: {
                    symbolHash = this.fGlobalGroupDecls;
                    break;
                }
                case 11: {
                    symbolHash = this.fGlobalNotationDecls;
                    break;
                }
            }
            if (n == 15 || n == 16) {
                this.fComponents[n] = new XSNamedMap4Types(this.fTargetNamespace, symbolHash, n);
            }
            else {
                this.fComponents[n] = new XSNamedMapImpl(this.fTargetNamespace, symbolHash);
            }
        }
        return this.fComponents[n];
    }
    
    public XSTypeDefinition getTypeDefinition(final String s) {
        return this.getGlobalTypeDecl(s);
    }
    
    public XSAttributeDeclaration getAttributeDeclaration(final String s) {
        return this.getGlobalAttributeDecl(s);
    }
    
    public XSElementDeclaration getElementDeclaration(final String s) {
        return this.getGlobalElementDecl(s);
    }
    
    public XSAttributeGroupDefinition getAttributeGroup(final String s) {
        return this.getGlobalAttributeGroupDecl(s);
    }
    
    public XSModelGroupDefinition getModelGroupDefinition(final String s) {
        return this.getGlobalGroupDecl(s);
    }
    
    public XSNotationDeclaration getNotationDeclaration(final String s) {
        return this.getGlobalNotationDecl(s);
    }
    
    public StringList getDocumentLocations() {
        return new StringListImpl(this.fLocations);
    }
    
    public XSModel toXSModel() {
        return new XSModelImpl(new SchemaGrammar[] { this });
    }
    
    public XSModel toXSModel(final XSGrammar[] array) {
        if (array == null || array.length == 0) {
            return this.toXSModel();
        }
        final int length = array.length;
        boolean b = false;
        for (int i = 0; i < length; ++i) {
            if (array[i] == this) {
                b = true;
                break;
            }
        }
        final SchemaGrammar[] array2 = new SchemaGrammar[b ? length : (length + 1)];
        for (int j = 0; j < length; ++j) {
            array2[j] = (SchemaGrammar)array[j];
        }
        if (!b) {
            array2[length] = this;
        }
        return new XSModelImpl(array2);
    }
    
    public XSObjectList getAnnotations() {
        return new XSObjectListImpl(this.fAnnotations, this.fNumAnnotations);
    }
    
    public void addAnnotation(final XSAnnotationImpl xsAnnotationImpl) {
        if (xsAnnotationImpl == null) {
            return;
        }
        if (this.fAnnotations == null) {
            this.fAnnotations = new XSAnnotationImpl[2];
        }
        else if (this.fNumAnnotations == this.fAnnotations.length) {
            final XSAnnotationImpl[] fAnnotations = new XSAnnotationImpl[this.fNumAnnotations << 1];
            System.arraycopy(this.fAnnotations, 0, fAnnotations, 0, this.fNumAnnotations);
            this.fAnnotations = fAnnotations;
        }
        this.fAnnotations[this.fNumAnnotations++] = xsAnnotationImpl;
    }
    
    static {
        fAnyType = new XSAnyType();
        SG_SchemaNS = new BuiltinSchemaGrammar(1);
        SG_Schema4Annotations = new Schema4Annotations();
        fAnySimpleType = (XSSimpleType)SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl("anySimpleType");
        SG_XSI = new BuiltinSchemaGrammar(2);
        GLOBAL_COMP = new boolean[] { false, true, true, true, false, true, true, false, false, false, false, true, false, false, false, true, true };
    }
    
    private static class BuiltinAttrDecl extends XSAttributeDecl
    {
        public BuiltinAttrDecl(final String fName, final String fTargetNamespace, final XSSimpleType fType, final short fScope) {
            super.fName = fName;
            super.fTargetNamespace = fTargetNamespace;
            super.fType = fType;
            super.fScope = fScope;
        }
        
        public void setValues(final String s, final String s2, final XSSimpleType xsSimpleType, final short n, final short n2, final ValidatedInfo validatedInfo, final XSComplexTypeDecl xsComplexTypeDecl) {
        }
        
        public void reset() {
        }
        
        public XSAnnotation getAnnotation() {
            return null;
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
        
        public void setValues(final String s, final String s2, final XSTypeDefinition xsTypeDefinition, final short n, final short n2, final short n3, final short n4, final boolean b, final XSAttributeGroupDecl xsAttributeGroupDecl, final XSSimpleType xsSimpleType, final XSParticleDecl xsParticleDecl) {
        }
        
        public void setName(final String s) {
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
            final XSWildcardDecl fAttributeWC = new XSWildcardDecl();
            fAttributeWC.fProcessContents = 3;
            final XSAttributeGroupDecl xsAttributeGroupDecl = new XSAttributeGroupDecl();
            xsAttributeGroupDecl.fAttributeWC = fAttributeWC;
            return xsAttributeGroupDecl;
        }
        
        public XSWildcard getAttributeWildcard() {
            final XSWildcardDecl xsWildcardDecl = new XSWildcardDecl();
            xsWildcardDecl.fProcessContents = 3;
            return xsWildcardDecl;
        }
        
        public XSParticle getParticle() {
            final XSWildcardDecl fValue = new XSWildcardDecl();
            fValue.fProcessContents = 3;
            final XSParticleDecl xsParticleDecl = new XSParticleDecl();
            xsParticleDecl.fMinOccurs = 0;
            xsParticleDecl.fMaxOccurs = -1;
            xsParticleDecl.fType = 2;
            xsParticleDecl.fValue = fValue;
            final XSModelGroupImpl fValue2 = new XSModelGroupImpl();
            fValue2.fCompositor = 102;
            fValue2.fParticleCount = 1;
            (fValue2.fParticles = new XSParticleDecl[1])[0] = xsParticleDecl;
            final XSParticleDecl xsParticleDecl2 = new XSParticleDecl();
            xsParticleDecl2.fType = 3;
            xsParticleDecl2.fValue = fValue2;
            return xsParticleDecl2;
        }
        
        public XSObjectList getAnnotations() {
            return null;
        }
    }
    
    public static final class Schema4Annotations extends SchemaGrammar
    {
        public Schema4Annotations() {
            super.fTargetNamespace = SchemaSymbols.URI_SCHEMAFORSCHEMA;
            super.fGrammarDescription = new XSDDescription();
            super.fGrammarDescription.fContextType = 3;
            super.fGrammarDescription.setNamespace(SchemaSymbols.URI_SCHEMAFORSCHEMA);
            super.fGlobalAttrDecls = new SymbolHash(1);
            super.fGlobalAttrGrpDecls = new SymbolHash(1);
            super.fGlobalElemDecls = new SymbolHash(6);
            super.fGlobalGroupDecls = new SymbolHash(1);
            super.fGlobalNotationDecls = new SymbolHash(1);
            super.fGlobalIDConstraintDecls = new SymbolHash(1);
            super.fGlobalTypeDecls = SchemaGrammar.SG_SchemaNS.fGlobalTypeDecls;
            final XSElementDecl annotationElementDecl = this.createAnnotationElementDecl(SchemaSymbols.ELT_ANNOTATION);
            final XSElementDecl annotationElementDecl2 = this.createAnnotationElementDecl(SchemaSymbols.ELT_DOCUMENTATION);
            final XSElementDecl annotationElementDecl3 = this.createAnnotationElementDecl(SchemaSymbols.ELT_APPINFO);
            super.fGlobalElemDecls.put(annotationElementDecl.fName, annotationElementDecl);
            super.fGlobalElemDecls.put(annotationElementDecl2.fName, annotationElementDecl2);
            super.fGlobalElemDecls.put(annotationElementDecl3.fName, annotationElementDecl3);
            final XSComplexTypeDecl fType = new XSComplexTypeDecl();
            final XSComplexTypeDecl fType2 = new XSComplexTypeDecl();
            final XSComplexTypeDecl fType3 = new XSComplexTypeDecl();
            annotationElementDecl.fType = fType;
            annotationElementDecl2.fType = fType2;
            annotationElementDecl3.fType = fType3;
            final XSAttributeGroupDecl xsAttributeGroupDecl = new XSAttributeGroupDecl();
            final XSAttributeGroupDecl xsAttributeGroupDecl2 = new XSAttributeGroupDecl();
            final XSAttributeGroupDecl xsAttributeGroupDecl3 = new XSAttributeGroupDecl();
            final XSAttributeUseImpl xsAttributeUseImpl = new XSAttributeUseImpl();
            (xsAttributeUseImpl.fAttrDecl = new XSAttributeDecl()).setValues(SchemaSymbols.ATT_ID, null, (XSSimpleType)super.fGlobalTypeDecls.get("ID"), (short)0, (short)2, null, fType, null);
            xsAttributeUseImpl.fUse = 0;
            xsAttributeUseImpl.fConstraintType = 0;
            final XSAttributeUseImpl xsAttributeUseImpl2 = new XSAttributeUseImpl();
            (xsAttributeUseImpl2.fAttrDecl = new XSAttributeDecl()).setValues(SchemaSymbols.ATT_SOURCE, null, (XSSimpleType)super.fGlobalTypeDecls.get("anyURI"), (short)0, (short)2, null, fType2, null);
            xsAttributeUseImpl2.fUse = 0;
            xsAttributeUseImpl2.fConstraintType = 0;
            final XSAttributeUseImpl xsAttributeUseImpl3 = new XSAttributeUseImpl();
            (xsAttributeUseImpl3.fAttrDecl = new XSAttributeDecl()).setValues("lang".intern(), NamespaceContext.XML_URI, (XSSimpleType)super.fGlobalTypeDecls.get("language"), (short)0, (short)2, null, fType2, null);
            xsAttributeUseImpl3.fUse = 0;
            xsAttributeUseImpl3.fConstraintType = 0;
            final XSAttributeUseImpl xsAttributeUseImpl4 = new XSAttributeUseImpl();
            (xsAttributeUseImpl4.fAttrDecl = new XSAttributeDecl()).setValues(SchemaSymbols.ATT_SOURCE, null, (XSSimpleType)super.fGlobalTypeDecls.get("anyURI"), (short)0, (short)2, null, fType3, null);
            xsAttributeUseImpl4.fUse = 0;
            xsAttributeUseImpl4.fConstraintType = 0;
            final XSWildcardDecl fAttributeWC = new XSWildcardDecl();
            fAttributeWC.fNamespaceList = new String[] { super.fTargetNamespace, null };
            fAttributeWC.fType = 2;
            fAttributeWC.fProcessContents = 3;
            xsAttributeGroupDecl.addAttributeUse(xsAttributeUseImpl);
            xsAttributeGroupDecl.fAttributeWC = fAttributeWC;
            xsAttributeGroupDecl2.addAttributeUse(xsAttributeUseImpl2);
            xsAttributeGroupDecl2.addAttributeUse(xsAttributeUseImpl3);
            xsAttributeGroupDecl2.fAttributeWC = fAttributeWC;
            xsAttributeGroupDecl3.addAttributeUse(xsAttributeUseImpl4);
            xsAttributeGroupDecl3.fAttributeWC = fAttributeWC;
            final XSParticleDecl unboundedModelGroupParticle = this.createUnboundedModelGroupParticle();
            final XSModelGroupImpl fValue = new XSModelGroupImpl();
            fValue.fCompositor = 101;
            fValue.fParticleCount = 2;
            (fValue.fParticles = new XSParticleDecl[2])[0] = this.createChoiceElementParticle(annotationElementDecl3);
            fValue.fParticles[1] = this.createChoiceElementParticle(annotationElementDecl2);
            unboundedModelGroupParticle.fValue = fValue;
            final XSParticleDecl unboundedAnyWildcardSequenceParticle = this.createUnboundedAnyWildcardSequenceParticle();
            fType.setValues("#AnonType_" + SchemaSymbols.ELT_ANNOTATION, super.fTargetNamespace, SchemaGrammar.fAnyType, (short)2, (short)0, (short)3, (short)2, false, xsAttributeGroupDecl, null, unboundedModelGroupParticle, new XSObjectListImpl(null, 0));
            fType.setName("#AnonType_" + SchemaSymbols.ELT_ANNOTATION);
            fType.setIsAnonymous();
            fType2.setValues("#AnonType_" + SchemaSymbols.ELT_DOCUMENTATION, super.fTargetNamespace, SchemaGrammar.fAnyType, (short)2, (short)0, (short)3, (short)3, false, xsAttributeGroupDecl2, null, unboundedAnyWildcardSequenceParticle, new XSObjectListImpl(null, 0));
            fType2.setName("#AnonType_" + SchemaSymbols.ELT_DOCUMENTATION);
            fType2.setIsAnonymous();
            fType3.setValues("#AnonType_" + SchemaSymbols.ELT_APPINFO, super.fTargetNamespace, SchemaGrammar.fAnyType, (short)2, (short)0, (short)3, (short)3, false, xsAttributeGroupDecl3, null, unboundedAnyWildcardSequenceParticle, new XSObjectListImpl(null, 0));
            fType3.setName("#AnonType_" + SchemaSymbols.ELT_APPINFO);
            fType3.setIsAnonymous();
        }
        
        public XMLGrammarDescription getGrammarDescription() {
            return super.fGrammarDescription.makeClone();
        }
        
        public void setImportedGrammars(final Vector vector) {
        }
        
        public void addGlobalAttributeDecl(final XSAttributeDecl xsAttributeDecl) {
        }
        
        public void addGlobalAttributeGroupDecl(final XSAttributeGroupDecl xsAttributeGroupDecl) {
        }
        
        public void addGlobalElementDecl(final XSElementDecl xsElementDecl) {
        }
        
        public void addGlobalGroupDecl(final XSGroupDecl xsGroupDecl) {
        }
        
        public void addGlobalNotationDecl(final XSNotationDecl xsNotationDecl) {
        }
        
        public void addGlobalTypeDecl(final XSTypeDefinition xsTypeDefinition) {
        }
        
        public void addComplexTypeDecl(final XSComplexTypeDecl xsComplexTypeDecl, final SimpleLocator simpleLocator) {
        }
        
        public void addRedefinedGroupDecl(final XSGroupDecl xsGroupDecl, final XSGroupDecl xsGroupDecl2, final SimpleLocator simpleLocator) {
        }
        
        public synchronized void addDocument(final Object o, final String s) {
        }
        
        synchronized DOMParser getDOMParser() {
            return null;
        }
        
        synchronized SAXParser getSAXParser() {
            return null;
        }
        
        private XSElementDecl createAnnotationElementDecl(final String fName) {
            final XSElementDecl xsElementDecl = new XSElementDecl();
            xsElementDecl.fName = fName;
            xsElementDecl.fTargetNamespace = super.fTargetNamespace;
            xsElementDecl.setIsGlobal();
            xsElementDecl.fBlock = 7;
            xsElementDecl.setConstraintType((short)0);
            return xsElementDecl;
        }
        
        private XSParticleDecl createUnboundedModelGroupParticle() {
            final XSParticleDecl xsParticleDecl = new XSParticleDecl();
            xsParticleDecl.fMinOccurs = 0;
            xsParticleDecl.fMaxOccurs = -1;
            xsParticleDecl.fType = 3;
            return xsParticleDecl;
        }
        
        private XSParticleDecl createChoiceElementParticle(final XSElementDecl fValue) {
            final XSParticleDecl xsParticleDecl = new XSParticleDecl();
            xsParticleDecl.fMinOccurs = 1;
            xsParticleDecl.fMaxOccurs = 1;
            xsParticleDecl.fType = 1;
            xsParticleDecl.fValue = fValue;
            return xsParticleDecl;
        }
        
        private XSParticleDecl createUnboundedAnyWildcardSequenceParticle() {
            final XSParticleDecl unboundedModelGroupParticle = this.createUnboundedModelGroupParticle();
            final XSModelGroupImpl fValue = new XSModelGroupImpl();
            fValue.fCompositor = 102;
            fValue.fParticleCount = 1;
            (fValue.fParticles = new XSParticleDecl[1])[0] = this.createAnyLaxWildcardParticle();
            unboundedModelGroupParticle.fValue = fValue;
            return unboundedModelGroupParticle;
        }
        
        private XSParticleDecl createAnyLaxWildcardParticle() {
            final XSParticleDecl xsParticleDecl = new XSParticleDecl();
            xsParticleDecl.fMinOccurs = 1;
            xsParticleDecl.fMaxOccurs = 1;
            xsParticleDecl.fType = 2;
            final XSWildcardDecl fValue = new XSWildcardDecl();
            fValue.fNamespaceList = null;
            fValue.fType = 1;
            fValue.fProcessContents = 3;
            xsParticleDecl.fValue = fValue;
            return xsParticleDecl;
        }
    }
    
    public static class BuiltinSchemaGrammar extends SchemaGrammar
    {
        public BuiltinSchemaGrammar(final int n) {
            final SchemaDVFactory instance = SchemaDVFactory.getInstance();
            if (n == 1) {
                super.fTargetNamespace = SchemaSymbols.URI_SCHEMAFORSCHEMA;
                super.fGrammarDescription = new XSDDescription();
                super.fGrammarDescription.fContextType = 3;
                super.fGrammarDescription.setNamespace(SchemaSymbols.URI_SCHEMAFORSCHEMA);
                super.fGlobalAttrDecls = new SymbolHash(1);
                super.fGlobalAttrGrpDecls = new SymbolHash(1);
                super.fGlobalElemDecls = new SymbolHash(1);
                super.fGlobalGroupDecls = new SymbolHash(1);
                super.fGlobalNotationDecls = new SymbolHash(1);
                super.fGlobalIDConstraintDecls = new SymbolHash(1);
                (super.fGlobalTypeDecls = instance.getBuiltInTypes()).put(SchemaGrammar.fAnyType.getName(), SchemaGrammar.fAnyType);
            }
            else if (n == 2) {
                super.fTargetNamespace = SchemaSymbols.URI_XSI;
                super.fGrammarDescription = new XSDDescription();
                super.fGrammarDescription.fContextType = 3;
                super.fGrammarDescription.setNamespace(SchemaSymbols.URI_XSI);
                super.fGlobalAttrGrpDecls = new SymbolHash(1);
                super.fGlobalElemDecls = new SymbolHash(1);
                super.fGlobalGroupDecls = new SymbolHash(1);
                super.fGlobalNotationDecls = new SymbolHash(1);
                super.fGlobalIDConstraintDecls = new SymbolHash(1);
                super.fGlobalTypeDecls = new SymbolHash(1);
                super.fGlobalAttrDecls = new SymbolHash(8);
                final boolean b = true;
                final String xsi_TYPE = SchemaSymbols.XSI_TYPE;
                super.fGlobalAttrDecls.put(xsi_TYPE, new BuiltinAttrDecl(xsi_TYPE, SchemaSymbols.URI_XSI, instance.getBuiltInType("QName"), (short)(b ? 1 : 0)));
                final String xsi_NIL = SchemaSymbols.XSI_NIL;
                super.fGlobalAttrDecls.put(xsi_NIL, new BuiltinAttrDecl(xsi_NIL, SchemaSymbols.URI_XSI, instance.getBuiltInType("boolean"), (short)(b ? 1 : 0)));
                final XSSimpleType builtInType = instance.getBuiltInType("anyURI");
                final String xsi_SCHEMALOCATION = SchemaSymbols.XSI_SCHEMALOCATION;
                super.fGlobalAttrDecls.put(xsi_SCHEMALOCATION, new BuiltinAttrDecl(xsi_SCHEMALOCATION, SchemaSymbols.URI_XSI, instance.createTypeList(null, SchemaSymbols.URI_XSI, (short)0, builtInType, null), (short)(b ? 1 : 0)));
                final String xsi_NONAMESPACESCHEMALOCATION = SchemaSymbols.XSI_NONAMESPACESCHEMALOCATION;
                super.fGlobalAttrDecls.put(xsi_NONAMESPACESCHEMALOCATION, new BuiltinAttrDecl(xsi_NONAMESPACESCHEMALOCATION, SchemaSymbols.URI_XSI, builtInType, (short)(b ? 1 : 0)));
            }
        }
        
        public XMLGrammarDescription getGrammarDescription() {
            return super.fGrammarDescription.makeClone();
        }
        
        public void setImportedGrammars(final Vector vector) {
        }
        
        public void addGlobalAttributeDecl(final XSAttributeDecl xsAttributeDecl) {
        }
        
        public void addGlobalAttributeGroupDecl(final XSAttributeGroupDecl xsAttributeGroupDecl) {
        }
        
        public void addGlobalElementDecl(final XSElementDecl xsElementDecl) {
        }
        
        public void addGlobalGroupDecl(final XSGroupDecl xsGroupDecl) {
        }
        
        public void addGlobalNotationDecl(final XSNotationDecl xsNotationDecl) {
        }
        
        public void addGlobalTypeDecl(final XSTypeDefinition xsTypeDefinition) {
        }
        
        public void addComplexTypeDecl(final XSComplexTypeDecl xsComplexTypeDecl, final SimpleLocator simpleLocator) {
        }
        
        public void addRedefinedGroupDecl(final XSGroupDecl xsGroupDecl, final XSGroupDecl xsGroupDecl2, final SimpleLocator simpleLocator) {
        }
        
        public synchronized void addDocument(final Object o, final String s) {
        }
        
        synchronized DOMParser getDOMParser() {
            return null;
        }
        
        synchronized SAXParser getSAXParser() {
            return null;
        }
    }
}
