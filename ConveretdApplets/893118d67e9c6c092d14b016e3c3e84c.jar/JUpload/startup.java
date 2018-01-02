// 
// Decompiled by Procyon v0.5.30
// 

package JUpload;

import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Label;
import java.util.Enumeration;
import java.util.Properties;
import java.security.AccessControlException;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.applet.AppletContext;
import java.applet.Applet;

public class startup extends Applet
{
    public AppletContext context;
    private JUpload jup;
    
    public String getAppletInfo() {
        return "JUpload Applet v0.63 (http://www.haller-systemservice.net/jupload/)";
    }
    
    public void debugInfo() {
        final String[][] props = { { "file.separator", "File separator" }, { "java.class.version", "Java class version number" }, { "java.vendor", "Java vendor-specific string" }, { "java.vendor.url", "Java vendor URL" }, { "java.version", "Java version number" }, { "os.arch", "Operating system architecture" }, { "os.name", "Operating system name" }, { "os.version", "Operating system version" }, { "path.separator", "Path separator" } };
        this.debug("startup() debugInfo() " + this.getAppletInfo());
        Properties systemProperties = null;
        try {
            systemProperties = System.getProperties();
        }
        catch (AccessControlException ace) {
            JOptionPane.showMessageDialog(this, "You have to grant read permissions  to the applet for local use in the java.policy file in the java directory.\n");
            this.debug("*** STOPPING APPLET DUE TO PERMISSION PROBLEMS ***");
            this.stop();
            return;
        }
        final Enumeration strKeys = systemProperties.keys();
        while (strKeys.hasMoreElements()) {
            final String strKey = strKeys.nextElement();
            final String strValue = systemProperties.getProperty(strKey);
            this.debug("  >> " + strKey + " = [" + strValue + "]");
        }
    }
    
    public void init() {
        this.debug("startup() init()");
        this.debugInfo();
        this.context = this.getAppletContext();
    }
    
    public void start() {
        this.debug("startup() start()");
        if (this.getParameter("checkJavaVersion") != null) {
            if (this.getParameter("checkJavaVersion").equalsIgnoreCase("true") || this.getParameter("checkJavaVersion").equalsIgnoreCase("1") || this.getParameter("checkJavaVersion").equalsIgnoreCase("on") || !this.getParameter("checkJavaVersion").equalsIgnoreCase("yes")) {}
            this.debug("startup() start() checking for java version...");
            if (this.checkJavaVersion()) {
                this.jup = new JUpload(this);
            }
        }
        else {
            this.debug("startup() start() no check for java version...checkJavaVersion parameter not found");
            this.jup = new JUpload(this);
        }
    }
    
    public void stop() {
        this.debug("startup() stop()");
        if (this.jup != null) {
            this.jup.stop();
        }
    }
    
    private boolean checkJavaVersion() {
        final String javaVersion = System.getProperty("java.class.version");
        final int dbJavaVersion = Integer.parseInt(javaVersion.substring(0, 2));
        if (dbJavaVersion < 47) {
            this.debug("startup() checkJavaVersion() java version " + dbJavaVersion + " not supported.");
            this.add(new Label("Your browser's java is too old."));
            this.add(new Label("Please go to java.sun.com and"));
            this.add(new Label("download the latest J2SE runtime."));
            this.add(new Label("(Internet Explorer 6 ist shipped with"));
            this.add(new Label("Java 1.1!)"));
            this.setVisible(true);
            try {
                URL pluginURL = new URL("http://java.sun.com/j2se/downloads.html");
                final String strGotoURL = this.getParameter("checkJavaVersionGotoURL");
                if (strGotoURL != null && !strGotoURL.equalsIgnoreCase("")) {
                    pluginURL = new URL(strGotoURL);
                }
                this.getAppletContext().showDocument(pluginURL, "_blank");
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }
    
    private void debug(final String s) {
        System.out.println(s);
        if (this.getParameter("debug") != null) {
            if (this.getParameter("debug").equalsIgnoreCase("true") || this.getParameter("debug").equalsIgnoreCase("1") || this.getParameter("debug").equalsIgnoreCase("on") || !this.getParameter("debug").equalsIgnoreCase("yes")) {}
            System.out.println(s);
        }
    }
}
