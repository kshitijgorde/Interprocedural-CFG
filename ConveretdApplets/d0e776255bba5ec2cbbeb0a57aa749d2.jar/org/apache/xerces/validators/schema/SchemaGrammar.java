// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.schema;

import org.apache.xerces.validators.common.XMLContentModel;
import org.apache.xerces.validators.datatype.DatatypeValidator;
import org.apache.xerces.utils.QName;
import org.apache.xerces.utils.NamespacesScope;
import org.apache.xerces.validators.datatype.DatatypeValidatorFactoryImpl;
import java.util.Hashtable;
import org.apache.xerces.validators.common.XMLAttributeDecl;
import org.apache.xerces.validators.common.XMLElementDecl;
import org.apache.xerces.framework.XMLContentSpec;
import org.apache.xerces.validators.common.Grammar;

public class SchemaGrammar extends Grammar
{
    private static final int CHUNK_SHIFT = 8;
    private static final int CHUNK_SIZE = 256;
    private static final int CHUNK_MASK = 255;
    private static final int INITIAL_CHUNK_COUNT = 4;
    private XMLContentSpec fTempContentSpecNode;
    private XMLElementDecl fTempElementDecl;
    private XMLAttributeDecl fTempAttributeDecl;
    private int[][] fScopeDefinedByElement;
    private String[][] fFromAnotherSchemaURI;
    private TraverseSchema.ComplexTypeInfo[][] fComplexTypeInfo;
    private int[][] fElementDeclDefaultType;
    private String[][] fElementDeclDefaultValue;
    private String[][] fElementDeclSubstitutionGroupFullName;
    private int[][] fElementDeclBlockSet;
    private int[][] fElementDeclFinalSet;
    private int[][] fElementDeclMiscFlags;
    private Hashtable fComplexTypeRegistry;
    private Hashtable fAttributeDeclRegistry;
    private DatatypeValidatorFactoryImpl fDatatypeRegistry;
    Hashtable topLevelGroupDecls;
    Hashtable topLevelNotationDecls;
    Hashtable topLevelAttrDecls;
    Hashtable topLevelAttrGrpDecls;
    private NamespacesScope fNamespacesScope;
    private String fTargetNamespaceURI;
    
    public SchemaGrammar() {
        this.fTempContentSpecNode = new XMLContentSpec();
        this.fTempElementDecl = new XMLElementDecl();
        this.fTempAttributeDecl = new XMLAttributeDecl();
        this.fScopeDefinedByElement = new int[4][];
        this.fFromAnotherSchemaURI = new String[4][];
        this.fComplexTypeInfo = new TraverseSchema.ComplexTypeInfo[4][];
        this.fElementDeclDefaultType = new int[4][];
        this.fElementDeclDefaultValue = new String[4][];
        this.fElementDeclSubstitutionGroupFullName = new String[4][];
        this.fElementDeclBlockSet = new int[4][];
        this.fElementDeclFinalSet = new int[4][];
        this.fElementDeclMiscFlags = new int[4][];
        this.fComplexTypeRegistry = null;
        this.fAttributeDeclRegistry = null;
        this.fDatatypeRegistry = null;
        this.topLevelGroupDecls = new Hashtable();
        this.topLevelNotationDecls = new Hashtable();
        this.topLevelAttrDecls = new Hashtable();
        this.topLevelAttrGrpDecls = new Hashtable();
        this.fNamespacesScope = null;
        this.fTargetNamespaceURI = "";
    }
    
    public NamespacesScope getNamespacesScope() {
        return this.fNamespacesScope;
    }
    
    public String getTargetNamespaceURI() {
        return this.fTargetNamespaceURI;
    }
    
    public Hashtable getAttirubteDeclRegistry() {
        return this.fAttributeDeclRegistry;
    }
    
    public Hashtable getComplexTypeRegistry() {
        return this.fComplexTypeRegistry;
    }
    
