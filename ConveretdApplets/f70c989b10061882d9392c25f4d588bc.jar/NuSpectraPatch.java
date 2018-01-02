import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class NuSpectraPatch
{
    public static String getURL(final Applet t) {
        final String host = t.getCodeBase().getHost();
        final int port = t.getCodeBase().getPort();
        String s = host;
        if (port != 80 && port > 1) {
            s = String.valueOf(s) + ":" + port;
        }
        s = ((s.charAt(s.length() - 1) != '/') ? (String.valueOf(s) + "/-wvhttp-01-/") : (String.valueOf(s) + "-wvhttp-01-/"));
        return s;
    }
}
