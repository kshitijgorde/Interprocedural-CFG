import java.awt.Frame;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class TocUtil
{
    public static String percentEncode(final String s) {
        TocDebug.TraceL3("JTree::_encode");
        String s2 = "";
        String string = new String(s);
        for (int i = 0; i < string.length(); ++i) {
            switch (string.charAt(i)) {
                case ';': {
                    s2 = "%3B";
                    break;
                }
                case '?': {
                    s2 = "%3F";
                    break;
                }
                case ':': {
                    s2 = "%3A";
                    break;
                }
                case '@': {
                    s2 = "%40";
                    break;
                }
                case '<': {
                    s2 = "%3C";
                    break;
                }
                case '>': {
                    s2 = "%3E";
                    break;
                }
                case '\"': {
                    s2 = "%22";
                    break;
                }
                case '#': {
                    s2 = "%23";
                    break;
                }
                case '%': {
                    s2 = "%25";
                    break;
                }
                case '{': {
                    s2 = "%7B";
                    break;
                }
                case '}': {
                    s2 = "%7D";
                    break;
                }
                case '|': {
                    s2 = "%7C";
                    break;
                }
                case '^': {
                    s2 = "%5E";
                    break;
                }
                case '~': {
                    s2 = "%7E";
                    break;
                }
                case '[': {
                    s2 = "%5B";
                    break;
                }
                case ']': {
                    s2 = "%5D";
                    break;
                }
                case '`': {
                    s2 = "%60";
                    break;
                }
                case ' ': {
                    s2 = "%20";
                    break;
                }
            }
            if (s2.length() > 0) {
                string = string.substring(0, i) + s2 + string.substring(i + 1);
                i += s2.length() - 1;
                s2 = "";
            }
        }
        return string;
    }
    
    public static String replace(final String s, final String s2, final String s3) {
        TocDebug.TraceL3("JTree::replace");
        String string;
        int index;
        for (string = new String(s); (index = string.indexOf(s2)) != -1; string = string.substring(0, index) + s3 + string.substring(index + s2.length())) {}
        return string;
    }
    
    public static String percentDecode(final String s) {
        TocDebug.TraceL3("JTree::_dencode");
        return replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(new String(s), "%40", "@"), "%3D", "="), "%26", "&"), "%3C", "<"), "%3E", ">"), "%22", "\""), "%23", "#"), "%25", "%"), "%7B", "{"), "%7D", "}"), "%7C", "|"), "%5E", "^"), "%7E", "~"), "%5B", "["), "%5D", "]"), "%60", "`"), "%20", " "), "+", " "), "%2E", "."), "%3B", ";");
    }
    
    public static Frame GetFrame(Component parent) {
        Frame frame = null;
        while ((parent = parent.getParent()) != null) {
            if (parent instanceof Frame) {
                frame = (Frame)parent;
                break;
            }
        }
        return frame;
    }
}
