// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.dtd;

import org.w3c.dom.Node;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.utils.QName;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.apache.xerces.framework.XMLContentSpec;
import org.apache.xerces.validators.common.XMLAttributeDecl;
import org.apache.xerces.validators.common.XMLElementDecl;
import org.apache.xerces.utils.StringPool;
import org.apache.xerces.framework.XMLDTDScanner;
import org.apache.xerces.validators.common.Grammar;

public class DTDGrammar extends Grammar implements XMLDTDScanner.EventHandler
{
    private static final int CHUNK_SHIFT = 8;
    private static final int CHUNK_SIZE = 256;
    private static final int CHUNK_MASK = 255;
    private static final int INITIAL_CHUNK_COUNT = 4;
    private StringPool fStringPool;
    private XMLElementDecl fElementDecl;
    private XMLAttributeDecl fAttributeDecl;
    private XMLContentSpec fContentSpec;
    private Document fGrammarDocument;
    private Element fRootElement;
    private QName fRootElementQName;
    private Element fCurrentElement;
    private int[][] fElementDeclIsExternal;
    private int[][] fElementDeclMap;
    private int[][] fAttributeDeclIsExternal;
    private int[][] fAttributeDeclMap;
    private int[][] fContentSpecMap;
    private QName fQName;
    private XMLContentSpec fTempContentSpec;
    
    public DTDGrammar(final StringPool stringPool) {
        this.fElementDecl = new XMLElementDecl();
        this.fAttributeDecl = new XMLAttributeDecl();
        this.fContentSpec = new XMLContentSpec();
        this.fRootElementQName = new QName();
        this.fElementDeclIsExternal = new int[4][];
        this.fElementDeclMap = new int[4][];
        this.fAttributeDeclIsExternal = new int[4][];
        this.fAttributeDeclMap = new int[4][];
        this.fContentSpecMap = new int[4][];
        this.fQName = new QName();
        this.fTempContentSpec = new XMLContentSpec();
        this.reset(stringPool);
    }
    
    public void reset(final StringPool fStringPool) {
        this.fStringPool = fStringPool;
    }
    
    public void callStartDTD() throws Exception {
        this.setGrammarDocument(null);
        this.fGrammarDocument = new DocumentImpl();
        this.fRootElement = this.fGrammarDocument.createElement("dtd");
        this.fCurrentElement = this.fRootElement;
    }
    
    public void callEndDTD() throws Exception {
        this.setGrammarDocument(this.fGrammarDocument);
    }
    
    public void callTextDecl(final int n, final int n2) throws Exception {
        final Element element = this.fGrammarDocument.createElement("textDecl");
        element.setAttribute("version", this.fStringPool.toString(n));
        element.setAttribute("encoding", this.fStringPool.toString(n2));
        this.fCurrentElement.appendChild(element);
    }
    
    public void doctypeDecl(final QName values, final int n, final int n2) throws Exception {
        final Element element = this.fGrammarDocument.createElement("doctypeDecl");
        element.setAttribute("name", this.fStringPool.toString(values.rawname));
        if (values.uri != 0) {
            element.setAttribute("xmlns:" + this.fStringPool.toString(values.prefix), this.fStringPool.toString(values.uri));
        }
        element.setAttribute("publicId", this.fStringPool.toString(n));
        element.setAttribute("systemId", this.fStringPool.toString(n2));
        this.fCurrentElement.appendChild(element);
        this.fRootElementQName.setValues(values);
    }
    
    public void startReadingFromExternalSubset(final int n, final int n2) throws Exception {
        final Element element = this.fGrammarDocument.createElement("external");
        element.setAttribute("publicId", this.fStringPool.toString(n));
        element.setAttribute("systemId", this.fStringPool.toString(n2));
        this.fCurrentElement.appendChild(element);
        this.fCurrentElement = element;
    }
    
    public void stopReadingFromExternalSubset() throws Exception {
        this.fCurrentElement = (Element)this.fCurrentElement.getParentNode();
    }
    
