// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.framework;

import org.apache.xerces.utils.QName;
import org.apache.xerces.utils.StringPool;
import org.xml.sax.AttributeList;

public final class XMLAttrList implements AttributeList
{
    private static final int CHUNK_SHIFT = 5;
    private static final int CHUNK_SIZE = 32;
    private static final int CHUNK_MASK = 31;
    private static final int INITIAL_CHUNK_COUNT = 32;
    private static final int ATTFLAG_SPECIFIED = 1;
    private static final int ATTFLAG_LASTATTR = 2;
    private StringPool fStringPool;
    private int fCurrentHandle;
    private int fAttributeListHandle;
    private int fAttributeListLength;
    private int fAttrCount;
    private int[][] fAttPrefix;
    private int[][] fAttLocalpart;
    private int[][] fAttName;
    private int[][] fAttURI;
    private int[][] fAttValue;
    private int[][] fAttType;
    private byte[][] fAttFlags;
    private QName fAttributeQName;
    
    public XMLAttrList(final StringPool fStringPool) {
        this.fStringPool = null;
        this.fCurrentHandle = -1;
        this.fAttributeListHandle = -1;
        this.fAttributeListLength = 0;
        this.fAttrCount = 0;
        this.fAttPrefix = new int[32][];
        this.fAttLocalpart = new int[32][];
        this.fAttName = new int[32][];
        this.fAttURI = new int[32][];
        this.fAttValue = new int[32][];
        this.fAttType = new int[32][];
        this.fAttFlags = new byte[32][];
        this.fAttributeQName = new QName();
        this.fStringPool = fStringPool;
    }
    
    public void reset(final StringPool fStringPool) {
        this.fStringPool = fStringPool;
        this.fCurrentHandle = -1;
        this.fAttributeListHandle = -1;
        this.fAttributeListLength = 0;
        this.fAttrCount = 0;
    }
    
    public int addAttr(final int n, final int n2, final int n3, final boolean b, final boolean b2) throws Exception {
        this.fAttributeQName.setValues(-1, n, n);
        return this.addAttr(this.fAttributeQName, n2, n3, b, b2);
    }
    
    public int addAttr(final QName qName, final int n, final int n2, final boolean b, final boolean b2) throws Exception {
        int n3;
        int n4;
        if (b2) {
            n3 = this.fCurrentHandle >> 5;
            n4 = (this.fCurrentHandle & 0x1F);
            for (int i = this.fCurrentHandle; i < this.fAttrCount; ++i) {
                if (this.fStringPool.equalNames(this.fAttName[n3][n4], qName.rawname)) {
                    return -1;
                }
                if (++n4 == 32) {
                    ++n3;
                    n4 = 0;
                }
            }
        }
        else {
            n3 = this.fAttrCount >> 5;
            n4 = (this.fAttrCount & 0x1F);
        }
        this.ensureCapacity(n3, n4);
        this.fAttPrefix[n3][n4] = qName.prefix;
        this.fAttLocalpart[n3][n4] = qName.localpart;
        this.fAttName[n3][n4] = qName.rawname;
        this.fAttURI[n3][n4] = qName.uri;
        this.fAttValue[n3][n4] = n;
        this.fAttType[n3][n4] = n2;
        this.fAttFlags[n3][n4] = (byte)(b ? 1 : 0);
        return this.fAttrCount++;
    }
    
    public int startAttrList() {
        return this.fCurrentHandle = this.fAttrCount;
    }
    
    public void endAttrList() {
        final int n = this.fAttrCount - 1;
        final int n2 = n >> 5;
        final int n3 = n & 0x1F;
        final byte[] array = this.fAttFlags[n2];
        final int n4 = n3;
        array[n4] |= 0x2;
        this.fCurrentHandle = -1;
    }
    
    public int getAttrPrefix(final int n) {
        if (n < 0 || n >= this.fAttrCount) {
            return -1;
        }
        return this.fAttPrefix[n >> 5][n & 0x1F];
    }
    
    public int getAttrLocalpart(final int n) {
        if (n < 0 || n >= this.fAttrCount) {
            return -1;
        }
        return this.fAttLocalpart[n >> 5][n & 0x1F];
    }
    
    public int getAttrName(final int n) {
        if (n < 0 || n >= this.fAttrCount) {
            return -1;
        }
        return this.fAttName[n >> 5][n & 0x1F];
    }
    
