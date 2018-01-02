// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.io.Serializable;

public class JBossStringBuilder implements Serializable, CharSequence
{
    private static final long serialVersionUID = 1874946609763446794L;
    protected char[] chars;
    protected int pos;
    
    public JBossStringBuilder() {
        this(16);
    }
    
    public JBossStringBuilder(final int capacity) {
        this.chars = new char[capacity];
    }
    
    public JBossStringBuilder(final String string) {
        this(string.length() + 16);
        this.append(string);
    }
    
    public JBossStringBuilder(final CharSequence charSequence) {
        this(charSequence.length() + 16);
        this.append(charSequence);
    }
    
    public JBossStringBuilder append(final Object object) {
        return this.append(String.valueOf(object));
    }
    
    public JBossStringBuilder append(String string) {
        if (string == null) {
            string = "null";
        }
        final int length = string.length();
        if (length == 0) {
            return this;
        }
        final int afterAppend = this.pos + length;
        if (afterAppend > this.chars.length) {
            this.expandCapacity(afterAppend);
        }
        string.getChars(0, length, this.chars, this.pos);
        this.pos = afterAppend;
        return this;
    }
    
    public JBossStringBuilder append(final StringBuffer buffer) {
        if (buffer == null) {
            return this.append("null");
        }
        final int length = buffer.length();
        if (length == 0) {
            return this;
        }
        final int afterAppend = this.pos + length;
        if (afterAppend > this.chars.length) {
            this.expandCapacity(afterAppend);
        }
        buffer.getChars(0, length, this.chars, this.pos);
        this.pos = afterAppend;
        return this;
    }
    
    public JBossStringBuilder append(final CharSequence charSequence) {
        if (charSequence == null) {
            return this.append("null");
        }
        final int length = charSequence.length();
        if (length == 0) {
            return this;
        }
        return this.append(charSequence, 0, charSequence.length());
    }
    
    public JBossStringBuilder append(final CharSequence charSequence, final int start, final int end) {
        if (charSequence == null) {
            return this.append("null");
        }
        if (start < 0 || end < 0 || start > end || start > charSequence.length()) {
            throw new IndexOutOfBoundsException("Invalid start=" + start + " end=" + end + " length=" + charSequence.length());
        }
        final int length = end - start;
        if (length == 0) {
            return this;
        }
        final int afterAppend = this.pos + length;
        if (afterAppend > this.chars.length) {
            this.expandCapacity(afterAppend);
        }
        for (int i = start; i < end; ++i) {
            this.chars[this.pos++] = charSequence.charAt(i);
        }
        this.pos = afterAppend;
        return this;
    }
    
    public JBossStringBuilder append(final char[] array) {
        if (array == null) {
            return this.append("null");
        }
        if (array.length == 0) {
            return this;
        }
        final String string = String.valueOf(array);
        return this.append(string);
    }
    
    public JBossStringBuilder append(final char[] array, final int offset, final int length) {
        if (array == null) {
            return this.append("null");
        }
        final int arrayLength = array.length;
        if (offset < 0 || length < 0 || offset + length > arrayLength) {
            throw new IndexOutOfBoundsException("Invalid offset=" + offset + " length=" + length + " array.length=" + arrayLength);
        }
        if (length == 0 || arrayLength == 0) {
            return this;
        }
        final String string = String.valueOf(array, offset, length);
        return this.append(string);
    }
    
    public JBossStringBuilder append(final boolean primitive) {
        final String string = String.valueOf(primitive);
        return this.append(string);
    }
    
    public JBossStringBuilder append(final char primitive) {
        final String string = String.valueOf(primitive);
        return this.append(string);
    }
    
    public JBossStringBuilder append(final int primitive) {
        final String string = String.valueOf(primitive);
        return this.append(string);
    }
    
    public JBossStringBuilder append(final long primitive) {
        final String string = String.valueOf(primitive);
        return this.append(string);
    }
    
