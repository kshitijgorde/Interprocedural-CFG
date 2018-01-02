// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIWindowCreator2 extends nsIWindowCreator
{
    static final int LAST_METHOD_ID;
    public static final String NS_IWINDOWCREATOR2_IID_STR = "f673ec81-a4b0-11d6-964b-eb5a2bf216fc";
    public static final nsID NS_IWINDOWCREATOR2_IID;
    public static final int PARENT_IS_LOADING_OR_RUNNING_TIMEOUT = 1;
    
    static {
        LAST_METHOD_ID = nsIWindowCreator.LAST_METHOD_ID + 1;
        NS_IWINDOWCREATOR2_IID = new nsID("f673ec81-a4b0-11d6-964b-eb5a2bf216fc");
    }
    
    public nsIWindowCreator2(final int n) {
        super(n);
    }
    
    public int CreateChromeWindow2(final int n, final int n2, final int n3, final int n4, final int[] array, final int[] array2) {
        return XPCOM.VtblCall(nsIWindowCreator.LAST_METHOD_ID + 1, this.getAddress(), n, n2, n3, n4, array, array2);
    }
}
