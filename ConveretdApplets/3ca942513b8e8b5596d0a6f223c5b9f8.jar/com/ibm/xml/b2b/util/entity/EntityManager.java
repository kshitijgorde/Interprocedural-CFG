// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.entity;

import java.io.File;
import com.ibm.xml.b2b.util.URI;
import com.ibm.xml.b2b.util.SymbolTable;

public final class EntityManager
{
    private boolean fStandalone;
    private int fEntityDepth;
    private int[] fEntityStack;
    private EntityEventHandler fHandler;
    private DTDEntityEventHandler fDTDHandler;
    private SymbolTable fSymbolTable;
    private ParsedEntityFactory fEntityFactory;
    private EntityDeclPool fDefaultEntityDeclPool;
    private EntityDeclPool fEntityDeclPool;
    private boolean fScanExternalGeneralEntities;
    private boolean fScanExternalParameterEntities;
    private boolean fInExternalEntity;
    private int fActiveReferences;
    private int[] fActiveReferencesStack;
    private int fActivePEReferences;
    private int[] fActivePEReferencesStack;
    private int[] fEntityHandleStack;
    private boolean[] fEntityExternalStack;
    private int fEntityStackDepth;
    private int fEntityCount;
    private int fFreeEntities;
    private ParsedEntity[] fParsedEntity;
    private String[] fBaseURIString;
    private int[] fBaseURI;
    
    public EntityManager(final EntityEventHandler fHandler, final SymbolTable fSymbolTable, final ParsedEntityFactory fEntityFactory) {
        this.fScanExternalGeneralEntities = true;
        this.fScanExternalParameterEntities = true;
        this.fHandler = fHandler;
        this.fSymbolTable = fSymbolTable;
        this.fEntityFactory = fEntityFactory;
        this.fDefaultEntityDeclPool = new EntityDeclPool(fSymbolTable);
        this.fEntityDeclPool = this.fDefaultEntityDeclPool;
        this.fEntityStack = new int[16];
        this.fActiveReferencesStack = new int[16];
        this.fActivePEReferencesStack = new int[16];
        this.fEntityHandleStack = new int[16];
        this.fEntityExternalStack = new boolean[16];
        this.fParsedEntity = new ParsedEntity[16];
        this.fBaseURIString = new String[16];
        this.fBaseURI = new int[16];
    }
    
    public void setDTDEntityEventHandler(final DTDEntityEventHandler fdtdHandler) {
        this.fDTDHandler = fdtdHandler;
    }
    
    public void setEntityDeclPool(final EntityDeclPool fEntityDeclPool) {
        if (fEntityDeclPool == null) {
            this.fEntityDeclPool = this.fDefaultEntityDeclPool;
        }
        else {
            this.fEntityDeclPool = fEntityDeclPool;
        }
    }
    
    public void reset(final boolean b) {
        for (int i = 0; i < this.fEntityCount; ++i) {
            if (this.fParsedEntity[i] != null) {
                this.fParsedEntity[i].release();
                this.fParsedEntity[i] = null;
            }
        }
        this.fStandalone = false;
        this.fEntityCount = 0;
        this.fFreeEntities = 0;
        this.fEntityDepth = 0;
        this.fInExternalEntity = false;
        this.fActiveReferences = 0;
        this.fActivePEReferences = 0;
        this.fEntityStackDepth = 0;
    }
    
    public void setScanExternalGeneralEntities(final boolean fScanExternalGeneralEntities) {
        this.fScanExternalGeneralEntities = fScanExternalGeneralEntities;
    }
    
    public boolean getScanExternalGeneralEntities() {
        return this.fScanExternalGeneralEntities;
    }
    
    public void setScanExternalParameterEntities(final boolean fScanExternalParameterEntities) {
        this.fScanExternalParameterEntities = fScanExternalParameterEntities;
    }
    
    public boolean getScanExternalParameterEntities() {
        return this.fScanExternalParameterEntities;
    }
    
