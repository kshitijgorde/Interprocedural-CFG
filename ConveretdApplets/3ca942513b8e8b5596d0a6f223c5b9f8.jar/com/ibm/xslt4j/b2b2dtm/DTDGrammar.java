// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.b2b2dtm;

import com.ibm.xml.b2b.scan.DocumentScannerSupport;
import com.ibm.xml.b2b.util.entity.ParsedEntityFactory;
import com.ibm.xml.b2b.util.IntegerHashtable;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.util.entity.EntityDeclPool;
import com.ibm.xml.b2b.util.DataStore;
import com.ibm.xml.b2b.util.XMLStringBuffer;
import com.ibm.xml.b2b.util.SymbolTable;

final class DTDGrammar
{
    public static final int ATTDEF_ATTRNAME_OFFSET = 0;
    public static final int ATTDEF_ATTTYPE_OFFSET = 1;
    public static final int ATTDEF_DEFAULTTYPE_OFFSET = 2;
    public static final int ATTDEF_ENUMTYPE_OFFSET = 3;
    public static final int ATTDEF_ATTVALUE_OFFSET = 4;
    public static final int ATTDEF_EXTERNAL_OFFSET = 5;
    public static final int ATTDEF_RECORD_SIZE = 6;
    protected SymbolTable fSymbolTable;
    protected XMLStringBuffer fStringBuffer;
    protected DataStore fDataStore;
    protected EntityDeclPool fEntityDeclPool;
    protected int fRootElementTypeHandle;
    protected int fPublicIDHandle;
    protected int fSystemIDHandle;
    protected int fEnumerationTypeList;
    protected QName[] fDefaultAttrNames;
    protected int fDefaultAttrNamesCount;
    protected XMLString[] fDefaultAttValues;
    protected int fDefaultAttValuesCount;
    protected int fCDATASymbol;
    protected int fREQUIREDSymbol;
    protected int fFIXEDSymbol;
    protected boolean fHaveAttDefs;
    protected IntegerHashtable fAttDefs;
    protected boolean fHaveElementContentElements;
    protected IntegerHashtable fElementContentElements;
    
    public DTDGrammar(final SymbolTable symbolTable, final ParsedEntityFactory entityFactory) {
        this.fSymbolTable = symbolTable;
        this.fEntityDeclPool = new EntityDeclPool(this.fSymbolTable);
        this.fStringBuffer = entityFactory.createStringBuffer();
        this.fDataStore = new DataStore();
        this.fDefaultAttrNames = new QName[16];
        this.fDefaultAttValues = new XMLString[16];
        this.fCDATASymbol = this.fSymbolTable.addSymbol("CDATA");
        this.fFIXEDSymbol = this.fSymbolTable.addSymbol("#FIXED");
        this.fREQUIREDSymbol = this.fSymbolTable.addSymbol("#REQUIRED");
    }
    
    public void reset(final boolean retainObjects) {
        this.fEntityDeclPool.reset(retainObjects);
        this.fStringBuffer.reset(retainObjects);
        this.fDataStore.reset(retainObjects);
        if (this.fHaveElementContentElements) {
            this.fElementContentElements.clear(retainObjects);
            this.fHaveElementContentElements = false;
            if (!retainObjects) {
                this.fElementContentElements = null;
            }
        }
        if (this.fHaveAttDefs) {
            this.fAttDefs.clear(retainObjects);
            this.fHaveAttDefs = false;
            if (!retainObjects) {
                for (int i = 0; i < this.fDefaultAttrNamesCount; ++i) {
                    this.fDefaultAttrNames[i] = null;
                }
                for (int i = 0; i < this.fDefaultAttValuesCount; ++i) {
                    this.fDefaultAttValues[i] = null;
                }
            }
            this.fDefaultAttrNamesCount = 0;
            this.fDefaultAttValuesCount = 0;
        }
        this.fCDATASymbol = this.fSymbolTable.addSymbol("CDATA");
        this.fFIXEDSymbol = this.fSymbolTable.addSymbol("#FIXED");
        this.fREQUIREDSymbol = this.fSymbolTable.addSymbol("#REQUIRED");
    }
    
    public EntityDeclPool getEntityDeclPool() {
        return this.fEntityDeclPool;
    }
    
    public XMLStringBuffer getStringBuffer() {
        return this.fStringBuffer;
    }
    
    public void setDescription(final QName rootElementType, final int publicID, final int systemID) {
        if (rootElementType != null) {
            this.fRootElementTypeHandle = this.fSymbolTable.addSymbol(rootElementType);
        }
        this.fPublicIDHandle = publicID;
        this.fSystemIDHandle = systemID;
    }
    
    public boolean hasElementContentElements() {
        return this.fHaveElementContentElements;
    }
    
    public boolean hasElementContent(final int elementTypeHandle) {
        return this.fElementContentElements.get(elementTypeHandle) != -1;
    }
    
    public void setElementContentElement(final int elementTypeHandle) {
        if (this.fElementContentElements == null) {
            this.fElementContentElements = new IntegerHashtable();
        }
        this.fElementContentElements.put(elementTypeHandle, 1);
        this.fHaveElementContentElements = true;
    }
    
