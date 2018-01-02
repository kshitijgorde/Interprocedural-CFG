// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.readers;

import org.xml.sax.helpers.LocatorImpl;
import java.util.Vector;
import org.apache.xerces.utils.QName;
import java.io.UnsupportedEncodingException;
import org.apache.xerces.utils.XMLCharacterProperties;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.io.File;
import org.apache.xerces.utils.URI;
import org.xml.sax.Locator;
import org.xml.sax.InputSource;
import java.util.Stack;
import org.xml.sax.EntityResolver;
import org.apache.xerces.framework.XMLErrorReporter;
import org.apache.xerces.utils.StringPool;

public class DefaultEntityHandler implements XMLEntityHandler, DTDHandler
{
    private ReaderState fReaderStateFreeList;
    private StringPool fStringPool;
    private EventHandler fEventHandler;
    private CharDataHandler fCharDataHandler;
    private XMLErrorReporter fErrorReporter;
    private EntityResolver fResolver;
    private EntityPool fEntityPool;
    private EntityPool fParameterEntityPool;
    private byte[] fEntityTypeStack;
    private int[] fEntityNameStack;
    private int fEntityStackDepth;
    private Stack fReaderStack;
    private EntityReader fReader;
    private InputSource fSource;
    private int fEntityName;
    private int fEntityType;
    private int fEntityContext;
    private String fPublicId;
    private String fSystemId;
    private int fReaderId;
    private int fReaderDepth;
    private int fNextReaderId;
    private NullReader fNullReader;
    protected XMLEntityReaderFactory fReaderFactory;
    private boolean fSendCharDataAsCharArray;
    static final int CHUNK_SHIFT = 5;
    static final int CHUNK_SIZE = 32;
    static final int CHUNK_MASK = 31;
    static final int INITIAL_CHUNK_COUNT = 32;
    
    public DefaultEntityHandler(final StringPool fStringPool, final XMLErrorReporter fErrorReporter) {
        this.fReaderStateFreeList = null;
        this.fStringPool = null;
        this.fEventHandler = null;
        this.fCharDataHandler = null;
        this.fErrorReporter = null;
        this.fResolver = null;
        this.fEntityPool = null;
        this.fParameterEntityPool = null;
        this.fEntityTypeStack = null;
        this.fEntityNameStack = null;
        this.fEntityStackDepth = 0;
        this.fReaderStack = new Stack();
        this.fReader = null;
        this.fSource = null;
        this.fEntityName = -1;
        this.fEntityType = -1;
        this.fEntityContext = -1;
        this.fPublicId = null;
        this.fSystemId = null;
        this.fReaderId = -1;
        this.fReaderDepth = -1;
        this.fNextReaderId = 0;
        this.fNullReader = null;
        this.fReaderFactory = null;
        this.fSendCharDataAsCharArray = false;
        this.fStringPool = fStringPool;
        this.fErrorReporter = fErrorReporter;
        this.fReaderFactory = new DefaultReaderFactory();
        this.fEntityPool = new EntityPool(this.fStringPool, this.fErrorReporter, true);
    }
    
    public void setEventHandler(final EventHandler fEventHandler) {
        this.fEventHandler = fEventHandler;
    }
    
    public void setCharDataHandler(final CharDataHandler fCharDataHandler) {
        this.fCharDataHandler = fCharDataHandler;
    }
    
    public CharDataHandler getCharDataHandler() {
        return this.fCharDataHandler;
    }
    
    public void setSendCharDataAsCharArray(final boolean fSendCharDataAsCharArray) {
        this.fSendCharDataAsCharArray = fSendCharDataAsCharArray;
        this.fReaderFactory.setSendCharDataAsCharArray(this.fSendCharDataAsCharArray);
    }
    
    public void setReaderFactory(final XMLEntityReaderFactory fReaderFactory) {
        (this.fReaderFactory = fReaderFactory).setSendCharDataAsCharArray(this.fSendCharDataAsCharArray);
    }
    
    public void reset(final StringPool fStringPool) {
        this.fStringPool = fStringPool;
        this.fEntityPool.reset(this.fStringPool);
        this.fParameterEntityPool = null;
        this.fReaderStack.removeAllElements();
        this.fEntityStackDepth = 0;
        this.fReader = null;
        this.fSource = null;
        this.fEntityName = -1;
        this.fEntityType = -1;
        this.fEntityContext = -1;
        this.fPublicId = null;
        this.fSystemId = null;
        this.fReaderId = -1;
        this.fReaderDepth = -1;
        this.fNextReaderId = 0;
    }
    
    public void setAllowJavaEncodings(final boolean allowJavaEncodingName) {
        this.fReaderFactory.setAllowJavaEncodingName(allowJavaEncodingName);
    }
    
    public boolean getAllowJavaEncodings() {
        return this.fReaderFactory.getAllowJavaEncodingName();
    }
    
    public int addInternalPEDecl(final int n, final int n2, final boolean b) throws Exception {
        if (this.fParameterEntityPool == null) {
            this.fParameterEntityPool = new EntityPool(this.fStringPool, this.fErrorReporter, false);
        }
        return this.fParameterEntityPool.addEntityDecl(n, n2, -1, -1, -1, -1, b);
    }
    
