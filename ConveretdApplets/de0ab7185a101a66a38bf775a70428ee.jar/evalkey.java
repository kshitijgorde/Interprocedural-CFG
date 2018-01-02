import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

class evalkey
{
    public static String getKey(final String s, final URL url) {
        final String[] array = { "opencube", "javaboutique", "localhost", "127.0.0.1", "internet" };
        int n;
        for (n = 1; Character.isDigit(s.charAt(s.length() - n)); ++n) {}
        if (url.getProtocol().equalsIgnoreCase("file")) {
            return s.substring(0, s.length() - n + 1).trim();
        }
        for (int i = 0; i < array.length; ++i) {
            if (url.getHost().toLowerCase().indexOf(array[i], 0) != -1) {
                return s.substring(0, s.length() - n + 1).trim();
            }
        }
        return "-1";
    }
}