    public void setStandalone() {
        this.fStandalone = true;
    }
    
    public boolean isStandalone() {
        return this.fStandalone;
    }
    
    public int currentBaseURI() {
        if (this.fEntityDepth > 0) {
            final int n = this.fEntityStack[this.fEntityDepth - 1];
            int n2 = this.fBaseURI[n];
            if (n2 == -1) {
                final String s = this.fBaseURIString[n];
                final int[] fBaseURI = this.fBaseURI;
                final int n3 = n;
                final int addSymbol = this.fSymbolTable.addSymbol(s);
                fBaseURI[n3] = addSymbol;
                n2 = addSymbol;
            }
            return n2;
        }
        return -1;
    }
    
    public String getSystemID() {
        return this.fSymbolTable.toString(this.currentBaseURI());
    }
    
    public boolean inExternalEntity() {
        return this.fInExternalEntity;
    }
    
    public boolean scanDocumentEntity(final EntityInputSource entityInputSource) {
        final int allocateDocumentEntity = this.allocateDocumentEntity(entityInputSource);
        if (allocateDocumentEntity == -1) {
            return false;
        }
        final boolean scanDocument = this.fHandler.scanDocument(this.getParsedEntity(allocateDocumentEntity));
        this.releaseEntity(allocateDocumentEntity);
        return scanDocument;
    }
    
    public boolean entityReferenceInContent(final int n) {
        if (this.isRecursiveReference(n)) {
            return this.fHandler.recursiveReferenceInContent(n, this.entityReferencePath(n));
        }
        final int lookupEntity = this.fEntityDeclPool.lookupEntity(n);
        if (lookupEntity == -1) {
            return this.fHandler.undeclaredEntityInContent(this.fSymbolTable.toString(n));
        }
        final int[] declArray = this.fEntityDeclPool.getDeclArray(lookupEntity);
        final int declBase = this.fEntityDeclPool.getDeclBase(lookupEntity);
        final int n2 = declArray[declBase + 0];
        final int n3 = n2 & 0xFFFF;
        if (this.fStandalone && (n2 & 0x10000) != 0x0 && !this.fHandler.externallyDeclaredEntityInContent(this.fSymbolTable.toString(n))) {
            return false;
        }
        if (n3 == 0) {
            this.fHandler.character(n, declArray[declBase + 2], true);
            return true;
        }
        if (n3 == 1) {
            final int allocateInternalEntity = this.allocateInternalEntity(declArray, declBase);
            if (allocateInternalEntity == -1) {
                return false;
            }
            final ParsedEntity parsedEntity = this.getParsedEntity(allocateInternalEntity);
            this.pushEntityReference(n);
            if (!this.fHandler.scanContent(n, parsedEntity)) {
                return false;
            }
            this.popEntityReference();
            this.releaseEntity(allocateInternalEntity);
            return true;
        }
        else {
            if (n3 == 5) {
                return this.fHandler.unparsedEntityInContent(n);
            }
            if (!this.fScanExternalGeneralEntities) {
                return this.fHandler.skippedEntityInContent(n);
            }
            final int n4 = declArray[declBase + 3];
            final int n5 = declArray[declBase + 4];
            final int n6 = declArray[declBase + 2];
            final boolean fInExternalEntity = this.fInExternalEntity;
            final int allocateExternalEntity = this.allocateExternalEntity(new EntityInputSource(this.fSymbolTable.toString(n4), this.fSymbolTable.toString(n5)), this.fSymbolTable.toString(n6));
            if (allocateExternalEntity == -1) {
                return false;
            }
            final ParsedEntity parsedEntity2 = this.getParsedEntity(allocateExternalEntity);
            this.fInExternalEntity = true;
            this.pushEntityReference(n);
            if (!this.fHandler.scanContent(n, parsedEntity2)) {
                return false;
            }
            this.popEntityReference();
            this.fInExternalEntity = fInExternalEntity;
            this.releaseEntity(allocateExternalEntity);
            return true;
        }
    }
    
