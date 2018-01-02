// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

public final class SymbolTable
{
    private static final boolean CHECK_SYMBOL_HANDLES = false;
    private static final boolean CHECK_CACHE_CONSISTENCY = false;
    private static final boolean DUMP_CACHE = false;
    private static final boolean DEBUG_CACHE = false;
    private static final boolean DEBUG_LOOKUPS = false;
    private static final boolean DEBUG_ADDITIONS = false;
    private static final boolean DEBUG_ALLOC = false;
    private static final int SYMBOL_MASK = 1610612736;
    private static final int SYMBOL_FLAG = 1073741824;
    private static final int STRING_FLAG = 536870912;
    private static final int INITIAL_CHUNK_SHIFT = 8;
    private static final int INITIAL_CHUNK_SIZE = 256;
    private static final int CHUNK_SHIFT = 13;
    private static final int CHUNK_SIZE = 8192;
    private static final int CHUNK_MASK = 8191;
    private static final int INITIAL_CHUNK_COUNT = 8;
    private int fSymbolCount;
    private String[][] fString;
    private int[][] fPrefixHandle;
    private int[][] fLocalHandle;
    private char[] fStringChars;
    private char[] fSymbolChars;
    private int[] fMBLen;
    private Node fCache;
    private Node fFreeNodes;
    
    public SymbolTable() {
        this.fString = new String[8][];
        this.fPrefixHandle = new int[8][];
        this.fLocalHandle = new int[8][];
        this.fMBLen = new int[1];
        this.fStringChars = new char[256];
        this.fCache = new Node();
        this.fCache.next = new Node();
        this.fCache.next.ch = 108;
        this.setStringForHandle(XMLString.EMPTY_STRING, 0);
        this.fSymbolCount = 1;
    }
    
    private void cacheNodeDeallocate(Node fFreeNodes) {
        do {
            fFreeNodes.ch = 0;
            fFreeNodes.handle = 0;
            if (fFreeNodes.left != null) {
                this.cacheNodeDeallocate(fFreeNodes.left);
                fFreeNodes.left = null;
            }
            if (fFreeNodes.right != null) {
                this.cacheNodeDeallocate(fFreeNodes.right);
                fFreeNodes.right = null;
            }
            final Node next = fFreeNodes.next;
            fFreeNodes.next = this.fFreeNodes;
            this.fFreeNodes = fFreeNodes;
            fFreeNodes = next;
        } while (fFreeNodes != null);
    }
    
    public void reset(final boolean b) {
        if (!b) {
            int n = 0;
            int n2 = 1;
            for (int i = 1; i < this.fSymbolCount; ++i) {
                this.fString[n][n2] = null;
                this.fPrefixHandle[n][n2] = 0;
                this.fLocalHandle[n][n2] = 0;
                if (++n2 == 8192) {
                    ++n;
                    n2 = 0;
                }
            }
            this.cacheNodeDeallocate(this.fCache.next);
            this.fCache.next = this.cacheNodeAllocate();
            this.fCache.next.ch = 108;
            this.fSymbolCount = 1;
        }
    }
    
    public String toString(final int n) {
        if (n == -1) {
            return null;
        }
        if (n >= this.fSymbolCount) {
            throw new RuntimeException("SymbolTable.toString(" + n + ")");
        }
        return this.fString[n >>> 13][n & 0x1FFF];
    }
    
    private String getString(final int n) {
        return this.fString[n >>> 13][n & 0x1FFF];
    }
    
    public int addSymbol(final XMLString xmlString) {
        if (xmlString.handle >= 0) {
            return xmlString.handle;
        }
        if (xmlString.bytes != null) {
            final int offset = xmlString.offset;
            final int endOffset = xmlString.endOffset;
            if (offset < endOffset) {
                xmlString.handle = this.addSymbol(xmlString.bytes, offset, endOffset, xmlString.encoding, xmlString.str);
            }
            else {
                xmlString.handle = 0;
            }
        }
        else if (xmlString.chars != null) {
            final int offset2 = xmlString.offset;
            final int endOffset2 = xmlString.endOffset;
            if (offset2 < endOffset2) {
                xmlString.handle = this.addSymbol(xmlString.chars, offset2, endOffset2, xmlString.str);
            }
            else {
                xmlString.handle = 0;
            }
        }
        else {
            xmlString.handle = this.addSymbol(xmlString.str);
        }
        if (xmlString.handle >= 0) {
            xmlString.str = this.getString(xmlString.handle);
        }
        return xmlString.handle;
    }
    