    public JBossStringBuilder append(final float primitive) {
        final String string = String.valueOf(primitive);
        return this.append(string);
    }
    
    public JBossStringBuilder append(final double primitive) {
        final String string = String.valueOf(primitive);
        return this.append(string);
    }
    
    public JBossStringBuilder delete(final int start, final int end) {
        if (start < 0 || start > this.pos || start > end || end > this.pos) {
            throw new IndexOutOfBoundsException("Invalid start=" + start + " end=" + end + " length=" + this.pos);
        }
        if (start == end) {
            return this;
        }
        final int removed = end - start;
        System.arraycopy(this.chars, start + removed, this.chars, start, this.pos - end);
        this.pos -= removed;
        return this;
    }
    
    public JBossStringBuilder deleteCharAt(final int index) {
        return this.delete(index, 1);
    }
    
    public JBossStringBuilder replace(final int start, final int end, final String string) {
        this.delete(start, end);
        return this.insert(start, string);
    }
    
    public JBossStringBuilder insert(final int index, final char[] string) {
        return this.insert(index, string, 0, string.length);
    }
    
    public JBossStringBuilder insert(final int index, final char[] string, final int offset, final int len) {
        final int stringLength = string.length;
        if (index < 0 || index > this.pos || offset < 0 || len < 0 || offset + len > string.length) {
            throw new IndexOutOfBoundsException("Invalid index=" + index + " offset=" + offset + " len=" + len + " string.length=" + stringLength + " length=" + this.pos);
        }
        if (len == 0) {
            return this;
        }
        final int afterAppend = this.pos + len;
        if (afterAppend > this.chars.length) {
            this.expandCapacity(afterAppend);
        }
        System.arraycopy(this.chars, index, this.chars, index + stringLength, this.pos - index);
        System.arraycopy(string, offset, this.chars, index, len);
        this.pos = afterAppend;
        return this;
    }
    
    public JBossStringBuilder insert(final int offset, final Object object) {
        if (object == null) {
            return this.insert(offset, "null");
        }
        return this.insert(offset, String.valueOf(object));
    }
    
    public JBossStringBuilder insert(final int offset, String string) {
        if (offset < 0 || offset > this.pos) {
            throw new IndexOutOfBoundsException("Invalid offset=" + offset + " length=" + this.pos);
        }
        if (string == null) {
            string = "null";
        }
        final int stringLength = string.length();
        final int afterAppend = this.pos + stringLength;
        if (afterAppend > this.chars.length) {
            this.expandCapacity(afterAppend);
        }
        System.arraycopy(this.chars, offset, this.chars, offset + stringLength, this.pos - offset);
        string.getChars(0, stringLength, this.chars, offset);
        this.pos = afterAppend;
        return this;
    }
    
    public JBossStringBuilder insert(final int offset, final CharSequence charSequence) {
        if (charSequence == null) {
            return this.insert(offset, "null");
        }
        return this.insert(offset, charSequence, 0, charSequence.length());
    }
    
    public JBossStringBuilder insert(int offset, CharSequence charSequence, final int start, final int end) {
        if (charSequence == null) {
            charSequence = "null";
        }
        final int sequenceLength = charSequence.length();
        if (offset < 0 || offset > this.pos || start < 0 || end < 0 || start > sequenceLength || end > sequenceLength || start > end) {
            throw new IndexOutOfBoundsException("Invalid offset=" + offset + " start=" + start + " end=" + end + " sequence.length()=" + sequenceLength + " length=" + this.pos);
        }
        final int len = end - start;
        if (len == 0) {
            return this;
        }
        final int afterAppend = this.pos + len;
        if (afterAppend > this.chars.length) {
            this.expandCapacity(afterAppend);
        }
        System.arraycopy(this.chars, offset, this.chars, offset + sequenceLength, this.pos - offset);
        for (int i = start; i < end; ++i) {
            this.chars[offset++] = charSequence.charAt(i);
        }
        this.pos = afterAppend;
        return this;
    }
    
