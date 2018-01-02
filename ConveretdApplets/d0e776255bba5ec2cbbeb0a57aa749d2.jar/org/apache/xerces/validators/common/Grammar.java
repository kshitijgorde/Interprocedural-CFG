// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.common;

import org.apache.xerces.utils.StringPool;
import org.apache.xerces.utils.Hash2intTable;
import java.util.Vector;
import org.apache.xerces.validators.datatype.DatatypeValidator;
import org.apache.xerces.utils.QName;
import org.w3c.dom.Document;
import org.apache.xerces.framework.XMLContentSpec;

public class Grammar implements XMLContentSpec.Provider
{
    public static final int TOP_LEVEL_SCOPE = -1;
    private static final int CHUNK_SHIFT = 8;
    private static final int CHUNK_SIZE = 256;
    private static final int CHUNK_MASK = 255;
    private static final int INITIAL_CHUNK_COUNT = 4;
    private static final int LIST_FLAG = 32768;
    private static final int LIST_MASK = -32769;
    private int fTargetNamespace;
    private Document fGrammarDocument;
    private int fElementDeclCount;
    private QName[][] fElementDeclName;
    private int[][] fElementDeclType;
    private DatatypeValidator[][] fElementDeclDatatypeValidator;
    private int[][] fElementDeclContentSpecIndex;
    private XMLContentModel[][] fElementDeclContentModelValidator;
    private int[][] fElementDeclFirstAttributeDeclIndex;
    private int[][] fElementDeclLastAttributeDeclIndex;
    private Vector[][] fElementDeclUnique;
    private Vector[][] fElementDeclKey;
    private Vector[][] fElementDeclKeyRef;
    private int fContentSpecCount;
    private int[][] fContentSpecType;
    private int[][] fContentSpecValue;
    private int[][] fContentSpecOtherValue;
    private int fAttributeDeclCount;
    private QName[][] fAttributeDeclName;
    private int[][] fAttributeDeclType;
    private int[][] fAttributeDeclEnumeration;
    private int[][] fAttributeDeclDefaultType;
    private DatatypeValidator[][] fAttributeDeclDatatypeValidator;
    private String[][] fAttributeDeclDefaultValue;
    private int[][] fAttributeDeclNextAttributeDeclIndex;
    private Hash2intTable fElementNameAndScopeToElementDeclIndexMapping;
    private QName fQName1;
    private QName fQName2;
    private int fLeafCount;
    private int fEpsilonIndex;
    
    public Grammar() {
        this.fElementDeclCount = 0;
        this.fElementDeclName = new QName[4][];
        this.fElementDeclType = new int[4][];
        this.fElementDeclDatatypeValidator = new DatatypeValidator[4][];
        this.fElementDeclContentSpecIndex = new int[4][];
        this.fElementDeclContentModelValidator = new XMLContentModel[4][];
        this.fElementDeclFirstAttributeDeclIndex = new int[4][];
        this.fElementDeclLastAttributeDeclIndex = new int[4][];
        this.fElementDeclUnique = new Vector[4][];
        this.fElementDeclKey = new Vector[4][];
        this.fElementDeclKeyRef = new Vector[4][];
        this.fContentSpecCount = 0;
        this.fContentSpecType = new int[4][];
        this.fContentSpecValue = new int[4][];
        this.fContentSpecOtherValue = new int[4][];
        this.fAttributeDeclCount = 0;
        this.fAttributeDeclName = new QName[4][];
        this.fAttributeDeclType = new int[4][];
        this.fAttributeDeclEnumeration = new int[4][];
        this.fAttributeDeclDefaultType = new int[4][];
        this.fAttributeDeclDatatypeValidator = new DatatypeValidator[4][];
        this.fAttributeDeclDefaultValue = new String[4][];
        this.fAttributeDeclNextAttributeDeclIndex = new int[4][];
        this.fElementNameAndScopeToElementDeclIndexMapping = new Hash2intTable();
        this.fQName1 = new QName();
        this.fQName2 = new QName();
        this.fLeafCount = 0;
        this.fEpsilonIndex = -1;
    }
    
