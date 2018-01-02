// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.util;

public class Bidi
{
    public static final int LEFT_TO_RIGHT = 0;
    public static final int RIGHT_TO_LEFT = 1;
    
    public static int toggleHorzPos(final int n, final int n2, final int n3) {
        return n3 - n - n2;
    }
    
    public static String BidiString(String string) {
        String property = "";
        if (string == null) {
            return string;
        }
        try {
            property = System.getProperty("os.version");
        }
        catch (Exception ex) {}
        if (property != null && Float.valueOf(property) >= 5.0f) {
            string = '\u202b' + string;
        }
        return string;
    }
}
