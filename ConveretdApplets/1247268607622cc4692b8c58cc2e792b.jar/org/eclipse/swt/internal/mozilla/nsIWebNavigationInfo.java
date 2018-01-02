// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIWebNavigationInfo extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IWEBNAVIGATIONINFO_IID_STR = "62a93afb-93a1-465c-84c8-0432264229de";
    public static final nsID NS_IWEBNAVIGATIONINFO_IID;
    public static final int UNSUPPORTED = 0;
    public static final int IMAGE = 1;
    public static final int PLUGIN = 2;
    public static final int OTHER = 32768;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 1;
        NS_IWEBNAVIGATIONINFO_IID = new nsID("62a93afb-93a1-465c-84c8-0432264229de");
    }
    
    public nsIWebNavigationInfo(final int n) {
        super(n);
    }
    
    public int IsTypeSupported(final int n, final int n2, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, n2, array);
    }
}
