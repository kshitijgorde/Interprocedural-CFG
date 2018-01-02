// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan;

import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.XMLStringBuffer;
import com.ibm.xml.b2b.util.SymbolTable;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.QName;

public abstract class DocumentScannerSupport extends DocumentEntityState
{
    public static final short CHARACTERS = 0;
    public static final short CHARACTER_REF = 1;
    public static final short PRE_ENTITY_REF = 2;
    public static final short ENTITY_REF = 3;
    public static final short CDATA = 4;
    public int attrCount;
    public QName[] attrNames;
    public String[] attTypes;
    public XMLString[] attValues;
    public boolean[] attValueNormalized;
    public String nsDeclPrefix;
    public int firstMapping;
    public int lastMapping;
    public String[] prefixes;
    public String[] namespaceURIs;
    public int[] nsHandles;
    public String[] nsDeclQNames;
    public int singleCh;
    public short chOrigin;
    public SymbolTable fSymbolTable;
    private XMLStringBuffer fStringBuffer;
    private QName[] fElementTypeStack;
    protected String fNamespaceDeclPrefix;
    private int fSpecifiedAttrCount;
    private boolean fSaveAttValue;
    private int fAttValueOffset;
    private int fFreeQNameCount;
    private QName[] fFreeQNames;
    protected String fDefaultPrefix;
    protected int fCurrentScope;
    private int[] fMappingScopes;
    
    public DocumentScannerSupport(final SymbolTable fSymbolTable, final XMLStringBuffer fStringBuffer) {
        this.fSymbolTable = fSymbolTable;
        this.fStringBuffer = fStringBuffer;
        this.attrNames = new QName[32];
        this.attTypes = new String[32];
        this.attValues = new XMLString[32];
        this.attValueNormalized = new boolean[32];
        this.fElementTypeStack = new QName[32];
        this.fFreeQNames = new QName[32];
        this.fSpecifiedAttrCount = -1;
        this.initNamespaces();
        this.resetNamespaces();
    }
    
    public void reset(final boolean b) {
        super.reset(b);
        if (!b) {
            for (int n = 0; n < this.fElementTypeStack.length && this.fElementTypeStack[n] != null; ++n) {
                this.fElementTypeStack[n] = null;
            }
            for (int i = 0; i < this.attrNames.length; ++i) {
                if (this.attrNames[i] == null) {
                    break;
                }
                this.attrNames[i] = null;
                this.attValues[i] = null;
            }
        }
        this.resetNamespaces();
    }
    
    public void createQNameSymbols(final QName qName) {
        if (qName.handle == -1) {
            if (qName.sepOffset == -1) {
                this.fSymbolTable.addSymbol(qName);
                qName.prefix = XMLString.EMPTY_STRING;
                qName.prefixHandle = 0;
                qName.localPart = qName.str;
                qName.localHandle = qName.handle;
            }
            else {
                this.fSymbolTable.addQNameSymbols(qName);
            }
        }
    }
    
    public void processElementType() {
        if (this.fSpecifiedAttrCount >= 0) {
            for (int i = this.fSpecifiedAttrCount; i < this.attrCount; ++i) {
                this.attrNames[i] = null;
            }
            this.fSpecifiedAttrCount = -1;
        }
        this.startNamespacesScope();
        this.attrCount = 0;
        this.fSaveAttValue = false;
        super.currentElement = super.elementType;
    }
    
    public void processAttributeName(QName currentAttribute, final boolean b) {
        if (this.fSaveAttValue) {
            this.saveSpecifiedAttValue();
        }
        final String prefix = currentAttribute.prefix;
        final String localPart = currentAttribute.localPart;
        if (prefix != this.nsDeclPrefix) {
            if (prefix != XMLString.EMPTY_STRING || localPart != this.nsDeclPrefix) {
                this.fNamespaceDeclPrefix = null;
            }
            else {
                this.fNamespaceDeclPrefix = XMLString.EMPTY_STRING;
            }
        }
        else {
            this.fNamespaceDeclPrefix = localPart;
        }
        super.currentAttribute = currentAttribute;
        if (this.attrCount == this.attrNames.length) {
            this.growAttributes();
        }
        if (b) {
            super.attributeName = this.attrNames[this.attrCount];
            if (super.attributeName == null) {
                super.attributeName = this.getFreeQName();
            }
            this.attrNames[this.attrCount] = super.currentAttribute;
        }
        else {
            currentAttribute = this.attrNames[this.attrCount];
            this.attrNames[this.attrCount] = super.currentAttribute;
            if (this.fSpecifiedAttrCount == -1) {
                this.fSpecifiedAttrCount = this.attrCount;
            }
            if (currentAttribute != null) {
                this.saveFreeQName(currentAttribute);
            }
        }
        this.attTypes[this.attrCount] = XMLString.EMPTY_STRING;
        if (this.attValues[this.attrCount] == null) {
            this.attValues[this.attrCount] = new XMLString();
        }
        else {
            this.attValues[this.attrCount].clear();
            this.attValues[this.attrCount].offset = 0;
        }
        this.attValueNormalized[this.attrCount] = b;
        this.fAttValueOffset = -1;
        this.fSaveAttValue = true;
        ++this.attrCount;
    }
    