    public Document getGrammarDocument() {
        return this.fGrammarDocument;
    }
    
    public int getElementDeclIndex(final int n, final int n2) {
        if (n > -1 && n2 > -2) {
            return this.fElementNameAndScopeToElementDeclIndexMapping.get(0, n, n2);
        }
        return -1;
    }
    
    public int getElementDeclIndex(final int n, final int n2, final int n3) {
        if (n2 > -1 && n3 > -2) {
            return this.fElementNameAndScopeToElementDeclIndexMapping.get(n, n2, n3);
        }
        return -1;
    }
    
    public int getElementDeclIndex(final QName qName, final int n) {
        if (qName.localpart > -1 && n > -2) {
            return this.fElementNameAndScopeToElementDeclIndexMapping.get(qName.uri, qName.localpart, n);
        }
        return -1;
    }
    
    public boolean getElementDecl(final int n, final XMLElementDecl xmlElementDecl) {
        if (n < 0 || n >= this.fElementDeclCount) {
            return false;
        }
        final int n2 = n >> 8;
        final int n3 = n & 0xFF;
        xmlElementDecl.name.setValues(this.fElementDeclName[n2][n3]);
        if (this.fElementDeclType[n2][n3] == -1) {
            xmlElementDecl.type = -1;
            xmlElementDecl.list = false;
        }
        else {
            xmlElementDecl.type = (this.fElementDeclType[n2][n3] & 0xFFFF7FFF);
            xmlElementDecl.list = ((this.fElementDeclType[n2][n3] & 0x8000) != 0x0);
        }
        xmlElementDecl.datatypeValidator = this.fElementDeclDatatypeValidator[n2][n3];
        xmlElementDecl.contentSpecIndex = this.fElementDeclContentSpecIndex[n2][n3];
        xmlElementDecl.unique.removeAllElements();
        for (int n4 = (this.fElementDeclUnique[n2][n3] != null) ? this.fElementDeclUnique[n2][n3].size() : 0, i = 0; i < n4; ++i) {
            xmlElementDecl.unique.addElement(this.fElementDeclUnique[n2][n3].elementAt(i));
        }
        xmlElementDecl.key.removeAllElements();
        for (int n5 = (this.fElementDeclKey[n2][n3] != null) ? this.fElementDeclKey[n2][n3].size() : 0, j = 0; j < n5; ++j) {
            xmlElementDecl.key.addElement(this.fElementDeclKey[n2][n3].elementAt(j));
        }
        xmlElementDecl.keyRef.removeAllElements();
        for (int n6 = (this.fElementDeclKeyRef[n2][n3] != null) ? this.fElementDeclKeyRef[n2][n3].size() : 0, k = 0; k < n6; ++k) {
            xmlElementDecl.keyRef.addElement(this.fElementDeclKeyRef[n2][n3].elementAt(k));
        }
        return true;
    }
    
    public int getFirstAttributeDeclIndex(final int n) {
        return this.fElementDeclFirstAttributeDeclIndex[n >> 8][n & 0xFF];
    }
    
    public int getNextAttributeDeclIndex(final int n) {
        return this.fAttributeDeclNextAttributeDeclIndex[n >> 8][n & 0xFF];
    }
    
    public boolean getContentSpec(final int n, final XMLContentSpec xmlContentSpec) {
        if (n < 0 || n >= this.fContentSpecCount) {
            return false;
        }
        final int n2 = n >> 8;
        final int n3 = n & 0xFF;
        xmlContentSpec.type = this.fContentSpecType[n2][n3];
        xmlContentSpec.value = this.fContentSpecValue[n2][n3];
        xmlContentSpec.otherValue = this.fContentSpecOtherValue[n2][n3];
        return true;
    }
    
