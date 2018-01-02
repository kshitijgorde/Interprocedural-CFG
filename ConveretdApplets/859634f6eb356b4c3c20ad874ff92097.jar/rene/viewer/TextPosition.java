// 
// Decompiled by Procyon v0.5.30
// 

package rene.viewer;

import rene.util.list.ListElement;

class TextPosition
{
    ListElement L;
    int LCount;
    int LPos;
    
    public TextPosition(final ListElement l, final int lCount, final int lPos) {
        this.L = l;
        this.LCount = lCount;
        this.LPos = lPos;
    }
    
    boolean equal(final TextPosition textPosition) {
        return textPosition.LCount == this.LCount && textPosition.LPos == this.LPos;
    }
    
    boolean before(final TextPosition textPosition) {
        return textPosition.LCount > this.LCount || (textPosition.LCount == this.LCount && textPosition.LPos > this.LPos);
    }
    
    void oneleft() {
        if (this.LPos > 0) {
            --this.LPos;
        }
    }
}