    public void addQNameSymbols(final QName qName) {
        final int sepOffset = qName.sepOffset;
        final byte[] bytes = qName.bytes;
        final int offset = qName.offset;
        final int endOffset = qName.endOffset;
        final String str = qName.str;
        int prefixHandle;
        int localHandle;
        if (bytes != null) {
            final EncodingSupport encoding = qName.encoding;
            final int n = qName.handle = this.addSymbol(bytes, offset, endOffset, encoding, str);
            final int n2 = n >>> 13;
            final int n3 = n & 0x1FFF;
            qName.str = this.fString[n2][n3];
            prefixHandle = this.fPrefixHandle[n2][n3];
            if (prefixHandle != 0) {
                localHandle = this.fLocalHandle[n2][n3];
            }
            else {
                prefixHandle = this.addSymbol(bytes, offset, sepOffset, encoding, null);
                localHandle = this.addSymbol(bytes, sepOffset + 1, endOffset, encoding, null);
                this.fPrefixHandle[n2][n3] = prefixHandle;
                this.fLocalHandle[n2][n3] = localHandle;
            }
        }
        else {
            final char[] chars = qName.chars;
            if (chars == null) {
                throw new IllegalArgumentException("SymbolTable#addQNameSymbols()");
            }
            final int n4 = qName.handle = this.addSymbol(chars, offset, endOffset, str);
            final int n5 = n4 >>> 13;
            final int n6 = n4 & 0x1FFF;
            qName.str = this.fString[n5][n6];
            prefixHandle = this.fPrefixHandle[n5][n6];
            if (prefixHandle != 0) {
                localHandle = this.fLocalHandle[n5][n6];
            }
            else {
                prefixHandle = this.addSymbol(chars, offset, sepOffset, null);
                localHandle = this.addSymbol(chars, sepOffset + 1, endOffset, null);
                this.fPrefixHandle[n5][n6] = prefixHandle;
                this.fLocalHandle[n5][n6] = localHandle;
            }
        }
        qName.prefix = this.getString(prefixHandle);
        qName.prefixHandle = prefixHandle;
        qName.localPart = this.getString(localHandle);
        qName.localHandle = localHandle;
    }
    
    public int addSymbol(final String s) {
        if (s == null) {
            return -1;
        }
        final int i = s.length();
        if (i == 0) {
            return 0;
        }
        if (i > this.fStringChars.length) {
            int n;
            for (n = this.fStringChars.length << 1; i > n; n <<= 1) {}
            this.fStringChars = new char[n];
        }
        s.getChars(0, i, this.fStringChars, 0);
        return this.addSymbol(this.fStringChars, 0, i, s);
    }
    
    public void dumpCache() {
    }
    
    private void dumpCacheRecord(final Node node, final int n) {
    }
    
    public void checkCache() {
    }
    
    private Node cacheNodeAllocate() {
        Node fFreeNodes = this.fFreeNodes;
        if (fFreeNodes != null) {
            this.fFreeNodes = fFreeNodes.next;
            fFreeNodes.next = null;
        }
        else {
            fFreeNodes = new Node();
        }
        return fFreeNodes;
    }
    
    private int addSymbol(final byte[] array, final int n, final int n2, final EncodingSupport encodingSupport, final String s) {
        if (encodingSupport.isASCIITransparent()) {
            return this.addASCIITransparentSymbol(array, n, n2, encodingSupport, s);
        }
        if (encodingSupport.isSingleByte()) {
            return this.addSingleByteSymbol(array, n, n2, (SingleByteEncodingSupport)encodingSupport, s);
        }
        return this.addMultiByteSymbol(array, n, n2, encodingSupport, s);
    }
    
