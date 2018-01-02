// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

public final class FastStringBuffer
{
    private static char[] logicalLiteral;
    private static char[] displayLiteral;
    public static final int NOTACHAR = 0;
    public static final int NOT_A_CHAR = 0;
    private char[] value;
    private int count;
    private int offset;
    static final String needingEscape = "\b\t\n\f\r\"\\";
    
    public FastStringBuffer() {
        this(16);
    }
    
    public FastStringBuffer(final int length) {
        this.value = new char[length];
        this.offset = 0;
        this.count = 0;
    }
    
    public FastStringBuffer(final String str) {
        this(str.length() + 16);
        this.append(str);
        this.offset = 0;
        this.count = str.length();
    }
    
    public FastStringBuffer(final char[] cArray) {
        this(cArray.length + 16);
        this.append(cArray);
        this.offset = 0;
        this.count = cArray.length;
    }
    
    public FastStringBuffer(final char[] cArray, int offset, final int len) {
        this(len + 16);
        this.append(cArray, offset, len);
        offset = 0;
        this.count = len;
    }
    
    public FastStringBuffer(final char c, final int nChars) {
        this(nChars);
        for (int i = 0; i < nChars; ++i) {
            this.value[i] = c;
        }
        this.count = nChars;
    }
    
    public void empty() {
        for (int i = 0; i < this.count; ++i) {
            this.value[i] = '\0';
        }
        this.count = 0;
        this.offset = 0;
    }
    
    public char[] value() {
        return this.value;
    }
    
    public char[] getValue() {
        return this.value;
    }
    
    public int length() {
        return this.count;
    }
    
    public int getLength() {
        return this.count;
    }
    
    public int capacity() {
        return this.value.length;
    }
    
    public int offset() {
        return this.offset;
    }
    
    public int getOffset() {
        return this.offset;
    }
    
    public void setOffset(final int offset) {
        this.offset = offset;
    }
    
    public void makeroom(final int minimumCapacity) {
        final int maxCapacity = this.value.length;
        if (minimumCapacity > maxCapacity) {
            int newCapacity = (maxCapacity + 1) * 2;
            if (minimumCapacity > newCapacity) {
                newCapacity = minimumCapacity;
            }
            final char[] newValue = new char[newCapacity];
            System.arraycopy(this.value, 0, newValue, 0, this.count);
            this.value = newValue;
        }
    }
    
    public void setLength(final int newLength) {
        if (newLength < 0) {
            throw new StringIndexOutOfBoundsException(newLength);
        }
        this.makeroom(newLength);
        if (this.count < newLength) {
            while (this.count < newLength) {
                this.value[this.count] = '\0';
                ++this.count;
            }
        }
        this.count = newLength;
    }
    
    public char charAt(final int index) {
        if (index < 0 || index >= this.count) {
            throw new StringIndexOutOfBoundsException(index);
        }
        this.offset = index;
        return this.value[index];
    }
    
    public char firstChar() {
        if (this.count <= 0) {
            return '\0';
        }
        this.offset = 0;
        return this.value[0];
    }
    
    public char lastChar() {
        if (this.count <= 0) {
            return '\0';
        }
        this.offset = this.count - 1;
        return this.value[this.offset];
    }
    
    public char currentChar() {
        if (this.offset < 0 || this.offset >= this.count) {
            return '\0';
        }
        return this.value[this.offset];
    }
    
    public char nextChar() {
        if (this.offset < -1 || this.offset >= this.count - 1) {
            return '\0';
        }
        return this.value[++this.offset];
    }
    
    public char priorChar() {
        if (this.offset < 1 || this.offset > this.count) {
            return '\0';
        }
        final char[] value = this.value;
        final int offset = this.offset - 1;
        this.offset = offset;
        return value[offset];
    }
    
    public char peekNextChar() {
        if (this.offset < -1 || this.offset >= this.count - 1) {
            return '\0';
        }
        return this.value[this.offset + 1];
    }
    
    public void getChars(final int srcBegin, final int srcEnd, final char[] dst, final int dstBegin) {
        if (srcBegin < 0 || srcBegin >= this.count) {
            throw new StringIndexOutOfBoundsException(srcBegin);
        }
        if (srcEnd < 0 || srcEnd > this.count) {
            throw new StringIndexOutOfBoundsException(srcEnd);
        }
        if (srcBegin < srcEnd) {
            System.arraycopy(this.value, srcBegin, dst, dstBegin, srcEnd - srcBegin);
        }
    }
    
