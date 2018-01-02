// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.submitter;

import java.net.URISyntaxException;
import java.net.URI;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import com.mindprod.http.Http;
import com.mindprod.common15.FontFactory;
import com.mindprod.common11.Build;
import com.mindprod.common13.HybridJ;
import com.mindprod.common11.ST;
import java.io.FileNotFoundException;
import java.io.Writer;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.File;
import java.awt.GridBagConstraints;
import java.util.prefs.BackingStoreException;
import com.mindprod.http.Get;
import java.net.MalformedURLException;
import java.net.URL;
import com.mindprod.common11.Misc;
import com.mindprod.common13.CMPAboutJBox;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import com.mindprod.common15.Laf;
import javax.swing.JMenuBar;
import java.io.BufferedWriter;
import javax.swing.SwingUtilities;
import java.io.IOException;
import com.mindprod.common11.VersionCheck;
import java.awt.Component;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mindprod.common13.JEButton;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.mindprod.common13.Common13;
import java.util.prefs.Preferences;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.text.html.HTMLDocument;
import java.awt.Container;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JApplet;

public final class Submitter extends JApplet implements Runnable
{
    private static final int APPLET_HEIGHT = 475;
    private static final int APPLET_WIDTH = 680;
    private static final int HOW_MANY_WEBSITES;
    private static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2007-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2011-05-22";
    private static final String SAMPLE_PAD_URL = "hypotheticalprogram.xml";
    private static final String SAMPLE_WEBSITE_URL = "http://mypretendwebsite.com/pad";
    private static final String TITLE_STRING = "Mini PAD Submitter";
    private static final String VERSION_STRING = "10.8";
    private static final Color BACKGROUND_FOR_BODY;
    private static final Color BACKGROUND_FOR_INSTRUCTIONS;
    private static final Color BACKGROUND_FOR_WORKING;
    private static final Color FOREGROUND_FOR_ALERT;
    private static final Color FOREGROUND_FOR_ALERT_ALT;
    private static final Color FOREGROUND_FOR_INSTRUCTIONS;
    private static final Color FOREGROUND_FOR_LABEL;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Color FOREGROUND_FOR_URL;
    private static final Color FOREGROUND_FOR_WORKING;
    private static final Font FONT_FOR_INSTRUCTIONS;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private static final Font FONT_FOR_URLS;
    private Container contentPane;
    private HTMLDocument htmlDocument;
    private JButton submitButton;
    private JLabel padFileLabel;
    private JLabel title;
    private JLabel title2;
    private JLabel websiteURLLabel;
    private JScrollPane scroller;
    private JTextField instructions;
    private JTextField padFile;
    private JTextField response;
    private JTextField websiteURL;
    private Preferences userPrefs;
    private RobustJEditorPane responsePage;
    private String fullPADURLString;
    private String logDir;
    private final boolean inApplet;
    private boolean usingAlt;
    
    public Submitter() {
        this.inApplet = true;
    }
    
