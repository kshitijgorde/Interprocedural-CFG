// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIDocShell extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IDOCSHELL_IID_STR = "69e5de00-7b8b-11d3-af61-00a024ffc08c";
    public static final nsID NS_IDOCSHELL_IID;
    public static final int ENUMERATE_FORWARDS = 0;
    public static final int ENUMERATE_BACKWARDS = 1;
    public static final int APP_TYPE_UNKNOWN = 0;
    public static final int APP_TYPE_MAIL = 1;
    public static final int BUSY_FLAGS_NONE = 0;
    public static final int BUSY_FLAGS_BUSY = 1;
    public static final int BUSY_FLAGS_BEFORE_PAGE_LOAD = 2;
    public static final int BUSY_FLAGS_PAGE_LOADING = 4;
    public static final int LOAD_CMD_NORMAL = 1;
    public static final int LOAD_CMD_RELOAD = 2;
    public static final int LOAD_CMD_HISTORY = 4;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 51;
        NS_IDOCSHELL_IID = new nsID("69e5de00-7b8b-11d3-af61-00a024ffc08c");
    }
    
    public nsIDocShell(final int n) {
        super(n);
    }
    
    public int LoadURI(final int n, final int n2, final int n3, final int n4) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, n2, n3, n4);
    }
    
    public int LoadStream(final int n, final int n2, final int n3, final int n4, final int n5) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n, n2, n3, n4, n5);
    }
    
    public int InternalLoad(final int n, final int n2, final int n3, final int n4, final char[] array, final int n5, final int n6, final int n7, final int n8, final int n9, final int[] array2, final int[] array3) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), n, n2, n3, n4, array, n5, n6, n7, n8, n9, array2, array3);
    }
    
    public int CreateLoadInfo(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), array);
    }
    
    public int PrepareForNewContentModel() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress());
    }
    
    public int SetCurrentURI(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), n);
    }
    
    public int FireUnloadNotification() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress());
    }
    
    public int GetPresContext(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress(), array);
    }
    
    public int GetPresShell(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, this.getAddress(), array);
    }
    
    public int GetEldestPresShell(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 10, this.getAddress(), array);
    }
    
    public int GetContentViewer(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 11, this.getAddress(), array);
    }
    
    public int GetChromeEventHandler(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 12, this.getAddress(), array);
    }
    
    public int SetChromeEventHandler(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 13, this.getAddress(), n);
    }
    
    public int GetParentURIContentListener(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 14, this.getAddress(), array);
    }
    
    public int SetParentURIContentListener(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 15, this.getAddress(), n);
    }
    
    public int GetDocumentCharsetInfo(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 16, this.getAddress(), array);
    }
    
    public int SetDocumentCharsetInfo(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 17, this.getAddress(), n);
    }
    
    public int GetAllowPlugins(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 18, this.getAddress(), array);
    }
    
    public int SetAllowPlugins(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 19, this.getAddress(), n);
    }
    
    public int GetAllowJavascript(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 20, this.getAddress(), array);
    }
    
    public int SetAllowJavascript(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 21, this.getAddress(), n);
    }
    
    public int GetAllowMetaRedirects(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 22, this.getAddress(), array);
    }
    
    public int SetAllowMetaRedirects(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 23, this.getAddress(), n);
    }
    
    public int GetAllowSubframes(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 24, this.getAddress(), array);
    }
    
    public int SetAllowSubframes(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 25, this.getAddress(), n);
    }
    
    public int GetAllowImages(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 26, this.getAddress(), array);
    }
    
    public int SetAllowImages(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 27, this.getAddress(), n);
    }
    
    public int GetDocShellEnumerator(final int n, final int n2, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 28, this.getAddress(), n, n2, array);
    }
    
    public int GetAppType(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 29, this.getAddress(), array);
    }
    
    public int SetAppType(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 30, this.getAddress(), n);
    }
    
    public int GetAllowAuth(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 31, this.getAddress(), array);
    }
    
    public int SetAllowAuth(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 32, this.getAddress(), n);
    }
    
    public int GetZoom(final float[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 33, this.getAddress(), array);
    }
    
    public int SetZoom(final float n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 34, this.getAddress(), n);
    }
    
    public int GetMarginWidth(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 35, this.getAddress(), array);
    }
    
    public int SetMarginWidth(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 36, this.getAddress(), n);
    }
    
    public int GetMarginHeight(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 37, this.getAddress(), array);
    }
    
    public int SetMarginHeight(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 38, this.getAddress(), n);
    }
    
    public int GetHasFocus(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 39, this.getAddress(), array);
    }
    
    public int SetHasFocus(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 40, this.getAddress(), n);
    }
    
    public int GetCanvasHasFocus(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 41, this.getAddress(), array);
    }
    
    public int SetCanvasHasFocus(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 42, this.getAddress(), n);
    }
    
    public int TabToTreeOwner(final int n, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 43, this.getAddress(), n, array);
    }
    
    public int GetBusyFlags(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 44, this.getAddress(), array);
    }
    
    public int GetLoadType(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 45, this.getAddress(), array);
    }
    
    public int SetLoadType(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 46, this.getAddress(), n);
    }
    
    public int IsBeingDestroyed(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 47, this.getAddress(), array);
    }
    
    public int GetIsExecutingOnLoadHandler(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 48, this.getAddress(), array);
    }
    
    public int GetLayoutHistoryState(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 49, this.getAddress(), array);
    }
    
    public int SetLayoutHistoryState(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 50, this.getAddress(), n);
    }
    
    public int GetShouldSaveLayoutState(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 51, this.getAddress(), array);
    }
}