    public void setCharAt(final int index, final char ch) {
        if (index < 0 || index >= this.count) {
            throw new StringIndexOutOfBoundsException(index);
        }
        this.value[index] = ch;
    }
    
    public void removeCharAt(final int index) {
        if (index < 0 || index >= this.count) {
            throw new StringIndexOutOfBoundsException(index);
        }
        final int len = this.count - index - 1;
        if (index < this.count - 1 && len > 0) {
            System.arraycopy(this.value, index + 1, this.value, index, len);
        }
        this.offset = index - 1;
        --this.count;
    }
    
    public void removeChar() {
        this.removeCharAt(this.offset);
    }
    
    public void removeCharsAt(final int index, final int removeCount) {
        if (index < 0 || index + removeCount >= this.count) {
            throw new StringIndexOutOfBoundsException(index);
        }
        if (index < this.count - removeCount) {
            System.arraycopy(this.value, index + removeCount, this.value, index, this.count - index - removeCount);
        }
        this.offset = index - removeCount;
        this.count -= removeCount;
    }
    
    public void removeChars(final int removeCount) {
        this.removeCharsAt(this.offset, removeCount);
    }
    
    public void replaceCharAt(final int index, final char c) {
        if (index < 0 || index >= this.count) {
            throw new StringIndexOutOfBoundsException(index);
        }
        this.value[index] = c;
    }
    
    public FastStringBuffer append(final Object obj) {
        return this.append(String.valueOf(obj));
    }
    
    public FastStringBuffer append(final char c) {
        this.makeroom(this.count + 1);
        this.value[this.count++] = c;
        return this;
    }
    
    public FastStringBuffer append(final char c, final int appendCount) {
        this.makeroom(this.count + appendCount);
        for (int i = 0; i < appendCount; ++i) {
            this.value[this.count++] = c;
        }
        return this;
    }
    
    public FastStringBuffer append(String str) {
        if (str == null) {
            str = String.valueOf(str);
        }
        final int len = str.length();
        this.makeroom(this.count + len);
        str.getChars(0, len, this.value, this.count);
        this.count += len;
        return this;
    }
    
    public FastStringBuffer append(final FastStringBuffer fsb) {
        if (fsb == null) {
            return this;
        }
        final int len = fsb.count;
        this.makeroom(this.count + len);
        fsb.getChars(0, len, this.value, this.count);
        this.count += len;
        return this;
    }
    
    public FastStringBuffer append(final char[] str) {
        final int len = str.length;
        this.makeroom(this.count + len);
        System.arraycopy(str, 0, this.value, this.count, len);
        this.count += len;
        return this;
    }
    
    public FastStringBuffer append(final char[] str, final int offset, final int len) {
        this.makeroom(this.count + len);
        System.arraycopy(str, offset, this.value, this.count, len);
        this.count += len;
        return this;
    }
    
    public FastStringBuffer insert(final int offset, final Object obj) {
        return this.insert(offset, String.valueOf(obj));
    }
    
    public FastStringBuffer insert(final int offset, final String str) {
        if (offset < 0 || offset > this.count) {
            throw new StringIndexOutOfBoundsException();
        }
        final int len = str.length();
        this.makeroom(this.count + len);
        System.arraycopy(this.value, offset, this.value, offset + len, this.count - offset);
        str.getChars(0, len, this.value, offset);
        this.count += len;
        return this;
    }
    
    public FastStringBuffer insert(final int offset, final char[] str) {
        if (offset < 0 || offset > this.count) {
            throw new StringIndexOutOfBoundsException();
        }
        final int len = str.length;
        this.makeroom(this.count + len);
        System.arraycopy(this.value, offset, this.value, offset + len, this.count - offset);
        System.arraycopy(str, 0, this.value, offset, len);
        this.count += len;
        return this;
    }
    
    public FastStringBuffer insert(final int offset, final boolean b) {
        return this.insert(offset, String.valueOf(b));
    }
    
    public FastStringBuffer insert(final int offset, final char c) {
        this.makeroom(this.count + 1);
        System.arraycopy(this.value, offset, this.value, offset + 1, this.count - offset);
        this.value[offset] = c;
        ++this.count;
        return this;
    }
    
    public int IndexOfSubString(final FastStringBuffer subStr, final int fromIndex) {
        return this.indexOf(subStr, fromIndex);
    }
    