    private int addASCIITransparentSymbol(final byte[] array, final int n, final int n2, final EncodingSupport encodingSupport, String s) {
        int i = n;
        Node fCache = this.fCache;
        Node node = fCache.next;
        int n3 = 0;
        int decodeCharacter;
        int n4;
        while (true) {
            decodeCharacter = array[i++];
            if (decodeCharacter < 0) {
                decodeCharacter = encodingSupport.decodeCharacter(array, i - 1, n2, this.fMBLen);
                i += this.fMBLen[0] - 1;
            }
            ++n3;
            if (node.ch != decodeCharacter) {
                node = splay(node, decodeCharacter);
                if (node.ch != decodeCharacter) {
                    n4 = 1;
                    break;
                }
                fCache.next = node;
            }
            if (i < n2) {
                if (node.next == null) {
                    n4 = 2;
                    break;
                }
                fCache = node;
                node = fCache.next;
            }
            else {
                final int handle = node.handle;
                if (handle != 0) {
                    return handle;
                }
                n4 = 3;
                break;
            }
        }
        Label_0301: {
            switch (n4) {
                case 1: {
                    final Node cacheNodeAllocate = this.cacheNodeAllocate();
                    cacheNodeAllocate.ch = decodeCharacter;
                    if (node.ch < decodeCharacter) {
                        cacheNodeAllocate.right = node.right;
                        cacheNodeAllocate.left = node;
                        node.right = null;
                    }
                    else {
                        cacheNodeAllocate.left = node.left;
                        cacheNodeAllocate.right = node;
                        node.left = null;
                    }
                    node = cacheNodeAllocate;
                    fCache.next = node;
                    if (i == n2) {
                        break;
                    }
                    break Label_0301;
                }
                case 2: {
                    do {
                        final byte b = array[i];
                        int decodeCharacter2;
                        if (b < 0) {
                            decodeCharacter2 = encodingSupport.decodeCharacter(array, i, n2, this.fMBLen);
                            i += this.fMBLen[0];
                        }
                        else {
                            ++i;
                            decodeCharacter2 = b;
                        }
                        final Node cacheNodeAllocate2 = this.cacheNodeAllocate();
                        cacheNodeAllocate2.ch = decodeCharacter2;
                        node.next = cacheNodeAllocate2;
                        node = cacheNodeAllocate2;
                        ++n3;
                    } while (i < n2);
                    break;
                }
            }
        }
        final int handle2 = this.fSymbolCount++;
        node.handle = handle2;
        if (s == null) {
            int n5 = 0;
            char[] fStringChars = this.fStringChars;
            if (n3 << 1 > fStringChars.length) {
                int n6;
                for (n6 = fStringChars.length << 1; n3 << 1 > n6; n6 <<= 1) {}
                final char[] fStringChars2 = new char[n6];
                this.fStringChars = fStringChars2;
                fStringChars = fStringChars2;
            }
            Node node2 = this.fCache.next;
            while (n3-- > 0) {
                final int ch = node2.ch;
                if (ch < 65536) {
                    fStringChars[n5++] = (char)ch;
                }
                else {
                    final int n7 = ch - 65536;
                    fStringChars[n5++] = (char)(55296 + (n7 >> 10));
                    fStringChars[n5++] = (char)(56320 + (n7 & 0x3FF));
                }
                node2 = node2.next;
            }
            s = new String(fStringChars, 0, n5);
        }
        this.setStringForHandle(s.intern(), handle2);
        return handle2;
    }
    
