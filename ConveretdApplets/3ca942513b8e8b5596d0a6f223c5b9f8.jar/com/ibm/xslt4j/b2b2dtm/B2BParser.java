// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.b2b2dtm;

import com.ibm.xml.b2b.scan.latin.LatinWFCDocumentScanner;
import com.ibm.xml.b2b.scan.utf16.UTF16WFCDocumentScanner;
import com.ibm.xml.b2b.scan.latin.LatinWFCExternalEntityScanner;
import com.ibm.xml.b2b.scan.DocumentEntityState;
import com.ibm.xml.b2b.scan.utf16.UTF16WFCExternalEntityScanner;
import com.ibm.xml.b2b.scan.latin.LatinWFCMarkupDeclScanner;
import com.ibm.xml.b2b.scan.MarkupDeclHandler;
import com.ibm.xml.b2b.scan.utf16.UTF16WFCMarkupDeclScanner;
import com.ibm.xml.b2b.scan.latin.LatinWFCInternalSubsetScanner;
import com.ibm.xml.b2b.scan.utf16.UTF16WFCInternalSubsetScanner;
import com.ibm.xml.b2b.scan.latin.LatinWFCDoctypeScanner;
import com.ibm.xml.b2b.scan.utf16.UTF16WFCDoctypeScanner;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.util.XMLName;
import org.apache.xml.utils.WrappedRuntimeException;
import org.xml.sax.Locator;
import org.xml.sax.SAXParseException;
import com.ibm.xml.b2b.util.MessageProvider;
import java.util.Locale;
import com.ibm.xml.b2b.util.MessageProviderRegistry;
import org.apache.xml.utils.SystemIDResolver;
import java.io.IOException;
import javax.xml.transform.stream.StreamSource;
import com.ibm.xml.b2b.util.entity.ByteArrayParsedEntityFactory;
import com.ibm.xml.b2b.util.entity.CharArrayParsedEntityFactory;
import java.util.Hashtable;
import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.util.entity.EntityDeclPool;
import com.ibm.xml.b2b.util.XMLStringBuffer;
import com.ibm.xml.b2b.scan.DTDParams;
import com.ibm.xml.b2b.util.entity.EntityManager;
import com.ibm.xml.b2b.util.entity.ParsedEntityFactory;
import com.ibm.xml.b2b.scan.ExternalEntityState;
import com.ibm.xml.b2b.util.SymbolTable;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.entity.EntityInputSource;
import com.ibm.xml.b2b.scan.DocumentScannerSupport;
import com.ibm.xml.b2b.scan.latin.LatinWFCExternalSubsetScanner;
import com.ibm.xml.b2b.scan.latin.LatinWFCScannerSupport;
import com.ibm.xml.b2b.scan.utf16.UTF16WFCExternalSubsetScanner;
import com.ibm.xml.b2b.scan.utf16.UTF16WFCScannerSupport;
import com.ibm.xml.b2b.util.ErrorReporter;
import com.ibm.xml.b2b.util.entity.EntityEventHandler;
import com.ibm.xml.b2b.util.entity.DTDEntityEventHandler;
import com.ibm.xml.b2b.scan.InternalSubsetHandler;
import com.ibm.xml.b2b.scan.ExternalSubsetHandler;
import com.ibm.xml.b2b.scan.DoctypeImplementationHandler;
import com.ibm.xml.b2b.scan.DoctypeEventHandler;
import com.ibm.xml.b2b.scan.DocumentImplementationHandler;
import com.ibm.xml.b2b.scan.DocumentEventHandler;
import com.ibm.xml.b2b.scan.ExternalEntityHandler;

