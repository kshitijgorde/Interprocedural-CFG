// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.dtm.ref.sax2dtm;

import org.apache.xml.dtm.DTMException;
import org.apache.xml.res.XMLMessages;
import org.apache.xml.dtm.DTMAxisIterator;
import org.apache.xml.dtm.ref.DTMDefaultBaseIterators;
import org.apache.xml.utils.SuballocatedIntVector;
import org.apache.xml.serializer.SerializationHandler;
import org.apache.xml.utils.FastStringBuffer;
import org.xml.sax.ContentHandler;
import org.apache.xml.utils.XMLStringDefault;
import org.xml.sax.SAXException;
import org.apache.xml.dtm.DTM;
import org.xml.sax.Attributes;
import org.apache.xml.utils.XMLStringFactory;
import org.apache.xml.dtm.DTMWSFilter;
import javax.xml.transform.Source;
import org.apache.xml.dtm.DTMManager;
import org.apache.xml.utils.XMLString;
import java.util.Vector;
import org.apache.xml.dtm.ref.ExtendedType;

public class SAX2DTM2 extends SAX2DTM
{
    private int[] m_exptype_map0;
    private int[] m_nextsib_map0;
    private int[] m_firstch_map0;
    private int[] m_parent_map0;
    private int[][] m_exptype_map;
    private int[][] m_nextsib_map;
    private int[][] m_firstch_map;
    private int[][] m_parent_map;
    protected ExtendedType[] m_extendedTypes;
    protected Vector m_values;
    private int m_valueIndex;
    private int m_maxNodeIndex;
    protected int m_SHIFT;
    protected int m_MASK;
    protected int m_blocksize;
    protected static final int TEXT_LENGTH_BITS = 10;
    protected static final int TEXT_OFFSET_BITS = 21;
    protected static final int TEXT_LENGTH_MAX = 1023;
    protected static final int TEXT_OFFSET_MAX = 2097151;
    protected boolean m_buildIdIndex;
    private static final String EMPTY_STR = "";
    private static final XMLString EMPTY_XML_STR;
    
    public SAX2DTM2(final DTMManager mgr, final Source source, final int dtmIdentity, final DTMWSFilter whiteSpaceFilter, final XMLStringFactory xstringfactory, final boolean doIndexing) {
        this(mgr, source, dtmIdentity, whiteSpaceFilter, xstringfactory, doIndexing, 512, true, true, false);
    }
    
    public SAX2DTM2(final DTMManager mgr, final Source source, final int dtmIdentity, final DTMWSFilter whiteSpaceFilter, final XMLStringFactory xstringfactory, final boolean doIndexing, int blocksize, final boolean usePrevsib, final boolean buildIdIndex, final boolean newNameTable) {
        super(mgr, source, dtmIdentity, whiteSpaceFilter, xstringfactory, doIndexing, blocksize, usePrevsib, newNameTable);
        this.m_valueIndex = 0;
        this.m_buildIdIndex = true;
        int shift = 0;
        while ((blocksize >>>= 1) != 0) {
            ++shift;
        }
        this.m_blocksize = 1 << shift;
        this.m_SHIFT = shift;
        this.m_MASK = this.m_blocksize - 1;
        this.m_buildIdIndex = buildIdIndex;
        this.m_values = new Vector(32, 512);
        this.m_maxNodeIndex = 65536;
        this.m_exptype_map0 = super.m_exptype.getMap0();
        this.m_nextsib_map0 = super.m_nextsib.getMap0();
        this.m_firstch_map0 = super.m_firstch.getMap0();
        this.m_parent_map0 = super.m_parent.getMap0();
    }
    
    public final int _exptype(final int identity) {
        return super.m_exptype.elementAt(identity);
    }
    
    public final int _exptype2(final int identity) {
        if (identity < this.m_blocksize) {
            return this.m_exptype_map0[identity];
        }
        return this.m_exptype_map[identity >>> this.m_SHIFT][identity & this.m_MASK];
    }
    
    public final int _nextsib2(final int identity) {
        if (identity < this.m_blocksize) {
            return this.m_nextsib_map0[identity];
        }
        return this.m_nextsib_map[identity >>> this.m_SHIFT][identity & this.m_MASK];
    }
    
    public final int _firstch2(final int identity) {
        if (identity < this.m_blocksize) {
            return this.m_firstch_map0[identity];
        }
        return this.m_firstch_map[identity >>> this.m_SHIFT][identity & this.m_MASK];
    }
    
    public final int _parent2(final int identity) {
        if (identity < this.m_blocksize) {
            return this.m_parent_map0[identity];
        }
        return this.m_parent_map[identity >>> this.m_SHIFT][identity & this.m_MASK];
    }
    
    public final int _type2(final int identity) {
        int eType;
        if (identity < this.m_blocksize) {
            eType = this.m_exptype_map0[identity];
        }
        else {
            eType = this.m_exptype_map[identity >>> this.m_SHIFT][identity & this.m_MASK];
        }
        if (-1 != eType) {
            return this.m_extendedTypes[eType].getNodeType();
        }
        return -1;
    }
    
    public final int getExpandedTypeID2(final int nodeHandle) {
        final int nodeID = this.makeNodeIdentity(nodeHandle);
        if (nodeID == -1) {
            return -1;
        }
        if (nodeID < this.m_blocksize) {
            return this.m_exptype_map0[nodeID];
        }
        return this.m_exptype_map[nodeID >>> this.m_SHIFT][nodeID & this.m_MASK];
    }
    
    public final int _exptype2Type(final int exptype) {
        if (-1 != exptype) {
            return this.m_extendedTypes[exptype].getNodeType();
        }
        return -1;
    }
    
    public int getIdForNamespace(final String uri) {
        final int index = this.m_values.indexOf(uri);
        if (index < 0) {
            this.m_values.addElement(uri);
            return this.m_valueIndex++;
        }
        return index;
    }
    