    public int addElementDecl(final QName values) throws Exception {
        final Element element = this.fGrammarDocument.createElement("elementDecl");
        element.setAttribute("name", this.fStringPool.toString(values.localpart));
        if (values.uri != 0) {
            element.setAttribute("xmlns:" + this.fStringPool.toString(values.prefix), this.fStringPool.toString(values.uri));
        }
        this.fCurrentElement.appendChild(element);
        final int elementDecl = this.createElementDecl();
        this.fElementDecl.clear();
        this.fElementDecl.name.setValues(values);
        this.setElementDecl(elementDecl, this.fElementDecl);
        return elementDecl;
    }
    
    public int addElementDecl(final QName values, final int type, final int contentSpecIndex, final boolean b) throws Exception {
        final Element element = this.fGrammarDocument.createElement("elementDecl");
        element.setAttribute("name", this.fStringPool.toString(values.localpart));
        if (values.uri != 0) {
            element.setAttribute("xmlns:" + this.fStringPool.toString(values.prefix), this.fStringPool.toString(values.uri));
        }
        element.setAttribute("type", this.fStringPool.toString(type));
        this.fCurrentElement.appendChild(element);
        final int elementDecl = this.createElementDecl();
        this.fElementDecl.clear();
        this.fElementDecl.name.setValues(values);
        this.fElementDecl.type = type;
        this.fElementDecl.contentSpecIndex = contentSpecIndex;
        this.setElementDecl(elementDecl, this.fElementDecl);
        final int n = elementDecl >> 8;
        final int n2 = elementDecl & 0xFF;
        this.ensureElementDeclCapacity(n);
        this.fElementDeclIsExternal[n][n2] = (b ? 1 : 0);
        return elementDecl;
    }
    
    protected void putElementNameMapping(final QName qName, final int n, final int n2) {
        this.fQName.uri = 0;
        this.fQName.localpart = qName.rawname;
        super.putElementNameMapping(this.fQName, n, n2);
    }
    
    public int getElementDeclIndex(final QName qName, final int n) {
        return super.getElementDeclIndex(qName.rawname, -1);
    }
    
    public void setElementDeclDTD(final int n, final XMLElementDecl xmlElementDecl) {
        super.setElementDecl(n, xmlElementDecl);
    }
    
    public void setElementDeclIsExternal(final int n, final boolean b) {
        final int n2 = n >> 8;
        final int n3 = n & 0xFF;
        this.ensureElementDeclCapacity(n2);
        this.fElementDeclIsExternal[n2][n3] = (b ? 1 : 0);
    }
    
    public boolean getElementDeclIsExternal(final int n) {
        return n >= 0 && this.fElementDeclIsExternal[n >> 8][n & 0xFF] != 0;
    }
    
    public boolean getAttributeDeclIsExternal(final int n) {
        return n >= 0 && this.fAttributeDeclIsExternal[n >> 8][n & 0xFF] != 0;
    }
    
    public boolean getRootElementQName(final QName qName) {
        if (this.fRootElementQName.rawname == -1) {
            return false;
        }
        qName.setValues(this.fRootElementQName);
        return true;
    }
    
    public int addAttDef(final QName qName, final QName values, final int type, final boolean list, final int enumeration, final int defaultType, final int n, final boolean b) throws Exception {
        final Element element = this.fGrammarDocument.createElement("attributeDecl");
        element.setAttribute("element", this.fStringPool.toString(qName.localpart));
        element.setAttribute("name", this.fStringPool.toString(values.localpart));
        if (values.uri != 0) {
            element.setAttribute("xmlns:" + this.fStringPool.toString(values.prefix), this.fStringPool.toString(values.uri));
        }
        element.setAttribute("type", this.fStringPool.toString(type));
        element.setAttribute("defaultType", this.fStringPool.toString(defaultType));
        element.setAttribute("defaultValue", this.fStringPool.toString(n));
        this.fCurrentElement.appendChild(element);
        final int attributeDecl = this.createAttributeDecl();
        switch (type) {
            case 0: {}
            case 1: {}
            case 2: {}
            case 3: {}
            case 4: {}
            case 5: {}
        }
        this.fAttributeDecl.clear();
        this.fAttributeDecl.name.setValues(values);
        this.fAttributeDecl.type = type;
        this.fAttributeDecl.list = list;
        this.fAttributeDecl.enumeration = enumeration;
        this.fAttributeDecl.defaultType = defaultType;
        this.fAttributeDecl.defaultValue = this.fStringPool.toString(n);
        this.setAttributeDecl(this.getElementDeclIndex(qName, -1), attributeDecl, this.fAttributeDecl);
        final int n2 = attributeDecl >> 8;
        final int n3 = attributeDecl & 0xFF;
        this.ensureAttributeDeclCapacity(n2);
        this.fAttributeDeclIsExternal[n2][n3] = (b ? 1 : 0);
        return attributeDecl;
    }
    
