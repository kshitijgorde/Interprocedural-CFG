// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.vboard;

import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;

final class s extends PlainDocument
{
    private s(final b b, final byte b2) {
    }
    
    public final void insertString(final int n, final String s, final AttributeSet set) {
        if (s.length() + this.getLength() <= 160) {
            super.insertString(n, s, set);
        }
    }
    
    s(final b b) {
        this(b, (byte)0);
    }
}
