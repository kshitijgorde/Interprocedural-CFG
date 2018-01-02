// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

import org.eclipse.swt.internal.C;

public class nsEmbedString
{
    int handle;
    
    public nsEmbedString() {
        this.handle = XPCOM.nsEmbedString_new();
    }
    
    public nsEmbedString(final String s) {
        if (s != null) {
            final char[] array = new char[s.length() + 1];
            s.getChars(0, s.length(), array, 0);
            this.handle = XPCOM.nsEmbedString_new(array);
        }
    }
    
    public int getAddress() {
        return this.handle;
    }
    
    public String toString() {
        if (this.handle == 0) {
            return null;
        }
        final int nsEmbedString_Length = XPCOM.nsEmbedString_Length(this.handle);
        final int nsEmbedString_get = XPCOM.nsEmbedString_get(this.handle);
        final char[] array = new char[nsEmbedString_Length];
        C.memmove(array, nsEmbedString_get, nsEmbedString_Length * 2);
        return new String(array);
    }
    
    public void dispose() {
        if (this.handle == 0) {
            return;
        }
        XPCOM.nsEmbedString_delete(this.handle);
        this.handle = 0;
    }
}