    public XMLContentModel getElementContentModel(final int n) throws CMException {
        if (n < 0 || n >= this.fElementDeclCount) {
            return null;
        }
        final int n2 = n >> 8;
        final int n3 = n & 0xFF;
        XMLContentModel childModel = this.fElementDeclContentModelValidator[n2][n3];
        if (childModel != null) {
            return childModel;
        }
        final int n4 = this.fElementDeclType[n2][n3];
        if (n4 == 4) {
            return null;
        }
        final int n5 = this.fElementDeclContentSpecIndex[n2][n3];
        final XMLContentSpec xmlContentSpec = new XMLContentSpec();
        this.getContentSpec(n5, xmlContentSpec);
        if (n4 == 2) {
            final Vector vector = new Vector();
            try {
                final ChildrenList list = new ChildrenList();
                this.contentSpecTree(n5, xmlContentSpec, list);
                childModel = new MixedContentModel(list.qname, list.type, 0, list.length, false, this.isDTD());
            }
            catch (CMException ex) {
                ex.printStackTrace();
            }
        }
        else {
            if (n4 != 3) {
                throw new CMException(8);
            }
            try {
                childModel = this.createChildModel(n5);
            }
            catch (CMException ex2) {
                ex2.printStackTrace();
            }
        }
        return this.fElementDeclContentModelValidator[n2][n3] = childModel;
    }
    
    public boolean getAttributeDecl(final int n, final XMLAttributeDecl xmlAttributeDecl) {
        if (n < 0 || n >= this.fAttributeDeclCount) {
            return false;
        }
        final int n2 = n >> 8;
        final int n3 = n & 0xFF;
        xmlAttributeDecl.name.setValues(this.fAttributeDeclName[n2][n3]);
        if (this.fAttributeDeclType[n2][n3] == -1) {
            xmlAttributeDecl.type = -1;
            xmlAttributeDecl.list = false;
        }
        else {
            xmlAttributeDecl.type = (this.fAttributeDeclType[n2][n3] & 0xFFFF7FFF);
            xmlAttributeDecl.list = ((this.fAttributeDeclType[n2][n3] & 0x8000) != 0x0);
        }
        xmlAttributeDecl.datatypeValidator = this.fAttributeDeclDatatypeValidator[n2][n3];
        xmlAttributeDecl.enumeration = this.fAttributeDeclEnumeration[n2][n3];
        xmlAttributeDecl.defaultType = this.fAttributeDeclDefaultType[n2][n3];
        xmlAttributeDecl.defaultValue = this.fAttributeDeclDefaultValue[n2][n3];
        return true;
    }
    
    protected void setGrammarDocument(final Document fGrammarDocument) {
        this.fGrammarDocument = fGrammarDocument;
    }
    
    protected int createElementDecl() {
        final int n = this.fElementDeclCount >> 8;
        final int n2 = this.fElementDeclCount & 0xFF;
        this.ensureElementDeclCapacity(n);
        this.fElementDeclName[n][n2] = new QName();
        this.fElementDeclType[n][n2] = -1;
        this.fElementDeclDatatypeValidator[n][n2] = null;
        this.fElementDeclContentSpecIndex[n][n2] = -1;
        this.fElementDeclContentModelValidator[n][n2] = null;
        this.fElementDeclFirstAttributeDeclIndex[n][n2] = -1;
        this.fElementDeclLastAttributeDeclIndex[n][n2] = -1;
        return this.fElementDeclCount++;
    }
    
