// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIChannel extends nsIRequest
{
    static final int LAST_METHOD_ID;
    public static final String NS_ICHANNEL_IID_STR = "c63a055a-a676-4e71-bf3c-6cfa11082018";
    public static final nsID NS_ICHANNEL_IID;
    public static final int LOAD_DOCUMENT_URI = 65536;
    public static final int LOAD_RETARGETED_DOCUMENT_URI = 131072;
    public static final int LOAD_REPLACE = 262144;
    public static final int LOAD_INITIAL_DOCUMENT_URI = 524288;
    public static final int LOAD_TARGETED = 1048576;
    public static final int LOAD_CALL_CONTENT_SNIFFERS = 2097152;
    
    static {
        LAST_METHOD_ID = nsIRequest.LAST_METHOD_ID + 16;
        NS_ICHANNEL_IID = new nsID("c63a055a-a676-4e71-bf3c-6cfa11082018");
    }
    
    public nsIChannel(final int n) {
        super(n);
    }
    
    public int GetOriginalURI(final int[] array) {
        return XPCOM.VtblCall(nsIRequest.LAST_METHOD_ID + 1, this.getAddress(), array);
    }
    
    public int SetOriginalURI(final int n) {
        return XPCOM.VtblCall(nsIRequest.LAST_METHOD_ID + 2, this.getAddress(), n);
    }
    
    public int GetURI(final int[] array) {
        return XPCOM.VtblCall(nsIRequest.LAST_METHOD_ID + 3, this.getAddress(), array);
    }
    
    public int GetOwner(final int[] array) {
        return XPCOM.VtblCall(nsIRequest.LAST_METHOD_ID + 4, this.getAddress(), array);
    }
    
    public int SetOwner(final int n) {
        return XPCOM.VtblCall(nsIRequest.LAST_METHOD_ID + 5, this.getAddress(), n);
    }
    
    public int GetNotificationCallbacks(final int[] array) {
        return XPCOM.VtblCall(nsIRequest.LAST_METHOD_ID + 6, this.getAddress(), array);
    }
    
    public int SetNotificationCallbacks(final int n) {
        return XPCOM.VtblCall(nsIRequest.LAST_METHOD_ID + 7, this.getAddress(), n);
    }
    
    public int GetSecurityInfo(final int[] array) {
        return XPCOM.VtblCall(nsIRequest.LAST_METHOD_ID + 8, this.getAddress(), array);
    }
    
    public int GetContentType(final int n) {
        return XPCOM.VtblCall(nsIRequest.LAST_METHOD_ID + 9, this.getAddress(), n);
    }
    
    public int SetContentType(final int n) {
        return XPCOM.VtblCall(nsIRequest.LAST_METHOD_ID + 10, this.getAddress(), n);
    }
    
    public int GetContentCharset(final int n) {
        return XPCOM.VtblCall(nsIRequest.LAST_METHOD_ID + 11, this.getAddress(), n);
    }
    
    public int SetContentCharset(final int n) {
        return XPCOM.VtblCall(nsIRequest.LAST_METHOD_ID + 12, this.getAddress(), n);
    }
    
    public int GetContentLength(final int[] array) {
        return XPCOM.VtblCall(nsIRequest.LAST_METHOD_ID + 13, this.getAddress(), array);
    }
    
    public int SetContentLength(final int n) {
        return XPCOM.VtblCall(nsIRequest.LAST_METHOD_ID + 14, this.getAddress(), n);
    }
    
    public int Open(final int[] array) {
        return XPCOM.VtblCall(nsIRequest.LAST_METHOD_ID + 15, this.getAddress(), array);
    }
    
    public int AsyncOpen(final int n, final int n2) {
        return XPCOM.VtblCall(nsIRequest.LAST_METHOD_ID + 16, this.getAddress(), n, n2);
    }
}