final class B2BParser implements ExternalEntityHandler, DocumentEventHandler, DocumentImplementationHandler, DoctypeEventHandler, DoctypeImplementationHandler, ExternalSubsetHandler, InternalSubsetHandler, DTDEntityEventHandler, EntityEventHandler, ErrorReporter
{
    private static final boolean RETAIN_OBJECTS_ACROSS_RESET = true;
    private static final String IDSYMBOL;
    private B2B2DTM fDTM;
    private UTF16WFCScannerSupport fUTF16ScannerSupport;
    private UTF16WFCExternalSubsetScanner fUTF16ExternalSubsetScanner;
    private LatinWFCScannerSupport fLatinScannerSupport;
    private LatinWFCExternalSubsetScanner fLatinExternalSubsetScanner;
    private DocumentScannerSupport fScannerSupport;
    private EntityInputSource fEntityInputSource;
    private boolean fNeedReset;
    private XMLString fContent;
    private char[][] fCharBuffer;
    private int[] fCharBufferOffset;
    private boolean fInDTD;
    private SymbolTable fSymbolTable;
    private ExternalEntityState fExternalEntityState;
    private ParsedEntityFactory fEntityFactory;
    private EntityManager fEntityManager;
    private DTDParams fDTDParams;
    private DTDGrammar fDTDGrammar;
    private XMLStringBuffer fStringBuffer;
    private boolean fHaveExternalSubset;
    private EntityDeclPool fEntityDeclPool;
    private String fDoctypePublicID;
    private String fDoctypeSystemID;
    private QName fDeclElementType;
    private XMLString fContentModel;
    private int fContentModelOffset;
    private boolean fContentModelPCDATA;
    private QName fDeclAttrName;
    private XMLString fDeclAttType;
    private boolean fScannedDefaultAttValue;
    private int fDefaultAttValueOffset;
    private XMLString fDefaultAttValue;
    private int fEntityValueOffset;
    private XMLString fInternalEntityValue;
    private boolean fUsingReader;
    private Hashtable fUnparsedEntities;
    private SimpleXMLDeclChecker fXMLDeclChecker;
    
    public B2BParser(final boolean usingReader) {
        this.fUsingReader = usingReader;
        this.fSymbolTable = new SymbolTable();
        this.fExternalEntityState = new ExternalEntityState();
        this.fDTDParams = new DTDParams();
        if (usingReader) {
            this.fEntityFactory = new CharArrayParsedEntityFactory(true);
            this.fUTF16ScannerSupport = new UTF16WFCScannerSupport(this.fSymbolTable, this.fEntityFactory.createStringBuffer(), this);
            this.fScannerSupport = this.fUTF16ScannerSupport;
        }
        else {
            this.fEntityFactory = new ByteArrayParsedEntityFactory(true);
            this.fLatinScannerSupport = new LatinWFCScannerSupport(this.fSymbolTable, this.fEntityFactory.createStringBuffer(), this);
            this.fScannerSupport = this.fLatinScannerSupport;
        }
        this.fEntityManager = new EntityManager(this, this.fSymbolTable, this.fEntityFactory);
        this.fStringBuffer = this.fEntityFactory.createStringBuffer();
        this.fEntityInputSource = new EntityInputSource();
        this.fContent = new XMLString();
        this.fCharBuffer = new char[1][256];
        this.fCharBufferOffset = new int[1];
        this.fContentModel = new XMLString();
        this.fDefaultAttValue = new XMLString();
        this.fInternalEntityValue = new XMLString();
        this.fEntityManager.setDTDEntityEventHandler(this);
        this.fDTDGrammar = new DTDGrammar(this.fSymbolTable, this.fEntityFactory);
        this.fEntityDeclPool = this.fDTDGrammar.getEntityDeclPool();
        this.fStringBuffer = this.fDTDGrammar.getStringBuffer();
        this.fEntityManager.setEntityDeclPool(this.fDTDGrammar.getEntityDeclPool());
        this.fUnparsedEntities = new Hashtable();
        this.fXMLDeclChecker = new SimpleXMLDeclChecker();
    }
    
    public void setDTM(final B2B2DTM dtm) {
        this.fDTM = dtm;
    }
    
    public SymbolTable getSymbolTable() {
        return this.fSymbolTable;
    }
    
    public DocumentScannerSupport getScannerSupport() {
        return this.fScannerSupport;
    }
    
    public Hashtable getUnparsedEntities() {
        return this.fUnparsedEntities;
    }
    
    protected void reset(final boolean retainObjects) {
        this.fSymbolTable.reset(retainObjects);
        this.fExternalEntityState.reset();
        this.fEntityFactory.reset(retainObjects);
        this.fDTDParams.reset();
        this.fEntityManager.reset(retainObjects);
        this.fStringBuffer.reset(retainObjects);
        this.fHaveExternalSubset = false;
        this.fDTDGrammar.reset(retainObjects);
        this.fDoctypePublicID = null;
        this.fDoctypeSystemID = null;
        this.fContentModel.clear();
        this.fUnparsedEntities.clear();
        this.fScannerSupport.reset(retainObjects);
        if (this.fUsingReader) {
            if (this.fUTF16ExternalSubsetScanner != null) {
                this.fUTF16ExternalSubsetScanner.reset(retainObjects);
            }
        }
        else if (this.fLatinExternalSubsetScanner != null) {
            this.fLatinExternalSubsetScanner.reset(retainObjects);
        }
        this.fInDTD = false;
    }
    
