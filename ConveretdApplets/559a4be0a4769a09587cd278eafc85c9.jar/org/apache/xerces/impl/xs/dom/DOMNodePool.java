// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.dom;

import org.apache.xerces.dom.AttrNSImpl;
import org.apache.xerces.dom.TextImpl;

public final class DOMNodePool
{
    private static final int CHUNK_SHIFT = 8;
    private static final int CHUNK_SIZE = 256;
    private static final int CHUNK_MASK = 255;
    private static final int INITIAL_CHUNK_COUNT = 4;
    private ElementNSImpl[][] fElements;
    private int fElementIndex;
    private TextImpl[][] fTextNode;
    private int fTextNodeIndex;
    private AttrNSImpl[][] fAttrNode;
    private int fAttrNodeIndex;
    
    public DOMNodePool() {
        this.fElements = new ElementNSImpl[4][];
        this.fElementIndex = 0;
        this.fTextNode = new TextImpl[4][];
        this.fTextNodeIndex = 0;
        this.fAttrNode = new AttrNSImpl[4][];
        this.fAttrNodeIndex = 0;
    }
    
    public final ElementNSImpl getElementNode() {
        final int chunk = this.fElementIndex >> 8;
        final int index = this.fElementIndex & 0xFF;
        this.ensureElementsCapacity(chunk);
        if (this.fElements[chunk][index] == null) {
            this.fElements[chunk][index] = new ElementNSImpl();
        }
        ++this.fElementIndex;
        return this.fElements[chunk][index];
    }
    
    private void ensureElementsCapacity(final int chunk) {
        if (this.fElements.length <= chunk) {
            this.fElements = resize(this.fElements, this.fElements.length * 2);
        }
        else if (this.fElements[chunk] != null) {
            return;
        }
        this.fElements[chunk] = new ElementNSImpl[256];
    }
    
    private static ElementNSImpl[][] resize(final ElementNSImpl[][] array, final int newsize) {
        final ElementNSImpl[][] newarray = new ElementNSImpl[newsize][];
        System.arraycopy(array, 0, newarray, 0, array.length);
        return newarray;
    }
    
    public final TextImpl getTextNode() {
        final int chunk = this.fTextNodeIndex >> 8;
        final int index = this.fTextNodeIndex & 0xFF;
        this.ensureTextCapacity(chunk);
        if (this.fTextNode[chunk][index] == null) {
            this.fTextNode[chunk][index] = new TextImpl();
        }
        ++this.fTextNodeIndex;
        return this.fTextNode[chunk][index];
    }
    
    private void ensureTextCapacity(final int chunk) {
        if (this.fTextNode.length <= chunk) {
            this.fTextNode = resize(this.fTextNode, this.fTextNode.length * 2);
        }
        else if (this.fTextNode[chunk] != null) {
            return;
        }
        this.fTextNode[chunk] = new TextImpl[256];
    }
    
    private static TextImpl[][] resize(final TextImpl[][] array, final int newsize) {
        final TextImpl[][] newarray = new TextImpl[newsize][];
        System.arraycopy(array, 0, newarray, 0, array.length);
        return newarray;
    }
    
    public final AttrNSImpl getAttrNode() {
        final int chunk = this.fAttrNodeIndex >> 8;
        final int index = this.fAttrNodeIndex & 0xFF;
        this.ensureAttrsCapacity(chunk);
        if (this.fAttrNode[chunk][index] == null) {
            this.fAttrNode[chunk][index] = new AttrNSImpl();
        }
        ++this.fAttrNodeIndex;
        return this.fAttrNode[chunk][index];
    }
    
    private void ensureAttrsCapacity(final int chunk) {
        if (this.fAttrNode.length <= chunk) {
            this.fAttrNode = resize(this.fAttrNode, this.fAttrNode.length * 2);
        }
        else if (this.fAttrNode[chunk] != null) {
            return;
        }
        this.fAttrNode[chunk] = new AttrNSImpl[256];
    }
    
    private static AttrNSImpl[][] resize(final AttrNSImpl[][] array, final int newsize) {
        final AttrNSImpl[][] newarray = new AttrNSImpl[newsize][];
        System.arraycopy(array, 0, newarray, 0, array.length);
        return newarray;
    }
    
    public void reset() {
        this.fElementIndex = 0;
        this.fTextNodeIndex = 0;
        this.fAttrNodeIndex = 0;
    }
}
