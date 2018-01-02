// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jdisplay;

import com.mindprod.common11.Build;
import com.mindprod.common13.HybridJ;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.net.URLConnection;
import java.io.InvalidClassException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.zip.GZIPInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import com.mindprod.common15.FontFactory;
import com.mindprod.common13.JEButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.mindprod.common13.Common13;
import com.mindprod.common11.VersionCheck;
import java.awt.Component;
import com.mindprod.jtokens.Token;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Container;
import java.awt.Color;
import javax.swing.JApplet;

public final class JDisplay extends JApplet
{
    private static final boolean HAS_LINE_NUMBERS_DEFAULT = false;
    private static final int APPLET_HEIGHT = 720;
    private static final int APPLET_WIDTH = 960;
    public static final long serialVersionUID = 30L;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2004-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2011-02-03";
    private static final String TITLE_STRING = "JDisplay";
    private static final String VERSION_STRING = "4.5";
    private static final Color BACKGROUND_FOR_APPLET;
    private BWTokenPanel bwTokenPanel;
    private ColorTokenPanel colorTokenPanel;
    private Container contentPane;
    private Footprint footprint;
    private JButton download;
    private JCheckBox userWantsColor;
    private JCheckBox userWantsLineNumbers;
    private JScrollPane bwTokenPanelScroller;
    private JScrollPane colorTokenPanelScroller;
    private String snippetName;
    private Token[] tokens;
    private final boolean asApplet;
    private boolean hasBar;
    private boolean hasBWPlainTextBeenLoaded;
    
    public JDisplay() {
        this.hasBar = true;
        this.hasBWPlainTextBeenLoaded = false;
        this.asApplet = true;
        this.hasBar = true;
    }
    
    public void destroy() {
        if (this.userWantsColor != null) {
            this.contentPane.remove(this.userWantsColor);
            this.userWantsColor = null;
        }
        if (this.download != null) {
            this.contentPane.remove(this.download);
            this.download = null;
        }
        if (this.userWantsLineNumbers != null) {
            this.contentPane.remove(this.userWantsLineNumbers);
            this.userWantsLineNumbers = null;
        }
        if (this.colorTokenPanelScroller != null && this.colorTokenPanel != null) {
            this.colorTokenPanelScroller.remove(this.colorTokenPanel);
            this.colorTokenPanel = null;
        }
        if (this.colorTokenPanelScroller != null) {
            this.contentPane.remove(this.colorTokenPanelScroller);
            this.colorTokenPanelScroller = null;
        }
        if (this.bwTokenPanelScroller != null && this.bwTokenPanel != null) {
            this.bwTokenPanelScroller.remove(this.bwTokenPanel);
            this.bwTokenPanel = null;
        }
        if (this.bwTokenPanelScroller != null) {
            this.contentPane.remove(this.bwTokenPanelScroller);
            this.bwTokenPanelScroller = null;
        }
    }
    
    public void init() {
        if (!VersionCheck.isJavaVersionOK(1, 5, 0, this)) {
            return;
        }
        Common13.setLaf();
        System.out.println("initialising JDisplay 4.5 released:2011-02-03 build:9410 in Java " + System.getProperty("java.version", "unknown"));
        if (this.asApplet) {
            this.getParams();
        }
        (this.contentPane = this.getContentPane()).setLayout(new GridBagLayout());
        this.contentPane.setBackground(JDisplay.BACKGROUND_FOR_APPLET);
        this.buildComponents();
        this.layoutGridBag();
        this.addListeners();
    }
    
    public void start() {
        if (this.userWantsColor != null) {
            this.userWantsColor.setSelected(true);
            this.redisplay();
        }
    }
    
    private JDisplay(final String snippetName) {
        this.hasBar = true;
        this.hasBWPlainTextBeenLoaded = false;
        this.snippetName = snippetName;
        this.asApplet = false;
        this.hasBar = true;
    }
    
