// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIFilePicker extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IFILEPICKER_IID_STR = "c47de916-1dd1-11b2-8141-82507fa02b21";
    public static final nsID NS_IFILEPICKER_IID;
    public static final int modeOpen = 0;
    public static final int modeSave = 1;
    public static final int modeGetFolder = 2;
    public static final int modeOpenMultiple = 3;
    public static final int returnOK = 0;
    public static final int returnCancel = 1;
    public static final int returnReplace = 2;
    public static final int filterAll = 1;
    public static final int filterHTML = 2;
    public static final int filterText = 4;
    public static final int filterImages = 8;
    public static final int filterXML = 16;
    public static final int filterXUL = 32;
    public static final int filterApps = 64;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 15;
        NS_IFILEPICKER_IID = new nsID("c47de916-1dd1-11b2-8141-82507fa02b21");
    }
    
    public nsIFilePicker(final int n) {
        super(n);
    }
    
    public int Init(final int n, final char[] array, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, array, n2);
    }
    
    public int AppendFilters(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n);
    }
    
    public int AppendFilter(final char[] array, final char[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), array, array2);
    }
    
    public int GetDefaultString(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), array);
    }
    
    public int SetDefaultString(final char[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), array);
    }
    
    public int GetDefaultExtension(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), array);
    }
    
    public int SetDefaultExtension(final char[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), array);
    }
    
    public int GetFilterIndex(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress(), array);
    }
    
    public int SetFilterIndex(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, this.getAddress(), n);
    }
    
    public int GetDisplayDirectory(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 10, this.getAddress(), array);
    }
    
    public int SetDisplayDirectory(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 11, this.getAddress(), n);
    }
    
    public int GetFile(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 12, this.getAddress(), array);
    }
    
    public int GetFileURL(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 13, this.getAddress(), array);
    }
    
    public int GetFiles(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 14, this.getAddress(), array);
    }
    
    public int Show(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 15, this.getAddress(), n);
    }
}