    public boolean checkSource(final StreamSource source) throws IOException {
        return this.fXMLDeclChecker.checkXMLDecl(source);
    }
    
    public void parse(final StreamSource source) {
        String urlOfSource = source.getSystemId();
        if (null != urlOfSource) {
            try {
                urlOfSource = SystemIDResolver.getAbsoluteURI(urlOfSource);
            }
            catch (Exception e) {
                System.err.println("Can not absolutize URL: " + urlOfSource);
            }
        }
        this.fEntityInputSource.clear();
        this.fEntityInputSource.setSystemId(urlOfSource);
        this.fEntityInputSource.setPublicId(source.getPublicId());
        if (source.getInputStream() != null) {
            this.fEntityInputSource.setByteStream(source.getInputStream());
        }
        if (source.getReader() != null) {
            this.fEntityInputSource.setCharacterStream(source.getReader());
        }
        if (this.fNeedReset) {
            this.reset(true);
        }
        this.fNeedReset = true;
        this.fDTM.startDocument();
        this.fEntityManager.scanDocumentEntity(this.fEntityInputSource);
        this.fDTM.endDocument();
    }
    
    public void reportWarning(final String uri, final int errorCode, final Object[] args) {
        final MessageProvider mp = MessageProviderRegistry.getMessageProvider(uri);
        final String msg = mp.createMessage(null, errorCode, args);
        System.err.println(msg);
    }
    
    public void reportRecoverableError(final String uri, final int errorCode, final Object[] args) {
        final MessageProvider mp = MessageProviderRegistry.getMessageProvider(uri);
        final String msg = mp.createMessage(null, errorCode, args);
        final SAXParseException spe = new SAXParseException(msg, (Locator)null);
        throw new WrappedRuntimeException(spe);
    }
    
    public void reportFatalError(final String uri, final int errorCode, final Object[] args) {
        final MessageProvider mp = MessageProviderRegistry.getMessageProvider(uri);
        final String msg = mp.createMessage(null, errorCode, args);
        final SAXParseException spe = new SAXParseException(msg, (Locator)null);
        throw new WrappedRuntimeException(spe);
    }
    
    public void xmlDeclEvent() {
        final XMLString standalone = this.fExternalEntityState.standalone;
        if (standalone != null && standalone.offset + 3 == standalone.endOffset) {
            this.fEntityManager.setStandalone();
        }
    }
    
    public void textDeclEvent() {
    }
    
    public void startElementEvent(final boolean isEmpty) {
        final QName elementType = this.fScannerSupport.currentElement;
        if (this.fDTDGrammar != null && this.fDTDGrammar.hasAttDefs()) {
            this.fDTDGrammar.addDefaultAttributes(this.fScannerSupport, elementType.handle);
        }
        this.fScannerSupport.resolveNamespaceURIs();
        final int elemNode = this.fDTM.createElementNode(elementType);
        this.fDTM.createNamespaceNodes(elemNode);
        for (int attrCount = this.fScannerSupport.attrCount, i = 0; i < attrCount; ++i) {
            final XMLString attValue = this.fScannerSupport.attValues[i];
            if (!this.fScannerSupport.attValueNormalized[i]) {
                this.fScannerSupport.normalizeAttributeValue(i);
            }
            this.fDTM.createAttributeNode(elemNode, this.fScannerSupport.attrNames[i], this.fScannerSupport.attTypes[i] == B2BParser.IDSYMBOL, attValue.toString());
        }
        this.fDTM.finishStartElement(elemNode);
        if (isEmpty) {
            this.fDTM.endElement(elementType.namespaceURI, elementType.localPart, elementType.str);
        }
    }
    
    public void endElementEvent() {
        final QName elementType = this.fScannerSupport.currentElement;
        this.fDTM.endElement(elementType.namespaceURI, elementType.localPart, elementType.str);
    }
    