    public boolean entityReferenceInAttValue(final int n) {
        if (this.isRecursiveReference(n)) {
            return this.fHandler.recursiveReferenceInAttValue(n, this.entityReferencePath(n));
        }
        final int lookupEntity = this.fEntityDeclPool.lookupEntity(n);
        if (lookupEntity == -1) {
            return this.fHandler.undeclaredEntityInAttValue(this.fSymbolTable.toString(n));
        }
        final int[] declArray = this.fEntityDeclPool.getDeclArray(lookupEntity);
        final int declBase = this.fEntityDeclPool.getDeclBase(lookupEntity);
        final int n2 = declArray[declBase + 0];
        final int n3 = n2 & 0xFFFF;
        if (this.fStandalone && (n2 & 0x10000) != 0x0 && !this.fHandler.externallyDeclaredEntityInAttValue(this.fSymbolTable.toString(n))) {
            return false;
        }
        if (n3 == 0) {
            this.fHandler.attributeValueCharacter(n, declArray[declBase + 2], true);
            return true;
        }
        if (n3 != 1) {
            return this.fHandler.externalEntityInAttValue(n);
        }
        final int allocateInternalEntity = this.allocateInternalEntity(declArray, declBase);
        if (allocateInternalEntity == -1) {
            return false;
        }
        final ParsedEntity parsedEntity = this.getParsedEntity(allocateInternalEntity);
        this.pushEntityReference(n);
        this.fHandler.scanAttValue(n, parsedEntity);
        this.popEntityReference();
        this.releaseEntity(allocateInternalEntity);
        return true;
    }
    
    public boolean entityReferenceInDefaultAttValue(final int n) {
        if (this.isRecursiveReference(n)) {
            return this.fHandler.recursiveReferenceInDefaultAttValue(n, this.entityReferencePath(n));
        }
        final int lookupEntity = this.fEntityDeclPool.lookupEntity(n);
        if (lookupEntity == -1) {
            return this.fHandler.undeclaredEntityInDefaultAttValue(n);
        }
        final int[] declArray = this.fEntityDeclPool.getDeclArray(lookupEntity);
        final int declBase = this.fEntityDeclPool.getDeclBase(lookupEntity);
        final int n2 = declArray[declBase + 0];
        final int n3 = n2 & 0xFFFF;
        if (this.fStandalone && (n2 & 0x10000) != 0x0 && !this.fHandler.externallyDeclaredEntityInDefaultAttValue(this.fSymbolTable.toString(n))) {
            return false;
        }
        if (n3 == 0) {
            this.fDTDHandler.defaultAttValueCharacter(n, declArray[declBase + 2], true);
            return true;
        }
        if (n3 != 1) {
            return this.fHandler.externalEntityInDefaultAttValue(n);
        }
        final int allocateInternalEntity = this.allocateInternalEntity(declArray, declBase);
        if (allocateInternalEntity == -1) {
            return false;
        }
        final ParsedEntity parsedEntity = this.getParsedEntity(allocateInternalEntity);
        this.pushEntityReference(n);
        this.fDTDHandler.scanDefaultAttValue(n, parsedEntity);
        this.popEntityReference();
        this.releaseEntity(allocateInternalEntity);
        return true;
    }
    
