// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIContextMenuListener extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_ICONTEXTMENULISTENER_IID_STR = "3478b6b0-3875-11d4-94ef-0020183bf181";
    public static final nsID NS_ICONTEXTMENULISTENER_IID;
    public static final int CONTEXT_NONE = 0;
    public static final int CONTEXT_LINK = 1;
    public static final int CONTEXT_IMAGE = 2;
    public static final int CONTEXT_DOCUMENT = 4;
    public static final int CONTEXT_TEXT = 8;
    public static final int CONTEXT_INPUT = 16;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 1;
        NS_ICONTEXTMENULISTENER_IID = new nsID("3478b6b0-3875-11d4-94ef-0020183bf181");
    }
    
    public nsIContextMenuListener(final int n) {
        super(n);
    }
    
    public int OnShowContextMenu(final int n, final int n2, final int n3) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, n2, n3);
    }
}