    public void characters(final XMLString content) {
        if (this.fUsingReader) {
            final int offset = content.offset;
            final int length = content.endOffset - offset;
            this.fDTM.characters(content.chars, offset, length);
        }
        else {
            this.fCharBufferOffset[0] = 0;
            content.getChars(this.fCharBuffer, this.fCharBufferOffset);
            final int length = this.fCharBufferOffset[0];
            this.fDTM.characters(this.fCharBuffer[0], 0, length);
        }
    }
    
    public void character(final int ch, final boolean predefinedEntity) {
        this.fDTM.character(ch);
    }
    
    public void processingInstruction(final XMLName target, final XMLString content) {
        this.fSymbolTable.addSymbol(target);
        this.fDTM.processingInstruction(target.handle, content.toString());
    }
    
    public boolean scanDoctypeDecl(final ParsedEntity entityContent) {
        this.fDTDParams.push(entityContent);
        boolean result;
        if (this.fUsingReader) {
            result = UTF16WFCDoctypeScanner.scanDoctypeDecl(this, this, this.fUTF16ScannerSupport, this.fDTDParams, entityContent);
        }
        else {
            result = LatinWFCDoctypeScanner.scanDoctypeDecl(this, this, this.fLatinScannerSupport, this.fDTDParams, entityContent);
        }
        this.fDTDParams.pop();
        return result;
    }
    
    public boolean scanExternalSubset() {
        final boolean result = !this.fHaveExternalSubset || this.fEntityManager.scanExternalSubset(this.fDoctypePublicID, this.fDoctypeSystemID);
        this.fInDTD = false;
        return result;
    }
    
    public void startCDATA() {
        this.fDTM.startCDATA();
    }
    
    public void endCDATA() {
        this.fDTM.endCDATA();
    }
    
    public void comment(final XMLString content) {
        if (this.fInDTD) {
            return;
        }
        this.fDTM.comment(content.toString());
    }
    
    public boolean entityReferenceInContent(final XMLName entityName) {
        final int entityNameHandle = this.fSymbolTable.addSymbol(entityName);
        return this.fEntityManager.entityReferenceInContent(entityNameHandle);
    }
    
    public boolean entityReferenceInAttValue(final XMLName entityName) {
        final int entityNameHandle = this.fSymbolTable.addSymbol(entityName);
        return this.fEntityManager.entityReferenceInAttValue(entityNameHandle);
    }
    
    public void doctype(final QName rootElementType, final XMLString publicID, final XMLString systemID, final boolean internalSubset) {
        this.fHaveExternalSubset = (systemID != null);
        if (publicID != null) {
            this.fStringBuffer.normalizePublicID(publicID);
        }
        final int pubID = (publicID != null) ? this.fSymbolTable.addSymbol(publicID) : -1;
        final int sysID = (systemID != null) ? this.fSymbolTable.addSymbol(systemID) : -1;
        if (internalSubset || this.fHaveExternalSubset) {
            this.fDTDGrammar.setDescription(rootElementType, pubID, sysID);
            if (sysID != -1) {
                this.fDoctypePublicID = this.fSymbolTable.toString(pubID);
                this.fDoctypeSystemID = this.fSymbolTable.toString(sysID);
            }
        }
        this.fInDTD = true;
    }
    
    public boolean scanInternalSubset(final ParsedEntity entityContent) {
        this.fDTDParams.push(entityContent);
        boolean result;
        if (this.fUsingReader) {
            result = UTF16WFCInternalSubsetScanner.scanInternalSubset(this, this.fUTF16ScannerSupport, this.fDTDParams, entityContent);
        }
        else {
            result = LatinWFCInternalSubsetScanner.scanInternalSubset(this, this.fLatinScannerSupport, this.fDTDParams, entityContent);
        }
        this.fDTDParams.pop();
        return result;
    }
    
    public void startElementDecl(final QName elementType) {
        this.fDeclElementType = elementType;
        this.fContentModel.clear();
        this.fContentModelOffset = -1;
        this.fContentModelPCDATA = false;
    }
    
    public void contentModelANY() {
        this.fContentModel.str = "ANY";
    }
    
    public void contentModelEMPTY() {
        this.fContentModel.str = "EMPTY";
    }
    
    public void contentModelStartGroup() {
        if (this.fContentModelOffset == -1) {
            this.fContentModelOffset = this.fStringBuffer.getOffset();
        }
        this.fStringBuffer.append(40);
    }
    
    public void contentModelPCDATA() {
        this.fStringBuffer.append("#PCDATA");
        this.fContentModelPCDATA = true;
    }
    
