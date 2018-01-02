// 
// Decompiled by Procyon v0.5.30
// 

package bbpi.dnc.util;

public class TagUtils
{
    public static String closeTag(final String s) {
        return "</" + s + ">";
    }
    
    public static String createTag(final String s) {
        return String.valueOf(openTag(s)) + closeTag(s);
    }
    
    public static String createTag(final String s, final String s2) {
        return String.valueOf(openTag(s)) + s2 + closeTag(s);
    }
    
    public static String createTag(final String s, final String s2, final String s3, final String s4) {
        return String.valueOf(openTag(s, s2, s3)) + s4 + closeTag(s);
    }
    
    public static String openTag(final String s) {
        return "<" + s + ">";
    }
    
    public static String openTag(final String s, final String s2, final String s3) {
        return "<" + s + " " + s2 + "=\"" + s3 + "\"" + ">";
    }
}
