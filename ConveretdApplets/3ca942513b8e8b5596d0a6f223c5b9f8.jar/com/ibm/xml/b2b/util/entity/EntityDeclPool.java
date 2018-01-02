// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.entity;

import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.IntegerHashtable;
import com.ibm.xml.b2b.util.DataStore;
import com.ibm.xml.b2b.util.SymbolTable;

public final class EntityDeclPool
{
    private static final boolean DEBUG_ADDITIONS = false;
    public static final int EntityType_Mask = 65535;
    public static final int EntityType_Predefined = 0;
    public static final int EntityType_InternalGeneral = 1;
    public static final int EntityType_ExternalGeneral = 2;
    public static final int EntityType_InternalParameter = 3;
    public static final int EntityType_ExternalParameter = 4;
    public static final int EntityType_Unparsed = 5;
    public static final int EntityType_DeclaredExternally = 65536;
    public static final int ENTITY_ENTITYTYPE_OFFSET = 0;
    public static final int ENTITY_NAME_OFFSET = 1;
    public static final int PREDEFINEDENTITY_CHARVALUE_OFFSET = 2;
    public static final int PREDEFINEDENTITY_RECORD_SIZE = 3;
    public static final int INTERNALENTITY_CONTENT_OFFSET = 2;
    public static final int INTERNALENTITY_RECORD_SIZE = 3;
    public static final int EXTERNALENTITY_BASEURI_OFFSET = 2;
    public static final int EXTERNALENTITY_PUBLICID_OFFSET = 3;
    public static final int EXTERNALENTITY_SYSTEMID_OFFSET = 4;
    public static final int EXTERNALENTITY_RECORD_SIZE = 5;
    public static final int UNPARSEDENTITY_RECORD_SIZE = 2;
    private SymbolTable fSymbolTable;
    private DataStore fDataStore;
    private IntegerHashtable fEntityDecls;
    private IntegerHashtable fPEDecls;
    private int fInternalEntityCount;
    private XMLString[] fInternalEntityContent;
    
    public EntityDeclPool(final SymbolTable fSymbolTable) {
        this.fSymbolTable = fSymbolTable;
        this.fDataStore = new DataStore();
        this.fEntityDecls = new IntegerHashtable();
        this.fInternalEntityContent = new XMLString[16];
        this.createPredefinedEntities();
    }
    
    public void reset(final boolean b) {
        this.fDataStore.reset(b);
        this.fEntityDecls.clear(b);
        if (this.fPEDecls != null) {
            this.fPEDecls.clear(b);
        }
        if (this.fInternalEntityCount != 0) {
            if (!b) {
                for (int i = 0; i < this.fInternalEntityCount; ++i) {
                    this.fInternalEntityContent[i] = null;
                }
            }
            this.fInternalEntityCount = 0;
        }
        this.createPredefinedEntities();
    }
    
    public int lookupEntity(final int n) {
        return this.fEntityDecls.get(n);
    }
    
    public int lookupPE(final int n) {
        return (this.fPEDecls == null) ? -1 : this.fPEDecls.get(n);
    }
    
    public boolean isUnparsedEntity(final int n) {
        return (this.fDataStore.getRecordArray(n)[this.fDataStore.getRecordBase(n) + 0] & 0xFFFF) == 0x5;
    }
    
    public int[] getDeclArray(final int n) {
        return this.fDataStore.getRecordArray(n);
    }
    
    public int getDeclBase(final int n) {
        return this.fDataStore.getRecordBase(n);
    }
    
    public XMLString getInternalEntityContent(final int n) {
        return this.fInternalEntityContent[n];
    }
    
    public boolean addInternalEntityDecl(final XMLName xmlName, final XMLString xmlString, final EntityManager entityManager) {
        final boolean inExternalEntity = entityManager.inExternalEntity();
        final int addSymbol = this.fSymbolTable.addSymbol(xmlName);
        if (this.fEntityDecls.get(addSymbol) != -1) {
            return false;
        }
        this.fEntityDecls.put(addSymbol, this.addInternalEntity(1, addSymbol, xmlString, inExternalEntity));
        return true;
    }
    
    public boolean addExternalEntityDecl(final XMLName xmlName, final XMLString xmlString, final XMLString xmlString2, final EntityManager entityManager) {
        final int currentBaseURI = entityManager.currentBaseURI();
        final boolean inExternalEntity = entityManager.inExternalEntity();
        final int addSymbol = this.fSymbolTable.addSymbol(xmlName);
        if (this.fEntityDecls.get(addSymbol) != -1) {
            return false;
        }
        this.fEntityDecls.put(addSymbol, this.addExternalEntity(2, addSymbol, (xmlString == null) ? -1 : this.fSymbolTable.addSymbol(xmlString), this.fSymbolTable.addSymbol(xmlString2), currentBaseURI, inExternalEntity));
        return true;
    }
    