    public void endOfSpecifiedAttributes() {
        if (this.fSaveAttValue) {
            this.saveSpecifiedAttValue();
            this.fSaveAttValue = false;
        }
    }
    
    public void storeAttValue() {
        if (this.fSaveAttValue) {
            this.saveSpecifiedAttValue();
            this.fSaveAttValue = false;
        }
    }
    
    private void saveSpecifiedAttValue() {
        final int n = this.attrCount - 1;
        final XMLString xmlString = this.attValues[n];
        if (this.fAttValueOffset != -1) {
            this.fStringBuffer.setStringValues(this.fAttValueOffset, this.fStringBuffer.getOffset(), xmlString);
        }
        else if (xmlString.offset == 0) {
            this.fStringBuffer.setStringValues(0, 0, xmlString);
        }
        if (this.fNamespaceDeclPrefix != null) {
            this.fSymbolTable.addSymbol(xmlString);
            this.createPrefixMapping(this.fNamespaceDeclPrefix, xmlString.handle, this.attrNames[n].str);
            --this.attrCount;
        }
    }
    
    public void resolveNamespaceURIs() {
        for (int i = 0; i < this.attrCount; ++i) {
            final QName namespaceURI = this.attrNames[i];
            final String prefix = namespaceURI.prefix;
            if (prefix != XMLString.EMPTY_STRING && prefix != this.nsDeclPrefix) {
                this.setNamespaceURI(namespaceURI);
            }
        }
        this.setNamespaceURI(super.currentElement);
    }
    
    public void attributeValueCharacters(final XMLString values, final boolean b) {
        if (values.offset == values.endOffset) {
            return;
        }
        if (this.fAttValueOffset == -1) {
            if (this.attValues[this.attrCount - 1].offset == 0) {
                if (b) {
                    this.attValues[this.attrCount - 1].setValues(values);
                    return;
                }
                this.fAttValueOffset = this.fStringBuffer.getOffset();
            }
            else {
                this.fAttValueOffset = this.fStringBuffer.getOffset();
                this.fStringBuffer.append(this.attValues[this.attrCount - 1]);
            }
        }
        this.fStringBuffer.normalizedAppend(values);
    }
    
    public void attributeValueCharacter(final int n, final boolean b) {
        if (this.fAttValueOffset == -1) {
            this.fAttValueOffset = this.fStringBuffer.getOffset();
            if (this.attValues[this.attrCount - 1].offset > 0) {
                this.fStringBuffer.append(this.attValues[this.attrCount - 1]);
            }
        }
        this.fStringBuffer.append(n);
    }
    
    public void normalizeAttributeValue(final int n) {
        this.fStringBuffer.normalizeTextValue(this.attValues[n], null);
        this.attValueNormalized[n] = true;
    }
    
    public void addDefaultAttribute(final QName qName, final String s, final XMLString values) {
        this.processAttributeName(qName, false);
        if (this.fNamespaceDeclPrefix == null) {
            this.attTypes[this.attrCount - 1] = s;
            this.attValues[this.attrCount - 1].setValues(values);
        }
        else {
            this.fSymbolTable.addSymbol(values);
            this.createPrefixMapping(this.fNamespaceDeclPrefix, values.handle, qName.str);
            --this.attrCount;
        }
    }
    
    public void undeclaredEntityInContent(final XMLName xmlName) {
        this.entityNotDeclared(xmlName);
    }
    
    public void undeclaredEntityInAttValue(final XMLName xmlName) {
        this.entityNotDeclared(xmlName);
    }
    
    protected void entityNotDeclared(final XMLName xmlName) {
    }
    
    public void pushElement() {
        if (super.elementDepth == this.fElementTypeStack.length) {
            this.growElements();
        }
        super.elementType = this.fElementTypeStack[super.elementDepth];
        if (super.elementType == null) {
            super.elementType = new QName();
        }
        this.fElementTypeStack[super.elementDepth++] = super.currentElement;
    }
    
    public QName popElement() {
        final QName[] fElementTypeStack = this.fElementTypeStack;
        final int elementDepth = super.elementDepth - 1;
        super.elementDepth = elementDepth;
        return super.currentElement = fElementTypeStack[elementDepth];
    }
    
    private QName getFreeQName() {
        QName qName;
        if (this.fFreeQNameCount > 0) {
            final QName[] fFreeQNames = this.fFreeQNames;
            final int fFreeQNameCount = this.fFreeQNameCount - 1;
            this.fFreeQNameCount = fFreeQNameCount;
            qName = fFreeQNames[fFreeQNameCount];
            this.fFreeQNames[this.fFreeQNameCount] = null;
        }
        else {
            qName = new QName();
        }
        return qName;
    }
    
