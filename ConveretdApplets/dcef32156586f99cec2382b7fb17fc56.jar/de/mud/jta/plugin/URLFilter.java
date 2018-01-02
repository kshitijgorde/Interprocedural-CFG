// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.plugin;

import javax.swing.JComponent;
import java.net.URL;
import javax.swing.JLabel;
import java.io.StreamTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import de.mud.jta.PluginConfig;
import de.mud.jta.event.ConfigurationListener;
import de.mud.jta.PluginListener;
import javax.swing.JApplet;
import de.mud.jta.event.AppletListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import de.mud.jta.PluginBus;
import java.applet.AppletContext;
import java.io.PipedOutputStream;
import java.io.PipedInputStream;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JList;
import java.util.Vector;
import de.mud.jta.VisualPlugin;
import de.mud.jta.FilterPlugin;
import de.mud.jta.Plugin;

public class URLFilter extends Plugin implements FilterPlugin, VisualPlugin, Runnable
{
    private static final int debug = 0;
    protected Vector protocols;
    protected JList urlList;
    protected JPanel urlPanel;
    protected JMenu urlMenu;
    protected PipedInputStream pin;
    protected PipedOutputStream pout;
    protected AppletContext context;
    private Vector urlCache;
    protected FilterPlugin source;
    
    public URLFilter(final PluginBus bus, final String id) {
        super(bus, id);
        this.protocols = new Vector();
        this.urlList = new JList();
        this.urlCache = new Vector();
        this.urlPanel = new JPanel(new BorderLayout());
        this.urlList.setVisibleRowCount(4);
        this.urlList.setSelectionMode(0);
        this.urlList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(final ListSelectionEvent e) {
                URLFilter.this.showURL(((JList)e.getSource()).getSelectedValue());
            }
        });
        this.urlPanel.add("Center", this.urlList);
        final JPanel p = new JPanel(new GridLayout(3, 1));
        JButton b = new JButton("Clear List");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                URLFilter.this.urlCache.removeAllElements();
                URLFilter.this.urlList.removeAll();
            }
        });
        p.add(b);
        b = new JButton("Remove URL");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                final String item = URLFilter.this.urlList.getSelectedValue();
                if (item != null) {
                    URLFilter.this.urlCache.removeElement(item);
                    URLFilter.this.urlList.remove(URLFilter.this.urlList.getSelectedIndex());
                }
            }
        });
        p.add(b);
        b = new JButton("Show URL");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                final String item = URLFilter.this.urlList.getSelectedValue();
                if (item != null) {
                    URLFilter.this.showURL(item);
                }
            }
        });
        p.add(b);
        this.urlPanel.add("East", p);
        bus.registerPluginListener(new AppletListener() {
            public void setApplet(final JApplet applet) {
                URLFilter.this.context = applet.getAppletContext();
            }
        });
        bus.registerPluginListener(new ConfigurationListener() {
            public void setConfiguration(final PluginConfig config) {
                final String s;
                if ((s = config.getProperty("URLFilter", id, "protocols")) != null) {
                    int old = -1;
                    for (int idx = s.indexOf(44); idx >= 0; idx = s.indexOf(44, old + 1)) {
                        System.out.println("URLFilter: adding protocol '" + s.substring(old + 1, idx) + "'");
                        URLFilter.this.protocols.addElement(s.substring(old + 1, idx));
                        old = idx;
                    }
                    System.out.println("URLFilter: adding protocol '" + s.substring(old + 1) + "'");
                    URLFilter.this.protocols.addElement(s.substring(old + 1));
                }
                else {
                    URLFilter.this.protocols.addElement("http");
                    URLFilter.this.protocols.addElement("ftp");
                    URLFilter.this.protocols.addElement("gopher");
                    URLFilter.this.protocols.addElement("file");
                }
            }
        });
        this.pin = new PipedInputStream();
        this.pout = new PipedOutputStream();
        try {
            this.pout.connect(this.pin);
        }
        catch (IOException e) {
            System.err.println("URLFilter: error installing recognizer: " + e);
        }
        final Thread recognizer = new Thread(this);
        recognizer.start();
    }
    
    public void run() {
        try {
            final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(this.pin)));
            st.eolIsSignificant(true);
            st.slashSlashComments(false);
            st.slashStarComments(false);
            st.whitespaceChars(0, 31);
            st.ordinaryChar(34);
            st.ordinaryChar(60);
            st.ordinaryChar(62);
            st.ordinaryChar(47);
            st.ordinaryChar(58);
            int token;
            while ((token = st.nextToken()) != -1) {
                if (token == -3) {
                    final String word = st.sval.toLowerCase();
                    if (!this.protocols.contains(word) || st.nextToken() != 58 || st.nextToken() != 47) {
                        continue;
                    }
                    String url = word + ":/";
                    while ((token = st.nextToken()) == -3 || token == 47) {
                        if (token == -3) {
                            url += st.sval;
                        }
                        else {
                            url += (char)token;
                        }
                    }
                    if (url.endsWith(".")) {
                        url = url.substring(0, url.length() - 1);
                    }
                    if (this.urlCache.contains(url)) {
                        continue;
                    }
                    this.urlCache.addElement(url);
                    this.urlList.add(url, new JLabel(url));
                    System.out.println("URLFilter: found \"" + url + "\"");
                }
            }
        }
        catch (IOException e) {
            System.err.println("URLFilter: recognition aborted: " + e);
        }
    }
    
    protected void showURL(final String url) {
        if (this.context == null) {
            System.err.println("URLFilter: no url-viewer available\n");
            return;
        }
        try {
            this.context.showDocument(new URL(url), "URLFilter");
        }
        catch (Exception e) {
            System.err.println("URLFilter: cannot load url: " + e);
        }
    }
    
    public void setFilterSource(final FilterPlugin plugin) {
        this.source = plugin;
    }
    
    public FilterPlugin getFilterSource() {
        return this.source;
    }
    
    public int read(final byte[] b) throws IOException {
        final int n = this.source.read(b);
        if (n > 0) {
            this.pout.write(b, 0, n);
        }
        return n;
    }
    
    public void write(final byte[] b) throws IOException {
        this.source.write(b);
    }
    
    public JComponent getPluginVisual() {
        return this.urlPanel;
    }
    
    public JMenu getPluginMenu() {
        return this.urlMenu;
    }
}
