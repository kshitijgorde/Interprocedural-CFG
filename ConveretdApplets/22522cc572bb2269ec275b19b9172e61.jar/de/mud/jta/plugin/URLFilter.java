// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.plugin;

import java.net.URL;
import java.io.StreamTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import de.mud.jta.PluginConfig;
import de.mud.jta.event.ConfigurationListener;
import de.mud.jta.PluginListener;
import java.applet.Applet;
import de.mud.jta.event.AppletListener;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import de.mud.jta.PluginBus;
import java.applet.AppletContext;
import java.io.PipedOutputStream;
import java.io.PipedInputStream;
import java.awt.Menu;
import java.awt.Panel;
import java.awt.List;
import java.util.Vector;
import de.mud.jta.VisualPlugin;
import de.mud.jta.FilterPlugin;
import de.mud.jta.Plugin;

public class URLFilter extends Plugin implements FilterPlugin, VisualPlugin, Runnable
{
    private static final int debug = 0;
    protected Vector protocols;
    protected List urlList;
    protected Panel urlPanel;
    protected Menu urlMenu;
    protected PipedInputStream pin;
    protected PipedOutputStream pout;
    protected AppletContext context;
    private Vector urlCache;
    protected FilterPlugin source;
    
    public URLFilter(final PluginBus bus, final String id) {
        super(bus, id);
        this.protocols = new Vector();
        this.urlList = new List(4, false);
        this.urlCache = new Vector();
        this.urlPanel = new Panel(new BorderLayout());
        this.urlList.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                URLFilter.this.showURL(evt.getActionCommand());
            }
        });
        this.urlPanel.add("Center", this.urlList);
        final Panel p = new Panel(new GridLayout(3, 1));
        Button b = new Button("Clear List");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                URLFilter.this.urlCache.removeAllElements();
                URLFilter.this.urlList.removeAll();
            }
        });
        p.add(b);
        b = new Button("Remove URL");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                final String item = URLFilter.this.urlList.getSelectedItem();
                if (item != null) {
                    URLFilter.this.urlCache.removeElement(item);
                    URLFilter.this.urlList.remove(item);
                }
            }
        });
        p.add(b);
        b = new Button("Show URL");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                final String item = URLFilter.this.urlList.getSelectedItem();
                if (item != null) {
                    URLFilter.this.showURL(item);
                }
            }
        });
        p.add(b);
        this.urlPanel.add("East", p);
        bus.registerPluginListener(new AppletListener() {
            public void setApplet(final Applet applet) {
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
                    this.urlList.add(url);
                    this.urlList.makeVisible(this.urlList.getItemCount() - 1);
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
    
    public Component getPluginVisual() {
        return this.urlPanel;
    }
    
    public Menu getPluginMenu() {
        return this.urlMenu;
    }
}
