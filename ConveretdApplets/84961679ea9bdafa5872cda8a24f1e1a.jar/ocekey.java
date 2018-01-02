import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

class ocekey
{
    public static String getKey(final String note, final URL u) {
        final String regURL = "www.opencube.com";
        int i;
        for (i = 1; Character.isDigit(note.charAt(note.length() - i)); ++i) {}
        if (u.getProtocol().equalsIgnoreCase("file") || u.getHost().equalsIgnoreCase(regURL)) {
            return note.substring(0, note.length() - i + 1).trim();
        }
        return "-1";
    }
}