    public int addUniqueLeafNode(final int n) throws Exception {
        final int contentSpec = this.createContentSpec();
        this.fContentSpec.setValues(0, n, -1);
        this.setContentSpec(contentSpec, this.fContentSpec);
        return contentSpec;
    }
    
    public int addContentSpecNode(final int n, final int n2) throws Exception {
        final int contentSpec = this.createContentSpec();
        this.fContentSpec.setValues(n, n2, -1);
        this.setContentSpec(contentSpec, this.fContentSpec);
        return contentSpec;
    }
    
    public int addContentSpecNode(final int n, final int n2, final int n3) throws Exception {
        final int contentSpec = this.createContentSpec();
        this.fContentSpec.setValues(n, n2, n3);
        this.setContentSpec(contentSpec, this.fContentSpec);
        return contentSpec;
    }
    
    public String getContentSpecNodeAsString(final int n) throws Exception {
        return XMLContentSpec.toString(this, this.fStringPool, n);
    }
    
    public boolean startEntityDecl(final boolean b, final int n) throws Exception {
        final Element element = this.fGrammarDocument.createElement("entityDecl");
        element.setAttribute("name", this.fStringPool.toString(n));
        element.setAttribute("parameter", b ? "true" : "false");
        this.fCurrentElement.appendChild(element);
        this.fCurrentElement = element;
        return true;
    }
    
    public void endEntityDecl() throws Exception {
        this.fCurrentElement = (Element)this.fCurrentElement.getParentNode();
    }
    
    public int addInternalPEDecl(final int n, final int n2) throws Exception {
        final Element element = this.fGrammarDocument.createElement("internalPEDecl");
        element.setAttribute("name", this.fStringPool.toString(n));
        element.setAttribute("value", this.fStringPool.toString(n2));
        this.fCurrentElement.appendChild(element);
        return -1;
    }
    
    public int addExternalPEDecl(final int n, final int n2, final int n3) throws Exception {
        final Element element = this.fGrammarDocument.createElement("externalPEDecl");
        element.setAttribute("name", this.fStringPool.toString(n));
        element.setAttribute("publicId", this.fStringPool.toString(n2));
        element.setAttribute("systemId", this.fStringPool.toString(n3));
        this.fCurrentElement.appendChild(element);
        return -1;
    }
    
    public int addInternalEntityDecl(final int n, final int n2) throws Exception {
        final Element element = this.fGrammarDocument.createElement("internalEntityDecl");
        element.setAttribute("name", this.fStringPool.toString(n));
        element.setAttribute("value", this.fStringPool.toString(n2));
        this.fCurrentElement.appendChild(element);
        return -1;
    }
    
    public int addExternalEntityDecl(final int n, final int n2, final int n3) throws Exception {
        final Element element = this.fGrammarDocument.createElement("externalEntityDecl");
        element.setAttribute("name", this.fStringPool.toString(n));
        element.setAttribute("publicId", this.fStringPool.toString(n2));
        element.setAttribute("systemId", this.fStringPool.toString(n3));
        this.fCurrentElement.appendChild(element);
        return -1;
    }
    
