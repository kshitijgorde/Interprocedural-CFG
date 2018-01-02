// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.plugin;

import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Dimension;
import de.mud.jta.PluginMessage;
import de.mud.jta.event.SocketRequest;
import java.awt.event.ActionEvent;
import java.awt.MenuItem;
import java.io.StreamTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.LayoutManager;
import de.mud.jta.event.SocketListener;
import de.mud.jta.PluginListener;
import java.awt.Container;
import de.mud.jta.PluginConfig;
import de.mud.jta.event.ConfigurationListener;
import de.mud.jta.PluginBus;
import java.awt.Menu;
import java.awt.Label;
import java.awt.CardLayout;
import java.awt.Panel;
import java.awt.Button;
import java.awt.TextField;
import java.awt.List;
import java.util.Hashtable;
import java.net.URL;
import java.awt.event.ActionListener;
import de.mud.jta.VisualPlugin;
import de.mud.jta.Plugin;

public class MudConnector extends Plugin implements VisualPlugin, Runnable, ActionListener
{
    private static final int debug = 0;
    protected URL listURL;
    protected int step;
    protected Hashtable mudList;
    protected List mudListSelector;
    protected TextField mudName;
    protected TextField mudAddr;
    protected TextField mudPort;
    protected Button connect;
    protected Panel mudListPanel;
    protected CardLayout layouter;
    protected ProgressBar progress;
    protected Label errorLabel;
    protected Menu MCMenu;
    