    protected void setElementDecl(final int n, final XMLElementDecl xmlElementDecl) {
        if (n < 0 || n >= this.fElementDeclCount) {
            return;
        }
        final int n2 = n >> 8;
        final int n3 = n & 0xFF;
        this.fElementDeclName[n2][n3].setValues(xmlElementDecl.name);
        this.fElementDeclType[n2][n3] = xmlElementDecl.type;
        if (xmlElementDecl.list) {
            final int[] array = this.fElementDeclType[n2];
            final int n4 = n3;
            array[n4] |= 0x8000;
        }
        this.fElementDeclDatatypeValidator[n2][n3] = xmlElementDecl.datatypeValidator;
        this.fElementDeclContentSpecIndex[n2][n3] = xmlElementDecl.contentSpecIndex;
        final int size = xmlElementDecl.unique.size();
        if (size > 0) {
            if (this.fElementDeclUnique[n2][n3] == null) {
                this.fElementDeclUnique[n2][n3] = (Vector)xmlElementDecl.unique.clone();
            }
            else {
                this.fElementDeclUnique[n2][n3].removeAllElements();
                for (int i = 0; i < size; ++i) {
                    this.fElementDeclUnique[n2][n3].addElement(xmlElementDecl.unique.elementAt(i));
                }
            }
        }
        final int size2 = xmlElementDecl.key.size();
        if (size2 > 0) {
            if (this.fElementDeclKey[n2][n3] == null) {
                this.fElementDeclKey[n2][n3] = (Vector)xmlElementDecl.key.clone();
            }
            else {
                this.fElementDeclKey[n2][n3].removeAllElements();
                for (int j = 0; j < size2; ++j) {
                    this.fElementDeclKey[n2][n3].addElement(xmlElementDecl.key.elementAt(j));
                }
            }
        }
        final int size3 = xmlElementDecl.keyRef.size();
        if (size3 > 0) {
            if (this.fElementDeclKeyRef[n2][n3] == null) {
                this.fElementDeclKeyRef[n2][n3] = (Vector)xmlElementDecl.keyRef.clone();
            }
            else {
                this.fElementDeclKeyRef[n2][n3].removeAllElements();
                for (int k = 0; k < size3; ++k) {
                    this.fElementDeclKeyRef[n2][n3].addElement(xmlElementDecl.keyRef.elementAt(k));
                }
            }
        }
        this.putElementNameMapping(xmlElementDecl.name, xmlElementDecl.enclosingScope, n);
    }
    
    protected void putElementNameMapping(final QName qName, final int n, final int n2) {
        this.fElementNameAndScopeToElementDeclIndexMapping.put(qName.uri, qName.localpart, n, n2);
    }
    
    protected void setFirstAttributeDeclIndex(final int n, final int n2) {
        if (n < 0 || n >= this.fElementDeclCount) {
            return;
        }
        this.fElementDeclFirstAttributeDeclIndex[n >> 8][n & 0xFF] = n2;
    }
    
    protected int createContentSpec() {
        final int n = this.fContentSpecCount >> 8;
        final int n2 = this.fContentSpecCount & 0xFF;
        this.ensureContentSpecCapacity(n);
        this.fContentSpecType[n][n2] = -1;
        this.fContentSpecValue[n][n2] = -1;
        this.fContentSpecOtherValue[n][n2] = -1;
        return this.fContentSpecCount++;
    }
    
    protected void setContentSpec(final int n, final XMLContentSpec xmlContentSpec) {
        final int n2 = n >> 8;
        final int n3 = n & 0xFF;
        this.fContentSpecType[n2][n3] = xmlContentSpec.type;
        this.fContentSpecValue[n2][n3] = xmlContentSpec.value;
        this.fContentSpecOtherValue[n2][n3] = xmlContentSpec.otherValue;
    }
    
    protected int createAttributeDecl() {
        final int n = this.fAttributeDeclCount >> 8;
        final int n2 = this.fAttributeDeclCount & 0xFF;
        this.ensureAttributeDeclCapacity(n);
        this.fAttributeDeclName[n][n2] = new QName();
        this.fAttributeDeclType[n][n2] = -1;
        this.fAttributeDeclDatatypeValidator[n][n2] = null;
        this.fAttributeDeclEnumeration[n][n2] = -1;
        this.fAttributeDeclDefaultType[n][n2] = 0;
        this.fAttributeDeclDefaultValue[n][n2] = null;
        this.fAttributeDeclNextAttributeDeclIndex[n][n2] = -1;
        return this.fAttributeDeclCount++;
    }
    
