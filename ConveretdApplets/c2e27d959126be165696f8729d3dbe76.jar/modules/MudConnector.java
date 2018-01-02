// 
// Decompiled by Procyon v0.5.30
// 

package modules;

import java.applet.Applet;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Graphics;
import java.io.StreamTokenizer;
import java.awt.Container;
import java.net.MalformedURLException;
import java.awt.Event;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.CardLayout;
import java.awt.List;
import java.util.Vector;
import java.net.URL;
import java.awt.Panel;

public class MudConnector extends Panel implements Module, Runnable
{
    private telnet app;
    private String url;
    private URL server;
    private Vector index;
    private List display;
    private CardLayout layouter;
    private Panel progress;
    private Panel address;
    private Label indicator;
    private TextField info;
    private Button connectButton;
    private Button disconnectButton;
    private Button refreshButton;
    private Button showButton;
    
    public MudConnector() {
        this.index = new Vector();
        this.display = new List();
    }
    
    public void addNotify() {
        super.addNotify();
        if (this.url == null) {
            if (this.app.getParameter("mudlist") != null) {
                this.url = this.app.getParameter("mudlist");
                this.loadData();
            }
            else {
                this.indicator.setText("The \"mudlist\" is not set, cannot load data!");
                System.out.println("MudConnector: cannot load data, missing parameter");
            }
        }
    }
    
    public void connect(final String host, final int port) {
    }
    
    public void disconnect() {
    }
    
    public boolean handleEvent(final Event evt) {
        if (evt.target == this.connectButton && evt.id == 1001) {
            if (this.display.getSelectedIndex() < 0 || this.display.getSelectedIndex() > this.index.size()) {
                this.info.setText("You did not select a MUD!");
                return false;
            }
            final String address = this.index.elementAt(this.display.getSelectedIndex());
            final int port = Integer.parseInt(address.substring(address.indexOf(35) + 1));
            this.app.connect(address.substring(0, address.indexOf(35)), port);
            return true;
        }
        else {
            if (evt.target == this.disconnectButton && evt.id == 1001) {
                this.app.disconnect();
            }
            if (evt.target == this.refreshButton && evt.id == 1001) {
                this.loadData();
            }
            if (evt.target != this.showButton || evt.id != 1001) {
                if (evt.target == this.display && evt.id == 701) {
                    final String tmp = this.index.elementAt(this.display.getSelectedIndex());
                    this.info.setText(String.valueOf(tmp.substring(0, tmp.indexOf(35))) + " " + tmp.substring(tmp.indexOf(35) + 1));
                }
                return false;
            }
            if (this.display.getSelectedIndex() < 0 || this.display.getSelectedIndex() > this.index.size()) {
                this.info.setText("You did not select a MUD!");
                return false;
            }
            URL page = null;
            try {
                page = new URL("http://www.mudconnect.com/mud-bin/adv_search.cgi?Mode=MUD&mud=" + this.display.getSelectedItem().replace(' ', '+'));
            }
            catch (MalformedURLException e) {
                this.info.setText("There was an URL error!");
                e.printStackTrace();
                return false;
            }
            ((Applet)this.app).getAppletContext().showDocument(page, "_TOP");
            return true;
        }
    }
    
    private void loadData() {
        this.layouter.show(this, "PROGRESS");
        final Thread t = new Thread(this);
        t.setPriority(1);
        t.start();
    }
    
    public String receive(final String str) {
        return null;
    }
    
    public void run() {
        try {
            System.out.print("MudConnector: loading data...");
            if (this.display.countItems() > 0) {
                this.display.clear();
            }
            this.index = new Vector();
            this.server = new URL(this.url);
            final StreamTokenizer ts = new StreamTokenizer(this.server.openStream());
            ts.resetSyntax();
            ts.whitespaceChars(0, 31);
            ts.wordChars(32, 255);
            final Graphics pg = this.progress.getGraphics();
            int p = 1;
            int max = 1;
            int token = ts.nextToken();
            if (token != -1) {
                try {
                    ts.sval = ts.sval.substring(1);
                    max = Integer.parseInt(ts.sval);
                    System.out.print("[" + max + " muds expected] ");
                    token = ts.nextToken();
                }
                catch (NumberFormatException ex) {
                    System.out.print("'" + ts.sval + "'");
                    System.out.print("[# of muds incorrect, expecting 1000] ");
                    max = 1000;
                }
            }
            while (token != -1) {
                pg.setColor(this.getBackground());
                pg.draw3DRect(this.indicator.location().x - 1, this.indicator.location().y + this.indicator.size().height + 4, this.indicator.size().width + 1, 21, false);
                pg.fill3DRect(this.indicator.location().x, this.indicator.location().y + this.indicator.size().height + 5, p++ * this.indicator.size().width / max, 20, true);
                final String name = ts.sval;
                token = ts.nextToken();
                if (token != 10 && token != -1) {
                    final String host = ts.sval;
                    token = ts.nextToken();
                    int port;
                    try {
                        port = Integer.parseInt(ts.sval);
                    }
                    catch (NumberFormatException ex2) {
                        port = 23;
                    }
                    this.display.addItem(name);
                    this.index.addElement(String.valueOf(host) + "#" + port);
                }
                else {
                    System.out.println("unexpected (" + name + ") " + ((token == -1) ? "EOF" : "EOL"));
                }
                while ((token = ts.nextToken()) != -3 && token != -1) {}
            }
        }
        catch (Exception e) {
            this.indicator.setText("The \"mudlist\" parameter is incorrect, cannot load data!");
            e.printStackTrace();
        }
        System.out.println("(" + this.index.size() + " muds)...done");
        this.layouter.show(this, "ADDRESS");
    }
    
    public void setLoader(final Object loader) {
        if (this.app != null) {
            return;
        }
        this.app = (telnet)loader;
        this.setLayout(this.layouter = new CardLayout());
        this.add("PROGRESS", this.progress = new Panel());
        this.progress.add(this.indicator = new Label("Loading mudlist, please wait..."));
        final GridBagLayout grid = new GridBagLayout();
        (this.address = new Panel()).setLayout(grid);
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridheight = 2;
        constraints.weightx = 2.0;
        constraints.fill = 1;
        grid.setConstraints(this.display, constraints);
        this.address.add(this.display);
        Panel panel = new Panel();
        panel.add(this.showButton = new Button("Info!"));
        panel.add(this.connectButton = new Button("connect"));
        panel.add(this.disconnectButton = new Button("disconnect"));
        panel.add(this.refreshButton = new Button("refresh list"));
        constraints.weightx = 0.0;
        constraints.weightx = 0.0;
        constraints.gridheight = 1;
        constraints.gridwidth = 0;
        constraints.fill = 0;
        grid.setConstraints(panel, constraints);
        this.address.add(panel);
        (panel = new Panel()).add(this.info = new TextField(30));
        this.info.setEditable(false);
        this.address.add(panel);
        grid.setConstraints(panel, constraints);
        this.add("ADDRESS", this.address);
        this.layouter.show(this, "PROGRESS");
    }
}
