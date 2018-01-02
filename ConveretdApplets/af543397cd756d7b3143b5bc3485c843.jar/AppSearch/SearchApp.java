// 
// Decompiled by Procyon v0.5.30
// 

package AppSearch;

import java.util.StringTokenizer;
import java.net.MalformedURLException;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import borland.jbcl.control.DecoratedFrame;
import java.awt.Component;
import borland.jbcl.layout.XYConstraints;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;
import borland.jbcl.control.BevelPanel;
import java.awt.Label;
import java.net.URL;
import java.awt.Choice;
import java.awt.Button;
import java.awt.TextField;
import borland.jbcl.layout.XYLayout;
import java.applet.Applet;

public class SearchApp extends Applet
{
    XYLayout xYLayout1;
    boolean isStandalone;
    TextField txtSearchField;
    Button butSearch;
    Choice chEngine;
    URL SearchEngineUrl;
    Label lblTitle;
    BevelPanel bevelPanel1;
    
    public SearchApp() {
        this.xYLayout1 = new XYLayout();
        this.isStandalone = false;
        this.txtSearchField = new TextField();
        this.butSearch = new Button();
        this.chEngine = new Choice();
        this.SearchEngineUrl = null;
        this.lblTitle = new Label();
        this.bevelPanel1 = new BevelPanel();
    }
    
    public void init() {
        try {
            this.jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void jbInit() throws Exception {
        this.setBackground(Color.black);
        this.xYLayout1.setWidth(399);
        this.xYLayout1.setHeight(125);
        this.txtSearchField.setBackground(new Color(77, 255, 255));
        this.butSearch.setFont(new Font("TimesRoman", 0, 14));
        this.butSearch.setLabel("Search..");
        this.butSearch.addActionListener(new SearchApp_butSearch_actionAdapter(this));
        this.chEngine.setBackground(new Color(77, 255, 255));
        this.lblTitle.setForeground(Color.red);
        this.lblTitle.setFont(new Font("TimesRoman", 1, 14));
        this.lblTitle.setText("Welcome to Jayesh's Search:");
        this.bevelPanel1.setBackground(new Color(163, 255, 162));
        this.setLayout(this.xYLayout1);
        this.add(this.txtSearchField, new XYConstraints(155, 38, 160, -1));
        this.add(this.butSearch, new XYConstraints(317, 37, 61, 25));
        this.add(this.chEngine, new XYConstraints(17, 40, 129, 32));
        this.add(this.lblTitle, new XYConstraints(3, 11, 392, 21));
        this.add(this.bevelPanel1, new XYConstraints(4, 5, 391, 116));
        this.chEngine.addItem("Yahoo");
        this.chEngine.addItem("AltaVista");
        this.chEngine.addItem("Lycos");
        this.chEngine.addItem("WebCrawler");
        this.chEngine.addItem("InfoSeek");
        this.chEngine.addItem("HotBot");
    }
    
    public void start() {
    }
    
    public void stop() {
    }
    
    public void destroy() {
    }
    
    public String getAppletInfo() {
        return "Applet Information";
    }
    
    public String[][] getParameterInfo() {
        return null;
    }
    
    public static void main(final String[] args) {
        final SearchApp applet = new SearchApp();
        applet.isStandalone = true;
        final DecoratedFrame frame = new DecoratedFrame();
        frame.setTitle("Applet Frame");
        frame.add(applet, "Center");
        applet.init();
        applet.start();
        frame.pack();
        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
        frame.setVisible(true);
    }
    
    void butSearch_actionPerformed(final ActionEvent e) {
        final String StrUrl = this.getStringQuery();
        System.out.println(String.valueOf("Context").concat(String.valueOf(this.getAppletContext())));
        System.out.println(String.valueOf("URL IS :").concat(String.valueOf(StrUrl)));
        try {
            this.SearchEngineUrl = new URL(StrUrl);
        }
        catch (MalformedURLException ee) {
            System.out.println(String.valueOf("Cannot Connect to URL").concat(String.valueOf(ee.getMessage())));
        }
        this.getAppletContext().showDocument(this.SearchEngineUrl, "_blank");
    }
    
    String getStringQuery() {
        String StrResolved = "";
        final StringTokenizer strtk = new StringTokenizer(this.txtSearchField.getText());
        while (strtk.hasMoreTokens()) {
            if (StrResolved.equals("")) {
                StrResolved = strtk.nextToken();
            }
            else {
                StrResolved = String.valueOf(String.valueOf(StrResolved).concat(String.valueOf("+"))).concat(String.valueOf(strtk.nextToken()));
            }
        }
        if (this.chEngine.getSelectedItem().equalsIgnoreCase("yahoo")) {
            StrResolved = String.valueOf("http://av.yahoo.com/bin/query?p=").concat(String.valueOf(StrResolved));
        }
        else if (this.chEngine.getSelectedItem().equalsIgnoreCase("AltaVista")) {
            StrResolved = String.valueOf("http://www.altavista.digital.com/cgi-bin/query?q=").concat(String.valueOf(StrResolved));
        }
        else if (this.chEngine.getSelectedItem().equalsIgnoreCase("Lycos")) {
            StrResolved = String.valueOf("http://www.lycos.com/cgi-bin/pursuit?query=").concat(String.valueOf(StrResolved));
            System.out.println(String.valueOf("Strresolved: ").concat(String.valueOf(StrResolved)));
        }
        else if (this.chEngine.getSelectedItem().equalsIgnoreCase("WebCrawler")) {
            StrResolved = String.valueOf("http://www.webcrawler.com/cgi-bin/WebQuery?searchText=").concat(String.valueOf(StrResolved));
        }
        else if (this.chEngine.getSelectedItem().equalsIgnoreCase("InfoSeek")) {
            StrResolved = String.valueOf("http://www.infoseek.com/Titles?qt=").concat(String.valueOf(StrResolved));
        }
        else if (this.chEngine.getSelectedItem().equalsIgnoreCase("HotBot")) {
            StrResolved = String.valueOf("http://www.search.hotbot.com/hResult.html?MT=").concat(String.valueOf(StrResolved));
        }
        return StrResolved;
    }
}