    protected void setAttributeDecl(final int n, final int n2, final XMLAttributeDecl xmlAttributeDecl) {
        final int n3 = n2 >> 8;
        final int n4 = n2 & 0xFF;
        this.fAttributeDeclName[n3][n4].setValues(xmlAttributeDecl.name);
        this.fAttributeDeclType[n3][n4] = xmlAttributeDecl.type;
        if (xmlAttributeDecl.list) {
            final int[] array = this.fAttributeDeclType[n3];
            final int n5 = n4;
            array[n5] |= 0x8000;
        }
        this.fAttributeDeclEnumeration[n3][n4] = xmlAttributeDecl.enumeration;
        this.fAttributeDeclDefaultType[n3][n4] = xmlAttributeDecl.defaultType;
        this.fAttributeDeclDatatypeValidator[n3][n4] = xmlAttributeDecl.datatypeValidator;
        this.fAttributeDeclDefaultValue[n3][n4] = xmlAttributeDecl.defaultValue;
        final int n6 = n >> 8;
        final int n7 = n & 0xFF;
        int n8;
        for (n8 = this.fElementDeclFirstAttributeDeclIndex[n6][n7]; n8 != -1 && n8 != n2; n8 = this.fAttributeDeclNextAttributeDeclIndex[n8 >> 8][n8 & 0xFF]) {}
        if (n8 == -1) {
            if (this.fElementDeclFirstAttributeDeclIndex[n6][n7] == -1) {
                this.fElementDeclFirstAttributeDeclIndex[n6][n7] = n2;
            }
            else {
                final int n9 = this.fElementDeclLastAttributeDeclIndex[n6][n7];
                this.fAttributeDeclNextAttributeDeclIndex[n9 >> 8][n9 & 0xFF] = n2;
            }
            this.fElementDeclLastAttributeDeclIndex[n6][n7] = n2;
        }
    }
    
    protected boolean isDTD() {
        return false;
    }
    
    public void printElements(final StringPool stringPool) {
        int n = 0;
        final XMLElementDecl xmlElementDecl = new XMLElementDecl();
        while (this.getElementDecl(n++, xmlElementDecl)) {
            System.out.println("element decl: " + xmlElementDecl.name + ", " + stringPool.toString(xmlElementDecl.name.rawname) + ", " + XMLContentSpec.toString(this, stringPool, xmlElementDecl.contentSpecIndex));
        }
    }
    
    public void printAttributes(final int n) {
        int i = this.getFirstAttributeDeclIndex(n);
        System.out.print(n);
        System.out.print(" [");
        while (i != -1) {
            System.out.print(' ');
            System.out.print(i);
            this.printAttribute(i);
            i = this.getNextAttributeDeclIndex(i);
            if (i != -1) {
                System.out.print(",");
            }
        }
        System.out.println(" ]");
    }
    
    private void printAttribute(final int n) {
        final XMLAttributeDecl xmlAttributeDecl = new XMLAttributeDecl();
        if (this.getAttributeDecl(n, xmlAttributeDecl)) {
            System.out.print(" { ");
            System.out.print(xmlAttributeDecl.name.localpart);
            System.out.print(" }");
        }
    }
    
