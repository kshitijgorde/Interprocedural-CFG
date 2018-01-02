// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsILocalFile extends nsIFile
{
    static final int LAST_METHOD_ID;
    public static final String NS_ILOCALFILE_IID_STR = "aa610f20-a889-11d3-8c81-000064657374";
    public static final nsID NS_ILOCALFILE_IID;
    
    static {
        LAST_METHOD_ID = nsIFile.LAST_METHOD_ID + 17;
        NS_ILOCALFILE_IID = new nsID("aa610f20-a889-11d3-8c81-000064657374");
    }
    
    public nsILocalFile(final int n) {
        super(n);
    }
    
    public int InitWithPath(final int n) {
        return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 1, this.getAddress(), n);
    }
    
    public int InitWithNativePath(final int n) {
        return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 2, this.getAddress(), n);
    }
    
    public int InitWithFile(final int n) {
        return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 3, this.getAddress(), n);
    }
    
    public int GetFollowLinks(final int[] array) {
        return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 4, this.getAddress(), array);
    }
    
    public int SetFollowLinks(final int n) {
        return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 5, this.getAddress(), n);
    }
    
    public int OpenNSPRFileDesc(final int n, final int n2, final int[] array) {
        return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 6, this.getAddress(), n, n2, array);
    }
    
    public int OpenANSIFileDesc(final byte[] array, final int[] array2) {
        return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 7, this.getAddress(), array, array2);
    }
    
    public int Load(final int[] array) {
        return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 8, this.getAddress(), array);
    }
    
    public int GetDiskSpaceAvailable(final long[] array) {
        return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 9, this.getAddress(), array);
    }
    
    public int AppendRelativePath(final int n) {
        return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 10, this.getAddress(), n);
    }
    
    public int AppendRelativeNativePath(final int n) {
        return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 11, this.getAddress(), n);
    }
    
    public int GetPersistentDescriptor(final int n) {
        return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 12, this.getAddress(), n);
    }
    
    public int SetPersistentDescriptor(final int n) {
        return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 13, this.getAddress(), n);
    }
    
    public int Reveal() {
        return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 14, this.getAddress());
    }
    
    public int Launch() {
        return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 15, this.getAddress());
    }
    
    public int GetRelativeDescriptor(final int n, final int n2) {
        return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 16, this.getAddress(), n, n2);
    }
    
    public int SetRelativeDescriptor(final int n, final int n2) {
        return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 17, this.getAddress(), n, n2);
    }
}