    public JBossStringBuilder insert(final int offset, final boolean primitive) {
        return this.insert(offset, String.valueOf(primitive));
    }
    
    public JBossStringBuilder insert(final int offset, final char primitive) {
        return this.insert(offset, String.valueOf(primitive));
    }
    
    public JBossStringBuilder insert(final int offset, final int primitive) {
        return this.insert(offset, String.valueOf(primitive));
    }
    
    public JBossStringBuilder insert(final int offset, final long primitive) {
        return this.insert(offset, String.valueOf(primitive));
    }
    
    public JBossStringBuilder insert(final int offset, final float primitive) {
        return this.insert(offset, String.valueOf(primitive));
    }
    
    public JBossStringBuilder insert(final int offset, final double primitive) {
        return this.insert(offset, String.valueOf(primitive));
    }
    
    public int indexOf(final String string) {
        return this.indexOf(string, 0);
    }
    
    public int indexOf(final String string, final int fromIndex) {
        return this.toString().indexOf(string, fromIndex);
    }
    
    public int lastIndexOf(final String string) {
        return this.lastIndexOf(string, 0);
    }
    
    public int lastIndexOf(final String string, final int fromIndex) {
        return this.toString().lastIndexOf(string, fromIndex);
    }
    
    public JBossStringBuilder reverse() {
        throw new NotImplementedException("FIXME: NYI");
    }
    
    public String toString() {
        return new String(this.chars, 0, this.pos);
    }
    
    public int length() {
        return this.pos;
    }
    
    public int capacity() {
        return this.chars.length;
    }
    
    public void ensureCapacity(final int minimum) {
        if (minimum < 0 || minimum < this.chars.length) {
            return;
        }
        this.expandCapacity(minimum);
    }
    
    public void trimToSize() {
        final char[] trimmed = new char[this.pos];
        System.arraycopy(this.chars, 0, trimmed, 0, this.pos);
        this.chars = trimmed;
    }
    
    public void setLength(final int newLength) {
        throw new NotImplementedException("FIXME: NYI");
    }
    
    public char charAt(final int index) {
        return this.chars[index];
    }
    
    public void getChars(final int srcBegin, final int srcEnd, final char[] dst, final int dstBegin) {
        if (srcBegin < 0 || dstBegin < 0 || srcBegin > srcEnd || srcEnd > this.pos || dstBegin + srcEnd - srcBegin > dst.length) {
            throw new IndexOutOfBoundsException("Invalid srcBegin=" + srcBegin + " srcEnd=" + srcEnd + " dstBegin=" + dstBegin + " dst.length=" + dst.length + " length=" + this.pos);
        }
        final int len = srcEnd - srcBegin;
        if (len == 0) {
            return;
        }
        System.arraycopy(this.chars, srcBegin, dst, dstBegin, len);
    }
    
    public void setCharAt(final int index, final char ch) {
        if (index < 0 || index > this.pos) {
            throw new IndexOutOfBoundsException("Invalid index=" + index + " length=" + this.pos);
        }
        this.chars[index] = ch;
    }
    
    public String substring(final int start) {
        return this.substring(start, this.pos);
    }
    
    public CharSequence subSequence(final int start, final int end) {
        return this.substring(start, end);
    }
    
    public String substring(final int start, final int end) {
        if (start < 0 || end < 0 || start > end || end > this.pos) {
            throw new IndexOutOfBoundsException("Invalid start=" + start + " end=" + end + " length=" + this.pos);
        }
        return new String(this.chars, start, end - start);
    }
    
    protected void expandCapacity(final int minimum) {
        int newSize = this.chars.length * 2;
        if (minimum > newSize) {
            newSize = minimum;
        }
        final char[] newChars = new char[newSize];
        System.arraycopy(this.chars, 0, newChars, 0, this.pos);
        this.chars = newChars;
    }
}