    private final XMLContentModel createChildModel(final int n) throws CMException {
        final XMLContentSpec xmlContentSpec = new XMLContentSpec();
        this.getContentSpec(n, xmlContentSpec);
        if ((xmlContentSpec.type & 0xF) != 0x6 && (xmlContentSpec.type & 0xF) != 0x7) {
            if ((xmlContentSpec.type & 0xF) != 0x8) {
                if (xmlContentSpec.type == 0) {
                    if (xmlContentSpec.value == -1 && xmlContentSpec.otherValue == -1) {
                        throw new CMException(11);
                    }
                    this.fQName1.setValues(-1, xmlContentSpec.value, xmlContentSpec.value, xmlContentSpec.otherValue);
                    return new SimpleContentModel(this.fQName1, null, xmlContentSpec.type, this.isDTD());
                }
                else if (xmlContentSpec.type == 4 || xmlContentSpec.type == 5) {
                    final XMLContentSpec xmlContentSpec2 = new XMLContentSpec();
                    final XMLContentSpec xmlContentSpec3 = new XMLContentSpec();
                    this.getContentSpec(xmlContentSpec.value, xmlContentSpec2);
                    this.getContentSpec(xmlContentSpec.otherValue, xmlContentSpec3);
                    if (xmlContentSpec2.type == 0 && xmlContentSpec3.type == 0) {
                        this.fQName1.setValues(-1, xmlContentSpec2.value, xmlContentSpec2.value, xmlContentSpec2.otherValue);
                        this.fQName2.setValues(-1, xmlContentSpec3.value, xmlContentSpec3.value, xmlContentSpec3.otherValue);
                        return new SimpleContentModel(this.fQName1, this.fQName2, xmlContentSpec.type, this.isDTD());
                    }
                }
                else {
                    if (xmlContentSpec.type != 1 && xmlContentSpec.type != 2 && xmlContentSpec.type != 3) {
                        throw new CMException(8);
                    }
                    final XMLContentSpec xmlContentSpec4 = new XMLContentSpec();
                    this.getContentSpec(xmlContentSpec.value, xmlContentSpec4);
                    if (xmlContentSpec4.type == 0) {
                        this.fQName1.setValues(-1, xmlContentSpec4.value, xmlContentSpec4.value, xmlContentSpec4.otherValue);
                        return new SimpleContentModel(this.fQName1, null, xmlContentSpec.type, this.isDTD());
                    }
                }
            }
        }
        this.fLeafCount = 0;
        return new DFAContentModel(this.buildSyntaxTree(n, xmlContentSpec), this.fLeafCount, this.isDTD());
    }
    
    private void printSyntaxTree(final CMNode cmNode) {
        System.out.println("CMNode : " + cmNode.type());
        if (cmNode.type() == 0) {
            System.out.println("     Leaf: " + ((CMLeaf)cmNode).getElement());
            return;
        }
        if (cmNode instanceof CMBinOp) {
            this.printSyntaxTree(((CMBinOp)cmNode).getLeft());
            this.printSyntaxTree(((CMBinOp)cmNode).getRight());
        }
        if (cmNode instanceof CMUniOp) {
            this.printSyntaxTree(((CMUniOp)cmNode).getChild());
        }
    }
    
    private int countLeaves(final int n) {
        return this.countLeaves(n, new XMLContentSpec());
    }
    
    private int countLeaves(final int n, final XMLContentSpec xmlContentSpec) {
        if (n == -1) {
            return 0;
        }
        this.getContentSpec(n, xmlContentSpec);
        if (xmlContentSpec.type == 0) {
            return 1;
        }
        return this.countLeaves(xmlContentSpec.value, xmlContentSpec) + this.countLeaves(xmlContentSpec.otherValue, xmlContentSpec);
    }
    
    private final CMNode buildSyntaxTree(final int n, final XMLContentSpec xmlContentSpec) throws CMException {
        this.getContentSpec(n, xmlContentSpec);
        CMNode cmNode;
        if ((xmlContentSpec.type & 0xF) == 0x6) {
            cmNode = new CMAny(xmlContentSpec.type, xmlContentSpec.otherValue, this.fLeafCount++);
        }
        else if ((xmlContentSpec.type & 0xF) == 0x7) {
            cmNode = new CMAny(xmlContentSpec.type, xmlContentSpec.otherValue, this.fLeafCount++);
        }
        else if ((xmlContentSpec.type & 0xF) == 0x8) {
            cmNode = new CMAny(xmlContentSpec.type, 0, this.fLeafCount++);
        }
        else if (xmlContentSpec.type == 0) {
            this.fQName1.setValues(-1, xmlContentSpec.value, xmlContentSpec.value, xmlContentSpec.otherValue);
            cmNode = new CMLeaf(this.fQName1, this.fLeafCount++);
        }
        else {
            final int value = xmlContentSpec.value;
            final int otherValue = xmlContentSpec.otherValue;
            if (xmlContentSpec.type == 4 || xmlContentSpec.type == 5) {
                cmNode = new CMBinOp(xmlContentSpec.type, this.buildSyntaxTree(value, xmlContentSpec), this.buildSyntaxTree(otherValue, xmlContentSpec));
            }
            else {
                if (xmlContentSpec.type != 2 && xmlContentSpec.type != 1 && xmlContentSpec.type != 3) {
                    throw new CMException(8);
                }
                cmNode = new CMUniOp(xmlContentSpec.type, this.buildSyntaxTree(value, xmlContentSpec));
            }
        }
        return cmNode;
    }
    