    public boolean addUnparsedEntityDecl(final XMLName xmlName) {
        final int addSymbol = this.fSymbolTable.addSymbol(xmlName);
        if (this.fEntityDecls.get(addSymbol) != -1) {
            return false;
        }
        final int allocateRecord = this.fDataStore.allocateRecord(2);
        final int[] recordArray = this.fDataStore.getRecordArray(allocateRecord);
        final int recordBase = this.fDataStore.getRecordBase(allocateRecord);
        recordArray[recordBase + 0] = 5;
        recordArray[recordBase + 1] = addSymbol;
        this.fEntityDecls.put(addSymbol, allocateRecord);
        return true;
    }
    
    public boolean addInternalPEDecl(final XMLName xmlName, final XMLString xmlString, final EntityManager entityManager) {
        final boolean inExternalEntity = entityManager.inExternalEntity();
        final int addSymbol = this.fSymbolTable.addSymbol(xmlName);
        if (this.fPEDecls != null && this.fPEDecls.get(addSymbol) != -1) {
            return false;
        }
        final int addInternalEntity = this.addInternalEntity(3, addSymbol, xmlString, inExternalEntity);
        if (this.fPEDecls == null) {
            this.fPEDecls = new IntegerHashtable();
        }
        this.fPEDecls.put(addSymbol, addInternalEntity);
        return true;
    }
    
    public boolean addExternalPEDecl(final XMLName xmlName, final XMLString xmlString, final XMLString xmlString2, final EntityManager entityManager) {
        final int currentBaseURI = entityManager.currentBaseURI();
        final boolean inExternalEntity = entityManager.inExternalEntity();
        final int addSymbol = this.fSymbolTable.addSymbol(xmlName);
        if (this.fPEDecls != null && this.fPEDecls.get(addSymbol) != -1) {
            return false;
        }
        final int addExternalEntity = this.addExternalEntity(4, addSymbol, (xmlString == null) ? -1 : this.fSymbolTable.addSymbol(xmlString), this.fSymbolTable.addSymbol(xmlString2), currentBaseURI, inExternalEntity);
        if (this.fPEDecls == null) {
            this.fPEDecls = new IntegerHashtable();
        }
        this.fPEDecls.put(addSymbol, addExternalEntity);
        return true;
    }
    
    private void createPredefinedEntities() {
        this.addPredefinedEntity("lt", '<');
        this.addPredefinedEntity("gt", '>');
        this.addPredefinedEntity("amp", '&');
        this.addPredefinedEntity("apos", '\'');
        this.addPredefinedEntity("quot", '\"');
    }
    
    private void addPredefinedEntity(final String s, final char c) {
        final int addSymbol = this.fSymbolTable.addSymbol(s);
        final int allocateRecord = this.fDataStore.allocateRecord(3);
        final int[] recordArray = this.fDataStore.getRecordArray(allocateRecord);
        final int recordBase = this.fDataStore.getRecordBase(allocateRecord);
        recordArray[recordBase + 0] = 0;
        recordArray[recordBase + 1] = addSymbol;
        recordArray[recordBase + 2] = c;
        this.fEntityDecls.put(addSymbol, allocateRecord);
    }
    
    private int addInternalEntity(final int n, final int n2, final XMLString values, final boolean b) {
        final int allocateRecord = this.fDataStore.allocateRecord(3);
        final int[] recordArray = this.fDataStore.getRecordArray(allocateRecord);
        final int recordBase = this.fDataStore.getRecordBase(allocateRecord);
        final int n3 = this.fInternalEntityCount++;
        if (n3 == this.fInternalEntityContent.length) {
            final XMLString[] fInternalEntityContent = new XMLString[n3 << 1];
            System.arraycopy(this.fInternalEntityContent, 0, fInternalEntityContent, 0, n3);
            this.fInternalEntityContent = fInternalEntityContent;
        }
        if (this.fInternalEntityContent[n3] == null) {
            this.fInternalEntityContent[n3] = new XMLString(values);
        }
        else {
            this.fInternalEntityContent[n3].setValues(values);
        }
        int n4;
        if (b) {
            n4 = (n | 0x10000);
        }
        else {
            n4 = n;
        }
        recordArray[recordBase + 0] = n4;
        recordArray[recordBase + 1] = n2;
        recordArray[recordBase + 2] = n3;
        return allocateRecord;
    }
    
    private int addExternalEntity(final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        final int allocateRecord = this.fDataStore.allocateRecord(5);
        final int[] recordArray = this.fDataStore.getRecordArray(allocateRecord);
        final int recordBase = this.fDataStore.getRecordBase(allocateRecord);
        int n6;
        if (b) {
            n6 = (n | 0x10000);
        }
        else {
            n6 = n;
        }
        recordArray[recordBase + 0] = n6;
        recordArray[recordBase + 1] = n2;
        recordArray[recordBase + 3] = n3;
        recordArray[recordBase + 4] = n4;
        recordArray[recordBase + 2] = n5;
        return allocateRecord;
    }
}
