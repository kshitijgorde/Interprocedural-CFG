// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIWindowCreator extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IWINDOWCREATOR_IID_STR = "30465632-a777-44cc-90f9-8145475ef999";
    public static final nsID NS_IWINDOWCREATOR_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 1;
        NS_IWINDOWCREATOR_IID = new nsID("30465632-a777-44cc-90f9-8145475ef999");
    }
    
    public nsIWindowCreator(final int n) {
        super(n);
    }
    
    public int CreateChromeWindow(final int n, final int n2, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, n2, array);
    }
}
