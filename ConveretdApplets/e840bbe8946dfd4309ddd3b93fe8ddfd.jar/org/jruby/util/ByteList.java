// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.util.concurrent.ConcurrentHashMap;
import java.nio.CharBuffer;
import org.jcodings.ascii.AsciiTables;
import java.nio.ByteBuffer;
import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;
import org.jcodings.specific.ASCIIEncoding;
import java.nio.charset.Charset;
import java.util.concurrent.ConcurrentMap;
import org.jcodings.Encoding;
import java.io.Serializable;

public final class ByteList implements Comparable, CharSequence, Serializable
{
    private static final long serialVersionUID = -1286166947275543731L;
    public static final byte[] NULL_ARRAY;
    public static final ByteList EMPTY_BYTELIST;
    @Deprecated
    public byte[] bytes;
    @Deprecated
    public int begin;
    @Deprecated
    public int realSize;
    @Deprecated
    public Encoding encoding;
    int hash;
    String stringValue;
    private static final int DEFAULT_SIZE = 4;
    private static final ConcurrentMap<String, Charset> charsetsByAlias;
    
    public ByteList() {
        this(4);
    }
    
    public ByteList(final int size) {
        this.encoding = ASCIIEncoding.INSTANCE;
        this.bytes = new byte[size];
        this.realSize = 0;
    }
    
    public ByteList(final byte[] bytes, final Encoding encoding) {
        this.encoding = ASCIIEncoding.INSTANCE;
        this.bytes = bytes;
        this.realSize = bytes.length;
        this.encoding = safeEncoding(encoding);
    }
    
    public ByteList(final byte[] wrap) {
        this(wrap, true);
    }
    
    public ByteList(final byte[] wrap, final boolean copy) {
        this(wrap, ASCIIEncoding.INSTANCE, copy);
    }
    
    public ByteList(final byte[] wrap, final Encoding encoding, final boolean copy) {
        this.encoding = ASCIIEncoding.INSTANCE;
        assert wrap != null;
        if (copy) {
            this.bytes = wrap.clone();
        }
        else {
            this.bytes = wrap;
        }
        this.realSize = wrap.length;
        this.encoding = safeEncoding(encoding);
    }
    
    public ByteList(final ByteList wrap) {
        this(wrap.bytes, wrap.begin, wrap.realSize, wrap.encoding, true);
    }
    
    public ByteList(final ByteList wrap, final boolean copy) {
        this(wrap.bytes, wrap.begin, wrap.realSize, wrap.encoding, false);
    }
    
    public ByteList(final byte[] wrap, final int index, final int len) {
        this(wrap, index, len, true);
    }
    
    public ByteList(final byte[] wrap, final int index, final int len, final boolean copy) {
        this(wrap, index, len, ASCIIEncoding.INSTANCE, copy);
    }
    
    public ByteList(final byte[] wrap, final int index, final int len, final Encoding encoding, final boolean copy) {
        this.encoding = ASCIIEncoding.INSTANCE;
        assert wrap != null : "'wrap' must not be null";
        assert index >= 0 && index <= wrap.length : "'index' is not without bounds of 'wrap' array";
        assert wrap.length >= index + len : "'index' + 'len' is longer than the 'wrap' array";
        if (copy) {
            System.arraycopy(wrap, index, this.bytes = new byte[len], 0, len);
        }
        else {
            this.begin = index;
            this.bytes = wrap;
        }
        this.realSize = len;
        this.encoding = safeEncoding(encoding);
    }
    
    public ByteList(final ByteList wrap, final int index, final int len) {
        this(wrap.bytes, wrap.begin + index, len);
    }
    
    public void delete(final int start, final int len) {
        assert start >= this.begin && start < this.realSize : "'start' is at invalid index";
        assert len >= 0 : "'len' must be positive";
        assert start + len <= this.begin + this.realSize : "too many bytes requested";
        this.realSize -= len;
        System.arraycopy(this.bytes, start + len, this.bytes, start, this.realSize);
    }
    
