// 
// Decompiled by Procyon v0.5.30
// 

package newstick.param;

import java.applet.Applet;

public class JavaScriptParameters extends AppletParameters
{
    private static final String openDocumentMethodPARAM = "opendocumentmethod";
    private static final String openDocumentMethodDEFAULT = "open";
    private static final String openNewsMethodPARAM = "opennewsmethod";
    private static final String openNewsMethodDEFAULT = "open";
    public String openDocumentMethod;
    public String openNewsMethod;
    
    public JavaScriptParameters(final Applet applet) {
        super(applet);
        this.openDocumentMethod = this.paramToString("opendocumentmethod", "open");
        this.openNewsMethod = this.paramToString("opennewsmethod", "open");
    }
}
