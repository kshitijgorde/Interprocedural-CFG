// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

import java.net.MalformedURLException;
import java.awt.Container;
import java.awt.Font;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.net.URL;
import java.awt.CardLayout;
import java.awt.Panel;
import java.awt.Label;
import java.applet.Applet;

public class MudApplet extends Applet
{
    public static final boolean debug = false;
    public static final boolean fesdebug = false;
    MudFrame mudframe;
    boolean userinput;
    boolean showing;
    String user1;
    String user2;
    String pass1;
    String pass2;
    String dispsize;
    String cb_string;
    Label glabel;
    Label adone;
    Label adone2;
    Label adone3;
    Panel p1;
    CardLayout cl;
    
    public void stop() {
        System.out.println("Browser tried to stop applet");
    }
    
    public MudApplet() {
        this.userinput = false;
        this.showing = false;
        this.glabel = new TitleLabel("                                                      ");
        this.cl = new CardLayout();
    }
    
    public void addNotify() {
        super.addNotify();
        System.out.println("Applet displaying");
        this.showing = true;
    }
    
    int getHostPort() {
        final String s = this.getParameter("Port", "8000");
        try {
            final int n = Integer.parseInt(s);
            return n;
        }
        catch (NumberFormatException e) {
            return 8000;
        }
    }
    
    public String[][] getParameterInfo() {
        return null;
    }
    
    public void destroy() {
        try {
            System.out.println("Browser destroying applet");
            if (this.mudframe != null && MudFrame.connected) {
                if (this.mudframe.mudbox != null) {
                    try {
                        if (this.mudframe.prompt_received) {
                            this.mudframe.mudbox.sendString("logout\r\n");
                        }
                        else {
                            this.mudframe.mudbox.sendString("q\r\n");
                        }
                    }
                    catch (Exception e) {
                        System.err.println("Error in sending log off string");
                        e.printStackTrace();
                    }
                }
                this.mudframe.stop_connect();
                this.mudframe.dispose();
            }
            else {
                System.out.println("MudFrame is null, no further action taken");
            }
        }
        catch (Exception e) {
            System.err.println("Error in destroying Applet");
            e.printStackTrace();
        }
    }
    
    public void jbInit() throws Exception {
        this.user1 = this.getParameter("User1", "mud");
        this.pass1 = this.getParameter("Pass1", "None");
        this.user2 = this.getParameter("User2", "?");
        this.pass2 = this.getParameter("Pass2", "None");
        this.dispsize = this.getParameter("DSize", "8");
        if (this.user2.equals("?")) {
            this.userinput = true;
        }
        try {
            final URL u = this.getCodeBase();
            this.cb_string = "";
            if (u != null) {
                this.cb_string += u;
            }
        }
        catch (Exception except) {
            System.err.println("Error determining codebase, using none.");
            this.cb_string = "";
        }
        final LoadFrame lf = new LoadFrame(this);
    }
    
    public void start() {
        this.repaint();
    }
    
    public String getAppletInfo() {
        return "Mud II Front End Starter applet, written by Robin Harper";
    }
    
    public String getParameter(final String key, final String def) {
        if (this.getParameter(key) != null) {
            return this.getParameter(key);
        }
        return def;
    }
    
    public void init() {
        this.setLayout(this.cl);
        this.p1 = new Panel();
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 10, 10, 10);
        this.p1.setLayout(gbl);
        gbc.anchor = 10;
        gbc.gridwidth = 0;
        gbc.gridheight = 2;
        final Label l0 = new TitleLabel("MUD II", 40);
        gbl.setConstraints(l0, gbc);
        this.p1.add(l0);
        gbc.anchor = 17;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        final Label l2 = new TitleLabel("Program Loaded...");
        gbl.setConstraints(l2, gbc);
        this.p1.add(l2);
        gbc.gridy = 3;
        final Label l3 = new TitleLabel("Please wait for graphics to be loaded");
        gbl.setConstraints(l3, gbc);
        this.p1.add(l3);
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        final Label l4 = new TitleLabel("Requesting...");
        gbl.setConstraints(l4, gbc);
        this.p1.add(l4);
        gbc.gridx = 1;
        this.glabel.setFont(new Font("Courier", 0, 14));
        gbl.setConstraints(this.glabel, gbc);
        this.p1.add(this.glabel);
        gbc.gridx = 0;
        gbc.gridwidth = 0;
        this.adone = new TitleLabel("                                                                              ");
        gbc.gridy = 5;
        gbl.setConstraints(this.adone, gbc);
        this.p1.add(this.adone);
        (this.adone2 = new TitleLabel("", 24)).setAlignment(1);
        gbc.gridy = 6;
        gbc.anchor = 10;
        gbc.fill = 1;
        gbc.gridwidth = 2;
        gbl.setConstraints(this.adone2, gbc);
        this.p1.add(this.adone2);
        (this.adone3 = new TitleLabel("  ", 24)).setAlignment(1);
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbl.setConstraints(this.adone3, gbc);
        this.p1.add(this.adone3);
        this.validate();
        this.add("Loader", this.p1);
        this.cl.show(this, "Loader");
        try {
            this.jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    void showHelp() {
        String hostname;
        try {
            final URL hosturl = this.getDocumentBase();
            hostname = "" + hosturl;
            final int n = hostname.lastIndexOf("/");
            if (n != -1) {
                hostname = hostname.substring(0, n) + "/" + "Mudfehelp.html";
            }
        }
        catch (Exception e2) {
            hostname = "localhost/Mudfehelp.html";
        }
        final String helppage = this.getParameter("HelpPage", hostname);
        try {
            final URL HelpURL = new URL(helppage);
            this.getAppletContext().showDocument(HelpURL);
        }
        catch (MalformedURLException e) {
            System.err.println("Bad error url, was: " + helppage);
            e.printStackTrace();
        }
    }
}
