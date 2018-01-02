// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.dtd;

import java.util.Enumeration;
import java.util.StringTokenizer;
import org.apache.xerces.framework.XMLAttrList;
import org.apache.xerces.framework.XMLContentSpecNode;
import java.util.Hashtable;
import org.apache.xerces.framework.XMLErrorReporter;
import org.apache.xerces.utils.StringPool;

public final class ElementDeclPool
{
    private static final int CHUNK_SHIFT = 5;
    private static final int CHUNK_SIZE = 32;
    private static final int CHUNK_MASK = 31;
    private static final int INITIAL_CHUNK_COUNT = 32;
    private StringPool fStringPool;
    private XMLErrorReporter fErrorReporter;
    private int fElementCount;
    private int[][] fElementType;
    private byte[][] fElementDeclIsExternal;
    private int[][] fContentSpecType;
    private int[][] fContentSpec;
    private XMLContentModel[][] fContentModel;
    private int[][] fAttlistHead;
    private int[][] fAttlistTail;
    private int fNodeCount;
    private byte[][] fNodeType;
    private int[][] fNodeValue;
    private int fAttDefCount;
    private int[][] fAttName;
    private int[][] fAttType;
    private int[][] fEnumeration;
    private int[][] fAttDefaultType;
    private int[][] fAttValue;
    private byte[][] fAttDefIsExternal;
    private int[][] fNextAttDef;
    private static final int INITIAL_BUCKET_SIZE = 4;
    private static final int HASHTABLE_SIZE = 128;
    private int[][] fElementTypeHashtable;
    private int fIDSymbol;
    private int fNotationSymbol;
    private int fMIXEDSymbol;
    private int fCHILDRENSymbol;
    private Hashtable fIdDefs;
    private Hashtable fIdRefs;
    private Object fNullValue;
    
    public ElementDeclPool(final StringPool fStringPool, final XMLErrorReporter fErrorReporter) {
        this.fElementType = new int[32][];
        this.fElementDeclIsExternal = new byte[32][];
        this.fContentSpecType = new int[32][];
        this.fContentSpec = new int[32][];
        this.fContentModel = new XMLContentModel[32][];
        this.fAttlistHead = new int[32][];
        this.fAttlistTail = new int[32][];
        this.fNodeType = new byte[32][];
        this.fNodeValue = new int[32][];
        this.fAttName = new int[32][];
        this.fAttType = new int[32][];
        this.fEnumeration = new int[32][];
        this.fAttDefaultType = new int[32][];
        this.fAttValue = new int[32][];
        this.fAttDefIsExternal = new byte[32][];
        this.fNextAttDef = new int[32][];
        this.fElementTypeHashtable = new int[128][];
        this.fIDSymbol = -1;
        this.fNotationSymbol = -1;
        this.fMIXEDSymbol = -1;
        this.fCHILDRENSymbol = -1;
        this.fStringPool = fStringPool;
        this.fErrorReporter = fErrorReporter;
    }
    
