// 
// Decompiled by Procyon v0.5.30
// 

package daeja.ji;

public class jiqs
{
    public static native boolean checkLibrary() throws UnsatisfiedLinkError;
    
    public static native boolean isRunning() throws UnsatisfiedLinkError;
    
    public static native boolean lockWindow(final Object p0, final int p1, final int p2, final Object p3, final Object p4) throws UnsatisfiedLinkError;
    
    public static native void unlockWindow(final Object p0, final int p1, final Object p2) throws UnsatisfiedLinkError;
    
    public static native void setDebugging(final boolean p0) throws UnsatisfiedLinkError;
}