    public DatatypeValidatorFactoryImpl getDatatypeRegistry() {
        return this.fDatatypeRegistry;
    }
    
    public int getElementDefinedScope(final int n) {
        if (n < -1) {
            return -1;
        }
        return this.fScopeDefinedByElement[n >> 8][n & 0xFF];
    }
    
    public int getElementDefaultTYpe(final int n) {
        if (n < -1) {
            return -1;
        }
        return this.fElementDeclDefaultType[n >> 8][n & 0xFF];
    }
    
    public int getElementDeclBlockSet(final int n) {
        if (n < -1) {
            return -1;
        }
        return this.fElementDeclBlockSet[n >> 8][n & 0xFF];
    }
    
    public int getElementDeclFinalSet(final int n) {
        if (n < -1) {
            return -1;
        }
        return this.fElementDeclFinalSet[n >> 8][n & 0xFF];
    }
    
    public int getElementDeclMiscFlags(final int n) {
        if (n < -1) {
            return -1;
        }
        return this.fElementDeclMiscFlags[n >> 8][n & 0xFF];
    }
    
    public String getElementFromAnotherSchemaURI(final int n) {
        if (n < 0) {
            return null;
        }
        return this.fFromAnotherSchemaURI[n >> 8][n & 0xFF];
    }
    
    public String getElementDefaultValue(final int n) {
        if (n < 0) {
            return null;
        }
        return this.fElementDeclDefaultValue[n >> 8][n & 0xFF];
    }
    
    public String getElementDeclSubstitutionGroupElementFullName(final int n) {
        if (n < 0) {
            return null;
        }
        return this.fElementDeclSubstitutionGroupFullName[n >> 8][n & 0xFF];
    }
    
    public TraverseSchema.ComplexTypeInfo getElementComplexTypeInfo(final int n) {
        if (n < -1) {
            return null;
        }
        return this.fComplexTypeInfo[n >> 8][n & 0xFF];
    }
    
    protected void setAttributeDeclRegistry(final Hashtable fAttributeDeclRegistry) {
        this.fAttributeDeclRegistry = fAttributeDeclRegistry;
    }
    
    protected void setComplexTypeRegistry(final Hashtable fComplexTypeRegistry) {
        this.fComplexTypeRegistry = fComplexTypeRegistry;
    }
    
    protected void setDatatypeRegistry(final DatatypeValidatorFactoryImpl fDatatypeRegistry) {
        this.fDatatypeRegistry = fDatatypeRegistry;
    }
    
    protected void setNamespacesScope(final NamespacesScope fNamespacesScope) {
        this.fNamespacesScope = fNamespacesScope;
    }
    
    protected void setTargetNamespaceURI(final String fTargetNamespaceURI) {
        this.fTargetNamespaceURI = fTargetNamespaceURI;
    }
    
    protected int createElementDecl() {
        return super.createElementDecl();
    }
    
    protected void setElementDecl(final int n, final XMLElementDecl xmlElementDecl) {
        super.setElementDecl(n, xmlElementDecl);
    }
    
    protected int createContentSpec() {
        return super.createContentSpec();
    }
    
    protected void setContentSpec(final int n, final XMLContentSpec xmlContentSpec) {
        super.setContentSpec(n, xmlContentSpec);
    }
    
    protected int createAttributeDecl() {
        return super.createAttributeDecl();
    }
    
    protected void setAttributeDecl(final int n, final int n2, final XMLAttributeDecl xmlAttributeDecl) {
        super.setAttributeDecl(n, n2, xmlAttributeDecl);
    }
    
    protected void setElementDefinedScope(final int n, final int n2) {
        final int n3 = n >> 8;
        final int n4 = n & 0xFF;
        this.ensureElementDeclCapacity(n3);
        if (n > -1) {
            this.fScopeDefinedByElement[n3][n4] = n2;
        }
    }
    