    private void contentSpecTree(final int n, final XMLContentSpec xmlContentSpec, final ChildrenList list) throws CMException {
        this.getContentSpec(n, xmlContentSpec);
        if (xmlContentSpec.type == 0 || (xmlContentSpec.type & 0xF) == 0x6 || (xmlContentSpec.type & 0xF) == 0x8 || (xmlContentSpec.type & 0xF) == 0x7) {
            if (list.length == list.qname.length) {
                final QName[] qname = new QName[list.length * 2];
                System.arraycopy(list.qname, 0, qname, 0, list.length);
                list.qname = qname;
                final int[] type = new int[list.length * 2];
                System.arraycopy(list.type, 0, type, 0, list.length);
                list.type = type;
            }
            list.qname[list.length] = new QName(-1, xmlContentSpec.value, xmlContentSpec.value, xmlContentSpec.otherValue);
            list.type[list.length] = xmlContentSpec.type;
            ++list.length;
            return;
        }
        final int value = xmlContentSpec.value;
        final int otherValue = xmlContentSpec.otherValue;
        if (xmlContentSpec.type == 4 || xmlContentSpec.type == 5) {
            this.contentSpecTree(value, xmlContentSpec, list);
            this.contentSpecTree(otherValue, xmlContentSpec, list);
            return;
        }
        if (xmlContentSpec.type == 1 || xmlContentSpec.type == 2 || xmlContentSpec.type == 3) {
            this.contentSpecTree(value, xmlContentSpec, list);
            return;
        }
        throw new CMException(8);
    }
    
    private boolean ensureElementDeclCapacity(final int n) {
        try {
            return this.fElementDeclName[n][0] == null;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            this.fElementDeclName = this.resize(this.fElementDeclName, this.fElementDeclName.length * 2);
            this.fElementDeclType = this.resize(this.fElementDeclType, this.fElementDeclType.length * 2);
            this.fElementDeclDatatypeValidator = this.resize(this.fElementDeclDatatypeValidator, this.fElementDeclDatatypeValidator.length * 2);
            this.fElementDeclContentSpecIndex = this.resize(this.fElementDeclContentSpecIndex, this.fElementDeclContentSpecIndex.length * 2);
            this.fElementDeclContentModelValidator = this.resize(this.fElementDeclContentModelValidator, this.fElementDeclContentModelValidator.length * 2);
            this.fElementDeclFirstAttributeDeclIndex = this.resize(this.fElementDeclFirstAttributeDeclIndex, this.fElementDeclFirstAttributeDeclIndex.length * 2);
            this.fElementDeclLastAttributeDeclIndex = this.resize(this.fElementDeclLastAttributeDeclIndex, this.fElementDeclLastAttributeDeclIndex.length * 2);
            this.fElementDeclUnique = this.resize(this.fElementDeclUnique, this.fElementDeclUnique.length * 2);
            this.fElementDeclKey = this.resize(this.fElementDeclKey, this.fElementDeclKey.length * 2);
            this.fElementDeclKeyRef = this.resize(this.fElementDeclKeyRef, this.fElementDeclKeyRef.length * 2);
        }
        catch (NullPointerException ex2) {}
        this.fElementDeclName[n] = new QName[256];
        this.fElementDeclType[n] = new int[256];
        this.fElementDeclDatatypeValidator[n] = new DatatypeValidator[256];
        this.fElementDeclContentSpecIndex[n] = new int[256];
        this.fElementDeclContentModelValidator[n] = new XMLContentModel[256];
        this.fElementDeclFirstAttributeDeclIndex[n] = new int[256];
        this.fElementDeclLastAttributeDeclIndex[n] = new int[256];
        this.fElementDeclUnique[n] = new Vector[256];
        this.fElementDeclKey[n] = new Vector[256];
        this.fElementDeclKeyRef[n] = new Vector[256];
        return true;
    }
    
