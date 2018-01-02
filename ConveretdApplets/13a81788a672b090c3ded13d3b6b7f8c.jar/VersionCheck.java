import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Frame;
import java.awt.Component;
import java.awt.Label;
import java.net.MalformedURLException;
import java.awt.TextArea;
import java.awt.Dialog;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class VersionCheck extends Applet implements Runnable
{
    int version;
    int targetVersion;
    URL redirect;
    URL applet;
    URL bouncer;
    URL jumpto;
    Dialog dialog;
    TextArea message;
    Thread t;
    boolean timeout;
    int failSafeDelay;
    private String redirectMessage;
    String[][] tr;
    
    public VersionCheck() {
        this.dialog = null;
        this.message = null;
        this.failSafeDelay = 10000;
        this.tr = new String[][] { { "+", " " }, { "%3A", ":" }, { "%2F", "/" }, { "%3F", "?" }, { "%3D", "=" }, { "%26", "&" }, { "%40", "@" } };
    }
    
    public void init() {
        final String docBase = this.getDocumentBase().toString();
        final String targetVersionString = this.getParameter("targetVersion");
        this.targetVersion = 9999;
        if (targetVersionString != null) {
            try {
                final float ver = new Float(targetVersionString);
                this.targetVersion = (int)(ver * 10.0f);
            }
            catch (NumberFormatException nfe) {
                this.popDialog("Parameter Malformed!", "The Java version " + targetVersionString + " cannot be recognised as a number!");
            }
        }
        else {
            this.popDialog("Parameter Missing!", "Java target version must be specified in the applet parameters.  E.G.\n<PARAM NAME='targetVersion' VALUE='1.7'>");
        }
        final String redirectString = this.getParameter("redirect");
        this.redirect = null;
        if (redirectString == null) {
            this.popDialog("Parameter Missing!", "The redirect URL must be given in the applet parameters.  E.G.\n<PARAM NAME='redirect' VALUE='javaversion.html'>");
        }
        else {
            try {
                final String applet = "";
                this.redirect = new URL(this.getCodeBase(), this.urlEncode(redirectString));
            }
            catch (MalformedURLException murle) {
                this.popDialog("Malformed URL!", "Could not recognise redirect URL '" + redirectString + "'");
            }
        }
        this.applet = null;
        final String appletString = this.getParameter("applet");
        if (appletString != null) {
            try {
                this.applet = new URL(this.getCodeBase(), this.urlEncode(appletString));
            }
            catch (MalformedURLException murle2) {
                this.popDialog("Malformed URL!", "Could not recognise applet URL '" + appletString + "'");
            }
        }
        final String failSafe = this.getParameter("failDelay");
        if (failSafe != null) {
            try {
                this.failSafeDelay = new Integer(failSafe) * 1000;
            }
            catch (NumberFormatException nfe2) {
                this.popDialog("Parameter Malformed!", "The failDelay " + failSafe + " cannot be recognised as an integer!");
            }
        }
        final String bouncerString = this.getParameter("bouncer");
        this.bouncer = null;
        if (bouncerString != null) {
            try {
                this.bouncer = new URL(this.getCodeBase(), this.urlEncode(bouncerString));
            }
            catch (MalformedURLException murle3) {
                this.popDialog("Malformed URL!", "Could not recognise redirect URL '" + bouncerString + "'");
            }
        }
        this.version = this.getVersionAsInt();
        System.out.println("Current Version: " + this.version);
        System.out.println("Target Version: " + this.targetVersion);
        final String op = System.getProperty("java.version");
        if (this.version < this.targetVersion) {
            this.add(new Label("Java version " + op + " too low!"));
            this.jumpto = this.redirect;
            this.redirectMessage = "Java version is too low to run the applet.\nFor help, visit..\n";
            this.redirect();
        }
        else {
            this.add(new Label("Java version" + op + " OK!"));
            if (this.applet != null) {
                this.jumpto = this.applet;
                this.redirectMessage = "To use the applet, visit..\n";
                this.redirect();
            }
        }
    }
    
    public void destroy() {
        if (this.t != null) {
            this.timeout = false;
        }
    }
    
    public void run() {
        final long time = System.currentTimeMillis();
        this.timeout = true;
        while (this.timeout) {
            if (time + this.failSafeDelay < System.currentTimeMillis()) {
                this.timeout = false;
                this.popDialog("Redirect failed!", this.redirectMessage + this.redirect);
            }
            else {
                try {
                    final Thread t = this.t;
                    Thread.sleep(150L);
                }
                catch (InterruptedException ie) {}
            }
        }
    }
    
    public String[][] getParameterInfo() {
        final String[][] paramInfo = { { "targetVersion", "float", "Float value for minimum major version" }, { "redirect", "URL", "Redirect URL if minimum version not available" }, { "applet", "URL", "(Optional) Redirect URL if minimum version available (the applet page)" }, { "failDelay", "seconds", "(Optional) Change the default 10 seconds before popping the 'cannot redirect' warning, to this number of seconds " }, { "bouncer", "URL", "(Optional) Instead of redirecting directly to an external URL, send the client via this page, adding the final destination as the 'jumpto' parameter." } };
        return paramInfo;
    }
    
    public void redirect() {
        final String popUpMessage = this.getParameter("popUpMessage");
        if (popUpMessage != null) {
            this.popDialog("System message:", popUpMessage);
        }
        URL url = null;
        if (this.bouncer == null || this.jumpto.getHost() == this.getCodeBase().getHost()) {
            url = this.jumpto;
        }
        else {
            try {
                url = new URL(this.bouncer.toString() + "?url=" + this.jumpto.toString());
            }
            catch (MalformedURLException murle) {
                murle.printStackTrace();
                url = this.jumpto;
            }
        }
        (this.t = new Thread(this)).start();
        this.getAppletContext().showDocument(this.jumpto);
    }
    
    public int getVersionAsInt() {
        final String versionString = System.getProperty("java.version");
        final int firstDot = versionString.indexOf(".");
        final String tensString = versionString.substring(0, firstDot);
        int nextDot = versionString.indexOf(".", firstDot + 1);
        if (nextDot < 0) {
            nextDot = versionString.length();
        }
        final String onesString = versionString.substring(firstDot + 1, nextDot);
        int version = -1;
        try {
            final int tens = new Integer(tensString);
            final int ones = new Integer(onesString);
            version = tens * 10 + ones;
        }
        catch (NumberFormatException nfe) {
            this.popDialog("Program Error!", "The applet has a bug, please report it to the author!\n" + nfe.getMessage());
        }
        return version;
    }
    
    public void popDialog(final String title, final String text) {
        if (this.dialog == null) {
            Component parent;
            for (parent = this.getParent(); parent.getParent() != null; parent = parent.getParent()) {}
            this.dialog = new Dialog((Frame)parent, true);
            (this.message = new TextArea("", 3, 30, 1)).setEditable(false);
            this.dialog.add(this.message, "Center");
            this.dialog.pack();
            this.dialog.setLocation(500, 500);
            this.dialog.addWindowListener(new WindowAdapter() {
                public void windowClosing(final WindowEvent we) {
                    VersionCheck.this.dialog.setVisible(false);
                }
            });
        }
        this.dialog.setTitle(title);
        this.message.setText(text);
        this.dialog.setVisible(true);
    }
    
    public String urlEncode(final String string) {
        String temp = string;
        for (int ii = 0; ii < this.tr.length; ++ii) {
            temp = this.replaceAll(temp, this.tr[ii][0], this.tr[ii][1]);
        }
        return temp;
    }
    
    public String urlDecode(final String string) {
        String temp = string;
        for (int ii = 0; ii < this.tr.length; ++ii) {
            temp = this.replaceAll(temp, this.tr[ii][1], this.tr[ii][0]);
        }
        return temp;
    }
    
    public String replaceAll(final String content, final String oldText, final String newText) {
        String temp = content;
        for (int lastIndex = temp.lastIndexOf(oldText); lastIndex > -1; lastIndex = temp.lastIndexOf(oldText)) {
            temp = temp.substring(0, lastIndex) + newText + temp.substring(lastIndex + oldText.length(), temp.length());
        }
        return temp;
    }
}