    private int addSingleByteSymbol(final byte[] array, final int n, final int n2, final SingleByteEncodingSupport singleByteEncodingSupport, String s) {
        int i = n;
        final char[] byteToCharMap = singleByteEncodingSupport.byteToCharMap;
        Node fCache = this.fCache;
        Node next = fCache.next;
        int n3 = 0;
        Label_0268: {
            while (true) {
                final char ch = byteToCharMap[array[i++] & 0xFF];
                ++n3;
                if (next.ch != ch) {
                    next = splay(next, ch);
                    if (next.ch != ch) {
                        final Node cacheNodeAllocate = this.cacheNodeAllocate();
                        if (next.ch < (cacheNodeAllocate.ch = ch)) {
                            cacheNodeAllocate.right = next.right;
                            cacheNodeAllocate.left = next;
                            next.right = null;
                        }
                        else {
                            cacheNodeAllocate.left = next.left;
                            cacheNodeAllocate.right = next;
                            next.left = null;
                        }
                        next = cacheNodeAllocate;
                    }
                    fCache.next = next;
                }
                if (i == n2) {
                    final int handle = next.handle;
                    if (handle != 0) {
                        return handle;
                    }
                    break Label_0268;
                }
                else {
                    if (next.next == null) {
                        break;
                    }
                    fCache = next;
                    next = fCache.next;
                }
            }
            do {
                final char ch2 = byteToCharMap[array[i++] & 0xFF];
                final Node cacheNodeAllocate2 = this.cacheNodeAllocate();
                cacheNodeAllocate2.ch = ch2;
                next.next = cacheNodeAllocate2;
                next = cacheNodeAllocate2;
                ++n3;
            } while (i < n2);
        }
        final int handle2 = this.fSymbolCount++;
        next.handle = handle2;
        if (s == null) {
            int n4 = 0;
            char[] fStringChars = this.fStringChars;
            if (n3 << 1 > fStringChars.length) {
                int n5;
                for (n5 = fStringChars.length << 1; n3 << 1 > n5; n5 <<= 1) {}
                final char[] fStringChars2 = new char[n5];
                this.fStringChars = fStringChars2;
                fStringChars = fStringChars2;
            }
            Node node = this.fCache.next;
            while (n3-- > 0) {
                final int ch3 = node.ch;
                if (ch3 < 65536) {
                    fStringChars[n4++] = (char)ch3;
                }
                else {
                    final int n6 = ch3 - 65536;
                    fStringChars[n4++] = (char)(55296 + (n6 >> 10));
                    fStringChars[n4++] = (char)(56320 + (n6 & 0x3FF));
                }
                node = node.next;
            }
            s = new String(fStringChars, 0, n4);
        }
        this.setStringForHandle(s.intern(), handle2);
        return handle2;
    }
    
    private int addMultiByteSymbol(final byte[] array, final int n, final int n2, final EncodingSupport encodingSupport, String s) {
        int i = n;
        Node fCache = this.fCache;
        Node next = fCache.next;
        int n3 = 0;
        Label_0287: {
            while (true) {
                final int decodeCharacter = encodingSupport.decodeCharacter(array, i, n2, this.fMBLen);
                i += this.fMBLen[0];
                ++n3;
                if (next.ch != decodeCharacter) {
                    next = splay(next, decodeCharacter);
                    if (next.ch != decodeCharacter) {
                        final Node cacheNodeAllocate = this.cacheNodeAllocate();
                        if (next.ch < (cacheNodeAllocate.ch = decodeCharacter)) {
                            cacheNodeAllocate.right = next.right;
                            cacheNodeAllocate.left = next;
                            next.right = null;
                        }
                        else {
                            cacheNodeAllocate.left = next.left;
                            cacheNodeAllocate.right = next;
                            next.left = null;
                        }
                        next = cacheNodeAllocate;
                    }
                    fCache.next = next;
                }
                if (i == n2) {
                    final int handle = next.handle;
                    if (handle != 0) {
                        return handle;
                    }
                    break Label_0287;
                }
                else {
                    if (next.next == null) {
                        break;
                    }
                    fCache = next;
                    next = fCache.next;
                }
            }
            do {
                final int decodeCharacter2 = encodingSupport.decodeCharacter(array, i, n2, this.fMBLen);
                i += this.fMBLen[0];
                final Node cacheNodeAllocate2 = this.cacheNodeAllocate();
                cacheNodeAllocate2.ch = decodeCharacter2;
                next.next = cacheNodeAllocate2;
                next = cacheNodeAllocate2;
                ++n3;
            } while (i < n2);
        }
        final int handle2 = this.fSymbolCount++;
        next.handle = handle2;
        if (s == null) {
            int n4 = 0;
            char[] fStringChars = this.fStringChars;
            if (n3 << 1 > fStringChars.length) {
                int n5;
                for (n5 = fStringChars.length << 1; n3 << 1 > n5; n5 <<= 1) {}
                final char[] fStringChars2 = new char[n5];
                this.fStringChars = fStringChars2;
                fStringChars = fStringChars2;
            }
            Node node = this.fCache.next;
            while (n3-- > 0) {
                final int ch = node.ch;
                if (ch < 65536) {
                    fStringChars[n4++] = (char)ch;
                }
                else {
                    final int n6 = ch - 65536;
                    fStringChars[n4++] = (char)(55296 + (n6 >> 10));
                    fStringChars[n4++] = (char)(56320 + (n6 & 0x3FF));
                }
                node = node.next;
            }
            s = new String(fStringChars, 0, n4);
        }
        this.setStringForHandle(s.intern(), handle2);
        return handle2;
    }
    
