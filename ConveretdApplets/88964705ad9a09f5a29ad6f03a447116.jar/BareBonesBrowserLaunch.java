import java.lang.reflect.Method;
import java.awt.Component;
import javax.swing.JOptionPane;

// 
// Decompiled by Procyon v0.5.30
// 

public class BareBonesBrowserLaunch
{
    private static final String errMsg = "Error attempting to launch web browser";
    
    public static void openURL(final String url) {
        final String osName = System.getProperty("os.name");
        try {
            if (osName.startsWith("Mac OS")) {
                final Class fileMgr = Class.forName("com.apple.eio.FileManager");
                final Method openURL = fileMgr.getDeclaredMethod("openURL", String.class);
                openURL.invoke(null, url);
            }
            else if (osName.startsWith("Windows")) {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            }
            else {
                final String[] browsers = { "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape" };
                String browser = null;
                for (int count = 0; count < browsers.length && browser == null; ++count) {
                    if (Runtime.getRuntime().exec(new String[] { "which", browsers[count] }).waitFor() == 0) {
                        browser = browsers[count];
                    }
                }
                if (browser == null) {
                    throw new Exception("Could not find web browser");
                }
                Runtime.getRuntime().exec(new String[] { browser, url });
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error attempting to launch web browser:\n" + e.getLocalizedMessage());
        }
    }
}