    public int indexOf(final FastStringBuffer subStr, final int fromIndex) {
        final char[] v1 = this.value;
        final char[] v2 = subStr.value();
        final int max = this.count - subStr.length();
        int i = (fromIndex < 0) ? 0 : fromIndex;
    Label_0034:
        while (i <= max) {
            int n = subStr.length();
            int j = i;
            int k = 0;
            while (n-- != 0) {
                if (v1[j++] != v2[k++]) {
                    ++i;
                    continue Label_0034;
                }
            }
            return i;
        }
        return -1;
    }
    
    public int lastIndexOf(final FastStringBuffer subStr, final int fromIndex) {
        final char[] v1 = this.value;
        final char[] v2 = subStr.value();
        final int max = this.count - subStr.length();
        int i = fromIndex;
    Label_0025:
        while (i >= 0) {
            int n = subStr.length();
            int j = i;
            int k = 0;
            while (n-- != 0) {
                if (v1[j++] != v2[k++]) {
                    --i;
                    continue Label_0025;
                }
            }
            return i;
        }
        return -1;
    }
    
    public FastStringBuffer substring(int startPos, int endPos) {
        if (startPos < 0) {
            startPos = 0;
        }
        if (endPos >= this.count) {
            endPos = this.count - 1;
        }
        FastStringBuffer fsb;
        if (startPos >= endPos) {
            fsb = new FastStringBuffer();
        }
        else {
            fsb = new FastStringBuffer(this.value, startPos, endPos - startPos);
        }
        return fsb;
    }
    
    public String toString() {
        return new String(this.value, 0, this.count);
    }
    
    public char parseBackSlash() {
        char c = this.nextChar();
        final char cVal = displayToLiteral(c);
        if (cVal != '\0') {
            return cVal;
        }
        int radix = 0;
        int maxChars = 0;
        switch (c) {
            case 'U':
            case 'u': {
                radix = 16;
                maxChars = 4;
                break;
            }
            case '0': {
                radix = 8;
                maxChars = 3;
                c = this.peekNextChar();
                if (c == 'x' || c == 'X') {
                    radix = 16;
                    maxChars = 2;
                    ++this.offset;
                }
                else {
                    this.priorChar();
                }
                break;
            }
            default: {
                return c;
            }
        }
        final FastStringBuffer s = new FastStringBuffer(maxChars);
        for (int i = 0; i < maxChars; ++i) {
            c = Character.toUpperCase(this.nextChar());
            if (!Character.isDigit(c) && ((radix == 8 && c > '7') || (radix == 16 && (c < 'A' || c > 'F')))) {
                this.priorChar();
                break;
            }
            s.append(c);
        }
        return (char)Integer.parseInt(s.toString(), radix);
    }
    
    public FastStringBuffer parseLiteral(final char delimiter, final boolean allowDouble) {
        final FastStringBuffer rsb = new FastStringBuffer();
        for (char c = this.nextChar(); c != '\0'; c = this.nextChar()) {
            if (c == delimiter) {
                if (!allowDouble || this.peekNextChar() != c) {
                    break;
                }
                rsb.append(c);
                this.nextChar();
            }
            else {
                if (c == '\\') {
                    c = this.parseBackSlash();
                }
                rsb.append(c);
            }
        }
        return rsb;
    }
    
    public FastStringBuffer parseLiteral() {
        return this.parseLiteral(this.currentChar(), false);
    }
    
    public static FastStringBuffer expandDelimiters(final String sourceString, final String delimiters) {
        final int len = (sourceString == null) ? 0 : sourceString.length();
        final FastStringBuffer result = new FastStringBuffer(len);
        for (int i = 0; i < len; ++i) {
            final char c = sourceString.charAt(i);
            if (delimiters.indexOf(c) >= 0) {
                result.append(charToUnicodeEscape(c));
            }
            else {
                result.append(c);
            }
        }
        return result;
    }
    
    public FastStringBuffer normalizeDelimiters(final String delimiters) {
        final FastStringBuffer result = new FastStringBuffer(this.count);
        for (char c = this.firstChar(); c != '\0'; c = this.nextChar()) {
            if (c == '\\') {
                final int oldoffset = this.offset;
                final char cVal = this.parseBackSlash();
                if (delimiters.indexOf(cVal) >= 0) {
                    final char cLog = literalToDisplay(cVal);
                    if (cLog != '\0') {
                        result.append('\\');
                        result.append(cLog);
                        continue;
                    }
                    result.append(cVal);
                    continue;
                }
                else {
                    this.offset = oldoffset;
                }
            }
            result.append(c);
        }
        return result;
    }
    
