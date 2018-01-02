// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.ui;

import java.io.IOException;
import java.net.URL;

public abstract class BrowserLauncher
{
    private static final String WIN_ID = "Windows";
    private static final String WIN_PATH = "rundll32";
    private static final String WIN_FLAG = "url.dll,FileProtocolHandler";
    private static final String UNIX_PATH = "netscape";
    private static final String UNIX_FLAG = "-remote openURL";
    
    public static void showDocument(final URL url) {
        showDocument(url.toString());
    }
    
    public static void showDocument(final String s) {
        final boolean windowsPlatform = isWindowsPlatform();
        String s2 = null;
        try {
            if (windowsPlatform) {
                s2 = "rundll32 url.dll,FileProtocolHandler " + s;
                Runtime.getRuntime().exec(s2);
            }
            else {
                s2 = "netscape -remote openURL(" + s + ")";
                final Process exec = Runtime.getRuntime().exec(s2);
                try {
                    if (exec.waitFor() != 0) {
                        s2 = "netscape " + s;
                        Runtime.getRuntime().exec(s2);
                    }
                }
                catch (InterruptedException ex) {
                    System.err.println("Error bringing up browser, cmd='" + s2 + "'");
                    System.err.println("Caught: " + ex);
                }
            }
        }
        catch (IOException ex2) {
            System.err.println("Could not invoke browser, command=" + s2);
            System.err.println("Caught: " + ex2);
        }
    }
    
    public static boolean isWindowsPlatform() {
        final String property = System.getProperty("os.name");
        return property != null && property.startsWith("Windows");
    }
    
    public static void main(final String[] array) {
        showDocument(array[0]);
    }
}