    public int addUnparsedEntityDecl(final int n, final int n2, final int n3, final int n4) throws Exception {
        final Element element = this.fGrammarDocument.createElement("unparsedEntityDecl");
        element.setAttribute("name", this.fStringPool.toString(n));
        element.setAttribute("publicId", this.fStringPool.toString(n2));
        element.setAttribute("systemId", this.fStringPool.toString(n3));
        element.setAttribute("notation", this.fStringPool.toString(n4));
        this.fCurrentElement.appendChild(element);
        return -1;
    }
    
    public int startEnumeration() throws Exception {
        final Element element = this.fGrammarDocument.createElement("enumeration");
        this.fCurrentElement.appendChild(element);
        this.fCurrentElement = element;
        return this.fStringPool.startStringList();
    }
    
    public void addNameToEnumeration(final int n, final int n2, final int n3, final int n4, final boolean b) throws Exception {
        final Element element = this.fGrammarDocument.createElement("literal");
        element.setAttribute("element", this.fStringPool.toString(n2));
        element.setAttribute("attribute", this.fStringPool.toString(n3));
        element.setAttribute("name", this.fStringPool.toString(n4));
        element.setAttribute("notation", b ? "true" : "false");
        this.fCurrentElement.appendChild(element);
        this.fStringPool.addStringToList(n, n4);
    }
    
    public void endEnumeration(final int n) throws Exception {
        this.fCurrentElement = (Element)this.fCurrentElement.getParentNode();
        this.fStringPool.finishStringList(n);
    }
    
    public int addNotationDecl(final int n, final int n2, final int n3) throws Exception {
        final Element element = this.fGrammarDocument.createElement("notationDecl");
        element.setAttribute("name", this.fStringPool.toString(n));
        element.setAttribute("publicId", this.fStringPool.toString(n2));
        element.setAttribute("systemId", this.fStringPool.toString(n3));
        this.fCurrentElement.appendChild(element);
        return -1;
    }
    
    public void callComment(final int n) throws Exception {
    }
    
    public void callProcessingInstruction(final int n, final int n2) throws Exception {
        this.fCurrentElement.appendChild(this.fGrammarDocument.createProcessingInstruction(this.fStringPool.toString(n), this.fStringPool.toString(n2)));
    }
    
    public void internalSubset(final int n) throws Exception {
    }
    
    protected boolean isDTD() {
        return true;
    }
    
    private boolean ensureElementDeclCapacity(final int n) {
        try {
            return this.fElementDeclMap[n][0] == 0;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            this.fElementDeclMap = this.resize(this.fElementDeclMap, this.fElementDeclMap.length * 2);
            this.fElementDeclIsExternal = this.resize(this.fElementDeclIsExternal, this.fElementDeclIsExternal.length * 2);
        }
        catch (NullPointerException ex2) {}
        this.fElementDeclMap[n] = new int[256];
        this.fElementDeclIsExternal[n] = new int[256];
        return true;
    }
    
    private boolean ensureAttributeDeclCapacity(final int n) {
        try {
            return this.fAttributeDeclMap[n][0] == 0;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            this.fAttributeDeclMap = this.resize(this.fAttributeDeclMap, this.fAttributeDeclMap.length * 2);
            this.fAttributeDeclIsExternal = this.resize(this.fAttributeDeclIsExternal, this.fAttributeDeclIsExternal.length * 2);
        }
        catch (NullPointerException ex2) {}
        this.fAttributeDeclMap[n] = new int[256];
        this.fAttributeDeclIsExternal[n] = new int[256];
        return true;
    }
    
    private boolean ensureContentSpecCapacity(final int n) {
        try {
            return this.fContentSpecMap[n][0] == 0;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            this.fContentSpecMap = this.resize(this.fContentSpecMap, this.fContentSpecMap.length * 2);
        }
        catch (NullPointerException ex2) {}
        this.fContentSpecMap[n] = new int[256];
        return true;
    }
    
    private int[][] resize(final int[][] array, final int n) {
        final int[][] array2 = new int[n][];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
}