    protected void setElementFromAnotherSchemaURI(final int n, final String s) {
        final int n2 = n >> 8;
        final int n3 = n & 0xFF;
        this.ensureElementDeclCapacity(n2);
        if (n > -1) {
            this.fFromAnotherSchemaURI[n2][n3] = s;
        }
    }
    
    protected void setElementComplexTypeInfo(final int n, final TraverseSchema.ComplexTypeInfo complexTypeInfo) {
        final int n2 = n >> 8;
        final int n3 = n & 0xFF;
        this.ensureElementDeclCapacity(n2);
        if (n > -1) {
            this.fComplexTypeInfo[n2][n3] = complexTypeInfo;
        }
    }
    
    protected void setElementDefault(final int n, final int n2, final String s) {
        final int n3 = n >> 8;
        final int n4 = n & 0xFF;
        this.ensureElementDeclCapacity(n3);
        if (n > -1) {
            this.fElementDeclDefaultType[n3][n4] = n2;
            this.fElementDeclDefaultValue[n3][n4] = s;
        }
    }
    
    protected void setElementDeclBlockSet(final int n, final int n2) {
        final int n3 = n >> 8;
        final int n4 = n & 0xFF;
        this.ensureElementDeclCapacity(n3);
        if (n > -1) {
            this.fElementDeclBlockSet[n3][n4] = n2;
        }
    }
    
    protected void setElementDeclFinalSet(final int n, final int n2) {
        final int n3 = n >> 8;
        final int n4 = n & 0xFF;
        this.ensureElementDeclCapacity(n3);
        if (n > -1) {
            this.fElementDeclFinalSet[n3][n4] = n2;
        }
    }
    
    protected void setElementDeclMiscFlags(final int n, final int n2) {
        final int n3 = n >> 8;
        final int n4 = n & 0xFF;
        this.ensureElementDeclCapacity(n3);
        if (n > -1) {
            this.fElementDeclMiscFlags[n3][n4] = n2;
        }
    }
    
    protected void setElementDeclSubstitutionGroupElementFullName(final int n, final String s) {
        final int n2 = n >> 8;
        final int n3 = n & 0xFF;
        this.ensureElementDeclCapacity(n2);
        if (n > -1) {
            this.fElementDeclSubstitutionGroupFullName[n2][n3] = s;
        }
    }
    
    protected int addElementDecl(final QName values, final int enclosingScope, final int n, final int type, final int contentSpecIndex, final int n2, final DatatypeValidator datatypeValidator) {
        int n3 = this.getElementDeclIndex(values, enclosingScope);
        if (n3 == -1) {
            if (enclosingScope < -1 || n < -1) {}
            this.fTempElementDecl.name.setValues(values);
            this.fTempElementDecl.enclosingScope = enclosingScope;
            this.fTempElementDecl.type = type;
            this.fTempElementDecl.contentSpecIndex = contentSpecIndex;
            this.fTempElementDecl.datatypeValidator = datatypeValidator;
            n3 = this.createElementDecl();
            this.setElementDecl(n3, this.fTempElementDecl);
            this.setFirstAttributeDeclIndex(n3, n2);
            this.setElementDefinedScope(n3, n);
        }
        return n3;
    }
    
    protected void addAttDef(final int n, final QName values, final int type, final int enumeration, final int defaultType, final String defaultValue, final DatatypeValidator datatypeValidator, final boolean list) {
        final int attributeDecl = this.createAttributeDecl();
        this.fTempAttributeDecl.name.setValues(values);
        this.fTempAttributeDecl.datatypeValidator = datatypeValidator;
        this.fTempAttributeDecl.type = type;
        this.fTempAttributeDecl.defaultType = defaultType;
        this.fTempAttributeDecl.defaultValue = defaultValue;
        this.fTempAttributeDecl.list = list;
        this.fTempAttributeDecl.enumeration = enumeration;
        super.setAttributeDecl(n, attributeDecl, this.fTempAttributeDecl);
    }
    
