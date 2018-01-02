// 
// Decompiled by Procyon v0.5.30
// 

package netscape.javascript;

import java.applet.Applet;

public final class JSObject
{
    private int a;
    
    private static native void initClass();
    
    public native Object getMember(final String p0);
    
    public native Object getSlot(final int p0);
    
    public native void setMember(final String p0, final Object p1);
    
    public native void setSlot(final int p0, final Object p1);
    
    public native void removeMember(final String p0);
    
    public native Object call(final String p0, final Object[] p1);
    
    public native Object eval(final String p0);
    
    public native String toString();
    
    public static native JSObject getWindow(final Applet p0);
    
    protected native void finalize();
    
    static {
        initClass();
    }
}