    public static String charToUnicodeEscape(final char ch) {
        final char[] hexChars = { '\\', 'u', Hex.chars[ch >> 12 & '\u000f'], Hex.chars[ch >> 8 & '\u000f'], Hex.chars[ch >> 4 & '\u000f'], Hex.chars[ch & '\u000f'] };
        return new String(hexChars, 0, 6);
    }
    
    private static char displayToLiteral(final char c) {
        for (int i = 0; i < FastStringBuffer.displayLiteral.length; ++i) {
            if (c == FastStringBuffer.displayLiteral[i]) {
                return FastStringBuffer.logicalLiteral[i];
            }
        }
        return '\0';
    }
    
    private static char literalToDisplay(final char c) {
        for (int i = 0; i < FastStringBuffer.logicalLiteral.length; ++i) {
            if (c == FastStringBuffer.logicalLiteral[i]) {
                return FastStringBuffer.displayLiteral[i];
            }
        }
        return '\0';
    }
    
    public static char charFromString(final String s) {
        char c = '\0';
        if (s != null && s.length() >= 0) {
            if (s.length() == 1) {
                c = s.charAt(0);
            }
            else {
                final FastStringBuffer fsb = new FastStringBuffer(s);
                c = fsb.firstChar();
                if (c == '\\') {
                    c = fsb.parseBackSlash();
                }
            }
        }
        return c;
    }
    
    public static String stringFromChar(final char c) {
        final char cLog = literalToDisplay(c);
        String s;
        if (cLog != '\0') {
            s = String.valueOf("\\").concat(String.valueOf(cLog));
        }
        else if (c < ' ') {
            s = charToUnicodeEscape(c);
        }
        else {
            s = new String(new char[] { c });
        }
        return s;
    }
    
    public static FastStringBuffer sourceToText(final String source) {
        final FastStringBuffer text = new FastStringBuffer();
        final FastStringBuffer input = new FastStringBuffer(source);
        if (input.charAt(0) == '\"') {
            input.removeCharAt(0);
        }
        final int len = input.length();
        if (len > 0 && input.charAt(len - 1) == '\"') {
            input.removeCharAt(len - 1);
        }
        for (char ch = input.firstChar(); ch != '\0'; ch = input.nextChar()) {
            if (ch == '\\') {
                ch = input.parseBackSlash();
            }
            text.append(ch);
        }
        return text;
    }
    
    public static FastStringBuffer textToSource(final String text, final boolean hasEscapes) {
        final FastStringBuffer source = new FastStringBuffer(text.length() * 2 + 2);
        source.append("\"");
        for (int i = 0; i < text.length(); ++i) {
            final char ch = text.charAt(i);
            if (ch == '\\' && hasEscapes && i + 2 < text.length()) {
                char digit = text.charAt(i + 2);
                switch (text.charAt(i + 1)) {
                    case '0': {
                        if ((digit == 'x' || digit == 'X') && i + 3 < text.length()) {
                            digit = text.charAt(i + 3);
                        }
                    }
                    case 'U':
                    case 'u': {
                        if (digit < '0' || digit > '9') {
                            break;
                        }
                        source.append(ch);
                        continue;
                    }
                }
            }
            if ("\b\t\n\f\r\"\\".indexOf(ch) >= 0) {
                source.append("\\");
                switch (ch) {
                    case '\b': {
                        source.append('b');
                        break;
                    }
                    case '\t': {
                        source.append('t');
                        break;
                    }
                    case '\n': {
                        source.append('n');
                        break;
                    }
                    case '\f': {
                        source.append('f');
                        break;
                    }
                    case '\r': {
                        source.append('r');
                        break;
                    }
                    case '\\': {
                        source.append('\\');
                        if (hasEscapes && i + 1 < text.length() && text.charAt(i + 1) == '\\') {
                            source.append(text.charAt(++i));
                        }
                        break;
                    }
                    default: {
                        source.append(stringFromChar(ch));
                        break;
                    }
                }
            }
            else {
                source.append(stringFromChar(ch));
            }
        }
        source.append("\"");
        return source;
    }
    
    static {
        FastStringBuffer.logicalLiteral = new char[] { '\r', '\n', '\t', '\'', '\\' };
        FastStringBuffer.displayLiteral = new char[] { 'r', 'n', 't', '\'', '\\' };
    }
}