    private boolean ensureContentSpecCapacity(final int n) {
        try {
            return this.fContentSpecType[n][0] == 0;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            this.fContentSpecType = this.resize(this.fContentSpecType, this.fContentSpecType.length * 2);
            this.fContentSpecValue = this.resize(this.fContentSpecValue, this.fContentSpecValue.length * 2);
            this.fContentSpecOtherValue = this.resize(this.fContentSpecOtherValue, this.fContentSpecOtherValue.length * 2);
        }
        catch (NullPointerException ex2) {}
        this.fContentSpecType[n] = new int[256];
        this.fContentSpecValue[n] = new int[256];
        this.fContentSpecOtherValue[n] = new int[256];
        return true;
    }
    
    private boolean ensureAttributeDeclCapacity(final int n) {
        try {
            return this.fAttributeDeclName[n][0] == null;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            this.fAttributeDeclName = this.resize(this.fAttributeDeclName, this.fAttributeDeclName.length * 2);
            this.fAttributeDeclType = this.resize(this.fAttributeDeclType, this.fAttributeDeclType.length * 2);
            this.fAttributeDeclEnumeration = this.resize(this.fAttributeDeclEnumeration, this.fAttributeDeclEnumeration.length * 2);
            this.fAttributeDeclDefaultType = this.resize(this.fAttributeDeclDefaultType, this.fAttributeDeclDefaultType.length * 2);
            this.fAttributeDeclDatatypeValidator = this.resize(this.fAttributeDeclDatatypeValidator, this.fAttributeDeclDatatypeValidator.length * 2);
            this.fAttributeDeclDefaultValue = this.resize(this.fAttributeDeclDefaultValue, this.fAttributeDeclDefaultValue.length * 2);
            this.fAttributeDeclNextAttributeDeclIndex = this.resize(this.fAttributeDeclNextAttributeDeclIndex, this.fAttributeDeclNextAttributeDeclIndex.length * 2);
        }
        catch (NullPointerException ex2) {}
        this.fAttributeDeclName[n] = new QName[256];
        this.fAttributeDeclType[n] = new int[256];
        this.fAttributeDeclEnumeration[n] = new int[256];
        this.fAttributeDeclDefaultType[n] = new int[256];
        this.fAttributeDeclDatatypeValidator[n] = new DatatypeValidator[256];
        this.fAttributeDeclDefaultValue[n] = new String[256];
        this.fAttributeDeclNextAttributeDeclIndex[n] = new int[256];
        return true;
    }
    
    private int[][] resize(final int[][] array, final int n) {
        final int[][] array2 = new int[n][];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
    
    private DatatypeValidator[][] resize(final DatatypeValidator[][] array, final int n) {
        final DatatypeValidator[][] array2 = new DatatypeValidator[n][];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
    
    private XMLContentModel[][] resize(final XMLContentModel[][] array, final int n) {
        final XMLContentModel[][] array2 = new XMLContentModel[n][];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
    
    private QName[][] resize(final QName[][] array, final int n) {
        final QName[][] array2 = new QName[n][];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
    
    private String[][] resize(final String[][] array, final int n) {
        final String[][] array2 = new String[n][];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
    
    private Vector[][] resize(final Vector[][] array, final int n) {
        final Vector[][] array2 = new Vector[n][];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
    
    static class ChildrenList
    {
        public int length;
        public QName[] qname;
        public int[] type;
        
        ChildrenList() {
            this.length = 0;
            this.qname = new QName[2];
            this.type = new int[2];
        }
    }
}