    public void contentModelElement(final QName elementName) {
        this.fStringBuffer.append(elementName);
    }
    
    public void contentModelSeparator(final int separator) {
        if (separator == 0) {
            this.fStringBuffer.append(124);
        }
        else {
            this.fStringBuffer.append(44);
        }
    }
    
    public void contentModelOccurrence(final int occurrence) {
        if (occurrence == 0) {
            this.fStringBuffer.append(63);
        }
        else if (occurrence == 1) {
            this.fStringBuffer.append(42);
        }
        else {
            this.fStringBuffer.append(43);
        }
    }
    
    public void contentModelEndGroup() {
        this.fStringBuffer.append(41);
    }
    
    public void endElementDecl() {
        if (this.fContentModelOffset != -1) {
            if (!this.fContentModelPCDATA) {
                this.fDTDGrammar.setElementContentElement(this.fSymbolTable.addSymbol(this.fDeclElementType));
            }
            this.fStringBuffer.setStringValues(this.fContentModelOffset, this.fStringBuffer.getOffset(), this.fContentModel);
        }
    }
    
    public void startAttlistDecl(final QName elementType) {
        this.fDeclElementType = elementType;
    }
    
    public void startAttDef(final QName attrName, final XMLString attType) {
        this.fDeclAttrName = attrName;
        this.fDeclAttType = attType;
        this.fScannedDefaultAttValue = false;
        this.fDTDGrammar.startAttDef(attrName, attType);
    }
    
    public void startEnumerationType() {
    }
    
    public void enumerationType(final XMLString type) {
        this.fDTDGrammar.enumerationType(type);
    }
    
    public void endEnumerationType() {
    }
    
    public void startDefaultAttValue() {
        this.fScannedDefaultAttValue = true;
        this.fDefaultAttValueOffset = -1;
    }
    
    public void defaultAttValueCharacters(final XMLString content) {
        if (content.offset < content.endOffset) {
            if (this.fDefaultAttValueOffset == -1) {
                this.fDefaultAttValueOffset = this.fStringBuffer.getOffset();
            }
            this.fStringBuffer.normalizedAppend(content);
        }
    }
    
    public void defaultAttValueCharacter(final int ch, final boolean predefinedEntity) {
        if (this.fDefaultAttValueOffset == -1) {
            this.fDefaultAttValueOffset = this.fStringBuffer.getOffset();
        }
        this.fStringBuffer.append(ch);
    }
    
    public boolean entityReferenceInDefaultAttValue(final XMLName entityName) {
        final int entityNameHandle = this.fSymbolTable.addSymbol(entityName);
        return this.fEntityManager.entityReferenceInDefaultAttValue(entityNameHandle);
    }
    
    public void endAttDef(final XMLString defaultType) {
        final QName elementType = this.fDeclElementType;
        final QName attrName = this.fDeclAttrName;
        final XMLString attType = this.fDeclAttType;
        if (this.fDTDGrammar.lookupAttDef(elementType, attrName) == -1) {
            this.fDTDGrammar.saveAttDef(elementType, attrName, attType, defaultType, this.getDefaultAttValueString(attType), this.fEntityManager.inExternalEntity());
        }
    }
    
    public void endAttlistDecl() {
    }
    
    private XMLString getDefaultAttValueString(final XMLString attType) {
        if (!this.fScannedDefaultAttValue) {
            return null;
        }
        int offset;
        int endOffset;
        if (this.fDefaultAttValueOffset == -1) {
            offset = 0;
            endOffset = 0;
        }
        else {
            offset = this.fDefaultAttValueOffset;
            endOffset = this.fStringBuffer.getOffset();
        }
        this.fStringBuffer.setStringValues(offset, endOffset, this.fDefaultAttValue);
        return this.fDefaultAttValue;
    }
    
    public void startEntityValue() {
        this.fEntityValueOffset = -1;
    }
    
    public void entityValueCharacters(final XMLString content) {
        if (content.offset < content.endOffset) {
            if (this.fEntityValueOffset == -1) {
                this.fEntityValueOffset = this.fStringBuffer.getOffset();
            }
            this.fStringBuffer.append(content);
        }
    }
    
    public void entityValueCharacter(final int ch) {
        if (this.fEntityValueOffset == -1) {
            this.fEntityValueOffset = this.fStringBuffer.getOffset();
        }
        this.fStringBuffer.append(ch);
    }
    