    public boolean peReference(final int n) {
        if (this.isRecursivePEReference(n)) {
            return this.fHandler.recursivePEReference(n, this.peReferencePath(n));
        }
        final int lookupPE = this.fEntityDeclPool.lookupPE(n);
        if (lookupPE == -1) {
            return this.fHandler.undeclaredParameterEntity(n);
        }
        final int[] declArray = this.fEntityDeclPool.getDeclArray(lookupPE);
        final int declBase = this.fEntityDeclPool.getDeclBase(lookupPE);
        if ((declArray[declBase + 0] & 0xFFFF) == 0x3) {
            final int allocateInternalEntity = this.allocateInternalEntity(declArray, declBase);
            if (allocateInternalEntity == -1) {
                return false;
            }
            final ParsedEntity parsedEntity = this.getParsedEntity(allocateInternalEntity);
            this.pushPEReference(n);
            if (this.fInExternalEntity) {
                this.fDTDHandler.scanExtSubsetDecl(n, parsedEntity);
            }
            else {
                this.fDTDHandler.scanIntSubsetDecl(n, parsedEntity);
            }
            this.popPEReference();
            this.releaseEntity(allocateInternalEntity);
            return true;
        }
        else {
            if (!this.fScanExternalParameterEntities) {
                this.fHandler.skippedParameterEntity(n);
                return true;
            }
            final int n2 = declArray[declBase + 3];
            final int n3 = declArray[declBase + 4];
            final int n4 = declArray[declBase + 2];
            final boolean fInExternalEntity = this.fInExternalEntity;
            final int allocateExternalEntity = this.allocateExternalEntity(new EntityInputSource(this.fSymbolTable.toString(n2), this.fSymbolTable.toString(n3)), this.fSymbolTable.toString(n4));
            if (allocateExternalEntity == -1) {
                return false;
            }
            final ParsedEntity parsedEntity2 = this.getParsedEntity(allocateExternalEntity);
            this.fInExternalEntity = true;
            this.pushPEReference(n);
            this.fDTDHandler.scanExtSubsetDecl(n, parsedEntity2);
            this.popPEReference();
            this.fInExternalEntity = fInExternalEntity;
            this.releaseEntity(allocateExternalEntity);
            return true;
        }
    }
    
    public boolean scanExternalSubset(final String s, final String s2) {
        if (!this.fScanExternalParameterEntities) {
            this.fHandler.skippedExternalSubsetEntity();
            return true;
        }
        final EntityInputSource entityInputSource = new EntityInputSource(s, s2);
        String s3;
        if (this.fEntityDepth > 0) {
            s3 = this.fBaseURIString[this.fEntityStack[this.fEntityDepth - 1]];
        }
        else {
            s3 = null;
        }
        final int allocateExternalEntity = this.allocateExternalEntity(entityInputSource, s3);
        if (allocateExternalEntity == -1) {
            return false;
        }
        final ParsedEntity parsedEntity = this.getParsedEntity(allocateExternalEntity);
        this.fInExternalEntity = true;
        if (!this.fDTDHandler.scanExternalSubset(parsedEntity)) {
            return false;
        }
        this.fInExternalEntity = false;
        this.releaseEntity(allocateExternalEntity);
        return true;
    }
    
    public boolean peReferenceInEntityValue(final int n) {
        if (this.isRecursivePEReference(n)) {
            return this.fHandler.recursivePEReference(n, this.peReferencePath(n));
        }
        final int lookupPE = this.fEntityDeclPool.lookupPE(n);
        if (lookupPE == -1) {
            return this.fHandler.undeclaredParameterEntity(n);
        }
        final int[] declArray = this.fEntityDeclPool.getDeclArray(lookupPE);
        final int declBase = this.fEntityDeclPool.getDeclBase(lookupPE);
        if ((declArray[declBase + 0] & 0xFFFF) == 0x3) {
            final int allocateInternalEntity = this.allocateInternalEntity(declArray, declBase);
            if (allocateInternalEntity == -1) {
                return false;
            }
            final ParsedEntity parsedEntity = this.getParsedEntity(allocateInternalEntity);
            this.pushPEReference(n);
            this.fDTDHandler.scanEntityValue(n, parsedEntity);
            this.popPEReference();
            this.releaseEntity(allocateInternalEntity);
            return true;
        }
        else {
            if (!this.fScanExternalParameterEntities) {
                this.fHandler.skippedParameterEntity(n);
                return true;
            }
            final int allocateExternalEntity = this.allocateExternalEntity(new EntityInputSource(this.fSymbolTable.toString(declArray[declBase + 3]), this.fSymbolTable.toString(declArray[declBase + 4])), this.fSymbolTable.toString(declArray[declBase + 2]));
            if (allocateExternalEntity == -1) {
                return false;
            }
            final ParsedEntity parsedEntity2 = this.getParsedEntity(allocateExternalEntity);
            this.pushPEReference(n);
            this.fDTDHandler.scanEntityValue(n, parsedEntity2);
            this.popPEReference();
            this.releaseEntity(allocateExternalEntity);
            return true;
        }
    }
    
