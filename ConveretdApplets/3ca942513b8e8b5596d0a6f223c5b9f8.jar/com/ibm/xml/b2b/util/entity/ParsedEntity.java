// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.entity;

import com.ibm.xml.b2b.util.EncodingSupport;
import com.ibm.xml.b2b.util.XMLString;

public abstract class ParsedEntity extends XMLString
{
    public int startOffset;
    
    public abstract void release();
    
    public abstract void getContent(final XMLString p0);
    
    public abstract int decodeInvalidCharacter();
    
    public abstract boolean skipValidCharacter();
    
    public abstract boolean skipInitialNameCharacter();
    
    public abstract boolean skipNameCharacter();
    
    protected ParsedEntity() {
        this.startOffset = -1;
    }
    
    public void clear() {
        super.clear();
        this.startOffset = -1;
    }
    
    public void setValues(final byte[] array, final int startOffset, final int n, final EncodingSupport encodingSupport) {
        super.setValues(array, startOffset, n, encodingSupport);
        this.startOffset = startOffset;
    }
    
    public void setValues(final char[] array, final int startOffset, final int n) {
        super.setValues(array, startOffset, n);
        this.startOffset = startOffset;
    }
    
    public void setValues(final XMLString values) {
        super.setValues(values);
        this.startOffset = values.offset;
    }
}