    public void entityReferenceInEntityValue(final XMLName entityName) {
        if (this.fEntityValueOffset == -1) {
            this.fEntityValueOffset = this.fStringBuffer.getOffset();
        }
        this.fStringBuffer.append(38);
        this.fStringBuffer.append(entityName);
        this.fStringBuffer.append(59);
    }
    
    public boolean peReferenceInEntityValue(final XMLName peName) {
        final int peNameHandle = this.fSymbolTable.addSymbol(peName);
        return this.fEntityManager.peReferenceInEntityValue(peNameHandle);
    }
    
    public void internalEntityDecl(final XMLName entityName) {
        final XMLString value = this.getInternalEntityValue();
        this.fEntityDeclPool.addInternalEntityDecl(entityName, value, this.fEntityManager);
    }
    
    public void externalEntityDecl(final XMLName entityName, final XMLString publicID, final XMLString systemID) {
        if (publicID != null) {
            this.fStringBuffer.normalizePublicID(publicID);
        }
        this.fEntityDeclPool.addExternalEntityDecl(entityName, publicID, systemID, this.fEntityManager);
    }
    
    public void unparsedEntityDecl(final XMLName entityName, final XMLString publicID, final XMLString systemID, final XMLName notationName) {
        if (publicID != null) {
            this.fStringBuffer.normalizePublicID(publicID);
        }
        this.fEntityDeclPool.addUnparsedEntityDecl(entityName);
        final String name = entityName.toString();
        if (this.fUnparsedEntities.get(name) == null) {
            final String uri = systemID.toString();
            this.fUnparsedEntities.put(name, uri);
        }
    }
    
    public void internalPEDecl(final XMLName peName) {
        final XMLString value = this.getInternalEntityValue();
        this.fEntityDeclPool.addInternalPEDecl(peName, value, this.fEntityManager);
    }
    
    public void externalPEDecl(final XMLName peName, final XMLString publicID, final XMLString systemID) {
        if (publicID != null) {
            this.fStringBuffer.normalizePublicID(publicID);
        }
        this.fEntityDeclPool.addExternalPEDecl(peName, publicID, systemID, this.fEntityManager);
    }
    
    private XMLString getInternalEntityValue() {
        int offset;
        int endOffset;
        if (this.fEntityValueOffset == -1) {
            offset = 0;
            endOffset = 0;
        }
        else {
            offset = this.fEntityValueOffset;
            endOffset = this.fStringBuffer.getOffset();
            this.fStringBuffer.append(0);
        }
        this.fStringBuffer.setStringValues(offset, endOffset, this.fInternalEntityValue);
        return this.fInternalEntityValue;
    }
    
    public void notationDecl(final XMLName notationName, final XMLString publicID, final XMLString systemID) {
        if (publicID != null) {
            this.fStringBuffer.normalizePublicID(publicID);
        }
    }
    
    public boolean internalSubsetPEReference(final XMLName peName) {
        final int peNameHandle = this.fSymbolTable.addSymbol(peName);
        return this.fEntityManager.peReference(peNameHandle);
    }
    
    public boolean externalSubsetPEReference(final XMLName peName) {
        final int peNameHandle = this.fSymbolTable.addSymbol(peName);
        return this.fEntityManager.peReference(peNameHandle);
    }
    
    public boolean startPEReferenceWithinMarkup(final XMLName peName, final int markupDepth) {
        final int peNameHandle = this.fSymbolTable.addSymbol(peName);
        return this.fEntityManager.startPEReferenceWithinMarkup(peNameHandle);
    }
    
    public void endPEReferenceWithinMarkup(final int markupDepth) {
        this.fDTDParams.pop();
        this.fEntityManager.endPEReferenceWithinMarkup();
    }
    
    public boolean scanExternalSubset(final ParsedEntity entityContent) {
        this.fDTDParams.push(entityContent);
        boolean result;
        if (this.fUsingReader) {
            if (this.fUTF16ExternalSubsetScanner == null) {
                this.fUTF16ExternalSubsetScanner = new UTF16WFCExternalSubsetScanner(this, this.fUTF16ScannerSupport);
            }
            result = this.fUTF16ExternalSubsetScanner.scanExternalSubset(this.fDTDParams, entityContent);
        }
        else {
            if (this.fLatinExternalSubsetScanner == null) {
                this.fLatinExternalSubsetScanner = new LatinWFCExternalSubsetScanner(this, this.fLatinScannerSupport);
            }
            result = this.fLatinExternalSubsetScanner.scanExternalSubset(this.fDTDParams, entityContent);
        }
        this.fDTDParams.pop();
        return result;
    }
    