    private void addListeners() {
        this.colorTokenPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent event) {
                JDisplay.this.userWantsColor.setSelected(false);
                JDisplay.this.userWantsLineNumbers.setSelected(false);
                JDisplay.this.redisplay();
            }
        });
        final ItemListener theListener = new ItemListener() {
            public void itemStateChanged(final ItemEvent event) {
                JDisplay.this.redisplay();
            }
        };
        this.userWantsColor.addItemListener(theListener);
        this.userWantsLineNumbers.addItemListener(theListener);
        this.download.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                JDisplay.this.download.setEnabled(false);
                JDisplay.this.download();
                JDisplay.this.download.setEnabled(true);
            }
        });
    }
    
    private void buildComponents() {
        this.userWantsLineNumbers = new JCheckBox("line numbers", false);
        this.userWantsColor = new JCheckBox("Color", true);
        (this.download = new JEButton("download")).setFont(FontFactory.build("Dialog", 1, 12));
        (this.bwTokenPanel = new BWTokenPanel()).setBackground(Color.WHITE);
        this.bwTokenPanel.setFont(FontFactory.build("monospaced", 0, 16));
        this.bwTokenPanel.setVisible(false);
        (this.colorTokenPanel = new ColorTokenPanel()).setBackground(Color.WHITE);
        this.colorTokenPanel.setVisible(false);
        this.fetchTokens();
        this.colorTokenPanel.setTokens(this.tokens, this.footprint.totalLines);
        this.footprint.s2CalcPayloadFootprint(this.tokens, this);
        this.footprint.s4CalcScrollableFootprint(Rendering.APPLET);
        this.footprint.s5CalcIdealAppletFootPrint(Rendering.APPLET, this.hasBar, false, false, false, 1.0f);
        final boolean horBars = this.getWidth() < this.footprint.idealAppletWidth;
        final boolean vertBars = this.getHeight() < this.footprint.idealAppletHeight;
        this.colorTokenPanelScroller = new JScrollPane(this.colorTokenPanel, vertBars ? 22 : 21, horBars ? 32 : 31);
        this.colorTokenPanelScroller.getVerticalScrollBar().setUnitIncrement(23);
        this.colorTokenPanelScroller.setVisible(false);
        this.bwTokenPanelScroller = new JScrollPane(this.bwTokenPanel, vertBars ? 22 : 21, horBars ? 32 : 31);
        this.bwTokenPanelScroller.getVerticalScrollBar().setUnitIncrement(23);
        this.bwTokenPanelScroller.setVisible(false);
    }
    
    private void download() {
        try {
            if (this.asApplet) {
                final URL url = new URL(this.getDocumentBase(), "snippet/" + this.snippetName);
                this.getAppletContext().showDocument(url);
            }
            else {
                System.out.println("download ignored in local mode.");
            }
        }
        catch (MalformedURLException e) {
            System.err.println();
            e.printStackTrace(System.err);
            System.err.println("\u0007problem downloading " + this.snippetName);
            System.err.println();
        }
    }
    
    private void fetchTokens() {
        this.tokens = null;
        this.footprint = null;
        try {
            URL url;
            if (this.asApplet) {
                url = new URL(this.getDocumentBase(), "snippet/ser/" + this.snippetName + ".ser");
            }
            else {
                url = new URL("file:snippet/ser/" + this.snippetName + ".ser");
            }
            final URLConnection urlc = url.openConnection();
            if (urlc == null) {
                throw new IOException("\u0007ailed to connect to document server.");
            }
            urlc.setAllowUserInteraction(false);
            urlc.setDoInput(true);
            urlc.setDoOutput(false);
            urlc.setUseCaches(false);
            urlc.connect();
            final InputStream is = urlc.getInputStream();
            final GZIPInputStream gzis = new GZIPInputStream(is, 4096);
            final ObjectInputStream ois = new ObjectInputStream(gzis);
            final long expectedVersion = 28L;
            final long fileVersion = (long)ois.readObject();
            if (fileVersion != expectedVersion) {
                System.err.println("\u0007Stale " + this.snippetName + " *.ser files are version  " + fileVersion + ". JDisplay is expecting  " + expectedVersion);
                ois.close();
                this.tokens = new Token[0];
                return;
            }
            this.footprint = (Footprint)ois.readObject();
            this.tokens = (Token[])ois.readObject();
            ois.close();
        }
        catch (InvalidClassException e3) {
            System.err.println("\u0007Stale " + this.snippetName);
        }
        catch (ClassNotFoundException e) {
            System.err.println("\u0007Bug: Token class files missing from jar " + e.getMessage());
        }
        catch (IOException e2) {
            System.err.println();
            e2.printStackTrace(System.err);
            System.err.println("\u0007Problem getting compacted source document " + this.snippetName);
            System.err.println();
        }
        if (this.tokens == null) {
            this.tokens = new Token[0];
        }
    }
    
    private boolean getBooleanParameter(final String paramName, final boolean defaultValue) {
        final String boolString = this.getParameter(paramName);
        if (boolString == null) {
            return defaultValue;
        }
        if (boolString.equalsIgnoreCase("true") || boolString.equalsIgnoreCase("yes") || boolString.equalsIgnoreCase("t") || boolString.equalsIgnoreCase("y")) {
            return true;
        }
        if (boolString.equalsIgnoreCase("false") || boolString.equalsIgnoreCase("no") || boolString.equalsIgnoreCase("yes") || boolString.equalsIgnoreCase("f") || boolString.equalsIgnoreCase("n")) {
            return false;
        }
        throw new IllegalArgumentException("JDisplay: " + paramName + " param: " + boolString + " should be true or false.");
    }
    
    private void getParams() {
        this.snippetName = this.getParameter("snippet");
        if (this.snippetName == null) {
            throw new IllegalArgumentException("missing snippet parameter");
        }
        if (this.snippetName.startsWith("snippet/")) {
            this.snippetName = this.snippetName.substring("snippet/".length());
        }
        if (this.snippetName.startsWith("ser/")) {
            this.snippetName = this.snippetName.substring("ser/".length());
        }
        this.hasBar = this.getBooleanParameter("bar", true);
    }
    
    private void layoutGridBag() {
        if (this.hasBar) {
            this.contentPane.add(this.userWantsColor, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 18, 0, new Insets(0, 0, 2, 0), 0, 0));
            this.contentPane.add(this.userWantsLineNumbers, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, 11, 0, new Insets(0, 10, 2, 0), 0, 0));
            this.contentPane.add(this.download, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, 12, 0, new Insets(0, 10, 2, 0), 0, 0));
        }
        this.contentPane.add(this.colorTokenPanelScroller, new GridBagConstraints(0, 1, 3, 1, 1.0, 1.0, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
        this.contentPane.add(this.bwTokenPanelScroller, new GridBagConstraints(0, 2, 3, 1, 1.0, 1.0, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
    }
    
    private void redisplay() {
        final boolean useColor = this.userWantsColor.isSelected();
        final boolean useLineNumbers = this.userWantsLineNumbers.isSelected();
        final int width = useLineNumbers ? this.footprint.scrollableWidthWithLineNumbers : this.footprint.scrollableWidthWithoutLineNumbers;
        final int height = this.footprint.scrollableHeight;
        if (useColor) {
            this.colorTokenPanel.set(width, height, useLineNumbers, this.footprint.lineNumberWidthInPixels);
            this.userWantsLineNumbers.setEnabled(true);
            this.userWantsLineNumbers.setVisible(true);
            this.bwTokenPanelScroller.setVisible(false);
            this.colorTokenPanelScroller.setVisible(true);
            this.bwTokenPanel.setVisible(false);
            this.colorTokenPanel.setVisible(true);
        }
        else {
            this.userWantsLineNumbers.setEnabled(false);
            this.userWantsLineNumbers.setVisible(false);
            this.userWantsLineNumbers.setSelected(false);
            if (!this.hasBWPlainTextBeenLoaded) {
                this.bwTokenPanel.setTokens(this.tokens);
                this.hasBWPlainTextBeenLoaded = true;
            }
            this.colorTokenPanelScroller.setVisible(false);
            this.bwTokenPanelScroller.setVisible(true);
            this.colorTokenPanel.setVisible(false);
            this.bwTokenPanel.setVisible(true);
        }
    }
    
    public static void main(final String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("missing snippet parameter");
        }
        HybridJ.fireup(new JDisplay(args[0]), "JDisplay 4.5", 960, 720);
    }
    
    static {
        BACKGROUND_FOR_APPLET = Build.BACKGROUND_FOR_BLENDING;
    }
}