    public void addNotify() {
        super.addNotify();
        Common13.setLaf();
        (this.contentPane = this.getContentPane()).setBackground(Submitter.BACKGROUND_FOR_BODY);
        this.contentPane.setLayout(new GridBagLayout());
        (this.title = new JLabel("Mini PAD Submitter 10.8")).setFont(Submitter.FONT_FOR_TITLE);
        this.title.setForeground(Submitter.FOREGROUND_FOR_TITLE);
        (this.title2 = new JLabel("released:2011-05-22 build:9411")).setFont(Submitter.FONT_FOR_TITLE2);
        this.title2.setForeground(Submitter.FOREGROUND_FOR_TITLE);
        (this.websiteURLLabel = new JLabel("Web Dir URL:", 4)).setForeground(Submitter.FOREGROUND_FOR_LABEL);
        String defaultWebsite;
        if (this.userPrefs != null) {
            defaultWebsite = this.userPrefs.get("website", "http://mypretendwebsite.com/pad");
        }
        else {
            defaultWebsite = "http://mypretendwebsite.com/pad";
        }
        (this.websiteURL = new JTextField(defaultWebsite, 50)).setFont(Submitter.FONT_FOR_URLS);
        this.websiteURL.setForeground(Submitter.FOREGROUND_FOR_URL);
        this.websiteURL.setMargin(new Insets(3, 2, 3, 2));
        this.websiteURL.setToolTipText("URL of directory or your website where you upload PAD xml files e.g. http://mypretendwebsite.com/pad");
        (this.padFileLabel = new JLabel("PAD xml file:", 4)).setForeground(Submitter.FOREGROUND_FOR_LABEL);
        (this.padFile = new JTextField("hypotheticalprogram.xml", 50)).setFont(Submitter.FONT_FOR_URLS);
        this.padFile.setForeground(Submitter.FOREGROUND_FOR_URL);
        this.padFile.setMargin(new Insets(3, 2, 3, 2));
        this.padFile.setToolTipText("name of your uploaded PAD xml file e.g. hypotheticalprogram.xml");
        (this.submitButton = new JEButton("Submit")).setToolTipText("Submit this PAD xml to " + Submitter.HOW_MANY_WEBSITES + " sites");
        this.submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                Submitter.this.submit();
            }
        });
        (this.instructions = new JTextField("To register your pad xml at " + Submitter.HOW_MANY_WEBSITES + " distribution websites, enter the URL of your uploaded pad and click submit.", 120)).setFont(Submitter.FONT_FOR_INSTRUCTIONS);
        this.instructions.setForeground(Submitter.FOREGROUND_FOR_INSTRUCTIONS);
        this.instructions.setBackground(Submitter.BACKGROUND_FOR_INSTRUCTIONS);
        this.instructions.setEditable(false);
        this.instructions.setMargin(new Insets(2, 2, 2, 2));
        this.responsePage = new RobustJEditorPane();
        this.htmlDocument = new HTMLDocument();
        this.responsePage.setDocument(this.htmlDocument);
        this.responsePage.setContentType("text/html");
        this.responsePage.setForeground(Color.BLACK);
        this.responsePage.setBackground(Color.WHITE);
        this.responsePage.setFont(Submitter.FONT_FOR_INSTRUCTIONS);
        this.responsePage.setMargin(new Insets(2, 2, 2, 2));
        this.scroller = new JScrollPane(this.responsePage, 20, 30);
        this.scroller.getVerticalScrollBar().setUnitIncrement(16);
        (this.response = new JTextField("", 120)).setFont(Submitter.FONT_FOR_INSTRUCTIONS);
        this.response.setForeground(Submitter.FOREGROUND_FOR_INSTRUCTIONS);
        this.response.setBackground(Submitter.BACKGROUND_FOR_INSTRUCTIONS);
        this.response.setEditable(false);
        this.response.setMargin(new Insets(2, 2, 2, 2));
        this.layoutComponents();
    }
    
    public void destroy() {
        this.contentPane = null;
        this.fullPADURLString = null;
        this.htmlDocument = null;
        this.instructions = null;
        this.logDir = null;
        this.padFile = null;
        this.padFileLabel = null;
        this.response = null;
        this.responsePage = null;
        this.scroller = null;
        this.submitButton = null;
        this.title2 = null;
        this.title = null;
        this.userPrefs = null;
        this.websiteURL = null;
        this.websiteURLLabel = null;
    }
    
    public void init() {
        if (this.inApplet) {
            this.logDir = this.getParameter("logDir");
            if (this.logDir == null || this.logDir.length() == 0 || this.logDir.equals("null") || this.logDir.equals("default") || this.logDir.equalsIgnoreCase("noLog")) {
                this.logDir = null;
            }
        }
        this.contentPane = this.getContentPane();
        if (!VersionCheck.isJavaVersionOK(1, 5, 0, this.contentPane)) {
            this.stop();
            this.destroy();
        }
        this.userPrefs = Preferences.userNodeForPackage(Submitter.class);
        this.usingAlt = false;
        this.buildMenu();
    }
    
    public void run() {
        System.out.println("");
        System.out.println("-------------------------------");
        System.out.println("");
        System.out.println(">>>> SUBMITTING " + this.fullPADURLString);
        System.out.println("");
        this.responsePage.setText("");
        for (final SubmissionSite site : SubmissionSite.values()) {
            this.instructions.setText("Submitting to " + site.getName() + ".");
            String siteResponse = site.submit(this.fullPADURLString);
            if (siteResponse == null) {
                siteResponse = "no response";
            }
            final int siteResponseCode = SubmissionSite.getResponseCode();
            final String siteResponseMessage = SubmissionSite.getResponseMessage();
            this.htmlDocument.setBase(site.getBaseURL());
            this.responsePage.setText(siteResponse);
            this.response.setText("Response from: " + site.getName() + " >>>" + siteResponseCode + "<<< " + siteResponseMessage);
            System.out.println("Response from: " + site.getName() + " >>>" + siteResponseCode + "<<< " + siteResponseMessage);
            try {
                final BufferedWriter log = this.openLog(this.fullPADURLString, site.getName());
                if (log != null) {
                    log.write(siteResponse);
                    log.close();
                }
            }
            catch (IOException e) {
                System.err.println("logging not functioning");
            }
            try {
                Thread.sleep(3000L);
            }
            catch (InterruptedException ex) {}
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Submitter.this.instructions.setForeground(Submitter.FOREGROUND_FOR_INSTRUCTIONS);
                Submitter.this.instructions.setBackground(Submitter.BACKGROUND_FOR_INSTRUCTIONS);
                Submitter.this.instructions.setText("D o n e !  Enter the URL of another pad xml file and click submit.");
                Submitter.this.response.setText("D o n e !  " + Submitter.this.response.getText());
                Submitter.this.response.setForeground(Submitter.FOREGROUND_FOR_INSTRUCTIONS);
                Submitter.this.response.setBackground(Submitter.BACKGROUND_FOR_INSTRUCTIONS);
                Submitter.this.submitButton.setEnabled(true);
            }
        });
    }
    
    private Submitter(String logDir) {
        this.inApplet = false;
        if (logDir == null || logDir.length() == 0 || logDir.equals("null") || logDir.equals("default") || logDir.equalsIgnoreCase("noLog")) {
            logDir = null;
        }
        this.logDir = logDir;
    }
    
    private void alert(final String text) {
        assert text.trim().equals(text) : "untrimmed alert text";
        if (text.equals(this.instructions.getText())) {
            this.usingAlt = !this.usingAlt;
            this.instructions.setForeground(this.usingAlt ? Submitter.FOREGROUND_FOR_ALERT_ALT : Submitter.FOREGROUND_FOR_ALERT);
        }
        else {
            this.instructions.setText(text);
            this.instructions.setForeground(Submitter.FOREGROUND_FOR_ALERT);
            this.usingAlt = false;
        }
    }
    
    private void buildMenu() {
        final JMenuBar menubar = new JMenuBar();
        this.setJMenuBar(menubar);
        menubar.add(Laf.buildLookAndFeelMenu());
        final JMenu menuHelp = new JMenu("Help");
        menubar.add(menuHelp);
        final JMenuItem aboutItem = new JMenuItem("About");
        menuHelp.add(aboutItem);
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                new CMPAboutJBox(Misc.getParentFrame(Submitter.this), "Mini PAD Submitter", "10.8", "Submits an ASP PAD XML program description to " + Submitter.HOW_MANY_WEBSITES + "websites.", "", "freeware", "2011-05-22", 2007, "Roedy Green", "SUBMITTER", "1.5");
            }
        });
    }
    
    private boolean isPadValid() {
        final String websiteURLString = this.websiteURL.getText().trim();
        final String padFileString = this.padFile.getText().trim();
        if (websiteURLString.length() == 0) {
            this.alert("You must fill in the website URL before hitting submit.");
            return false;
        }
        if (padFileString.length() == 0) {
            this.alert("You must fill in the PAD URL before hitting submit.");
            return false;
        }
        if (!websiteURLString.startsWith("http://")) {
            this.alert("The website URL [" + websiteURLString + "] must begin with http://");
            return false;
        }
        if (!padFileString.endsWith(".xml")) {
            this.alert("The PAD URL [" + padFileString + "] must end with .xml");
            return false;
        }
        if (websiteURLString.indexOf(92) >= 0) {
            this.alert("The website URL [" + websiteURLString + "] must not contain any \\ characters; use / instead.");
            return false;
        }
        if (padFileString.indexOf(92) >= 0 || padFileString.indexOf(47) >= 0) {
            this.alert("The PAD URL [" + padFileString + "] must not contain any \\ or / characters.");
            return false;
        }
        if (websiteURLString.equalsIgnoreCase("http://mypretendwebsite.com/pad")) {
            this.alert("You must enter the URL of YOUR website before hitting submit.");
            return false;
        }
        if (padFileString.equalsIgnoreCase("hypotheticalprogram.xml")) {
            this.alert("You must enter the URL of YOUR pad on YOUR website before hitting submit.");
            return false;
        }
        URL url;
        try {
            url = new URL(websiteURLString + '/' + padFileString);
        }
        catch (MalformedURLException e) {
            this.alert("Your URL [" + websiteURLString + '/' + padFileString + "] is malformed.");
            return false;
        }
        final Get get = new Get();
        final String padText = get.send(url, Get.UTF8Charset);
        final int padResponseCode = get.getResponseCode();
        if (padResponseCode >= 300 || padText == null) {
            this.alert("The PAD must already be uploaded to your website.");
            return false;
        }
        if (padText.length() < 5000) {
            this.alert("The uploaded PAD xml file should be 5000+ character long. It is only " + padText.length() + ".");
            return false;
        }
        final String lowerPadURLString = padFileString.toLowerCase();
        long lastSubmitted;
        if (this.userPrefs != null) {
            lastSubmitted = this.userPrefs.getLong(lowerPadURLString, 0L);
        }
        else {
            lastSubmitted = 0L;
        }
        final long now = System.currentTimeMillis();
        if (lastSubmitted > now - 604800000L) {
            this.alert(padFileString + " already submitted within the last week.");
            return false;
        }
        try {
            if (this.userPrefs != null) {
                this.userPrefs.putLong(lowerPadURLString, now);
                this.userPrefs.put("website", websiteURLString);
                this.userPrefs.flush();
            }
        }
        catch (BackingStoreException e2) {
            System.err.println("Cannot save Preferences.");
        }
        return true;
    }
    
    private void layoutComponents() {
        this.contentPane.add(this.title, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 17, 0, new Insets(10, 10, 5, 5), 0, 0));
        this.contentPane.add(this.title2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, 10, 0, new Insets(10, 5, 5, 5), 0, 0));
        this.contentPane.add(this.websiteURLLabel, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, 13, 0, new Insets(5, 10, 5, 5), 0, 0));
        this.contentPane.add(this.websiteURL, new GridBagConstraints(1, 1, 1, 1, 95.0, 0.0, 13, 1, new Insets(5, 5, 5, 5), 0, 0));
        this.contentPane.add(this.padFileLabel, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, 13, 0, new Insets(5, 10, 5, 5), 0, 0));
        this.contentPane.add(this.padFile, new GridBagConstraints(1, 2, 1, 1, 95.0, 0.0, 13, 1, new Insets(5, 5, 5, 5), 0, 0));
        this.contentPane.add(this.submitButton, new GridBagConstraints(2, 2, 1, 1, 1.0, 0.0, 13, 0, new Insets(5, 5, 5, 10), 0, 0));
        this.contentPane.add(this.instructions, new GridBagConstraints(0, 3, 3, 1, 100.0, 0.0, 10, 1, new Insets(5, 10, 5, 10), 0, 0));
        this.contentPane.add(this.scroller, new GridBagConstraints(0, 4, 3, 1, 100.0, 100.0, 10, 1, new Insets(5, 10, 5, 10), 0, 0));
        this.contentPane.add(this.response, new GridBagConstraints(0, 5, 3, 1, 100.0, 0.0, 10, 1, new Insets(5, 10, 10, 10), 0, 0));
    }
    
    private BufferedWriter openLog(final String fullPADURLString, final String siteName) throws FileNotFoundException {
        String padName;
        if (fullPADURLString.length() < 4) {
            padName = "unknown";
        }
        else {
            padName = fullPADURLString.substring(0, fullPADURLString.length() - 4);
            final int place = padName.lastIndexOf("/");
            padName = padName.substring(place + 1);
        }
        if (this.logDir == null) {
            return null;
        }
        final FileOutputStream fos = new FileOutputStream(new File(this.logDir, padName + "_" + siteName + ".log.html"), false);
        final OutputStreamWriter osw = new OutputStreamWriter(fos);
        return new BufferedWriter(osw, 20000);
    }
    
    private void submit() {
        this.submitButton.setEnabled(false);
        this.tidyWebsiteURL();
        this.tidyPadFile();
        if (this.isPadValid()) {
            this.fullPADURLString = this.websiteURL.getText().trim() + '/' + this.padFile.getText().trim();
            this.instructions.setForeground(Submitter.FOREGROUND_FOR_WORKING);
            this.instructions.setBackground(Submitter.BACKGROUND_FOR_WORKING);
            this.response.setForeground(Submitter.FOREGROUND_FOR_WORKING);
            this.response.setBackground(Submitter.BACKGROUND_FOR_WORKING);
            new Thread(this).start();
        }
        else {
            this.submitButton.setEnabled(true);
        }
    }
    
    private void tidyPadFile() {
        String padFileString = this.padFile.getText().trim();
        if (padFileString.length() == 0) {
            return;
        }
        padFileString = ST.trimLeading(padFileString, '/');
        padFileString = ST.trimLeading(padFileString, '\\');
        if (!padFileString.endsWith(".xml")) {
            padFileString += ".xml";
        }
        this.padFile.setText(padFileString);
    }
    
    private void tidyWebsiteURL() {
        String websiteURLString = this.websiteURL.getText().trim();
        if (websiteURLString.length() == 0) {
            return;
        }
        if (!websiteURLString.startsWith("http://")) {
            websiteURLString = "http://" + websiteURLString;
        }
        if (websiteURLString.endsWith("/")) {
            websiteURLString = ST.trimTrailing(websiteURLString, '/');
        }
        this.websiteURL.setText(websiteURLString);
    }
    
    public static void main(final String[] args) {
        final String logDir = (args.length >= 1) ? args[0] : null;
        HybridJ.fireup(new Submitter(logDir), "Mini PAD Submitter 10.8", 680, 475);
    }
    
    static {
        HOW_MANY_WEBSITES = SubmissionSite.values().length;
        BACKGROUND_FOR_BODY = Build.BACKGROUND_FOR_BLENDING;
        BACKGROUND_FOR_INSTRUCTIONS = new Color(16316664);
        BACKGROUND_FOR_WORKING = new Color(24174);
        FOREGROUND_FOR_ALERT = new Color(14423100);
        FOREGROUND_FOR_ALERT_ALT = Color.RED;
        FOREGROUND_FOR_INSTRUCTIONS = new Color(3381521);
        FOREGROUND_FOR_LABEL = Color.BLUE;
        FOREGROUND_FOR_TITLE = new Color(14423100);
        FOREGROUND_FOR_URL = new Color(4465186);
        FOREGROUND_FOR_WORKING = new Color(13434828);
        FONT_FOR_INSTRUCTIONS = FontFactory.build("Dialog", 0, 12);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 15);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
        FONT_FOR_URLS = FontFactory.build("Dialog", 0, 14);
    }
}
