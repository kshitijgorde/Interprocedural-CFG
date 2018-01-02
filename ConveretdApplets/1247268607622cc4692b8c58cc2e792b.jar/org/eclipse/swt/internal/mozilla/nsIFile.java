// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIFile extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IFILE_IID_STR = "c8c0a080-0868-11d3-915f-d9d889d48e3c";
    public static final nsID NS_IFILE_IID;
    public static final int NORMAL_FILE_TYPE = 0;
    public static final int DIRECTORY_TYPE = 1;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 45;
        NS_IFILE_IID = new nsID("c8c0a080-0868-11d3-915f-d9d889d48e3c");
    }
    
    public nsIFile(final int n) {
        super(n);
    }
    
    public int Append(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n);
    }
    
    public int AppendNative(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n);
    }
    
    public int Normalize() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress());
    }
    
    public int Create(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), n, n2);
    }
    
    public int GetLeafName(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), n);
    }
    
    public int SetLeafName(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), n);
    }
    
    public int GetNativeLeafName(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), n);
    }
    
    public int SetNativeLeafName(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress(), n);
    }
    
    public int CopyTo(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, this.getAddress(), n, n2);
    }
    
    public int CopyToNative(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 10, this.getAddress(), n, n2);
    }
    
    public int CopyToFollowingLinks(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 11, this.getAddress(), n, n2);
    }
    
    public int CopyToFollowingLinksNative(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 12, this.getAddress(), n, n2);
    }
    
    public int MoveTo(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 13, this.getAddress(), n, n2);
    }
    
    public int MoveToNative(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 14, this.getAddress(), n, n2);
    }
    
    public int Remove(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 15, this.getAddress(), n);
    }
    
    public int GetPermissions(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 16, this.getAddress(), array);
    }
    
    public int SetPermissions(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 17, this.getAddress(), n);
    }
    
    public int GetPermissionsOfLink(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 18, this.getAddress(), array);
    }
    
    public int SetPermissionsOfLink(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 19, this.getAddress(), n);
    }
    
    public int GetLastModifiedTime(final long[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 20, this.getAddress(), array);
    }
    
    public int SetLastModifiedTime(final long n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 21, this.getAddress(), n);
    }
    
    public int GetLastModifiedTimeOfLink(final long[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 22, this.getAddress(), array);
    }
    
    public int SetLastModifiedTimeOfLink(final long n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 23, this.getAddress(), n);
    }
    
    public int GetFileSize(final long[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 24, this.getAddress(), array);
    }
    
    public int SetFileSize(final long n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 25, this.getAddress(), n);
    }
    
    public int GetFileSizeOfLink(final long[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 26, this.getAddress(), array);
    }
    
    public int GetTarget(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 27, this.getAddress(), n);
    }
    
    public int GetNativeTarget(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 28, this.getAddress(), n);
    }
    
    public int GetPath(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 29, this.getAddress(), n);
    }
    
    public int GetNativePath(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 30, this.getAddress(), n);
    }
    
    public int Exists(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 31, this.getAddress(), array);
    }
    
    public int IsWritable(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 32, this.getAddress(), array);
    }
    
    public int IsReadable(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 33, this.getAddress(), array);
    }
    
    public int IsExecutable(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 34, this.getAddress(), array);
    }
    
    public int IsHidden(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 35, this.getAddress(), array);
    }
    
    public int IsDirectory(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 36, this.getAddress(), array);
    }
    
    public int IsFile(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 37, this.getAddress(), array);
    }
    
    public int IsSymlink(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 38, this.getAddress(), array);
    }
    
    public int IsSpecial(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 39, this.getAddress(), array);
    }
    
    public int CreateUnique(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 40, this.getAddress(), n, n2);
    }
    
    public int Clone(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 41, this.getAddress(), array);
    }
    
    public int Equals(final int n, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 42, this.getAddress(), n, array);
    }
    
    public int Contains(final int n, final int n2, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 43, this.getAddress(), n, n2, array);
    }
    
    public int GetParent(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 44, this.getAddress(), array);
    }
    
    public int GetDirectoryEntries(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 45, this.getAddress(), array);
    }
}