    public boolean startPEReferenceWithinMarkup(final int n) {
        if (this.isRecursivePEReference(n)) {
            return this.fHandler.recursivePEReference(n, this.peReferencePath(n));
        }
        final int lookupPE = this.fEntityDeclPool.lookupPE(n);
        if (lookupPE == -1) {
            return this.fHandler.undeclaredParameterEntity(n);
        }
        final int[] declArray = this.fEntityDeclPool.getDeclArray(lookupPE);
        final int declBase = this.fEntityDeclPool.getDeclBase(lookupPE);
        int n2;
        boolean fInExternalEntity;
        if ((declArray[declBase + 0] & 0xFFFF) == 0x3) {
            n2 = this.allocateInternalEntity(declArray, declBase);
            if (n2 == -1) {
                return false;
            }
            fInExternalEntity = this.fInExternalEntity;
        }
        else {
            if (!this.fScanExternalParameterEntities) {
                this.fHandler.skippedParameterEntity(n);
                return true;
            }
            n2 = this.allocateExternalEntity(new EntityInputSource(this.fSymbolTable.toString(declArray[declBase + 3]), this.fSymbolTable.toString(declArray[declBase + 4])), this.fSymbolTable.toString(declArray[declBase + 2]));
            if (n2 == -1) {
                return false;
            }
            fInExternalEntity = true;
        }
        final ParsedEntity parsedEntity = this.getParsedEntity(n2);
        if (this.fEntityStackDepth == this.fEntityHandleStack.length) {
            final int[] fEntityHandleStack = new int[this.fEntityStackDepth << 1];
            final boolean[] fEntityExternalStack = new boolean[this.fEntityStackDepth << 1];
            System.arraycopy(this.fEntityHandleStack, 0, fEntityHandleStack, 0, this.fEntityStackDepth);
            System.arraycopy(this.fEntityExternalStack, 0, fEntityExternalStack, 0, this.fEntityStackDepth);
            this.fEntityHandleStack = fEntityHandleStack;
            this.fEntityExternalStack = fEntityExternalStack;
        }
        this.fEntityHandleStack[this.fEntityStackDepth] = n2;
        this.fEntityExternalStack[this.fEntityStackDepth] = this.fInExternalEntity;
        ++this.fEntityStackDepth;
        this.pushPEReference(n);
        this.fInExternalEntity = fInExternalEntity;
        return this.fDTDHandler.scanPEWithinMarkup(n, parsedEntity);
    }
    
    public void endPEReferenceWithinMarkup() {
        this.popPEReference();
        --this.fEntityStackDepth;
        this.releaseEntity(this.fEntityHandleStack[this.fEntityStackDepth]);
        this.fInExternalEntity = this.fEntityExternalStack[this.fEntityStackDepth];
    }
    
    public String currentPEReferenceName() {
        return this.fSymbolTable.toString(this.fActivePEReferencesStack[this.fActivePEReferences - 1]);
    }
    
    private ParsedEntity getParsedEntity(final int n) {
        return this.fParsedEntity[n];
    }
    
