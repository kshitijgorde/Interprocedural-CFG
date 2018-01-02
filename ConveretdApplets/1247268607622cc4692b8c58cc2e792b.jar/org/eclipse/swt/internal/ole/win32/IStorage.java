// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.OS;

public class IStorage extends IUnknown
{
    public IStorage(final int n) {
        super(n);
    }
    
    public int Commit(final int n) {
        return OS.VtblCall(9, this.address, n);
    }
    
    public int CopyTo(final int n, final GUID guid, final String[] array, final int n2) {
        if (array != null) {
            return -2147024809;
        }
        return COM.VtblCall(7, this.address, n, guid, 0, n2);
    }
    
    public int CreateStorage(final String s, final int n, final int n2, final int n3, final int[] array) {
        char[] charArray = null;
        if (s != null) {
            charArray = (String.valueOf(s) + "\u0000").toCharArray();
        }
        return COM.VtblCall(5, this.address, charArray, n, n2, n3, array);
    }
    
    public int CreateStream(final String s, final int n, final int n2, final int n3, final int[] array) {
        char[] charArray = null;
        if (s != null) {
            charArray = (String.valueOf(s) + "\u0000").toCharArray();
        }
        return COM.VtblCall(3, this.address, charArray, n, n2, n3, array);
    }
    
    public int DestroyElement(final String s) {
        char[] charArray = null;
        if (s != null) {
            charArray = (String.valueOf(s) + "\u0000").toCharArray();
        }
        return OS.VtblCall(12, this.address, charArray);
    }
    
    public int EnumElements(final int n, final int n2, final int n3, final int[] array) {
        return OS.VtblCall(11, this.address, n, n2, n3, array);
    }
    
    public int OpenStorage(final String s, final int n, final int n2, final String[] array, final int n3, final int[] array2) {
        char[] charArray = null;
        if (s != null) {
            charArray = (String.valueOf(s) + "\u0000").toCharArray();
        }
        if (array != null) {
            return -2147024809;
        }
        return COM.VtblCall(6, this.address, charArray, n, n2, 0, n3, array2);
    }
    
    public int OpenStream(final String s, final int n, final int n2, final int n3, final int[] array) {
        char[] charArray = null;
        if (s != null) {
            charArray = (String.valueOf(s) + "\u0000").toCharArray();
        }
        return COM.VtblCall(4, this.address, charArray, n, n2, n3, array);
    }
    
    public int RenameElement(final String s, final String s2) {
        char[] charArray = null;
        if (s != null) {
            charArray = (String.valueOf(s) + "\u0000").toCharArray();
        }
        char[] charArray2 = null;
        if (s2 != null) {
            charArray2 = (String.valueOf(s2) + "\u0000").toCharArray();
        }
        return COM.VtblCall(13, this.address, charArray, charArray2);
    }
    
    public int Revert() {
        return OS.VtblCall(10, this.address);
    }
    
    public int SetClass(final GUID guid) {
        return COM.VtblCall(15, this.address, guid);
    }
}
