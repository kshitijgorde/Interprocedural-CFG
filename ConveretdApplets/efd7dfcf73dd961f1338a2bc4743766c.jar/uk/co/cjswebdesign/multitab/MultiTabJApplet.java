// 
// Decompiled by Procyon v0.5.30
// 

package uk.co.cjswebdesign.multitab;

import javax.swing.event.HyperlinkEvent;
import javax.swing.border.Border;
import java.awt.Color;
import java.net.URLConnection;
import javax.swing.Icon;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import javax.swing.BorderFactory;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JComponent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.AppletContext;
import java.net.URL;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.event.HyperlinkListener;
import javax.swing.JApplet;

public class MultiTabJApplet extends JApplet implements HyperlinkListener
{
    private boolean isStandalone;
    private boolean debug;
    private JPanel panel_1_;
    private JTabbedPane tp;
    private URL doc;
    private String root;
    private String contentURL;
    private AppletContext context;
    private String linkTarget;
    private String debugParam;
    
    public MultiTabJApplet() {
        this.isStandalone = false;
        this.debug = false;
        this.panel_1_ = new JPanel(new BorderLayout());
        this.tp = new JTabbedPane();
    }
    
    public void init() {
        try {
            this.jbInit();
        }
        catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        catch (IOException ex2) {
            System.out.println(ex2.getMessage());
            ex2.printStackTrace();
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
    }
    
    public void start() {
    }
    
    public void stop() {
    }
    
    public void destroy() {
    }
    
    public String getAppletInfo() {
        return "Tab Applet Information";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "title", "String", "Text displayed in the surrounding title border" }, { "tabalign", "String", "Controls placement of tabs valid values are top,left,bottom,right" }, { "fgred", "Integer", "Number between 0-255, red value of RGB used to set forground color" }, { "fggreen", "Integer", "Number between 0-255, green value of RGB used to set forground color" }, { "fgblue", "Integer", "Number between 0-255, blue value of RGB used to set forground color" }, { "bgred", "Integer", "Number between 0-255, red value of RGB used to set background color" }, { "bggreen", "Integer", "Number between 0-255, green value of RGB used to set background color" }, { "bgblue", "Integer", "Number between 0-255, blue value of RGB used to set background color" }, { "tabcolorred", "Integer", "Number between 0-255, red value of RGB used to set background color" }, { "tabcolorgreen", "Integer", "Number between 0-255, green value of RGB used to set background color" }, { "tabcolorblue", "Integer", "Number between 0-255, blue value of RGB used to set background color" }, { "link_target", "String", "Target where followed link is to be open in" }, { "tabpolicy", "String", "Sets whether tabs should wrap to next line or scroll, values wrap or scroll" }, { "debug", "String", "Trouble shoot applet values true or false" } };
    }
    
    private void jbInit() throws MalformedURLException, IOException {
        final MultiTabJApplet multiTabJApplet = new MultiTabJApplet();
        this.context = this.getAppletContext();
        this.linkTarget = this.getParameter("link_target", "_top");
        this.debugParam = this.getParameter("debug", "false");
        final String parameter = this.getParameter("fgred", "0");
        final String parameter2 = this.getParameter("fggreen", "0");
        final String parameter3 = this.getParameter("fgblue", "0");
        final String parameter4 = this.getParameter("tabcolorred", "100");
        final String parameter5 = this.getParameter("tabcolorblue", "100");
        final String parameter6 = this.getParameter("tabcolorgreen", "100");
        final String parameter7 = this.getParameter("tabalign", "top");
        final String parameter8 = this.getParameter("tabpolicy", "scroll");
        final String parameter9 = this.getParameter("title");
        final String parameter10 = this.getParameter("bgred", "255");
        final String parameter11 = this.getParameter("bggreen", "255");
        final String parameter12 = this.getParameter("bgblue", "255");
        this.doc = this.getDocumentBase();
        this.root = this.doc.toString();
        this.contentURL = this.root.substring(0, this.root.lastIndexOf("/") + 1) + "pages.html";
        this.setDegugging(this.debugParam);
        this.setAppletTitleBorder(parameter9, this.panel_1_);
        this.setComponentRGBBackgroundColor(parameter10, parameter11, parameter12, this);
        this.setComponentRGBBackgroundColor(parameter10, parameter11, parameter12, this.panel_1_);
        this.setAppletTabPolicy(parameter8, this.tp);
        this.setAppletTabPlacement(parameter7, this.tp);
        this.setAppletTabBackground(parameter4, parameter6, parameter5, this.tp);
        this.setAppletTabForeground(parameter, parameter2, parameter3, this.tp);
        if (this.debug) {
            System.out.println("Path to document containg applet= " + this.root);
            System.out.println("Path to content= " + this.contentURL);
            System.out.println("Tab position value: " + this.tp.getTabPlacement());
            System.out.println("Tab policy= " + this.tp.getTabLayoutPolicy());
            System.out.println("Tab foreground color= " + this.tp.getForeground());
            System.out.println("Tab background color= " + this.tp.getForeground());
            System.out.println("Applet background color= " + this.getBackground());
        }
        this.getInfo(this.tp, this.contentURL, this.debug);
        this.panel_1_.add(this.tp, "Center");
        this.getContentPane().add(this.panel_1_, "Center");
        final JLabel label = new JLabel("Evaluation Version");
        final JButton button = new JButton("Register");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    MultiTabJApplet.this.context.showDocument(new URL("http://www.cjswebdesign.co.uk/multitab/"), "_top");
                }
                catch (Exception ex) {}
            }
        });
        final JPanel panel = new JPanel(new FlowLayout(0));
        panel.add(label);
        panel.add(button);
        panel.setBorder(BorderFactory.createRaisedBevelBorder());
        this.panel_1_.add(panel, "South");
    }
    
    public String getParameter(final String s, final String s2) {
        return this.isStandalone ? System.getProperty(s, s2) : ((this.getParameter(s) != null) ? this.getParameter(s) : s2);
    }
    
    private void getInfo(final JTabbedPane tabbedPane, final String s, final boolean b) throws MalformedURLException, IOException {
        final URLConnection openConnection = new URL(s).openConnection();
        final StringBuffer sb = new StringBuffer();
        openConnection.getContentLength();
        final InputStream inputStream = openConnection.getInputStream();
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        final InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream, "ISO-8859-1");
        int read;
        while ((read = inputStreamReader.read()) != -1) {
            sb.append((char)read);
        }
        final String string = sb.toString();
        if (b) {
            System.out.println("Contents of file parsed: ");
            System.out.println(string);
            System.out.println();
        }
        final String[] split = string.split("<html>");
        for (int i = 1; i < split.length; ++i) {
            String substring;
            try {
                substring = split[i].substring(split[i].indexOf("<title>") + 7, split[i].lastIndexOf("</title>"));
            }
            catch (StringIndexOutOfBoundsException ex) {
                substring = null;
            }
            String substring2;
            try {
                substring2 = split[i].substring(split[i].indexOf("<tabtip>") + 8, split[i].indexOf("</tabtip>"));
            }
            catch (StringIndexOutOfBoundsException ex2) {
                substring2 = null;
            }
            final JPanel panel = new JPanel(new BorderLayout());
            final JEditorPane editorPane = new JEditorPane("text/html", split[i]);
            editorPane.setEditable(false);
            editorPane.addHyperlinkListener(this);
            panel.add(new JScrollPane(editorPane), "Center");
            tabbedPane.addTab(substring, null, panel, substring2);
        }
        inputStreamReader.close();
        bufferedInputStream.close();
        inputStream.close();
    }
    
    private void setComponentRGBBackgroundColor(final String s, final String s2, final String s3, final Component component) {
        int int1;
        int int2;
        int int3;
        try {
            int1 = Integer.parseInt(s);
            int2 = Integer.parseInt(s2);
            int3 = Integer.parseInt(s3);
        }
        catch (NumberFormatException ex) {
            System.out.println("Number format exception in background settings using defaults");
            int1 = 255;
            int2 = 255;
            int3 = 255;
        }
        if (int1 < 0 || int1 > 255) {
            System.out.println("Red background color exceeds range, using default");
            int1 = 255;
        }
        if (int2 < 0 || int2 > 255) {
            System.out.println("Green background color exceeds range, using default");
            int2 = 255;
        }
        if (int3 < 0 || int3 > 255) {
            System.out.println("Blue background color exceeds range, using default");
            int3 = 255;
        }
        component.setBackground(new Color(int1, int2, int3));
    }
    
    private void setAppletTitleBorder(final String s, final JComponent component) {
        if (s != null) {
            if (!s.trim().equals("")) {
                component.setBorder(BorderFactory.createTitledBorder(s));
            }
        }
        else {
            component.setBorder(BorderFactory.createEmptyBorder());
        }
    }
    
    private void setDegugging(final String s) {
        if (s.equalsIgnoreCase("true")) {
            this.debug = true;
        }
        else {
            this.debug = false;
        }
    }
    
    private void setAppletTabPolicy(final String s, final JTabbedPane tabbedPane) {
        if (s.equalsIgnoreCase("wrap")) {
            tabbedPane.setTabLayoutPolicy(0);
        }
        else if (s.equalsIgnoreCase("scroll")) {
            tabbedPane.setTabLayoutPolicy(1);
        }
        else {
            tabbedPane.setTabLayoutPolicy(1);
        }
    }
    
    private void setAppletTabPlacement(final String s, final JTabbedPane tabbedPane) {
        if (s.equalsIgnoreCase("top")) {
            tabbedPane.setTabPlacement(1);
        }
        else if (s.equalsIgnoreCase("right")) {
            tabbedPane.setTabPlacement(4);
        }
        else if (s.equalsIgnoreCase("bottom")) {
            tabbedPane.setTabPlacement(3);
        }
        else if (s.equalsIgnoreCase("left")) {
            tabbedPane.setTabPlacement(2);
        }
        else {
            tabbedPane.setTabPlacement(1);
        }
    }
    
    private void setAppletTabBackground(final String s, final String s2, final String s3, final JTabbedPane tabbedPane) {
        int int1;
        int int2;
        int int3;
        try {
            int1 = Integer.parseInt(s);
            int2 = Integer.parseInt(s2);
            int3 = Integer.parseInt(s3);
        }
        catch (NumberFormatException ex) {
            System.out.println("Number format exception in Tabs background settings using defaults");
            int1 = 100;
            int2 = 100;
            int3 = 100;
        }
        if (int1 < 0 || int1 > 255) {
            System.out.println("Tab red background color exceeds range, using default");
            int1 = 100;
        }
        if (int2 < 0 || int2 > 255) {
            System.out.println("Tab green background color exceeds range, using default");
            int2 = 100;
        }
        if (int3 < 0 || int3 > 255) {
            System.out.println("Tab blue background color exceeds range, using default");
            int3 = 100;
        }
        tabbedPane.setBackground(new Color(int1, int2, int3));
    }
    
    private void setAppletTabForeground(final String s, final String s2, final String s3, final JTabbedPane tabbedPane) {
        int int1;
        int int2;
        int int3;
        try {
            int1 = Integer.parseInt(s);
            int2 = Integer.parseInt(s2);
            int3 = Integer.parseInt(s3);
        }
        catch (NumberFormatException ex) {
            System.out.println("Number format exception in Tabs foreground settings using defaults");
            int1 = 0;
            int2 = 0;
            int3 = 0;
        }
        if (int1 < 0 || int1 > 255) {
            System.out.println("Tab red foreground color exceeds range, using default");
            int1 = 0;
        }
        if (int2 < 0 || int2 > 255) {
            System.out.println("Tab green foreground color exceeds range, using default");
            int2 = 0;
        }
        if (int3 < 0 || int3 > 255) {
            System.out.println("Tab blue foreground color exceeds range, using default");
            int3 = 0;
        }
        tabbedPane.setForeground(new Color(int1, int2, int3));
    }
    
    public void hyperlinkUpdate(final HyperlinkEvent hyperlinkEvent) {
        final URL url = hyperlinkEvent.getURL();
        final HyperlinkEvent.EventType eventType = hyperlinkEvent.getEventType();
        hyperlinkEvent.getSource();
        if (eventType == HyperlinkEvent.EventType.ACTIVATED) {
            try {
                this.context.showDocument(url, this.linkTarget);
            }
            catch (Exception ex) {
                this.context.showStatus("Unable to open URL ");
                if (this.debug) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}