    public void reset(final StringPool fStringPool) {
        this.fStringPool = fStringPool;
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < this.fElementCount; ++i) {
            this.fContentModel[n][n2] = null;
            if (++n2 == 32) {
                ++n;
                n2 = 0;
            }
        }
        for (int j = 0; j < 128; ++j) {
            this.fElementTypeHashtable[j] = null;
        }
        this.fElementCount = 0;
        this.fNodeCount = 0;
        this.fAttDefCount = 0;
        this.fIDSymbol = -1;
        this.fNotationSymbol = -1;
        this.fMIXEDSymbol = -1;
        this.fCHILDRENSymbol = -1;
        if (this.fIdDefs != null) {
            this.fIdDefs.clear();
        }
        if (this.fIdRefs != null) {
            this.fIdRefs.clear();
        }
    }
    
    private boolean ensureElementCapacity(final int n) {
        try {
            return this.fElementType[n][0] == 0;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            final byte[][] fElementDeclIsExternal = new byte[n * 2][];
            System.arraycopy(this.fElementDeclIsExternal, 0, fElementDeclIsExternal, 0, n);
            this.fElementDeclIsExternal = fElementDeclIsExternal;
            final int[][] fElementType = new int[n * 2][];
            System.arraycopy(this.fElementType, 0, fElementType, 0, n);
            this.fElementType = fElementType;
            final int[][] fContentSpecType = new int[n * 2][];
            System.arraycopy(this.fContentSpecType, 0, fContentSpecType, 0, n);
            this.fContentSpecType = fContentSpecType;
            final int[][] fContentSpec = new int[n * 2][];
            System.arraycopy(this.fContentSpec, 0, fContentSpec, 0, n);
            this.fContentSpec = fContentSpec;
            final XMLContentModel[][] fContentModel = new XMLContentModel[n * 2][];
            System.arraycopy(this.fContentModel, 0, fContentModel, 0, n);
            this.fContentModel = fContentModel;
            final int[][] fAttlistHead = new int[n * 2][];
            System.arraycopy(this.fAttlistHead, 0, fAttlistHead, 0, n);
            this.fAttlistHead = fAttlistHead;
            final int[][] fAttlistTail = new int[n * 2][];
            System.arraycopy(this.fAttlistTail, 0, fAttlistTail, 0, n);
            this.fAttlistTail = fAttlistTail;
        }
        catch (NullPointerException ex2) {}
        this.fElementType[n] = new int[32];
        this.fElementDeclIsExternal[n] = new byte[32];
        this.fContentSpecType[n] = new int[32];
        this.fContentSpec[n] = new int[32];
        this.fContentModel[n] = new XMLContentModel[32];
        this.fAttlistHead[n] = new int[32];
        this.fAttlistTail[n] = new int[32];
        return true;
    }
    
    public int getElement(int fullNameForQName) {
        fullNameForQName = this.fStringPool.getFullNameForQName(fullNameForQName);
        final int[] array = this.fElementTypeHashtable[fullNameForQName % 128];
        if (array != null) {
            int n = 1;
            for (int i = 0; i < array[0]; ++i) {
                if (array[n] == fullNameForQName) {
                    return array[n + 1];
                }
                n += 2;
            }
        }
        return -1;
    }
    
    public int addElement(final int n) {
        final int fullNameForQName = this.fStringPool.getFullNameForQName(n);
        final int n2 = fullNameForQName % 128;
        int[] array = this.fElementTypeHashtable[n2];
        if (array != null) {
            int n3 = 1;
            for (int i = 0; i < array[0]; ++i) {
                if (array[n3] == fullNameForQName) {
                    return array[n3 + 1];
                }
                n3 += 2;
            }
        }
        final int n4 = this.fElementCount >> 5;
        final int n5 = this.fElementCount & 0x1F;
        this.ensureElementCapacity(n4);
        this.fElementType[n4][n5] = n;
        this.fElementDeclIsExternal[n4][n5] = 0;
        this.fContentSpecType[n4][n5] = -1;
        this.fContentSpec[n4][n5] = -1;
        this.fContentModel[n4][n5] = null;
        this.fAttlistHead[n4][n5] = -1;
        this.fAttlistTail[n4][n5] = -1;
        if (array == null) {
            final int[] array2 = new int[9];
            array2[array2[0] = 1] = fullNameForQName;
            array2[2] = this.fElementCount;
            this.fElementTypeHashtable[n2] = array2;
        }
        else {
            int n6 = array[0];
            int n7 = 1 + n6 * 2;
            if (n7 == array.length) {
                final int[] array3 = new int[1 + (n6 + 4) * 2];
                System.arraycopy(array, 0, array3, 0, n7);
                array = array3;
                this.fElementTypeHashtable[n2] = array;
            }
            array[n7++] = fullNameForQName;
            array[n7++] = this.fElementCount;
            array[0] = ++n6;
        }
        return this.fElementCount++;
    }
    
    public int addElementDecl(final int n, final int n2, final int n3, final boolean b) {
        final int fullNameForQName = this.fStringPool.getFullNameForQName(n);
        final int n4 = fullNameForQName % 128;
        int[] array = this.fElementTypeHashtable[n4];
        if (array != null) {
            int n5 = 1;
            int i = 0;
            while (i < array[0]) {
                if (array[n5] == fullNameForQName) {
                    final int n6 = array[n5 + 1];
                    final int n7 = n6 >> 5;
                    final int n8 = n6 & 0x1F;
                    if (this.fContentSpecType[n7][n8] != -1) {
                        return -1;
                    }
                    this.fElementDeclIsExternal[n7][n8] = (byte)(b ? 1 : 0);
                    this.fContentSpecType[n7][n8] = n2;
                    this.fContentSpec[n7][n8] = n3;
                    this.fContentModel[n7][n8] = null;
                    return n6;
                }
                else {
                    n5 += 2;
                    ++i;
                }
            }
        }
        final int n9 = this.fElementCount >> 5;
        final int n10 = this.fElementCount & 0x1F;
        this.ensureElementCapacity(n9);
        this.fElementType[n9][n10] = n;
        this.fElementDeclIsExternal[n9][n10] = (byte)(b ? 1 : 0);
        this.fContentSpecType[n9][n10] = n2;
        this.fContentSpec[n9][n10] = n3;
        this.fContentModel[n9][n10] = null;
        this.fAttlistHead[n9][n10] = -1;
        this.fAttlistTail[n9][n10] = -1;
        if (array == null) {
            final int[] array2 = new int[9];
            array2[array2[0] = 1] = fullNameForQName;
            array2[2] = this.fElementCount;
            this.fElementTypeHashtable[n4] = array2;
        }
        else {
            int n11 = array[0];
            int n12 = 1 + n11 * 2;
            if (n12 == array.length) {
                final int[] array3 = new int[1 + (n11 + 4) * 2];
                System.arraycopy(array, 0, array3, 0, n12);
                array = array3;
                this.fElementTypeHashtable[n4] = array;
            }
            array[n12++] = fullNameForQName;
            array[n12++] = this.fElementCount;
            array[0] = ++n11;
        }
        return this.fElementCount++;
    }
    
    public int getElementType(final int n) {
        if (n < 0 || n >= this.fElementCount) {
            return -1;
        }
        return this.fElementType[n >> 5][n & 0x1F];
    }
    
    public boolean getElementDeclIsExternal(final int n) {
        return n >= 0 && n < this.fElementCount && this.fElementDeclIsExternal[n >> 5][n & 0x1F] != 0;
    }
    
    public int getContentSpecType(final int n) {
        if (n < 0 || n >= this.fElementCount) {
            return -1;
        }
        return this.fContentSpecType[n >> 5][n & 0x1F];
    }
    
    public int getContentSpec(final int n) {
        if (n < 0 || n >= this.fElementCount) {
            return -1;
        }
        return this.fContentSpec[n >> 5][n & 0x1F];
    }
    
    public void setContentSpec(final int n, final int n2) {
        if (n < 0 || n >= this.fElementCount) {
            return;
        }
        this.fContentSpec[n >> 5][n & 0x1F] = n2;
    }
    
    public String getContentSpecAsString(final int n) {
        if (n < 0 || n >= this.fElementCount) {
            return null;
        }
        final int n2 = n >> 5;
        final int n3 = n & 0x1F;
        final int n4 = this.fContentSpecType[n2][n3];
        if (this.fMIXEDSymbol == -1) {
            this.fMIXEDSymbol = this.fStringPool.addSymbol("MIXED");
            this.fCHILDRENSymbol = this.fStringPool.addSymbol("CHILDREN");
        }
        if (n4 == this.fMIXEDSymbol || n4 == this.fCHILDRENSymbol) {
            return this.getContentSpecNodeAsString(this.fContentSpec[n2][n3]);
        }
        return this.fStringPool.toString(n4);
    }
    
    public XMLContentModel getContentModel(final int n) {
        if (n < 0 || n >= this.fElementCount) {
            return null;
        }
        return this.fContentModel[n >> 5][n & 0x1F];
    }
    
    public void setContentModel(final int n, final XMLContentModel xmlContentModel) {
        if (n < 0 || n >= this.fElementCount) {
            return;
        }
        this.fContentModel[n >> 5][n & 0x1F] = xmlContentModel;
    }
    
    private boolean ensureNodeCapacity(final int n) {
        try {
            return this.fNodeType[n][0] == 0;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            final byte[][] fNodeType = new byte[n * 2][];
            System.arraycopy(this.fNodeType, 0, fNodeType, 0, n);
            this.fNodeType = fNodeType;
            final int[][] fNodeValue = new int[n * 2][];
            System.arraycopy(this.fNodeValue, 0, fNodeValue, 0, n);
            this.fNodeValue = fNodeValue;
        }
        catch (NullPointerException ex2) {}
        this.fNodeType[n] = new byte[32];
        this.fNodeValue[n] = new int[32];
        return true;
    }
    
    private int addUniqueLeafNode(final int n) throws Exception {
        if (n != -1) {
            int n2 = this.fNodeCount >> 5;
            int n3 = this.fNodeCount & 0x1F;
            while (true) {
                if (n3-- == 0) {
                    n3 = 31;
                    --n2;
                }
                if (this.fNodeType[n2][n3] == 0) {
                    final int n4 = this.fNodeValue[n2][n3];
                    if (n4 == -1) {
                        break;
                    }
                    if (n4 == n) {
                        return -1;
                    }
                    continue;
                }
            }
        }
        final int n5 = this.fNodeCount >> 5;
        final int n6 = this.fNodeCount & 0x1F;
        this.ensureNodeCapacity(n5);
        this.fNodeType[n5][n6] = 0;
        this.fNodeValue[n5][n6] = n;
        return this.fNodeCount++;
    }
    
    public int addContentSpecNode(final int n, final int n2, final int n3, final boolean b) throws Exception {
        if (b) {
            return this.addUniqueLeafNode(n2);
        }
        int n4 = this.fNodeCount >> 5;
        int n5 = this.fNodeCount & 0x1F;
        this.ensureNodeCapacity(n4);
        switch (n) {
            case 0:
            case 1:
            case 2:
            case 3: {
                this.fNodeType[n4][n5] = (byte)n;
                this.fNodeValue[n4][n5] = n2;
                return this.fNodeCount++;
            }
            case 4:
            case 5: {
                this.fNodeType[n4][n5] = (byte)n;
                this.fNodeValue[n4][n5] = n2;
                final int n6 = this.fNodeCount++;
                if (++n5 == 32) {
                    ++n4;
                    this.ensureNodeCapacity(n4);
                    n5 = 0;
                }
                this.fNodeType[n4][n5] = (byte)(n | 0x40);
                this.fNodeValue[n4][n5] = n3;
                ++this.fNodeCount;
                return n6;
            }
            default: {
                return -1;
            }
        }
    }
    
    public void getContentSpecNode(final int n, final XMLContentSpecNode xmlContentSpecNode) {
        int n2 = n >> 5;
        int n3 = n & 0x1F;
        xmlContentSpecNode.type = this.fNodeType[n2][n3];
        xmlContentSpecNode.value = this.fNodeValue[n2][n3];
        if (xmlContentSpecNode.type == 4 || xmlContentSpecNode.type == 5) {
            if (++n3 == 32) {
                ++n2;
                n3 = 0;
            }
            xmlContentSpecNode.otherValue = this.fNodeValue[n2][n3];
            return;
        }
        xmlContentSpecNode.otherValue = -1;
    }
    
    private void appendContentSpecNode(final int n, final StringBuffer sb, final boolean b) {
        int n2 = n >> 5;
        int n3 = n & 0x1F;
        final byte b2 = this.fNodeType[n2][n3];
        final int n4 = this.fNodeValue[n2][n3];
        switch (b2) {
            case 0: {
                sb.append((n4 == -1) ? "#PCDATA" : this.fStringPool.toString(n4));
            }
            case 1: {
                this.appendContentSpecNode(n4, sb, false);
                sb.append('?');
            }
            case 2: {
                this.appendContentSpecNode(n4, sb, false);
                sb.append('*');
            }
            case 3: {
                this.appendContentSpecNode(n4, sb, false);
                sb.append('+');
            }
            case 4:
            case 5: {
                if (!b) {
                    sb.append('(');
                }
                this.appendContentSpecNode(n4, sb, this.fNodeType[n4 >> 5][n4 & 0x1F] == b2);
                sb.append((char)((b2 == 4) ? 124 : 44));
                if (++n3 == 32) {
                    ++n2;
                    n3 = 0;
                }
                this.appendContentSpecNode(this.fNodeValue[n2][n3], sb, false);
                if (!b) {
                    sb.append(')');
                }
            }
            default: {}
        }
    }
    
    public String getContentSpecNodeAsString(final int n) {
        final int n2 = n >> 5;
        final int n3 = n & 0x1F;
        final byte b = this.fNodeType[n2][n3];
        final int n4 = this.fNodeValue[n2][n3];
        final StringBuffer sb = new StringBuffer();
        switch (b) {
            case 0: {
                sb.append("(" + ((n4 == -1) ? "#PCDATA" : this.fStringPool.toString(n4)) + ")");
                break;
            }
            case 1: {
                final int n5 = n4 >> 5;
                final int n6 = n4 & 0x1F;
                if (this.fNodeType[n5][n6] == 0) {
                    final int n7 = this.fNodeValue[n5][n6];
                    sb.append("(" + ((n7 == -1) ? "#PCDATA" : this.fStringPool.toString(n7)) + ")?");
                    break;
                }
                this.appendContentSpecNode(n, sb, false);
                break;
            }
            case 2: {
                final int n8 = n4 >> 5;
                final int n9 = n4 & 0x1F;
                if (this.fNodeType[n8][n9] == 0) {
                    final int n10 = this.fNodeValue[n8][n9];
                    sb.append("(" + ((n10 == -1) ? "#PCDATA" : this.fStringPool.toString(n10)) + ")*");
                    break;
                }
                this.appendContentSpecNode(n, sb, false);
                break;
            }
            case 3: {
                final int n11 = n4 >> 5;
                final int n12 = n4 & 0x1F;
                if (this.fNodeType[n11][n12] == 0) {
                    final int n13 = this.fNodeValue[n11][n12];
                    sb.append("(" + ((n13 == -1) ? "#PCDATA" : this.fStringPool.toString(n13)) + ")+");
                    break;
                }
                this.appendContentSpecNode(n, sb, false);
                break;
            }
            case 4:
            case 5: {
                this.appendContentSpecNode(n, sb, false);
                break;
            }
            default: {
                return null;
            }
        }
        return sb.toString();
    }
    
    private boolean ensureAttrCapacity(final int n) {
        try {
            return this.fAttName[n][0] == 0;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            final byte[][] fAttDefIsExternal = new byte[n * 2][];
            System.arraycopy(this.fAttDefIsExternal, 0, fAttDefIsExternal, 0, n);
            this.fAttDefIsExternal = fAttDefIsExternal;
            final int[][] fAttName = new int[n * 2][];
            System.arraycopy(this.fAttName, 0, fAttName, 0, n);
            this.fAttName = fAttName;
            final int[][] fAttType = new int[n * 2][];
            System.arraycopy(this.fAttType, 0, fAttType, 0, n);
            this.fAttType = fAttType;
            final int[][] fEnumeration = new int[n * 2][];
            System.arraycopy(this.fEnumeration, 0, fEnumeration, 0, n);
            this.fEnumeration = fEnumeration;
            final int[][] fAttDefaultType = new int[n * 2][];
            System.arraycopy(this.fAttDefaultType, 0, fAttDefaultType, 0, n);
            this.fAttDefaultType = fAttDefaultType;
            final int[][] fAttValue = new int[n * 2][];
            System.arraycopy(this.fAttValue, 0, fAttValue, 0, n);
            this.fAttValue = fAttValue;
            final int[][] fNextAttDef = new int[n * 2][];
            System.arraycopy(this.fNextAttDef, 0, fNextAttDef, 0, n);
            this.fNextAttDef = fNextAttDef;
        }
        catch (NullPointerException ex2) {}
        this.fAttDefIsExternal[n] = new byte[32];
        this.fAttName[n] = new int[32];
        this.fAttType[n] = new int[32];
        this.fEnumeration[n] = new int[32];
        this.fAttDefaultType[n] = new int[32];
        this.fAttValue[n] = new int[32];
        this.fNextAttDef[n] = new int[32];
        return true;
    }
    
    public int addAttDef(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b, final boolean b2, final boolean b3) throws Exception {
        final int n7 = n >> 5;
        final int n8 = n & 0x1F;
        int i = this.fAttlistHead[n7][n8];
        int n9 = -1;
        int n10 = -1;
        if (b2 && this.fIDSymbol == -1) {
            this.fIDSymbol = this.fStringPool.addSymbol("ID");
            this.fNotationSymbol = this.fStringPool.addSymbol("NOTATION");
        }
        while (i != -1) {
            final int n11 = i >> 5;
            final int n12 = i & 0x1F;
            if (this.fStringPool.equalNames(this.fAttName[n11][n12], n2)) {
                if (b3) {
                    this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 85, 85, new Object[] { this.fStringPool.toString(this.fElementType[n7][n8]), this.fStringPool.toString(n2) }, 0);
                }
                return -1;
            }
            if (b2) {
                if (n3 == this.fIDSymbol && this.fAttType[n11][n12] == this.fIDSymbol) {
                    n9 = this.fAttName[n11][n12];
                }
                if (n3 == this.fNotationSymbol && this.fAttType[n11][n12] == this.fNotationSymbol) {
                    n10 = this.fAttName[n11][n12];
                }
            }
            i = this.fNextAttDef[n11][n12];
        }
        if (b2) {
            if (n9 != -1) {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 86, 81, new Object[] { this.fStringPool.toString(this.fElementType[n7][n8]), this.fStringPool.toString(n9), this.fStringPool.toString(n2) }, 1);
                return -1;
            }
            if (n10 != -1) {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 121, 143, new Object[] { this.fStringPool.toString(this.fElementType[n7][n8]), this.fStringPool.toString(n10), this.fStringPool.toString(n2) }, 1);
                return -1;
            }
        }
        final int n13 = this.fAttDefCount >> 5;
        final int n14 = this.fAttDefCount & 0x1F;
        this.ensureAttrCapacity(n13);
        this.fAttName[n13][n14] = n2;
        this.fAttType[n13][n14] = n3;
        this.fEnumeration[n13][n14] = n4;
        this.fAttDefaultType[n13][n14] = n5;
        this.fAttDefIsExternal[n13][n14] = (byte)(b ? 1 : 0);
        int n15;
        if ((this.fAttValue[n13][n14] = n6) != -1) {
            n15 = this.fAttlistHead[n7][n8];
            this.fAttlistHead[n7][n8] = this.fAttDefCount;
            if (n15 == -1) {
                this.fAttlistTail[n7][n8] = this.fAttDefCount;
            }
        }
        else {
            n15 = this.fAttlistTail[n7][n8];
            this.fAttlistTail[n7][n8] = this.fAttDefCount;
            if (n15 == -1) {
                this.fAttlistHead[n7][n8] = this.fAttDefCount;
            }
            else {
                this.fNextAttDef[n15 >> 5][n15 & 0x1F] = this.fAttDefCount;
                n15 = -1;
            }
        }
        this.fNextAttDef[n13][n14] = n15;
        return this.fAttDefCount++;
    }
    
    public int getAttDef(final int n, final int n2) {
        int n3 = 0;
        int n4 = 0;
        for (int i = 0; i < this.fElementCount; ++i) {
            if (this.fStringPool.equalNames(this.fElementType[n3][n4], n)) {
                int n5;
                int n6;
                for (int j = this.fAttlistHead[n3][n4]; j != -1; j = this.fNextAttDef[n5][n6]) {
                    n5 = j >> 5;
                    n6 = (j & 0x1F);
                    if (this.fStringPool.equalNames(this.fAttName[n5][n6], n2)) {
                        return j;
                    }
                }
                return -1;
            }
            if (++n4 == 32) {
                ++n3;
                n4 = 0;
            }
        }
        return -1;
    }
    
    public boolean copyAtts(final int n, final int n2) {
        int n3 = 0;
        int n4 = 0;
        try {
            for (int i = 0; i < this.fElementCount; ++i) {
                if (this.fElementType[n3][n4] == n) {
                    for (int j = this.fAttlistHead[n3][n4]; j != -1; j = this.fNextAttDef[j >> 5][j & 0x1F]) {
                        this.addAttDef(this.getElement(n2), this.getAttName(j), this.getAttType(j), this.getEnumeration(j), this.getAttDefaultType(j), this.getAttValue(j), this.getAttDefIsExternal(j), true, false);
                    }
                    return true;
                }
                if (++n4 == 32) {
                    ++n3;
                    n4 = 0;
                }
            }
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public boolean getAttDefIsExternal(final int n) {
        return this.fAttDefIsExternal[n >> 5][n & 0x1F] != 0;
    }
    
    public int getAttName(final int n) {
        return this.fAttName[n >> 5][n & 0x1F];
    }
    
    public int getAttValue(final int n) {
        return this.fAttValue[n >> 5][n & 0x1F];
    }
    
    public int getAttType(final int n) {
        return this.fAttType[n >> 5][n & 0x1F];
    }
    
    public int getAttDefaultType(final int n) {
        return this.fAttDefaultType[n >> 5][n & 0x1F];
    }
    
    public int getEnumeration(final int n) {
        return this.fEnumeration[n >> 5][n & 0x1F];
    }
    
    public int addDefaultAttributes(final int n, final XMLAttrList list, int startAttrList, final boolean b, final boolean b2) throws Exception {
        final int n2 = n >> 5;
        final int n3 = n & 0x1F;
        int i = this.fAttlistHead[n2][n3];
        final int n4 = startAttrList;
        int n5 = -1;
        while (i != -1) {
            final int n6 = i >> 5;
            final int n7 = i & 0x1F;
            final int n8 = this.fAttName[n6][n7];
            final int n9 = this.fAttType[n6][n7];
            final int n10 = this.fAttDefaultType[n6][n7];
            final int n11 = this.fAttValue[n6][n7];
            boolean b3 = false;
            final boolean b4 = n10 == this.fStringPool.addSymbol("#REQUIRED");
            this.fStringPool.addSymbol("#IMPLIED");
            if (n4 != -1) {
                final boolean b5 = n9 == this.fStringPool.addSymbol("CDATA");
                final boolean b6 = n10 == this.fStringPool.addSymbol("#FIXED");
                if (!b5 || b4 || n11 != -1) {
                    for (int n12 = list.getFirstAttr(n4); n12 != -1 && (n5 == -1 || n12 <= n5); n12 = list.getNextAttr(n12)) {
                        if (this.fStringPool.equalNames(list.getAttrName(n12), n8)) {
                            if (b && b6) {
                                final int attValue = list.getAttValue(n12);
                                if (attValue != n11 && !this.fStringPool.toString(attValue).equals(this.fStringPool.toString(n11))) {
                                    this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 93, 87, new Object[] { this.fStringPool.toString(this.fElementType[n2][n3]), this.fStringPool.toString(n8), this.fStringPool.toString(attValue), this.fStringPool.toString(n11) }, 1);
                                }
                            }
                            b3 = true;
                            break;
                        }
                    }
                }
            }
            if (!b3) {
                if (b4) {
                    if (b) {
                        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 94, 88, new Object[] { this.fStringPool.toString(this.fElementType[n2][n3]), this.fStringPool.toString(n8) }, 1);
                    }
                }
                else if (n11 != -1) {
                    if (b && b2 && this.fAttDefIsExternal[n6][n7] != 0) {
                        this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 95, 80, new Object[] { this.fStringPool.toString(this.fElementType[n2][n3]), this.fStringPool.toString(n8) }, 1);
                    }
                    if (n9 == this.fStringPool.addSymbol("IDREF")) {
                        this.addIdRef(n11);
                    }
                    else if (n9 == this.fStringPool.addSymbol("IDREFS")) {
                        final StringTokenizer stringTokenizer = new StringTokenizer(this.fStringPool.toString(n11));
                        while (stringTokenizer.hasMoreTokens()) {
                            this.addIdRef(this.fStringPool.addSymbol(stringTokenizer.nextToken()));
                        }
                    }
                    if (startAttrList == -1) {
                        startAttrList = list.startAttrList();
                    }
                    final int addAttr = list.addAttr(n8, n11, n9, false, false);
                    if (n5 == -1) {
                        n5 = addAttr;
                    }
                }
            }
            i = this.fNextAttDef[n6][n7];
        }
        return startAttrList;
    }
    
    public boolean addId(final int n) {
        final Integer n2 = new Integer(n);
        if (this.fIdDefs == null) {
            this.fIdDefs = new Hashtable();
        }
        else if (this.fIdDefs.containsKey(n2)) {
            return false;
        }
        if (this.fNullValue == null) {
            this.fNullValue = new Object();
        }
        this.fIdDefs.put(n2, this.fNullValue);
        return true;
    }
    
    public void addIdRef(final int n) {
        final Integer n2 = new Integer(n);
        if (this.fIdDefs != null && this.fIdDefs.containsKey(n2)) {
            return;
        }
        if (this.fIdRefs == null) {
            this.fIdRefs = new Hashtable();
        }
        else if (this.fIdRefs.containsKey(n2)) {
            return;
        }
        if (this.fNullValue == null) {
            this.fNullValue = new Object();
        }
        this.fIdRefs.put(n2, this.fNullValue);
    }
    
    public void checkIdRefs() throws Exception {
        if (this.fIdRefs == null) {
            return;
        }
        final Enumeration<Integer> keys = this.fIdRefs.keys();
        while (keys.hasMoreElements()) {
            final Integer n = keys.nextElement();
            if (this.fIdDefs == null || !this.fIdDefs.containsKey(n)) {
                this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 81, 2, new Object[] { this.fStringPool.toString(n) }, 1);
            }
        }
    }
    
    public void checkDeclaredElements() throws Exception {
        for (int i = 0; i < this.fElementCount; ++i) {
            final int contentSpecType = this.getContentSpecType(i);
            if (contentSpecType == this.fStringPool.addSymbol("MIXED") || contentSpecType == this.fStringPool.addSymbol("CHILDREN")) {
                this.checkDeclaredElements(i, this.fContentSpec[i >> 5][i & 0x1F]);
            }
        }
    }
    
    private void checkDeclaredElements(final int n, final int n2) throws Exception {
        int n3 = n2 >> 5;
        int n4 = n2 & 0x1F;
        final byte b = this.fNodeType[n3][n4];
        final int n5 = this.fNodeValue[n3][n4];
        switch (b) {
            case 0: {
                if (n5 != -1 && this.getElement(n5) == -1) {
                    this.fErrorReporter.reportError(this.fErrorReporter.getLocator(), "http://www.w3.org/TR/1998/REC-xml-19980210", 92, 83, new Object[] { this.fStringPool.toString(this.fElementType[n >> 5][n & 0x1F]), this.fStringPool.toString(n5) }, 0);
                    return;
                }
                break;
            }
            case 1:
            case 2:
            case 3: {
                this.checkDeclaredElements(n, n5);
            }
            case 4:
            case 5: {
                this.checkDeclaredElements(n, n5);
                if (++n4 == 32) {
                    ++n3;
                    n4 = 0;
                }
                this.checkDeclaredElements(n, this.fNodeValue[n3][n4]);
            }
        }
    }
}