    public boolean scanIntSubsetDecl(final int peName, final ParsedEntity entityContent) {
        this.fDTDParams.push(entityContent);
        boolean result;
        if (this.fUsingReader) {
            result = UTF16WFCInternalSubsetScanner.scanIntSubsetDecl(this, this.fUTF16ScannerSupport, this.fDTDParams, entityContent);
        }
        else {
            result = LatinWFCInternalSubsetScanner.scanIntSubsetDecl(this, this.fLatinScannerSupport, this.fDTDParams, entityContent);
        }
        this.fDTDParams.pop();
        return result;
    }
    
    public boolean scanExtSubsetDecl(final int peName, final ParsedEntity entityContent) {
        this.fDTDParams.push(entityContent);
        boolean result;
        if (this.fUsingReader) {
            if (this.fUTF16ExternalSubsetScanner == null) {
                this.fUTF16ExternalSubsetScanner = new UTF16WFCExternalSubsetScanner(this, this.fUTF16ScannerSupport);
            }
            result = this.fUTF16ExternalSubsetScanner.scanExtSubsetDecl(this.fDTDParams, entityContent);
        }
        else {
            if (this.fLatinExternalSubsetScanner == null) {
                this.fLatinExternalSubsetScanner = new LatinWFCExternalSubsetScanner(this, this.fLatinScannerSupport);
            }
            result = this.fLatinExternalSubsetScanner.scanExtSubsetDecl(this.fDTDParams, entityContent);
        }
        this.fDTDParams.pop();
        return result;
    }
    
    public boolean scanDefaultAttValue(final int entityName, final ParsedEntity entityContent) {
        this.fDTDParams.push(entityContent);
        boolean result;
        if (this.fUsingReader) {
            result = UTF16WFCMarkupDeclScanner.scanDefaultAttValue(this, this.fUTF16ScannerSupport, this.fDTDParams, entityContent, 0);
        }
        else {
            result = LatinWFCMarkupDeclScanner.scanDefaultAttValue(this, this.fLatinScannerSupport, this.fDTDParams, entityContent, 0);
        }
        this.fDTDParams.pop();
        return result;
    }
    
    public boolean scanEntityValue(final int peName, final ParsedEntity entityContent) {
        this.fDTDParams.push(entityContent);
        boolean result;
        if (this.fUsingReader) {
            result = UTF16WFCMarkupDeclScanner.scanEntityValue(this, this.fUTF16ScannerSupport, this.fDTDParams, entityContent, 0, false);
        }
        else {
            result = LatinWFCMarkupDeclScanner.scanEntityValue(this, this.fLatinScannerSupport, this.fDTDParams, entityContent, 0, false);
        }
        this.fDTDParams.pop();
        return result;
    }
    
    public boolean scanPEWithinMarkup(final int peName, final ParsedEntity entityContent) {
        this.fDTDParams.push(entityContent);
        if (this.fUsingReader) {
            return this.fUTF16ExternalSubsetScanner.scanPEWithinMarkup(this.fDTDParams, entityContent);
        }
        return this.fLatinExternalSubsetScanner.scanPEWithinMarkup(this.fDTDParams, entityContent);
    }
    
    public void defaultAttValueCharacter(final int entityName, final int ch, final boolean predefinedEntity) {
        this.defaultAttValueCharacter(ch, predefinedEntity);
    }
    
    public boolean scanXMLDecl(final ParsedEntity entityContent) {
        if (this.fUsingReader) {
            return UTF16WFCExternalEntityScanner.scanXMLDecl(this, this.fScannerSupport, this.fExternalEntityState, entityContent);
        }
        return LatinWFCExternalEntityScanner.scanXMLDecl(this, this.fScannerSupport, this.fExternalEntityState, entityContent);
    }
    
    public boolean scanTextDecl(final ParsedEntity entityContent) {
        if (this.fUsingReader) {
            return UTF16WFCExternalEntityScanner.scanTextDecl(this, this.fScannerSupport, this.fExternalEntityState, entityContent);
        }
        return LatinWFCExternalEntityScanner.scanTextDecl(this, this.fScannerSupport, this.fExternalEntityState, entityContent);
    }
    
