// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIWebBrowserSetup extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IWEBBROWSERSETUP_IID_STR = "f15398a0-8018-11d3-af70-00a024ffc08c";
    public static final nsID NS_IWEBBROWSERSETUP_IID;
    public static final int SETUP_ALLOW_PLUGINS = 1;
    public static final int SETUP_ALLOW_JAVASCRIPT = 2;
    public static final int SETUP_ALLOW_META_REDIRECTS = 3;
    public static final int SETUP_ALLOW_SUBFRAMES = 4;
    public static final int SETUP_ALLOW_IMAGES = 5;
    public static final int SETUP_FOCUS_DOC_BEFORE_CONTENT = 6;
    public static final int SETUP_USE_GLOBAL_HISTORY = 256;
    public static final int SETUP_IS_CHROME_WRAPPER = 7;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 1;
        NS_IWEBBROWSERSETUP_IID = new nsID("f15398a0-8018-11d3-af70-00a024ffc08c");
    }
    
    public nsIWebBrowserSetup(final int n) {
        super(n);
    }
    
    public int SetProperty(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, n2);
    }
}
