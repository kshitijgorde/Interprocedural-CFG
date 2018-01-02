// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

public class Alignment
{
    public static final int LEFT = 1;
    public static final int CENTER = 2;
    public static final int RIGHT = 3;
    public static final int HSTRETCH = 4;
    public static final int HORIZONTAL = 15;
    public static final int TOP = 16;
    public static final int MIDDLE = 32;
    public static final int BOTTOM = 48;
    public static final int VSTRETCH = 64;
    public static final int VERTICAL = 240;
    private static final int ALL = 255;
    
    public static final boolean valid(final int alignment) {
        return (alignment & 0xFF) == alignment;
    }
}
