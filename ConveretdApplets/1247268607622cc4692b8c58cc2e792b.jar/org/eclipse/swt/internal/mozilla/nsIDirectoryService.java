// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIDirectoryService extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IDIRECTORYSERVICE_IID_STR = "57a66a60-d43a-11d3-8cc2-00609792278c";
    public static final nsID NS_IDIRECTORYSERVICE_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 3;
        NS_IDIRECTORYSERVICE_IID = new nsID("57a66a60-d43a-11d3-8cc2-00609792278c");
    }
    
    public nsIDirectoryService(final int n) {
        super(n);
    }
    
    public int Init() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress());
    }
    
    public int RegisterProvider(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n);
    }
    
    public int UnregisterProvider(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), n);
    }
}
