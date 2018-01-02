// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.OS;

public class IDispatchEx extends IDispatch
{
    public IDispatchEx(final int n) {
        super(n);
    }
    
    public int GetDispID(final int n, final int n2, final int[] array) {
        return COM.VtblCall(7, this.address, n, n2, array);
    }
    
    public int InvokeEx(final int n, final int n2, final int n3, final DISPPARAMS dispparams, final int n4, final EXCEPINFO excepinfo, final int n5) {
        return COM.VtblCall(8, this.address, n, n2, n3, dispparams, n4, excepinfo, n5);
    }
    
    public int DeleteMemberByName(final int n, final int n2) {
        return OS.VtblCall(9, this.address, n, n2);
    }
    
    public int DeleteMemberByDispID(final int n) {
        return OS.VtblCall(10, this.address, n);
    }
    
    public int GetMemberProperties(final int n, final int n2, final int[] array) {
        return COM.VtblCall(11, this.address, n, n2, array);
    }
    
    public int GetMemberName(final int n, final int[] array) {
        return OS.VtblCall(12, this.address, n, array);
    }
    
    public int GetNextDispID(final int n, final int n2, final int[] array) {
        return COM.VtblCall(13, this.address, n, n2, array);
    }
    
    public int GetNameSpaceParent(final int[] array) {
        return OS.VtblCall(14, this.address, array);
    }
}