    public void setAttrURI(final int n, final int n2) {
        if (n < 0 || n >= this.fAttrCount) {
            return;
        }
        this.fAttURI[n >> 5][n & 0x1F] = n2;
    }
    
    public int getAttrURI(final int n) {
        if (n < 0 || n >= this.fAttrCount) {
            return -1;
        }
        return this.fAttURI[n >> 5][n & 0x1F];
    }
    
    public int getAttValue(final int n) {
        if (n < 0 || n >= this.fAttrCount) {
            return -1;
        }
        return this.fAttValue[n >> 5][n & 0x1F];
    }
    
    public void setAttValue(final int n, final int n2) {
        if (n < 0 || n >= this.fAttrCount) {
            return;
        }
        this.fAttValue[n >> 5][n & 0x1F] = n2;
    }
    
    public void setAttType(final int n, final int n2) {
        if (n < 0 || n >= this.fAttrCount) {
            return;
        }
        this.fAttType[n >> 5][n & 0x1F] = n2;
    }
    
    public int getAttType(final int n) {
        if (n < 0 || n >= this.fAttrCount) {
            return -1;
        }
        return this.fAttType[n >> 5][n & 0x1F];
    }
    
    public boolean isSpecified(final int n) {
        return n < 0 || n >= this.fAttrCount || (this.fAttFlags[n >> 5][n & 0x1F] & 0x1) != 0x0;
    }
    
    public void releaseAttrList(final int fAttrCount) {
        if (fAttrCount == -1) {
            return;
        }
        int n = fAttrCount >> 5;
        int n2 = fAttrCount & 0x1F;
        boolean b;
        do {
            b = ((this.fAttFlags[n][n2] & 0x2) != 0x0);
            this.fAttPrefix[n][n2] = -1;
            this.fAttLocalpart[n][n2] = -1;
            this.fAttName[n][n2] = -1;
            this.fAttURI[n][n2] = 0;
            if ((this.fAttFlags[n][n2] & 0x1) != 0x0) {
                this.fStringPool.releaseString(this.fAttValue[n][n2]);
            }
            this.fAttValue[n][n2] = -1;
            if (++n2 == 32) {
                ++n;
                n2 = 0;
            }
        } while (!b);
        if (this.fAttrCount == (n << 5) + n2) {
            this.fAttrCount = fAttrCount;
        }
    }
    
    public int getFirstAttr(final int n) {
        if (n < 0 || n >= this.fAttrCount) {
            return -1;
        }
        return n;
    }
    
    public int getNextAttr(final int n) {
        if (n < 0 || n + 1 >= this.fAttrCount) {
            return -1;
        }
        if ((this.fAttFlags[n >> 5][n & 0x1F] & 0x2) != 0x0) {
            return -1;
        }
        return n + 1;
    }
    
    public AttributeList getAttributeList(final int fAttributeListHandle) {
        this.fAttributeListHandle = fAttributeListHandle;
        if (this.fAttributeListHandle == -1) {
            this.fAttributeListLength = 0;
        }
        else {
            int n = this.fAttributeListHandle >> 5;
            int n2 = this.fAttributeListHandle & 0x1F;
            this.fAttributeListLength = 1;
            while ((this.fAttFlags[n][n2] & 0x2) == 0x0) {
                if (++n2 == 32) {
                    ++n;
                    n2 = 0;
                }
                ++this.fAttributeListLength;
            }
        }
        return this;
    }
    
    public int getLength() {
        return this.fAttributeListLength;
    }
    
    public String getPrefix(final int n) {
        if (n < 0 || n >= this.fAttributeListLength) {
            return null;
        }
        return this.fStringPool.toString(this.fAttPrefix[this.fAttributeListHandle + n >> 5][this.fAttributeListHandle + n & 0x1F]);
    }
    
    public String getLocalpart(final int n) {
        if (n < 0 || n >= this.fAttributeListLength) {
            return null;
        }
        return this.fStringPool.toString(this.fAttLocalpart[this.fAttributeListHandle + n >> 5][this.fAttributeListHandle + n & 0x1F]);
    }
    
    public String getName(final int n) {
        if (n < 0 || n >= this.fAttributeListLength) {
            return null;
        }
        return this.fStringPool.toString(this.fAttName[this.fAttributeListHandle + n >> 5][this.fAttributeListHandle + n & 0x1F]);
    }
    