    public int addExternalPEDecl(final int n, final int n2, final int n3, final boolean b) throws Exception {
        if (this.fParameterEntityPool == null) {
            this.fParameterEntityPool = new EntityPool(this.fStringPool, this.fErrorReporter, false);
        }
        return this.fParameterEntityPool.addEntityDecl(n, -1, n2, n3, this.fStringPool.addSymbol(this.fSystemId), -1, b);
    }
    
    public int addInternalEntityDecl(final int n, final int n2, final boolean b) throws Exception {
        return this.fEntityPool.addEntityDecl(n, n2, -1, -1, -1, -1, b);
    }
    
    public int addExternalEntityDecl(final int n, final int n2, final int n3, final boolean b) throws Exception {
        return this.fEntityPool.addEntityDecl(n, -1, n2, n3, this.fStringPool.addSymbol(this.fSystemId), -1, b);
    }
    
    public int addUnparsedEntityDecl(final int n, final int n2, final int n3, final int n4, final boolean b) throws Exception {
        final int addEntityDecl = this.fEntityPool.addEntityDecl(n, -1, n2, n3, this.fStringPool.addSymbol(this.fSystemId), n4, b);
        if (!this.fEntityPool.isNotationDeclared(n4)) {
            this.fEntityPool.addRequiredNotation(n4, this.fErrorReporter.getLocator(), 114, 89, new Object[] { this.fStringPool.toString(n), this.fStringPool.toString(n4) });
        }
        return addEntityDecl;
    }
    
    public int addNotationDecl(final int n, final int n2, final int n3, final boolean b) throws Exception {
        return this.fEntityPool.addNotationDecl(n, n2, n3, this.fStringPool.addSymbol(this.fSystemId), b);
    }
    
    public boolean isUnparsedEntity(final int n) {
        final int lookupEntity = this.fEntityPool.lookupEntity(n);
        return lookupEntity != -1 && this.fEntityPool.isUnparsedEntity(lookupEntity);
    }
    
    public boolean isNotationDeclared(final int n) {
        return this.fEntityPool.isNotationDeclared(n);
    }
    
    public void addRequiredNotation(final int n, final Locator locator, final int n2, final int n3, final Object[] array) {
        this.fEntityPool.addRequiredNotation(n, locator, n2, n3, array);
    }
    
    public void checkRequiredNotations() throws Exception {
        this.fEntityPool.checkRequiredNotations();
    }
    
    protected int lookupEntity(final int n) {
        return this.fEntityPool.lookupEntity(n);
    }
    
    private void reportRecoverableXMLError(final int n, final int n2, final int n3) throws Exception {
        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", n, n2, new Object[] { this.fStringPool.toString(n3) }, 1);
    }
    
    public boolean externalReferenceInContent(final int n) throws Exception {
        final boolean externalEntity = this.fEntityPool.isExternalEntity(n);
        if (this.fEventHandler.externalEntityStandaloneCheck()) {
            if (externalEntity) {
                this.reportRecoverableXMLError(102, 80, this.fEntityName);
            }
            else if (this.fEntityPool.getEntityDeclIsExternal(n)) {
                this.reportRecoverableXMLError(132, 80, this.fEntityName);
            }
        }
        return externalEntity;
    }
    
    protected int valueOfReferenceInAttValue(final int n) throws Exception {
        if (this.fEventHandler.externalEntityStandaloneCheck() && this.fEntityPool.getEntityDeclIsExternal(n)) {
            this.reportRecoverableXMLError(132, 80, this.fEntityName);
        }
        return this.fEntityPool.getEntityValue(n);
    }
    
    protected boolean isExternalEntity(final int n) {
        return this.fEntityPool.isExternalEntity(n);
    }
    
    protected int getEntityValue(final int n) {
        return this.fEntityPool.getEntityValue(n);
    }
    
    protected String getPublicIdOfEntity(final int n) {
        return this.fStringPool.toString(this.fEntityPool.getPublicId(n));
    }
    
    protected String getSystemIdOfEntity(final int n) {
        return this.fStringPool.toString(this.fEntityPool.getSystemId(n));
    }
    
    protected int lookupParameterEntity(final int n) throws Exception {
        int lookupEntity = -1;
        if (this.fParameterEntityPool != null) {
            lookupEntity = this.fParameterEntityPool.lookupEntity(n);
        }
        return lookupEntity;
    }
    
    protected boolean isExternalParameterEntity(final int n) {
        return this.fParameterEntityPool.isExternalEntity(n);
    }
    
    protected int getParameterEntityValue(final int n) {
        return this.fParameterEntityPool.getEntityValue(n);
    }
    
    protected String getPublicIdOfParameterEntity(final int n) {
        return this.fStringPool.toString(this.fParameterEntityPool.getPublicId(n));
    }
    
    protected String getSystemIdOfParameterEntity(final int n) {
        return this.fStringPool.toString(this.fParameterEntityPool.getSystemId(n));
    }
    
    public EntityReader getEntityReader() {
        return this.fReader;
    }
    
    public void addRecognizer(final XMLDeclRecognizer xmlDeclRecognizer) {
        this.fReaderFactory.addRecognizer(xmlDeclRecognizer);
    }
    
