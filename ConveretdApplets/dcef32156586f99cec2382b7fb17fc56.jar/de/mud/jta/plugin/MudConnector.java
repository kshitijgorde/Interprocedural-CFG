// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.plugin;

import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Dimension;
import javax.swing.JComponent;
import de.mud.jta.PluginMessage;
import de.mud.jta.event.SocketRequest;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import java.io.StreamTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.LayoutManager;
import de.mud.jta.event.ReturnFocusListener;
import de.mud.jta.PluginListener;
import java.awt.Container;
import de.mud.jta.PluginConfig;
import de.mud.jta.event.ConfigurationListener;
import de.mud.jta.PluginBus;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;
import java.util.Map;
import java.net.URL;
import java.awt.event.ActionListener;
import de.mud.jta.VisualPlugin;
import de.mud.jta.Plugin;

public class MudConnector extends Plugin implements VisualPlugin, Runnable, ActionListener
{
    private static final int debug = 0;
    protected URL listURL;
    protected int step;
    protected Map mudList;
    protected JList mudListSelector;
    protected JTextField mudName;
    protected JTextField mudAddr;
    protected JTextField mudPort;
    protected JButton connect;
    protected JPanel mudListPanel;
    protected CardLayout layouter;
    protected ProgressBar progress;
    protected JLabel errorLabel;
    protected JMenu MCMenu;
    
    public MudConnector(final PluginBus bus, final String id) {
        super(bus, id);
        this.listURL = null;
        this.mudList = null;
        this.mudListSelector = new JList();
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
        bus.registerPluginListener(new ReturnFocusListener() {
            public void returnFocus() {
                MudConnector.this.setup();
            }
        });
        (this.mudListPanel = new JPanel((LayoutManager)(this.layouter = new CardLayout())) {
            public void update(final Graphics g) {
                this.paint(g);
            }
        }).add("ERROR", this.errorLabel = new JLabel("Loading ..."));
        JPanel panel = new JPanel(new BorderLayout());
        panel.add("North", new JLabel("Loading mud list ... please wait"));
        panel.add("Center", this.progress = new ProgressBar());
        this.mudListPanel.add("PROGRESS", panel);
        panel = new JPanel(new BorderLayout());
        final JScrollPane scrollPane = new JScrollPane(this.mudListSelector);
        panel.add("Center", scrollPane);
        this.mudListPanel.add("MUDLIST", panel);
        panel.add("East", panel = new JPanel(new GridLayout(3, 1)));
        panel.add(this.mudName = new JTextField(20));
        this.mudName.setEditable(false);
        final JPanel apanel = new JPanel(new BorderLayout());
        apanel.add("Center", this.mudAddr = new JTextField(20));
        this.mudAddr.setEditable(false);
        apanel.add("East", this.mudPort = new JTextField(6));
        this.mudPort.setEditable(false);
        panel.add(apanel);
        panel.add(this.connect = new JButton("Connect"));
        this.connect.addActionListener(this);
        this.mudListSelector.setVisibleRowCount(3);
        this.mudListSelector.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(final ListSelectionEvent evt) {
                final JList list = (JList)evt.getSource();
                list.ensureIndexIsVisible(list.getSelectedIndex());
                final String item = list.getSelectedValue();
                MudConnector.this.mudName.setText(item);
                final Object[] mud = MudConnector.this.mudList.get(item);
                MudConnector.this.mudAddr.setText((String)mud[0]);
                MudConnector.this.mudPort.setText(((Integer)mud[1]).toString());
            }
        });
        this.layouter.show(this.mudListPanel, "PROGRESS");
        this.MCMenu = new JMenu("MudConnector");
    }
    
    private void setup() {
        if (this.mudList == null && this.listURL != null) {
            new Thread(this).start();
        }
    }
    
    public void run() {
        try {
            final Map menuList = new HashMap();
            this.mudList = new HashMap();
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
                    this.progress.adjust(++counter, name);
                    this.mudListPanel.repaint();
                    final String key = ("" + name.charAt(0)).toUpperCase();
                    JMenu subMenu = menuList.get(key);
                    if (subMenu == null) {
                        subMenu = new JMenu(key);
                        this.MCMenu.add(subMenu);
                        menuList.put(key, subMenu);
                    }
                    final JMenuItem item = new JMenuItem(name);
                    item.addActionListener(this);
                    subMenu.add(item);
                }
                while (token != -1 && token != 10) {
                    token = ts.nextToken();
                }
            }
            final List list = new ArrayList(this.mudList.keySet());
            Collections.sort((List<Comparable>)list);
            this.mudListSelector.setListData(list.toArray());
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
            this.mudListSelector.setSelectedIndex(idx);
            this.mudName.setText(item);
            final Object[] mud = this.mudList.get(item);
            this.mudAddr.setText((String)mud[0]);
            this.mudPort.setText(((Integer)mud[1]).toString());
        }
        final String addr = this.mudAddr.getText();
        String port = this.mudPort.getText();
        if (addr != null) {
            this.bus.broadcast(new SocketRequest());
            if (port == null || port.length() <= 0) {
                port = "23";
            }
            this.bus.broadcast(new SocketRequest(addr, Integer.parseInt(port)));
        }
    }
    
    public JComponent getPluginVisual() {
        return this.mudListPanel;
    }
    
    public JMenu getPluginMenu() {
        return this.MCMenu;
    }
    
    class ProgressBar extends JComponent
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
            final String percent = "" + this.current * 100 / ((this.max > 0) ? this.max : 1) + "% / " + this.current + " of " + this.max;
            g.drawString(percent, this.getSize().width / 2 - this.getFontMetrics(this.getFont()).stringWidth(percent) / 2, this.getSize().height / 2);
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
