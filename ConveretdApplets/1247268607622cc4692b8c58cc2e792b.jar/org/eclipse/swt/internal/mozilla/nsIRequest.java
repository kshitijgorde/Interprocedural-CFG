// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIRequest extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IREQUEST_IID_STR = "ef6bfbd2-fd46-48d8-96b7-9f8f0fd387fe";
    public static final nsID NS_IREQUEST_IID;
    public static final int LOAD_NORMAL = 0;
    public static final int LOAD_BACKGROUND = 1;
    public static final int INHIBIT_CACHING = 128;
    public static final int INHIBIT_PERSISTENT_CACHING = 256;
    public static final int LOAD_BYPASS_CACHE = 512;
    public static final int LOAD_FROM_CACHE = 1024;
    public static final int VALIDATE_ALWAYS = 2048;
    public static final int VALIDATE_NEVER = 4096;
    public static final int VALIDATE_ONCE_PER_SESSION = 8192;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 10;
        NS_IREQUEST_IID = new nsID("ef6bfbd2-fd46-48d8-96b7-9f8f0fd387fe");
    }
    
    public nsIRequest(final int n) {
        super(n);
    }
    
    public int GetName(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n);
    }
    
    public int IsPending(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), array);
    }
    
    public int GetStatus(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), array);
    }
    
    public int Cancel(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), n);
    }
    
    public int Suspend() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress());
    }
    
    public int Resume() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress());
    }
    
    public int GetLoadGroup(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), array);
    }
    
    public int SetLoadGroup(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress(), n);
    }
    
    public int GetLoadFlags(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, this.getAddress(), array);
    }
    
    public int SetLoadFlags(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 10, this.getAddress(), n);
    }
}
