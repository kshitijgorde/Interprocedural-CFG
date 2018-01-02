// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl;

import java.io.EOFException;
import org.apache.xerces.util.XMLStringBuffer;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.QName;
import java.io.InputStreamReader;
import org.apache.xerces.util.EncodingMap;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.impl.io.UCSReader;
import org.apache.xerces.impl.io.ASCIIReader;
import org.apache.xerces.impl.io.UTF8Reader;
import java.util.Locale;
import org.apache.xerces.util.URI;
import java.io.UnsupportedEncodingException;
import java.io.File;
import java.util.Enumeration;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLComponentManager;
import java.io.InputStream;
import java.net.URL;
import java.io.Reader;
import java.io.StringReader;
import org.apache.xerces.xni.XNIException;
import java.io.IOException;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.XMLResourceIdentifier;
import java.util.Vector;
import org.apache.xerces.util.XMLResourceIdentifierImpl;
import java.util.Stack;
import java.util.Hashtable;
import org.apache.xerces.impl.validation.ValidationManager;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.parser.XMLComponent;

public class XMLEntityManager implements XMLComponent, XMLEntityResolver
{
    public static final int DEFAULT_BUFFER_SIZE = 2048;
    public static final int DEFAULT_XMLDECL_BUFFER_SIZE = 64;
    public static final int DEFAULT_INTERNAL_BUFFER_SIZE = 1024;
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String EXTERNAL_GENERAL_ENTITIES = "http://xml.org/sax/features/external-general-entities";
    protected static final String EXTERNAL_PARAMETER_ENTITIES = "http://xml.org/sax/features/external-parameter-entities";
    protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
    protected static final String WARN_ON_DUPLICATE_ENTITYDEF = "http://apache.org/xml/features/warn-on-duplicate-entitydef";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    protected static final String BUFFER_SIZE = "http://apache.org/xml/properties/input-buffer-size";
    protected static final String ENTITY_EXPANSION_LIMIT = "http://apache.org/xml/properties/entity-expansion-limit";
    protected static final String SYSTEM_PROPERTY_ENTITY_EXPANSION_LIMIT = "entityExpansionLimit";
    private static final String[] RECOGNIZED_FEATURES;
    private static final Boolean[] FEATURE_DEFAULTS;
    private static final String[] RECOGNIZED_PROPERTIES;
    private static final Object[] PROPERTY_DEFAULTS;
    private static final String XMLEntity;
    private static final String DTDEntity;
    private static final boolean DEBUG_BUFFER = false;
    private static final boolean DEBUG_ENTITIES = false;
    private static final boolean DEBUG_ENCODINGS = false;
    private static final boolean DEBUG_RESOLVER = false;
    private static int entityExpansionLimit;
    protected boolean fValidation;
    protected boolean fExternalGeneralEntities;
    protected boolean fExternalParameterEntities;
    protected boolean fAllowJavaEncodings;
    protected boolean fWarnDuplicateEntityDef;
    protected SymbolTable fSymbolTable;
    protected XMLErrorReporter fErrorReporter;
    protected XMLEntityResolver fEntityResolver;
    protected ValidationManager fValidationManager;
    protected int fBufferSize;
    protected Integer fEntityExpansionLimit;
    protected int fEntityExpansionCount;
    protected boolean fStandalone;
    protected boolean fInExternalSubset;
    protected XMLEntityHandler fEntityHandler;
    protected XMLEntityScanner fEntityScanner;
    protected Hashtable fEntities;
    protected Stack fEntityStack;
    protected ScannedEntity fCurrentEntity;
    protected Hashtable fDeclaredEntities;
    private final XMLResourceIdentifierImpl fResourceIdentifier;
    protected Vector fOwnReaders;
    private static String gUserDir;
    private static String gEscapedUserDir;
    private static boolean[] gNeedEscaping;
    private static char[] gAfterEscaping1;
    private static char[] gAfterEscaping2;
    private static char[] gHexChs;
    
    public XMLEntityManager() {
        this(null);
    }
    
    public XMLEntityManager(final XMLEntityManager entityManager) {
        this.fBufferSize = 2048;
        this.fEntityExpansionLimit = null;
        this.fInExternalSubset = false;
        this.fEntities = new Hashtable();
        this.fEntityStack = new Stack();
        this.fResourceIdentifier = new XMLResourceIdentifierImpl();
        this.fOwnReaders = new Vector();
        this.fEntityScanner = this.createEntityScanner();
        this.fDeclaredEntities = ((entityManager != null) ? entityManager.getDeclaredEntities() : null);
    }
    
    public void setStandalone(final boolean standalone) {
        this.fStandalone = standalone;
    }
    
    public boolean isStandalone() {
        return this.fStandalone;
    }
    
    public void setEntityHandler(final XMLEntityHandler entityHandler) {
        this.fEntityHandler = entityHandler;
    }
    