    public MudConnector(final PluginBus bus, final String id) {
        super(bus, id);
        this.listURL = null;
        this.mudList = null;
        this.mudListSelector = new List();
        bus.registerPluginListener(new ConfigurationListener() {
            public void setConfiguration(final PluginConfig config) {
                final String url = config.getProperty("MudConnector", id, "listURL");
                if (url != null) {
                    try {
                        MudConnector.this.listURL = new URL(url);
                    }
                    catch (Exception e) {
                        MudConnector.this.error("" + e);
                        MudConnector.this.errorLabel.setText("Error: " + e);
                    }
                }
                else {
                    MudConnector.this.error("no listURL specified");
                    MudConnector.this.errorLabel.setText("Missing list URL");
                    MudConnector.this.layouter.show(MudConnector.this.mudListPanel, "ERROR");
                }
                final String sstep = config.getProperty("MudConnector", id, "step");
                try {
                    MudConnector.this.step = Integer.parseInt(sstep);
                }
                catch (Exception e2) {
                    if (sstep != null) {
                        MudConnector.this.error("warning: " + sstep + " is not a number");
                    }
                    MudConnector.this.step = 10;
                }
            }
        });
        bus.registerPluginListener(new SocketListener() {
            public void connect(final String host, final int port) {
                MudConnector.this.setup();
            }
            
            public void disconnect() {
                MudConnector.this.setup();
            }
        });
        (this.mudListPanel = new Panel((LayoutManager)(this.layouter = new CardLayout())) {
            public void update(final Graphics g) {
                this.paint(g);
            }
        }).add("ERROR", this.errorLabel = new Label("Loading ..."));
        Panel panel = new Panel(new BorderLayout());
        panel.add("North", new Label("Loading mud list ... please wait"));
        panel.add("Center", this.progress = new ProgressBar());
        this.mudListPanel.add("PROGRESS", panel);
        panel = new Panel(new BorderLayout());
        panel.add("Center", this.mudListSelector);
        this.mudListPanel.add("MUDLIST", panel);
        panel.add("East", panel = new Panel(new GridLayout(3, 1)));
        panel.add(this.mudName = new TextField(20));
        this.mudName.setEditable(false);
        final Panel apanel = new Panel(new BorderLayout());
        apanel.add("Center", this.mudAddr = new TextField(20));
        this.mudAddr.setEditable(false);
        apanel.add("East", this.mudPort = new TextField(6));
        this.mudPort.setEditable(false);
        panel.add(apanel);
        panel.add(this.connect = new Button("Connect"));
        this.connect.addActionListener(this);
        this.mudListSelector.addActionListener(this);
        this.mudListSelector.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                switch (evt.getStateChange()) {
                    case 1: {
                        final String item = MudConnector.this.mudListSelector.getSelectedItem();
                        MudConnector.this.mudName.setText(item);
                        final Object[] mud = MudConnector.this.mudList.get(item);
                        MudConnector.this.mudAddr.setText((String)mud[0]);
                        MudConnector.this.mudPort.setText(((Integer)mud[1]).toString());
                        break;
                    }
                    case 2: {
                        MudConnector.this.mudName.setText("");
                        MudConnector.this.mudAddr.setText("");
                        MudConnector.this.mudPort.setText("");
                        break;
                    }
                }
            }
        });
        this.layouter.show(this.mudListPanel, "PROGRESS");
        this.MCMenu = new Menu("MudConnector");
    }
    
    private void setup() {
        if (this.mudList == null && this.listURL != null) {
            new Thread(this).start();
        }
    }
    
    public void run() {
        try {
            final Hashtable menuList = new Hashtable();
            this.mudList = new Hashtable();
            final BufferedReader r = new BufferedReader(new InputStreamReader(this.listURL.openStream()));
            final String line = r.readLine();
            int mudCount = 0;
            try {
                mudCount = Integer.parseInt(line);
            }
            catch (NumberFormatException nfe) {
                this.error("number of muds: " + nfe);
            }
            System.out.println("MudConnector: expecting " + mudCount + " mud entries");
            this.progress.setMax(mudCount);
            final StreamTokenizer ts = new StreamTokenizer(r);
            ts.resetSyntax();
            ts.whitespaceChars(0, 9);
            ts.ordinaryChars(32, 255);
            ts.wordChars(32, 255);
            int counter = 0;
            int idx = 0;
            int token;
            while ((token = ts.nextToken()) != -1) {
                final String name = ts.sval;
                if ((token = ts.nextToken()) != -1) {
                    if (token == 10) {
                        this.error(name + ": unexpected end of line" + ", missing host and port");
                    }
                    final String host = ts.sval;
                    Integer port = new Integer(23);
                    if ((token = ts.nextToken()) != -1) {
                        try {
                            if (token == 10) {
                                this.error(name + ": default port 23");
                            }
                            port = new Integer(ts.sval);
                        }
                        catch (NumberFormatException nfe2) {
                            this.error("port for " + name + ": " + nfe2);
                        }
                    }
                    this.mudList.put(name, new Object[] { host, port, new Integer(idx++) });
                    this.mudListSelector.add(name);
                    this.progress.adjust(++counter, name);
                    this.mudListPanel.repaint();
                    Menu subMenu = menuList.get(name.charAt(0) + "");
                    if (subMenu == null) {
                        subMenu = new Menu(name.charAt(0) + "");
                        this.MCMenu.add(subMenu);
                        menuList.put(name.charAt(0) + "", subMenu);
                    }
                    final MenuItem item = new MenuItem(name);
                    item.addActionListener(this);
                    subMenu.add(item);
                }
                while (token != -1 && token != 10) {
                    token = ts.nextToken();
                }
            }
            System.out.println("MudConnector: found " + this.mudList.size() + " entries");
        }
        catch (Exception e) {
            this.error("error: " + e);
            this.errorLabel.setText("Error: " + e);
            this.layouter.show(this.mudListPanel, "ERROR");
        }
        this.layouter.show(this.mudListPanel, "MUDLIST");
    }
    
    public void actionPerformed(final ActionEvent evt) {
        if (evt.getSource() instanceof MenuItem) {
            final String item = evt.getActionCommand();
            final int idx = (int)((Object[])this.mudList.get(item))[2];
            this.mudListSelector.select(idx);
            this.mudListSelector.makeVisible(idx);
            this.mudName.setText(item);
            final Object[] mud = this.mudList.get(item);
            this.mudAddr.setText((String)mud[0]);
            this.mudPort.setText(((Integer)mud[1]).toString());
        }
        final String addr = this.mudAddr.getText();
        String port = this.mudPort.getText();
        if (addr != null) {
            super.bus.broadcast(new SocketRequest());
            if (port == null || port.length() <= 0) {
                port = "23";
            }
            super.bus.broadcast(new SocketRequest(addr, Integer.parseInt(port)));
        }
    }
    
    public Component getPluginVisual() {
        return this.mudListPanel;
    }
    
    public Menu getPluginMenu() {
        return this.MCMenu;
    }
    
    class ProgressBar extends Component
    {
        int max;
        int current;
        String text;
        Dimension size;
        Image backingStore;
        
        ProgressBar() {
            this.size = new Dimension(250, 20);
        }
        
        public void setMax(final int max) {
            this.max = max;
        }
        
        public void update(final Graphics g) {
            this.paint(g);
        }
        
        public void paint(final Graphics g) {
            if (this.backingStore == null) {
                this.backingStore = this.createImage(this.getSize().width, this.getSize().height);
                this.redraw();
            }
            g.drawImage(this.backingStore, 0, 0, this);
        }
        
        private void redraw() {
            if (this.backingStore == null || this.text == null) {
                return;
            }
            final Graphics g = this.backingStore.getGraphics();
            final int width = this.current / this.max * this.getSize().width;
            g.fill3DRect(0, 0, this.getSize().width, this.getSize().height, false);
            g.setColor(this.getBackground());
            g.fill3DRect(0, 0, width, this.getSize().height, true);
            g.setColor(this.getForeground());
            g.setXORMode(this.getBackground());
            g.drawString("" + this.current * 100 / ((this.max > 0) ? this.max : 1) + "%", this.getSize().width / 2 - 15, this.getSize().height / 2);
            g.drawString(this.text, this.getSize().width / 2 - this.getFontMetrics(this.getFont()).stringWidth(this.text) / 2, this.getSize().height / 2 + 12);
            this.paint(this.getGraphics());
        }
        
        public void adjust(final int value, final String name) {
            this.current = value;
            if (value > this.max) {
                this.current = this.max;
            }
            this.text = name;
            if (this.current / MudConnector.this.step == this.current / MudConnector.this.step) {
                this.redraw();
            }
        }
        
        public void setSize(final int width, final int height) {
            this.size = new Dimension(width, height);
        }
        
        public Dimension getPreferredSize() {
            return this.size;
        }
        
        public Dimension getMinimumSize() {
            return this.size;
        }
    }
}
