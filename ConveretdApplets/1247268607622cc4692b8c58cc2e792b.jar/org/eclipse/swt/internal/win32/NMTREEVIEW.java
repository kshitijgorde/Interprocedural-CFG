// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class NMTREEVIEW
{
    public NMHDR hdr;
    public int action;
    public TVITEM itemOld;
    public TVITEM itemNew;
    public POINT ptDrag;
    public static final int sizeof;
    
    static {
        sizeof = OS.NMTREEVIEW_sizeof();
    }
    
    public NMTREEVIEW() {
        this.hdr = new NMHDR();
        this.itemOld = new TVITEM();
        this.itemNew = new TVITEM();
        this.ptDrag = new POINT();
    }
}