    public void setEntityResolver(final EntityResolver fResolver) {
        this.fResolver = fResolver;
    }
    
    public EntityResolver getEntityResolver() {
        return this.fResolver;
    }
    
    public String expandSystemId(final String s) {
        return this.expandSystemId(s, this.fSystemId);
    }
    
    private String expandSystemId(final String s, final String s2) {
        if (s == null || s.length() == 0) {
            return s;
        }
        try {
            if (new URI(s) != null) {
                return s;
            }
        }
        catch (URI.MalformedURIException ex) {}
        final String fixURI = fixURI(s);
        URI uri = null;
        try {
            URI uri2;
            if (s2 == null) {
                String s3;
                try {
                    s3 = fixURI(System.getProperty("user.dir"));
                }
                catch (SecurityException ex2) {
                    s3 = "";
                }
                if (!s3.endsWith("/")) {
                    s3 += "/";
                }
                uri2 = new URI("file", "", s3, null, null);
            }
            else {
                uri2 = new URI(s2);
            }
            uri = new URI(uri2, fixURI);
        }
        catch (Exception ex3) {}
        if (uri == null) {
            return s;
        }
        return uri.toString();
    }
    
    private static String fixURI(String s) {
        s = s.replace(File.separatorChar, '/');
        if (s.length() >= 2 && s.charAt(1) == ':') {
            final char upperCase = Character.toUpperCase(s.charAt(0));
            if (upperCase >= 'A' && upperCase <= 'Z') {
                s = "/" + s;
            }
        }
        return s;
    }
    