    private int allocateDocumentEntity(final EntityInputSource entityInputSource) {
        final String resolveDocumentEntity = this.resolveDocumentEntity(entityInputSource);
        final ParsedEntity parsedEntity = this.fEntityFactory.createParsedEntity(entityInputSource, true);
        if (parsedEntity == null) {
            return -1;
        }
        final int allocateHandle = this.allocateHandle();
        this.fParsedEntity[allocateHandle] = parsedEntity;
        this.fBaseURIString[allocateHandle] = resolveDocumentEntity;
        if (!this.fHandler.scanXMLDecl(parsedEntity)) {
            this.deallocateHandle(allocateHandle);
            return -1;
        }
        return this.fEntityStack[this.fEntityDepth++] = allocateHandle;
    }
    
    private int allocateExternalEntity(final EntityInputSource entityInputSource, final String s) {
        final String resolveExternalEntity = this.resolveExternalEntity(entityInputSource, s);
        final ParsedEntity parsedEntity = this.fEntityFactory.createParsedEntity(entityInputSource, false);
        if (parsedEntity == null) {
            return -1;
        }
        final int allocateHandle = this.allocateHandle();
        this.fParsedEntity[allocateHandle] = parsedEntity;
        this.fBaseURIString[allocateHandle] = resolveExternalEntity;
        if (!this.fHandler.scanTextDecl(parsedEntity)) {
            this.deallocateHandle(allocateHandle);
            return -1;
        }
        return this.fEntityStack[this.fEntityDepth++] = allocateHandle;
    }
    
    private int allocateInternalEntity(final int[] array, final int n) {
        final int n2 = array[n + 2];
        final int currentBaseURI = this.currentBaseURI();
        final ParsedEntity parsedEntity = this.fEntityFactory.createParsedEntity(this.fEntityDeclPool.getInternalEntityContent(n2));
        if (parsedEntity == null) {
            return -1;
        }
        final int allocateHandle = this.allocateHandle();
        this.fParsedEntity[allocateHandle] = parsedEntity;
        this.fBaseURIString[allocateHandle] = this.fSymbolTable.toString(currentBaseURI);
        this.fBaseURI[allocateHandle] = currentBaseURI;
        return this.fEntityStack[this.fEntityDepth++] = allocateHandle;
    }
    
    private void releaseEntity(final int n) {
        this.deallocateHandle(n);
        --this.fEntityDepth;
    }
    
    private synchronized int allocateHandle() {
        int n = -1;
        if (this.fFreeEntities > 0) {
            for (int i = 0; i < this.fEntityCount; ++i) {
                if (this.fBaseURI[i] == 0) {
                    n = i;
                    break;
                }
            }
            --this.fFreeEntities;
        }
        else {
            n = this.fEntityCount++;
            if (n == this.fParsedEntity.length) {
                final ParsedEntity[] fParsedEntity = new ParsedEntity[n << 1];
                System.arraycopy(this.fParsedEntity, 0, fParsedEntity, 0, n);
                this.fParsedEntity = fParsedEntity;
                final int[] fBaseURI = new int[n << 1];
                System.arraycopy(this.fBaseURI, 0, fBaseURI, 0, n);
                this.fBaseURI = fBaseURI;
                final String[] fBaseURIString = new String[n << 1];
                System.arraycopy(this.fBaseURIString, 0, fBaseURIString, 0, n);
                this.fBaseURIString = fBaseURIString;
            }
        }
        this.fBaseURI[n] = -1;
        return n;
    }
    
    private void deallocateHandle(final int n) {
        this.fParsedEntity[n].release();
        this.fParsedEntity[n] = null;
        this.fBaseURI[n] = 0;
        this.fBaseURIString[n] = null;
        ++this.fFreeEntities;
    }
    
    private String resolveDocumentEntity(final EntityInputSource entityInputSource) {
        final String systemId = entityInputSource.getSystemId();
        if (systemId == null) {
            return null;
        }
        final String expandSystemID = this.expandSystemID(systemId, null);
        if (expandSystemID != systemId) {
            entityInputSource.setSystemId(expandSystemID);
        }
        return expandSystemID;
    }
    
