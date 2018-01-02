// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.util;

import java.io.Writer;
import java.io.PrintWriter;
import com.ms.io.clientstorage.ClientStorageManager;
import com.ms.security.PolicyEngine;
import com.ms.security.PermissionID;
import netscape.javascript.JSObject;
import java.applet.Applet;

public class UtilApplet extends Applet
{
    public boolean isInstalled;
    private String[] params;
    private String[] values;
    private static final int INSTALL = 0;
    private static final int UNINSTALL = 1;
    
    public UtilApplet() {
        this.isInstalled = false;
        this.params = new String[] { "installcheck", "uninstall" };
        this.values = new String[2];
    }
    
    public void init() {
        if (this.readParams()) {
            this.handleParams();
        }
    }
    
    public boolean readParams() {
        for (int i = 0; i <= this.params.length - 1; ++i) {
            this.values[i] = this.params[i] + ":" + this.getParameter(this.params[i]);
        }
        return this.values[0] != null;
    }
    
    public void handleParams() {
        for (int i = 0; i <= this.values.length - 1; ++i) {
            if (this.values[i].indexOf("installcheck") != -1 && this.values[i].indexOf("true") != -1) {
                this.handleInstallChecking();
            }
            if (this.values[i].indexOf("uninstall") != -1 && this.values[i].indexOf("true") != -1) {
                this.handleUninstall();
            }
        }
    }
    
    public void handleInstallChecking() {
        if (this.isInstalled()) {
            this.writeScratchSpace("_installed=true;");
            Util.setInstallCookie("true", this, new Boolean(false));
            this.notifyBrowserOf(0);
        }
    }
    
    public void handleUninstall() {
        if (this.isInstalled()) {
            boolean b = false;
            try {
                b = (System.getProperty("browser").toLowerCase().indexOf("netscape") != -1);
            }
            catch (Exception ex) {}
            if (!b) {
                this.writeScratchSpace("_installed=false;");
            }
            Util.UninstallCookie(this, new Boolean(b));
            this.notifyBrowserOf(1);
        }
    }
    
    public boolean isInstalled() {
        if (this.getParameter("cabbase") == null && this.getParameter("useslibrary") != null) {
            this.isInstalled = true;
        }
        return this.isInstalled;
    }
    
    private void notifyBrowserOf(final int n) {
        Class<?> forName;
        try {
            forName = Class.forName("netscape.javascript.JSObject");
        }
        catch (ClassNotFoundException ex2) {
            forName = null;
        }
        if (forName != null) {
            try {
                final JSObject window = JSObject.getWindow((Applet)this);
                if (n == 0) {
                    window.eval("setInstalled()");
                }
                else if (n == 1) {
                    window.eval("setUninstalled()");
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void writeScratchSpace(final String s) {
        try {
            PolicyEngine.assertPermission(PermissionID.CLIENTSTORE);
            final PrintWriter printWriter = new PrintWriter(new PrintWriter(ClientStorageManager.openWritable("dominoapplets.txt")));
            printWriter.println(s);
            printWriter.close();
        }
        catch (Exception ex) {}
    }
}