    public String getURI(final int n) {
        if (n < 0 || n >= this.fAttributeListLength) {
            return null;
        }
        return this.fStringPool.toString(this.fAttURI[this.fAttributeListHandle + n >> 5][this.fAttributeListHandle + n & 0x1F]);
    }
    
    public String getType(final int n) {
        if (n < 0 || n >= this.fAttributeListLength) {
            return null;
        }
        int addSymbol = this.fAttType[this.fAttributeListHandle + n >> 5][this.fAttributeListHandle + n & 0x1F];
        if (addSymbol == this.fStringPool.addSymbol("ENUMERATION")) {
            addSymbol = this.fStringPool.addSymbol("NMTOKEN");
        }
        return this.fStringPool.toString(addSymbol);
    }
    
    public String getValue(final int n) {
        if (n < 0 || n >= this.fAttributeListLength) {
            return null;
        }
        return this.fStringPool.toString(this.fAttValue[this.fAttributeListHandle + n >> 5][this.fAttributeListHandle + n & 0x1F]);
    }
    
    public String getType(final String s) {
        final int addSymbol = this.fStringPool.addSymbol(s);
        if (addSymbol == -1) {
            return null;
        }
        int n = this.fAttributeListHandle >> 5;
        int n2 = this.fAttributeListHandle & 0x1F;
        for (int i = 0; i < this.fAttributeListLength; ++i) {
            if (this.fStringPool.equalNames(this.fAttName[n][n2], addSymbol)) {
                int addSymbol2 = this.fAttType[n][n2];
                if (addSymbol2 == this.fStringPool.addSymbol("ENUMERATION")) {
                    addSymbol2 = this.fStringPool.addSymbol("NMTOKEN");
                }
                return this.fStringPool.toString(addSymbol2);
            }
            if (++n2 == 32) {
                ++n;
                n2 = 0;
            }
        }
        return null;
    }
    
    public String getValue(final String s) {
        final int addSymbol = this.fStringPool.addSymbol(s);
        if (addSymbol == -1) {
            return null;
        }
        int n = this.fAttributeListHandle >> 5;
        int n2 = this.fAttributeListHandle & 0x1F;
        for (int i = 0; i < this.fAttributeListLength; ++i) {
            if (this.fStringPool.equalNames(this.fAttName[n][n2], addSymbol)) {
                return this.fStringPool.toString(this.fAttValue[n][n2]);
            }
            if (++n2 == 32) {
                ++n;
                n2 = 0;
            }
        }
        return null;
    }
    
    private boolean ensureCapacity(final int n, final int n2) {
        try {
            return this.fAttName[n][n2] != 0;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            final int[][] fAttPrefix = new int[n * 2][];
            System.arraycopy(this.fAttPrefix, 0, fAttPrefix, 0, n);
            this.fAttPrefix = fAttPrefix;
            final int[][] fAttLocalpart = new int[n * 2][];
            System.arraycopy(this.fAttLocalpart, 0, fAttLocalpart, 0, n);
            this.fAttLocalpart = fAttLocalpart;
            final int[][] fAttName = new int[n * 2][];
            System.arraycopy(this.fAttName, 0, fAttName, 0, n);
            this.fAttName = fAttName;
            final int[][] fAttURI = new int[n * 2][];
            System.arraycopy(this.fAttURI, 0, fAttURI, 0, n);
            this.fAttURI = fAttURI;
            final int[][] fAttValue = new int[n * 2][];
            System.arraycopy(this.fAttValue, 0, fAttValue, 0, n);
            this.fAttValue = fAttValue;
            final int[][] fAttType = new int[n * 2][];
            System.arraycopy(this.fAttType, 0, fAttType, 0, n);
            this.fAttType = fAttType;
            final byte[][] fAttFlags = new byte[n * 2][];
            System.arraycopy(this.fAttFlags, 0, fAttFlags, 0, n);
            this.fAttFlags = fAttFlags;
        }
        catch (NullPointerException ex2) {}
        this.fAttPrefix[n] = new int[32];
        this.fAttLocalpart[n] = new int[32];
        this.fAttName[n] = new int[32];
        this.fAttURI[n] = new int[32];
        this.fAttValue[n] = new int[32];
        this.fAttType[n] = new int[32];
        this.fAttFlags[n] = new byte[32];
        return true;
    }
}