    public void fill(final int b, int len) {
        while (--len >= 0) {
            this.append(b);
        }
    }
    
    public Object clone() {
        return this.dup();
    }
    
    public ByteList dup() {
        final ByteList dup = this.dup(this.realSize);
        dup.hash = this.hash;
        dup.stringValue = this.stringValue;
        return dup;
    }
    
    public ByteList shallowDup() {
        final ByteList dup = new ByteList(this.bytes, false);
        dup.realSize = this.realSize;
        dup.begin = this.begin;
        dup.encoding = safeEncoding(this.encoding);
        dup.hash = this.hash;
        dup.stringValue = this.stringValue;
        return dup;
    }
    
    public ByteList dup(final int length) {
        final ByteList dup = new ByteList(length);
        dup.append(this.bytes, this.begin, this.realSize);
        dup.encoding = safeEncoding(this.encoding);
        return dup;
    }
    
    public void ensure(final int length) {
        if (length > this.bytes.length) {
            final byte[] tmp = new byte[length + (length >>> 1)];
            System.arraycopy(this.bytes, this.begin, tmp, 0, this.realSize);
            this.bytes = tmp;
        }
    }
    
    public ByteList makeShared(final int index, final int len) {
        final ByteList shared = new ByteList(this.bytes, this.encoding);
        shared.realSize = len;
        shared.begin = this.begin + index;
        return shared;
    }
    
    public void view(final int index, final int len) {
        this.realSize = len;
        this.begin += index;
    }
    
    public void unshare() {
        this.unshare(this.realSize);
    }
    
    public void unshare(final int length) {
        final byte[] tmp = new byte[length];
        System.arraycopy(this.bytes, this.begin, tmp, 0, Math.min(this.realSize, length));
        this.bytes = tmp;
        this.begin = 0;
    }
    
    public void invalidate() {
        this.hash = 0;
        this.stringValue = null;
    }
    
    public void prepend(final byte b) {
        this.grow(1);
        System.arraycopy(this.bytes, this.begin + 0, this.bytes, this.begin + 1, this.realSize);
        this.bytes[this.begin + 0] = b;
        ++this.realSize;
    }
    
    public ByteList append(final byte b) {
        this.grow(1);
        this.bytes[this.begin + this.realSize] = b;
        ++this.realSize;
        return this;
    }
    
    public ByteList append(final int b) {
        this.append((byte)b);
        return this;
    }
    
    public ByteList append(final InputStream input, final int length) throws IOException {
        this.grow(length);
        int read = 0;
        final int start = this.begin + this.realSize;
        while (read < length) {
            final int n = input.read(this.bytes, start + read, length - read);
            if (n == -1) {
                if (read == 0) {
                    throw new EOFException();
                }
                break;
            }
            else {
                read += n;
            }
        }
        this.realSize += read;
        return this;
    }
    
    public void append(final ByteBuffer buffer, final int len) {
        this.grow(len);
        buffer.get(this.bytes, this.begin + this.realSize, len);
        this.realSize += len;
    }
    
    public void append(final byte[] moreBytes) {
        assert moreBytes != null : "moreBytes is null";
        this.grow(moreBytes.length);
        System.arraycopy(moreBytes, 0, this.bytes, this.begin + this.realSize, moreBytes.length);
        this.realSize += moreBytes.length;
    }
    
    public void append(final ByteList moreBytes) {
        this.append(moreBytes.bytes, moreBytes.begin, moreBytes.realSize);
    }
    
    public void append(final ByteList moreBytes, final int index, final int len) {
        this.append(moreBytes.bytes, moreBytes.begin + index, len);
    }
    
    public void append(final byte[] moreBytes, final int start, final int len) {
        assert moreBytes != null : "moreBytes is null";
        assert len >= 0 && moreBytes.length - start >= len : "Bad length";
        this.grow(len);
        System.arraycopy(moreBytes, start, this.bytes, this.begin + this.realSize, len);
        this.realSize += len;
    }
    
