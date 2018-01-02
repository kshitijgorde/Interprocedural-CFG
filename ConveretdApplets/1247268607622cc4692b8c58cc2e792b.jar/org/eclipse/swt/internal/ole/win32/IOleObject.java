// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.SIZE;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.MSG;
import org.eclipse.swt.internal.win32.OS;

public class IOleObject extends IUnknown
{
    public IOleObject(final int n) {
        super(n);
    }
    
    public int Advise(final int n, final int[] array) {
        return OS.VtblCall(19, this.address, n, array);
    }
    
    public int Close(final int n) {
        return OS.VtblCall(6, this.address, n);
    }
    
    public int DoVerb(final int n, final MSG msg, final int n2, final int n3, final int n4, final RECT rect) {
        return COM.VtblCall(11, this.address, n, msg, n2, n3, n4, rect);
    }
    
    public int GetClientSite(final int[] array) {
        return OS.VtblCall(4, this.address, array);
    }
    
    public int GetExtent(final int n, final SIZE size) {
        return COM.VtblCall(18, this.address, n, size);
    }
    
    public int SetClientSite(final int n) {
        return OS.VtblCall(3, this.address, n);
    }
    
    public int SetExtent(final int n, final SIZE size) {
        return COM.VtblCall(17, this.address, n, size);
    }
    
    public int SetHostNames(final String s, final String s2) {
        char[] array = null;
        if (s != null) {
            final int length = s.length();
            array = new char[length + 1];
            s.getChars(0, length, array, 0);
        }
        char[] array2 = null;
        if (s2 != null) {
            final int length2 = s2.length();
            array2 = new char[length2 + 1];
            s2.getChars(0, length2, array2, 0);
        }
        return COM.VtblCall(5, this.address, array, array2);
    }
    
    public int Unadvise(final int n) {
        return OS.VtblCall(20, this.address, n);
    }
    
    public int Update() {
        return OS.VtblCall(13, this.address);
    }
}