    public void addInternalEntity(final String name, final String text) {
        if (!this.fEntities.containsKey(name)) {
            final Entity entity = new InternalEntity(name, text, this.fInExternalSubset);
            this.fEntities.put(name, entity);
        }
        else if (this.fWarnDuplicateEntityDef) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_DUPLICATE_ENTITY_DEFINITION", new Object[] { name }, (short)0);
        }
    }
    
    public void addExternalEntity(final String name, final String publicId, final String literalSystemId, String baseSystemId) {
        if (!this.fEntities.containsKey(name)) {
            if (baseSystemId == null) {
                final int size = this.fEntityStack.size();
                if (size == 0 && this.fCurrentEntity != null && this.fCurrentEntity.entityLocation != null) {
                    baseSystemId = this.fCurrentEntity.entityLocation.getExpandedSystemId();
                }
                for (int i = size - 1; i >= 0; --i) {
                    final ScannedEntity externalEntity = (ScannedEntity)this.fEntityStack.elementAt(i);
                    if (externalEntity.entityLocation != null && externalEntity.entityLocation.getExpandedSystemId() != null) {
                        baseSystemId = externalEntity.entityLocation.getExpandedSystemId();
                        break;
                    }
                }
            }
            final Entity entity = new ExternalEntity(name, new XMLResourceIdentifierImpl(publicId, literalSystemId, baseSystemId, expandSystemId(literalSystemId, baseSystemId)), null, this.fInExternalSubset);
            this.fEntities.put(name, entity);
        }
        else if (this.fWarnDuplicateEntityDef) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_DUPLICATE_ENTITY_DEFINITION", new Object[] { name }, (short)0);
        }
    }
    
    public boolean isExternalEntity(final String entityName) {
        final Entity entity = this.fEntities.get(entityName);
        return entity != null && entity.isExternal();
    }
    
    public boolean isEntityDeclInExternalSubset(final String entityName) {
        final Entity entity = this.fEntities.get(entityName);
        return entity != null && entity.isEntityDeclInExternalSubset();
    }
    
    public void addUnparsedEntity(final String name, final String publicId, final String systemId, final String baseSystemId, final String notation) {
        if (!this.fEntities.containsKey(name)) {
            final Entity entity = new ExternalEntity(name, new XMLResourceIdentifierImpl(publicId, systemId, baseSystemId, null), notation, this.fInExternalSubset);
            this.fEntities.put(name, entity);
        }
        else if (this.fWarnDuplicateEntityDef) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_DUPLICATE_ENTITY_DEFINITION", new Object[] { name }, (short)0);
        }
    }
    
    public boolean isUnparsedEntity(final String entityName) {
        final Entity entity = this.fEntities.get(entityName);
        return entity != null && entity.isUnparsed();
    }
    
    public boolean isDeclaredEntity(final String entityName) {
        final Entity entity = this.fEntities.get(entityName);
        return entity != null;
    }
    
    public XMLInputSource resolveEntity(final XMLResourceIdentifier resourceIdentifier) throws IOException, XNIException {
        if (resourceIdentifier == null) {
            return null;
        }
        final String publicId = resourceIdentifier.getPublicId();
        final String literalSystemId = resourceIdentifier.getLiteralSystemId();
        String baseSystemId = resourceIdentifier.getBaseSystemId();
        String expandedSystemId = resourceIdentifier.getExpandedSystemId();
        boolean needExpand = expandedSystemId == null;
        if (baseSystemId == null && this.fCurrentEntity != null && this.fCurrentEntity.entityLocation != null) {
            baseSystemId = this.fCurrentEntity.entityLocation.getExpandedSystemId();
            if (baseSystemId != null) {
                needExpand = true;
            }
        }
        if (needExpand) {
            expandedSystemId = expandSystemId(literalSystemId, baseSystemId);
        }
        XMLInputSource xmlInputSource = null;
        if (this.fEntityResolver != null) {
            XMLResourceIdentifierImpl ri = null;
            if (resourceIdentifier instanceof XMLResourceIdentifierImpl) {
                ri = (XMLResourceIdentifierImpl)resourceIdentifier;
            }
            else {
                this.fResourceIdentifier.clear();
                ri = this.fResourceIdentifier;
            }
            ri.setValues(publicId, literalSystemId, baseSystemId, expandedSystemId);
            xmlInputSource = this.fEntityResolver.resolveEntity(ri);
        }
        if (xmlInputSource == null) {
            xmlInputSource = new XMLInputSource(publicId, literalSystemId, baseSystemId);
        }
        return xmlInputSource;
    }
    
    public void startEntity(final String entityName, final boolean literal) throws IOException, XNIException {
        final Entity entity = this.fEntities.get(entityName);
        if (entity == null) {
            if (this.fEntityHandler != null) {
                final String encoding = null;
                this.fResourceIdentifier.clear();
                this.fEntityHandler.startEntity(entityName, this.fResourceIdentifier, encoding);
                this.fEntityHandler.endEntity(entityName);
            }
            return;
        }
        final boolean external = entity.isExternal();
        if (external && (this.fValidationManager == null || !this.fValidationManager.isCachedDTD())) {
            final boolean unparsed = entity.isUnparsed();
            final boolean parameter = entityName.startsWith("%");
            final boolean general = !parameter;
            if (unparsed || (general && !this.fExternalGeneralEntities) || (parameter && !this.fExternalParameterEntities)) {
                if (this.fEntityHandler != null) {
                    this.fResourceIdentifier.clear();
                    final String encoding2 = null;
                    final ExternalEntity externalEntity = (ExternalEntity)entity;
                    final String extLitSysId = (externalEntity.entityLocation != null) ? externalEntity.entityLocation.getLiteralSystemId() : null;
                    final String extBaseSysId = (externalEntity.entityLocation != null) ? externalEntity.entityLocation.getBaseSystemId() : null;
                    final String expandedSystemId = expandSystemId(extLitSysId, extBaseSysId);
                    this.fResourceIdentifier.setValues((externalEntity.entityLocation != null) ? externalEntity.entityLocation.getPublicId() : null, extLitSysId, extBaseSysId, expandedSystemId);
                    this.fEntityHandler.startEntity(entityName, this.fResourceIdentifier, encoding2);
                    this.fEntityHandler.endEntity(entityName);
                }
                return;
            }
        }
        int i;
        for (int size = i = this.fEntityStack.size(); i >= 0; --i) {
            Entity activeEntity = (i == size) ? this.fCurrentEntity : ((Entity)this.fEntityStack.elementAt(i));
            if (activeEntity.name == entityName) {
                String path = entityName;
                for (int j = i + 1; j < size; ++j) {
                    activeEntity = (Entity)this.fEntityStack.elementAt(j);
                    path = path + " -> " + activeEntity.name;
                }
                path = path + " -> " + this.fCurrentEntity.name;
                path = path + " -> " + entityName;
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "RecursiveReference", new Object[] { entityName, path }, (short)2);
                if (this.fEntityHandler != null) {
                    this.fResourceIdentifier.clear();
                    final String encoding3 = null;
                    if (external) {
                        final ExternalEntity externalEntity2 = (ExternalEntity)entity;
                        final String extLitSysId2 = (externalEntity2.entityLocation != null) ? externalEntity2.entityLocation.getLiteralSystemId() : null;
                        final String extBaseSysId2 = (externalEntity2.entityLocation != null) ? externalEntity2.entityLocation.getBaseSystemId() : null;
                        final String expandedSystemId2 = expandSystemId(extLitSysId2, extBaseSysId2);
                        this.fResourceIdentifier.setValues((externalEntity2.entityLocation != null) ? externalEntity2.entityLocation.getPublicId() : null, extLitSysId2, extBaseSysId2, expandedSystemId2);
                    }
                    this.fEntityHandler.startEntity(entityName, this.fResourceIdentifier, encoding3);
                    this.fEntityHandler.endEntity(entityName);
                }
                return;
            }
        }
        XMLInputSource xmlInputSource = null;
        if (external) {
            final ExternalEntity externalEntity3 = (ExternalEntity)entity;
            xmlInputSource = this.resolveEntity(externalEntity3.entityLocation);
        }
        else {
            final InternalEntity internalEntity = (InternalEntity)entity;
            final Reader reader = new StringReader(internalEntity.text);
            xmlInputSource = new XMLInputSource(null, null, null, reader, null);
        }
        this.startEntity(entityName, xmlInputSource, literal, external);
    }
    
    public void startDocumentEntity(final XMLInputSource xmlInputSource) throws IOException, XNIException {
        this.startEntity(XMLEntityManager.XMLEntity, xmlInputSource, false, true);
    }
    
    public void startDTDEntity(final XMLInputSource xmlInputSource) throws IOException, XNIException {
        this.startEntity(XMLEntityManager.DTDEntity, xmlInputSource, false, true);
    }
    
    public void startExternalSubset() {
        this.fInExternalSubset = true;
    }
    
    public void endExternalSubset() {
        this.fInExternalSubset = false;
    }
    
    public void startEntity(final String name, final XMLInputSource xmlInputSource, final boolean literal, final boolean isExternal) throws IOException, XNIException {
        if (this.fEntityExpansionLimit != null && this.fEntityExpansionCount++ > this.fEntityExpansionLimit) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EntityExpansionLimitExceeded", new Object[] { this.fEntityExpansionLimit }, (short)2);
        }
        final String publicId = xmlInputSource.getPublicId();
        final String literalSystemId = xmlInputSource.getSystemId();
        String baseSystemId = xmlInputSource.getBaseSystemId();
        String encoding = xmlInputSource.getEncoding();
        Boolean isBigEndian = null;
        InputStream stream = null;
        Reader reader = xmlInputSource.getCharacterStream();
        final String expandedSystemId = expandSystemId(literalSystemId, baseSystemId);
        if (baseSystemId == null) {
            baseSystemId = expandedSystemId;
        }
        if (reader == null) {
            stream = xmlInputSource.getByteStream();
            if (stream == null) {
                stream = new URL(expandedSystemId).openStream();
            }
            stream = new RewindableInputStream(stream);
            if (encoding == null) {
                final byte[] b4 = new byte[4];
                int count;
                for (count = 0; count < 4; ++count) {
                    b4[count] = (byte)stream.read();
                }
                if (count == 4) {
                    final Object[] encodingDesc = this.getEncodingName(b4, count);
                    encoding = (String)encodingDesc[0];
                    isBigEndian = (Boolean)encodingDesc[1];
                    stream.reset();
                    final int offset = 0;
                    if (count > 2 && encoding.equals("UTF-8")) {
                        final int b5 = b4[0] & 0xFF;
                        final int b6 = b4[1] & 0xFF;
                        final int b7 = b4[2] & 0xFF;
                        if (b5 == 239 && b6 == 187 && b7 == 191) {
                            stream.skip(3L);
                        }
                    }
                    reader = this.createReader(stream, encoding, isBigEndian);
                }
                else {
                    reader = this.createReader(stream, encoding, isBigEndian);
                }
            }
            else {
                reader = this.createReader(stream, encoding, isBigEndian);
            }
        }
        this.fOwnReaders.addElement(reader);
        if (this.fCurrentEntity != null) {
            this.fEntityStack.push(this.fCurrentEntity);
        }
        this.fCurrentEntity = new ScannedEntity(name, new XMLResourceIdentifierImpl(publicId, literalSystemId, baseSystemId, expandedSystemId), stream, reader, encoding, literal, false, isExternal);
        if (this.fEntityHandler != null) {
            this.fResourceIdentifier.setValues(publicId, literalSystemId, baseSystemId, expandedSystemId);
            this.fEntityHandler.startEntity(name, this.fResourceIdentifier, encoding);
        }
    }
    
    public XMLEntityScanner getEntityScanner() {
        return this.fEntityScanner;
    }
    
    public void closeReaders() {
        for (int i = this.fOwnReaders.size() - 1; i >= 0; --i) {
            try {
                this.fOwnReaders.elementAt(i).close();
            }
            catch (IOException ex) {}
        }
        this.fOwnReaders.removeAllElements();
    }
    
    public void reset(final XMLComponentManager componentManager) throws XMLConfigurationException {
        try {
            this.fValidation = componentManager.getFeature("http://xml.org/sax/features/validation");
        }
        catch (XMLConfigurationException e) {
            this.fValidation = false;
        }
        try {
            this.fExternalGeneralEntities = componentManager.getFeature("http://xml.org/sax/features/external-general-entities");
        }
        catch (XMLConfigurationException e) {
            this.fExternalGeneralEntities = true;
        }
        try {
            this.fExternalParameterEntities = componentManager.getFeature("http://xml.org/sax/features/external-parameter-entities");
        }
        catch (XMLConfigurationException e) {
            this.fExternalParameterEntities = true;
        }
        try {
            this.fAllowJavaEncodings = componentManager.getFeature("http://apache.org/xml/features/allow-java-encodings");
        }
        catch (XMLConfigurationException e) {
            this.fAllowJavaEncodings = false;
        }
        try {
            this.fWarnDuplicateEntityDef = componentManager.getFeature("http://apache.org/xml/features/warn-on-duplicate-entitydef");
        }
        catch (XMLConfigurationException e) {
            this.fWarnDuplicateEntityDef = false;
        }
        this.fSymbolTable = (SymbolTable)componentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        this.fErrorReporter = (XMLErrorReporter)componentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        try {
            this.fEntityResolver = (XMLEntityResolver)componentManager.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
        }
        catch (XMLConfigurationException e) {
            this.fEntityResolver = null;
        }
        try {
            this.fValidationManager = (ValidationManager)componentManager.getProperty("http://apache.org/xml/properties/internal/validation-manager");
        }
        catch (XMLConfigurationException e) {
            this.fValidationManager = null;
        }
        try {
            this.fEntityExpansionLimit = (Integer)componentManager.getProperty("http://apache.org/xml/properties/entity-expansion-limit");
        }
        catch (XMLConfigurationException e) {
            this.fEntityExpansionLimit = null;
        }
        final Integer entityExpansionLimit = Integer.getInteger("entityExpansionLimit");
        if (entityExpansionLimit != null) {
            this.fEntityExpansionLimit = entityExpansionLimit;
        }
        this.fStandalone = false;
        this.fEntities.clear();
        this.fEntityStack.removeAllElements();
        this.fEntityExpansionCount = 0;
        this.fCurrentEntity = null;
        if (this.fDeclaredEntities != null) {
            final Enumeration keys = this.fDeclaredEntities.keys();
            while (keys.hasMoreElements()) {
                final Object key = keys.nextElement();
                final Object value = this.fDeclaredEntities.get(key);
                this.fEntities.put(key, value);
            }
        }
    }
    
    public String[] getRecognizedFeatures() {
        return XMLEntityManager.RECOGNIZED_FEATURES.clone();
    }
    
    public void setFeature(final String featureId, final boolean state) throws XMLConfigurationException {
        if (featureId.startsWith("http://apache.org/xml/features/")) {
            final String feature = featureId.substring("http://apache.org/xml/features/".length());
            if (feature.equals("allow-java-encodings")) {
                this.fAllowJavaEncodings = state;
            }
        }
    }
    
    public String[] getRecognizedProperties() {
        return XMLEntityManager.RECOGNIZED_PROPERTIES.clone();
    }
    
    public void setProperty(final String propertyId, final Object value) throws XMLConfigurationException {
        if (propertyId.startsWith("http://apache.org/xml/properties/")) {
            final String property = propertyId.substring("http://apache.org/xml/properties/".length());
            if (property.equals("internal/symbol-table")) {
                this.fSymbolTable = (SymbolTable)value;
                return;
            }
            if (property.equals("internal/error-reporter")) {
                this.fErrorReporter = (XMLErrorReporter)value;
                return;
            }
            if (property.equals("internal/entity-resolver")) {
                this.fEntityResolver = (XMLEntityResolver)value;
                return;
            }
            if (property.equals("input-buffer-size")) {
                final Integer bufferSize = (Integer)value;
                if (bufferSize != null && bufferSize > 64) {
                    this.fBufferSize = bufferSize;
                }
                return;
            }
            if (property.equals("entity-expansion-limit")) {
                final Integer entityExpansionLimit = (Integer)value;
            }
        }
    }
    
    public Boolean getFeatureDefault(final String featureId) {
        for (int i = 0; i < XMLEntityManager.RECOGNIZED_FEATURES.length; ++i) {
            if (XMLEntityManager.RECOGNIZED_FEATURES[i].equals(featureId)) {
                return XMLEntityManager.FEATURE_DEFAULTS[i];
            }
        }
        return null;
    }
    
    public Object getPropertyDefault(final String propertyId) {
        for (int i = 0; i < XMLEntityManager.RECOGNIZED_PROPERTIES.length; ++i) {
            if (XMLEntityManager.RECOGNIZED_PROPERTIES[i].equals(propertyId)) {
                return XMLEntityManager.PROPERTY_DEFAULTS[i];
            }
        }
        return null;
    }
    
    public static String expandSystemId(final String systemId) {
        return expandSystemId(systemId, null);
    }
    
    private static synchronized String getUserDir() {
        String userDir = "";
        try {
            userDir = System.getProperty("user.dir");
        }
        catch (SecurityException ex) {}
        if (userDir.length() == 0) {
            return "";
        }
        if (userDir.equals(XMLEntityManager.gUserDir)) {
            return XMLEntityManager.gEscapedUserDir;
        }
        XMLEntityManager.gUserDir = userDir;
        final char separator = File.separatorChar;
        userDir = userDir.replace(separator, '/');
        final int len = userDir.length();
        final StringBuffer buffer = new StringBuffer(len * 3);
        if (len >= 2 && userDir.charAt(1) == ':') {
            final int ch = Character.toUpperCase(userDir.charAt(0));
            if (ch >= 65 && ch <= 90) {
                buffer.append('/');
            }
        }
        int i;
        for (i = 0; i < len; ++i) {
            final int ch = userDir.charAt(i);
            if (ch >= 128) {
                break;
            }
            if (XMLEntityManager.gNeedEscaping[ch]) {
                buffer.append('%');
                buffer.append(XMLEntityManager.gAfterEscaping1[ch]);
                buffer.append(XMLEntityManager.gAfterEscaping2[ch]);
            }
            else {
                buffer.append((char)ch);
            }
        }
        if (i < len) {
            byte[] bytes = null;
            try {
                bytes = userDir.substring(i).getBytes("UTF-8");
            }
            catch (UnsupportedEncodingException e) {
                return userDir;
            }
            for (final byte b : bytes) {
                if (b < 0) {
                    final int ch = b + 256;
                    buffer.append('%');
                    buffer.append(XMLEntityManager.gHexChs[ch >> 4]);
                    buffer.append(XMLEntityManager.gHexChs[ch & 0xF]);
                }
                else if (XMLEntityManager.gNeedEscaping[b]) {
                    buffer.append('%');
                    buffer.append(XMLEntityManager.gAfterEscaping1[b]);
                    buffer.append(XMLEntityManager.gAfterEscaping2[b]);
                }
                else {
                    buffer.append((char)b);
                }
            }
        }
        if (!userDir.endsWith("/")) {
            buffer.append('/');
        }
        return XMLEntityManager.gEscapedUserDir = buffer.toString();
    }
    
    public static String expandSystemId(final String systemId, final String baseSystemId) {
        if (systemId == null || systemId.length() == 0) {
            return systemId;
        }
        try {
            final URI uri = new URI(systemId);
            if (uri != null) {
                return systemId;
            }
        }
        catch (URI.MalformedURIException ex) {}
        final String id = fixURI(systemId);
        URI base = null;
        URI uri2 = null;
        try {
            if (baseSystemId == null || baseSystemId.length() == 0 || baseSystemId.equals(systemId)) {
                final String dir = getUserDir();
                base = new URI("file", "", dir, null, null);
            }
            else {
                try {
                    base = new URI(fixURI(baseSystemId));
                }
                catch (URI.MalformedURIException e) {
                    if (baseSystemId.indexOf(58) != -1) {
                        base = new URI("file", "", fixURI(baseSystemId), null, null);
                    }
                    else {
                        String dir2 = getUserDir();
                        dir2 += fixURI(baseSystemId);
                        base = new URI("file", "", dir2, null, null);
                    }
                }
            }
            uri2 = new URI(base, id);
        }
        catch (Exception ex2) {}
        if (uri2 == null) {
            return systemId;
        }
        return uri2.toString();
    }
    
    protected void endEntity() throws XNIException {
        if (this.fEntityHandler != null) {
            this.fEntityHandler.endEntity(this.fCurrentEntity.name);
        }
        this.fCurrentEntity = ((this.fEntityStack.size() > 0) ? this.fEntityStack.pop() : null);
    }
    
    protected Object[] getEncodingName(final byte[] b4, final int count) {
        if (count < 2) {
            return new Object[] { "UTF-8", null };
        }
        final int b5 = b4[0] & 0xFF;
        final int b6 = b4[1] & 0xFF;
        if (b5 == 254 && b6 == 255) {
            return new Object[] { "UTF-16BE", new Boolean(true) };
        }
        if (b5 == 255 && b6 == 254) {
            return new Object[] { "UTF-16LE", new Boolean(false) };
        }
        if (count < 3) {
            return new Object[] { "UTF-8", null };
        }
        final int b7 = b4[2] & 0xFF;
        if (b5 == 239 && b6 == 187 && b7 == 191) {
            return new Object[] { "UTF-8", null };
        }
        if (count < 4) {
            return new Object[] { "UTF-8", null };
        }
        final int b8 = b4[3] & 0xFF;
        if (b5 == 0 && b6 == 0 && b7 == 0 && b8 == 60) {
            return new Object[] { "ISO-10646-UCS-4", new Boolean(true) };
        }
        if (b5 == 60 && b6 == 0 && b7 == 0 && b8 == 0) {
            return new Object[] { "ISO-10646-UCS-4", new Boolean(false) };
        }
        if (b5 == 0 && b6 == 0 && b7 == 60 && b8 == 0) {
            return new Object[] { "ISO-10646-UCS-4", null };
        }
        if (b5 == 0 && b6 == 60 && b7 == 0 && b8 == 0) {
            return new Object[] { "ISO-10646-UCS-4", null };
        }
        if (b5 == 0 && b6 == 60 && b7 == 0 && b8 == 63) {
            return new Object[] { "UTF-16BE", new Boolean(true) };
        }
        if (b5 == 60 && b6 == 0 && b7 == 63 && b8 == 0) {
            return new Object[] { "UTF-16LE", new Boolean(false) };
        }
        if (b5 == 76 && b6 == 111 && b7 == 167 && b8 == 148) {
            return new Object[] { "CP037", null };
        }
        return new Object[] { "UTF-8", null };
    }
    
    protected Reader createReader(final InputStream inputStream, String encoding, final Boolean isBigEndian) throws IOException {
        if (encoding == null) {
            encoding = "UTF-8";
        }
        final String ENCODING = encoding.toUpperCase(Locale.ENGLISH);
        if (ENCODING.equals("UTF-8")) {
            return new UTF8Reader(inputStream, this.fBufferSize, this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210"), this.fErrorReporter.getLocale());
        }
        if (ENCODING.equals("US-ASCII")) {
            return new ASCIIReader(inputStream, this.fBufferSize, this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210"), this.fErrorReporter.getLocale());
        }
        if (ENCODING.equals("ISO-10646-UCS-4")) {
            if (isBigEndian != null) {
                final boolean isBE = isBigEndian;
                if (isBE) {
                    return new UCSReader(inputStream, (short)8);
                }
                return new UCSReader(inputStream, (short)4);
            }
            else {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EncodingByteOrderUnsupported", new Object[] { encoding }, (short)2);
            }
        }
        if (ENCODING.equals("ISO-10646-UCS-2")) {
            if (isBigEndian != null) {
                final boolean isBE = isBigEndian;
                if (isBE) {
                    return new UCSReader(inputStream, (short)2);
                }
                return new UCSReader(inputStream, (short)1);
            }
            else {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EncodingByteOrderUnsupported", new Object[] { encoding }, (short)2);
            }
        }
        final boolean validIANA = XMLChar.isValidIANAEncoding(encoding);
        final boolean validJava = XMLChar.isValidJavaEncoding(encoding);
        if (!validIANA || (this.fAllowJavaEncodings && !validJava)) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EncodingDeclInvalid", new Object[] { encoding }, (short)2);
            encoding = "ISO-8859-1";
        }
        String javaEncoding = EncodingMap.getIANA2JavaMapping(ENCODING);
        if (javaEncoding == null) {
            if (this.fAllowJavaEncodings) {
                javaEncoding = encoding;
            }
            else {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EncodingDeclInvalid", new Object[] { encoding }, (short)2);
                javaEncoding = "ISO8859_1";
            }
        }
        return new InputStreamReader(inputStream, javaEncoding);
    }
    
    protected XMLEntityScanner createEntityScanner() {
        return new EntityScanner();
    }
    
    protected static String fixURI(String str) {
        str = str.replace(File.separatorChar, '/');
        if (str.length() >= 2) {
            final char ch1 = str.charAt(1);
            if (ch1 == ':') {
                final char ch2 = Character.toUpperCase(str.charAt(0));
                if (ch2 >= 'A' && ch2 <= 'Z') {
                    str = "/" + str;
                }
            }
            else if (ch1 == '/' && str.charAt(0) == '/') {
                str = "file:" + str;
            }
        }
        return str;
    }
    
    Hashtable getDeclaredEntities() {
        return this.fEntities;
    }
    
    final void print() {
    }
    
    static {
        RECOGNIZED_FEATURES = new String[] { "http://xml.org/sax/features/validation", "http://xml.org/sax/features/external-general-entities", "http://xml.org/sax/features/external-parameter-entities", "http://apache.org/xml/features/allow-java-encodings", "http://apache.org/xml/features/warn-on-duplicate-entitydef" };
        FEATURE_DEFAULTS = new Boolean[] { null, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE };
        RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/validation-manager", "http://apache.org/xml/properties/input-buffer-size", "http://apache.org/xml/properties/entity-expansion-limit" };
        PROPERTY_DEFAULTS = new Object[] { null, null, null, null, new Integer(2048), null };
        XMLEntity = "[xml]".intern();
        DTDEntity = "[dtd]".intern();
        XMLEntityManager.entityExpansionLimit = 0;
        XMLEntityManager.gNeedEscaping = new boolean[128];
        XMLEntityManager.gAfterEscaping1 = new char[128];
        XMLEntityManager.gAfterEscaping2 = new char[128];
        XMLEntityManager.gHexChs = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        for (int i = 0; i <= 31; ++i) {
            XMLEntityManager.gNeedEscaping[i] = true;
            XMLEntityManager.gAfterEscaping1[i] = XMLEntityManager.gHexChs[i >> 4];
            XMLEntityManager.gAfterEscaping2[i] = XMLEntityManager.gHexChs[i & 0xF];
        }
        XMLEntityManager.gNeedEscaping[127] = true;
        XMLEntityManager.gAfterEscaping1[127] = '7';
        XMLEntityManager.gAfterEscaping2[127] = 'F';
        for (final char ch : new char[] { ' ', '<', '>', '#', '%', '\"', '{', '}', '|', '\\', '^', '~', '[', ']', '`' }) {
            XMLEntityManager.gNeedEscaping[ch] = true;
            XMLEntityManager.gAfterEscaping1[ch] = XMLEntityManager.gHexChs[ch >> 4];
            XMLEntityManager.gAfterEscaping2[ch] = XMLEntityManager.gHexChs[ch & '\u000f'];
        }
    }
    
    protected abstract static class Entity
    {
        public String name;
        public boolean inExternalSubset;
        
        public Entity() {
            this.clear();
        }
        
        public Entity(final String name, final boolean inExternalSubset) {
            this.name = name;
            this.inExternalSubset = inExternalSubset;
        }
        
        public boolean isEntityDeclInExternalSubset() {
            return this.inExternalSubset;
        }
        
        public abstract boolean isExternal();
        
        public abstract boolean isUnparsed();
        
        public void clear() {
            this.name = null;
            this.inExternalSubset = false;
        }
        
        public void setValues(final Entity entity) {
            this.name = entity.name;
            this.inExternalSubset = entity.inExternalSubset;
        }
    }
    
    protected static class InternalEntity extends Entity
    {
        public String text;
        
        public InternalEntity() {
            this.clear();
        }
        
        public InternalEntity(final String name, final String text, final boolean inExternalSubset) {
            super(name, inExternalSubset);
            this.text = text;
        }
        
        public final boolean isExternal() {
            return false;
        }
        
        public final boolean isUnparsed() {
            return false;
        }
        
        public void clear() {
            super.clear();
            this.text = null;
        }
        
        public void setValues(final Entity entity) {
            super.setValues(entity);
            this.text = null;
        }
        
        public void setValues(final InternalEntity entity) {
            super.setValues(entity);
            this.text = entity.text;
        }
    }
    
    protected static class ExternalEntity extends Entity
    {
        public XMLResourceIdentifier entityLocation;
        public String notation;
        
        public ExternalEntity() {
            this.clear();
        }
        
        public ExternalEntity(final String name, final XMLResourceIdentifier entityLocation, final String notation, final boolean inExternalSubset) {
            super(name, inExternalSubset);
            this.entityLocation = entityLocation;
            this.notation = notation;
        }
        
        public final boolean isExternal() {
            return true;
        }
        
        public final boolean isUnparsed() {
            return this.notation != null;
        }
        
        public void clear() {
            super.clear();
            this.entityLocation = null;
            this.notation = null;
        }
        
        public void setValues(final Entity entity) {
            super.setValues(entity);
            this.entityLocation = null;
            this.notation = null;
        }
        
        public void setValues(final ExternalEntity entity) {
            super.setValues(entity);
            this.entityLocation = entity.entityLocation;
            this.notation = entity.notation;
        }
    }
    
    protected class ScannedEntity extends Entity
    {
        public InputStream stream;
        public Reader reader;
        public XMLResourceIdentifier entityLocation;
        public int lineNumber;
        public int columnNumber;
        public String encoding;
        public boolean literal;
        public boolean isExternal;
        public char[] ch;
        public int position;
        public int count;
        public boolean mayReadChunks;
        
        public ScannedEntity(final String name, final XMLResourceIdentifier entityLocation, final InputStream stream, final Reader reader, final String encoding, final boolean literal, final boolean mayReadChunks, final boolean isExternal) {
            super(name, XMLEntityManager.this.fInExternalSubset);
            this.lineNumber = 1;
            this.columnNumber = 1;
            this.ch = null;
            this.entityLocation = entityLocation;
            this.stream = stream;
            this.reader = reader;
            this.encoding = encoding;
            this.literal = literal;
            this.mayReadChunks = mayReadChunks;
            this.isExternal = isExternal;
            this.ch = new char[isExternal ? XMLEntityManager.this.fBufferSize : 1024];
        }
        
        public final boolean isExternal() {
            return this.isExternal;
        }
        
        public final boolean isUnparsed() {
            return false;
        }
        
        public String toString() {
            final StringBuffer str = new StringBuffer();
            str.append("name=\"" + super.name + '\"');
            str.append(",ch=" + (Object)this.ch);
            str.append(",position=" + this.position);
            str.append(",count=" + this.count);
            return str.toString();
        }
    }
    
    protected class EntityScanner extends XMLEntityScanner
    {
        public String getBaseSystemId() {
            return (XMLEntityManager.this.fCurrentEntity != null && XMLEntityManager.this.fCurrentEntity.entityLocation != null) ? XMLEntityManager.this.fCurrentEntity.entityLocation.getExpandedSystemId() : null;
        }
        
        public void setEncoding(final String encoding) throws IOException {
            if (XMLEntityManager.this.fCurrentEntity.stream != null && (XMLEntityManager.this.fCurrentEntity.encoding == null || !XMLEntityManager.this.fCurrentEntity.encoding.equals(encoding))) {
                if (XMLEntityManager.this.fCurrentEntity.encoding != null && XMLEntityManager.this.fCurrentEntity.encoding.startsWith("UTF-16")) {
                    final String ENCODING = encoding.toUpperCase(Locale.ENGLISH);
                    if (ENCODING.equals("UTF-16")) {
                        return;
                    }
                    if (ENCODING.equals("ISO-10646-UCS-4")) {
                        if (XMLEntityManager.this.fCurrentEntity.encoding.equals("UTF-16BE")) {
                            XMLEntityManager.this.fCurrentEntity.reader = new UCSReader(XMLEntityManager.this.fCurrentEntity.stream, (short)8);
                        }
                        else {
                            XMLEntityManager.this.fCurrentEntity.reader = new UCSReader(XMLEntityManager.this.fCurrentEntity.stream, (short)4);
                        }
                        return;
                    }
                    if (ENCODING.equals("ISO-10646-UCS-2")) {
                        if (XMLEntityManager.this.fCurrentEntity.encoding.equals("UTF-16BE")) {
                            XMLEntityManager.this.fCurrentEntity.reader = new UCSReader(XMLEntityManager.this.fCurrentEntity.stream, (short)2);
                        }
                        else {
                            XMLEntityManager.this.fCurrentEntity.reader = new UCSReader(XMLEntityManager.this.fCurrentEntity.stream, (short)1);
                        }
                        return;
                    }
                }
                XMLEntityManager.this.fCurrentEntity.reader = XMLEntityManager.this.createReader(XMLEntityManager.this.fCurrentEntity.stream, encoding, null);
            }
        }
        
        public boolean isExternal() {
            return XMLEntityManager.this.fCurrentEntity.isExternal();
        }
        
        public int peekChar() throws IOException {
            if (XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count) {
                this.load(0, true);
            }
            final int c = XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.position];
            if (XMLEntityManager.this.fCurrentEntity.isExternal()) {
                return (c != 13) ? c : 10;
            }
            return c;
        }
        
        public int scanChar() throws IOException {
            if (XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count) {
                this.load(0, true);
            }
            int c = XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.position++];
            boolean external = false;
            if (c == 10 || (c == 13 && (external = XMLEntityManager.this.fCurrentEntity.isExternal()))) {
                final ScannedEntity fCurrentEntity = XMLEntityManager.this.fCurrentEntity;
                ++fCurrentEntity.lineNumber;
                XMLEntityManager.this.fCurrentEntity.columnNumber = 1;
                if (XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count) {
                    XMLEntityManager.this.fCurrentEntity.ch[0] = (char)c;
                    this.load(1, false);
                }
                if (c == 13 && external) {
                    if (XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.position++] != '\n') {
                        final ScannedEntity fCurrentEntity2 = XMLEntityManager.this.fCurrentEntity;
                        --fCurrentEntity2.position;
                    }
                    c = 10;
                }
            }
            final ScannedEntity fCurrentEntity3 = XMLEntityManager.this.fCurrentEntity;
            ++fCurrentEntity3.columnNumber;
            return c;
        }
        
        public String scanNmtoken() throws IOException {
            if (XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count) {
                this.load(0, true);
            }
            int offset = XMLEntityManager.this.fCurrentEntity.position;
            while (XMLChar.isName(XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.position])) {
                if (++XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count) {
                    final int length = XMLEntityManager.this.fCurrentEntity.position - offset;
                    if (length == XMLEntityManager.this.fBufferSize) {
                        final char[] tmp = new char[XMLEntityManager.this.fBufferSize * 2];
                        System.arraycopy(XMLEntityManager.this.fCurrentEntity.ch, offset, tmp, 0, length);
                        XMLEntityManager.this.fCurrentEntity.ch = tmp;
                        final XMLEntityManager this$0 = XMLEntityManager.this;
                        this$0.fBufferSize *= 2;
                    }
                    else {
                        System.arraycopy(XMLEntityManager.this.fCurrentEntity.ch, offset, XMLEntityManager.this.fCurrentEntity.ch, 0, length);
                    }
                    offset = 0;
                    if (this.load(length, false)) {
                        break;
                    }
                    continue;
                }
            }
            final int length = XMLEntityManager.this.fCurrentEntity.position - offset;
            final ScannedEntity fCurrentEntity = XMLEntityManager.this.fCurrentEntity;
            fCurrentEntity.columnNumber += length;
            String symbol = null;
            if (length > 0) {
                symbol = XMLEntityManager.this.fSymbolTable.addSymbol(XMLEntityManager.this.fCurrentEntity.ch, offset, length);
            }
            return symbol;
        }
        
        public String scanName() throws IOException {
            if (XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count) {
                this.load(0, true);
            }
            int offset = XMLEntityManager.this.fCurrentEntity.position;
            if (XMLChar.isNameStart(XMLEntityManager.this.fCurrentEntity.ch[offset])) {
                if (++XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count) {
                    XMLEntityManager.this.fCurrentEntity.ch[0] = XMLEntityManager.this.fCurrentEntity.ch[offset];
                    offset = 0;
                    if (this.load(1, false)) {
                        final ScannedEntity fCurrentEntity = XMLEntityManager.this.fCurrentEntity;
                        ++fCurrentEntity.columnNumber;
                        final String symbol = XMLEntityManager.this.fSymbolTable.addSymbol(XMLEntityManager.this.fCurrentEntity.ch, 0, 1);
                        return symbol;
                    }
                }
                while (XMLChar.isName(XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.position])) {
                    if (++XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count) {
                        final int length = XMLEntityManager.this.fCurrentEntity.position - offset;
                        if (length == XMLEntityManager.this.fBufferSize) {
                            final char[] tmp = new char[XMLEntityManager.this.fBufferSize * 2];
                            System.arraycopy(XMLEntityManager.this.fCurrentEntity.ch, offset, tmp, 0, length);
                            XMLEntityManager.this.fCurrentEntity.ch = tmp;
                            final XMLEntityManager this$0 = XMLEntityManager.this;
                            this$0.fBufferSize *= 2;
                        }
                        else {
                            System.arraycopy(XMLEntityManager.this.fCurrentEntity.ch, offset, XMLEntityManager.this.fCurrentEntity.ch, 0, length);
                        }
                        offset = 0;
                        if (this.load(length, false)) {
                            break;
                        }
                        continue;
                    }
                }
            }
            final int length = XMLEntityManager.this.fCurrentEntity.position - offset;
            final ScannedEntity fCurrentEntity2 = XMLEntityManager.this.fCurrentEntity;
            fCurrentEntity2.columnNumber += length;
            String symbol2 = null;
            if (length > 0) {
                symbol2 = XMLEntityManager.this.fSymbolTable.addSymbol(XMLEntityManager.this.fCurrentEntity.ch, offset, length);
            }
            return symbol2;
        }
        
        public boolean scanQName(final QName qname) throws IOException {
            if (XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count) {
                this.load(0, true);
            }
            int offset = XMLEntityManager.this.fCurrentEntity.position;
            if (XMLChar.isNameStart(XMLEntityManager.this.fCurrentEntity.ch[offset])) {
                if (++XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count) {
                    XMLEntityManager.this.fCurrentEntity.ch[0] = XMLEntityManager.this.fCurrentEntity.ch[offset];
                    offset = 0;
                    if (this.load(1, false)) {
                        final ScannedEntity fCurrentEntity = XMLEntityManager.this.fCurrentEntity;
                        ++fCurrentEntity.columnNumber;
                        final String name = XMLEntityManager.this.fSymbolTable.addSymbol(XMLEntityManager.this.fCurrentEntity.ch, 0, 1);
                        qname.setValues(null, name, name, null);
                        return true;
                    }
                }
                int index = -1;
                while (XMLChar.isName(XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.position])) {
                    final char c = XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.position];
                    if (c == ':') {
                        if (index != -1) {
                            break;
                        }
                        index = XMLEntityManager.this.fCurrentEntity.position;
                    }
                    if (++XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count) {
                        final int length = XMLEntityManager.this.fCurrentEntity.position - offset;
                        if (length == XMLEntityManager.this.fBufferSize) {
                            final char[] tmp = new char[XMLEntityManager.this.fBufferSize * 2];
                            System.arraycopy(XMLEntityManager.this.fCurrentEntity.ch, offset, tmp, 0, length);
                            XMLEntityManager.this.fCurrentEntity.ch = tmp;
                            final XMLEntityManager this$0 = XMLEntityManager.this;
                            this$0.fBufferSize *= 2;
                        }
                        else {
                            System.arraycopy(XMLEntityManager.this.fCurrentEntity.ch, offset, XMLEntityManager.this.fCurrentEntity.ch, 0, length);
                        }
                        if (index != -1) {
                            index -= offset;
                        }
                        offset = 0;
                        if (this.load(length, false)) {
                            break;
                        }
                        continue;
                    }
                }
                final int length2 = XMLEntityManager.this.fCurrentEntity.position - offset;
                final ScannedEntity fCurrentEntity2 = XMLEntityManager.this.fCurrentEntity;
                fCurrentEntity2.columnNumber += length2;
                if (length2 > 0) {
                    String prefix = null;
                    String localpart = null;
                    final String rawname = XMLEntityManager.this.fSymbolTable.addSymbol(XMLEntityManager.this.fCurrentEntity.ch, offset, length2);
                    if (index != -1) {
                        final int prefixLength = index - offset;
                        prefix = XMLEntityManager.this.fSymbolTable.addSymbol(XMLEntityManager.this.fCurrentEntity.ch, offset, prefixLength);
                        final int len = length2 - prefixLength - 1;
                        localpart = XMLEntityManager.this.fSymbolTable.addSymbol(XMLEntityManager.this.fCurrentEntity.ch, index + 1, len);
                    }
                    else {
                        localpart = rawname;
                    }
                    qname.setValues(prefix, localpart, rawname, null);
                    return true;
                }
            }
            return false;
        }
        
        public int scanContent(final XMLString content) throws IOException {
            if (XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count) {
                this.load(0, true);
            }
            else if (XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count - 1) {
                XMLEntityManager.this.fCurrentEntity.ch[0] = XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.count - 1];
                this.load(1, false);
                XMLEntityManager.this.fCurrentEntity.position = 0;
            }
            int offset = XMLEntityManager.this.fCurrentEntity.position;
            int c = XMLEntityManager.this.fCurrentEntity.ch[offset];
            int newlines = 0;
            final boolean external = XMLEntityManager.this.fCurrentEntity.isExternal();
            if (c == 10 || (c == 13 && external)) {
                do {
                    c = XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.position++];
                    if (c == 13 && external) {
                        ++newlines;
                        final ScannedEntity fCurrentEntity = XMLEntityManager.this.fCurrentEntity;
                        ++fCurrentEntity.lineNumber;
                        XMLEntityManager.this.fCurrentEntity.columnNumber = 1;
                        if (XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count) {
                            offset = 0;
                            XMLEntityManager.this.fCurrentEntity.position = newlines;
                            if (this.load(newlines, false)) {
                                break;
                            }
                        }
                        if (XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.position] == '\n') {
                            final ScannedEntity fCurrentEntity2 = XMLEntityManager.this.fCurrentEntity;
                            ++fCurrentEntity2.position;
                            ++offset;
                        }
                        else {
                            ++newlines;
                        }
                    }
                    else {
                        if (c != 10) {
                            final ScannedEntity fCurrentEntity3 = XMLEntityManager.this.fCurrentEntity;
                            --fCurrentEntity3.position;
                            break;
                        }
                        ++newlines;
                        final ScannedEntity fCurrentEntity4 = XMLEntityManager.this.fCurrentEntity;
                        ++fCurrentEntity4.lineNumber;
                        XMLEntityManager.this.fCurrentEntity.columnNumber = 1;
                        if (XMLEntityManager.this.fCurrentEntity.position != XMLEntityManager.this.fCurrentEntity.count) {
                            continue;
                        }
                        offset = 0;
                        XMLEntityManager.this.fCurrentEntity.position = newlines;
                        if (this.load(newlines, false)) {
                            break;
                        }
                        continue;
                    }
                } while (XMLEntityManager.this.fCurrentEntity.position < XMLEntityManager.this.fCurrentEntity.count - 1);
                for (int i = offset; i < XMLEntityManager.this.fCurrentEntity.position; ++i) {
                    XMLEntityManager.this.fCurrentEntity.ch[i] = '\n';
                }
                final int length = XMLEntityManager.this.fCurrentEntity.position - offset;
                if (XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count - 1) {
                    content.setValues(XMLEntityManager.this.fCurrentEntity.ch, offset, length);
                    return -1;
                }
            }
            while (XMLEntityManager.this.fCurrentEntity.position < XMLEntityManager.this.fCurrentEntity.count) {
                c = XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.position++];
                if (!XMLChar.isContent(c)) {
                    final ScannedEntity fCurrentEntity5 = XMLEntityManager.this.fCurrentEntity;
                    --fCurrentEntity5.position;
                    break;
                }
            }
            final int length2 = XMLEntityManager.this.fCurrentEntity.position - offset;
            final ScannedEntity fCurrentEntity6 = XMLEntityManager.this.fCurrentEntity;
            fCurrentEntity6.columnNumber += length2 - newlines;
            content.setValues(XMLEntityManager.this.fCurrentEntity.ch, offset, length2);
            if (XMLEntityManager.this.fCurrentEntity.position != XMLEntityManager.this.fCurrentEntity.count) {
                c = XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.position];
                if (c == 13 && external) {
                    c = 10;
                }
            }
            else {
                c = -1;
            }
            return c;
        }
        
        public int scanLiteral(final int quote, final XMLString content) throws IOException {
            if (XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count) {
                this.load(0, true);
            }
            else if (XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count - 1) {
                XMLEntityManager.this.fCurrentEntity.ch[0] = XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.count - 1];
                this.load(1, false);
                XMLEntityManager.this.fCurrentEntity.position = 0;
            }
            int offset = XMLEntityManager.this.fCurrentEntity.position;
            int c = XMLEntityManager.this.fCurrentEntity.ch[offset];
            int newlines = 0;
            final boolean external = XMLEntityManager.this.fCurrentEntity.isExternal();
            if (c == 10 || (c == 13 && external)) {
                do {
                    c = XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.position++];
                    if (c == 13 && external) {
                        ++newlines;
                        final ScannedEntity fCurrentEntity = XMLEntityManager.this.fCurrentEntity;
                        ++fCurrentEntity.lineNumber;
                        XMLEntityManager.this.fCurrentEntity.columnNumber = 1;
                        if (XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count) {
                            offset = 0;
                            XMLEntityManager.this.fCurrentEntity.position = newlines;
                            if (this.load(newlines, false)) {
                                break;
                            }
                        }
                        if (XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.position] == '\n') {
                            final ScannedEntity fCurrentEntity2 = XMLEntityManager.this.fCurrentEntity;
                            ++fCurrentEntity2.position;
                            ++offset;
                        }
                        else {
                            ++newlines;
                        }
                    }
                    else {
                        if (c != 10) {
                            final ScannedEntity fCurrentEntity3 = XMLEntityManager.this.fCurrentEntity;
                            --fCurrentEntity3.position;
                            break;
                        }
                        ++newlines;
                        final ScannedEntity fCurrentEntity4 = XMLEntityManager.this.fCurrentEntity;
                        ++fCurrentEntity4.lineNumber;
                        XMLEntityManager.this.fCurrentEntity.columnNumber = 1;
                        if (XMLEntityManager.this.fCurrentEntity.position != XMLEntityManager.this.fCurrentEntity.count) {
                            continue;
                        }
                        offset = 0;
                        XMLEntityManager.this.fCurrentEntity.position = newlines;
                        if (this.load(newlines, false)) {
                            break;
                        }
                        continue;
                    }
                } while (XMLEntityManager.this.fCurrentEntity.position < XMLEntityManager.this.fCurrentEntity.count - 1);
                for (int i = offset; i < XMLEntityManager.this.fCurrentEntity.position; ++i) {
                    XMLEntityManager.this.fCurrentEntity.ch[i] = '\n';
                }
                final int length = XMLEntityManager.this.fCurrentEntity.position - offset;
                if (XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count - 1) {
                    content.setValues(XMLEntityManager.this.fCurrentEntity.ch, offset, length);
                    return -1;
                }
            }
            while (XMLEntityManager.this.fCurrentEntity.position < XMLEntityManager.this.fCurrentEntity.count) {
                c = XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.position++];
                if ((c == quote && (!XMLEntityManager.this.fCurrentEntity.literal || external)) || c == 37 || !XMLChar.isContent(c)) {
                    final ScannedEntity fCurrentEntity5 = XMLEntityManager.this.fCurrentEntity;
                    --fCurrentEntity5.position;
                    break;
                }
            }
            final int length2 = XMLEntityManager.this.fCurrentEntity.position - offset;
            final ScannedEntity fCurrentEntity6 = XMLEntityManager.this.fCurrentEntity;
            fCurrentEntity6.columnNumber += length2 - newlines;
            content.setValues(XMLEntityManager.this.fCurrentEntity.ch, offset, length2);
            if (XMLEntityManager.this.fCurrentEntity.position != XMLEntityManager.this.fCurrentEntity.count) {
                c = XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.position];
                if (c == quote && XMLEntityManager.this.fCurrentEntity.literal) {
                    c = -1;
                }
            }
            else {
                c = -1;
            }
            return c;
        }
        
        public boolean scanData(final String delimiter, final XMLStringBuffer buffer) throws IOException {
            boolean done = false;
            final int delimLen = delimiter.length();
            final char charAt0 = delimiter.charAt(0);
            final boolean external = XMLEntityManager.this.fCurrentEntity.isExternal();
            do {
                if (XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count) {
                    this.load(0, true);
                }
                for (boolean bNextEntity = false; XMLEntityManager.this.fCurrentEntity.position >= XMLEntityManager.this.fCurrentEntity.count - delimLen && !bNextEntity; bNextEntity = this.load(XMLEntityManager.this.fCurrentEntity.count - XMLEntityManager.this.fCurrentEntity.position, false), XMLEntityManager.this.fCurrentEntity.position = 0) {
                    System.arraycopy(XMLEntityManager.this.fCurrentEntity.ch, XMLEntityManager.this.fCurrentEntity.position, XMLEntityManager.this.fCurrentEntity.ch, 0, XMLEntityManager.this.fCurrentEntity.count - XMLEntityManager.this.fCurrentEntity.position);
                }
                if (XMLEntityManager.this.fCurrentEntity.position >= XMLEntityManager.this.fCurrentEntity.count - delimLen) {
                    final int length = XMLEntityManager.this.fCurrentEntity.count - XMLEntityManager.this.fCurrentEntity.position;
                    buffer.append(XMLEntityManager.this.fCurrentEntity.ch, XMLEntityManager.this.fCurrentEntity.position, length);
                    final ScannedEntity fCurrentEntity = XMLEntityManager.this.fCurrentEntity;
                    fCurrentEntity.columnNumber += XMLEntityManager.this.fCurrentEntity.count;
                    XMLEntityManager.this.fCurrentEntity.position = XMLEntityManager.this.fCurrentEntity.count;
                    this.load(0, true);
                    return false;
                }
                int offset = XMLEntityManager.this.fCurrentEntity.position;
                int c = XMLEntityManager.this.fCurrentEntity.ch[offset];
                int newlines = 0;
                if (c == 10 || (c == 13 && external)) {
                    do {
                        c = XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.position++];
                        if (c == 13 && external) {
                            ++newlines;
                            final ScannedEntity fCurrentEntity2 = XMLEntityManager.this.fCurrentEntity;
                            ++fCurrentEntity2.lineNumber;
                            XMLEntityManager.this.fCurrentEntity.columnNumber = 1;
                            if (XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count) {
                                offset = 0;
                                XMLEntityManager.this.fCurrentEntity.position = newlines;
                                if (this.load(newlines, false)) {
                                    break;
                                }
                            }
                            if (XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.position] == '\n') {
                                final ScannedEntity fCurrentEntity3 = XMLEntityManager.this.fCurrentEntity;
                                ++fCurrentEntity3.position;
                                ++offset;
                            }
                            else {
                                ++newlines;
                            }
                        }
                        else {
                            if (c != 10) {
                                final ScannedEntity fCurrentEntity4 = XMLEntityManager.this.fCurrentEntity;
                                --fCurrentEntity4.position;
                                break;
                            }
                            ++newlines;
                            final ScannedEntity fCurrentEntity5 = XMLEntityManager.this.fCurrentEntity;
                            ++fCurrentEntity5.lineNumber;
                            XMLEntityManager.this.fCurrentEntity.columnNumber = 1;
                            if (XMLEntityManager.this.fCurrentEntity.position != XMLEntityManager.this.fCurrentEntity.count) {
                                continue;
                            }
                            offset = 0;
                            XMLEntityManager.this.fCurrentEntity.position = newlines;
                            XMLEntityManager.this.fCurrentEntity.count = newlines;
                            if (this.load(newlines, false)) {
                                break;
                            }
                            continue;
                        }
                    } while (XMLEntityManager.this.fCurrentEntity.position < XMLEntityManager.this.fCurrentEntity.count - 1);
                    for (int i = offset; i < XMLEntityManager.this.fCurrentEntity.position; ++i) {
                        XMLEntityManager.this.fCurrentEntity.ch[i] = '\n';
                    }
                    final int length2 = XMLEntityManager.this.fCurrentEntity.position - offset;
                    if (XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count - 1) {
                        buffer.append(XMLEntityManager.this.fCurrentEntity.ch, offset, length2);
                        return true;
                    }
                }
            Label_1126:
                while (XMLEntityManager.this.fCurrentEntity.position < XMLEntityManager.this.fCurrentEntity.count) {
                    c = XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.position++];
                    if (c == charAt0) {
                        final int delimOffset = XMLEntityManager.this.fCurrentEntity.position - 1;
                        for (int j = 1; j < delimLen; ++j) {
                            if (XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count) {
                                final ScannedEntity fCurrentEntity6 = XMLEntityManager.this.fCurrentEntity;
                                fCurrentEntity6.position -= j;
                                break Label_1126;
                            }
                            c = XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.position++];
                            if (delimiter.charAt(j) != c) {
                                final ScannedEntity fCurrentEntity7 = XMLEntityManager.this.fCurrentEntity;
                                --fCurrentEntity7.position;
                                break;
                            }
                        }
                        if (XMLEntityManager.this.fCurrentEntity.position == delimOffset + delimLen) {
                            done = true;
                            break;
                        }
                        continue;
                    }
                    else {
                        if (c == 10 || (external && c == 13)) {
                            final ScannedEntity fCurrentEntity8 = XMLEntityManager.this.fCurrentEntity;
                            --fCurrentEntity8.position;
                            break;
                        }
                        if (XMLChar.isInvalid(c)) {
                            final ScannedEntity fCurrentEntity9 = XMLEntityManager.this.fCurrentEntity;
                            --fCurrentEntity9.position;
                            final int length3 = XMLEntityManager.this.fCurrentEntity.position - offset;
                            final ScannedEntity fCurrentEntity10 = XMLEntityManager.this.fCurrentEntity;
                            fCurrentEntity10.columnNumber += length3 - newlines;
                            buffer.append(XMLEntityManager.this.fCurrentEntity.ch, offset, length3);
                            return true;
                        }
                        continue;
                    }
                }
                int length3 = XMLEntityManager.this.fCurrentEntity.position - offset;
                final ScannedEntity fCurrentEntity11 = XMLEntityManager.this.fCurrentEntity;
                fCurrentEntity11.columnNumber += length3 - newlines;
                if (done) {
                    length3 -= delimLen;
                }
                buffer.append(XMLEntityManager.this.fCurrentEntity.ch, offset, length3);
            } while (!done);
            return !done;
        }
        
        public boolean skipChar(final int c) throws IOException {
            if (XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count) {
                this.load(0, true);
            }
            final int cc = XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.position];
            if (cc == c) {
                final ScannedEntity fCurrentEntity = XMLEntityManager.this.fCurrentEntity;
                ++fCurrentEntity.position;
                if (c == 10) {
                    final ScannedEntity fCurrentEntity2 = XMLEntityManager.this.fCurrentEntity;
                    ++fCurrentEntity2.lineNumber;
                    XMLEntityManager.this.fCurrentEntity.columnNumber = 1;
                }
                else {
                    final ScannedEntity fCurrentEntity3 = XMLEntityManager.this.fCurrentEntity;
                    ++fCurrentEntity3.columnNumber;
                }
                return true;
            }
            if (c == 10 && cc == 13 && XMLEntityManager.this.fCurrentEntity.isExternal()) {
                if (XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count) {
                    XMLEntityManager.this.fCurrentEntity.ch[0] = (char)cc;
                    this.load(1, false);
                }
                final ScannedEntity fCurrentEntity4 = XMLEntityManager.this.fCurrentEntity;
                ++fCurrentEntity4.position;
                if (XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.position] == '\n') {
                    final ScannedEntity fCurrentEntity5 = XMLEntityManager.this.fCurrentEntity;
                    ++fCurrentEntity5.position;
                }
                final ScannedEntity fCurrentEntity6 = XMLEntityManager.this.fCurrentEntity;
                ++fCurrentEntity6.lineNumber;
                XMLEntityManager.this.fCurrentEntity.columnNumber = 1;
                return true;
            }
            return false;
        }
        
        public boolean skipSpaces() throws IOException {
            if (XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count) {
                this.load(0, true);
            }
            int c = XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.position];
            if (XMLChar.isSpace(c)) {
                final boolean external = XMLEntityManager.this.fCurrentEntity.isExternal();
                do {
                    boolean entityChanged = false;
                    if (c == 10 || (external && c == 13)) {
                        final ScannedEntity fCurrentEntity = XMLEntityManager.this.fCurrentEntity;
                        ++fCurrentEntity.lineNumber;
                        XMLEntityManager.this.fCurrentEntity.columnNumber = 1;
                        if (XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count - 1) {
                            XMLEntityManager.this.fCurrentEntity.ch[0] = (char)c;
                            entityChanged = this.load(1, true);
                            if (!entityChanged) {
                                XMLEntityManager.this.fCurrentEntity.position = 0;
                            }
                        }
                        if (c == 13 && external && XMLEntityManager.this.fCurrentEntity.ch[++XMLEntityManager.this.fCurrentEntity.position] != '\n') {
                            final ScannedEntity fCurrentEntity2 = XMLEntityManager.this.fCurrentEntity;
                            --fCurrentEntity2.position;
                        }
                    }
                    else {
                        final ScannedEntity fCurrentEntity3 = XMLEntityManager.this.fCurrentEntity;
                        ++fCurrentEntity3.columnNumber;
                    }
                    if (!entityChanged) {
                        final ScannedEntity fCurrentEntity4 = XMLEntityManager.this.fCurrentEntity;
                        ++fCurrentEntity4.position;
                    }
                    if (XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count) {
                        this.load(0, true);
                    }
                } while (XMLChar.isSpace(c = XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.position]));
                return true;
            }
            return false;
        }
        
        public boolean skipString(final String s) throws IOException {
            if (XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count) {
                this.load(0, true);
            }
            final int length = s.length();
            for (int i = 0; i < length; ++i) {
                final char c = XMLEntityManager.this.fCurrentEntity.ch[XMLEntityManager.this.fCurrentEntity.position++];
                if (c != s.charAt(i)) {
                    final ScannedEntity fCurrentEntity = XMLEntityManager.this.fCurrentEntity;
                    fCurrentEntity.position -= i + 1;
                    return false;
                }
                if (i < length - 1 && XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count) {
                    System.arraycopy(XMLEntityManager.this.fCurrentEntity.ch, XMLEntityManager.this.fCurrentEntity.count - i - 1, XMLEntityManager.this.fCurrentEntity.ch, 0, i + 1);
                    if (this.load(i + 1, false)) {
                        final ScannedEntity fCurrentEntity2 = XMLEntityManager.this.fCurrentEntity;
                        fCurrentEntity2.position -= i + 1;
                        return false;
                    }
                }
            }
            final ScannedEntity fCurrentEntity3 = XMLEntityManager.this.fCurrentEntity;
            fCurrentEntity3.columnNumber += length;
            return true;
        }
        
        public String getPublicId() {
            return (XMLEntityManager.this.fCurrentEntity != null && XMLEntityManager.this.fCurrentEntity.entityLocation != null) ? XMLEntityManager.this.fCurrentEntity.entityLocation.getPublicId() : null;
        }
        
        public String getExpandedSystemId() {
            if (XMLEntityManager.this.fCurrentEntity != null) {
                if (XMLEntityManager.this.fCurrentEntity.entityLocation != null && XMLEntityManager.this.fCurrentEntity.entityLocation.getExpandedSystemId() != null) {
                    return XMLEntityManager.this.fCurrentEntity.entityLocation.getExpandedSystemId();
                }
                final int size = XMLEntityManager.this.fEntityStack.size();
                for (int i = size - 1; i >= 0; --i) {
                    final ScannedEntity externalEntity = (ScannedEntity)XMLEntityManager.this.fEntityStack.elementAt(i);
                    if (externalEntity.entityLocation != null && externalEntity.entityLocation.getExpandedSystemId() != null) {
                        return externalEntity.entityLocation.getExpandedSystemId();
                    }
                }
            }
            return null;
        }
        
        public String getLiteralSystemId() {
            if (XMLEntityManager.this.fCurrentEntity != null) {
                if (XMLEntityManager.this.fCurrentEntity.entityLocation != null && XMLEntityManager.this.fCurrentEntity.entityLocation.getLiteralSystemId() != null) {
                    return XMLEntityManager.this.fCurrentEntity.entityLocation.getLiteralSystemId();
                }
                final int size = XMLEntityManager.this.fEntityStack.size();
                for (int i = size - 1; i >= 0; --i) {
                    final ScannedEntity externalEntity = (ScannedEntity)XMLEntityManager.this.fEntityStack.elementAt(i);
                    if (externalEntity.entityLocation != null && externalEntity.entityLocation.getLiteralSystemId() != null) {
                        return externalEntity.entityLocation.getLiteralSystemId();
                    }
                }
            }
            return null;
        }
        
        public int getLineNumber() {
            if (XMLEntityManager.this.fCurrentEntity != null) {
                if (XMLEntityManager.this.fCurrentEntity.isExternal()) {
                    return XMLEntityManager.this.fCurrentEntity.lineNumber;
                }
                final int size = XMLEntityManager.this.fEntityStack.size();
                for (int i = size - 1; i > 0; --i) {
                    final ScannedEntity firstExternalEntity = (ScannedEntity)XMLEntityManager.this.fEntityStack.elementAt(i);
                    if (firstExternalEntity.isExternal()) {
                        return firstExternalEntity.lineNumber;
                    }
                }
            }
            return -1;
        }
        
        public int getColumnNumber() {
            if (XMLEntityManager.this.fCurrentEntity != null) {
                if (XMLEntityManager.this.fCurrentEntity.isExternal()) {
                    return XMLEntityManager.this.fCurrentEntity.columnNumber;
                }
                final int size = XMLEntityManager.this.fEntityStack.size();
                for (int i = size - 1; i > 0; --i) {
                    final ScannedEntity firstExternalEntity = (ScannedEntity)XMLEntityManager.this.fEntityStack.elementAt(i);
                    if (firstExternalEntity.isExternal()) {
                        return firstExternalEntity.columnNumber;
                    }
                }
            }
            return -1;
        }
        
        final boolean load(final int offset, final boolean changeEntity) throws IOException {
            final int length = XMLEntityManager.this.fCurrentEntity.mayReadChunks ? (XMLEntityManager.this.fCurrentEntity.ch.length - offset) : 64;
            final int count = XMLEntityManager.this.fCurrentEntity.reader.read(XMLEntityManager.this.fCurrentEntity.ch, offset, length);
            boolean entityChanged = false;
            if (count != -1) {
                if (count != 0) {
                    XMLEntityManager.this.fCurrentEntity.count = count + offset;
                    XMLEntityManager.this.fCurrentEntity.position = offset;
                }
            }
            else {
                XMLEntityManager.this.fCurrentEntity.count = offset;
                XMLEntityManager.this.fCurrentEntity.position = offset;
                entityChanged = true;
                if (changeEntity) {
                    XMLEntityManager.this.endEntity();
                    if (XMLEntityManager.this.fCurrentEntity == null) {
                        throw new EOFException();
                    }
                    if (XMLEntityManager.this.fCurrentEntity.position == XMLEntityManager.this.fCurrentEntity.count) {
                        this.load(0, true);
                    }
                }
            }
            return entityChanged;
        }
    }
    
    protected final class RewindableInputStream extends InputStream
    {
        private InputStream fInputStream;
        private byte[] fData;
        private int fStartOffset;
        private int fEndOffset;
        private int fOffset;
        private int fLength;
        private int fMark;
        
        public RewindableInputStream(final InputStream is) {
            this.fData = new byte[64];
            this.fInputStream = is;
            this.fStartOffset = 0;
            this.fEndOffset = -1;
            this.fOffset = 0;
            this.fLength = 0;
            this.fMark = 0;
        }
        
        public void setStartOffset(final int offset) {
            this.fStartOffset = offset;
        }
        
        public void rewind() {
            this.fOffset = this.fStartOffset;
        }
        
        public int read() throws IOException {
            int b = 0;
            if (this.fOffset < this.fLength) {
                return this.fData[this.fOffset++] & 0xFF;
            }
            if (this.fOffset == this.fEndOffset) {
                return -1;
            }
            if (this.fOffset == this.fData.length) {
                final byte[] newData = new byte[this.fOffset << 1];
                System.arraycopy(this.fData, 0, newData, 0, this.fOffset);
                this.fData = newData;
            }
            b = this.fInputStream.read();
            if (b == -1) {
                this.fEndOffset = this.fOffset;
                return -1;
            }
            this.fData[this.fLength++] = (byte)b;
            ++this.fOffset;
            return b & 0xFF;
        }
        
        public int read(final byte[] b, final int off, int len) throws IOException {
            final int bytesLeft = this.fLength - this.fOffset;
            if (bytesLeft != 0) {
                if (len < bytesLeft) {
                    if (len <= 0) {
                        return 0;
                    }
                }
                else {
                    len = bytesLeft;
                }
                if (b != null) {
                    System.arraycopy(this.fData, this.fOffset, b, off, len);
                }
                this.fOffset += len;
                return len;
            }
            if (this.fOffset == this.fEndOffset) {
                return -1;
            }
            if (XMLEntityManager.this.fCurrentEntity.mayReadChunks) {
                return this.fInputStream.read(b, off, len);
            }
            final int returnedVal = this.read();
            if (returnedVal == -1) {
                this.fEndOffset = this.fOffset;
                return -1;
            }
            b[off] = (byte)returnedVal;
            return 1;
        }
        
        public long skip(long n) throws IOException {
            if (n <= 0L) {
                return 0L;
            }
            final int bytesLeft = this.fLength - this.fOffset;
            if (bytesLeft == 0) {
                if (this.fOffset == this.fEndOffset) {
                    return 0L;
                }
                return this.fInputStream.skip(n);
            }
            else {
                if (n <= bytesLeft) {
                    this.fOffset += (int)n;
                    return n;
                }
                this.fOffset += bytesLeft;
                if (this.fOffset == this.fEndOffset) {
                    return bytesLeft;
                }
                n -= bytesLeft;
                return this.fInputStream.skip(n) + bytesLeft;
            }
        }
        
        public int available() throws IOException {
            final int bytesLeft = this.fLength - this.fOffset;
            if (bytesLeft != 0) {
                return bytesLeft;
            }
            if (this.fOffset == this.fEndOffset) {
                return -1;
            }
            return XMLEntityManager.this.fCurrentEntity.mayReadChunks ? this.fInputStream.available() : 0;
        }
        
        public void mark(final int howMuch) {
            this.fMark = this.fOffset;
        }
        
        public void reset() {
            this.fOffset = this.fMark;
        }
        
        public boolean markSupported() {
            return true;
        }
        
        public void close() throws IOException {
            if (this.fInputStream != null) {
                this.fInputStream.close();
                this.fInputStream = null;
            }
        }
    }
}
