// 
// Decompiled by Procyon v0.5.30
// 

package testJava2_1;

import java.net.URL;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import java.awt.Color;
import java.applet.AppletContext;
import javax.swing.JApplet;

public class TestVMApplet extends JApplet
{
    private static AppletContext appletContext;
    
    public void init() {
        TestVMApplet.appletContext = this.getAppletContext();
        UIManager.put("TabbedPane.selected", Color.WHITE);
        this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.getContentPane().add(new TestVM(this.getCodeBase()));
    }
    
    protected static void showJavaVendorURL(final URL url) {
        if (TestVMApplet.appletContext != null) {
            final AppletContext context = TestVMApplet.appletContext;
            context.showDocument(url, "_self");
        }
    }
}
