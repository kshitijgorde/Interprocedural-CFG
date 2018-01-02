// 
// Decompiled by Procyon v0.5.30
// 

package daeja.ji;

public class jiex1
{
    public static native int jiCheck();
    
    public static native int jiSetHook(final String p0, final int p1, final String p2);
    
    public static native int jiCreateFilePipe(final String p0, final String p1);
    
    public static native int addShutdownCallback(final Object p0, final int p1, final Object p2, final Object p3) throws UnsatisfiedLinkError;
    
    public static native void removeShutdownCallback(final Object p0, final int p1, final Object p2) throws UnsatisfiedLinkError;
    
    public static native boolean checkOLEACCDependency() throws UnsatisfiedLinkError;
    
    public static native int getWindowHandleFromRect(final String p0, final int p1, final int p2, final int p3, final int p4);
    
    public static native int jiReleaseHook(final String p0, final String p1, final int p2);
    
    public static native boolean jiResetHook(final int p0);
    
    public static native int jiGetTopWindow();
    
    public static native boolean jiCopyToClipboard(final String p0, final int p1, final int p2, final int p3);
    
    public static native boolean jiCopyTextToClipboard(final String p0, final boolean p1);
    
    public static native boolean jiGetTextFromClipboard(final String p0);
    
    public static native boolean jiEmptyClipboard();
    
    public static native String jiExtReleaseAllPipes();
    
    public static native boolean addToStartup(final String p0, final String p1, final String p2, final String p3, final String p4) throws UnsatisfiedLinkError;
    
    public static native boolean removeFromStartup(final String p0, final String p1) throws UnsatisfiedLinkError;
    
    public static native boolean installedInStartup(final String p0, final String p1, final String p2) throws UnsatisfiedLinkError;
    
    public static native String getBrowserExe() throws UnsatisfiedLinkError;
    
    public static native boolean launchQuickstartWindow(final int p0, final Object p1, final String p2) throws UnsatisfiedLinkError;
    
    public static native int setDebugging(final boolean p0) throws UnsatisfiedLinkError;
    
    public static native void closeWindow(final int p0, final int p1) throws UnsatisfiedLinkError;
    
    public static native boolean isInternetExplorer7(final int p0) throws UnsatisfiedLinkError;
    
    public static native boolean isWindowsXPSP2OrGreater() throws UnsatisfiedLinkError;
    
    public static native long getModuleHandle(final String p0) throws UnsatisfiedLinkError;
    
    public static native long loadLibrary(final String p0, final boolean p1) throws UnsatisfiedLinkError;
    
    public static native boolean freeLibrary(final long p0) throws UnsatisfiedLinkError;
    
    public static native boolean registryKeyExists_LOCAL_MACHINE(final String p0) throws UnsatisfiedLinkError;
    
    public static native boolean registryKeyDelete_LOCAL_MACHINE(final String p0) throws UnsatisfiedLinkError;
    
    public static native int getRLEInts(final byte[] p0, final int p1, final int p2, final int p3, final int[] p4, final int p5, final int p6, final int p7) throws UnsatisfiedLinkError;
    
    public static native long openFile(final String p0, final boolean p1) throws UnsatisfiedLinkError;
    
    public static native void closeFile(final long p0) throws UnsatisfiedLinkError;
    
    public static native long fileLength(final long p0) throws UnsatisfiedLinkError;
    
    public static native long fileLengthAbs(final String p0) throws UnsatisfiedLinkError;
    
    public static native long getFilePointer(final long p0) throws UnsatisfiedLinkError;
    
    public static native void seekFile(final long p0, final long p1) throws UnsatisfiedLinkError;
    
    public static native String getLastErrorString() throws UnsatisfiedLinkError;
    
    public static native void writeFileBytes(final long p0, final byte[] p1, final int p2, final int p3, final boolean p4) throws UnsatisfiedLinkError;
    
    public static native void writeFileInts(final long p0, final int[] p1, final int p2, final int p3, final boolean p4) throws UnsatisfiedLinkError;
    
    public static native void writeFileByte(final long p0, final int p1) throws UnsatisfiedLinkError;
    
    public static native void writeFileShort(final long p0, final int p1) throws UnsatisfiedLinkError;
    
    public static native void writeFileInt(final long p0, final int p1) throws UnsatisfiedLinkError;
    
    public static native void writeFileLong(final long p0, final long p1) throws UnsatisfiedLinkError;
    
    public static native int readFileBytes(final long p0, final byte[] p1, final int p2, final int p3) throws UnsatisfiedLinkError;
    
    public static native int readFileInts(final long p0, final int[] p1, final int p2, final int p3, final boolean p4) throws UnsatisfiedLinkError;
    
    public static native int readFileByte(final long p0) throws UnsatisfiedLinkError;
    
    public static native int readFileShort(final long p0) throws UnsatisfiedLinkError;
    
    public static native int readFileInt(final long p0) throws UnsatisfiedLinkError;
    
    public static native int readFileLong(final long p0) throws UnsatisfiedLinkError;
    
    public static native void releaseFileResources() throws UnsatisfiedLinkError;
    
    public static native boolean fileExists(final String p0) throws UnsatisfiedLinkError;
    
    public static native boolean deleteFile(final String p0) throws UnsatisfiedLinkError;
    
    public static native boolean moveToDisk(final String p0) throws UnsatisfiedLinkError;
    
    public static native long fileLastModified(final long p0) throws UnsatisfiedLinkError;
    
    public static native long fileLastModifiedAbs(final String p0) throws UnsatisfiedLinkError;
    
    public static native boolean renameFile(final String p0, final String p1) throws UnsatisfiedLinkError;
    
    public static native void showMem() throws UnsatisfiedLinkError;
    
    public static native void purgeMem() throws UnsatisfiedLinkError;
    
    public static native void setParams(final int p0, final int p1, final int p2, final boolean p3, final boolean p4, final boolean p5, final boolean p6);
    
    public static native String getWindowsDirectory() throws UnsatisfiedLinkError;
    
    public static native boolean addFontResource(final String p0) throws UnsatisfiedLinkError;
    
    public static native boolean removeFontResource(final String p0) throws UnsatisfiedLinkError;
}