    public boolean startReadingFromDocument(final InputSource fSource) throws Exception {
        this.pushEntity(false, -2);
        this.fSystemId = null;
        this.pushNullReader();
        this.fEntityName = -2;
        this.fEntityType = 5;
        this.fEntityContext = 6;
        this.fReaderDepth = 0;
        this.fReaderId = this.fNextReaderId++;
        this.fPublicId = fSource.getPublicId();
        this.fSystemId = fSource.getSystemId();
        this.fEventHandler.startEntityReference(this.fEntityName, this.fEntityType, this.fEntityContext);
        this.fSystemId = this.expandSystemId(this.fSystemId, null);
        this.fSource = fSource;
        final boolean b = true;
        try {
            this.fReader = this.fReaderFactory.createReader(this, this.fErrorReporter, fSource, this.fSystemId, b, this.fStringPool);
        }
        catch (MalformedURLException ex2) {
            final String fSystemId = this.fSystemId;
            this.fEventHandler.endEntityReference(this.fEntityName, this.fEntityType, this.fEntityContext);
            this.popReader();
            this.popEntity();
            this.fReader = null;
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.apache.org/xml/xerces.html", 5, 0, new Object[] { fSystemId }, 2);
        }
        catch (FileNotFoundException ex3) {
            final String fSystemId2 = this.fSystemId;
            this.fEventHandler.endEntityReference(this.fEntityName, this.fEntityType, this.fEntityContext);
            this.popReader();
            this.popEntity();
            this.fReader = null;
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.apache.org/xml/xerces.html", 5, 0, new Object[] { fSystemId2 }, 2);
        }
        catch (UnsupportedEncodingException ex) {
            this.fEventHandler.endEntityReference(this.fEntityName, this.fEntityType, this.fEntityContext);
            this.popReader();
            this.popEntity();
            this.fReader = null;
            final String message = ex.getMessage();
            if (message == null) {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 70, 72, null, 2);
            }
            else if (!XMLCharacterProperties.validEncName(message)) {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 40, 42, new Object[] { message }, 2);
            }
            else {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 61, 60, new Object[] { message }, 2);
            }
        }
        this.fEventHandler.sendReaderChangeNotifications(this.fReader, this.fReaderId);
        return this.fReader != null;
    }
    
    public void startReadingFromExternalSubset(final String fPublicId, final String fSystemId, final int fReaderDepth) throws Exception {
        this.pushEntity(true, -1);
        this.pushReader();
        this.pushNullReader();
        this.fEntityName = -1;
        this.fEntityType = 6;
        this.fEntityContext = 7;
        this.fReaderDepth = fReaderDepth;
        this.fReaderId = this.fNextReaderId++;
        this.fPublicId = fPublicId;
        this.fSystemId = fSystemId;
        this.startReadingFromExternalEntity(false, -1);
    }
    
    public void stopReadingFromExternalSubset() throws Exception {
        if (!(this.fReader instanceof NullReader)) {
            throw new RuntimeException("FWK004 cannot happen 18\n18");
        }
        this.popReader();
        this.fEventHandler.sendReaderChangeNotifications(this.fReader, this.fReaderId);
    }
    
    public boolean startReadingFromEntity(final int fEntityName, final int fReaderDepth, final int fEntityContext) throws Exception {
        if (fEntityContext > 2) {
            return this.startReadingFromParameterEntity(fEntityName, fReaderDepth, fEntityContext);
        }
        final int lookupEntity = this.lookupEntity(fEntityName);
        if (lookupEntity < 0) {
            int n = 62;
            int n2 = 1;
            if (this.fEntityContext == 6 || this.fEntityContext == 0) {
                n = 61;
                n2 = 2;
            }
            else if (!this.fEventHandler.getValidating()) {
                return false;
            }
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 62, n, new Object[] { this.fStringPool.toString(fEntityName) }, n2);
            return false;
        }
        if (fEntityContext == 2) {
            if (this.fEntityPool.isUnparsedEntity(lookupEntity)) {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 63, 63, new Object[] { this.fStringPool.toString(fEntityName) }, 2);
                return false;
            }
        }
        else if (this.isExternalEntity(lookupEntity)) {
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 64, 64, new Object[] { this.fStringPool.toString(fEntityName) }, 2);
            return false;
        }
        if (!this.pushEntity(false, fEntityName)) {
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 71, 73, new Object[] { this.fStringPool.toString(fEntityName), this.entityReferencePath(false, fEntityName) }, 2);
            return false;
        }
        this.pushReader();
        this.fEntityName = fEntityName;
        this.fEntityContext = fEntityContext;
        this.fReaderDepth = fReaderDepth;
        this.fReaderId = this.fNextReaderId++;
        if (fEntityContext != 2 || !this.externalReferenceInContent(lookupEntity)) {
            this.fEntityType = 2;
            this.fPublicId = null;
            this.fSystemId = this.fSystemId;
            int n3;
            if (fEntityContext == 2 || fEntityContext == 1) {
                n3 = this.getEntityValue(lookupEntity);
            }
            else {
                n3 = this.valueOfReferenceInAttValue(lookupEntity);
            }
            this.startReadingFromInternalEntity(n3, false);
            return false;
        }
        this.fEntityType = 3;
        this.fPublicId = this.getPublicIdOfEntity(lookupEntity);
        this.fSystemId = this.getSystemIdOfEntity(lookupEntity);
        return this.startReadingFromExternalEntity(true, lookupEntity);
    }
    
    private boolean startReadingFromParameterEntity(final int fEntityName, final int fReaderDepth, final int fEntityContext) throws Exception {
        final int lookupParameterEntity = this.lookupParameterEntity(fEntityName);
        if (lookupParameterEntity == -1) {
            if (this.fEventHandler.getValidating()) {
                this.reportRecoverableXMLError(62, 62, fEntityName);
            }
            return false;
        }
        if (!this.pushEntity(true, fEntityName)) {
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 72, 73, new Object[] { this.fStringPool.toString(fEntityName), this.entityReferencePath(true, fEntityName) }, 2);
            return false;
        }
        this.pushReader();
        this.fEntityName = fEntityName;
        this.fEntityContext = fEntityContext;
        this.fReaderDepth = fReaderDepth;
        this.fReaderId = this.fNextReaderId++;
        if (!this.isExternalParameterEntity(lookupParameterEntity)) {
            this.fEntityType = 0;
            this.fPublicId = null;
            this.fSystemId = this.fSystemId;
            this.startReadingFromInternalEntity(this.getParameterEntityValue(lookupParameterEntity), this.fEntityContext != 4);
            return false;
        }
        this.fEntityType = 1;
        this.fPublicId = this.getPublicIdOfParameterEntity(lookupParameterEntity);
        this.fSystemId = this.getSystemIdOfParameterEntity(lookupParameterEntity);
        return this.startReadingFromExternalEntity(true, lookupParameterEntity);
    }
    
    private void startReadingFromInternalEntity(final int n, final boolean b) throws Exception {
        if (this.fEntityContext == 4) {}
        this.fSource = null;
        this.fEventHandler.startEntityReference(this.fEntityName, this.fEntityType, this.fEntityContext);
        this.fReader = this.fReaderFactory.createStringReader(this, this.fErrorReporter, this.fSendCharDataAsCharArray, this.getLineNumber(), this.getColumnNumber(), n, this.fStringPool, b);
        this.fEventHandler.sendReaderChangeNotifications(this.fReader, this.fReaderId);
    }
    
    private boolean startReadingFromExternalEntity(final boolean b, final int n) throws Exception {
        if (this.fEntityContext == 4) {}
        if (this.fEntityContext == 5) {}
        this.fEventHandler.startEntityReference(this.fEntityName, this.fEntityType, this.fEntityContext);
        String s = null;
        if (n != -1) {
            if (this.fEntityType == 1) {
                s = this.fParameterEntityPool.getBaseSystemId(n);
            }
            else {
                s = this.fEntityPool.getBaseSystemId(n);
            }
        }
        if (s == null) {
            s = this.fReaderStack.peek().systemId;
        }
        this.fSystemId = this.expandSystemId(this.fSystemId, s);
        this.fSource = ((this.fResolver == null) ? null : this.fResolver.resolveEntity(this.fPublicId, this.fSystemId));
        if (this.fSource == null) {
            this.fSource = new InputSource(this.fSystemId);
            if (this.fPublicId != null) {
                this.fSource.setPublicId(this.fPublicId);
            }
        }
        else {
            if (this.fSource.getSystemId() != null) {
                this.fSystemId = this.expandSystemId(this.fSource.getSystemId(), s);
            }
            if (this.fSource.getPublicId() != null) {
                this.fPublicId = this.fSource.getPublicId();
            }
        }
        final boolean b2 = false;
        try {
            this.fReader = this.fReaderFactory.createReader(this, this.fErrorReporter, this.fSource, this.fSystemId, b2, this.fStringPool);
        }
        catch (MalformedURLException ex2) {
            final String fSystemId = this.fSystemId;
            this.fEventHandler.endEntityReference(this.fEntityName, this.fEntityType, this.fEntityContext);
            this.popReader();
            this.popEntity();
            this.fReader = null;
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.apache.org/xml/xerces.html", 5, 0, new Object[] { fSystemId }, 2);
        }
        catch (FileNotFoundException ex3) {
            final String fSystemId2 = this.fSystemId;
            this.fEventHandler.endEntityReference(this.fEntityName, this.fEntityType, this.fEntityContext);
            this.popReader();
            this.popEntity();
            this.fReader = null;
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.apache.org/xml/xerces.html", 5, 0, new Object[] { fSystemId2 }, 2);
        }
        catch (UnsupportedEncodingException ex) {
            this.fEventHandler.endEntityReference(this.fEntityName, this.fEntityType, this.fEntityContext);
            this.popReader();
            this.popEntity();
            this.fReader = null;
            final String message = ex.getMessage();
            if (message == null) {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 70, 72, null, 2);
            }
            else if (!XMLCharacterProperties.validEncName(message)) {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 40, 42, new Object[] { message }, 2);
            }
            else {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 61, 60, new Object[] { message }, 2);
            }
        }
        if (this.fReader == null || !b) {
            this.fEventHandler.sendReaderChangeNotifications(this.fReader, this.fReaderId);
            return false;
        }
        final int fReaderId = this.fReaderId;
        this.fEventHandler.sendReaderChangeNotifications(this.fReader, this.fReaderId);
        boolean lookingAtChar = this.fReader.lookingAtChar('<', false);
        if (fReaderId != this.fReaderId) {
            lookingAtChar = false;
        }
        return lookingAtChar;
    }
    
    private void pushNullReader() {
        ReaderState fReaderStateFreeList = this.fReaderStateFreeList;
        if (fReaderStateFreeList == null) {
            fReaderStateFreeList = new ReaderState();
        }
        else {
            this.fReaderStateFreeList = fReaderStateFreeList.nextReaderState;
        }
        if (this.fNullReader == null) {
            this.fNullReader = new NullReader();
        }
        fReaderStateFreeList.reader = this.fNullReader;
        fReaderStateFreeList.source = null;
        fReaderStateFreeList.entityName = -1;
        fReaderStateFreeList.entityType = -1;
        fReaderStateFreeList.entityContext = -1;
        fReaderStateFreeList.publicId = "Null Entity";
        fReaderStateFreeList.systemId = this.fSystemId;
        fReaderStateFreeList.readerId = this.fNextReaderId++;
        fReaderStateFreeList.depth = -1;
        fReaderStateFreeList.nextReaderState = null;
        this.fReaderStack.push(fReaderStateFreeList);
    }
    
    private void pushReader() {
        ReaderState fReaderStateFreeList = this.fReaderStateFreeList;
        if (fReaderStateFreeList == null) {
            fReaderStateFreeList = new ReaderState();
        }
        else {
            this.fReaderStateFreeList = fReaderStateFreeList.nextReaderState;
        }
        fReaderStateFreeList.reader = this.fReader;
        fReaderStateFreeList.source = this.fSource;
        fReaderStateFreeList.entityName = this.fEntityName;
        fReaderStateFreeList.entityType = this.fEntityType;
        fReaderStateFreeList.entityContext = this.fEntityContext;
        fReaderStateFreeList.publicId = this.fPublicId;
        fReaderStateFreeList.systemId = this.fSystemId;
        fReaderStateFreeList.readerId = this.fReaderId;
        fReaderStateFreeList.depth = this.fReaderDepth;
        fReaderStateFreeList.nextReaderState = null;
        this.fReaderStack.push(fReaderStateFreeList);
    }
    
    private void popReader() {
        if (this.fReaderStack.empty()) {
            throw new RuntimeException("FWK004 cannot happen 19\n19");
        }
        final ReaderState fReaderStateFreeList = this.fReaderStack.pop();
        this.fReader = fReaderStateFreeList.reader;
        this.fSource = fReaderStateFreeList.source;
        this.fEntityName = fReaderStateFreeList.entityName;
        this.fEntityType = fReaderStateFreeList.entityType;
        this.fEntityContext = fReaderStateFreeList.entityContext;
        this.fPublicId = fReaderStateFreeList.publicId;
        this.fSystemId = fReaderStateFreeList.systemId;
        this.fReaderId = fReaderStateFreeList.readerId;
        this.fReaderDepth = fReaderStateFreeList.depth;
        fReaderStateFreeList.nextReaderState = this.fReaderStateFreeList;
        this.fReaderStateFreeList = fReaderStateFreeList;
    }
    
    public boolean startEntityDecl(final boolean b, final int n) throws Exception {
        if (!this.pushEntity(b, n)) {
            this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", b ? 72 : 71, 73, new Object[] { this.fStringPool.toString(n), this.entityReferencePath(b, n) }, 2);
            return false;
        }
        return true;
    }
    
    public void endEntityDecl() throws Exception {
        this.popEntity();
    }
    
    private boolean pushEntity(final boolean b, final int n) throws Exception {
        if (n >= 0) {
            for (int i = 0; i < this.fEntityStackDepth; ++i) {
                if (this.fEntityNameStack[i] == n && this.fEntityTypeStack[i] == (b ? 1 : 0)) {
                    return false;
                }
            }
        }
        if (this.fEntityTypeStack == null) {
            this.fEntityTypeStack = new byte[8];
            this.fEntityNameStack = new int[8];
        }
        else if (this.fEntityStackDepth == this.fEntityTypeStack.length) {
            final byte[] fEntityTypeStack = new byte[this.fEntityStackDepth * 2];
            System.arraycopy(this.fEntityTypeStack, 0, fEntityTypeStack, 0, this.fEntityStackDepth);
            this.fEntityTypeStack = fEntityTypeStack;
            final int[] fEntityNameStack = new int[this.fEntityStackDepth * 2];
            System.arraycopy(this.fEntityNameStack, 0, fEntityNameStack, 0, this.fEntityStackDepth);
            this.fEntityNameStack = fEntityNameStack;
        }
        this.fEntityTypeStack[this.fEntityStackDepth] = (byte)(b ? 1 : 0);
        this.fEntityNameStack[this.fEntityStackDepth] = n;
        ++this.fEntityStackDepth;
        return true;
    }
    
    private String entityReferencePath(final boolean b, final int n) {
        final StringBuffer sb = new StringBuffer();
        sb.append("(top-level)");
        for (int i = 0; i < this.fEntityStackDepth; ++i) {
            if (this.fEntityNameStack[i] >= 0) {
                sb.append('-');
                sb.append((char)((this.fEntityTypeStack[i] == 1) ? 37 : 38));
                sb.append(this.fStringPool.toString(this.fEntityNameStack[i]));
                sb.append(';');
            }
        }
        sb.append('-');
        sb.append(b ? '%' : '&');
        sb.append(this.fStringPool.toString(n));
        sb.append(';');
        return sb.toString();
    }
    
    private void popEntity() throws Exception {
        --this.fEntityStackDepth;
    }
    
    public int getReaderId() {
        return this.fReaderId;
    }
    
    public void setReaderDepth(final int fReaderDepth) {
        this.fReaderDepth = fReaderDepth;
    }
    
    public int getReaderDepth() {
        return this.fReaderDepth;
    }
    
    public String getPublicId() {
        return this.fPublicId;
    }
    
    public String getSystemId() {
        return this.fSystemId;
    }
    
    public int getLineNumber() {
        return (this.fReader == null) ? 0 : this.fReader.getLineNumber();
    }
    
    public int getColumnNumber() {
        return (this.fReader == null) ? 0 : this.fReader.getColumnNumber();
    }
    
    public EntityReader changeReaders() throws Exception {
        this.fEventHandler.sendEndOfInputNotifications(this.fEntityName, this.fReaderStack.size() > 1);
        this.fEventHandler.endEntityReference(this.fEntityName, this.fEntityType, this.fEntityContext);
        this.popReader();
        this.fEventHandler.sendReaderChangeNotifications(this.fReader, this.fReaderId);
        this.popEntity();
        return this.fReader;
    }
    
    private class ReaderState
    {
        EntityReader reader;
        InputSource source;
        int entityName;
        int entityType;
        int entityContext;
        String publicId;
        String systemId;
        int readerId;
        int depth;
        ReaderState nextReaderState;
    }
    
    private final class NullReader implements EntityReader
    {
        public int currentOffset() {
            return -1;
        }
        
        public int getLineNumber() {
            return -1;
        }
        
        public int getColumnNumber() {
            return -1;
        }
        
        public void setInCDSect(final boolean b) {
        }
        
        public boolean getInCDSect() {
            return false;
        }
        
        public void append(final CharBuffer charBuffer, final int n, final int n2) {
        }
        
        public int addString(final int n, final int n2) {
            return -1;
        }
        
        public int addSymbol(final int n, final int n2) {
            return -1;
        }
        
        public boolean lookingAtChar(final char c, final boolean b) {
            return false;
        }
        
        public boolean lookingAtValidChar(final boolean b) {
            return false;
        }
        
        public boolean lookingAtSpace(final boolean b) {
            return false;
        }
        
        public void skipToChar(final char c) {
        }
        
        public void skipPastSpaces() {
        }
        
        public void skipPastName(final char c) {
        }
        
        public void skipPastNmtoken(final char c) {
        }
        
        public boolean skippedString(final char[] array) {
            return false;
        }
        
        public int scanInvalidChar() {
            return -1;
        }
        
        public int scanCharRef(final boolean b) {
            return -2;
        }
        
        public int scanStringLiteral() {
            return -1;
        }
        
        public int scanAttValue(final char c, final boolean b) {
            return -3;
        }
        
        public int scanEntityValue(final int n, final boolean b) {
            return -4;
        }
        
        public boolean scanExpectedName(final char c, final StringPool.CharArrayRange charArrayRange) {
            return false;
        }
        
        public void scanQName(final char c, final QName qName) {
            qName.clear();
        }
        
        public int scanName(final char c) {
            return -1;
        }
        
        public int scanContent(final QName qName) throws Exception {
            return 9;
        }
    }
    
    public final class EntityPool
    {
        private StringPool fStringPool;
        private XMLErrorReporter fErrorReporter;
        private int fEntityCount;
        private int[][] fName;
        private int[][] fValue;
        private int[][] fPublicId;
        private int[][] fSystemId;
        private int[][] fBaseSystemId;
        private int[][] fNotationName;
        private byte[][] fDeclIsExternal;
        private int fNotationListHead;
        private boolean fCreateStandardEntities;
        private Vector fRequiredNotations;
        
        public EntityPool(final StringPool fStringPool, final XMLErrorReporter fErrorReporter, final boolean fCreateStandardEntities) {
            this.fStringPool = null;
            this.fErrorReporter = null;
            this.fEntityCount = 0;
            this.fName = new int[32][];
            this.fValue = new int[32][];
            this.fPublicId = new int[32][];
            this.fSystemId = new int[32][];
            this.fBaseSystemId = new int[32][];
            this.fNotationName = new int[32][];
            this.fDeclIsExternal = new byte[32][];
            this.fNotationListHead = -1;
            this.fCreateStandardEntities = false;
            this.fRequiredNotations = null;
            this.fStringPool = fStringPool;
            this.fErrorReporter = fErrorReporter;
            this.fCreateStandardEntities = fCreateStandardEntities;
            if (this.fCreateStandardEntities) {
                this.createInternalEntity("lt", "&#60;");
                this.createInternalEntity("gt", ">");
                this.createInternalEntity("amp", "&#38;");
                this.createInternalEntity("apos", "'");
                this.createInternalEntity("quot", "\"");
            }
        }
        
        public void reset(final StringPool fStringPool) {
            this.fStringPool = fStringPool;
            this.fEntityCount = 0;
            this.fNotationListHead = -1;
            if (this.fRequiredNotations != null) {
                this.fRequiredNotations.removeAllElements();
            }
            if (this.fCreateStandardEntities) {
                this.createInternalEntity("lt", "&#60;");
                this.createInternalEntity("gt", ">");
                this.createInternalEntity("amp", "&#38;");
                this.createInternalEntity("apos", "'");
                this.createInternalEntity("quot", "\"");
            }
        }
        
        private void createInternalEntity(final String s, final String s2) {
            final int n = this.fEntityCount >> 5;
            final int n2 = this.fEntityCount & 0x1F;
            this.ensureCapacity(n);
            this.fName[n][n2] = this.fStringPool.addSymbol(s);
            this.fValue[n][n2] = this.fStringPool.addString(s2);
            this.fPublicId[n][n2] = -1;
            this.fSystemId[n][n2] = -1;
            this.fBaseSystemId[n][n2] = -1;
            this.fNotationName[n][n2] = -1;
            ++this.fEntityCount;
        }
        
        private boolean ensureCapacity(final int n) {
            try {
                return this.fName[n][0] == 0;
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                final int[][] fName = new int[n * 2][];
                System.arraycopy(this.fName, 0, fName, 0, n);
                this.fName = fName;
                final int[][] fValue = new int[n * 2][];
                System.arraycopy(this.fValue, 0, fValue, 0, n);
                this.fValue = fValue;
                final int[][] fPublicId = new int[n * 2][];
                System.arraycopy(this.fPublicId, 0, fPublicId, 0, n);
                this.fPublicId = fPublicId;
                final int[][] fSystemId = new int[n * 2][];
                System.arraycopy(this.fSystemId, 0, fSystemId, 0, n);
                this.fSystemId = fSystemId;
                final int[][] fBaseSystemId = new int[n * 2][];
                System.arraycopy(this.fBaseSystemId, 0, fBaseSystemId, 0, n);
                this.fBaseSystemId = fBaseSystemId;
                final int[][] fNotationName = new int[n * 2][];
                System.arraycopy(this.fNotationName, 0, fNotationName, 0, n);
                this.fNotationName = fNotationName;
                final byte[][] fDeclIsExternal = new byte[n * 2][];
                System.arraycopy(this.fDeclIsExternal, 0, fDeclIsExternal, 0, n);
                this.fDeclIsExternal = fDeclIsExternal;
            }
            catch (NullPointerException ex2) {}
            this.fName[n] = new int[32];
            this.fValue[n] = new int[32];
            this.fPublicId[n] = new int[32];
            this.fSystemId[n] = new int[32];
            this.fBaseSystemId[n] = new int[32];
            this.fNotationName[n] = new int[32];
            this.fDeclIsExternal[n] = new byte[32];
            return true;
        }
        
        public int addEntityDecl(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b) {
            final int n7 = this.fEntityCount >> 5;
            final int n8 = this.fEntityCount & 0x1F;
            this.ensureCapacity(n7);
            this.fName[n7][n8] = n;
            this.fValue[n7][n8] = n2;
            this.fPublicId[n7][n8] = n3;
            this.fSystemId[n7][n8] = n4;
            this.fBaseSystemId[n7][n8] = n5;
            this.fNotationName[n7][n8] = n6;
            this.fDeclIsExternal[n7][n8] = (byte)(b ? -128 : 0);
            return this.fEntityCount++;
        }
        
        public int addNotationDecl(final int n, final int n2, final int n3, final int n4, final boolean b) {
            int n5;
            int n6;
            for (int i = this.fNotationListHead; i != -1; i = this.fValue[n5][n6]) {
                n5 = i >> 5;
                n6 = (i & 0x1F);
                if (this.fNotationName[n5][n6] == n) {
                    return -1;
                }
            }
            final int n7 = this.fEntityCount >> 5;
            final int n8 = this.fEntityCount & 0x1F;
            this.ensureCapacity(n7);
            this.fName[n7][n8] = -1;
            this.fValue[n7][n8] = this.fNotationListHead;
            this.fPublicId[n7][n8] = n2;
            this.fSystemId[n7][n8] = n3;
            this.fBaseSystemId[n7][n8] = n4;
            this.fNotationName[n7][n8] = n;
            this.fDeclIsExternal[n7][n8] = (byte)(b ? -128 : 0);
            return this.fNotationListHead = this.fEntityCount++;
        }
        
        public int lookupEntity(final int n) {
            if (n == -1) {
                return -1;
            }
            int n2 = 0;
            int n3 = 0;
            for (int i = 0; i < this.fEntityCount; ++i) {
                if (this.fName[n2][n3] == n) {
                    return i;
                }
                if (++n3 == 32) {
                    ++n2;
                    n3 = 0;
                }
            }
            return -1;
        }
        
        public boolean isExternalEntity(final int n) {
            return this.fValue[n >> 5][n & 0x1F] == -1;
        }
        
        public boolean isUnparsedEntity(final int n) {
            return this.fNotationName[n >> 5][n & 0x1F] != -1;
        }
        
        public boolean getEntityDeclIsExternal(final int n) {
            return this.fDeclIsExternal[n >> 5][n & 0x1F] < 0;
        }
        
        public int getEntityName(final int n) {
            return this.fName[n >> 5][n & 0x1F];
        }
        
        public int getEntityValue(final int n) {
            return this.fValue[n >> 5][n & 0x1F];
        }
        
        public int getPublicId(final int n) {
            return this.fPublicId[n >> 5][n & 0x1F];
        }
        
        public int getSystemId(final int n) {
            return this.fSystemId[n >> 5][n & 0x1F];
        }
        
        public String getBaseSystemId(final int n) {
            final int n2 = this.fBaseSystemId[n >> 5][n & 0x1F];
            if (n2 == -1) {
                return null;
            }
            return this.fStringPool.toString(n2);
        }
        
        public boolean isNotationDeclared(final int n) {
            int n2;
            int n3;
            for (int i = this.fNotationListHead; i != -1; i = this.fValue[n2][n3]) {
                n2 = i >> 5;
                n3 = (i & 0x1F);
                if (this.fNotationName[n2][n3] == n) {
                    return true;
                }
            }
            return false;
        }
        
        public boolean getNotationDeclIsExternal(final int n) {
            return this.fDeclIsExternal[n >> 5][n & 0x1F] < 0;
        }
        
        public int getNotationName(final int n) {
            return this.fNotationName[n >> 5][n & 0x1F];
        }
        
        public void addRequiredNotation(final int n, final Locator locator, final int n2, final int n3, final Object[] array) {
            if (this.fRequiredNotations == null) {
                this.fRequiredNotations = new Vector();
            }
            for (int i = 0; i < this.fRequiredNotations.size(); ++i) {
                if (((RequiredNotation)this.fRequiredNotations.elementAt(i)).fNotationName == n) {
                    return;
                }
            }
            this.fRequiredNotations.addElement(new RequiredNotation(n, locator, n2, n3, array));
        }
        
        public void checkRequiredNotations() throws Exception {
            if (this.fRequiredNotations == null) {
                return;
            }
            for (int i = 0; i < this.fRequiredNotations.size(); ++i) {
                final RequiredNotation requiredNotation = this.fRequiredNotations.elementAt(i);
                if (!this.isNotationDeclared(requiredNotation.fNotationName)) {
                    this.fErrorReporter.reportError(requiredNotation.fLocator, "http://www.w3.org/TR/1998/REC-xml-19980210", requiredNotation.fMajorCode, requiredNotation.fMinorCode, requiredNotation.fArgs, 1);
                }
            }
        }
        
        class RequiredNotation
        {
            int fNotationName;
            LocatorImpl fLocator;
            int fMajorCode;
            int fMinorCode;
            Object[] fArgs;
            
            RequiredNotation(final int fNotationName, final Locator locator, final int fMajorCode, final int fMinorCode, final Object[] fArgs) {
                this.fNotationName = fNotationName;
                this.fLocator = new LocatorImpl(locator);
                this.fMajorCode = fMajorCode;
                this.fMinorCode = fMinorCode;
                this.fArgs = fArgs;
            }
        }
    }
    
    public interface EventHandler
    {
        void startEntityReference(final int p0, final int p1, final int p2) throws Exception;
        
        void endEntityReference(final int p0, final int p1, final int p2) throws Exception;
        
        void sendEndOfInputNotifications(final int p0, final boolean p1) throws Exception;
        
        void sendReaderChangeNotifications(final EntityReader p0, final int p1) throws Exception;
        
        boolean externalEntityStandaloneCheck();
        
        boolean getValidating();
    }
}