    public void realloc(final int length) {
        assert length >= 0 : "Invalid length";
        assert length >= this.realSize : "length is too small";
        final byte[] tmp = new byte[length];
        System.arraycopy(this.bytes, 0, tmp, 0, this.realSize);
        this.bytes = tmp;
    }
    
    public int length() {
        return this.realSize;
    }
    
    public void length(final int newLength) {
        this.grow(newLength - this.realSize);
        this.realSize = newLength;
    }
    
    public int lengthEnc() {
        return this.encoding.strLength(this.bytes, this.begin, this.begin + this.realSize);
    }
    
    public int get(final int index) {
        assert index >= 0 : "index must be positive";
        return this.bytes[this.begin + index];
    }
    
    public int getEnc(final int index) {
        return this.encoding.strCodeAt(this.bytes, this.begin, this.begin + this.realSize, index);
    }
    
    public void set(final int index, final int b) {
        assert index >= 0 : "index must be positive";
        assert this.begin + index < this.begin + this.realSize : "index is too large";
        this.bytes[this.begin + index] = (byte)b;
    }
    
    @Deprecated
    public void replace(final byte[] newBytes) {
        assert newBytes != null;
        this.bytes = newBytes;
        this.realSize = newBytes.length;
    }
    
    public void unsafeReplace(final int beg, final int len, final ByteList nbytes) {
        this.unsafeReplace(beg, len, nbytes.bytes, nbytes.begin, nbytes.realSize);
    }
    
    public void unsafeReplace(final int beg, final int len, final byte[] buf) {
        this.unsafeReplace(beg, len, buf, 0, buf.length);
    }
    
    public void unsafeReplace(final int beg, final int len, final byte[] nbytes, final int index, final int count) {
        this.grow(count - len);
        final int newSize = this.realSize + count - len;
        System.arraycopy(this.bytes, beg + len, this.bytes, beg + count, this.realSize - (len + beg));
        System.arraycopy(nbytes, index, this.bytes, beg, count);
        this.realSize = newSize;
    }
    
    public void replace(final int beg, final int len, final ByteList nbytes) {
        this.replace(beg, len, nbytes.bytes, nbytes.begin, nbytes.realSize);
    }
    
    public void replace(final int beg, final int len, final byte[] buf) {
        this.replace(beg, len, buf, 0, buf.length);
    }
    
    public void replace(final int beg, final int len, final byte[] nbytes, final int index, final int count) {
        this.unsafeReplace(beg, len, nbytes, index, count);
    }
    
    public void insert(final int index, final int b) {
        this.grow(1);
        System.arraycopy(this.bytes, index, this.bytes, index + 1, this.realSize - index);
        this.bytes[index] = (byte)b;
        ++this.realSize;
    }
    
    public int indexOf(final int c) {
        return this.indexOf(c, 0);
    }
    
    public int indexOf(final int c, int pos) {
        if (c > 255) {
            return -1;
        }
        byte b;
        int size;
        byte[] buf;
        for (b = (byte)(c & 0xFF), size = this.begin + this.realSize, buf = this.bytes, pos += this.begin; pos < size && buf[pos] != b; ++pos) {}
        return (pos < size) ? (pos - this.begin) : -1;
    }
    
    public int indexOf(final ByteList find) {
        return this.indexOf(find, 0);
    }
    
    public int indexOf(final ByteList find, final int i) {
        return indexOf(this.bytes, this.begin, this.realSize, find.bytes, find.begin, find.realSize, i);
    }
    