    private String resolveExternalEntity(final EntityInputSource entityInputSource, final String s) {
        final String systemId = entityInputSource.getSystemId();
        if (systemId == null) {
            return null;
        }
        final String expandSystemID = this.expandSystemID(systemId, s);
        if (expandSystemID != systemId) {
            entityInputSource.setSystemId(expandSystemID);
        }
        if (!this.fHandler.resolveExternalEntity(entityInputSource)) {
            return null;
        }
        return expandSystemID;
    }
    
    private String expandSystemID(final String s, final String s2) {
        if (s == null || s.length() == 0) {
            return s;
        }
        if (URI.parse(s) != null) {
            return s;
        }
        final String fixURI = fixURI(s);
        URI uri;
        if (s2 == null) {
            String s3;
            try {
                s3 = fixURI(System.getProperty("user.dir"));
            }
            catch (SecurityException ex) {
                s3 = "";
            }
            if (!s3.endsWith("/")) {
                s3 += "/";
            }
            uri = URI.parse("file", "", s3);
        }
        else {
            uri = URI.parse(s2);
        }
        final URI parse = URI.parse(uri, fixURI);
        if (parse == null) {
            return s;
        }
        return parse.toString();
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
    
    private void pushEntityReference(final int n) {
        if (this.fActiveReferences == this.fActiveReferencesStack.length) {
            final int[] fActiveReferencesStack = new int[this.fActiveReferences << 1];
            System.arraycopy(this.fActiveReferencesStack, 0, fActiveReferencesStack, 0, this.fActiveReferences);
            this.fActiveReferencesStack = fActiveReferencesStack;
        }
        this.fActiveReferencesStack[this.fActiveReferences++] = n;
    }
    
    private void popEntityReference() {
        --this.fActiveReferences;
    }
    
    private boolean isRecursiveReference(final int n) {
        for (int i = 0; i < this.fActiveReferences; ++i) {
            if (this.fActiveReferencesStack[i] == n) {
                return true;
            }
        }
        return false;
    }
    
    private String entityReferencePath(final int n) {
        final StringBuffer sb = new StringBuffer();
        sb.append('|');
        for (int i = 0; i < this.fActiveReferences; ++i) {
            if (this.fActiveReferencesStack[i] >= 0) {
                sb.append('-');
                sb.append('&');
                sb.append(this.fSymbolTable.toString(this.fActiveReferencesStack[i]));
                sb.append(';');
            }
        }
        sb.append('-');
        sb.append('&');
        sb.append(this.fSymbolTable.toString(n));
        sb.append(';');
        return sb.toString();
    }
    
    private void pushPEReference(final int n) {
        if (this.fActivePEReferences == this.fActivePEReferencesStack.length) {
            final int[] fActivePEReferencesStack = new int[this.fActivePEReferences << 1];
            System.arraycopy(this.fActivePEReferencesStack, 0, fActivePEReferencesStack, 0, this.fActivePEReferences);
            this.fActivePEReferencesStack = fActivePEReferencesStack;
        }
        this.fActivePEReferencesStack[this.fActivePEReferences++] = n;
    }
    
    private void popPEReference() {
        --this.fActivePEReferences;
    }
    
    private boolean isRecursivePEReference(final int n) {
        for (int i = 0; i < this.fActivePEReferences; ++i) {
            if (this.fActivePEReferencesStack[i] == n) {
                return true;
            }
        }
        return false;
    }
    
    private String peReferencePath(final int n) {
        final StringBuffer sb = new StringBuffer();
        sb.append('|');
        for (int i = 0; i < this.fActivePEReferences; ++i) {
            if (this.fActivePEReferencesStack[i] >= 0) {
                sb.append('-');
                sb.append('%');
                sb.append(this.fSymbolTable.toString(this.fActivePEReferencesStack[i]));
                sb.append(';');
            }
        }
        sb.append('-');
        sb.append('%');
        sb.append(this.fSymbolTable.toString(n));
        sb.append(';');
        return sb.toString();
    }
}
