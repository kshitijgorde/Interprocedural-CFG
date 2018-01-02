import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.net.URLEncoder;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class GooSiteSearch extends Applet implements ActionListener
{
    Panel p;
    Panel pp;
    Panel row1;
    Panel row2;
    Label queryLabel;
    TextField query;
    Button goButton;
    Button vButton;
    Frame popup;
    Label byLabel;
    Label byLabel2;
    Label byLabel3;
    Button visitButton;
    Button closeButton;
    String Head;
    String searchText;
    String Logo;
    String LC;
    String VLC;
    String GALT;
    String GIMP;
    String fgValue;
    String BG;
    String bgValue;
    String TC;
    String lcValue;
    String tbs;
    String target;
    String domain;
    int TS;
    Color bgColor;
    Color fgColor;
    
    public GooSiteSearch() {
        this.p = new Panel();
        this.pp = new Panel();
        this.row1 = new Panel();
        this.row2 = new Panel();
        this.queryLabel = new Label("Search for: ", 1);
        this.query = new TextField();
        this.goButton = new Button("Go");
        this.vButton = new Button("About");
        this.popup = new Frame("About GooSiteSearch");
        this.byLabel = new Label("GooSiteSearch applet", 1);
        this.byLabel2 = new Label("(c) 2001 virtig01", 1);
        this.byLabel3 = new Label("virtig01.cjb.net", 1);
        this.visitButton = new Button("Visit virtig01 Main");
        this.closeButton = new Button("Close");
        this.Head = "http://www.google.com/custom?q=";
        this.searchText = "";
        this.target = "_self";
        this.domain = "virtig01.cjb.net";
        this.TS = 11;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.vButton) {
            if (this.popup.isVisible()) {
                this.popup.dispose();
            }
            else {
                this.popup.show();
                this.popup.setLocation(200, 150);
            }
        }
        else if (source == this.closeButton) {
            this.popup.dispose();
        }
        else if (source == this.visitButton) {
            this.popup.dispose();
            try {
                this.getAppletContext().showDocument(new URL("http://virtig01.cjb.net"), this.target);
            }
            catch (MalformedURLException ex) {}
        }
        else {
            (this.searchText = this.query.getText()).trim();
            final String string = String.valueOf(this.Head) + URLEncoder.encode(this.searchText) + ("&sitesearch=" + this.domain + "&cof=") + URLEncoder.encode(String.valueOf(this.TC) + this.Logo + this.LC + this.BG + this.VLC + this.GALT + this.GIMP) + "&domains=" + URLEncoder.encode(this.domain);
            try {
                this.getAppletContext().showDocument(new URL(string), this.target);
            }
            catch (MalformedURLException ex2) {
                this.showStatus("Search failed");
            }
        }
    }
    
    public void init() {
        this.showStatus("GooSiteSearch loading...");
        if (this.getParameter("domain") != null) {
            this.domain = this.getParameter("domain").trim();
        }
        if (this.domain.startsWith("http://")) {
            this.domain = this.domain.substring(7);
        }
        if (this.domain.endsWith("/")) {
            this.domain = this.domain.substring(0, this.domain.length() - 1);
        }
        if (this.getParameter("target") != null) {
            this.target = this.getParameter("target");
        }
        this.tbs = ((this.getParameter("boxsize") != null) ? this.getParameter("boxsize") : "10");
        this.query.setColumns(Integer.parseInt(this.tbs));
        this.bgValue = ((this.getParameter("bgcolor") != null) ? this.getParameter("bgcolor") : "f5f5f5");
        this.bgColor = new Color(Integer.parseInt(this.bgValue, 16));
        this.BG = "BGC:#" + this.bgValue + ";";
        this.setBackground(this.bgColor);
        this.fgValue = ((this.getParameter("fgcolor") != null) ? this.getParameter("fgcolor") : "4487cc");
        this.fgColor = new Color(Integer.parseInt(this.fgValue, 16));
        this.lcValue = ((this.getParameter("links") != null) ? this.getParameter("links") : "AA0000");
        this.LC = "LC:#" + this.lcValue + ";";
        this.VLC = "VLC:#" + this.lcValue + ";";
        this.GIMP = "VLC:#" + this.lcValue + ";";
        this.TC = "T:#" + this.fgValue + ";";
        this.GALT = "GALT:#" + this.fgValue + ";";
        this.Logo = ((this.getParameter("logo") != null) ? ("L:" + this.getParameter("logo") + ";") : "");
        if (this.getParameter("textsize") != null) {
            this.TS = Integer.parseInt(this.getParameter("textsize"));
        }
        if (this.getParameter("buttontext") != null) {
            this.goButton.setLabel(this.getParameter("buttontext"));
        }
        if (this.getParameter("labelText") != null) {
            this.queryLabel.setText(this.getParameter("labeltext"));
        }
        this.row1.setLayout(new FlowLayout(0));
        this.queryLabel.setFont(new Font("Helvetica", 3, this.TS));
        this.row1.add(this.queryLabel);
        this.query.setFont(new Font("Helvetica", 0, this.TS));
        this.row1.add(this.query);
        this.row2.setLayout(new GridLayout(1, 2, 60, 1));
        this.goButton.setFont(new Font("Helvetica", 3, this.TS));
        this.vButton.setFont(new Font("Helvetica", 2, this.TS - 2));
        this.row2.add(this.vButton);
        this.row2.add(this.goButton);
        this.p.setLayout(new BorderLayout());
        this.p.add(this.row1, "North");
        this.p.add(this.row2, "South");
        this.setLayout(new BorderLayout());
        this.add(this.p, "South");
        this.goButton.addActionListener(this);
        this.vButton.addActionListener(this);
        this.visitButton.addActionListener(this);
        this.closeButton.addActionListener(this);
        this.setBackground(this.bgColor);
        this.setForeground(this.fgColor);
        this.goButton.setBackground(this.fgColor);
        this.goButton.setForeground(this.bgColor);
        this.vButton.setBackground(this.bgColor);
        this.vButton.setForeground(this.fgColor);
        this.vButton.setSize(100, 15);
        this.query.requestFocus();
        this.pp.setLayout(new BorderLayout());
        this.popup.setSize(230, 138);
        this.popup.setBackground(this.bgColor);
        this.popup.setForeground(this.fgColor);
        this.popup.setResizable(false);
        this.byLabel.setFont(new Font("Helvetica", 1, 16));
        this.byLabel2.setFont(new Font("Helvetica", 0, 13));
        this.byLabel3.setFont(new Font("Helvetica", 0, 11));
        this.visitButton.setBackground(this.bgColor);
        this.visitButton.setForeground(this.fgColor);
        this.closeButton.setBackground(this.bgColor);
        this.closeButton.setForeground(this.fgColor);
        this.pp.add(this.byLabel, "North");
        this.pp.add(this.byLabel2, "Center");
        this.pp.add(this.byLabel3, "South");
        this.popup.add(this.pp, "North");
        this.popup.add(this.visitButton, "Center");
        this.popup.add(this.closeButton, "East");
    }
}