    static int indexOf(final byte[] source, final int sourceOffset, final int sourceCount, final byte[] target, final int targetOffset, final int targetCount, int fromIndex) {
        if (fromIndex >= sourceCount) {
            return (targetCount == 0) ? sourceCount : -1;
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (targetCount == 0) {
            return fromIndex;
        }
        final byte first = target[targetOffset];
        for (int max = sourceOffset + (sourceCount - targetCount), i = sourceOffset + fromIndex; i <= max; ++i) {
            if (source[i] != first) {
                while (++i <= max && source[i] != first) {}
            }
            if (i <= max) {
                int j = i + 1;
                final int end = j + targetCount - 1;
                for (int k = targetOffset + 1; j < end && source[j] == target[k]; ++j, ++k) {}
                if (j == end) {
                    return i - sourceOffset;
                }
            }
        }
        return -1;
    }
    
    public int lastIndexOf(final int c) {
        return this.lastIndexOf(c, this.realSize - 1);
    }
    
    public int lastIndexOf(final int c, int pos) {
        if (c > 255) {
            return -1;
        }
        final byte b = (byte)(c & 0xFF);
        final int size = this.begin + this.realSize;
        pos += this.begin;
        final byte[] buf = this.bytes;
        if (pos >= size) {
            pos = size;
        }
        else {
            ++pos;
        }
        while (--pos >= this.begin && buf[pos] != b) {}
        return pos - this.begin;
    }
    
    public int lastIndexOf(final ByteList find) {
        return this.lastIndexOf(find, this.realSize);
    }
    
    public int lastIndexOf(final ByteList find, final int pos) {
        return lastIndexOf(this.bytes, this.begin, this.realSize, find.bytes, find.begin, find.realSize, pos);
    }
    
    static int lastIndexOf(final byte[] source, final int sourceOffset, final int sourceCount, final byte[] target, final int targetOffset, final int targetCount, int fromIndex) {
        final int rightIndex = sourceCount - targetCount;
        if (fromIndex < 0) {
            return -1;
        }
        if (fromIndex > rightIndex) {
            fromIndex = rightIndex;
        }
        if (targetCount == 0) {
            return fromIndex;
        }
        final int strLastIndex = targetOffset + targetCount - 1;
        final byte strLastChar = target[strLastIndex];
        final int min = sourceOffset + targetCount - 1;
        int i = min + fromIndex;
    Label_0062:
        while (true) {
            if (i >= min && source[i] != strLastChar) {
                --i;
            }
            else {
                if (i < min) {
                    return -1;
                }
                int j = i - 1;
                final int start = j - (targetCount - 1);
                int k = strLastIndex - 1;
                while (j > start) {
                    if (source[j--] != target[k--]) {
                        --i;
                        continue Label_0062;
                    }
                }
                return start - sourceOffset + 1;
            }
        }
    }
    
    public boolean startsWith(final ByteList other, final int toffset) {
        if (this.realSize == 0) {
            return false;
        }
        final byte[] ta = this.bytes;
        int to = this.begin + toffset;
        final byte[] pa = other.bytes;
        int po = other.begin;
        int pc = other.realSize;
        while (--pc >= 0) {
            if (ta[to++] != pa[po++]) {
                return false;
            }
        }
        return true;
    }
    
    public boolean startsWith(final ByteList other) {
        return this.startsWith(other, 0);
    }
    
    public boolean endsWith(final ByteList other) {
        return this.startsWith(other, this.realSize - other.realSize);
    }
    
    public boolean equals(final Object other) {
        return other instanceof ByteList && this.equal((ByteList)other);
    }
    
    public boolean equal(final ByteList other) {
        if (other == this) {
            return true;
        }
        if (this.hash != 0 && other.hash != 0 && this.hash != other.hash) {
            return false;
        }
        int last;
        if ((last = this.realSize) == other.realSize) {
            final byte[] buf = this.bytes;
            final byte[] otherBuf = other.bytes;
            int first = -1;
            while (--last > first && buf[this.begin + last] == otherBuf[other.begin + last] && ++first < last && buf[this.begin + first] == otherBuf[other.begin + first]) {}
            return first >= last;
        }
        return false;
    }
    
    public boolean sample_equals(final Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof ByteList) {
            final ByteList b = (ByteList)other;
            final int size;
            if ((size = this.realSize) == b.realSize) {
                final byte[] buf = this.bytes;
                int first = -1;
                int last = size + 1 & 0xFFFFFFFE;
                do {
                    last -= 2;
                    if (last < 0 || buf[this.begin + last] != b.bytes[b.begin + last]) {
                        break;
                    }
                    first += 2;
                } while (first < size && buf[this.begin + first] == b.bytes[b.begin + first]);
                return last < 0 || first == size;
            }
        }
        return false;
    }
    