    public boolean scanDocument(final ParsedEntity entityContent) {
        if (this.fUsingReader) {
            return UTF16WFCDocumentScanner.scanDocument(this, this, this.fScannerSupport, entityContent);
        }
        return LatinWFCDocumentScanner.scanDocument(this, this, this.fScannerSupport, entityContent);
    }
    
    public boolean resolveExternalEntity(final EntityInputSource source) {
        return true;
    }
    
    public void attributeValueCharacter(final int entityName, final int ch, final boolean predefinedEntity) {
        this.fScannerSupport.attributeValueCharacter(ch, predefinedEntity);
    }
    
    public void character(final int entityName, final int ch, final boolean predefinedEntity) {
        this.character(ch, predefinedEntity);
    }
    
    public boolean scanContent(final int entityName, final ParsedEntity entityContent) {
        if (this.fUsingReader) {
            return UTF16WFCDocumentScanner.scanContent(this, this, this.fScannerSupport, entityContent);
        }
        return LatinWFCDocumentScanner.scanContent(this, this, this.fScannerSupport, entityContent);
    }
    
    public boolean scanAttValue(final int entityName, final ParsedEntity entityContent) {
        if (this.fUsingReader) {
            return UTF16WFCDocumentScanner.scanAttValue(this, this, this.fScannerSupport, entityContent, 0);
        }
        return LatinWFCDocumentScanner.scanAttValue(this, this, this.fScannerSupport, entityContent, 0);
    }
    
    public boolean recursiveReferenceInContent(final int entityName, final String referencePath) {
        final String[] args = { this.fSymbolTable.toString(entityName), referencePath };
        this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 108, args);
        return false;
    }
    
    public boolean undeclaredEntityInContent(final String entityName) {
        final String[] args = { entityName };
        this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 47, args);
        return false;
    }
    
    public boolean skippedEntityInContent(final int entityName) {
        return true;
    }
    
    public boolean unparsedEntityInContent(final int entityName) {
        final String[] args = { this.fSymbolTable.toString(entityName) };
        this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 107, args);
        return false;
    }
    
    public boolean externallyDeclaredEntityInContent(final String entityName) {
        final String[] args = { entityName };
        this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 73, args);
        return true;
    }
    
    public boolean recursiveReferenceInAttValue(final int entityName, final String referencePath) {
        final String[] args = { this.fSymbolTable.toString(entityName), referencePath };
        this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 108, args);
        return false;
    }
    
    public boolean undeclaredEntityInAttValue(final String entityName) {
        final String[] args = { entityName };
        this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 47, args);
        return false;
    }
    
    public boolean externalEntityInAttValue(final int entityName) {
        final String[] args = { this.fSymbolTable.toString(entityName) };
        this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 105, args);
        return false;
    }
    
    public boolean externallyDeclaredEntityInAttValue(final String entityName) {
        final String[] args = { entityName };
        this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 73, args);
        return true;
    }
    
    public boolean skippedParameterEntity(final int peName) {
        return true;
    }
    
    public boolean recursiveReferenceInDefaultAttValue(final int entityName, final String referencePath) {
        final String[] args = { this.fSymbolTable.toString(entityName), referencePath };
        this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 108, args);
        return false;
    }
    
    public boolean undeclaredEntityInDefaultAttValue(final int entityName) {
        final String[] args = { this.fSymbolTable.toString(entityName) };
        this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 47, args);
        return false;
    }
    
    public boolean externalEntityInDefaultAttValue(final int entityName) {
        final String[] args = { this.fSymbolTable.toString(entityName) };
        this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 105, args);
        return false;
    }
    
    public boolean externallyDeclaredEntityInDefaultAttValue(final String entityName) {
        final String[] args = { entityName };
        this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 73, args);
        return true;
    }
    
    public boolean skippedExternalSubsetEntity() {
        return true;
    }
    
    public boolean recursivePEReference(final int peName, final String referencePath) {
        final String[] args = { this.fSymbolTable.toString(peName), referencePath };
        this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 109, args);
        return false;
    }
    
    public boolean undeclaredParameterEntity(final int peName) {
        final String[] args = { this.fSymbolTable.toString(peName) };
        this.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 106, args);
        return true;
    }
    
    static {
        IDSYMBOL = "ID".intern();
    }
}
