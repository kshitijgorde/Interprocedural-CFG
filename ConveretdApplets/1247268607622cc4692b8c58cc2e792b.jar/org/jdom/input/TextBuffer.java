// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.input;

import org.jdom.Verifier;

class TextBuffer
{
    private static final String CVS_ID = "@(#) $RCSfile: TextBuffer.java,v $ $Revision: 1.10 $ $Date: 2007/11/10 05:29:00 $ $Name: jdom_1_1_1 $";
    private String prefixString;
    private char[] array;
    private int arraySize;
    
    TextBuffer() {
        this.array = new char[4096];
        this.arraySize = 0;
    }
    
    void append(final char[] source, final int start, final int count) {
        if (this.prefixString == null) {
            this.prefixString = new String(source, start, count);
        }
        else {
            this.ensureCapacity(this.arraySize + count);
            System.arraycopy(source, start, this.array, this.arraySize, count);
            this.arraySize += count;
        }
    }
    
    int size() {
        if (this.prefixString == null) {
            return 0;
        }
        return this.prefixString.length() + this.arraySize;
    }
    
    void clear() {
        this.arraySize = 0;
        this.prefixString = null;
    }
    
    boolean isAllWhitespace() {
        if (this.prefixString == null || this.prefixString.length() == 0) {
            return true;
        }
        for (int size = this.prefixString.length(), i = 0; i < size; ++i) {
            if (!Verifier.isXMLWhitespace(this.prefixString.charAt(i))) {
                return false;
            }
        }
        for (int i = 0; i < this.arraySize; ++i) {
            if (!Verifier.isXMLWhitespace(this.array[i])) {
                return false;
            }
        }
        return true;
    }
    
    public String toString() {
        if (this.prefixString == null) {
            return "";
        }
        String str = "";
        if (this.arraySize == 0) {
            str = this.prefixString;
        }
        else {
            str = new StringBuffer(this.prefixString.length() + this.arraySize).append(this.prefixString).append(this.array, 0, this.arraySize).toString();
        }
        return str;
    }
    
    private void ensureCapacity(final int csize) {
        final int capacity = this.array.length;
        if (csize > capacity) {
            final char[] old = this.array;
            int nsize;
            for (nsize = capacity; csize > nsize; nsize += capacity / 2) {}
            System.arraycopy(old, 0, this.array = new char[nsize], 0, this.arraySize);
        }
    }
}