    public int compareTo(final Object other) {
        return this.cmp((ByteList)other);
    }
    
    public int cmp(final ByteList other) {
        if (other == this) {
            return 0;
        }
        final int size = this.realSize;
        final int len = Math.min(size, other.realSize);
        int offset = -1;
        while (++offset < len && this.bytes[this.begin + offset] == other.bytes[other.begin + offset]) {}
        if (offset < len) {
            return ((this.bytes[this.begin + offset] & 0xFF) > (other.bytes[other.begin + offset] & 0xFF)) ? 1 : -1;
        }
        return (size == other.realSize) ? 0 : ((size == len) ? -1 : 1);
    }
    
    public int caseInsensitiveCmp(final ByteList other) {
        if (other == this) {
            return 0;
        }
        final int size = this.realSize;
        final int len = Math.min(size, other.realSize);
        final int other_begin = other.begin;
        final byte[] other_bytes = other.bytes;
        int offset = -1;
        while (++offset < len) {
            final int myCharIgnoreCase = AsciiTables.ToLowerCaseTable[this.bytes[this.begin + offset] & 0xFF];
            final int otherCharIgnoreCase = AsciiTables.ToLowerCaseTable[other_bytes[other_begin + offset] & 0xFF];
            if (myCharIgnoreCase < otherCharIgnoreCase) {
                return -1;
            }
            if (myCharIgnoreCase > otherCharIgnoreCase) {
                return 1;
            }
        }
        return (size == other.realSize) ? 0 : ((size == len) ? -1 : 1);
    }
    
    public byte[] unsafeBytes() {
        return this.bytes;
    }
    
    public byte[] bytes() {
        final byte[] newBytes = new byte[this.realSize];
        System.arraycopy(this.bytes, this.begin, newBytes, 0, this.realSize);
        return newBytes;
    }
    
    public int begin() {
        return this.begin;
    }
    
    private void grow(final int increaseRequested) {
        if (increaseRequested < 0) {
            return;
        }
        final int newSize = this.realSize + increaseRequested;
        if (newSize > this.bytes.length - this.begin) {
            final byte[] newBytes = new byte[newSize + (newSize >> 1)];
            if (this.bytes.length != 0) {
                System.arraycopy(this.bytes, this.begin, newBytes, 0, this.realSize);
            }
            this.bytes = newBytes;
            this.begin = 0;
        }
    }
    
    public int hashCode() {
        if (this.hash != 0) {
            return this.hash;
        }
        int key = 0;
        for (int index = this.begin, end = this.begin + this.realSize; index < end; key = (key << 16) + (key << 6) - key + this.bytes[index++]) {}
        key += key >> 5;
        return this.hash = key;
    }
    
    public String toString() {
        if (this.stringValue == null) {
            this.stringValue = decode(this.bytes, this.begin, this.realSize, "ISO-8859-1");
        }
        return this.stringValue;
    }
    
    public static ByteList create(final CharSequence s) {
        return new ByteList(plain(s), false);
    }
    