    public boolean hasAttDefs() {
        return this.fHaveAttDefs;
    }
    
    public void addDefaultAttributes(final DocumentScannerSupport support, final int elementTypeHandle) {
        final int attDefs = this.getAttDefs(elementTypeHandle);
        if (attDefs == -1) {
            return;
        }
        final int count = this.attDefsCount(attDefs);
        final int[] defs = this.attDefsArray(attDefs);
        final int defbase = this.attDefsBase(attDefs);
        final String nsDeclPrefix = support.nsDeclPrefix;
        final int attrCount = support.attrCount;
        for (int i = 0; i < count; ++i) {
            final int attDef = defs[defbase + i];
            final int[] record = this.attDefArray(attDef);
            final int base = this.attDefBase(attDef);
            final int attrNameHandle = record[base + 0];
            final QName attrName = this.defaultAttrName(attrNameHandle);
            final boolean isNSDecl = attrName.localPart == nsDeclPrefix || attrName.prefix == nsDeclPrefix;
            final int attType = record[base + 1];
            final int attDefType = record[base + 2];
            final int attValueHandle = record[base + 4];
            final XMLString attValue = this.defaultAttValue(attValueHandle);
            boolean found = false;
            final boolean required = attDefType == this.fREQUIREDSymbol;
            if (required || attType != this.fCDATASymbol || attValueHandle != -1) {
                if (isNSDecl) {
                    final int firstMapping = support.firstMapping;
                    for (int lastMapping = support.lastMapping, j = firstMapping; j < lastMapping; ++j) {
                        final String nsDeclQName = support.nsDeclQNames[j];
                        if (attrName.str == nsDeclQName) {
                            if (attDefType == this.fFIXEDSymbol) {
                                final String specValue = support.namespaceURIs[j];
                                final XMLString defValue = attValue;
                                this.checkFixedAttValue(attrName, specValue, defValue.toString());
                            }
                            found = true;
                            break;
                        }
                    }
                }
                else {
                    for (int j = 0; j < attrCount; ++j) {
                        if (attrName.handle == support.attrNames[j].handle) {
                            if (attDefType == this.fFIXEDSymbol) {
                                final XMLString specValue2 = support.attValues[j];
                                final XMLString defValue2 = attValue;
                                this.checkFixedAttValue(attrName, specValue2.toString(), defValue2.toString());
                            }
                            if (attType != this.fCDATASymbol) {
                                support.attValueNormalized[j] = false;
                                support.attTypes[j] = this.fSymbolTable.toString(attType);
                            }
                            found = true;
                            break;
                        }
                    }
                }
                if (!found) {
                    if (required) {
                        this.requiredAttributeNotSpecified(attrName);
                    }
                    if (attValueHandle != -1) {
                        this.addDefaultAttribute(support, attrName, this.fSymbolTable.toString(attType), attValue, attDef);
                    }
                }
            }
        }
    }
    
    protected void checkFixedAttValue(final QName attrName, final String specValue, final String defValue) {
    }
    
    protected void requiredAttributeNotSpecified(final QName attrName) {
    }
    
    protected void addDefaultAttribute(final DocumentScannerSupport support, final QName attrName, final String attType, final XMLString attValue, final int attDef) {
        support.addDefaultAttribute(attrName, attType, attValue);
    }
    
    public int getAttDefs(final int elementTypeHandle) {
        return this.fHaveAttDefs ? this.fAttDefs.get(elementTypeHandle) : -1;
    }
    
    public int attDefsCount(final int attDefs) {
        return this.fDataStore.getListCount(attDefs);
    }
    
    public int[] attDefsArray(final int attDefs) {
        return this.fDataStore.getListArray(attDefs);
    }
    
    public int attDefsBase(final int attDefs) {
        return this.fDataStore.getListBase(attDefs);
    }
    
    public int[] attDefArray(final int attDef) {
        return this.fDataStore.getRecordArray(attDef);
    }
    
    public int attDefBase(final int attDef) {
        return this.fDataStore.getRecordBase(attDef);
    }
    
    public QName attDefAttrName(final int attDef) {
        final int[] record = this.attDefArray(attDef);
        final int base = this.attDefBase(attDef);
        final int attrNameHandle = record[base + 0];
        final QName attrName = this.defaultAttrName(attrNameHandle);
        return attrName;
    }
    
    public XMLString attDefAttValue(final int attDef) {
        final int[] record = this.attDefArray(attDef);
        final int base = this.attDefBase(attDef);
        final int attValueHandle = record[base + 4];
        final XMLString attValue = this.defaultAttValue(attValueHandle);
        return attValue;
    }
    
    public boolean attDefIsExternal(final int attDef) {
        final int[] record = this.fDataStore.getRecordArray(attDef);
        final int base = this.fDataStore.getRecordBase(attDef);
        return record[base + 5] != 0;
    }
    
    private QName defaultAttrName(final int handle) {
        return this.fDefaultAttrNames[handle];
    }
    
