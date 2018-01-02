// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.OS;

public class IAccessible extends IDispatch
{
    public IAccessible(final int n) {
        super(n);
    }
    
    public int get_accParent(final int n) {
        return OS.VtblCall(7, this.address, n);
    }
    
    public int get_accChildCount(final int n) {
        return OS.VtblCall(8, this.address, n);
    }
    
    public int get_accChild(final int n, final int n2) {
        return COM.VtblCall_VARIANTP(9, this.address, n, n2);
    }
    
    public int get_accName(final int n, final int n2) {
        return COM.VtblCall_VARIANTP(10, this.address, n, n2);
    }
    
    public int get_accValue(final int n, final int n2) {
        return COM.VtblCall_VARIANTP(11, this.address, n, n2);
    }
    
    public int get_accDescription(final int n, final int n2) {
        return COM.VtblCall_VARIANTP(12, this.address, n, n2);
    }
    
    public int get_accRole(final int n, final int n2) {
        return COM.VtblCall_VARIANTP(13, this.address, n, n2);
    }
    
    public int get_accState(final int n, final int n2) {
        return COM.VtblCall_VARIANTP(14, this.address, n, n2);
    }
    
    public int get_accHelp(final int n, final int n2) {
        return COM.VtblCall_VARIANTP(15, this.address, n, n2);
    }
    
    public int get_accHelpTopic(final int n, final int n2, final int n3) {
        return COM.VtblCall_PVARIANTP(16, this.address, n, n2, n3);
    }
    
    public int get_accKeyboardShortcut(final int n, final int n2) {
        return COM.VtblCall_VARIANTP(17, this.address, n, n2);
    }
    
    public int get_accFocus(final int n) {
        return OS.VtblCall(18, this.address, n);
    }
    
    public int get_accSelection(final int n) {
        return OS.VtblCall(19, this.address, n);
    }
    
    public int get_accDefaultAction(final int n, final int n2) {
        return COM.VtblCall_VARIANTP(20, this.address, n, n2);
    }
    
    public int accSelect(final int n, final int n2) {
        return COM.VtblCall_IVARIANT(21, this.address, n, n2);
    }
    
    public int accLocation(final int n, final int n2, final int n3, final int n4, final int n5) {
        return COM.VtblCall_PPPPVARIANT(22, this.address, n, n2, n3, n4, n5);
    }
    
    public int accNavigate(final int n, final int n2, final int n3) {
        return COM.VtblCall_IVARIANTP(23, this.address, n, n2, n3);
    }
    
    public int accHitTest(final int n, final int n2, final int n3) {
        return OS.VtblCall(24, this.address, n, n2, n3);
    }
    
    public int accDoDefaultAction(final int n) {
        return COM.VtblCall_VARIANT(25, this.address, n);
    }
    
    public int put_accName(final int n, final int n2) {
        return COM.VtblCall_VARIANTP(26, this.address, n, n2);
    }
    
    public int put_accValue(final int n, final int n2) {
        return COM.VtblCall_VARIANTP(27, this.address, n, n2);
    }
}