    public static byte[] plain(final CharSequence s) {
        if (s instanceof String) {
            return encode(s, "ISO-8859-1");
        }
        final byte[] bytes = new byte[s.length()];
        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = (byte)s.charAt(i);
        }
        return bytes;
    }
    
    public static byte[] plain(final char[] s) {
        final byte[] bytes = new byte[s.length];
        for (int i = 0; i < s.length; ++i) {
            bytes[i] = (byte)s[i];
        }
        return bytes;
    }
    
    public static char[] plain(final byte[] b, final int start, final int length) {
        assert b != null : "byte array cannot be null";
        assert start >= 0 && start + length <= b.length : "Invalid start or start+length too long";
        final char[] chars = new char[length];
        for (int i = 0; i < length; ++i) {
            chars[i] = (char)(b[start + i] & 0xFF);
        }
        return chars;
    }
    
    public static char[] plain(final byte[] b) {
        assert b != null : "byte array cannot be null";
        final char[] chars = new char[b.length];
        for (int i = 0; i < b.length; ++i) {
            chars[i] = (char)(b[i] & 0xFF);
        }
        return chars;
    }
    
    public static String decode(final byte[] data, final int offset, final int length, final String charsetName) {
        return lookup(charsetName).decode(ByteBuffer.wrap(data, offset, length)).toString();
    }
    
    public static String decode(final byte[] data, final String charsetName) {
        return lookup(charsetName).decode(ByteBuffer.wrap(data)).toString();
    }
    
    public static byte[] encode(final CharSequence data, final String charsetName) {
        return lookup(charsetName).encode(CharBuffer.wrap(data)).array();
    }
    
    private static Charset lookup(final String alias) {
        Charset cs = ByteList.charsetsByAlias.get(alias);
        if (cs == null) {
            cs = Charset.forName(alias);
            ByteList.charsetsByAlias.putIfAbsent(alias, cs);
        }
        return cs;
    }
    
    public char charAt(final int ix) {
        return (char)(this.bytes[this.begin + ix] & 0xFF);
    }
    
    public CharSequence subSequence(final int start, final int end) {
        return new ByteList(this, start, end - start);
    }
    
    public static int memcmp(final byte[] first, final int firstStart, final int firstLen, final byte[] second, final int secondStart, final int secondLen) {
        if (first == second) {
            return 0;
        }
        final int len = Math.min(firstLen, secondLen);
        int offset = -1;
        while (++offset < len && first[firstStart + offset] == second[secondStart + offset]) {}
        if (offset < len) {
            return ((first[firstStart + offset] & 0xFF) > (second[secondStart + offset] & 0xFF)) ? 1 : -1;
        }
        return (firstLen == secondLen) ? 0 : ((firstLen == len) ? -1 : 1);
    }
    
    public static int memcmp(final byte[] first, final int firstStart, final byte[] second, final int secondStart, final int len) {
        if (first == second) {
            return 0;
        }
        int offset = -1;
        while (++offset < len && first[firstStart + offset] == second[secondStart + offset]) {}
        if (offset < len) {
            return ((first[firstStart + offset] & 0xFF) > (second[secondStart + offset] & 0xFF)) ? 1 : -1;
        }
        return 0;
    }
    
    public final byte[] getUnsafeBytes() {
        return this.bytes;
    }
    
    public final void setUnsafeBytes(final byte[] bytes) {
        assert bytes != null;
        this.bytes = bytes;
    }
    
    public final int getBegin() {
        return this.begin;
    }
    
    public final void setBegin(final int begin) {
        assert begin >= 0;
        this.begin = begin;
    }
    
    public final int getRealSize() {
        return this.realSize;
    }
    
    public final void setRealSize(final int realSize) {
        assert realSize >= 0;
        this.realSize = realSize;
    }
    
    public final Encoding getEncoding() {
        return this.encoding;
    }
    
    public final void setEncoding(final Encoding encoding) {
        assert encoding != null;
        this.encoding = safeEncoding(encoding);
    }
    
    public static Encoding safeEncoding(final Encoding incoming) {
        if (incoming == null) {
            return ASCIIEncoding.INSTANCE;
        }
        return incoming;
    }
    
    static {
        NULL_ARRAY = new byte[0];
        EMPTY_BYTELIST = new ByteList(0);
        charsetsByAlias = new ConcurrentHashMap<String, Charset>();
    }
}