    private int addSymbol(final char[] array, final int n, final int n2, String s) {
        int i = n;
        Node fCache = this.fCache;
        Node node = fCache.next;
        int ch;
        int n3;
        while (true) {
            ch = array[i++];
            if (ch >= 55296) {
                if (ch < 56320) {
                    if (i == n2) {
                        CharConversionError.missingSecondHalfOfSurrogatePair();
                    }
                    final char c = array[i++];
                    if (c < '\udc00' || c >= '\ue000') {
                        CharConversionError.invalidSecondHalfOfSurrogatePair();
                    }
                    ch = 65536 + (ch - 55296 << 10) + (c - '\udc00');
                }
                else if (ch < 57344) {
                    CharConversionError.invalidFirstHalfOfSurrogatePair();
                }
            }
            if (node.ch != ch) {
                node = splay(node, ch);
                if (node.ch != ch) {
                    n3 = 1;
                    break;
                }
                fCache.next = node;
            }
            if (i < n2) {
                if (node.next == null) {
                    n3 = 2;
                    break;
                }
                fCache = node;
                node = fCache.next;
            }
            else {
                final int handle = node.handle;
                if (handle != 0) {
                    return handle;
                }
                n3 = 3;
                break;
            }
        }
        Label_0341: {
            switch (n3) {
                case 1: {
                    final Node cacheNodeAllocate = this.cacheNodeAllocate();
                    cacheNodeAllocate.ch = ch;
                    if (node.ch < ch) {
                        cacheNodeAllocate.right = node.right;
                        cacheNodeAllocate.left = node;
                        node.right = null;
                    }
                    else {
                        cacheNodeAllocate.left = node.left;
                        cacheNodeAllocate.right = node;
                        node.left = null;
                    }
                    node = cacheNodeAllocate;
                    fCache.next = node;
                    if (i == n2) {
                        break;
                    }
                    break Label_0341;
                }
                case 2: {
                    do {
                        int ch2 = array[i++];
                        if (ch2 >= 55296) {
                            if (ch2 < 56320) {
                                if (i == n2) {
                                    CharConversionError.missingSecondHalfOfSurrogatePair();
                                }
                                final char c2 = array[i++];
                                if (c2 < '\udc00' || c2 >= '\ue000') {
                                    CharConversionError.invalidSecondHalfOfSurrogatePair();
                                }
                                ch2 = 65536 + (ch2 - 55296 << 10) + (c2 - '\udc00');
                            }
                            else {
                                CharConversionError.invalidFirstHalfOfSurrogatePair();
                            }
                        }
                        final Node cacheNodeAllocate2 = this.cacheNodeAllocate();
                        cacheNodeAllocate2.ch = ch2;
                        node.next = cacheNodeAllocate2;
                        node = cacheNodeAllocate2;
                    } while (i < n2);
                    break;
                }
            }
        }
        final int handle2 = this.fSymbolCount++;
        node.handle = handle2;
        if (s == null) {
            s = new String(array, n, n2 - n);
        }
        this.setStringForHandle(s.intern(), handle2);
        return handle2;
    }
    