    public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) throws SAXException {
        this.charactersFlush();
        int exName = super.m_expandedNameTable.getExpandedTypeID(uri, localName, 1);
        int prefixIndex = (qName.length() != localName.length()) ? super.m_valuesOrPrefixes.stringToIndex(qName) : 0;
        final int elemNode = this.addNode(1, exName, super.m_parents.peek(), super.m_previous, prefixIndex, true);
        if (super.m_indexing) {
            this.indexNode(exName, elemNode);
        }
        super.m_parents.push(elemNode);
        final int startDecls = super.m_contextIndexes.peek();
        final int nDecls = super.m_prefixMappings.size();
        if (!super.m_pastFirstElement) {
            final String prefix = "xml";
            final String declURL = "http://www.w3.org/XML/1998/namespace";
            exName = super.m_expandedNameTable.getExpandedTypeID(null, prefix, 13);
            this.m_values.addElement(declURL);
            final int val = this.m_valueIndex++;
            this.addNode(13, exName, elemNode, -1, val, false);
            super.m_pastFirstElement = true;
        }
        for (int i = startDecls; i < nDecls; i += 2) {
            final String prefix = super.m_prefixMappings.elementAt(i);
            if (prefix != null) {
                final String declURL2 = super.m_prefixMappings.elementAt(i + 1);
                exName = super.m_expandedNameTable.getExpandedTypeID(null, prefix, 13);
                this.m_values.addElement(declURL2);
                final int val2 = this.m_valueIndex++;
                this.addNode(13, exName, elemNode, -1, val2, false);
            }
        }
        for (int n = attributes.getLength(), j = 0; j < n; ++j) {
            final String attrUri = attributes.getURI(j);
            final String attrQName = attributes.getQName(j);
            String valString = attributes.getValue(j);
            final String attrLocalName = attributes.getLocalName(j);
            int nodeType;
            if (null != attrQName && (attrQName.equals("xmlns") || attrQName.startsWith("xmlns:"))) {
                final String prefix = this.getPrefix(attrQName, attrUri);
                if (this.declAlreadyDeclared(prefix)) {
                    continue;
                }
                nodeType = 13;
            }
            else {
                nodeType = 2;
                if (this.m_buildIdIndex && attributes.getType(j).equalsIgnoreCase("ID")) {
                    this.setIDAttribute(valString, elemNode);
                }
            }
            if (null == valString) {
                valString = "";
            }
            this.m_values.addElement(valString);
            int val3 = this.m_valueIndex++;
            if (attrLocalName.length() != attrQName.length()) {
                prefixIndex = super.m_valuesOrPrefixes.stringToIndex(attrQName);
                final int dataIndex = super.m_data.size();
                super.m_data.addElement(prefixIndex);
                super.m_data.addElement(val3);
                val3 = -dataIndex;
            }
            exName = super.m_expandedNameTable.getExpandedTypeID(attrUri, attrLocalName, nodeType);
            this.addNode(nodeType, exName, elemNode, -1, val3, false);
        }
        if (null != super.m_wsfilter) {
            final short wsv = super.m_wsfilter.getShouldStripSpace(this.makeNodeHandle(elemNode), this);
            final boolean shouldStrip = (3 == wsv) ? this.getShouldStripWhitespace() : (2 == wsv);
            this.pushShouldStripWhitespace(shouldStrip);
        }
        super.m_previous = -1;
        super.m_contextIndexes.push(super.m_prefixMappings.size());
    }
    
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        this.charactersFlush();
        super.m_contextIndexes.quickPop(1);
        final int topContextIndex = super.m_contextIndexes.peek();
        if (topContextIndex != super.m_prefixMappings.size()) {
            super.m_prefixMappings.setSize(topContextIndex);
        }
        super.m_previous = super.m_parents.pop();
        this.popShouldStripWhitespace();
    }
    
    public void comment(final char[] ch, final int start, final int length) throws SAXException {
        if (super.m_insideDTD) {
            return;
        }
        this.charactersFlush();
        this.m_values.addElement(new String(ch, start, length));
        final int dataIndex = this.m_valueIndex++;
        super.m_previous = this.addNode(8, 8, super.m_parents.peek(), super.m_previous, dataIndex, false);
    }
    
    public void startDocument() throws SAXException {
        final int doc = this.addNode(9, 9, -1, -1, 0, true);
        super.m_parents.push(doc);
        super.m_previous = -1;
        super.m_contextIndexes.push(super.m_prefixMappings.size());
    }
    
    public void endDocument() throws SAXException {
        super.endDocument();
        super.m_exptype.addElement(-1);
        super.m_parent.addElement(-1);
        super.m_nextsib.addElement(-1);
        super.m_firstch.addElement(-1);
        this.m_extendedTypes = super.m_expandedNameTable.getExtendedTypes();
        this.m_exptype_map = super.m_exptype.getMap();
        this.m_nextsib_map = super.m_nextsib.getMap();
        this.m_firstch_map = super.m_firstch.getMap();
        this.m_parent_map = super.m_parent.getMap();
    }
    
    protected final int addNode(final int type, final int expandedTypeID, final int parentIndex, final int previousSibling, final int dataOrPrefix, final boolean canHaveFirstChild) {
        final int nodeIndex = super.m_size++;
        if (nodeIndex == this.m_maxNodeIndex) {
            this.addNewDTMID(nodeIndex);
            this.m_maxNodeIndex += 65536;
        }
        super.m_firstch.addElement(-1);
        super.m_nextsib.addElement(-1);
        super.m_parent.addElement(parentIndex);
        super.m_exptype.addElement(expandedTypeID);
        super.m_dataOrQName.addElement(dataOrPrefix);
        if (super.m_prevsib != null) {
            super.m_prevsib.addElement(previousSibling);
        }
        if (super.m_locator != null && super.m_useSourceLocationProperty) {
            this.setSourceLocation();
        }
        switch (type) {
            case 13: {
                this.declareNamespaceInContext(parentIndex, nodeIndex);
                break;
            }
            case 2: {
                break;
            }
            default: {
                if (-1 != previousSibling) {
                    super.m_nextsib.setElementAt(nodeIndex, previousSibling);
                    break;
                }
                if (-1 != parentIndex) {
                    super.m_firstch.setElementAt(nodeIndex, parentIndex);
                    break;
                }
                break;
            }
        }
        return nodeIndex;
    }
    
    protected final void charactersFlush() {
        if (super.m_textPendingStart >= 0) {
            final int length = super.m_chars.size() - super.m_textPendingStart;
            boolean doStrip = false;
            if (this.getShouldStripWhitespace()) {
                doStrip = super.m_chars.isWhitespace(super.m_textPendingStart, length);
            }
            if (doStrip) {
                super.m_chars.setLength(super.m_textPendingStart);
            }
            else if (length > 0) {
                if (length <= 1023 && super.m_textPendingStart <= 2097151) {
                    super.m_previous = this.addNode(super.m_coalescedTextType, 3, super.m_parents.peek(), super.m_previous, length + (super.m_textPendingStart << 10), false);
                }
                else {
                    final int dataIndex = super.m_data.size();
                    super.m_previous = this.addNode(super.m_coalescedTextType, 3, super.m_parents.peek(), super.m_previous, -dataIndex, false);
                    super.m_data.addElement(super.m_textPendingStart);
                    super.m_data.addElement(length);
                }
            }
            super.m_textPendingStart = -1;
            final int n = 3;
            super.m_coalescedTextType = n;
            super.m_textType = n;
        }
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        this.charactersFlush();
        final int dataIndex = super.m_data.size();
        super.m_previous = this.addNode(7, 7, super.m_parents.peek(), super.m_previous, -dataIndex, false);
        super.m_data.addElement(super.m_valuesOrPrefixes.stringToIndex(target));
        this.m_values.addElement(data);
        super.m_data.addElement(this.m_valueIndex++);
    }
    
    public final int getFirstAttribute(final int nodeHandle) {
        int nodeID = this.makeNodeIdentity(nodeHandle);
        if (nodeID == -1) {
            return -1;
        }
        int type = this._type2(nodeID);
        if (1 == type) {
            do {
                ++nodeID;
                type = this._type2(nodeID);
                if (type == 2) {
                    return this.makeNodeHandle(nodeID);
                }
            } while (13 == type);
        }
        return -1;
    }
    
    protected int getFirstAttributeIdentity(int identity) {
        if (identity == -1) {
            return -1;
        }
        int type = this._type2(identity);
        if (1 == type) {
            do {
                ++identity;
                type = this._type2(identity);
                if (type == 2) {
                    return identity;
                }
            } while (13 == type);
        }
        return -1;
    }
    
    protected int getNextAttributeIdentity(int identity) {
        while (true) {
            ++identity;
            final int type = this._type2(identity);
            if (type == 2) {
                return identity;
            }
            if (type != 13) {
                return -1;
            }
        }
    }
    
    protected final int getTypedAttribute(final int nodeHandle, final int attType) {
        int nodeID = this.makeNodeIdentity(nodeHandle);
        if (nodeID == -1) {
            return -1;
        }
        int type = this._type2(nodeID);
        if (1 == type) {
            while (true) {
                ++nodeID;
                final int expType = this._exptype2(nodeID);
                if (expType == -1) {
                    return -1;
                }
                type = this.m_extendedTypes[expType].getNodeType();
                if (type == 2) {
                    if (expType == attType) {
                        return this.makeNodeHandle(nodeID);
                    }
                    continue;
                }
                else {
                    if (13 != type) {
                        break;
                    }
                    continue;
                }
            }
        }
        return -1;
    }
    
    public String getLocalName(final int nodeHandle) {
        final int expType = this._exptype(this.makeNodeIdentity(nodeHandle));
        if (expType == 7) {
            int dataIndex = this._dataOrQName(this.makeNodeIdentity(nodeHandle));
            dataIndex = super.m_data.elementAt(-dataIndex);
            return super.m_valuesOrPrefixes.indexToString(dataIndex);
        }
        return super.m_expandedNameTable.getLocalName(expType);
    }
    
    public final String getNodeNameX(final int nodeHandle) {
        final int nodeID = this.makeNodeIdentity(nodeHandle);
        final int eType = this._exptype2(nodeID);
        if (eType == 7) {
            int dataIndex = this._dataOrQName(nodeID);
            dataIndex = super.m_data.elementAt(-dataIndex);
            return super.m_valuesOrPrefixes.indexToString(dataIndex);
        }
        final ExtendedType extType = this.m_extendedTypes[eType];
        if (extType.getNamespace().length() == 0) {
            return extType.getLocalName();
        }
        int qnameIndex = super.m_dataOrQName.elementAt(nodeID);
        if (qnameIndex == 0) {
            return extType.getLocalName();
        }
        if (qnameIndex < 0) {
            qnameIndex = -qnameIndex;
            qnameIndex = super.m_data.elementAt(qnameIndex);
        }
        return super.m_valuesOrPrefixes.indexToString(qnameIndex);
    }
    
    public String getNodeName(final int nodeHandle) {
        final int nodeID = this.makeNodeIdentity(nodeHandle);
        final int eType = this._exptype2(nodeID);
        final ExtendedType extType = this.m_extendedTypes[eType];
        if (extType.getNamespace().length() == 0) {
            final int type = extType.getNodeType();
            final String localName = extType.getLocalName();
            if (type == 13) {
                if (localName.length() == 0) {
                    return "xmlns";
                }
                return "xmlns:" + localName;
            }
            else {
                if (type == 7) {
                    int dataIndex = this._dataOrQName(nodeID);
                    dataIndex = super.m_data.elementAt(-dataIndex);
                    return super.m_valuesOrPrefixes.indexToString(dataIndex);
                }
                if (localName.length() == 0) {
                    return this.getFixedNames(type);
                }
                return localName;
            }
        }
        else {
            int qnameIndex = super.m_dataOrQName.elementAt(nodeID);
            if (qnameIndex == 0) {
                return extType.getLocalName();
            }
            if (qnameIndex < 0) {
                qnameIndex = -qnameIndex;
                qnameIndex = super.m_data.elementAt(qnameIndex);
            }
            return super.m_valuesOrPrefixes.indexToString(qnameIndex);
        }
    }
    
    public XMLString getStringValue(final int nodeHandle) {
        int identity = this.makeNodeIdentity(nodeHandle);
        if (identity == -1) {
            return SAX2DTM2.EMPTY_XML_STR;
        }
        int type = this._type2(identity);
        if (type == 1 || type == 9) {
            final int startNode = identity;
            identity = this._firstch2(identity);
            if (-1 == identity) {
                return SAX2DTM2.EMPTY_XML_STR;
            }
            int offset = -1;
            int length = 0;
            do {
                type = this._exptype2(identity);
                if (type == 3 || type == 4) {
                    final int dataIndex = super.m_dataOrQName.elementAt(identity);
                    if (dataIndex >= 0) {
                        if (-1 == offset) {
                            offset = dataIndex >>> 10;
                        }
                        length += (dataIndex & 0x3FF);
                    }
                    else {
                        if (-1 == offset) {
                            offset = super.m_data.elementAt(-dataIndex);
                        }
                        length += super.m_data.elementAt(-dataIndex + 1);
                    }
                }
                ++identity;
            } while (this._parent2(identity) >= startNode);
            if (length <= 0) {
                return SAX2DTM2.EMPTY_XML_STR;
            }
            if (super.m_xstrf != null) {
                return super.m_xstrf.newstr(super.m_chars, offset, length);
            }
            return new XMLStringDefault(super.m_chars.getString(offset, length));
        }
        else if (3 == type || 4 == type) {
            final int dataIndex2 = super.m_dataOrQName.elementAt(identity);
            if (dataIndex2 >= 0) {
                if (super.m_xstrf != null) {
                    return super.m_xstrf.newstr(super.m_chars, dataIndex2 >>> 10, dataIndex2 & 0x3FF);
                }
                return new XMLStringDefault(super.m_chars.getString(dataIndex2 >>> 10, dataIndex2 & 0x3FF));
            }
            else {
                if (super.m_xstrf != null) {
                    return super.m_xstrf.newstr(super.m_chars, super.m_data.elementAt(-dataIndex2), super.m_data.elementAt(-dataIndex2 + 1));
                }
                return new XMLStringDefault(super.m_chars.getString(super.m_data.elementAt(-dataIndex2), super.m_data.elementAt(-dataIndex2 + 1)));
            }
        }
        else {
            int dataIndex2 = super.m_dataOrQName.elementAt(identity);
            if (dataIndex2 < 0) {
                dataIndex2 = -dataIndex2;
                dataIndex2 = super.m_data.elementAt(dataIndex2 + 1);
            }
            if (super.m_xstrf != null) {
                return super.m_xstrf.newstr(this.m_values.elementAt(dataIndex2));
            }
            return new XMLStringDefault(this.m_values.elementAt(dataIndex2));
        }
    }
    
    public final String getStringValueX(final int nodeHandle) {
        int identity = this.makeNodeIdentity(nodeHandle);
        if (identity == -1) {
            return "";
        }
        int type = this._type2(identity);
        if (type == 1 || type == 9) {
            final int startNode = identity;
            identity = this._firstch2(identity);
            if (-1 == identity) {
                return "";
            }
            int offset = -1;
            int length = 0;
            do {
                type = this._exptype2(identity);
                if (type == 3 || type == 4) {
                    final int dataIndex = super.m_dataOrQName.elementAt(identity);
                    if (dataIndex >= 0) {
                        if (-1 == offset) {
                            offset = dataIndex >>> 10;
                        }
                        length += (dataIndex & 0x3FF);
                    }
                    else {
                        if (-1 == offset) {
                            offset = super.m_data.elementAt(-dataIndex);
                        }
                        length += super.m_data.elementAt(-dataIndex + 1);
                    }
                }
                ++identity;
            } while (this._parent2(identity) >= startNode);
            if (length > 0) {
                return super.m_chars.getString(offset, length);
            }
            return "";
        }
        else {
            if (3 != type && 4 != type) {
                int dataIndex2 = super.m_dataOrQName.elementAt(identity);
                if (dataIndex2 < 0) {
                    dataIndex2 = -dataIndex2;
                    dataIndex2 = super.m_data.elementAt(dataIndex2 + 1);
                }
                return this.m_values.elementAt(dataIndex2);
            }
            int dataIndex2 = super.m_dataOrQName.elementAt(identity);
            if (dataIndex2 >= 0) {
                return super.m_chars.getString(dataIndex2 >>> 10, dataIndex2 & 0x3FF);
            }
            return super.m_chars.getString(super.m_data.elementAt(-dataIndex2), super.m_data.elementAt(-dataIndex2 + 1));
        }
    }
    
    public String getStringValue() {
        final int child = this._firstch2(0);
        if (child == -1) {
            return "";
        }
        if (this._exptype2(child) != 3 || this._nextsib2(child) != -1) {
            return this.getStringValueX(this.getDocument());
        }
        final int dataIndex = super.m_dataOrQName.elementAt(child);
        if (dataIndex >= 0) {
            return super.m_chars.getString(dataIndex >>> 10, dataIndex & 0x3FF);
        }
        return super.m_chars.getString(super.m_data.elementAt(-dataIndex), super.m_data.elementAt(-dataIndex + 1));
    }
    
    public final void dispatchCharactersEvents(final int nodeHandle, final ContentHandler ch, final boolean normalize) throws SAXException {
        int identity = this.makeNodeIdentity(nodeHandle);
        if (identity == -1) {
            return;
        }
        int type = this._type2(identity);
        if (type == 1 || type == 9) {
            final int startNode = identity;
            identity = this._firstch2(identity);
            if (-1 != identity) {
                int offset = -1;
                int length = 0;
                do {
                    type = this._exptype2(identity);
                    if (type == 3 || type == 4) {
                        final int dataIndex = super.m_dataOrQName.elementAt(identity);
                        if (dataIndex >= 0) {
                            if (-1 == offset) {
                                offset = dataIndex >>> 10;
                            }
                            length += (dataIndex & 0x3FF);
                        }
                        else {
                            if (-1 == offset) {
                                offset = super.m_data.elementAt(-dataIndex);
                            }
                            length += super.m_data.elementAt(-dataIndex + 1);
                        }
                    }
                    ++identity;
                } while (this._parent2(identity) >= startNode);
                if (length > 0) {
                    if (normalize) {
                        super.m_chars.sendNormalizedSAXcharacters(ch, offset, length);
                    }
                    else {
                        super.m_chars.sendSAXcharacters(ch, offset, length);
                    }
                }
            }
        }
        else if (3 == type || 4 == type) {
            final int dataIndex2 = super.m_dataOrQName.elementAt(identity);
            if (dataIndex2 >= 0) {
                if (normalize) {
                    super.m_chars.sendNormalizedSAXcharacters(ch, dataIndex2 >>> 10, dataIndex2 & 0x3FF);
                }
                else {
                    super.m_chars.sendSAXcharacters(ch, dataIndex2 >>> 10, dataIndex2 & 0x3FF);
                }
            }
            else if (normalize) {
                super.m_chars.sendNormalizedSAXcharacters(ch, super.m_data.elementAt(-dataIndex2), super.m_data.elementAt(-dataIndex2 + 1));
            }
            else {
                super.m_chars.sendSAXcharacters(ch, super.m_data.elementAt(-dataIndex2), super.m_data.elementAt(-dataIndex2 + 1));
            }
        }
        else {
            int dataIndex2 = super.m_dataOrQName.elementAt(identity);
            if (dataIndex2 < 0) {
                dataIndex2 = -dataIndex2;
                dataIndex2 = super.m_data.elementAt(dataIndex2 + 1);
            }
            final String str = this.m_values.elementAt(dataIndex2);
            if (normalize) {
                FastStringBuffer.sendNormalizedSAXcharacters(str.toCharArray(), 0, str.length(), ch);
            }
            else {
                ch.characters(str.toCharArray(), 0, str.length());
            }
        }
    }
    
    public String getNodeValue(final int nodeHandle) {
        final int identity = this.makeNodeIdentity(nodeHandle);
        final int type = this._type2(identity);
        if (type == 3 || type == 4) {
            final int dataIndex = this._dataOrQName(identity);
            if (dataIndex > 0) {
                return super.m_chars.getString(dataIndex >>> 10, dataIndex & 0x3FF);
            }
            return super.m_chars.getString(super.m_data.elementAt(-dataIndex), super.m_data.elementAt(-dataIndex + 1));
        }
        else {
            if (1 == type || 11 == type || 9 == type) {
                return null;
            }
            int dataIndex = super.m_dataOrQName.elementAt(identity);
            if (dataIndex < 0) {
                dataIndex = -dataIndex;
                dataIndex = super.m_data.elementAt(dataIndex + 1);
            }
            return this.m_values.elementAt(dataIndex);
        }
    }
    
    protected final void copyTextNode(final int nodeID, final SerializationHandler handler) throws SAXException {
        if (nodeID != -1) {
            final int dataIndex = super.m_dataOrQName.elementAt(nodeID);
            if (dataIndex >= 0) {
                super.m_chars.sendSAXcharacters(handler, dataIndex >>> 10, dataIndex & 0x3FF);
            }
            else {
                super.m_chars.sendSAXcharacters(handler, super.m_data.elementAt(-dataIndex), super.m_data.elementAt(-dataIndex + 1));
            }
        }
    }
    
    protected final String copyElement(final int nodeID, final int exptype, final SerializationHandler handler) throws SAXException {
        final ExtendedType extType = this.m_extendedTypes[exptype];
        final String uri = extType.getNamespace();
        final String name = extType.getLocalName();
        if (uri.length() == 0) {
            final int nodeHandle = this.getNodeHandle(nodeID);
            final String prefix = this.getNode(nodeHandle).getPrefix();
            if (prefix.length() == 0) {
                handler.startElement(name);
                return name;
            }
        }
        int qnameIndex = super.m_dataOrQName.elementAt(nodeID);
        if (qnameIndex == 0) {
            handler.startElement(name);
            handler.namespaceAfterStartElement("", uri);
            return name;
        }
        if (qnameIndex < 0) {
            qnameIndex = -qnameIndex;
            qnameIndex = super.m_data.elementAt(qnameIndex);
        }
        final String qName = super.m_valuesOrPrefixes.indexToString(qnameIndex);
        handler.startElement(qName);
        final int prefixIndex = qName.indexOf(58);
        String prefix2;
        if (prefixIndex > 0) {
            prefix2 = qName.substring(0, prefixIndex);
        }
        else {
            prefix2 = null;
        }
        handler.namespaceAfterStartElement(prefix2, uri);
        return qName;
    }
    
    protected final void copyNS(final int nodeID, final SerializationHandler handler, final boolean inScope) throws SAXException {
        if (super.m_namespaceDeclSetElements != null && super.m_namespaceDeclSetElements.size() == 1 && super.m_namespaceDeclSets != null && super.m_namespaceDeclSets.elementAt(0).size() == 1) {
            return;
        }
        SuballocatedIntVector nsContext = null;
        int nextNSNode;
        if (inScope) {
            nsContext = this.findNamespaceContext(nodeID);
            if (nsContext == null || nsContext.size() < 1) {
                return;
            }
            nextNSNode = this.makeNodeIdentity(nsContext.elementAt(0));
        }
        else {
            nextNSNode = this.getNextNamespaceNode2(nodeID);
        }
        int nsIndex = 1;
        while (nextNSNode != -1) {
            final int eType = this._exptype2(nextNSNode);
            final String nodeName = this.m_extendedTypes[eType].getLocalName();
            int dataIndex = super.m_dataOrQName.elementAt(nextNSNode);
            if (dataIndex < 0) {
                dataIndex = -dataIndex;
                dataIndex = super.m_data.elementAt(dataIndex + 1);
            }
            final String nodeValue = this.m_values.elementAt(dataIndex);
            handler.namespaceAfterStartElement(nodeName, nodeValue);
            if (inScope) {
                if (nsIndex >= nsContext.size()) {
                    return;
                }
                nextNSNode = this.makeNodeIdentity(nsContext.elementAt(nsIndex));
                ++nsIndex;
            }
            else {
                nextNSNode = this.getNextNamespaceNode2(nextNSNode);
            }
        }
    }
    
    protected final int getNextNamespaceNode2(int baseID) {
        int type;
        while ((type = this._type2(++baseID)) == 2) {}
        if (type == 13) {
            return baseID;
        }
        return -1;
    }
    
    protected final void copyAttributes(final int nodeID, final SerializationHandler handler) throws SAXException {
        for (int current = this.getFirstAttributeIdentity(nodeID); current != -1; current = this.getNextAttributeIdentity(current)) {
            final int eType = this._exptype2(current);
            this.copyAttribute(current, eType, handler);
        }
    }
    
    protected final void copyAttribute(final int nodeID, final int exptype, final SerializationHandler handler) throws SAXException {
        final ExtendedType extType = this.m_extendedTypes[exptype];
        final String uri = extType.getNamespace();
        final String localName = extType.getLocalName();
        String prefix = null;
        String qname = null;
        int valueIndex;
        final int dataIndex = valueIndex = this._dataOrQName(nodeID);
        if (dataIndex <= 0) {
            final int prefixIndex = super.m_data.elementAt(-dataIndex);
            valueIndex = super.m_data.elementAt(-dataIndex + 1);
            qname = super.m_valuesOrPrefixes.indexToString(prefixIndex);
            final int colonIndex = qname.indexOf(58);
            if (colonIndex > 0) {
                prefix = qname.substring(0, colonIndex);
            }
        }
        if (uri.length() != 0) {
            handler.namespaceAfterStartElement(prefix, uri);
        }
        final String nodeName = (prefix != null) ? qname : localName;
        final String nodeValue = this.m_values.elementAt(valueIndex);
        handler.addAttribute(nodeName, nodeValue);
    }
    
    static {
        EMPTY_XML_STR = new XMLStringDefault("");
    }
    
    public final class ChildrenIterator extends InternalAxisIteratorBase
    {
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = SAX2DTM2.this.getDocument();
            }
            if (super._isRestartable) {
                super._startNode = node;
                super._currentNode = ((node == -1) ? -1 : SAX2DTM2.this._firstch2(SAX2DTM2.this.makeNodeIdentity(node)));
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            if (super._currentNode != -1) {
                final int node = super._currentNode;
                super._currentNode = SAX2DTM2.this._nextsib2(node);
                return this.returnNode(SAX2DTM2.this.makeNodeHandle(node));
            }
            return -1;
        }
    }
    
    public final class ParentIterator extends InternalAxisIteratorBase
    {
        private int _nodeType;
        
        public ParentIterator() {
            this._nodeType = -1;
        }
        
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = SAX2DTM2.this.getDocument();
            }
            if (super._isRestartable) {
                if ((super._startNode = node) != -1) {
                    super._currentNode = SAX2DTM2.this._parent2(SAX2DTM2.this.makeNodeIdentity(node));
                }
                else {
                    super._currentNode = -1;
                }
                return this.resetPosition();
            }
            return this;
        }
        
        public DTMAxisIterator setNodeType(final int type) {
            this._nodeType = type;
            return this;
        }
        
        public int next() {
            final int result = super._currentNode;
            if (result == -1) {
                return -1;
            }
            if (this._nodeType == -1) {
                super._currentNode = -1;
                return this.returnNode(SAX2DTM2.this.makeNodeHandle(result));
            }
            if (this._nodeType >= 14) {
                if (this._nodeType == SAX2DTM2.this._exptype2(result)) {
                    super._currentNode = -1;
                    return this.returnNode(SAX2DTM2.this.makeNodeHandle(result));
                }
            }
            else if (this._nodeType == SAX2DTM2.this._type2(result)) {
                super._currentNode = -1;
                return this.returnNode(SAX2DTM2.this.makeNodeHandle(result));
            }
            return -1;
        }
    }
    
    public final class TypedChildrenIterator extends InternalAxisIteratorBase
    {
        private final int _nodeType;
        
        public TypedChildrenIterator(final int nodeType) {
            this._nodeType = nodeType;
        }
        
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = SAX2DTM2.this.getDocument();
            }
            if (super._isRestartable) {
                super._startNode = node;
                super._currentNode = ((node == -1) ? -1 : SAX2DTM2.this._firstch2(SAX2DTM2.this.makeNodeIdentity(super._startNode)));
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            int node = super._currentNode;
            if (node == -1) {
                return -1;
            }
            final int nodeType = this._nodeType;
            if (nodeType != 1) {
                while (node != -1) {
                    if (SAX2DTM2.this._exptype2(node) == nodeType) {
                        break;
                    }
                    node = SAX2DTM2.this._nextsib2(node);
                }
            }
            else {
                while (node != -1) {
                    final int eType = SAX2DTM2.this._exptype2(node);
                    if (eType >= 14) {
                        break;
                    }
                    node = SAX2DTM2.this._nextsib2(node);
                }
            }
            if (node == -1) {
                return super._currentNode = -1;
            }
            super._currentNode = SAX2DTM2.this._nextsib2(node);
            return this.returnNode(SAX2DTM2.this.makeNodeHandle(node));
        }
        
        public int getNodeByPosition(final int position) {
            if (position <= 0) {
                return -1;
            }
            int node = super._currentNode;
            int pos = 0;
            final int nodeType = this._nodeType;
            if (nodeType != 1) {
                while (node != -1) {
                    if (SAX2DTM2.this._exptype2(node) == nodeType && ++pos == position) {
                        return SAX2DTM2.this.makeNodeHandle(node);
                    }
                    node = SAX2DTM2.this._nextsib2(node);
                }
                return -1;
            }
            while (node != -1) {
                if (SAX2DTM2.this._exptype2(node) >= 14 && ++pos == position) {
                    return SAX2DTM2.this.makeNodeHandle(node);
                }
                node = SAX2DTM2.this._nextsib2(node);
            }
            return -1;
        }
    }
    
    public class TypedRootIterator extends RootIterator
    {
        private final int _nodeType;
        
        public TypedRootIterator(final int nodeType) {
            this._nodeType = nodeType;
        }
        
        public int next() {
            if (super._startNode == super._currentNode) {
                return -1;
            }
            final int node = super._startNode;
            final int expType = SAX2DTM2.this._exptype2(SAX2DTM2.this.makeNodeIdentity(node));
            super._currentNode = node;
            if (this._nodeType >= 14) {
                if (this._nodeType == expType) {
                    return this.returnNode(node);
                }
            }
            else if (expType < 14) {
                if (expType == this._nodeType) {
                    return this.returnNode(node);
                }
            }
            else if (SAX2DTM2.this.m_extendedTypes[expType].getNodeType() == this._nodeType) {
                return this.returnNode(node);
            }
            return -1;
        }
    }
    
    public class FollowingSiblingIterator extends InternalAxisIteratorBase
    {
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = SAX2DTM2.this.getDocument();
            }
            if (super._isRestartable) {
                super._startNode = node;
                super._currentNode = SAX2DTM2.this.makeNodeIdentity(node);
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            super._currentNode = ((super._currentNode == -1) ? -1 : SAX2DTM2.this._nextsib2(super._currentNode));
            return this.returnNode(SAX2DTM2.this.makeNodeHandle(super._currentNode));
        }
    }
    
    public final class TypedFollowingSiblingIterator extends FollowingSiblingIterator
    {
        private final int _nodeType;
        
        public TypedFollowingSiblingIterator(final int type) {
            this._nodeType = type;
        }
        
        public int next() {
            if (super._currentNode == -1) {
                return -1;
            }
            int node = super._currentNode;
            final int nodeType = this._nodeType;
            if (nodeType != 1) {
                while ((node = SAX2DTM2.this._nextsib2(node)) != -1) {
                    if (SAX2DTM2.this._exptype2(node) == nodeType) {
                        break;
                    }
                }
            }
            else {
                while ((node = SAX2DTM2.this._nextsib2(node)) != -1 && SAX2DTM2.this._exptype2(node) < 14) {}
            }
            return ((super._currentNode = node) == -1) ? -1 : this.returnNode(SAX2DTM2.this.makeNodeHandle(node));
        }
    }
    
    public final class AttributeIterator extends InternalAxisIteratorBase
    {
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = SAX2DTM2.this.getDocument();
            }
            if (super._isRestartable) {
                super._startNode = node;
                super._currentNode = SAX2DTM2.this.getFirstAttributeIdentity(SAX2DTM2.this.makeNodeIdentity(node));
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            final int node = super._currentNode;
            if (node != -1) {
                super._currentNode = SAX2DTM2.this.getNextAttributeIdentity(node);
                return this.returnNode(SAX2DTM2.this.makeNodeHandle(node));
            }
            return -1;
        }
    }
    
    public final class TypedAttributeIterator extends InternalAxisIteratorBase
    {
        private final int _nodeType;
        
        public TypedAttributeIterator(final int nodeType) {
            this._nodeType = nodeType;
        }
        
        public DTMAxisIterator setStartNode(final int node) {
            if (super._isRestartable) {
                super._startNode = node;
                super._currentNode = SAX2DTM2.this.getTypedAttribute(node, this._nodeType);
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            final int node = super._currentNode;
            super._currentNode = -1;
            return this.returnNode(node);
        }
    }
    
    public class PrecedingSiblingIterator extends InternalAxisIteratorBase
    {
        protected int _startNodeID;
        
        public boolean isReverse() {
            return true;
        }
        
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = SAX2DTM2.this.getDocument();
            }
            if (!super._isRestartable) {
                return this;
            }
            super._startNode = node;
            final int nodeIdentity = SAX2DTM2.this.makeNodeIdentity(node);
            this._startNodeID = nodeIdentity;
            node = nodeIdentity;
            if (node == -1) {
                super._currentNode = node;
                return this.resetPosition();
            }
            final int type = SAX2DTM2.this._type2(node);
            if (2 == type || 13 == type) {
                super._currentNode = node;
            }
            else {
                super._currentNode = SAX2DTM2.this._parent2(node);
                if (-1 != super._currentNode) {
                    super._currentNode = SAX2DTM2.this._firstch2(super._currentNode);
                }
                else {
                    super._currentNode = node;
                }
            }
            return this.resetPosition();
        }
        
        public int next() {
            if (super._currentNode == this._startNodeID || super._currentNode == -1) {
                return -1;
            }
            final int node = super._currentNode;
            super._currentNode = SAX2DTM2.this._nextsib2(node);
            return this.returnNode(SAX2DTM2.this.makeNodeHandle(node));
        }
    }
    
    public final class TypedPrecedingSiblingIterator extends PrecedingSiblingIterator
    {
        private final int _nodeType;
        
        public TypedPrecedingSiblingIterator(final int type) {
            this._nodeType = type;
        }
        
        public int next() {
            int node = super._currentNode;
            final int nodeType = this._nodeType;
            final int startNodeID = super._startNodeID;
            if (nodeType != 1) {
                while (node != -1 && node != startNodeID) {
                    if (SAX2DTM2.this._exptype2(node) == nodeType) {
                        break;
                    }
                    node = SAX2DTM2.this._nextsib2(node);
                }
            }
            else {
                while (node != -1 && node != startNodeID && SAX2DTM2.this._exptype2(node) < 14) {
                    node = SAX2DTM2.this._nextsib2(node);
                }
            }
            if (node == -1 || node == startNodeID) {
                return super._currentNode = -1;
            }
            super._currentNode = SAX2DTM2.this._nextsib2(node);
            return this.returnNode(SAX2DTM2.this.makeNodeHandle(node));
        }
        
        public int getLast() {
            if (super._last != -1) {
                return super._last;
            }
            this.setMark();
            int node = super._currentNode;
            final int nodeType = this._nodeType;
            final int startNodeID = super._startNodeID;
            int last = 0;
            if (nodeType != 1) {
                while (node != -1) {
                    if (node == startNodeID) {
                        break;
                    }
                    if (SAX2DTM2.this._exptype2(node) == nodeType) {
                        ++last;
                    }
                    node = SAX2DTM2.this._nextsib2(node);
                }
            }
            else {
                while (node != -1 && node != startNodeID) {
                    if (SAX2DTM2.this._exptype2(node) >= 14) {
                        ++last;
                    }
                    node = SAX2DTM2.this._nextsib2(node);
                }
            }
            this.gotoMark();
            return super._last = last;
        }
    }
    
    public class PrecedingIterator extends InternalAxisIteratorBase
    {
        private final int _maxAncestors = 8;
        protected int[] _stack;
        protected int _sp;
        protected int _oldsp;
        protected int _markedsp;
        protected int _markedNode;
        protected int _markedDescendant;
        
        public PrecedingIterator() {
            this._stack = new int[8];
        }
        
        public boolean isReverse() {
            return true;
        }
        
        public DTMAxisIterator cloneIterator() {
            super._isRestartable = false;
            try {
                final PrecedingIterator clone = (PrecedingIterator)super.clone();
                final int[] stackCopy = new int[this._stack.length];
                System.arraycopy(this._stack, 0, stackCopy, 0, this._stack.length);
                clone._stack = stackCopy;
                return clone;
            }
            catch (CloneNotSupportedException e) {
                throw new DTMException(XMLMessages.createXMLMessage("ER_ITERATOR_CLONE_NOT_SUPPORTED", null));
            }
        }
        
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = SAX2DTM2.this.getDocument();
            }
            if (super._isRestartable) {
                node = SAX2DTM2.this.makeNodeIdentity(node);
                if (SAX2DTM2.this._type2(node) == 2) {
                    node = SAX2DTM2.this._parent2(node);
                }
                super._startNode = node;
                int index;
                this._stack[index = 0] = node;
                int parent = node;
                while ((parent = SAX2DTM2.this._parent2(parent)) != -1) {
                    if (++index == this._stack.length) {
                        final int[] stack = new int[index * 2];
                        System.arraycopy(this._stack, 0, stack, 0, index);
                        this._stack = stack;
                    }
                    this._stack[index] = parent;
                }
                if (index > 0) {
                    --index;
                }
                super._currentNode = this._stack[index];
                final int n = index;
                this._sp = n;
                this._oldsp = n;
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            ++super._currentNode;
            while (this._sp >= 0) {
                if (super._currentNode < this._stack[this._sp]) {
                    final int type = SAX2DTM2.this._type2(super._currentNode);
                    if (type != 2 && type != 13) {
                        return this.returnNode(SAX2DTM2.this.makeNodeHandle(super._currentNode));
                    }
                }
                else {
                    --this._sp;
                }
                ++super._currentNode;
            }
            return -1;
        }
        
        public DTMAxisIterator reset() {
            this._sp = this._oldsp;
            return this.resetPosition();
        }
        
        public void setMark() {
            this._markedsp = this._sp;
            this._markedNode = super._currentNode;
            this._markedDescendant = this._stack[0];
        }
        
        public void gotoMark() {
            this._sp = this._markedsp;
            super._currentNode = this._markedNode;
        }
    }
    
    public final class TypedPrecedingIterator extends PrecedingIterator
    {
        private final int _nodeType;
        
        public TypedPrecedingIterator(final int type) {
            this._nodeType = type;
        }
        
        public int next() {
            int node = super._currentNode;
            final int nodeType = this._nodeType;
            if (nodeType >= 14) {
                while (true) {
                    ++node;
                    if (super._sp < 0) {
                        node = -1;
                        break;
                    }
                    if (node >= super._stack[super._sp]) {
                        if (--super._sp < 0) {
                            node = -1;
                            break;
                        }
                        continue;
                    }
                    else {
                        if (SAX2DTM2.this._exptype2(node) == nodeType) {
                            break;
                        }
                        continue;
                    }
                }
            }
            else {
                while (true) {
                    ++node;
                    if (super._sp < 0) {
                        node = -1;
                        break;
                    }
                    if (node >= super._stack[super._sp]) {
                        if (--super._sp < 0) {
                            node = -1;
                            break;
                        }
                        continue;
                    }
                    else {
                        final int expType = SAX2DTM2.this._exptype2(node);
                        if (expType < 14) {
                            if (expType == nodeType) {
                                break;
                            }
                            continue;
                        }
                        else {
                            if (SAX2DTM2.this.m_extendedTypes[expType].getNodeType() == nodeType) {
                                break;
                            }
                            continue;
                        }
                    }
                }
            }
            super._currentNode = node;
            return (node == -1) ? -1 : this.returnNode(SAX2DTM2.this.makeNodeHandle(node));
        }
    }
    
    public class FollowingIterator extends InternalAxisIteratorBase
    {
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = SAX2DTM2.this.getDocument();
            }
            if (super._isRestartable) {
                super._startNode = node;
                node = SAX2DTM2.this.makeNodeIdentity(node);
                final int type = SAX2DTM2.this._type2(node);
                if (2 == type || 13 == type) {
                    node = SAX2DTM2.this._parent2(node);
                    final int first = SAX2DTM2.this._firstch2(node);
                    if (-1 != first) {
                        super._currentNode = SAX2DTM2.this.makeNodeHandle(first);
                        return this.resetPosition();
                    }
                }
                int first;
                do {
                    first = SAX2DTM2.this._nextsib2(node);
                    if (-1 == first) {
                        node = SAX2DTM2.this._parent2(node);
                    }
                } while (-1 == first && -1 != node);
                super._currentNode = SAX2DTM2.this.makeNodeHandle(first);
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            final int node = super._currentNode;
            int current = SAX2DTM2.this.makeNodeIdentity(node);
            while (true) {
                ++current;
                final int type = SAX2DTM2.this._type2(current);
                if (-1 == type) {
                    super._currentNode = -1;
                    return this.returnNode(node);
                }
                if (2 == type) {
                    continue;
                }
                if (13 == type) {
                    continue;
                }
                super._currentNode = SAX2DTM2.this.makeNodeHandle(current);
                return this.returnNode(node);
            }
        }
    }
    
    public final class TypedFollowingIterator extends FollowingIterator
    {
        private final int _nodeType;
        
        public TypedFollowingIterator(final int type) {
            this._nodeType = type;
        }
        
        public int next() {
            final int nodeType = this._nodeType;
            int currentNodeID = SAX2DTM2.this.makeNodeIdentity(super._currentNode);
            int node;
            if (nodeType >= 14) {
                do {
                    int current;
                    node = (current = currentNodeID);
                    int type;
                    do {
                        ++current;
                        type = SAX2DTM2.this._type2(current);
                    } while (type != -1 && (2 == type || 13 == type));
                    currentNodeID = ((type != -1) ? current : -1);
                    if (node != -1) {
                        continue;
                    }
                    break;
                } while (SAX2DTM2.this._exptype2(node) != nodeType);
            }
            else {
                do {
                    int current;
                    node = (current = currentNodeID);
                    int type;
                    do {
                        ++current;
                        type = SAX2DTM2.this._type2(current);
                    } while (type != -1 && (2 == type || 13 == type));
                    currentNodeID = ((type != -1) ? current : -1);
                } while (node != -1 && SAX2DTM2.this._exptype2(node) != nodeType && SAX2DTM2.this._type2(node) != nodeType);
            }
            super._currentNode = SAX2DTM2.this.makeNodeHandle(currentNodeID);
            return (node == -1) ? -1 : this.returnNode(SAX2DTM2.this.makeNodeHandle(node));
        }
    }
    
    public class AncestorIterator extends InternalAxisIteratorBase
    {
        private static final int m_blocksize = 32;
        int[] m_ancestors;
        int m_size;
        int m_ancestorsPos;
        int m_markedPos;
        int m_realStartNode;
        
        public AncestorIterator() {
            this.m_ancestors = new int[32];
            this.m_size = 0;
        }
        
        public int getStartNode() {
            return this.m_realStartNode;
        }
        
        public final boolean isReverse() {
            return true;
        }
        
        public DTMAxisIterator cloneIterator() {
            super._isRestartable = false;
            try {
                final AncestorIterator clone = (AncestorIterator)super.clone();
                clone._startNode = super._startNode;
                return clone;
            }
            catch (CloneNotSupportedException e) {
                throw new DTMException(XMLMessages.createXMLMessage("ER_ITERATOR_CLONE_NOT_SUPPORTED", null));
            }
        }
        
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = SAX2DTM2.this.getDocument();
            }
            this.m_realStartNode = node;
            if (!super._isRestartable) {
                return this;
            }
            int nodeID = SAX2DTM2.this.makeNodeIdentity(node);
            this.m_size = 0;
            if (nodeID == -1) {
                super._currentNode = -1;
                this.m_ancestorsPos = 0;
                return this;
            }
            if (!super._includeSelf) {
                nodeID = SAX2DTM2.this._parent2(nodeID);
                node = SAX2DTM2.this.makeNodeHandle(nodeID);
            }
            super._startNode = node;
            while (nodeID != -1) {
                if (this.m_size >= this.m_ancestors.length) {
                    final int[] newAncestors = new int[this.m_size * 2];
                    System.arraycopy(this.m_ancestors, 0, newAncestors, 0, this.m_ancestors.length);
                    this.m_ancestors = newAncestors;
                }
                this.m_ancestors[this.m_size++] = node;
                nodeID = SAX2DTM2.this._parent2(nodeID);
                node = SAX2DTM2.this.makeNodeHandle(nodeID);
            }
            this.m_ancestorsPos = this.m_size - 1;
            super._currentNode = ((this.m_ancestorsPos >= 0) ? this.m_ancestors[this.m_ancestorsPos] : -1);
            return this.resetPosition();
        }
        
        public DTMAxisIterator reset() {
            this.m_ancestorsPos = this.m_size - 1;
            super._currentNode = ((this.m_ancestorsPos >= 0) ? this.m_ancestors[this.m_ancestorsPos] : -1);
            return this.resetPosition();
        }
        
        public int next() {
            final int next = super._currentNode;
            final int ancestorsPos = this.m_ancestorsPos - 1;
            this.m_ancestorsPos = ancestorsPos;
            final int pos = ancestorsPos;
            super._currentNode = ((pos >= 0) ? this.m_ancestors[this.m_ancestorsPos] : -1);
            return this.returnNode(next);
        }
        
        public void setMark() {
            this.m_markedPos = this.m_ancestorsPos;
        }
        
        public void gotoMark() {
            this.m_ancestorsPos = this.m_markedPos;
            super._currentNode = ((this.m_ancestorsPos >= 0) ? this.m_ancestors[this.m_ancestorsPos] : -1);
        }
    }
    
    public final class TypedAncestorIterator extends AncestorIterator
    {
        private final int _nodeType;
        
        public TypedAncestorIterator(final int type) {
            this._nodeType = type;
        }
        
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = SAX2DTM2.this.getDocument();
            }
            super.m_realStartNode = node;
            if (!super._isRestartable) {
                return this;
            }
            int nodeID = SAX2DTM2.this.makeNodeIdentity(node);
            super.m_size = 0;
            if (nodeID == -1) {
                super._currentNode = -1;
                super.m_ancestorsPos = 0;
                return this;
            }
            final int nodeType = this._nodeType;
            if (!super._includeSelf) {
                nodeID = SAX2DTM2.this._parent2(nodeID);
                node = SAX2DTM2.this.makeNodeHandle(nodeID);
            }
            super._startNode = node;
            if (nodeType >= 14) {
                while (nodeID != -1) {
                    final int eType = SAX2DTM2.this._exptype2(nodeID);
                    if (eType == nodeType) {
                        if (super.m_size >= super.m_ancestors.length) {
                            final int[] newAncestors = new int[super.m_size * 2];
                            System.arraycopy(super.m_ancestors, 0, newAncestors, 0, super.m_ancestors.length);
                            super.m_ancestors = newAncestors;
                        }
                        super.m_ancestors[super.m_size++] = SAX2DTM2.this.makeNodeHandle(nodeID);
                    }
                    nodeID = SAX2DTM2.this._parent2(nodeID);
                }
            }
            else {
                while (nodeID != -1) {
                    final int eType = SAX2DTM2.this._exptype2(nodeID);
                    if ((eType < 14 && eType == nodeType) || (eType >= 14 && SAX2DTM2.this.m_extendedTypes[eType].getNodeType() == nodeType)) {
                        if (super.m_size >= super.m_ancestors.length) {
                            final int[] newAncestors = new int[super.m_size * 2];
                            System.arraycopy(super.m_ancestors, 0, newAncestors, 0, super.m_ancestors.length);
                            super.m_ancestors = newAncestors;
                        }
                        super.m_ancestors[super.m_size++] = SAX2DTM2.this.makeNodeHandle(nodeID);
                    }
                    nodeID = SAX2DTM2.this._parent2(nodeID);
                }
            }
            super.m_ancestorsPos = super.m_size - 1;
            super._currentNode = ((super.m_ancestorsPos >= 0) ? super.m_ancestors[super.m_ancestorsPos] : -1);
            return this.resetPosition();
        }
        
        public int getNodeByPosition(final int position) {
            if (position > 0 && position <= super.m_size) {
                return super.m_ancestors[position - 1];
            }
            return -1;
        }
        
        public int getLast() {
            return super.m_size;
        }
    }
    
    public class DescendantIterator extends InternalAxisIteratorBase
    {
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = SAX2DTM2.this.getDocument();
            }
            if (super._isRestartable) {
                node = SAX2DTM2.this.makeNodeIdentity(node);
                super._startNode = node;
                if (super._includeSelf) {
                    --node;
                }
                super._currentNode = node;
                return this.resetPosition();
            }
            return this;
        }
        
        protected final boolean isDescendant(final int identity) {
            return SAX2DTM2.this._parent2(identity) >= super._startNode || super._startNode == identity;
        }
        
        public int next() {
            final int startNode = super._startNode;
            if (startNode == -1) {
                return -1;
            }
            if (super._includeSelf && super._currentNode + 1 == startNode) {
                return this.returnNode(SAX2DTM2.this.makeNodeHandle(++super._currentNode));
            }
            int node = super._currentNode;
            if (startNode == 0) {
                int eType;
                int type;
                do {
                    ++node;
                    eType = SAX2DTM2.this._exptype2(node);
                    if (-1 == eType) {
                        return super._currentNode = -1;
                    }
                } while (eType == 3 || (type = SAX2DTM2.this.m_extendedTypes[eType].getNodeType()) == 2 || type == 13);
            }
            else {
                int type;
                do {
                    ++node;
                    type = SAX2DTM2.this._type2(node);
                    if (-1 == type || !this.isDescendant(node)) {
                        return super._currentNode = -1;
                    }
                } while (2 == type || 3 == type || 13 == type);
            }
            super._currentNode = node;
            return this.returnNode(SAX2DTM2.this.makeNodeHandle(node));
        }
        
        public DTMAxisIterator reset() {
            final boolean temp = super._isRestartable;
            super._isRestartable = true;
            this.setStartNode(SAX2DTM2.this.makeNodeHandle(super._startNode));
            super._isRestartable = temp;
            return this;
        }
    }
    
    public final class TypedDescendantIterator extends DescendantIterator
    {
        private final int _nodeType;
        
        public TypedDescendantIterator(final int nodeType) {
            this._nodeType = nodeType;
        }
        
        public int next() {
            final int startNode = super._startNode;
            if (super._startNode == -1) {
                return -1;
            }
            int node = super._currentNode;
            final int nodeType = this._nodeType;
            if (nodeType != 1) {
                int expType;
                do {
                    ++node;
                    expType = SAX2DTM2.this._exptype2(node);
                    if (-1 == expType || (SAX2DTM2.this._parent2(node) < startNode && startNode != node)) {
                        return super._currentNode = -1;
                    }
                } while (expType != nodeType);
            }
            else if (startNode == 0) {
                int expType;
                do {
                    ++node;
                    expType = SAX2DTM2.this._exptype2(node);
                    if (-1 == expType) {
                        return super._currentNode = -1;
                    }
                } while (expType < 14 || SAX2DTM2.this.m_extendedTypes[expType].getNodeType() != 1);
            }
            else {
                int expType;
                do {
                    ++node;
                    expType = SAX2DTM2.this._exptype2(node);
                    if (-1 == expType || (SAX2DTM2.this._parent2(node) < startNode && startNode != node)) {
                        return super._currentNode = -1;
                    }
                } while (expType < 14 || SAX2DTM2.this.m_extendedTypes[expType].getNodeType() != 1);
            }
            super._currentNode = node;
            return this.returnNode(SAX2DTM2.this.makeNodeHandle(node));
        }
    }
    
    public final class TypedSingletonIterator extends SingletonIterator
    {
        private final int _nodeType;
        
        public TypedSingletonIterator(final int nodeType) {
            this._nodeType = nodeType;
        }
        
        public int next() {
            final int result = super._currentNode;
            if (result == -1) {
                return -1;
            }
            super._currentNode = -1;
            if (this._nodeType >= 14) {
                if (SAX2DTM2.this._exptype2(SAX2DTM2.this.makeNodeIdentity(result)) == this._nodeType) {
                    return this.returnNode(result);
                }
            }
            else if (SAX2DTM2.this._type2(SAX2DTM2.this.makeNodeIdentity(result)) == this._nodeType) {
                return this.returnNode(result);
            }
            return -1;
        }
    }
}
