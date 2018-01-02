// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class TCHAR
{
    int codePage;
    public char[] chars;
    public byte[] bytes;
    int byteCount;
    public static final int sizeof;
    
    static {
        sizeof = (OS.IsUnicode ? 2 : 1);
    }
    
    public TCHAR(final int codePage, final int byteCount) {
        this.codePage = codePage;
        if (OS.IsUnicode) {
            this.chars = new char[byteCount];
        }
        else {
            this.byteCount = byteCount;
            this.bytes = new byte[byteCount];
        }
    }
    
    public TCHAR(final int n, final char c, final boolean b) {
        final char[] array2;
        if (b) {
            final char[] array;
            (array = new char[2])[0] = c;
        }
        else {
            array2 = new char[] { c };
        }
        this(n, array2, false);
    }
    
    public TCHAR(final int codePage, char[] chars, final boolean b) {
        this.codePage = codePage;
        final int length = chars.length;
        if (OS.IsUnicode) {
            if (b && (length == 0 || (length > 0 && chars[length - 1] != '\0'))) {
                final char[] array = new char[length + 1];
                System.arraycopy(chars, 0, array, 0, length);
                chars = array;
            }
            this.chars = chars;
        }
        else {
            final int n = (codePage != 0) ? codePage : 0;
            final int byteCount = length * 2 + (b ? 1 : 0);
            this.byteCount = byteCount;
            this.bytes = new byte[byteCount];
            this.byteCount = OS.WideCharToMultiByte(n, 0, chars, length, this.bytes, this.byteCount, null, null);
            if (b) {
                ++this.byteCount;
            }
        }
    }
    
    public TCHAR(final int n, final String s, final boolean b) {
        this(n, getChars(s, b), false);
    }
    
    static char[] getChars(final String s, final boolean b) {
        final int length = s.length();
        final char[] array = new char[length + (b ? 1 : 0)];
        s.getChars(0, length, array, 0);
        return array;
    }
    
    public int length() {
        if (OS.IsUnicode) {
            return this.chars.length;
        }
        return this.byteCount;
    }
    
    public int strlen() {
        if (OS.IsUnicode) {
            for (int i = 0; i < this.chars.length; ++i) {
                if (this.chars[i] == '\0') {
                    return i;
                }
            }
            return this.chars.length;
        }
        for (int j = 0; j < this.byteCount; ++j) {
            if (this.bytes[j] == 0) {
                return j;
            }
        }
        return this.byteCount;
    }
    
    public int tcharAt(final int n) {
        if (OS.IsUnicode) {
            return this.chars[n];
        }
        int n2 = this.bytes[n] & 0xFF;
        if (OS.IsDBCSLeadByte((byte)n2)) {
            n2 = (n2 << 8 | (this.bytes[n + 1] & 0xFF));
        }
        return n2;
    }
    
    public String toString() {
        return this.toString(0, this.length());
    }
    
    public String toString(final int n, final int n2) {
        if (OS.IsUnicode) {
            return new String(this.chars, n, n2);
        }
        byte[] bytes = this.bytes;
        if (n != 0) {
            bytes = new byte[n2];
            System.arraycopy(this.bytes, n, bytes, 0, n2);
        }
        final char[] array = new char[n2];
        return new String(array, 0, OS.MultiByteToWideChar((this.codePage != 0) ? this.codePage : 0, 1, bytes, n2, array, n2));
    }
}