    private XMLString defaultAttValue(final int handle) {
        return (handle != -1) ? this.fDefaultAttValues[handle] : null;
    }
    
    public int lookupAttDef(final QName elementType, final QName attrName) {
        if (this.fHaveAttDefs) {
            final int elem = this.fSymbolTable.addSymbol(elementType);
            final int attDefs = this.fAttDefs.get(elem);
            if (attDefs != -1) {
                final int count = this.fDataStore.getListCount(attDefs);
                final int[] defs = this.fDataStore.getListArray(attDefs);
                final int base = this.fDataStore.getListBase(attDefs);
                final int attr = this.fSymbolTable.addSymbol(attrName);
                for (int i = 0; i < count; ++i) {
                    final int def = defs[base + i];
                    final int[] record = this.fDataStore.getRecordArray(def);
                    final int recordBase = this.fDataStore.getRecordBase(def);
                    final int defAttrName = record[recordBase + 0];
                    if (attr == this.fDefaultAttrNames[defAttrName].handle) {
                        return def;
                    }
                }
            }
        }
        return -1;
    }
    
    public void startAttDef(final QName attrName, final XMLString attType) {
        this.fEnumerationTypeList = -1;
    }
    
    public void enumerationType(final XMLString type) {
        this.fEnumerationTypeList = this.fDataStore.addToList(this.fEnumerationTypeList, this.fSymbolTable.addSymbol(type));
    }
    
    public void saveAttDef(final QName elementType, final QName attrName, final XMLString attType, final XMLString defaultType, final XMLString attValue, final boolean declaredExternally) {
        final int elem = this.fSymbolTable.addSymbol(elementType);
        int attr = this.fSymbolTable.addSymbol(attrName);
        int attDefs = this.fHaveAttDefs ? this.fAttDefs.get(elem) : -1;
        if (attrName.prefix == null) {
            if (attrName.sepOffset == -1) {
                attrName.prefix = XMLString.EMPTY_STRING;
                attrName.localPart = this.fSymbolTable.toString(attr);
            }
            else {
                this.fSymbolTable.addQNameSymbols(attrName);
            }
        }
        final int attDef = this.fDataStore.allocateRecord(6);
        final int[] defChunk = this.fDataStore.getRecordArray(attDef);
        final int base = this.fDataStore.getRecordBase(attDef);
        attr = this.fDefaultAttrNamesCount++;
        if (attr == this.fDefaultAttrNames.length) {
            final QName[] newDefaultAttrNames = new QName[attr << 1];
            System.arraycopy(this.fDefaultAttrNames, 0, newDefaultAttrNames, 0, attr);
            (this.fDefaultAttrNames = newDefaultAttrNames)[attr] = new QName(attrName);
        }
        else if (this.fDefaultAttrNames[attr] == null) {
            this.fDefaultAttrNames[attr] = new QName(attrName);
        }
        else {
            this.fDefaultAttrNames[attr].setValues(attrName);
        }
        defChunk[base + 0] = attr;
        int at;
        if (attType != null) {
            at = this.fSymbolTable.addSymbol(attType);
        }
        else {
            at = -1;
        }
        int dt;
        if (defaultType != null) {
            dt = this.fSymbolTable.addSymbol(defaultType);
        }
        else {
            dt = -1;
        }
        defChunk[base + 1] = at;
        defChunk[base + 2] = dt;
        defChunk[base + 3] = this.fEnumerationTypeList;
        if (attValue != null) {
            if (at != this.fCDATASymbol) {
                this.normalizeDefaultAttValue(attrName, attValue, at, attDef);
            }
            if (this.fDefaultAttValuesCount == this.fDefaultAttValues.length) {
                final XMLString[] newDefaultAttValues = new XMLString[this.fDefaultAttValuesCount << 1];
                System.arraycopy(this.fDefaultAttValues, 0, newDefaultAttValues, 0, this.fDefaultAttValuesCount);
                (this.fDefaultAttValues = newDefaultAttValues)[this.fDefaultAttValuesCount] = new XMLString(attValue);
            }
            else if (this.fDefaultAttValues[this.fDefaultAttValuesCount] == null) {
                this.fDefaultAttValues[this.fDefaultAttValuesCount] = new XMLString(attValue);
            }
            else {
                this.fDefaultAttValues[this.fDefaultAttValuesCount].setValues(attValue);
            }
            attr = this.fDefaultAttValuesCount++;
            defChunk[base + 4] = attr;
        }
        else {
            defChunk[base + 4] = -1;
        }
        defChunk[base + 5] = (declaredExternally ? 1 : 0);
        if (attDefs == -1) {
            attDefs = this.fDataStore.addToList(-1, attDef);
            if (this.fAttDefs == null) {
                this.fAttDefs = new IntegerHashtable();
            }
            this.fAttDefs.put(elem, attDefs);
            this.fHaveAttDefs = true;
        }
        else {
            this.fDataStore.addToList(attDefs, attDef);
        }
    }
    
    protected void normalizeDefaultAttValue(final QName attrName, final XMLString attValue, final int attType, final int attDef) {
        this.fStringBuffer.normalizeTextValue(attValue, null);
    }
}