    private void setStringForHandle(final String s, final int n) {
        final int n2 = n >>> 13;
        final int n3 = n & 0x1FFF;
        if (n2 < this.fString.length) {
            if (this.fString[n2] != null) {
                if (n3 < this.fString[n2].length) {
                    this.fString[n2][n3] = s;
                    this.fPrefixHandle[n2][n3] = 0;
                    this.fLocalHandle[n2][n3] = 0;
                    return;
                }
                final String[] array = new String[n3 << 1];
                System.arraycopy(this.fString[n2], 0, array, 0, n3);
                this.fString[n2] = array;
                final int[] array2 = new int[n3 << 1];
                System.arraycopy(this.fPrefixHandle[n2], 0, array2, 0, n3);
                this.fPrefixHandle[n2] = array2;
                final int[] array3 = new int[n3 << 1];
                System.arraycopy(this.fLocalHandle[n2], 0, array3, 0, n3);
                this.fLocalHandle[n2] = array3;
            }
            else {
                this.fString[n2] = new String[256];
                this.fPrefixHandle[n2] = new int[256];
                this.fLocalHandle[n2] = new int[256];
            }
        }
        else {
            final String[][] fString = new String[n2 << 1][];
            System.arraycopy(this.fString, 0, fString, 0, n2);
            this.fString = fString;
            final int[][] fPrefixHandle = new int[n2 << 1][];
            System.arraycopy(this.fPrefixHandle, 0, fPrefixHandle, 0, n2);
            this.fPrefixHandle = fPrefixHandle;
            final int[][] fLocalHandle = new int[n2 << 1][];
            System.arraycopy(this.fLocalHandle, 0, fLocalHandle, 0, n2);
            this.fLocalHandle = fLocalHandle;
            this.fString[n2] = new String[256];
            this.fPrefixHandle[n2] = new int[256];
            this.fLocalHandle[n2] = new int[256];
        }
        this.fString[n2][n3] = s;
    }
    
    private static Node splay(Node node, final int n) {
        Node left = null;
        Node right = null;
        Node node2 = null;
        Node node3 = null;
        while (true) {
            while (node.ch != n) {
                if (node.ch < n) {
                    if (node.right != null) {
                        if (node.right.ch > n && node.right.left != null) {
                            if (node2 == null) {
                                left = node;
                            }
                            else {
                                node2.right = node;
                            }
                            node2 = node;
                            if (node3 == null) {
                                right = node.right;
                            }
                            else {
                                node3.left = node.right;
                            }
                            node3 = node.right;
                            node = node3.left;
                            continue;
                        }
                        if (node.right.ch < n && node.right.right != null) {
                            if (node2 == null) {
                                left = node.right;
                            }
                            else {
                                node2.right = node.right;
                            }
                            node2 = node.right;
                            node.right = node2.left;
                            node2.left = node;
                            node = node2.right;
                            continue;
                        }
                        if (node2 == null) {
                            left = node;
                        }
                        else {
                            node2.right = node;
                        }
                        node2 = node;
                        node = node.right;
                    }
                }
                else if (node.left != null) {
                    if (node.left.ch < n && node.left.right != null) {
                        if (node3 == null) {
                            right = node;
                        }
                        else {
                            node3.left = node;
                        }
                        node3 = node;
                        if (node2 == null) {
                            left = node.left;
                        }
                        else {
                            node2.right = node.left;
                        }
                        node2 = node.left;
                        node = node2.right;
                        continue;
                    }
                    if (node.left.ch > n && node.left.left != null) {
                        if (node3 == null) {
                            right = node.left;
                        }
                        else {
                            node3.left = node.left;
                        }
                        node3 = node.left;
                        node.left = node3.right;
                        node3.right = node;
                        node = node3.left;
                        continue;
                    }
                    if (node3 == null) {
                        right = node;
                    }
                    else {
                        node3.left = node;
                    }
                    node3 = node;
                    node = node.left;
                }
                if (node2 != null) {
                    node2.right = node.left;
                    node.left = left;
                }
                if (node3 != null) {
                    node3.left = node.right;
                    node.right = right;
                }
                return node;
            }
            continue;
        }
    }
    
    public static final class Node
    {
        public Node left;
        public Node right;
        public Node next;
        public int ch;
        public int handle;
    }
}
