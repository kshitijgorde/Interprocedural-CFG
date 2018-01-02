import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Image;
import java.awt.List;
import java.awt.Point;
import java.awt.Frame;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class search extends Applet implements Runnable
{
    Thread theengine;
    Frame theframe;
    Point zer;
    List whatisdone;
    int numofen;
    int i;
    String winvar;
    Image logo;
    Button gonow;
    List searchen;
    TextField search;
    String searchstr;
    String selected;
    String[] sites;
    String[] address;
    String[] steps;
    
    public search() {
        this.zer = new Point(0, 0);
        this.numofen = -1;
        this.i = 0;
        this.sites = new String[15];
        this.address = new String[16];
        this.steps = new String[5];
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.gonow && this.searchen.getSelectedItem() == null) {
            this.getAppletContext().showStatus("No Search Engine");
            return true;
        }
        if (event.target == this.gonow && this.searchen.getSelectedItem() == "Search All") {
            (this.searchstr = this.search.getText()).toLowerCase();
            if (this.searchstr == "") {
                this.getAppletContext().showStatus("No Query");
            }
            this.searchstr = this.searchstr.replace(' ', '+');
            this.whatisdone.addItem(String.valueOf(this.steps[0]) + "All");
            this.whatisdone.addItem(this.steps[1]);
            this.getAppletContext().showStatus("Searching...........");
            this.i = 0;
            while (this.i < 15) {
                this.winvar = Integer.toString(this.i);
                try {
                    this.getAppletContext().showDocument(new URL(String.valueOf(this.sites[this.i]) + this.searchstr), "newwin" + this.winvar);
                }
                catch (MalformedURLException ex) {}
                ++this.i;
            }
            this.whatisdone.addItem(this.steps[2]);
            this.whatisdone.addItem(String.valueOf(this.steps[3]) + this.searchstr);
            this.whatisdone.addItem(this.steps[4]);
            this.whatisdone.addItem(" ");
            this.whatisdone.addItem(" ");
            this.whatisdone.addItem(" ");
            this.getAppletContext().showStatus("Searched");
            return true;
        }
        if (event.target == this.gonow) {
            System.out.println("<center> Hello </center>");
            (this.searchstr = this.search.getText()).toLowerCase();
            if (this.searchstr == "") {
                this.getAppletContext().showStatus("No Query");
            }
            this.getAppletContext().showStatus("Searching...........");
            this.searchstr = this.searchstr.replace(' ', '+');
            this.i = 0;
            while (this.i < 15) {
                this.selected = this.searchen.getSelectedItem();
                if (this.selected.equals(this.address[this.i])) {
                    this.numofen = this.i;
                }
                this.getAppletContext().showStatus("Searching Engines: " + this.address[this.i]);
                ++this.i;
            }
            this.whatisdone.addItem(String.valueOf(this.steps[0]) + this.address[this.numofen]);
            this.whatisdone.addItem(this.steps[1]);
            try {
                this.getAppletContext().showDocument(new URL(String.valueOf(this.sites[this.numofen]) + this.searchstr), "prodwin");
            }
            catch (MalformedURLException ex2) {}
            this.whatisdone.addItem(this.steps[2]);
            this.whatisdone.addItem(String.valueOf(this.steps[3]) + this.searchstr);
            this.whatisdone.addItem(this.steps[4]);
            this.whatisdone.addItem(" ");
            this.whatisdone.addItem(" ");
            this.whatisdone.addItem(" ");
            this.getAppletContext().showStatus("Searched");
            return true;
        }
        return false;
    }
    
    public boolean gotFocus(final Event event, final Object o) {
        if (event.target == this.gonow) {
            this.getAppletContext().showStatus("Press To Search");
            return true;
        }
        return false;
    }
    
    public void init() {
        this.sites[0] = "http://www.google.com/search?q=";
        this.sites[1] = "http://search.yahoo.com/bin/search?p=";
        this.sites[2] = "http://search.excite.com/search.gw?lk=webcrawler&s=";
        this.sites[3] = "http://search.ebay.com/search/search.dll?MfcISAPICommand=GetResult&ht=1&SortProperty=MetaEndSort&query=";
        this.sites[4] = "http://search.lycos.com/main/?query=";
        this.sites[5] = "http://download.cnet.com/downloads/1,10150,0-10001-103-0-1-7,00.html?tag=srch&qt=";
        this.sites[6] = "http://www.goto.com/d/search/p/go/?Partner=go_home&Keywords=";
        this.sites[7] = "http://www.aj.com/main/askjeeves.asp?ask=";
        this.sites[8] = "http://search.excite.com/search.gw?c=web&search=";
        this.sites[9] = "http://www.looksmart.com/r_search?look=&key=";
        this.sites[10] = "http://www.searchpower.com/engine/searchtheweb.cgi?query=";
        this.sites[11] = "http://hotbot.lycos.com/?MT=";
        this.sites[12] = "http://cnet.search.com/search?timeout=3&tag=ex.cn.1.srch.cnet&q=";
        this.sites[13] = "http://www.mamma.com/Mamma?p1=&timeout=2&query=";
        this.sites[14] = "http://search.dogpile.com/texis/search?q=";
        this.address[0] = "Google";
        this.address[1] = "Yahoo";
        this.address[2] = "Web Crawler";
        this.address[3] = "E-bay";
        this.address[4] = "Lycos";
        this.address[5] = "C-Net Download";
        this.address[6] = "Go";
        this.address[7] = "Ask Jeeves";
        this.address[8] = "Excite";
        this.address[9] = "Look Smart";
        this.address[10] = "Search Power";
        this.address[11] = "Hot Bot";
        this.address[12] = "C-Net";
        this.address[13] = "Mamma";
        this.address[14] = "Dogpile";
        this.address[15] = "Search All";
        this.steps[0] = "Finding ";
        this.steps[1] = "Searching......";
        this.steps[2] = "Found matching Query for:";
        this.steps[3] = "";
        this.steps[4] = "Done";
        this.searchen = new List(3, false);
        this.theframe = new Frame("Multi-Search");
        this.whatisdone = new List(5, false);
        this.i = 0;
        while (this.i < 16) {
            this.searchen.addItem(this.address[this.i]);
            ++this.i;
        }
        this.logo = this.getImage(this.getDocumentBase(), "searchi.gif");
        this.gonow = new Button("Go!");
        this.search = new TextField(25);
        this.gonow.setForeground(Color.blue);
        this.setBackground(Color.cyan);
        this.add(this.search);
        this.add(this.gonow);
        this.add(this.searchen);
        this.theframe.add(this.whatisdone);
        this.theframe.resize(200, 200);
        this.theframe.show();
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 10) {
            this.deliverEvent(new Event(this.gonow, 1001, null));
            return true;
        }
        return false;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.logo, 82, 75, this);
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        Thread.currentThread();
    }
    
    public void start() {
        if (this.theengine == null) {
            this.theengine = new Thread(this);
        }
        this.theengine.start();
    }
    
    public void stop() {
        this.theframe.dispose();
        if (this.theengine != null && this.theengine.isAlive()) {
            this.theengine.stop();
        }
    }
}
