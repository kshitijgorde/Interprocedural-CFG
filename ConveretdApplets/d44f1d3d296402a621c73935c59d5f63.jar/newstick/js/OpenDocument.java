// 
// Decompiled by Procyon v0.5.30
// 

package newstick.js;

import java.net.URL;
import java.applet.Applet;

public class OpenDocument extends JavaScriptFront
{
    public OpenDocument(final Applet applet) {
        super(applet);
    }
    
    public boolean openDocument(final URL url, final String s, final String s2) {
        return this.openDocument("open", url, s, s2);
    }
    
    public boolean openDocument(final String s, final URL url, final String s2, final String s3) {
        return this.internalInvoke(s, new Object[] { url.toString(), generateOpenWinName(), s2, s3 });
    }
    
    private static Object generateOpenWinName() {
        return "id_" + Math.round(Math.random() * 1000.0);
    }
    
    private boolean internalInvoke(final String s, final Object[] array) {
        if (JavaScriptFront.isEnabled()) {
            JavaScriptFront.errorException = null;
            this.jsInvoke(s, array);
            return JavaScriptFront.errorException == null;
        }
        return false;
    }
    
    public boolean openNews(final URL url, final String s) {
        return this.openNews("open", url, s);
    }
    
    public boolean openNews(final String s, final URL url, final String s2) {
        return this.internalInvoke(s, new Object[] { url.toString(), generateOpenWinName(), s2 });
    }
}