    public int getAttributeDeclIndex(final int n, final QName qName) {
        if (n == -1) {
            return -1;
        }
        for (int i = this.getFirstAttributeDeclIndex(n); i != -1; i = this.getNextAttributeDeclIndex(i)) {
            this.getAttributeDecl(i, this.fTempAttributeDecl);
            if (this.fTempAttributeDecl.name.localpart == qName.localpart && this.fTempAttributeDecl.name.uri == qName.uri) {
                return i;
            }
        }
        return -1;
    }
    
    protected int addContentSpecNode(final int type, final int value, final int otherValue, final boolean b) {
        this.fTempContentSpecNode.type = type;
        this.fTempContentSpecNode.value = value;
        this.fTempContentSpecNode.otherValue = otherValue;
        final int contentSpec = this.createContentSpec();
        this.setContentSpec(contentSpec, this.fTempContentSpecNode);
        return contentSpec;
    }
    
    private boolean ensureElementDeclCapacity(final int n) {
        try {
            return this.fScopeDefinedByElement[n][0] == -2;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            this.fScopeDefinedByElement = this.resize(this.fScopeDefinedByElement, this.fScopeDefinedByElement.length * 2);
            this.fFromAnotherSchemaURI = this.resize(this.fFromAnotherSchemaURI, this.fFromAnotherSchemaURI.length * 2);
            this.fComplexTypeInfo = this.resize(this.fComplexTypeInfo, this.fComplexTypeInfo.length * 2);
            this.fElementDeclDefaultType = this.resize(this.fElementDeclDefaultType, this.fElementDeclDefaultType.length * 2);
            this.fElementDeclDefaultValue = this.resize(this.fElementDeclDefaultValue, this.fElementDeclDefaultValue.length * 2);
            this.fElementDeclBlockSet = this.resize(this.fElementDeclBlockSet, this.fElementDeclBlockSet.length * 2);
            this.fElementDeclFinalSet = this.resize(this.fElementDeclFinalSet, this.fElementDeclFinalSet.length * 2);
            this.fElementDeclMiscFlags = this.resize(this.fElementDeclMiscFlags, this.fElementDeclMiscFlags.length * 2);
            this.fElementDeclSubstitutionGroupFullName = this.resize(this.fElementDeclSubstitutionGroupFullName, this.fElementDeclSubstitutionGroupFullName.length * 2);
        }
        catch (NullPointerException ex2) {}
        this.fScopeDefinedByElement[n] = new int[256];
        for (int i = 0; i < 256; ++i) {
            this.fScopeDefinedByElement[n][i] = -2;
        }
        this.fFromAnotherSchemaURI[n] = new String[256];
        this.fComplexTypeInfo[n] = new TraverseSchema.ComplexTypeInfo[256];
        this.fElementDeclDefaultType[n] = new int[256];
        this.fElementDeclDefaultValue[n] = new String[256];
        this.fElementDeclSubstitutionGroupFullName[n] = new String[256];
        this.fElementDeclBlockSet[n] = new int[256];
        this.fElementDeclFinalSet[n] = new int[256];
        this.fElementDeclMiscFlags[n] = new int[256];
        return true;
    }
    
    private int[][] resize(final int[][] array, final int n) {
        final int[][] array2 = new int[n][];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
    
    private DatatypeValidator[][] resize(final DatatypeValidator[][] array, final int n) {
        return array;
    }
    
    private XMLContentModel[][] resize(final XMLContentModel[][] array, final int n) {
        return array;
    }
    
    private QName[][] resize(final QName[][] array, final int n) {
        return array;
    }
    
    private String[][] resize(final String[][] array, final int n) {
        final String[][] array2 = new String[n][];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
    
    private TraverseSchema.ComplexTypeInfo[][] resize(final TraverseSchema.ComplexTypeInfo[][] array, final int n) {
        final TraverseSchema.ComplexTypeInfo[][] array2 = new TraverseSchema.ComplexTypeInfo[n][];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
}