    private void saveFreeQName(final QName qName) {
        if (this.fFreeQNameCount == this.fFreeQNames.length) {
            final QName[] fFreeQNames = new QName[this.fFreeQNameCount << 1];
            System.arraycopy(this.fFreeQNames, 0, fFreeQNames, 0, this.fFreeQNameCount);
            this.fFreeQNames = fFreeQNames;
        }
        this.fFreeQNames[this.fFreeQNameCount++] = qName;
    }
    
    private void growElements() {
        final QName[] fElementTypeStack = new QName[super.elementDepth << 1];
        System.arraycopy(this.fElementTypeStack, 0, fElementTypeStack, 0, super.elementDepth);
        this.fElementTypeStack = fElementTypeStack;
    }
    
    private void growAttributes() {
        final QName[] attrNames = new QName[this.attrCount << 1];
        System.arraycopy(this.attrNames, 0, attrNames, 0, this.attrCount);
        this.attrNames = attrNames;
        final String[] attTypes = new String[this.attrCount << 1];
        System.arraycopy(this.attTypes, 0, attTypes, 0, this.attrCount);
        this.attTypes = attTypes;
        final XMLString[] attValues = new XMLString[this.attrCount << 1];
        System.arraycopy(this.attValues, 0, attValues, 0, this.attrCount);
        this.attValues = attValues;
        final boolean[] attValueNormalized = new boolean[this.attrCount << 1];
        System.arraycopy(this.attValueNormalized, 0, attValueNormalized, 0, this.attrCount);
        this.attValueNormalized = attValueNormalized;
    }
    
    private void initNamespaces() {
        this.prefixes = new String[8];
        this.namespaceURIs = new String[8];
        this.nsHandles = new int[8];
        this.nsDeclQNames = new String[8];
        this.fMappingScopes = new int[8];
    }
    
    private String internString(final String s) {
        return this.fSymbolTable.toString(this.fSymbolTable.addSymbol(s));
    }
    
    private void resetNamespaces() {
        this.nsDeclPrefix = this.internString("xmlns");
        this.fDefaultPrefix = this.internString("xml");
        this.prefixes[0] = this.fDefaultPrefix;
        this.nsHandles[0] = this.fSymbolTable.addSymbol("http://www.w3.org/XML/1998/namespace");
        this.namespaceURIs[0] = this.fSymbolTable.toString(this.nsHandles[0]);
        this.firstMapping = 1;
        this.lastMapping = 1;
        this.fCurrentScope = 1;
        this.fMappingScopes[0] = 0;
        this.fMappingScopes[1] = 1;
    }
    
    protected void createPrefixMapping(final String s, final int n, final String s2) {
        if (this.lastMapping == this.prefixes.length) {
            final String[] prefixes = new String[this.lastMapping << 1];
            System.arraycopy(this.prefixes, 0, prefixes, 0, this.lastMapping);
            this.prefixes = prefixes;
            final String[] namespaceURIs = new String[this.lastMapping << 1];
            System.arraycopy(this.namespaceURIs, 0, namespaceURIs, 0, this.lastMapping);
            this.namespaceURIs = namespaceURIs;
            final int[] nsHandles = new int[this.lastMapping << 1];
            System.arraycopy(this.nsHandles, 0, nsHandles, 0, this.lastMapping);
            this.nsHandles = nsHandles;
            final String[] nsDeclQNames = new String[this.lastMapping << 1];
            System.arraycopy(this.nsDeclQNames, 0, nsDeclQNames, 0, this.lastMapping);
            this.nsDeclQNames = nsDeclQNames;
        }
        this.prefixes[this.lastMapping] = s;
        this.nsHandles[this.lastMapping] = n;
        this.namespaceURIs[this.lastMapping] = this.fSymbolTable.toString(n);
        this.nsDeclQNames[this.lastMapping] = s2;
        this.fMappingScopes[this.fCurrentScope] = ++this.lastMapping;
    }
    
    protected boolean setNamespaceURI(final QName qName) {
        final String prefix = qName.prefix;
        for (int i = this.lastMapping - 1; i >= 0; --i) {
            if (this.prefixes[i] == prefix) {
                qName.nsHandle = this.nsHandles[i];
                qName.namespaceURI = this.namespaceURIs[i];
                return true;
            }
        }
        qName.nsHandle = 0;
        qName.namespaceURI = XMLString.EMPTY_STRING;
        return prefix == XMLString.EMPTY_STRING;
    }
    
    private void startNamespacesScope() {
        ++this.fCurrentScope;
        this.firstMapping = this.lastMapping;
        if (this.fCurrentScope == this.fMappingScopes.length) {
            final int[] fMappingScopes = new int[this.fCurrentScope << 1];
            System.arraycopy(this.fMappingScopes, 0, fMappingScopes, 0, this.fCurrentScope);
            this.fMappingScopes = fMappingScopes;
        }
        this.fMappingScopes[this.fCurrentScope] = this.lastMapping;
    }
    
    public void endNamespacesScope() {
        --this.fCurrentScope;
        this.lastMapping = this.fMappingScopes[this.fCurrentScope];
        this.firstMapping = this.fMappingScopes[this.fCurrentScope - 1];
    }
}
