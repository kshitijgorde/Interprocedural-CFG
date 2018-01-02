// 
// Decompiled by Procyon v0.5.30
// 

package com.electa.installer;

import java.awt.Container;
import java.awt.Font;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import netscape.javascript.JSException;
import java.util.logging.Level;
import java.applet.Applet;
import netscape.javascript.JSObject;
import java.net.URL;
import java.util.logging.Logger;
import javax.swing.JApplet;

public class MainApplet extends JApplet
{
    private static Logger log;
    private static final long serialVersionUID = 8817147665796778787L;
    private static final boolean isStandalone = false;
    private URL currentURL;
    public JSObject browserWindow;
    private String successScript;
    private String errorScript;
    private String nonMSWindowsScript;
    public String onProcessStartedScript;
    public String onProcessNotStartedScript;
    private String SetupFileURL;
    private String SetupFileURL1;
    private String eLectaVersion;
    private String AccessString;
    private String InstallPath;
    private String SecurityErrorMsg;
    public String runFile;
    public String versionFile;
    public String msgInit;
    public String msgStartDownload;
    public String msgLaunching;
    public String msgInstallFinished;
    public String msgInstallFailed;
    public String msgDownloadFailed;
    public String msgComplete;
    
    static {
        MainApplet.log = Logger.getLogger(MainApplet.class.getName());
    }
    
    public void init() {
        this.currentURL = this.getCodeBase();
        this.SecurityErrorMsg = this.getParameter("msgSecurityError");
        this.onProcessNotStartedScript = this.getParameter("onProcessNotStartedScript", "");
        final String osName = System.getProperty("os.name").toLowerCase();
        try {
            this.browserWindow = JSObject.getWindow((Applet)this);
        }
        catch (JSException e) {
            MainApplet.log.log(Level.SEVERE, "Error getting Browser window for LiveConnect", (Throwable)e);
        }
        try {
            this.getAppletParameters();
        }
        catch (Throwable e2) {
            this.executeJS(this.onProcessNotStartedScript);
            this.showErrorMessage(this.SecurityErrorMsg);
            return;
        }
        if (!osName.matches(".*windows.*")) {
            MainApplet.log.info("Client system isn't MS Windows: " + osName + " . Quit");
            if (this.browserWindow != null && !Utils.isEmpty(this.nonMSWindowsScript)) {
                this.browserWindow.eval(this.nonMSWindowsScript);
            }
            return;
        }
        final InstallerCtrl downloaderCtrl = new InstallerCtrl(this);
        downloaderCtrl.startProcess();
    }
    
    void executeFinalJS(final boolean success) {
        if (this.browserWindow != null) {
            this.browserWindow.eval(success ? this.successScript : this.errorScript);
        }
    }
    
    void executeJS(final String AScript) {
        if (this.browserWindow != null) {
            this.browserWindow.eval(AScript);
        }
    }
    
    public void showErrorMessage(final String AMessage) {
        this.setLayout(new FlowLayout());
        this.setBackground(Color.white);
        final Container content = this.getContentPane();
        content.setBackground(Color.white);
        content.setLayout(new FlowLayout());
        final JLabel lblstatus = new JLabel(AMessage);
        this.add(lblstatus);
        final Font f = new Font("Tahoma", 0, 12);
        lblstatus.setFont(f);
        lblstatus.setBackground(Color.white);
        lblstatus.setText(AMessage);
    }
    
    public String getParameter(final String key, final String def) {
        return (this.getParameter(key) != null) ? this.getParameter(key) : def;
    }
    
    public void getAppletParameters() throws Throwable {
        this.successScript = this.getParameter("successScript", "");
        this.errorScript = this.getParameter("errorScript", "");
        this.nonMSWindowsScript = this.getParameter("nonMSWindowsScript", "");
        this.SetupFileURL = this.getParameter("setupFileURL", "").toLowerCase();
        this.SetupFileURL1 = this.getParameter("setupFileURL1", "").toLowerCase();
        this.eLectaVersion = this.getParameter("eLectaVersion", "");
        this.AccessString = this.getParameter("accessString", "");
        this.onProcessStartedScript = this.getParameter("onProcessStartedScript", "");
        this.runFile = this.getParameter("runFile", "");
        this.msgInit = this.getParameter("msgInit", "Preparing, please wait...");
        this.msgStartDownload = this.getParameter("msgStartDownload", "Downloading Components...");
        this.msgLaunching = this.getParameter("msgLaunching", "Launching eLecta Live...");
        this.msgInstallFinished = this.getParameter("msgInstallFinished", "Installation Complete!");
        this.msgInstallFailed = this.getParameter("msgInstallFailed", "Installation Failed!");
        this.msgDownloadFailed = this.getParameter("msgDownloadFailed", "Download Failed...");
        this.msgComplete = this.getParameter("msgComplete", "eLecta Live Session Closed.");
        try {
            this.InstallPath = Utils.parsePath(this.getParameter("installPath", ""));
            this.versionFile = Utils.parsePath(this.getParameter("versionFile", ""));
        }
        catch (Throwable E) {
            throw new Throwable(this.SecurityErrorMsg);
        }
    }
    
    public String getInstallPath() {
        return this.InstallPath;
    }
    
    public String getAccessString() {
        return this.AccessString;
    }
    
    public String getSetupFileURL() {
        if (this.SetupFileURL.indexOf("http://support.e-lecta.com/") == 0) {
            return this.SetupFileURL;
        }
        if (this.SetupFileURL.indexOf("https://support.e-lecta.com/") == 0) {
            return this.SetupFileURL;
        }
        if (this.SetupFileURL.indexOf(".e-lecta.com/") > 0) {
            return this.SetupFileURL;
        }
        if (this.SetupFileURL.indexOf(".e-lectazone.com/") > 0) {
            return this.SetupFileURL;
        }
        return "";
    }
    
    public String getSetupFileURL1() {
        if (this.SetupFileURL1.indexOf("http://support.e-lecta.com/") == 0) {
            return this.SetupFileURL1;
        }
        if (this.SetupFileURL1.indexOf("https://support.e-lecta.com/") == 0) {
            return this.SetupFileURL1;
        }
        if (this.SetupFileURL1.indexOf(".e-lecta.com/") > 0) {
            return this.SetupFileURL1;
        }
        if (this.SetupFileURL1.indexOf(".e-lectazone.com/") > 0) {
            return this.SetupFileURL1;
        }
        return "";
    }
    
    public String geteLectaVersion() {
        return this.eLectaVersion;
    }
    
    public URL getCurrentURL() {
        return this.currentURL;
    }
    
    public JSObject getBrowserWindow() {
        return this.browserWindow;
    }
    
    public String getErrorScript() {
        return this.errorScript;
    }
    
    public String getSuccessScript() {
        return this.successScript;
    }
    
    public String getAppletInfo() {
        return "e-lecta.com Installer";
    }
}
